/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sendSMS;

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

/**
 *
 * @author Maureen
 */
public class retrieveSMSedit extends HttpServlet {

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
   String status,category;
      int msgCount;    
    HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
          dbConnect conn = new dbConnect(); 
         session = request.getSession(true);
         String viewMsgs="<tr><th>#</th><th>MOTHER'S SMS</th><th>NEXT OF KIN'S SMS</th></tr>";
           String query="";
           int count=0;
          msgCount=0;
           if(request.getParameter("status")!=null && !request.getParameter("status").equals("")){
           
           status= request.getParameter("status");
           System.out.println(status);
           }
            if(request.getParameter("category")!=null && !request.getParameter("category").equals("")){
           category=request.getParameter("category"); 
             System.out.println(category);
            }
            ArrayList msgs = new ArrayList();
  // String query = "SELECT * FROM anc_visits WHERE DATE(VisitDate) = DATE(NOW())";     
                            if(msgs!=null && msgs.size()>0 )
                            {
                                msgs.clear();
                            }       
                     
                        if(status!= null && !status.equals("") && category != null && !category.equals("")){
                         query="select * from messages where status_id='"+status+"' && category_id='"+category+"'";
                                conn.rs = conn.state.executeQuery(query);
                                System.out.println(query);
                                  
                          while(conn.rs.next()){
                             msgCount++;
                             
                             
                                String ID=conn.rs.getString(1);
                               String MESSAGE= conn.rs.getString(2);
                                String NOKMESSAGE= conn.rs.getString(6);
                                      
                                      
                                  
                                  
                                viewMsgs+="<tr><td>"+ msgCount +"</td>"
                                         + "<td>"+MESSAGE+" </td>"
                                         + "<td>"+NOKMESSAGE+"</td> "
                                       
                                        + "</tr><tr><th colspan=\"3\"><input type=\"button\" name=\"edit\" value=\"Edit\" style=\"background-color:#49743D;font-weight:bold;color:#ffffff;\" onclick=\"editRecord("+ID+");\" ></th></tr>";

           
           count++;
           }
                   
              
                               //String noMsg="";
          System.out.println("msgcount before "+msgCount);
         // System.out.println("no msg before "+  noMsg);
                          if(msgCount==0){
                         String  noMsg="";
                         noMsg="No Messages under the Category";
                         System.out.println("no msg  "+  noMsg);
                         
                         viewMsgs="<tr><td colspan=\"8\" style=\"background:white;\">No Messages under the selected status and categorycombination </td>";
                         
                          //session.setAttribute("nm", noMsg);
                          }
                          else if(msgCount>0) {
                               String noMsgs="";
                               noMsgs="";
                           System.out.println("no msg  "+  noMsgs);
                       
                           //session.setAttribute("nm", null );
                          }           
                          
            out.println("<html>");
            out.println("<head>");           
            out.println("</head>");
            out.println("<body>");
            out.println(""+viewMsgs+"");
            out.println("</body>");
            out.println("</html>");
              count++; 
              session.setAttribute("count", count);
           
            
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
            Logger.getLogger(mother_child.viewMsgs.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(mother_child.viewMsgs.class.getName()).log(Level.SEVERE, null, ex);
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

