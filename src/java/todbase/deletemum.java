/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todbase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sender.dbConn;

/**
 *
 * @author SIXTYFOURBIT
 */
public class deletemum extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       String id = request.getParameter("id");    
        
       
        
                     dbConn conn = new dbConn();
                 
				//String query = "update chws set active='2' where chwid='"+id+"'";
				String query = "delete from mother_details where motherID='"+id+"'";
				String query1 = "delete from atoh where motherid='"+id+"'";
				String query3 = "delete from itop where motherid='"+id+"'";
				String query4 = "delete from qtow where motherid='"+id+"'";
				String query5 = "delete from xtoad where motherid='"+id+"'";
				String query6 = "delete from aetoak where motherid='"+id+"'";
				String query7 = "delete from altoan where motherid='"+id+"'";
				String query8 = "delete from maternal_details where motherid='"+id+"'";
				String query9 = "delete from anc_visits where motherid='"+id+"'";
			         
                                //delete from pnc 
                              String querypostnat1 = "delete from postnat_aatoae where motherid='"+id+"'";  
                              String querypostnat2 = "delete from postnat_aftoai where motherid='"+id+"'";  
                              String querypostnat3 = "delete from postnat_atof where motherid='"+id+"'";  
                              String querypostnat4 = "delete from postnat_gtom where motherid='"+id+"'";  
                              String querypostnat5 = "delete from postnat_ntot where motherid='"+id+"'";  
                              String querypostnat6 = "delete from postnat_utoz where motherid='"+id+"'";  
                               
                              
                              //delete from mat
                              String querymat1 = "delete from mat_actoai where motherid='"+id+"'";
                              String querymat2 = "delete from mat_ajtoan where motherid='"+id+"'";
                              String querymat3 = "delete from mat_atoh where motherid='"+id+"'";
                              String querymat4 = "delete from mat_htol where motherid='"+id+"'";
                              String querymat5 = "delete from mat_mtou where motherid='"+id+"'";
                              String querymat6 = "delete from mat_vtoab where motherid='"+id+"'";
                               
                    try {       
                        conn.st.executeUpdate(query);
                        conn.st.executeUpdate(query1);
                        conn.st.executeUpdate(query3);
                        conn.st.executeUpdate(query4);
                        conn.st.executeUpdate(query5);
                        conn.st.executeUpdate(query6);
                        conn.st.executeUpdate(query7);
                        conn.st.executeUpdate(query8);
                        conn.st.executeUpdate(query9);
                        
                        
                        //=====delete maternity=====
                            conn.st.executeUpdate(querymat1);
                            conn.st.executeUpdate(querymat2);
                            conn.st.executeUpdate(querymat3);
                            conn.st.executeUpdate(querymat4);
                            conn.st.executeUpdate(querymat5);
                            conn.st.executeUpdate(querymat6);
                            
                            
                         //======delete postnat
                                conn.st.executeUpdate(querypostnat1);
                                conn.st.executeUpdate(querypostnat2);
                                conn.st.executeUpdate(querypostnat3);
                                conn.st.executeUpdate(querypostnat4);
                                conn.st.executeUpdate(querypostnat5);
                                conn.st.executeUpdate(querypostnat6);
                        
                                System.out.println(query);
                                System.out.println(query1);
                                System.out.println(querypostnat1);
                                System.out.println(querymat1);
                                
                        conn.st.close();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(deletemum.class.getName()).log(Level.SEVERE, null, ex);
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
