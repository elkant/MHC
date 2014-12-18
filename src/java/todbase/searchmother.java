/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todbase;

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
public class searchmother extends HttpServlet {

    String name="";
    String chwlist="";
    String qualifying_chw="";
    dbConn conn;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    name=request.getParameter("name");
    
   chwlist="";
    
    qualifying_chw="";
    qualifying_chw="<tr>"
           
                    + "<th>ANC No</th>"
                    + " <th>First Name</th>"
                    + "<th>Middle name</th> "
                    + "<th>Last name</th>"
                    + "<th>Mother Phone no</th>"
                    + "<th>current chw</th>"
                    + "<th>New chw</th>  "
                    + "<th>view anc reg.</th>" 
                    +"<th>view mother baby book</th>"
                    + "</tr>";
    
    //String allchws="select * from chw";
    
    String selectmums="select * from mother_details where FName='"+name+"' OR SName='"+name+"' OR LName='"+name+"' OR anc_no='"+name+"' OR PhoneNo='"+name+"'";
    
    // System.out.println("Querry:"+selectmums);
    
     conn= new dbConn();
     //get a list of all the chws
     
      getchws();
      conn.rs=conn.st.executeQuery(selectmums);
      
      int count1=0;
    
      while(conn.rs.next()){
      
      count1++;
      
      qualifying_chw+="<tr><td>"+conn.rs.getString("anc_no")+"</td><td>"+conn.rs.getString("FName") +"</td><td>"+conn.rs.getString("SName") +"</td><td>"+conn.rs.getString("LName") +"</td><td>"+conn.rs.getString("PhoneNo")+"</td><td> ";
              conn.rs2=conn.st2.executeQuery("select * from chw where chv_phone='"+conn.rs.getString("ChwID")+"' or chw_id='"+conn.rs.getString("ChwID")+"'");
              while(conn.rs2.next()){
              
               qualifying_chw+= "<p id=\"am_curchw\">"+conn.rs2.getString(2)+" "+conn.rs2.getString(3)+" "+conn.rs2.getString(4)+"</p>";   
                  
              }
              
              qualifying_chw+= "  </td>"
                + "<td>" 
    +"<select  name=\"newchw\" onchange=\"transferchw(this,'"+conn.rs.getString("anc_no")+"');\" ><option value=\"\">select the new chw</option>"+chwlist
                               
                            +"</select>"
                        +"</td>" 
                      
                       +"<td> <a href=\"ANCRegister.jsp?ancnumber="+conn.rs.getString("anc_no")+"\" class=\"linkstyle\" >anc reg</a></td>"
                        +"<td> <a href=\"maternal_profile.jsp?ancnumber="+conn.rs.getString("anc_no")+"\" class=\"linkstyle\" > mother baby</a></td>"
                      +" <td> <a href=\"maternityRegister.jsp\" class=\"linkstyle\" > maternity reg.</a></td>"
                + "</tr>";
    
    
    }
    if(count1<1){
        
        qualifying_chw+="<tr><td colspan=\"7\" style=\"background:yellow;\"> No mother found with the search words you have entered</td></tr>";
        
    }
    
    
    
    PrintWriter out = response.getWriter();
    try {
      
        out.println(qualifying_chw);
        
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(searchmother.class.getName()).log(Level.SEVERE, null, ex);
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

public void getchws(){
        try {
            String allchws="select * from chw";

            conn.rs_6=conn.st_6.executeQuery(allchws);
           while(conn.rs_6.next()){
           chwlist+="<option value=\""+conn.rs_6.getString(1) +"\">"+conn.rs_6.getString(2)+" "+conn.rs_6.getString(3)+" "+conn.rs_6.getString(4)+"</option>";
           
           
           }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(searchmother.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
}


    
    
}
