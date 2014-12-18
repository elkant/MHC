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
import sender.dbConn;

/**
 *
 * @author SIXTYFOURBIT
 */
public class actoai extends HttpServlet {

    String ancno = "";
    String allinputs = "";
    
    String motherID="";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    
    
       String hiv_res[] = {"P", "N", "KP", "U"};
       String anc_reg[] = {"sdNVP", "AZT", "T", "P"};
    
        allinputs = "";

        int oldrows = 0;
        int newrows = 1;
        int allrows = 0;

        ancno = request.getParameter("ancno");
        
        motherID = request.getParameter("motherID");
        
        

        //======================anc hiv options=============================

                String res_options = "<option value=\"\"></option>";
                
                for (int a = 0; a < hiv_res.length; a++) {

               res_options += "<option   value=\"" + hiv_res[a] + "\">" + hiv_res[a] + "</option>";
                    
                                                           }
        //======================anc regimen options=============================

                String anc_reg_options = "<option value=\"\"></option>";
                
                for (int a = 0; a < anc_reg.length; a++) {

               anc_reg_options += "<option   value=\"" + anc_reg[a] + "\">" + anc_reg[a] + "</option>";
                    
                                                           }
        
        
        
        
        
        
        
        
        
        dbConn conn = new dbConn();
       //get the static drug codes from db
        
        
       
 String codes="<option value=\"\"></option>";       
 String codesstring="";
 
        
conn.rs4=conn.st4.executeQuery("select * from acai_drugcodes");
while(conn.rs4.next()){

    codesstring+=conn.rs4.getString(2)+",";
    
    codes+="<option title=\""+conn.rs4.getString(3)+"\" value=\""+conn.rs4.getString(2)+"\">"+conn.rs4.getString(2) +"</option>";


                    }
        
        
       allinputs+="<tr><th>No</th><th colspan=\"3\">ARV Prophylaxis</th><th rowspan=\"3\">CTX to<br/>Mother</th><th rowspan=\"3\">Vitamin A</th><th colspan=\"2\">Partner HIV C&T</th></tr>";
       allinputs+="<tr><th></th><th colspan=\"2\">Regimen to the Mother</th><th rowspan=\"2\">To baby</th><th rowspan=\"2\">tested</th><th rowspan=\"2\">Hiv Test results</th></tr>";
       allinputs+="<tr><th></th><th>ANC</th><th>Maternity</th></tr>";
       allinputs+="<tr><th></th><th>(ac)</th><th>(ad)</th><th>(ae)</th><th>(af)</th><th>(ag)</th><th>(ah)</th><th>(ai)</th></tr>";
   
       String yes_no_options = "<option value=\"\"></option><option value=\"Y\">Y</option><option value=\"N\">N</option>";
       
    //======================================values from the database============================================
    
       
    String fromdb="select * from mat_actoai where motherid='"+motherID+"'";   
       
   
    conn.rs=conn.st.executeQuery(fromdb);
    
   
    
