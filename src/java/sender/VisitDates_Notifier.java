/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import it.sauronsoftware.cron4j.Scheduler;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
public class VisitDates_Notifier extends HttpServlet {

    ArrayList phonenos = new ArrayList();
    ArrayList phonenos1 = new ArrayList();
    ArrayList monthlypn= new ArrayList();
    ArrayList monthlypn1= new ArrayList();
    
    ArrayList url = new ArrayList();
    ArrayList msg = new ArrayList();
    ArrayList monthlymsg = new ArrayList();
    
    String status_id, message_id;
 int year,month,date,hour,min;
    protected void processRequest(HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Calendar cal= Calendar.getInstance();
        
        year=cal.get(Calendar.YEAR);
       month=cal.get(Calendar.MONTH)+1;
       date=cal.get(Calendar.DAY_OF_MONTH);
        hour=cal.get(Calendar.HOUR_OF_DAY); 
       min=cal.get(Calendar.MINUTE)+1;  
        
        
        
        
        if (phonenos.size() > 0) {
            phonenos.clear();

        }
  if (phonenos1.size() > 0) {
            phonenos1.clear();

        }
    if (msg.size() > 0) {
            msg.clear();

        }

if (url.size() > 0) {
           url.clear();

        }

        status_id = "1";
        message_id = "1";



        Scheduler s = new Scheduler();
        // Schedule a daily task.
        //s.schedule(""+min+" "+ hour+" * * 1-7", new Runnable() {
     s.schedule("45 20 * * 1-7", new Runnable() {
     // s.schedule("* * * * *", new Runnable() {

            public void run() {
                //select querr from dbase




                System.out.println("Another minute ticked away...");
                
                //get all users who qualify for the text..
                getmothers();

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++





                for (int p = 0; p < url.size(); p++) {
                    
                    System.out.println(""+url.get(p));
                    
                    
                    try {
                        
                        URL murl = new URL(""+url.get(p));
                        HttpURLConnection connection = (HttpURLConnection)murl.openConnection();
                        connection.setRequestMethod("GET");
                        connection.connect();

                        int code = connection.getResponseCode();
                    }
                    //_____________________________________________________________________________________________________________
                    catch (IOException ex) {
                        Logger.getLogger(VisitDates_Notifier.class.getName()).log(Level.SEVERE, null, ex);
                    }
               

                    

                }


//_____________________________________________________________________________________________________________                                
            }
        });
        // Starts the scheduler.
        s.start();
        // Will run for ten minutes.
        try {
            Thread.sleep(1000L * 60L * 10L);
        } catch (InterruptedException e) {
            ;
        }
        // Stops the scheduler.
        s.stop();


    }

