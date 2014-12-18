/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ADTELCONNECTION;

import DBCREDENTIALSFILE.db_backup;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import sender.dbConn;

/**
 *
 * @author SIXTYFOURBIT
 */
public class ALLSMSRECEIVER extends HttpServlet {

    int year, month, date, hour, min, sec, schedulingmin;
    boolean shutdown;
    String nextpage;
    int jobprefix = 0;
    int edd_anc_repeatcount = 5;
    private Date dit;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            /**  
            This servlet receives all the messages from sms table in adtel connection which receives the incomming messages
            
             *A check is done every 15 seconds if there there are newly received values
             * 
             * After an sms has been modified, the table should be changed such that 
             * the specific sms  processed will have the status changed to clicked
             * 
             * 
             */
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            dbConn conn = new dbConn();
            nextpage = "index.jsp";

            System.out.println("******************************* received value: " + request.getParameter("shutdown"));





//==============================================CURRENT SMS LISTENER=====================================        

            //id 3 is the sms scheduler settings


            conn.anc_sch_rs = conn.anc_scheduling_st.executeQuery("Select second from scheduler_settings where schedule_id='3'");

            //repeat schedule is 25 seconds
            int recheckinginterval = 25;

            if (conn.anc_sch_rs.next()) {

                recheckinginterval = conn.anc_sch_rs.getInt(1);

            }


            System.out.println("ALL SMS SCHEDULERS HAS STARTED");

            // =====DEFINE ALL THE JOBS HERE==========          

            JobDetail realtimesmsjob = JobBuilder.newJob(MOTHER_CHW_EXECUTER.class).withIdentity("REALTIMESMSSCHEDULER" + jobprefix).build();

            JobDetail ancvisitjob = JobBuilder.newJob(ANCVISITSEXECUTER.class).withIdentity("ANCSCHEDULER" + jobprefix).build();

            JobDetail eddjob = JobBuilder.newJob(EDDEXECUTER.class).withIdentity("EDDSCHEDULER" + jobprefix).build();



            JobDetail backupjob = JobBuilder.newJob(db_backup.class).withIdentity("DBBACKUPJOB" + jobprefix).build();


            //incase no net, this two jobs are called


            JobDetail rescheduledancvisit = JobBuilder.newJob(ANCVISITSEXECUTER.class).withIdentity("ANC_rescheduled").build();




            JobDetail reschedulededdjob = JobBuilder.newJob(EDDEXECUTER.class).withIdentity("EDD_rescheduled").build();






            //schedule the job
            SchedulerFactory schFactory = new StdSchedulerFactory();
            Scheduler sch = schFactory.getScheduler();
            sch.start();


            //=========whether to stop scheduler or start===============    

            if (request.getParameter("shutdown") != null) {

                if (request.getParameter("shutdown").equals("true")) {



                    //set shutdown to false after shutting down
                    nextpage = "";

                    shutdown = true;

                    realtimesmsjob = null;
                    if (!sch.getCurrentlyExecutingJobs().isEmpty()) {
                    }

                    sch.shutdown(shutdown);

//after a shutdown, revert the statements to true           

                    sch.shutdown(false);

                    out.println("Restart scheduler");




                } else if (request.getParameter("shutdown") != null && request.getParameter("shutdown").equals("false")) {

                    nextpage = "";
                    shutdown = false;
                    out.println("stop scheduler");

                }
            }

            //=======================end of start or stop scheduler======================           



            //====================REALTIME SMS SCHEDULER=========================================            



            // specify the running period of the job
            Trigger realtimesmstrigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(recheckinginterval).repeatForever()).build();


            if (!sch.isShutdown()) {

                sch.scheduleJob(realtimesmsjob, realtimesmstrigger);

            }


            //================================end of REALTIME SMS============================       





            //============================================ ANC VISITS=====================================================

            //get the set scheduling time of anc visits from the database           

            edd_anc_repeatcount = 23;


            conn.anc_sch_rs = conn.anc_scheduling_st.executeQuery("Select * from scheduler_settings where schedule_id='1'");
            //second minute hour am/pm

            currentdates();
            String anc_check_time = "0 02 8am ";

            String ancretrigertime = "";
            if (conn.anc_sch_rs.next()) {

                anc_check_time = "0 " + conn.anc_sch_rs.getString("minute") + " " + conn.anc_sch_rs.getString("hour") + conn.anc_sch_rs.getString("am_pm");
                ancretrigertime = year + "-" + month + "-" + date + " " + conn.anc_sch_rs.getString("hour") + ":" + conn.anc_sch_rs.getString("minute") + ":" + conn.anc_sch_rs.getString("second");

                dit = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ancretrigertime);

            }

            Trigger ancretrigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(1).withRepeatCount(edd_anc_repeatcount)).startAt(dit).build();

            System.out.println("HOUR" + dit);





            CronTrigger ancTrigger = TriggerBuilder.newTrigger().withIdentity("anctrigger" + jobprefix, "anstriggergroup" + jobprefix).withSchedule(CronScheduleBuilder.cronSchedule(anc_check_time + " * * ?")) // .withSchedule(CronScheduleBuilder.cronSchedule("0 26 8a * * ?"))
                    .build();
           
            
            
            
            
            CronTrigger backuptrigger = TriggerBuilder.newTrigger().withIdentity("backuptrigger" + jobprefix, "dbbackuptriggergroup" + jobprefix).withSchedule(CronScheduleBuilder.cronSchedule(anc_check_time + " * * ?")) // .withSchedule(CronScheduleBuilder.cronSchedule("0 26 8a * * ?"))
                    .build();
            
             if (!sch.isShutdown()) {

                sch.scheduleJob(backupjob, backuptrigger);

            }
            
            
            

