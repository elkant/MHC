/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todbase;

import java.io.IOException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sendSMS.dbConnect;
import sender.dbConn;

/**
 *
 * @author SIXTYFOURBIT
 */
public class saveMaternityRegister extends HttpServlet {
String admNo="";
     HttpSession session;  
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        
        
        dbConn conn= new dbConn();
        dbConnect conn1= new dbConnect();
        
       session=request.getSession(); 
        //========================AN ARRAYLIST TO STORE ALL THE UNIQUE PRIMARY KEYS==========
        //
       //check if a mother has been selected from  the view
        ArrayList uniqkey= new ArrayList();                                                //
        //                                                                                 //
         String allrows = request.getParameter("all_rows6_count");                     //
         int allRows1 = Integer.parseInt(allrows);                                  //
        //                                                                                 //
        //                                                                                 //
        for(int c=0;c<allRows1;c++){                                               //
        //                       
            String New_MotherID = request.getParameter("motherID");
            
            //if you are not adding data for an existing mother
            if(New_MotherID!=null&&!New_MotherID.equals("")){
            uniqkey.add(New_MotherID);
            }
            else{
                uniqkey.add(uniqueid().trim());
                }
                System.out.println(c+"___"+uniqueid());
         }                                                                          //
         //================================================================================== 
        
        
              Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String mdate;

                    Date mydate = new Date();
                    mdate = formatter.format(mydate);
               // timestamp='"+mdate+"',
        
        
        
//       admNo=request.getParameter("admNo"); 
  // begining of saving a to u ...........................................................................     
       
       
       

//            String admNo="";
            String p="";
            
            String facility=request.getParameter("facility");
             if (request.getParameter("admNo") != null && !request.getParameter("admNo").equals("")) {
                admNo = request.getParameter("admNo");
               
                }
             
             
             System.out.println("........................."+admNo);
            int MatReg = 0;
            if (request.getParameter("MatRegister_old_rows") != null && !request.getParameter("MatRegister_old_rows").equals("")) {
                p = request.getParameter("MatRegister_old_rows");
                MatReg = Integer.parseInt(p);
            }
          

            System.out.println("MatRegister_old_rows" + MatReg);
            for (int i = 1; i <= MatReg; i++) {
                  String AdmissionDate = "";
            String adm_No = "";
            String FirstVisit = "";
            String No_Visits = "";
            String FName = "";
            String SName = "";
            String LName = "";
            String Village = "";
            String Age = "";
          
            String MaritalStatus = "";
            String ID = "";
                if (request.getParameter("AdmissionDate" + i) != null && !request.getParameter("AdmissionDate" + i).equals("")) {
                    AdmissionDate = request.getParameter("AdmissionDate"+i);
                   
                    System.out.println("AdmissionDate" + AdmissionDate);
                }
//                if (request.getParameter("admNo" + i) != null && !request.getParameter("admNo" + i).equals("")) {
//                    adm_No = request.getParameter("admNo" + i);
//                    System.out.println("admNo" + adm_No);
//                }
////          
                
                
                if (request.getParameter("No_Visits" + i) != null && !request.getParameter("No_Visits" + i).equals("")) {
                    No_Visits = request.getParameter("No_Visits" + i);
                    System.out.println("No_Visits" + No_Visits);
                }
                if (request.getParameter("FirstName" + i) != null && !request.getParameter("FirstName" + i).equals("")) {
                    FName = request.getParameter("FirstName" + i);
                    System.out.println("FName" + FName);
                }
                if (request.getParameter("SecondName" + i) != null && !request.getParameter("SecondName" + i).equals("")) {
                    SName = request.getParameter("SecondName" + i);

                    System.out.println("SName" + SName);
                }
                if (request.getParameter("LastName" + i) != null && !request.getParameter("LastName" + i).equals("")) {
                    LName = request.getParameter("LastName" + i);
                    System.out.println("LastName" + LName);
                }
                if (request.getParameter("Village" + i) != null && !request.getParameter("Village" + i).equals("")) {
                    Village = request.getParameter("Village" + i);
                    System.out.println("Village" + Village);
                }
                if (request.getParameter("Age" + i) != null && !request.getParameter("Age" + i).equals("")) {
                    Age = request.getParameter("Age" + i);
                    System.out.println("Age" + Age);

                }

                if (request.getParameter("ID" + i) != null && !request.getParameter("ID" + i).equals("")) {
                    ID = request.getParameter("ID" + i);
                    System.out.println("ID" + ID);
                }
                if (request.getParameter("MaritalStatus" + i) != null && !request.getParameter("MaritalStatus" + i).equals("")) {
                    MaritalStatus = request.getParameter("MaritalStatus" + i);
                    System.out.println("MaritalStatus" + MaritalStatus);
                }

                System.out.println("admission" + admNo);


                String query = "";
                String query1 = "";
                if (!AdmissionDate.equals("") ||  (!admNo.equals("") && admNo != null) ) {
                   
                    query1 = "update mat_atoh set timestamp='"+mdate+"', AdmissionDate='" + AdmissionDate + "',AdmissionNo='" + admNo + "',AncVisits='" + No_Visits + "',FirstName='"+FName+"',SecondName='"+SName+"',Village='" + Village + "',LastName='"+LName+"',Age='"+Age+"', MaritalStatus='"+MaritalStatus+"' where MatRegisterID='" + ID + "'";
                   
                    conn1.state.executeUpdate("update mother_details set timestamp='"+mdate+"', Fname='" + FName + "',Sname='" + SName + "',Lname='" + LName + "',Age='" + Age + "',maritalStatus='" + MaritalStatus + "' , facilityname='"+facility+"' where motherID='" + uniqkey.get(i-1) + "'");
                    
                    System.out.println(query1);
                    conn1.state.executeUpdate(query1);


                }

            }
            //=================================== for inserting data for the new prefix in the savematernityregisters table==============================
            String newRows = "";
            int newRows1 = 0;
            if (request.getParameter("MatRegister_newRows") != null && !request.getParameter("MatRegister_newRows").equals("")) {
                newRows = request.getParameter("MatRegister_newRows");
                newRows1 = Integer.parseInt(newRows);
            } String New_AdmissionDate = "";
            String New_anc_no = "";
            String New_FirstVisit = "";
            String New_No_Visits = "";
            String New_FName = "";
            String New_SName = "";
            String New_LName = "";
            String New_Village = "";
            String New_Age = "";
            String New_MaritalStatus = "";
            String New_AdmissionNo = "";
            String New_MotherID = "";
          
