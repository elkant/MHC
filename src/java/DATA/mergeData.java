/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import SCRIPTS.UpdateEddMotherDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sender.dbConn;
import SCRIPTS.UpdateEddMotherDetails;

/**
 *
 * @author SIXTYFOURBIT
 */
public class mergeData extends HttpServlet {
HttpSession sess;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        sess=request.getSession();
        
        int allmothers=0;
    int aeak=0;
    int alan=0;
    int ancvisits=0;
    int atoh=0;
    int cervicalscreening=0;
    int delivery=0;
    int deworming=0;
    int hiv_exposure=0;
    int itop=0;
    int maternaldetails=0;
    int motherdetails=0;
    int motherprofile=0;
    int presentpregnacy=0;
    int qtow=0;
    int xtoad=0;
    
    int postaatoae=0;
    int postnataftoai=0;
    int postnatatof=0;
    int postnatgtom=0;
    int postnatntot=0;
    int postnatutoz=0;
        
    int mat_actoai=0;
    int mat_ajtoan=0;
    int mat_atoh=0;
    int mat_htol=0;
    int mat_mtou=0;
    int mat_vtoab=0;
       
        try {
          
    //temporary db connection  
            
      tempdbConn tempconn=new tempdbConn();
      
      //normal dbconn
      
      masterdbConn conn= new masterdbConn();
      
       //read data from the temp table
            String mergeaetoak="select * from aetoak";

            tempconn.rs = tempconn.st.executeQuery(mergeaetoak);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from aetoak where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "' and timestamp='"+tempconn.rs.getTimestamp(12)+"'";
String existancechecker = "select * from aetoak where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "' ";

//System.out.println(existancechecker);


//insert into the master db from the temp db if it doesnt exist

   String insertdata = "replace into aetoak(ancRegisterID,ancno,ConditionID,others_section_6,deworming,ipt,ttdose,iron,folic,itn,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(11)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "','" + tempconn.rs.getString(10) + "','" + tempconn.rs.getString(11) + "','" + tempconn.rs.getTimestamp(12) + "')";

   
   System.out.println(insertdata);
   
   
       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        String upd="update aetoak set ancno='"+tempconn.rs.getString(2)+"',ConditionID='"+tempconn.rs.getString(3)+"',others_section_6='"+tempconn.rs.getString(4)+"',deworming='"+tempconn.rs.getString(5)+"',ipt='"+tempconn.rs.getString(6)+"',ttdose='"+tempconn.rs.getString(7)+"',iron='"+tempconn.rs.getString(8)+"', folic='"+tempconn.rs.getString(9)+"',itn='"+tempconn.rs.getString(10)+"',timestamp='"+tempconn.rs.getTimestamp(12)+"' where ancRegisterID='"+tempconn.rs.getString(1)+tempconn.rs.getString(11)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        aeak++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    aeak++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }

//============================================al to an===========================================================
      
            
           String mergealtoan="select * from altoan";

            tempconn.rs = tempconn.st.executeQuery(mergealtoan);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from altoan where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(9) + "' and timestamp='"+tempconn.rs.getString(10)+"'";
String existancechecker = "select * from altoan where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(9) + "' ";

//insert into the master db from the temp db

   String insertdata = "insert into altoan(ancRegisterID,ancno,PartnerResults,AdditionalTreatment,others_section_7,reffered,remarks,couselled,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(9)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "','" + tempconn.rs.getString(10) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        String upd="update altoan set ancno='"+tempconn.rs.getString(2)+"',PartnerResults='"+tempconn.rs.getString(3)+"',AdditionalTreatment='"+tempconn.rs.getString(4)+"',others_section_7='"+tempconn.rs.getString(5)+"',reffered='"+tempconn.rs.getString(6)+"',remarks='"+tempconn.rs.getString(7)+"',couselled='"+tempconn.rs.getString(8)+"',timestamp='"+tempconn.rs.getString(10)+"' where ancRegisterID='"+tempconn.rs.getString(1)+tempconn.rs.getString(9)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        alan++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    alan++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }
//=============================================ANC VISITS==============================================================    
            
           String mergeancvisits="select * from anc_visits";

            tempconn.rs = tempconn.st.executeQuery(mergeancvisits);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from anc_visits where ancVisitsID='" + tempconn.rs.getString(1)+tempconn.rs.getString(5) + "' and timestamp='"+tempconn.rs.getString(6)+"'";
String existancechecker = "select * from anc_visits where ancVisitsID='" + tempconn.rs.getString(1)+tempconn.rs.getString(5) + "' ";

//insert into the master db from the temp db

   String insertdata = "insert into anc_visits(ancVisitsID,ancNo,visitDate,Status,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(5)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {
              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        
                        
                        String upd="update anc_visits set ancNo='"+tempconn.rs.getString(2)+"',VisitDate='"+tempconn.rs.getString(3)+"' , Status='"+tempconn.rs.getString(4)+"' where ancVisitsID='"+tempconn.rs.getString(1)+tempconn.rs.getString(5)+"' ";
                        
                        
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        ancvisits++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    ancvisits++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }//end of while
            
            
            
            
//===================================================================================================================
            
            
 //============================================a to h===========================================================
      
            
           String mergeatoh="select * from atoh";

            tempconn.rs = tempconn.st.executeQuery(mergeatoh);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
String existancechecker = "select * from atoh where (motherid='" + tempconn.rs.getString(7) + "' ||  ancRegisterID LIKE '%" + tempconn.rs.getString(7) + "' ) and ancno='"+tempconn.rs.getString(2)+"' and Dateofvisit='"+tempconn.rs.getString(3)+"' and FirstVisit='"+tempconn.rs.getString(4)+"' and Village='"+tempconn.rs.getString(5)+"'";
//String existancechecker = "select * from atoh where ancRegisterID='" + conn.rs.getString(1)+conn.rs.getString(7) + "' and timestamp='"+conn.rs.getString(6)+"'";

