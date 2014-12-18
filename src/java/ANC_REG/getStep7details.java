/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ANC_REG;

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
import sender.dbConn;

/**
 *
 * @author SIXTYFOURBIT
 */
public class getStep7details extends HttpServlet {

    String anc_no, allinputfields;
    String other_conitions,yes_no_counselled="",defyesno="";
    String yes_no_arr[] = {"Y", "N"};
    String test_results[] = {"P", "N", "U","KP"};
//    String ttdose[] = {"1", "2", "3", "4", "5"};
    String defyes_no_arr[] = {"","Y", "N"};
    
  String oldotherconditions,old_partner_results;
  String filledother;
    dbConn conn = new dbConn();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
           int allrows=0;
            response.setContentType("text/html;charset=UTF-8");

          yes_no_counselled="";
            anc_no = request.getParameter("ancno");
            String motherID = request.getParameter("motherID");
            
            defyesno="";
          
            
            
            
            
            
            int oldrows=0;
            int newrows=0;
   
            oldotherconditions="";
            old_partner_results="";
            filledother="";
            allinputfields = "";

            allinputfields += "<tr><th rowspan=\"2\">No</th><th>Additional Treatment</th><th colspan=\"2\">Partner HIV C&T </th><th>Reffered for</th><th>Remarks</th></tr>";

            allinputfields += "<tr><th></th><th>Counselled<br/> as a couple</th><th>Test Results <br/>of Partner</th><th></th><th></th></th></tr>";
            allinputfields += "<tr><td></td><td>(al)</td><td>(am)</td><td>(an)</td><td>(ao)</td><td>(ap)</td></tr>";

            String otherconditions = "";
            String results_partner = "";
           

            
            
            
             for (int p = 0; p < defyes_no_arr.length; p++) {
              
                    defyesno += "<option  value=\"" + defyes_no_arr[p] + "\">" + defyes_no_arr[p] + "</option>";
               
            }

            
            
            
            
            

            //this will be fetched from the previos tables
            yes_no_counselled = "<option value=\"\"></option>";
            results_partner = "<option value=\"\"></option>";
           
            otherconditions += "";

            //get all the conditions from the conditions table

            //to be dealt with
             if(conn.st.isClosed()){conn=new dbConn();}
             
            conn.rs = conn.st.executeQuery("select * from other_conditions");

            while (conn.rs.next()) {

                otherconditions += "<option value=\"" + conn.rs.getString(1) + "\">" + conn.rs.getString(1) + "=" + conn.rs.getString(2) + "</option>";

            }

            //+++++++++++++++++++++++++++++++++++++Counselled done ++++++++++  
          
            //yes no option 
          

 ///========================================================TEST RESULTS OF PARTNER++++++++++++++++++++ 
            
    //add the values in my iptarray to an arrylist for easy management i.e adding and removing the already displayed options
    //since the ipt and ttdoses are in one table, use one while to create my options        
           
            //test results arraylist
           ArrayList testresultal=new ArrayList(); 
         for(int p = 0; p < test_results.length; p++) {

               testresultal.add(test_results[p]);

            }   
           
 
  

            for (int p = 0; p < testresultal.size(); p++) {

                results_partner += "<option value=\"" + testresultal.get(p) + "\">" +  testresultal.get(p)+ "</option>";

            }
      
            
            
            
                 //the values to be selected from the database



conn.rs_1=conn.st_1.executeQuery("Select * from altoan where motherid='"+motherID+"'");

while(conn.rs_1.next()){
oldrows++;
oldotherconditions=""; 
old_partner_results="<option value=\"\"></option>";

String mytext=conn.rs_1.getString("ancRegisterID");



//pass the register id to the edistor of the first field
getOtherConditions(mytext);

 allinputfields += "<tr><th>"+allrows+"</th><th style=\"width:80px;\"><select style=\"height:58px;\" multiple  name=\"old_adt_treatment"+oldrows+"\" id=\"old_adt_treatment"+oldrows+"\">" + oldotherconditions + "</select>"
                    + " <input type=\"checkbox\" class=\"shortinput\" title=\"\" value=\"yes\" id=\"old_otherstriger7"+oldrows+"\" onclick=\"old_otherothercondz7("+oldrows+");\">"
         + "6=Others<br/><input name=\"old_others7"+oldrows+"\" value=\""+conn.rs_1.getString("others_section_7")+"\" required id=\"old_others7"+oldrows+"\" size=\"24px\" type=\"hidden\"></th>"
                    + "<th><select name=\"old_coupleconselled"+oldrows+"\" id=\"old_coupleconselled"+oldrows+"\">" + yes_no_counselled + "</select></th>"
                    + "<th><select name=\"old_test_results"+oldrows+"\" id=\"old_test_results"+oldrows+"\" >" + old_partner_results + "</select></th>"
                    + "<th><input type=\"text\" name=\"old_reffered"+oldrows+"\" id=\"old_reffered"+oldrows+"\" value=\""+conn.rs_1.getString("reffered") +"\"/></th>"
                    + "<th><textarea  name=\"old_remarks"+oldrows+"\" id=\"old_remarks"+oldrows+"\" cols=\"15\" rows=\"3\">"+conn.rs_1.getString("remarks")+"</textarea></th>"
                    
         + "<input type=\"hidden\" name=\"anc_regid7"+oldrows+"\" id=\"anc_regid7"+oldrows+"\" value=\""+conn.rs_1.getString("ancRegisterID")+"\"></td></tr>";

allrows++;





}
//=========================end of values from dbase 
            
            
            
            
            
            
            
            
            
            
            
