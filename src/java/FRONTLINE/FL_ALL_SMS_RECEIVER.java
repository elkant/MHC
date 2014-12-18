/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FRONTLINE;

import java.awt.Toolkit;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
public class FL_ALL_SMS_RECEIVER extends HttpServlet {

    String sender_no;
    String msg;
    int year, month, date, hour, min, sec, schedulingmin;
    String smssend = "false";
    //used to identify the messages headed to a mother
    String CHWTOMOTHEREXPRESSION = "@";
    String NEXTOFKEENCHWHEADER = "Message from ";
    String CHWTOMOTHER = "CHW TO MOTHER", MOTHERTOCHW = "MOTHER TO CHW";
    String CHW_HAS_REPLIED="has replied";
    dbConn conn;
    String shortcode = "Frontline";
    FL_CONNECTION addres = new FL_CONNECTION();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

System.out.println("________ALL SMS RECEIVER CALLED________");

        sender_no = request.getParameter("sender");


        msg = request.getParameter("msg");

        conn = new dbConn();


        String myreturn = "";


        if (msg.trim().startsWith(CHWTOMOTHEREXPRESSION)) {

            //==this messages are from the chw to a mother..should go to mother
            myreturn = CHWTOMOTHER(sender_no, msg, shortcode);

        } else {

            //message should go to chw 
            myreturn = MOTHERTOCHW(sender_no, msg, shortcode);


        }



    }//end of process request

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

    public String MOTHERTOCHW(String sender_no, String msg, String shortcode) {


        smssend = "false";
        try {




            msg = msg.replace("'", "%20");

            if (sender_no.startsWith("+254")) {

                sender_no = sender_no.replace("+254", "0");

            }

           

            //adtelConn aconn = new adtelConn();





            //if mother uses another number which is not registered, let them begin by writting 
            //their registered number first, then the message...
            //eg. 0712256**3 I am in pain

            String nonregisterednumber = "";

            if (msg.trim().startsWith("07")) {

                nonregisterednumber = msg.trim().substring(1, 10);
                nonregisterednumber="254"+nonregisterednumber;

            }

 String tempphoneno="";
//for now, change the phone number to be 12 didgits
if(sender_no.startsWith("07")){
    
     tempphoneno="254"+sender_no.substring(1);
}
 String check_chw;

 if(nonregisterednumber.equals("")){
 
 check_chw = "Select ChwID,FName,SName,motherID from mother_details where PhoneNo='" + tempphoneno + "' OR NOKPhoneNo='" + tempphoneno + "' ";
 
 }
 else{
  
     System.out.println(" this is the number"+nonregisterednumber);
     
  check_chw = "Select ChwID,FName,SName,motherID from mother_details where PhoneNo='" + nonregisterednumber + "' OR NOKPhoneNo='" + nonregisterednumber + "'";

 }
 

            conn.rs = conn.st.executeQuery(check_chw);
            if (conn.rs.next() && !msg.startsWith("@")) {

                 //System.out.println("inside loop" + sender_no);

                
                //save the details of the sender of messages to a table.               

                String addtoreplyto = "Insert into reply_to SET reply_to_number='" + tempphoneno + "' , mother_id='" + conn.rs.getString("motherID") + "'";

                conn.st4.executeUpdate(addtoreplyto);


System.out.println("");



                //======get chw details

                conn.rs1 = conn.st1.executeQuery("Select * from chw where chw_id='" + conn.rs.getString(1) + "'");
                conn.rs1.next();

                String sender_name = conn.rs.getString("FName").toString().trim() + " " + conn.rs.getString("SName").toString().trim() + " ";
                String chv_phone = conn.rs1.getString("chv_phone").toString().trim();
                String motherid = conn.rs.getString("motherID").toString().trim();

                //=====MESSAGE      

                String msgtochw = "FROM " + sender_name + "(mother id:" + motherid + ") " + msg + ":FHI360 Koibatek Intervention";



                //if mothers question is long, split it into two then send it to chw

                msgtochw = msgtochw.replace(" ", "%20");







                //====((((((((SEND SMS TO USERS))))))))))===============//



                if (chv_phone.startsWith("254") && chv_phone.length() == 12) {


                   

                    // aconn.st.executeUpdate("insert into request2 set Number='" + chv_phone + "' , Message='" +allsms.get(a)+ "'");

                    System.out.println("QSTN TO CHW:  message send to chw:" + chv_phone + "   " + msgtochw);

                    Toolkit.getDefaultToolkit().beep();

                    //set the logger 

                    if (chv_phone.startsWith("254") && chv_phone.length() == 12 && sendSMS(chv_phone, msgtochw) == true) {



                        Date dat = new Date();

                        conn.st_1.executeUpdate("insert into send_sms set number='" + chv_phone + "' , message='" + msgtochw + "',status='send' ,shortcode='frontline' ,time='" + dat + "', message_section='mother_to_chw' , target_reached='yes'");

                        System.out.println("Logging done");




                    }


                    smssend = "true";



                }











            }//end of if. conn.rs.next
            else {


                //if none of the mother got those details..
                Date dat = new Date();


                //Save a message to send sms with target_reachesd value set to false

                conn.st_1.executeUpdate("insert into send_sms set number='" + sender_no + "' , message='" + msg + "',status='pending' ,shortcode='Frontline' ,time='" + dat + "', message_section='" + MOTHERTOCHW + "',target_reached='NO'");

                // System.out.println("");
                smssend = "true";

            }




            //allsms.clear();



            /**
            
            
            
            
             */
        }//end of try
        catch (SQLException ex) {
            Logger.getLogger(FL_ALL_SMS_RECEIVER.class.getName()).log(Level.SEVERE, null, ex);

            //if an exception occured, set status of sms as not set.

            smssend = "false";


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
    public String CHWTOMOTHER(String sender_no, String msg, String shortcode) {





        smssend = "false";
        try {


      if(sender_no.startsWith("+")){
      
      sender_no=sender_no.replace("+", "");
      }    



            msg = msg.replace("'", "");
            msg = msg.replace(" ", "%20");

          

System.out.println("Reply message from "+sender_no);

            String smsarray[];

            smsarray = msg.split(CHWTOMOTHEREXPRESSION, 3);


            //System.out.println("" + sender_no);

            String check_mother = "Select ChwID, PhoneNo , NOKPhoneNo ,FName,SName, nok_consent from mother_details where motherID='" + smsarray[1] + "'";

            //String check_chw = "Select ChwID,FName,SName,motherID from mother_details where PhoneNo='" + sender_no + "' OR NOKPhoneNo='" + sender_no + "' OR PhoneNo='" + nonregisterednumber + "' OR NOKPhoneNo='" + nonregisterednumber + "'";

            conn.rs_6 = conn.st_6.executeQuery(check_mother);

            //conn.rs = conn.st.executeQuery(check_chw);
            if (conn.rs_6.next()) {

                //====get CHW PHONE NUMBER=======


                conn.rs_5 = conn.st_5.executeQuery("Select * from chw where chv_phone='" + sender_no + "'");
                conn.rs_5.next();




                //===============get the names of the CHV============================




                String sender_name = conn.rs_5.getString(2).toString().trim() + " " + conn.rs_5.getString(3).toString().trim() + "";

                
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
                String mother_phone = "";
                String msgtomom = "";
                //===============GET THE NUMBER TO REPLY TO=======================================


                conn.rs_4 = conn.st_4.executeQuery("SELECT MAX(tableid) from reply_to where mother_id='" + smsarray[1] + "' AND status='0'");
                if (conn.rs_4.next()) {

                    String MAXIMUMTABLEID = conn.rs_4.getString(1).trim().toString();

                    String get_reply_to_number = "select reply_to_number FROM reply_to where tableid='" + MAXIMUMTABLEID + "'";

                    conn.rs_3 = conn.st_3.executeQuery(get_reply_to_number);


                    //====get number from the reply_to tables

                    if (conn.rs_3.next()) {

                        mother_phone = conn.rs_3.getString("reply_to_number");


                        msgtomom = NEXTOFKEENCHWHEADER + " " + sender_name + ": \"" + "" + smsarray[2] + "\"";
//              
//       
                        //update the status of that id to yes


                        conn.st_2.executeUpdate("update reply_to set status='1' where tableid='" + MAXIMUMTABLEID + "'");




                    }

                } else {

                    mother_phone = conn.rs_6.getString("PhoneNo").toString().trim();

                    msgtomom = NEXTOFKEENCHWHEADER + " " + sender_name + ": \"" + "" + smsarray[2] + "\"";

                    //if the mother has got no set number, then we get the noks no on condition that the they have consented...

                    if ( mother_phone == null|| mother_phone.equals("")) {
                        //check if the nok has consented
                        //if yes get 

                        if (conn.rs_6.getString("nok_consent").equalsIgnoreCase("YES")) {

                            mother_phone = conn.rs_6.getString("NOKPhoneNo");
                            msgtomom = NEXTOFKEENCHWHEADER + " " + conn.rs_6.getString("FNAME") + ", " + sender_name + " "+CHW_HAS_REPLIED+" \"" + "" + smsarray[2] + "\"";
                            //get the message header here..


                        }


                    }



                }

                //=======================WE NOW HAVE THE MOTHER NUMBER AND MESSAGE  UP TO  HERE===========================


                msgtomom = msgtomom.replace(" ", "%20");






                //if mothers question is long, split it into two then send it to chw






                //====((((((((SEND SMS TO USERS))))))))))===============//



                if ( mother_phone.length() >4) {

                    //aconn.st1.executeUpdate("insert into request set Number='" + nokno + "' , Message='" + nokmsg + "', shortcode='" + shortcode + "'");


                    sendSMS(mother_phone, msgtomom);

                    //(((((((((((((((((((((((((((((((((((((SMS TO MOTHER VIA FRONTLINE))))))))))))))))))))))))))))))))))))))))))) 



// aconn.st.executeUpdate("insert into request2 set Number='" + mother_phone + "' , Message='" +allsms1.get(a)+ "'");

                    Toolkit.getDefaultToolkit().beep();


                    //(((((((((((((((((((((((((((((((((((((((((((((())))))))))))))))))))))))))))))))))))))))))))))))))))))          




                    System.out.println("RESPONSE TO MOTHER :  message send to mother:" + mother_phone + "   " + msgtomom);



                    smssend = "true";






//logging

                 



                        Date dat = new Date();

                        conn.st_1.executeUpdate("insert into send_sms set number='" + mother_phone + "' , message='" + msgtomom + "',status='sent' ,shortcode='" + shortcode + "' ,time='" + dat + "', message_section='" + CHWTOMOTHER + "',target_reached='YES'");

                        System.out.println("Logging done");

                   


                }







            }//end of if. conn.rs.next
            //if no mother was found to own that number,then save to a debug area
            else {


                //if none of the mother got those details..
                Date dat = new Date();


                //Save a message to send sms with target_reachesd value set to false

                conn.st_1.executeUpdate("insert into send_sms set number='" + sender_no + "' , message='" + msg + "',status='pending' ,shortcode='" + shortcode + "' ,time='" + dat + "', message_section='" + CHWTOMOTHER + "',target_reached='NO'");


            }

            // allsms1.clear();



            /**
            
            
            
            
             */
        }//end of try
        catch (SQLException ex) {
            Logger.getLogger(FL_ALL_SMS_RECEIVER.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
            //if an exception occured, set status of sms as not set.

            smssend = "false";


        }










        return smssend;
    }

//==============================end of chw to mother=====================================================    
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
            System.out.println("No internet Connection");
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("No internet Connection");
            return false;
        }
        return true;
    }

    public boolean sendSMS(String pn, String msg) {
        
//System.out.println("Sending msg to" + pn);
        
        
        try {
            //http://localhost:8011/send/sms/
            //replace addres.Frontline with the above address

            URL murl = new URL("" + addres.FrontlineAddress + pn + "/" + msg);
            HttpURLConnection connection = (HttpURLConnection) murl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            System.out.println("Sending msg to " + pn);

             int code = connection.getResponseCode();
        } //_____________________________________________________________________________________________________________
        catch (IOException ex) {

            System.out.println("Error while sending Message to Frontline");
            return false;
        }

        return true;
    }
}
