/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package sender;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
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
public class redirect_to_mother extends HttpServlet {

String sender_no="",msg;
String smsarray[];
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");


            sender_no=request.getParameter("sender");
            msg=request.getParameter("msg");
            //receive the value after @
            
            
           msg=msg.replace(" ","%20");

           smsarray=msg.split("@");
           
            if(sender_no.contains("+254")){

            sender_no=sender_no.replace("+254", "0");
           
            }

            System.out.println("smsarray"+smsarray[0]);



            dbConn conn=new dbConn();


            String check_mother="Select ChwID, PhoneNo , PhoneNo1 ,FName,SName from mother_details where motherID='"+smsarray[0]+"'";


        conn.rs=conn.st.executeQuery(check_mother);
      if(  conn.rs.next()&&!msg.contains("HACK")&&!msg.contains("monthly")&&smsarray.length>0&&!msg.contains("ANC")){
        
       conn.rs1=conn.st1.executeQuery("Select * from chw where  	chv_phone='"+sender_no+"'");
       conn.rs1.next();
       String sender_name=conn.rs1.getString(2).toString().trim()+"%20"+conn.rs.getString(3).toString().trim()+"%20";
       String mother_phone=conn.rs.getString("PhoneNo").toString().trim();
       
       
       //redirect message to mother
       //System.out.println("http://localhost:8011/send/sms/" +chv_phone+ "/" +sender_no+"_"+msg );
       
       
        URL murl = new URL("http://localhost:8011/send/sms/"+mother_phone+ "/"+sender_name+"%20reply:_"+""+smsarray[1]);
        HttpURLConnection connection = (HttpURLConnection)murl.openConnection();
        connection.setRequestMethod("POST");
        connection.connect();

        int code = connection.getResponseCode();
       
       
        
                
      }
            

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
}
