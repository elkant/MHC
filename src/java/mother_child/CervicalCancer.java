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
 * @author Maureen
 */
public class CervicalCancer extends HttpServlet {

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
    int count1 = 1;
    int count = 1;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         String anc_no, cervicalScreen;
         cervicalScreen = "";

String query="";
int counter =0;
 String test="";
              
  count1 = 1;
             count = 1;
            String options1;
            String options2;
            String options;
        anc_no = request.getParameter("ancno");
String motherID = request.getParameter("motherID");
        dbConnect conn = new dbConnect();

        query = "Select * from cervical_screening where motherid='" + motherID + "'";

System.out.println(query);
        
        try {

     
            conn.rs1 = conn.state.executeQuery(query);
            //get the values from the databse and put them in the htmlfield appropriately
    // this is tjhe header for te cervical screen table
           cervicalScreen += "<tr style=\"text-align: center;\">"+
                                "<th> </th>"+
                                 "<th> Date </th>"+
                                 "<th colspan=\"2\"> Examination </th>"+
                                 "<th > Test </th>"+
                                 "<th colspan=\"2\"> Results</th>"+
                                 "<th colspan=\"8\"> Treatment</th>"+
                                 "</tr>";
         int counters=0;
            while (conn.rs1.next()) {
                
counters++;
                String testID= conn.rs1.getString(4);
                //System.out.println("test ID ====="+testID);
// gets the test IDs from the DB
    String queryc = "select * from cervical_tests where test_id='"+testID+"'";
    //System.out.println("queryc"+queryc);
                  conn.rs3 = conn.state3.executeQuery(queryc);
                  
                  while(conn.rs3.next()){
                      if(conn.rs3.getString(2)!=null && conn.rs3.getString(2)!=""){
                         test =conn.rs3.getString(2);
                      }
                 // System.out.println("test======="+test);
                   counter++;
                 }
                  
                  //System.out.println("counter-------"+counter);
                 
                 
                  String select="";
                  
                   String results="";
                   
                   if(conn.rs1.getString(6)!=null){
                       results=conn.rs1.getString(6);
                         //System.out.println("results-------"+results);
                   }
                  
                  
                   String option="";
              
             // cretes an option for the select for test results for via and vili 
               if(results.equalsIgnoreCase("VIA NEGATIVE")){
               
                 option+="<option value=\"VIA Positive\">VIA Positive</option>";
                }
               else if(results.equalsIgnoreCase("VIA positive")){
               
                 option+="<option value=\"VIA Negative\">VIA Negative</option>";
            }
               else if(results.equalsIgnoreCase("VILI positive")){
               
                 option+="<option value=\"VILI Negative\">VILI Negative</option>";
            }
               else if(results.equalsIgnoreCase("VILI NEGATIVE")){
              
                 option+="<option value=\"VILI Positive\">VILI Positive</option>";
            }
             //System.out.println("counters============"+counters);
             
             
             
            
                if( counter<=2 && counters<=2){

 //the existing values
        
         cervicalScreen +="<tr><td style=\"width:40px;text-align: left;\">" + count1 + "</td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" name=\"CervixDate" + count1 + "\"   id=\"CervixDate" + count1 + "\" value=\""+ conn.rs1.getString(3) +"\"></td>"
                        + "<td style=\"width:40px;\">CERVIX</td><td style=\"width:40px;\"><input type=\"checkbox\" style=\"width:40px;\" name=\"Cervix" + count1 + "\" id=\"Cervix" + count1 + "\" value=\""+conn.rs1.getString(5)+"\" onclick=\"oncheck(\'Cervix" + count1 + "\');\"></td>"
                        + "<td style=\"width:40px;\"><label style=\"width:40px;\">" + test + "</label><input type=\"hidden\" value=\""+ count1 +"\" name=\"testID"+count1+"\"></td>"
                        + "<td style=\"width:100px;\"><select onChange=\"checkNegative();\" style=\"width:100px; height:30px;\" name=\"tests" + count1 + "\" id=\"tests" + count1 + "\"/>"
                        + "<option value=\""+results+"\">"+results+"</option>"
                        + "" + option + ""
                        + "</select>"
                        + "</td>"
                        + "<td style=\"width:40px;\"> Suspicious for Cancer <input type=\"checkbox\"    onclick=\"check(); onclick=oncheck(\'Suspect" + count1 +"\'); \"style=\"width:40px;\" name=\"Suspect" + count1 + "\" id=\"Suspect" + count1 + "\"  value=\""+conn.rs1.getString(7)+"\"></td>"
                        + "<td style=\"width:40px;\"> Cryo <input type=\"checkbox\" style=\"width:40px;\" onclick=oncheck(\'Cryo" + count1 +"\');  name=\"Cryo" + count1 + "\" id=\"Cryo" + count1 + "\" value=\""+conn.rs1.getString(8)+"\"></td>"
                        + "<td style=\"width:40px;\"> Leep <input type=\"checkbox\" style=\"width:40px;\"  onclick=oncheck(\'Leep" + count1 +"\');  name=\"Leep" + count1 + "\" id=\"Leep" + count1 + "\" value=\""+conn.rs1.getString(9)+"\"></td>"
                        + "<td style=\"width:40px;\"> Other(Specify) <input type=\"checkbox\" id=\"OtherCheck"+ count1 +"\"  name=\"OtherCheck"+ count1 +"\" onclick=\"check();oncheck(\'OtherCheck" + count1 + "\');\" value=\"\"><input type=\"hidden\" style=\"width:100px;\" name=\"Others" + count1 + "\"  id=\"Others" + count1 + "\" value=\""+conn.rs1.getString(10)+"\" /></td>"
                        + "<td style=\"width:80px;\"> Referral</td><td style=\"width:40px;\"><input type=\"text\" style=\"width:80px;\" name=\"Referral" + count1 + "\" id=\"Referral"+ count1 + "\"  value=\""+conn.rs1.getString(13)+"\"/></td>"
                        + "<input type=\"hidden\"value=\""+conn.rs1.getString(1)+"\" name=\"rowID"+count1+"\"></tr>";
                
       
       
                count1++;

               
             


           
            System.out.println("count-------"+count1);

  }
                 
     if( counter>=2 && counters>2){
    
    
    
     if(results.equalsIgnoreCase("Normal")){
                select+="<option value=\""+conn.rs1.getString(6)+"\">"+conn.rs1.getString(6)+"</option>";
                 select+="<option value=\"LSIL\">LSIL</option>";
            }
               else if(results.equalsIgnoreCase("LSIL")){
                select+="<option value=\""+conn.rs1.getString(5)+"\">"+conn.rs1.getString(5)+"</option>";
                 select+="<option value=\"Normal\">Normal</option>";
            }
              
                
              
             
                cervicalScreen += "<tr><td style=\"width:20px;text-align: left;\">" + count1 + "</td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" name=\"PapCervixDate" + count1 + "\" id=\"PapCervixDate" + count1 + "\" value=\""+ conn.rs1.getString(3) +"\"></td>"
                        + "<td style=\"width:40px;\"></td><td style=\"width:40px;\"><input type=\"checkbox\" style=\"width:40px;\" onclick=\"oncheck(\'cervixPap" + count1 + "\');\" name=\"cervixPap" + count1 + "\"  id=\"cervixPap" + count1 + "\"    value=\""+ conn.rs1.getString(5) +"\"></td>"
                        + "<td style=\"width:40px;\"><label style=\"width:40px;\">Pap Smear</label><input type=\"hidden\" value=\""+ count1 +"\" name=\"PaptestID"+count1+"\"></td>"
                        + "<td style=\"width:100px;\"><select style=\"width:100px; height:30px;\" name=\"papsmear" + count1 + "\" id=\"papsmear" + count1 + "\"/>"
                        + ""+ conn.rs1.getString(6) +""
                        + ""+select+""
                        + "</select>"
                        + "</td>"
                        + "<td style=\"width:40px;\"> HSIL <input type=\"checkbox\" style=\"width:40px;\" name=\"HSIL" + count1 + "\" id=\"HSIL" + count1 + "\" onclick=\"oncheck(\'HSIL" + count1 + "\')\" value=\""+ conn.rs1.getString(11) +"\" /></td>"
                        + "<td style=\"width:40px;\"> Overt cancer <input type=\"checkbox\" style=\"width:40px;\" name=\"Overt" + count1 + "\"  id=\"Overt" + count1 + "\" onclick=\"oncheck(\'Overt" + count1 + "\');\" value=\""+ conn.rs1.getString(12) +"\" /></td>"
                        + "<td style=\"width:40px;\"> Cryo <input type=\"checkbox\" style=\"width:40px;\" name=\"CryoPap" + count1 + "\" id=\"CryoPap" + count1 + "\" onclick=\"oncheck(\'CryoPap" + count1 + "\');\" value=\""+ conn.rs1.getString(8) +"\" /></td>"
                        + "<td style=\"width:40px;\"> Leep <input type=\"checkbox\" style=\"width:40px;\" name=\"LeepPap" + count1 + "\"  id=\"LeepPap" + count1 + "\" onclick=\"oncheck(\'LeepPap" + count1 + "\');\" value=\""+ conn.rs1.getString(9) +"\" /></td>"
                        + "<td style=\"width:40px;\"> Other(Specify)<input type=\"checkbox\" name=\"papsmear\" id=\"papsmear\" onclick=\"check();oncheck(\'papsmear\');\"  value=\""+ conn.rs1.getString(10) +"\"><input type=\"hidden\" style=\"width:40px;\" name=\"OthersPap" + count1 + "\" id=\"OthersPap\"  value=\""+ conn.rs1.getString(10) +"\"></td>"
                        + "<td style=\"width:80px;\"> Referral <br/><input type=\"text\" style=\"width:80px;\" name=\"PapReferral" + count1 + "\" id=\"Referral" + count1 + "\" value=\""+ conn.rs1.getString(13) +"\"></td>"
                        + "<input type=\"hidden\"value=\""+conn.rs1.getString(1)+"\" name=\"PaprowID"+count1+"\"></tr>";
                
                
                }


 }//end of while

            if (cervicalScreen.equals("")) {
                // allinputfields="No mother found under that ANC No.";
            }
            //_____________________after everything else, then add the default input fields.
            //This table will be used to only save the newly input table values 
            //thus the naming of the input fields is different
            
            String option="";
            int counters1=1;
            if(counter==1 && counters==1){
 //counters1++;
 if(test.equalsIgnoreCase("VILI"))
   {
   cervicalScreen += "<tr><td style=\"width:20px;text-align: left;\">" + (counters1+1) + "</td>"
                        + "<td style=\"width:60px;\"><input type=\"text\" style=\"width:100px;\" placeholder=\"yyyy-mm-dd\" name=\"New_CervixDate" + counters1 + "\" id=\"New_CervixDate" + counters1 + "\" value=\"\" ></td>"
                        + "<td style=\"width:40px;\">CERVIX</td><td style=\"width:40px;\"><input type=\"checkbox\" style=\"width:40px;\" name=\"New_Cervix" + counters1 + "\" id=\"New_Cervix" + counters1 + "\" value=\"\" onclick=oncheck(\"New_Cervix" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"><label style=\"width:40px;\">VIA</label><input type=\"hidden\" value=\"1\" name=\"test_id"+counters1+"\"></td>"
                        + "<td style=\"width:100px;\"><select style=\"width:100px; height:30px;\" onChange=\"checkNegative();\" name=\"New_Tests" + counters1 + "\" id=\"New_Tests" + counters1 + "\"/>"
                        + "<option value=\"\"></option>"
                        + "<option value=\"VIA Negative\">VIA Negative</option>"
                        + "<option value=\"VIA Positive\">VIA Positive</option>"
                        + "</select>"
                        + "</td>"
                        + "<td style=\"width:40px;\"> Suspicious for Cancer <input type=\"checkbox\" style=\"width:40px;\" name=\"New_Suspect" + counters1 + "\" value=\"\"  onclick=oncheck(\"New_Suspect" + counters1 +"\");></td>"
                       
                        + "<td style=\"width:40px;\"> Cryo <input type=\"checkbox\" style=\"width:40px;\" name=\"New_Cryo" + counters1 + "\" id=\"New_Cryo" + counters1 + "\" value=\"\" onclick=oncheck(\"New_Cryo" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"> Leep <input type=\"checkbox\" style=\"width:40px;\" name=\"New_Leep" + counters1 + "\" id=\"New_Leep" + counters1 + "\" value=\"\" onclick=oncheck(\"New_Leep" + counters1 +"\");></td>"
                         + "<td style=\"width:40px;\"> Other(Specify) <input type=\"checkbox\" id=\"New_OtherCheck"+ counters1 +"\" name=\"New_OtherCheck"+ counters1 +"\" onclick=\"check();oncheck(\'New_OtherCheck" + counters1 +"\');\" value=\"\"><input type=\"hidden\" style=\"width:100px;\" name=\"New_Others" + counters1 + "\"  id=\"New_Others" + counters1 + "\" value=\"\"></td>"
                        + "<td style=\"width:40px;\"> Referral</td><td style=\"width:40px;\"><input type=\"text\" style=\"width:100px;\" name=\"New_Referral" + counters1 + "\" id=\"New_Referral"+ counters1 + "\" value=\"\"></td>"
                        + "</tr>";}
 
 else if(test.equalsIgnoreCase("VIA"))
     { cervicalScreen += "<tr><td style=\"width:20px;text-align: left;\">" + (counters1+1) + "</td>"
                        + "<td style=\"width:60px;\"><input type=\"text\" style=\"width:100px;\" placeholder=\"yyyy-mm-dd\" name=\"New_CervixDate" + counters1 + "\" id=\"New_CervixDate" + counters1 + "\" value=\"\" ></td>"
                        + "<td style=\"width:40px;\">CERVIX</td><td style=\"width:40px;\"><input type=\"checkbox\" style=\"width:40px;\" name=\"New_Cervix" + counters1 + "\" id=\"New_Cervix" + counters1 + "\" value=\"\" onclick=oncheck(\"New_Cervix" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"><label style=\"width:40px;\">VILI</label><input type=\"hidden\" value=\"2\" name=\"test_id"+counters1+"\"></td>"
                        + "<td style=\"width:100px;\"><select style=\"width:100px; height:30px;\" onChange=\"checkNegative();\" name=\"New_Tests" + counters1 + "\" id=\"New_Tests" + counters1 + "\"/>"
                        + "<option value=\"\"></option>"
                        + "<option value=\"VILI Negative\">VILI Negative</option>"
                        + "<option value=\"VILI Positive\">VILI Positive</option>"
                        + "</select>"
                        + "</td>"
                        + "<td style=\"width:40px;\"> Suspicious for Cancer <input type=\"checkbox\" style=\"width:40px;\" name=\"New_Suspect" + counters1 + "\" value=\"\"  onclick=oncheck(\"New_Suspect" + counters1 +"\");></td>"
                       
                        + "<td style=\"width:40px;\"> Cryo <input type=\"checkbox\" style=\"width:40px;\" name=\"New_Cryo" + counters1 + "\" id=\"New_Cryo" + counters1 + "\" value=\"\" onclick=oncheck(\"New_Cryo" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"> Leep <input type=\"checkbox\" style=\"width:40px;\" name=\"New_Leep" + counters1 + "\" id=\"New_Leep" + counters1 + "\" value=\"\" onclick=oncheck(\"New_Leep" + counters1 +"\");></td>"
                         + "<td style=\"width:40px;\"> Other(Specify) <input type=\"checkbox\" id=\"New_OtherCheck"+ counters1 +"\" name=\"New_OtherCheck"+ counters1 +"\" onclick=\"check();oncheck(\'New_OtherCheck" + counters1 +"\');\" value=\"\"><input type=\"hidden\" style=\"width:100px;\" name=\"New_Others" + counters1 + "\"  id=\"New_Others" + counters1 + "\" value=\"\"></td>"
                        + "<td style=\"width:40px;\"> Referral</td><td style=\"width:40px;\"><input type=\"text\" style=\"width:100px;\" name=\"New_Referral" + counters1 + "\" id=\"New_Referral"+ counters1 + "\" value=\"\"></td>"
                        + "</tr>";}
counters1++;
             cervicalScreen += "<tr><td style=\"width:20px;text-align: left;\">" + (counters1+1) + "</td>"
                        + "<td style=\"width:60px;\"><input type=\"text\" style=\"width:100px;\" name=\"New_CervixPapDate" + counters1 + "\" id=\"New_CervixDate" + counters1 + "\" value=\"\" ></td>"
                        + "<td style=\"width:40px;\"></td><td style=\"width:40px;\"><input type=\"checkbox\" style=\"width:40px;\" name=\"New_PapCervix" +counters1 + "\" id=\"New_PapCervix" +counters1 + "\" value=\"\" onclick=oncheck(\"New_PapCervix" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"><label style=\"width:40px;\">Pap Smear</label><input type=\"hidden\" value=\"3\" name=\"Paptest_id"+counters1+"\"></td>"
                        + "<td style=\"width:100px;\"><select style=\"width:100px; height:30px;\" name=\"New_Papsmear" + counters1 + "\" id=\"New_Papsmear" + counters1 + "\" onChange=\"checkNegative();\" >"
                        + "<option value=\"\"></option>"
                        + "<option value=\"Normal\">Normal</option>"
                        + "<option value=\"LSIL\">LSIL</option>"
                        + "</select>"
                        + "</td>"
                        + "<td style=\"width:40px;\"> HSIL <input type=\"checkbox\" style=\"width:40px;\" name=\"New_HSIL" + counters1 + "\" id=\"New_HSIL" + counters1 + "\" value=\"\" onclick=oncheck(\"New_HSIL" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"> Overt cancer <input type=\"checkbox\" style=\"width:40px;\" name=\"New_Overt" + counters1 + "\" id=\"New_Overt" + counters1 + "\" value=\"\" onclick=oncheck(\"New_Overt" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"> Cryo <input type=\"checkbox\" style=\"width:40px;\" name=\"New_PapCryo" + counters1 + "\" id=\"New_PapCryo" + counters1 + "\" value=\"\" onclick=oncheck(\"New_PapCryo" + counters1 +"\"); ></td>"
                        + "<td style=\"width:40px;\"> Leep <input type=\"checkbox\" style=\"width:40px;\" name=\"New_PapLeep" + counters1 + "\" id=\"New_PapLeep" + counters1 + "\" value=\"\" onclick=oncheck(\"New_PapLeep" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"> Other(Specify)<input type=\"checkbox\"  id=\"papsmear\"   onclick=\"check();oncheck(\'papsmear\');\"><input type=\"hidden\" style=\"width:100px;\" name=\"New_PapOthers" + counters1 + "\"  id=\"OthersPap\" value=\"\" ></td>"
                        + "<td style=\"width:40px;\"> Referral <br/><input type=\"text\" style=\"width:100px;\" name=\"New_PapReferral" + counters1 + "\" id=\"New_Referral" + counters1 + "\" value=\"\"></td>"
                        + "</tr>";
                

            }
            if(counter==2 && counters==2){
 counters1++;
 
             cervicalScreen += "<tr><td style=\"width:20px;text-align: left;\">" + (counters1+1) + "</td>"
                        + "<td style=\"width:60px;\"><input type=\"text\" style=\"width:100px;\" name=\"New_CervixPapDate" + counters1 + "\" placeholder=\"yyyy-mm-dd\" id=\"New_CervixDate" + counters1 + "\" value=\"\" ></td>"
                        + "<td style=\"width:40px;\"></td><td style=\"width:40px;\"><input type=\"checkbox\" style=\"width:40px;\" name=\"New_PapCervix" +counters1 + "\" id=\"New_PapCervix" +counters1 + "\" value=\"\" onclick=oncheck(\"New_PapCervix" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"><label style=\"width:40px;\">Pap Smear</label><input type=\"hidden\" value=\"3\" name=\"Paptest_id"+counters1+"\"></td>"
                        + "<td style=\"width:100px;\"><select style=\"width:100px; height:30px;\" name=\"New_Papsmear" + counters1 + "\"  id=\"New_Papsmear" + counters1 + "\"  onChange=\"checkNegative();\">"
                        + "<option value=\"\"></option>"
                        + "<option value=\"Normal\">Normal</option>"
                        + "<option value=\"LSIL\">LSIL</option>"
                        + "</select>"
                        + "</td>"
                        + "<td style=\"width:40px;\"> HSIL <input type=\"checkbox\" style=\"width:40px;\" name=\"New_HSIL" + counters1 + "\" id=\"New_HSIL" + counters1 + "\" value=\"\" onclick=oncheck(\"New_HSIL" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"> Overt cancer <input type=\"checkbox\" style=\"width:40px;\" name=\"New_Overt" + counters1 + "\" id=\"New_Overt" + counters1 + "\" value=\"\" onclick=oncheck(\"New_Overt" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"> Cryo <input type=\"checkbox\" style=\"width:40px;\" name=\"New_PapCryo" + counters1 + "\" id=\"New_PapCryo" + counters1 + "\" value=\"\" onclick=oncheck(\"New_PapCryo" + counters1 +"\"); ></td>"
                        + "<td style=\"width:40px;\"> Leep <input type=\"checkbox\" style=\"width:40px;\" name=\"New_PapLeep" + counters1 + "\" id=\"New_PapLeep" + counters1 + "\" value=\"\" onclick=oncheck(\"New_PapLeep" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"> Other(Specify)<input type=\"checkbox\"  id=\"papsmear\"   onclick=\"check();oncheck(\'papsmear\');\"><input type=\"hidden\" style=\"width:100px;\" name=\"New_PapOthers" + counters1 + "\"  id=\"OthersPap\" value=\"\" ></td>"
                        + "<td style=\"width:40px;\"> Referral <br/><input type=\"text\" style=\"width:100px;\" name=\"New_PapReferral" + counters1 + "\" id=\"New_Referral" + counters1 + "\" value=\"\"></td>"
                        + "</tr>";
                

            }
            if(counter==0 && counters==0){
                
   cervicalScreen += "<tr><td style=\"width:20px;text-align: left;\">" + counters1 + "</td>"
                        + "<td style=\"width:60px;\"><input type=\"text\"style=\"width:100px;\" placeholder=\"yyyy-mm-dd\" name=\"New_CervixDate" + counters1 + "\" id=\"New_CervixDate" + counters1 + "\" value=\"\" ></td>"
                        + "<td style=\"width:40px;\">CERVIX</td><td style=\"width:40px;\"><input type=\"checkbox\" style=\"width:40px;\" value=\"\" name=\"New_Cervix" + counters1 + "\" id=\"New_Cervix" + counters1 + "\"  onclick=oncheck(\"New_Cervix" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"><label style=\"width:40px;\">VIA</label><input type=\"hidden\" value=\"1\" name=\"test_id"+counters1+"\"></td>"
                        + "<td style=\"width:100px;\"><select style=\"width:100px; height:30px;\" name=\"New_Tests" + counters1 + "\" id=\"New_Tests" + counters1 + "\" onChange=\"checkNegative();\">"
                        + "<option value=\"\"></option>"
                        + "<option value=\"VIA Negative\">VIA Negative</option>"
                        + "<option value=\"VIA Positive\">VIA Positive</option>"
                        + "</select>"
                        + "</td>"
                        + "<td style=\"width:40px;\"> Suspicious for Cancer <input type=\"checkbox\" style=\"width:40px;\" name=\"New_Suspect" + counters1 + "\" id=\"New_Suspect" + counters1 + "\"  onclick=oncheck(\"New_Suspect" + counters1 +"\"); value=\"\"/></td>"
                       
                        + "<td style=\"width:40px;\"> Cryo <input type=\"checkbox\" style=\"width:40px;\" name=\"New_Cryo" + counters1 + "\" id=\"New_Cryo" + counters1 + "\" value=\"\" onclick=oncheck(\"New_Cryo" + counters1 +"\"); ></td>"
                        + "<td style=\"width:40px;\"> Leep <input type=\"checkbox\" style=\"width:40px;\" name=\"New_Leep" + counters1 + "\" id=\"New_Leep" + counters1 + "\" value=\"\" onclick=oncheck(\"New_Leep" + counters1 +"\"); ></td>"
                         + "<td style=\"width:40px;\"> Other(Specify) <input type=\"checkbox\" id=\"New_OtherCheck"+ counters1 +"\" name=\"New_OtherCheck"+ counters1 +"\" id=\"New_OtherCheck"+ counters1 +"\" onclick=\"check(); oncheck(\'New_OtherCheck" + counters1 +"\');\" value=\"\"><input type=\"hidden\" style=\"width:100px;\" value=\"\" name=\"New_Others" + counters1 + "\"  id=\"New_Others" + counters1 + "\" /></td>"
                        + "<td style=\"width:40px;\"> Referral</td><td style=\"width:40px;\"><input type=\"text\" style=\"width:100px;\" name=\"New_Referral" + counters1 + "\" id=\"New_Referral"+ counters1 + "\"  value=\"\"/></td>"
                        + "</tr>";
   counters1++;
   cervicalScreen += "<tr><td style=\"width:20px;text-align: left;\">" + counters1 + "</td>"
                        + "<td style=\"width:60px;\"><input type=\"text\" style=\"width:100px;\"  name=\"New_CervixDate" + counters1 + "\" placeholder=\"yyyy-mm-dd\" id=\"New_CervixDate" + counters1 + "\" value=\"\" ></td>"
                        + "<td style=\"width:40px;\">CERVIX</td><td style=\"width:40px;\"><input type=\"checkbox\" style=\"width:40px;\" name=\"New_Cervix" + counters1 + "\" id=\"New_Cervix" + counters1 + "\" value=\"\" onclick=oncheck(\"New_Cervix" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"><label style=\"width:40px;\">VILI</label><input type=\"hidden\" value=\"2\" name=\"test_id"+counters1+"\"></td>"
                        + "<td style=\"width:100px;\">"
           + "<select style=\"width:100px; height:30px;\" name=\"New_Tests" + counters1 + "\" id=\"New_Tests" + counters1 + "\" onChange=\"checkNegative();\"/>"
                        + "<option value=\"\"></option>"
                        + "<option value=\"VILI Negative\">VILI Negative</option>"
                        + "<option value=\"VILI Positive\">VILI Positive</option>"
                        + "</select>"
                        + "</td>"
                        + "<td style=\"width:40px;\"> Suspicious for Cancer <input type=\"checkbox\" style=\"width:40px;\" name=\"New_Suspect" + counters1 + "\" value=\"\"  onclick=oncheck(\"New_Suspect" + counters1 +"\");></td>"
                       
                        + "<td style=\"width:40px;\"> Cryo <input type=\"checkbox\" style=\"width:40px;\" name=\"New_Cryo" + counters1 + "\" id=\"New_Cryo" + counters1 + "\" value=\"\" onclick=oncheck(\"New_Cryo" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"> Leep <input type=\"checkbox\" style=\"width:40px;\" name=\"New_Leep" + counters1 + "\" id=\"New_Leep" + counters1 + "\" value=\"\" onclick=oncheck(\"New_Leep" + counters1 +"\");></td>"
                         + "<td style=\"width:40px;\"> Other(Specify) <input type=\"checkbox\" id=\"New_OtherCheck"+ counters1 +"\" name=\"New_OtherCheck"+ counters1 +"\" onclick=\"check();oncheck(\'New_OtherCheck" + counters1 +"\');\" value=\"\"><input type=\"hidden\" style=\"width:100px;\" name=\"New_Others" + counters1 + "\"  id=\"New_Others" + counters1 + "\" value=\"\"></td>"
                        + "<td style=\"width:40px;\"> Referral</td><td style=\"width:40px;\"><input type=\"text\" style=\"width:100px;\" name=\"New_Referral" + counters1 + "\" id=\"New_Referral"+ counters1 + "\" value=\"\"></td>"
                        + "</tr>";
counters1++;
             cervicalScreen += "<tr><td style=\"width:20px;text-align: left;\">" + counters1 + "</td>"
                        + "<td style=\"width:60px;\"><input type=\"text\" style=\"width:100px;\" name=\"New_CervixPapDate" + counters1 + "\" placeholder=\"yyyy-mm-dd\" id=\"New_CervixDate" + counters1 + "\" value=\"\" ></td>"
                        + "<td style=\"width:40px;\"></td><td style=\"width:40px;\"><input type=\"checkbox\" style=\"width:40px;\" name=\"New_PapCervix" +counters1 + "\" id=\"New_PapCervix" +counters1 + "\" value=\"\" onclick=oncheck(\"New_PapCervix" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"><label style=\"width:40px;\">Pap Smear</label><input type=\"hidden\" value=\"3\" name=\"Paptest_id"+counters1+"\"></td>"
                        + "<td style=\"width:100px;\"><select style=\"width:100px; height:30px;\" name=\"New_Papsmear" + counters1 + "\" id=\"New_Papsmear" + counters1 + "\"  onChange=\"checkNegative();\">"
                        + "<option value=\"\"></option>"
                        + "<option value=\"Normal\">Normal</option>"
                        + "<option value=\"LSIL\">LSIL</option>"
                        + "</select>"
                        + "</td>"
                        + "<td style=\"width:40px;\"> HSIL <input type=\"checkbox\" style=\"width:40px;\" name=\"New_HSIL" + counters1 + "\" id=\"New_HSIL" + counters1 + "\" value=\"\" onclick=oncheck(\"New_HSIL" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"> Overt cancer <input type=\"checkbox\" style=\"width:40px;\" name=\"New_Overt" + counters1 + "\" id=\"New_Overt" + counters1 + "\" value=\"\" onclick=oncheck(\"New_Overt" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"> Cryo <input type=\"checkbox\" style=\"width:40px;\" name=\"New_PapCryo" + counters1 + "\" id=\"New_PapCryo" + counters1 + "\" value=\"\" onclick=oncheck(\"New_PapCryo" + counters1 +"\"); ></td>"
                        + "<td style=\"width:40px;\"> Leep <input type=\"checkbox\" style=\"width:40px;\" name=\"New_PapLeep" + counters1 + "\" id=\"New_PapLeep" + counters1 + "\" value=\"\" onclick=oncheck(\"New_PapLeep" + counters1 +"\");></td>"
                        + "<td style=\"width:40px;\"> Other(Specify)<input type=\"checkbox\"  id=\"papsmear\"   onclick=\"check();oncheck(\'papsmear\');\"><input type=\"hidden\" style=\"width:100px;\" name=\"New_PapOthers" + counters1 + "\"  id=\"OthersPap\" value=\"\" ></td>"
                        + "<td style=\"width:40px;\"> Referral <br/><input type=\"text\" style=\"width:100px;\" name=\"New_PapReferral" + counters1 + "\" id=\"New_Referral" + counters1 + "\" value=\"\"></td>"
                        + "</tr>";
                

            }
  cervicalScreen += "<input type=\"hidden\" id=\"CervicalCancer\" name=\"CervicalCancer\" value=\"" + counters1 + "\"/> <input type=\"hidden\" id=\"CervicalCancer_old_rows\" value=\"" + (count1-1) + "\" name=\"CervicalCancer_old_rows\" />";

           
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("" + cervicalScreen + "");
                out.println("</body>");
                out.println("</html>");

                cervicalScreen = "";
            } finally {
                out.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(CervicalCancer.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CervicalCancer.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CervicalCancer.class.getName()).log(Level.SEVERE, null, ex);
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
