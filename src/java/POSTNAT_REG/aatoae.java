/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package POSTNAT_REG;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.quartz.utils.DBConnectionManager;
import sender.dbConn;

/**
 *
 * @author SIXTYFOURBIT
 */
public class aatoae extends HttpServlet {

    String motherid = "";
    String allinputs = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");


            String pnc_arr[] = {"","P", "N", "ND", "NA"};
            String yesno_arr[] = {"","Y", "N"};
            String fp_arr[] = {"","C", "ECP","OC","INJ","IMP","IUD","LAM","D","FA","TL","V"};
            
            String method_arr2[] = {"","VIA", "VILI","PAP","NOT DONE"};
            
            String results_arr2[] = {"","Normal", "Suspect","Confirmed"};
            
            String yesno_arr2[] = {"","Y", "N","NA"};
            
            String res_arr[] = {"ND","P", "N", "U","KP"};

            allinputs = "";

            int oldrows = 0;
            int newrows = 1;
           int allrows=0;
            motherid = request.getParameter("motherid");

            dbConn conn = new dbConn();

            allinputs += "<tr><th>No.</th><th colspan=\"3\">Partner HIV C&T</th> <th colspan=\"1\">Screened for<br/> Cervical <br/>Cancer</th><th rowspan=\"3\">Provided with<br/>Modern FP <br/>Method</th></tr>";
            allinputs += "<tr><th rowspan=\"2\"></th><th rowspan=\"2\">Couple<br/> Counselled</th><th rowspan=\"2\">Partner Tested in<br/>PNC</th><th rowspan=\"2\">Results</th><th>Method</th></tr>";
            allinputs += "<tr><th>Result</th></tr>";

            allinputs += "<tr><td></td><td>(aa)</td><td>(ab)</td><td>(ac)</td><td>(ad)</td><td>(ae)</td></tr>";
            
            
            String myquery = "select * from postnat_aatoae where motherid='" + motherid + "'";

            conn.rs = conn.st4.executeQuery(myquery);

            //======================get the already existing content=====================
           String yes_no_options = "<option value=\"\"></option><option value=\"Y\">Y</option><option value=\"N\">N</option>";
            String pncyesno_options = "<option value=\"\"></option><option value=\"Y\">Y</option><option value=\"N\">N</option><option value=\"NA\">NA</option>";
            String hivres_options = "<option value=\"\"></option><option value=\"P\">P</option><option value=\"N\">N</option><option value=\"U\">U</option><option value=\"KP\">KP</option>";
            String meth_options = "<option value=\"\"></option><option value=\"VIA\">VIA</option><option value=\"VILI\">VILI</option><option value=\"PAP\">PAP</option><option value=\"NOT DONE\">NOT DONE</option>";
            String res_options = "<option value=\"\"></option><option value=\"Normal\">Normal</option><option value=\"Suspect\">Suspect</option><option value=\"Confirmed\">Confirmed</option>";
            String fp_options = "<option value=\"\"></option><option value=\"C\">C</option><option value=\"ECP\">ECP</option><option value=\"OC\">OC</option><option value=\"INJ\">INJ</option><option value=\"IMP\">IMP</option><option value=\"IUD\">IUD</option><option value=\"LAM\">LAM</option><option value=\"D\">D</option><option value=\"FA\">FA</option><option value=\"TL\">TL</option><option value=\"V\">V</option>";







