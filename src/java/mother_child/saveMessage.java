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
public class saveMessage extends HttpServlet {

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
             String kaleMsg;
             String kaleMsgNOK;
             String ujumbenok;
             String status;
             String category;
             dbConnect conn = new dbConnect();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            String end ="";
            end="";
            if(request.getParameter("message")!= null && !"".equals(request.getParameter("message"))){
                 message= request.getParameter("message")+end;
                 
//                 System.out.println("message   "+message);
            }
           if(request.getParameter("nokmessage")!= null && !"".equals(request.getParameter("nokmessage"))){
                 nokmessage= request.getParameter("nokmessage")+end;
//            System.out.println("nokmessage   "+nokmessage);
           
           }
            if(request.getParameter("ujumbe")!= null && !"".equals(request.getParameter("ujumbe"))){
                 ujumbe= request.getParameter("ujumbe")+end;
            }
            if(request.getParameter("ujumbenok")!= null && !"".equals(request.getParameter("ujumbenok"))){
             ujumbenok= request.getParameter("ujumbenok")+end;
            }
            if(request.getParameter("kaleMsg")!= null && !"".equals(request.getParameter("kaleMsg"))){
             kaleMsg= request.getParameter("kaleMsg")+end;
            }
            if(request.getParameter("kaleMsgNOK")!= null && !"".equals(request.getParameter("kaleMsgNOK"))){
             kaleMsgNOK= request.getParameter("kaleMsgNOK")+end;
            }
              if(request.getParameter("status")!= null && !"".equals(request.getParameter("status"))){
              status= request.getParameter("status");
              }
               if(request.getParameter("category")!= null && !"".equals(request.getParameter("category"))){
             category= request.getParameter("category");
               }
               
               
               String query= "insert into messages set message='"+message+"', status_id='"+status+"',category_id='"+category+"',nok_message='"+nokmessage+"'"
                       + ",ujumbe='"+ujumbe+"',nok_ujumbe='"+ujumbenok+"',salutation='Dear',salamu='Jambo',salutation_Kale='Chamge',Kale_msg='"+kaleMsg+"',Kale_msgNOK='"+kaleMsgNOK+"'";
              
                     conn.state.executeUpdate(query);
                     response.sendRedirect("createSMS.jsp");
                     
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
            Logger.getLogger(saveMessage.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(saveMessage.class.getName()).log(Level.SEVERE, null, ex);
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
