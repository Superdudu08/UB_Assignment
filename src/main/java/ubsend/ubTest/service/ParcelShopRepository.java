package ubsend.ubTest.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import ubsend.ubTest.entities.ParcelShop;

public interface ParcelShopRepository extends MongoRepository<ParcelShop, String>{
	
}
