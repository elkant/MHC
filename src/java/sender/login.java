/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SIXTYFOURBIT
 */
public class login extends HttpServlet {

    String uname,pass,error_login,nextPage,current_time;
    String computername;
    MessageDigest m;
    
    HttpSession session;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, SQLException {
        
            response.setContentType("text/html;charset=UTF-8");
          
Calendar cal = Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH)+1;
int date=cal.get(Calendar.DATE);
int hour = cal.get(Calendar.HOUR_OF_DAY);
int min=cal.get(Calendar.MINUTE);
int sec=cal.get(Calendar.SECOND);
String yr,mnth,dater,hr,mn,sc,action="";
yr=Integer.toString(year);
mnth=Integer.toString(month);
dater=Integer.toString(date);
hr=Integer.toString(hour);
mn=Integer.toString(min);
sc=Integer.toString(sec);


//____________________GET COMPUTER NAME____________________________________
computername=InetAddress.getLocalHost().getHostName();
System.out.println("Computer name "+computername);





updateFacilityNames();


//current_time=sc+"-"+mn+"-"+hr+"-"+dater+"-"+mnth+"-"+yr;

//current_time=yr+"-"+mnth+"-"+dater+"-"+hr+":"+mn+":"+sc;

current_time=yr+"-"+mnth+"-"+dater+"-"+hr+":"+mn+":"+sc;
            //get username and password
            
           uname=request.getParameter("uname");
            
           pass=request.getParameter("pass");
           
           
           session= request.getSession();
           
           
              
        //encrypt password
            
                m = MessageDigest.getInstance("MD5");
                m.update(pass.getBytes(), 0, pass.length());
                String pw = new BigInteger(1, m.digest()).toString(16);
         
                
                //connection to database class instance
                dbConn conn = new dbConn();
                
                
                //query for checking user existance in the database
                String select1 = "select * from users";
                
                
                
                
//System.out.println("worked up to here 1;");
                
                   conn.rs = conn.st.executeQuery(select1);
                   
                  // System.out.println("username:"+uname+"  Password :"+pw );
                   
                   
                    while (conn.rs.next()) {
  if (conn.rs.getString("username").equals(uname) && conn.rs.getString("password").equals(pw)) {
          
                    error_login = "";
   //___________________________ADMIN level 0_____________________________________
                    if (conn.rs.getString("level").equals("0")) {
                     String ip=InetAddress.getLocalHost().getHostAddress();   
                      //  System.out.println("level:"+conn.rs.getString("level"));
    String inserter="insert into audit set host_comp='"+computername+" "+ip+"' , action='Logged in ',time='"+current_time+"',actor_id='"+conn.rs.getString("userid")+"'";                     
     
    //String inserter="insert into audit  (action,time,actor_id,host_comp) values ('"++"','"+"')";
    
    conn.st3.executeUpdate(inserter);                
                        //the next page to be opened based on user level
                        nextPage = "newClerk.jsp";
                        
                       
//String fulname=""+conn.rs.getString("firstname") + " "+conn.rs.getString("lastname");
//audit="Insert into audit (Action,User) values('Logged in','"+fulname+"')";

                        
                        
                        //save current user details into a session
                        
                        
                        session.setAttribute("userid", conn.rs.getString("userid"));
                        session.setAttribute("username", conn.rs.getString("username"));
                        session.setAttribute("level", conn.rs.getString("level"));
                       
                        
                        //get teacher details from the teacher registration table 
                        
                 
                     /** code for auditing  */
                     
                       // conn.st.executeUpdate(audit);
                       break;  
                    }//end of admin level
                    
     
                       
                       
//****************************Clerk module**********************************************        
                       
       
                       
                               
                       else if(conn.rs.getString("level").equals("2"))
                           

{
    // System.out.println("level 2");      
                nextPage = "viewallmothers";
               
                
                
                session.setAttribute("userid", conn.rs.getString(1));
                session.setAttribute("username", conn.rs.getString("username"));
                session.setAttribute("level", conn.rs.getString("level"));
                //save other session details to dbase
               
                String clerk="select * from clerks";
                
                 conn.rs = conn.st.executeQuery(clerk); 
                 
                while( conn.rs.next()){
                    
                    if(conn.rs.getString("clerk_id").equals(session.getAttribute("userid"))){
                    
                     session.setAttribute("f_name", conn.rs.getString("first_name"));   
                    session.setAttribute("s_name", conn.rs.getString("sur_name")); 
                   String ip=InetAddress.getLocalHost().getHostAddress();  
                       String inserter="insert into audit set host_comp='"+computername+" "+ip+"' , action='Logged in',time='"+current_time+"',actor_id='"+conn.rs.getString("clerk_id")+"'";
                     conn.st3.executeUpdate(inserter);
                     
                break;
                    }
                
                }
//error_login="<b><font color=\"red\">ooops! wrong username and / or password combination</font></b>";
error_login=null;

 break;

} 
                       
                       
                       
//****************************wrong username password                        
                       else {
                       
                       nextPage = "index.jsp";
                     
                        System.out.println("third level");
                       
                     error_login="<b><font color=\"red\">ooops! wrong username and / or password combination</font></b>";
                       
                       }
                
                
                           
           
           
            
            
            
        }//end of first if

  
  else {
   
        //System.out.println("worked up to here 6;");
      
                       nextPage = "index.jsp";
                     
                        error_login="<b><font color=\"red\">wrong username and or password of clerk</font></b>";
                       
                     //System.out.println(">>"+nextPage);   
  
  }
  
  
  
                    }
                    
                    
                    session.setAttribute("error_login", error_login);
                   response.sendRedirect(nextPage); 
                    
              
                           
                        
                         
                           
                 
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }

        /** 
         * Returns a short description of the servlet.
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo() {
            return "Short description";
        }// </editor-fold>
        

        
        public  void updateFacilityNames(){
        try {
           // mobiledbConn conn=new mobiledbConn();
           dbConn conn = new dbConn();
                   
                   String getfacil="Select *  from mother_details where facilityname LIKE 'Emmining'";
                   
                   conn.rs=conn.st.executeQuery(getfacil);
                   
                   int count=0;
                   
                   while(conn.rs.next()){
                   
                       count++;
                       
                       String updatefacil="update mother_details set facilityname='Emining' where motherID='"+conn.rs.getString("motherID")+"'";
                   
                       System.out.println("\n "+count+"___"+updatefacil);
                       
                       conn.st2.executeUpdate(updatefacil);
                       
                   
                   }
                   
                   //update NGUBURET FACILITY
                   
                    String getfacil2="Select *  from mother_details where facilityname LIKE 'Ngubureti'";
                   
                   conn.rs=conn.st.executeQuery(getfacil2);
                   
                   int count1=0;
                   
                   while(conn.rs.next()){
                   
                       count1++;
                       
                       String updatefacil2="update mother_details set facilityname='Ngubereti' where motherID='"+conn.rs.getString("motherID")+"'";
                   
                       System.out.println("\n "+count1+"___"+updatefacil2);
                       
                       conn.st2.executeUpdate(updatefacil2);
                       
                   
                   }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       
        
     
     }
        
        
        
    
}