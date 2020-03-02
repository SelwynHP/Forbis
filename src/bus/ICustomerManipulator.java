package bus;

import data.DBHandler;

public class ICustomerManipulator {
	public static void addCustomer() {
		Customer c1 = new Customer();
		Address a1 = new Address();
		System.out.println("=============Client Management=============");
		System.out.println("Add a Customer");
		
		System.out.println("Enter the first name");
		c1.setFirstName(Validation.getName());
		System.out.println("Enter the last name");
		c1.setLastName(Validation.getName());
		//Address
		System.out.println("Enter the address");
		System.out.println("Street Number");
		a1.setStreetNumber(Validation.getString());
		System.out.println("Street Name");
		a1.setStreetName(Validation.getString());
		System.out.println("Postal Code");
		a1.setPostalCode(Validation.getString());
		System.out.println("City");
		a1.setCity(Validation.getString());
		System.out.println("Province");
		a1.setProvince(Validation.getString());
		
		c1.setAddress(a1);
		System.out.println("Enter the phone number");
		c1.setPhoneNumber(Validation.getPhone());
		//System.out.println("Enter id");
		System.out.println("Enter password");
		c1.setPassword(Validation.getString());
		c1.setId(Validation.getNextCustomerID());
		DBHandler.CustomerInsert(c1, DBHandler.connDB());
		
		System.out.println("Customer added successfully!\n" + c1.toString());
		Validation.getPause();
	}
}