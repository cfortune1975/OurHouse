package main;

import java.awt.EventQueue;
//import java.awt.Font;

import javax.swing.*;
import java.sql.*;
import main.Employee;
//import javax.swing.table.DefaultTableModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/*
 * @author Alex Wubbena, Chad Fortune, Timothy Lee
 * 
 */

public class OurHouseSearchDB {
	private static Connection conn = null;
	private static Employee[] employee = null;

	private JFrame frmSearchDB;
	private JTable tblEmployee;
	private JScrollPane scrollPane;

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
		frmSearchDB = new JFrame();
		frmSearchDB.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				int newWide = frmSearchDB.getWidth() - 31;
				int newHigh = frmSearchDB.getHeight() - 60;
				scrollPane.setBounds(10, 11, newWide, newHigh);
			}
		});
		frmSearchDB.setTitle("All Employee Database");
		frmSearchDB.setName("frmSearchDB");
		frmSearchDB.setBounds(100, 100, 450, 342);
		frmSearchDB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSearchDB.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 10, 424, 292);
		frmSearchDB.getContentPane().add(scrollPane);
		
		int employees = LoadEmployeeDB();

		String[] columnNames = { 
				"Employee Number", "First Name", "MI", "Last Name", "Phone Number", "Street", "City", "State", "Zip Code" 
				};
		Object[][] data = new Object[employees][10];
		
		for (int i = 1; i<= employees; i++)
		{
			data[i][1] = employee[i].getEmployeeNumber();
			data[i][2] = employee[i].getEmployeeFirstName();
			data[i][3] = employee[i].getEmployeeMiddleName();
			data[i][4] = employee[i].getEmployeeLastName();
			data[i][5] = employee[i].getEmployeePhoneArea() + " " + employee[i].getEmployeePhoneNumber();
			data[i][6] = employee[i].getEmployeeAddressStreetNUm() + " " + employee[i].getEmployeeAddressStName();
			data[i][7] = employee[i].getEmployeeAddressCity();
			data[i][8] = employee[i].getEmployeeAddressState();
			data[i][9] = employee[i].getEmployeeAddressZip();
		}

		tblEmployee = new JTable(data, columnNames);
		scrollPane.setViewportView(tblEmployee);


	} // end of method initialize

	private static int LoadEmployeeDB() {
		int count = 0;
		int total = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("jdbc driver loaded.");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
			System.out.println("Database connection opened.");

			// Count total employees from database
			PreparedStatement prepSt = conn.prepareStatement("Select count(EMPLOYEE_NUMBER) FROM dbo.emp_name");
			ResultSet rs = prepSt.executeQuery();
			while (rs.next()) {
				total = rs.getInt(1);
			}
			System.out.println("total employee records: " + total);

			// Read employee information from database
			String sql = "SELECT en.EMPLOYEE_NUMBER, LAST_NAME, FIRST_NAME, MIDDLE_NAME,"
					+ " EMP_PHONE_AREA, EMP_PHONE_NUMBER, EMP_ADDRESS_STNUM, "
					+ " EMP_ADDRESS_STNAME, EMP_ADDRESS_CITY, EMP_ADDRESS_STATE, " 
					+ " EMP_ADDRESS_ZIP"
					+ "FROM dbo.emp_name en, dbo.emp_phone ep, dbo.emp_Address ea "
					+ "WHERE en.EMPLOYEE_NUMBER = ep.EMPLOYEE_NUMBER AND "
					+ " en.EMPLOYEE_NUMBER = ea.EMPLOYEE_NUMBER";

			//String sql = "{call dbo.sp_emp_get}";
			prepSt = conn.prepareStatement(sql);
			System.out.println("Statement created.");

			rs = prepSt.executeQuery();
			System.out.println("Query executed.");

			Employee[] employee = new Employee[total + 1];
			while (rs.next()) {
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

			
			rs.close();
			prepSt.close();
			conn.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return count;
	}
}
