/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package POSTNAT_REG;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sendSMS.ANCRegister1;
import sendSMS.dbConnect;

/**
 *
 * @author Maureen
 */
public class getGtoM extends HttpServlet {

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
 String ancREG = "";
    String query = "";
    String maritalStatus[];
    int count1 = 1;
    int count = 1;
    String option = "";
    String querys = "";
    HttpSession session;
    String visits = "";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String PNCNo = "";
        String motherID = "";
        dbConnect conn = new dbConnect();
        PNCNo = request.getParameter("PNCNo");
        motherID = request.getParameter("motherID");
        // for getting the anc no in a bean
      
        
       session = request.getSession();
      
     //   System.out.println(session.getAttribute("ANC"));
        
        
        
        String PostNat1 = "";
        count1 = 1;
        count = 1;
       
         ArrayList deliveryMode = new ArrayList(); 
      String deliveryQuery="select * from delivery_mode";
      conn.rs2= conn.state2.executeQuery(deliveryQuery);
      while(conn.rs2.next()){
      deliveryMode.add(conn.rs2.getString(2));
      }
      String deliveryModes="";
      for(int i=0;i<deliveryMode.size();i++){
 
        
        deliveryModes+="<option value=\""+deliveryMode.get(i) +"\">"+deliveryMode.get(i)+"</option>";
        
        }

        ancREG = "";
        PostNat1 = "";

        PostNat1 += " <tr><td></td>\n" +
"                   <td colspan=\"3\">Maternity History</td>\n" +
"                   <td>Maternity History</td>\n" +
"                   <td colspan=\"3\">Vital Signs</td>\n" +
"                   \n" +
"               </tr>\n" +
"               <tr>\n" +
"                   <td>No</td>    \n" +
"                   <td>Date of Delivery</td>    \n" +
"                   <td>Place of Delivery</td>    \n" +
"                   <td>Mode of Delivery</td>    \n" +
"                   <td>State of baby</td>    \n" +
"                   <td>Temperature</td>    \n" +
"                   <td>Pulse</td>    \n" +
"                   <td>Blood Pressure</td>    \n" +
"                 \n" +
"               </tr>  \n" +
"               <tr>\n" +
"                     \n" +
"                   <td></td>    \n" +
"                   <td>(g)</td>    \n" +
"                   <td>(h)</td>    \n" +
"                   <td>(i)</td>    \n" +
"                   <td>(j)</td>    \n" +
"                   <td>(k)</td>    \n" +
"                   <td>(l)</td>    \n" +
"                   <td>(m)</td>    \n" +
"                     \n" +
"               \n" +
"               </tr> ";
             
        

