/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todbase;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
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
public class saveANCRegister extends HttpServlet {

    dbConnect conn = new dbConnect();
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        //========================AN ARRAYLIST TO STORE ALL THE UNIQUE PRIMARY KEYS==========
        
        ArrayList uniqkey= new ArrayList();                                                
       
        String allrows="0"; 
        
        if(request.getParameter("all_rows7_count")!=null){
         allrows = request.getParameter("all_rows7_count"); 
         
         
         
        }
        else{
        
        
        System.out.println("ERROR ========================All rows7 count is null================");
        
        }
         
                int allRows1 = Integer.parseInt(allrows);                                 
       
       
         for(int c=0;c<allRows1;c++){
             
       String New_MotherID = request.getParameter("motherID");
       //get unique key from mother ids hidden field
       if(New_MotherID!=null&&!New_MotherID.equals("")){
           
       uniqkey.add(New_MotherID);
       }
       else{
           //autogenerate random key afresh
                uniqkey.add(uniqueid().trim());
                 System.out.println(c+"___"+uniqueid());
                
       }
               
                }                                                                          
         //==================================================================================       
        
            
         
               Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String mdate;

                    Date mydate = new Date();
                    mdate = formatter.format(mydate);
               // timestamp='"+mdate+"',
         
         
         
         
         
                
          String computername = InetAddress.getLocalHost().getHostName();
        
