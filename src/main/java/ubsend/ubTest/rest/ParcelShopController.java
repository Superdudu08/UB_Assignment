package ubsend.ubTest.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ubsend.ubTest.entities.ParcelShop;
import ubsend.ubTest.service.ParcelShopService;

@RestController
public class ParcelShopController {
	
	private final ParcelShopService parcelShopService;
	
	public ParcelShopController(ParcelShopService parcelShopService) {
		this.parcelShopService = parcelShopService;
	}
	
	@GetMapping("/getParcelShops")
	public List<ParcelShop> getParcelShops(@RequestParam(name="carrier") String carrier, @RequestParam(name="country") String country, @RequestParam(name="limit") int limit) {
		return this.parcelShopService.getParcelShopsFromUB(carrier, country, limit);
	}
	
	@GetMapping("/displayShopsInDatabase")
	public List<ParcelShop> displayShopsInDatabase() {
		return this.parcelShopService.getParcelShopsFromDatabase();
	}
}