    //______________________________GET MOTHERS PHONENUMBERS___________________________
    public void getmothers() {


//        String mymothers = "Select PhoneNo , PhoneNo1 ,Anc_visit from mother_details where status_id ='" + status_id + "'";
        String mymothers = "Select PhoneNo , NOKPhoneNo ,Anc_visit,FName from mother_details where status_id ='" + status_id + "'";
        //get current calender date and compare it with the anc visit date.
        
        
        
        
        //connect to database
        dbConn conn = new dbConn();
        
           String msgs = "",msgs1="";
        
        try {
//get the message that will be displayed to the user in the range of one 
 //????????????????????????????????????????????????????????????????????????????????
  String anconeweekmsg="select * from messages where message_id='3'";          
            
  String anconedaymsg="select * from messages where message_id='2'";            
            
  String amonthbefore="select * from messages where message_id='5'"; 
            
//Get the range to compare i.e if 7 days to time of visit 
//????????????????????????????????????????????????????????????????????????????????            
  String oneday="select * from messaging_days where id='1'";          
            
    String oneweek="select * from messaging_days where id='2'";          
            
            
            
            
          //execute the mothers querr....  
            conn.rs = conn.st.executeQuery(mymothers);

            while (conn.rs.next()) {
                
                
                
                //compare the dates now
              int dbaseyea=0,dbasemonth=0,dbasedate=0;
              //  
              
              
                if(conn.rs.getString("Anc_visit")!=""&&conn.rs.getString("Anc_visit")!=null){
                              
                //yyyy-mm-dd
                String anc_dates[]=null;
                
                //splil the dates
                
                if(conn.rs.getString("Anc_visit").contains("-")){
                anc_dates=conn.rs.getString("Anc_visit").split("-");
                }
                else if(conn.rs.getString("Anc_visit").contains("/")){
                anc_dates=conn.rs.getString("Anc_visit").split("/");
                }
                
                if(anc_dates.length==3){
                dbaseyea=Integer.parseInt(anc_dates[0]);
                dbasemonth=Integer.parseInt(anc_dates[1]);
                dbasedate=Integer.parseInt(anc_dates[2]);
                }
                //System.out.println("dbase yea"+dbaseyea);
                
                 }       
              //if current year ,month and database year,month match, then go ahead and make a comparison
               if((year==dbaseyea&&month==dbasemonth)||(year!=dbaseyea&&dbasemonth>month)||(year==dbaseyea&&dbasemonth>month)){
                   
               System.out.println("dbase yea"+dbaseyea);    
                   //System.out.println(" same month and year if");
                
                   
               //check if you are one day to time
               conn.rs1=conn.st1.executeQuery(oneday);
               conn.rs1.next();
              //rs an st for one week  
               conn.rs3=conn.st3.executeQuery(oneweek);
               conn.rs3.next();
               
               //if one day to go, then fetch the one day to go message
               if((dbasedate-date)==Integer.parseInt( conn.rs1.getString(2))){
 //if wel include an option of many languages, then at this point wel get the user preffered language first,  which will tell us which msg to get              
  //===============================GET USER FAVOURATE LANGUAGE HERE================================
                   
                   
 //===========================================================================================                   
                   
                   //for now get english sms
                   conn.rs2=conn.st2.executeQuery(anconedaymsg);
               
                   conn.rs2.next();
               //send user with message that their next anc visit is tommorrow
               msgs="Reminder: Dear "+conn.rs.getString("FName").trim()+", "+conn.rs2.getString(2)+" which is scheduled on "+conn.rs.getString("Anc_visit")+":FHI360 Koibatek Intervention";
                System.out.println(" one day msg"+msgs);
                 msgs=msgs.replace(" ","%20");
              msg.add(msgs);  
                
                 //_________________ADD PHONE NUMBERS____________________
               phonenos.add(conn.rs.getString(1));
                phonenos1.add(conn.rs.getString(2));
                
                System.out.println("" + conn.rs.getString(2));
                System.out.println("" + conn.rs.getString(1));
                //get the first line only
                   // break;
               
               
               }
               //if one week                                                         //if for one month
               else if((dbasedate-date)==Integer.parseInt( conn.rs3.getString(2))||((30-date)+dbasedate)==Integer.parseInt( conn.rs3.getString(2))){
               
              conn.rs2=conn.st2.executeQuery(anconeweekmsg);
               
                   conn.rs2.next();
                //send user with message that their next anc visit is next week
               msgs1="Reminder:Dear "+conn.rs.getString("FName").trim()+", "+conn.rs2.getString(2)+" which is scheduled on "+conn.rs.getString("Anc_visit")+":FHI360 Koibatek Intervention"; 
                System.out.println(" one week msg"+msgs);
                 msgs1=msgs1.replace(" ","%20");
                     msg.add(msgs1); 
                 //_________________ADD PHONE NUMBERS____________________
                     
               phonenos.add(conn.rs.getString(1));
               
               phonenos1.add(conn.rs.getString(2));
               
               System.out.println("" + conn.rs.getString(2));
               System.out.println("" + conn.rs.getString(1));
                //get the first line only
                   // break;
               
                
               }
//               else{
//               
//               msgs="Your next ANC Visit Date is not well known. please ensure you confirm from the nurses";
//               System.out.println(" else ... where no one phoneno qualifies");
//               
//               
//               
//               //_________________ADD PHONE NUMBERS____________________
//               phonenos.add(conn.rs.getString(1));
//                phonenos.add(conn.rs.getString(2));
//                System.out.println("" + conn.rs.getString(2));
//                System.out.println("" + conn.rs.getString(1));
//                //get the first line only
//                    break;
//               
//               }
              
                   
                   
                   
               
               }//end of anc visit date if
               
//===================================INCLUDE THE MONTHLY UPDATES HERE FOR A PREGNANT MOTHER================================================               
//           if(date>=dbasedate&&(month-dbasemonth)==1){
//           
//           
//            conn.rs4=conn.st4.executeQuery(amonthbefore);
//               
//                   conn.rs4.next();
//                //send user with message that their next anc visit is next week
//               msgs1=conn.rs4.getString(2); 
//                System.out.println(" one month msg"+msgs);
//                 msgs1=msgs1.replace(" ","%20");
//                     monthlymsg.add(msgs1); 
//                 //_________________ADD PHONE NUMBERS____________________
//                     
//               monthlypn.add(conn.rs.getString(1));
//               
//               monthlypn1.add(conn.rs.getString(2));
//               
//           
//           
//           
//           }     
               //send one months message 
                
                
                
                
            }
            //

         



            //get the message that should be send.
/**
            String getmsg = "Select * from messages where message_id='" + message_id + "'";


            conn.rs2 = conn.st2.executeQuery(getmsg);


            while (conn.rs2.next()) {

                msgs = "" + conn.rs2.getString(2);
             * 
             *  }
                */
                
             
                
                
if(!msgs.equals("")){
           
          msgs=msgs.replace(" ","%20");
          msgs1=msgs1.replace(" ","%20");
            //call the class that sends messages


            sendText(phonenos,phonenos1, msg);
}





            //call a redirection






        } catch (SQLException ex) {
            Logger.getLogger(QuickStart.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    //__________________________________________________________________________________
    //=====================================ADD THE URLS==========================================
    public void sendText(ArrayList pn , ArrayList pn1, ArrayList msg) {

        for (int a = 0; a < pn.size(); a++) {

            url.add("http://localhost:8011/send/sms/" + pn.get(a) + "/" + msg.get(a));
             //url.add("http://localhost:8011/send/sms/" + pn1.get(a) + "/" + msg.get(a));
 System.out.println("This was called");

                                            }



    }
    

    
    
    
    
//_________________________________________________________________________________________________   

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
