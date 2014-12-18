/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ADTELCONNECTION;

import it.sauronsoftware.cron4j.Scheduler;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
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
public class ANCVISITS_sms extends HttpServlet {

    String status_id, message_id;
    int year, month, date, hour, min, sec, schedulingmin;
    adtelConn aconn;
    
    
    String WEEKLYANC="WEEKLY ANC VISITS",DAILYANC="DAILY ANC VISITS";

    protected void processRequest(HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

//=============connect to adtel dbase===========================

        aconn = new adtelConn();



//status 1 is for pregnant mothers
        
        status_id = "1";
        
        
        message_id = "1";

        currentdates();

        System.out.println("execution time "+hour+":"+schedulingmin);
        
        
        Scheduler deliveryscheduler = new Scheduler();
        // Schedule a daily task.
        deliveryscheduler.schedule(""+schedulingmin+" "+ hour+" * * 1-7", new Runnable() {

        //deliveryscheduler.schedule("40 7 * * 1-7", new Runnable() {

            // s.schedule("* * * * *", new Runnable() {
            public void run() {
                //select query from dbase


                System.out.println("Another ANC shedule ticked away...");

                /**get all users who qualify for the text..

                 * Send them text messages 

                 * Keep a log of the messages send to a local copy
                 * 
                 * 


                 */
                getmothers();


            }//end of run
        });
        // Starts the scheduler.
        deliveryscheduler.start();
        // Will run for ten minutes.
        try {
            Thread.sleep(1000L * 60L * 10L);
        } catch (InterruptedException e) {
        }
        // Stops the scheduler.
        deliveryscheduler.stop();


    }

