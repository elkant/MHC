/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SCRIPTS;

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
import sender.mobiledbConn;

/**
 *
 * @author Manuel
 */
public class UpdateEddMotherDetails extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    response.setContentType("text/html;charset=UTF-8");
    
    //after calling this method, the rest of the linked methods have been called sequentially
    updateANCEDD();
    
    
    
    PrintWriter out = response.getWriter();
    try {
        
    } finally {            
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
    
    
    public void updateANCEDD(){
        try {
            dbConn conn= new dbConn();
            
            
            String getdata="select * from mother_details where delivery_date='' or delivery_date='null'";
            
            conn.rs=conn.st.executeQuery(getdata);
            
            while(conn.rs.next()){
            
                
                
               String getmoms="Select edd from maternal_details where motherid='"+conn.rs.getString("motherID")+"'";
               
               conn.rs1=conn.st1.executeQuery(getmoms);
               while(conn.rs1.next()){
               String eddqr="update mother_details set delivery_date='"+conn.rs1.getString(1) +"' where motherID='"+conn.rs.getString("motherID")+"'";
               
               System.out.println(eddqr);
               
               
               
               conn.st2.executeUpdate(eddqr);
               }
            
               //now call update postnatal EDD
               
               
               
            }//end of while loop
            
            
            updatePOSTNATEDD();
            
        } catch (SQLException ex) {
            Logger.getLogger(UpdateEddMotherDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
  
    
    ///update postnata edd
    
     public void updatePOSTNATEDD(){
        try {
            dbConn conn= new dbConn();
            
            
            String getdata="select * from mother_details ";
            
            conn.rs=conn.st.executeQuery(getdata);
            
            while(conn.rs.next()){
            
                
                
               String getmoms="Select DeliveryDate, VisitDate from postnat_gtom inner join postnat_atof on postnat_atof.motherid=postnat_gtom.motherid  where postnat_gtom.motherid='"+conn.rs.getString("motherID")+"'";
               
               conn.rs1=conn.st1.executeQuery(getmoms);
               while(conn.rs1.next()){
               String eddqr="update mother_details set delivery_date='"+conn.rs1.getString(1) +"' ,VisitDate='"+conn.rs1.getString(2)+"'  where motherID='"+conn.rs.getString("motherID")+"'";
               
               System.out.println(eddqr);
               
               
               
               conn.st2.executeUpdate(eddqr);
               }
            
            }
            
            
            //end of while loop
            
            UPDATEVISITDATESANC();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UpdateEddMotherDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
    }
   
    //update Visit dates
     
     public void UPDATEVISITDATESANC(){
        try {
            dbConn conn= new dbConn();
                           
                 String select= " select * from mother_details ";
                 
                 conn.rs=conn.st.executeQuery(select);
                 int count=0;
                 
                 while(conn.rs.next()){
                 
                     count++;
                     
                     System.out.println(count);
                     
                 String sl2="Select DateofVisit from atoh where motherid='"+conn.rs.getString("motherID") +"'";
                 conn.rs1=conn.st2.executeQuery(sl2);
                 
                 while(conn.rs1.next()){
                 
                     
                 String updqr="Update mother_details set VisitDate='"+conn.rs1.getString(1)+"' where motherID='"+conn.rs.getString("motherID")+"'";
                
                 
                 System.out.println(updqr);
                 
                 
                 
                 
                 conn.st3.executeUpdate(updqr);
                 
                 }
                 
                 }
                 
                 updateMaternityEDD();
                
                 
        } catch (SQLException ex) {
            Logger.getLogger(UpdateEddMotherDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
        
        
        
        
     
     }//end of function
     
     
     
     //===============================================MATERNITY REGISTER============================================
     //=============================================================================================================
      public void updateMaternityEDD(){
        try {
            dbConn conn= new dbConn();
            
            
            String getdata="select * from mother_details where delivery_date='' or delivery_date='null'";
            
            conn.rs=conn.st.executeQuery(getdata);
            
            while(conn.rs.next()){            
                
                
               String getmoms="Select DeliveryDate, AdmissionDate  from mat_atoh inner join mat_mtou on mat_atoh.motherid=mat_mtou.motherid  where mat_mtou.motherid='"+conn.rs.getString("motherID")+"'";
               
               conn.rs1=conn.st1.executeQuery(getmoms);
               while(conn.rs1.next()){
               String eddqr="update mother_details set delivery_date='"+conn.rs1.getString(1) +"' ,VisitDate='"+conn.rs1.getString(2)+"'  where motherID='"+conn.rs.getString("motherID")+"'";
               
               System.out.println("Maternity:::"+eddqr);
               
               
               
               conn.st2.executeUpdate(eddqr);
               }
            
            }
            
            
            //end of while loop
            
         
            updateFacilityNames(); 
            
        } catch (SQLException ex) {
            Logger.getLogger(UpdateEddMotherDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
    }
   
    
     
     
     //===========
     
     
     public  void updateFacilityNames(){
        try {
            mobiledbConn conn=new mobiledbConn();
           // dbConn conn = new dbConn();
                   
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
            Logger.getLogger(UpdateEddMotherDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       
         updatevisitdetails(); 
     
     } 
     
     
   public void updatevisitdetails(){
        try {
            dbConn conn= new dbConn();
            
            
            String qr="select motherid , count(*) as VISITS from atoh group by motherid order by DateofVisit ASC limit 10000";
          
                conn.rs=conn.st.executeQuery(qr);
                
                while(conn.rs.next()){
                
                    String updqr="select * from atoh where motherid='"+conn.rs.getString(1)+"'";
                    
                    int visitno=0;
                    
                    conn.rs1=conn.st1.executeQuery(updqr);
                    
                    while(conn.rs1.next()){
                    visitno++;
                    //UPDATE THE FIRST VISIT
                    String isfirstvisit="No";
                    if(visitno==1){isfirstvisit="Yes";}
                    
                    String update=" update atoh set visit_no='"+visitno+"', FirstVisit='"+isfirstvisit+"' where ancRegisterID='"+conn.rs1.getString(1) +"'";
                    
                    System.out.println(update);
                    
                    conn.st3.executeUpdate(update);
                    
                                          }
                
                
                
                                     }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateEddMotherDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   }  
     
    
}
