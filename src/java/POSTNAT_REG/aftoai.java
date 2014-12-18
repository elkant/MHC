/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package POSTNAT_REG;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.quartz.utils.DBConnectionManager;
import sender.dbConn;

/**
 *
 * @author SIXTYFOURBIT
 */
public class aftoai extends HttpServlet {

    String motherid = "";
    String allinputs = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");


           
            String yesno_arr[] = {"","Y", "N"};
           

            allinputs = "";

            int oldrows = 0;
            int newrows = 1;
           int allrows=0;
            motherid = request.getParameter("motherid");

            dbConn conn = new dbConn();

            allinputs += "<tr><th>No.</th><th colspan=\"2\">Treatment</th> <th rowspan=\"2\">Referred</th><th rowspan=\"2\">Remarks </th></tr>";
            allinputs += "<tr><th ></th><th>Multi-vitamin</th><th>Haematinics</th></tr>";

            allinputs += "<tr><td></td><td>(af)</td><td>(ag)</td><td>(ah)</td><td>(ai)</td></tr>";
            
            
            String myquery = "select * from postnat_aftoai where motherid='" + motherid + "'";

            conn.rs = conn.st4.executeQuery(myquery);

            //======================get the already existing content=====================
           String yes_no_options = "<option value=\"\"></option><option value=\"Y\">Y</option><option value=\"N\">N</option>";
            





            while (conn.rs.next()) {

                oldrows++;
                allrows++;
                
                
                //===============================================Yes no options=========================        


                String multivitamin_options_old = "<option value=\"\"></option>";
                for (int b = 0; b < yesno_arr.length; b++) {

                    if (conn.rs.getString("multivitamin").equalsIgnoreCase(yesno_arr[b])) {

                        multivitamin_options_old += "<option selected value=\"" + yesno_arr[b] + "\">" + yesno_arr[b] + "</option>";

                    }
                    else {
                        
                        multivitamin_options_old += "<option  value=\"" + yesno_arr[b] + "\">" + yesno_arr[b] + "</option>";
                        
                    }
                }
                
             
                
                //=============== haema options  ================================
                String haema_options_old = "<option value=\"\"></option>";
               for (int b = 0; b < yesno_arr.length; b++) {

                    if (conn.rs.getString("haematinics").equalsIgnoreCase(yesno_arr[b])) {

                        haema_options_old += "<option selected value=\"" + yesno_arr[b] + "\">" + yesno_arr[b] + "</option>";

                    }
                    else {
                        
                        haema_options_old += "<option  value=\"" + yesno_arr[b] + "\">" + yesno_arr[b] + "</option>";
                        
                    }
                }
            
            
                
//===========================================================================
 allinputs +="<tr><th rowspan=\"1\"> <input type=\"hidden\" name=\"afaitableid"+oldrows+"\" value=\""+conn.rs.getString(1) +"\"> "+allrows+"</th><th rowspan=\"1\"><select  name=\"old_multivitamin"+oldrows+"\" id=\"old_multivitamin"+oldrows+"\" >"+multivitamin_options_old+"</select></th>         <th > <Select id=\"old_haematinics"+oldrows+"\" style=\"width:39px;\" name=\"old_haematinics"+oldrows+"\">" + haema_options_old + " </Select></th>     <th><input type=\"text\" value=\""+conn.rs.getString("referred") +"\" class=\"textbox\" id=\"old_ref"+oldrows+"\" style=\"width:109px;\" name=\"old_ref"+oldrows+"\"></th>        <th> <Textarea id=\"old_remarks"+oldrows+"\" style=\"\" name=\"old_remarks"+oldrows+"\"   cols=\"30\" rows=\"3\">"+conn.rs.getString("remarks")+"</Textarea></th>  </tr>";
            }


            //======================the new values=========================  

   if(1==1){
    allrows++;
allinputs +="<tr><td rowspan=\"1\">"+allrows+"</td><td rowspan=\"1\"><select style=\"\" name=\"new_multivitamin"+newrows+"\" id=\"new_multivitamin"+newrows+"\" >"+yes_no_options+"</select></td>         <td > <Select id=\"new_haematinics"+newrows+"\" style=\"width:39px;\" name=\"new_haematinics"+newrows+"\">" + yes_no_options + " </Select></td>     <td > <input type=\"text\" class=\"textbox\" id=\"new_ref"+newrows+"\" style=\"width:129px;\" name=\"new_ref"+newrows+"\"></td>        <td> <Textarea id=\"new_remarks"+newrows+"\" style=\"\" name=\"new_remarks"+newrows+"\"   cols=\"30\" rows=\"3\"> </Textarea></td>  </tr>";

  allinputs+="<input type=\"hidden\" value=\""+oldrows+"\" name=\"afai_oldrows\" id=\"afai_oldrows\">"
                    + "<input type=\"hidden\" name=\"afai_newrows\" id=\"afai_newrows\" value=\""+newrows+"\">"
                    + "<input type=\"hidden\" name=\"all_rows6_count\" id=\"all_rows6_count\" value=\""+allrows+"\">";

         }
            PrintWriter out = response.getWriter();
            try {

                out.println(allinputs);

            } finally {
                out.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(utoz.class.getName()).log(Level.SEVERE, null, ex);
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