//insert into the master db from the temp db
//avoid duplication for same mother
String insertdata = "replace into atoh(ancRegisterID,ancno,DateofVisit,FirstVisit,Village,timestamp,motherid) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(7)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "')";

//execute existance checker-uses main db conn
   
   
   System.out.println("___To insert:: " + insertdata);
   System.out.println("___Checker:: " + existancechecker);
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

            if (!conn.rs1.next()) {
                  atoh++;
                    
                    //check for blanks
                    
                    conn.st2.executeUpdate(insertdata);
                    
            
            } 
        
 //String existancechecker1 = "select * from atoh where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(7) + "' and timestamp='"+tempconn.rs.getString(6)+"'";
 String existancechecker1 = "select * from atoh where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(7) + "'";
 
            
        conn.rs1 = conn.st1.executeQuery(existancechecker1);    
            if(conn.rs1.next()){
            
        
      
            
        //check if the timestamps are equal.. otherwise do an update                   
                    
           System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
   if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
   String upd="update atoh set ancno='"+tempconn.rs.getString(2)+"',DateofVisit='"+tempconn.rs.getString(3)+"',FirstVisit='"+tempconn.rs.getString(4)+"',Village='"+tempconn.rs.getString(5)+"' where ancRegisterID='"+tempconn.rs.getString(1)+tempconn.rs.getString(7)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        //atoh++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                                              } 
              //  else if(){}
                else {
                
                
                
                 
                }


            }           
 //=========================================================CERVICAL SCREENING============================================           
            
   String mergecscreening="select * from cervical_screening";

            tempconn.rs = tempconn.st.executeQuery(mergecscreening);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from cervical_screening where screen_id='" + tempconn.rs.getString(1)+tempconn.rs.getString(14) + "' and timestamp='"+tempconn.rs.getString(15)+"'";
String existancechecker = "select * from cervical_screening where screen_id='" + tempconn.rs.getString(1)+tempconn.rs.getString(14) + "'";

//insert into the master db from the temp db

   String insertdata = "insert into cervical_screenng(screen_id,anc_no,date,test_id,cervixDone,results,cancer_suspicious,cryo,leep,others,HSIL,overtCancer,referral,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(14)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "','" + tempconn.rs.getString(10) + "','" + tempconn.rs.getString(11) + "','" + tempconn.rs.getString(12) + "','" + tempconn.rs.getString(13) + "','" + tempconn.rs.getString(14) + "','" + tempconn.rs.getString(15) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {
              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        
                        
                        String upd="update cervical_screening set anc_no='"+tempconn.rs.getString(2)+"',date='"+tempconn.rs.getString(3)+"' , test_id='"+tempconn.rs.getString(4)+"', cervixDone='"+tempconn.rs.getString(5)+"', results='"+tempconn.rs.getString(6)+"', cancer_suspicious='"+tempconn.rs.getString(7)+"', cryo='"+tempconn.rs.getString(8)+"', leep='"+tempconn.rs.getString(9)+"', others='"+tempconn.rs.getString(10)+"', HSIL='"+tempconn.rs.getString(11)+"', overtCancer='"+tempconn.rs.getString(12)+"', referral='"+tempconn.rs.getString(13)+"', timestamp="+tempconn.rs.getString(15)+"' where screen_id='"+tempconn.rs.getString(1)+tempconn.rs.getString(14)+"' ";
                        
                        
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        cervicalscreening++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    cervicalscreening++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }//end of while
            
            
                    
    //====================================================delivery=============================================
            
       
            
            String mergedelivery="select * from delivery";

            tempconn.rs = tempconn.st.executeQuery(mergedelivery);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from delivery where delivery_id='" + tempconn.rs.getString(1)+tempconn.rs.getString(15) + "' and timestamp='"+tempconn.rs.getString(16)+"'";
String existancechecker = "select * from delivery where delivery_id='" + tempconn.rs.getString(1)+tempconn.rs.getString(15) + "' ";

//insert into the master db from the temp db

   String insertdata = "insert into delivery(delivery_id,pregnancy_duration,date,mum_condition,apgar_score_1,apgar_score_5,apgar_score_10,rescuscitation,delivery_place,conductor_id,anc_no,conductor_other,delivery_place_other,delivery_mode,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(15)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "','" + tempconn.rs.getString(10) + "','" + tempconn.rs.getString(11) + "','" + tempconn.rs.getString(12) + "','" + tempconn.rs.getString(13) + "','" + tempconn.rs.getString(14) + "','" + tempconn.rs.getString(15) + "','" + tempconn.rs.getString(16) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {
              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        
                        
                        String upd="update delivery set pregnancy_duration='"+tempconn.rs.getString(2)+"',date='"+tempconn.rs.getString(3)+"' , mum_condition='"+tempconn.rs.getString(4)+"', apgar_score_1='"+tempconn.rs.getString(5)+"', apgar_score_5='"+tempconn.rs.getString(6)+"', apgar_score_10='"+tempconn.rs.getString(7)+"', rescuscitation='"+tempconn.rs.getString(8)+"', delivery_place='"+tempconn.rs.getString(9)+"', conductor_id='"+tempconn.rs.getString(10)+"', anc_no='"+tempconn.rs.getString(11)+"', conductor_other='"+tempconn.rs.getString(12)+"', delivery_place_other='"+tempconn.rs.getString(13)+"',delivery_mode='"+tempconn.rs.getString(14)+"',timestamp='"+tempconn.rs.getString(16)+"' where delivery_id='"+tempconn.rs.getString(1)+tempconn.rs.getString(15)+"' ";
                        
                        
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        delivery++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    delivery++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }//end of while
            
            
            
   //=======================================deworming===============================================
  
            
   String mergedeworming="select * from deworming";

            tempconn.rs = tempconn.st.executeQuery(mergedeworming);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from deworming where deworming_id='" + tempconn.rs.getString(1)+tempconn.rs.getString(7) + "' and timestamp='"+tempconn.rs.getString(8)+"'";
String existancechecker = "select * from deworming where deworming_id='" + tempconn.rs.getString(1)+tempconn.rs.getString(7) + "'";

