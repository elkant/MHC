/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MAT_REG;

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
import mother_child.ItoMRegister;
import sendSMS.dbConnect;

/**
 *
 * @author Maureen
 */
public class maternityMtoU extends HttpServlet {

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
 String MtoU = "";
    String query = "";
    String query2 = "";
String admNo="";

String motherID="";
int count1=1;
dbConnect conn = new dbConnect();
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        admNo = request.getParameter("admNo");
        // data from mother details table
       conn = new dbConnect();
       System.out.println("admNo"+admNo);
       
       count1=1;
       
       motherID= request.getParameter("motherID");
       
       
        String options = "";
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
      String[] YesNo;
      String selectsYesNo="";
        YesNo = new String []{"Y","N"};
         for(int k=0;k<YesNo.length;k++){
        
        selectsYesNo+="<option value=\""+YesNo[k]+"\">"+YesNo[k]+"</option>";
        
        }
         String[] AD;
         AD= new String[]{"A","D"};
         String selectsAD="";
          for(int l=0;l<AD.length;l++){
        
        selectsAD+="<option value=\""+AD[l]+"\">"+AD[l]+"</option>";
        
        }
        MtoU = "";
        MtoU +="<tr><th colspan=\"10\">Delivery</th></tr>"
               + "<tr><th></th>"
                + "<th style=\"width: 100px;\"> Duration of Labour</th>"
                + "<th style=\"width: 100px;\"> Date of Delivery </th>"
                + "<th style=\"width: 100px;\">Time of Delivery </th>"
                + "<th style=\"width: 100px;\">Gestation at Birth</th>"
                + "<th style=\"width: 100px;\">Mode of Delivery</th>"
                + "<th style=\"width: 100px;\">Placenta Complete</th>"
                + "<th style=\"width: 100px;\">Blood Loss</th>"
                + "<th style=\"width: 100px;\">Condition After Delivery</th>"
                + "<th style=\"width: 100px;\">Other Delivery Complications</th>"
                
                + "</tr>";

        MtoU += "<tr><th></th>"
                + "<th style=\"width: 100px;\"> (m)</th>"
                + "<th style=\"width: 100px;\"> (n) </th>"
                + "<th style=\"width: 100px;\">(o) </th>"
                + "<th style=\"width: 100px;\">(p)</th>"
                + "<th style=\"width: 100px;\">(q)</th>"
                + "<th style=\"width: 100px;\">(r)</th>"
                + "<th style=\"width: 100px;\">(s)</th>"
                + "<th style=\"width: 100px;\">(t)</th>"
                + "<th style=\"width: 100px;\">(u)</th>"
                + "</tr>";

