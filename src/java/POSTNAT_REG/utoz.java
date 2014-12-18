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
public class utoz extends HttpServlet {

    String motherid = "";
    String allinputs = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");


            String pnc_arr[] = {"","P", "N", "ND", "NA"};
            String yesno_arr[] = {"","Y", "N"};
            String pks_arr[] = {"","P", "N", "U"};

            allinputs = "";

            int oldrows = 0;
            int newrows = 1;
           int allrows=0;
            motherid = request.getParameter("motherid");

            dbConn conn = new dbConn();

            allinputs += "<tr><th>No.</th><th colspan=\"3\">HIV Status</th><th colspan=\"3\">Prophylaxis</th></tr>";
            allinputs += "<tr><th></th><th rowspan=\"2\">Prior Known Status</th><th colspan=\"2\">Tested in PNC</th><th rowspan=\"2\">NVP to Baby</th><th colspan=\"2\">CTX</th></tr>";
            allinputs += "<tr><th></th><th ><=72 hours</th><th> >72hrs</th><th> Baby</th><th>Mother</th></tr>";

            allinputs += "<tr><td></td><td>(u)</td><td>(v)</td><td>(w)</td><td>(x)</td><td>(y)</td><td>(z)</td></tr>";
            String myquery = "select * from postnat_utoz where motherid='" + motherid + "'";

            conn.rs = conn.st4.executeQuery(myquery);

            //======================get the already existing content=====================
            String sex_options = "<option value=\"\"></option><option value=\"M\">M</option><option value=\"F\">F</option>";
            String yes_no_options = "<option value=\"\"></option><option value=\"Y\">Y</option><option value=\"N\">N</option>";
            String pnc_options = "<option value=\"\"></option><option value=\"P\">P</option><option value=\"N\">N</option><option value=\"ND\">ND</option><option value=\"NA\">NA</option>";
            String pks_options = "<option value=\"\"></option><option value=\"P\">P</option><option value=\"N\">N</option><option value=\"U\">U</option>";







