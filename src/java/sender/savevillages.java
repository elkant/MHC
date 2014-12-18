/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.Date;
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
 * @author SIXTYFOURBIT
 */
public class savevillages extends HttpServlet {

    String no_of_rows;
    HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        session = request.getSession();

        dbConn conn = new dbConn();
        no_of_rows = request.getParameter("no_of_rows");

        
        String notregistered="";
        
        String computername = InetAddress.getLocalHost().getHostName();

        String allchwsadded = "";

        for (int a = 1; a < Integer.parseInt(no_of_rows); a++) {


            String villagename;

            villagename = request.getParameter("villagename" + a);

           
            
           
         
            
            //check to avoid saving an existing chw
             try {
                 
            conn.rs1=conn.st1.executeQuery("select * from villages where villagename LIKE '"+villagename+"'");
            
            if(!conn.rs1.next()){
            if (villagename != null &&!villagename.trim().equals("")) {
               
                    conn.st.executeUpdate("insert into villages set villagename='" + villagename + "'");

               allchwsadded+=villagename+" ,";

            }
            }
            else{
            notregistered+=""+villagename+"    , ";
            
            }
            
 } catch (SQLException ex) {
                    Logger.getLogger(savevillages.class.getName()).log(Level.SEVERE, null, ex);
                }





        }//end of for loop

        Date dat = new Date();

        if (session.getAttribute("userid") != null) {
            try {
                String inserter = "insert into audit set host_comp='" + computername + "' , action='Registered villages(s) "+allchwsadded+" ',time='" + dat + "',actor_id='" + session.getAttribute("userid") + "'";

                conn.st3.executeUpdate(inserter);
            } catch (SQLException ex) {
                Logger.getLogger(savevillages.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
        String myreturn="";
        
        if(!allchwsadded.equals("")){
        
        myreturn+="<font color=\"green\">Village(s) "+allchwsadded+" registered succesfully</font>"; 
        }
        if(!notregistered.equals("")){
        myreturn+="  <font color=\"red\">Village(s) "+notregistered+" not registered since they already exist.</font>";
        }
        
        
        session.setAttribute("villageadded", myreturn);

        myreturn="";
        allchwsadded="";
        notregistered="";
        
        response.sendRedirect("addvillages.jsp");


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
