package bus;

import data.DBHandler;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

public class IAccountManipulator {
	public static void openAccount(int id) {
		int num;
		
		System.out.println("=============Account Management=============");
		System.out.println("Open an Account");
		System.out.println("What type of Account would you like to open?"
				+ "\n\t1 - Checking"
				+ "\n\t2 - Saving"
				+ "\n\t3 - Credit");
		num = Validation.getInteger();
		
		switch(num) {
		case 1:
			Checking aChecking = new Checking();
			aChecking.openAccount(id);
			Validation.getPause();
			break;
		case 2:
			Saving aSaving = new Saving();
			aSaving.openAccount(id);
			Validation.getPause();
			break;
		case 3:
			Credit aCredit = new Credit();
			aCredit.openAccount(id);
			Validation.getPause();
			break;
		}
	}
	public static void deposit(){
		Connection conn = DBHandler.connDB();
		Transaction t1 = new Transaction();
		int id;
		double amount;
		
		System.out.println("=============Account Management=============");
		System.out.println("Deposit");
		System.out.println("Please enter the account number you which to make your deposit to:");
		id = Validation.getInteger();
		System.out.println("Enter the amount");
		amount = Validation.getDouble();
		
		switch(DBHandler.getAccountType(id, conn).toString()) {
		case "Checking":
			Checking aChecking = DBHandler.getChecking(id, conn);
			aChecking.setBalance(Validation.addition(aChecking.getBalance(), amount));
			aChecking.deposit();
			t1.setDescription("Account Deposit");t1.setPostedDate(LocalDate.now());t1.setAmount(amount);t1.setType(TransactionType.Deposit);t1.setAid(aChecking.getId());
			t1.setRecord();
			Validation.getPause();
			break;
		case "Saving":
			Saving aSaving = DBHandler.getSaving(id, conn);
			aSaving.setBalance(Validation.addition(aSaving.getBalance(), amount));
			aSaving.deposit();
			t1.setDescription("Account Deposit");t1.setPostedDate(LocalDate.now());t1.setAmount(amount);t1.setType(TransactionType.Deposit);t1.setAid(aSaving.getId());
			t1.setRecord();
			Validation.getPause();
			break;
		case "Credit":
			Credit aCredit = DBHandler.getCredit(id, conn);
			aCredit.setBalance(Validation.addition(aCredit.getBalance(), amount));
			t1.setDescription("Account Deposit");t1.setPostedDate(LocalDate.now());t1.setAmount(amount);t1.setType(TransactionType.Deposit);t1.setAid(aCredit.getId());
			aCredit.deposit();
			t1.setRecord();
			Validation.getPause();
			break;
		}
	}
	public static void withdraw() {
		Connection conn = DBHandler.connDB();
		Transaction t1 = new Transaction();
		int id;
		double amount;
		
		System.out.println("=============Account Management=============");
		System.out.println("Withdraw");
		System.out.println("Please enter the account number you which to make your withdraw from:");
		id = Validation.getInteger();
		System.out.println("Enter the amount");
		amount = Validation.getDouble();
		
		switch(DBHandler.getAccountType(id, conn).toString()) {
		case "Checking":
			Checking aChecking = DBHandler.getChecking(id, conn);
			aChecking.setBalance(Validation.substraction(aChecking.getBalance(), amount));
			t1.setDescription("Account Withdraw");t1.setPostedDate(LocalDate.now());t1.setAmount(amount);t1.setType(TransactionType.Withdraw);t1.setAid(aChecking.getId());
			t1.setRecord();
			aChecking.deposit();
			Validation.getPause();
			break;
		case "Saving":
			Saving aSaving = DBHandler.getSaving(id, conn);
			aSaving.setBalance(Validation.substraction(aSaving.getBalance(), amount));
			t1.setDescription("Account Withdraw");t1.setPostedDate(LocalDate.now());t1.setAmount(amount);t1.setType(TransactionType.Withdraw);t1.setAid(aSaving.getId());
			t1.setRecord();
			aSaving.deposit();
			Validation.getPause();
			break;
		case "Credit":
			Credit aCredit = DBHandler.getCredit(id, conn);
			aCredit.setBalance(Validation.substraction(aCredit.getBalance(), amount));
			t1.setDescription("Account Withdraw");t1.setPostedDate(LocalDate.now());t1.setAmount(amount);t1.setType(TransactionType.Withdraw);t1.setAid(aCredit.getId());
			t1.setRecord();
			aCredit.deposit();
			Validation.getPause();
			break;
		}
	}
	public static void checkBalance() {
		Connection conn = DBHandler.connDB();
		int id;
		
		System.out.println("=============Account Management=============");
		System.out.println("Check Balance");
		System.out.println("Please enter the account number you which to check balance:");
		id = Validation.getInteger();
		
		AccountType at = DBHandler.getAccountType(id, conn);
		if(at != null)
		{
			switch(at.toString()) {
			case "Checking":
				Checking aChecking = DBHandler.getChecking(id, conn);
				aChecking.checkBalance();
				Validation.getPause();
				break;
			case "Saving":
				Saving aSaving = DBHandler.getSaving(id, conn);
				aSaving.checkBalance();
				Validation.getPause();
				break;
			case "Credit":
				Credit aCredit = DBHandler.getCredit(id, conn);
				aCredit.checkBalance();
				Validation.getPause();
				break;
			}
		}
		else {
			System.out.println("No accounts founds");
		}
	}
	public static void closeAccount() {
		Connection conn = DBHandler.connDB();
		int id;
		
		System.out.println("=============Account Management=============");
		System.out.println("Close an Account");
		System.out.println("Enter the account id you would like to delete");
		id = Validation.getInteger();
		
		switch(DBHandler.getAccountType(id, conn).toString()) {
		case "Checking":
			Checking aChecking = DBHandler.getChecking(id, conn);
			aChecking.closeAccount();
			Validation.getPause();
			break;
		case "Saving":
			Saving aSaving = DBHandler.getSaving(id, conn);
			aSaving.closeAccount();
			Validation.getPause();
			break;
		case "Credit":
			Credit aCredit = DBHandler.getCredit(id, conn);
			aCredit.closeAccount();
			Validation.getPause();
			break;
		}
	}
	public static void listAccounts(int id) {
		Connection conn = DBHandler.connDB();
		
		ArrayList<Checking> ch1 = DBHandler.getCheckings(conn);
		ArrayList<Saving> s1 = DBHandler.getSavings(conn);
		ArrayList<Credit> c1 = DBHandler.getCredits(conn);
		if(!ch1.isEmpty()) {
			for(Checking element : ch1) {
				System.out.println(element.getId() + "," + element.getType());
			}
		}
		if(!s1.isEmpty()) {
			for(Saving element : s1) {
				System.out.println(element.getId() + "," + element.getType());
			}
		}
		if(!c1.isEmpty()) {
			for(Credit element : c1) {
				System.out.println(element.getId() + "," + element.getType());
			}
		}
	}
}
