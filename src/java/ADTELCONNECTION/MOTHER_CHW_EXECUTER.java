/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ADTELCONNECTION;

/**
 *
 * @author SIXTYFOURBIT
 */

import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import sender.dbConn;






public class MOTHER_CHW_EXECUTER implements Job{

    
    
    
 String sender_no = "", msg,shortcode;


 int year, month, date, hour, min, sec, schedulingmin;
 
  String smssend="false";
    
  
  //used to identify the messages headed to a mother
  
  String CHWTOMOTHEREXPRESSION="@";  
  
  String NEXTOFKEENCHWHEADER="Message from ";
  String CHW_HAS_REPLIED="has replied";
  String CHWTOMOTHER="CHW TO MOTHER",MOTHERTOCHW="MOTHER TO CHW";
    adtelConn aconn ;
   dbConn conn;
   
   
    
    
    public void execute(JobExecutionContext context) throws JobExecutionException {
        
           
              conn = new dbConn(); 
              
            
      
              
              
              
              
              
        String checknewsms="Select * from sms where clicked=0 limit 20 ";
             
               
                //this will change to conn.rs.getString values
       
         
     System.out.println("SMS RECEIVER IS LISTENING"); 
     
     //before any listening?, first check whether there is any internet connectivity       
 
     
     if(isInternetReachable()){
         
         try {
         
             aconn = new adtelConn();      
                aconn.rs2=aconn.st2.executeQuery(checknewsms);
                
           
                
                
                while(aconn.rs2.next()){
                
        //        
        //       
                 sender_no = aconn.rs2.getString("Number").trim();
            

                 msg = aconn.rs2.getString("Message").trim();

                    
                  //shortcode=aconn.rs_6.getString("Message").trim();
                  shortcode="30530";
           
                  
                   // System.out.println("sender no"+sender_no);
                  
                  
                  
                  //get a status that contains the value of the send sms... send the sms first, the 
                  
          String updatesms="Update sms set clicked='1' where sms_index='"+aconn.rs2.getString("sms_index")+"'";
                  
           String myreturn=""; 
          
          //check the first thing that preceeds the sms
          //if it begins with a @, then redirect to CHW, else redirect to a chw
          
          if(msg.trim().startsWith(CHWTOMOTHEREXPRESSION)){
              
          //==this messages are from the chw to a mother..should go to mother
              myreturn=CHWTOMOTHER(sender_no,msg,shortcode);
          
          }
          
          else{
              
             //message should go to chw 
            myreturn= MOTHERTOCHW(sender_no,msg,shortcode);
          
          
          }
        // System.out.println("Myreturn::  "+myreturn);
              
        // System.out.println("Myreturn::  "+myreturn);
          
       if(myreturn.equals("true")){
       
       aconn.st_5.executeUpdate(updatesms);
       
           System.out.println("request to update table made");
           
           
       }   
          
             // break;  
                }   //end of while loop
                
                
                
                
                
                 } catch (SQLException ex) {
           Logger.getLogger(MOTHER_CHW_EXECUTER.class.getName()).log(Level.SEVERE, null, ex);
            
            System.out.println("UNABLE TO REACH THE ATABASE HOST SERVER");
            
            
            
        }
                
                
                
                
                }//check presence of internet
                
                
       
         
        
      
        
        
        
          
    }
    
    
    
    
    
    
    
