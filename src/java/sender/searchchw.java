/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SIXTYFOURBIT
 */
public class searchchw extends HttpServlet {

    String name="";
    String qualifying_chw="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    name=request.getParameter("name");
    qualifying_chw="";
    qualifying_chw="<tr>"
            + "<th>#.</th>"
            + "<th>First Name</th>"
            + "<th>Middle name</th>"
            + "<th>Last name</th>"
            + "<th>Phone no</th>"
            + "<th>Mothers under this chw</th>"
            + "</tr>";
    String selectchws="select * from chw where chv_fname='"+name+"'OR chv_mname='"+name+"' OR chv_lname='"+name+"'";
    dbConn conn= new dbConn();
    
    conn.rs=conn.st.executeQuery(selectchws);
    int count1=0;
    
    while(conn.rs.next()){
        count1++;
        qualifying_chw+="<tr><td>"+count1+"</td><td>"+conn.rs.getString(2) +"</td><td>"+conn.rs.getString(3) +"</td><td>"+conn.rs.getString(4) +"</td><td>"+conn.rs.getString(5) +"</td>"
                + "<td>" 
        +"<form action=\"edit_mothers_under_chw.jsp\" method=\"post\" id=\"tableform\" style=\"background: #ffffff;\">"
+"<input type=\"hidden\" name=\"userid\" value=\""+conn.rs.getString(1)+"\"/>"
+"<input type=\"submit\" name=\"Submit\" value=\"view\" class=\"submit\"/>"                   
   +"</form></td>"
                + "</tr>";
    
    
    }
    if(count1<1){
        
        qualifying_chw+="<tr><td colspan=\"6\" style=\"background:yellow;\"> No chw yet found with that name! </td></tr>";
        
    }
    
    
    
    PrintWriter out = response.getWriter();
    try {
      
        out.println(qualifying_chw);
        
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(searchchw.class.getName()).log(Level.SEVERE, null, ex);
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
