package com.sheva.service.user;

import com.sheva.domain.Gym;
import com.sheva.domain.SystemRoles;
import com.sheva.domain.User;
import com.sheva.domain.UserRole;
import com.sheva.repository.GymSpringDataRepository;
import com.sheva.repository.RoleSpringDataRepository;
import com.sheva.repository.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    private final UserSpringDataRepository userRepository;

    private final RoleSpringDataRepository roleRepository;

    private final GymSpringDataRepository gymRepository;

    public User findUserById(Long id){

        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        }else{
            throw new EntityNotFoundException(
                    String.format("User with this id \"%s\" is not found", id));
        }

    }

    public List<User> findAllTrainersInCity(String city){

        List<User> cityTrainers = new ArrayList<>();
        List<User> trainers = userRepository.findAllByLocationCity(city);
        for (User trainer : trainers) {
            for (UserRole role : trainer.getRoles()) {
                if(role.getRoleName() == SystemRoles.ROLE_TRAINER){
                    cityTrainers.add(trainer);
                }
            }
        }
        return cityTrainers;
    }

    public User findTrainerByNameAndSurname(String name, String surname){

        Optional<User> result = userRepository.findUserByUserNameAndUserSurname(name, surname);
        User trainer = new User();
        if(result.isPresent()) {
            User user = result.get();
            for (UserRole role : user.getRoles()) {
                if(role.getRoleName() == SystemRoles.ROLE_TRAINER) {
                    trainer = user;
                }
                else {
                    throw new EntityNotFoundException(String.format("Trainer \"%s\" \"%s\" is not found", name, surname));
                }
            }
        }
        return trainer;
    }

    public User findClientByClientNameAndSurname(String name, String surname){

        Optional<User> result = userRepository.findUserByUserNameAndUserSurname(name, surname);
        User client = new User();
        if(result.isPresent()) {
            User user = result.get();
            for (UserRole role : user.getRoles()) {
                if(role.getRoleName() == SystemRoles.ROLE_TRAINER) {
                    client = user;
                }
                else {
                    throw new EntityNotFoundException(String.format("Client \"%s\" \"%s\" is not found", name, surname));
                }
            }
        }
        return client;
    }

    @Transactional
    public User createClientAccount(User user){

        User createdUser = userRepository.save(setRole(user, SystemRoles.ROLE_CLIENT));

        userRepository.createRoleRow(roleRepository.findByRoleName(SystemRoles.ROLE_CLIENT).getId(), createdUser.getId(),
                new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
        return createdUser;
    }

    @Transactional
    public User createTrainerAccount(User user, String gymName){

        User trainer = setGymForTrainer(user, gymName);
        User createdUser = userRepository.save(setRole(trainer, SystemRoles.ROLE_TRAINER));

        userRepository.createRoleRow(roleRepository.findByRoleName(SystemRoles.ROLE_TRAINER).getId(), createdUser.getId(),
                new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));

        return createdUser;
    }

    @Transactional
    public User updateUser(User user){
        userRepository.save(user);
        return user;
    }

    private User setGymForTrainer(User user, String gymName){

        Gym gym = gymRepository.findGymByGymName(gymName).get();
        user.setTrainerGym(gym);
        Set<User> trainers = gym.getTrainers();

        Set<User> updatedTrainers = new HashSet<>();
        if (!CollectionUtils.isEmpty(trainers)) {
            updatedTrainers.addAll(trainers);
        }
        gym.setTrainers(updatedTrainers);
        gymRepository.save(gym);
        return user;
    }

    private User setRole(User user, SystemRoles role) {
        Set<UserRole> roles = user.getRoles();

        Set<UserRole> updatedRoles = new HashSet<>();
        if (!CollectionUtils.isEmpty(roles)) {
            updatedRoles.addAll(roles);
        }
        UserRole userRole = roleRepository.findByRoleName(role);
        updatedRoles.add(userRole);

        user.setRoles(updatedRoles);
        return user;
    }


}