     //===============================================================================================================
    //====================================MOTHER TO CHW METHOD======================================================
 
    
    public String MOTHERTOCHW(String sender_no,String msg,String shortcode){
       
        
        smssend="false";
        try {
            
            
           
            
            
            ArrayList allsms= new ArrayList();            
                
     

    if(allsms.size()>0){

    allsms.clear();
    
    }
               
                
                
                msg = msg.replace("'", "");

                if (sender_no.startsWith("+254")) {

                    sender_no = sender_no.replace("+", "");

                }

                //System.out.println("" + sender_no);


                //adtelConn aconn = new adtelConn();


                


                //if mother uses another number which is not registered, let them begin by writting 
                //their registered number first, then the message...
                //eg. 0712256**3 I am in vain

                String nonregisterednumber = "";

                if (msg.trim().startsWith("07")) {

                    nonregisterednumber = msg.trim().substring(1, 10);
                    nonregisterednumber = "254"+nonregisterednumber;

                }




               String check_chw;

 if(nonregisterednumber.equals("")){
 
 check_chw = "Select ChwID,FName,SName,motherID from mother_details where PhoneNo='" + sender_no + "' OR NOKPhoneNo='" + sender_no + "' ";
 
 }
 else{
  
     //System.out.println(" this is the number"+nonregisterednumber);
     
  check_chw = "Select ChwID,FName,SName,motherID from mother_details where PhoneNo='" + nonregisterednumber + "' OR NOKPhoneNo='" + nonregisterednumber + "'";

 }

                conn.rs = conn.st.executeQuery(check_chw);
                if (conn.rs.next() && !msg.startsWith("@")) {

     //save the details of the sender of messages to a table.               
                    
    String addtoreplyto="Insert into reply_to SET reply_to_number='"+sender_no+"' , mother_id='"+conn.rs.getString("motherID")+"'";                
                    
       conn.st4.executeUpdate(addtoreplyto);             
       
                    
                    
                    
                    
                    
    //======get chw details

          conn.rs1 = conn.st1.executeQuery("Select * from chw where chw_id='" + conn.rs.getString(1) + "'");
          conn.rs1.next();
          String sender_name = conn.rs.getString("FName").toString().trim() + " " + conn.rs.getString("SName").toString().trim() + " ";
          String chv_phone = conn.rs1.getString("chv_phone").toString().trim();
          String motherid = conn.rs.getString("motherID").toString().trim();

     //=====MESSAGE      
          
          String msgtochw="Message From " + sender_name + "(code:" + motherid + ") " + msg + ":FHI360 Koibatek Intervention";     
       
       
          
          //if mothers question is long, split it into two then send it to chw
          
          
          
            if (msgtochw.length() > 160&& msgtochw.length()<=320) {
                            
              allsms.add(msgtochw.substring(0, 159));
               
              allsms.add(msgtochw.substring(160));
               

               
               
                        } 
            
          
          else if (msgtochw.length() > 320&& msgtochw.length()<=480) {
                            
                allsms.add(msgtochw.substring(0, 159));
               
                allsms.add(msgtochw.substring(160,319));
               
                allsms.add(msgtochw.substring(320,479));
               

                        } 
          
          
          //if message exceeds 480 characters
          
          else if (msgtochw.length() > 480) {
              
              //send only three messages and leave the rest
              
                allsms.add(msgtochw.substring(0, 159));
               
                allsms.add(msgtochw.substring(160,319));
               
                allsms.add(msgtochw.substring(320,479));
               

                        } 
          
          // a normal sms
          
          else{    allsms.add(msgtochw);  } 
          

            
             //====((((((((SEND SMS TO USERS))))))))))===============//
            
            

    if(chv_phone.startsWith("2547")&&chv_phone.length()==12){
     
    //aconn.st1.executeUpdate("insert into request set Number='" + nokno + "' , Message='" + nokmsg + "', shortcode='" + shortcode + "'");
       for(int a=0;a<allsms.size();a++){
           
   // aconn.st.executeUpdate("insert into request2 set Number='" + chv_phone + "' , Message='" +allsms.get(a)+ "'");

    System.out.println("QSTN TO CHW:  message send to chw:"+chv_phone +"   "+allsms.get(a));
         
    Toolkit.getDefaultToolkit().beep();
                
    
    smssend="true";
       }


    }




    if(chv_phone.startsWith("2547")&&chv_phone.length()==12){
        
         for(int a=0;a<allsms.size();a++){ 
                       
          Date dat=new Date();
                            
          conn.st_1.executeUpdate("insert into send_sms set number='" + chv_phone + "' , message='" + allsms.get(a)+ "',status='pending' ,shortcode='" + shortcode + "' ,time='" +dat+ "', message_section='mother_to_chw' , target_reached='yes'");

                   System.out.println("Logging done");
                        
         }
         
         
    }







                }//end of if. conn.rs.next

                else{
                    
                    
                    //if none of the mother got those details..
                 Date dat=new Date();
                
                
                //Save a message to send sms with target_reachesd value set to false
                
                  conn.st_1.executeUpdate("insert into send_sms set number='" + sender_no + "' , message='" + msg+ "',status='pending' ,shortcode='" + shortcode + "' ,time='" +dat+ "', message_section='"+MOTHERTOCHW+"',target_reached='NO'");

               // System.out.println("");
                smssend="true";  
                  
                } 
                
                
                

                allsms.clear();
               
                
                
                /**
                
                
                
                
                 */
        }//end of try
        
        catch (SQLException ex) {
            Logger.getLogger(ALLSMSRECEIVER.class.getName()).log(Level.SEVERE, null, ex);
            
            //if an exception occured, set status of sms as not set.
            
            smssend="false";
            
            
        }

        




        
        
        
         
     return smssend;   
    
    }
    
