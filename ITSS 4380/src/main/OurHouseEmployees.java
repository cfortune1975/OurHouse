package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.sql.*;

import org.apache.commons.lang3.Validate;
//import main.Employee;

/*
 * @author Alex Wubbena, Chad Fortune, Timothy Lee
 * 
 */

public class OurHouseEmployees {
	private int empNum;
	private static Connection conn = null;

	private JFrame frmAddEmployee;
	private JLabel lblEmployeeNumber;
	private JLabel lblNameFirst;
	private JLabel lblNameMiddle;
	private JLabel lblNameLast;
	private JLabel lblBracketOpen;
	private JLabel lblBracketClose;
	private JLabel lblPhoneNumber;
	private JLabel lblAddress;
	private JLabel lblAddressStreetNum;
	private JLabel lblAddressCity;
	private JLabel lblAddressState;
	private JLabel lblAddressZip;

	private JTextField txtNameFirst;
	private JTextField txtNameMiddle;
	private JTextField txtNameLast;
	private JTextField txtPhoneArea;
	private MaskFormatter mask;
	private JFormattedTextField txtPhoneNumber;
	private JTextField txtAddressStreetNum;
	private JTextField txtAddressStreetName;
	private JTextField txtAddressCity;
	private JTextField txtAddressState;
	private JTextField txtAddressZip;

	private JSeparator separator1;
	private JSeparator separator2;

	private JButton btnAdd;
	private JButton btnCancel;

	/** Launch the application. */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OurHouseEmployees window = new OurHouseEmployees();
					window.frmAddEmployee.setVisible(true);
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
		/** Application Window */
		frmAddEmployee = new JFrame();
		frmAddEmployee.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					int result = JOptionPane.showConfirmDialog(frmAddEmployee, "Are you sure you want to Quit?", "Quit",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION)
						System.exit(0);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		frmAddEmployee.setName("frmAdd");
		frmAddEmployee.setFont(new Font("SansSerif", Font.PLAIN, 16));
		frmAddEmployee.setResizable(false);
		frmAddEmployee.getContentPane().setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		frmAddEmployee.setTitle("Our House - Add Employee");
		frmAddEmployee.setBounds(100, 100, 363, 385);
		frmAddEmployee.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAddEmployee.getContentPane().setLayout(null);

		/** Application Contents */
		empNum = getEmployeeNum() + 1;

		lblEmployeeNumber = new JLabel("Employee Number: " + empNum);
		lblEmployeeNumber.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		lblEmployeeNumber.setBounds(10, 11, 337, 20);
		frmAddEmployee.getContentPane().add(lblEmployeeNumber);

		separator1 = new JSeparator();
		separator1.setBounds(10, 33, 335, 2);
		frmAddEmployee.getContentPane().add(separator1);

		lblNameFirst = new JLabel("First Name:");
		lblNameFirst.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		lblNameFirst.setBounds(10, 63, 86, 20);
		lblNameFirst.setLabelFor(txtNameFirst);
		frmAddEmployee.getContentPane().add(lblNameFirst);

		txtNameFirst = new JTextField();
		txtNameFirst.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		txtNameFirst.setBounds(10, 38, 100, 26);
		txtNameFirst.setColumns(10);
		frmAddEmployee.getContentPane().add(txtNameFirst);

		lblNameMiddle = new JLabel("MI:");
		lblNameMiddle.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNameMiddle.setBounds(118, 63, 20, 20);
		lblNameMiddle.setLabelFor(txtNameMiddle);
		frmAddEmployee.getContentPane().add(lblNameMiddle);

		txtNameMiddle = new JTextField();
		txtNameMiddle.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		txtNameMiddle.setBounds(115, 38, 27, 26);
		txtNameMiddle.setColumns(2);
		frmAddEmployee.getContentPane().add(txtNameMiddle);

