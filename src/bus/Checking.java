package bus;
import data.DBHandler;
import java.time.LocalDate;

public class Checking extends Account {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private double annualInterestRate;
	private double extraFees;
	public Checking() {
		super();
		this.annualInterestRate = 0.00;
		this.extraFees = 0.00;
	}
	public Checking(int id, int pin, String name, AccountType type, LocalDate openedDate, double balance,
			int transactionLimit, int cid, double annualInterestRate, double extraFees) {
		super(id, pin, name, type, openedDate, balance, transactionLimit, cid);
		this.annualInterestRate = annualInterestRate;
		this.extraFees = extraFees;
	}
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}
	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	public double getExtraFees() {
		return extraFees;
	}
	public void setExtraFees(double extraFees) {
		this.extraFees = extraFees;
	}
	public String toString() {
		return super.toString() + "," + this.annualInterestRate + "," + this.extraFees;
	}
	public void openAccount(int id) {
		DBHandler.CheckingInsert(Validation.getUserChecking(id), DBHandler.connDB());
	}
	public void deposit() {
		DBHandler.CheckingUpdate(this, DBHandler.connDB());
	}
	public void withdraw() {
		DBHandler.CheckingUpdate(this, DBHandler.connDB());
	}
	public void checkBalance() {
		System.out.println(this.getBalance());
	}
	public void closeAccount() {
		DBHandler.CheckingDelete(this, DBHandler.connDB());
	}
}
