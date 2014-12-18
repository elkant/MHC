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
public class transferchw extends HttpServlet {

    String ancno, chwid;
    HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            session = request.getSession();

            ancno = request.getParameter("ancno");
            chwid = request.getParameter("chwid");
            String newchw = "";

            dbConn conn = new dbConn();

            conn.st.executeUpdate("update mother_details set ChwID ='" + chwid + "' where anc_no='" + ancno + "'");





            conn.rs1 = conn.st1.executeQuery("select * from chw where  chv_phone='"+chwid+"'");
            conn.rs1.next();

            newchw = conn.rs1.getString("chv_fname") + " " + conn.rs1.getString("chv_mname") + " " + conn.rs1.getString("chv_lname");

            String computername = InetAddress.getLocalHost().getHostName();

            Date dat = new Date();

            if (session.getAttribute("userid") != null) {
                try {
                    String inserter = "insert into audit set host_comp='" + computername + "' , action='Transfered mother whose anc no is "+ancno+" to a chw  whose chwid is "+chwid+" ',time='" + dat + "',actor_id='" + session.getAttribute("userid") + "'";

                    conn.st3.executeUpdate(inserter);
                } catch (SQLException ex) {
                    Logger.getLogger(savechws.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            PrintWriter out = response.getWriter();
            try {

                out.println("" + newchw);

            } finally {
                out.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(transferchw.class.getName()).log(Level.SEVERE, null, ex);
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
