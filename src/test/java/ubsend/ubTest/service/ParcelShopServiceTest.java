package ubsend.ubTest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@ExtendWith(MockitoExtension.class)
public class ParcelShopServiceTest {

	@InjectMocks
	private ParcelShopService subject;
	
	@Mock
	private AuthService authService;
	
	@Test
	public void shouldCreateCorrectHeaders() {
		String expectedClientID = "expectedID";
		String expectedToken = "expectedToken";
		
		Mockito.when(authService.getBearerToken()).thenReturn(expectedToken);
		Mockito.when(authService.getClientID()).thenReturn(expectedClientID);
		
		HttpHeaders actual = subject.createHttpHeaders();
		
		Assertions.assertTrue(actual.get("clientID").contains(expectedClientID));
		Assertions.assertTrue(actual.get("Authorization").contains("Bearer " + expectedToken));
		Assertions.assertTrue(actual.get("Accept").contains(MediaType.APPLICATION_JSON_VALUE));
	}
	
	@Test
	public void shouldCreateCorrectURL() {
		String expectedURL = "https://staging-api.ubsend.io/v1/parcel-shops/parcel-shops?carrier=fakeCarrier&country=fakeCountry&limit=10";

		String carrier = "fakeCarrier";
		String country = "fakeCountry";
		int limit = 10;
		
		String actual = subject.createURL(carrier, country, limit);
		
		Assertions.assertEquals(expectedURL, actual);
	}
}
