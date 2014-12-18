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
public class viewMsgs extends HttpServlet {

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
    HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
          dbConnect conn = new dbConnect(); 
         session = request.getSession();
         
           String query="";
           int count=0;
            ArrayList msgs = new ArrayList();
  // String query = "SELECT * FROM anc_visits WHERE DATE(VisitDate) = DATE(NOW())";     
                            if(msgs!=null && msgs.size()>0 )
                            {
                                msgs.clear();
                            }       
                      int msgCount=0;
                         query="SELECT * FROM messages";
                                conn.rs = conn.state.executeQuery(query); 
                          while(conn.rs.next()){
                             msgCount++;
                             
                               MsgBean DB= new MsgBean();
                                DB.setID(conn.rs.getString(1));
                                 DB.setMESSAGE(conn.rs.getString(2));
                                DB.setNOKMESSAGE(conn.rs.getString(6));
                                      
                                       msgs.add(DB);
                                  
                                  }
                                  session.setAttribute("msgs", msgs);
                                  session.setAttribute("msgCount", msgCount);
                               
                                 //String nextJSP = "MHC/Messages.jsp";
                                        response.sendRedirect("Messages.jsp");
//                                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
//                                        dispatcher.forward(request,response);
                                       // conn.connect.close();
                                       // System.out.println("Disconnected from database");
       
                         

 
 
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
            Logger.getLogger(viewMsgs.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(viewMsgs.class.getName()).log(Level.SEVERE, null, ex);
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