        try {
            response.setContentType("text/html;charset=UTF-8");


            //==================================DATABASE CONNECTION @ EMMANUELS DBASE===================        

            dbConn econn = new dbConn();


            //======================SECTION 1    FORM VALUES==================
            //======================SECTION 1    FORM VALUES==================


                        String ancRegister = "";
            // =================================================update present pregnancy details==================================================================================================
            String p = "";
            String ancno = "";
            String queryMax = "";

//            queryMax = "select max(ancRegisterID) from anc_register";
//            conn.rs1 = conn.state.executeQuery(queryMax);
//            while (conn.rs1.next()) {
//                ancRegister = conn.rs1.getString(1);
//            }
            if (request.getParameter("ancno") != null && !request.getParameter("ancno").equals("")) {
                ancno = request.getParameter("ancno");
                //          session.setAttribute("ancno",ancno);
            }
            int ancReg = 0;
            if (request.getParameter("ANCRegister1_old_rows") != null && !request.getParameter("ANCRegister1_old_rows").equals("")) {
                p = request.getParameter("ANCRegister1_old_rows");
                ancReg = Integer.parseInt(p);
            }
          

            
            
            System.out.println("ANCRegister1_old_rows " + ancReg);
            for (int i = 1; i <= ancReg; i++) {
                  String VisitDate = "";
            String anc_no = "";
            String FirstVisit = "";
            String No_Visits = "";
            String FName = "";
            String SName = "";
            String LName = "";
            String Village = "";
            String Age = "";
            String MaritalStatus = "";
            String ID = "";
                if (request.getParameter("VisitDate" + i) != null && !request.getParameter("VisitDate" + i).equals("")) {
                    VisitDate = request.getParameter("VisitDate" + i);
                    //                  visitNum = Integer.parseInt(visitNo);
                    System.out.println("VisitDate" + VisitDate);
                }
                if (request.getParameter("anc_no" + i) != null && !request.getParameter("anc_no" + i).equals("")) {
                    anc_no = request.getParameter("anc_no" + i);
                    System.out.println("anc_no__" + anc_no);
                }
                if (request.getParameter("FirstVisit" + i) != null && !request.getParameter("FirstVisit" + i).equals("")) {
                    FirstVisit = request.getParameter("FirstVisit" + i);
                    System.out.println("FirstVisit" + FirstVisit);
                }
                if (request.getParameter("No_Visits" + i) != null && !request.getParameter("No_Visits" + i).equals("")) {
                    No_Visits = request.getParameter("No_Visits" + i);
                    System.out.println("No_Visits" + No_Visits);
                }
                if (request.getParameter("FName" + i) != null && !request.getParameter("FName" + i).equals("")) {
                    FName = request.getParameter("FName" + i);
                    System.out.println("FName" + FName);
                }
                if (request.getParameter("SName" + i) != null && !request.getParameter("SName" + i).equals("")) {
                    SName = request.getParameter("SName" + i);

                    System.out.println("SName" + SName);
                }
                if (request.getParameter("LName" + i) != null && !request.getParameter("LName" + i).equals("")) {
                    LName = request.getParameter("LName" + i);
                    System.out.println("LName" + LName);
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

                System.out.println("MaritalStatus" + MaritalStatus);


                String query = "";
                String query1 = "";
                
                
                System.out.println("_________________array size "+(uniqkey.size()-1)+" Index "+i);
                
                if (i<=(uniqkey.size()))
                {
                    
                    query = "update mother_details set timestamp='"+mdate+"', anc_no='"+anc_no+"', Anc_visit='" + No_Visits + "',Fname='" + FName + "',Sname='" + SName + "',Lname='" + LName + "',Age='" + Age + "',maritalStatus='" + MaritalStatus + "' where motherID='" + uniqkey.get(i-1) + "'";
                    System.out.println("old data_"+query);
                    conn.state.executeUpdate(query);
                    query1 = "update atoh set timestamp='"+mdate+"', DateofVisit='" + VisitDate + "',ancno='" + anc_no + "',FirstVisit='" + FirstVisit + "',Village='" + Village + "' where ancRegisterID='" + ID + "'";
                    System.out.println(query1);
                    conn.state.executeUpdate(query1);

                }

            }

            //=================================== for inserting data for the new prefix in the present pregnancy table==============================
            String newRows = "";
            int newRows1 = 0;
            if (request.getParameter("ANCRegister_newRows") != null && !request.getParameter("ANCRegister_newRows").equals("")) {
                newRows = request.getParameter("ANCRegister_newRows");
                newRows1 = Integer.parseInt(newRows);
            } String New_VisitDate = "";
            String New_anc_no = "";
            String New_FirstVisit = "";
            String New_No_Visits = "";
            String New_FName = "";
            String New_SName = "";
            String New_LName = "";
            String New_Village = "";
            String New_Age = "";
            String New_MaritalStatus = "";
            String New_ID= "";
            String New_MotherID = "";
            String facility = "";
          
            System.out.println("ANCRegister1_new_rows" + newRows1);
            for (int i = 1; i <= newRows1; i++) {                 

                if (request.getParameter("New_VisitDate" + i) != null && !request.getParameter("New_VisitDate" + i).equals("")) {
                    New_VisitDate = request.getParameter("New_VisitDate" + i);
                    //                  visitNum = Integer.parseInt(visitNo);
                    System.out.println("New_VisitDate" + New_VisitDate);
                }
                if (request.getParameter("New_ANCNo" + i) != null && !request.getParameter("New_ANCNo" + i).equals("")) {
                    New_anc_no = request.getParameter("New_ANCNo" + i);
                    System.out.println("New_anc_no" + New_anc_no);
                }
                if (request.getParameter("New_FirstVisit" + i) != null && !request.getParameter("New_FirstVisit" + i).equals("")) {
                    New_FirstVisit = request.getParameter("New_FirstVisit" + i);
                    System.out.println("New_FirstVisit" + New_FirstVisit);
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
                if (request.getParameter("motherID") != null && !request.getParameter("motherID").equals("")) {
                    New_MotherID = request.getParameter("motherID");
                    System.out.println("New_MotherID" + New_MotherID);

                }
                if (request.getParameter("facility") != null && !request.getParameter("facility").equals("")) {
                    facility = request.getParameter("facility");
                    System.out.println("facility" + facility);

                }


                if (request.getParameter("New_MaritalStatus" + i) != null && !request.getParameter("New_MaritalStatus" + i).equals("")) {
                    New_MaritalStatus = request.getParameter("New_MaritalStatus" + i);
                    System.out.println("New_MaritalStatus" + New_MaritalStatus);
                }
               




                String querys = "";
                String querys1 = "";
                try {
                    
                    
                 
                        //               querys = "INSERT INTO anc_register(DateofVisit,ancno,FirstVisit,NoVisits,Fname,Sname,Lname,Village,Age,MaritalStatus)"
                        //                       + "VALUES('"+New_VisitDate+"','"+New_anc_no+"','"+New_FirstVisit+"','"+New_No_Visits+"','"+New_FName+"','"+New_SName+"','"+New_LName+"','"+New_Village+"','"+New_Age+"','"+New_MaritalStatus+"')";
                        //               System.out.println(querys);
                        //                   
                        //               conn.state.executeUpdate(querys);
                       
                       if(New_MotherID!=null&&!New_MotherID.equals("")){
                             if ( !(New_SName.equals("") &&New_LName.equals("") && New_Age.equals("") && New_MaritalStatus.equals("") )) {
                        querys = "update mother_details set timestamp='"+mdate+"', anc_no='"+New_anc_no+"', Anc_visit='" + New_No_Visits + "', Fname='" + New_FName + "',Sname='" + New_SName + "',Lname='" + New_LName + "',Age='" + New_Age + "',maritalStatus='" + New_MaritalStatus + "' where motherID='"+New_MotherID+"'";
                          System.out.println(" new new update "+querys);
                        try {
                            
                            conn.state.executeUpdate(querys);
                            System.out.println(querys);
                        } catch (SQLException a) {

                            System.out.println(a);
                        }
                   
                     if (!New_VisitDate.equals("") &&!New_Village.equals("")) { 
                  String  querysAtoH = "insert into atoh set ancno='" + New_anc_no + "',DateofVisit='" + New_VisitDate + "',FirstVisit='" + New_FirstVisit + "',Village='" + New_Village + "',motherid='"+New_MotherID+"'";

                        try {
                            System.out.println(querysAtoH);
                            conn.state.executeUpdate(querysAtoH);
                        } catch (SQLException B) {
                            System.out.println(B.toString());
                        }
                     }
                             
                             }}
                     else{
                     
                      querys = "insert into mother_details set motherid='"+uniqkey.get(i-1) +"',anc_no='"+New_anc_no+"', Anc_visit='" + New_No_Visits + "',Fname='" + New_FName + "',Sname='" + New_SName + "',Lname='" + New_LName + "',Age='" + New_Age + "',maritalStatus='" + New_MaritalStatus + "',facilityname='"+facility+"',MotherProfileID='"+uniqkey.get(i-1)+"'";
                      System.out.println(querys);
                      
                      
                        try {
                            conn.state.executeUpdate(querys);
                            System.out.println(querys);
                        } catch (SQLException a) {

                            System.out.println(a);
                        }
                 
                     if (!New_VisitDate.equals("") || !New_anc_no.equals("") ||  !New_FirstVisit.equals("") || !New_Village.equals("") ) { 
                  String  querysAtoH = "insert into atoh set ancno='" + New_anc_no + "',DateofVisit='" + New_VisitDate + "',FirstVisit='" + New_FirstVisit + "',Village='" + New_Village + "',motherid='"+uniqkey.get(i-1) +"'";

                        try {
                            System.out.println(querysAtoH);
                            conn.state.executeUpdate(querysAtoH);
                            
                              
                            
                        } catch (SQLException B) {
                            System.out.println(B.toString());
                        }
                     
                     }


                    }
                } catch (Exception EX) {
                    System.out.println("error" + EX.toString());
                }
            }




//---------------END OF SECTION 1---------------------------------- 

// ====================================================for updating i to m Register===========================================
              String rows="";
              int rows1=0;
              
              if(request.getParameter("ItoM_old_rows")!= null && !request.getParameter("ItoM_old_rows").equals("")){
                    rows= request.getParameter("ItoM_old_rows");
                      rows1 = Integer.parseInt(rows);
                  }
              String parity="";
             String gravidae="";
             String LMP="";
             String EDD="";
             String Gestation="";
             String Weight="";
             String BP="";
             String counselledOn[];
             String ItoMID="";
             
            
             System.out.println("rows1-----"+ rows1);
              for(int i=1;i<=rows1;i++) {
                 
              if(request.getParameter("parity"+i)!= null && !request.getParameter("parity"+i).equals("")){
                  parity= request.getParameter("parity"+i);
//                  visitNum = Integer.parseInt(visitNo);
               System.out.println("parity"+parity);    
              }
              else{
              parity="";
              }
              if(request.getParameter("gravidae"+i)!= null && !request.getParameter("gravidae"+i).equals("")){
                  gravidae= request.getParameter("gravidae"+i);
                  System.out.println("gravidae"+gravidae); }
              
              else{
              
              gravidae="";
              }
              if(request.getParameter("LMP"+i)!= null && !request.getParameter("LMP"+i).equals("")){
                  LMP= request.getParameter("LMP"+i);
                  System.out.println("LMP"+LMP);}
              if(request.getParameter("EDD"+i)!= null && !request.getParameter("EDD"+i).equals("")){
                  EDD= request.getParameter("EDD"+i);
                  System.out.println("EDD"+EDD);
                  }
              if(request.getParameter("Gestation"+i)!= null && !request.getParameter("Gestation"+i).equals("")){
                  Gestation= request.getParameter("Gestation"+i);
                   System.out.println("Gestation"+Gestation);
                  }
              if(request.getParameter("Weight"+i)!= null && !request.getParameter("Weight"+i).equals("")){
                  Weight= request.getParameter("Weight"+i);
                  
                   System.out.println("Weight"+Weight);
                  }
              if(request.getParameter("BP"+i)!= null && !request.getParameter("BP"+i).equals("")){
                  BP= request.getParameter("BP"+i);
                   System.out.println("BP"+BP);
                  }
              String Counselled="";
              if(request.getParameterValues("counselledOn"+i)!= null ){
                  counselledOn= request.getParameterValues("counselledOn"+i);
                    System.out.println("counselledOn"+counselledOn);
          for(int y=0;y<counselledOn.length; y++){
           Counselled+=counselledOn[y]+"_";
            System.out.println(Counselled);
            System.out.println(counselledOn[y]);
           }
                  }
              if(request.getParameter("ItoMID"+i)!= null && !request.getParameter("ItoMID"+i).equals("")){
                  ItoMID= request.getParameter("ItoMID"+i);
                    System.out.println("ItoMID"+ItoMID);
                  }
             
            
              
               String query="";
               String query2="";
               String query3="";
               
               
              if(!parity.equals("")&&!gravidae.equals("")&&gravidae!=null ){
                  if(i<uniqkey.size()){
               query = "update mother_details set timestamp='"+mdate+"', parity='"+parity+"',Gravida='"+gravidae+"' where motherID='"+uniqkey.get(i-1)+"'";
                conn.state.executeUpdate(query);
                  }
               System.out.println(query);
              }
              if( LMP!=null && !LMP.equals("")&& !EDD.equals("") && EDD!=null){
                  if(i<uniqkey.size()){
               query2= "update maternal_details set timestamp='"+mdate+"', lmp='"+LMP+"',edd='"+EDD+"', ancno='"+ancno+"' where motherid='"+uniqkey.get(i-1) +"'";
                    conn.state.executeUpdate(query2);
                   System.out.println(query2);
                  }
              }
              if(!Gestation.equals("")|| !Weight.equals("")|| !BP.equals("")|| !Counselled.equals("")){
             query3 = "update itop set timestamp='"+mdate+"', Gestation='"+Gestation+"',Weight='"+Weight+"',BloodPressure='"+BP+"',counselledOn='"+Counselled+"' where ancRegisterID='"+ItoMID+"'";
               conn.state.executeUpdate(query3); 
             System.out.println(query3);
             
              }
              
              }
              String ItoM="";
              int ItoMRows=0;
              if(request.getParameter("ItoM")!= null && !request.getParameter("ItoM").equals("")){
                    ItoM= request.getParameter("ItoM");
                      ItoMRows = Integer.parseInt(ItoM);
                  }
             
      
            
            // System.out.println("ItoMRows-----"+ ItoMRows);
              for(int i=1;i<=ItoMRows;i++) {
             String New_parity="";
             String New_gravidae="";
             String New_LMP="";
             String New_EDD="";
             String New_Gestation="";
             String New_Weight="";
             String New_BP="";
             String New_counselledOn[];
              if (request.getParameter("New_ANCNo" + i) != null && !request.getParameter("New_ANCNo" + i).equals("")) {
                    New_anc_no = request.getParameter("New_ANCNo" + i);
                    System.out.println("New_anc_no" + New_anc_no);
                }
              if(request.getParameter("New_parity"+i)!= null && !request.getParameter("New_parity"+i).equals("")){
                  New_parity= request.getParameter("New_parity"+i);
//                  visitNum = Integer.parseInt(visitNo);
                 System.out.println("New_parity "+New_parity); 
              }
              if(request.getParameter("New_gravidae"+i)!=null && !request.getParameter("New_gravidae"+i).equals("")){
                  New_gravidae= request.getParameter("New_gravidae"+i);
                  System.out.println("New_gravidae  "+New_gravidae); 
              
              }
              if(request.getParameter("New_LMP"+i)!= null && !request.getParameter("New_LMP"+i).equals("")){
                  New_LMP= request.getParameter("New_LMP"+i);
                  System.out.println("New_LMP"+New_LMP);}
              if(request.getParameter("New_EDD"+i)!= null && !request.getParameter("New_EDD"+i).equals("")){
                  New_EDD= request.getParameter("New_EDD"+i);
                  System.out.println("New_EDD"+New_EDD);
                  }
              if(request.getParameter("New_Gestation"+i)!= null && !request.getParameter("New_Gestation"+i).equals("")){
                  New_Gestation= request.getParameter("New_Gestation"+i);
                   System.out.println("New_Gestation"+New_Gestation);
                  }
              if(request.getParameter("New_Weight"+i)!= null && !request.getParameter("New_Weight"+i).equals("")){
                  New_Weight= request.getParameter("New_Weight"+i);
                  
                   System.out.println("New_Weight"+New_Weight);
                  }
              if(request.getParameter("New_BP"+i)!= null && !request.getParameter("New_BP"+i).equals("")){
                  New_BP= request.getParameter("New_BP"+i);
                   System.out.println("New_BP"+New_BP);
                  }
//             
               if (request.getParameter("motherID") != null && !request.getParameter("motherID").equals("")) {
                    New_MotherID = request.getParameter("motherID");
                    System.out.println("New_MotherID" + New_MotherID);

                }
               String NewCounselled="";
              if(request.getParameterValues("New_counselledOn"+i)!= null ){
                  New_counselledOn= request.getParameterValues("New_counselledOn"+i);
                    System.out.println("New_counselledOn"+New_counselledOn);
          for(int y=0;y<New_counselledOn.length; y++){
           NewCounselled+=New_counselledOn[y]+"_";
            System.out.println(NewCounselled);
            System.out.println(New_counselledOn[y]);
           }
              }
              
              
              
              String Gest=" ";
            
              
               String queryItoM="";
               String queryItoM1="";
               String queryItoM2="";
//             if(!New_Gestation.equals("") && New_Gestation!=null  && !NewCounselled.equals("")&& NewCounselled!=null){
               if(New_MotherID!=null && !New_MotherID.equals("")){
                 
                 queryItoM = "insert into itop set Gestation='"+New_Gestation+"',Weight='"+New_Weight+"',BloodPressure='"+New_BP+"',counselledOn='"+NewCounselled+"',ancno='"+New_anc_no+"',motherid='"+New_MotherID+"'";
                 
                 
                 if(!New_BP.equals("")){ 
                 System.out.println("queryItoM   " +queryItoM);
                  
                  
                  conn.state.executeUpdate(queryItoM); 
                 }
            
                  
//                    }
                    if(!New_parity.equals("")&&!New_gravidae.equals("")){
               
                   queryItoM1 = "update mother_details set timestamp='"+mdate+"', parity='"+New_parity+"',Gravida='"+New_gravidae+"' WHERE motherID='"+New_MotherID+"'";
                   conn.state.executeUpdate(queryItoM1); 
                   System.out.println(queryItoM1);}
                    if( !New_LMP.equals("") || !New_EDD.equals("")){
                 
                   queryItoM2 = "insert into maternal_details set ancno='"+New_anc_no+"',lmp='"+New_LMP+"',edd='"+New_EDD+"',institution='',motherid='"+New_MotherID+"'";
                  conn.state.executeUpdate(queryItoM2); 
                   System.out.println(queryItoM2);}
                    
                  }
              else{
                   
                     queryItoM = "insert into itop set Gestation='"+New_Gestation+"',Weight='"+New_Weight+"',BloodPressure='"+New_BP+"',counselledOn='"+NewCounselled+"',ancno='"+New_anc_no+"',motherid='"+uniqkey.get(i-1)+"'";
                 
                 
                  System.out.println("queryItoM" +queryItoM);
                  conn.state.executeUpdate(queryItoM); 
            
                  
//                    }
                    if( !New_parity.equals("") && New_parity!=null && !New_gravidae.equals("") && New_gravidae!=null ){
               
                   queryItoM1 = "update mother_details set timestamp='"+mdate+"', parity='"+New_parity+"',Gravida='"+New_gravidae+"' WHERE motherID='"+uniqkey.get(i-1)+"'";
                   conn.state.executeUpdate(queryItoM1); 
                   System.out.println(queryItoM1);}
                    if( !New_LMP.equals("") || !New_EDD.equals("")  ){
                 
                   queryItoM2 = "insert into maternal_details set ancno='"+New_anc_no+"',lmp='"+New_LMP+"',edd='"+New_EDD+"',institution='',motherid='"+uniqkey.get(i-1)+"'";
                  conn.state.executeUpdate(queryItoM2); 
                   System.out.println(queryItoM2);
                    
                    }
               }
    
    
}
                  
                  //queryItoM = "UPDATE INTO  anc_register(ancno,Gestation,Weight,BloodPressure,counselledOn) "
//                       + "VALUES('"+ancno+"','"+New_Gestation+"','"+New_Weight+"','"+New_BP+"','"+New_counselledOn+"')";
//               System.out.println(queryItoM);
//                    conn.state.executeUpdate(queryItoM);
               
                    
              
              
              
              
              
               String QtoW="";
              int QtoWrows=0;
              
              if(request.getParameter("QtoWRegister_old_rows")!= null && !request.getParameter("QtoWRegister_old_rows").equals("")){
                    QtoW= request.getParameter("QtoWRegister_old_rows");
                      QtoWrows = Integer.parseInt(QtoW);
                  }
             
            
            
            
             System.out.println("rows1-----"+ rows1);
              for(int i=1;i<=rows1;i++) {
                   String haemoglobin="";
             String RPR="";
             String HIVInit="";
             String HIVRetest="";
             String WHO="";
             String CD4="";
             String ARTStart="";
             String QtoWID="";
              if(request.getParameter("haemoglobin"+i)!= null && !request.getParameter("haemoglobin"+i).equals("")){
                  haemoglobin= request.getParameter("haemoglobin"+i);
//                  visitNum = Integer.parseInt(visitNo);
               System.out.println("haemoglobin"+haemoglobin);    
              }
              if(request.getParameter("RPR"+i)!= null && !request.getParameter("RPR"+i).equals("")){
                  RPR= request.getParameter("RPR"+i);
                  System.out.println("RPR"+RPR); }
              if(request.getParameter("HIVInit"+i)!= null && !request.getParameter("HIVInit"+i).equals("")){
                  HIVInit= request.getParameter("HIVInit"+i);
                  System.out.println("HIVInit"+HIVInit);}
              if(request.getParameter("HIVRetest"+i)!= null && !request.getParameter("HIVRetest"+i).equals("")){
                  HIVRetest= request.getParameter("HIVRetest"+i);
                  System.out.println("HIVRetest"+HIVRetest);
                  }
              if(request.getParameter("ARTStart"+i)!= null && !request.getParameter("ARTStart"+i).equals("")){
                  ARTStart= request.getParameter("ARTStart"+i);
                   System.out.println("ARTStart"+ARTStart);
                  }
              if(request.getParameter("WHO"+i)!= null && !request.getParameter("WHO"+i).equals("")){
                  WHO= request.getParameter("WHO"+i);
                  
                   System.out.println("WHO"+WHO);
                  }
              if(request.getParameter("CD4"+i)!= null && !request.getParameter("CD4"+i).equals("")){
                  CD4= request.getParameter("CD4"+i);
                   System.out.println("CD4"+CD4);
                  }
             
              if(request.getParameter("QtoWID"+i)!= null && !request.getParameter("QtoWID"+i).equals("")){
                  QtoWID= request.getParameter("QtoWID"+i);
                    System.out.println("QtoWID"+QtoWID);
                  }
             
            
              
            
               String querys3="";
              if(!haemoglobin.equals("") || !RPR.equals("") || !HIVInit.equals("") || !HIVRetest.equals("")  ||
                      !WHO.equals("") || !CD4.equals("") || !ARTStart.equals("") ){
              
                  
             querys3 = "update qtow set timestamp='"+mdate+"', Haemoglobin='"+haemoglobin+"',RPR='"+RPR+"',HIVInit='"+HIVInit+"',HIVRetest='"+HIVRetest+"',WHOStage='"+WHO+"',ARTCD4='"+CD4+"',ARTStart='"+ARTStart+"' where ancRegisterID='"+QtoWID+"'";
               conn.state.executeUpdate(querys3); 
             System.out.println(querys3);
              }
              
              }
              
              
              
                String QtoW1="";
              int QtoWrows1=0;
              
              if(request.getParameter("QtoWRegister")!= null && !request.getParameter("QtoWRegister").equals("")){
                    QtoW1= request.getParameter("QtoWRegister");
                      QtoWrows1 = Integer.parseInt(QtoW1);
                  }
            
           
              
            
             System.out.println("QtoWrows1-------"+ QtoWrows1);
              for(int i=1;i<=QtoWrows1;i++) {
                  String New_haemoglobin="";
             String New_RPR="";
             String New_HIVInit="";
             String New_HIVRetest="";
             String New_WHO="";
             String New_CD4="";
             String New_ARTStart="";
            
               if (request.getParameter("New_ANCNo" + i) != null && !request.getParameter("New_ANCNo" + i).equals("")) {
                    New_anc_no = request.getParameter("New_ANCNo" + i);
                    System.out.println("New_anc_no" + New_anc_no);
                }
              if(request.getParameter("New_haemoglobin"+i)!= null && !request.getParameter("New_haemoglobin"+i).equals("")){
                  New_haemoglobin= request.getParameter("New_haemoglobin"+i);
//                  visitNum = Integer.parseInt(visitNo);
               System.out.println("New_haemoglobin"+New_haemoglobin);    
              }
              if(request.getParameter("New_RPR"+i)!= null && !request.getParameter("New_RPR"+i).equals("")){
                  New_RPR= request.getParameter("New_RPR"+i);
                  System.out.println("New_RPR"+New_RPR); }
              if(request.getParameter("New_HIVInit"+i)!= null && !request.getParameter("New_HIVInit"+i).equals("")){
                  New_HIVInit= request.getParameter("New_HIVInit"+i);
                  System.out.println("New_HIVInit"+New_HIVInit);}
              if(request.getParameter("New_HIVRetest"+i)!= null && !request.getParameter("New_HIVRetest"+i).equals("")){
                  New_HIVRetest= request.getParameter("New_HIVRetest"+i);
                  System.out.println("New_HIVRetest"+New_HIVRetest);
                  }
              if(request.getParameter("New_ARTStart"+i)!= null && !request.getParameter("New_ARTStart"+i).equals("")){
                  New_ARTStart= request.getParameter("New_ARTStart"+i);
                   System.out.println("New_ARTStart"+New_ARTStart);
                  }
              if(request.getParameter("New_WHO"+i)!= null && !request.getParameter("New_WHO"+i).equals("")){
                  New_WHO= request.getParameter("New_WHO"+i);
                  
                   System.out.println("New_WHO"+New_WHO);
                  }
              if(request.getParameter("New_CD4"+i)!= null && !request.getParameter("New_CD4"+i).equals("")){
                  New_CD4= request.getParameter("New_CD4"+i);
                   System.out.println("New_CD4"+New_CD4);
                  }
             if (request.getParameter("motherID") != null && !request.getParameter("motherID").equals("")) {
                    New_MotherID = request.getParameter("motherID");
                    System.out.println("New_MotherID" + New_MotherID);

                }
             
//              if(request.getParameter("QtoWID"+i)!= null && request.getParameter("QtoWID"+i)!=""){
//                  QtoWID= request.getParameter("QtoWID"+i);
//                    System.out.println("QtoWID"+QtoWID);
//                  }
             
              String querys3="";
              
            if(New_MotherID!=null && !New_MotherID.equals("")){
             
              if(!New_haemoglobin.equals("") || !New_RPR.equals("") || !New_HIVInit.equals("") || !New_HIVRetest.equals("") ||
                      !New_WHO.equals("") || !New_CD4.equals("")|| !New_ARTStart.equals("") ){
              
                 
             querys3 = "insert into qtow set ancno='"+New_anc_no+"',Haemoglobin='"+New_haemoglobin+"',RPR='"+New_RPR+"',HIVInit='"+New_HIVInit+"',HIVRetest='"+New_HIVRetest+"',WHOStage='"+New_WHO+"',ARTCD4='"+New_CD4+"',ARTStart='"+New_ARTStart+"',motherid='"+New_MotherID+"'";
               conn.state.executeUpdate(querys3); 
             System.out.println(querys3);
              }
               } 
              else{
  if(!New_haemoglobin.equals("") || !New_RPR.equals("") || !New_HIVInit.equals("") && !New_HIVRetest.equals("") ||
                      !New_WHO.equals("") || !New_CD4.equals("")|| !New_ARTStart.equals("")){
              
                 
             querys3 = "insert into qtow set ancno='"+New_anc_no+"',Haemoglobin='"+New_haemoglobin+"',RPR='"+New_RPR+"',HIVInit='"+New_HIVInit+"',HIVRetest='"+New_HIVRetest+"',WHOStage='"+New_WHO+"',ARTCD4='"+New_CD4+"',ARTStart='"+New_ARTStart+"',motherid='"+uniqkey.get(i-1)+"'";
               conn.state.executeUpdate(querys3); 
             System.out.println(querys3);
              }

}
            
              }
            
            
            
            
    
            
            

//=============================================================================================================================================================

            //===============SECTION 4 FORM VALUES===============================

            String ANC_NO, CTX, MotherNVP, MotherAZT, MotherHAART, BabyNVP, TbScreen, cc_results;
            String[] Cc_methods;
            String old_step5_no_rows, new_step5_no_rows;

            new_step5_no_rows = request.getParameter("new_step5_no_rows");
            old_step5_no_rows = request.getParameter("old_step5_no_rows");

            ANC_NO = request.getParameter("ancno");
            //=======save the fresh values
            if (new_step5_no_rows != null) {
                for (int q = 1; q <= Integer.parseInt(new_step5_no_rows); q++) {
                    try {
                        //all methods    
                        String allmethods = "";

                        CTX = request.getParameter("CTX" + q);
                        MotherNVP = request.getParameter("NVP" + q);
                        MotherAZT = request.getParameter("AZT" + q);
                        MotherHAART = request.getParameter("HAART" + q);
                        BabyNVP = request.getParameter("BABYNVP" + q);
                        TbScreen = request.getParameter("TB" + q);
                        Cc_methods = request.getParameterValues("Test" + q);
                        
                         if (request.getParameter("New_ANCNo" + q) != null && !request.getParameter("New_ANCNo" + q).equals("")) {
New_anc_no = request.getParameter("New_ANCNo" + q);
System.out.println("New_anc_no" + New_anc_no);
                                }
                        
                        if (Cc_methods != null) {
                            for (int x = 0; x < Cc_methods.length; x++) {

                                allmethods += Cc_methods[x] + ",";
                            }
                        }
                        cc_results = request.getParameter("Result" + q);

                        String qr="insert into xtoad set CTX='" + CTX + "' ,motherid='"+uniqkey.get(q-1)+"',MotherNVP='" + MotherNVP + "',MotherAZT='" + MotherAZT + "',MotherHAART='" + MotherHAART + "',BabyNVP='" + BabyNVP + "',TbScreen='" + TbScreen + "',Cc_methods='" + allmethods + "' ,cc_results ='" + cc_results + "', ancno='" + New_anc_no + "'";
                        
                        if (!TbScreen.equals("") || !CTX.equals("") || !MotherNVP.equals("") || !MotherAZT.equals("") || !MotherHAART.equals("") || !BabyNVP.equals("") ||  !cc_results.equals("")) {


                            econn.st.executeUpdate(qr);
                            
                            System.out.println(qr);

                        }

                    } catch (SQLException ex) {

                        Logger.getLogger(saveANCRegister.class.getName()).log(Level.SEVERE, null, ex);

                    }





                }

            }//end of if
            //===perform an update

            if (old_step5_no_rows != null) {
                for (int r = 1; r <= Integer.parseInt(old_step5_no_rows); r++) {
                    try {
                        //all methods    
                        String allmethods = "", ANCREGID;

                        ANCREGID = request.getParameter("ANCREGID" + r);
                        CTX = request.getParameter("old_CTX" + r);
                        MotherNVP = request.getParameter("old_NVP" + r);
                        MotherAZT = request.getParameter("old_AZT" + r);
                        MotherHAART = request.getParameter("old_HAART" + r);
                        BabyNVP = request.getParameter("old_BABYNVP" + r);
                        TbScreen = request.getParameter("old_TB" + r);
                        Cc_methods = request.getParameterValues("old_Test" + r);
                        
                        
  


                        if (Cc_methods != null) {

                            for (int a = 0; a < Cc_methods.length; a++) {

                                allmethods += Cc_methods[a] + ",";

                            }
                        }

                        //System.out.println("allmethids:"+allmethods);            

                        cc_results = request.getParameter("old_Result" + r);

                        if (TbScreen != null && CTX != null && MotherNVP != null && MotherAZT != null && MotherHAART != null && BabyNVP != null && allmethods != null && !cc_results.equals("")) {

                            econn.st.executeUpdate("update xtoad set timestamp='"+mdate+"', CTX='" + CTX + "',MotherNVP='" + MotherNVP + "',MotherAZT='" + MotherAZT + "',MotherHAART='" + MotherHAART + "',BabyNVP='" + BabyNVP + "',TbScreen='" + TbScreen + "',Cc_methods='" + allmethods + "' , cc_results ='" + cc_results + "' where ancRegisterID='" + ANCREGID + "'");
                        }


                    } catch (SQLException ex) {

                        Logger.getLogger(saveANCRegister.class.getName()).log(Level.SEVERE, null, ex);

                    }





                }
            }//end of if

            //---------------------------------------------------------------        


//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\--------------------------------------------------------------///////////////////////////////////////////////////////////




            //===========================SECTION 6 DETAILS===============================

            //........save existing documents..
            String old_step6_no_rows, new_step6_no_rows;

            old_step6_no_rows = request.getParameter("old_step6_no_rows");
            new_step6_no_rows = request.getParameter("new_step6_no_rows");



            if (old_step6_no_rows != null) {
                for (int s = 1; s <= Integer.parseInt(old_step6_no_rows); s++) {
                    try {
                        String old_other_conditions[], old_others, REGID;
                        String joined_conditions = "";

                        String deworming = "";
                        String ipt = "";
                        String iptarr[];
                        String ttdosearr[];
                        String ttdose = "";
                        String iron = "";
                        String folic = "";
                        String itn = "";                        
                        
                        
                           deworming=request.getParameter("old_deworming"+s);
                           //=====arrays
                        iptarr=request.getParameterValues("old_IPT"+s);
                        ttdosearr=request.getParameterValues("old_tt_dose"+s);
                        
                        if(iptarr!=null){
                         for (int a = 0; a < iptarr.length; a++) {
                                ipt+= iptarr[a] + ",";

                            }
                        }
                         
                        if(ttdosearr!=null){
                         for (int a = 0; a < ttdosearr.length; a++) {
                                ttdose += ttdosearr[a] + ",";
                            }
                        }
                         
                        iron=request.getParameter("old_iron"+s);
                        folic=request.getParameter("old_folic_acid"+s);
                        itn=request.getParameter("old_ITN"+s);
                        old_other_conditions = request.getParameterValues("old_other_conditions" + s);
                        //get the conditions from the multiple select into one string

                        if (old_other_conditions != null) {
                            for (int a = 0; a < old_other_conditions.length; a++) {
                                joined_conditions += old_other_conditions[a] + ",";

                            }

                        }

                        //old others input field    
                        old_others = request.getParameter("old_others" + s);
                        if (old_others == null) {
                            old_others = "";
                        }

                        REGID = request.getParameter("anc_regid" + s);
                        //update table where the anc register is appended into a hidden field    
                        if (joined_conditions != null || (!old_others.equals("") && old_others != null)) {
                            
                            econn.st_6.executeUpdate("Update aetoak set timestamp='"+mdate+"', ConditionID='" + joined_conditions + "' ,others_section_6='" + old_others + "' , deworming='"+deworming+"' , ancno='" + ANC_NO + "',ipt='"+ipt+"',ttdose='"+ttdose+"',iron='"+iron+"',folic='"+folic+"',itn='"+itn+"' where ancRegisterID='" + REGID + "'");
                        
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(saveANCRegister.class.getName()).log(Level.SEVERE, null, ex);
                    }


                }//end of for
            }//end of if

            //====save new values afresh======

//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\--------------------------------------------------------------///////////////////////////////////////////////////////////



            if (new_step6_no_rows != null) {
                for (int t = 1; t <= Integer.parseInt(new_step6_no_rows); t++) {
                    try {
                        String old_other_conditions[], old_others, REGID;
                        String joined_conditions = "";
                        String deworming = "";
                               String iptarr[]=null;
                        String ttdosearr[]=null;
                        String ipt = "";
                        String ttdose = "";
                        String iron = "";
                        String folic = "";
                        String itn = "";

                        //since the anc table is one, we wil be updating the minimum row whose conditions id contains values null and the anc no is the one entered.


                        //                String minimum_anc_reg="";
                        //                econn.rs3=econn.st3.executeQuery("SELECT MIN(ancRegisterID) AS myregid FROM anc_register where ConditionID='NULL' and others_section_6='NULL'");
                        //                econn.rs3.next();
                        //                minimum_anc_reg=econn.rs3.getString(1);
                        //                econn.rs3.close();

                        //System.out.println("min:"+minimum_anc_reg);

                        old_other_conditions = request.getParameterValues("other_conditions" + t);
                        
                        deworming=request.getParameter("deworming"+t);
                        iptarr=request.getParameterValues("IPT"+t);
                        ttdosearr=request.getParameterValues("tt_dose"+t);
                        iron=request.getParameter("iron"+t);
                        folic=request.getParameter("folic_acid"+t);
                        itn=request.getParameter("ITN"+t);
                        
                        
                         if (request.getParameter("New_ANCNo" + t) != null && !request.getParameter("New_ANCNo" + t).equals("")) {
New_anc_no = request.getParameter("New_ANCNo" + t);
System.out.println("New_anc_no" + New_anc_no);
                                }
                        
                        //get the conditions from the multiple select into one string

                        if (old_other_conditions != null) {
                            for (int a = 0; a < old_other_conditions.length; a++) {
                                joined_conditions += old_other_conditions[a] + ",";

                            }

                        }
                        
                        if(iptarr!=null){
                         for (int a = 0; a < iptarr.length; a++) {
                                ipt += iptarr[a] + ",";

                            }
                        }
                         
                        
                        
                        if(ttdosearr!=null){
                          for (int a = 0; a < ttdosearr.length; a++) {
                                ttdose += ttdosearr[a] + ",";

                            }
                        }
                        //old others input field    
                        old_others = request.getParameter("others" + t);
                        if (old_others == null) {
                            old_others = "";
                        }

                        REGID = request.getParameter("anc_regid" + t);
                        //update table where the anc register is appended into a hidden field    

                        if ( !old_others.equals("") || !deworming.equals("") || !ipt.equals("")|| !ttdose.equals("") ||!iron.equals("")||! folic.equals("")||!itn.equals("")) {
                            econn.st_6.executeUpdate("insert into aetoak set ConditionID='" + joined_conditions + "',motherid='"+uniqkey.get(t-1)+"' ,others_section_6='" + old_others + "', deworming='"+deworming+"' , ancno='" + New_anc_no + "',ipt='"+ipt+"',ttdose='"+ttdose+"',iron='"+iron+"',folic='"+folic+"',itn='"+itn+"'");

                            
                                     if (session.getAttribute("userid") != null) {
         Date dat= new Date();
         String inserter = "insert into audit set host_comp='" + computername + "' , action='inserted details for a mother whose anc no is "+ANC_NO+" using the ANC REGISTER',time='" + dat + "',actor_id='" + session.getAttribute("userid") + "'";

                    econn.st3.executeUpdate(inserter);
                
            }
                            
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(saveANCRegister.class.getName()).log(Level.SEVERE, null, ex);
                    }


                }
            }//end of if





            //-------------------------end of section 6---------------------------


//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\--------------------------------------------------------------///////////////////////////////////////////////////////////


            //==========================SECTION 7 DETAILS===================================


            //==========================SECTION 7 DETAILS===================================
            String old_step7_no_rows, new_step7_no_rows;

            old_step7_no_rows = request.getParameter("old_step7_no_rows");
            new_step7_no_rows = request.getParameter("new_step7_no_rows");



            if (old_step7_no_rows != null) {
                for (int u = 1; u <= Integer.parseInt(old_step7_no_rows); u++) {
                    try {
                        String old_other_conditions[], old_others, old_test_results, old_reffered, old_remarks, REGID;
                        String joined_conditions = "";

                        old_other_conditions = request.getParameterValues("old_adt_treatment" + u);
                        //get the conditions from the multiple select into one string

                        if (old_other_conditions != null) {
                            for (int a = 0; a < old_other_conditions.length; a++) {

                                joined_conditions += old_other_conditions[a] + ",";

                            }

                        }

                        //old others input field    
                        old_others = request.getParameter("old_others7" + u);
                        if (old_others == null) {
                            old_others = "";
                        }

                        //reg id
                        REGID = request.getParameter("anc_regid7" + u);
   
                        String couplecounselled=request.getParameter("old_coupleconselled"+u);
                        //test results
                        old_test_results = request.getParameter("old_test_results" + u);

                        //reffered
                        old_reffered = request.getParameter("old_reffered" + u);


                        //remarks
                        old_remarks = request.getParameter("old_remarks" + u);



                        //update table where the anc register is appended into a hidden field  

                        if ( !(old_others.equals("")  && old_test_results.equals("")&&old_reffered.equals("")&&old_remarks.equals(""))) {


                            econn.st_6.executeUpdate("Update altoan set timestamp='"+mdate+"', AdditionalTreatment='" + joined_conditions + "' , others_section_7='" + old_others + "', PartnerResults='" + old_test_results + "' , couselled='"+couplecounselled+"' ,reffered='" + old_reffered + "', remarks='" + old_remarks + "' where ancRegisterID='" + REGID + "'");


                        }

                    } catch (SQLException ex) {

                        Logger.getLogger(saveANCRegister.class.getName()).log(Level.SEVERE, null, ex);

                    }


                }//end of for
            }//end of if

            //save new values...



            if (new_step7_no_rows != null) {
                for (int v = 1; v <= Integer.parseInt(new_step7_no_rows); v++) {
                    try {

                        String old_other_conditions[], old_others, old_test_results="", old_reffered="", old_remarks="", REGID;
                        String joined_conditions = "";

                        String minimum_anc_reg = "";

                        //                econn.rs3=econn.st3.executeQuery("SELECT MIN(ancRegisterID) AS myregid FROM anc_register where PartnerResults='NULL' and others_section_7='NULL' and AdditionalTreatment='NULL'");
                        //                econn.rs3.next();
                        //                minimum_anc_reg=econn.rs3.getString(1);
                        //                econn.rs3.close();
                        //                

                        old_other_conditions = request.getParameterValues("adt_treatment" + v);
                        //get the conditions from the multiple select into one string

                        if (old_other_conditions != null) {

                            for (int a = 0; a < old_other_conditions.length; a++) {

                                joined_conditions += old_other_conditions[a] + ",";

                            }

                        }

                        //old others input field    
                        old_others = request.getParameter("old_others7" + v);
                        if (old_others == null) {
                            old_others = "";
                        }

                        //reg id
                        // REGID=request.getParameter("anc_regid7"+p);

                          if (request.getParameter("New_ANCNo" + v) != null && !request.getParameter("New_ANCNo" + v).equals("")) {
New_anc_no = request.getParameter("New_ANCNo" + v);
System.out.println("New_anc_no" + New_anc_no);
                                }
                        
                        
                        
                            String couplecounselled="";
                                    
                            
                            if(request.getParameter("coupleconselled"+v)!=null){
                                    couplecounselled=request.getParameter("coupleconselled"+v);
                            }
                        //test results
                        old_test_results = request.getParameter("test_results" + v);

                        //reffered
                        old_reffered = request.getParameter("reffered" + v);


                        
                        //remarks
                        old_remarks = request.getParameter("remarks" + v);


String ins="insert into altoan set AdditionalTreatment='" + joined_conditions + "' , motherid='"+uniqkey.get(v-1)+"', others_section_7='" + old_others + "', couselled='"+couplecounselled+"', PartnerResults='" + old_test_results + "',reffered='" + old_reffered + "', remarks='" + old_remarks + "' , ancno='" + New_anc_no + "' ";
                      


//update table where the anc register is appended into a hidden field    
                        if (!old_test_results.equals("")||!couplecounselled.equals("")||!old_reffered.equals("")||!old_remarks.equals("")){
                            econn.st_6.executeUpdate(ins);
                      
                        System.out.println(ins);
                        }

                        
                    } catch (SQLException ex) {

                        Logger.getLogger(saveANCRegister.class.getName()).log(Level.SEVERE, null, ex);

                    }


                }//end of for
            }//end of if


 session.setAttribute("mother_edited", "<font color=\"green\">mother details edited succesfully!</font>");


            //-------------------------end of section 7 ------------------------------------




            response.sendRedirect("viewallmothers");
        } catch (SQLException ex) {
            Logger.getLogger(saveANCRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(saveANCRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(saveANCRegister.class.getName()).log(Level.SEVERE, null, ex);
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

    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
