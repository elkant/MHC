/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MAT_REG;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sender.dbConn;

/**
 *
 * @author SIXTYFOURBIT
 */
public class ajtoan extends HttpServlet {

       String ancno = "";
       String motherID = "";
    String allinputs = "";
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    
    
    allinputs = "";

    int oldrows = 0;
    int newrows = 1;
    int allrows = 0;

    ancno = request.getParameter("ancno");
    motherID= request.getParameter("motherID");
    allinputs+="<tr><th rowspan=\"2\">No</th><th rowspan=\"2\">Delivery <br/>conducted by</th><th rowspan=\"2\">Birth <br/>Notification Number</th><th colspan=\"2\">Discharge</th><th rowspan=\"2\">Comments</th></tr>";
    allinputs+="<tr><th>Date</th><th>Status of Baby</th></tr>";
    allinputs+="<tr><td></td><td>(aj)</td><td>(ak)</td><td>(al)</td><td>(am)</td><td>(an)</td></tr>";
    
    
    String babystatus="<option></option><option value=\"Alive\">Alive</option><option value=\"Dead\">Dead</option>";
    
    dbConn conn = new dbConn();
    
String db="select * from mat_ajtoan where motherid='"+motherID+"'";       

conn.rs=conn.st.executeQuery(db);

while(conn.rs.next()){

       //=============== babystatus options  ================================
                String baby_options_old = "<option value=\"\"></option>";
                if (conn.rs.getString("baby_status").equalsIgnoreCase("Alive")) {

                    baby_options_old += "<option selected value=\"Alive\">Alive</option>";
                    baby_options_old += "<option  value=\"Dead\">Dead</option>";
                }
                else {

                    baby_options_old += "<option selected value=\"Dead\">Dead</option>";
                    baby_options_old += "<option  value=\"Alive\">Alive</option>";

                }
                
    
   oldrows++; 
   allrows++;
 allinputs+="<tr><th>"+allrows+"</th><th><input type=\"hidden\" name=\"ajan_tableid"+oldrows+"\" id=\"ajan_tableid"+oldrows+"\" value=\""+conn.rs.getString(1) +"\"><input type=\"text\" class=\"textbox\" value=\""+conn.rs.getString(3) +"\" placeholder=\"enter name\" name=\"ajan_old_conductor"+oldrows+"\" id=\"ajan_old_conductor"+oldrows+"\" style=\"padding:1px; \"></th><th><input type=\"text\" class=\"textbox\" value=\""+conn.rs.getString(4) +"\" name=\"ajan_old_birthno"+oldrows+"\" id=\"ajan_old_birthno"+oldrows+"\" style=\"padding:1px; width:80px;\"></th><th><input type=\"text\" name=\"ajan_old_date"+oldrows+"\" id=\"ajan_old_date"+oldrows+"\" value=\""+conn.rs.getString(5) +"\" class=\"textbox\" style=\"width:80px;\" ></th><th><select style=\"width:50px;\" name=\"old_status_of_baby"+oldrows+"\"  id=\"old_status_of_baby"+oldrows+"\">"+baby_options_old+"</select></th><th><textarea cols=\"15\" name=\"ajan_old_comments"+oldrows+"\" id=\"ajan_old_comments"+oldrows+"\" rows=\"1\">"+conn.rs.getString(7) +"</textarea></th></tr>";    
    



}




    
    //==============================values from the database================================
    
    
    
    //===============================new values============================================= 

if(1==1){
    allrows++;
   allinputs+="<tr><td>"+allrows+"</td><td><input type=\"text\" class=\"textbox\" placeholder=\"enter name\" name=\"ajan_new_conductor1\" id=\"ajan_new_conductor1\" style=\"padding:1px; \"></td><td><input type=\"text\" class=\"textbox\" placeholder=\"\" name=\"ajan_new_birthno1\" id=\"ajan_new_birthno1\" style=\"padding:1px; width:80px;\"></td><td><input type=\"text\" name=\"ajan_new_date1\" id=\"ajan_new_date1\" class=\"textbox\" style=\"width:80px;\" ></td><td><select style=\"width:50px;\" name=\"new_status_of_baby1\"  id=\"new_status_of_baby1\">"+babystatus+"</select></td><td><textarea cols=\"15\" name=\"ajan_new_comments1\" id=\"ajan_new_comments1\" rows=\"1\"></textarea></td></tr>"; 

} 

 allinputs+="<input type=\"hidden\" value=\""+oldrows+"\" name=\"ajan_oldrows\" id=\"ajan_oldrows\">"
           + "<input type=\"hidden\" name=\"ajan_newrows\" id=\"ajan_newrows\" value=\""+newrows+"\">"
           + "<input type=\"hidden\" name=\"all_rows6_count\" id=\"all_rows6_count\" value=\""+allrows+"\">";
   
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
       out.println(allinputs);
        
       
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(ajtoan.class.getName()).log(Level.SEVERE, null, ex);
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
