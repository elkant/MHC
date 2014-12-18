/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ANC_REG;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sender.dbConn;

/**
 *
 * @author SIXTYFOURBIT
 */
public class getStep5details_anc extends HttpServlet {
    
 String anc_no;
 String allinputfields;
   String col1opt=""; 
    String col2opt="";
    String col3opt="";
    String col4opt="";
    String col5opt="";
    String col6opt="";
    String col7opt="";
    String col8opt="";
    
    
      String old_yes_no_arr[]={"","Y","N"};
    String old_P_T_arr[]={"","P","T"};
    
    
     String Cancer_screening_method="";
 
 String cancer_screening_results_array[]={"","Suspected","Normal","Confirmed"};
    
    dbConn conn=new dbConn();
                                                              
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
  
     int allrowscount=0; 
  
    
    anc_no=request.getParameter("ancno");
     String motherID = request.getParameter("motherID");
    
        allinputfields="";
        Cancer_screening_method="";
        
        //TABEL HEADERS
        allinputfields+="<tr><th rowspan=\"4\">No</th><th colspan=\"5\">Prophylaxis</th><th colspan=\"2\">Screened For</th></tr>";
        
        allinputfields+="<tr><th rowspan=\"3\">CTX</th><th colspan=\"4\">ARV Regimen(Predominant)</th>"
                + "<th rowspan=\"3\">TB</td><th rowspan=\"2\">Cervical Cancer</th></tr>";
        
        allinputfields+="<tr><th colspan=\"3\">Mother</th><th rowspan=\"2\">NVP for Baby</th></tr>";
        
        allinputfields+="<tr><th>NVP</th><th>AZT</th><th>HAART</th><th>Method<br/>Result</th></tr>";
        allinputfields+="<tr><td></td><td>(x)</td><td>(y)</td><td>(z)</td><td>(aa)</td><td>(ab)</td><td>(ac)</td><td>(ad)</td></tr>";
        
        //IF DETAILS DONT EXIST IN THE DATABASE, create an extra table
   
        
//building up the options================================================= 
        
 String yes_no_option="<option value=\"\"></option><option value=\"Y\">Y</option><option value=\"N\">N</option>";
 
 String P_T_option="<option value=\"\"></option><option value=\"P\">P</option><option value=\"T\">T</option>";
 
 
 
 String TB_STATUS_option="<option value=\"\"></option>";

 String results_option="<option value=\"\"></option>";
 
 //==========tb_status option
 
 if(conn.st.isClosed()){conn=new dbConn();}
 
 conn.rs=conn.st.executeQuery("Select * from tb_status");
 while(conn.rs.next()){
     
 TB_STATUS_option+="<option value=\""+conn.rs.getString(1) +"\">"+conn.rs.getString(2) +"</option>";
 
 }    
 
//======cancer screening method option 
 
 conn.rs=conn.st.executeQuery("Select * from cervical_tests");
 while(conn.rs.next()){
     
 Cancer_screening_method+="<option value=\""+conn.rs.getString(1) +"\">"+conn.rs.getString(2) +"</option>";
 
                     } 
 
 
 //==========Cancer results option=========
 
 
 for(int p=0;p<cancer_screening_results_array.length;p++){
 
 results_option+="<option value=\""+cancer_screening_results_array[p] +"\">"+cancer_screening_results_array[p] +"</option>";
 
 
  }
 
//===================================end of building up the options================================  
        
     //if data exists in the database   
        
        
        
       // conn.rs1=conn.st1.executeQuery("Select CTX ,MotherNVP ,MotherAZT ,MotherHAART ,BabyNVP ,TbScreen ,Cc_methods ,cc_results  from xtoad where ancno='"+anc_no+"'");
  
     
    
    
  
        
        
        //===execute again while filling the table values
        
        String qr="Select CTX ,MotherNVP ,MotherAZT ,MotherHAART ,BabyNVP ,TbScreen ,Cc_methods ,cc_results,ancRegisterID  from xtoad where motherid='"+motherID+"'";
        
