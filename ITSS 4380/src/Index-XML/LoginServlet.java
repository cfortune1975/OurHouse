/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static java.time.Clock.system;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.*;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author cxf140730
 */
@WebServlet("/BinServlet")
public class BinServlet extends HttpServlet {
 
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        int warehouse_ID = request.getParameter("warehouse_ID");
        int bin_Number = request.getParameter("bin_Number");
        
        
        try {
            Class.forName ("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
        PreparedStatement ps = con.prepareStatement("insert into bin values(?,?,?)");

		ps.setInt(1, bin_Number);
        ps.setString(2, warehouse_ID);
		ps.setInt(3, bin_Capacity);
            boolean execute = ps.execute();
        System.out.println(execute);
        
        
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        // read form fields
        
         
        System.out.println("Warehouse ID: " + warehouse_ID);
        System.out.println("Bin Number: " + bin_Number);
		System.out.println("Bin Capacity: " + bin_Capacity)
 
        // do some processing here...
         
        // get response writer
        PrintWriter writer = response.getWriter();
         
        // build HTML code
        String htmlRespone = "<html>";
        htmlRespone += "<h2>The Warehouse ID is: " + warehouse_ID + "<br/>";
        htmlRespone += "The Bin Number is: " + bin_Number + "<br/>";
		htmlRespone += "The Bin Capacity is: " + bin_Capacity + "</h2>";
        htmlRespone += "</html>";
         
        // return response
        writer.println(htmlRespone);
         
    }
 
}