        String options = "";
        try {

            //============================================================A TO G TABLE DETAILS==========================   
           
             String Matquery="Select * from postnat_gtom where motherid='"+motherID+"'";
          conn.rs = conn.state.executeQuery(Matquery);
//           System.out.println("B  "+B.toString());
//     System.out.println(query);
String selectDelivery="";
           
           String modes="";     
         
while(conn.rs.next()){
String querySelect = "select * from delivery_mode where modeid='"+conn.rs.getString("DeliveryMode")+"'";
conn.rs3 = conn.state3.executeQuery(querySelect);
while(conn.rs3.next()){
    modes = conn.rs.getString(2);
}

 for(int i=0;i<deliveryMode.size();i++){
                
                if(deliveryMode.get(i).equals(modes)){
                    
                 selectDelivery+="<option selected value=\""+deliveryMode.get(i)+"\">"+deliveryMode.get(i)+"</option>";
                
                }
                
                else{
                selectDelivery+="<option value=\""+deliveryMode.get(i)+"\">"+deliveryMode.get(i) +"</option>";
                }
                             }
                    PostNat1 += "<tr><td>" + count1 + "</td>"
                             + "<th style=\"width:100px;\"><input type=\"hidden\"   value=\"" + conn.rs.getString("motherid") + "\" name=\"motherID\" id=\"motherID\"><input class=\"textbox\" readonly type=\"text\" style=\"width:100px;\" value=\"" + conn.rs.getString(2) + "\" name=\"DeliveryDate" + count1 + "\" id=\"DeliveryDate" + count1 + "\"/></th>"
                             + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\"  style=\"width:100px;\"value=\"" + conn.rs.getString(3) + "\" name=\"DeliveryPlace" + count1 + "\" id=\"DeliveryPlace" + count1 + "\" /><input type=\"hidden\" value=\"" + conn.rs.getString(1) + "\" name=\"ID" + count1 + "\" id=\"ID" + count1 + "\" /></th>"
//                             +"<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs.getString(4) + "\" name=\"DeliveryMode" + count1 + "\"/></td>"
                              + "<th style=\"width:100px;\"><select style=\"width:80px;\"  name=\"DeliveryMode" + count1 + "\"  id=\"DeliveryMode" + count1 + "\"/>"+selectDelivery+"</select></th>"
                             + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs.getString(5) + "\" name=\"BabyStatus" + count1 + "\"/></th>"
                             + "<th style=\"width:100px;\"><input class=\"textbox\"   type=\"text\" style=\"width:100px;\" value=\"" + conn.rs.getString(6) + "\" name=\"Temp" + count1 + "\"/></th>"
                             + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs.getString(8) + "\" name=\"Pulse" + count1 + "\"/></th>"
                             + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs.getString(7) + "\" name=\"BP" + count1 + "\"/></th></tr>";
                            
                    count1++;

                   
           option="";    
 }




                PostNat1 += "<tr ><td style=\"width:40px;\">" + count1 + "</td>"
                        + "<th =\"width:100px;\"><input readonly class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"\" name=\"New_DeliveryDate" + count + "\" id=\"New_DeliveryDate" + count + "\"/></th>"
                        + "<td style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\"  style=\"width:100px;\" value=\"\" name=\"New_DeliveryPlace" + count + "\"  id=\"New_DeliveryPlace" + count + "\" /></td>"
                        + "<td style=\"width:100px;\"><select style=\"width:80px;\" id=\"New_DeliveryMode" + count + "\"  name=\"New_DeliveryMode" + count + "\"/>"
                        
                        + "<option value=\"\"></option>"+deliveryModes+"</select></th>"
                        + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"\" id=\"New_BabyStatus" + count + "\" name=\"New_BabyStatus" + count + "\"></th>"
                        + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"\" name=\"New_Temp" + count + "\"></th>"
                        + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"\" name=\"New_Pulse" + count + "\"></th>"
                        + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"\" name=\"New_BP" + count + "\"/></th>"                       
                        + "</tr>";

            //no_of_old_rows
            PostNat1 += "<input type=\"hidden\" id=\"PNCRegister_newRows\" name=\"PNCRegister_newRows\" value=\"" + count + "\" > <input type=\"hidden\" id=\"PNCRegister_old_rows\" value=\"" + (count1 - 1) + "\" name=\"PNCRegister_old_rows\" >";
            PostNat1 += " <input type=\"hidden\" id=\"GtoM\" name=\"GtoM\" value=\""+count+"\" > \n" +
"               <input type=\"hidden\" id=\"GtoM_old_rows\" value=\"" + (count1 - 1) + "\" name=\"GtoM_old_rows\" >\n" +
"             ";



//enter search details in an history..
            
            
            String user1="";
            if(session.getAttribute("userid")!=null){
            
            user1=session.getAttribute("userid").toString();
            }
            
 String history="insert  into history set ancnumber='"+PNCNo+"', userid='"+user1+"', page='PostNatal Register' ";           
 
 
 conn.state.executeUpdate(history);

            try {
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("" + PostNat1 + "");
                out.println("" + ancREG + "");
                out.println("</body>");
                out.println("</html>");

                ancREG = "";
                PostNat1 = "";
            } finally {
                out.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ANCRegister1.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
                    try {
                        processRequest(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(getGtoM.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } catch (ParseException ex) {
            Logger.getLogger(getGtoM.class.getName()).log(Level.SEVERE, null, ex);
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
                    try {
                        processRequest(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(getGtoM.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } catch (ParseException ex) {
            Logger.getLogger(getGtoM.class.getName()).log(Level.SEVERE, null, ex);
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
