/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SIXTYFOURBIT
 */
public class getPCRDNADetails extends HttpServlet {
    
 String anc_no, allinputfields,tableid;
 
 ArrayList allhivtestnames= new ArrayList();
 ArrayList allhivtestids= new ArrayList();
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    
     anc_no = request.getParameter("ancno");
     String motherID = request.getParameter("motherID");
     
     
     allinputfields="";
     
     
     //create the default table input fields
     
     allinputfields+="<tr><th style=\"width:12px;\">Type of Test</th>" 
               +"<th style=\"width:62px;\">Date of Sample</th>"
               +"<th style=\"width:62px;\">Results</th>"
               +"</tr>";
     
     
     
     
     //first check whether there are any tests done in the database
     
     
     
   
     
     dbConn conn= new dbConn();
      int allrows=0;
     //get all test ids into one string.
     if(allhivtestnames.size()>0){
     allhivtestnames.clear();
     }
     
     
     if(allhivtestids.size()>0){
     
     allhivtestids.clear();
     }
     
     //get all the tests from a database
     conn.rs1=conn.st1.executeQuery("select * from hiv_exposure_tests");
     
     while(conn.rs1.next()){
         
         allrows++;
     
         allhivtestids.add(conn.rs1.getString(1));
         allhivtestnames.add(conn.rs1.getString(2));
     
         
     }//end of while
     
  
   
     
    int count=0;
    int count1=1;
   
    
    
     //get the saved content
     String usedids="Select * from hiv_exposure where motherid='"+motherID+"'";
   conn.rs=conn.st.executeQuery(usedids);
  
   
   
   
   
   
   
   
   
   while(conn.rs.next()){
       
       conn.rs4=conn.st4.executeQuery("Select * from hiv_exposure_tests where test_id='"+conn.rs.getString("test_id")+"'");
       conn.rs4.next();
       
   count++;
  // System.out.println("SIZE"+allhivtestnames.size());
   
    // if(!allhivtestnames.get(count-1).equals("")){ 
         
 allinputfields += "<tr><th><label><b>" + conn.rs4.getString(2) + "</b></label><input type=\"hidden\" name=\"dna_old_test_id"+count+"\" value=\""+conn.rs.getString("test_id")+"\"></th>"
         + " <th><input type=\"text\" id=\"dna_dp" + count + "\" name=\"dna_old_date" + count + "\" value=\""+conn.rs.getString("date_of_sample_collection") +"\" id=\"dna_dp" + count + "\"/></th>"
         + " <th><input type=\"text\" value=\""+conn.rs.getString("results")+"\"  id=\"dna_old_results"+count + "\" name=\"dna_old_results"+count + "\" >"
         + "<input type=\"hidden\" name=\"dna_exposure_id"+count+"\" value=\""+conn.rs.getString(1) +"\"></td></tr>";
 

    // }
     
     //reduce the number of rows expected in the input fields that are remaining
     allrows--;
       
     
       for(int a=0;a<allhivtestids.size();a++){
       if(allhivtestids.get(a).toString().trim().equals(conn.rs.getString("test_id").trim())){
allhivtestids.remove(a);
allhivtestnames.remove(a);
       }
   }
       
   }//end of first while
   
   //at this position, clear from the arraylist the already added integers
  // int allength=allhivtestids.size();
   
 
   
   
   //create the fresh input fields 
   for(int p=0;p<allrows;p++){
   
   allinputfields += "<tr><td><label>" + allhivtestnames.get(p)+ "</label><input type=\"hidden\" value=\""+allhivtestids.get(p) +"\" name=\"dna_test_id"+(p+1)+"\" ></td>"
         + " <td><input type=\"text\" placeholder=\"yyyy-mm-dd\" name=\"dna_date" +(p+1) + "\"id=\"dnadp" +(p+1) + "\"/></td>"
         + " <td><input type=\"text\" id=\"dna_results"+(p+1) + "\"  name=\"dna_results"+(p+1) + "\" >"
         + "</td></tr>";
       
   }  
   
   
   allinputfields+="<input type=\"hidden\" name=\"dna_new_no_of_rows\" id=\"dna_new_no_of_rows\" value=\""+allrows+"\">"
        + "<input type=\"hidden\" name=\"dna_old_no_of_rows\" id=\"dna_old_no_of_rows\" value=\""+count+"\"></td></tr>";
   
   
    PrintWriter out = response.getWriter();
    try {
        
        out.println(allinputfields);
        
    } finally {            
        out.close();
    }
    
    
    
    
    
}       catch (SQLException ex) {
            Logger.getLogger(getPCRDNADetails.class.getName()).log(Level.SEVERE, null, ex);
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
}