    //______________________________GETMOTHERS FUCTION___________________________
    public void getmothers() {

        
        
        Date todaysdate= new Date();
        
        
        String mymothers = "Select PhoneNo , NOKPhoneNo ,Anc_visit,FName,nok,nok_consent from mother_details where status_id ='" + status_id + "'";


//get current calender date and compare it with the anc visit date.




        //connect to local mnhc database
        dbConn conn = new dbConn();



        try {

//get the message that will be displayed to the user in the range of one 

            String anconeweekmsg = "select * from messages where message_id='3'";

            String anconedaymsg = "select * from messages where message_id='2'";

            // String amonthbefore = "select * from messages where message_id='5'";

            String oneday = "select * from messaging_days where id='1'";

            String oneweek = "select * from messaging_days where id='2'";



            //execute the mothers query....  
            conn.rs = conn.st.executeQuery(mymothers);

            while (conn.rs.next()) {


                //get dates
                currentdates();

                //compare the dates now
                int dbaseyea = 0, dbasemonth = 0, dbasedate = 0;
                //  

                //GET THE NEXT ANC VISIT
                if (!conn.rs.getString("Anc_visit").equals("") && conn.rs.getString("Anc_visit") != null) {

                    //yyyy-mm-dd
                    String anc_day = conn.rs.getString("Anc_visit").toString().trim();


                    //--if date is separated with / or _, replace with -


                    if (anc_day.contains("/")) {

                        anc_day = anc_day.replace("/", "-");

                    }

                    if (anc_day.contains("_")) {

                        anc_day = anc_day.replace("_", "-");

                    }

                    //split the delivery date
                    if (anc_day.contains("-")) {
                        String anc_dates[] = anc_day.split("-");

                        dbaseyea = Integer.parseInt(anc_dates[0]);
                        dbasemonth = Integer.parseInt(anc_dates[1]);
                        dbasedate = Integer.parseInt(anc_dates[2]);
                    }
//               System.out.println("month difference:"+(month-dbasemonth));
//               System.out.println("date difference:"+(date-dbasedate));
//               

                }


//=================================== ANC VISITS HERE================================================               


                if ((year == dbaseyea && month == dbasemonth) || (year != dbaseyea && dbasemonth > month) || (year == dbaseyea && dbasemonth > month)) {


                    conn.rs1 = conn.st1.executeQuery(oneday);
                    conn.rs1.next();



                    //rs an st for one week  
                    conn.rs3 = conn.st3.executeQuery(oneweek);
                    conn.rs3.next();



                    //if one day to go, then fetch the one day to go message

                    if ((dbasedate - date) == Integer.parseInt(conn.rs1.getString(2))) {



                        /**           *    if wel include an option of many languages, 
                         *    then at this point wel get the user preffered language first, * 
                         *    which will tell us which msg to get                           **/
                        //===============================GET USER FAVOURATE LANGUAGE HERE================================
                        //===========================================================================================                   
                        //for now get english sms
                        conn.rs2 = conn.st2.executeQuery(anconedaymsg);

                        conn.rs2.next();

                        //send user with message that their next anc visit is tommorrow
                        
                        
                        
                        
                        //sms to mum
                        String mommsgs = conn.rs2.getString("salutation") + " " + conn.rs.getString("FName").trim() + ", " + conn.rs2.getString("message") + " which is scheduled on " + conn.rs.getString("Anc_visit") + ":FHI360 Koibatek Intervention";
                        //System.out.println(" one day msg"+msgs);
                        mommsgs = mommsgs.replace("'", "");

                        
                        
                        
                        
                        //sms to nok                
                        String nokmsg = conn.rs2.getString("salutation") + " " + conn.rs.getString("nok").trim() + ", " + conn.rs.getString("FName").trim() + " " + conn.rs2.getString("nok_message") + " which is scheduled on " + conn.rs.getString("Anc_visit") + ".Kindly remind them:FHI360 Koibatek Intervention";
                        //System.out.println(" one day msg"+msgs);
                        nokmsg = nokmsg.replace("'", "");



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
                        
                 
                  
                    
                           //get shortcode from messages table

                    String shortcode = conn.rs2.getString("shortcode").trim();

                    //send each message to adtel requests database..
                    
                    
                    
                      if (mumno.startsWith("07")) {
                    String rest_of_number=mumno.substring(2);
                    mumno="2547"+rest_of_number;
                      
                         
                    }
                    
                    
                     
                     if (nokno.startsWith("07")) {
                    String rest_of_number=nokno.substring(2);
                    nokno="2547"+rest_of_number;  
                         
                    }
                    
                    

//msg to mum
                    if (mumno.startsWith("2547")&&mumno.length()==12) {
                       // aconn.st.executeUpdate("insert into request set Number='" + mumno + "' , Message='" + mommsgs + "', shortcode='" + shortcode + "'");
  aconn.st.executeUpdate("insert into request2 set Number='" + mumno + "' , Message='" + mommsgs + "'");
                        System.out.println("adtel:one day rem :mom message send to " + mumno+"  MSG:"+mommsgs);

                    }

                    
                    
                    if (nokno.startsWith("2547")&&nokno.length()==12) {
                        
                       
                        
                        
                        if(conn.rs.getString("nok_consent").trim().toUpperCase().equals("YES")){
                        //aconn.st1.executeUpdate("insert into request set Number='" + nokno + "' , Message='" + nokmsg + "', shortcode='" + shortcode + "'");
                        aconn.st.executeUpdate("insert into request2 set Number='" + nokno + "' , Message='" + nokmsg + "'");
                       
                          System.out.println("adtel:one day rem :NOK message send to " + nokno+"  MSG:"+nokmsg);

                        }
                    }



//add the same same messages to my send_sms table for reports purpose....
                    
                    
                    if (mumno.startsWith("2547")&&mumno.length()==12) {
                        conn.st_1.executeUpdate("insert into send_sms set number='" + mumno + "' , message='" + mommsgs + "',status='pending' ,shortcode='" + shortcode + "' , time='" + todaysdate + "' , message_section='"+DAILYANC+"'");
                   
                     //  System.out.println("mnhc send_sms:one day rem :mom message send to " + mumno+"  MSG:"+mommsgs);

                    }

                    if (nokno.startsWith("2547")&&nokno.length()==12) {
                         if(conn.rs.getString("nok_consent").trim().toUpperCase().equals("YES")){ 
                             
                        conn.st_2.executeUpdate("insert into send_sms set number='" + nokno + "' , message='" + nokmsg + "', status='pending',shortcode='" + shortcode + "' ,time='" + todaysdate + "', message_section='"+DAILYANC+"'");
                   
                      //  System.out.println("mnhc send_sms:one day rem :nok message send to " + nokno+"  MSG:"+nokmsg);

                         
                         }
                    }
                    
                    
                   
       } //if one day               
                                       
                    
                    
 //(((((((((((((((((((((((((((((((((((((((ONE DAY ENDS HERE, NOW ONE WEEK)))))))))))))))))))))))))))))))))))))))))))))))))                   
                    
                    
                    
                    //if for one week
                    
       else if ((dbasedate - date)==Integer.parseInt(conn.rs3.getString(2)) || ((30 - date) + dbasedate) == Integer.parseInt(conn.rs3.getString(2))) {

                        conn.rs2 = conn.st2.executeQuery(anconeweekmsg);

                        conn.rs2.next();
                        //send user with message that their next anc visit is next week
                                               
                        
                        //sms to mum
                        String mommsgs = conn.rs2.getString("salutation") + " " + conn.rs.getString("FName").trim() + ", " + conn.rs2.getString("message") + " which is scheduled on " + conn.rs.getString("Anc_visit") + " :FHI360 Koibatek Intervention";
                        //System.out.println(" one day msg"+msgs);
                        mommsgs = mommsgs.replace("'", "");

                        
                        
                        
                        //sms to nok                
                        String nokmsg = conn.rs2.getString("salutation") + " " + conn.rs.getString("nok").trim() + ", " + conn.rs.getString("FName").trim() + " " + conn.rs2.getString("nok_message") + " which is scheduled on " + conn.rs.getString("Anc_visit") + ".Kindly remind them.:FHI360 Koibatek Intervention";
                        //System.out.println(" one day msg"+msgs);
                        nokmsg = nokmsg.replace("'", "");



                        //_________________PHONE NUMBERS____________________
                        String oneweekmumno = conn.rs.getString(1).trim();


                        String oneweeknokno = conn.rs.getString(2).trim();


                        
                        
                       //take care of 160 characters


                    if (mommsgs.length() > 160) {
                        mommsgs = mommsgs.substring(0, 160);

                    }


                    if (nokmsg.length() > 160) {
                        
                        nokmsg = nokmsg.substring(0, 160);

                    }  
                        
                 
                  
                    
                           //get shortcode from messages table

                    String shortcode = conn.rs2.getString("shortcode").trim();

                    //send each message to adtel requests database..

                    
                     if (oneweekmumno.startsWith("07")) {
                    String rest_of_number=oneweekmumno.substring(2);
                    oneweekmumno="2547"+rest_of_number;
                      
                         
                    }
                    
                    
                     
                     if (oneweeknokno.startsWith("07")) {
                    String rest_of_number=oneweeknokno.substring(2);
                    oneweeknokno="2547"+rest_of_number;  
                         
                    }
                     
                     
                    

                    if (oneweekmumno.startsWith("2547")&&oneweeknokno.length()==12) {
                       // aconn.st.executeUpdate("insert into request set Number='" + oneweekmumno + "' , Message='" + mommsgs + "', shortcode='" + shortcode + "'");
                       
                       //// aconn.st.executeUpdate("insert into request2 set Number='" + oneweekmumno + "' , Message='" + mommsgs + "'");
                      
                      System.out.println("adtel:one week rem :mom message send to " + oneweekmumno+"  MSG:"+mommsgs);


                    }
                    
                    

                    if (oneweeknokno.startsWith("2547")&&oneweeknokno.length()==12) {
                        
                        System.out.println("one week:"+conn.rs.getString("nok_consent").trim().toUpperCase());
                        
                        if(conn.rs.getString("nok_consent").trim().toUpperCase().equals("YES")){ 
                      //  aconn.st1.executeUpdate("insert into request set Number='" + nokno + "' , Message='" + nokmsg + "', shortcode='" + shortcode + "'");

     aconn.st.executeUpdate("insert into request2 set Number='" + oneweeknokno + "' , Message='" + nokmsg + "'");
                      
        System.out.println("adtel:one week rem :nok message send to " + oneweeknokno+"  MSG:"+nokmsg);

                        }
                    }



//add the same same messages to my send_sms table for reports purpose....
                    
                    
                    if (oneweekmumno.startsWith("2547")&&oneweekmumno.length()==12) {
                        conn.st_1.executeUpdate("insert into send_sms set number='" + oneweekmumno + "' , message='" + mommsgs + "',status='pending' ,shortcode='" + shortcode + "' ,time='"+todaysdate + "', message_section='"+WEEKLYANC+"'");
                  
                
                       // System.out.println("mnhc send_sms:one week rem :mom message send to " + oneweeknokno+"  MSG:"+nokmsg);
    
                    
                    }

                  if (oneweeknokno.startsWith("2547")&&oneweeknokno.length()==12) {
                     if(conn.rs.getString("nok_consent").trim().toUpperCase().equals("YES")){ 
                        conn.st_2.executeUpdate("insert into send_sms set number='" + oneweeknokno + "' , message='" + nokmsg + "', status='pending',shortcode='" + shortcode + "' ,time='" + todaysdate + "' , message_section='"+WEEKLYANC+"'");
                   
                     
                       // System.out.println("mnhc send_sms:one week rem :nok message send to " + oneweeknokno+"  MSG:"+nokmsg);

                     
                     }
                    }

                    

                    }





                  


        
}//end of while
//close all connections





            //======show that this scheduler executed succesfully======             

            conn.st_2.executeUpdate("Insert into monitor_scheduling set purpose='DAILY ANC Notifier',date='" +todaysdate + "', status='excecuted' ");
          //  conn.st_2.close();











    }//end of getmothers function


}
        catch (SQLException ex) {
            Logger.getLogger(ANCVISITS_sms.class.getName()).log(Level.SEVERE, null, ex);
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
        schedulingmin=cal.get(Calendar.MINUTE)+2;
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
