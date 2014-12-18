/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;  
import sender.dbConn;


/**
 *
 * @author SIXTYFOURBIT
 */
public class importSQL extends HttpServlet {
    
    
    dbConn conn=null;
    String dbname,dbuser,dbpassword;
boolean executed=false;
String localhost="";
String[] localhostsplit;
String dbpath="";
String found_folder,full_date,path,computername,senderofmail;
HttpSession session;
String [] myalphabet={"B","C","D","F","G","H","I","J","K","L","M","N","O","Q","R","S","T","U","V","W","X","Y","Z"};

    

    private static final long serialVersionUID = 1L;

    private static final String DATA_DIRECTORY = "MHCDATA";
    private static final int MAX_MEMORY_SIZE = 1024 * 2048 * 20;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024*4;
    
    String filename="";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      
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
        try {
            processRequest(request, response);
            
            
            
            
              filename=request.getParameter("filename");
            System.out.println("file name is"+filename);
            
             // Check that we have a file upload request
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);

            if (!isMultipart) {
                return;
            }

            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Sets the size threshold beyond which files are written directly to
            // disk.
            factory.setSizeThreshold(MAX_MEMORY_SIZE);

            // Sets the directory used to temporarily store files that are larger
            // than the configured size threshold. We use temporary directory for
            // java
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            // constructs the folder where uploaded file will be stored
            String uploadFolder = getServletContext().getRealPath("")+ File.separator + DATA_DIRECTORY;

            new File(uploadFolder).mkdirs();
            
            
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Set overall request size constraint
            upload.setSizeMax(MAX_REQUEST_SIZE);

            
                // Parse the request
                List /* FileItem */ items = upload.parseRequest(request);  
                Iterator iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();

                    if (!item.isFormField()) {
                  
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadFolder + File.separator + fileName;
                        File uploadedFile = new File(filePath);
                        
                        dbpath=filePath;
                        System.out.println(filePath);
                        // saves the file to upload directory
                        item.write(uploadedFile);
                        
                   
                    }}
        } catch (Exception ex) {
            Logger.getLogger(importSQL.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        //======now begin uploading the data into the database
        
        //the the drive of current location
          URL location = importSQL.class.getProtectionDomain().getCodeSource().getLocation();
 String   mydriv = location.getFile().substring(1, 2);  
        



        
        
        session=request.getSession();
         conn = new dbConn();
        
         
         createtables();
        //====call the method that creates the tables at this stage....
        
        //this database name should be static to avoid replicationg the master database
        dbname="tempmhc";
        dbuser="root";
        dbpassword="";

        found_folder="";



    
    if(1==1){
//         
          String  executeCmd="";  
String[] executeCmd1=null;
        
             if(conn.dbsetup[3]!=null){
             dbpassword=conn.dbsetup[3];
                 
             
             }
             
             
             
             if(conn.dbsetup[2]!=null){
                 
             dbuser=conn.dbsetup[2];
                 
             
             }
             
             
             
//             if(conn.dbsetup[1]!=null){
//                 
//             dbname=conn.dbsetup[1];                 
//             
//             }
             
                if (conn.dbsetup[0] != null) {

                localhost = conn.dbsetup[0];
                localhostsplit = localhost.split(":");

            }
             
             
             System.out.println("PASSWORD IS :"+dbpassword);
             System.out.println("USER IS :"+dbuser);
             System.out.println("DBNAME IS :"+dbname);
             System.out.println("HOST :"+localhost);
             System.out.println("PORT :"+localhostsplit[1]);
  
             
                for (int i=0;i<myalphabet.length;i++){
                    System.out.println("at position  :  "+myalphabet[i]);
              String current_drive=myalphabet[i];
              File f =  new File(current_drive+":\\wamp\\mysql\\bin\\");
              File f1 = new File(current_drive+":\\wamp\\bin\\mysql\\mysql5.6.12\\bin");
              File f2 = new File(current_drive+":\\Program Files\\MySQL\\MySQL Server 5.5\\bin");
             

        
  
// CHECK WHICH PROGRAM IS INSTALLED TO ENSURE THAT DATA IS BACKED UP SUCCESSFULLY.             
              
if (f.exists() && f.isDirectory()){

executeCmd1 = new String[]{current_drive+":\\wamp\\mysql\\bin\\mysql","--host=" + localhostsplit[0], "--port=" + localhostsplit[1], "--user=" + dbuser, "--password=" + dbpassword, dbname,"-e", " source "+dbpath};  


System.out.println(executeCmd);
found_folder="it is old wamp";
}
if (f1.exists() && f1.isDirectory()){

 executeCmd1 = new String[]{current_drive+":\\wamp\\bin\\mysql\\mysql5.6.12\\bin\\mysql","--host=" + localhostsplit[0], "--port=" + localhostsplit[1], "--user=" + dbuser, "--password=" + dbpassword, dbname,"-e", " source "+dbpath};  


System.out.println(executeCmd);

found_folder="it is new wamp";
}
 if (f2.exists() && f2.isDirectory()){

 executeCmd1 = new String[]{current_drive+":\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysql","--host=" + localhostsplit[0], "--port=" + localhostsplit[1], "--user=" + dbuser, "--password=" + dbpassword, dbname, "-e", " source "+dbpath};  


found_folder="it is workbench";
System.out.println(executeCmd);
}
 
 
}      
             
             
             
             
System.out.println(found_folder);

Process runtimeProcess;
        try {
            
 System.out.println("trying to import the data.");
 for(int a=0;a<executeCmd1.length;a++){
 System.out.println(""+executeCmd1[a]);
 
 }
 
 //if the selected database is biometric_master_db,do not import a database since 
 
 
runtimeProcess = Runtime.getRuntime().exec(executeCmd1);



System.out.println("process started");
             int processComplete = runtimeProcess.waitFor();
            System.out.println("at 1 is "+processComplete);
            if (processComplete == 0) {

                System.out.println("Import completed successfully");
           
                
                session.setAttribute("datasend1", "<font color=\"green\">Data has been imported successfully</font>");
              
               conn.st4.executeUpdate("CREATE TABLE if not exists `mastermhc`.`importhistory` ("
+"`tableid` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,"
+"`file_name` VARCHAR( 200 ) NOT NULL ,"
+"`timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,"
+"INDEX ( `file_name` )) ENGINE = InnoDB;"); 
                
               
 conn.st4.executeUpdate("CREATE TABLE if not exists `mhc`.`importhistory` ("
+"`tableid` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,"
+"`file_name` VARCHAR( 200 ) NOT NULL ,"
+"`timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,"
+"INDEX ( `file_name` )) ENGINE = InnoDB;"); 
               
               
                conn.st4.executeUpdate("insert into importhistory(file_name)values('"+dbpath+"')"); 
              
				 
            } else {
                System.out.println("Could not import data");
	session.setAttribute("datasend1", "Data not imported");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    
    response.sendRedirect("mergeData");
        
    }//end of dopost

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
  public void createtables(){  
        try {
            //create connection
              
              dbConn conn1= new dbConn();
              
            System.out.println("CREATING TABLE CALLED___");  
            
              conn1.st.executeUpdate("CREATE DATABASE IF NOT EXISTS `tempmhc`");
              
             
              
              
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(importSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
      
    
  }
    
    
}
