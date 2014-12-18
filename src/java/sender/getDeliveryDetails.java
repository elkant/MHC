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
public class getDeliveryDetails extends HttpServlet {

    String anc_no;
    String allinputfields;
    String formlabels[] = {"Duration of Pregnancy(weeks):", " Mode of delivery:", "Date of Delivery:", "Condition of mother:", "Apgar Score: 1 min","Apgar Score 5 min","Apgar Score 10 min","Rescuscitation done:", "Place of Delivery:", "Conducted by:"};
    String inputfieldnames[] = {"preg_duration", "delivery_mode", "delivery_date", "mother_condition", "apgar_score_1","apgar_score_5","apgar_score_10","rescuscitation", "delivery_place", "conducted_by"};
    String oldinputfieldnames[] = {"old_preg_duration", "old_delivery_mode", "old_delivery_date", "old_mother_condition","old_apgar_score_1" ,"old_apgar_score_5","old_apgar_score_10","old_rescuscitation", "old_delivery_place", "old_conducted_by"};
    String db_column_names[] = {"pregnancy_duration", "delivery_mode", "date", "mum_condition", "apgar_score_1","apgar_score_5","apgar_score_10","rescuscitation", "delivery_place", "conductor_id"};

    String conothersactivator="false", deliveryotheractovator="false";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    anc_no = request.getParameter("ancno");

    String motherID = request.getParameter("motherID");
    
    String getdetails = "Select *  from delivery where motherid='" + motherID + "'";
 String newdeliverymodes="<option value=\"\"></option>";
        String olddeliverymodes="<option value=\"\"></option>";


    //create a options for displaying in the condition of mother
    
    
    String mother_condions_AR[]={"NORMAL","CRITICAL","SAFE","BAD"};
  
    
    
    
    
    

    allinputfields = "";
    String rescus_options="";
    int count = 0;

    dbConn conn = new dbConn();
  

        //============================================================MAIN TABLE DETAILS==========================   

        conn.rs = conn.st.executeQuery(getdetails);
        //get the values from the databse and put them in the htmlfield appropriately


        //if the mother details exists, then view their saved details from the database
        //otherwise, display the input fields for data entry

        
        
//.............................get the select box input fields first..............................
    String oldrescus="<label> Rescuscitation done:</label></td><td><select id=\""+oldinputfieldnames[7]+"\" name=\""+oldinputfieldnames[7]+"\" >";
    String oldPlaceofdeliveryselect="<label>Place of delivery:</label></td><td><select required id=\""+oldinputfieldnames[8]+"\" name=\""+oldinputfieldnames[8]+"\" onchange=\"podothers(this);\"><option value=\"\"></option>";
    String oldconductorselect="<label>Conducted by:</label></td><td><select id=\""+oldinputfieldnames[9]+"\"  name=\""+oldinputfieldnames[9]+"\" onchange=\"conductorothersfun(this);\"><option value=\"\"></option>";
  
           
                    
//=====================================UNSELECTED DROP DOWNLIST===============================================================
            
              conn.rs4 = conn.st4.executeQuery("Select * from delivery_mode");
                while (conn.rs4.next()) {

                   

                          newdeliverymodes += "<option  value=\"" + conn.rs4.getString(1) + "\">" + conn.rs4.getString(2) + "</option>";
                  

                }
            
//====================================================================================================  
   
        
        
        

