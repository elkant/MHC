/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpSession;
import sender.dbConn;

/**
 *
 * @author Geofrey Nyabuto
 */
public class back_up_tables extends HttpServlet {

    String dbname, dbuser, dbpassword;
    String localhost = "", localhostsplit[];
    boolean executed = false;
    String dbpath, dbpathD, dbpathE, dbpathF, dbpathG, dbpathM;
    String found_folder, full_date, path, computername, senderofmail;
    HttpSession session;
    String[] myalphabet = {"B", "C", "D",  "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    String filname = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();


        filname = "";

        dbConn conn = new dbConn();
        Date dat = new Date();
        String date = dat.toString().replace(" ", "_");

        String lasttimestampid = "1";
        String lasttimestamp = "2014-03-12 11:25:20";




        dbname = "mhc";
        dbuser = "root";
        dbpassword = "";
        String nextpage = "";
        found_folder = "";



//MAKE A DIRECTORY TO STORE THE BACK_UP FILE.
//        GET CURRENT DATE:
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);

        full_date = "Created_On_" + year + "_" + month + "_" + day + "_" + hour + "_" + min + "_" + sec;
        String full_dates = day + " / " + month + " / " + year + "  :and the exact time is  " + hour + ":" + min + ":" + sec;
        URL location = dbConn.class.getProtectionDomain().getCodeSource().getLocation();
        if (session.getAttribute("username") != null) {

            filname = session.getAttribute("username").toString() + "_";

            senderofmail = " which has been send by user :: " + session.getAttribute("username").toString() + " ";

        } else {
            senderofmail = "";
        }

        filname += date + "_";


