package ubsend.ubTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ubsend.ubTest.entities.ParcelShop;

@Service
public class ParcelShopService implements IParcelShopService{

	private static String URL_PARCEL = "https://staging-api.ubsend.io/v1/parcel-shops/parcel-shops";
	private final IAuthService authService;
	
	@Autowired
	private ParcelShopRepository repository;
	
	public ParcelShopService(IAuthService authService) {
		this.authService = authService;
	}
	
	@Override
	public List<ParcelShop> getParcelShopsFromUB(String carrier, String country, int limit) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = this.createHttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ResponseEntity<ParcelShop[]> response = restTemplate.exchange(this.createURL(carrier, country, limit), HttpMethod.GET, entity, ParcelShop[].class);
		
		for(ParcelShop p : response.getBody()) {
			this.repository.save(p);
		}
		return this.getParcelShopsFromDatabase();
	}
	
	public String createURL(String carrier, String country, int limit) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_PARCEL)
		        .queryParam("carrier", carrier)
		        .queryParam("country", country)
		        .queryParam("limit", limit);
		return builder.toUriString();
	}
	
	public HttpHeaders createHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("ClientID", this.authService.getClientID());
		headers.add("Authorization", "Bearer " + this.authService.getBearerToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}

	@Override
	public List<ParcelShop> getParcelShopsFromDatabase() {
		return this.repository.findAll();
	}

}
