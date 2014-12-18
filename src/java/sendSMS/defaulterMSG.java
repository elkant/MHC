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
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maureen
 */
public class defaulterMSG extends HttpServlet {

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
//  String confirmation="";
//  String addPhones="";
//    String msg="";
//    String status="";
//    String sms1="";
    String salutation = "";
    ArrayList messages = new ArrayList();
    ArrayList messages1 = new ArrayList();
    HttpSession session;
    String message = "";
    int counters = 0;
//  String phoneNum ;
    String FName;
//  String NokphoneNum;
    String consent;
    String FinalSMS = "";
    String queries = "";
    int count = 1;
//ArrayList phone = new ArrayList();
    String FinalNum = "";
    int counter = 0;

//  adtelConn smsconn=new adtelConn();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        counters = 0;
        session = request.getSession(true);
        counters = (Integer) session.getAttribute("rowCount");
//         System.out.println("counters-------"+counters);
        dbConnect conn = new dbConnect();

        Date date = new Date();
        String warning = "";
        String sentMsg = "";
        String sentNokMsg = "";
        String nok_message = "";
        String nok = "";
        String salamu = "";
        String ujumbe = "";
        String kaleMsg = "";
        String kaleMsgNOK = "";
        String ujumbe_nok = "";
        String language = "";
        String nokPhone = "";
        String salutationEng = "";
        String kaleSalamu = "";

        ArrayList id = new ArrayList();
        ArrayList defaulter = new ArrayList();
        String phoneNo = "";
        int rowsCount = 0;
        //  System.out.println("|||||||||||||||||||||||||||||||||||||||||REACHED");
//        System.out.println("counters"+counters);
        // gets the status of the mother to be sen to the message whether pregnant 
        //or postnatal and also th esaluation since they are dependent on the language     
        for (int i = 0; i <= counters; i++) {

            if (request.getParameter("id" + i) != null && request.getParameter("id" + i) != "") {
//          rowsCount++;
                id.add(request.getParameter("id" + i));
                // System.out.println(id.get(0));   

            }
        }

        for (int i = 0; i <= counters; i++) {

            if (request.getParameter("defaulter" + i) != null && request.getParameter("defaulter" + i) != "") {
                rowsCount++;
                defaulter.add(request.getParameter("defaulter" + i));


            }
        }
//          System.out.println("aaaaa===="+defaulter.get(0)); 
//          System.out.println("bbbbb===="+rowsCount); 

        String dates = "";
        if (request.getParameter("date") != null && request.getParameter("date") != "") {

            dates = request.getParameter("date");
            //   System.out.println("dates"+dates);
        }