     //=====new valuesthe obvious created row       
if(1==1){
    newrows++;
          allinputfields += "<tr><td>"+allrows+"</td><td style=\"width:80px;\"><select style=\"height:58px;\" multiple  name=\"adt_treatment"+newrows+"\" id=\"adt_treatment"+newrows+"\">" + otherconditions + "</select>"
                    + " <input type=\"checkbox\" class=\"shortinput\" title=\"\" value=\"no\" id=\"otherstriger7"+newrows+"\" onclick=\"otherothercondz7("+newrows+");\">"
         + "6=Others<br/><input name=\"others7"+newrows+"\"  required id=\"others7"+newrows+"\" size=\"24px\" type=\"hidden\"></td>"
                    + "<td><select id=\"coupleconselled"+newrows+"\" name=\"coupleconselled"+newrows+"\">" + defyesno + "</select></td>"
                    + "<td><select id=\"test_results"+newrows+"\" name=\"test_results"+newrows+"\"  >" + results_partner + "</select></td>"
                    + "<td><input id=\"reffered"+newrows+"\" type=\"text\" name=\"reffered"+newrows+"\" /></td>"
                    + "<td><textarea name=\"remarks"+newrows+"\"  id=\"remarks"+newrows+"\"cols=\"15\" rows=\"3\" /></textarea></td>"
                    
         + "</tr>";

allrows++;
}



allinputfields+="<input type=\"hidden\" id=\"old_step7_no_rows\" name=\"old_step7_no_rows\" value=\""+oldrows+"\">"
           + "<input type=\"hidden\" id=\"new_step7_no_rows\" name=\"new_step7_no_rows\" value=\""+newrows+"\">"
           + "<input type=\"hidden\" id=\"all_rows7_count\" name=\"all_rows7_count\" value=\""+allrows+"\">";



            PrintWriter out = response.getWriter();
            try {

                out.println(allinputfields);

            } finally {
                out.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(getStep6details.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        catch(NullPointerException np){
        System.out.println("NULL POINTER ERROR  :  "+np);
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


public void getOtherConditions(String regid){
        try {
            yes_no_counselled="";
    // old_partner_results="";       
    conn.rs_2=conn.st_2.executeQuery("select AdditionalTreatment, others_section_7,PartnerResults,couselled from altoan where ancRegisterID='"+regid+"'");
         
            while(conn.rs_2.next()){
                
                //build the options for each row
                
                //another inner while loop
            
            conn.rs4 = conn.st4.executeQuery("select * from other_conditions");
            while (conn.rs4.next()) {
                
                //System.out.println("inner loop"+regid);
                if(conn.rs_2.getString("AdditionalTreatment")!=null&&!conn.rs_2.getString("AdditionalTreatment").equals("")){
                if(conn.rs_2.getString("AdditionalTreatment").toString().contains(conn.rs4.getString(1))){

                oldotherconditions += "<option selected value=\"" + conn.rs4.getString(1) + "\">" + conn.rs4.getString(1) + "=" + conn.rs4.getString(2) + "</option>";
                    
                    }
                
                 else{
                    
                oldotherconditions += "<option  value=\"" + conn.rs4.getString(1) + "\">" + conn.rs4.getString(1) + "=" + conn.rs4.getString(2) + "</option>";
                
                }
                
                }
                else{
                    
                oldotherconditions += "<option  value=\"" + conn.rs4.getString(1) + "\">" + conn.rs4.getString(1) + "=" + conn.rs4.getString(2) + "</option>";
                
                }
            }//end of inner while
            
           //check the partner results
            
                for(int p = 0; p < test_results.length; p++) {
 if(conn.rs_2.getString("PartnerResults")!=null&&!conn.rs_2.getString("PartnerResults").equals("")){
               if(conn.rs_2.getString("PartnerResults").equals(test_results[p])){
               old_partner_results+="<option selected value=\""+test_results[p]+"\">"+test_results[p]+"</option>";
               
               }
                
               else{
               old_partner_results+="<option  value=\""+test_results[p]+"\">"+test_results[p]+"</option>";
               
               }
}//if the value being fetched is not null
 else{
 
 old_partner_results+="<option  value=\""+test_results[p]+"\">"+test_results[p]+"</option>";
 
 
 }
 
 
            }  
            
           for (int p = 0; p < yes_no_arr.length; p++) {
                //yes
                if (conn.rs_2.getString("couselled").contains(yes_no_arr[p])) {

                    yes_no_counselled+= "<option selected value=\"" + yes_no_arr[p] + "\">" + yes_no_arr[p] + "</option>";
                } //no
                else if (conn.rs_2.getString("couselled").contains(yes_no_arr[p])) {
                    yes_no_counselled += "<option selected value=\"" + yes_no_arr[p] + "\">" + yes_no_arr[p] + "</option>";

                } else {
                    yes_no_counselled += "<option  value=\"" + yes_no_arr[p] + "\">" + yes_no_arr[p] + "</option>";
                }
            }

                
                
                
            }//end of while.....
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(getStep6details.class.getName()).log(Level.SEVERE, null, ex);
        }
     catch(NullPointerException np){
        System.out.println("NULL POINTER ERROR IN METHOD--getstep 7 details : "+np);
        }
    



}

}
