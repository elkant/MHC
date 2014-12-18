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
import sendSMS.dbConnect;

/**
 *
 * @author SIXTYFOURBIT
 */
public class ItoMRegister extends HttpServlet {

    String ItoM = "";
    String query = "";
    String query2 = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        dbConnect conn = new dbConnect();
        PrintWriter out = response.getWriter();
        String anc_no = "";
        String motherID = "";
        anc_no = request.getParameter("ancno");
         motherID = request.getParameter("motherID");
        // data from mother details table
        query = "Select * from mother_details where motherid='" + motherID + "'";
         String rowcomplete="no";
        
        
        System.out.println(query);
        String counsel = "";
        int counselID = 0;
        int count1 = 0;
        String options = "";
        ArrayList counselled = new ArrayList();
        ArrayList counselledID = new ArrayList();

        String counselling = "Select * from counselling";
        String options1 = "";
        conn.rs7 = conn.state7.executeQuery(counselling);
        while (conn.rs7.next()) {

            counsel = conn.rs7.getString(2);
            counselID = conn.rs7.getInt(1);
            if (counsel != null && !"".equals(counsel) && counselID != 0) {
                options1 += "<option value=" + counselID + ">" + counsel + "</option>";
                counselled.add(counsel);
                counselledID.add(counselID);
            }


        }
        ItoM = "";
        ItoM += "<tr><th></th>"
                + "<th style=\"width: 40px;\"> Parity</th>"
                + "<th style=\"width: 60px;\"> Gravidae </th>"
                + "<th style=\"width: 100px;\">Date of Last Menstrual Period </th>"
                + "<th style=\"width: 80px;\">Expected date of delivery</th>"
                + "<th style=\"width: 100px;\">Gestation in weeks</th>"
                + "<th style=\"width: 100px;\">Weight </th>"
                + "<th style=\"width: 100px;\">Blood Pressure</th>"
                + "<th style=\"width: 100px;\">Counselled on</th></tr>";

        ItoM += "<tr><th></th>"
                + "<th style=\"width: 40px;\"> i</th>"
                + "<th style=\"width: 60px;\"> j </th>"
                + "<th style=\"width: 100px;\">k </th>"
                + "<th style=\"width: 80px;\">l</th>"
                + "<th style=\"width: 100px;\">m</th>"
                + "<th style=\"width: 100px;\">n </th>"
                + "<th style=\"width: 100px;\">o</th>"
                + "<th style=\"width: 100px;\">p</th></tr>";

        int count = 1;
        String option = "";

