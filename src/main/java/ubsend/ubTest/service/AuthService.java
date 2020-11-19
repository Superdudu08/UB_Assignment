package ubsend.ubTest.service;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ubsend.ubTest.entities.AuthRequest;
import ubsend.ubTest.entities.LoginResult;

@Service
public class AuthService implements IAuthService{
	
	private String bearerToken;
	private static String URL_LOGIN = "https://staging-api.ubsend.io/v1/auth/login";
	
	@Override
	public String getTokenFromUB(AuthRequest authRequest) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("ClientID", authRequest.getClientID());
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		JSONObject userCredentials = new JSONObject();
		userCredentials.put("username", authRequest.getUsername());
		userCredentials.put("password", authRequest.getPassword());		
		
		HttpEntity<String> request = new HttpEntity<String>(userCredentials.toString(),headers);
		
		LoginResult result = restTemplate.postForObject(URL_LOGIN, request, LoginResult.class);
		this.bearerToken = result.getAccessToken();
		return this.bearerToken;
	}
	
	@Override
	public String getBearerToken() {
		return this.bearerToken;
	}
}
