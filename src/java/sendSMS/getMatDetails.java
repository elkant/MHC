/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sendSMS;

import com.mysql.jdbc.ResultSetMetaData;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sender.dbConn;

/**
 *
 * @author SIXTYFOURBIT
 */
public class getMatDetails extends HttpServlet {

    String anc_no="";
    String allinputfields;
    HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
String motherID="";
motherID=request.getParameter("motherID");









        anc_no = request.getParameter("ancno");
 String db1a=""; 
 String db1b="";
        
if(!motherID.equals("")){
 db1a="mother_details";
  db1b="maternal_details";
 
}
else{

db1a="blank_mother_details";
 db1b="blank_maternal_details";
}


System.out.println("db1a"+db1a+"db1b  "+db1b);

        session= request.getSession();

        if (anc_no != null) {
            anc_no = anc_no.replace("'", "");
        }
        dbConn conn = new dbConn();

        String getdetails = "Select FName , SName , LName ,Age , Gravida ,parity ,maritalStatus , education  , PhoneNo ,occupation ,nok ,relationship , NOKPhoneNo,delivery_date from "+db1a+" where motherID='" + motherID + "'";

        System.out.println(getdetails);
        
        String MAXTABLEID = "";
      String AGE_sub="";
      
     

        try {

            conn.rs = conn.st.executeQuery("SELECT MAX(tableid) from "+db1b+" where motherid='" + motherID + "'");

            conn.rs.next();
            MAXTABLEID = conn.rs.getString(1);

        } catch (SQLException ex) {
            Logger.getLogger(getMatDetails.class.getName()).log(Level.SEVERE, null, ex);
        }


        String getotherdetails = "Select * from "+db1b+" where motherid='" + motherID + "' and tableid='" + MAXTABLEID + "'";


        allinputfields = "";
        //allinputfields="<tr><td colspan=\"4\">___________________________________________________________________________</td></tr>";