            System.out.println("MatRegister_newRows" + newRows1);
            for (int i = 1; i <= newRows1; i++) {
                 
    
    
                if (request.getParameter("New_AdmissionDate" + i) != null && !request.getParameter("New_AdmissionDate" + i).equals("")) {
                    New_AdmissionDate = request.getParameter("New_AdmissionDate" + i);
                    //                  visitNum = Integer.parseInt(visitNo);
                    System.out.println("New_AdmissionDate" + New_AdmissionDate);
                }
                if (request.getParameter("New_AdmissionNum" + i) != null && !request.getParameter("New_AdmissionNum" + i).equals("")) {
                    New_AdmissionNo = request.getParameter("New_AdmissionNum" + i);
                    System.out.println("New_AdmissionNo" + New_AdmissionNo);
                }
                
                if (request.getParameter("New_No_Visits" + i) != null && !request.getParameter("New_No_Visits" + i).equals("")) {
                    New_No_Visits = request.getParameter("New_No_Visits" + i);
                    System.out.println("New_No_Visits" + New_No_Visits);
                }
                if (request.getParameter("New_FirstName" + i) != null && !request.getParameter("New_FirstName" + i).equals("")) {
                    New_FName = request.getParameter("New_FirstName" + i);
                    System.out.println("New_FirstName" + New_FName);
                }
                if (request.getParameter("New_SecondName" + i) != null && !request.getParameter("New_SecondName" + i).equals("")) {
                    New_SName = request.getParameter("New_SecondName" + i);

                    System.out.println("New_SecondName" + New_SName);
                }
                if (request.getParameter("New_LastName" + i) != null && !request.getParameter("New_LastName" + i).equals("")) {
                    New_LName = request.getParameter("New_LastName" + i);
                    System.out.println("New_LName" + New_LName);
                }
                if (request.getParameter("New_Village" + i) != null && !request.getParameter("New_Village" + i).equals("")) {
                    New_Village = request.getParameter("New_Village" + i);
                    System.out.println("New_Village" + New_Village);
                }
                if (request.getParameter("New_Age" + i) != null && !request.getParameter("New_Age" + i).equals("")) {
                    New_Age = request.getParameter("New_Age" + i);
                    System.out.println("New_Age" + New_Age);

                }


                if (request.getParameter("New_MaritalStatus" + i) != null && !request.getParameter("New_MaritalStatus" + i).equals("")) {
                    New_MaritalStatus = request.getParameter("New_MaritalStatus" + i);
                    System.out.println("New_MaritalStatus" + New_MaritalStatus);
                }

  if (request.getParameter("motherID") != null && !request.getParameter("motherID").equals("")) {
                    New_MotherID = request.getParameter("motherID");
                    System.out.println("New_MotherID" + New_MotherID);

                }

String  querysAtoH="";
                String querys = "";
                String querys1 = "";
                try {
                   if (!New_AdmissionDate.equals("") && !New_AdmissionNo.equals("")) { 
                 
                        if(New_MotherID!=null && !New_MotherID.equals("")){
                       
                         querysAtoH = "insert into mat_atoh set AdmissionDate='" + New_AdmissionDate + "',AdmissionNo='" + New_AdmissionNo + "',AncVisits='" + New_No_Visits + "',FirstName='"+New_FName+"',SecondName='"+New_SName+"',Village='" + New_Village + "',LastName='"+New_LName+"',Age='"+New_Age+"', MaritalStatus='"+New_MaritalStatus+"',motherid='"+New_MotherID+"'";
                   
                        
                        }
                        else{
                   querysAtoH = "insert into mat_atoh set AdmissionDate='" + New_AdmissionDate + "',AdmissionNo='" + New_AdmissionNo + "',AncVisits='" + New_No_Visits + "',FirstName='"+New_FName+"',SecondName='"+New_SName+"',Village='" + New_Village + "',LastName='"+New_LName+"',Age='"+New_Age+"', MaritalStatus='"+New_MaritalStatus+"',motherid='"+uniqkey.get(i-1)+"'";
                   }
                        try {
                            System.out.println(querysAtoH);
                            conn1.state.executeUpdate(querysAtoH);
                            
                             conn.st.executeUpdate("insert into mother_details (anc_no,FName,SName,LName,Age,maritalStatus,motherid,MotherProfileID,facilityname) "
                       + "values ('','"+New_FName+"','"+New_SName+"','"+New_LName+"','"+New_Age+"','"+New_MaritalStatus+"','"+uniqkey.get(i-1) +"','"+uniqkey.get(i-1) +"','"+facility+"')");
           
                            
                        } 
                        
                        catch (SQLException B) {
                            System.out.println(B.toString());
                        }



                   }  
                } catch (Exception EX) {
                    System.out.println("error" + EX.toString());
                }
            }
//==========================================================end of saving a to g===================================================================================================
//==========================================================beginning  of saving H TO I===================================================================================================


