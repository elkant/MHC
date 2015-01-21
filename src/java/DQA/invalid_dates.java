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
        
        Unique_No="ANC NO";
        }
        else if(request.getParameter("register").equalsIgnoreCase("maternity")){
        Unique_No="Admission No.";
        
        }
        else if (request.getParameter("register").equalsIgnoreCase("postnatal")){
        Unique_No="PNCRegNo";
        
        }
        
        try {
           
            session=request.getSession();
            
          dbConn conn= new dbConn();
          
         
          
         
         //now receive the parameters passed
         String category="";
         if(request.getParameter("category")!=null){
         
         category=request.getParameter("category");
         }
         
          
         
     
          System.out.println(category);
          
         
          
          
          
   String table=" <thead>"
		           +"<tr>"
                           +"<th>"+Unique_No+"</th>"                          
                           +"<th>Full Name</th>"                          
                           +"<th>Facility Name</th>"                                              
                           +"<th>Date</th>"                                              
                           +"<th>Date Type</th>"                     
                           +"</tr>"		                
		           +"</thead>"
		           +"<tbody>";
                             
         
         
          String anc_facil_name=" select "
+" u.motherID as id,"
+" u.anc_no, "
+" UPPER(concat(u.FName,\"\",u.SName,u.LName,u.Age)) as mother_name,"
+" u.FName,"
+" u.SName,"
+" u.LName,"
+" u.Age,"
+" u.facilityname,"
+" ocur,"
+" case" 
+" when ocur >1 then 'yes' "
+" else 'no' "
+" end as isduplicate"
+" FROM mother_details  u "
+" inner JOIN ("
+" select  motherID  ,anc_no, FName ,SName,LName,facilityname  ,mother_details.Age  , count(*) as ocur"
+" FROM mother_details"
 
+" group by anc_no,FName,SName,LName,Age,facilityname having ocur > 1"
 +" )  temp "
 +" on u.FName=temp.FName "
+" and u.SName=temp.SName "
+" and u.LName=temp.LName "
+" and u.anc_no=temp.anc_no"
+" and u.facilityname=temp.facilityname" 
+" group by id order by mother_name desc " ;
      
   
//=========================================anc and facility=================================================================
        String anc_facility=  " select "
+" u.motherID as id,"
+" u.anc_no, "
+" UPPER(concat(u.FName,\"\",u.SName,u.LName,u.Age)) as mother_name,"
+" u.FName,"
+" u.SName,"
+" u.LName,"
+" u.Age,"
+" u.facilityname,"
+" ocur,"
+" case" 
+" when ocur >1 then 'yes' "
+" else 'no' "
+" end as isduplicate"
+" FROM mother_details  u "
+" inner JOIN ("
+" select  motherID  ,anc_no, FName ,SName,LName,facilityname  ,mother_details.Age  , count(*) as ocur"
+" FROM mother_details where anc_no !=''"
+" group by anc_no,FName,SName,LName,Age,facilityname having ocur > 1"
 +" )  temp "
 +" on  u.anc_no=temp.anc_no"
+" and u.facilityname=temp.facilityname where u.anc_no !=''" 
+" group by id order by mother_name desc " ;
 
        
      //  ====================================Names and facility=============================================
        
        
            String name_facility=" select "
+" u.motherID as id,"
+" u.anc_no, "
+" UPPER(concat(u.FName,\"\",u.SName,u.LName,u.Age)) as mother_name,"
+" u.FName,"
+" u.SName,"
+" u.LName,"
+" u.Age,"
+" u.facilityname,"
+" ocur,"
+" case" 
+" when ocur >1 then 'yes' "
+" else 'no' "
+" end as isduplicate"
+" FROM mother_details  u "
+" inner JOIN ("
+" select  motherID  ,anc_no, FName ,SName,LName,facilityname  ,mother_details.Age  , count(*) as ocur"
+" FROM mother_details where concat(FName,\"\",SName,LName) !=''"
+" group by anc_no,FName,SName,LName,Age,facilityname having ocur > 1"
 +" )  temp "
 +" on u.FName=temp.FName "
+" and u.SName=temp.SName "
+" and u.LName=temp.LName"
+" and u.facilityname=temp.facilityname where concat(temp.FName,\"\",temp.SName,temp.LName)!=''" 
+" group by id order by mother_name desc " ;
        
     String qr="";   
          
       
     
     if(category.equals("ancno_facility")){
     
     qr=anc_facility;
     header="Duplicates Found Using a combination of </font><font color='orange'> ANC NO and FACILITY NAME</font>";
     }
     else if (category.equals("name_facility"))
     {
     header="Duplicates Found Using a combination of </font><font color='orange'>MOTHER NAME and FACILITY NAME</font>";
      qr=name_facility;   
     
     }
     else if(category.equals("name_ancno_facility")){
     
     qr=anc_facil_name;
     header="Duplicates Found Using a combination of <font color='orange'>MOTHERS NAME, ANC NO and FACILITY NAME</font>";
     }
     
    //now  based on the received parameter, choose the query to run 
       System.out.println(qr);
       
          conn.rs=conn.st.executeQuery(qr);
          
     int count=0;     
    while(conn.rs.next()){
    count++;
    //now check from various registers whether the current mothers records exist
    
  String registers="";
  
  String anc="";
  String matno="";
  String postnataf="";
  
  
  conn.rs1=conn.st1.executeQuery("select ancno from atoh where motherid='"+conn.rs.getString("id")+"' limit 1");
 if(conn.rs1.next()){ anc=conn.rs1.getString(1); registers+="ANC ,";     }
 
 conn.rs1=conn.st1.executeQuery("select AdmissionNo from mat_atoh where motherid='"+conn.rs.getString("id")+"' limit 1");
 if(conn.rs1.next()){ matno=conn.rs1.getString(1);  registers+="MATERNITY ,";     }
 
 
 conn.rs1=conn.st1.executeQuery("select PNCRegNo from postnat_atof where motherid='"+conn.rs.getString("id")+"' limit 1");
 if(conn.rs1.next()){ postnataf=conn.rs1.getString(1);  registers+="POSTNATAL";     }
 
 
 if(postnataf.equalsIgnoreCase("null")){ postnataf="";}
 if(anc.equalsIgnoreCase("null")){ anc="";}
 if(matno.equalsIgnoreCase("null")){ matno="";}
 
    table+="<tr id='"+conn.rs.getString("id")+"'>"
          
            + "<td>"+conn.rs.getString("mother_name")+"</td>"
            + "<td>"+anc+"</td>"
            + "<td>"+matno+"</td>"
            + "<td>"+postnataf+"</td>"
            + "<td>"+conn.rs.getString("facilityname")+"</td>"
            + "<td>"+conn.rs.getString("Age")+"</td>"           
            + "<td>"+conn.rs.getString("ocur")+"</td>"
            + "<td>"+registers+"</td>"
           
            + "</tr>";
    
   // System.out.println(count+"~~"+table);
    
    
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
