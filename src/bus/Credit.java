package bus;

import java.time.LocalDate;

import data.DBHandler;

public class Credit extends Account {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private double annualInterestRate;
	private double extraFees;
	public Credit() {
		super();
		this.annualInterestRate = 0.00;
		this.extraFees = 0.00;
	}
	public Credit(int id, int pin, String name, AccountType type, LocalDate openedDate, double balance,
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
		DBHandler.CreditInsert(Validation.getUserCredit(id), DBHandler.connDB());
	}
	public void deposit() {
		DBHandler.CreditUpdate(this, DBHandler.connDB());
	}
	public void withdraw() {
		DBHandler.CreditUpdate(this, DBHandler.connDB());
	}
	public void checkBalance() {
		System.out.println(this.getBalance());
	}
	public void closeAccount() {
		DBHandler.CreditDelete(this, DBHandler.connDB());
	}
}
