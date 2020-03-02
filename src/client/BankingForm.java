package client;
import bus.*;
import data.DBHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.EOFException;
import java.sql.Connection;

public class BankingForm {
	//Notes for Teacher
	//Employee Login:
	//id : 1
	//password : admin
	
	//Customer Login:
	//id : 1
	//password : 123
	//Serialize is implemented but no longer in use as it has been replaced with DB.
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException, EOFException {
		//Object Declarations
		Connection connBank = DBHandler.connDB();
		Customer cUser;
		Employee eUser;
		Boolean end = false;
		Boolean cLogin;
		Boolean eLogin;
		Boolean cDone;
		Boolean eDone;
		
		while(!end) {
			cUser = null; eUser = null; cLogin = false; eLogin = false; cDone = false; eDone = false;
			
			System.out.println("===============================");
			System.out.println("          Fortis Bank          ");
			System.out.println("===============================");
			System.out.println("      ~Money in good hands~    ");
			System.out.println("\nWelcome!\n");
			System.out.println("Choose login:\n1 - Client\n2 - Employee");
			switch (Validation.getInteger()) {
			//Client Login
			case 1:
				while (!cLogin){
					System.out.println("==Client Login==");
					System.out.println("Enter your Client ID: ");
					int id = Validation.getInteger();
					System.out.println("Enter your password: ");
					String password = Validation.getString();
					
					for(Customer element : DBHandler.getCustomers(connBank))
					{
						if(id == element.getId() && password.equals(element.getPassword())) {
							cUser = element;
							cLogin = true;
							break;
						}
					}
					
					if(cUser != null)
					{	
						while (!cDone) {
							System.out.println("=============Main Menu=============");
							System.out.println("==Accounts==");
							cUser.listAccounts();
							System.out.println("What would you like to do?");
							System.out.println("\n\t1 - Open an Account"
											+ "\n\t2 - Deposit"
											+ "\n\t3 - Withdraw"
											+ "\n\t4 - Check Balance"
											+ "\n\t5 - Close an Account"
											+ "\n\t6 - Exit");
							switch(Validation.getInteger()) {
							case 1:
								cUser.openAccount();
								break;
							case 2:
								cUser.deposit();
								break;
							case 3:
								cUser.withdraw();
								break;
							case 4:
								cUser.checkBalance();
								break;
							case 5:
								cUser.closeAccount();
								break;
							case 6:
								System.out.println("Are you sure you would like to exit? Y/N");
								if(Validation.getString().charAt(0) == 'Y') {
									cDone = true;
									break;
								}
							default:
								System.out.println("An invalid selection was made. \nPlease try again.");
							}
						}
					}
					else {
						System.out.println("User doesn't exist");
					}
				}
				break;
			//Employee Login
			case 2:
				while (!eLogin){
					System.out.println("==Employee Login==");
					System.out.println("Enter your Employee ID: ");
					int id = Validation.getInteger();
					System.out.println("Enter your password: ");
					String password = Validation.getString();
					
					for(Employee element : DBHandler.getEmployees(connBank))
					{
						if(id == element.getId() && password.equals(element.getPassword())) {
							eUser = element;
							eLogin = true;
							break;
						}
					}
					
					if(eUser != null)
					{	
						while (!eDone) {
							System.out.println("=============Main Menu=============");
							System.out.println("\n\nWhat would you like to do?");
							System.out.println("\n\t1 - Add a Customer"
											+ "\n\t2 - Exit");
							switch(Validation.getInteger()) {
							case 1:
								eUser.addCustomer();
								break;
							case 2:
								System.out.println("Are you sure you would like to exit? Y/N");
								if(Validation.getString().charAt(0) == 'Y') {
									eDone = true;
									break;
								}
							default:
								System.out.println("An invalid selection was made. \nPlease try again.");
							}
						}
					}
					else {
						System.out.println("User doesn't exist");
					}
				}
				break;
			default:
				System.out.println("An invalid selection was made. \nPlease try again.");
			}
			//Logout
			System.out.println("Are you sure you would like to logout? Y/N");
			if(Validation.getString().charAt(0) == 'Y') {
				end = true;
				break;
			}
		}
	}
}
