/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

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
public class updatemomid extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            String motherID="";
            String ancnumber="";
            String facil="";
            String fname="";
            
          
            motherID=request.getParameter("motherID");
            ancnumber=request.getParameter("ancnumber");
            facil=request.getParameter("facil");
            fname=request.getParameter("fname");
            
            if(facil.equalsIgnoreCase("Ngubureti")){
            
            facil="Ngubereti";
            
            }
            
            //update all mothers which belong to that health facility and anc number and firstname and dont update current recod...
            
           String allqry=" select * from mother_details  where anc_no ='"+ancnumber+"' and facilityname='"+facil+"' and   FName='"+fname+"'  and motherID !='"+motherID+"'";
                dbConn conn= new dbConn();
           
           conn.rs2=conn.st2.executeQuery(allqry);
           
           
           while(conn.rs2.next()){
           //dont use qr for now
            String qr="update mother_details set motherID='"+motherID+"'  where  motherID ='"+conn.rs2.getString("motherID")+"'";
            
            String atohqr="update atoh set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            String itopqr="update itop set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            String qtowqr="update qtow set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            String xtoadqr="update xtoad set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            String aetoakqr="update aetoak set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            String altoanqr="update altoan set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            String ancvisitsqr="update anc_visits set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            String matdetailsqr="update maternal_details set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            
            //update ma id
            String mat1="update mat_actoai set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            String mat2="update mat_ajtoan set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            String mat3="update mat_atoh set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            String mat4="update mat_htol set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            String mat5="update mat_mtou set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            String mat6="update mat_vtoab set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
           
            
            //update postnat id
            String post1="update postnat_aatoae set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            String post2="update postnat_aftoai set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            String post3="update postnat_atof set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            String post4="update postnat_gtom set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            String post5="update postnat_ntot set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
            String post6="update postnat_utoz set motherid='"+motherID+"'  where  motherid ='"+conn.rs2.getString("motherID")+"'";
                       
            //conn.st.executeUpdate(qr);
            conn.st1.executeUpdate(atohqr);
            conn.st1.executeUpdate(itopqr);
            conn.st1.executeUpdate(qtowqr);
            conn.st1.executeUpdate(xtoadqr);
            conn.st1.executeUpdate(aetoakqr);
            conn.st1.executeUpdate(altoanqr);
            conn.st1.executeUpdate(ancvisitsqr);
            conn.st1.executeUpdate(matdetailsqr);
           
            //mat
            conn.st1.executeUpdate(mat1);
            conn.st1.executeUpdate(mat2);
            conn.st1.executeUpdate(mat3);
            conn.st1.executeUpdate(mat4);
            conn.st1.executeUpdate(mat5);
            conn.st1.executeUpdate(mat6);
            
            //postnat
            conn.st1.executeUpdate(post1);
            conn.st1.executeUpdate(post2);
            conn.st1.executeUpdate(post3);
            conn.st1.executeUpdate(post4);
            conn.st1.executeUpdate(post5);
            conn.st1.executeUpdate(post6);
            
            //delete that record from mother_details   
               String del="delete from mother_details where  motherID ='"+conn.rs2.getString("motherID")+"'";
            conn.st1.executeUpdate(del);   
            
               System.out.println(atohqr);
               System.out.println(itopqr);
               System.out.println(qtowqr);
               System.out.println(xtoadqr);
               System.out.println(aetoakqr);
               System.out.println(altoanqr);
               System.out.println(ancvisitsqr);
               System.out.println(matdetailsqr);
               System.out.println(del);
               }
           
        
            
            
//   String qr1=" CREATE TABLE IF NOT EXISTS mdetailstmp SELECT *  FROM mother_details GROUP BY motherID";
//   
//   String qr2=" DROP TABLE IF EXISTS mother_details";
//String qr3="ALTER TABLE mdetailstmp RENAME TO mother_details";
//      conn.st.executeUpdate(qr1);
//     
//      conn.st.executeUpdate(qr2);
//     
//  conn.st.executeUpdate(qr3);
//  
  

        } catch (SQLException ex) {
            Logger.getLogger(updatemomid.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        response.sendRedirect("seeduplicates.jsp");
        
        
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
