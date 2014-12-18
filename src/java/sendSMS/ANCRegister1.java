/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sendSMS;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mother_child.ANCBean;

/**
 *
 * @author Maureen
 */
public class ANCRegister1 extends HttpServlet {

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
        String anc_no = "";
        dbConnect conn = new dbConnect();
        anc_no = request.getParameter("ancno");
        
        
        
         String motherID = request.getParameter("motherID");
        
        // for getting the anc no in a bean
        ArrayList ancNO = new ArrayList();
        ANCBean DB = new ANCBean();
        DB.setANC(anc_no);
        ancNO.add(DB);
        System.out.println(anc_no);
        
       session = request.getSession();
        session.setAttribute("ANC", anc_no);

     //   System.out.println(session.getAttribute("ANC"));
        
        
        String yesno[] = new String[]{"Yes", "No"};
        String ancReg1 = "";
        count1 = 1;
        count = 1;
        query = "Select * from mother_details where motherID='" + motherID + "'";

        maritalStatus = new String[]{"","Married", "Widowed", "Single", "Divorced", "Separated"};

        ancREG = "";
        ancReg1 = "";

        ancReg1 += "<tr><th></th>"
                + "<th style=\"width: 40px;\"> Date of Visit </th>"
                + "<th style=\"width: 60px;\"> ANC No </th>"
                + "<th style=\"width: 100px;\">1st Visit </th>"
                + "<th style=\"width: 80px;\">Number of Visits</th>"
                + "<th style=\"width: 100px;\">First Name</th>"
                + "<th style=\"width: 100px;\">Middle Name </th>"
                + "<th style=\"width: 100px;\">Surname</th>"
                + "<th style=\"width: 100px;\">Village/Estate</th>"
                + "<th style=\"width: 60px;\">Age</th>"
                + "<th style=\"width: 100px;\">Marital Status</th></tr>";
        ancREG += "<tr><th></th>"
                + "<th style=\"width: 40px;\"> a </th>"
                + "<th style=\"width: 60px;\"> b </th>"
                + "<th style=\"width: 100px;\">c </th>"
                + "<th style=\"width: 80px;\">d</th>"
                + "<th style=\"width: 100px;\">e</th>"
                + "<th style=\"width: 100px;\">e </th>"
                + "<th style=\"width: 100px;\">e</th>"
                + "<th style=\"width: 100px;\">f</th>"
                + "<th style=\"width: 60px;\">g</th>"
                + "<th style=\"width: 100px;\">h</th></tr>";