             String rows="";
              int rows1=0;
              
              if(request.getParameter("HtoL_old_rows")!= null && !request.getParameter("HtoL_old_rows").equals("")){
                    rows= request.getParameter("HtoL_old_rows");
                      rows1 = Integer.parseInt(rows);
                  }
            
              String parity="";
             String gravidae="";
             String LMP="";
             String EDD="";
             String Diagnosis="";
            String HtoLID="";
            System.out.println("adm no"+admNo) ;
            
             System.out.println("rows1-----"+ rows1);
              for(int i=1;i<=rows1;i++) {
                 if (request.getParameter("New_AdmissionNum" + i) != null && !request.getParameter("New_AdmissionNum" + i).equals("")) {
                    New_AdmissionNo = request.getParameter("New_AdmissionNum" + i);
                    System.out.println("New_AdmissionNo" + New_AdmissionNo);
                }
                 else {
New_AdmissionNo = request.getParameter("AdmissionNum" + i);    

                    }
 
                 
              if(request.getParameter("parity"+i)!= null && !request.getParameter("parity"+i).equals("")){
                  parity= request.getParameter("parity"+i);
//                  visitNum = Integer.parseInt(visitNo);
               System.out.println("parity"+parity);    
              }
              if(request.getParameter("gravidae"+i)!= null && !request.getParameter("gravidae"+i).equals("")){
                  gravidae= request.getParameter("gravidae"+i);
                  System.out.println("gravidae"+gravidae); }
              if(request.getParameter("LMP"+i)!= null && !request.getParameter("LMP"+i).equals("")){
                  LMP= request.getParameter("LMP"+i);
                  System.out.println("LMP"+LMP);}
              if(request.getParameter("EDD"+i)!= null && !request.getParameter("EDD"+i).equals("")){
                  EDD= request.getParameter("EDD"+i);
                  System.out.println("EDD"+EDD);
                  }
              if(request.getParameter("Diagnosis"+i)!= null && !request.getParameter("Diagnosis"+i).equals("")){
                  Diagnosis= request.getParameter("Diagnosis"+i);
                   System.out.println("Diagnosis"+Diagnosis);
                  }
             
              if(request.getParameter("HtoLID")!= null && !request.getParameter("HtoLID").equals("")){
                  HtoLID= request.getParameter("HtoLID");
                    System.out.println("HtoLID"+HtoLID);
                  }
             
            
               String query1="";
             
              
              if(!admNo.equals("")|| !HtoLID.equals("") ){
             query1 = "update mat_htol set timestamp='"+mdate+"', AdmissionNo='"+admNo+"',Parity='"+parity+"',Gravidae='"+gravidae+"',LMP='"+LMP+"',EDD='"+EDD+"',Diagnosis='"+Diagnosis+"' where MatRegisterID='"+HtoLID+"'";
               conn1.state.executeUpdate(query1); 
             System.out.println(query1);
              }
              
              }
            
            //============================== end of updating h to l===========================================================
            //============================== start of saving h to l===========================================================
            
            
              String HtoL="";
              int HtoLRows=0;
//              if(request.getParameter("HtoL")!= null && !request.getParameter("HtoL").equals("")){
                    HtoL= request.getParameter("HtoL");
                      HtoLRows = Integer.parseInt(HtoL);
//                  }
             
      
            
