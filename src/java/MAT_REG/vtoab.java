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
import org.quartz.utils.DBConnectionManager;
import sender.dbConn;

/**
 *
 * @author SIXTYFOURBIT
 */
public class vtoab extends HttpServlet {

    String ancno = "";
    String motherID = "";
    String allinputs = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");


            String anc_arr[] = {"P", "N", "KP", "U"};
            String mat_arr[] = {"P", "N", "U"};

            allinputs = "";

            int oldrows = 0;
            int newrows = 1;
           int allrows=0;
            ancno = request.getParameter("ancno");
          motherID= request.getParameter("motherID");
            dbConn conn = new dbConn();

            allinputs += "<tr><th>No.</th><th colspan=\"4\">baby</th><th rowspan=\"2\">VDRL/<br/>RPR Results</th><th colspan=\"2\">HIV Status</th></tr>";
            allinputs += "<tr><th></th><th>Sex</th><th>Birth weight</th><th>Live birth ,<br/> FSB, MSB</th><th>APGAR Score</th><th>ANC</th><th>Maternity</th></tr>";

            allinputs += "<tr><th></th><th>(v)</th><th>(w)</th><th>(x)</th><th>(y)</th><th>(z)</th><th>(aa)</th><th>(ab)</th></tr>";
            
            String myquery = "select * from mat_vtoab where motherid='" + motherID + "'";

            System.out.println(myquery);
            
            conn.rs = conn.st4.executeQuery(myquery);

            //======================get the already existing content=====================
            String sex_options = "<option value=\"\"></option><option value=\"M\">M</option><option value=\"F\">F</option>";
            String babystatus_options = "<option value=\"NA\"></option><option title=\"Live Birth\" value=\"Live Birth\">LB</option><option title=\"Fresh Still Birth\" value=\"Fresh Still Birth\">FSB</option><option title=\"Macerated Still Birth\" value=\"Macerated Still Birth\">MSB</option>";
            String vdrl_options = "<option value=\"\"></option><option value=\"P\">P</option><option value=\"N\">N</option>";
            String anc_hiv_options = "<option value=\"\"></option><option value=\"P\">P</option><option value=\"N\">N</option><option value=\"KP\">KP</option><option value=\"U\">U</option>";
            String mat_hiv_options = "<option value=\"\"></option><option value=\"P\">P</option><option value=\"N\">N</option><option value=\"U\">U</option>";