        if (conn.rs.next()) { //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            //client name
            allinputfields += "<tr>";
            
//=========================================ALREADY FILLED DROP DOWN LIST===========================================================
            
              conn.rs4 = conn.st4.executeQuery("Select * from delivery_mode");
                while (conn.rs4.next()) {

                    if (conn.rs4.getString(1).trim().equals(conn.rs.getString("delivery_mode"))) {

                        olddeliverymodes += "<option selected value=\"" + conn.rs4.getString(1) + "\">" + conn.rs4.getString(2) + "</option>";
                        newdeliverymodes += "<option  value=\"" + conn.rs4.getString(1) + "\">" + conn.rs4.getString(2) + "</option>";
                    } else {

                        olddeliverymodes += "<option  value=\"" + conn.rs4.getString(1) + "\">" + conn.rs4.getString(2) + "</option>";
                        newdeliverymodes += "<option  value=\"" + conn.rs4.getString(1) + "\">" + conn.rs4.getString(2) + "</option>";
                   
                    }


                }
            
//====================================================================================================            
            
            // get the option values    
   
  //==============================================OLD AGPAR SELECT--not used for now==========================================  
//     conn.rs4=conn.st4.executeQuery("Select * from apgar_score");
//     
//        while(conn.rs4.next()){
//            
//        if(conn.rs4.getString(1).equals(conn.rs.getString("apgar_score"))){
//            
//        oldagparselect+="<option   value=\""+conn.rs4.getString(1) +"\" selected >"+conn.rs4.getString(2) +"</option>";
//        
//        }
//        
//        else{
//         oldagparselect+="<option  value=\""+conn.rs4.getString(1) +"\">"+conn.rs4.getString(2) +"</option>";      
//           } 
//        }

//=====================================MOTHER CONDITION===============================================================
            
            
            
            
             String mother_cond="";
    for(int a=0;a<mother_condions_AR.length;a++){
    mother_cond+="<option value=\""+mother_condions_AR[a]+"\">"+mother_condions_AR[a]+"</option>";
    
        
    }
            
            
            
            
            
            
            
        
//========================================================DELIVERY PLACES OPTION LIST=============================================================
        
        conn.rs3=conn.st3.executeQuery("Select * from delivery_places");
        while(conn.rs3.next()){
        
         if(conn.rs3.getString(1).equals(conn.rs.getString("delivery_place"))){
        oldPlaceofdeliveryselect+="<option  selected value=\""+conn.rs3.getString(1) +"\">"+conn.rs3.getString(2) +"</option>";
        
        //be ready to display the others button and the contained value
        if(conn.rs.getString("delivery_place").equals("3")){
          deliveryotheractovator="true";
          
           }  
        
        }
        
        else{
         oldPlaceofdeliveryselect+="<option  value=\""+conn.rs3.getString(1) +"\">"+conn.rs3.getString(2) +"</option>";      
        }
          
        }
        
//====================================================CONDUCTOR OPTION LIST===============================================================           
        
        
        conn.rs2=conn.st2.executeQuery("Select * from conductor");
        while(conn.rs2.next()){
        
          if(conn.rs2.getString(1).equals(conn.rs.getString("conductor_id"))){
          if(conn.rs.getString("conductor_id").equals("5")){
          conothersactivator="true";
          
           }    
        oldconductorselect+="<option  selected value=\""+conn.rs2.getString(1) +"\">"+conn.rs2.getString(2) +"</option>";
        
        }
        
        else{
         oldconductorselect+="<option  value=\""+conn.rs2.getString(1) +"\">"+conn.rs2.getString(2) +"</option>";      
        }
         
        }
//=====================================================================================================================    
            
        //==========FETCHING RORM VALUES FROM AN EXISTING DATABASE========    

            for (int p = 0; p < formlabels.length; p++) {//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
String myid="";
String mymaxlength="",placeholder="";
String mypattern="";
//set the date id for the calender to appear
                if(formlabels[p].equals("Date of Delivery:")){
                myid="del_dp1";
                placeholder="YYYY-MM-DD";
                }
                else{
                 myid= oldinputfieldnames[count];
                 placeholder="";
                }
                
                
                 if(formlabels[p].equals("Duration of Pregnancy(weeks):")){
                mymaxlength="2";
                mypattern="[0-9]";
                }
             else{
             mymaxlength="";
             mypattern="";
             }
             
                
if(p<7){
    
    if(p==0){allinputfields += "<td style=\"width:80px;\">" + formlabels[count] + "</td>"
+"<td><input type=\"text\" maxlength=\""+mymaxlength+"\" id=\""+myid+"\" name=\"" + oldinputfieldnames[count] + "\" value=\"" + conn.rs.getString("" + db_column_names[count]) + "\"/></td>";
}
    else if(p==1){allinputfields+="<td style=\"width:80px;\">" + formlabels[count] + "</td><td><select id=\"" + oldinputfieldnames[count] + "\" name=\"" + oldinputfieldnames[count] + "\">"+olddeliverymodes+"</select></td>";}
    else{  
allinputfields += "<td style=\"width:80px;\">" + formlabels[count] + "</td>"
+"<td><input type=\"text\" placeholder=\""+placeholder+"\"  maxlength=\""+mymaxlength+"\" id=\""+myid+"\" id=\"" + oldinputfieldnames[count] + "\" name=\"" + oldinputfieldnames[count] + "\" value=\"" + conn.rs.getString("" + db_column_names[count]) + "\"/></td>";
    }
}
else{

 if(oldinputfieldnames[p].equals("old_rescuscitation")){
     //get the alternative option value if not yes
     String yesorno="";
     if(conn.rs.getString("rescuscitation").equals("yes")){
     
     yesorno="<option value=\"no\">no</option>";
     }else{
     
     yesorno="<option value=\"yes\">yes</option>";
     }
     
     
             allinputfields += "<td>"+oldrescus+"<option selected value=\""+ conn.rs.getString("rescuscitation")+"\">"+ conn.rs.getString("rescuscitation")+"</option>"+yesorno+"</select></td>";
             
                                       }
            
            if(oldinputfieldnames[p].equals("old_delivery_place")){
            
                
                 if(deliveryotheractovator.equals("true")){
             allinputfields += "<td>"+oldPlaceofdeliveryselect+"</select><input placeholder=\"delivery place\" required type=\"text\"   value=\"" + conn.rs.getString("delivery_place_other")+ "\" id=\"deliveryothers\" name=\"deliveryothers\"/></td>";
                 }
                 else{
                 allinputfields += "<td>"+oldPlaceofdeliveryselect+"</select><input placeholder=\"delivery place\" required type=\"hidden\"  value=\"" +conn.rs.getString("delivery_place_other")+"\" id=\"deliveryothers\" name=\"deliveryothers\"/></td>";
            
                 
                 }
                 
                 
            }
            
             if(oldinputfieldnames[p].equals("old_conducted_by")){
                 
                 if(conothersactivator.equals("true")){
                 
             allinputfields += "<td>"+oldconductorselect+"</select></td><td><input placeholder=\"conducted by\" required type=\"text\" value=\""+conn.rs.getString("conductor_other")+"\" id=\"conductorothers\"  name=\"oldconductorothers\"/></td>";
                 }
                 else{
                 
                      allinputfields += "<td>"+oldconductorselect+"</select></td><td><input placeholder=\"conducted by\" required type=\"hidden\" value=\""+ conn.rs.getString("conductor_other")+"\" id=\"conductorothers\"  name=\"oldconductorothers\"/></td>";
     
                     
                     
                 }
             }
    
    
    
   }





                count++;

                if (count != 0) {
                    if (count % 2 == 0) {
                        allinputfields += "</tr><tr>";
                    }
                }
            }//end of for
        
        
        }//end of if






