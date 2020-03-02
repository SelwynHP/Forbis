package data;
import bus.*;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBHandler {
	public static Connection connDB() {
		Connection conn = null;
		String username = "system";
		String password = "123";
		String service = "localhost";
		String url = "jdbc:oracle:thin:";
		
		try {
			conn = DriverManager.getConnection(url+username+"/"+password+"@"+service);
			//System.out.println("Connection was successful!");
		}
		catch(SQLException e){
			System.out.println("An error has occured...");
		}
		return conn;
	}
	public static void CustomerInsert(Customer c1, Connection conn) {
		String insertQuery = "Insert into CUSTOMERS values (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(insertQuery);
			ps.setString(1, c1.getFirstName());
			ps.setString(2, c1.getLastName());
			ps.setString(3, c1.getAddress().toString());
			ps.setString(4, c1.getPhoneNumber());
			ps.setInt(5, c1.getId());
			ps.setString(6, c1.getPassword());
			
			ps.executeUpdate();
			conn.commit();
		}
		catch(SQLException e) {
			System.out.println("An Error has occured for INSERT Statement");
		}
	}
	public static void CustomerUpdate(Customer c1, Connection conn) {
		String updateQuery = "UPDATE CUSTOMERS "
				+ "SET firstName = ?,"
				+ "lastName = ?,"
				+ "address = ?,"
				+ "phoneNumber = ?,"
				+ "id = ?,"
				+ "password = ? "
				+ "WHERE id = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(updateQuery);
			ps.setString(1, c1.getFirstName());
			ps.setString(2, c1.getLastName());
			ps.setString(3, c1.getAddress().toString());
			ps.setString(4, c1.getPhoneNumber());
			ps.setInt(5, c1.getId());
			ps.setString(6, c1.getPassword());
			ps.setInt(7, c1.getId());
			
			ps.executeUpdate();
			conn.commit();
		}
		catch(SQLException e) {
			System.out.println("An Error has occured for UPDATE Statement");
		}
	}
	public static void CustomerDelete(Customer c1, Connection conn) {
		String deleteQuery = "DELETE from CUSTOMERS "
				+ "WHERE id = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(deleteQuery);
			ps.setInt(1, c1.getId());
			
			ps.executeUpdate();
			conn.commit();
		}
		catch (SQLException e) {
			System.out.println("An error has occured for DELETE Statement");
		}
	}
	public static Customer getCustomer(int id, Connection conn) {
		Customer c1 = null;
		Address anAddress = null;
		PreparedStatement ps;
		ResultSet resultSet;
		String selectQuery = "SELECT * from CUSTOMERS "
				+ "WHERE id = ?";
		try {
			ps = conn.prepareStatement(selectQuery);
			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			while(resultSet.next()) {
				c1 = new Customer();
				anAddress = new Address();
				c1.setFirstName(resultSet.getString(1));
				c1.setLastName(resultSet.getString(2));
				
				String[] line = resultSet.getString(3).split(",");
				anAddress.setStreetNumber(line[0]);
				anAddress.setStreetName(line[1]);
				anAddress.setPostalCode(line[2]);
				anAddress.setCity(line[3]);
				anAddress.setProvince(line[4]);
				
				c1.setAddress(anAddress);
				c1.setPhoneNumber(resultSet.getString(4));
				c1.setId(resultSet.getInt(5));
				c1.setPassword(resultSet.getString(6));
			}
		}
		catch(SQLException e) {
			System.out.println("An error has occured from SELECT statement.");
		}
		return c1;
	}
	public static ArrayList<Customer> getCustomers(Connection conn) {
		ArrayList<Customer> cList = new ArrayList<Customer>();
		Customer c1;
		Address anAddress;
		String selectQuery = "SELECT * from CUSTOMERS";
		try {
			Statement command = conn.createStatement();
			ResultSet resultSet = command.executeQuery(selectQuery);
			while(resultSet.next()) {
				c1 = new Customer();
				anAddress = new Address();
				c1.setFirstName(resultSet.getString(1));
				c1.setLastName(resultSet.getString(2));
				
				String[] line = resultSet.getString(3).split(",");
				anAddress.setStreetNumber(line[0]);
				anAddress.setStreetName(line[1]);
				anAddress.setPostalCode(line[2]);
				anAddress.setCity(line[3]);
				anAddress.setProvince(line[4]);
				
				c1.setAddress(anAddress);
				c1.setPhoneNumber(resultSet.getString(4));
				c1.setId(resultSet.getInt(5));
				c1.setPassword(resultSet.getString(6));
				
				cList.add(c1);
			}
		}
		catch(SQLException e) {
			System.out.println("An error has occured from SELECT Statement.");
		}
		
		return cList;
	}
	public static int getNextCustomerID(Connection conn) {
		int nextID;
		String selectQuery = "SELECT MAX(id) from CUSTOMERS";
		try {
			Statement command = conn.createStatement();
			ResultSet resultSet = command.executeQuery(selectQuery);
			while(resultSet.next()) {
				nextID = resultSet.getInt(1);
				return nextID;
			}
		}
		catch(SQLException e) {
			System.out.println("An error has occured from SELECT Statement.");
		}
		
		return -1;
	}
	public static ArrayList<Employee> getEmployees(Connection conn){
		String selectQuery = "SELECT * FROM EMPLOYEES";
		ArrayList<Employee> eList = new ArrayList<Employee>();
		Employee e1;
		Address address;
		
		Statement cmd;
		ResultSet rs;
		try {
			cmd = conn.createStatement();
			rs = cmd.executeQuery(selectQuery);
			while(rs.next()) {
				e1 = new Employee();
				address = new Address();
				e1.setFirstName(rs.getString(1));
				e1.setLastName(rs.getString(2));
				
				String[] line = rs.getString(3).split(",");
				address.setStreetNumber(line[0]);
				address.setStreetName(line[1]);
				address.setPostalCode(line[2]);
				address.setCity(line[3]);
				address.setProvince(line[4]);
				
				e1.setAddress(address);
				e1.setPhoneNumber(rs.getString(4));
				e1.setId(rs.getInt(5));
				e1.setType(EmployeeType.valueOf(rs.getString(6)));
				e1.setPassword(rs.getString(7));
				
				eList.add(e1);
			}
		}
		catch (SQLException e) {
			System.out.println("An error has occured with SELECT Statement\n" + e.getMessage());
		}
		return eList;
	}
	public static void CheckingInsert(Checking ch1, Connection conn) {
		String insertQuery = "INSERT INTO ACCOUNTS(id, pin, name, type, openedDate, balance, transactionLimit, cid, annualInterestRate, extraFees) "
				+ "VALUES(account_seq.NEXTVAL,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(insertQuery);
			//ps.setString(1, "account_seq.NEXTVAL");
			ps.setInt(1, ch1.getPin());
			ps.setString(2, ch1.getName());
			ps.setString(3, ch1.getType().toString());
			ps.setString(4, ch1.getOpenedDate().toString());
			ps.setDouble(5, ch1.getBalance());
			ps.setInt(6, ch1.getTransactionLimit());
			ps.setInt(7, ch1.getCid());
			ps.setDouble(8, ch1.getAnnualInterestRate());
			ps.setDouble(9, ch1.getExtraFees());
			ps.executeUpdate();
			conn.commit();
		}
		catch(SQLException e) {
			System.out.println("an error has occured from INSERT Statement.");
		}
	}
	public static void CheckingUpdate(Checking ch1, Connection conn) {
		String updateQuery = "UPDATE ACCOUNTS "
				+ "SET pin = ?,"
				+ "name = ?,"
				+ "type = ?,"
				+ "openedDate = ?,"
				+ "balance = ?,"
				+ "transactionLimit = ?,"
				+ "annualInterestRate = ?,"
				+ "extraFees = ? "
				+ "WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(updateQuery);
			//ps.setString(1, "account_seq.NEXTVAL");
			ps.setInt(1, ch1.getPin());
			ps.setString(2, ch1.getName());
			ps.setString(3, ch1.getType().toString());
			ps.setString(4, ch1.getOpenedDate().toString());
			ps.setDouble(5, ch1.getBalance());
			ps.setInt(6, ch1.getTransactionLimit());
			ps.setDouble(7, ch1.getAnnualInterestRate());
			ps.setDouble(8, ch1.getExtraFees());
			ps.setInt(9, ch1.getId());
			ps.executeUpdate();
			conn.commit();
		}
		catch(SQLException e) {
			System.out.println("An Error has occured for UPDATE Statement");
		}
	}
	public static void CheckingDelete(Checking ch1, Connection conn) {
		String deleteQuery = "DELETE FROM ACCOUNTS "
				+ "WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(deleteQuery);
			ps.setInt(1, ch1.getId());
			ps.executeUpdate();
			conn.commit();
		}
		catch(SQLException e) {
			System.out.println("An error has occured from DELETE Statement.");
		}
	}
	public static Checking getChecking(int id, Connection conn) {
		Checking ch1 = null;
		String selectQuery = "SELECT id, pin, name, type, openedDate, balance, transactionLimit, cid, annualInterestRate, extraFees " + 
				"FROM ACCOUNTS "
				+ "WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(selectQuery);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ch1 = new Checking();
				ch1.setId(rs.getInt(1));
				ch1.setPin(rs.getInt(2));
				ch1.setName(rs.getString(3));
				ch1.setType(AccountType.valueOf(rs.getString(4)));
				
				String[] line = rs.getString(5).split("-");
				
				ch1.setOpenedDate(LocalDate.of(Integer.valueOf(line[0]), Integer.valueOf(line[1]), Integer.valueOf(line[2])));
				ch1.setBalance(rs.getDouble(6));
				ch1.setTransactionLimit(rs.getInt(7));
				ch1.setCid(rs.getInt(8));
				ch1.setAnnualInterestRate(rs.getDouble(9));
				ch1.setExtraFees(rs.getDouble(10));
				break;
			}
		}
		catch(SQLException e) {
			System.out.println("An error has occured from SELECT Statement.");
		}
		return ch1;
	}
	public static ArrayList<Checking> getCheckings(Connection conn){
		ArrayList<Checking> chList = new ArrayList<Checking>();
		Checking ch1 = null;
		String selectQuery = "SELECT * FROM ACCOUNTS "
				+ "WHERE type = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(selectQuery);
			ps.setString(1, AccountType.Checking.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ch1 = new Checking();
				ch1.setId(rs.getInt(1));
				ch1.setPin(rs.getInt(2));
				ch1.setName(rs.getString(3));
				ch1.setType(AccountType.valueOf(rs.getString(4)));
				
				String[] line = rs.getString(5).split("-");
				
				ch1.setOpenedDate(LocalDate.of(Integer.valueOf(line[0]), Integer.valueOf(line[1]), Integer.valueOf(line[2])));
				ch1.setBalance(rs.getDouble(6));
				ch1.setTransactionLimit(rs.getInt(7));
				ch1.setCid(rs.getInt(8));
				ch1.setAnnualInterestRate(rs.getDouble(9));
				ch1.setExtraFees(rs.getDouble(10));
				
				chList.add(ch1);
			}
		}
		catch(SQLException e) {
			System.out.println("An error has occured from SELECT Statement.");
		}
		return chList;
	}
	public static void SavingInsert(Saving s1, Connection conn) {
		String insertQuery = "INSERT INTO ACCOUNTS(id, pin, name, type, openedDate, balance, transactionLimit, cid, annualInterestRate, annualGain, extraFees) "
				+ "VALUES(account_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(insertQuery);
			//ps.setString(1, "account_seq.NEXTVAL");
			ps.setInt(1, s1.getPin());
			ps.setString(2, s1.getName());
			ps.setString(3, s1.getType().toString());
			ps.setString(4, s1.getOpenedDate().toString());
			ps.setDouble(5, s1.getBalance());
			ps.setInt(6, s1.getTransactionLimit());
			ps.setInt(7, s1.getCid());
			ps.setDouble(8, s1.getAnnualInterestRate());
			ps.setDouble(9, s1.getAnnualGain());
			ps.setDouble(10, s1.getExtraFees());
			ps.executeUpdate();
			conn.commit();
		}
		catch(SQLException e) {
			System.out.println("an error has occured from INSERT Statement.");
		}
	}
	public static void SavingUpdate(Saving s1, Connection conn) {
		String updateQuery = "UPDATE ACCOUNTS "
				+ "SET pin = ?,"
				+ "name = ?,"
				+ "type = ?,"
				+ "openedDate = ?,"
				+ "balance = ?,"
				+ "transactionLimit = ?,"
				+ "annualInterestRate = ?,"
				+ "annualGain = ?,"
				+ "extraFees = ? "
				+ "WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(updateQuery);
			//ps.setString(1, "account_seq.NEXTVAL");
			ps.setInt(1, s1.getPin());
			ps.setString(2, s1.getName());
			ps.setString(3, s1.getType().toString());
			ps.setString(4, s1.getOpenedDate().toString());
			ps.setDouble(5, s1.getBalance());
			ps.setInt(6, s1.getTransactionLimit());
			ps.setDouble(7, s1.getAnnualInterestRate());
			ps.setDouble(8, s1.getAnnualGain());
			ps.setDouble(9, s1.getExtraFees());
			ps.setInt(10, s1.getId());
			ps.executeUpdate();
			conn.commit();
		}
		catch(SQLException e) {
			System.out.println("An Error has occured for UPDATE Statement");
		}
	}
	public static void SavingDelete(Saving s1, Connection conn) {
		String deleteQuery = "DELETE FROM ACCOUNTS "
				+ "WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(deleteQuery);
			ps.setInt(1, s1.getId());
			ps.executeUpdate();
			conn.commit();
		}
		catch(SQLException e) {
			System.out.println("An error has occured from DELETE Statement.");
		}
	}
	public static Saving getSaving(int id, Connection conn) {
		Saving s1 = null;
		String selectQuery = "SELECT id, pin, name, type, openedDate, balance, transactionLimit, cid, annualInterestRate, extraFees " + 
				"FROM ACCOUNTS "
				+ "WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(selectQuery);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				s1 = new Saving();
				s1.setId(rs.getInt(1));
				s1.setPin(rs.getInt(2));
				s1.setName(rs.getString(3));
				s1.setType(AccountType.valueOf(rs.getString(4)));
				
				String[] line = rs.getString(5).split("-");
				
				s1.setOpenedDate(LocalDate.of(Integer.valueOf(line[0]), Integer.valueOf(line[1]), Integer.valueOf(line[2])));
				s1.setBalance(rs.getDouble(6));
				s1.setTransactionLimit(rs.getInt(7));
				s1.setCid(rs.getInt(8));
				s1.setAnnualInterestRate(rs.getDouble(9));
				s1.setExtraFees(rs.getDouble(10));
				break;
			}
		}
		catch(SQLException e) {
			System.out.println("An error has occured from SELECT Statement.");
		}
		return s1;
	}
	public static ArrayList<Saving> getSavings(Connection conn){
		ArrayList<Saving> sList = new ArrayList<Saving>();
		Saving s1 = null;
		String selectQuery = "SELECT * FROM ACCOUNTS "
				+ "WHERE type = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(selectQuery);
			ps.setString(1, AccountType.Saving.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				s1 = new Saving();
				s1.setId(rs.getInt(1));
				s1.setPin(rs.getInt(2));
				s1.setName(rs.getString(3));
				s1.setType(AccountType.valueOf(rs.getString(4)));
				
				String[] line = rs.getString(5).split("-");
				
				s1.setOpenedDate(LocalDate.of(Integer.valueOf(line[0]), Integer.valueOf(line[1]), Integer.valueOf(line[2])));
				s1.setBalance(rs.getDouble(6));
				s1.setTransactionLimit(rs.getInt(7));
				s1.setCid(rs.getInt(8));
				s1.setAnnualInterestRate(rs.getDouble(9));
				s1.setExtraFees(rs.getDouble(10));
				
				sList.add(s1);
			}
		}
		catch(SQLException e) {
			System.out.println("An error has occured from SELECT Statement.");
		}
		return sList;
	}
	public static void CreditInsert(Credit cr1, Connection conn) {
		String insertQuery = "INSERT INTO ACCOUNTS(id, pin, name, type, openedDate, balance, transactionLimit, cid, annualInterestRate, extraFees) "
				+ "VALUES(account_seq.NEXTVAL,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(insertQuery);
			//ps.setString(1, "account_seq.NEXTVAL");
			ps.setInt(1, cr1.getPin());
			ps.setString(2, cr1.getName());
			ps.setString(3, cr1.getType().toString());
			ps.setString(4, cr1.getOpenedDate().toString());
			ps.setDouble(5, cr1.getBalance());
			ps.setInt(6, cr1.getTransactionLimit());
			ps.setInt(7, cr1.getCid());
			ps.setDouble(8, cr1.getAnnualInterestRate());
			ps.setDouble(9, cr1.getExtraFees());
			ps.executeUpdate();
			conn.commit();
		}
		catch(SQLException e) {
			System.out.println("an error has occured from INSERT Statement.");
		}
	}
	public static void CreditUpdate(Credit cr1, Connection conn) {
		String updateQuery = "UPDATE ACCOUNTS "
				+ "SET pin = ?,"
				+ "name = ?,"
				+ "type = ?,"
				+ "openedDate = ?,"
				+ "balance = ?,"
				+ "transactionLimit = ?,"
				+ "annualInterestRate = ?,"
				+ "extraFees = ? "
				+ "WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(updateQuery);
			//ps.setString(1, "account_seq.NEXTVAL");
			ps.setInt(1, cr1.getPin());
			ps.setString(2, cr1.getName());
			ps.setString(3, cr1.getType().toString());
			ps.setString(4, cr1.getOpenedDate().toString());
			ps.setDouble(5, cr1.getBalance());
			ps.setInt(6, cr1.getTransactionLimit());
			ps.setDouble(7, cr1.getAnnualInterestRate());
			ps.setDouble(8, cr1.getExtraFees());
			ps.setInt(9, cr1.getId());
			ps.executeUpdate();
			conn.commit();
		}
		catch(SQLException e) {
			System.out.println("An Error has occured for UPDATE Statement");
		}
	}
	public static void CreditDelete(Credit cr1, Connection conn) {
		String deleteQuery = "DELETE FROM ACCOUNTS "
				+ "WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(deleteQuery);
			ps.setInt(1, cr1.getId());
			ps.executeUpdate();
			conn.commit();
		}
		catch(SQLException e) {
			System.out.println("An error has occured from DELETE Statement.");
		}
	}
	public static Credit getCredit(int id, Connection conn) {
		Credit cr1 = null;
		String selectQuery = "SELECT id, pin, name, type, openedDate, balance, transactionLimit, cid, annualInterestRate, extraFees " + 
				"FROM ACCOUNTS "
				+ "WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(selectQuery);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				cr1 = new Credit();
				cr1.setId(rs.getInt(1));
				cr1.setPin(rs.getInt(2));
				cr1.setName(rs.getString(3));
				cr1.setType(AccountType.valueOf(rs.getString(4)));
				
				String[] line = rs.getString(5).split("-");
				
				cr1.setOpenedDate(LocalDate.of(Integer.valueOf(line[0]), Integer.valueOf(line[1]), Integer.valueOf(line[2])));
				cr1.setBalance(rs.getDouble(6));
				cr1.setTransactionLimit(rs.getInt(7));
				cr1.setCid(rs.getInt(8));
				cr1.setAnnualInterestRate(rs.getDouble(9));
				cr1.setExtraFees(rs.getDouble(10));
				break;
			}
		}
		catch(SQLException e) {
			System.out.println("An error has occured from SELECT Statement.");
		}
		return cr1;
	}
	public static ArrayList<Credit> getCredits(Connection conn){
		ArrayList<Credit> crList = new ArrayList<Credit>();
		Credit cr1 = null;
		String selectQuery = "SELECT * FROM ACCOUNTS "
				+ "WHERE type = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(selectQuery);
			ps.setString(1, AccountType.Credit.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				cr1 = new Credit();
				cr1.setId(rs.getInt(1));
				cr1.setPin(rs.getInt(2));
				cr1.setName(rs.getString(3));
				cr1.setType(AccountType.valueOf(rs.getString(4)));
				
				String[] line = rs.getString(5).split("-");
				
				cr1.setOpenedDate(LocalDate.of(Integer.valueOf(line[0]), Integer.valueOf(line[1]), Integer.valueOf(line[2])));
				cr1.setBalance(rs.getDouble(6));
				cr1.setTransactionLimit(rs.getInt(7));
				cr1.setCid(rs.getInt(8));
				cr1.setAnnualInterestRate(rs.getDouble(9));
				cr1.setExtraFees(rs.getDouble(10));
				
				crList.add(cr1);
			}
		}
		catch(SQLException e) {
			System.out.println("An error has occured from SELECT Statement.");
		}
		return crList;
	}
	public static void TransactionInsert(Transaction t1, Connection conn) {
		String insertQuery = "INSERT INTO TRANSACTIONS "
				+ "VALUES(transaction_seq.NEXTVAL, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(insertQuery);
			ps.setString(1, t1.getDescription());
			ps.setString(2, t1.getPostedDate().toString());
			ps.setDouble(3, t1.getAmount());
			ps.setString(4, t1.getType().toString());
			ps.setInt(5, t1.getAid());
			
			ps.executeUpdate();
			conn.commit();
		}
		catch(SQLException e) {
			System.out.println("An error has occured from INSERT Statement.");
		}
	}
	public static void TransactionUpdate(Transaction t1, Connection conn) {
		String updateQuery = "UPDATE TRANSACTIONS "
				+ "SET description = ?,"
				+ "postedDate = ?,"
				+ "amount = ?,"
				+ "type = ? "
				+ "WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(updateQuery);
			ps.executeUpdate();
			conn.commit();
		}
		catch (SQLException e) {
			System.out.println("An error occured from UPDATE Statement\n" + e.getMessage());
		}
	}
	public static void TransactionDelete(Transaction t1, Connection conn) {
		String deleteQuery = "DELETE FROM TRANSACTIONS "
				+ "WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(deleteQuery);
			ps.setInt(1, t1.getId());
			
			ps.executeUpdate();
			conn.commit();
		}
		catch(SQLException e) {
			System.out.println("An error occured from DELETE Statement.\n" + e.getMessage());
		}
	}
	public static Transaction getTransaction(int id, Connection conn) {
		Transaction t1 = null;
		String selectQuery = "SELECT * FROM TRANSACTIONS "
				+ "WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(selectQuery);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				t1 = new Transaction();
				t1.setId(rs.getInt(1));
				t1.setDescription(rs.getString(2));
				
				String[] line = rs.getString(3).split("-");
				
				t1.setPostedDate(LocalDate.of(Integer.valueOf(line[0]), Integer.valueOf(line[1]), Integer.valueOf(line[2])));
				t1.setAmount(rs.getDouble(4));
				t1.setType(TransactionType.valueOf(rs.getString(5)));
				t1.setAid(rs.getInt(6));
				break;
			}
		}
		catch(SQLException e) {
			System.out.println("An error occured from SELECT Statement.\n" + e.getMessage());
		}
		return t1;
	}
	public static ArrayList<Transaction> getTransactions(Connection conn){
		ArrayList<Transaction> tList = new ArrayList<Transaction>();
		Transaction t1 = null;
		String selectQuery = "SELECT * FROM TRANSACTIONS";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(selectQuery);
			while (rs.next()) {
				t1 = new Transaction();
				t1.setId(rs.getInt(1));
				t1.setDescription(rs.getString(2));
				
				String[] line = rs.getString(3).split("-");
				
				t1.setPostedDate(LocalDate.of(Integer.valueOf(line[0]), Integer.valueOf(line[1]), Integer.valueOf(line[2])));
				t1.setAmount(rs.getDouble(4));
				t1.setType(TransactionType.valueOf(rs.getString(5)));
				t1.setAid(rs.getInt(6));
				break;
			}
		}
		catch(SQLException e) {
			System.out.println("An error occured from SELECT Statement.\n" + e.getMessage());
		}
		return tList;
	}
	public static AccountType getAccountType(int id, Connection conn) {
		AccountType at = null;
		String selectQuery = "SELECT type FROM ACCOUNTS "
				+ "WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(selectQuery);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				at = AccountType.valueOf(rs.getString(1));
			}
		}
		catch(SQLException e) {
			System.out.println("An error occured from SELECT Statement.\n" + e.getMessage());
		}
		return at;
	}
}
