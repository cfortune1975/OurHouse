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
	
	private JTextField txtNameFirst;
	private JTextField txtNameMiddle;
	private JTextField txtNameLast;
	private JTextField txtPhoneArea;
	private JTextField txtPhoneNumber;
	
	

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

		// conn.close();
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
		frmMain.setBounds(100, 100, 439, 447);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);

		lblEmployeeNumber = new JLabel("Employee Number:");
		lblEmployeeNumber.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		lblEmployeeNumber.setBounds(10, 11, 142, 20);
		frmMain.getContentPane().add(lblEmployeeNumber);

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
		txtPhoneArea.setColumns(10);
		txtPhoneArea.setBounds(18, 101, 32, 20);
		frmMain.getContentPane().add(txtPhoneArea);
		
		lblBracketClose = new JLabel(")");
		lblBracketClose.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblBracketClose.setBounds(52, 99, 14, 20);
		frmMain.getContentPane().add(lblBracketClose);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(62, 101, 60, 20);
		frmMain.getContentPane().add(txtPhoneNumber);
		
		lblAddress = new JLabel("Employee Address:");
		lblAddress.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblAddress.setBounds(10, 155, 142, 20);
		frmMain.getContentPane().add(lblAddress);
	}

	private static int LoadEmployeeDB() 
	{
		int count = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("jdbc driver loaded.");
			
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
			System.out.println("Database connection opened.");

			Statement st = conn.createStatement();
			System.out.println("Statement created.");

			ResultSet rs = st.executeQuery("Select count(EMPLOYEE_NUMBER) FROM emp_name");
			int total = rs.getInt(0);
			
			System.out.println("total employee records: " + total);
			
			conn.createStatement();
			String Sql = "SELECT en.EMPLOYEE_NUMBER, LAST_NAME, FIRST_NAME, MIDDLE_NAME, "
					+ "		  EMP_PHONE_AREA, EMP_PHONE_NUMBER, EMP_ADDRESS_STNUM, "
					+ "		  EMP_ADDRESS_STNAME, EMP_ADDRESS_CITY, EMP_ADDRESS_STATE, " + "		  EMP_ADDRESS_ZIP"
					+ "FROM emp_name en, emp_phone ep, emp_Address ea "
					+ "WHERE en.EMPLOYEE_NUMBER = ep.EMPLOYEE_NUMBER AND " + "en.EMPLOYEE_NUMBER = ea.EMPLOYEE_NUMBER";

			rs = st.executeQuery(Sql);
			Employee[] employee = new Employee[total + 1];

			while (rs.next()) 
			{
				employee[count].setEmployeeNumber(rs.getInt(0));
				employee[count].setEmployeeLastName(rs.getString(1));
				employee[count].setEmployeeFirstName(rs.getString(2));
				employee[count].setEmployeeMiddleName(rs.getString(3));
				employee[count].setEmployeePhoneArea(rs.getString(4));
				employee[count].setEmployeePhoneNumber(rs.getString(5));
				employee[count].setEmployeeAddressStNum(rs.getInt(6));
				employee[count].setEmployeeAddressStName(rs.getString(7));
				employee[count].setEmployeeAddressCity(rs.getString(8));
				employee[count].setEmployeeAddressState(rs.getString(9));
				employee[count].setEmployeeAddressZip(rs.getInt(10));
				
				count++;
			}
		} 
		catch (Exception ex) 
		{
			System.out.println(ex);
		}
		
		return count;
	}
}
