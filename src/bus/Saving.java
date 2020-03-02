package bus;

import java.time.LocalDate;

import data.DBHandler;

public class Saving extends Account {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private double annualInterestRate;
	private double annualGain;
	private double extraFees;
	public Saving() {
		super();
		this.annualInterestRate = 0.00;
		this.annualGain = 0.00;
		this.extraFees = 0.00;
	}
	public Saving(int id, int pin, String name, AccountType type, LocalDate openedDate, double balance,
			int transactionLimit, int cid, double annualInterestRate, double annualGain, double extraFees) {
		super(id, pin, name, type, openedDate, balance, transactionLimit, cid);
		this.annualInterestRate = annualInterestRate;
		this.annualGain = annualGain;
		this.extraFees = extraFees;
	}
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}
	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	public double getAnnualGain() {
		return annualGain;
	}
	public void setAnnualGain(double annualGain) {
		this.annualGain = annualGain;
	}
	public double getExtraFees() {
		return extraFees;
	}
	public void setExtraFees(double extraFees) {
		this.extraFees = extraFees;
	}
	public String toString() {
		return super.toString() + "," + this.annualInterestRate + "," + this.annualGain + "," + this.extraFees;
	}
	public void openAccount(int id) {
		DBHandler.SavingInsert(Validation.getUserSaving(id), DBHandler.connDB());
	}
	public void deposit() {
		DBHandler.SavingUpdate(this, DBHandler.connDB());
	}
	public void withdraw() {
		DBHandler.SavingUpdate(this, DBHandler.connDB());
	}
	public void checkBalance() {
		System.out.println(this.getBalance());
	}
	public void closeAccount() {
		DBHandler.SavingDelete(this, DBHandler.connDB());
	}
}
