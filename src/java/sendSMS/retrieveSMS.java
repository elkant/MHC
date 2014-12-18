/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sendSMS;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class retrieveSMS extends HttpServlet {

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
    
      String msg,current_msg,status,category;
   HttpSession session;
    int count=0;
     String message;
     String Showmsgs="";
     String msgs="";
   
    ArrayList dist=new ArrayList();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Showmsgs="";
        PrintWriter out = response.getWriter();
        
        
        
      
        try {
            response.setContentType("text/html;charset=UTF-8");
           count=0;
           session = request.getSession(true);
         Showmsgs+="<tr><th>#</th><th>MESSAGE</th><th>select message </th></tr>";
             if(request.getParameter("status")!=null && !request.getParameter("status").equals("")){
                   status=request.getParameter("status"); 
             }
          if(request.getParameter("category")!=null && !request.getParameter("category").equals("")){
           category=request.getParameter("category"); }
//           System.out.println("status:"+ status); 
           current_msg="";
           
           
           
//           String titles="select status,PhoneNo,FName from mother_details where status='"+status+"'";
//           System.out.println(titles);
           String sms="";
            String salutation="";
            String nok_message="";
           dbConnect conn=new dbConnect();
           if(status!= null && !status.equals("") && category != null && !category.equals("")){
//           conn.rs=conn.state.executeQuery(titles);
             String titlesID="select * from messages where status_id='"+status+"' && category_id='"+category+"'";
             conn.rs=conn.state.executeQuery(titlesID);
//           System.out.println(titlesID);
          String salamu="";
          String ujumbe="";
          String ujumbe_nok="";
          String kalemsg="";
          String kalenokmsg="";
          String kale_salutation="";
         int count1=0;
         int msgcount=0;
            while(conn.rs.next()){
          count1++;
           current_msg="";
           if(conn.rs.getString("message")!= null && !conn.rs.getString("message").equals("")){
            sms =conn.rs.getString("message");}
            if(conn.rs.getString("salutation")!= null && !conn.rs.getString("salutation").equals("")){
           salutation =conn.rs.getString("salutation");}
             if(conn.rs.getString("nok_message")!= null && !conn.rs.getString("nok_message").equals("")){
            nok_message =conn.rs.getString("nok_message");}
             if(conn.rs.getString("salamu")!= null && !conn.rs.getString("salamu").equals("")){
            salamu =conn.rs.getString("salamu");}
             if(conn.rs.getString("ujumbe")!= null && !conn.rs.getString("ujumbe").equals("")){
            ujumbe =conn.rs.getString("ujumbe");}
             if(conn.rs.getString("nok_ujumbe")!= null && !conn.rs.getString("nok_ujumbe").equals("")){
            ujumbe_nok =conn.rs.getString("nok_ujumbe");}
//             if(conn.rs.getString("kalemsg")!= null && conn.rs.getString("kalemsg")!=""){
//            kalemsg =conn.rs.getString("kalemsg");}
//             if(conn.rs.getString("kalenokmsg")!= null && conn.rs.getString("kalenokmsg")!=""){
//            kalenokmsg =conn.rs.getString("kalenokmsg");}
//             if(conn.rs.getString("kale_salutation")!= null && conn.rs.getString("kale_salutation")!=""){
//            kale_salutation =conn.rs.getString("kale_salutation");}
          
         
          
           
         
//          current_msg+="<textarea  id=\"msgs_0\" name=\"msgs_0\" value=\""+sms+"\"> "+sms+"</textarea><";
          message= conn.rs.getString("message");
           msgs= message.replace(" ", "%20");

          
          
           
   
          
           Showmsgs+="<tr><td>"+count1+"</td><td><p id=\"label_"+count+"\">" +sms+"</p> </td>"
                   + "<td><input value=\"\"   type=\"checkbox\" onclick=\"togglesms('"+count+"');\" name=\"msg_"+count+"\" id=\"msg_"+count+"\" ></td></tr>"
                    + "<input type=\"hidden\" value=\""+msgs+"\" name=\"eng_"+count+"\"  id=\"eng_"+count+"\">"
                    + "<input type=\"hidden\" value=\""+salutation+"\" name=\"salutation_"+count+"\">"
                    + "<input type=\"hidden\" value=\""+nok_message+"\" name=\"nok_message_"+count+"\">"
                    + "<input type=\"hidden\" value=\""+salamu+"\" name=\"salamu_"+count+"\">"
                    + "<input type=\"hidden\" value=\""+ujumbe_nok+"\" name=\"ujumbe_nok_"+count+"\">"
                    + "<input type=\"hidden\" value=\""+ujumbe+"\" name=\"ujumbe_"+count+"\">"
                    + "<input type=\"hidden\" value=\""+kalemsg+"\" name=\"ujumbe_"+count+"\">"
                    + "<input type=\"hidden\" value=\""+kalenokmsg+"\" name=\"ujumbe_"+count+"\">"
                    + "<input type=\"hidden\" value=\""+kale_salutation+"\" name=\"ujumbe_"+count+"\">";
           
           count++;
           msgcount++;
           }
            
             Showmsgs+=  "<tr><td><input type=\"hidden\" value=\""+count+"\" name=\"counter\"></td></tr>";
            
            if(msgcount==0){
            
            Showmsgs="<h2 style=\"background:white; \">NO messages under the selected category. You may add new messages for that category <a class=\"linkstyle\" href=\"createSMS.jsp\">here </a> .</h2>";
            }
          
            out.println("<html>");
            out.println("<head>");           
            out.println("</head>");
            out.println("<body>");
            out.println("<tr><td>"+Showmsgs+"</td></tr>");
            out.println("</body>");
            out.println("</html>");
              count++; 
              session.setAttribute("count", count);
              //Showmsgs="";
            
            }
                    
//          String titles="select PhoneNo,FName from mother_details where status='"+status+"'";
//          System.out.println(titles);
//            String phone="";
//            String fname ="";
//           
//           conn.rs1=conn.state.executeQuery(titles); 
//           
//              while(conn.rs1.next()){
//               phone =   conn.rs1.getString("PhoneNo");
//               fname = conn.rs1.getString("FName");
//                 
//              }
//              System.out.println("phone"+phone);
//              System.out.println( " fname"+fname);
//        
          // response.sendRedirect("myajax.html");
        } catch (SQLException ex) {
            Logger.getLogger(retrieveSMS.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
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
        processRequest(request, response);
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
        processRequest(request, response);
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
