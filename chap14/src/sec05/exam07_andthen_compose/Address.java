package sec05.exam07_andthen_compose;

public class Address {
	private String country;
	private String city;
	
	public Address(String country, String city) {
		this.city=city;
		this.country=country;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}
	
	
}
