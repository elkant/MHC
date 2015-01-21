/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import FRONTLINE.FL_CONNECTION;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MANUEL
 */
public class bundles extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        response.setContentType("text/html;charset=UTF-8");
      
        for(int a=0;a<50;a++){
        String val1="355290050"+generateRandomNumber(2000000, 4000000);
        
        sendSMS("440",val1);
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
    
    
    
    public boolean sendSMS(String pn, String msg){
    
     try {
            FL_CONNECTION addres= new FL_CONNECTION();
                        
                        URL murl = new URL(""+addres.FrontlineAddress+pn+"/"+msg);
                        HttpURLConnection connection = (HttpURLConnection)murl.openConnection();
                        connection.setRequestMethod("GET");
                        connection.connect();

                        System.out.println(murl);
                        
                        int code = connection.getResponseCode();
                    }
                    //_____________________________________________________________________________________________________________
                    catch (IOException ex) {
                        
                       System.out.println("Error while sending Message to Frontline");
                       return false;
                    } 
    
    return true;
    }
    
    public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    } 
}