            while (conn.rs.next()) {

                oldrows++;
                allrows++;
                
                
                //===============================================counselled options=========================        


                String counselled_options_old = "<option value=\"\"></option>";
                for (int b = 0; b < yesno_arr.length; b++) {

                    if (conn.rs.getString("counselled").equalsIgnoreCase(yesno_arr[b])) {

                        counselled_options_old += "<option selected value=\"" + yesno_arr[b] + "\">" + yesno_arr[b] + "</option>";

                    }
                    else {
                        
                        counselled_options_old += "<option  value=\"" + yesno_arr[b] + "\">" + yesno_arr[b] + "</option>";
                        
                    }
                }
                //pnc_arr
                //=============== pnc options  ================================
                String pnc_options_old = "<option value=\"\"></option>";
               for (int b = 0; b < yesno_arr2.length; b++) {

                    if (conn.rs.getString("hivtested").equalsIgnoreCase(yesno_arr2[b])) {

                        pnc_options_old += "<option selected value=\"" + yesno_arr2[b] + "\">" + yesno_arr2[b] + "</option>";

                    }
                    else {
                        
                        pnc_options_old += "<option  value=\"" + yesno_arr2[b] + "\">" + yesno_arr2[b] + "</option>";
                        
                    }
                }
               //=============== gretpnc options  ================================
                String hivres_options_old = "<option value=\"\"></option>";
               for (int b = 0; b < res_arr.length; b++) {

                    if (conn.rs.getString("hivresults").equalsIgnoreCase(res_arr[b])) {

                        hivres_options_old += "<option selected value=\"" + res_arr[b] + "\">" + res_arr[b] + "</option>";

                    }
                    else {
                        
                        hivres_options_old += "<option  value=\"" + res_arr[b] + "\">" + res_arr[b] + "</option>";
                        
                    }
                }
              
                //======================cervical cancer=============================

                String meth_options_old = "<option value=\"\"></option>";
                for (int a = 0; a < method_arr2.length; a++) {

                    if (conn.rs.getString("screenedmethod").equalsIgnoreCase(method_arr2[a])) {

                        meth_options_old += "<option selected value=\"" + method_arr2[a] + "\">" + method_arr2[a] + "</option>";

                    } else {
                        meth_options_old += "<option  value=\"" + method_arr2[a] + "\">" + method_arr2[a] + "</option>";
                    }
                }

                
                
                
                  //======================cancer results=============================

                String cancerres_options_old = "<option value=\"\"></option>";
                for (int a = 0; a < results_arr2.length; a++) {

                    if (conn.rs.getString("screenedresult").equalsIgnoreCase(results_arr2[a])) {

                        cancerres_options_old += "<option selected value=\"" + results_arr2[a] + "\">" + results_arr2[a] + "</option>";

                    } 
                    else {
                        cancerres_options_old += "<option  value=\"" + results_arr2[a] + "\">" + results_arr2[a] + "</option>";
                         }
                }

                
                
                  //======================FP=============================

                String fp_options_old = "<option value=\"\"></option>";
                for (int a = 0; a < fp_arr.length; a++) {

                    if (conn.rs.getString("modernfp").equalsIgnoreCase(fp_arr[a])) {

                        fp_options_old += "<option selected value=\"" + fp_arr[a] + "\">" + fp_arr[a] + "</option>";

                    } else {
                        fp_options_old += "<option  value=\"" + fp_arr[a] + "\">" + fp_arr[a] + "</option>";
                    }
                                                           }
                
//===========================================================================
 allinputs +="<tr><th rowspan=\"2\"><input type=\"hidden\" name=\"aaaetableid"+oldrows+"\" value=\""+conn.rs.getString(1) +"\">"+allrows+"</th><th rowspan=\"2\"><select  name=\"old_counselled"+oldrows+"\" id=\"old_counselled"+oldrows+"\" >"+counselled_options_old+"</select></th>         <th rowspan=\"2\"> <Select id=\"old_pnc"+oldrows+"\" style=\"width:39px;\" name=\"old_pnc"+oldrows+"\">" + pnc_options_old + " </Select></th>     <th rowspan=\"2\"> <Select id=\"old_hivres"+oldrows+"\" style=\"width:39px;\" name=\"old_hivres"+oldrows+"\">" + hivres_options_old + " </Select></th>        <th> <Select id=\"old_meth"+oldrows+"\" style=\"width:39px;\" name=\"old_meth"+oldrows+"\">" + meth_options_old + " </Select></th> <th rowspan=\"2\"> <Select id=\"old_fp"+oldrows+"\" style=\"width:39px;\" name=\"old_fp"+oldrows+"\">" + fp_options_old + " </Select></th>  </tr>";
allinputs+="<th rowspan=\"1\"><select  name=\"old_res"+oldrows+"\" id=\"old_res"+oldrows+"\" >"+cancerres_options_old+"</select></th>";
            }


            //======================the new values=========================  

if(1==1){
    allrows++;
allinputs +="<tr><td rowspan=\"2\">"+allrows+"</td><td rowspan=\"2\"><select  name=\"new_counselled"+newrows+"\" id=\"new_counselled"+newrows+"\" >"+yes_no_options+"</select></td>         <td rowspan=\"2\"> <Select id=\"new_pnc"+newrows+"\" style=\"width:39px;\" name=\"new_pnc"+newrows+"\">" + pncyesno_options + " </Select></td>     <td rowspan=\"2\"> <Select id=\"new_hivres"+newrows+"\" style=\"width:39px;\" name=\"new_hivres"+newrows+"\">" + hivres_options + " </Select></td>        <td> <Select id=\"new_meth"+newrows+"\" style=\"width:39px;\" name=\"new_meth"+newrows+"\">" + meth_options + " </Select></td> <td rowspan=\"2\"> <Select id=\"old_fp"+newrows+"\" style=\"width:39px;\" name=\"new_fp"+newrows+"\">" + fp_options + " </Select></td>  </tr>";
allinputs+="<td rowspan=\"1\"><select  name=\"new_res"+newrows+"\" id=\"new_res"+newrows+"\" >"+res_options+"</select></td>";
 
  allinputs+="<input type=\"hidden\" value=\""+oldrows+"\" name=\"aaae_oldrows\" id=\"aaae_oldrows\">"
                    + "<input type=\"hidden\" name=\"aaae_newrows\" id=\"aaae_newrows\" value=\""+newrows+"\">"
                    + "<input type=\"hidden\" name=\"all_rows5_count\" id=\"all_rows5_count\" value=\""+allrows+"\">";



         }
            PrintWriter out = response.getWriter();
            try {

                out.println(allinputs);

            } finally {
                out.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(utoz.class.getName()).log(Level.SEVERE, null, ex);
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
