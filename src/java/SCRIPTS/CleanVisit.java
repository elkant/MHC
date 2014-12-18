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

/**
 *
 * @author Manuel
 */
public class CleanVisit extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          
            dbConn conn= new dbConn();
            
            
            String qr="select motherid , count(*) as VISITS from atoh group by motherid order by DateofVisit ASC limit 10000";
            try {
                conn.rs=conn.st.executeQuery(qr);
                
                while(conn.rs.next()){
                
                    String updqr="select * from atoh where motherid='"+conn.rs.getString(1)+"'";
                    
                    int visitno=0;
                    
                    conn.rs1=conn.st1.executeQuery(updqr);
                    
                    while(conn.rs1.next()){
                    visitno++;
                    //UPDATE THE FIRST VISIT
                    String isfirstvisit="No";
                    if(visitno==1){isfirstvisit="Yes";}
                    
                    String update=" update atoh set visit_no='"+visitno+"', FirstVisit='"+isfirstvisit+"' where ancRegisterID='"+conn.rs1.getString(1) +"'";
                    
                    System.out.println(update);
                    
                    conn.st3.executeUpdate(update);
                    
                                          }
                
                
                
                                     }
                
            } catch (SQLException ex) {
                Logger.getLogger(CleanVisit.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } finally {            
            out.close();
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
