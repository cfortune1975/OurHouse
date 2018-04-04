package main;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;
import java.sql.*;

import main.Employee;

/*
 * @author Alex Wubbena, Chad Fortune, Josh Kofile, Timothy Lee
 * 
 */

public class OurHouseEmployees {

	// private Employee[] employee;
	private JFrame frmMain;
	private JLabel lblEmployeeNumber;
	private JLabel lblNameFirst;
	private JLabel lblNameMiddle;
	private JLabel lblNameLast;
	private JLabel lblBracketOpen;
	private JLabel lblBracketClose;
	private JLabel lblPhoneNumber;
	private JLabel lblAddress;
	private JLabel lblAddressStreetNum;
	
	private JTextField txtNameFirst;
	private JTextField txtNameMiddle;
	private JTextField txtNameLast;
	private JTextField txtPhoneArea;
	private JTextField txtPhoneNumber;
	private JTextField txtAddressStreetNum;
	private JTextField txtAddressStreetName;
	
	private JSeparator separator1;
	private JSeparator separator2;
	private JTextField txtAddressCity;
	private JTextField txtAddressState;
	private JTextField txtAddressZip;

	/** Launch the application. */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OurHouseEmployees window = new OurHouseEmployees();
					window.frmMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		
	}

	/**
	 * Create the application.
	 */
	public OurHouseEmployees() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		LoadEmployeeDB();

		frmMain = new JFrame();
		frmMain.setResizable(false);
		frmMain.getContentPane().setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		frmMain.setTitle("Our House - Add Employee");
		frmMain.setBounds(100, 100, 439, 354);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);

		lblEmployeeNumber = new JLabel("Employee Number:");
		lblEmployeeNumber.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		lblEmployeeNumber.setBounds(10, 11, 142, 20);
		frmMain.getContentPane().add(lblEmployeeNumber);

		separator1 = new JSeparator();
		separator1.setBounds(10, 33, 170, 2);
		frmMain.getContentPane().add(separator1);
		
		lblNameFirst = new JLabel("First Name:");
		lblNameFirst.setLabelFor(txtNameFirst);
		lblNameFirst.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		lblNameFirst.setBounds(10, 63, 86, 20);
		frmMain.getContentPane().add(lblNameFirst);

		txtNameFirst = new JTextField();
		txtNameFirst.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		txtNameFirst.setBounds(10, 44, 97, 20);
		txtNameFirst.setColumns(10);
		frmMain.getContentPane().add(txtNameFirst);
		
		lblNameMiddle = new JLabel("MI:");
		lblNameMiddle.setLabelFor(txtNameMiddle);
		lblNameMiddle.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNameMiddle.setBounds(111, 63, 27, 20);
		frmMain.getContentPane().add(lblNameMiddle);

		txtNameMiddle = new JTextField();
		txtNameMiddle.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		txtNameMiddle.setBounds(112, 44, 27, 20);
		txtNameMiddle.setColumns(2);
		frmMain.getContentPane().add(txtNameMiddle);
		
		lblNameLast = new JLabel("Last Name:");
		lblNameLast.setLabelFor(txtNameLast);
		lblNameLast.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNameLast.setBounds(149, 64, 86, 20);
		frmMain.getContentPane().add(lblNameLast);
		
		txtNameLast = new JTextField();
		txtNameLast.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		txtNameLast.setBounds(149, 44, 223, 20);
		txtNameLast.setColumns(20);
		frmMain.getContentPane().add(txtNameLast);
		
		lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblPhoneNumber.setBounds(10, 119, 129, 20);
		frmMain.getContentPane().add(lblPhoneNumber);
		
		lblBracketOpen = new JLabel("(");
		lblBracketOpen.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblBracketOpen.setBounds(10, 99, 14, 20);
		frmMain.getContentPane().add(lblBracketOpen);
		
		txtPhoneArea = new JTextField();
		lblPhoneNumber.setLabelFor(txtPhoneArea);
		txtPhoneArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtPhoneArea.setColumns(3);
		txtPhoneArea.setBounds(18, 101, 32, 20);
		frmMain.getContentPane().add(txtPhoneArea);
		
		lblBracketClose = new JLabel(")");
		lblBracketClose.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblBracketClose.setBounds(52, 99, 14, 20);
		frmMain.getContentPane().add(lblBracketClose);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtPhoneNumber.setColumns(8);
		txtPhoneNumber.setBounds(62, 101, 60, 20);
		frmMain.getContentPane().add(txtPhoneNumber);
		
		lblAddress = new JLabel("Employee Address:");
		lblAddress.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblAddress.setBounds(10, 155, 142, 20);
		frmMain.getContentPane().add(lblAddress);
		
		txtAddressStreetNum = new JTextField();
		txtAddressStreetNum.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtAddressStreetNum.setColumns(5);
		txtAddressStreetNum.setBounds(10, 191, 56, 20);
		frmMain.getContentPane().add(txtAddressStreetNum);
		
		lblAddressStreetNum = new JLabel("Street Number & Name:");
		lblAddressStreetNum.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblAddressStreetNum.setBounds(10, 210, 170, 20);
		frmMain.getContentPane().add(lblAddressStreetNum);
		
		separator2 = new JSeparator();
		separator2.setBounds(10, 176, 200, 2);
		frmMain.getContentPane().add(separator2);
		
		txtAddressStreetName = new JTextField();
		txtAddressStreetName.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtAddressStreetName.setColumns(10);
		txtAddressStreetName.setBounds(66, 191, 200, 20);
		frmMain.getContentPane().add(txtAddressStreetName);
		
		JLabel lblAddressCity = new JLabel("City:");
		lblAddressCity.setBounds(10, 268, 42, 20);
		frmMain.getContentPane().add(lblAddressCity);
		
		txtAddressCity = new JTextField();
		txtAddressCity.setBounds(10, 243, 170, 26);
		frmMain.getContentPane().add(txtAddressCity);
		txtAddressCity.setColumns(20);
		
		txtAddressState = new JTextField();
		txtAddressState.setColumns(2);
		txtAddressState.setBounds(193, 243, 56, 26);
		frmMain.getContentPane().add(txtAddressState);
		
		JLabel lblAddressState = new JLabel("State:");
		lblAddressState.setBounds(193, 268, 42, 20);
		frmMain.getContentPane().add(lblAddressState);
		
		txtAddressZip = new JTextField();
		txtAddressZip.setColumns(5);
		txtAddressZip.setBounds(258, 243, 146, 26);
		frmMain.getContentPane().add(txtAddressZip);
		
		JLabel lblAddressZip = new JLabel("Zip Code:");
		lblAddressZip.setBounds(258, 268, 69, 20);
		frmMain.getContentPane().add(lblAddressZip);
	}

	private static int LoadEmployeeDB() 
	{
		int count = 0;
		int total = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("jdbc driver loaded.");
			
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
			System.out.println("Database connection opened.");

			// Count total employees from database
			PreparedStatement prepSt = conn.prepareStatement("Select count(EMPLOYEE_NUMBER) FROM cxf140730.emp_name");
			ResultSet rs = prepSt.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
			}
			System.out.println("total employee records: " + total);
			
			// Read employee information from database
			Statement st = conn.createStatement();
			System.out.println("Statement created.");
			
			String Sql = "SELECT en.EMPLOYEE_NUMBER, LAST_NAME, FIRST_NAME, MIDDLE_NAME, "
					+ "		  EMP_PHONE_AREA, EMP_PHONE_NUMBER, EMP_ADDRESS_STNUM, "
					+ "		  EMP_ADDRESS_STNAME, EMP_ADDRESS_CITY, EMP_ADDRESS_STATE, " + "		  EMP_ADDRESS_ZIP"
					+ "FROM cxf140730.emp_name en, cxf140730.emp_phone ep, cxf140730.emp_Address ea "
					+ "WHERE en.EMPLOYEE_NUMBER = ep.EMPLOYEE_NUMBER AND " + "en.EMPLOYEE_NUMBER = ea.EMPLOYEE_NUMBER";
			rs = st.executeQuery(Sql);
			System.out.println("Query executed.");
			
			Employee[] employee = new Employee[total + 1];
			while (rs.next()) 
			{
				employee[count].setEmployeeNumber(rs.getInt(1));
				employee[count].setEmployeeLastName(rs.getString(2));
				employee[count].setEmployeeFirstName(rs.getString(3));
				employee[count].setEmployeeMiddleName(rs.getString(4));
				employee[count].setEmployeePhoneArea(rs.getString(5));
				employee[count].setEmployeePhoneNumber(rs.getString(6));
				employee[count].setEmployeeAddressStNum(rs.getInt(7));
				employee[count].setEmployeeAddressStName(rs.getString(8));
				employee[count].setEmployeeAddressCity(rs.getString(9));
				employee[count].setEmployeeAddressState(rs.getString(10));
				employee[count].setEmployeeAddressZip(rs.getInt(11));
				
				count++;
			}
			
			conn.close();
		} 
		catch (Exception ex) 
		{
			System.out.println(ex);
		}
		
		return count;
	}
}
