/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sendSMS.dbConnect;

/**
 *
 * @author Manuel
 */
public class loadvillages extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    
    
    dbConnect conn= new dbConnect();

  String datalis="<datalist id=\"villages\">";
  String villageoptions="<option >select village</option>";
String loadvillages="select * from  villages order by villagename";

conn.rs5=conn.state5.executeQuery(loadvillages);

while(conn.rs5.next()){

datalis+="<option value=\""+conn.rs5.getString(2) +"\">";
villageoptions+="<option value=\""+conn.rs5.getString(2) +"\">"+conn.rs5.getString(2).trim().toUpperCase()+"</option>";


}
datalis+="</datalist>";



    
    
    
    PrintWriter out = response.getWriter();
    try {
        out.println(villageoptions);
      
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(loadvillages.class.getName()).log(Level.SEVERE, null, ex);
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
