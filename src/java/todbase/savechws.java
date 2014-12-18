/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todbase;

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
public class savechws extends HttpServlet {

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


            String fname, mname, lname, phoneno, sendsms;

            fname = request.getParameter("chv_fname" + a);

            mname = request.getParameter("chv_mname" + a);

            if (mname == null) {
                mname = "";
            }

            lname = request.getParameter("chv_lname" + a);

            
            if(!fname.equals("")&&!lname.equals("")&&!mname.equals("")){
            allchwsadded += fname + " " + lname + ",";
            }
            phoneno = request.getParameter("chv_phone" + a);

            sendsms = request.getParameter("chv_sms" + a);
            
            //check to avoid saving an existing chw
             try {
                 
            conn.rs1=conn.st1.executeQuery("select * from chw where chv_phone='"+phoneno+"'");
            
            if(!conn.rs1.next()){
            if (fname != null &&!fname.trim().equals("")&& lname != null&&!lname.trim().equals("") &&!phoneno.trim().equals("")&& phoneno != null) {
               
                    conn.st.executeUpdate("insert into chw set chv_fname='" + fname + "',chv_mname='" + mname + "',chv_lname='" + lname + "',chv_phone='" + phoneno + "'");

               

            }
            }
            else{
            notregistered+=""+fname+" "+lname+"  phoneno("+phoneno+") , ";
            
            }
            
 } catch (SQLException ex) {
                    Logger.getLogger(savechws.class.getName()).log(Level.SEVERE, null, ex);
                }





        }//end of for loop

        Date dat = new Date();

        if (session.getAttribute("userid") != null) {
            try {
                String inserter = "insert into audit set host_comp='" + computername + "' , action='Registered chws(s) "+allchwsadded+" ',time='" + dat + "',actor_id='" + session.getAttribute("userid") + "'";

                conn.st3.executeUpdate(inserter);
            } catch (SQLException ex) {
                Logger.getLogger(savechws.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
        String myreturn="";
        
        if(!allchwsadded.equals("")){
        
        myreturn+="<font color=\"green\">Chw(s) "+allchwsadded+" registered succesfully</font>"; 
        }
        if(!notregistered.equals("")){
        myreturn+=" . <font color=\"red\">Chw(s) "+notregistered+" not registered due to using already added phonenumbers</font>";
        }
        
        
        session.setAttribute("chwadded", myreturn);

        myreturn="";
        allchwsadded="";
        notregistered="";
        
        response.sendRedirect("Addchw.jsp");


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
