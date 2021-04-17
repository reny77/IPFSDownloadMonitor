package it.ipfsdownloadmonitor.commons;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import it.ipfsdownloadmonitor.model.Contributor;

public class GeoLiteManager {

	private static GeoLiteManager _INSTANCE = new GeoLiteManager();
	private DatabaseReader dbReader = null; 
	
	private GeoLiteManager() {
		InputStream is = GeoLiteManager.class.getClassLoader().getResourceAsStream("GeoLite2-City.mmdb");
		try {
			this.dbReader = new DatabaseReader.Builder(is).build();
		} catch (IOException e) {
			throw new RuntimeException("GeoLite2-City.mmdb not found", e);
		}
	}
	
	public static synchronized GeoLiteManager getInstance() {
		return _INSTANCE;
	}
	

	public boolean resolve(Contributor contributor) throws IOException, GeoIp2Exception {
		if (contributor.getIp() == null) return false;
		InetAddress ipAddress = InetAddress.getByName(contributor.getIp());
		//		CountryResponse response = dbReader.country(ipAddress);
		CityResponse response = dbReader.city(ipAddress);

		String countryName = response.getCountry().getName();
		String countryCode = response.getCountry().getIsoCode();
		String cityName = response.getCity().getName();
		String postal = response.getPostal().getCode();
		Double lat = response.getLocation().getLatitude();
		Double lng = response.getLocation().getLongitude();
		String state = response.getLeastSpecificSubdivision().getName();
		
		contributor.setCountryName(countryName);
		contributor.setCountryCode(countryCode);
		contributor.setCity(cityName);
		contributor.setLatitude(lat);
		contributor.setLongitude(lng);
		
		return true;
	}

	
	
}

