package bus;

public class Employee extends Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private EmployeeType type;
	private String password;
	public Employee() {
		super();
		this.id = 0;
		this.type = EmployeeType.Undefined;
		this.password = "Undefined";
	}
	public Employee(String firstName, String lastName, Address address, String phoneNumber, int id, EmployeeType type, String password) {
		super(firstName, lastName, address, phoneNumber);
		this.id = id;
		this.type = type;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public EmployeeType getType() {
		return type;
	}
	public void setType(EmployeeType type) {
		this.type = type;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", type=" + type + ", password=" + password + "]";
	}
	public void addCustomer() {
		ICustomerManipulator.addCustomer();
	}
}
