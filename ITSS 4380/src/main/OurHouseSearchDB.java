package main;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;

public class OurHouseSearchDB {

	private JFrame frmSearchDB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OurHouseSearchDB window = new OurHouseSearchDB();
					window.frmSearchDB.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OurHouseSearchDB() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		LoadEmployeeDB();
		
		frmSearchDB = new JFrame();
		frmSearchDB.setName("frmSearchDB");
		frmSearchDB.setBounds(100, 100, 450, 300);
		frmSearchDB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			PreparedStatement prepSt = conn.prepareStatement("Select count(EMPLOYEE_NUMBER) FROM dbo.emp_name");
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
					+ "		  EMP_ADDRESS_STNAME, EMP_ADDRESS_CITY, EMP_ADDRESS_STATE, " 
					+ "		  EMP_ADDRESS_ZIP"
					+ "FROM dbo.emp_name en, dbo.emp_phone ep, dbo.emp_Address ea "
					+ "WHERE en.EMPLOYEE_NUMBER = ep.EMPLOYEE_NUMBER AND " 
					+ "      en.EMPLOYEE_NUMBER = ea.EMPLOYEE_NUMBER";
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
