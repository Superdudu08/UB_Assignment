package ubsend.ubTest.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ubsend.ubTest.entities.AuthRequest;
import ubsend.ubTest.service.IAuthService;

@RestController
public class AuthController {
	
	private final IAuthService authService;
	
	public AuthController(IAuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/auth")
	public String authToUBSend(@RequestBody AuthRequest authRequest) {
		return this.authService.getTokenFromUB(authRequest);
	}
}