//insert into the master db from the temp db

   String insertdata = "insert into deworming(deworming_id,age_id,drug,dosage,next_visit,anc_no,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(7)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {
              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        
                        
                        String upd="update deworming set deworming_id='"+tempconn.rs.getString(2)+"',age_id='"+tempconn.rs.getString(3)+"' , drug='"+tempconn.rs.getString(4)+"',dosage='"+tempconn.rs.getString(5)+"', next_visit='"+tempconn.rs.getString(6)+"', anc_no='"+tempconn.rs.getString(7)+"' ,timestamp='" + tempconn.rs.getString(8) + "' where deworming_id='"+tempconn.rs.getString(1)+tempconn.rs.getString(7)+"' ";
                        
                        
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        deworming++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    deworming++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }//end of while
            
            
            
            
//===================================================================================================================
                    
            
  //=============================================ANC VISITS==============================================================    
            
           String mergehiv="select * from hiv_exposure";

            tempconn.rs = tempconn.st.executeQuery(mergehiv);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from hiv_exposure where exposure_id='" + tempconn.rs.getString(1)+tempconn.rs.getString(6) + "' and timestamp='"+tempconn.rs.getString(7)+"'";
String existancechecker = "select * from hiv_exposure where exposure_id='" + tempconn.rs.getString(1)+tempconn.rs.getString(6) + "' ";

//insert into the master db from the temp db

   String insertdata = "insert into hiv_exposure(exposure_id,test_id,anc_no,date_of_sample_collection,results,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(6)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {
              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        
                        
                        String upd="update hiv_exposure set test_id='"+tempconn.rs.getString(2)+"', anc_no='"+tempconn.rs.getString(3)+"' , date_of_sample_collection='"+tempconn.rs.getString(4)+"',results='"+tempconn.rs.getString(5)+"' ,timestamp='" + tempconn.rs.getString(7) + "' where exposure_id='"+tempconn.rs.getString(1)+tempconn.rs.getString(6)+"' ";
                        
                        
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        hiv_exposure++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    hiv_exposure++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }//end of while
            
            
            
            
//===================================================================================================================
          
// ===========================================i to p===========================================================
      
            
           String mergeitop="select * from itop";

            tempconn.rs = tempconn.st.executeQuery(mergeitop);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from itop where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(9) + "' and timestamp='"+tempconn.rs.getString(8)+"'";
String existancechecker = "select * from itop where ancRegisterID='"+tempconn.rs.getString(1)+tempconn.rs.getString(9) + "' ";

//insert into the master db from the temp db

   String insertdata = "insert into itop(ancRegisterID,ancno,Gestation,Weight,BloodPressure,CounselledOn,Haemoglobin,timestamp,motherid) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(9)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        String upd="update itop set Gestation='"+tempconn.rs.getString(3)+"',Weight='"+tempconn.rs.getString(4)+"',BloodPressure='"+tempconn.rs.getString(5)+"',CounselledOn='"+tempconn.rs.getString(6)+"',Haemoglobin='"+tempconn.rs.getString(7)+"',timestamp='"+tempconn.rs.getTimestamp(8)+"' where ancRegisterID='"+tempconn.rs.getString(1)+tempconn.rs.getString(9)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        itop++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    itop++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }
            //======================================================================
                      
// ===========================================maternal_details===========================================================
      
            
           String mergematernaldetails="select * from maternal_details";

            tempconn.rs = tempconn.st.executeQuery(mergematernaldetails);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
String existancechecker = "select * from maternal_details where ancno='" + tempconn.rs.getString(1)+ "' and height='"+tempconn.rs.getString(2)+"' and lmp='"+tempconn.rs.getString(3)+"' and edd='"+tempconn.rs.getString(4)+"' and institution='"+tempconn.rs.getString(5)+"'  and motherid='"+tempconn.rs.getString(7)+"'";

//insert into the master db from the temp db

   String insertdata = "replace into maternal_details(uniqueid,ancno,height,lmp,edd,institution,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(6)+tempconn.rs.getString(7)+"','" + tempconn.rs.getString(1) + "','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getTimestamp(8) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                
                
                
                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        
                        String upd="update maternal_details set height='"+tempconn.rs.getString(2)+"',lmp='"+tempconn.rs.getString(3)+"',edd='"+tempconn.rs.getString(4)+"',institution='"+tempconn.rs.getString(5)+"',timestamp='"+tempconn.rs.getTimestamp(8)+"' where uniqueid='"+tempconn.rs.getString(6)+tempconn.rs.getString(7)+"' ";
                        
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        maternaldetails++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    //maternaldetails++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    
                    System.out.println("INSERTED:: " + insertdata);
                    System.out.println("EXISTING DATA:: " + existancechecker);
                }


            }
            
            
            
            
            //==================================================================================
            
            
                          
// ===========================================motherdetails===========================================================
      
            
           String mergemotherdetails="select * from mother_details";

            tempconn.rs = tempconn.st.executeQuery(mergemotherdetails);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
String existancechecker = "select * from mother_details where motherID like '%" + tempconn.rs.getString(1).trim()+ "%'";

