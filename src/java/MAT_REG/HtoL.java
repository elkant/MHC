/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MAT_REG;

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
public class HtoL extends HttpServlet {

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
    String HtoL = "";
    String query = "";
    String query2 = "";
String admNo="";
String motherID="";
dbConnect conn = new dbConnect();
 HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         admNo = request.getParameter("admNo");
           motherID = request.getParameter("motherID");
        // data from mother details table
       session = request.getSession();
       System.out.println("admNo"+admNo);
        int count1 = 1;
        String options = "";
      
        HtoL = "";
        HtoL += "<tr><th></th>"
                + "<th style=\"width: 100px;\"> Parity</th>"
                + "<th style=\"width: 100px;\"> Gravidae </th>"
                + "<th style=\"width: 100px;\">Date of Last Menstrual Period(LMP) </th>"
                + "<th style=\"width: 100px;\">Expected date of delivery(EDD)</th>"
                + "<th style=\"width: 100px;\">Diagnosis</th></tr>";

        HtoL += "<tr><th></th>"
                + "<th style=\"width: 100px;\"> (h)</th>"
                + "<th style=\"width: 100px;\"> (i) </th>"
                + "<th style=\"width: 100px;\">(j) </th>"
                + "<th style=\"width: 100px;\">(k)</th>"
                + "<th style=\"width: 100px;\">(l)</th></tr>";

        int count = 1;
        String option = "";

        try {
 //============================================================From i to m==========================   
            // query for getting lmp and edd
            query = "Select * from mat_htol where motherid='"+motherID+"'";
System.out.println(query);

if(conn.state.isClosed()){conn = new dbConnect();}
            conn.rs = conn.state.executeQuery(query);
           
            while (conn.rs.next()) {


              
//               cell++;

                    HtoL += "<tr><th style=\"width:40px;\">" + count1 + "</th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\"  style=\"width:80px;\" value=\"" + conn.rs.getString("Parity") + "\" name=\"parity" + count1 + "\" id=\"parity" + count1 + "\" /></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\" type=\"text\" style=\"width:80px;\" value=\"" + conn.rs.getString("Gravidae") + "\" name=\"gravidae" + count1+ "\" id=\"gravidae" + count1 + "\"/></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:80px;\" value=\"" + conn.rs.getString("LMP") + "\" name=\"LMP" + count1 + "\" id=\"LMP" + count1 + "\"></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:80px;\" value=\"" + conn.rs.getString("EDD") + "\" name=\"EDD" + count1 + "\"  id=\"EDD" + count1 + "\"/></th>"
                            +"<th style=\"width:180px;\"><textarea style=\"width:180px;\" name=\"Diagnosis" + count1 + "\"  id=\"Diagnosis" + count1 + "\"/>" + conn.rs.getString("Diagnosis") + "</textarea></th><input type=\"hidden\" name=\"HtoLID\" value=\"" + conn.rs.getString(1) +"\"></tr>";

             
             count1++;
//             count++;
            }
            

            if (HtoL.equals("")) {
                // allinputfields="No mother found under that ANC No.";
            }
            //_____________________after everything else, then add the default input fields.
            //This table will be used to only save the newly input table values 
            //thus the naming of the input fields is different
  HtoL += "<tr><th style=\"width:40px;\">" + count1 + "</th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\"  style=\"width:80px;\" value=\"\" name=\"New_Parity" + count+ "\" id=\"New_Parity" + count + "\" /></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_Gravida" + count + "\" id=\"New_Gravida" + count + "\"/></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:80px;\" value=\"\" readonly name=\"New_LMP" + count + "\" id=\"New_LMP" + count + "\"></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:80px;\" value=\"\" readonly name=\"New_EDD" + count1 + "\"  id=\"New_EDD" + count + "\"/></th>"
                            +"<th style=\"width:180px;\"><input type=\"text\" list=\"conditions\" style=\"width:120px;\" class=\"textbox\" name=\"New_Diagnosis" + count + "\"  id=\"New_Diagnosis" + count + "\"/><datalist id=\"conditions\"><option value=\"APH\"><option value=\"PPH\"><option value=\"ECLAMPSIA\"><option value=\"RUPTURED UTERUS\"><option value=\"OBSTRUCTED LABOUR\"><option value=\"SEPSIS\"><option value=\"LABOUR PAINS\"></datalist></th></tr>";



            //the hidden field which holds the number of hidden fields to update
            //no_of_old_rows
            HtoL += "<input type=\"hidden\" id=\"HtoL\"  name=\"HtoL\" value=\"" + count + "\"/> <input type=\"hidden\" name=\"HtoL_old_rows\"  id=\"HtoL_old_rows\" value=\"" + (count1-1) + "\"/>";
   String user1="";
            if(session.getAttribute("userid")!=null){
            
            user1=session.getAttribute("userid").toString();
            }
String history="insert  into history set ancnumber='"+admNo+"', userid='"+user1+"', page='Maternity Register' ";     
            //   System.out.println("ANC NO:" + allinputfields);



            try {
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("" + HtoL + "");
                out.println("</body>");
                out.println("</html>");

                HtoL = "";
            } finally {
                out.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(HtoL.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(HtoL.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(HtoL.class.getName()).log(Level.SEVERE, null, ex);
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
