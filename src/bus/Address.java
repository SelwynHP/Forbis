package bus;
import java.io.Serializable;
public class Address implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	//Variables
	private String streetNumber;
	private String streetName;
	private String postalCode;
	private String city;
	private String province;
	//Constructors
	public Address() {
		this.streetNumber = "Undefined";
		this.streetName = "Undefined";
		this.postalCode = "Undefined";
		this.city = "Undefined";
		this.province = "Undefined";
	}
	public Address(String streetNumber, String streetName, String postalCode, String city, String province) {
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.postalCode = postalCode;
		this.city = city;
		this.province = province;
	}
	//Get&Set
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	//Methods
	public String toString() {
		return this.streetNumber + "," + this.streetName + "," + this.postalCode + "," + this.city + "," + this.province;
	}
}