//insert into the master db from the temp db

   String insertdata = "replace into mother_details(motherID,anc_no,DOB,FName,SName,LName,PhoneNo,NOKPhoneNo,LocationID,Gest_to_firstANC,ChwID,Anc_visit,VisitDate,delivery_date,status_id,Age,Gravida,parity,maritalStatus,education,occupation,nok,relationship,nok_consent,languagePreferred,MotherProfileID,facilityname,timestamp) "
                        + "values ('" + tempconn.rs.getString(1) + "','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "','" + tempconn.rs.getString(10) + "','" + tempconn.rs.getString(11) + "','" + tempconn.rs.getString(12) + "','" + tempconn.rs.getString(13) + "','" + tempconn.rs.getString(14) + "','" + tempconn.rs.getString(15) + "','" + tempconn.rs.getString(16) + "','" + tempconn.rs.getString(17) + "','" + tempconn.rs.getString(18) + "','" + tempconn.rs.getString(19) + "','" + tempconn.rs.getString(20) + "','" + tempconn.rs.getString(21) + "','" + tempconn.rs.getString(22) + "','" + tempconn.rs.getString(23) + "','" + tempconn.rs.getString(24) + "','" + tempconn.rs.getString(25) + "','" + tempconn.rs.getString(26) + "','" + tempconn.rs.getString(27) + "','" + tempconn.rs.getString(28) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        
                        String upd="update mother_details set anc_no='"+tempconn.rs.getString(2)+"',DOB='"+tempconn.rs.getString(3)+"',FName='"+tempconn.rs.getString(4)+"',SName='"+tempconn.rs.getString(5)+"',LName='"+tempconn.rs.getString(6)+"',PhoneNo='"+tempconn.rs.getString(7)+"',NOKPhoneNo='"+tempconn.rs.getString(8)+"',LocationID='"+tempconn.rs.getString(9)+"',Gest_to_firstANC='"+tempconn.rs.getString(10)+"', ChwID='"+tempconn.rs.getString(11)+"',Anc_visit='"+tempconn.rs.getString(12)+"',VisitDate='"+tempconn.rs.getString(13)+"',delivery_date='"+tempconn.rs.getString(14)+"',status_id='"+tempconn.rs.getString(15)+"', Age='"+tempconn.rs.getString(16)+"', Gravida='"+tempconn.rs.getString(17)+"',parity='"+tempconn.rs.getString(18)+"',maritalStatus='"+tempconn.rs.getString(19)+"',education='"+tempconn.rs.getString(20)+"',occupation='"+tempconn.rs.getString(21)+"',nok='"+tempconn.rs.getString(22)+"',relationship='"+tempconn.rs.getString(23)+"',nok_consent='"+tempconn.rs.getString(24)+"',languagePreferred='"+tempconn.rs.getString(25)+"',facilityname='"+tempconn.rs.getString(27)+"',timestamp='"+tempconn.rs.getTimestamp(28)+"' where motherID='"+tempconn.rs.getString(1)+"' ";
                        
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        motherdetails++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    motherdetails++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }
            
            
            
            
            //==================================================================================
             
            //=============================================ANC VISITS==============================================================    
            
           String mergepresentpregnancy="select * from present_pregnancy";

            tempconn.rs = tempconn.st.executeQuery(mergepresentpregnancy);

            while (tempconn.rs.next()) {                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from present_pregnancy where PresentPregnancy='" + tempconn.rs.getString(1)+tempconn.rs.getString(7) + "' and timestamp='"+tempconn.rs.getString(8)+"'";
String existancechecker="select * from present_pregnancy where PresentPregnancy like '%" + tempconn.rs.getString(1)+tempconn.rs.getString(7) + "%'";

//insert into the master db from the temp db

   String insertdata = "insert into present_pregnancy(PresentPregnancy,anc_no,visit_no,date,weight,Next_visit,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(7)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {
              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        
                        
                        String upd="update present_pregnancy set anc_no='"+tempconn.rs.getString(2)+"',visit_no='"+tempconn.rs.getString(3)+"' , date='"+tempconn.rs.getString(4)+"',Weight='"+tempconn.rs.getString(5)+"' ,Next_visit='"+tempconn.rs.getString(6)+"' where PresentPregnancy='"+tempconn.rs.getString(1)+tempconn.rs.getString(7)+"' ";
                        
                        
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        presentpregnacy++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    presentpregnacy++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }//end of while
            
            
            
            
//===================================================================================================================

            
      // ===========================================q to w===========================================================
      
            
           String mergeqtow="select * from qtow";

            tempconn.rs = tempconn.st.executeQuery(mergeqtow);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from qtow where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "' and timestamp='"+tempconn.rs.getString(10)+"'";
String existancechecker = "select * from qtow where ancRegisterID like '%" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "%'";

//insert into the master db from the temp db

   String insertdata = "insert into qtow(ancRegisterID,ancno,Haemoglobin,RPR,HIVinit,HIVRetest,WHOStage,ARTCD4,ARTStart,timestamp,motherid) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(11)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "','" + tempconn.rs.getString(10) + "','" + tempconn.rs.getString(11) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        String upd="update qtow set Haemoglobin='"+tempconn.rs.getString(3)+"',RPR='"+tempconn.rs.getString(4)+"',HIVinit='"+tempconn.rs.getString(5)+"',HIVRetest='"+tempconn.rs.getString(6)+"',  WHOStage='"+tempconn.rs.getString(7)+"',ARTCD4='"+tempconn.rs.getString(8)+"',ARTStart='"+tempconn.rs.getString(9)+"', timestamp='"+tempconn.rs.getTimestamp(10)+"'  where ancRegisterID='"+tempconn.rs.getString(1)+tempconn.rs.getString(11)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        itop++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    qtow++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }
            //======================================================================
      
            
            
    
            
            
      // ===========================================x to ad===========================================================
      
            
           String mergextoad="select * from xtoad";

            tempconn.rs = tempconn.st.executeQuery(mergextoad);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from xtoad where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "' and timestamp='"+tempconn.rs.getString(12)+"'";
String existancechecker = "select * from xtoad where ancRegisterID like '%" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "%' ";

//insert into the master db from the temp db

   String insertdata = "insert into xtoad(ancRegisterID,ancno,CTX,MotherNVP,MotherAZT,MotherHAART,BabyNVP,TbScreen,Cc_methods,cc_results,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(11)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "','" + tempconn.rs.getString(10) + "','" + tempconn.rs.getString(11) + "','" + tempconn.rs.getTimestamp(12) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        String upd="update xtoad set CTX='"+tempconn.rs.getString(3)+"',MotherNVP='"+tempconn.rs.getString(4)+"',MotherAZT='"+tempconn.rs.getString(5)+"',MotherHAART='"+tempconn.rs.getString(6)+"',BabyNVP='"+tempconn.rs.getString(7)+"',TbScreen='"+tempconn.rs.getString(8)+"', Cc_methods='"+tempconn.rs.getString(9)+"' ,cc_results='"+tempconn.rs.getString(10)+"',timestamp='"+tempconn.rs.getTimestamp(12)+"'  where ancRegisterID='"+tempconn.rs.getString(1)+tempconn.rs.getString(11)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        xtoad++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    xtoad++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }
            //======================================================================
      
            
     
            
      
   //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
   //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
   // POSTNATAL REGISTER MERGING STARTS HERE
   //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
   //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
     
             // ======================================================================================================
      
            
           String mergepostnataatoae="select * from postnat_aatoae";

            tempconn.rs = tempconn.st.executeQuery(mergepostnataatoae);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from xtoad where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "' and timestamp='"+tempconn.rs.getString(12)+"'";
