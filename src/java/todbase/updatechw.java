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
 * @author Nyabuto Geofrey
 */
public class updatechw extends HttpServlet {
String query1,query2,qr3,userid,fname,mname,lname,username,phoneno;
HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //____________________COMPUTER NAME____________________________________
 String computername=InetAddress.getLocalHost().getHostName();
         dbConn conn=new dbConn();
     session=request.getSession();
userid=request.getParameter("userid");
fname=request.getParameter("fname");

mname=request.getParameter("sname");
lname=request.getParameter("username");
phoneno=request.getParameter("phoneno");
//System.out.println(userid);      
// System.out.println(fname);
// System.out.println(mname);
// System.out.println(sname);
// System.out.println(username);
// System.out.println(phoneno);
query1="UPDATE chw SET chv_fname='"+fname+"',chv_mname='"+mname+"', chv_lname='"+lname+"', chv_phone='"+phoneno+"' where chw_id='"+userid+"'";


conn.st.executeUpdate(query1);

if(conn.st.executeUpdate(query1)==1){
    Date dat= new Date();
session.setAttribute("chwedited","<font color=\"green\">"+ fname+"s details edited Successfully</font>");
 String inserter="insert into audit set host_comp='"+computername+"' , action='edited "+fname+" "+lname+"s details',time='"+dat+"',actor_id='"+session.getAttribute("userid")+"'";                     
if (session.getAttribute("userid")!=null) {
       
         
       conn.st3.executeUpdate(inserter);    
             }
}
else{

session.setAttribute("chwedited", "Clerk Edit failed");
}
System.out.println(session.getAttribute("chwedited"));
response.sendRedirect("allchw.jsp");

        }
        finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(updatechw.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(updatechw.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
