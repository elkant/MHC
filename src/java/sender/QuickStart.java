/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import it.sauronsoftware.cron4j.Scheduler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SIXTYFOURBIT
 */
public class QuickStart {
    
    
    
    ArrayList phonenos=new ArrayList();

    public QuickStart(){
    
 //if phonenumbers arraylist is not empty clear it
        
        
    if(phonenos.size()>0){
        phonenos.clear();
        
        
       }
    
    
    }
    
    
    
   public static void main(String[] args) {
       //empty the arraylist
       
       
       new QuickStart();
       
       
		// Creates a Scheduler instance.
		Scheduler s = new Scheduler();
		// Schedule a once-a-minute task.
		s.schedule("55 08 * * 1-5", new Runnable() {
			public void run() {
                            //select querr from dbase
                            
                            
				System.out.println("Another minute ticked away...");
			}
		});
		// Starts the scheduler.
		s.start();
		// Will run for ten minutes.
		try {
			Thread.sleep(1000L * 60L * 10L);
		} catch (InterruptedException e) {
			;
		}
		// Stops the scheduler.
		s.stop();
	}
   
   
   
   public void getmothers(){
   
       String mymothers="Select PhoneNo , PhoneNo1 from mother details where status_id ='1'";
       dbConn conn= new dbConn();
        try {
            
            conn.rs=conn.st.executeQuery(mymothers);
            while(conn.rs.next()){
            phonenos.add(conn.rs.getString(1));
            phonenos.add(conn.rs.getString(2));
            
            
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(QuickStart.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   
   }
   
   
}