        if (messages != null && messages.size() > 0) {
            messages.clear();
        }
        if (messages1 != null && messages1.size() > 0) {
            messages1.clear();
        }
        if (defaulter.size() > 0) {
            String query = "select cat_id from message_category where category='defaulters'";


            conn.rs = conn.state.executeQuery(query);


            while (conn.rs.next()) {
                conn.rs.getString("cat_id");
                // System.out.println("cat_id"+conn.rs.getString("cat_id"));

                String catQuery = "select * from messages where category_id='" + conn.rs.getString("cat_id") + "'";

                conn.rs2 = conn.state2.executeQuery(catQuery);
                while (conn.rs2.next()) {


                    sentNokMsg = conn.rs2.getString(6);
                    sentMsg = conn.rs2.getString(2);
                    ujumbe = conn.rs2.getString(7);
                    ujumbe_nok = conn.rs2.getString(10);
                    salutationEng = conn.rs2.getString(8);
                    salamu = conn.rs2.getString(9);
                    kaleMsg = conn.rs2.getString("Kale_msg");
                    kaleMsgNOK = conn.rs2.getString("Kale_msgNOK");
                    kaleSalamu = conn.rs2.getString("salutation_Kale");

                }
            }
            System.out.println(sentNokMsg + "___" + sentMsg + "__" + ujumbe + "__" + ujumbe_nok + "____" + salutationEng + "_____" + salamu);
            String mother = "";
            for (int i = 0; i < rowsCount; i++) {
                String f = defaulter.get(i).toString();
                mother = "select * from mother_details  where anc_no='" + f + "'";
                String visitDate = "";
// System.out.println(mother);

                conn.rs3 = conn.state3.executeQuery(mother);
                while (conn.rs3.next()) {
                    phoneNo = conn.rs3.getString("PhoneNo");
                    FName = conn.rs3.getString("FName");
                    nokPhone = conn.rs3.getString("NOKPhoneNo");
                    consent = conn.rs3.getString("nok_consent");
                    nok = conn.rs3.getString("nok");
                    language = conn.rs3.getString("languagePreferred");
                    visitDate = conn.rs3.getString("VisitDate");
//  
//System.out.println("phoneNo"+phoneNo);
//System.out.println("nokPhone"+nokPhone);

                    if (nokPhone == null || "".equals(nokPhone) && phoneNo == null || " ".equals(phoneNo)) {
                        warning = "No Available Contacts for " + FName + "";

                        session.setAttribute("warning", "<font color=\"red\">" + warning + "</font>");

                        // System.out.println("warning--------------"+warning);
                    }

                    if (language != null && !"".equals(language) && language.equalsIgnoreCase("english")) {
                        message = sentMsg;
                        nok_message = sentNokMsg;
                        salutation = salutationEng;

//System.out.println(message+"--"+nok_message+""+salutation);

                    } else if (language != null && !"".equals(language) && language.equalsIgnoreCase("swahili")) {
                        message = ujumbe;
                        nok_message = ujumbe_nok;
                        salutation = salamu;
//System.out.println(message+"--"+nok_message+""+salutation);
                    } else if (language != null && !language.equals("") && language.equalsIgnoreCase("swahili")) {
                        message = kaleMsg;
                        nok_message = kaleMsgNOK;
                        salutation = kaleSalamu;
//System.out.println(message+"--"+nok_message+""+salutation);
                    } else if (language != null && !language.equals("") && language.equalsIgnoreCase("kalenjin")) {
                        message = ujumbe;
                        nok_message = ujumbe_nok;
                        salutation = salamu;
//System.out.println(message+"--"+nok_message+""+salutation);
                    }


                    String p = "";
                    String phones2 = "";
                    String phones = "";

                    if (phoneNo != null && !phoneNo.equals("") && message != null && !message.equals("")) {
//    message=message;
                        if (phoneNo.startsWith("7")) {
                            phones = phoneNo.substring(0, 1);

                            phones2 = phoneNo.substring(1, 9);

                            String k = phones.replace("7", "07");



                            phoneNo = k + phones2;

//System.out.println("lllll  "+phoneNo);
                        }

                    } else if (nokPhone != null && !nokPhone.equals("") && consent != null && !consent.equals("") && consent.equalsIgnoreCase("Both or either")) {
                        String phon = "";
                        String phon1 = "";
//    nok_message= nok_message;
                        if (nokPhone.startsWith("7")) {
                            phon = nokPhone.substring(0, 1);

                            phon1 = nokPhone.substring(1, 10);

                            String a = phon.replace("7", "07");
                            nokPhone = a + phon1;

                        }
                    }

                    if (nokPhone != null && !"".equals(nokPhone) && consent != null && !"".equals(consent) && consent.equalsIgnoreCase("My number only")) {
                        warning = "Available NOK But No Consent";

                        session.setAttribute("warning", "<font color=\"red\">" + warning + "</font>");

                        // System.out.println("warning--------------"+warning);
                    }




//else{
//    out.println("NO RECIPIENT AVAILABLE");
//    FinalNum ="";
//    message="";
//
//}
//System.out.println("Phone No"+phoneNo +" message"+ message);
//System.out.println("Phone No"+nokPhone +" message"+ nok_message);

                    System.out.println("message is" + message);
                    int messageCount = 0;
                    if (phoneNo != null && !"".equals(phoneNo) && message != null && !message.trim().equals("")) {

                        
                        
                        //limit to 160 characters      
//                        if (message.length() > 160) {
//
//                            message = message.substring(0, 160);
//
//                        }

//            System.out.println("sms1++++++"+message);
//            System.out.println("phoneNum++++++"+phoneNo);
//            System.out.println("Name+++++"+FName);
//            System.out.println("salutation++++++"+salutation);
//          
//           System.out.println(dates);
                        FinalSMS = message + "%20" + visitDate + "";

//             messageCount++;
//System.out.println("messageCount+++++++"+messageCount);



// Create the URL for adding the phones number to sms 
                        messages.add("http://localhost:8011/send/sms/" + phoneNo + "/" + salutation.trim().replace(" ", "%20") + "%20" + FName.trim().replace(" ", "%20") + "%20,%20" + FinalSMS.trim().replace(" ", "%20"));
//       System.out.println("messages size nnmnmn"+messages.size());
                        warning = "Message sent successfully";

                        session.setAttribute("warning", "<font color=\"red\">" + warning + "</font>");
                    }//END OF IF FOR PHONE NUMBER 
                    else if (nokPhone != null && !nokPhone.equals("") && nok_message != null && !nok_message.trim().equals("") && consent != null && !consent.equals("") && consent.equalsIgnoreCase("Both or either") && phoneNo == null || phoneNo.equals("")) {

                        //limit to 160 characters      
                        if (nok_message.length() > 160) {

                            nok_message = nok_message.substring(0, 160);

                        }

//            System.out.println("sms1------"+nok_message);
//            System.out.println("phoneNum------"+nokPhone);
//            System.out.println("Name------"+FName);
//            System.out.println("salutation------"+salutation);
                        String FinalSMSs = "";
                        FinalSMSs = nok_message + "%20" + visitDate + "";

//             String  FinalSMS2 =salutation+" "+nok+","+FName+" "+nok_message;
// Create the URL for adding the phones number to sms 
                        messages1.add("http://localhost:8011/send/sms/" + nokPhone + "/" + salutation.trim().replace(" ", "%20") + "%20" + nok.trim().replace(" ", "%20") + "%20,%20" + FName.trim().replace(" ", "%20") + "%20" + FinalSMSs.trim().replace(" ", "%20"));

//            System.out.println("Message Size "+messages1.size());

                        warning = "Message sent successfully";

                        session.setAttribute("warning", "<font color=\"red\">" + warning + "</font>");

                    }//END OF IF FOR NOK PHONE NUMBER 
                }//END OF WHILE LOOP
            }  // END OF IF LOOP FOR CHECKING IF STATUS IS NULL    


            //__________________________SENDING NEXT OF KIN SMS_________________________________________

            for (int a = 0; a < messages.size(); a++) {
                try {

                    URL murl = new URL(messages.get(a).toString());
                    HttpURLConnection connection = (HttpURLConnection) murl.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    int code = connection.getResponseCode();
                } //_____________________________________________________________________________________________________________
                catch (IOException ex) {

                    System.out.println("Error while sending Message to Frontline  " + ex);

                }

            } // END OF FOR LOOP FOR MESSAGES to mum
            for (int a = 0; a < messages1.size(); a++) {
                System.out.println("URL 1 " + messages1.get(a));
                System.out.println("size for message 1 " + messages1.size());


                try {

                    URL murl = new URL(messages1.get(a).toString());
                    HttpURLConnection connection = (HttpURLConnection) murl.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    int code = connection.getResponseCode();
                } //_____________________________________________________________________________________________________________
                catch (IOException ex) {

                    System.out.println("Error while sending Message to Frontline  " + ex);

                }



            } // END OF FOR LOOP FOR MESSAGES to nok

        }


        response.sendRedirect("Diary.jsp");
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
            Logger.getLogger(defaulterMSG.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(defaulterMSG.class.getName()).log(Level.SEVERE, null, ex);
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
