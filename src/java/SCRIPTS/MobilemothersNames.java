/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SCRIPTS;

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
import sender.mobiledbConn;

/**
 *
 * @author Manuel
 */
public class MobilemothersNames extends HttpServlet {

   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    
    dbConn conn= new dbConn();
    
    mobiledbConn mconn= new mobiledbConn();
    
    
    
    //select all the mothers from the mobile db //mhc who do not have a name, then try to search the name from the 
    
   String getmothers="select * from mother_details where FName LIKE '' ";
   
   mconn.rs=mconn.st.executeQuery(getmothers);
  while(mconn.rs.next()){
  
      System.out.println("anc_no  "+ mconn.rs.getString("anc_no"));
      
      //get the mother details from the master db where 
  
      String getmothersupdated="select * from mother_details where anc_no LIKE '"+mconn.rs.getString("anc_no")+"' and facilityname LIKE '"+mconn.rs.getString("facilityname")+"' and Fname!=''";
  
      conn.rs1=conn.st.executeQuery(getmothersupdated);
      
      while(conn.rs1.next()){
      
          
          System.out.println(" MOBILE ANC NO "+mconn.rs.getString("anc_no")+"  EDD "+mconn.rs.getString("delivery_date")+"  HOSPITAL DATA "+conn.rs1.getString("FName")+" "+conn.rs1.getString("SName")+" "+conn.rs1.getString("LName"));
         
          //update mobile mothers using the names from our database
          
     String updatemothernames="update mother_details set FName='"+conn.rs1.getString("FName")+"' , SName='"+conn.rs1.getString("SName")+"' , LName='"+conn.rs1.getString("LName")+"' where motherID='"+mconn.rs.getString("motherID")+"'";     
          
      System.out.println(updatemothernames);    
          
      mconn.st3.executeUpdate(updatemothernames);
      
      
      }
  
  } 
    
    
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
       
         
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(MobilemothersNames.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
