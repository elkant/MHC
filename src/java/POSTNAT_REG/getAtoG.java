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
public class getAtoG extends HttpServlet {

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
       
        maritalStatus = new String[]{"Married", "Widowed", "Single", "Divorced", "Separated"};

        ancREG = "";
        PostNat1 = "";

        PostNat1 += "<tr><th></th>"
                + "<th style=\"width: 10px;\"> Date of Visit </th>"
                + "<th style=\"width: 100px;\"> PNC Register Number </th>"
                + "<th style=\"width: 100px;\">Adm. No.</th>"
                + "<th style=\"width: 100px;\">First Name</th>"
                + "<th style=\"width: 100px;\">Middle Name </th>"
                + "<th style=\"width: 100px;\">Surname</th>"
                + "<th style=\"width: 100px;\">Village/Estate</th>"
                + "<th style=\"width: 100px;\">Age</th>";
             
        PostNat1 += "<tr><th></th>"
                + "<th style=\"width: 100px;\"> a </th>"
                + "<th style=\"width: 100px;\"> b </th>"
                + "<th style=\"width: 100px;\">c </th>"
                + "<th style=\"width: 100px;\">d</th>"
                + "<th style=\"width: 100px;\">d</th>"
                + "<th style=\"width: 100px;\">d </th>"
                + "<th style=\"width: 100px;\">e</th>"
                + "<th style=\"width: 100px;\">f</th>"
             
                + "</tr>";

        String options = "";
        try {

            //============================================================A TO G TABLE DETAILS==========================   
           
             String Matquery="Select * from postnat_atof where motherid='"+motherID+"'";
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
          
          
          
          
          
          
          String query1= "select * from mother_details where motherid='"+motherID+"'";
             conn.rs1 = conn.state1.executeQuery(query1);
             conn.rs2 = conn.state2.executeQuery(query1);
         
while(conn.rs.next()){
conn.rs1.next();
                    PostNat1 += "<tr><td>" + count1 + "</td>"
                             + "<th style=\"width:100px;\"><input type=\"hidden\"   value=\"" + conn.rs.getString("motherid") + "\" name=\"motherID\" id=\"motherID\"><input class=\"textbox\" type=\"text\" style=\"width:100px;\" value=\"" + conn.rs.getString(2) + "\" readonly name=\"VisitDate" + count1 + "\" id=\"VisitDate" + count1 + "\"/></th>"
                             + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\"  style=\"width:100px;\"value=\"" + conn.rs.getString(3) + "\" name=\"PNCNo" + count1 + "\" id=\"PNCNo" + count1 + "\" /><input type=\"hidden\" value=\"" + conn.rs.getString(1) + "\" name=\"ID" + count1 + "\" id=\"ID" + count1 + "\" /></th>"
                             +"<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs.getString(4) + "\" name=\"AdmNo" + count1 + "\"/></td>"
                             + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs1.getString("FName") + "\" name=\"FirstName" + count1 + "\"/></th>"
                             + "<th style=\"width:100px;\"><input class=\"textbox\"   type=\"text\" style=\"width:100px;\" value=\"" + conn.rs1.getString("SName") + "\" name=\"SecondName" + count1 + "\"/></th>"
                             + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs1.getString("LName") + "\" name=\"LastName" + count1 + "\"/></th>"
                             + "<th style=\"width:100px;\"><input list=\"villages\" class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs.getString("Village") + "\" name=\"Village" + count1 + "\"/>"+datalis+"</th>"
                             + "<th style=\"width:100px;\"><input   class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs1.getString("Age") + "\" name=\"Age" + count1 + "\" id=\"Age" + count1 + "\" /></th></tr>";
                            
                    count1++;

                   
           option="";    
 }


if(conn.rs2.next()){

                PostNat1 += "<tr><tstyler><td style=\"width:40px;\">" + count1 + "</td>"
                        + "<th =\"width:100px;\"><input  class=\"textbox\"  type=\"text\" readonly  style=\"width:100px;\" value=\"\" name=\"New_VisitDate" + count + "\" id=\"New_VisitDate" + count + "\"/></th>"
                        + "<td style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\"  style=\"width:100px;\" value=\"\" name=\"New_PNCNo" + count + "\"  id=\"New_PNCNo" + count + "\" /></td>"
                        + "<td style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\"  value=\"\" name=\"New_AdmNo" + count + "\"/></td>"
                        + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs2.getString("FName") + "\" name=\"New_FirstName" + count + "\"></th>"
                        + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs2.getString("SName") + "\" name=\"New_SecondName" + count + "\"></th>"
                        + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs2.getString("LName") + "\" name=\"New_LastName" + count + "\"></th>"
                        + "<th style=\"width:100px;\"><select   style=\"width:100px;\"  id=\"New_Village" + count + "\" name=\"New_Village" + count + "\">"+villageoptions+"</select></th>"
                        + "<th style=\"width:100px;\"><input  class=\"textbox\"   type=\"text\" style=\"width:100px;\" value=\"" + conn.rs2.getString("Age") + "\" name=\"New_Age" + count + "\" id=\"New_Age" + count + "\" /></th></tr>"
                       
                        + "</tr>";
}
else{
 PostNat1 += "<tr><tstyler><td style=\"width:40px;\">" + count1 + "</td>"
                        + "<th =\"width:100px;\"><input readonly  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"\" name=\"New_VisitDate" + count + "\" id=\"New_VisitDate" + count + "\"/></th>"
                        + "<td style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\"  style=\"width:100px;\" value=\"\" name=\"New_PNCNo" + count + "\"  id=\"New_PNCNo" + count + "\" /></td>"
                        + "<td style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\"  value=\"\" name=\"New_AdmNo" + count + "\"/></td>"
                        + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"\" name=\"New_FirstName" + count + "\"></th>"
                        + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"\" name=\"New_SecondName" + count + "\"></th>"
                        + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"\" name=\"New_LastName" + count + "\"></th>"
                        + "<th style=\"width:100px;\"><select  style=\"width:100px;\"  id=\"New_Village" + count + "\" name=\"New_Village" + count + "\"/>"+villageoptions+"</select></th>"
                        + "<th style=\"width:100px;\"><input  class=\"textbox\"   type=\"text\" style=\"width:100px;\" value=\"\" name=\"New_Age" + count + "\" id=\"New_Age" + count + "\" /></th></tr>"
                       
                        + "</tr>";
}
            //no_of_old_rows
            PostNat1 += "<input type=\"hidden\" id=\"PNCRegister_newRows\" name=\"PNCRegister_newRows\" value=\"" + count + "\" > <input type=\"hidden\" id=\"PNCRegister_old_rows\" value=\"" + (count1 - 1) + "\" name=\"PNCRegister_old_rows\" >";



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
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(getAtoG.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(getAtoG.class.getName()).log(Level.SEVERE, null, ex);
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
