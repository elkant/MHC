/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todbase;

import beans.loadmothersbean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sender.dbConn;

/**
 *
 * @author Manuel
 */
public class viewallmothers extends HttpServlet {
    
    
    HttpSession session;
    ArrayList allwomen= null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        try {
            session= request.getSession();
            
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            
            allwomen= new ArrayList();

            dbConn conn = new dbConn();

            String mothers = "select  mother_details.motherID as motherID,anc_no,FName,SName,LName,PhoneNo , facilityname,AdmissionNo,PNCRegNo  from mother_details LEFT join atoh on mother_details.motherID=atoh.motherid "
+" LEFT join mat_atoh on mat_atoh.motherid =mother_details.motherID "
+" LEFT join postnat_atof on mother_details.motherID=postnat_atof.motherid group by mother_details.motherID ";

            if (conn.st1.isClosed()) {
                conn = new dbConn();
            }
            System.out.println(mothers);
            
            conn.rs1 = conn.st1.executeQuery(mothers);
     //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%       
     //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%       
           
            
            
        String createdtable="";    
            
            
            
                           int count = 1;
  while (conn.rs1.next()) {

                loadmothersbean lm= new loadmothersbean();                
                lm.setMotherid(conn.rs1.getString("motherID"));
                lm.setAncNo(conn.rs1.getString("anc_no"));
                lm.setFname(conn.rs1.getString("FName"));
                lm.setMname(conn.rs1.getString("SName"));
                lm.setLname(conn.rs1.getString("LName"));
                lm.setPhone(conn.rs1.getString("PhoneNo"));
                lm.setFacility(conn.rs1.getString("facilityname"));
                lm.setAdmNo(conn.rs1.getString("mat_atoh.AdmissionNo"));
                lm.setPncNo(conn.rs1.getString("postnat_atof.PNCRegNo"));
                allwomen.add(lm);
                System.out.println("ROW NUMBER__"+count);
                count++;
                           }

 
  
                         
session.setAttribute("mothersfound",allwomen);
                      

       
            
            
            
     //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%       
     //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%       
            
            
      

         
        } catch (SQLException ex) {
            Logger.getLogger(loadmothers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      response.sendRedirect("viewallmothers1.jsp");  
      
      
      
        
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
