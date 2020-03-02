package bus;

import java.util.regex.Pattern;

import data.DBHandler;

import java.time.LocalDate;
import java.util.Scanner;

public class Validation {
	//Input Validation
	public static boolean isName(String name) {
		Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
		if(pattern.matcher(name).matches()) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean isPhone(String name) {
		Pattern pattern = Pattern.compile("^\\d{3}[-\\s]\\d{3}[-\\s]\\d{4}+$");
		if(pattern.matcher(name).matches()) {
			return true;
		}
		else {
			return false;
		}
	}
	public static String getString() {
		String myString;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		myString = input.next();
		return myString;
	}
	public static String getPhone() {
		String phone;
		Boolean valid = false;
		
		phone = Validation.getString();
		while(!valid) {
			if(Validation.isPhone(phone)) {
				valid = true;
			}
			else {
				System.out.println("Invalid entry! Try again.\nApprouved Format: 555-555-5555");
				phone = Validation.getString();
			}
		}
		return phone;
	}
	public static String getName() {
		String name;
		int maxChances = 3;
		boolean valid;
		//Scanner input = new Scanner(System.in);
		
		for(int i = 0;i<maxChances;++i) {
			name = Validation.getString();
			valid = Validation.isName(name);
			if(valid) {
				return name;
			}
			else {
				System.out.println("Invalid Entry!\n"
						+ "Your entry must only contain letters.\n"
						+ "Special Characters and/or numbers are NOT accepted.");
			}
			System.out.println("Attempts left" + (maxChances - i));
		}
		return null;
	}
	public static int getInteger() {
		String str;
		int num = 0;
		
		str = Validation.getString();
		
		try {
			num = Integer.parseInt(str);
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid Entry!\n"
					+ "Only integers are accepted." + e.getMessage());
		}
		return num;
	}
	public static double getDouble() {
		String str;
		double num = 0;
		
		str = Validation.getString();
		
		try {
			num = Double.parseDouble(str);
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid Entry!\n"
					+ "Only integers are accepted." + e.getMessage());
		}
		return num;
	}
	//Object Validation
	public static Checking getUserChecking(int id) {
		Checking ch1 = new Checking();
		//Auto-Generated ID
		
		System.out.println("Enter the PIN");
		ch1.setPin(Validation.getInteger());
		ch1.setName("Basic Checking");
		ch1.setType(AccountType.Checking);
		ch1.setOpenedDate(LocalDate.now());
		ch1.setBalance(0.00);
		ch1.setTransactionLimit(3);
		ch1.setCid(id);
		ch1.setAnnualInterestRate(3.9);
		ch1.setExtraFees(12.0);
		
		return ch1;
	}
	public static Saving getUserSaving(int id) {
		Saving aSaving = new Saving();
		//Auto-Generated ID
		
		System.out.println("Enter the PIN");
		aSaving.setPin(Validation.getInteger());
		aSaving.setName("Basic Saving");
		aSaving.setType(AccountType.Saving);
		aSaving.setOpenedDate(LocalDate.now());
		aSaving.setBalance(0.00);
		aSaving.setTransactionLimit(3);
		aSaving.setCid(id);
		aSaving.setAnnualInterestRate(3.9);
		aSaving.setAnnualGain(1.2);
		aSaving.setExtraFees(12.0);
		
		return aSaving;
	}
	public static Credit getUserCredit(int id) {
		Credit aCredit = new Credit();
		//Auto-Generated ID
		
		System.out.println("Enter the PIN");
		aCredit.setPin(Validation.getInteger());
		aCredit.setName("Basic Checking");
		aCredit.setType(AccountType.Credit);
		aCredit.setOpenedDate(LocalDate.now());
		aCredit.setBalance(0.00);
		aCredit.setTransactionLimit(3);
		aCredit.setCid(id);
		aCredit.setAnnualInterestRate(3.9);
		aCredit.setExtraFees(12.0);
		
		return aCredit;
	}
	public static void getUserTransaction() {
		
	}
	public static int getNextCustomerID() {
		Boolean valid = false;
		int id = -1;
		while(!valid) {
			id = DBHandler.getNextCustomerID(DBHandler.connDB());
			if(id == -1) {
				System.out.println("An error when getting the id. Try again.");
			}
			else {
				valid = true;
			}
		}
		return id + 1;
	}
	public static void getPause() {
		@SuppressWarnings("resource")
		Scanner wait = new Scanner(System.in);
		System.out.println("Press Enter to Continue...");
		wait.nextLine();
	}
	public static double addition(double n1, double n2) {
		return n1 + n2;
	}
	public static double substraction(double n1, double n2) {
		return n1 - n2;
	}
}