             System.out.println("HtoLRows-----"+ HtoLRows);
              for(int i=1;i<=HtoLRows;i++) {
                     String New_parity="";
             String New_gravidae="";
             String New_LMP="";
             String New_EDD="";
             String New_Diagnosis="";
             if (request.getParameter("New_AdmissionNum" + i) != null && !request.getParameter("New_AdmissionNum" + i).equals("")) {
                    New_AdmissionNo = request.getParameter("New_AdmissionNum" + i);
                    System.out.println("New_AdmissionNo" + New_AdmissionNo);
                }
              else {
                  New_AdmissionNo = request.getParameter("AdmissionNum" + i);    

                    }
                  
              if(request.getParameter("New_Parity"+i)!= null && !request.getParameter("New_Parity"+i).equals("")){
                  New_parity= request.getParameter("New_Parity"+i);
//                  visitNum = Integer.parseInt(visitNo);
               System.out.println("New_Parity======"+New_parity);    
              }
              if(request.getParameter("New_Gravida"+i)!= null && !request.getParameter("New_Gravida"+i).equals("")){
                  New_gravidae= request.getParameter("New_Gravida"+i);
                  System.out.println("New_Gravida"+New_gravidae); }
              if(request.getParameter("New_LMP"+i)!= null && !request.getParameter("New_LMP"+i).equals("")){
                  New_LMP= request.getParameter("New_LMP"+i);
                  System.out.println("New_LMP"+New_LMP);}
              if(request.getParameter("New_EDD"+i)!= null && !request.getParameter("New_EDD"+i).equals("")){
                  New_EDD= request.getParameter("New_EDD"+i);
                  System.out.println("New_EDD"+New_EDD);
                  }
              if(request.getParameter("New_Diagnosis"+i)!= null && !request.getParameter("New_Diagnosis"+i).equals("")){
                  New_Diagnosis= request.getParameter("New_Diagnosis"+i);
                   System.out.println("New_Diagnosis"+New_Diagnosis);
                  }
          
   if (request.getParameter("motherID") != null && !request.getParameter("motherID").equals("")) {
                    New_MotherID = request.getParameter("motherID");
                    System.out.println("New_MotherID" + New_MotherID);

                }          
              
             
               String queryHTOL="";
             if((!New_parity.equals("") ||!New_gravidae.equals(""))&& !New_AdmissionNo.equals("")){
               
                  if(New_MotherID!=null && !New_MotherID.equals("")){
             queryHTOL = "insert into mat_htol set AdmissionNo='"+New_AdmissionNo+"', Parity='"+New_parity+"',Gravidae='"+New_gravidae+"',LMP='"+New_LMP+"',EDD='"+New_EDD+"',Diagnosis='"+New_Diagnosis+"',motherid='"+New_MotherID+"'";
              
                  }
                  else{
                   queryHTOL = "insert into mat_htol set AdmissionNo='"+New_AdmissionNo+"', Parity='"+New_parity+"',Gravidae='"+New_gravidae+"',LMP='"+New_LMP+"',EDD='"+New_EDD+"',Diagnosis='"+New_Diagnosis+"',motherid='"+uniqkey.get(i-1) +"' ";
             }
             conn1.state.executeUpdate(queryHTOL); 
                     System.out.println(queryHTOL);
            
                    }
                   
              }
            
      //======================================================= end of inserting into db from h to l =================================================================================
      //======================================================= start of updating db from m to u =================================================================================
            
            
            
            
            int mtou = 0;
            String g="";
            if (request.getParameter("MtoU_old_rows") != null && !request.getParameter("MtoU_old_rows").equals("")) {
                g = request.getParameter("MtoU_old_rows");
                mtou = Integer.parseInt(p);
            }
          

