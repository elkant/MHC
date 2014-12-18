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
public class defaulterSMS extends HttpServlet {

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
//  String confirmation="";
//  String addPhones="";
//    String msg="";
//    String status="";
//    String sms1="";
    String salutation="";
// ArrayList messages = new ArrayList();
 HttpSession session;
 String message="";
  int counters=0;
//  String phoneNum ;
  String FName;
  
  
  
//  String NokphoneNum;
  String consent;
  String FinalSMS="";
  String queries ="";
  int count=1;
//ArrayList phone = new ArrayList();
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
        counters = (Integer)session.getAttribute("rowCount");
        
                           Date date= new Date(); 
          String warning="";              
        String sentMsg="";
        String sentNokMsg="";
        String nok_message="";
        String nok="";
        String salamu="";
        String ujumbe="";
        String ujumbe_nok="";
        String language="";
        String nokPhone="";
        String salutationEng="";
       
        ArrayList id = new ArrayList();
        ArrayList defaulter = new ArrayList();
        String phoneNo="";
        int rowsCount=0;
         System.out.println("|||||||||||||||||||||||||||||||||||||||||REACHED");
//        System.out.println("counters"+counters);
           // gets the status of the mother to be sen to the message whether pregnant 
        //or postnatal and also th esaluation since they are dependent on the language     
         for(int i=0;i<=counters;i++){  
             
        if(request.getParameter("id"+i)!=null && request.getParameter("id"+i)!=""){
//          rowsCount++;
                   id.add(request.getParameter("id"+i)); 
               System.out.println(id.get(0));   
                   
             }}
         
         for(int i=0;i<=counters;i++){  
             
        if(request.getParameter("defaulter"+i)!=null && request.getParameter("defaulter"+i)!=""){
          rowsCount++;
                   defaulter.add(request.getParameter("defaulter"+i)); 
                    System.out.println(defaulter.get(0)); 
                   
             }}
          
//             for(int i=0;i<=counters;i++){ 
//          if(request.getParameter("phoneNo"+i)!=null && request.getParameter("phoneNo"+i)!=""){
//               rowsCount++;
//                   phoneNo=request.getParameter("phoneNo"+i); 
//                    System.out.println("phoneNo"+phoneNo);
//             }
//             }
//           for(int i=0;i<=counters;i++){ 
//          if(request.getParameter("FName"+i)!=null && request.getParameter("FName"+i)!=""){
//                   FName=request.getParameter("FName"+i); 
//                    System.out.println("FName"+FName);
//             }
//              }
          
//           for(int i=0;i<=counters;i++){ 
//          if(request.getParameter("language"+i)!=null && request.getParameter("language"+i)!=""){
//                   language=request.getParameter("language"+i); 
//                    System.out.println("language"+language);
//             }
//              }
//           for(int i=0;i<=counters;i++){ 
//          if(request.getParameter("consent"+i)!=null && request.getParameter("consent"+i)!=""){
//                   consent=request.getParameter("consent"+i); 
//                   System.out.println("consent"+consent);
//             }
//              }
//           for(int i=0;i<=counters;i++){ 
//          if(request.getParameter("nokPhone"+i)!=null && request.getParameter("nokPhone"+i)!=""){
//                   nokPhone=request.getParameter("nokPhone"+i); 
//                     System.out.println("nokPhone"+nokPhone);
//             }
//              }
//           for(int i=0;i<=counters;i++){ 
//          if(request.getParameter("nok"+i)!=null && request.getParameter("nok"+i)!=""){
//                   nok=request.getParameter("nok"+i);
//                    System.out.println("nok"+nok);
//            
//              }
         


