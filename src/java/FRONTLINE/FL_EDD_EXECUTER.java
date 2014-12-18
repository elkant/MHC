/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FRONTLINE;

import ADTELCONNECTION.adtelConn;
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
public class FL_EDD_EXECUTER implements Job {

    String status_id, message_id;
    int year, month, date, hour, min, sec, schedulingmin;
    adtelConn aconn;
    dbConn conn;
    String MONTHLYEDD = "MONTHLY EDD";
    FL_CONNECTION addres = new FL_CONNECTION();

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            //=============connect to adtel dbase===========================

            if (isInternetReachable()) {


                aconn = new adtelConn();

            }


            status_id = "1";
            message_id = "1";

            currentdates();

            Date dat = new Date();



            //connect to local mnhc database
            conn = new dbConn();



            String todaysrecord = "select * from monitor_scheduling where date=DATE(CURDATE()) AND purpose='EDD NOTIFIER'";

            conn.rs_6 = conn.st_6.executeQuery(todaysrecord);

            if (conn.rs_6.next()) {

                //System.out.println("THE SMS DISBURSING SMS IS NOT AVAILABLE. A RESCHEDULE HAS TO BE DONE");
                System.out.println("___________EDD  SCHEDULE ALREADY DONE_____________");


            } else {

                System.out.println("______________EDD  SCHEDULE RUNNING_____________________");

                // if(isInternetReachable()){

                getmothers();

                // }           

            }
        } catch (SQLException ex) {
            Logger.getLogger(FL_EDD_EXECUTER.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //______________________________GETMOTHERS FUCTION___________________________
    public void getmothers() {


        Date todaysdate = new Date();


        String mymothers = "Select PhoneNo , NOKPhoneNo ,delivery_date,FName,nok ,nok_consent,languagePreferred from mother_details where status_id ='" + status_id + "'";


//get current calender date and compare it with the anc visit date.







        try {

//get the message that will be displayed to the user in the range of one 



            String amonthbefore = "select * from messages where message_id='5'";





            //execute the mothers query....  
            conn.rs = conn.st.executeQuery(mymothers);

            while (conn.rs.next()) {


                //get dates
                currentdates();

                //compare the dates now
                int dbaseyea = 0, dbasemonth = 0, dbasedate = 0;
                //  

                //GET THE EXPECTED DELIVERY DATE
                if (!conn.rs.getString("delivery_date").equals("") && conn.rs.getString("delivery_date") != null) {

                    //yyyy-mm-dd
                    String delivery_date = conn.rs.getString("delivery_date").toString().trim();


                    //--if date is separated with / or _, replace with -


                    if (delivery_date.contains("/")) {

                        delivery_date = delivery_date.replace("/", "-");

                    }

                    if (delivery_date.contains("_")) {

                        delivery_date = delivery_date.replace("_", "-");

                    }

                    //split the delivery date
                    String anc_dates[] = delivery_date.split("-");

                    dbaseyea = Integer.parseInt(anc_dates[0]);
                    dbasemonth = Integer.parseInt(anc_dates[1]);
                    dbasedate = Integer.parseInt(anc_dates[2]);
//               System.out.println("month difference:"+(month-dbasemonth));
//               System.out.println("date difference:"+(date-dbasedate));
//               

                }


//=================================== THE MONTHLY UPDATES HERE FOR A PREGNANT MOTHER================================================               


                if (date - dbasedate == 0) {


                    //if one month to delivery

                    if (((dbasemonth - month) == 1) || ((month - dbasemonth) == 11)) {

//**get the messages from mnhc database


                        conn.rs4 = conn.st4.executeQuery(amonthbefore);

                        conn.rs4.next();

                        //send user with message that their next anc visit is next week
                        //check if nok has accepted to have their no receive messages.
                        String msgtomother = conn.rs4.getString("salutation").trim() + " " + conn.rs.getString("FName").trim() + ", Your expected date of delivery is " + conn.rs.getString("delivery_date").trim() + ". " + conn.rs4.getString("message").trim() + ":FHI360 Koibatek Intervention";

                        String msgtoNOK = conn.rs4.getString("salutation").trim() + " " + conn.rs.getString("nok").trim() + ",  " + conn.rs.getString("FName").trim() + "s  expected date of delivery is " + conn.rs.getString("delivery_date").trim() + ". " + conn.rs4.getString("nok_message").trim() + ":FHI360 Koibatek Intervention";



                        if (conn.rs.getString("languagePreferred").contains("wahili")) {

                            msgtomother = conn.rs4.getString("salamu").trim() + " " + conn.rs.getString("FName").trim() + ", Siku yako tarajiwa ya kujifungua ni " + conn.rs.getString("delivery_date").trim() + ". " + conn.rs4.getString("ujumbe").trim() + ":FHI360 Koibatek Intervention";


                            msgtoNOK = conn.rs4.getString("salamu").trim() + " " + conn.rs.getString("nok").trim() + ",  siku tarajiwa ya " + conn.rs.getString("FName").trim() + " ya kujifungua ni " + conn.rs.getString("delivery_date").trim() + ". " + conn.rs4.getString("nok_ujumbe").trim() + ":FHI360 Koibatek Intervention";

                        }





                        //take care of 160 characters

//
//                        if (msgtomother.length() > 160) {
//                            msgtomother = msgtomother.substring(0, 160);
//
//                        }
//
//
//                        if (msgtoNOK.length() > 160) {
//                            msgtoNOK = msgtoNOK.substring(0, 160);
//
//                        }


                        //avoid apostrophe

                        if (msgtoNOK.contains("'")) {

                            msgtoNOK = msgtoNOK.replace("'", " ");
                        }


                        if (msgtomother.contains("'")) {

                            msgtomother = msgtomother.replace("'", " ");
                        }
                        //replace spaces with %20 

                        msgtoNOK = msgtoNOK.replace(" ", "%20");
                        msgtomother = msgtomother.replace(" ", "%20");


                        //_________________ADD PHONE NUMBERS____________________

                        String mumsno = conn.rs.getString(1).trim();

                        String noksno = conn.rs.getString(2).trim();

                        //get shortcode from messages table

                        String shortcode = conn.rs4.getString("shortcode").trim();

                        //send each message to adtel requests database..




                        if (mumsno.startsWith("254")) {
                            String rest_of_number = mumsno.substring(3);
                            mumsno = "0" + rest_of_number;


                        }



                        if (noksno.startsWith("254")) {
                            String rest_of_number = noksno.substring(3);
                            noksno = "0" + rest_of_number;

                        }


//==========MUMMSG

                        if (mumsno.startsWith("07") && mumsno.length() == 10) {

                            sendSMS(mumsno, msgtomother);

                            //add the same same messages to my send_sms table..
                            if (mumsno.startsWith("07") && mumsno.length() == 10) {

                                conn.st_1.executeUpdate("insert into send_sms set number='" + mumsno + "' , message='" + msgtomother + "',status='sent' ,shortcode='Fronline' , time='" + todaysdate + "', message_section='" + MONTHLYEDD + "' ,target_reached='yes'");

                            }


                            //aconn.st.executeUpdate("insert into request2 set Number='" + mumsno + "' , Message='" + msgtomother + "'");
                            System.out.println("EDD:  message send to mum:" + mumsno);

                        }


//======NOKMSG============


                        if (noksno.startsWith("07") && noksno.length() == 10) {

                            if (conn.rs.getString("nok_consent").trim().toUpperCase().equals("YES")) {
//aconn.st1.executeUpdate("insert into request2 set Number='" + noksno + "' , Message='" + msgtoNOK + "'");

                                sendSMS(noksno, msgtoNOK);


                                System.out.println("EDD: message send to nok: " + noksno);

                            }




                            if (noksno.startsWith("07") && noksno.length() == 10) {

                                //if the nok have allowed to receive sms

                                if (conn.rs.getString("nok_consent").trim().toUpperCase().equals("YES")) {

                                    conn.st_2.executeUpdate("insert into send_sms set number='" + noksno + "' , message='" + msgtoNOK + "', status='sent',shortcode='Frontline' ,time='" + todaysdate + "', message_section='" + MONTHLYEDD + "' ,target_reached='yes'");
                                }
                            }



                        }



                    } //send one months message


                }//end of if dates match with delivery date
                else {

                    System.out.println("For this user, not today");


                }



            }//end of while

//close all connections
//            aconn.st.close();
//            aconn.st1.close();


            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String mdate;

            Date mydate = new Date();
            mdate = formatter.format(mydate);

            //======show that this scheduler executed succesfully======             

            conn.st_2.executeUpdate("Insert into monitor_scheduling set purpose='EDD NOTIFIER',date='" + mdate + "',status='excecuted' ");
            // conn.st_2.close();



        } catch (SQLException ex) {
            Logger.getLogger(FL_EDD_EXECUTER.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//end of getmothers function

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

    public boolean sendSMS(String pn, String msg) {

        try {
// System.out.println("" +  addres.FrontlineAddress + pn + "/" + msg);
            //http://localhost:8011/send/sms/
            URL murl = new URL("" + addres.FrontlineAddress + pn + "/" + msg);
            HttpURLConnection connection = (HttpURLConnection) murl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            System.out.println("Sending EDD msg to " + pn);

             int code = connection.getResponseCode();
        } //_____________________________________________________________________________________________________________
        catch (IOException ex) {

            System.out.println("Error while sending Message to Frontline");
            return false;
        }

        return true;
    }
}