//if there is internet, then call the scheduler thats made to execute once in a day..
            if (!sch.isShutdown() && isInternetReachable()) {

                sch.scheduleJob(ancvisitjob, ancTrigger);

            } //if no net, the call a scheduler which refires after every certain duration eg. one hour
            else if (!sch.isShutdown() && !isInternetReachable()) {

                sch.scheduleJob(rescheduledancvisit, ancretrigger);

                System.out.println("REFIRING ANC SCHEDULER CALLED");

            }


            //===============================END OF ANC VISTS DATE=====================================   

            //===============================MONTHLY EXPECTED DELIVERY DATE===============================          

            //get the set scheduling time of edd from the database           


            conn.anc_sch_rs = conn.anc_scheduling_st.executeQuery("Select * from scheduler_settings where schedule_id='2'");


            //second minute hour am/pm


            String edd_check_time = "0 02 8am ";
            String eddretrigertime = " ";

            if (conn.anc_sch_rs.next()) {

                edd_check_time = "0 " + conn.anc_sch_rs.getString("minute") + " " + conn.anc_sch_rs.getString("hour") + conn.anc_sch_rs.getString("am_pm");
                eddretrigertime = year + "-" + month + "-" + date + " " + conn.anc_sch_rs.getString("hour") + ":" + conn.anc_sch_rs.getString("minute") + ":" + conn.anc_sch_rs.getString("second");
                dit = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(eddretrigertime);
            }




            CronTrigger eddTrigger = TriggerBuilder.newTrigger().withIdentity("eddtrigger" + jobprefix, "eddtriggergroup" + jobprefix).withSchedule(CronScheduleBuilder.cronSchedule(edd_check_time + " * * ?")) // .withSchedule(CronScheduleBuilder.cronSchedule("0 26 8a * * ?"))
                    .build();

            //edd if no internet connection
            Trigger eddretrigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(1).withRepeatCount(edd_anc_repeatcount)).startAt(dit).build();





            if (!sch.isShutdown() && isInternetReachable()) {

                sch.scheduleJob(eddjob, eddTrigger);
            } else if (!sch.isShutdown() && !isInternetReachable()) {

                sch.scheduleJob(reschedulededdjob, eddretrigger);

                System.out.println("((((()))))))=>REFIRING EDD SCHEDULER CALLED");

            }


//==========END OF EDD SCHEDULER


//when starting the system for the first time, this method is called

            if (request.getParameter("shutdown") == null) {
                nextpage = "index.jsp";
                shutdown = false;
                response.sendRedirect(nextpage);
            }

            // sch.shutdown();




        } catch (ParseException ex) {
            Logger.getLogger(ALLSMSRECEIVER.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SchedulerException ex) {

            Logger.getLogger(ALLSMSRECEIVER.class.getName()).log(Level.SEVERE, null, ex);
        }






    }//end of preocess request

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ALLSMSRECEIVER.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ALLSMSRECEIVER.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    //METHOD TO CHECK IF THERE IS INTERNET CONNECTIVITY FIRST BEFORE CALLING THE REMOTE SERVER..
    public static boolean isInternetReachable() {
        try {
            //make a URL to a known source
            URL url = new URL("http://196.201.225.101");

            //open a connection to that source
            URLConnection urlConnect = url.openConnection();

            //trying to retrieve data from the source. If there
            //is no connection, this line will fail
            urlConnect.getInputStream();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            System.out.println("ALL SMS SCHEDULER:Unknown host");
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("ALL SMS SCHEDULER:Error in internet connection");
            return false;
        }
        return true;
    }

    //=================DATE FUNCTION========
    public void currentdates() {

        Calendar cal = Calendar.getInstance();

        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        date = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        min = cal.get(Calendar.MINUTE);
        sec = cal.get(Calendar.SECOND);
        schedulingmin = cal.get(Calendar.MINUTE) + 2;
    }
}
