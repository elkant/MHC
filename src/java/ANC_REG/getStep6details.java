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
public class getStep6details extends HttpServlet {

    String anc_no, allinputfields;
    String other_conitions;
    String yes_no_arr[] = {"Y", "N"};
    String ipt1_3[] = {"1", "2", "3"};
    String ttdose[] = {"1", "2", "3", "4", "5"};
    String deafaultyesno[]={"","Y", "N"};
    
  String oldotherconditions;
  String filledother;
    String otherconditions = "";
            String yes_no_de = "", yes_no_iron = "", yes_no_fo = "", yes_no_itn = "",yes_no_def="";
            String ipt = "";
            String ttdoze = "";
            
            String newttdoze,newipt;
    dbConn conn = new dbConn();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
 int allrows=1;
            response.setContentType("text/html;charset=UTF-8");


            anc_no = request.getParameter("ancno");
             String motherID = request.getParameter("motherID");
            int oldrows=0;
            int newrows=0;
   
            
            
            newttdoze="";
            newipt="";
            
            oldotherconditions="";
            filledother="";
            allinputfields = "";

            allinputfields += "<tr><th rowspan=\"2\">No</th><th>Other Conditions</th><th colspan=\"5\">Treatment <th></tr>";

            allinputfields += "<tr><th></th><th>Deworming</th><th>IPT 1-3</th><th>TT Dose</th><th>Iron</th><th>Folic Acid</th><th>Received ITN</th></tr>";
            allinputfields += "<tr><td></td><td>(ae)</td><td>(af)</td><td>(ag)</td><td>(ah)</td><td>(ai)</td><td>(aj)</td><td>(ak)</td></tr>";

             otherconditions = "";
             yes_no_de = "";
             yes_no_iron = "";
             yes_no_fo = "";
             yes_no_itn = "";
             ipt = "";
             ttdoze = "";


            //this will be fetched from the previos tables
            yes_no_de = "<option value=\"\"></option>";
            yes_no_iron = "<option value=\"\"></option>";
            yes_no_fo = "<option value=\"\"></option>";
            yes_no_itn = "<option value=\"\"></option>";

            
            yes_no_def="";
            
            otherconditions += "";

      
               for (int p = 0; p < deafaultyesno.length; p++) {
                
            
                    yes_no_def += "<option  value=\"" + deafaultyesno[p] + "\">" + deafaultyesno[p] + "</option>";
               
            }
            
            
                for (int p = 0; p < ttdose.length; p++) {
                
            
                    newttdoze += "<option  value=\"" + ttdose[p] + "\">" + ttdose[p] + "</option>";
               
            }
                
                for (int p = 0; p < ipt1_3.length; p++) {
                
            
                    newipt += "<option  value=\"" + ipt1_3[p] + "\">" + ipt1_3[p] + "</option>";
               
            }
            
            
            
            
            
            
            
            
            

            //get all the conditions from the conditions table

            //to be dealt with
                
             if(conn.st.isClosed()){conn=new dbConn();}    
            conn.rs = conn.st.executeQuery("select * from other_conditions");

            while (conn.rs.next()) {

                otherconditions += "<option value=\"" + conn.rs.getString(1) + "\">" + conn.rs.getString(1) + "=" + conn.rs.getString(2) + "</option>";

            }

         

 
            

      
            
            
            
                 //the values to be selected from the database



conn.rs_1=conn.st_1.executeQuery("Select * from aetoak where motherid='"+motherID+"'");

while(conn.rs_1.next()){
oldrows++;
oldotherconditions=""; 


String mytext=conn.rs_1.getString("ancRegisterID");



//pass the register id to the edistor of the first field
getOtherConditions(mytext);

 allinputfields += "<tr><th>"+allrows+"</th><th style=\"width:80px;\"><select style=\"height:58px;\" multiple id=\"old_other_conditions"+oldrows+"\"  name=\"old_other_conditions"+oldrows+"\" id=\"other_conditions"+oldrows+"\">" + oldotherconditions + "</select>"
                    + " <input type=\"checkbox\" class=\"shortinput\" title=\"\" value=\"yes\" id=\"old_otherstriger"+oldrows+"\" onclick=\"old_otherothercondz("+oldrows+");\">"
         + "6=Others<br/><input name=\"old_others"+oldrows+"\" value=\""+conn.rs_1.getString("others_section_6")+"\" required id=\"old_others"+oldrows+"\" size=\"24px\" type=\"hidden\"></th>"
                    + "<th ><select style=\"width:60px;\" id=\"old_deworming"+oldrows+"\" name=\"old_deworming"+oldrows+"\">" + yes_no_de + "</select></th>"
                    + "<th ><select style=\"width:60px;height:58px;\" id=\"old_IPT"+oldrows+"\" name=\"old_IPT"+oldrows+"\" style=\"height:58px;\" multiple>" + ipt + "</select></th>"
                    + "<th ><select style=\"width:60px;height:58px;\" id=\"old_tt_dose"+oldrows+"\" name=\"old_tt_dose"+oldrows+"\" style=\"height:58px;\" multiple>" + ttdoze + "</select></th>"
                    + "<th ><select style=\"width:60px;\" id=\"old_iron"+oldrows+"\" name=\"old_iron"+oldrows+"\">" + yes_no_iron + "</select></th>"
                    + "<th ><select style=\"width:60px;\" id=\"old_folic_acid"+oldrows+"\" name=\"old_folic_acid"+oldrows+"\">" + yes_no_fo + "</select></th>"
                    + "<th ><select style=\"width:60px;\" id=\"old_ITN"+oldrows+"\" name=\"old_ITN"+oldrows+"\">" + yes_no_itn + "</select>"
         + "<input type=\"hidden\" name=\"anc_regid"+oldrows+"\" id=\"anc_regid"+oldrows+"\" value=\""+conn.rs_1.getString("ancRegisterID")+"\"></th></tr>";

allrows++;





}
//=========================end of values from dbase 
            
            
            
            
            
            
            
            
            
            
            
