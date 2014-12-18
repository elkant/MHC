/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sendSMS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
 * @author Maureen
 */
public class ReceiveSMS extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String phonenum ="";
    int CHWID =0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // to recieve sms and number from sender from fronltine sms
        String sender_number = request.getParameter("sender");
        String message = request.getParameter("message");
//           String sender_number="+2547193939";
//           String message="dvbvhvghg confirmed.You have recieved ksh1500.00 from";
       dbConnect conn = new dbConnect();

        System.out.println(sender_number);
        System.out.println(message);
        String msgs = message.replace(" ", "%20");
        String no = sender_number.replace("+254", "0");
       
      
String query = "select ChwID from mother_details where PhoneNo='"+no+"' OR PhoneNo1='"+no+"'";
				System.out.println("query " + query);
				
				conn.rs = conn.state.executeQuery(query);


				while(conn.rs.next())
				{
                                     CHWID = conn.rs.getInt(1);
                                     System.out.println("CHWID"+CHWID);
                                }

  String query1 = "select * from chw where chw_id='"+CHWID+"'";
				System.out.println("query " + query1);
				
				conn.rs1 = conn.state1.executeQuery(query1);


				while(conn.rs1.next())
				{
                                     phonenum = conn.rs1.getString(5);
                                     System.out.println("phonenum"+phonenum);
                                     String numbers= phonenum.toString().trim();
try{ 
      //sets the url that will be sent to frontline sms
       //String url = "http://localhost:8011/send/sms/0712894282/hshadsssd";
       String url = "http://localhost:8011/send/sms/"+numbers+"/Message%20From_"+no+","+msgs;
 
	URL obj = new URL(url);
	HttpURLConnection conne = (HttpURLConnection) obj.openConnection();
	conne.setReadTimeout(5000);
	conne.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
	conne.addRequestProperty("User-Agent", "Mozilla");
	conne.addRequestProperty("Referer", "google.com");
 
	System.out.println("Request URL ... " + obj);
 
	boolean redirect = false;
 
	// normally, 3xx is redirect
	int statuss = conne.getResponseCode();
	if (statuss != HttpURLConnection.HTTP_OK) {
		if (statuss == HttpURLConnection.HTTP_MOVED_TEMP
			|| statuss == HttpURLConnection.HTTP_MOVED_PERM
				|| statuss == HttpURLConnection.HTTP_SEE_OTHER)
		redirect = true;
	}
 
	System.out.println("Response Code ... " + statuss);
 
	if (redirect) {
 
		// get redirect url from "location" header field
		String newUrl = conne.getHeaderField("Location");
 
		// get the cookie if need, for login
		String cookies = conne.getHeaderField("Set-Cookie");
 
		// open the new connnection again
		conne = (HttpURLConnection) new URL(newUrl).openConnection();
		conne.setRequestProperty("Cookie", cookies);
		conne.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
		conne.addRequestProperty("User-Agent", "Mozilla");
		conne.addRequestProperty("Referer", "google.com");
 
		System.out.println("Redirect to URL : " + newUrl);
 
	}
 
	BufferedReader in = new BufferedReader(
                              new InputStreamReader(conne.getInputStream()));
	String inputLine;
	StringBuffer html = new StringBuffer();
 
	while ((inputLine = in.readLine()) != null) {
		html.append(inputLine);
	}
	in.close();
 
	System.out.println("URL Content... \n" + html.toString());
	System.out.println("Done");
 
    } catch (Exception e) {
	e.printStackTrace();
    }
                                
                                
                                
                                
                                
                                }

//
//        String query = "INSERT INTO recievedSMS(SenderNo,message)"
//                + " VALUES ('" + sender_number + "','" + message + "')";
//            System.out.println(query);
//        try {
//            conn.state.executeUpdate(query);
//            System.out.println("Inserted into db");
//          
//            
//            out.println("SUCCESSS !!!!!!!");
//        } catch (Exception ex) {
//
//            System.out.println(ex.toString());
//            out.println("FAILURE !!!!!!!");
//        }
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
        } catch (SQLException ex) {
            Logger.getLogger(ReceiveSMS.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(ReceiveSMS.class.getName()).log(Level.SEVERE, null, ex);
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
