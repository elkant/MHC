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
public class getPrevServices extends HttpServlet {

    String anc_no, allinputfields, alreadyaddedids;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");


        anc_no = request.getParameter("ancno");
String motherID = request.getParameter("motherID");
        //String  getdetails="Select FName , SName , LName ,Age , Gravida ,parity ,maritalStatus , education  , PhoneNo ,occupation ,nok ,relationship , NOKPhoneNo from Mother_details where anc_no='"+anc_no+"'";

        String getotherdetails = "Select * from preventive_services where motherid='" + motherID + "' order by prentive_id ";
        String getprevids = "Select * from preventions";


        allinputfields = "";
        alreadyaddedids = "";
        // table header
        allinputfields = "<tr><th style=\"width:12px;\">Preventive Services</th><th style=\"width:62px;\">DATE</th>"
                + "<th style=\"width:62px;\">Next Visit</th></tr>";

        dbConn conn = new dbConn();
        try {

            //============================================================PREVIOUS PREGNANCY TABLE DETAILS==========================   




            conn.rs1 = conn.st1.executeQuery(getotherdetails);
            //get the values from the databse and put them in the htmlfield appropriately
            int lasttworows = 2;
            int allrows = 4;//the total no of rows which a single mother should have
            int count = 1; //old table values
            int count1 = 1;//new table values

//===========THE ALREADY DEFINED TABLE VALUES==============================================================================            
            while (conn.rs1.next()) {

//get the name of the prevention type since in the preventive services db we only have the id
                conn.rs2 = conn.st2.executeQuery("Select prev_name from preventions where prevention_id='" + conn.rs1.getString(2) + "'");
                conn.rs2.next();
                //name
                String preventionname = conn.rs2.getString(1);

                //add the name to the list of already fetched prevention names

                alreadyaddedids += preventionname + "|";

                if (conn.rs1.getString("prentive_id").equals("4") || conn.rs1.getString("prentive_id").equals("5")) {

                    allinputfields += "<tr><td><label >" + preventionname + "</label><input id=\"label" + count + "\" type=\"hidden\" value=\"" + conn.rs1.getString(2) + "\"></td> <td><input  type=\"text\" placeholder=\"yyyy-mm-dd\" name=\"ps_old_date" + count + "\" value=\"" + conn.rs1.getString(3) + "\" id=\"_dpr1" + count + "\"/></td> <td><input placeholder=\"yyyy-mm-dd\"  type=\"hidden\" value=\"" + conn.rs1.getString(4) + "\" name=\"old_nextvisit" + count + "\" id=\"_dpr2" + count + "\">"
                            + "<input type=\"hidden\" name=\"prev_table_id" + count + "\" value=\"" + conn.rs1.getString(1) + "\"></td></tr>";
                    lasttworows--;

                } else {
                    allinputfields += "<tr><td><label >" + preventionname + "</label><input id=\"label" + count + "\" type=\"hidden\" value=\"" + conn.rs1.getString(2) + "\"></td> <td><input type=\"text\"  placeholder=\"yyyy-mm-dd\" name=\"ps_old_date" + count + "\" value=\"" + conn.rs1.getString(3) + "\" id=\"_dpr1" + count + "\"/></td> <td><input placeholder=\"yyyy-mm-dd\"  type=\"text\" value=\"" + conn.rs1.getString(4) + "\" name=\"old_nextvisit" + count + "\" id=\"_dpr2" + count + "\">"
                            + "<input type=\"hidden\"  name=\"prev_table_id" + count + "\" value=\"" + conn.rs1.getString(1) + "\"></td></tr>";
                    allrows--;
                }

                count++;

               


            }//end of while


//+++++++++++++++++++++++++++GET THE VALUES FORM THE DBASE+++++++++++++++++++++++++++++++
            String myselect = "<option value=\"\"></option>";
            String lowermyselect = "<option value=\"\"></option>";
            conn.rs = conn.st.executeQuery(getprevids);

            while (conn.rs.next()) {
                //avoid repeating the already defined names and they should not be the last two

                if (!alreadyaddedids.contains(conn.rs.getString(2)) && !conn.rs.getString(1).equals("4") && !conn.rs.getString(1).equals("5")) {
                    myselect += "<option value=\"" + conn.rs.getString(1) + "\">" + conn.rs.getString(2) + "</option>";
                }

            }
//get the last two drop down lists







            conn.rs = conn.st.executeQuery(getprevids);


            while (conn.rs.next()) {
                //avoid repeating the already defined names and they should not be the first four

                if (!alreadyaddedids.contains(conn.rs.getString(2)) && !conn.rs.getString(1).equals("1") && !conn.rs.getString(1).equals("2") && !conn.rs.getString(1).equals("3")&&!conn.rs.getString(1).equals("6")) {
                    lowermyselect += "<option value=\"" + conn.rs.getString(1) + "\">" + conn.rs.getString(2) + "</option>";
                }

            }



//==============================================NEW VALUES================================================            
            //THIS ARE THE NEW VALUES

            for (int a = 0; a < allrows; a++) {

                allinputfields += "<tr><td><select   id=\"prevention" + count1 + "\" name=\"prevention" + count1 + "\" >" + myselect + "</select></td> <td><input type=\"text\" placeholder=\"yyyy-mm-dd\"  name=\"date" + count1 + "\" value=\"\" id=\"dpr1" + count1 + "\"/></td> <td><input placeholder=\"yyyy-mm-dd\"  type=\"text\" name=\"nextvisit" + count1 + "\" id=\"dpr2" + count1 + "\"></td></tr>";

                count1++;

            }
//================================THE LAST TWO ROWS WHICH DONT HAVE NEXT VISIT DATE===============================
            for (int a = 0; a < lasttworows; a++) {

                allinputfields += "<tr><td><select id=\"prevention" + count1 + "\"  name=\"prevention" + count1 + "\" >" + lowermyselect + "</select></td> <td><input type=\"text\" placeholder=\"yyyy-mm-dd\"   name=\"date" + count1 + "\" value=\"\" id=\"dpr1" + count1 + "\"/></td> <td></td></tr>";

                count1++;

            }

//=====================================end of new values=======================================================

            if (allinputfields.equals("")) {

                allinputfields = "No mother found under that ANC No.";
            }
            //_____________________after everything else, then add the default input fields.
            //This table will be used to only save the newly input table values 
            //thus the naming of the input fields is different




            //the hidden field which holds the number of hidden fields to update
            //no_of_old_rows
            allinputfields += "<tr><td><input type=\"hidden\" id=\"ps_no_of_rows\" name=\"ps_no_of_rows\" class=\"dp\"value=\"" + (count1 - 1) + "\"/></td><td> <input type=\"hidden\" id=\"ps_no_of_old_rows\" name=\"ps_no_of_old_rows\" value=\"" + (count - 1) + "\"/></td><td> </td></td></tr>";


            // System.out.println("ANC NO:"+allinputfields); 


            PrintWriter out = response.getWriter();
            try {

                out.println("" + allinputfields + "");



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
