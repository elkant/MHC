/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DQA;


import java.io.IOException;
import java.io.PrintWriter;
import java.lang.String;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sender.dbConn;

/**
 *
 * @author Manuel
 */
public class invalid_dates extends HttpServlet {

  HttpSession session;
  String header="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String Unique_No="";
        
        header="";
        
        
        
//(start_date NOT REGEXP '^..........$' ||  new_topic.end_date NOT REGEXP '^..........$')
        
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        if(request.getParameter("register").equalsIgnoreCase("anc")){
        
        Unique_No="Anc no";
        }
        else if(request.getParameter("register").equalsIgnoreCase("maternity")){
        Unique_No="Admission No.";
        
        }
        else if (request.getParameter("register").equalsIgnoreCase("postnatal")){
        Unique_No="PNC Reg No.";
        
        }
        
        try {
           
            session=request.getSession();
            
          dbConn conn= new dbConn();
          
         
          
         
         //now receive the parameters passed
         String category="";
         if(request.getParameter("register")!=null){
         
         category=request.getParameter("register");
         }
         
          
         
     
          System.out.println(category);
          
         
          
          
          
   String table=" <thead>"
		           +"<tr>"
                           +"<th>"+Unique_No+"</th>"                          
                           +"<th>Full Name</th>"                          
                           +"<th>Facility Name</th>"                                              
                           +"<th>Invalid Date</th>"                                              
                           +"<th>Date Type</th>"                     
                           +"</tr>"		                
		           +"</thead>"
		           +"<tbody>";
                             
         
         
          String anc1="select  concat(ancRegisterID,\"%ancRegisterID%DateofVisit%atoh\") as id, ancno, UPPER(concat(FName,\" \",SName,\" \",LName)) as mother_name, "
 +" facilityname,DateofVisit ,\"Visit date\" FROM mother_details left join atoh on mother_details.motherID=atoh.motherid "  
 +" where (DateofVisit NOT REGEXP '^..........$' || DateofVisit>='2017-01-01')";
          
   
          
String mat1="select  concat(mat_atoh.MatRegisterID,\"%MatRegisterID%AdmissionDate%mat_atoh\") as id, mat_atoh.AdmissionNo as anc_no, UPPER(concat(FName,\" \",SName,\" \",LName)) as mother_name, "
+" facilityname,AdmissionDate ,\"Admission Date\" FROM mother_details left join mat_atoh on mother_details.motherID=mat_atoh.motherid "  
 +" where (AdmissionDate NOT REGEXP '^..........$' || AdmissionDate>='2016-01-01') ";
   
String mat2 ="select  concat(mat_htol.MatRegisterID,\"%MatRegisterID%LMP%mat_atoh\") as id, mat_htol.AdmissionNo as anc_no, UPPER(concat(FName,\" \",SName,\" \",LName)) as mother_name, "
+" facilityname,LMP ,\"LMP\" FROM mother_details left join mat_htol on mother_details.motherID=mat_htol.motherid "  
 +" where (LMP NOT REGEXP '^..........$' || LMP>='2016-01-01')";           
          
String mat3="select  concat(mat_htol.MatRegisterID,\"%MatRegisterID%EDD%mat_htol\") as id, mat_htol.AdmissionNo as anc_no, UPPER(concat(FName,\" \",SName,\" \",LName)) as mother_name, "
+" facilityname,EDD ,\"EDD\" FROM mother_details left join mat_htol on mother_details.motherID=mat_htol.motherid "
+" where (EDD NOT REGEXP '^..........$' || EDD>='2016-01-01')  ";          
      

String mat4="select  concat(mat_mtou.MatRegisterID,\"%MatRegisterID%DeliveryDate%mat_mtou\") as id, mat_mtou.AdmissionNo as anc_no, UPPER(concat(FName,\" \",SName,LName)) as mother_name," 
+" facilityname,DeliveryDate ,\"DeliveryDate\" FROM mother_details left join mat_mtou on mother_details.motherID=mat_mtou.motherid"   
+" where (DeliveryDate NOT REGEXP '^..........$' || DeliveryDate>='2016-01-01')";
  


String postnat="select  concat(postnat_atof.ID,\"%ID%VisitDate%postnat_atof\") as id , postnat_atof.PNCRegNo as anc_no, UPPER(concat(FName,\" \",SName,\" \",LName)) as mother_name,"
+" facilityname,postnat_atof.VisitDate,\"Visit Date\" FROM mother_details left join postnat_atof on mother_details.motherID=postnat_atof.motherid "
+" where (postnat_atof.VisitDate NOT REGEXP '^..........$' || postnat_atof.VisitDate>='2016-01-01') ";


String qr="";   
          
       
     
     if(category.equals("anc")){
     
     qr=anc1;
     header="Invalid dates in the </font><font color='orange'> ANC REGISTER </font>";
     }
     else if (category.equals("maternity"))
     {
     header="Invalid dates entered in the </font><font color='orange'>MATERNITY REGISTER </font>";
      qr=mat1;   
  //============================================================================   
      conn.rs=conn.st.executeQuery(mat2);       
    int count=0;     
    while(conn.rs.next()){
    count++;
    //now check from various registers whether the current mothers records exist     
    table+="<tr id='"+conn.rs.getString("id")+"'>"          
            + "<td style='text-align:center;'>"+conn.rs.getString(2)+"</td>"
            + "<td style='text-align:center;'>"+conn.rs.getString(3)+"</td>"
            + "<td style='text-align:center;'>"+conn.rs.getString(4)+"</td>"           
            + "<td style='text-align:center;'>"+conn.rs.getString(5)+"</td>"
            + "<td style='text-align:center;'>"+conn.rs.getString(6)+"</td>"
            + "</tr>"; 
    }
    
//=============================================================================    

    //============================================================================   
      conn.rs=conn.st.executeQuery(mat3);       
    
    while(conn.rs.next()){
    count++;
    //now check from various registers whether the current mothers records exist     
    table+="<tr id='"+conn.rs.getString("id")+"'>"          
            + "<td style='text-align:center;'>"+conn.rs.getString(2)+"</td>"
            + "<td style='text-align:center;'>"+conn.rs.getString(3)+"</td>"
            + "<td style='text-align:center;'>"+conn.rs.getString(4)+"</td>"           
            + "<td style='text-align:center;'>"+conn.rs.getString(5)+"</td>"
             + "<td style='text-align:center;'>"+conn.rs.getString(6)+"</td>"
            + "</tr>"; 
    }
    
//============================================================================= 
    
    
  //============================================================================   
      conn.rs=conn.st.executeQuery(mat4);       
        
    while(conn.rs.next()){
    count++;
    //now check from various registers whether the current mothers records exist     
    table+="<tr id='"+conn.rs.getString("id")+"'>"          
            + "<td style='text-align:center;'>"+conn.rs.getString(2)+"</td>"
            + "<td style='text-align:center;'>"+conn.rs.getString(3)+"</td>"
            + "<td style='text-align:center;'>"+conn.rs.getString(4)+"</td>"           
            + "<td style='text-align:center;'>"+conn.rs.getString(5)+"</td>"
             + "<td style='text-align:center;'>"+conn.rs.getString(6)+"</td>"
            + "</tr>"; 
    }
    
//=============================================================================   
    
    
    
      
     }
     else if(category.equals("postnatal")){
     
     qr=postnat;
     header="Invalid dates enterd in the  <font color='orange'> POSTNATAL REGISTER </font>";
     }
     
    //now  based on the received parameter, choose the query to run 
       System.out.println(qr);     
    conn.rs=conn.st.executeQuery(qr);       
    int count=0;     
    while(conn.rs.next()){
    count++;
    //now check from various registers whether the current mothers records exist     
    table+="<tr id='"+conn.rs.getString("id")+"'>"          
            + "<td style='text-align:center;'>"+conn.rs.getString(2)+"</td>"
            + "<td style='text-align:center;'>"+conn.rs.getString(3)+"</td>"
            + "<td style='text-align:center;'>"+conn.rs.getString(4)+"</td>"           
            + "<td style='text-align:center;'>"+conn.rs.getString(5)+"</td>"
            + "<td style='text-align:center;'>"+conn.rs.getString(6)+"</td>"
            + "</tr>"; 
    }    
    
      table+=" </tbody>"; 
            out.println(table);   
            session.setAttribute("loaded_inv_table",table);
            
            session.setAttribute("inv_header",header);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(loadduplicates.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        finally { 
            
            out.close();
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
