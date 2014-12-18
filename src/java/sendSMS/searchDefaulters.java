/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sendSMS;

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


/**
 *
 * @author Maureen
 */
public class searchDefaulters extends HttpServlet {

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
    dbConnect conn = new dbConnect();
     HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String startdate = request.getParameter("startdate");
        String enddate = request.getParameter("enddate");
//          out.println("date----"+date);
          
       session = request.getSession();
        
//        System.out.println(startdate);
//        System.out.println(enddate);
          String results;
          results="";
          int count1=0;
          String options="";
           results +="<tr>"
                            +"<th style=\"width: 40px;\">ANC NO</th>"
                            +"<th style=\"width: 60px;\"> FIRST NAME </th>"
                            +"<th style=\"width: 100px;\">SECOND NAME </th>"
                            +"<th style=\"width: 80px;\">PHONE NO</th>"
                            +"<th style=\"width: 100px;\">NOK PHONE</th>"
                            +"<th style=\"width: 100px;\">STATUS </th>"
                            +"</tr>"; 
//           String query="SELECT * FROM anc_visits WHERE DATE(VisitDate) = DATE('"+date+"')";
           
         String query= "SELECT * FROM anc_visits where DATE(VisitDate) BETWEEN DATE('"+startdate+"') AND DATE('"+enddate+"')";
//System.out.println(query);
        

           conn.rs = conn.state.executeQuery(query); 
           
                          while(conn.rs.next()){
                          
                            String  anc  = conn.rs.getString("ancNo");
                           String status = conn.rs.getString("Status");
                           
                           if(status.equalsIgnoreCase("attended")){
                           options+="<option value=\"Not Attended\">Not Attended</option>";
                           
                           }
                           if(status.equalsIgnoreCase("Not Attended")){
                           options+="<option value=\"Attended\">Attended</option>";
                           
                           }
                            System.out.println("ANC"+anc);
                            String query1 = "select * from mother_details where anc_no='"+anc+"'";
                              conn.rs2 = conn.state2.executeQuery(query1);
                               while(conn.rs2.next()){
                                    count1++;
                               results += "<tr>"
                        + "<td style=\"width:80px;\">" + conn.rs2.getString("anc_no") + "</td>"
                        + "<td style=\"width:100px;\">" + conn.rs2.getString("FName") + "</td>"
                        + "<td style=\"width:100px;\">" + conn.rs2.getString("SName") + "</td>"
                        + "<td style=\"width:100px;\">" + conn.rs2.getString("PhoneNo") + "</td>"
                        + "<td style=\"width:100px;\">" + conn.rs2.getString("NOKPhoneNo") + "</td>"
                        + "<td style=\"width:100px;\">" + status + "<input type=\"hidden\" name=\"status"+count1+"\" id=\"status"+count1+"\" value=\""+ status +"\"></td>"
                        + "<td style=\"width:100px;\"><input type=\"checkbox\" value=\"" + conn.rs2.getString("anc_no") + "\" name=\"defaulter"+count1+"\"  id=\"defaulter"+count1+"\"></td></tr>"
                        ;
                       
         }
                               session.setAttribute("rowCount",count1);
                               options="";
                          }
                        results +=  " <tr >" 
                                +"<td colspan=\"8\"><input type=\"hidden\" name=\"count\" id=\"count\" value=\""+count1+"\"><input type=\"submit\" value=\"Send SMS to defaulters\" name=\"submit\" style=\"width: 200px;\"></td>"
                                +"</tr> "; 
                          if (count1==0){
                            results +="<tr><td colspan=\"7\"  style=\"width:100px;\"><font color=\"red\">No Defaulting Mothers on this date</font></td></tr>";
                         
                          }
                       
                                try {
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("<form method=\"post\" action=\"defaulterMSG\">");
                out.println("" + results + "");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");

                results = "";
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
            Logger.getLogger(searchDefaulters.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(searchDefaulters.class.getName()).log(Level.SEVERE, null, ex);
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