    //end of motherto chw question
    
    
//=====================================================END OF MOTHER TO CHW============================    
    
    
   //*****************
   //*****************
   //*****************
   //*****************
   //*****************
   //*****************
   //*****************
   //*****************
   //*****************
   //*****************
   //*****************
   //*****************
   //*****************
   //*****************
   //*****************
    
   
    
 
//=======================================CHW TO MOTHER METHOD====================================================    
    
    
   public String CHWTOMOTHER(String sender_no,String msg,String shortcode){
   
   
       
   
       
         smssend="false";
        try {
            
         
            ArrayList allsms1= new ArrayList();            
                
     

    if(allsms1.size()>0){

         allsms1.clear();
    
    }
               
                
                
                msg = msg.replace("'", "");

                if (sender_no.startsWith("+254")) {

                    sender_no = sender_no.replace("+", "");

                }
                
                
                
                String smsarray[];
                
                 smsarray=msg.split(CHWTOMOTHEREXPRESSION,3);
                

                //System.out.println("" + sender_no);
      


 String check_mother="Select ChwID, PhoneNo , NOKPhoneNo ,FName,SName, nok_consent from mother_details where motherID='"+smsarray[1]+"'";

                //String check_chw = "Select ChwID,FName,SName,motherID from mother_details where PhoneNo='" + sender_no + "' OR NOKPhoneNo='" + sender_no + "' OR PhoneNo='" + nonregisterednumber + "' OR NOKPhoneNo='" + nonregisterednumber + "'";

                conn.rs_6=conn.st_6.executeQuery(check_mother);
                
                //conn.rs = conn.st.executeQuery(check_chw);
                if (conn.rs_6.next()) {
       
             //====get CHW PHONE NUMBER=======
       
                    
       conn.rs_5=conn.st_5.executeQuery("Select * from chw where  	chv_phone='"+sender_no+"'");
       conn.rs_5.next();
       
       
       
       
       //===============get the names of the CHV============================
       
       
       
       
       String sender_name=conn.rs_5.getString(2).toString().trim()+" "+conn.rs_5.getString(3).toString().trim()+"";
       
       
       /**first we should not use the mothers number in the system 
       to reply back to them but rather use the number which they send the message with
        * if the number is not there, then we can use the mothers saved phone number
        * 
        * 
        * if its null, then we can use the noks number..
        * 
        * 
        * if we use noks number, then the message should recognize that this message 
        * is meant to go to another person
        * 
        * This happens when the nok of kins consent is set to yes.
      */
       
       
        String mother_phone="";
        String msgtomom="";
        //===============GET THE NUMBER TO REPLY TO=======================================
        
        
       conn.rs_4=conn.st_4.executeQuery("SELECT MAX(tableid) from reply_to where mother_id='"+smsarray[1]+"' AND status='0'");
       if(conn.rs_4.next()){
       
        String MAXIMUMTABLEID=  conn.rs_4.getString(1).trim().toString() ; 
           
       String get_reply_to_number="select reply_to_number FROM reply_to where tableid='"+MAXIMUMTABLEID+"'";
       
        conn.rs_3=conn.st_3.executeQuery(get_reply_to_number);
      
        
        //====get number from the reply_to tables
        
       if(conn.rs_3.next()){
           
       mother_phone=conn.rs_3.getString("reply_to_number");
       
       
         msgtomom=NEXTOFKEENCHWHEADER+" "+sender_name+": \""+""+smsarray[2]+"\"";
//              
//       
      //update the status of that id to yes
       
         
         conn.st_2.executeUpdate("update reply_to set status='1' where tableid='"+MAXIMUMTABLEID+"'");
       
       
       
       
       }
       
       }
       else{
       
         mother_phone=conn.rs_6.getString("PhoneNo").toString().trim();
         
          msgtomom=NEXTOFKEENCHWHEADER+" "+sender_name+": \""+""+smsarray[2]+"\"";
         
         //if the mother has got no set number, then we get the noks no on condition that the they have consented...
         
         if(mother_phone.length()!=12||mother_phone==null){
         //check if the nok has consented
         //if yes get 
             
             if(conn.rs_6.getString("nok_consent").equalsIgnoreCase("YES")){
             
                 mother_phone=conn.rs_6.getString("NOKPhoneNo");
                msgtomom=NEXTOFKEENCHWHEADER+" "+ conn.rs_6.getString("FNAME") +", "+sender_name+" "+CHW_HAS_REPLIED+" \""+""+smsarray[2]+"\"";
                 //get the message header here..
                 
                 
             }
         
         
         }
         
         
                    
       }
      
       //=======================WE NOW HAVE THE MOTHER NUMBER AND MESSAGE  UP TO  HERE===========================
       
       
       
       
   

        

          
          //if mothers question is long, split it into two then send it to chw
          
          
          
            if (msgtomom.length() > 160&& msgtomom.length()<=320) {
                            
              allsms1.add(msgtomom.substring(0, 159));
               
              allsms1.add(msgtomom.substring(160));
               

               
               
                        } 
            
          
          else if (msgtomom.length() > 320&& msgtomom.length()<=480) {
                            
                allsms1.add(msgtomom.substring(0, 159));
               
                allsms1.add(msgtomom.substring(160,319));
               
                allsms1.add(msgtomom.substring(320,479));
               

                        } 
          
          
          //if message exceeds 480 characters
          
          else if (msgtomom.length() > 480) {
              
              //send only three messages and leave the rest
              
                allsms1.add(msgtomom.substring(0, 159));
               
                allsms1.add(msgtomom.substring(160,319));
               
                allsms1.add(msgtomom.substring(320,479));
                
               allsms1.add(msgtomom.substring(480,599));

                        } 
          
          // a normal sms
          
          else{    allsms1.add(msgtomom);  } 
          

            
             //====((((((((SEND SMS TO USERS))))))))))===============//
            
            

    if(mother_phone.startsWith("2547")&&mother_phone.length()==12){
     
    //aconn.st1.executeUpdate("insert into request set Number='" + nokno + "' , Message='" + nokmsg + "', shortcode='" + shortcode + "'");
       for(int a=0;a<allsms1.size();a++){
           
           
           
 //(((((((((((((((((((((((((((((((((((((SMS TO ADTEL SERVERS))))))))))))))))))))))))))))))))))))))))))) 
           
                                      //send sms to adtel server
           
// aconn.st.executeUpdate("insert into request2 set Number='" + mother_phone + "' , Message='" +allsms1.get(a)+ "'");

    Toolkit.getDefaultToolkit().beep();
                       
           
 //(((((((((((((((((((((((((((((((((((((((((((((())))))))))))))))))))))))))))))))))))))))))))))))))))))          
           
           
           
           
           
    System.out.println("RESPONSE TO MOTHER :  message send to chw:"+mother_phone +"   "+allsms1.get(a));
         
    
    
    smssend="true";
       }


    }




    if(mother_phone.startsWith("2547")&&mother_phone.length()==12){
        
         for(int a=0;a<allsms1.size();a++){ 
                       
          Date dat=new Date();
                            
          conn.st_1.executeUpdate("insert into send_sms set number='" + mother_phone + "' , message='" + allsms1.get(a)+ "',status='pending' ,shortcode='" + shortcode + "' ,time='" +dat+ "', message_section='"+CHWTOMOTHER+"',target_reached='YES'");

                   System.out.println("Logging done");
                        
         }
         
         
    }







                }//end of if. conn.rs.next
                
                
                //if no mother was found to own that number,then save to a debug area
                else{
                    
                    
                    //if none of the mother got those details..
                 Date dat=new Date();
                
                
                //Save a message to send sms with target_reachesd value set to false
                
                  conn.st_1.executeUpdate("insert into send_sms set number='" + sender_no + "' , message='" + msg+ "',status='pending' ,shortcode='" + shortcode + "' ,time='" +dat+ "', message_section='"+CHWTOMOTHER+"',target_reached='NO'");

                
                }

                allsms1.clear();
               
                
                
                /**
                
                
                
                
                 */
        }//end of try
        
        catch (SQLException ex) {
            Logger.getLogger(ALLSMSRECEIVER.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
            //if an exception occured, set status of sms as not set.
            
            smssend="false";
            
            
        }

        
       
       
       
       
       
       
       
       
   return smssend;
   } 
    
    
    
    
    
    
    
//==============================end of chw to mother=====================================================    
    
  
   
   
   
   //METHOD TO CHECK IF THERE IS INTERNET CONNECTIVITY FIRST BEFORE CALLING THE REMOTE SERVER..
   
   
   public static boolean isInternetReachable()
        {
            try {
                
                //make a URL to a known source
                URL url = new URL("http://196.201.225.101");

                //open a connection to that source
                URLConnection urlConnect = url.openConnection();

                //trying to retrieve data from the source. If there
                //is no connection, this line will fail
                urlConnect.getInputStream();

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                System.out.println("No internet Connection");
                return false;
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("No internet Connection");
                return false;
            }
            return true;
        }           
              
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
    
    
}
