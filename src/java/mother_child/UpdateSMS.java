/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mother_child;

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
 * @author Maureen
 */
public class UpdateSMS extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String message;
    String nokmessage;
    String ujumbe;
    String ujumbenok;
    String status;
    String category;
    String ID;
    dbConnect conn = new dbConnect();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            if (request.getParameter("message") != null && request.getParameter("message") != "") {
                message = request.getParameter("message");
            }
            if (request.getParameter("nokmessage") != null && request.getParameter("nokmessage") != "") {
                nokmessage = request.getParameter("nokmessage");
            }
            if (request.getParameter("ujumbe") != null && request.getParameter("ujumbe") != "") {
                ujumbe = request.getParameter("ujumbe");
            }
            if (request.getParameter("ujumbenok") != null && request.getParameter("ujumbenok") != "") {
                ujumbenok = request.getParameter("ujumbenok");
            }
            if (request.getParameter("status") != null && request.getParameter("status") != "") {
                status = request.getParameter("status");
            }
            if (request.getParameter("category") != null && request.getParameter("category") != "") {
                category = request.getParameter("category");
            }
            if (request.getParameter("ID") != null && request.getParameter("ID") != "") {
                ID = request.getParameter("ID");
            }
            String kaleMsg="";
  if(request.getParameter("kaleMsg")!= null && request.getParameter("kaleMsg")!=""){
             kaleMsg= request.getParameter("kaleMsg");}
            String kaleMsgNOK="";
             if(request.getParameter("kaleMsgNOK")!= null && request.getParameter("kaleMsgNOK")!=""){
             kaleMsgNOK= request.getParameter("kaleMsgNOK");
             }
              if(request.getParameter("status")!= null && request.getParameter("status")!=""){
              status= request.getParameter("status");
              }
               
               String query= "update messages set message='"+message+"', status_id='"+status+"',category_id='"+category+"',nok_message='"+nokmessage+"'"
                       + ",ujumbe='"+ujumbe+"',nok_ujumbe='"+ujumbenok+"',salutation='Dear',salamu='Jambo',salutation_Kale='',Kale_msg='"+kaleMsg+"',Kale_msgNOK='"+kaleMsgNOK+"' where message_id='"+ID+"'";
               System.out.println(query);
               conn.state.executeUpdate(query);
           response.sendRedirect("viewMsgs");
        } finally {
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
            Logger.getLogger(UpdateSMS.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateSMS.class.getName()).log(Level.SEVERE, null, ex);
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
