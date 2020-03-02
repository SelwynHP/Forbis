package bus;

public class Customer extends Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Variables
	private int id;
	private String password;
	//Constructors
	public Customer() {
		super();
		this.id = 0;
		this.password = "Undefined";
	}
	public Customer(String firstName, String lastName, Address address, String phoneNumber, int id, String password) {
		super(firstName, lastName, address, phoneNumber);
		this.id = id;
		this.password = password;
	}
	//Get&Set
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//Methods
	public String toString() {
		return this.id + "," + this.password + "," + super.toString();
	}
	public void openAccount() {
		IAccountManipulator.openAccount(this.id);
	}
	public void deposit() {
		IAccountManipulator.deposit();
	}
	public void withdraw() {
		IAccountManipulator.withdraw();
	}
	public void checkBalance() {
		IAccountManipulator.checkBalance();
	}
	public void closeAccount() {
		IAccountManipulator.closeAccount();
	}
	public void listAccounts() {
		IAccountManipulator.listAccounts(this.id);
	}
}
