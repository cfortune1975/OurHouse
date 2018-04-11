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
import java.sql.ResultSet;
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
@WebServlet("/Fetch")
public class Fetch extends HttpServlet {
 
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        String warehouse_ID = request.getParameter("warehouse_ID");
        String password = "Warehouse ID does not exist";
        
        
        try {
            Class.forName ("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
        PreparedStatement ps = con.prepareStatement("select BIN_NUMBER, BIN_CAPACITY from BIN where WAREHOUSE_ID=?");


        ps.setString(1, warehouse_ID);
       
            ResultSet rs = ps.executeQuery();
        if(rs.next()){
             bin_Number = rs.getString(1);
			 bin_Capacity = rs.getString(2);
        }
       
        
        
        } catch (SQLException ex) {
            Logger.getLogger(Fetch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fetch.class.getName()).log(Level.SEVERE, null, ex);
        }
        // read form fields
          
        
         
       
        // do some processing here...
         
        // get response writer
        PrintWriter writer = response.getWriter();
         
        // build HTML code
        String htmlRespone = "<html>";
       
        htmlRespone += "Your password is: " + password + "</h2>";    
        htmlRespone += "</html>";
         
        // return response
        writer.println(htmlRespone);
         
    }
 
}