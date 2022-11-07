package com.sheva.controller;

import com.sheva.controller.requests.AuthRequest;
import com.sheva.controller.responses.AuthResponse;
import com.sheva.security.jwt.JwtTokenHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenHelper jwtTokenHelper;

    private final UserDetailsService userDetailsService;

    @Operation(summary = "Login user in system",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful authorization",
                            content = @Content),
                    @ApiResponse(responseCode = "400", description = "Request error", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
            })
    @PostMapping
    public ResponseEntity<AuthResponse> loginUser(@RequestBody @Valid AuthRequest request) {

        /*Check login and password*/
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        /*Generate token with answer to user*/
        return ResponseEntity.ok(
                AuthResponse
                        .builder()
                        .userName(request.getLogin())
                        .token(jwtTokenHelper.generateToken(userDetailsService.loadUserByUsername(request.getLogin())))
                        .build()
        );
    }
}
