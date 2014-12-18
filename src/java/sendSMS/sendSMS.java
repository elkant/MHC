/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sendSMS;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maureen
 */
public class sendSMS extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
           
String confirmation="";
    String addPhones="";
String msg="";
String status="";
String sms1="";
String salutation="";
 ArrayList messages = new ArrayList();
 HttpSession session;
 String message="";
  int counters=0;
   String phoneNum ;
  String FName;
  String NokphoneNum;
  String consent;
  String FinalSMS="";
  String queries ="";
  int count=1;
ArrayList phone = new ArrayList();
String FinalNum="";
int counter=0;
   dbConnect conn = new dbConnect();
//  adtelConn smsconn=new adtelConn();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
          counters=0;
        session = request.getSession(true);
        counters = (Integer)session.getAttribute("count");
        
                           Date date= new Date(); 
                        
       String sentMsg="";
       String sentNokMsg="";
        String nok_message="";
        String nok="";
        String salamu="";
        String ujumbe="";
        String ujumbe_nok="";
        String languagePreferred="";
        String salutationEng="";
       
//        System.out.println("counters"+counters);
           // gets the status of the mother to be sen to the message whether pregnant 
        //or postnatal and also th esaluation since they are dependent on the language     
          if(request.getParameter("status")!=null && request.getParameter("status")!=""){
                   status=request.getParameter("status"); 
             }
           for(int i=0;i<=counters;i++){ 
          if(request.getParameter("salutation_"+i)!=null && request.getParameter("salutation_"+i)!=""){
                   salutationEng=request.getParameter("salutation_"+i); 
             }
              }
           for(int i=0;i<=counters;i++){ 
          if(request.getParameter("salamu_"+i)!=null && request.getParameter("salamu_"+i)!=""){
                   salamu=request.getParameter("salamu_"+i); 
             }
              }
          for(int k=0;k<=counters;k++){
             
          if(request.getParameter("msg_"+k)!=null && request.getParameter("msg_"+k)!=""){
                  msg = request.getParameter("msg_"+k);
                sentMsg  = msg.replace("%20"," ");
                   
             }
          }
          for(int l=0;l<=counters;l++){
             
          if(request.getParameter("nok_message_"+l)!=null && request.getParameter("nok_message_"+l)!=""){
                  sentNokMsg = request.getParameter("nok_message_"+l);
  
             }
          }
          for(int l=0;l<=counters;l++){
             
          if(request.getParameter("ujumbe_"+l)!=null && request.getParameter("ujumbe_"+l)!=""){
                  ujumbe = request.getParameter("ujumbe_"+l);
  
             }
          }
          for(int l=0;l<=counters;l++){
             
          if(request.getParameter("ujumbe_nok_"+l)!=null && request.getParameter("ujumbe_nok_"+l)!=""){
                  ujumbe_nok = request.getParameter("ujumbe_nok_"+l);
  
             }
          }



 if(status!=null && status!=""){
String query = "select FName,PhoneNo,NOKPhoneNo,nok,languagePreferred,nok_consent from mother_details where status_id='"+status+"'";


conn.rs = conn.state.executeQuery(query);   
  

while(conn.rs.next()){
  
   phoneNum =conn.rs.getString("PhoneNo");
   FName=conn.rs.getString("FName");
   NokphoneNum=conn.rs.getString("NOKPhoneNo");
   consent=conn.rs.getString("nok_consent");
   nok=conn.rs.getString("nok");
   languagePreferred=conn.rs.getString("languagePreferred");
 
System.out.println(phoneNum+"___"+FName +"__"+NokphoneNum+"__"+consent+"____"+nok);
if( languagePreferred!= null && languagePreferred!="" && languagePreferred.equalsIgnoreCase("english"))
{
message= sentMsg;
nok_message= sentNokMsg;
salutation = salutationEng;

}
else if(  languagePreferred!= null && languagePreferred!="" && languagePreferred.equalsIgnoreCase("swahili") )
{
message=ujumbe;
nok_message= ujumbe_nok;
salutation= salamu;
}


String p ="";
String phones2 ="";
String phones="";

if(phoneNum != null && phoneNum != "" && message != null && message != "" ){
    message=message;
if(phoneNum.startsWith("07")){
 phones = phoneNum.substring(0, 2);
// System.out.println("qqqqq"+phones);
 phones2 = phoneNum.substring(1, 10);
// System.out.println("wwwwww"+phones2);
 String k =phones.replace("07", "254");
// System.out.println("kkkkkk     "+k);


phoneNum = k+phones2;

//System.out.println("lllll  "+FinalNum);
}

}

else if(NokphoneNum!=null && NokphoneNum!="" && consent.equalsIgnoreCase("Both or either")){
    String phon="";
    String phon1="";
    nok_message= nok_message;
if(NokphoneNum.startsWith("07")){
 phon = NokphoneNum.substring(0, 2);
 System.out.println("qqqqq"+phon);
 phon1 = NokphoneNum.substring(1, 10);
 System.out.println("wwwwww"+phon1);
 String a =phon.replace("07", "254");
 System.out.println("aaaaaa     "+a);


NokphoneNum = a+phon1;

//System.out.println("lllll  "+FinalNum);

}

}

//else{
//    out.println("NO RECIPIENT AVAILABLE");
//    FinalNum ="";
//    message="";
//
//}
System.out.println("Phone No"+phoneNum +" message"+ message);
System.out.println("Phone No"+NokphoneNum +" message"+ nok_message);

System.out.println("message"+message);  

         if(phoneNum!=null && !"".equals(phoneNum) &&message!=null && !message.trim().equals("")&&phoneNum.startsWith("2547") &&phoneNum.length()==12){
     
                //limit to 160 characters      
//            if (message.length() > 160) {
//                
//                 message =message.substring(0, 160);
//
//                    }
            
            System.out.println("sms1------"+message);
            System.out.println("phoneNum------"+phoneNum);
            System.out.println("Name------"+FName);
            System.out.println("salutation------"+salutation);
            
             FinalSMS =salutation+" "+FName+","+message;
            
            System.out.println("FinalSMS------"+FinalSMS);
//            String query ="insert into request2 set Number='"+phoneNum+"' , Message='"+FinalSMS+"'";
//            System.out.println(query);
//           smsconn.st.executeUpdate(query);
           
             queries  ="insert into send_sms set number='"+phoneNum+"' ,message='"+FinalSMS+"', time='"+date+"',status='Pending',shortcode='31155'";
            System.out.println(queries);
            try{
           conn.state2.executeUpdate(queries);}
            catch(SQLException ex){
            System.out.println(ex.toString());}
     }
         
         
         else if(NokphoneNum!=null && NokphoneNum!="" &&nok_message!=null && !nok_message.trim().equals("")&&NokphoneNum.startsWith("2547") &&NokphoneNum.length()==12){
     
                //limit to 160 characters      
            if (nok_message.length() > 160) {
                
                 nok_message =nok_message.substring(0, 160);

                    }
            
            System.out.println("sms1------"+nok_message);
            System.out.println("phoneNum------"+NokphoneNum);
            System.out.println("Name------"+FName);
            System.out.println("salutation------"+salutation);
            
            String  FinalSMS2 =salutation+" "+nok+","+FName+","+nok_message;
            
            System.out.println("FinalSMS------"+FinalSMS2);
//            String query ="insert into request2 set Number='"+phoneNum+"' , Message='"+FinalSMS+"'";
//            System.out.println(query);
//           smsconn.st.executeUpdate(query);
           
             queries  ="insert into send_sms set number='"+NokphoneNum+"' ,message='"+FinalSMS2+"', time='"+date+"',status='Pending',shortcode='31155'";
            System.out.println(queries);
            try{
           conn.state2.executeUpdate(queries);}
            catch(SQLException ex){
            System.out.println(ex.toString());}
     }
}}
//            
//smsconn.st.close();          
//
//if(smsconn.st.isClosed()){
//confirmation="Message Send!";
//System.out.println(confirmation);
//
//}
//else{
////System.out.println("Did not complete:"+confirmation);
//confirmation="Sending Failed! Check your internet connection";
//}
//           }
//                     
         

         

 }


    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(sendSMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(sendSMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