            while (conn.rs.next()) {

                oldrows++;
                allrows++;
                
                
                //===============================================pks options=========================        


                String pks_options_old = "<option value=\"\"></option>";
                for (int b = 0; b < pks_arr.length; b++) {

                    if (conn.rs.getString("priorknownstatus").equalsIgnoreCase(pks_arr[b])) {

                        pks_options_old += "<option selected value=\"" + pks_arr[b] + "\">" + pks_arr[b] + "</option>";

                    }
                    else {
                        
                        pks_options_old += "<option  value=\"" + pks_arr[b] + "\">" + pks_arr[b] + "</option>";
                        
                    }
                }
                //pnc_arr
                //=============== lesspnc options  ================================
                String lesspnc_options_old = "<option value=\"\"></option>";
               for (int b = 0; b < pnc_arr.length; b++) {

                    if (conn.rs.getString("lessequals72").equalsIgnoreCase(pnc_arr[b])) {

                        lesspnc_options_old += "<option selected value=\"" + pnc_arr[b] + "\">" + pnc_arr[b] + "</option>";

                    }
                    else {
                        
                        lesspnc_options_old += "<option  value=\"" + pnc_arr[b] + "\">" + pnc_arr[b] + "</option>";
                        
                    }
                }
               //=============== gretpnc options  ================================
                String gretpnc_options_old = "<option value=\"\"></option>";
               for (int b = 0; b < pnc_arr.length; b++) {

                    if (conn.rs.getString("greaterthan72").equalsIgnoreCase(pnc_arr[b])) {

                        gretpnc_options_old += "<option selected value=\"" + pnc_arr[b] + "\">" + pnc_arr[b] + "</option>";

                    }
                    else {
                        
                        gretpnc_options_old += "<option  value=\"" + pnc_arr[b] + "\">" + pnc_arr[b] + "</option>";
                        
                    }
                }
              
                //======================nvp=============================

                String nvp_options_old = "<option value=\"\"></option>";
                for (int a = 0; a < yesno_arr.length; a++) {

                    if (conn.rs.getString("nvptobaby").equalsIgnoreCase(yesno_arr[a])) {

                        nvp_options_old += "<option selected value=\"" + yesno_arr[a] + "\">" + yesno_arr[a] + "</option>";

                    } else {
                        nvp_options_old += "<option  value=\"" + yesno_arr[a] + "\">" + yesno_arr[a] + "</option>";
                    }
                }

                
                
                
                  //======================ctx baby=============================

                String ctxbaby_options_old = "<option value=\"\"></option>";
                for (int a = 0; a < yesno_arr.length; a++) {

                    if (conn.rs.getString("ctxbaby").equalsIgnoreCase(yesno_arr[a])) {

                        ctxbaby_options_old += "<option selected value=\"" + yesno_arr[a] + "\">" + yesno_arr[a] + "</option>";

                    } else {
                        ctxbaby_options_old += "<option  value=\"" + yesno_arr[a] + "\">" + yesno_arr[a] + "</option>";
                    }
                }

                
                
                  //======================ctx mother=============================

                String ctxmother_options_old = "<option value=\"\"></option>";
                for (int a = 0; a < yesno_arr.length; a++) {

                    if (conn.rs.getString("ctxmother").equalsIgnoreCase(yesno_arr[a])) {

                        ctxmother_options_old += "<option selected value=\"" + yesno_arr[a] + "\">" + yesno_arr[a] + "</option>";

                    } else {
                        ctxmother_options_old += "<option  value=\"" + yesno_arr[a] + "\">" + yesno_arr[a] + "</option>";
                    }
                                                           }
                
//===========================================================================
 allinputs +="<tr><th><input type=\"hidden\" name=\"uztableid"+oldrows+"\" value=\""+conn.rs.getString(1) +"\">"+allrows+"</th><th><select  name=\"old_pks"+oldrows+"\" id=\"old_pks"+oldrows+"\" >"+pks_options_old+"</select></th>         <th> <Select id=\"old_les72"+oldrows+"\" style=\"width:39px;\" name=\"old_les72"+oldrows+"\">" + lesspnc_options_old + " </Select></th>     <th> <Select id=\"old_gret72"+oldrows+"\" style=\"width:39px;\" name=\"old_gret72"+oldrows+"\">" + gretpnc_options_old + " </Select></th>        <th> <Select id=\"old_nvptobaby"+oldrows+"\" style=\"width:39px;\" name=\"old_nvptobaby"+oldrows+"\">" + nvp_options_old + " </Select></th> <th> <Select id=\"old_ctxbaby"+oldrows+"\" style=\"width:39px;\" name=\"old_ctxbaby"+oldrows+"\">" + ctxbaby_options_old + " </Select></th>   <th> <Select id=\"old_ctxmother"+oldrows+"\" style=\"width:39px;\" name=\"old_ctxmother"+oldrows+"\">" + ctxmother_options_old + " </Select></th></tr>";

            }


            //======================the new values=========================  

if(1==1){
    allrows++;
 allinputs +="<tr><td>"+allrows+"</td><td><select  name=\"new_pks"+newrows+"\" id=\"new_pks"+newrows+"\" >"+pks_options+"</select></td>         <td> <Select id=\"new_les72"+newrows+"\" style=\"width:39px;\" name=\"new_les72"+newrows+"\">" + pnc_options + " </Select></td>     <td> <Select id=\"new_gret72"+newrows+"\" style=\"width:39px;\" name=\"new_gret72"+newrows+"\">" + pnc_options + " </Select></td>        <td> <Select id=\"new_nvptobaby"+newrows+"\" style=\"width:39px;\" name=\"new_nvptobaby"+newrows+"\">" + yes_no_options + " </Select></td> <td> <Select id=\"new_ctxbaby"+newrows+"\" style=\"width:39px;\" name=\"new_ctxbaby"+newrows+"\">" + yes_no_options + " </Select></td>   <td> <Select id=\"new_ctxmother"+newrows+"\" style=\"width:39px;\" name=\"new_ctxmother"+newrows+"\">" + yes_no_options + " </Select></td></tr>";
 allinputs+="<input type=\"hidden\" value=\""+oldrows+"\" name=\"uz_oldrows\" id=\"uz_oldrows\">"
                    + "<input type=\"hidden\" name=\"uz_newrows\" id=\"uz_newrows\" value=\""+newrows+"\">"
                    + "<input type=\"hidden\" name=\"all_rows4_count\" id=\"all_rows4_count\" value=\""+allrows+"\">";



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
