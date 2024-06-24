package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import request.AuthenticationRequest;
import request.RegisterRequest;
import response.AuthenticationResponse;
import service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	private final AuthService authService;
	  @Autowired
	    public AuthController(AuthService authService) {
	        this.authService = authService;
	    }

	    @PostMapping("/sign-up")
	    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
	        return ResponseEntity.ok(authService.register(request));
	    }

	    @PostMapping("/login")
	    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
	        return ResponseEntity.ok(authService.authenticate(request));
	    }
}