            while (conn.rs.next()) {

                oldrows++;
                allrows++;
                //=============== sex db options  ================================
                String sex_options_old = "<option value=\"\"></option>";
                if (conn.rs.getString("Sex").equalsIgnoreCase("M")) {

                    sex_options_old += "<option selected value=\"M\">M</option>";
                    sex_options_old += "<option  value=\"F\">F</option>";
                } else {

                    sex_options_old += "<option selected value=\"F\">F</option>";
                    sex_options_old += "<option  value=\"M\">M</option>";

                }
                
                
                
                 //=============== Baby Status db options  ================================
                String babystatus_options_old = "<option value=\"\"></option>";
                if (conn.rs.getString("live_Birth").equalsIgnoreCase("Live Birth")) {

                    babystatus_options_old += "<option selected value=\"Live Birth\">LB</option>";
                    babystatus_options_old += "<option  value=\"Fresh Still Birth\">FSB</option>";
                    babystatus_options_old += "<option  value=\"Macerated Still Birth\">MSB</option>";
                } else if (conn.rs.getString("live_Birth").equalsIgnoreCase("Fresh Still Birth")) {

                    babystatus_options_old += "<option  value=\"Live Birth\">LB</option>";
                    babystatus_options_old += "<option selected value=\"Fresh Still Birth\">FSB</option>";
                    babystatus_options_old += "<option  value=\"Macerated Still Birth\">MSB</option>";

                }
                else if (conn.rs.getString("live_Birth").equalsIgnoreCase("Macerated Still Birth")) {
                
                    babystatus_options_old += "<option title=\"Live Birth\"  value=\"Live Birth\">LB</option>";
                    babystatus_options_old += "<option title=\"Fresh Still Birth\"  value=\"Fresh Still Birth\">FSB</option>";
                    babystatus_options_old += "<option title=\"Macerated Still Birth\" selected value=\"Macerated Still Birth\">MSB</option>";
                    
                }
                else{
                 babystatus_options_old += "<option  value=\"\">NA</option>";
                 babystatus_options_old += "<option title=\"Live Birth\"  value=\"Live Birth\">LB</option>";
                    babystatus_options_old += "<option title=\"Fresh Still Birth\"  value=\"Fresh Still Birth\">FSB</option>";
                    babystatus_options_old += "<option title=\"Macerated Still Birth\"  value=\"Macerated Still Birth\">MSB</option>";
                    
                
                }
                
                
                
                //===========================vdrl options=======================vdrl
                String vdrl_options_old = "<option value=\"\"></option>";
                if (conn.rs.getString("vdrl_rpr_results").equalsIgnoreCase("P")) {

                    vdrl_options_old += "<option selected value=\"P\">P</option>";
                    vdrl_options_old += "<option  value=\"N\">N</option>";
                } else {

                    vdrl_options_old += "<option selected value=\"N\">N</option>";
                    vdrl_options_old += "<option  value=\"P\">P</option>";

                }
                //======================anc hiv options=============================

                String anc_hiv_options_old = "<option value=\"\"></option>";
                for (int a = 0; a < anc_arr.length; a++) {

                    if (conn.rs.getString("hiv_anc").equalsIgnoreCase(anc_arr[a])) {

                        anc_hiv_options_old += "<option selected value=\"" + anc_arr[a] + "\">" + anc_arr[a] + "</option>";

                    } else {
                        anc_hiv_options_old += "<option  value=\"" + anc_arr[a] + "\">" + anc_arr[a] + "</option>";
                    }
                }

//===============================================mat hiv options=========================        


                String mat_hiv_options_old = "<option value=\"\"></option>";
                for (int b = 0; b < mat_arr.length; b++) {

                    if (conn.rs.getString("hiv_maternity").equalsIgnoreCase(mat_arr[b])) {

                        mat_hiv_options_old += "<option selected value=\"" + mat_arr[b] + "\">" + mat_arr[b] + "</option>";

                    }
                    else {
                        
                        mat_hiv_options_old += "<option  value=\"" + mat_arr[b] + "\">" + mat_arr[b] + "</option>";
                        
                    }
                }
//===========================================================================
                allinputs +="<tr><th>"+allrows+"</th><th><input type=\"hidden\" name=\"vab_tableid"+oldrows+"\" id=\"vab_tableid"+oldrows+"\" value=\""+conn.rs.getString("tableid") +"\"> <Select id=\"vab_old_sex"+oldrows+"\" style=\"width:39px;\" name=\"vab_old_sex"+oldrows+"\">" + sex_options_old + " </Select></th><th><input type=\"text\" class=\"textbox\" value=\"" + conn.rs.getString("birth_weight") + "\" placeholder=\"in grams\" name=\"vab_old_weight"+oldrows+"\" style=\"padding:1px; width:80px;\"></th><th><select  name=\"vab_old_livebirth"+oldrows+"\" id=\"vab_old_livebirth"+oldrows+"\"  style=\"width:80px;\"/>"+babystatus_options_old+"</select></th><th><input type=\"text\" value=\"" + conn.rs.getString("apgar_score") + "\" name=\"old_apgarscore"+oldrows+"\" id=\"old_apgarscore"+oldrows+"\" class=\"textbox\" style=\"width:80px;\"></th><th> <Select id=\"vab_old_vdrl"+oldrows+"\" style=\"width:35px;\" name=\"vab_old_vdrl"+oldrows+"\">" + vdrl_options_old + " </Select></th><th><Select id=\"vab_old_anc"+oldrows+"\" style=\"width:39px;\" name=\"vab_old_anc"+oldrows+"\">" + anc_hiv_options_old + " </Select></th><th><Select id=\"vab_old_mat"+oldrows+"\" style=\"width:35px;\" name=\"vab_old_mat"+oldrows+"\">" + mat_hiv_options_old + " </Select></th></tr>";

            }


            //======================the new values=========================  

if(1==1){
    allrows++;
            allinputs += "<tr><td> "+allrows+"</td><td><Select id=\"vab_new_sex1\" style=\"width:35px;\" name=\"vab_new_sex1\">" + sex_options + " </Select></td><td><input type=\"text\" class=\"textbox\" placeholder=\"in grams\" name=\"vab_new_weight1\" style=\"padding:1px; width:80px;\"></td><td><Select  name=\"vab_new_livebirth1\" id=\"vab_new_livebirth1\"  style=\"width:80px;\"/>"+babystatus_options+"</select></td><td><input type=\"text\" name=\"new_apgarscore1\" id=\"new_apgarscore1\" class=\"textbox\" style=\"width:80px;\"></td><td> <Select id=\"vab_new_vdrl1\" style=\"width:35px;\" name=\"vab_new_vdrl1\">" + vdrl_options + " </Select></td><td><Select id=\"vab_new_anc1\" style=\"width:39px;\" name=\"vab_new_anc1\">" + anc_hiv_options + " </Select></td><td><Select id=\"vab_new_mat1\" style=\"width:35px;\" name=\"vab_new_mat1\">" + mat_hiv_options + " </Select></td></tr>";
            allinputs+="<input type=\"hidden\" value=\""+oldrows+"\" name=\"vab_oldrows\" id=\"vab_oldrows\">"
                    + "<input type=\"hidden\" name=\"vab_newrows\" id=\"vab_newrows\" value=\""+newrows+"\">"
                    + "<input type=\"hidden\" name=\"all_rows4_count\" id=\"all_rows4_count\" value=\""+allrows+"\">";



         }
            PrintWriter out = response.getWriter();
            try {

                out.println(allinputs);

            } finally {
                out.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(vtoab.class.getName()).log(Level.SEVERE, null, ex);
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
