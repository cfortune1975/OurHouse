package main;

public class Employee {

	/** class attributes */
	private int emp_Number;
	private String emp_FName;
	private String emp_MName;
	private String emp_LName;
	private String emp_PhoneArea;
	private String emp_PhoneNumber;
	private int emp_AddressStNum;
	private String emp_AddressStName;
	private String emp_AddressCity;
	private String emp_AddressState;
	private int emp_AddressZip;

	/** public constructor */
	public Employee() {
		this.emp_Number = 0;
		this.emp_FName = "";
		this.emp_MName = "";
		this.emp_LName = "";
		this.emp_PhoneArea = "";
		this.emp_PhoneNumber = "";
		this.emp_AddressStNum = 0;
		this.emp_AddressStName = "";
		this.emp_AddressCity = "";
		this.emp_AddressState = "";
		this.emp_AddressZip = 0;
	}

	/**
	 * public constructor
	 * 
	 * @param num
	 *            {int} : Employee Number
	 * @param FName
	 *            {String} : Employee First Name
	 * @param LName
	 *            {String} : Employee Last Name
	 */
	public Employee(int num, String FName, String LName) {
		this.emp_Number = num;
		this.emp_FName = FName;
		this.emp_MName = "";
		this.emp_LName = LName;
		this.emp_PhoneArea = "";
		this.emp_PhoneNumber = "";
		this.emp_AddressStNum = 0;
		this.emp_AddressStName = "";
		this.emp_AddressCity = "";
		this.emp_AddressState = "";
		this.emp_AddressZip = 0;
	}

	/* Class field get/set methods */

	public int getEmployeeNumber() {
		return this.emp_Number;
	}

	public void setEmployeeNumber(int value) {
		this.emp_Number = value;
	}

	public String getFirstName() {
		return this.emp_FName;
	}

	public String getLastName() {
		return this.emp_LName;
	}

	public String getMiddleName() {
		return this.emp_MName;
	}

	public void setFirstName(String value) {
		this.emp_FName = value;
	}

	public void setMiddleName(String value) {
		this.emp_MName = value;
	}

	public void setLastName(String value) {
		this.emp_LName = value;
	}

	public String getEmployeePhoneArea() {
		return this.emp_PhoneArea;
	}

	public void setEmployeePhoneArea(String value) {
		this.emp_PhoneArea = value;
	}

	public String getEmployeePhoneNumber() {
		return this.emp_PhoneNumber;
	}

	public void getEmployeePhoneNumber(String value) {
		this.emp_PhoneNumber = value;
	}

	public int getEmployeeAddressStreetNUm() {
		return this.emp_AddressStNum;
	}

	public void setEmployeeAddressStNum(int value) {
		this.emp_AddressStNum = value;
	}

	public String getEmployeeAddressStName() {
		return this.emp_AddressStName;
	}

	public void setEmployeeAddressStName(String value) {
		this.emp_AddressStName = value;
	}

	public String getEmployeeAddressCity() {
		return this.emp_AddressCity;
	}

	public void setEmployeeAddressCity(String value) {
		this.emp_AddressCity = value;
	}

	public String getEmployeeAddressState() {
		return this.emp_AddressState;
	}

	public void setEmployeeAddressState(String value) {
		this.emp_AddressState = value;
	}

	public int getEmployeeAddressZip() {
		return this.emp_AddressZip;
	}

	public void setEmployeeAddressZip(int value) {
		this.emp_AddressZip = value;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(this.emp_Number + ": ");
		str.append(this.emp_LName + ", ");
		str.append(this.emp_FName + " ");
		str.append(this.emp_MName + ", ");
		str.append("(" + this.emp_PhoneArea + ") ");
		str.append(this.emp_PhoneNumber + ", ");
		str.append(this.emp_AddressStNum + " ");
		str.append(this.emp_AddressStName + ", ");
		str.append(this.emp_AddressCity + ", ");
		str.append(this.emp_AddressState + " ");
		str.append(this.emp_AddressZip + "\n");

		return str.toString();
	}
}
