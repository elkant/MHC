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
public class sendMsg extends HttpServlet {

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
    String addPhones = "";
    String msg = "";
    String status = "";
    HttpSession session;
    String message = "";
    int counters = 0;
    String confirmation = "";
    String warnings = "";
    String sms1 = "";
    String salutation = "";
    String phoneNum;
    String FName;
    String NokphoneNum;
    String consent;
    String FinalSMS = "";
    String queries = "";
    int count = 1;
    ArrayList phone = new ArrayList();
    String FinalNum = "";
    int counter = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        String startdate = "";
        String enddate = "";

        if (request.getParameter("status") != null && !"".equals(request.getParameter("status"))) {
            status = request.getParameter("status");
        }


        if (request.getParameter("startdate") != null && !request.getParameter("startdate").equals("")) {

            startdate = request.getParameter("startdate");

        }

        if (request.getParameter("enddate") != null && !request.getParameter("enddate").equals("")) {

            enddate = request.getParameter("enddate");

        }

        String query = "";

        if (enddate.trim().equals("") && startdate.trim().equals("")) {

            query = "select FName,PhoneNo,NOKPhoneNo,nok,languagePreferred,nok_consent,anc_no from mother_details where status_id='" + status + "' and facilityname!='Timboroa'  and facilityname!='Torongo' and facilityname!='Simotwet'";



        } else if (!enddate.trim().equals("") && !startdate.trim().equals("")) {

            query = "select FName,PhoneNo,NOKPhoneNo,nok,languagePreferred,nok_consent,anc_no from mother_details where status_id='" + status + "' and facilityname!='Timboroa'  and facilityname!='Torongo' and facilityname!='Simotwet' and delivery_date between date ('" + startdate + "') and  date ('" + enddate + "')";


        }


        ArrayList messages = new ArrayList();
        ArrayList messages1 = new ArrayList();
        counters = 0;
        session = request.getSession(true);
//        if(session.getAttribute("count").toString()!=null){
//        counters = (Integer)session.getAttribute("count");
//            }

        Date date = new Date();

        String sentMsg = "";
        String sentNokMsg = "";
        String nok_message = "";
        String nok = "";
        String salamu = "";
        String ujumbe = "";
        String ujumbe_nok = "";
        String languagePreferred = "";
        String salutationEng = "";
        String warning = "";
        String nokintro = "";
//        System.out.println("counters"+counters);


        if (request.getParameter("counter") != null && !"".equals(request.getParameter("counter"))) {
            counters = Integer.parseInt(request.getParameter("counter"));
        }

