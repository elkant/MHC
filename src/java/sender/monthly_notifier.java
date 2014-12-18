/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import it.sauronsoftware.cron4j.Scheduler;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
public class monthly_notifier extends HttpServlet {
    
    public static final String PREURL="http://localhost:8080";
    
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
       min=cal.get(Calendar.MINUTE)+2; 
        
        
        
        if (monthlymsg.size() > 0) {
           monthlymsg.clear();

        }
        if (monthlypn.size() > 0) {
           monthlypn.clear();

        }
        if (monthlypn1.size() > 0) {
           monthlypn1.clear();

        }
 if (url.size() > 0) {
           url.clear();

                     }

        status_id = "1";
        message_id = "1";



        Scheduler s = new Scheduler();
        // Schedule a daily task.
      // s.schedule(""+min+" "+ hour+" * * 1-7", new Runnable() {
           s.schedule("33 19 * * 1-7", new Runnable() {
     // s.schedule("* * * * *", new Runnable() {

            public void run() {
                //select querr from dbase


                System.out.println("Another monthly shedule ticked away...");
                
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
        String mymothers = "Select PhoneNo , NOKPhoneNo ,delivery_date,FName from mother_details where status_id ='" + status_id + "'";
        //get current calender date and compare it with the anc visit date.
        
        
        
        
        //connect to database
        dbConn conn = new dbConn();
        
           String msgs = "",msgs1="";
        
        try {
//get the message that will be displayed to the user in the range of one 
 //????????????????????????????????????????????????????????????????????????????????
          
            
  String amonthbefore="select * from messages where message_id='5'"; 
        
            
            
            
            
          //execute the mothers querr....  
            conn.rs = conn.st.executeQuery(mymothers);

            while (conn.rs.next()) {
                
                
                
                //compare the dates now
              int dbaseyea=0,dbasemonth=0,dbasedate=0;
              //  
              
              //GET THE EXPECTED DELIVERY DATE
                if(conn.rs.getString("delivery_date")!=""&&conn.rs.getString("delivery_date")!=null){
                              
                //yyyy-mm-dd
                String anc_dates[]=conn.rs.getString("delivery_date").toString().trim().split("-");
                
                dbaseyea=Integer.parseInt(anc_dates[0]);
                dbasemonth=Integer.parseInt(anc_dates[1]);
                dbasedate=Integer.parseInt(anc_dates[2]);
//               System.out.println("month difference:"+(month-dbasemonth));
//               System.out.println("date difference:"+(date-dbasedate));
//               
                
                 }       
            
               
//=================================== THE MONTHLY UPDATES HERE FOR A PREGNANT MOTHER================================================               
           if(date-dbasedate==0&&((dbasemonth-month)==1)){
           
//            System.out.println("dbase date:"+dbasedate+" curr date:"+date);
//            System.out.println("dbase month:"+dbasemonth+" curr date:"+month);
               
               
            conn.rs4=conn.st4.executeQuery(amonthbefore);
               
                   conn.rs4.next();
                //send user with message that their next anc visit is next week
               msgs1="Dear "+conn.rs.getString("FName").trim()+", Your expected date of delivery is "+conn.rs.getString("delivery_date").trim()+". "+conn.rs4.getString(2).trim()+":FHI360 Koibatek Intervention"; 
               // System.out.println(" one month msg"+msgs);
                 msgs1=msgs1.replace(" ","%20");
                     monthlymsg.add(msgs1); 
                 //_________________ADD PHONE NUMBERS____________________
                     
               monthlypn.add(conn.rs.getString(1));
               
               monthlypn1.add(conn.rs.getString(2));
               
           
           
           
           }     
               //send one months message 
           else{
           
           System.out.println("Not qualified");
           
           
           }     
                
                
                
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
                
             
                
                

if(!msgs1.equals("")){
           
        


            sendmonthlyTexts(monthlypn,monthlypn1, monthlymsg);
}




            //call a redirection






        } catch (SQLException ex) {
            Logger.getLogger(QuickStart.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    //__________________________________________________________________________________
    //=====================================ADD THE URLS==========================================
  
    
//((((((((((((((((((((((((((((((((((((((((MONTHLY TEXTS))))))))))))))))))))))))))))))))))))))))))))))))
    //separate this with the other pages
     public void sendmonthlyTexts(ArrayList pn , ArrayList pn1, ArrayList msg) {

        for (int a = 0; a < pn.size(); a++) {

            url.add(PREURL+"/sms?to="+ pn.get(a) +"&msg="+ msg.get(a));
            // url.add("http://localhost:8011/send/sms/" + pn.get(a) + "/" + msg.get(a));
            // url.add("http://localhost:8011/send/sms/" + pn1.get(a) + "/" + msg.get(a));
             //System.out.println("This was called");

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
