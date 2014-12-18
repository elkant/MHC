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
import javax.servlet.http.HttpSession;
import sendSMS.dbConnect;

/**
 *
 * @author Maureen
 */
public class UpdateDiary extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */HttpSession session;
     dbConnect conn = new dbConnect();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        session = request.getSession();
       int counters = (Integer)session.getAttribute("rowCount");
       String Status="";
       String rowID="";
       System.out.println("counter"+counters);
       
       // gets the updatd status from the form submitted
         for(int i=1;i<=counters;i++){ 
          if(request.getParameter("status"+i)!=null && request.getParameter("status"+i)!=""){
                   Status=request.getParameter("status"+i); 
                    System.out.println("Status"+Status);
             }
      
          if(request.getParameter("rowID"+i)!=null && request.getParameter("rowID"+i)!=""){
                   rowID=request.getParameter("rowID"+i); 
                    System.out.println("rowID"+rowID);
             }
             
        // updates anc_visits wen their status changes
         
          String update = "update anc_visits set Status='"+Status+"' where ancVisitsID='"+rowID+"'";
               conn.state.executeUpdate(update); 
             System.out.println(update);
         
             } 
         response.sendRedirect("UpdateMothersDiary");
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
            Logger.getLogger(UpdateDiary.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateDiary.class.getName()).log(Level.SEVERE, null, ex);
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
