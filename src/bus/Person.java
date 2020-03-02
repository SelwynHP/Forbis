 package bus;

import java.io.Serializable;

public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Variables
	private String firstName;
	private String lastName;
	private Address address;
	private String phoneNumber;
	//Constructors
	public Person() {
		this.firstName = "Undefined";
		this.lastName = "Undefined";
		this.address = new Address();
		this.phoneNumber = "Undefined";
	}
	public Person(String firstName, String lastName, Address address, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	//Get&Set
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	//Methods
	public String toString() {
		return this.firstName + "," + this.lastName + "," + this.address.toString() + "," + this.phoneNumber;
	}
}