            System.out.println("MtoU_old_rows" + mtou);
            for (int i = 1; i <= mtou; i++) {
                  String LabourDuration = "";
            String DeliveryDate = "";
            String DeliveryTime = "";
            String GestationAtBirth = "";
            String DeliveryMode = "";
            String PlacentaComplete = "";
            String BloodLoss = "";
            String ConditionAfterDelivery = "";
            String DeliveryComplications = "";
           
            String MtoUID = "";
                if (request.getParameter("LabourDuration" + i) != null && !request.getParameter("LabourDuration" + i).equals("")) {
                    LabourDuration = request.getParameter("LabourDuration"+i);
                   
                    System.out.println("LabourDuration" + LabourDuration);
                }
             
//          
                if (request.getParameter("DeliveryDate" + i) != null && !request.getParameter("DeliveryDate" + i).equals("")) {
                    DeliveryDate = request.getParameter("DeliveryDate" + i);
                    System.out.println("DeliveryDate" + DeliveryDate);
                }
                if (request.getParameter("DeliveryTime" + i) != null && !request.getParameter("DeliveryTime" + i).equals("")) {
                    DeliveryTime = request.getParameter("DeliveryTime" + i);
                    System.out.println("DeliveryTime" + DeliveryTime);
                }
                if (request.getParameter("GestationAtBirth" + i) != null && !request.getParameter("GestationAtBirth" + i).equals("")) {
                    GestationAtBirth = request.getParameter("GestationAtBirth" + i);

                    System.out.println("GestationAtBirth" + GestationAtBirth);
                }
                if (request.getParameter("DeliveryMode" + i) != null && !request.getParameter("DeliveryMode" + i).equals("")) {
                    DeliveryMode = request.getParameter("DeliveryMode" + i);
                    System.out.println("DeliveryMode" + DeliveryMode);
                }
                if (request.getParameter("PlacentaComplete" + i) != null && !request.getParameter("PlacentaComplete" + i).equals("")) {
                    PlacentaComplete = request.getParameter("PlacentaComplete" + i);
                    System.out.println("PlacentaComplete" + PlacentaComplete);
                }
                if (request.getParameter("BloodLoss" + i) != null && !request.getParameter("BloodLoss" + i).equals("")) {
                    BloodLoss = request.getParameter("BloodLoss" + i);
                    System.out.println("BloodLoss" + BloodLoss);

                }

                if (request.getParameter("MtoUID") != null && !request.getParameter("MtoUID").equals("")) {
                    MtoUID = request.getParameter("MtoUID");
                    System.out.println("MtoUID" + MtoUID);
                }
                if (request.getParameter("ConditionAfterDelivery" + i) != null && !request.getParameter("ConditionAfterDelivery" + i).equals("")) {
                    ConditionAfterDelivery = request.getParameter("ConditionAfterDelivery" + i);
                    System.out.println("ConditionAfterDelivery" + ConditionAfterDelivery);
                }
                if (request.getParameter("DeliveryComplications" + i) != null && !request.getParameter("DeliveryComplications" + i).equals("")) {
                    DeliveryComplications = request.getParameter("DeliveryComplications" + i);
                    System.out.println("DeliveryComplications" + DeliveryComplications);
                }

                System.out.println("admission" + New_AdmissionNo);


                String query = "";
                String query1 = "";
                if (!admNo.equals("") || !LabourDuration.equals("")  ) {
                   
                    query1 = "update mat_mtou set timestamp='"+mdate+"', LabourDuration='" + LabourDuration + "',DeliveryDate='" + DeliveryDate + "',DeliveryTime='" + DeliveryTime + "',GestationAtBirth='"+GestationAtBirth+"',DeliveryMode='"+DeliveryMode+"',PlacentaComplete='" + PlacentaComplete + "',BloodLoss='"+BloodLoss+"',ConditionAfterDelivery='"+ConditionAfterDelivery+"', DeliveryComplications='"+DeliveryComplications+"' where MatRegisterID='" + MtoUID + "'";
                    System.out.println(query1);
                    conn1.state.executeUpdate(query1);


                }

            }
      //======================================================= end of updating db from m to u =================================================================================
      //======================================================= start of saving  from m to u =================================================================================
            
            
            
            
            int mtouRows = 0;
            String h="";
            if (request.getParameter("MtoU") != null && !request.getParameter("MtoU").equals("")) {
                h = request.getParameter("MtoU");
                mtouRows = Integer.parseInt(h);
            }
          

