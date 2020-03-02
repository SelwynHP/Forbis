package bus;

import java.time.LocalDate;

import data.DBHandler;

public class Transaction {
	private int id;
	private String description;
	private LocalDate postedDate;
	private double amount;
	private TransactionType type;
	private int aid;
	public Transaction() {
		this.id = 0;
		this.description = "Undefined";
		this.postedDate = LocalDate.of(1990, 01, 01);
		this.amount = 0.00;
		this.type = TransactionType.Undefined;
		this.aid = 0;
	}
	public Transaction(int id, String description, LocalDate postedDate, double amount, TransactionType type, int aid) {
		this.id = id;
		this.description = description;
		this.postedDate = postedDate;
		this.amount = amount;
		this.type = type;
		this.aid = aid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(LocalDate postedDate) {
		this.postedDate = postedDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String toString() {
		return this.aid + "," + this.description + "," + this.description + "," + this.postedDate + "," + this.amount + "," + this.type + "," + this.aid;
	}
	public void setRecord() {
		DBHandler.TransactionInsert(this, DBHandler.connDB());
	}
}
