package ubsend.ubTest.service;


import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import ubsend.ubTest.entities.AuthRequest;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
	
	@InjectMocks
	private AuthService subject;
	
	@Test
	public void shouldCreateCorrectHeaders() {
		AuthRequest request = new AuthRequest();
		String expectedID = "idTest";
		request.setClientID(expectedID);
		
		HttpHeaders actual = subject.createHttpHeaders(request);
		
		Assertions.assertTrue(actual.get("ClientID").contains(expectedID));
		Assertions.assertEquals(MediaType.APPLICATION_JSON, actual.getContentType());
	}
	
	@Test
	public void shouldCreateCorrectJSON() throws JSONException {
		AuthRequest request = new AuthRequest();
		String expectedUserName = "expectedName";
		String expectedPass = "expectedPass";
		request.setPassword(expectedPass);
		request.setUsername(expectedUserName);
		
		JSONObject actual = subject.createJSONCredentials(request);
		
		Assertions.assertEquals(expectedUserName, actual.get("username"));
		Assertions.assertEquals(expectedPass, actual.get("password"));
	}
}