    while(conn.rs.next()){
       

        allrows++; 
        
     String codesarray[]=codesstring.split(",");   
     
     //======================anc hiv options=============================

                String anc_arv_options_old = "<option value=\"\"></option>";
                for (int a = 0; a < anc_reg.length; a++) {

                    if (conn.rs.getString("arv_anc").equalsIgnoreCase(anc_reg[a])) {

                        anc_arv_options_old += "<option selected value=\"" + anc_reg[a] + "\">" + anc_reg[a] + "</option>";

                    } else {
                        anc_arv_options_old += "<option  value=\"" + anc_reg[a] + "\">" + anc_reg[a] + "</option>";
                           }
                    
                }
        
    
                //======================mat hiv options=============================

                String anc_mat_options_old = "<option value=\"\"></option>";
                for (int a = 0; a < codesarray.length; a++) {

                    if (conn.rs.getString("arv_maternity").equalsIgnoreCase(codesarray[a])) {

                        anc_mat_options_old += "<option selected value=\"" + codesarray[a] + "\">" + codesarray[a] + "</option>";

                    } else {
                        anc_mat_options_old += "<option  value=\"" + codesarray[a] + "\">" + codesarray[a] + "</option>";
                           }
                    
                }
        
                
                
                //======================baby hiv options=============================

                String baby_arv_options_old = "<option value=\"\"></option>";
              
        if (conn.rs.getString("arv_tobaby").equalsIgnoreCase("Y")) {

                    baby_arv_options_old += "<option selected value=\"Y\">Y</option>";
                    baby_arv_options_old += "<option  value=\"N\">N</option>";
                } else {

                    baby_arv_options_old += "<option selected value=\"N\">N</option>";
                    baby_arv_options_old += "<option  value=\"Y\">Y</option>";

                }
                
                
                   
                //=============== ctx to mother db options  ================================
                String ctx_options_old = "<option value=\"\"></option>";
                if (conn.rs.getString("ctx_tomother").equalsIgnoreCase("Y")) {

                    ctx_options_old += "<option selected value=\"Y\">Y</option>";
                    ctx_options_old += "<option  value=\"N\">N</option>";
                } else {

                    ctx_options_old += "<option selected value=\"N\">N</option>";
                    ctx_options_old += "<option  value=\"Y\">Y</option>";

                }
                
                
                //=============== vitamin a to mother db options  ================================
                String vit_options_old = "<option value=\"\"></option>";
                if (conn.rs.getString("vitamin_a").equalsIgnoreCase("Y")) {

                    vit_options_old += "<option selected value=\"Y\">Y</option>";
                    vit_options_old += "<option  value=\"N\">N</option>";
                } else {

                    vit_options_old += "<option selected value=\"N\">N</option>";
                    vit_options_old += "<option  value=\"Y\">Y</option>";

                }
                
                
                
                //=============== vitamin a to mother db options  ================================
                String tested_options_old = "<option value=\"\"></option>";
                if (conn.rs.getString("partner_tested").equalsIgnoreCase("Y")) {

                    tested_options_old += "<option selected value=\"Y\">Y</option>";
                    tested_options_old += "<option  value=\"N\">N</option>";
                } else {

                    tested_options_old += "<option selected value=\"N\">N</option>";
                    tested_options_old += "<option  value=\"Y\">Y</option>";

                }
                
                 //======================anc hiv options=============================

                String hiv_res_options_old = "<option value=\"\"></option>";
                for (int a = 0; a < hiv_res.length; a++) {

                    if (conn.rs.getString("partner_results").equalsIgnoreCase(hiv_res[a])) {

                        hiv_res_options_old += "<option selected value=\"" + hiv_res[a] + "\">" + hiv_res[a] + "</option>";

                    } else {
                        
                        hiv_res_options_old += "<option  value=\"" + hiv_res[a] + "\">" + hiv_res[a] + "</option>";
                    }
                }
                
        oldrows++;         
                
     allinputs += "<tr><th>"+allrows+"</th><td><input type=\"hidden\" name=\"acai_tableid"+oldrows+"\"  value=\""+conn.rs.getString("tableid") +"\"> <select name=\"acai_old_anc"+oldrows+"\" id=\"acai_old_anc"+oldrows+"\" style=\"padding:1px; width:65px;\">"+anc_arv_options_old+"</select></td><td><select name=\"acai_old_mat"+oldrows+"\" id=\"acai_old_mat"+oldrows+"\"  style=\"width:55px;\">"+anc_mat_options_old+"</select></td><td><select name=\"old_tobaby"+oldrows+"\" id=\"old_tobaby"+oldrows+"\" style=\"width:45px;\">"+baby_arv_options_old+"</select></td><td> <Select id=\"acai_old_ctx"+oldrows+"\" style=\"width:35px;\" name=\"acai_old_ctx"+oldrows+"\">" + ctx_options_old + " </Select></td><td><Select id=\"acai_old_vitamina"+oldrows+"\" style=\"width:39px;\" name=\"acai_old_vitamina"+oldrows+"\">" + vit_options_old + " </Select></td><td><Select id=\"acai_old_tested"+oldrows+"\" style=\"width:39px;\" name=\"acai_old_tetsed"+oldrows+"\">" + tested_options_old + " </Select></td><td><Select id=\"acai_old_results"+oldrows+"\" style=\"width:39px;\" name=\"acai_old_results"+oldrows+"\">" + hiv_res_options_old + " </Select></td></tr>";
    
    
   
    }
    
       
   //=========================================New value========================================================
       
    if(1==1){
       allrows++; 
   allinputs += "<tr><td>"+allrows+"</td><td> <select name=\"acai_new_anc1\" id=\"acai_new_anc1\" style=\"padding:1px; width:65px;\">"+anc_reg_options+"</select></td><td><select name=\"acai_new_mat1\" id=\"acai_new_mat1\"  style=\"width:55px;\">"+codes+"</select></td><td><select name=\"new_tobaby1\" id=\"new_tobaby1\" style=\"width:45px;\">"+yes_no_options+"</select></td><td> <Select id=\"acai_ctx1\" style=\"width:45px;\" name=\"acai_ctx1\">" + yes_no_options + " </Select></td><td><Select id=\"acai_vitamina1\" style=\"width:39px;\" name=\"acai_vitamina1\">" + yes_no_options + " </Select></td><td><Select id=\"acai_tested1\" style=\"width:39px;\" name=\"acai_tetsed1\">" + yes_no_options + " </Select></td><td><Select id=\"acai_results1\" style=\"width:39px;\" name=\"acai_results1\">" + res_options + " </Select></td></tr>";
   
    }
   allinputs+="<input type=\"hidden\" value=\""+oldrows+"\" name=\"acai_oldrows\" id=\"acai_oldrows\">"
           + "<input type=\"hidden\" name=\"acai_newrows\" id=\"acai_newrows\" value=\""+newrows+"\">"
           + "<input type=\"hidden\" name=\"all_rows5_count\" id=\"all_rows5_count\" value=\""+allrows+"\">";
  
       
       
       
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
        out.println(allinputs);
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(actoai.class.getName()).log(Level.SEVERE, null, ex);
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
