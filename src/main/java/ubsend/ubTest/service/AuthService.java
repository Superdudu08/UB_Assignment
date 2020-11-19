package ubsend.ubTest.service;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import ubsend.ubTest.entities.AuthRequest;
import ubsend.ubTest.entities.LoginResult;

@Service
public class AuthService implements IAuthService{
	
	private String bearerToken;
	private String clientID;
	private static String URL_LOGIN = "https://staging-api.ubsend.io/v1/auth/login";
	
	@Override
	public String getTokenFromUB(AuthRequest authRequest) {
		if(!StringUtils.isEmpty(this.bearerToken)) {
			return this.bearerToken;
		}
		
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
		this.clientID = authRequest.getClientID();
		return this.getBearerToken();
	}
	
	@Override
	public String getBearerToken() {
		return this.bearerToken;
	}
	
	@Override
	public String getClientID() {
		return this.clientID;
	}
}