        try {

            //============================================================MAIN TABLE DETAILS==========================   

            conn.rs = conn.st.executeQuery(getdetails);
            //get the values from the databse and put them in the htmlfield appropriately
            java.sql.ResultSetMetaData rsmd = conn.rs.getMetaData();
            int count = 1;
String initialedd="";
            while (conn.rs.next()) {
                //client name

    String name = rsmd.getColumnName(count);
    String mname = rsmd.getColumnName(count+ 1);
    
    System.out.println("__"+mname);
    String lname = rsmd.getColumnName(count+ 2);

                
                
                allinputfields += "<td style=\"width:80px;\">Name Of Client:</td><td><input placeholder=\"first name\" type=\"text\" name=\"" + name + "\" value=\"" + conn.rs.getString(1) + "\"required/></td>"
                        + "<td><input type=\"text\" placeholder=\"middle name\" name=\"" + mname + "\" value=\"" + conn.rs.getString(2) + "\"/></td>"
                        + "<td><input type=\"text\" placeholder=\"last name\" name=\"" + lname + "\" value=\"" + conn.rs.getString(3) + "\"required/></td></tr>";
               //this will be used in the others section
                initialedd=conn.rs.getString("delivery_date");
                
                count = count + 3;
                name = rsmd.getColumnName(count);
                
                String marstatus="";
                if(conn.rs.getString("maritalStatus")!=null){
                marstatus=conn.rs.getString("maritalStatus");
                }
                //age   
 conn.rs_6=conn.st_6.executeQuery("select TIMESTAMPDIFF(YEAR,DOB,CURDATE()) AS AGE FROM "+db1a+" where motherid='"+motherID+"'");
 
 conn.rs_6.next();
 //System.out.println("AAAAGE"+conn.rs_6.getString("AGE"));
 
                allinputfields += "<td style=\"width:80px;\">Age:</td><td><input type=\"text\" pattern=\"[0-9]{2}\" name=\"" + name + "\" value=\"" + conn.rs_6.getString("AGE") + "\"required/></td>";

                count++;
                name = rsmd.getColumnName(count);
                //gravida

                allinputfields += "<td style=\"width:80px;\">Gravida:</td><td><input type=\"text\" pattern=\"[0-9]\" name=\"" + name + "\" value=\"" + conn.rs.getString(5) + "\"required/></td>";
                // parity
                count++;
                name = rsmd.getColumnName(count);
                name = rsmd.getColumnName(count);
                allinputfields += "</tr><tr><td style=\"width:80px;\">Parity:</td><td><input type=\"text\" pattern=\"[0-9]\" name=\"" + name + "\" value=\"" + conn.rs.getString(6) + "\"required/></td>";

                //marital status
                count++;
                name = rsmd.getColumnName(count);
                name = rsmd.getColumnName(count);

                String marriedoptions[] = {"Married", "Single", "Divorced"};
                String mymarroption = "";
                for (int p = 0; p < marriedoptions.length; p++) {
                    if (!marstatus.equalsIgnoreCase(marriedoptions[p])) {

                        mymarroption += "<option value=\"" + marriedoptions[p] + "\">" + marriedoptions[p] + "</option>";

                    }
                }

                allinputfields += "<td style=\"width:80px;\">Marital Status:</td><td><select  name=\"" + name + "\" required> <option selected value=\"" + conn.rs.getString(7) + "\">" + conn.rs.getString(7) + "</option>" + mymarroption + "</select></td>";

                //education
                count++;
                name = rsmd.getColumnName(count);
                name = rsmd.getColumnName(count);
                allinputfields += "</tr><tr><td style=\"width:80px;\"> Education:</td><td><input type=\"text\" name=\"" + name + "\" value=\"" + conn.rs.getString(8) + "\"required/></td>";

//          //telephone
                count++;
                name = rsmd.getColumnName(count);
                name = rsmd.getColumnName(count);
                allinputfields += "<td style=\"width:80px;\">Telephone:</td><td><input type=\"text\" pattern=\"(2547)[0-9]{8}\" placeholder=\"254712345678\" name=\"" + name + "\" value=\"" + conn.rs.getString(9) + "\"/></td>";

//          //occupation
                count++;
                name = rsmd.getColumnName(count);
                name = rsmd.getColumnName(count);
                allinputfields += "</tr><tr><td style=\"width:80px;\">Occupation:</td><td><input type=\"text\" name=\"" + name + "\" value=\"" + conn.rs.getString(10) + "\"/></td>";

//          
                //nok
                count++;
                name = rsmd.getColumnName(count);
                name = rsmd.getColumnName(count);
                allinputfields += "<td style=\"width:80px;\">Next of Kin:</td><td><input type=\"text\" name=\"" + name + "\" value=\"" + conn.rs.getString(11) + "\"required/></td>";

//          //relationship
                count++;
                name = rsmd.getColumnName(count);
                name = rsmd.getColumnName(count);
                allinputfields += "</tr><tr><td style=\"width:80px;\">Relationship:</td><td><input type=\"text\" name=\"" + name + "\" value=\"" + conn.rs.getString(12) + "\"required/></td>";

//          //nok phone
                count++;
                name = rsmd.getColumnName(count);
                name = rsmd.getColumnName(count);
                allinputfields += "<td style=\"width:80px;\">Next of Kin Contacts:</td><td><input type=\"text\" pattern=\"(2547)[0-9]{8}\" maxlength=\"12\" placeholder=\"254712345678\" name=\"" + name + "\" value=\"" + conn.rs.getString(13) + "\"required/></td>";

//          


                //count++;





            }//end of while
//=================================================================MATERNAL DETAILS TABLE==============================

//---------------------------------------------------------------------------------------------------------------------








            conn.rs1 = conn.st1.executeQuery(getotherdetails);
            //get the values from the databse and put them in the htmlfield appropriately
            java.sql.ResultSetMetaData rsmd1 = conn.rs1.getMetaData();
            int count1 = 2;

            while (conn.rs1.next()) {
                //height

                String name1 = rsmd1.getColumnName(count1);

                allinputfields += "</tr><tr><td style=\"width:80px;\">Height:</td><td><input type=\"text\" name=\"" + name1 + "\" placeholder=\"eg. 5 ft 3 inch\" value=\"" + conn.rs1.getString(2) + "\"required/></td>";

                count1++;
                name1 = rsmd1.getColumnName(count1);
                //lmp  

                allinputfields += "<td style=\"width:80px;\">L.M.P:</td><td><input autocomplete=\"off\" placeholder=\"yyyy-MM-dd\" type=\"text\" onblur=\"validatedate('lmp');\" id=\"lmp\"  name=\"" + name1 + "\" value=\"" + conn.rs1.getString(3) + "\"required/></td>";

                count1++;
                name1 = rsmd1.getColumnName(count1);

                //edd

                allinputfields += "</tr><tr><td style=\"width:80px;\">E.D.D:</td><td><input autocomplete=\"off\" placeholder=\"yyyy-MM-dd\" type=\"text\" id=\"edd\"  name=\"" + name1 + "\" value=\"" + conn.rs1.getString(4) + "\"required/>"
                        + "</td>";
                // institution
                count1++;
                name1 = rsmd1.getColumnName(count1);
                allinputfields += "<td><label>Institution:</label></td><td><input type=\"text\" name=\"" + name1 + "\" value=\"" + conn.rs1.getString(5) + "\"required/></td>";
//            
//            count++;





            }//end of while







//(((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((())))))))))))))))))))))))))))))))))))))))))))))))            
            //System.out.println(""+allinputfields);

            //if the lower section {edd, height, lmp, then add a blank column}

            if (count1 == 2 && count > 1) {



                String name1 = rsmd1.getColumnName(count1);

                allinputfields += "</tr><tr><td style=\"width:80px;\">Height:</td><td><input type=\"text\" name=\"" + name1 + "\" required/></td>";

                count1++;
                name1 = rsmd1.getColumnName(count1);
                //lmp  

                allinputfields += "<td style=\"width:80px;\">L.M.P:</td><td><input placeholder=\"yyyy-MM-dd\" type=\"text\" id=\"lmp\" onblur=\"validatedate('lmp');\"  name=\"" + name1 + "\" required/></td>";

                count1++;
                name1 = rsmd1.getColumnName(count1);

                //edd

                allinputfields += "</tr><tr><td style=\"width:80px;\">E.D.D:</td><td><input placeholder=\"yyyy-MM-dd\" type=\"text\" id=\"edd\" value=\""+initialedd+"\" name=\"" + name1 + "\" required/>"
                        + "</td>";
                // institution
                count1++;
                name1 = rsmd1.getColumnName(count1);
                allinputfields += "<td><label>Institution:</label></td><td><input type=\"text\" name=\"" + name1 + "\" required/></td>";
//            
//            count++;





            }

            //=========end of ALternative lower section        

            if (allinputfields.equals("")) {

                allinputfields = "<font color=\"red\">No mother found under that ANC No.Register her <br/><a href=\"DisplayRegForm.jsp\" class=\"linkstyle\">here</a>  </font>";
            }
            
//enter search details in an history..
            
            
            String user="";
            if(session.getAttribute("userid")!=null){
            
            user=session.getAttribute("userid").toString();
            }
            
 String history="insert  into history set ancnumber='"+anc_no+"', userid='"+user+"', page='Mother Baby book' ";           
 
 
 conn.st_6.executeUpdate(history);
            
 

            PrintWriter out = response.getWriter();
            try {

                out.println("<tr>" + allinputfields + "</tr>");



            } finally {
                out.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(getMatDetails.class.getName()).log(Level.SEVERE, null, ex);
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