		lblNameLast = new JLabel("Last Name:");
		lblNameLast.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNameLast.setBounds(147, 63, 86, 20);
		lblNameLast.setLabelFor(txtNameLast);
		frmAddEmployee.getContentPane().add(lblNameLast);

		txtNameLast = new JTextField();
		txtNameLast.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		txtNameLast.setBounds(147, 38, 200, 26);
		txtNameLast.setColumns(20);
		frmAddEmployee.getContentPane().add(txtNameLast);

		lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblPhoneNumber.setBounds(10, 119, 129, 20);
		lblPhoneNumber.setLabelFor(txtPhoneArea);
		frmAddEmployee.getContentPane().add(lblPhoneNumber);

		lblBracketOpen = new JLabel("(");
		lblBracketOpen.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblBracketOpen.setBounds(10, 97, 14, 20);
		lblBracketOpen.setLabelFor(txtPhoneArea);
		frmAddEmployee.getContentPane().add(lblBracketOpen);

		txtPhoneArea = new JTextField();
		txtPhoneArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtPhoneArea.setColumns(3);
		txtPhoneArea.setBounds(18, 95, 32, 26);
		frmAddEmployee.getContentPane().add(txtPhoneArea);

		lblBracketClose = new JLabel(")");
		lblBracketClose.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblBracketClose.setBounds(52, 97, 14, 20);
		lblBracketClose.setLabelFor(txtPhoneNumber);
		frmAddEmployee.getContentPane().add(lblBracketClose);

		mask = createFormatter("###-####", ' ');
		txtPhoneNumber = new JFormattedTextField(mask);
		txtPhoneNumber.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtPhoneNumber.setColumns(8);
		txtPhoneNumber.setBounds(62, 95, 76, 26);
		frmAddEmployee.getContentPane().add(txtPhoneNumber);

		lblAddress = new JLabel("Employee Address:");
		lblAddress.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblAddress.setBounds(10, 161, 142, 20);
		frmAddEmployee.getContentPane().add(lblAddress);

		separator2 = new JSeparator();
		separator2.setBounds(10, 153, 320, 2);
		frmAddEmployee.getContentPane().add(separator2);

		txtAddressStreetNum = new JTextField();
		txtAddressStreetNum.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtAddressStreetNum.setColumns(5);
		txtAddressStreetNum.setBounds(10, 190, 56, 26);
		frmAddEmployee.getContentPane().add(txtAddressStreetNum);

		lblAddressStreetNum = new JLabel("Street Number & Name:");
		lblAddressStreetNum.setLabelFor(txtAddressStreetNum);
		lblAddressStreetNum.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblAddressStreetNum.setBounds(10, 216, 170, 20);
		frmAddEmployee.getContentPane().add(lblAddressStreetNum);

		txtAddressStreetName = new JTextField();
		txtAddressStreetName.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtAddressStreetName.setColumns(20);
		txtAddressStreetName.setBounds(65, 190, 200, 26);
		frmAddEmployee.getContentPane().add(txtAddressStreetName);

		txtAddressCity = new JTextField();
		txtAddressCity.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtAddressCity.setBounds(10, 255, 200, 26);
		txtAddressCity.setColumns(20);
		frmAddEmployee.getContentPane().add(txtAddressCity);

		lblAddressCity = new JLabel("City:");
		lblAddressCity.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblAddressCity.setBounds(10, 280, 42, 20);
		lblAddressCity.setLabelFor(txtAddressCity);
		frmAddEmployee.getContentPane().add(lblAddressCity);

		txtAddressState = new JTextField();
		txtAddressState.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtAddressState.setColumns(2);
		txtAddressState.setBounds(215, 255, 45, 26);
		frmAddEmployee.getContentPane().add(txtAddressState);

		lblAddressState = new JLabel("State:");
		lblAddressState.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblAddressState.setLabelFor(txtAddressState);
		lblAddressState.setBounds(215, 280, 42, 20);
		frmAddEmployee.getContentPane().add(lblAddressState);

