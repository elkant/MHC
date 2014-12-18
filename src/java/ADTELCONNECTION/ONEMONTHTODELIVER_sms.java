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
public class ONEMONTHTODELIVER_sms extends HttpServlet {

 
  
    String status_id, message_id;
    int year, month, date, hour, min, sec,schedulingmin;
    adtelConn aconn;
    
    String MONTHLYEDD="MONTHLY EDD";
    

    protected void processRequest(HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

//=============connect to adtel dbase===========================
       
        aconn = new adtelConn();


      

        status_id = "1";
        message_id = "1";

             currentdates();

        Scheduler deliveryscheduler = new Scheduler();
        // Schedule a daily task.
        // s.schedule(""+min+" "+ hour+" * * 1-7", new Runnable() {
        
        deliveryscheduler.schedule("48 9 * * 1-7", new Runnable() {
            
     // s.schedule("* * * * *", new Runnable() {

            public void run() {
                //select query from dbase


        System.out.println("Another monthly shedule ticked away...");

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
        

String mymothers = "Select PhoneNo , NOKPhoneNo ,delivery_date,FName,nok ,nok_consent from mother_details where status_id ='" + status_id + "'";
        

//get current calender date and compare it with the anc visit date.




        //connect to local mnhc database
        dbConn conn = new dbConn();

    

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
                if (conn.rs.getString("delivery_date")!="" && conn.rs.getString("delivery_date") != null) {

                    //yyyy-mm-dd
                  String delivery_date=conn.rs.getString("delivery_date").toString().trim();
                    
                    
               //--if date is separated with / or _, replace with -
                    
                    
                    if(delivery_date.contains("/")){
                        
                      delivery_date=delivery_date.replace("/", "-");  
                  
                    }
                    
                    if(delivery_date.contains("_")){
                        
                      delivery_date=delivery_date.replace("_", "-");  
                  
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
               
                if (((dbasemonth - month)== 1)||((month-dbasemonth)==11)) {

//**get the messages from mnhc database


                    conn.rs4 = conn.st4.executeQuery(amonthbefore);

                    conn.rs4.next();

                    //send user with message that their next anc visit is next week
                    //check if nok has accepted to have their no receive messages.




                    String msgtomother =conn.rs4.getString("salutation").trim() + " " + conn.rs.getString("FName").trim() + ", Your expected date of delivery is " + conn.rs.getString("delivery_date").trim() + ". " + conn.rs4.getString("message").trim() + ":FHI360 Koibatek Intervention";

                    String msgtoNOK = conn.rs4.getString("salutation").trim() +" " + conn.rs.getString("nok").trim() + ",  " + conn.rs.getString("FName").trim() + "s  expected date of delivery is " + conn.rs.getString("delivery_date").trim() + ". " + conn.rs4.getString("nok_message").trim() + ":FHI360 Koibatek Intervention";


                    //take care of 160 characters


                    if (msgtomother.length() > 160) {
                        msgtomother = msgtomother.substring(0, 160);

                    }


                    if (msgtoNOK.length() > 160) {
                        msgtoNOK = msgtoNOK.substring(0, 160);

                    }

                    
                    //avoid apostrophe
                    
                    if(msgtoNOK.contains("'")){
                    
                    msgtoNOK=msgtoNOK.replace("'", " ");
                    }
                    
                    
                   if(msgtomother.contains("'")){
                    
                    msgtomother=msgtomother.replace("'", " ");
                    }
                     


                    //_________________ADD PHONE NUMBERS____________________

                    String mumsno = conn.rs.getString(1).trim();

                    String noksno = conn.rs.getString(2).trim();

                    //get shortcode from messages table

                    String shortcode = conn.rs4.getString("shortcode").trim();

                    //send each message to adtel requests database..
                    
                    
                    
                    
                      if (mumsno.startsWith("07")) {
                    String rest_of_number=mumsno.substring(2);
                    mumsno="2547"+rest_of_number;
                      
                         
                    }
                    
                    
                     
                     if (noksno.startsWith("07")) {
                    String rest_of_number=noksno.substring(2);
                    noksno="2547"+rest_of_number;  
                         
                    }
                    
                    


if(mumsno.startsWith("2547")&&mumsno.length()==12){
                    //aconn.st.executeUpdate("insert into request2 set Number='" + mumsno + "' , Message='" + msgtomother + "'");
System.out.println("EDD:  message send to mum:"+mumsno);

}

if(noksno.startsWith("2547")&&noksno.length()==12){
    
 if(conn.rs.getString("nok_consent").trim().toUpperCase().equals("YES")){   
//aconn.st1.executeUpdate("insert into request2 set Number='" + noksno + "' , Message='" + msgtoNOK + "'");

System.out.println("EDD: message send to nok: "+noksno);
 }
}



//add the same same messages to my send_sms table..
if(mumsno.startsWith("2547")&&mumsno.length()==12){
    
                    conn.st_1.executeUpdate("insert into send_sms set number='" + mumsno + "' , message='" + msgtomother + "',status='pending' ,shortcode='" + shortcode + "' , time='" +todaysdate + "', message_section='"+MONTHLYEDD+"'");

}

if(noksno.startsWith("2547")&&noksno.length()==12){
    
    //if the nok have allowed to receive sms
    
    if(conn.rs.getString("nok_consent").trim().toUpperCase().equals("YES")){
        
     conn.st_2.executeUpdate("insert into send_sms set number='" + noksno + "' , message='" + msgtoNOK + "', status='pending',shortcode='" + shortcode + "' ,time='" + todaysdate + "', message_section='"+MONTHLYEDD+"'");
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




            //======show that this scheduler executed succesfully======             

            conn.st_2.executeUpdate("Insert into monitor_scheduling set purpose='Monthly Notifier',date='" + todaysdate + "',status='excecuted' ");
           // conn.st_2.close();






        } catch (SQLException ex) {
            Logger.getLogger(ONEMONTHTODELIVER_sms.class.getName()).log(Level.SEVERE, null, ex);
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
