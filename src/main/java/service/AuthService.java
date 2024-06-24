package service;


import model.Role;
import request.AuthenticationRequest;
import request.RegisterRequest;
import response.AuthenticationResponse;
import dao.UserRepository;
import service.jwt.JwtService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse  register(RegisterRequest request) {
        var role = request.getRole() == 1 ? Role.ADMIN : Role.USER;
        UserDetails user = User.builder()
                .username(request.getEmailid())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(role.name())
                .build();
        if (userRepository.findByEmail(request.getEmailid()).isEmpty()) {
            userRepository.save(user);
        }
        Map<String, Object> extraClaims = new HashMap<>();
        var jwtToken = jwtService.generateToken(extraClaims, user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmailid(),
                request.getPassword()
        ));
        UserDetails user = userRepository.findByEmail(request.getEmailid());
 
        	var jwtToken = jwtService.generateToken(user);
     
        
        return AuthenticationResponse.getToken(jwtToken).build();;
    }
}