       conn.rs2=conn.st2.executeQuery(qr);
    
       
       System.out.append(qr);
        int oldcount=0;
        int newcount=0;
        while(conn.rs2.next()){
            
            
            col1opt=""; 
     col2opt="";
     col3opt="";
     col4opt="";
     col5opt="";
     col6opt="";
     col7opt="";
     col8opt="";
          
            //===call the options filler method
            
            
            currentrow(conn.rs2.getString("ancRegisterID").toString());
            
            
        oldcount++;
        allrowscount++;
        //=====values in the database
        
      allinputfields+="<tr ><th>"+allrowscount+"</th><th><select style=\"width:60px;\" id=\"old_CTX"+oldcount+"\" name=\"old_CTX"+oldcount+"\" >"+col1opt +"</select></th>"
                + "<th><select style=\"width:60px;\" id=\"old_NVP"+oldcount+"\" name=\"old_NVP"+oldcount+"\" >"+col2opt+"</select></th>"
                + "<th><select style=\"width:60px;\" id=\"old_AZT"+oldcount+"\" name=\"old_AZT"+oldcount+"\" >"+col3opt+"</select></th>"
                + "<th><select style=\"width:60px;\" id=\"old_HAART"+oldcount+"\" name=\"old_HAART"+oldcount+"\" >"+col4opt+"</select></th>"
                + "<th><select style=\"width:60px;\" id=\"old_BABYNVP"+oldcount+"\" name=\"old_BABYNVP"+oldcount+"\" >"+col5opt+"</select></th>"
                + "<th><select style=\"width:90px;\" id=\"old_TB"+oldcount+"\" name=\"old_TB"+oldcount+"\" >"+col6opt+"</select></th>"
                + "<th style=\"height:55px;\"><select id=\"old_Test"+oldcount+"\" name=\"old_Test"+oldcount+"\" style=\"height:44px;\" multiple >"+col7opt+"</select><br/><br/>"
                + "<select name=\"old_Result"+oldcount+"\" id=\"old_Result"+oldcount+"\" required>"+col8opt+"</select>"
              + "<input type=\"hidden\" id=\"ANCREGID"+oldcount+"\" name=\"ANCREGID"+oldcount+"\"  value=\""+conn.rs2.getString("ancRegisterID")+"\"/></th></tr>";
        
     
     
     }   
        
 
        
         //=======if no data exists in the database===========   
        if(1==1){
        newcount++;
        allrowscount++;
        allinputfields+="<tr><td>"+allrowscount+"</td><td><select style=\"width:60px;\" id=\"CTX1\" name=\"CTX1\" >"+yes_no_option+"</select></td>"
                + "<td><select style=\"width:60px;\" name=\"NVP1\" id=\"NVP1\" >"+yes_no_option+"</select></td>"
                + "<td><select style=\"width:60px;\" name=\"AZT1\"  id=\"AZT1\">"+yes_no_option+"</select></td>"
                + "<td><select style=\"width:60px;\" name=\"HAART1\" id=\"HAART1\" >"+P_T_option+"</select></td>"
                + "<td><select style=\"width:60px;\" name=\"BABYNVP1\"  id=\"BABYNVP1\">"+yes_no_option+"</select></td>"
                + "<td><select style=\"width:90px;\" name=\"TB1\" id=\"TB1\" >"+TB_STATUS_option+"</select></td>"
                + "<td style=\"height:55px;\"><select name=\"Test1\" id=\"Test1\" autofocus=\"true\" style=\"height:44px;\" multiple=\"true\" >"+Cancer_screening_method+"</select><br/><br/>"
                + "<select name=\"Result1\" id=\"Result1\"  required>"+results_option+"</select></td></tr>";
        
        }
        
        
        System.out.println("old count:"+oldcount);
        
   allinputfields+="<input type=\"hidden\" name=\"old_step5_no_rows\" id=\"old_step5_no_rows\" value=\""+oldcount+"\">"
           + "<input type=\"hidden\" name=\"new_step5_no_rows\" id=\"new_step5_no_rows\" value=\""+newcount+"\">"
           + "<input type=\"hidden\" name=\"all_rows_count\" id=\"all_rows_count\" value=\""+allrowscount+"\">";     
        
        
 
   
   