     //=====new valuesthe obvious created row       
if(1==1){
    newrows++;
            allinputfields += "<tr><td>"+allrows+"</td><td style=\"width:80px;\"><select style=\"height:58px;\" multiple  name=\"other_conditions1\" id=\"other_conditions1\">" + otherconditions + "</select>"
                    + " <input type=\"checkbox\" class=\"shortinput\" title=\"click to add other conditions\" value=\"yes\" id=\"otherstriger1\" onclick=\"otherothercondz(1);\">6=Others<br/><input name=\"others1\" required id=\"others1\" size=\"24px\" type=\"hidden\"></td>"
                    + "<td ><select style=\"width:60px;\" id=\"deworming1\" name=\"deworming1\">" + yes_no_def + "</select></td>"
                    + "<td ><select style=\"width:60px;height:58px;\" id=\"IPT1\" name=\"IPT1\" style=\"height:58px;\" multiple>" + newipt + "</select></td>"
                    + "<td ><select style=\"width:60px;height:58px;\" id=\"tt_dose1\" name=\"tt_dose1\" style=\"height:58px;\" multiple>" + newttdoze + "</select></td>"
                    + "<td ><select style=\"width:60px;\" id=\"iron1\" name=\"iron1\">" + yes_no_def + "</select></td>"
                    + "<td ><select style=\"width:60px;\" id=\"folic_acid1\" name=\"folic_acid1\">" + yes_no_def + "</select></td>"
                    + "<td ><select style=\"width:60px;\" id=\"ITN1\" name=\"ITN1\">" + yes_no_def + "</select></td></tr>";

allrows++;
}



allinputfields+="<input type=\"hidden\" id=\"old_step6_no_rows\" name=\"old_step6_no_rows\" value=\""+oldrows+"\">"
           + "<input type=\"hidden\" id=\"new_step6_no_rows\" name=\"new_step6_no_rows\" value=\""+newrows+"\">"
+ "<input type=\"hidden\" id=\"all_rows6_count\" name=\"all_rows6_count\" value=\""+allrows+"\">";
//System.out.println("old_step6_no_rows==================="+oldrows);
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
            
            
            
            ipt="";
            ttdoze="";
             yes_no_de = "";
            yes_no_iron = "";
            yes_no_fo = "";
            yes_no_itn = "";
            
    conn.rs_2=conn.st_2.executeQuery("select ConditionID, others_section_6,deworming,ipt,ttdose,iron,folic,itn from aetoak where ancRegisterID='"+regid+"'");
         
