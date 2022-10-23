package com.sheva.security.jwt;

import com.sheva.configuration.JwtSecurityConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.jsonwebtoken.Claims.SUBJECT;

@Component
@RequiredArgsConstructor
public class JwtTokenHelper {

    public static final String CREATE_VALUE = "created";

    public static final String ROLES = "roles";

    public static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS512;

    public static final String JWT = "JWT";

    private final JwtSecurityConfig jwtSecurityConfig;

    private String generateToken(Map<String, Object> claims){

        return Jwts.builder().setHeader(generateJWTHeaders()).setClaims(claims).setExpiration(generateExpirationDate())
                .signWith(ALGORITHM, jwtSecurityConfig.getSecret()).compact();
    }

    private Map<String, Object> generateJWTHeaders(){

        Map<String, Object> jwtHeaders = new LinkedHashMap<>();
        jwtHeaders.put("typ", JWT);
        jwtHeaders.put("alg", ALGORITHM.getValue());
        return jwtHeaders;
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public Date getCreatedDateFromToken(String token) {
        return (Date) getClaimsFromToken(token).get(CREATE_VALUE);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    private Claims getClaimsFromToken(String token) {

        return Jwts
                .parser()
                .setSigningKey(jwtSecurityConfig.getSecret())
                .parseClaimsJws(token)
                .getBody();

    }

    private Date generateCurrentDate(){

        return new Date();
    }

    private Date generateExpirationDate(){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND, jwtSecurityConfig.getExpiration());

        return calendar.getTime();
    }

    private Boolean isTokenExpired(String token){

        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(this.generateCurrentDate());

    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset){

        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    public String generateToken(UserDetails userDetails){

        Map<String, Object> claims = new HashMap<>();
        claims.put(SUBJECT, userDetails.getUsername());
        claims.put(CREATE_VALUE, generateCurrentDate());
        claims.put(ROLES, getEncryptedRoles(userDetails));
        return generateToken(claims);
    }

    private List<String> getEncryptedRoles(UserDetails userDetails){
        return userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .map(s->s.replace("ROLE_", ""))
                .map(String::toLowerCase).collect(Collectors.toList());

    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = this.getCreatedDateFromToken(token);
        return !(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset))
                && !(this.isTokenExpired(token));
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            claims.put("created", this.generateCurrentDate());
            refreshedToken = this.generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername());
    }



}
