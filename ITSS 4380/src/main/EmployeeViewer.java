package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class EmployeeViewer {

	private JFrame frmEmployeeView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeViewer window = new EmployeeViewer();
					window.frmEmployeeView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeeViewer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEmployeeView = new JFrame();
		frmEmployeeView.setResizable(false);
		frmEmployeeView.setTitle("Our House - Employees");
		frmEmployeeView.setBounds(100, 100, 450, 300);
		frmEmployeeView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
