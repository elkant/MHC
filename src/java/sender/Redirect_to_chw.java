/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import ADTELCONNECTION.adtelConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class Redirect_to_chw extends HttpServlet {

    String sender_no = "", msg,shortcode;
 int year, month, date, hour, min, sec, schedulingmin;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");


ArrayList allsms= new ArrayList();            
            
 



if(allsms.size()>0){

allsms.clear();
}
            sender_no = request.getParameter("to");


            msg = request.getParameter("msg");

            
            shortcode=request.getParameter("shortcode");
            
            
            msg = msg.replace("'", "");

            if (sender_no.startsWith("+254")) {

                sender_no = sender_no.replace("+", "");

            }

            System.out.println("" + sender_no);


            adtelConn aconn = new adtelConn();


            dbConn conn = new dbConn();


            //if mother uses another number which is not registered, let them begin by writting 
            //their registered number first, then the message...
            //eg. 0712256**3 I am in vain

            String nonregisterednumber = "";

            if (msg.trim().startsWith("07")) {

                nonregisterednumber = msg.trim().substring(0, 10);

            }




            String check_chw = "Select ChwID,FName,SName,motherID from mother_details where PhoneNo='" + sender_no + "' OR NOKPhoneNo='" + sender_no + "' OR PhoneNo='" + nonregisterednumber + "' OR NOKPhoneNo='" + nonregisterednumber + "'";


            conn.rs = conn.st.executeQuery(check_chw);
            if (conn.rs.next() && !msg.startsWith("@")) {

                
                
                
                
//======get chw details

      conn.rs1 = conn.st1.executeQuery("Select * from chw where chw_id='" + conn.rs.getString(1) + "'");
      conn.rs1.next();
      String sender_name = conn.rs.getString("FName").toString().trim() + " " + conn.rs.getString("SName").toString().trim() + " ";
      String chv_phone = conn.rs1.getString("chv_phone").toString().trim();
      String motherid = conn.rs.getString("motherID").toString().trim();

 //=====MESSAGE      
      
      String msgtochw="Message From " + sender_name + "(code:" + motherid + ") " + msg + ":FHI360 Koibatek Intervention";     
   
      String msgtochw1="";
      
      String msgtochw2="";
      
      String msgtochw3="";
      
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
      


if(chv_phone.startsWith("2547")&&chv_phone.length()==12){
 
//aconn.st1.executeUpdate("insert into request set Number='" + nokno + "' , Message='" + nokmsg + "', shortcode='" + shortcode + "'");
   for(int a=0;a<allsms.size();a++){
       
aconn.st.executeUpdate("insert into request2 set Number='" + chv_phone + "' , Message='" +allsms.get(a)+ "'");

System.out.println("QSTN TO CHW:  message send to chw:"+chv_phone +"   "+allsms.get(a));
   
   }


}




if(chv_phone.startsWith("2547")&&chv_phone.length()==12){
    
     for(int a=0;a<allsms.size();a++){ 
         Date dat=new Date();
         
  conn.st_1.executeUpdate("insert into send_sms set number='" + chv_phone + "' , message='" + allsms.get(a)+ "',status='pending' ,shortcode='" + shortcode + "' ,time='" +dat+ "'");

System.out.println("Logging done");
     }
     
     
}







            }//end of if. conn.rs.next


            allsms.clear();
            
            /**
            
            
            
            
             */
        } catch (SQLException ex) {
            Logger.getLogger(Redirect_to_chw.class.getName()).log(Level.SEVERE, null, ex);
        }





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


}