String existancechecker = "select * from postnat_aatoae where tableid like '%" + tempconn.rs.getString(1)+tempconn.rs.getString(9) + "%' ";

//insert into the master db from the temp db

   String insertdata = "replace into postnat_aatoae(tableid,pncno,counselled,hivtested,hivresults,screenedmethod,screenedresult,modernfp,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(9)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "','" + tempconn.rs.getString(10) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        String upd="update postnat_aatoae set pncno='"+tempconn.rs.getString(2)+"',counselled='"+tempconn.rs.getString(3)+"',hivtested='"+tempconn.rs.getString(4)+"',hivresults='"+tempconn.rs.getString(5)+"',screenedmethod='"+tempconn.rs.getString(6)+"',screenedresult='"+tempconn.rs.getString(7)+"', modernfp='"+tempconn.rs.getString(8)+"' , timestamp='"+tempconn.rs.getTimestamp(10)+"'  where tableid='"+tempconn.rs.getString(1)+tempconn.rs.getString(9)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        postaatoae++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    postaatoae++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }
            //======================================================================
      
     
            
            
             // ======================================================================================================
      
            
           String mergepostnataftoai="select * from postnat_aftoai";

            tempconn.rs = tempconn.st.executeQuery(mergepostnataftoai);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from xtoad where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "' and timestamp='"+tempconn.rs.getString(12)+"'";
String existancechecker = "select * from postnat_aftoai where tableid like '%"+tempconn.rs.getString(1)+tempconn.rs.getString(7)+"%' ";

//insert into the master db from the temp db

   String insertdata = "replace into postnat_aftoai (tableid,pncno,multivitamin,haematinics,referred,remarks,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(7)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        String upd="update postnat_aftoai set pncno='"+tempconn.rs.getString(2)+"',multivitamin='"+tempconn.rs.getString(3)+"',haematinics='"+tempconn.rs.getString(4)+"',referred='"+tempconn.rs.getString(5)+"',remarks='"+tempconn.rs.getString(6)+"' , timestamp='"+tempconn.rs.getTimestamp(8)+"'  where tableid='"+tempconn.rs.getString(1)+tempconn.rs.getString(7)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        postnataftoai++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    postnataftoai++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }
            //======================================================================
      
     
            
        
                   
             // ======================================================================================================
      
            
           String mergepostnatatof="select * from postnat_atof";

            tempconn.rs = tempconn.st.executeQuery(mergepostnatatof);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from xtoad where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "' and timestamp='"+tempconn.rs.getString(12)+"'";
String existancechecker = "select * from postnat_atof where ID like '%" + tempconn.rs.getString(1)+tempconn.rs.getString(10) + "%' ";

//insert into the master db from the temp db

   String insertdata = "replace into postnat_atof(ID,VisitDate,PNCRegNo,AdmNo,FirstName,SecondName,LastName,village,Age,motherid,timestamp,pncno) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(10)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "','" + tempconn.rs.getString(10) + "','" + tempconn.rs.getString(11) + "','" + tempconn.rs.getString(12) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        String upd="update postnat_atof set visitDate='"+tempconn.rs.getString(2)+"',PNCRegNo='"+tempconn.rs.getString(3)+"',AdmNo='"+tempconn.rs.getString(4)+"',FirstName='"+tempconn.rs.getString(5)+"',SecondName='"+tempconn.rs.getString(6)+"',LastName='"+tempconn.rs.getString(7)+"', village='"+tempconn.rs.getString(8)+"' ,Age='"+tempconn.rs.getString(9)+"',pncno='" + tempconn.rs.getString(12) + "', timestamp='"+tempconn.rs.getTimestamp(11)+"'  where ID='"+tempconn.rs.getString(1)+tempconn.rs.getString(10)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        postnatatof++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    postnatatof++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }
            //======================================================================
            
            
            
            
            
            
            
            
             // ======================================================================================================
      
            
           String mergepostnat_gtom="select * from postnat_gtom";

            tempconn.rs = tempconn.st.executeQuery(mergepostnat_gtom);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from xtoad where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "' and timestamp='"+tempconn.rs.getString(12)+"'";
String existancechecker = "select * from postnat_gtom where ID like '%" + tempconn.rs.getString(1)+tempconn.rs.getString(9) + "%' ";

//insert into the master db from the temp db

   String insertdata = "replace into postnat_gtom(ID,DeliveryDate,DeliveryPlace,DeliveryMode,BabyState,Temp,Pulse,BP,motherid,timestamp,pncno) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(9)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "','" + tempconn.rs.getString(10) + "','" + tempconn.rs.getString(11) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        String upd="update postnat_gtom set DeliveryDate='"+tempconn.rs.getString(2)+"',DeliveryPlace='"+tempconn.rs.getString(3)+"',DeliveryMode='"+tempconn.rs.getString(4)+"',BabyState='"+tempconn.rs.getString(5)+"',Temp='"+tempconn.rs.getString(6)+"', Pulse='"+tempconn.rs.getString(7)+"' ,BP='"+tempconn.rs.getString(8)+"', timestamp='"+tempconn.rs.getTimestamp(10)+"',pncno='"+tempconn.rs.getString(11)+"'  where ID='"+tempconn.rs.getString(1)+tempconn.rs.getString(9)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        postnatgtom++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    postnatgtom++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }
            //======================================================================
             
            
            
            
            
            
            
            
            
             // ======================================================================================================
      
            
           String mergepostnat_ntot="select * from postnat_ntot";

            tempconn.rs = tempconn.st.executeQuery(mergepostnat_ntot);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from xtoad where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "' and timestamp='"+tempconn.rs.getString(12)+"'";
