package bus;
import java.io.Serializable;
import java.time.LocalDate;

public abstract class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private int id;
	private int pin;
	private String name;
	private AccountType type;
	private LocalDate openedDate;
	private double balance;
	private int transactionLimit;
	private int cid;

	public Account() {
		this.id = 0;
		this.pin = 0000;
		this.name = "Undefined";
		this.type = AccountType.Undefined;
		this.openedDate = LocalDate.of(1990, 01, 10);
		this.balance = 0;
		this.transactionLimit = 0;
		this.cid = 0;
	}
	public Account(int id, int pin, String name, AccountType type, LocalDate openedDate, double balance,
			int transactionLimit, int cid) {
		this.id = id;
		this.pin = pin;
		this.name = name;
		this.type = type;
		this.openedDate = openedDate;
		this.balance = balance;
		this.transactionLimit = transactionLimit;
		this.cid = cid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AccountType getType() {
		return type;
	}
	public void setType(AccountType type) {
		this.type = type;
	}
	public LocalDate getOpenedDate() {
		return openedDate;
	}
	public void setOpenedDate(LocalDate openedDate) {
		this.openedDate = openedDate;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getTransactionLimit() {
		return transactionLimit;
	}
	public void setTransactionLimit(int transactionLimit) {
		this.transactionLimit = transactionLimit;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String toString() {
		return this.id + "," + this.pin + "," + this.name + "," + this.type + "," + this.openedDate + "," + this.balance + "," + this.transactionLimit + "," + this.cid;
	}
	public abstract void openAccount(int id);
	public abstract void deposit();
	public abstract void withdraw();
	public abstract void checkBalance();
	public abstract void closeAccount();
}
