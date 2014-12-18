/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SIXTYFOURBIT
 */
public class getPrevPregDetails extends HttpServlet {

    String anc_no, allinputfields;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");





        anc_no = request.getParameter("ancno");
String motherID = request.getParameter("motherID");
        //String  getdetails="Select FName , SName , LName ,Age , Gravida ,parity ,maritalStatus , education  , PhoneNo ,occupation ,nok ,relationship , NOKPhoneNo from Mother_details where anc_no='"+anc_no+"'";

        String getotherdetails = "Select * from sergical_history where motherid='" + motherID + "'";



        allinputfields = "";
        
        
        
        //table header
       allinputfields+="<tr><th style=\"width:62px;\">Pregnancy Order</th><th style=\"width:62px;\">Year</th>"+
                   "<th style=\"width:62px;\">Place Of Delivery</th>"+
                   "<th style=\"width:62px;\">Type of Delivery</th>"+
                   "</tr>"; 

        //get the delivery modes
        
        
        String newdeliverymodes="<option value=\"\"></option>";
        String olddeliverymodes="<option value=\"\"></option>";
        
       
        
        
        dbConn conn = new dbConn();
        try {

            //============================================================PREVIOUS PREGNANCY TABLE DETAILS==========================   



            conn.rs1 = conn.st1.executeQuery(getotherdetails);
            //get the values from the databse and put them in the htmlfield appropriately

            int count1 = 1;
            int count = 1;
            while (conn.rs1.next()) {
                //the delivery modes
                
                conn.rs = conn.st.executeQuery("Select * from delivery_mode");
                while (conn.rs.next()) {

                    if (conn.rs.getString(1).trim().equals(conn.rs1.getString(6))) {

                        olddeliverymodes += "<option selected value=\"" + conn.rs.getString(1) + "\">" + conn.rs.getString(2) + "</option>";
                    } else {

                        olddeliverymodes += "<option  value=\"" + conn.rs.getString(1) + "\">" + conn.rs.getString(2) + "</option>";

                    }


                }

                

                allinputfields += "<tr><td style=\"width:56px;text-align: center;\">" + count1 + 
                        "<input type=\"hidden\" value=\"" + conn.rs1.getString(3) + "\" name=\"preg_0rder" + count1 + "\" id=\"preg_0rder" + count1 + "\" /></td>"
                        + "<td style=\"width:12px;\"><input  type=\"text\" value=\"" + conn.rs1.getString(4) + "\" name=\"year" + count1 + "\"   id=\"year" + count1 + "\"/></td>"
                        + "<td style=\"width:12px;\"><input  type=\"text\" value=\"" + conn.rs1.getString(5) + "\" name=\"place_of_delivery" + count1 + "\"  id=\"place_of_delivery" + count1 + "\"/></td>"
                        + "<td style=\"width:12px;\"><select   name=\"Type_of_delivery" + count1 + "\" id=\"Type_of_delivery" + count1 + "\" >"+olddeliverymodes+"</select>"
                        + "<input type=\"hidden\" value=\"" + conn.rs1.getString(1) + "\" name=\"surgicalid" + count1 + "\"></td>"
                        + "</tr>";



olddeliverymodes="";
                count1++;





            }//end of while



//(((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((())))))))))))))))))))))))))))))))))))))))))))))))            
            //System.out.println(""+allinputfields);

            
            
           // the delivery modes
            
            
              conn.rs=conn.st.executeQuery("Select * from delivery_mode");
                while (conn.rs.next()) {

                 

                        newdeliverymodes += "<option  value=\"" + conn.rs.getString(1) + "\">" + conn.rs.getString(2) + "</option>";
                    
                    
                    
                }
            
            

            if (allinputfields.equals("")) {
                // allinputfields="No mother found under that ANC No.";
            }
            //_____________________after everything else, then add the default input fields.
            //This table will be used to only save the newly input table values 
            //thus the naming of the input fields is different



            allinputfields += "<tr><td style=\"width:56px;text-align: center;\">" + count1 + 
                    "<input type=\"hidden\" value=\""+count1+"\"  name=\"new_preg_0rder" + count + "\" id=\"new_preg_0rder" + count + "\" /></td>"
                    + "<td style=\"width:12px;\"><input  type=\"text\" pattern=\"[0-9]{4}\" placeholder=\"YYYY\" name=\"new_year" + count + "\"  id=\"new_year" + count + "\"/></td>"
                    + "<td style=\"width:12px;\"><input  type=\"text\" id=\"new_place_of_delivery" + count + "\" name=\"new_place_of_delivery" + count + "\"/></td>"
                    + "<td style=\"width:12px;\"><select name=\"new_Type_of_delivery" + count + "\" id=\"new_Type_of_delivery" + count + "\" onkeypress=\"ifenter(event);\" >"+newdeliverymodes+"</select>"
                    + "</td>"
                    + "</tr>";

            //the hidden field which holds the number of hidden fields to update
            //no_of_old_rows
            allinputfields += "<input type=\"hidden\" id=\"no_of_rows\" name=\"no_of_rows\" value=\"" + count + "\"/> <input type=\"hidden\" id=\"no_of_old_rows\" name=\"no_of_old_rows\" value=\"" + (count1 - 1) + "\"/>";


            //   System.out.println("ANC NO:" + allinputfields);


            PrintWriter out = response.getWriter();
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("" + allinputfields + "");
                out.println("</body>");
                out.println("</html>");

                allinputfields = "";
            } finally {
                out.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(getPrevPregDetails.class.getName()).log(Level.SEVERE, null, ex);
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