   //=================================================================A FRESH FORM .==============================

//---------------------------------------------------------------------------------------------------------------------
//get the select types..
     rescus_options+="<option value=\"\"></option><option value=\"yes\">yes</option><option value=\"no\">no</option>";
    String rescusselect="<select  id=\""+inputfieldnames[7]+"\"  name=\""+inputfieldnames[7]+"\" >"+rescus_options;
    String Placeofdeliveryselect="<select id=\""+inputfieldnames[8]+"\"  name=\""+inputfieldnames[8]+"\" onchange=\"podothers(this);\"><option value=\"\"></option>";
    String conductorselect="<select id=\""+inputfieldnames[9]+"\"  name=\""+inputfieldnames[9]+"\" onchange=\"conductorothersfun(this);\"><option value=\"\"></option>";
  
    
//==========================================================================================================
//        
//        conn.rs4=conn.st4.executeQuery("Select * from apgar_score");
//        while(conn.rs4.next()){
//        
//        agparselect+="<option value=\"yes\">yes</option><option value=\"no\">no</option>";
//        
//        
//        }
//       
        
//=====================================================================================================================
        
        conn.rs4=conn.st4.executeQuery("Select * from delivery_places");
        while(conn.rs4.next()){
        
        Placeofdeliveryselect+="<option value=\""+conn.rs4.getString(1) +"\">"+conn.rs4.getString(2) +"</option>";   
        
        }
        
//===================================================================================================================           
        
        
        conn.rs4=conn.st4.executeQuery("Select * from conductor");
        while(conn.rs4.next()){
        
        conductorselect+="<option value=\""+conn.rs4.getString(1) +"\">"+conn.rs4.getString(2) +"</option>";   
        
        }
//=====================================================================================================================            
        
   
        
        
        
        
        
   






//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=
    int count1 = 0;