        System.out.println("counters" + counters);
        for (int i = 0; i <= counters; i++) {


            //========if a checkbox has got a message , then receive all the messages of that row....
            if (request.getParameter("msg_" + i) != null) {

                if (request.getParameter("salutation_" + i) != null && !"".equals(request.getParameter("salutation_" + i))) {
                    salutationEng = request.getParameter("salutation_" + i).trim();
                }

                if (request.getParameter("salamu_" + i) != null && !"".equals(request.getParameter("salamu_" + i))) {
                    salamu = request.getParameter("salamu_" + i).trim();
                }


                if (request.getParameter("msg_" + i) != null && !"".equals(request.getParameter("msg_" + i))) {
                    msg = request.getParameter("msg_" + i).trim();
//                sentMsg  = msg.replace("%20"," ");
                    System.out.println(msg);
                }

                System.out.println(status);
                System.out.println(msg);
                System.out.println(salamu);
                System.out.println(salutationEng);

                String sentNOKMsg = "";


                if (request.getParameter("nok_message_" + i) != null && !"".equals(request.getParameter("nok_message_" + i))) {
                    sentNokMsg = request.getParameter("nok_message_" + i).trim();
                    sentNOKMsg = sentNokMsg.replace(" ", "%20");
                }

                String Ujumbe = "";


                if (request.getParameter("ujumbe_" + i) != null && !"".equals(request.getParameter("ujumbe_" + i))) {
                    ujumbe = request.getParameter("ujumbe_" + i).trim();
                    Ujumbe = ujumbe.replace(" ", "%20").trim();
                    // System.out.println("ujumbe___"+ujumbe);
                }

                String UjumbeNOK = "";


                if (request.getParameter("ujumbe_nok_" + i) != null && !"".equals(request.getParameter("ujumbe_nok_" + i))) {
                    ujumbe_nok = request.getParameter("ujumbe_nok_" + i).trim();
                    UjumbeNOK = ujumbe_nok.replace(" ", "%20").trim();
                    //System.out.println("ujumbe_nok___"+ujumbe_nok);
                }


                String kaleMsg = "";



                if (request.getParameter("kaleMsg" + i) != null && !request.getParameter("kaleMsg" + i).equals("")) {
                    kaleMsg = request.getParameter("kaleMsg" + i).replace(" ", "%20").trim();
                    // UjumbeNOK = ujumbe_nok.replace(" ", "%20").trim();
                    System.out.println("kaleMsg" + kaleMsg);
                }

                String KaleMsgNOK = "";


                if (request.getParameter("kaleMsgNOK" + i) != null && !request.getParameter("kaleMsgNOK" + i).equals("")) {
                    KaleMsgNOK = request.getParameter("kaleMsgNOK" + i).trim().replace(" ", "%20");

                }

                String KaleSalutation = "";


                if (request.getParameter("salutation_Kale" + i) != null && !request.getParameter("salutation_Kale" + i).equals("")) {
                    KaleSalutation = request.getParameter("salutation_Kale" + i).trim().replace(" ", "%20");


                }
//       System.out.println("msg"+msg);
//                      message  = msg.replace(" ","%20");
//                      
//                      String[] sms = message.split("#");
//                      System.out.println(sms[0]);
//                      System.out.println(sms[1]);
//                              
//      System.out.println(message);
//       System.out.println("status"+status);

//      String message  = msg.replace(" ","%20");
//      System.out.println(message);

                if (messages != null && messages.size() > 0) {
                    messages.clear();
                }
                dbConnect conn = new dbConnect();
                if (status != null && !"".equals(status)) {

                    conn.rs = conn.state.executeQuery(query);

                    System.out.println(query);
                    while (conn.rs.next()) {
                        FName = "";
                        phoneNum = conn.rs.getString("PhoneNo");
                        FName = conn.rs.getString("FName");
                        NokphoneNum = conn.rs.getString("NOKPhoneNo");
                        consent = conn.rs.getString("nok_consent");
                        String ANCNO = conn.rs.getString("anc_no");
                        ANCNO = ANCNO.replace("/", "\\");
                        nok = conn.rs.getString("nok");
                        languagePreferred = conn.rs.getString("languagePreferred");
// counter++;

                        if (languagePreferred != null && !"".equals(languagePreferred) && languagePreferred.equalsIgnoreCase("english")) {
                            message = msg;

                            nokintro = "Inform " + FName + " ";
                            if (FName.equals("")) {
                                nokintro = "please Pass this message to the Mother whose ANC Number is " + ANCNO + " ";
                            }
                            nok_message = sentNOKMsg;
                            salutation = salutationEng;
                            
                            //if the salutation is dear but no FName of the mother, then replace dear with HI
                            if(salutation.equalsIgnoreCase("Dear") && FName.equals("")){
                            salutation="Hi";
                            }
                            
                            
//System.out.println("eng------"+message);
//System.out.println("eng------"+nok_message);
//System.out.println("eng------"+salutation);
                        } else if (languagePreferred != null && !"".equals(languagePreferred) && languagePreferred.equalsIgnoreCase("Swahili")) {
                            message = Ujumbe;
                            nokintro = "Fahamisha  " + FName + " hivi:";


                            if (FName.equals("")) {
                                nokintro = "Tafadhali Pitisha ujumbe huu kwa mama mwenye  nambari ya kliniki " + ANCNO + " ";
                            }
                            nok_message = UjumbeNOK;
                            salutation = salamu;

//System.out.println("swa-----"+nok_message);
//System.out.println("swa------"+message);
//System.out.println("swa-----"+salutation);
                        } else if (languagePreferred != null && !"".equals(languagePreferred) && languagePreferred.equalsIgnoreCase("kalenjin")) {
                            message = kaleMsg;
                            nok_message = KaleMsgNOK;
                            nokintro = "Fahamisha " + FName + " hivi:";

                            if (FName.equals("")) {
                                nokintro = "Pitisha ujumbe huu kwa mama mwenye  nambari ya kliniki " + ANCNO + " ";
                            }
                            salutation = KaleSalutation;

                            System.out.println("kale-----" + nok_message);
                            System.out.println("kale------" + message);
                            System.out.println("kale-----" + salutation);
                        }


                        String p = "";
                        String phones2 = "";
                        String phones = "";
                        System.out.println(phoneNum + "___" + FName + "__" + NokphoneNum + "__" + consent + "____" + nok + "______" + message + "____" + nok_message);
                        if (phoneNum != null && !phoneNum.equals("") && message != null && !message.equals("")) {
                            message = message;
                            System.out.println("____________________" + message);
                            System.out.println("____________________" + phoneNum);
                            if (phoneNum.startsWith("7")) {
                                phones = phoneNum.substring(0, 1);
                                System.out.println("qqqqq" + phones);
                                phones2 = phoneNum.substring(1, 9);
                                System.out.println("wwwwww" + phones2);
                                String k = phones.replace("7", "07");
                                System.out.println("kkkkkk     " + k);


                                phoneNum = k + phones2;

                                System.out.println("lllll  " + FinalNum);
                            }

                        } else if (NokphoneNum != null && !NokphoneNum.equals("") && consent.equalsIgnoreCase("Both or either")) {
                            String phon = "";
                            String phon1 = "";
                            nok_message = nok_message;
                            if (NokphoneNum.startsWith("7")) {
                                phon = NokphoneNum.substring(0, 1);
// System.out.println("qqqqq"+phon);
                                phon1 = NokphoneNum.substring(1, 9);
// System.out.println("wwwwww"+phon1);
                                String a = phon.replace("7", "07");
// System.out.println("aaaaaa     "+a);


                                NokphoneNum = a + phon1;

//System.out.println("lllll  "+FinalNum);

                            }

                        }

//else{
//    out.println("NO RECIPIENT AVAILABLE");
//    FinalNum ="";
//    message="";
//
//}
//System.out.println("Phone No"+phoneNum +" message"+ message);
//System.out.println("Phone No"+NokphoneNum +" message"+ nok_message);
//
//System.out.println("message"+message);  



                        if (NokphoneNum != null && !NokphoneNum.equals("") && consent != null && !consent.equals("") && consent.equalsIgnoreCase("My number only") && phoneNum == null && phoneNum.equals("")) {
                            warnings = "Available NOK But No Consent for Some Mothers";

                            session.setAttribute("warnings", "<font color=\"red\">" + warnings + "</font>");

                            System.out.println("warnings--------------" + warnings);
                        }




                        if (phoneNum != null && !"0".equals(phoneNum) && !"".equals(phoneNum) && message != null && !message.trim().equals("")) {

                            //limit to 160 characters      
//            if (message.length() > 160) {
//                
//                 message =message.substring(0, 160);
//
//                    }

                            System.out.println("sms1------" + message);
                            System.out.println("phoneNum------" + phoneNum);
                            System.out.println("Name------" + FName);
                            System.out.println("salutation------" + salutation);

                            FinalSMS = salutation + " " + FName + "," + message;

                            System.out.println("FinalSMS------" + FinalSMS);




// Create the URL for adding the phones number to sms 
                            if (phoneNum.length() >= 10) {
                                messages.add("http://localhost:8011/send/sms/" + phoneNum.trim() + "/" + salutation.trim().replace(" ", "%20") + "%20" + FName.trim().replace(" ", "%20") + "%20,%20" + message.trim().replace(" ", "%20"));
                            }
                            for (int j = 0; j < messages.size(); j++) {
                                System.out.println("Message      " + messages.get(j).toString());
                            }



                        }//end of if for phone number 
                        if (NokphoneNum != null && !"0".equals(NokphoneNum) && !"".equals(NokphoneNum) && nok_message != null && !nok_message.trim().equals("") && consent != null && consent.equalsIgnoreCase("Both or either")) {

                            //limit to 160 characters      
//            if (nok_message.length() > 160) {
//                
//                 nok_message =nok_message.substring(0, 160);
//
//                    }

                            System.out.println("sms1------" + nok_message);
                            System.out.println("NOkphoneNum------" + NokphoneNum);
                            System.out.println("Name------" + FName);
                            System.out.println("salutation------" + salutation);


//
//                        if (messages1 != null && messages1.size() > 0) {
//                            messages1.clear();
//                        }


// Create the URL for adding the phones number to sms 
                            //messages1.add("http://localhost:8011/send/sms/"+NokphoneNum+"/"+salutation.trim().replace(" ","%20")+"%20"+nok.trim().replace(" ", "%20") +"%20,%20"+nok_message.trim().replace(" ", "%20"));
                            messages1.add("http://localhost:8011/send/sms/" + NokphoneNum + "/" + salutation.trim().replace(" ", "%20") + ",%20" + nokintro.trim().replace(" ", "%20") + "%20\"%20" + nok_message.trim().replace(" ", "%20") + "\"");


                        } //end of the else for nok phone 
                    } // end of while
                }// end of if 
                for (int a = 0; a < messages.size(); a++) {
//     System.out.println("URL 1 "+messages.get(a)); 
                    //  System.out.println("size "+messages.size()); 
                    try {

                        URL obj1 = new URL("" + messages.get(a));

                        HttpURLConnection connection = (HttpURLConnection) obj1.openConnection();
                        //connection.setReadTimeout(5000);

                        System.out.println("Request URL ... " + obj1);

                        boolean redirect = false;

                        // normally, 3xx is redirect
                        int statuses = connection.getResponseCode();
                        if (statuses != HttpURLConnection.HTTP_OK) {
                            if (statuses == HttpURLConnection.HTTP_MOVED_TEMP
                                    || statuses == HttpURLConnection.HTTP_MOVED_PERM
                                    || statuses == HttpURLConnection.HTTP_SEE_OTHER) {
                                redirect = true;
                            }
                        }

                        System.out.println("Response Code ... " + statuses);

                        if (redirect) {

                            // get redirect url from "location" header field
                            String newUrl = connection.getHeaderField("Location");

                            // get the cookie if need, for login
                            String cookies = connection.getHeaderField("Set-Cookie");

                            // open the new connnection again
                            connection = (HttpURLConnection) new URL(newUrl).openConnection();

                            System.out.println("Redirect to URL : " + newUrl);

                        }

                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(connection.getInputStream()));
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
                for (int a = 0; a < messages1.size(); a++) {
                    System.out.println("URL 1 " + messages1.get(a));
                    System.out.println("size " + messages1.size());
                    try {

                        URL obj = new URL("" + messages1.get(a));

                        HttpURLConnection conne = (HttpURLConnection) obj.openConnection();
                        conne.setReadTimeout(5000);

                        System.out.println("Request URL ... " + obj);

                        boolean redirect = false;

                        // normally, 3xx is redirect
                        int statuss = conne.getResponseCode();
                        if (statuss != HttpURLConnection.HTTP_OK) {
                            if (statuss == HttpURLConnection.HTTP_MOVED_TEMP
                                    || statuss == HttpURLConnection.HTTP_MOVED_PERM
                                    || statuss == HttpURLConnection.HTTP_SEE_OTHER) {
                                redirect = true;
                            }
                        }

                        System.out.println("Response Code ... " + statuss);

                        if (redirect) {

                            // get redirect url from "location" header field
                            String newUrl = conne.getHeaderField("Location");

                            // get the cookie if need, for login
                            String cookies = conne.getHeaderField("Set-Cookie");

                            // open the new connnection again
                            conne = (HttpURLConnection) new URL(newUrl).openConnection();

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
            } else {

                System.out.println("Message not selected");
            }
        }//end of ofr loop



        response.sendRedirect("sendGeneralSms.jsp");
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
            Logger.getLogger(sendSMS.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(sendSMS.class.getName()).log(Level.SEVERE, null, ex);
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
