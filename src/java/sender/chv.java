/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SIXTYFOURBIT
 */
public class chv extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
            response.setContentType("text/html;charset=UTF-8");
            
    //  Process p=Runtime.getRuntime().exec("cmd /c start http://localhost:8011/send/sms/0712256703/Do20%Not20%Drink%20alcohol,%20coffee,%20colas,%20teas%20or%20eat%20soft%20cheeses"); 
            
   URL url = new URL("http://localhost:8011/send/sms/0712256703/Preparing for a%20baby:::Do%20not%20Drink%20alcohol,%20coffee,%20colas,%20teas%20or%20eat%20soft%20cheeses");
HttpURLConnection connection = (HttpURLConnection)url.openConnection();
connection.setRequestMethod("GET");
connection.connect();


int code = connection.getResponseCode();         
            
       
//          String myloader="LOAD DATA LOCAL INFILE 'P:/mhs.txt' INTO TABLE mother_details";
//          
//          dbConn conn=new dbConn();
//          
//          
//          conn.st.executeQuery(myloader);
       
      
      
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
