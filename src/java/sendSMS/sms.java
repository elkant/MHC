/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sendSMS;

import ADTELCONNECTION.adtelConn;
import java.io.IOException;
import java.io.PrintWriter;
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


//=====THIS SERVLETS FORWARDS ALL OUTGOING MESSAGES TO THE REQUESTS TABLE IN OUR ADTEL TABLE.


public class sms extends HttpServlet {

    String to,msg,shortcode;
    
    
    String confirmation;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
          try {
        confirmation="";
        
     
        //the receiver.  
      to=request.getParameter("to");
      
      
      
      
      //the message to be send
      msg=request.getParameter("msg");
      
      //the testing shortcode
      shortcode="31155";
        
     
//      System.out.println("no"+to);
//      
//      System.out.println("msg"+msg);
//      
      
      //connection to my receiver table      
           adtelConn smsconn=new adtelConn();
           
           
           
    
           
           
           
           //if all the parameters are available , then submit the messages to the database.
           
           if(to!=null&&msg!=null&&!to.equals("")&&!msg.trim().equals("")&&to.startsWith("2547")){
     
                //limit to 160 characters      
            if (msg.length() > 160) {
                
                 msg = msg.substring(0, 160);

                    }
               
               
               
smsconn.st.executeUpdate("insert into request2 set Number='"+to+"' , Message='"+msg+"' ");
smsconn.st.close();          

if(smsconn.st.isClosed()){
confirmation="Message Send!";
System.out.println(confirmation);

}
else{
//System.out.println("Did not complete:"+confirmation);
confirmation="Sending Failed! Check your internet connection";
}
           }


      
      //response.sendRedirect("adtelTest.jsp");
       
     
      
      
    
        
       
        
    }
          catch (SQLException ex) {
            Logger.getLogger(sms.class.getName()).log(Level.SEVERE, null, ex);
            //confirmation+=""+ex;
        }  
         
          
          finally { 
               out.println(""+confirmation);
        out.close();
    }
      
    
        
        
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(sms.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(sms.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