 if(defaulter.size()>0){
String query = "select cat_id from message_category where category='defaulters'";


conn.rs = conn.state.executeQuery(query);   
  

while(conn.rs.next()){
    conn.rs.getString("cat_id");
    System.out.println("cat_id"+conn.rs.getString("cat_id"));
    
    String catQuery="select * from messages where category_id='"+ conn.rs.getString("cat_id")+"'"; 
                    
                   conn.rs2 = conn.state2.executeQuery(catQuery); 
    while(conn.rs2.next()){
  

   sentNokMsg =conn.rs2.getString(6);
   sentMsg=conn.rs2.getString(2);
   ujumbe=conn.rs2.getString(7);
   ujumbe_nok=conn.rs2.getString(10);
   salutationEng=conn.rs2.getString(8);
   salamu=conn.rs2.getString(9);
  
    }
 }
System.out.println(sentNokMsg+"___"+sentMsg +"__"+ujumbe+"__"+ujumbe_nok+"____"+salutationEng+"_____"+salamu);
String mother="";
for(int i=0; i< rowsCount;i++){
String f = defaulter.get(i).toString();
 mother="select * from mother_details  where anc_no='"+f+"'";

 System.out.println(mother);
                    
                   conn.rs3 = conn.state3.executeQuery(mother); 
   while(conn.rs3.next()){
   phoneNo =conn.rs3.getString("PhoneNo");
   FName=conn.rs3.getString("FName");
   nokPhone=conn.rs3.getString("NOKPhoneNo");
   consent=conn.rs3.getString("nok_consent");
   nok=conn.rs3.getString("nok");
   language=conn.rs3.getString("languagePreferred");
  
System.out.println("phoneNo"+phoneNo);
System.out.println("nokPhone"+nokPhone);

if(nokPhone==null || nokPhone.equals(" ")  && phoneNo== null || phoneNo.equals(" ")){
   warning = "No Available Contacts for "+FName+"";
  
  session.setAttribute("warning","<font color=\"red\">"+warning+"</font>");
  
  System.out.println("warning--------------"+warning);
}

if( language!= null && !language.equals("") && language.equalsIgnoreCase("english"))
{
message= sentMsg;
nok_message= sentNokMsg;
salutation = salutationEng;

System.out.println(message+"--"+nok_message+""+salutation);

}
else if(language!= null && !language.equals("") && language.equalsIgnoreCase("kiswahili") )
{
message=ujumbe;
nok_message= ujumbe_nok;
salutation= salamu;
System.out.println(message+"--"+nok_message+""+salutation);
}


String p ="";
String phones2 ="";
String phones="";

if(phoneNo != null && !phoneNo .equals("") && message != null && !message .equals("") ){
//    message=message;
if(phoneNo.startsWith("07")){
 phones = phoneNo.substring(0, 2);
 System.out.println("ppp"+phones);
 phones2 = phoneNo.substring(1, 10);
 System.out.println("ooo"+phones2);
 String k =phones.replace("07", "254");
 System.out.println("iiiii    "+k);


phoneNo = k+phones2;

System.out.println("lllll  "+phoneNo);
}

}

else if(nokPhone!=null && !nokPhone.equals("")  && consent!= null && consent!="" && consent.equalsIgnoreCase("Yes")){
    String phon="";
    String phon1="";
//    nok_message= nok_message;
if(nokPhone.startsWith("07")){
 phon = nokPhone.substring(0, 2);
 System.out.println("qqqqq"+phon);
 phon1 = nokPhone.substring(1, 10);
 //System.out.println("wwwwww"+phon1);
 String a =phon.replace("07", "254");
 System.out.println("aaaaaa     "+a);


nokPhone = a+phon1;

System.out.println("ggggg  "+nokPhone);

}

}
else if(nokPhone!=null && nokPhone!=""  && consent!= null && consent!="" && consent.equalsIgnoreCase("No")){
  warning = "Available NOK But No Consent";
  
  session.setAttribute("warning","<font color=\"red\">"+warning+"</font>");
  
  System.out.println("warning--------------"+warning);
}




//else{
//    out.println("NO RECIPIENT AVAILABLE");
//    FinalNum ="";
//    message="";
//
//}
System.out.println("Phone No"+phoneNo +" message"+ message);
System.out.println("Phone No"+nokPhone +" message"+ nok_message);

System.out.println("message"+message);  

         if(phoneNo!=null && !"".equals(phoneNo) &&message!=null && !message.trim().equals("")&&phoneNo.startsWith("2547") &&phoneNo.length()==12){
     
                //limit to 160 characters      
            if (message.length() > 160) {
                
                 message =message.substring(0, 160);

                    }
            
            System.out.println("sms1------"+message);
            System.out.println("phoneNum------"+phoneNo);
            System.out.println("Name------"+FName);
            System.out.println("salutation------"+salutation);
            
             FinalSMS =salutation+" "+FName+","+message;
            
            System.out.println("FinalSMS------"+FinalSMS);
//            String query3 ="insert into request2 set Number='"+phoneNum+"' , Message='"+FinalSMS+"'";
//            System.out.println(query3);
//           smsconn.st.executeUpdate(query);
           
             queries  ="insert into send_sms set number='"+phoneNo+"' ,message='"+FinalSMS+"', time='"+date+"',status='Pending',shortcode='31155'";
            System.out.println(queries);
//            try{
//           conn.state2.executeUpdate(queries);}
//            catch(SQLException ex){
//            System.out.println(ex.toString());}
            
             warning = "Message sent successfully";
  
  session.setAttribute("warning","<font color=\"red\">"+warning+"</font>");
     }
         
         
         else if(nokPhone!=null && nokPhone!="" &&nok_message!=null && !nok_message.trim().equals("")&&nokPhone.startsWith("2547") &&nokPhone.length()==12){
     
                //limit to 160 characters      
            if (nok_message.length() > 160) {
                
                 nok_message =nok_message.substring(0, 160);

                    }
            
            System.out.println("sms1------"+nok_message);
            System.out.println("phoneNum------"+nokPhone);
            System.out.println("Name------"+FName);
            System.out.println("salutation------"+salutation);
            
            String  FinalSMS2 =salutation+" "+nok+","+FName+" "+nok_message;
            
            System.out.println("FinalSMS------"+FinalSMS2);
//            String query ="insert into request2 set Number='"+phoneNum+"' , Message='"+FinalSMS+"'";
//            System.out.println(query);
//           smsconn.st.executeUpdate(query);
           
             queries  ="insert into send_sms set number='"+nokPhone+"' ,message='"+FinalSMS2+"', time='"+date+"',status='Pending',shortcode='31155'";
            System.out.println(queries);
             warning = "Message sent successfully";
  
  session.setAttribute("warning","<font color=\"red\">"+warning+"</font>");
//            try{
////           conn.state2.executeUpdate(queries);
//            }
//            catch(SQLException ex){
//            System.out.println(ex.toString());}
     }
//}
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

 }}
      response.sendRedirect("Diary.jsp");   }

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
            Logger.getLogger(defaulterSMS.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(defaulterSMS.class.getName()).log(Level.SEVERE, null, ex);
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
