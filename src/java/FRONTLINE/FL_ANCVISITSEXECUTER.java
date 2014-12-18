/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FRONTLINE;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import sender.dbConn;

/**
 *
 * @author SIXTYFOURBIT
 */
public class FL_ANCVISITSEXECUTER implements Job {

    String status_id, message_id;
    int year, month, date, hour, min, sec, schedulingmin;
    //adtelConn aconn;
    dbConn conn;
    FL_CONNECTION addres= new FL_CONNECTION();
    String WEEKLYANC = "WEEKLY ANC VISITS", DAILYANC = "DAILY ANC VISITS";

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            
            if(isInternetReachable()){
                
            //aconn = new adtelConn();

            }

            //status 1 is for pregnant mothers

            status_id = "1";


            message_id = "1";

            System.out.println("______________________ANC VISITS RUNNING________________________");

            currentdates();
            conn = new dbConn();


            //check first if there exists any send sms for today..

            Date dat = new Date();

            String todaysrecord = "select * from monitor_scheduling where date=DATE(CURDATE()) AND purpose='ANC VISITS'";

            conn.rs_6 = conn.st_6.executeQuery(todaysrecord);

            if (conn.rs_6.next()) {

                //System.out.println("THE SMS DISBURSING SMS IS NOT AVAILABLE. A RESCHEDULE HAS TO BE DONE");
                System.out.println("ANC VISIT SCHEDULE ALREADY DONE");


            } else {
                
          //call the get mothers class only if net is available      

                //if (isInternetReachable()) {

                    getmothers();
                    

                //}
System.out.println("ANC VISIT SCHEDULER STARTED");
            }


        } catch (SQLException ex) {
            Logger.getLogger(FL_ANCVISITSEXECUTER.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    //______________________________GETMOTHERS FUCTION___________________________
    public void getmothers() {



        Date todaysdate = new Date();


       // String mymothers = "Select PhoneNo , NOKPhoneNo ,VisitDate,FName,nok,nok_consent,languagePreferred from mother_details where status_id ='" + status_id + "'";


        String onedaymothers = "Select PhoneNo , NOKPhoneNo ,VisitDate,FName,nok,nok_consent,languagePreferred from mother_details where status_id ='" + status_id + "' AND DATE(VisitDate) = DATE(CURDATE()+1)";


        String oneweekmothers = "Select PhoneNo , NOKPhoneNo ,VisitDate,FName,nok,nok_consent,languagePreferred from mother_details where status_id ='" + status_id + "' AND DATE(VisitDate) = DATE_ADD(CURDATE(), INTERVAL 7 DAY)";



//get current calender date and compare it with the anc visit date.




        //connect to local mnhc database




        try {

//get the message that will be displayed to the user in the range of one 

            String anconeweekmsg = "select * from messages where message_id='3'";

            String anconedaymsg = "select * from messages where message_id='2'";

            // String amonthbefore = "select * from messages where message_id='5'";



            String oneweek = "select * from messaging_days where id='2'";



            //rs an st for one week  
            conn.rs3 = conn.st3.executeQuery(oneweek);
            conn.rs3.next();


//=================================== ANC VISITS HERE================================================  

            //get the mothers who desrve to get their notices one day from now....  


            conn.rs = conn.st.executeQuery(onedaymothers);

            while (conn.rs.next()) {


                //get dates
                currentdates();





                /**           *    if we'll include an option of many languages, 
                 *    then at this point we'll get the user preffered language first, * 
                 *    which will tell us which msg to get                           **/
                //===============================GET USER FAVOURATE LANGUAGE HERE================================
                //===========================================================================================                   
                //for now get english sms
                conn.rs2 = conn.st2.executeQuery(anconedaymsg);

                conn.rs2.next();

                //send user with message that their next anc visit is tommorrow




                //sms to mum
                String mommsgs = conn.rs2.getString("salutation") + " " + conn.rs.getString("FName").trim() + ", " + conn.rs2.getString("message") + "" + conn.rs.getString("VisitDate") + ":FHI360 Koibatek Intervention";
                //System.out.println(" one day msg"+msgs);






                //sms to nok                
                String nokmsg = conn.rs2.getString("salutation") + " " + conn.rs.getString("nok").trim() + ", " + conn.rs.getString("FName").trim() + " " + conn.rs2.getString("nok_message") + "" + conn.rs.getString("VisitDate") + ".:FHI360 Koibatek Intervention";
                //System.out.println(" one day msg"+msgs);



                //SWAHILI LANGUAGE


                if (conn.rs.getString("languagePreferred").contains("Kiswahili")) {

                    mommsgs = conn.rs2.getString("salamu") + " " + conn.rs.getString("FName").trim() + ", " + conn.rs2.getString("ujumbe") + "" + conn.rs.getString("VisitDate") + ":FHI360 Koibatek Intervention";


                    nokmsg = conn.rs2.getString("salamu") + " " + conn.rs.getString("nok").trim() + ", " + conn.rs.getString("FName").trim() + " " + conn.rs2.getString("nok_ujumbe") + "" + conn.rs.getString("VisitDate") + ".:FHI360 Koibatek Intervention";

                }
                nokmsg = nokmsg.replace("'", "");
                mommsgs = mommsgs.replace("'", "");
                nokmsg = nokmsg.replace(" ", "%20");
                mommsgs = mommsgs.replace(" ", "%20");

                //_________________PHONE NUMBERS____________________
                String mumno = conn.rs.getString(1).trim();


                String nokno = conn.rs.getString(2).trim();




                //take care of 160 characters


                if (mommsgs.length() > 160) {
                    mommsgs = mommsgs.substring(0, 160);

                }


                if (nokmsg.length() > 160) {

                    nokmsg = nokmsg.substring(0, 160);

                }



//replace message spaces with %20
                
//                nokmsg.replace(" ", "%20");
//                
//                mommsgs.replace(" ","%20");
             

              
                //send each message to adtel requests database..



                if (mumno.startsWith("254")) {
                    String rest_of_number = mumno.substring(3);
                    mumno = "0" + rest_of_number;


                }



                if (nokno.startsWith("254")) {
                    String rest_of_number = nokno.substring(3);
                    nokno = "0" + rest_of_number;

                }



//msg to mum
                if (mumno.startsWith("07") && mumno.length() == 10) {

                   // sendSMS(mumno, mommsgs);
          //add the same same messages to my send_sms table for reports purpose....   
                    

                    //send to my logger

                    if (mumno.startsWith("07") && mumno.length() == 10 && sendSMS(mumno, mommsgs) == true) {

                        conn.st_1.executeUpdate("insert into send_sms set number='" + mumno + "' , message='" + mommsgs + "',status='sent' ,shortcode=' frontline' , time='" + todaysdate + "' , message_section='" + DAILYANC + "' , target_reached='yes'");

                        
                    }

                }

//MSG TO CONSENT

                if (nokno.startsWith("07") && nokno.length() == 10) {




                    if (conn.rs.getString("nok_consent").trim().toUpperCase().equals("YES")) {
             //sendSMS(nokno, nokmsg);
                     //add the same same messages to my send_sms table for reports purpose....   
                        if (nokno.startsWith("07") && nokno.length() == 10 &&sendSMS(nokno, nokmsg) == true ) {
                            if (conn.rs.getString("nok_consent").trim().toUpperCase().equals("YES")) {

                                conn.st_2.executeUpdate("insert into send_sms set number='" + nokno + "' , message='" + nokmsg + "', status='sent',shortcode='frontline' ,time='" + todaysdate + "', message_section='" + DAILYANC + "' , target_reached='yes'");

               

                            }
                        }
   
                        
                        

                    }
                }






              

              


            
            }//end of one day while loop



//(((((((((((((((((((((((((((((((((((((((ONE DAY ENDS HERE, NOW ONE WEEK)))))))))))))))))))))))))))))))))))))))))))))))))                      




            conn.rs = conn.st.executeQuery(oneweekmothers);

            while (conn.rs.next()) {



                currentdates();


                //if for one week



                conn.rs2 = conn.st2.executeQuery(anconeweekmsg);

                conn.rs2.next();


                //send user with message that their next anc visit is next week


                //sms to mum
                String mommsgs = conn.rs2.getString("salutation") + " " + conn.rs.getString("FName").trim() + ", " + conn.rs2.getString("message") + " " + conn.rs.getString("VisitDate") + " :FHI360 Koibatek Intervention";
                //System.out.println(" one day msg"+msgs);





                //sms to nok                
                String nokmsg = conn.rs2.getString("salutation") + " " + conn.rs.getString("nok").trim() + ", " + conn.rs.getString("FName").trim() + " " + conn.rs2.getString("nok_message") + "" + conn.rs.getString("VisitDate") + ".:FHI360 Koibatek Intervention";
                //System.out.println(" one day msg"+msgs);




                //SWAHILI LANGUAGE


                if (conn.rs.getString("languagePreferred").contains("wahili")) {

                    mommsgs = conn.rs2.getString("salamu") + " " + conn.rs.getString("FName").trim() + ", " + conn.rs2.getString("ujumbe") + "" + conn.rs.getString("VisitDate") + ":FHI360 Koibatek Intervention";


                    nokmsg = conn.rs2.getString("salamu") + " " + conn.rs.getString("nok").trim() + ", " + conn.rs.getString("FName").trim() + " " + conn.rs2.getString("nok_ujumbe") + "" + conn.rs.getString("VisitDate") + ".:FHI360 Koibatek Intervention";

                }
                nokmsg = nokmsg.replace("'", "");
                mommsgs = mommsgs.replace("'", "");
                nokmsg = nokmsg.replace(" ", "%20");
                mommsgs = mommsgs.replace(" ", "%20");




                //_________________PHONE NUMBERS____________________
                String oneweekmumno = conn.rs.getString(1).trim();


                String oneweeknokno = conn.rs.getString(2).trim();





                //take care of 160 characters


//                if (mommsgs.length() > 160) {
//                    mommsgs = mommsgs.substring(0, 160);
//
//                }
//
//
//                if (nokmsg.length() > 160) {
//
//                    nokmsg = nokmsg.substring(0, 160);
//
//                }




                //get shortcode from messages table

                String shortcode = conn.rs2.getString("shortcode").trim();

                //send each message to adtel requests database..


                if (oneweekmumno.startsWith("254")) {
                    String rest_of_number = oneweekmumno.substring(3);
                    oneweekmumno = "0" + rest_of_number;


                }



                if (oneweeknokno.startsWith("254")) {
                    String rest_of_number = oneweeknokno.substring(3);
                    oneweeknokno = "0" + rest_of_number;

                }




                if (oneweekmumno.startsWith("07") && oneweeknokno.length() == 10) {
                    
             //sendSMS(oneweekmumno, mommsgs);
                    
           
             
           if (oneweekmumno.startsWith("07") && oneweekmumno.length() == 10 && sendSMS(oneweekmumno, mommsgs)==true) {
               
         conn.st_1.executeUpdate("insert into send_sms set number='" + oneweekmumno + "' , message='" + mommsgs + "',status='sent' ,shortcode='Frontline' ,time='" + todaysdate + "', message_section='" + WEEKLYANC + "' ,target_reached='yes'");


                }

                }



                if (oneweeknokno.startsWith("07") && oneweeknokno.length() == 10) {

                    

                    if (conn.rs.getString("nok_consent").trim().toUpperCase().equals("YES")) {
                        
                         // sendSMS(oneweeknokno, nokmsg);
                      //send to log
                          
                          
                            if (oneweeknokno.startsWith("07") && oneweeknokno.length() == 10&&sendSMS(oneweeknokno, nokmsg)==true) {
     if (conn.rs.getString("nok_consent").trim().toUpperCase().equals("YES")) {
         
  conn.st_2.executeUpdate("insert into send_sms set number='" + oneweeknokno + "' , message='" + nokmsg + "', status='sent',shortcode='Frontline' , time='" + todaysdate + "' , message_section='" + WEEKLYANC + "' ,target_reached='yes'");


                         System.out.println("mnhc send_sms:one week rem :nok message send to " + oneweeknokno+"  MSG:"+nokmsg);


                    }
                }
                          
                          
                          
                    }
                }



//add the same same messages to my send_sms table for reports purpose....



              














            }//end of while
//close all connections

            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String mdate;

            Date mydate = new Date();
            mdate = formatter.format(mydate);
            //======show that this scheduler executed succesfully. This will be done only if there is internet connection======             

            

                conn.st_2.executeUpdate("Insert into monitor_scheduling set purpose='ANC VISITS',date='" + mdate + "',status='excecuted' ");

            




        }//end of getmothers function
        catch (SQLException ex) {
            Logger.getLogger(FL_ANCVISITSEXECUTER.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //=================DATE FUNCTION========
    public void currentdates() {

        Calendar cal = Calendar.getInstance();

        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        date = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        min = cal.get(Calendar.MINUTE);
        sec = cal.get(Calendar.SECOND);
        schedulingmin = cal.get(Calendar.MINUTE) + 2;
    }

    //METHOD TO CHECK IF THERE IS INTERNET CONNECTIVITY FIRST BEFORE CALLING THE REMOTE SERVER..
    public static boolean isInternetReachable() {
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
            System.out.println("EDD:No internet Connection");
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("EDD:No internet Connection");
            return false;
        }
        return true;
    }
    
    //Message Sender 
    
    public boolean sendSMS(String pn, String msg){
    
     try {
                        
                        URL murl = new URL(""+addres.FrontlineAddress+pn+"/"+msg);
                        HttpURLConnection connection = (HttpURLConnection)murl.openConnection();
                        connection.setRequestMethod("GET");
                        connection.connect();

                        System.out.println("Sending msg to "+pn);
                        
                        int code = connection.getResponseCode();
                    }
                    //_____________________________________________________________________________________________________________
                    catch (IOException ex) {
                        
                       System.out.println("Error while sending Message to Frontline");
                       return false;
                    } 
    
    return true;
    }
    
    
    
    public void change_from_pre_to_postnatal(){
    
    String changestatus="update mother_details set status_id='2' where delivery_date";
    
    
    
    
    
    
    
    }
    
    
    
}