            System.out.println("MtoU Rows" + mtouRows);
            for (int i = 1; i <= mtouRows; i++) {
                  String New_LabourDuration = "";
            String New_DeliveryDate = "";
            String New_DeliveryTime = "";
            String New_GestationAtBirth = "";
            String New_DeliveryMode = "";
            String New_PlacentaComplete = "";
            String New_BloodLoss = "";
            String New_ConditionAfterDelivery = "";
            String New_DeliveryComplications = "";
           
            String MtoUID = "";
                if (request.getParameter("New_LabourDuration" + i) != null && !request.getParameter("New_LabourDuration" + i).equals("")) {
                    New_LabourDuration = request.getParameter("New_LabourDuration"+i);
                   
                    System.out.println("New_LabourDuration" + New_LabourDuration);
                }
              if (request.getParameter("New_AdmissionNum" + i) != null && !request.getParameter("New_AdmissionNum" + i).equals("")) {
                    New_AdmissionNo = request.getParameter("New_AdmissionNum" + i);
                    System.out.println("New_AdmissionNo" + New_AdmissionNo);
                } 
              
               else {
New_AdmissionNo = request.getParameter("AdmissionNum" + i);    

                    }
//          
                if (request.getParameter("New_DeliveryDate" + i) != null && !request.getParameter("New_DeliveryDate" + i).equals("")) {
                    New_DeliveryDate = request.getParameter("New_DeliveryDate" + i);
                    System.out.println("New_DeliveryDate" + New_DeliveryDate);
                }
                if (request.getParameter("New_DeliveryTime" + i) != null && !request.getParameter("New_DeliveryTime" + i).equals("")) {
                    New_DeliveryTime = request.getParameter("New_DeliveryTime" + i);
                    System.out.println("New_DeliveryTime" + New_DeliveryTime);
                }
                if (request.getParameter("New_GestationAtBirth" + i) != null && !request.getParameter("New_GestationAtBirth" + i).equals("")) {
                    New_GestationAtBirth = request.getParameter("New_GestationAtBirth" + i);

                    System.out.println("New_GestationAtBirth" + New_GestationAtBirth);
                }
                if (request.getParameter("New_DeliveryMode" + i) != null && !request.getParameter("New_DeliveryMode" + i).equals("")) {
                    New_DeliveryMode = request.getParameter("New_DeliveryMode" + i);
                    System.out.println("New_DeliveryMode" + New_DeliveryMode);
                }
                if (request.getParameter("New_PlacentaComplete" + i) != null && !request.getParameter("New_PlacentaComplete" + i).equals("")) {
                    New_PlacentaComplete = request.getParameter("New_PlacentaComplete" + i);
                    System.out.println("New_PlacentaComplete" + New_PlacentaComplete);
                }
                if (request.getParameter("New_BloodLoss" + i) != null && !request.getParameter("New_BloodLoss" + i).equals("")) {
                    New_BloodLoss = request.getParameter("New_BloodLoss" + i);
                    System.out.println("New_BloodLoss" + New_BloodLoss);

                }
                if (request.getParameter("New_ConditionAfterDelivery" + i) != null && !request.getParameter("New_ConditionAfterDelivery" + i).equals("")) {
                    New_ConditionAfterDelivery = request.getParameter("New_ConditionAfterDelivery" + i);
                    System.out.println("New_ConditionAfterDelivery" + New_ConditionAfterDelivery);
                }
                if (request.getParameter("New_DeliveryComplications" + i) != null && !request.getParameter("New_DeliveryComplications" + i).equals("")) {
                    New_DeliveryComplications = request.getParameter("New_DeliveryComplications" + i);
                    System.out.println("New_DeliveryComplications" + New_DeliveryComplications);
                }
         
   if (request.getParameter("motherID") != null && !request.getParameter("motherID").equals("")) {
                    New_MotherID = request.getParameter("motherID");
                    System.out.println("New_MotherID" + New_MotherID);

                }  
                System.out.println("admission" + New_AdmissionNo);


                String query = "";
               
                if ((!New_AdmissionNo.equals("") || !New_LabourDuration.equals(""))&& (!New_DeliveryDate.equals("")&&New_DeliveryDate!=null)) {
                   if(New_MotherID!=null && !New_MotherID.equals("")){
                    query = "insert into mat_mtou set AdmissionNo='"+New_AdmissionNo+"',LabourDuration='" + New_LabourDuration + "',DeliveryDate='" + New_DeliveryDate + "',DeliveryTime='" + New_DeliveryTime + "',GestationAtBirth='"+New_GestationAtBirth+"',DeliveryMode='"+New_DeliveryMode+"',PlacentaComplete='" + New_PlacentaComplete + "',BloodLoss='"+New_BloodLoss+"',ConditionAfterDelivery='"+New_ConditionAfterDelivery+"', DeliveryComplications='"+New_DeliveryComplications+"',motherid='"+New_MotherID+"'";
                    
                   }
                   else{
                    query = "insert into mat_mtou set AdmissionNo='"+New_AdmissionNo+"',LabourDuration='" + New_LabourDuration + "',DeliveryDate='" + New_DeliveryDate + "',DeliveryTime='" + New_DeliveryTime + "',GestationAtBirth='"+New_GestationAtBirth+"',DeliveryMode='"+New_DeliveryMode+"',PlacentaComplete='" + New_PlacentaComplete + "',BloodLoss='"+New_BloodLoss+"',ConditionAfterDelivery='"+New_ConditionAfterDelivery+"', DeliveryComplications='"+New_DeliveryComplications+"',motherid='"+uniqkey.get(i-1) +"'";
                   
                   }
                    System.out.println(query);
                    conn1.state.executeUpdate(query);


                }

            }     
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       // end of saving a to u......................................................................
       // begining of saving v to an......................................................................
       String vab_newrows="",vab_oldrows="";
       
       
       
       
       if(request.getParameter("vab_oldrows")!=null){
       vab_oldrows=request.getParameter("vab_oldrows");
       
       }
       
       if(request.getParameter("vab_newrows")!=null){
           
       vab_newrows=request.getParameter("vab_newrows");
       
       
       }
       
       //if(request.getParameter("admNo")!=null&&!request.getParameter("admNo").trim().equals("")){
       
           //====================insert new values v to ab ==============================
           
       System.out.println("VTOAB new Rows are::"+vab_newrows);
          