        String options = "";
        try {

            //============================================================PREVIOUS PREGNANCY TABLE DETAILS==========================   
            String newSelects = "";
            for (int i = 0; i < maritalStatus.length; i++) {
                newSelects += "<option value=\"" + maritalStatus[i] + "\">" + maritalStatus[i] + "</option>";
            }

            String datalis="<datalist id=\"villages\">";
            String villageoptions="<option value=\"\">select village</option>";
String loadvillages="select * from  villages order by villagename";

conn.rs5=conn.state5.executeQuery(loadvillages);

while(conn.rs5.next()){

datalis+="<option value=\""+conn.rs5.getString(2) +"\">";
villageoptions+="<option value=\""+conn.rs5.getString(2) +"\">"+conn.rs5.getString(2)+"</option>";


}
datalis+="</datalist>";

            querys = "Select * from atoh where motherid='" + motherID + "'";
            int AtoHCount = 0;
            conn.rs2 = conn.state1.executeQuery(querys);
            conn.rs3 = conn.state2.executeQuery(querys);
            conn.rs4 = conn.state3.executeQuery(querys);
            conn.rs5 = conn.state4.executeQuery(querys);
            conn.rs_1 = conn.state_1.executeQuery(querys);
            conn.rs_2 = conn.state_2.executeQuery(querys);
            conn.rs8 = conn.state7.executeQuery(query);


            if (conn.rs_2.next() == true) {
                visits = conn.rs_2.getString(4);
//System.out.println("S___________"+visits);

                if (visits.equals("Yes")) {
                    options += "<option value=\"\"></option>"
                            + "<option value=\"Yes\">Yes</option>"
                            + "<option selected value=\"No\">No</option>";
                }
                if (visits.equals("No")) {
                    options += "<option value=\"\"></option>"
                            + "<option value=\"Yes\">Yes</option>"
                            + "<option  value=\"No\">No</option>";
                }

            } else {

                options += "<option value=\"\"></option>"
                        + "<option value=\"Yes\">Yes</option>"
                        + "<option  value=\"No\">No</option>";


            }
            while (conn.rs_1.next()) {
//   AtoHCount++;
//String a=  conn.rs_1.getString(2);
//System.out.println("INSTANCE __________________"+ AtoHCount);


                int cell1 = 0;
                if (conn.rs2.next() == true) {
                    cell1++;
                    ancREG += "<tr><td>" + count1 + "</td>"
                            + "<th style=\"width:80px;\"><input type=\"text\"  style=\"width:80px;\"value=\"" + conn.rs2.getString(3) + "\" placeholder=\"yyyy-mm-dd\" name=\"VisitDate" + count1 + "\" id=\"VisitDate" + count1 + "\" /><input type=\"hidden\" value=\"" + conn.rs2.getString(1) + "\" name=\"ID" + count1 + "\" id=\"ID" + count1 + "\" /></th>";
                }
                if (cell1 == 0) {
                    ancREG += "<tr><td>" + count1 + "</td>"
                            + "<td style=\"width:80px;\"><input type=\"text\"  style=\"width:80px;\"value=\"\" placeholder=\"yyyy-mm-dd\" name=\"VisitDate" + count1 + "\" id=\"VisitDate" + count1 + "\" /></td>";
                }

                conn.rs1 = conn.state.executeQuery(query);
                conn.rs6 = conn.state5.executeQuery(query);
                conn.rs7 = conn.state6.executeQuery(query);

                //get the values from the databse and put them in the htmlfield appropriately

                int cell2 = 0;
                if (conn.rs1.next() == true) {
                    cell2++;
                    ancREG += "<th style=\"width:100px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs1.getString(2) + "\" name=\"anc_no" + count1 + "\" id=\"anc_no" + count1 + "\"/></th>";

                }
                if (cell2 == 0) {
                    ancREG += "<td style=\"width:100px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"anc_no" + count1 + "\" id=\"anc_no" + count1 + "\"/></td>";


                }
                //end of while
                // to get data from anc register for the no of visits

                String visitOption = "";
                int cell3 = 0;


                if (conn.rs3.next() == true) {
                    cell3++;
                    String visit = conn.rs3.getString(4);
                    for (int p = 0; p < yesno.length; p++) {
//         System.out.println("Yes/No"+yesno[p]);
                        if (yesno[p].equalsIgnoreCase(visit)) {
                            visitOption += "<option selected value=\"" + yesno[p] + "\">" + yesno[p] + "</option>";

                        } else {
                            visitOption += "<option value=\"" + yesno[p] + "\">" + yesno[p] + "</option>";
                        }
                    }
                    ancREG += "<th style=\"width:100px;\">"
                            + "<select style=\"width:80px;\"name=\"FirstVisit" + count1 + "\" id=\"FirstVisit" + count1 + "\">"
                            + "" + visitOption + ""
                            + "</select></th>";


                }
                if (cell3 == 0) {
                    if (visits.equalsIgnoreCase("Yes")) {
                        ancREG += "<td style=\"width:100px;\">"
                                + "<select style=\"width:80px;\"name=\"New_FirstVisit" + count1 + "\" id=\"New_FirstVisit" + count1 + "\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option selected value=\"No\">No</option>"
                                + "</select></td>";
                    } else {
                        ancREG += "<td style=\"width:100px;\">"
                                + "<select style=\"width:80px;\"name=\"New_FirstVisit" + count1 + "\" id=\"New_FirstVisit" + count1 + "\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option  value=\"No\">No</option>"
                                + "</select></td>";
                    }
                }
                int cell4 = 0;
                if (conn.rs6.next() == true) {
                    cell4++;
                    ancREG += "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs6.getString(12) + "\" name=\"No_Visits" + count1 + "\"/></td>"
                            + "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs6.getString(4) + "\" name=\"FName" + count1 + "\"/></th>"
                            + "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs6.getString(5) + "\" name=\"SName" + count1 + "\"/></th>"
                            + "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs6.getString(6) + "\" name=\"LName" + count1 + "\"/></th>";
                }

                if (cell4 == 0) {
                    ancREG += "     <td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_No_Visits" + count1 + "\"/></td>"
                            + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_FirstName" + count1 + "\"/></td>"
                            + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_SecondName" + count1 + "\"/></td>"
                            + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_LastName" + count1 + "\"/></td>";

                }
                int cell5 = 0;
                if (conn.rs4.next() == true) {

                    cell5++;
                    ancREG += "<th style=\"width:80px;\"><input list=\"villages\" type=\"text\" style=\"width:80px;\" value=\"" + conn.rs4.getString(5) + "\" name=\"Village" + count1 + "\"/>"+datalis+"</th>";

                }
                if (cell5 == 0) {
                    ancREG += "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_Village" + count1 + "\"/></td>";

                }
                int cell6 = 0;

                if (conn.rs7.next() == true) {
                    cell6++;


                    for (int i = 0; i < maritalStatus.length; i++) {
                        String marital = conn.rs7.getString("maritalStatus");
                        if (maritalStatus[i].equalsIgnoreCase(marital)) {

                            option += "<option selected value=\"" + maritalStatus[i] + "\">" + maritalStatus[i] + "</option>";

                        } else {
                            option += "<option value=\"" + maritalStatus[i] + "\">" + maritalStatus[i] + "</option>";
                        }
                    }
//    for(int i=0;i<maritalStatus.length;i++){
//             
//               if(!(conn.rs7.getString(18)).equalsIgnoreCase(maritalStatus[i])){
//                   option+="<option value=\""+maritalStatus[i]+"\">"+maritalStatus[i]+"</option>";
//               }
//               
//               }

                    String DOB = conn.rs7.getString("DOB");


                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
                    String formattedDate = formatter.format(date);




//System.out.println("Dates"+Integer.parseInt(formattedDate)-Integer.parseInt(DOB));
                    ancREG += "<th style=\"width:60px;\"><input  type=\"text\" style=\"width:60px;\" value=\"" + conn.rs7.getString("Age") + "\" name=\"Age" + count1 + "\" id=\"Age" + count1 + "\" /></th>"
                            + "<th style=\"width:100px;\"><select style=\"width:80px;\"  name=\"MaritalStatus" + count1 + "\"  id=\"MaritalStatus" + count1 + "\" />" + option + "</select></th></tr>";
                    count1++;


                }

                if (cell6 == 0) {
                    ancREG += "<td style=\"width:60px;\"><input  type=\"text\" style=\"width:60px;\" value=\"\" name=\"New_Age" + count1 + "\" id=\"New_Age" + count1 + "\" /></td>"
                            + "<td style=\"width:100px;\"><select style=\"width:80px;\"  name=\"New_MaritalStatus" + count1 + "\" id=\"New_MaritalStatus" + count1 + "\">"
                            + " <option value=\"\"></option>"
                            + newSelects + "</select></td></tr>";
                    count1++;
                }
                option = "";
            }



//            if (ancREG.equals("")) {
//                // allinputfields="No mother found under that ANC No.";
//            }
            //_____________________after everything else, then add the default input fields.
            //This table will be used to only save the newly input table values 
            //thus the naming of the input fields is different

            if (conn.rs8.next()) {

                ancREG += "<tr><td style=\"width:40px;\">" + count1 + "</td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" readonly  style=\"width:80px;\"value=\"\" placeholder=\"yyyy-mm-dd\" name=\"New_VisitDate" + count + "\"  id=\"New_VisitDate" + count + "\" /></td>"
                        + "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs8.getString(2) + "\" name=\"New_ANCNo" + count + "\" id=\"New_ANCNo" + count + "\"/></th>"
                        + "<td style=\"width:80px;\">"
                        + "<select style=\"width:80px;\"name=\"New_FirstVisit" + count + "\" id=\"New_FirstVisit" + count + "\">"
                        + options
                        + "</select></td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_No_Visits" + count + "\"/></td>"
                        + "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs8.getString("FName") + "\" name=\"New_FirstName" + count + "\"></th>"
                        + "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs8.getString("SName") + "\" name=\"New_SecondName" + count + "\"></th>"
                        + "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs8.getString("LName") + "\" name=\"New_LastName" + count + "\"></th>"
                        + "<td style=\"width:80px;\"><input list=\"villages\" type=\"text\" style=\"width:80px;\" value=\"\" id=\"New_Village" + count + "\" name=\"New_Village" + count + "\"/></td>"
                        + "<th style=\"width:80px;\"><input  type=\"text\" style=\"width:60px;\" value=\"" + conn.rs8.getString("Age") + "\" name=\"New_Age" + count + "\" id=\"New_Age" + count + "\" /></th>"
                        + "<td style=\"width:80px;\">"
                        + "<select name=\"New_MaritalStatus" + count + "\" id=\"New_MaritalStatus" + count + "\" style=\"width:80px;\" >"
                        + "" + newSelects + ""
                        + " </select>"
                        + "</td>"
                        + "</tr>";


            } else {
                  ancREG += "<tr><td style=\"width:40px;\">" + count1 + "</td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" readonly  style=\"width:80px;\"value=\"\" placeholder=\"yyyy-mm-dd\" name=\"New_VisitDate" + count + "\"  id=\"New_VisitDate" + count + "\" /></td>"
                        + "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_ANCNo" + count + "\" id=\"New_ANCNo" + count + "\"/></th>"
                        + "<td style=\"width:80px;\">"
                        + "<select style=\"width:80px;\"name=\"New_FirstVisit" + count + "\" id=\"New_FirstVisit" + count + "\">"
                        + options
                        + "</select></td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" id=\"New_No_Visits" + count + "\" name=\"New_No_Visits" + count + "\"/></td>"
                        + "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" id=\"New_FirstName" + count + "\" name=\"New_FirstName" + count + "\"></th>"
                        + "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" id=\"New_SecondName" + count + "\" name=\"New_SecondName" + count + "\"></th>"
                        + "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" id=\"New_LastName" + count + "\" name=\"New_LastName" + count + "\"></th>"
                        + "<td style=\"width:80px;\"><select   style=\"width:80px;\"  id=\"New_Village" + count + "\"  name=\"New_Village" + count + "\">"+villageoptions+"</select></td>"
                        + "<th style=\"width:80px;\"><input  type=\"text\" style=\"width:60px;\" value=\"\" name=\"New_Age" + count + "\" id=\"New_Age" + count + "\" /></th>"
                        + "<td style=\"width:80px;\">"
                        + "<select name=\"New_MaritalStatus" + count + "\" id=\"New_MaritalStatus" + count + "\" style=\"width:80px;\" >"
                        + "" + newSelects + ""
                        + " </select>"
                        + "</td>"
                        + "</tr>";
               // ancREG = "<table style=\"margin-left=\"50px;\"><tr><td style=\"height:100px;\" ><font color=\"red\">You have not filled data in mother-baby module for this ANC No.<br/>Kindly fill the mother-baby module for this ANC No. before proceeding.</font></td></tr></table>";


            }
            newSelects = "";         //the hidden field which holds the number of hidden fields to update
            //no_of_old_rows
            ancREG += "<input type=\"hidden\" id=\"ANCRegister_newRows\" name=\"ANCRegister_newRows\" value=\"" + count + "\" > <input type=\"hidden\" id=\"ANCRegister1_old_rows\" value=\"" + (count1 - 1) + "\" name=\"ANCRegister1_old_rows\" >";


            //   System.out.println("ANC NO:" + allinputfields);

//enter search details in an history..
            
            
            String user1="";
            if(session.getAttribute("userid")!=null){
            
            user1=session.getAttribute("userid").toString();
            }
            
 String history="insert  into history set ancnumber='"+anc_no+"', userid='"+user1+"', page='ANC Register' ";           
 
 
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
            Logger.getLogger(ANCRegister1.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ANCRegister1.class.getName()).log(Level.SEVERE, null, ex);
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
