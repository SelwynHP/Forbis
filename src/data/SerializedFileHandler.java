package data;
import bus.*;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class SerializedFileHandler {
	public static void SetCustomers(ArrayList<Customer> c1) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Customer.ser"));
		oos.writeObject(c1);
		oos.close();
	}
	public static void SetTransactions(ArrayList<Transaction> t1) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Transaction.ser"));
		oos.writeObject(t1);
		oos.close();
	}
	public static void SetAccounts(ArrayList<Account> a1) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Account.ser"));
		oos.writeObject(a1);
		oos.close();
	}
	public static void SetSavings(ArrayList<Saving> s1) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Saving.ser"));
		oos.writeObject(s1);
		oos.close();
	}
	public static void SetCheckings(ArrayList<Checking> ch1) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Checking.ser"));
		oos.writeObject(ch1);
		oos.close();
	}
	public static void SetCredits(ArrayList<Credit> cd1) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Credit.ser"));
		oos.writeObject(cd1);
		oos.close();
	}
	@SuppressWarnings("unchecked")
	public static ArrayList<Customer> getCustomers() throws FileNotFoundException, IOException, ClassNotFoundException, EOFException {
		ArrayList<Customer> cList = new ArrayList<Customer>();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Customer.ser"));
			cList = (ArrayList<Customer>) ois.readObject();
		ois.close();
		return cList;
	}
	@SuppressWarnings("unchecked")
	public static ArrayList<Checking> getCheckings() throws FileNotFoundException, IOException, ClassNotFoundException, EOFException {
		ArrayList<Checking> cList = new ArrayList<Checking>();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Checking.ser"));
			cList = (ArrayList<Checking>) ois.readObject();
		ois.close();
		return cList;
	}
	@SuppressWarnings("unchecked")
	public static ArrayList<Saving> getSavings() throws FileNotFoundException, IOException, ClassNotFoundException, EOFException {
		ArrayList<Saving> sList = new ArrayList<Saving>();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Saving.ser"));
			sList = (ArrayList<Saving>) ois.readObject();
		ois.close();
		return sList;
	}
	@SuppressWarnings("unchecked")
	public static ArrayList<Credit> getCredits() throws FileNotFoundException, IOException, ClassNotFoundException, EOFException {
		ArrayList<Credit> cList = new ArrayList<Credit>();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Credit.ser"));
			cList = (ArrayList<Credit>) ois.readObject();
		ois.close();
		return cList;
	}
	@SuppressWarnings("unchecked")
	public static ArrayList<Transaction> getTransactions() throws FileNotFoundException, IOException, ClassNotFoundException, EOFException {
		ArrayList<Transaction> tList = new ArrayList<Transaction>();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Transaction.ser"));
			tList = (ArrayList<Transaction>) ois.readObject();
		ois.close();
		return tList;
	}
}
