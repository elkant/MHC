/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DQA;

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
import sender.dbConn;

/**
 *
 * @author MANUEL
 */
public class joinmatchinganc extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
        
        try {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    //get a way of matching 
    
    dbConn conn= new dbConn();
    
   ArrayList id=new ArrayList();
    
   String qr="select mother_details.motherID as motherID,anc_no,FName,SName,LName,facilityname, mat_atoh.motherid as motherid2 ,AdmissionNo,postnat_atof.motherid as motherid3, PNCRegNo  from mother_details left join mat_atoh on mat_atoh.motherid=mother_details.motherID left join postnat_atof on  postnat_atof.motherid=mother_details.motherID where (anc_no!=''&& anc_no !='NO ANC NO' && anc_no !='No') group by mother_details.motherID";
   
   conn.rs=conn.st.executeQuery(qr);
     String motherID="";
     String ancnumber="";
     String facil="";
     String fname="";
     String lname="";
     String sname="";
   int cnt=0;
   while(conn.rs.next()){
   //now in this loop , find the matching record and delete the necessary , then edit the rest
          
            motherID=conn.rs.getString("motherID");
            ancnumber=conn.rs.getString("anc_no");
            facil=conn.rs.getString("facilityname");
            fname=conn.rs.getString("FName");
            lname=conn.rs.getString("LName");
            sname=conn.rs.getString("SName");
       
   String allqry=" select mother_details.motherID as motherID,anc_no,FName,SName,LName,facilityname from mother_details  where  facilityname='"+facil+"' and  ( FName like '%"+fname+"%' and LName like '%"+lname+"%' and SName like '%"+sname+"%'  ) and motherID!='"+motherID+"'  ";
                
     conn.rs2=conn.st2.executeQuery(allqry);
   
     while(conn.rs2.next()){
           //dont use qr for now
         //delete from mother_details where  motherID ='"+conn.rs2.getString("motherID")+"'
             String qr1[]={"delete from mother_details where  motherID ='" + conn.rs2.getString("motherID") + "'",
             "update atoh set motherid='" + motherID + "' ,ancno='"+ancnumber+"'   where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update itop set motherid='" + motherID + "' ,ancno='"+ancnumber+"' where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update qtow set motherid='" + motherID + "' ,ancno='"+ancnumber+"' where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update xtoad set motherid='" + motherID + "' ,ancno='"+ancnumber+"' where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update aetoak set motherid='" + motherID + "' ,ancno='"+ancnumber+"' where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update altoan set motherid='" + motherID + "' ,ancno='"+ancnumber+"' where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update anc_visits set motherid='" + motherID + "',ancNo='"+ancnumber+"'  where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update maternal_details set motherid='" + motherID + "',ancno='"+ancnumber+"'  where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update mat_actoai set motherid='" + motherID + "'  where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update mat_ajtoan set motherid='" + motherID + "'  where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update mat_atoh set motherid='" + motherID + "'  where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update mat_htol set motherid='" + motherID + "'  where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update mat_mtou set motherid='" + motherID + "'  where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update mat_vtoab set motherid='" + motherID + "'  where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update postnat_aatoae set motherid='" + motherID + "'  where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update postnat_aftoai set motherid='" + motherID + "'  where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update postnat_atof set motherid='" + motherID + "'  where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update postnat_gtom set motherid='" + motherID + "'  where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update postnat_ntot set motherid='" + motherID + "'  where  motherid ='" + conn.rs2.getString("motherID") + "'",
             "update postnat_utoz set motherid='" + motherID + "'  where  motherid ='" + conn.rs2.getString("motherID") + "'"};
  
            //count the mother id with many visits and use that one as the main id
           
 String  count="select count(motherid) from atoh  where motherid='"+conn.rs2.getString("motherID")+"' ";
 String count2="select count(motherid) from atoh where motherid='"+motherID+"'  ";
 
 int cou1=0;//no of instances of the inner loop
 int cou2=0;//no if instances of the outer loop
   conn.rs3=conn.st3.executeQuery(count);
   conn.rs4=conn.st4.executeQuery(count2);
   if(conn.rs3.next()){
       
   cou1=conn.rs3.getInt(1);
   
   }
   if(conn.rs4.next()){
   cou2=conn.rs4.getInt(1);   
   }
   //if the outer loop instances is greater than the inner loop in anc reg 
   //if(cou2>cou1){
   if(!id.contains(motherID)){
   cnt++;
   
       for(int a=0;a<qr1.length;a++){
       conn.st_5.executeUpdate(qr1[a]);
       System.out.println(" "+qr1[a]);
                                     }       
       System.out.println("____"+cnt);
       id.add(conn.rs2.getString("motherID"));
   }
       
   //}
   
   }
   
   
       
   
   }
    
    
    try {
        
        
        
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(joinmatchinganc.class.getName()).log(Level.SEVERE, null, ex);
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
