/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCREDENTIALSFILE;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import sender.dbConn;

/**
 *
 * @author SIXTYFOURBIT
 */
public class db_backup implements Job{
    
    
      String dbname, dbuser, dbpassword;
    String localhost = "", localhostsplit[];
    boolean executed = false;
    String dbpath, dbpathD, dbpathE, dbpathF, dbpathG, dbpathM;
    String found_folder, full_date, path, computername, senderofmail;
    
    String[] myalphabet = {"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    String filname = "";
    

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            filname = "MNCH_AUTOBACKUP";

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
          
                             
                             
                             if (f.exists() && f.isDirectory()) {
                      //executeCmd = current_drive + ":\\wamp\\mysql\\bin\\mysqldump  --host=" + localhostsplit[0] + " --port=" + localhostsplit[1] + " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " facilitator_details transfer_facilitator forms groups member_details new_topic register_attendance session topics --where=timestamp>='" + lasttimestamp + "' -r " + dbpath + "";
                    //executeCmd = "C:\\wamp3\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump --no-create-info --skip-add-drop-table --host=localhost --user="+dbuser+" --password="+dbpassword+" "+dbname+" audit enrollment childage clientmember clientoccupation clientoparea dummy medical_form riskassessmentdetails riskassessmentmain riskreductionmain riskreductiondetails user taskauditor --where=timestamp>='"+startdate+"' -r "+dbpath+"";

                        
                executeCmd = current_drive + ":\\wamp\\mysql\\bin\\mysqldump  --host=" + localhostsplit[0] + " --port=" + localhostsplit[1] + " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " -r " + dbpath + "";
                     
                        
                        found_folder = "it is old wamp";
                    }
                    if (f1.exists() && f1.isDirectory()) {


                        
                executeCmd = current_drive + ":\\wamp\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump  --host=" + localhostsplit[0] + " --port=" + localhostsplit[1] + " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " -r " + dbpath + "";
                        found_folder = "it is new wamp";
                    }
                    if (f2.exists() && f2.isDirectory()) {
                executeCmd = current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump  --host=" + localhostsplit[0] + " --port=" + localhostsplit[1] + " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " -r " + dbpath + "";
                       
                        found_folder = "it is workbench";
                    }
                
                     }
                    
                     System.out.println(found_folder);
            System.out.println(executeCmd);
            Process runtimeProcess;
           
                System.out.println("trying to back up the data.");
                runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int processComplete = runtimeProcess.waitFor();
                System.out.println("at 1 is " + processComplete);
                if (processComplete == 0) {
//                SEND TO THE MAIL BACKED UP DATA.
                    System.out.println("Backup created successfully");



                 


                    if (1==1) {  //if there is internet connection


                        //============at this point, if the data is send, then do a new timestamp into the database
                        if (1==1) {


//                            //do an insert
//                            conn.st.executeUpdate("update timestamp set datasend='yes' where timestampid='" + lasttimestampid + "'");
//


                            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                            String mdate;

                            Date mydate = new Date();
                            mdate = formatter.format(mydate);


                            String daytime = "" + year + "-" + month + "-" + day;
                            // a new timestamp that will be called next time a backup is being created.

                           // conn.st.executeUpdate("insert into timestamp (timestamp,datasend) values('" + mdate + "','No')");


                        }//end of if..
                        else {

                            
                            System.out.println("Data not send via mail");

                        }

                    }//if internet connection is available,
                    else {

                        
                        System.out.println("BACKUP AUTOCREATED");
                        
                    }


                     //session.setAttribute("datasend", "<font color=\"green\">Backup has been created Successfully</font>");

                }//end of if a bacup has been created
                else {
                    System.out.println("Could not create the backup");
                    
                }
                    
                             
                             
                             
             
         }
             
         }
        catch (InterruptedException ex) {
            Logger.getLogger(db_backup.class.getName()).log(Level.SEVERE, null, ex);
        }        catch (IOException ex) {
            Logger.getLogger(db_backup.class.getName()).log(Level.SEVERE, null, ex);
        }        } 
        
        

}