		txtAddressZip = new JTextField();
		txtAddressZip.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtAddressZip.setColumns(5);
		txtAddressZip.setBounds(266, 255, 69, 26);
		frmAddEmployee.getContentPane().add(txtAddressZip);

		lblAddressZip = new JLabel("Zip Code:");
		lblAddressZip.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblAddressZip.setLabelFor(txtAddressZip);
		lblAddressZip.setBounds(266, 280, 69, 20);
		frmAddEmployee.getContentPane().add(lblAddressZip);

		btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					btnAdd_Click();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnAdd.setMnemonic('A');
		btnAdd.setActionCommand("btnAdd");
		btnAdd.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnAdd.setBounds(159, 323, 89, 23);
		frmAddEmployee.getContentPane().add(btnAdd);

		btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					int result = JOptionPane.showConfirmDialog(frmAddEmployee, "Are you sure you want to cancel?",
							"Cancel", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION)
						System.exit(0);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnCancel.setMnemonic('C');
		btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnCancel.setBounds(258, 323, 89, 23);
		frmAddEmployee.getContentPane().add(btnCancel);
	}

	private int getEmployeeNum() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
			System.out.println("Database connection opened.");

			// Find highest employee number from database.
			PreparedStatement prepSt = conn.prepareStatement("Select max(EMPLOYEE_NUMBER) FROM dbo.EMPLOYEE");
			ResultSet rs = prepSt.executeQuery();
			int total = 0;
			while (rs.next()) {
				total = rs.getInt(1);
			}

			conn.close();
			prepSt.close();
			rs.close();
			System.out.println("Database connection closed.");

			return total;

		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	} // end of method initialize

	private void btnAdd_Click() {
		// -------------- First, validate all the text fields
		// If any problem, a dialog warning pops up to stop the program
		boolean isValidated = validateTextFields();

		if (!isValidated)
			return;

		// -------------- All the text fields have been validated
		String strFName = txtNameFirst.getText();
		String strMName = txtNameMiddle.getText();
		String strLName = txtNameLast.getText();
		String strArea = txtPhoneArea.getText();
		String strPhone = txtPhoneNumber.getText();
		int intStNum = Integer.parseInt(txtAddressStreetNum.getText());
		String strStName = txtAddressStreetName.getText();
		String strCity = txtAddressCity.getText();
		String strState = txtAddressState.getText();
		int intZip = Integer.parseInt(txtAddressZip.getText());

		// Create employee object
		Employee anEmp = new Employee(empNum, strFName, strLName);
		anEmp.setEmployeeMiddleName(strMName);
		anEmp.setEmployeePhoneArea(strArea);
		anEmp.setEmployeePhoneNumber(strPhone);
		anEmp.setEmployeeAddressStNum(intStNum);
		anEmp.setEmployeeAddressStName(strStName);
		anEmp.setEmployeeAddressCity(strCity);
		anEmp.setEmployeeAddressState(strState);
		anEmp.setEmployeeAddressZip(intZip);

		try {
			// Write data to database
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
			System.out.println("Database connection opened.");

			String sql = "{call dbo.SP_ADD_EMPLOYEE" + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

			CallableStatement cSt = conn.prepareCall(sql);
			cSt.setInt(1, empNum);
			cSt.setString(2, strFName);
			cSt.setString(3, strLName);
			cSt.setString(4, strMName);
			cSt.setString(5, strArea);
			cSt.setString(6, strPhone);
			cSt.setInt(7, intStNum);
			cSt.setString(8, strStName);
			cSt.setString(9, strCity);
			cSt.setString(10, strState);
			cSt.setInt(11, intZip);

			conn.close();
			cSt.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JOptionPane.showMessageDialog(frmAddEmployee,
				"The new employee has been successfully inserted into the database.");

	}

	/****************************
	 * Name: validateTextFields Parameters: None Return: boolean --> TRUE: all the
	 * text fields are successfully validate --> FALSE: at least one text field has
	 * failed the validation Description: --> This method verify to be sure each
	 * text field contains valid data: --> Valid data: not null, not zero-size data,
	 * not empty String, not filled only with blank space --> For txtPhoneArea:
	 * valid data must also be numeric, i.e. only consisting of digits
	 ****************************/
	private boolean validateTextFields() {
		boolean isValidated = true;
		String value = "";

		// ----------- Validate txtNameFirst text field
		try {
			value = txtNameFirst.getText();
			Validate.notBlank(value);

			if (value.length() > 10) {
				JOptionPane.showMessageDialog(frmAddEmployee, "First name - ten letters maximum.");
				value = value.substring(0, Math.min(value.length(), 10));
				txtNameFirst.setText(value);

				txtNameFirst.requestFocusInWindow(); // make it ready to enter the value
				txtNameFirst.selectAll(); // select all text in the text field to
											// delete it or to replace it
				isValidated = false;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmAddEmployee,
					"All the text fields must have valid values - First name cannot be blank !!!");
			txtNameFirst.requestFocusInWindow(); // make it ready to enter the value
			txtNameFirst.selectAll(); // select all text in the text field to
										// delete it or to replace it
			isValidated = false;
		}

		if (!isValidated)
			return (isValidated);

		// ----------- Validate txtNameMiddle text field
		value = txtNameMiddle.getText();
		if (value.length() > 2) {
			JOptionPane.showMessageDialog(frmAddEmployee, "Middle Name - two letters maximum.");
			value = value.substring(0, Math.min(value.length(), 2));
			txtNameMiddle.setText(value);

			txtNameMiddle.requestFocusInWindow(); // make it ready to enter the value
			txtNameMiddle.selectAll(); // select all text in the text field to
										// delete it or to replace it

			isValidated = false;
		}

		if (!isValidated)
			return (isValidated);

		// ----------- Validate txtNameLast text field
		try {
			value = txtNameLast.getText();
			Validate.notBlank(value);

			if (value.length() > 20) {
				JOptionPane.showMessageDialog(frmAddEmployee, "Last Name - twenty letters maximum.");
				value = value.substring(0, Math.min(value.length(), 20));
				txtNameLast.setText(value);

				txtNameLast.requestFocusInWindow(); // make it ready to enter the value
				txtNameLast.selectAll(); // select all text in the text field to
											// delete it or to replace it

				isValidated = false;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmAddEmployee,
					"All the text fields must have valid values - Last Name cannot be blank !!!");
			txtNameLast.requestFocusInWindow(); // make it ready to enter the value
			txtNameLast.selectAll(); // select all text in the text field to
										// delete it or to replace it
			isValidated = false;
		}

		if (!isValidated)
			return (isValidated);

		// ----------- Validate txtPhoneArea text field
		try {
			value = txtPhoneArea.getText();
			Validate.notBlank(value);

			if (value.length() != 3) {
				JOptionPane.showMessageDialog(frmAddEmployee, "Area Code - requires three numbers.");
				value = value.substring(0, Math.min(value.length(), 3));
				txtPhoneArea.setText(value);

				txtPhoneArea.requestFocusInWindow(); // make it ready to enter the value
				txtPhoneArea.selectAll(); // select all text in the text field to
											// delete it or to replace it

				isValidated = false;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmAddEmployee,
					"All the text fields must have valid values - Area Code cannot be blank !!!");
			txtPhoneArea.requestFocusInWindow(); // make it ready to enter the value
			txtPhoneArea.selectAll(); // select all text in the text field to
										// delete it or to replace it
			isValidated = false;
		}

		if (!isValidated)
			return (isValidated);

		// For txtPhoneArea, also need to verify the entered value is a valid
		// numeric
		try {
			@SuppressWarnings("unused")
			long tempPA = Long.parseLong(txtPhoneArea.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmAddEmployee, "Area Code must have a Numeric Value.");
			txtPhoneArea.requestFocusInWindow(); // make it ready to enter the value
			txtPhoneArea.selectAll(); // select all text in the text field
										// to delete it or to replace it
			isValidated = false;
		}

		if (!isValidated)
			return (isValidated);

		// ----------- Validate txtPhoneNumber text field
		try {
			value = txtPhoneNumber.getText();
			Validate.notBlank(value);

			if (value.length() != 8) {
				JOptionPane.showMessageDialog(frmAddEmployee, "Phone Number - requires seven numbers.");
				value = value.substring(0, Math.min(value.length(), 8));
				txtPhoneNumber.setText(value);

				txtPhoneNumber.requestFocusInWindow(); // make it ready to enter the value
				txtPhoneNumber.selectAll(); // select all text in the text field to
											// delete it or to replace it

				isValidated = false;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmAddEmployee,
					"All the text fields must have valid values - Phone Number cannot be blank !!!");
			txtPhoneNumber.requestFocusInWindow(); // make it ready to enter the value
			txtPhoneNumber.selectAll(); // select all text in the text field to
										// delete it or to replace it
			isValidated = false;
		}

		if (!isValidated)
			return (isValidated);

		// ----------- Validate txtAddressStreetNum text field
		try {
			value = txtAddressStreetNum.getText();
			Validate.notBlank(value);

			if (value.length() > 5) {
				JOptionPane.showMessageDialog(frmAddEmployee, "Street Number - five numbers maximum.");
				value = value.substring(0, 4);

				txtAddressStreetNum.requestFocusInWindow(); // make it ready to enter the value
				txtAddressStreetNum.selectAll(); // select all text in the text field to
													// delete it or to replace it

				isValidated = false;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmAddEmployee,
					"All the text fields must have valid values - Street Number cannot be blank !!!");
			txtAddressStreetNum.requestFocusInWindow(); // make it ready to enter the value
			txtAddressStreetNum.selectAll(); // select all text in the text field to
												// delete it or to replace it
			isValidated = false;
		}

		if (!isValidated)
			return (isValidated);

		// For txtAddressStreetNum, also need to verify the entered value is a valid
		// numeric
		try {
			@SuppressWarnings("unused")
			long tempSN = Long.parseLong(txtAddressStreetNum.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmAddEmployee, "Street Number must have a Numeric Value.");
			txtAddressStreetNum.requestFocusInWindow(); // make it ready to enter the value
			txtAddressStreetNum.selectAll(); // select all text in the text field
												// to delete it or to replace it
			isValidated = false;
		}

		if (!isValidated)
			return (isValidated);

		// ----------- Validate txtAddressStreetName text field
		try {
			value = txtAddressStreetName.getText();
			Validate.notBlank(value);

			if (value.length() > 20) {
				JOptionPane.showMessageDialog(frmAddEmployee, "Street Name - twenty letters maximum.");
				value = value.substring(0, Math.min(value.length(), 20));
				txtAddressStreetName.setText(value);

				txtAddressStreetName.requestFocusInWindow(); // make it ready to enter the value
				txtAddressStreetName.selectAll(); // select all text in the text field to
				// delete it or to replace it

				isValidated = false;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmAddEmployee,
					"All the text fields must have valid values - Street Name cannot be blank !!!");
			txtAddressStreetName.requestFocusInWindow(); // make it ready to enter the value
			txtAddressStreetName.selectAll(); // select all text in the text field to
			// delete it or to replace it
			isValidated = false;
		}

		if (!isValidated)
			return (isValidated);

		// ----------- Validate txtAddressCity text field
		try {
			value = txtAddressCity.getText();
			Validate.notBlank(value);

			if (value.length() > 20) {
				JOptionPane.showMessageDialog(frmAddEmployee, "City Name - twenty letters maximum.");
				value = value.substring(0, Math.min(value.length(), 20));
				txtAddressCity.setText(value);

				txtAddressCity.requestFocusInWindow(); // make it ready to enter the value
				txtAddressCity.selectAll(); // select all text in the text field to
											// delete it or to replace it

				isValidated = false;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmAddEmployee,
					"All the text fields must have valid values - City Name cannot be blank !!!");
			txtAddressCity.requestFocusInWindow(); // make it ready to enter the value
			txtAddressCity.selectAll(); // select all text in the text field to
										// delete it or to replace it
			isValidated = false;
		}

		if (!isValidated)
			return (isValidated);

		// ----------- Validate txtAddressCity text field
		try {
			value = txtAddressCity.getText();
			Validate.notBlank(value);

			if (value.length() > 20) {
				JOptionPane.showMessageDialog(frmAddEmployee, "City Name - twenty letters maximum.");
				value = value.substring(0, Math.min(value.length(), 20));
				txtAddressCity.setText(value);

				txtAddressCity.requestFocusInWindow(); // make it ready to enter the value
				txtAddressCity.selectAll(); // select all text in the text field to
											// delete it or to replace it

				isValidated = false;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmAddEmployee,
					"All the text fields must have valid values - City Name cannot be blank !!!");
			txtAddressCity.requestFocusInWindow(); // make it ready to enter the value
			txtAddressCity.selectAll(); // select all text in the text field to
										// delete it or to replace it
			isValidated = false;
		}

		if (!isValidated)
			return (isValidated);

		// ----------- Validate txtAddressState text field
		try {
			value = txtAddressState.getText();
			Validate.notBlank(value);

			if (value.length() != 2) {
				JOptionPane.showMessageDialog(frmAddEmployee, "State  - requires two letters.");
				value = value.substring(0, Math.min(value.length(), 2));
				txtAddressState.setText(value);

				txtAddressState.requestFocusInWindow(); // make it ready to enter the value
				txtAddressState.selectAll(); // select all text in the text field to
												// delete it or to replace it

				isValidated = false;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmAddEmployee,
					"All the text fields must have valid values - State cannot be blank !!!");
			txtAddressState.requestFocusInWindow(); // make it ready to enter the value
			txtAddressState.selectAll(); // select all text in the text field to
											// delete it or to replace it
			isValidated = false;
		}

		if (!isValidated)
			return (isValidated);

		// ----------- Validate txtAddressZip text field
		try {
			value = txtAddressZip.getText();
			Validate.notBlank(value);

			if (value.length() != 5) {
				JOptionPane.showMessageDialog(frmAddEmployee, "Zip Code - requires five numbers.");
				value = value.substring(0, Math.min(value.length(), 5));

				txtAddressZip.requestFocusInWindow(); // make it ready to enter the value
				txtAddressZip.selectAll(); // select all text in the text field to
											// delete it or to replace it

				isValidated = false;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmAddEmployee,
					"All the text fields must have valid values - Zip Code cannot be blank !!!");
			txtAddressZip.requestFocusInWindow(); // make it ready to enter the value
			txtAddressZip.selectAll(); // select all text in the text field to
										// delete it or to replace it
			isValidated = false;
		}

		if (!isValidated)
			return (isValidated);

		// For txtAddressZip, also need to verify the entered value is a valid
		// numeric
		try {
			@SuppressWarnings("unused")
			long tempZC = Long.parseLong(txtAddressZip.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmAddEmployee, "Zip Code must have a Numeric Value.");
			txtAddressZip.requestFocusInWindow(); // make it ready to enter the value
			txtAddressZip.selectAll(); // select all text in the text field
										// to delete it or to replace it
			isValidated = false;
		}

		if (!isValidated)
			return (isValidated);

		return true;
	}

	private MaskFormatter createFormatter(String format, char placeHolder) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(format);
			// formatter.setPlaceholderCharacter(placeHolder);
			formatter.setAllowsInvalid(false); // if needed
			formatter.setOverwriteMode(true); // if needed
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
		}
		return formatter;
	}
}