    //height
    //if no values were fetched from the database

    if (count == 0) {
        allinputfields += "<tr>";


        for (int p = 0; p < formlabels.length; p++) {
String  myid1;
String mymaxlength="",mypattern="",placeholder;
             if(formlabels[p].equals("Date of Delivery:")){
                myid1="del_dp";
                placeholder="YYYY-MM-DD";
                }
                else{
                 myid1=inputfieldnames[count1] ;
                 placeholder="";
                }
             
             //set maxlength for the first input field
             
             if(formlabels[p].equals("Duration of Pregnancy(weeks):")){
                mymaxlength="2";
                mypattern="[0-9]";
                }
             else{
             mymaxlength="";
             mypattern="";
             }
             
             
             
             
            
            allinputfields += "<td style=\"width:120px;\">" + formlabels[count1] + "</td>";
            if(p<7){
                if(p==0){
                  allinputfields += "<td><input type=\"text\"   placeholder=\"eg 6\" id=\""+myid1+"\" maxlength=\""+mymaxlength+"\" name=\"" + inputfieldnames[count1] + "\"  value=\"\"/></td>";
             
                }
                else if(p==1){
                  allinputfields += "<td><select name=\"" + inputfieldnames[count1] + "\" id=\"" + inputfieldnames[count1] + "\">"+newdeliverymodes+"</select></td>";
            
                }
                else{
                allinputfields += "<td><input type=\"text\"  placeholder=\""+placeholder+"\" id=\""+myid1+"\" maxlength=\""+mymaxlength+"\" name=\"" + inputfieldnames[count1] + "\"  value=\"\"/></td>";
            
                
                }//end of else
                     
                }
            else{
                
                
            if(inputfieldnames[p].equals("rescuscitation")){
             allinputfields += "<td>"+rescusselect+"</select></td>";
                                       }
            
            if(inputfieldnames[p].equals("delivery_place")){
             allinputfields += "<td>"+Placeofdeliveryselect+"</select><br/><input placeholder=\"delivery place\" required type=\"hidden\" id=\"deliveryothers\" name=\"deliveryothers\"/></td>";
                                       }
            
             if(inputfieldnames[p].equals("conducted_by")){
             allinputfields += "<td>"+conductorselect+"</select></td><td><input placeholder=\"conducted by\" required type=\"hidden\" id=\"conductorothers\" name=\"conductorothers\"/></td>";
                                                          }
            
            
            }
            count1++;

            if (count1 != 0) {
                if (count1 % 2 == 0) {
                    allinputfields += "</tr></tr>";
                }
            }



        }//end of for
        if (count1 > 0) {
            allinputfields += "<td><input type=\"hidden\"  name=\"delivery_update_or_save\" value=\""+count+"\"></td></tr>";
        }


    }//end of if
    else {
        //if values were fetched from the database, then close the just opened table          

        allinputfields += "<td><input type=\"hidden\"  name=\"delivery_update_or_save\" value=\""+count+"\"></td></tr>";

    }

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++




//(((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((())))))))))))))))))))))))))))))))))))))))))))))))            
    //System.out.println(""+allinputfields);


    if (allinputfields.equals("")) {

        allinputfields = "No mother found under that ANC No.";
    }

                          rescusselect="";
                          conductorselect="";
                          Placeofdeliveryselect="";
    
                          
                          
    PrintWriter out = response.getWriter();
    try {
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<tr>" + allinputfields + "</tr>");
        out.println("</body>");
        out.println("</html>");


    } finally {
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(getDeliveryDetails.class.getName()).log(Level.SEVERE, null, ex);
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
