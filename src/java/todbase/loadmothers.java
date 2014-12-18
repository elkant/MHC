/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todbase;

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
import sender.dbConn;

/**
 *
 * @author Manuel
 */
public class loadmothers extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();


            dbConn conn = new dbConn();

            String mothers = "select  mother_details.motherID as motherID,anc_no,FName,SName,LName,PhoneNo , facilityname,AdmissionNo,PNCRegNo  from mother_details LEFT join atoh on mother_details.motherID=atoh.motherid "
+" LEFT join mat_atoh on mat_atoh.motherid =mother_details.motherID "
+" LEFT join postnat_atof on mother_details.motherID=postnat_atof.motherid group by mother_details.motherID ";

            if (conn.st1.isClosed()) {
                conn = new dbConn();
            }
            System.out.println(mothers);
            
            conn.rs1 = conn.st1.executeQuery(mothers);
     //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%       
     //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%       
           
            
            
        String createdtable="";    
            
            
            
           createdtable="<thead><tr><th>ANC No</th><th>First Name</th><th>Middle name</th><th>Last name</th><th>Mother Phone no</th>"; 
           createdtable+="<th>Facility</th><th>mother baby book</th><th>anc reg.</th><th>maternity reg.</th><th>postnatal reg.</th></tr></thead><tbody>";                  
                             int count = 1;
  while (conn.rs1.next()) {

                            
                            String mumid = conn.rs1.getString("motherID");
                            String ancno = conn.rs1.getString("anc_no");
                            //String fname = conn.rs1.getString("FName");
//
                            createdtable+="<tr id=\""+mumid+"\">";
//
//
        //createdtable+="<td class=\"format_cell\"><input type=\"hidden\" name=\"userid\" value=\""+mumid+"\"/>"+ancno+"</td><td class=\"format_cell\">"+fname+"</td>";
//        createdtable+="<td class=\"format_cell\">"+conn.rs1.getString("SName")+"</td><td class=\"format_cell\">"+conn.rs1.getString("LName")+"</td>"; 
//        createdtable+="<td class=\"format_cell\">"+conn.rs1.getString("PhoneNo")+"</td>";
//        createdtable+="<td class=\"format_cell\">"+conn.rs1.getString("facilityname")+"</td>";
//
// createdtable+="<td> <a href=\"maternal_profile.jsp?motherID="+conn.rs1.getString("motherID")+"&ancnumber="+conn.rs1.getString("anc_no")+"&facil="+conn.rs1.getString("facilityname")+"\" class=\"linkstyle\" > mother baby</a></td>";
//                                
// createdtable+="<td> <a href=\"ANCRegister.jsp?motherID="+conn.rs1.getString("motherID")+"&ancnumber="+conn.rs1.getString("anc_no")+"&facil="+conn.rs1.getString("facilityname")+"\" class=\"linkstyle\" >anc reg</a></td>";
//
// createdtable+="<td> <a href=\"maternityRegister.jsp?motherID="+conn.rs1.getString("motherID")+"&admNo="+conn.rs1.getString("mat_atoh.AdmissionNo")+"&facil="+conn.rs1.getString("facilityname")+"\" class=\"linkstyle\" > maternity reg.</a></td>";
//                                
// createdtable+="<td> <a href=\"postnatal.jsp?motherID="+conn.rs1.getString("motherID")+"&pncNo="+conn.rs1.getString("postnat_atof.PNCRegNo")+"&facil="+conn.rs1.getString("facilityname")+"\" class=\"linkstyle\" > Postnatal reg.</a></td>";
//                               
//                         createdtable+=" </tr>";
                            System.out.println("ROW NUMBER__"+count);
                                    count++;
                                }

                                if (count == 1) {
createdtable+="<tr><td colspan=\"11\" style=\"background:yellow ;\">No Mothers details that have been uploaded to the system yet</td></tr><tr></tr>";

                        }

                        createdtable+="</tbody>"; 

            
            
            
            
            
            
            
            
            
            
     //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%       
     //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%       
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            

            try {
                
                out.println(createdtable);
                System.out.println(createdtable);
            } finally {
                out.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(loadmothers.class.getName()).log(Level.SEVERE, null, ex);
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
