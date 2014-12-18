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
public class getPresentPregDet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       String anc_no, currPregDets;

String currPreg="";


String motherID = request.getParameter("motherID");
        anc_no = request.getParameter("ancno");

        //String  getdetails="Select FName , SName , LName ,Age , Gravida ,parity ,maritalStatus , education  , PhoneNo ,occupation ,nok ,relationship , NOKPhoneNo from Mother_details where anc_no='"+anc_no+"'";

        currPreg = "Select * from present_pregnancy where motherid='" + motherID + "'";



        currPregDets = "";
        currPregDets +="<th style=\"width: 80px;\"> No of Visit </th>"
                   +"<th style=\"width: 120px;\"> Date </th>"
                    +"<th style=\"width: 80px;\">Weight</th>"
                     +"<th style=\"width: 120px;\">Next Visit</th>";

        dbConnect conn = new dbConnect();
        try {

            //============================================================PREVIOUS PREGNANCY TABLE DETAILS==========================   




            conn.rs1 = conn.state.executeQuery(currPreg);
            //get the values from the databse and put them in the htmlfield appropriately

            int count1 = 1;
            int count = 1;
            while (conn.rs1.next()) {
                //height

                currPregDets += "<tr><td style=\"width:40px;\">" + count1 + "</td>"
                        + "<input type=\"hidden\"  style=\"width:80px;\"value=\"" + conn.rs1.getString(3) + "\" name=\"visitNo" + count1 + "\"  />"
                        + "<td style=\"width:120px;\"><input type=\"text\" style=\"width:120px;\" value=\"" + conn.rs1.getString(4) + "\" name=\"date" + count1 + "\" id=\"date"+ count1 + "\"/></td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs1.getString(5) + "\" name=\"weight" + count1 + "\" id=\"weight" + count1 + "\"/></td>"
                        + "<td style=\"width:120px;\"><input  type=\"text\" style=\"width:120px;\" value=\"" + conn.rs1.getString(6) + "\" name=\"NextVisit" + count1 + "\" id=\"NextVisit" + count1 + "\"/></td>"
                        + "<input  type=\"hidden\" style=\"width:120px;\" value=\"" + conn.rs1.getInt(1) + "\" name=\"PresentPregnancy" + count1 + "\" id=\"PresentPregnancy" + count1 + "\" />"
                        + "</tr>";




                count1++;





            }//end of while



//(((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((())))))))))))))))))))))))))))))))))))))))))))))))            
            //System.out.println(""+allinputfields);


            if (currPregDets.equals("")) {
                // allinputfields="No mother found under that ANC No.";
            }
            //_____________________after everything else, then add the default input fields.
            //This table will be used to only save the newly input table values 
            //thus the naming of the input fields is different


            currPregDets += "<tr><td style=\"width:40px;\">" + count1 + "</td>"
                    + "<input type=\"hidden\" style=\"width: 80px;\" name=\"new_VisitNo" + count + "\" id=\"new_VisitNo" + count + "\" value=\""+ count1 +"\" />"
                    + "<td style=\"width:120px;\"><input type=\"text\" value=\"\" style=\"width:120px;\" placeholder=\"yyyy-mm-dd\" name=\"new_date" + count + "\" id=\"new_date" + count + "\" /></td>"
                    + "<td style=\"width:80px;\"><input type=\"text\" value=\"\" style=\"width:80px;\" placeholder=\"in kgs\"  name=\"new_weight" + count + "\"  id=\"new_weight" + count + "\" /></td>"
                    + "<td style=\"width:120px;\"><input  style=\"width:120px;\" value=\"\" placeholder=\"yyyy-mm-dd\" name=\"new_NextVisit" + count + "\" id=\"new_NextVisit" + count + "\" onkeypress=\"ifenter(event);\" type=\"date\" required />"
                    + "</td>"
                    + "</tr>";
            
            
//             currPregDets += "<tr><td><input  type=\"submit\" style=\"width:120px;\" value=\"Submit\" name=\"submit\"></td></tr>";

            //the hidden field which holds the number of hidden fields to update
            //no_of_old_rows
            currPregDets += "<input type=\"hidden\" id=\"pregnancy\"  name=\"pregnancy\" value=\"" + count + "\"/> <input type=\"hidden\" id=\"pregnancy_old_rows\"   name=\"pregnancy_old_rows\" value=\"" + (count1 - 1) + "\"/>";


         //   System.out.println("ANC NO:" + allinputfields);


           
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("" + currPregDets + "");
                out.println("</body>");
                out.println("</html>");

                currPregDets = "";
            } finally {
                out.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(getPresentPregDet.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
