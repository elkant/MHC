/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mother_child;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
public class MothersDiary extends HttpServlet {

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
          String ancno="";
           String anc="";
           String query1="";
           String query="";
           int count=0;
           String date="";
            ArrayList details = new ArrayList();
  // String query = "SELECT * FROM anc_visits WHERE DATE(VisitDate) = DATE(NOW())";     
                            if(details!=null && details.size()>0)
                            {
                                details.clear();
                            }       
//                         query="SELECT * FROM anc_visits WHERE DATE(VisitDate) = DATE(CURDATE()-2)";
                         query="SELECT * FROM anc_visits WHERE DATE(VisitDate) = DATE(CURDATE())";
                                conn.rs = conn.state.executeQuery(query); 
                          while(conn.rs.next()){
                              count++;
                              anc  = conn.rs.getString("ancNo");
                               MotherBean DB= new MotherBean();
                            DB.setANCNO(conn.rs.getString("ancNo"));
                            DB.setSTATUS(conn.rs.getString("Status"));
                             date=conn.rs.getString("VisitDate");
                          System.out.println("ANC======="+anc);
                          System.out.println("date======="+date);
                          
                         
                           query1= "SELECT * FROM mother_details where anc_no='"+anc+"'";
                            conn.rs2 = conn.state2.executeQuery(query1);
                                  while(conn.rs2.next()){
                                      DB.setFNAME(conn.rs2.getString("FName"));
                                      DB.setSNAME(conn.rs2.getString("SName"));
                                      DB.setPHONENO(conn.rs2.getString("PhoneNo"));
                                      DB.setNOKPHONE(conn.rs2.getString("NokPhoneNo"));
                                      DB.setLANGUAGE(conn.rs2.getString("languagePreferred"));
                                      DB.setCONSENT(conn.rs2.getString("nok_consent"));
                                      DB.setNOK(conn.rs2.getString("nok"));
                                       details.add(DB);
                                  
                                  } }
                                  session.setAttribute("details", details);
                                  session.setAttribute("rowCount", count);
                                   session.setAttribute("date", date);
                                 String nextJSP = "Diary.jsp";
                                        
                                       response.sendRedirect(nextJSP);
                                       // conn.connect.close();
                                        System.out.println("Disconnected from database");
       
                         

 
 
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
            Logger.getLogger(MothersDiary.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MothersDiary.class.getName()).log(Level.SEVERE, null, ex);
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
