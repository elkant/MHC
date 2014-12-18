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
import sendSMS.dbConnect;

/**
 *
 * @author Maureen
 */
public class ALtoANRegister extends HttpServlet {

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
     
    String query="";
    String ALtoAN="";
    dbConnect conn = new dbConnect();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         String anc_no="";
         anc_no=request.getParameter("ancno");
         
        query = "Select * from anc_register where ancno='"+anc_no+"'";



        ALtoAN = "";
      String treatments[];
      String testresults[];
      treatments = new String[]{"1=Hypertension","2=Diabetes","3=Epilepsy","4=Malaria in Pregnancy","5=STIs/RTI","6=Others(Specify)"};
      testresults = new String[]{"Positive","Negative","Unknown","Known Positive"}; 
          String testOptions="";    
        for(int i=0; i<testresults.length;i++){
        testOptions+="<option value=\""+testresults[i]+"\">"+testresults[i]+"</option>";
        }
               ALtoAN +="<tr>"
                       + "<th></th>"
                       + "<th style=\"width: 100px;\">Additional Treatment</th>"
                +"<th style=\"width: 100px;\" colspan=\"2\"> Partner HIV C&T</th>"
                +"<th style=\"width: 100px;\" colspan=\"2\"> </th>"
              +"</tr>";
               ALtoAN +="<tr>"
                        + "<th></th>"
                        + "<th style=\"width: 100px;\"></th>"
                        +"<th style=\"width: 100px;\"> Counselled as a couple</th>"
                        +"<th style=\"width: 100px;\"> Test Results for Partner</th>"
                        +"<th style=\"width: 100px;\"> Referred For</th>"
                        +"<th style=\"width: 100px;\"> Remarks</th>"
                        +"</tr>";
               
              
               ALtoAN +="<tr>"
                + "<th></th>"
                + "<th>al</th>"
                + "<th>am</th>"
                + "<th>an</th>"
                + "<th>ao</th>"
                + "<th>ap</th>"
               +"</tr>";
               
                      
        

      
        try {

            //============================================================PREVIOUS PREGNANCY TABLE DETAILS==========================   




            conn.rs1 = conn.state.executeQuery(query);
            //get the values from the databse and put them in the htmlfield appropriately

            int count1 = 1;
            int count = 1;
            String option="";
            String option1="";
            String option2="";
            String option3="";
            while (conn.rs1.next()) {
                
                String couple="";
                if(conn.rs1.getString("CoupleCounselled")!=null){
                couple=conn.rs1.getString("CoupleCounselled");
                }
                        if(couple.equalsIgnoreCase("Yes")){
                        option1 +="<option value=\"No\">No</option>";
                                 
                      }
                        if(couple.equalsIgnoreCase("negative")){
                        option1 +="<option value=\"Yes\">Yes</option>";
                               
                      }
                       
                 String PartnerResults="";
                 if(conn.rs1.getString("PartnerResults")!=null){
                 
                 PartnerResults=conn.rs1.getString("PartnerResults");
                 }
                  if(PartnerResults.equalsIgnoreCase("positive")){
                        option2 +="<option value=\"Negative\">Negative</option>"
                                 +"<option value=\"Known Positive\">Known Positive</option>"
                                 +"<option value=\"Unknown\">Unknown</option>";
                                 
                      }
                  if(PartnerResults.equalsIgnoreCase("negative")){
                        option2 +="<option value=\"Positive\">Positive</option>"
                                 +"<option value=\"Known Positive\">Known Positive</option>"
                                 +"<option value=\"Unknown\">Unknown</option>";
                                 
                      }
                  if(PartnerResults.equalsIgnoreCase("Known Positive")){
                        option2 +="<option value=\"Positive\">Positive</option>"
                                 +"<option value=\"Negative\">Negative</option>"
                                 +"<option value=\"Unknown\">Unknown</option>";
                                 
                      }
                  if(PartnerResults.equalsIgnoreCase("Unknown")){
                        option2 +="<option value=\"Positive\">Positive</option>"
                                 +"<option value=\"Negative\">Negative</option>"
                                 +"<option value=\"Known Positive\">Known Positive</option>";
                                 
                      }
                  for(int i=0;i<treatments.length;i++){
                      option3+="<option value="+treatments[i]+">"+treatments[i]+"</option>";
                      if(!treatments[i].equalsIgnoreCase(conn.rs1.getString("AdditionalTreatment")))
                      {
                  option+="<option value=\""+treatments[i]+"\">"+treatments[i]+"</option>";
                  }
                      }
                ALtoAN += "<tr>"
                        + "<td style=\"width:40px;text-align: left;\">" + count1 + "</td>"
                        + "<td style=\"width:100px;\">" 
                        + "<select style=\"width:150px; height:60px; \"name=\"treatment" + count1 + "\" id=\"treatment\" onchange=\"change();\" multiple>"
                        + "<option selected value=\"" + conn.rs1.getString("AdditionalTreatment") + "\">" + conn.rs1.getString("AdditionalTreatment") + "</option>"
                        + ""+option+""
                        + "</select>"
                        + "<br/><input type=\"hidden\" id=\"others1\" value=\"\" name=\"others"+ count1 +"\"></td>"
                        
                        + "<td style=\"width:80px;\"><select style=\"width:80px;\" name=\"CoupleCounselled" + count1 + "\"/><option value=\" " + conn.rs1.getString("CoupleCounselled") + "\" >" + conn.rs1.getString("CoupleCounselled") + "</option>"+option1+"</select></td>"
                        + "<td style=\"width:80px;\"><select style=\"width:80px;\" name=\"PartnerResults" + count1 + "\"/><option value=\"" + conn.rs1.getString("PartnerResults") + "\" >" + conn.rs1.getString("PartnerResults") + "</option>"+option2+"</select></td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" name=\"RefferedFor" + count1 + "\" value=\"" + conn.rs1.getString(34) + "\" ></td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" name=\"Remarks" + count1 + "\" value=\"" + conn.rs1.getString(35) + "\" ></td>"
                       
                        + "</tr>";




                count1++;





            }//end of while



//(((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((())))))))))))))))))))))))))))))))))))))))))))))))            
            //System.out.println(""+allinputfields);


            if (ALtoAN.equals("")) {
                // allinputfields="No mother found under that ANC No.";
            }
            //_____________________after everything else, then add the default input fields.
            //This table will be used to only save the newly input table values 
            //thus the naming of the input fields is different


            ALtoAN += "<tr>"
                        + "<td style=\"width:40px;text-align: left;\">" + count1 + "</td>"
                        + "<td style=\"width:100px;\">" 
                        + "<select style=\"width:150px; height:60px; \"name=\"RPR" + count1 + "\" id=\"RPR"+ count1 + "\" multiple>"
                        + "<option value=\"\">Select</option>"
                        + ""+option3+""
                        + "</select></td>"
                        
                        + "<td style=\"width:80px;\"><select style=\"width:80px;\" value=\"\" name=\"No_Visits" + count1 + "\">"
                    + "<option value=\"\"></option>"
                    + "<option value=\"Yes\">Yes</option>"
                    + "<option value=\"No\">No</option>"
                    + "</select></td>"
                    + "<td style=\"width:80px;\"><select style=\"width:80px;\" value=\"\" name=\"FName" + count1 + "\">"
                    + "<option></option>"
                    + ""+testOptions+""
                    + "</select></td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" name=\"RefferedFor" + count1 + "\" value=\"\" ></td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" name=\"Remarks" + count1 + "\" value=\"\" ></td>"
                        + "</tr>";


            //the hidden field which holds the number of hidden fields to update
            //no_of_old_rows
            ALtoAN += "<input type=\"hidden\" id=\"ALtoAN\" value=\"" + count + "\"/> <input type=\"hidden\" id=\"ALtoAN_old_rows\" value=\"" + (count1 - 1) + "\"/>";


         //   System.out.println("ANC NO:" + allinputfields);


           
            try {
             
                out.println("" + ALtoAN + "");
              

                ALtoAN = "";
            } finally {
                out.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(getPrevPregDetails.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
