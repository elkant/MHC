/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MAT_REG;

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
import mother_child.ANCBean;
import sendSMS.ANCRegister1;
import sendSMS.dbConnect;

/**
 *
 * @author Maureen
 */
public class AtoG extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String admNo = "";
        String motherID = "";
        dbConnect conn = new dbConnect();
        admNo = request.getParameter("admNo");
        motherID = request.getParameter("motherID");
        // for getting the anc no in a bean
        ArrayList ancNO = new ArrayList();
        ANCBean DB = new ANCBean();
        DB.setANC(admNo);
        ancNO.add(DB);
        System.out.println(admNo);
        
       session = request.getSession();
        session.setAttribute("AdmissionNo", admNo);

     //   System.out.println(session.getAttribute("ANC"));
        
        
        
        String ancReg1 = "";
        count1 = 1;
        count = 1;
       
        maritalStatus = new String[]{"","Married", "Widowed", "Single", "Divorced", "Separated"};

        ancREG = "";
        ancReg1 = "";

        ancReg1 += "<tr><th></th>"
                + "<th style=\"width: 10px;\"> Admission Number </th>"
                + "<th style=\"width: 100px;\"> Date of Admission </th>"
                + "<th style=\"width: 100px;\">No. of ANC Visits</th>"
                + "<th style=\"width: 100px;\">First Name</th>"
                + "<th style=\"width: 100px;\">Middle Name </th>"
                + "<th style=\"width: 100px;\">Surname</th>"
                + "<th style=\"width: 100px;\">Village/Estate</th>"
                + "<th style=\"width: 100px;\">Age</th>"
                + "<th style=\"width: 100px;\">Marital Status</th></tr>";
        ancREG += "<tr><th></th>"
                + "<th style=\"width: 100px;\"> a </th>"
                + "<th style=\"width: 100px;\"> b </th>"
                + "<th style=\"width: 100px;\">c </th>"
                + "<th style=\"width: 100px;\">d</th>"
                + "<th style=\"width: 100px;\">d</th>"
                + "<th style=\"width: 100px;\">d </th>"
                + "<th style=\"width: 100px;\">e</th>"
                + "<th style=\"width: 100px;\">f</th>"
                + "<th style=\"width: 100px;\">g</th>"
                + "</tr>";

        String options = "";
        try {

            //============================================================A TO G TABLE DETAILS==========================   
           
             String Matquery="Select * from mat_atoh where motherid='"+motherID+"'";
             if(conn.state.isClosed()){conn = new dbConnect();}
          conn.rs = conn.state.executeQuery(Matquery);
//           System.out.println("B  "+B.toString());
//          
          
          
          
            String datalis="<datalist id=\"villages\">";
            String villageoptions="<option value=\"\">select village</option>";
String loadvillages="select * from  villages order by villagename";

conn.rs5=conn.state5.executeQuery(loadvillages);

while(conn.rs5.next()){

datalis+="<option value=\""+conn.rs5.getString(2) +"\">";
villageoptions+="<option value=\""+conn.rs5.getString(2) +"\">"+conn.rs5.getString(2)+"</option>";


}
datalis+="</datalist>";
          
         
            String newSelects = "";
            for (int i = 0; i < maritalStatus.length; i++) {
                newSelects += "<option value=\"" + maritalStatus[i] + "\">" + maritalStatus[i] + "</option>";
            }
while(conn.rs.next()){
 for (int i = 0; i < maritalStatus.length; i++) {
                        String marital = conn.rs.getString(10);
                        if (maritalStatus[i].equalsIgnoreCase(marital)) {

                            option += "<option selected value=\"" + maritalStatus[i] + "\">" + maritalStatus[i] + "</option>";

                        } else {
                            option += "<option value=\"" + maritalStatus[i] + "\">" + maritalStatus[i] + "</option>";
                        }
 }
                    ancREG += "<tr><td>" + count1 + "</td>"
                             + "<th style=\"width:100px;\"><input type=\"hidden\"   value=\"" + conn.rs.getString(1) + "\" name=\"motherID\" id=\"motherID\"><input class=\"textbox\" type=\"text\" style=\"width:100px;\" value=\"" + conn.rs.getString(2) + "\" name=\"AdmissionNum" + count1 + "\" id=\"AdmissionNum" + count1 + "\"/></th>"
                             + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\"  style=\"width:100px;\"value=\"" + conn.rs.getString(3) + "\" name=\"AdmissionDate" + count1 + "\" id=\"AdmissionDate" + count1 + "\" /><input type=\"hidden\" value=\"" + conn.rs.getString(1) + "\" name=\"ID" + count1 + "\" id=\"ID" + count1 + "\" /></th>"
                             +"<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs.getString(4) + "\" name=\"No_Visits" + count1 + "\"/></td>"
                             + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs.getString(5) + "\" name=\"FirstName" + count1 + "\"/></th>"
                             + "<th style=\"width:100px;\"><input class=\"textbox\"   type=\"text\" style=\"width:100px;\" value=\"" + conn.rs.getString(6) + "\" name=\"SecondName" + count1 + "\"/></th>"
                             + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs.getString(8) + "\" name=\"LastName" + count1 + "\"/></th>"
                             + "<th style=\"width:100px;\"><input list=\"villages\"  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs.getString(7) + "\" name=\"Village" + count1 + "\"/>"+datalis+"</th>"
                             + "<th style=\"width:100px;\"><input   class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs.getString(9) + "\" name=\"Age" + count1 + "\" id=\"Age" + count1 + "\" /></th>"
                            + "<th style=\"width:100px;\"><select style=\"width:100px;\"  name=\"MaritalStatus" + count1 + "\"  id=\"MaritalStatus" + count1 + "\" />" + option + "</select></th></tr>";
                    count1++;

                   
           option="";    
 }




                ancREG += "<tstyler><td style=\"width:40px;\">" + count1 + "</td>"
                        + "<th =\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"\" name=\"New_AdmissionNum" + count + "\" id=\"New_AdmissionNum" + count + "\"/></th>"
                        + "<td style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\"  style=\"width:100px;\" readonly value=\"\" name=\"New_AdmissionDate" + count + "\"  id=\"New_AdmissionDate" + count + "\" /></td>"
                        + "<td style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\"  value=\"\" name=\"New_No_Visits" + count + "\"/></td>"
                        + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"\" name=\"New_FirstName" + count + "\"></th>"
                        + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"\" name=\"New_SecondName" + count + "\"></th>"
                        + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"\" name=\"New_LastName" + count + "\"></th>"
                        + "<th style=\"width:100px;\"><select style=\"width:100px;\"  id=\"New_Village" + count + "\" name=\"New_Village" + count + "\"> "+villageoptions+" </select></th>"
                        + "<th style=\"width:100px;\"><input  class=\"textbox\"   type=\"text\" style=\"width:100px;\" value=\"\" name=\"New_Age" + count + "\" id=\"New_Age" + count + "\" /></th>"
                        + "<td style=\"width:100px;\">"
                        + "<select name=\"New_MaritalStatus" + count + "\" id=\"New_MaritalStatus" + count + "\" style=\"width:100px;\" >"
                        + "" + newSelects + ""
                        + " </select>"
                        + "</td>"
                        + "</tr>";

            newSelects = "";         //the hidden field which holds the number of hidden fields to update
            //no_of_old_rows
            ancREG += "<input type=\"hidden\" id=\"MatRegister_newRows\" name=\"MatRegister_newRows\" value=\"" + count + "\" > <input type=\"hidden\" id=\"MatRegister_old_rows\" value=\"" + (count1 - 1) + "\" name=\"MatRegister_old_rows\" >";



//enter search details in an history..
            
            
            String user1="";
            if(session.getAttribute("userid")!=null){
            
            user1=session.getAttribute("userid").toString();
            }
            
 String history="insert  into history set ancnumber='"+admNo+"', userid='"+user1+"', page='Maternity Register' ";           
 
 
 conn.state.executeUpdate(history);

            try {
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("" + ancReg1 + "");
                out.println("" + ancREG + "");
                out.println("</body>");
                out.println("</html>");

                ancREG = "";
                ancReg1 = "";
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
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AtoG.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(AtoG.class.getName()).log(Level.SEVERE, null, ex);
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