String existancechecker = "select * from postnat_ntot where ID like '%" + tempconn.rs.getString(1)+tempconn.rs.getString(9) + "%' ";

//insert into the master db from the temp db

   String insertdata = "replace into postnat_ntot(ID,Parlor,Breast,Uterus,PPH,CSection,Lochial,Episiotomy,motherid,timestamp,pncno) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(9)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "','" + tempconn.rs.getString(10) + "','" + tempconn.rs.getString(11) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        String upd="update postnat_ntot set Parlor='"+tempconn.rs.getString(2)+"',Breast='"+tempconn.rs.getString(3)+"',Uterus='"+tempconn.rs.getString(4)+"',PPH='"+tempconn.rs.getString(5)+"',CSection='"+tempconn.rs.getString(6)+"', Lochial='"+tempconn.rs.getString(7)+"' ,Episiotomy='"+tempconn.rs.getString(8)+"',pncno='" + tempconn.rs.getString(11) + "', timestamp='"+tempconn.rs.getTimestamp(10)+"'  where ID='"+tempconn.rs.getString(1)+tempconn.rs.getString(9)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        postnatntot++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    postnatntot++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }
            //======================================================================
            
            
            
           
             // ======================================================================================================
      
            
           String mergepostnat_utoz="select * from postnat_utoz";

            tempconn.rs = tempconn.st.executeQuery(mergepostnat_utoz);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from xtoad where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "' and timestamp='"+tempconn.rs.getString(12)+"'";
String existancechecker = "select * from postnat_utoz where tableid like '%" + tempconn.rs.getString(1)+tempconn.rs.getString(9) + "%' ";

//insert into the master db from the temp db

   String insertdata = "replace into postnat_utoz(tableid,pncno,priorknownstatus,lessequals72,greaterthan72,nvptobaby,ctxbaby,ctxmother,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(9)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "','" + tempconn.rs.getString(10) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        String upd="update postnat_utoz set pncno='"+tempconn.rs.getString(2)+"',priorknownstatus='"+tempconn.rs.getString(3)+"',lessequals72='"+tempconn.rs.getString(4)+"',greaterthan72='"+tempconn.rs.getString(5)+"',nvptobaby='"+tempconn.rs.getString(6)+"', ctxbaby='"+tempconn.rs.getString(7)+"' ,ctxmother='"+tempconn.rs.getString(8)+"', timestamp='"+tempconn.rs.getTimestamp(10)+"'  where tableid='"+tempconn.rs.getString(1)+tempconn.rs.getString(9)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        postnatutoz++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    postnatutoz++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }
            //======================================================================
             
      
   //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
   //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
   // MATERNITY REGISTER MERGING STARTS HERE
   //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
   //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
   
            
             // ======================================================================================================
      
            
           String mergeactoai="select * from mat_actoai";

            tempconn.rs = tempconn.st.executeQuery(mergeactoai);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from xtoad where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "' and timestamp='"+tempconn.rs.getString(12)+"'";
String existancechecker = "select * from mat_actoai where tableid like '%" + tempconn.rs.getString(1)+tempconn.rs.getString(10) + "%' ";

//insert into the master db from the temp db

String insertdata = "replace into mat_actoai(tableid,ancno,arv_anc,arv_maternity,arv_tobaby,ctx_tomother,vitamin_a,partner_tested,partner_results,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(10)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "','" + tempconn.rs.getString(10) + "','" + tempconn.rs.getString(11) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        String upd="update mat_actoai set ancno='"+tempconn.rs.getString(2)+"',arv_anc='"+tempconn.rs.getString(3)+"',arv_maternity='"+tempconn.rs.getString(4)+"',arv_tobaby='"+tempconn.rs.getString(5)+"',ctx_tomother='"+tempconn.rs.getString(6)+"', vitamin_a='"+tempconn.rs.getString(7)+"' ,partner_tested='"+tempconn.rs.getString(8)+"',partner_results='"+tempconn.rs.getString(9)+"', timestamp='"+tempconn.rs.getTimestamp(11)+"'  where tableid='"+tempconn.rs.getString(1)+tempconn.rs.getString(10)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        mat_actoai++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                     mat_actoai++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }
            //======================================================================
                  
   
            

             // ====================================mat_ajtoan Merging==================================================================
      
            
           String mergeajtoan="select * from mat_ajtoan";

            tempconn.rs = tempconn.st.executeQuery(mergeajtoan);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from xtoad where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "' and timestamp='"+tempconn.rs.getString(12)+"'";
String existancechecker = "select * from mat_ajtoan where tableid like '%" + tempconn.rs.getString(1)+tempconn.rs.getString(8) + "' ";

//insert into the master db from the temp db

String insertdata = "replace into mat_ajtoan(tableid,ancno,delivery_by,birth_no,discharge_date,baby_status,comments,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(8)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        String upd="update mat_ajtoan set ancno='"+tempconn.rs.getString(2)+"',delivery_by='"+tempconn.rs.getString(3)+"',birth_no='"+tempconn.rs.getString(4)+"',discharge_date='"+tempconn.rs.getString(5)+"', baby_status='"+tempconn.rs.getString(6)+"', comments='"+tempconn.rs.getString(7)+"' , timestamp='"+tempconn.rs.getTimestamp(9)+"'  where tableid='"+tempconn.rs.getString(1)+tempconn.rs.getString(8)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        mat_ajtoan++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                     mat_ajtoan++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }
            //======================================================================            
            
            
  
              // ====================================mat_ajtoan Merging==================================================================
      
            
           String mergematatoh="select * from mat_atoh";

            tempconn.rs = tempconn.st.executeQuery(mergematatoh);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from xtoad where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "' and timestamp='"+tempconn.rs.getString(12)+"'";
