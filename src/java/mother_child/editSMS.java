/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mother_child;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sendSMS.dbConnect;

/**
 *
 * @author Maureen
 */
public class editSMS extends HttpServlet {

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
    String ID="";
    dbConnect conn = new dbConnect();
    HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        session = request.getSession();
    //int msgCount=(Integer)session.getAttribute("msgCount");
    
      ArrayList message = new ArrayList();
  // String query = "SELECT * FROM anc_visits WHERE DATE(VisitDate) = DATE(NOW())";     
                            if(message!=null && message.size()>0 )
                            {
                                message.clear();
                            }     
        try {
           
         if(request.getParameter("ID")!=null && request.getParameter("ID")!=""){
                ID=request.getParameter("ID");
                
                 
           }
         System.out.println("ID"+ID);
        String query= "select * from messages where message_id='"+ID+"'";
                   
                        conn.rs = conn.state.executeQuery(query);
                   MsgBean DB= new MsgBean(); 
        while(conn.rs.next()){
           
           DB.setID(conn.rs.getString("message_id"));
           DB.setMESSAGE(conn.rs.getString("message"));
           DB.setNOKMESSAGE(conn.rs.getString("nok_message"));
           DB.setSTATUS(conn.rs.getString("status_id"));
           DB.setCATEGORY(conn.rs.getString("category_id"));
           DB.setUJUMBE(conn.rs.getString("ujumbe"));
           DB.setUJUMBENOK(conn.rs.getString("nok_ujumbe"));
           DB.setKALENJIN(conn.rs.getString("Kale_msg"));
           DB.setKALENJINNOK(conn.rs.getString("Kale_msgNOK"));
           
        message.add(DB);
                                  }    
                                  
                                  session.setAttribute("message", message);
//                                  session.setAttribute("msgCount", msgCount);
                               
                              
                                   
       
                           response.sendRedirect("EditMessages.jsp");
//                                        
  
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
            Logger.getLogger(editSMS.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(editSMS.class.getName()).log(Level.SEVERE, null, ex);
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
