package ubsend.ubTest.service;

import ubsend.ubTest.entities.AuthRequest;

public interface IAuthService {

	String getTokenFromUB(AuthRequest request);
	String getBearerToken();
}