String existancechecker = "select * from mat_atoh where MatRegisterID like '%" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "%' ";

//insert into the master db from the temp db

String insertdata = "replace into mat_atoh(MatRegisterID,AdmissionNo,AdmissionDate,AncVisits,FirstName,SecondName,Village,LastName,Age,MaritalStatus,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(11)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "','" + tempconn.rs.getString(10) + "','" + tempconn.rs.getString(11) + "','" + tempconn.rs.getString(12) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        String upd="update mat_atoh set AdmissionNo='"+tempconn.rs.getString(2)+"',AdmissionDate='"+tempconn.rs.getString(3)+"',AncVisits='"+tempconn.rs.getString(4)+"',FirstName='"+tempconn.rs.getString(5)+"', SecondName='"+tempconn.rs.getString(6)+"', Village='"+tempconn.rs.getString(7)+"' ,LastName='"+tempconn.rs.getString(8)+"', Age='"+tempconn.rs.getString(9)+"', MaritalStatus='"+tempconn.rs.getString(10)+"'  , timestamp='"+tempconn.rs.getTimestamp(12)+"'  where MatRegisterID='"+tempconn.rs.getString(1)+tempconn.rs.getString(12)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        mat_atoh++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                     mat_atoh++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }
            //======================================================================            
           
            
            
            
            
     
  
              // ====================================mat_htol Merging==================================================================
      
            
           String mergemathtol="select * from mat_htol";

            tempconn.rs = tempconn.st.executeQuery(mergemathtol);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from xtoad where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "' and timestamp='"+tempconn.rs.getString(12)+"'";
String existancechecker = "select * from mat_htol where MatRegisterID like '%" + tempconn.rs.getString(1)+tempconn.rs.getString(8) + "%' ";

//insert into the master db from the temp db

String insertdata = "replace into mat_htol(MatRegisterID,AdmissionNo,Parity,Gravidae,LMP,EDD,Diagnosis,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(8)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        String upd="update mat_htol set AdmissionNo='"+tempconn.rs.getString(2)+"',Parity='"+tempconn.rs.getString(3)+"',Gravidae='"+tempconn.rs.getString(4)+"',LMP='"+tempconn.rs.getString(5)+"', EDD='"+tempconn.rs.getString(6)+"', Diagnosis='"+tempconn.rs.getString(7)+"' , timestamp='"+tempconn.rs.getTimestamp(9)+"'  where MatRegisterID='"+tempconn.rs.getString(1)+tempconn.rs.getString(9)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        mat_htol++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                     mat_htol++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }
            //======================================================================            
           
                   
            
    
            
            // ====================================mat_htol Merging==================================================================
      
            
           String mergematmtou="select * from mat_mtou";

            tempconn.rs = tempconn.st.executeQuery(mergematmtou);

            while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from xtoad where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "' and timestamp='"+tempconn.rs.getString(12)+"'";
String existancechecker = "select * from mat_mtou where MatRegisterID like '%" + tempconn.rs.getString(1)+tempconn.rs.getString(12) + "%' ";

//insert into the master db from the temp db