            while(conn.rs_2.next()){
                
                //build the options for each row
                
                //another inner while loop
            
            conn.rs4 = conn.st4.executeQuery("select * from other_conditions");
            while (conn.rs4.next()) {
                
                //System.out.println("inner loop"+regid);
                if(conn.rs_2.getString("ConditionID")!=null&&!conn.rs_2.getString("ConditionID").equals("")){
                if(conn.rs_2.getString("ConditionID").toString().contains(conn.rs4.getString(1))){

                oldotherconditions += "<option selected value=\"" + conn.rs4.getString(1) + "\">" + conn.rs4.getString(1) + "=" + conn.rs4.getString(2) + "</option>";
                    
                    }
                
                 else{
                    
                oldotherconditions += "<option  value=\"" + conn.rs4.getString(1) + "\">" + conn.rs4.getString(1) + "=" + conn.rs4.getString(2) + "</option>";
                
                }
                
                }
                else{
                    
                oldotherconditions += "<option  value=\"" + conn.rs4.getString(1) + "\">" + conn.rs4.getString(1) + "=" + conn.rs4.getString(2) + "</option>";
                
                }
                
              
                
            }
            
            
            
            
              
        //===============================get deworming value===
         
            //yes no option deworming 
            for (int p = 0; p < yes_no_arr.length; p++) {
                //yes
                if (yes_no_arr[p].equals("Y") && conn.rs_2.getString("deworming").equalsIgnoreCase("Y")) {

                    yes_no_de += "<option selected value=\"" + yes_no_arr[p] + "\">" + yes_no_arr[p] + "</option>";
                } 
                
                else if (yes_no_arr[p].equals("N") && conn.rs_2.getString("deworming").equalsIgnoreCase("N")) {
                    yes_no_de += "<option selected value=\"" + yes_no_arr[p] + "\">" + yes_no_arr[p] + "</option>";

                } else {
                    yes_no_de += "<option  value=\"" + yes_no_arr[p] + "\">" + yes_no_arr[p] + "</option>";
                }
            }
   //=====================================================================================================
            
            
            
            
            ///========================================================IPT        ++++++++++++++++++++ 
  
          
            //yes no option deworming 
            for (int p = 0; p < ipt1_3.length; p++) {
                //yes
                
                
         
                
                if (conn.rs_2.getString("ipt").contains(ipt1_3[p])) {

                    ipt += "<option selected value=\"" + ipt1_3[p] + "\">" + ipt1_3[p] + "</option>";
                } 
                
                else {
                    ipt += "<option  value=\"" + ipt1_3[p] + "\">" + ipt1_3[p] + "</option>";
                }
            }
            
            
            
            
            //==================================ttdose=====================
            
            
              for (int p = 0; p < ttdose.length; p++) {
                //yes
                if (conn.rs_2.getString("ttdose").contains(ttdose[p])) {

                    ttdoze += "<option selected value=\"" + ttdose[p] + "\">" + ttdose[p] + "</option>";
                } 
                
                else {
                    
                    ttdoze += "<option  value=\"" + ttdose[p] + "\">" + ttdose[p] + "</option>";
                }
            }
            
            
              
              
              //===============================iron===
         
            //yes no option deworming 
            for (int p = 0; p < yes_no_arr.length; p++) {
                //yes
                if (yes_no_arr[p].equals("Y") && conn.rs_2.getString("iron").equalsIgnoreCase("Y")) {

                    yes_no_iron += "<option selected value=\"" + yes_no_arr[p] + "\">" + yes_no_arr[p] + "</option>";
                } 
                
                else if (yes_no_arr[p].equals("N") && conn.rs_2.getString("iron").equalsIgnoreCase("N")) {
                    yes_no_iron += "<option selected value=\"" + yes_no_arr[p] + "\">" + yes_no_arr[p] + "</option>";

                } else {
                    yes_no_iron += "<option  value=\"" + yes_no_arr[p] + "\">" + yes_no_arr[p] + "</option>";
                }
            }
   //=====================================================================================================
              
           
            
            
            
            
            
             //===============================folic===
         
            //yes no option folate 
            for (int p = 0; p < yes_no_arr.length; p++) {
                //yes
                if (yes_no_arr[p].equals("Y") && conn.rs_2.getString("folic").equalsIgnoreCase("Y"))
                {

                    yes_no_fo+= "<option selected value=\"" + yes_no_arr[p] + "\">" + yes_no_arr[p] + "</option>";
                } 
                
                else if (yes_no_arr[p].equals("N") && conn.rs_2.getString("folic").equalsIgnoreCase("N")) 
                {
                    yes_no_fo += "<option selected value=\"" + yes_no_arr[p] + "\">" + yes_no_arr[p] + "</option>";

                } 
                else {
                    yes_no_fo += "<option  value=\"" + yes_no_arr[p] + "\">" + yes_no_arr[p] + "</option>";
                }
            }
   //=====================================================================================================
            
           
            
            
            
             //===============================folic===
         
            //yes no option folate 
            for (int p = 0; p < yes_no_arr.length; p++) {
                //yes
                if (yes_no_arr[p].equals("Y") && conn.rs_2.getString("itn").equalsIgnoreCase("Y"))
                {

                    yes_no_itn+= "<option selected value=\"" + yes_no_arr[p] + "\">" + yes_no_arr[p] + "</option>";
                } 
                
                else if (yes_no_arr[p].equals("N") && conn.rs_2.getString("itn").equalsIgnoreCase("N")) 
                {
                    yes_no_itn += "<option selected value=\"" + yes_no_arr[p] + "\">" + yes_no_arr[p] + "</option>";

                } 
                else {
                    yes_no_itn += "<option  value=\"" + yes_no_arr[p] + "\">" + yes_no_arr[p] + "</option>";
                }
            }
   //=====================================================================================================
            
            
            
            }//____________________________end of while _______________________
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(getStep6details.class.getName()).log(Level.SEVERE, null, ex);
        }
     catch(NullPointerException np){
        System.out.println("NULL POINTER ERROR IN METHOD : "+np);
        }
    



}

}