        computername = InetAddress.getLocalHost().getHostName();
        if (1 == 1) {
//         
            String executeCmd = "";


            if (conn.dbsetup[3] != null) {
                dbpassword = conn.dbsetup[3];


            }



            if (conn.dbsetup[2] != null) {

                dbuser = conn.dbsetup[2];


            }



            if (conn.dbsetup[1] != null) {

                dbname = conn.dbsetup[1];

            }



            if (conn.dbsetup[0] != null) {

                localhost = conn.dbsetup[0];
                localhostsplit = localhost.split(":");

            }


            System.out.println("PASSWORD IS :" + dbpassword);
            System.out.println("USER IS :" + dbuser);
            System.out.println("DBNAME IS :" + dbname);


            for (int i = 0; i < myalphabet.length; i++) {
                try {
                    //                try {
                                        System.out.println("at position  :  " + myalphabet[i]);
                                        String current_drive = myalphabet[i];
                                        File f = new File(current_drive + ":\\wamp\\mysql\\bin\\");
                                        File f1 = new File(current_drive + ":\\wamp\\bin\\mysql\\mysql5.6.12\\bin");
                                        File f2 = new File(current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.5\\bin");
                                        File f3 = new File(current_drive + ":\\APHIAPLUS");

                                        //     CREATE A DIRECTORY AND THE FILE TO HOLD DATA
                                        if (f3.exists() && f3.isDirectory()) {
                                            path = current_drive + ":\\APHIAPLUS\\MNCH_SYSTEM\\DATA\\BACKUP2";
                                            new File(path).mkdirs();
                                            dbpath = path + "\\" + full_date + "_MNCH.sql";
                                        }

                                        //select the last timestamp which a backup was picked from.....



                                        conn.rs_6 = conn.st_6.executeQuery("select MAX(timestampid) from timestamp");
                                        if (conn.rs_6.next()) {


                                            conn.rs_5 = conn.st_5.executeQuery("select timestamp from timestamp where timestampid='" + conn.rs_6.getString(1) + "'");

                                            if (conn.rs_5.next()) {
                                                lasttimestamp = conn.rs_5.getString("timestamp");

                                                System.out.println(conn.rs_5.getString("timestamp"));

                                            }


                                            System.out.println("Timestamp id::" + conn.rs_6.getString(1));


                                            lasttimestampid = conn.rs_6.getString(1);

                                        }

                                        //conn.st_6.close();


                    // CHECK WHICH PROGRAM IS INSTALLED TO ENSURE THAT DATA IS BACKED UP SUCCESSFULLY.             

                                        if (f.exists() && f.isDirectory()) {
                           executeCmd = current_drive + ":\\wamp\\mysql\\bin\\mysqldump  --host=" + localhostsplit[0] + " --port=" + localhostsplit[1] + " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " aetoak altoan anc_visits atoh cervical_screening delivery deworming hiv_exposure ironfolate itop mat_actoai mat_ajtoan mat_atoh mat_htol mat_mtou mat_vtoab maternal_details mother_details post_natal_baby post_natal_examination postnat_aatoae postnat_aftoai postnat_atof postnat_gtom postnat_ntot postnat_utoz present_pregnancy preventive_services qtow sergical_history vitamin_a_capsule xtoad  --where=timestamp>='" + lasttimestamp + "' -r " + dbpath + "";
                                        //executeCmd = "C:\\wamp3\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump --no-create-info --skip-add-drop-table --host=localhost --user="+dbuser+" --password="+dbpassword+" "+dbname+" audit enrollment childage clientmember clientoccupation clientoparea dummy medical_form riskassessmentdetails riskassessmentmain riskreductionmain riskreductiondetails user taskauditor --where=timestamp>='"+startdate+"' -r "+dbpath+"";

                                            
                                   // executeCmd = current_drive + ":\\wamp\\mysql\\bin\\mysqldump  --host=" + localhostsplit[0] + " --port=" + localhostsplit[1] + " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " -r " + dbpath + "";
                                         
                                            
                                            found_folder = "it is old wamp";
                                        }
                                        if (f1.exists() && f1.isDirectory()) {

executeCmd = current_drive + ":\\wamp\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump  --host=" + localhostsplit[0] + " --port=" + localhostsplit[1] + " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " aetoak altoan anc_visits atoh cervical_screening delivery deworming hiv_exposure ironfolate itop mat_actoai mat_ajtoan mat_atoh mat_htol mat_mtou mat_vtoab maternal_details mother_details post_natal_baby post_natal_examination postnat_aatoae postnat_aftoai postnat_atof postnat_gtom postnat_ntot postnat_utoz present_pregnancy preventive_services qtow sergical_history vitamin_a_capsule xtoad  --where=timestamp>='" + lasttimestamp + "' -r " + dbpath + "";
                           
                                            
                                    //executeCmd = current_drive + ":\\wamp\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump  --host=" + localhostsplit[0] + " --port=" + localhostsplit[1] + " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " -r " + dbpath + "";
                                            found_folder = "it is new wamp";
                                        }
                                        if (f2.exists() && f2.isDirectory()) {
                                   
  executeCmd = current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump --host=" + localhostsplit[0] + " --port=" + localhostsplit[1] + " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " aetoak altoan anc_visits atoh cervical_screening delivery deworming hiv_exposure ironfolate itop mat_actoai mat_ajtoan mat_atoh mat_htol mat_mtou mat_vtoab maternal_details mother_details post_natal_baby post_natal_examination postnat_aatoae postnat_aftoai postnat_atof postnat_gtom postnat_ntot postnat_utoz present_pregnancy preventive_services qtow sergical_history vitamin_a_capsule xtoad  --where=timestamp>='" + lasttimestamp + "' -r " + dbpath + "";

                                            // executeCmd = current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump  --host=" + localhostsplit[0] + " --port=" + localhostsplit[1] + " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " -r " + dbpath + "";
                                           
                                            found_folder = "it is workbench";
                                        }
                    //                } catch (SQLException ex) {
                    //                    //session.setAttribute("datasend", "<font color=\"red\">an SQL Error occured while sending data</font>");
                    //
                    //                    Logger.getLogger(Back_up_data.class.getName()).log(Level.SEVERE, null, ex);
                    //                }
                } catch (SQLException ex) {
                    Logger.getLogger(back_up_tables.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }




            System.out.println(found_folder);
            System.out.println(executeCmd);
            Process runtimeProcess;
            try {
                System.out.println("trying to back up the data.");
                runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int processComplete = runtimeProcess.waitFor();
                System.out.println("at 1 is " + processComplete);
                if (processComplete == 0) {
//                SEND TO THE MAIL BACKED UP DATA.
                    System.out.println("Backup created successfully");



                    SendMail me = new SendMail();


                    if (isInternetReachable()) {  //if there is internet connection


                        //============at this point, if the data is send, then do a new timestamp into the database
                        if (me.Sendattachment(full_dates, dbpath, computername, senderofmail, filname) == true) {


//                            //do an insert
                            conn.st.executeUpdate("update timestamp set datasend='yes' where timestampid='" + lasttimestampid + "'");
//


                            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                            String mdate;

                            Date mydate = new Date();
                            mdate = formatter.format(mydate);


                            String daytime = "" + year + "-" + month + "-" + day;
                            // a new timestamp that will be called next time a backup is being created.

                            conn.st.executeUpdate("insert into timestamp (timestamp,datasend) values('" + mdate + "','No')");


                            session.setAttribute("datasend", "<font color=\"green\">Backup Created  successfully</font>");
                        }//end of if..
                        else {

                             session.setAttribute("datasend", "<font color=\"yellow\">Backup Created but not send via mail</font>");

                            System.out.println("Data not send via mail");

                        }

                    }//if internet connection is available,
                    else {
session.setAttribute("datasend", "<font color=\"red\">Backup Created but NOT send via email.</font>");
                    }


                     //session.setAttribute("datasend", "<font color=\"green\">Backup has been created Successfully</font>");

                }//end of if a bacup has been created
                else {
                    System.out.println("Could not create the backup");
                    session.setAttribute("datasend", "Backup Could <font color=\"red\">NOT</font> be created");
                }
            } catch (Exception ex) {

                System.out.println(ex);
            }


        }


     

     

         response.sendRedirect("backupdata.jsp");

    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static boolean isInternetReachable() {
        try {
            //make a URL to a known source
            URL url = new URL("http://www.google.com");

            //open a connection to that source
            URLConnection urlConnect = url.openConnection();

            //trying to retrieve data from the source. If there
            //is no connection, this line will fail
            urlConnect.getInputStream();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            System.out.println("Unknown host");
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println(":Error in internet connection");
            return false;
        }
        return true;
    }
}