      PrintWriter out = response.getWriter();
    try {
        out.println(allinputfields);
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(getStep5details_anc.class.getName()).log(Level.SEVERE, null, ex);
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

//-------------------------Get the values in dbase for a certain row -------------------------------
    public void currentrow(String regid){
        try {
            conn.rs1=conn.st1.executeQuery("Select CTX ,MotherNVP ,MotherAZT ,MotherHAART ,BabyNVP ,TbScreen ,Cc_methods ,cc_results  from xtoad where ancRegisterID='"+regid+"'");
               
               while(conn.rs1.next()){
                //===CTX
                for(int a=0;a<old_yes_no_arr.length;a++){
                
                if(old_yes_no_arr[a].equals(conn.rs1.getString(1))){
                    
                col1opt+="<option selected value=\""+old_yes_no_arr[a]+"\">"+old_yes_no_arr[a]+"</option>";
                
                }
                
                else{
                col1opt+="<option value=\""+old_yes_no_arr[a]+"\">"+old_yes_no_arr[a]+"</option>";
                }
                             }
                //==NVP
                for(int a=0;a<old_yes_no_arr.length;a++){
                
                if(old_yes_no_arr[a].equals(conn.rs1.getString(2))){
                    
                col2opt+="<option selected value=\""+old_yes_no_arr[a]+"\">"+old_yes_no_arr[a]+"</option>";
                
                }
                
                else{
                col2opt+="<option value=\""+old_yes_no_arr[a]+"\">"+old_yes_no_arr[a]+"</option>";
                }
                             }
                
                
                
                //==AZT
                for(int a=0;a<old_yes_no_arr.length;a++){
                
                if(old_yes_no_arr[a].equals(conn.rs1.getString(3))){
                    
                col3opt+="<option selected value=\""+old_yes_no_arr[a]+"\">"+old_yes_no_arr[a]+"</option>";
                
                }
                
                else{
                col3opt+="<option value=\""+old_yes_no_arr[a]+"\">"+old_yes_no_arr[a]+"</option>";
                }
                             }
                
                
                
                //==HAART
                for(int a=0;a<old_P_T_arr.length;a++){
                
                if(old_P_T_arr[a].equals(conn.rs1.getString(4))){
                    
                col4opt+="<option selected value=\""+old_P_T_arr[a]+"\">"+old_P_T_arr[a]+"</option>";
                
                }
                
                else{
                    
                col4opt+="<option value=\""+old_P_T_arr[a]+"\">"+old_P_T_arr[a]+"</option>";
                
                }
                             }
                
                
                
                
                //==NVP FOR BABY
                
              
                for(int a=0;a<old_yes_no_arr.length;a++){
                
                if(old_yes_no_arr[a].equals(conn.rs1.getString(5))){
                    
                col5opt+="<option selected value=\""+old_yes_no_arr[a]+"\">"+old_yes_no_arr[a]+"</option>";
                
                }
                
                else{
                col5opt+="<option value=\""+old_yes_no_arr[a]+"\">"+old_yes_no_arr[a]+"</option>";
                }
                             }
                
              //TBSTATUS
                
                conn.rs=conn.st.executeQuery("Select * from tb_status");
                
        while(conn.rs.next()){
            if(conn.rs.getString(1).equals(conn.rs1.getString(6))){
       col6opt+="<option selected value=\""+conn.rs.getString(1) +"\">"+conn.rs.getString(2) +"</option>";
            }
            else{
            col6opt+="<option  value=\""+conn.rs.getString(1) +"\">"+conn.rs.getString(2) +"</option>";
            }
        }
                
        
        //cc methods
                conn.rs=conn.st.executeQuery("Select * from cervical_tests");
                
        while(conn.rs.next()){
            if(conn.rs1.getString(7).contains(conn.rs.getString(1))){
       col7opt+="<option selected value=\""+conn.rs.getString(1) +"\">"+conn.rs.getString(2) +"</option>";
            }
            else{
            col7opt+="<option  value=\""+conn.rs.getString(1) +"\">"+conn.rs.getString(2) +"</option>";
            }
        }
                
        
        //cc results using a fixed array list 
               
                
        for(int a=0;a<cancer_screening_results_array.length;a++){
            if(conn.rs1.getString(8).equalsIgnoreCase(cancer_screening_results_array[a])){
                
       col8opt+="<option selected value=\""+cancer_screening_results_array[a]+"\">"+cancer_screening_results_array[a]+"</option>";
            
            }
            
            
            else{
            col8opt+="<option  value=\""+cancer_screening_results_array[a] +"\">"+cancer_screening_results_array[a]+"</option>";
            }
                             }
                
       // System.out.println("passed reg id"+regid);
        
              
               
               }//end of while
        } catch (SQLException ex) {
            Logger.getLogger(getStep5details_anc.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}//end of function

}
