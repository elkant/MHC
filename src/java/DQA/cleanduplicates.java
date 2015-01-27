
package DQA;

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
 * @author MANUEL
 */
public class cleanduplicates extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        dbConn conn= new dbConn();
     
        String qr []={"delete from  postnat_atof  where char_length(postnat_atof.ID) >=30",
            "delete from  postnat_gtom  where char_length(postnat_gtom.ID) >=30",
            "delete from  postnat_ntot  where char_length(postnat_ntot.ID) >=30",
            "delete from  postnat_utoz  where char_length(postnat_utoz.tableid) >=30",
            "delete from  postnat_aatoae  where char_length(postnat_aatoae.tableid) >=30",
            "delete from  postnat_aftoai  where char_length(postnat_aftoai.tableid) >=30"};
        
        
        String matqr[]={"delete from  mat_atoh  where char_length(MatRegisterID) >=30",
                        "delete from  mat_htol  where char_length(MatRegisterID) >=30",
                        "delete from  mat_mtou  where char_length(MatRegisterID) >=30",
                        "delete from  mat_vtoab  where char_length(tableid) >=30",                         
                        "delete from  mat_actoai  where char_length(tableid) >=30",
                        "delete from  mat_ajtoan  where char_length(tableid) >=30"};
     
        for(int a=0;a<matqr.length;a++){
            try {
                conn.st.executeUpdate(matqr[a]);
                System.out.println(matqr[a]);
                
            } catch (SQLException ex) {
                Logger.getLogger(cleanduplicates.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
         for(int a=0;a<qr.length;a++){
            try {
                conn.st.executeUpdate(qr[a]);
                System.out.println(qr[a]);
                
            } catch (SQLException ex) {
                Logger.getLogger(cleanduplicates.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        
        
        try {
          
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
