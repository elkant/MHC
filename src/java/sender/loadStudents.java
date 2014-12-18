/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author SIXTYFOURBIT
 */
public class loadStudents {
    
    dbConn conn= new dbConn();
    
    String pth="D:\\k.csv";
    String myloader="LOAD DATA LOCAL INFILE 'D:/k.csv' INTO TABLE mother_profile ";
   
  public loadStudents(){
        try {
            conn.st.executeUpdate(myloader);
        } catch (SQLException ex) {
            Logger.getLogger(loadStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  }
    
}
