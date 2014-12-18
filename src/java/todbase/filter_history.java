/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todbase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sender.dbConn;

/**
 *
 * @author SIXTYFOURBIT
 */
public class filter_history extends HttpServlet {
String date1="",date2="";

String allinputs="";
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    
   allinputs=""; 
   date1=request.getParameter("startdate"); 
   date2=request.getParameter("enddate"); 
   
   
  // System.out.println(date1+"  and  "+date2);
   dbConn conn= new dbConn();
   
   allinputs="<tr><th>ANC NO</th><th>DATE</th><th>PAGE</th><th>DONE BY USER</th></tr>";
    
  String hist ="select * from history where Date(date) between'"+date1+"' and  '"+date2+"'"; 
    
  
  conn.rs=conn.st.executeQuery(hist);
  int count=0;
  
  while(conn.rs.next()){
  
allinputs+="<tr><td>"+conn.rs.getString(2)+"</td>"
          +"<td>"+conn.rs.getString(4)+"</td>"
          +"<td>"+conn.rs.getString(5)+"</td>"
          +"<td>"+conn.rs.getString(3)+"</td></tr>";      
count++;  

  }
  
    if(count==0){
    
    allinputs+="<tr><td colspan=\"4\" style=\"background:yellow;\">No edits done between <b>"+date1+"</b> and <b>"+date2+"</b></td></tr>";
    }
    
    PrintWriter out = response.getWriter();
    try {
        
        out.println(allinputs);
        
        
        
        
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(filter_history.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