        int count = 1;
        String option = "";
String YesNoSelects="";
        try {
 //============================================================From i to m==========================   
            // query for getting lmp and edd
            query = "Select * from mat_mtou where motherid='" + motherID + "'";
System.out.println(query);
String selectDelivery="";
            conn.rs = conn.state.executeQuery(query);
           String modes="";
            while (conn.rs.next()) {
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

 for(int j=0;j<YesNo.length;j++){
                
                if(YesNo[j].equals(conn.rs.getString("PlacentaComplete"))){
                    
                 YesNoSelects+="<option selected value=\""+YesNo[j]+"\">"+YesNo[j]+"</option>";
                
                }
                
                else{
                YesNoSelects+="<option value=\""+YesNo[j]+"\">"+YesNo[j] +"</option>";
                }
                             }
 String ADSelects="";
 for(int k=0;k<AD.length;k++){
                
                if(AD[k].equals(conn.rs.getString("ConditionAfterDelivery"))){
                    
                 ADSelects+="<option selected value=\""+AD[k]+"\">"+AD[k]+"</option>";
                
                }
                
                else{
                ADSelects+="<option value=\""+AD[k]+"\">"+AD[k] +"</option>";
                }
                             }
              
//               cell++;

                    MtoU += "<tr><th style=\"width:40px;\">" + count1+ "</th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\"  style=\"width:80px;\" value=\"" + conn.rs.getString("LabourDuration") + "\" name=\"LabourDuration" + count1 + "\" id=\"LabourDuration" + count1 + "\" /><input type=\"hidden\" name=\"MtoUID\" value=\"" + conn.rs.getString(1) + "\"></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:80px;\" value=\"" + conn.rs.getString("DeliveryDate") + "\" name=\"DeliveryDate" + count1+ "\" id=\"DeliveryDate" + count1 + "\"/></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:80px;\" value=\"" + conn.rs.getString("DeliveryTime") + "\" name=\"DeliveryTime" + count1 + "\" id=\"DeliveryTime" + count1 + "\" class=\"DeliveryTime" + count1 + "\"></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:80px;\" value=\"" + conn.rs.getString("GestationAtBirth") + "\" name=\"GestationAtBirth" + count1 + "\"  id=\"GestationAtBirth" + count1 + "\"/></th>"
                            + "<th style=\"width:100px;\"><select style=\"width:80px;\"  name=\"DeliveryMode" + count1 + "\"  id=\"DeliveryMode" + count1 + "\"/>"+selectDelivery+"</select></th>"
                            + "<th style=\"width:100px;\"><select style=\"width:80px;\" name=\"PlacentaComplete" + count1 + "\"  id=\"PlacentaComplete" + count1 + "\"/>"+YesNoSelects+"</select></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:80px;\" value=\"" + conn.rs.getString("BloodLoss") + "\" name=\"BloodLoss" + count1 + "\"  id=\"BloodLoss" + count1 + "\"/></th>"
                            + "<th style=\"width:100px;\"><select style=\"width:80px;\"name=\"ConditionAfterDelivery" + count1 + "\"  id=\"ConditionAfterDelivery" + count1 + "\"/>"+ADSelects+"</select></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:80px;\" value=\"" + conn.rs.getString("DeliveryComplications") + "\" name=\"DeliveryComplications" + count1 + "\"  id=\"DeliveryComplications" + count1 + "\"/></th>"
                            + "</tr>";

             
             count1++;
//             count++;
            }

            if (MtoU.equals("")) {
                // allinputfields="No mother found under that ANC No.";
            }
            //_____________________after everything else, then add the default input fields.
            //This table will be used to only save the newly input table values 
            //thus the naming of the input fields is different
  MtoU += "<tr><th style=\"width:40px;\">" + count1+ "</th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\"  style=\"width:80px;\" value=\"\" name=\"New_LabourDuration" +  count + "\" id=\"New_LabourDuration" +  count + "\" /></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_DeliveryDate" + count+ "\" id=\"New_DeliveryDate" + count + "\"/></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_DeliveryTime" + count + "\" id=\"New_DeliveryTime" + count + "\"></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_GestationAtBirth" + count + "\"  id=\"New_GestationAtBirth" + count + "\"/></th>"
                            + "<th style=\"width:100px;\"><select style=\"width:80px;\" name=\"New_DeliveryMode" + count + "\"  id=\"New_DeliveryMode" + count + "\"><option value=\"\"></option>"+deliveryModes+"</select></th>"
                            + "<th style=\"width:100px;\"><select style=\"width:80px;\" name=\"New_PlacentaComplete" + count + "\"  id=\"New_PlacentaComplete" + count + "\"/><option value=\"\"></option>"+selectsYesNo+"</select></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_BloodLoss" + count + "\"  id=\"New_BloodLoss" + count + "\"/></th>"
                            + "<th style=\"width:100px;\"><select style=\"width:80px;\" name=\"New_ConditionAfterDelivery" + count + "\" id=\"New_ConditionAfterDelivery" + count + "\"/><option value=\"\"></option>"+selectsAD+"</select></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_DeliveryComplications" + count + "\"  id=\"New_DeliveryComplications" + count + "\"/></th>"
                            + "</tr>";


            //the hidden field which holds the number of hidden fields to update
            //no_of_old_rows
            MtoU += "<input type=\"hidden\" id=\"MtoU\"  name=\"MtoU\" value=\"" + count + "\"/> <input type=\"hidden\" name=\"MtoU_old_rows\"  id=\"MtoU_old_rows\" value=\"" + (count1 - 1) + "\"/>";

//count++;
            //   System.out.println("ANC NO:" + allinputfields);



            try {
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("" + MtoU + "");
                out.println("</body>");
                out.println("</html>");

                MtoU = "";
            } finally {
                out.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(maternityMtoU.class.getName()).log(Level.SEVERE, null, ex);
        }}
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
            Logger.getLogger(maternityMtoU.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(maternityMtoU.class.getName()).log(Level.SEVERE, null, ex);
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