        try {

            conn.rs3 = conn.state3.executeQuery(query);
            int cell = 0;
            // while loop for the counter of cell
            while (conn.rs3.next()) {

                cell++;
                //System.out.println("cell+++++"+cell);
            }




            //============================================================From i to m==========================   
            // query for getting lmp and edd
            query2 = "Select * from maternal_details where motherid='" + motherID + "'";

            conn.rs4 = conn.state4.executeQuery(query2);
            int cell1 = 0;
            while (conn.rs4.next()) {
                cell1++;
//            System.out.println("cell+++++"+cell1);
            }


            // query for getting gestation etc frm itop
            String query3 = "Select * from itop where motherid='" + motherID + "'";


            conn.rs5 = conn.state5.executeQuery(query3);
            int cell4 = 0;
            while (conn.rs5.next()) {
                cell4++;
                // System.out.println("cell4________________"+cell4);


            }
            // for ensuring the fetch is arranged in one row

            if (cell4 != 0) {
                
                
                
                conn.rs_1 = conn.state_1.executeQuery(query3);
            } else {
                conn.rs_1 = conn.state_1.executeQuery(query);
            }
            conn.rs1 = conn.state1.executeQuery(query3);

            //get the values from the databse and put them in the htmlfield appropriately
            count1 = 1;
            conn.rs = conn.state.executeQuery(query2);
            while (conn.rs_1.next()) {


                conn.rs2 = conn.state2.executeQuery(query);

                if (conn.rs2.next() == true) {
//               cell++;

                    ItoM += "<tr><th style=\"width:40px;\">" + count1 + "</th>"
                            + "<th style=\"width:80px;\"><input type=\"text\"  style=\"width:80px;\" value=\"" + conn.rs2.getString("parity") + "\" name=\"parity" + count1 + "\" id=\"parity" + count1 + "\" /></th>"
                            + "<th style=\"width:100px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs2.getString("Gravida") + "\" name=\"gravidae" + count1 + "\" id=\"gravidae" + count1 + "\"/></th>";
//      System.out.println("cell _______________"+cell);
                }//end of while

                if (cell == 0) {
                    ItoM += "<tr><td style=\"width:40px;\">" + count1 + "</td>"
                            + "<td style=\"width:80px;\"><input type=\"text\"  style=\"width:80px;\" value=\"\" name=\"parity" + count1 + "\" id=\"parity" + count1 + "\" /></td>"
                            + "<td style=\"width:100px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"gravidae" + count1 + "\" id=\"gravidae" + count1 + "\"/></td>";
                }




//data from mother details

                //============================================================to get edd amd lmp========================== 


                if (conn.rs.next() == true) {
                    // cell1++;

                    ItoM += "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs.getString("lmp") + "\" name=\"LMP" + count1 + "\" id=\"LMP" + count1 + "\"></th>"
                            + "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs.getString("edd") + "\" name=\"EDD" + count1 + "\"  id=\"EDD" + count1 + "\"/></th>";
rowcomplete="no";

// count1++;
//System.out.println("cell1 _______________"+cell1);
                }

                if (cell1 == 0) {

                    ItoM += "<td style=\"width:80px;\"><input type=\"text\" placeholder=\"yyyy-mm-dd\" style=\"width:80px;\" value=\"\" name=\"LMP" + count1 + "\" id=\"LMP" + count1 + "\"></td>"
                            + "<td style=\"width:80px;\"><input type=\"text\" placeholder=\"yyyy-mm-dd\" style=\"width:80px;\" value=\"\" name=\"EDD" + count1 + "\"  id=\"EDD" + count1 + "\"/></td>";


                    
                    

                }



                if (conn.rs1.next() == true) {
                    // cell4++;
                    String splitsCounselled[];
                    String a = conn.rs1.getString("CounselledOn");
                    splitsCounselled = a.split("_");

                    ArrayList counselledOn = new ArrayList();
                    ArrayList counselledOnID = new ArrayList();
                    // String counselledOnID[];
                    for (int j = 0; j < splitsCounselled.length; j++) {
//    System.out.println("values----"+splitsCounselled[j]);

                        String counsels = "select * from counselling where counsellingID='" + splitsCounselled[j] + "'";
                        conn.rs6 = conn.state6.executeQuery(counsels);
//System.out.println("query"+counsels);
                        while (conn.rs6.next()) {
                            counselledOn.add(conn.rs6.getString("counselling"));
                            counselledOnID.add(conn.rs6.getString("counsellingID"));
//System.out.println(counselledOn);
//System.out.println(counselledOnID);
                        }
                    }
                    for (int i = 0; i < counselled.size(); i++) {
                        if (counselledOn != null && !counselledOn.equals("")) {
                            if (counselledOn.contains(counselled.get(i))) {
                                options += "<option selected value=\"" + counselledID.get(i) + "\">" + counselled.get(i) + "</option>";

                            } else {
                                options += "<option  value=\"" + counselledID.get(i) + "\">" + counselled.get(i) + "</option>";

                            }
                        }//if the value being fetched is not null
                        else {

                            options += "<option  value=\"" + counselledID.get(i) + "\">" + counselled.get(i) + "</option>";


                        }


                    }

                    ItoM += "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\"  value=\"" + conn.rs1.getString("Gestation") + "\" name=\"Gestation" + count1 + "\" name=\"Gestation" + count1 + "\"></th>"
                            + "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs1.getString("Weight") + "\"  name=\"Weight" + count1 + "\" id=\"Weight" + count1 + "\"></th>"
                            + "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs1.getString("BloodPressure") + "\"  name=\"BP" + count1 + "\" id=\"BP" + count1 + "\"></th>"
                            + "<th style=\"width:80px;\">"
                            + "<select style=\"width:150px; height: 50px;\"  multiple name=\"counselledOn" + count1 + "\" id=\"counselledOn" + count1 + "\"> "
                           
                            //                        + "<option selected value=\"" +conn.rs1.getString("CounselledOn") + "\"  >" + counselledOn + "</option>"
                            + "" + options + ""
                            + "</th>"
                            + "<input type=\"hidden\" value=\"" + conn.rs1.getString(1) + "\"  name=\"ItoMID" + count1 + "\">"
                            + "</tr>";



                    //System.out.println("cell4 _______________"+cell4);    
                } else {
//System.out.println("aaaaa");
                }
                
               
                
                if (cell4 == 0) {
                    ItoM += "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_Gestation" + count + "\" name=\"Gestation" + count + "\"></td>"
                            + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" placeholder=\"in kgs\" name=\"New_Weight" + count + "\" id=\"Weight" + count + "\"></td>"
                            + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_BP" + count + "\" id=\"BP" + count + "\"></td>"
                            + "<td style=\"width:150px;\"><select style=\"width:150px; height: 50px; \"  multiple name=\"New_counselledOn" + count + "\" id=\"New_counselledOn" + count + "\"> "
                            
                            + "" + options1 + ""
                            + "</td>"
                            + "</tr>";
                    
                    rowcomplete="yes";

                }
                count1++;
                options = "";
            }

            if (ItoM.equals("")) {
                // allinputfields="No mother found under that ANC No.";
            }
            //_____________________after everything else, then add the default input fields.
            //This table will be used to only save the newly input table values 
            //thus the naming of the input fields is different
if(rowcomplete.equals("no")){

          ItoM +=  "<tr><td style=\"width:40px;\">" + count1 + "</td>"
                        + "<td style=\"width:80px;\"><input type=\"text\"  style=\"width:80px;\" value=\"\" name=\"New_parity" + count + "\" id=\"New_parity" + count + "\" /></td>"
                        + "<td style=\"width:100px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_gravidae" + count + "\" id=\"New_gravidae"+ count + "\"/></td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" readOnly name=\"New_LMP" + count + "\" id=\"New_LMP" + count + "\"></td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" readOnly name=\"New_EDD" + count + "\"  id=\"New_EDD" + count + "\"/></td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_Gestation" + count + "\" name=\"New_Gestation" + count + "\"></td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_Weight" + count + "\" id=\"New_Weight" + count + "\"></td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_BP" + count + "\" id=\"New_BP" + count + "\"></td>"
                        + "<td style=\"width:150px;\"><select style=\"width:150px; height: 50px; \"  multiple name=\"New_counselledOn" + count + "\" id=\"New_counselledOn" + count + "\"> "
                        + "<option value=\"\" >Select an option</option>"
                        + ""+options1+""
                        + "</td>"
                        + "</tr>";
          

}
          
          


            //the hidden field which holds the number of hidden fields to update
            //no_of_old_rows
            ItoM += "<input type=\"hidden\" id=\"ItoM\"  name=\"ItoM\" value=\"" + count + "\"/> <input type=\"hidden\" name=\"ItoM_old_rows\"  id=\"ItoM_old_rows\" value=\"" + (count1 - 1) + "\"/>";


               System.out.println("Row complete is" + rowcomplete);



            try {
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("" + ItoM + "");
                out.println("</body>");
                out.println("</html>");

                ItoM = "";
            } finally {
                out.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItoMRegister.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ItoMRegister.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ItoMRegister.class.getName()).log(Level.SEVERE, null, ex);
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
