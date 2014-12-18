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
public class filtercategory extends HttpServlet {

    String status="";
    
    String myselect="";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        try {
    response.setContentType("text/html;charset=UTF-8");
    
    status=request.getParameter("status");
    
    myselect="<option value=\"\" >select category</option>";
    
    
    dbConn conn= new dbConn();
    
    String cats="select * from message_category where status='"+status+"'";
    
    conn.rs=conn.st.executeQuery(cats);
    
    while(conn.rs.next()){
    
    myselect+="<option value=\""+conn.rs.getString(1) +"\">"+conn.rs.getString(2) +"</option>";
    
    }
    
    PrintWriter out = response.getWriter();
    
    
    
    try {
        
        out.println(myselect);
        
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(filtercategory.class.getName()).log(Level.SEVERE, null, ex);
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