      for(int a=1; a<=Integer.parseInt(vab_newrows);a++){
            try {
                
                String New_adm_no="";
                
if (request.getParameter("New_AdmissionNum" + a) != null && !request.getParameter("New_AdmissionNum" + a).equals("")) {
New_adm_no = request.getParameter("New_AdmissionNum" + a);
System.out.println(a+"___New_AdmissionNum" + New_anc_no);
                                }
else {
New_adm_no = request.getParameter("AdmissionNum" + a);    

}
                
                String vab_new_sex=request.getParameter("vab_new_sex"+a);
                String vab_new_weight=request.getParameter("vab_new_weight"+a);
                String vab_new_livebirth=request.getParameter("vab_new_livebirth"+a);
                String new_apgarscore=request.getParameter("new_apgarscore"+a);
                String vab_new_vdrl=request.getParameter("vab_new_vdrl"+a);
                String vab_new_anc=request.getParameter("vab_new_anc"+a);
                String vab_new_mat=request.getParameter("vab_new_mat"+a);
                if(!vab_new_sex.equals("")||!vab_new_weight.equals("") ){
             
                    String updatematvtoab="insert into mat_vtoab  (ancno,Sex,birth_weight,live_Birth,apgar_score,vdrl_rpr_results,hiv_anc,hiv_maternity,motherid ) values ('"+New_adm_no+"','"+vab_new_sex +"','"+vab_new_weight+"','"+vab_new_livebirth+"','"+new_apgarscore+"','"+vab_new_vdrl+"','"+vab_new_anc+"','"+vab_new_mat+"','"+uniqkey.get(a-1) +"')";
                    
                    System.out.println(updatematvtoab);
                    
               conn.st.executeUpdate(updatematvtoab);
           
                }
                
                }
            catch (SQLException ex) {
                Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
      
      //==============================updating v to ab=========================
     
       
      for(int a=1; a<=Integer.parseInt(vab_oldrows);a++){
            try {
                String vab_tableid=request.getParameter("vab_tableid"+a);
                String vab_new_sex=request.getParameter("vab_old_sex"+a);
                String vab_new_weight=request.getParameter("vab_old_weight"+a);
                String vab_new_livebirth=request.getParameter("vab_old_livebirth"+a);
                String new_apgarscore=request.getParameter("old_apgarscore"+a);
                String vab_new_vdrl=request.getParameter("vab_old_vdrl"+a);
                String vab_new_anc=request.getParameter("vab_old_anc"+a);
                String vab_new_mat=request.getParameter("vab_old_mat"+a);
              
                System.out.println("Updating value++==========="+vab_tableid+ "   "+vab_new_sex);
                if(!vab_new_sex.equals("")&&vab_new_sex!=null){
                    String vtoabupdate="update mat_vtoab  set timestamp='"+mdate+"', Sex='"+vab_new_sex +"',birth_weight='"+vab_new_weight+"',live_Birth='"+vab_new_livebirth+"',apgar_score='"+new_apgarscore+"',vdrl_rpr_results='"+vab_new_vdrl+"',hiv_anc='"+vab_new_anc+"',hiv_maternity='"+vab_new_mat+"' where tableid='"+vab_tableid+"'";
                    
                    System.out.println(vtoabupdate);
                    
               conn.st.executeUpdate(vtoabupdate);
                }
                } catch (SQLException ex) {
                Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      
      } 
       //}//end of if
       
       
       
//===================================   end of  v to ab ========================================       
       
       
       
       
       

     String acai_newrows="",acai_oldrows="";
       
       
       
       
       if(request.getParameter("acai_oldrows")!=null){
       acai_oldrows=request.getParameter("acai_oldrows");
       
       }
       
       if(request.getParameter("acai_newrows")!=null){
           
       acai_newrows=request.getParameter("acai_newrows");
       
       
       }
       
     //  if(request.getParameter("admNo")!=null&&!request.getParameter("admNo").trim().equals("")){
       
           //====================new values==============================
           
          
      for(int a=1; a<=Integer.parseInt(acai_newrows);a++){
        
          
          String New_adm_no="";
          
          if (request.getParameter("New_AdmissionNum" + a) != null && !request.getParameter("New_AdmissionNum" + a).equals("")) {
New_adm_no = request.getParameter("New_AdmissionNum" + a);
System.out.println(a+"____New_AdmissionNum" + New_anc_no);
                                                          }
        else {
New_adm_no = request.getParameter("AdmissionNum" + a);    

}
          
          
            try {
                String acai_new_anc=request.getParameter("acai_new_anc"+a);
                String acai_new_mat=request.getParameter("acai_new_mat"+a);
                String new_tobaby=request.getParameter("new_tobaby"+a);
                String acai_ctx=request.getParameter("acai_ctx"+a);
                String acai_vitamina=request.getParameter("acai_vitamina"+a);
                String acai_results=request.getParameter("acai_results"+a);
                String acai_tetsed=request.getParameter("acai_tetsed"+a);
                if(!new_tobaby.equals("")||!acai_vitamina.equals("") ){
                    
               conn.st.executeUpdate("insert into mat_actoai  (ancno,arv_anc,arv_maternity,arv_tobaby,ctx_tomother,vitamin_a,partner_tested,partner_results,motherid ) values ('"+New_adm_no+"','"+acai_new_anc +"','"+acai_new_mat+"','"+new_tobaby+"','"+acai_ctx+"','"+acai_vitamina+"','"+acai_tetsed+"','"+acai_results+"','"+uniqkey.get(a-1) +"')");
           
                }
                
                }
            catch (SQLException ex) {
                Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
      
      
     
       
      for(int a=1; a<=Integer.parseInt(acai_oldrows);a++){
          
          
            try {
             String acai_old_anc=request.getParameter("acai_old_anc"+a);
                String acai_old_mat=request.getParameter("acai_old_mat"+a);
                String old_tobaby="";
                 old_tobaby=request.getParameter("old_tobaby"+a);
                String acai_old_ctx=request.getParameter("acai_old_ctx"+a);
                String acai_old_vitamina=request.getParameter("acai_old_vitamina"+a);
                String acai_old_results=request.getParameter("acai_old_results"+a);
                String acai_old_tetsed=request.getParameter("acai_old_tetsed"+a);
                if(!old_tobaby.equals("")||!acai_old_vitamina.equals("") ){
                
               conn.st.executeUpdate("update mat_actoai SET timestamp='"+mdate+"', arv_anc='"+acai_old_anc+"',arv_maternity='"+acai_old_mat+"',arv_tobaby='"+old_tobaby+"',ctx_tomother='"+acai_old_ctx+"',vitamin_a='"+acai_old_vitamina+"',partner_tested='"+acai_old_tetsed+"',partner_results='"+acai_old_results+"' where tableid='acai_tableid"+a+"'  ");
           
            }} catch (SQLException ex) {
                Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      
      } 
      // }//end of if       
       
       
/////////////////////////////////////////////////////////////////////////////////////AJ TO AN /////////////////
       
        String ajan_newrows="",ajan_oldrows="";
       
       if(request.getParameter("ajan_oldrows")!=null){
       ajan_oldrows=request.getParameter("ajan_oldrows");
       
       }
       
       if(request.getParameter("ajan_newrows")!=null){
           
       ajan_newrows=request.getParameter("ajan_newrows");
       
       
       }
       
       System.out.println(""+ajan_oldrows);
       
       
      // if(request.getParameter("admNo")!=null&&!request.getParameter("admNo").trim().equals("")){
       
           //====================new values==============================
           
          
      for(int c=1; c<=Integer.parseInt(ajan_newrows);c++){
          
          
          String New_adm_no="";
          
if (request.getParameter("New_AdmissionNum" + c) != null && !request.getParameter("New_AdmissionNum" + c).equals("")) {
 New_adm_no = request.getParameter("New_AdmissionNum" + c);
System.out.println(c+" ____New_AdmissionNum" + New_anc_no);
                                                          }
else {
    
New_adm_no = request.getParameter("AdmissionNum" + c);    

}
          
          
          
            try {
                
                String ajan_old_conductor=request.getParameter("ajan_new_conductor"+c);
                String ajan_old_birthno=request.getParameter("ajan_new_birthno"+c);
                String ajan_old_date=request.getParameter("ajan_new_date"+c);
                String old_status_of_baby=request.getParameter("new_status_of_baby"+c);
                String ajan_old_comments=request.getParameter("ajan_new_comments"+c);
              
                if(!ajan_old_conductor.equals("")||!ajan_old_birthno.equals("") ){
                    
               conn.st.executeUpdate("insert into mat_ajtoan  (ancno,delivery_by,birth_no,discharge_date,baby_status,comments,motherid ) values ('"+New_adm_no+"','"+ajan_old_conductor +"','"+ajan_old_birthno+"','"+ajan_old_date+"','"+old_status_of_baby+"','"+ajan_old_comments+"','"+uniqkey.get(c-1) +"')");
           
                }
                
                }
            catch (SQLException ex) {
                Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
      
      
     //==================================oldrows=================================
       
      if(!ajan_oldrows.equals("0")){
       for(int c=1; c<=Integer.parseInt(ajan_oldrows);c++){
            try {
                String ajan_old_conductor=request.getParameter("ajan_old_conductor"+c);
                String ajan_old_birthno=request.getParameter("ajan_old_birthno"+c);
                String ajan_old_date=request.getParameter("ajan_old_date"+c);
                String old_status_of_baby=request.getParameter("old_status_of_baby"+c);
                String ajan_old_comments=request.getParameter("ajan_old_comments"+c);
              
                if(!ajan_old_conductor.equals("")||!ajan_old_birthno.equals("") ){
                    
               conn.st.executeUpdate("update mat_ajtoan  set timestamp='"+mdate+"',  delivery_by='"+ajan_old_conductor +"',birth_no='"+ajan_old_birthno+"' ,discharge_date='"+ajan_old_date+"',baby_status='"+old_status_of_baby+"',comments ='"+ajan_old_comments+"' where tableid='ajan_tableid"+c+"'");
           
                }
                
                }
            catch (SQLException ex) {
                Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      } 
       }
      
      
     //  }//end of if       

       
       
        session.setAttribute("mother_edited", "<font color=\"green\">mother details edited succesfully!</font>");
        
      response.sendRedirect("viewallmothers");  
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    
    
    
    
    //====================random id functions================================ 

 public String uniqueid() {

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        int milsec=cal.get(Calendar.MILLISECOND);
        
        
        return year+""+month+""+date+hour+min+sec+milsec+generateRandomNumber(800, 9000);
    }

 
 
   public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }
 
//==========================================================================
    
    
    
    
    
    
    
    
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