String insertdata = "replace into mat_mtou(MatRegisterID,AdmissionNo,LabourDuration,DeliveryDate,DeliveryTime,GestationAtBirth,DeliveryMode,PlacentaComplete,BloodLoss,ConditionAfterDelivery,DeliveryComplications,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(12)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "','" + tempconn.rs.getString(10) + "','" + tempconn.rs.getString(11) + "','" + tempconn.rs.getString(12) + "','" + tempconn.rs.getString(13) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        String upd="update mat_mtou set AdmissionNo='"+tempconn.rs.getString(2)+"',LabourDuration='"+tempconn.rs.getString(3)+"',DeliveryDate='"+tempconn.rs.getString(4)+"',DeliveryTime='"+tempconn.rs.getString(5)+"', GestationAtBirth='"+tempconn.rs.getString(6)+"', DeliveryMode='"+tempconn.rs.getString(7)+"' ,PlacentaComplete='"+tempconn.rs.getString(8)+"' , BloodLoss='"+tempconn.rs.getString(9)+"' , ConditionAfterDelivery='"+tempconn.rs.getString(10)+"' ,DeliveryComplications='"+tempconn.rs.getString(11)+"' , timestamp='"+tempconn.rs.getTimestamp(13)+"'  where MatRegisterID='"+tempconn.rs.getString(1)+tempconn.rs.getString(12)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        mat_mtou++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                     mat_mtou++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }
            //======================================================================            
           
            
            
     
            
            
            
            // ====================================mat_htol Merging==================================================================
      
            
           String mergematvtoab="select * from mat_vtoab";

           tempconn.rs = tempconn.st.executeQuery(mergematvtoab);

           while (tempconn.rs.next()) {
                
                //check in the master db whether data exists...
//String existancechecker = "select * from aetoak where motherid='" + conn.rs.getString(11) + "' and ancno='"+conn.rs.getString(2)+"' and ConditionID='"+conn.rs.getString(3)+"' and others_section_6='"+conn.rs.getString(4)+"' and deworming='"+conn.rs.getString(5)+"' and ipt='"+conn.rs.getString(6)+"' and ttdose='"+conn.rs.getString(7)+"' and iron='"+conn.rs.getString(8)+"' and folic='"+conn.rs.getString(9)+"' and itn='"+conn.rs.getString(10)+"'";
//String existancechecker = "select * from xtoad where ancRegisterID='" + tempconn.rs.getString(1)+tempconn.rs.getString(11) + "' and timestamp='"+tempconn.rs.getString(12)+"'";
String existancechecker = "select * from mat_vtoab where tableid like '%" + tempconn.rs.getString(1)+tempconn.rs.getString(10) + "%' ";

//insert into the master db from the temp db

String insertdata = "replace into mat_vtoab(tableid,ancno,Sex,birth_weight,live_Birth,apgar_score,vdrl_rpr_results,hiv_anc,hiv_maternity,motherid,timestamp) "
                        + "values ('"+tempconn.rs.getString(1)+tempconn.rs.getString(10)+"','" + tempconn.rs.getString(2) + "','" + tempconn.rs.getString(3) + "','" + tempconn.rs.getString(4) + "','" + tempconn.rs.getString(5) + "','" + tempconn.rs.getString(6) + "','" + tempconn.rs.getString(7) + "','" + tempconn.rs.getString(8) + "','" + tempconn.rs.getString(9) + "','" + tempconn.rs.getString(10) + "','" + tempconn.rs.getString(11) + "')";

       //execute existance checker-uses main db conn
   
                conn.rs1 = conn.st1.executeQuery(existancechecker);

                if (conn.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(tempconn.rs.getTimestamp("timestamp")+":::::"+conn.rs1.getTimestamp("timestamp"));
                    
                    if(!tempconn.rs.getTimestamp("timestamp").toString().equals(conn.rs1.getTimestamp("timestamp").toString())){
                    
                        String upd="update mat_vtoab set ancno='"+tempconn.rs.getString(2)+"',Sex='"+tempconn.rs.getString(3)+"',birth_weight='"+tempconn.rs.getString(4)+"',live_Birth='"+tempconn.rs.getString(5)+"', apgar_score='"+tempconn.rs.getString(6)+"', vdrl_rpr_results='"+tempconn.rs.getString(7)+"' ,hiv_anc='"+tempconn.rs.getString(8)+"' , hiv_maternity='"+tempconn.rs.getString(9)+"' , timestamp='"+tempconn.rs.getTimestamp(11)+"'  where tableid='"+tempconn.rs.getString(1)+tempconn.rs.getString(10)+"' ";
                        //update master db
                        conn.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        mat_vtoab++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                     mat_vtoab++;
                    
                    //check for blanks
                    
                    
                    
                    conn.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }
            //======================================================================   
            
            
            
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(mergeData.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        
        
        
          String feedbackmsg = "";
          
          
          
     
   
          

            if (xtoad==0 && qtow==0 && aeak == 0 && alan == 0 && ancvisits == 0 && atoh == 0 && cervicalscreening == 0 &&delivery==0&&deworming==0&&hiv_exposure==0 && itop==0  && motherdetails==0 &&presentpregnacy==0 && mat_actoai == 0 &&  mat_ajtoan == 0 && postaatoae == 0) {

                feedbackmsg = "<font color=\"red\">No New Inserts/updates done</font>";

            } else {

                feedbackmsg += "<font color=\"green\">" + motherdetails + " </font>New Mothers imported<br/>";
                feedbackmsg += "<font color=\"green\">" + presentpregnacy + " </font>New Present pregnancy records imported<br/>";
                feedbackmsg += "<font color=\"green\">" + atoh + " </font>New atoh records imported<br/>";
                feedbackmsg += "<font color=\"green\">" + aeak + " </font>New aeak details imported<br/>";
                feedbackmsg += "<font color=\"green\">" + alan + " </font>New alan records imported<br/>";
                feedbackmsg += "<font color=\"green\">" + itop + " </font>New itop records imported<br/>";
                feedbackmsg += "<font color=\"green\">" + qtow + " </font>New qtow records imported<br/>";
                feedbackmsg += "<font color=\"green\">" + xtoad + " </font>New xtoad records imported<br/>";
                feedbackmsg += "<font color=\"green\">" + ancvisits + " </font>New ancvisits records imported<br/>";
                //___postnatal Register
                  feedbackmsg += "<font color=\"green\">" + postnatatof + " </font>New postnatal atof records imported<br/>";
                  feedbackmsg += "<font color=\"green\">" + postnatgtom + " </font>New postnatal gtom records imported<br/>";
                  feedbackmsg += "<font color=\"green\">" + postnatntot + " </font>New postnatal ntot records imported<br/>";
                  feedbackmsg += "<font color=\"green\">" + postnatutoz + " </font>New postnatal utoz records imported<br/>";
                  feedbackmsg += "<font color=\"green\">" + postaatoae + " </font>New postnatal aatoae records imported<br/>";
                  feedbackmsg += "<font color=\"green\">" + postnataftoai+ " </font>New postnatal aftoai records imported<br/>";
                
                  
                  //__Maternity Register
                  feedbackmsg += "<font color=\"green\">" + mat_atoh + " </font>New Maternity atoh records imported<br/>";
                  feedbackmsg += "<font color=\"green\">" + mat_htol + " </font>New Maternity htol records imported<br/>";
                  feedbackmsg += "<font color=\"green\">" + mat_mtou + " </font>New Maternity mtou records imported<br/>";
                  feedbackmsg += "<font color=\"green\">" + mat_vtoab + " </font>New Maternity vtoab records imported<br/>";
                  feedbackmsg += "<font color=\"green\">" + mat_actoai + " </font>New Maternity actoai records imported<br/>";
                  feedbackmsg += "<font color=\"green\">" + mat_ajtoan + " </font>New Maternity ajtoan records imported<br/>";
                  
                  
                feedbackmsg += "<font color=\"green\">" + cervicalscreening + " </font>New cervicalscreening records imported<br/>";
                feedbackmsg += "<font color=\"green\">" + deworming + " </font>New deworming records imported<br/>";
                feedbackmsg += "<font color=\"green\">" + delivery + " </font>New delivery records imported<br/>";
                feedbackmsg += "<font color=\"green\">" + hiv_exposure + " </font>New hiv_exposure records imported<br/>";
                feedbackmsg += "<font color=\"green\">" + maternaldetails + " </font>New maternal Details records imported<br/>";
                
            }

            sess.setAttribute("feedbackmsg", feedbackmsg);
        
        
        //System.out.println(feedbackmsg);
            
            
            //after merging data, now update the blank edd and visit dates
            
            UpdateEddMotherDetails update= new UpdateEddMotherDetails();
            
            update.UPDATEVISITDATESANC();
            
            
        
        response.sendRedirect("mergedata.jsp");
        
        
       
       
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
