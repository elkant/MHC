/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ADTELCONNECTION;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SIXTYFOURBIT
 */
public class adtelConn {
    
  







    
  public  ResultSet rs,rs1,rs2,rs3,rs4,rs_1,rs_2,rs_3,rs_4,rs_5,rs_6;
   public Statement st,st1,st2,st3,st4,st_1,st_2,st_3,st_4,st_5,st_6;
   PreparedStatement pst;
   
    
    
    public adtelConn(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://196.201.225.101:3306/fhi360","fhi360", "fh^37*360");
            st = conn.createStatement();
            st1 = conn.createStatement();
            st2= conn.createStatement();
            st3 = conn.createStatement();
            st4= conn.createStatement();
             st_5= conn.createStatement();
             st_6= conn.createStatement();
              st_4= conn.createStatement();
               st_3= conn.createStatement();
                st_2= conn.createStatement();
                 st_1= conn.createStatement();
            
           // pst=conn.prepareStatement(null);
        } catch (Exception ex) {
            Logger.getLogger(adtelConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    


    
    
}
