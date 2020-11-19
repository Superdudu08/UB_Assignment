package ubsend.ubTest.service;

import java.util.List;

import ubsend.ubTest.entities.ParcelShop;

public interface IParcelShopService {
	public List<ParcelShop> getParcelShopsFromUB(String carrier, String country, int limit);
	public List<ParcelShop> getParcelShopsFromDatabase();
}
