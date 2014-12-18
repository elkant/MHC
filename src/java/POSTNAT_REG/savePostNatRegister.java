/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package POSTNAT_REG;

import java.io.IOException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sendSMS.dbConnect;
import sender.dbConn;
import todbase.saveMaternityRegister;

/**
 *
 * @author Maureen
 */
public class savePostNatRegister extends HttpServlet {

    String admNo="";
      HttpSession session; 
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        
             session=request.getSession();
        
        dbConn conn= new dbConn();
        dbConnect conn1= new dbConnect();
        
        
        //========================AN ARRAYLIST TO STORE ALL THE UNIQUE PRIMARY KEYS==========
        //
       //check if a mother has been selected from  the view
        ArrayList uniqkey = new ArrayList();                                                //
        //                                                                                 //
        String allrows = request.getParameter("PNCRegister_newRows");                     //
        int allRows1 = Integer.parseInt(allrows);                                  //
                                                                                     //
        for (int c = 0; c < allRows1; c++) {                                               //
            //                                                                                 //
            String New_MotherID = request.getParameter("motherID");
            //get unique key from mother ids hidden field
            if (New_MotherID != null && !New_MotherID.equals("")) {

                uniqkey.add(New_MotherID);
            } else {
                //autogenerate random key afresh
                uniqkey.add(uniqueid().trim());
            }
            System.out.println(c + "___" + uniqueid());
                }                                                                          //
         //================================================================================== 
        
        
        
         Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String mdate;

                    Date mydate = new Date();
                    mdate = formatter.format(mydate);
               // timestamp='"+mdate+"',
         
        
        
//       admNo=request.getParameter("admNo"); 
  // begining of saving a to u ...........................................................................     
       
       
       

//            String admNo="";
            String p="";
             if (request.getParameter("admNo") != null && !request.getParameter("admNo").equals("")) {
                admNo = request.getParameter("admNo");
               
            }
             
             
        //============= start avbing a to f
             
               String PNCRegister_newRows="",PNCRegister_old_rows="";
       
       
       
       
       if(request.getParameter("PNCRegister_old_rows")!=null){
       PNCRegister_old_rows=request.getParameter("PNCRegister_old_rows");
       
       }
       
       if(request.getParameter("PNCRegister_newRows")!=null){
           
       PNCRegister_newRows=request.getParameter("PNCRegister_newRows");
       
       
       }
       
      // if(request.getParameter("admNo")!=null&&!request.getParameter("admNo").trim().equals("")){
       
           //====================insert new values u to z ==============================
           
          
      for(int a=1; a<=Integer.parseInt(PNCRegister_newRows);a++){
            try {
                
                String New_pnc="";
                
if (request.getParameter("New_PNCNo" + a) != null && !request.getParameter("New_PNCNo" + a).equals("")) {
New_pnc = request.getParameter("New_PNCNo" + a);
System.out.println(a+"___New_AdmissionNum" + New_pnc);
                                }
                
                String New_VisitDate=request.getParameter("New_VisitDate"+a);
                String New_PNCNo=request.getParameter("New_PNCNo"+a);
                String New_AdmNo=request.getParameter("New_AdmNo"+a);
                String New_FirstName=request.getParameter("New_FirstName"+a);
                String New_SecondName=request.getParameter("New_SecondName"+a);
                String New_LastName=request.getParameter("New_LastName"+a);
                String New_Village=request.getParameter("New_Village"+a);
                String New_Age=request.getParameter("New_Age"+a);
                String facility=request.getParameter("facility");
               
              
                if(!New_PNCNo.equals("")||!New_AdmNo.equals("") ){
                    
               conn.st.executeUpdate("insert into postnat_atof (VisitDate,PNCRegNo,AdmNo,FirstName,SecondName,LastName,Village,Age,motherid,pncno) "
                       + "values ('"+New_VisitDate+"','"+New_PNCNo +"','"+New_AdmNo+"','"+New_FirstName+"','"+New_SecondName+"','"+New_LastName+"','"+New_Village+"','"+New_Age+"','"+uniqkey.get(a-1) +"','"+New_pnc+"')");
           
               //insert into mother details if only the motherID hidden fieeld is null or blank
               
               if(request.getParameter("MotherID")==null){
               
               conn.st.executeUpdate("insert into mother_details (anc_no,FName,SName,LName,Age,motherid,MotherProfileID,facilityname) "
                       + "values ('','"+New_FirstName+"','"+New_SecondName+"','"+New_LastName+"','"+New_Age+"','"+uniqkey.get(a-1) +"','"+uniqkey.get(a-1) +"','"+facility+"')");
           
               }
                }
                
                }
            catch (SQLException ex) {
                Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
      
      //==============================updating u to z=========================
     
       
      for(int a=1; a<=Integer.parseInt(PNCRegister_old_rows);a++){
            try {
               String New_VisitDate=request.getParameter("VisitDate"+a);
                String New_PNCNo=request.getParameter("PNCNo"+a);
                String New_AdmNo=request.getParameter("AdmNo"+a);
                String New_FirstName=request.getParameter("FirstName"+a);
                String New_SecondName=request.getParameter("SecondName"+a);
                String New_LastName=request.getParameter("LastName"+a);
                String New_Village=request.getParameter("Village"+a);
                String New_Age=request.getParameter("Age"+a);
                String ID=request.getParameter("ID"+a);
                 String motherid=request.getParameter("MotherID");
              
                
                
                if(!New_PNCNo.equals("")||!New_AdmNo.equals("") ){
               
                
                    conn.st.executeUpdate("update postnat_atof set timestamp='"+mdate+"', VisitDate='"+New_VisitDate+"', PNCRegNo='"+New_PNCNo +"',AdmNo='"+New_AdmNo+"',FirstName='"+New_FirstName+"',SecondName='"+New_SecondName+"',LastName='"+New_LastName+"',Village='"+New_Village+"',Age='"+New_Age+"' where ID='"+ID+"'");
            conn.st.executeUpdate("update mother_details  set timestamp='"+mdate+"', anc_no='',FName='"+New_FirstName+"',SName='"+New_SecondName+"',LName='"+New_LastName+"',Age='"+New_Age+"' where motherid ='"+motherid+"' ");
                    
           
                
                }
                } catch (SQLException ex) {
                Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      
      } 
     //  }//end of if
       
       
       
       
       String GtoM="";
       String GtoM_old_rows="";
       
       
    if(request.getParameter("GtoM_old_rows")!=null){
       GtoM_old_rows=request.getParameter("GtoM_old_rows");
       
       }
       
       if(request.getParameter("GtoM")!=null){
           
       GtoM=request.getParameter("GtoM");
       
       
       }
       
      // if(request.getParameter("admNo")!=null&&!request.getParameter("admNo").trim().equals("")){
       
           //====================insert new values u to z ==============================
           
          
      for(int a=1; a<=Integer.parseInt(GtoM);a++){
            try {
                
                String New_pnc="";
                
if (request.getParameter("New_PNCNo" + a) != null && !request.getParameter("New_PNCNo" + a).equals("")) {
New_pnc = request.getParameter("New_PNCNo" + a);
System.out.println(a+"___New_AdmissionNum" + New_pnc);
                                }
                
                String New_DeliveryDate=request.getParameter("New_DeliveryDate"+a);
                String New_DeliveryPlace=request.getParameter("New_DeliveryPlace"+a);
                String New_DeliveryMode=request.getParameter("New_DeliveryMode"+a);
                String New_Temp=request.getParameter("New_Temp"+a);
                String New_BabyStatus=request.getParameter("New_BabyStatus"+a);
                String New_Pulse=request.getParameter("New_Pulse"+a);
                String New_BP=request.getParameter("New_BP"+a);
             
              
                if(!New_DeliveryDate.equals("")||!New_DeliveryPlace.equals("") ){
                    
               conn.st.executeUpdate("insert into postnat_gtom  (pncno,DeliveryDate,DeliveryPlace,DeliveryMode,Temp,BabyState,Pulse,BP,motherid ) "
                       + "values ('"+New_pnc+"','"+New_DeliveryDate+"','"+New_DeliveryPlace +"','"+New_DeliveryMode+"','"+New_Temp+"','"+New_BabyStatus+"','"+New_Pulse+"','"+New_BP+"','"+uniqkey.get(a-1) +"')");
           
                }
                
                }
            catch (SQLException ex) {
                Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
      
      //==============================updating u to z=========================
     
       
      for(int a=1; a<=Integer.parseInt(GtoM_old_rows);a++){
            try {
                String New_DeliveryDate=request.getParameter("New_DeliveryDate"+a);
                String New_DeliveryPlace=request.getParameter("New_DeliveryPlace"+a);
                String New_DeliveryMode=request.getParameter("New_DeliveryMode"+a);
                String New_Temp=request.getParameter("New_Temp"+a);
                String New_BabyStatus=request.getParameter("New_BabyStatus"+a);
                String New_Pulse=request.getParameter("New_Pulse"+a);
                String New_BP=request.getParameter("New_BP"+a);
              String ID=request.getParameter("ID"+a);
                
              
                if(!New_DeliveryDate.equals("")||!New_DeliveryPlace.equals("") ){
                    
               conn.st.executeUpdate("update postnat_gtom   set timestamp='"+mdate+"', DeliveryDate='"+New_DeliveryDate+"',DeliveryPlace='"+New_DeliveryPlace +"',DeliveryMode='"+New_DeliveryMode+"',Temp='"+New_Temp+"',BabyState='"+New_BabyStatus+"',Pulse='"+New_Pulse+"',BP='"+New_BP+"'WHERE ID='"+ID+"' ) ");
                      
                }
                } catch (SQLException ex) {
                Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      
      } 
     //  }//end of if     
       
       
       String NtoT="";
       String NtoT_old_rows="";
       
       
    if(request.getParameter("NtoT_old_rows")!=null){
       NtoT_old_rows=request.getParameter("NtoT_old_rows");
       
       }
       
       if(request.getParameter("NtoT")!=null){
           
       NtoT=request.getParameter("NtoT");
       
       
       }
       
      // if(request.getParameter("admNo")!=null&&!request.getParameter("admNo").trim().equals("")){
       
           //====================insert new values u to z ==============================
           
          
      for(int a=1; a<=Integer.parseInt(NtoT);a++){
            try {
                
                String New_pnc="";
                
if (request.getParameter("New_PNCNo" + a) != null && !request.getParameter("New_PNCNo" + a).equals("")) {
New_pnc = request.getParameter("New_PNCNo" + a);
System.out.println(a+"___New_AdmissionNum" + New_pnc);
                                }
                
                String New_Parlor=request.getParameter("New_Parlor"+a);
                String New_Breast=request.getParameter("New_Breast"+a);
                String New_Uterus=request.getParameter("New_Uterus"+a);
                String New_PPH=request.getParameter("New_PPH"+a);
                String New_CSection=request.getParameter("New_CSection"+a);
                String New_Lochial=request.getParameter("New_Lochial"+a);
                String New_Episiotomy=request.getParameter("New_Episiotomy"+a);
             
              
                if(!New_Parlor.equals("")||!New_Breast.equals("") ){
                    
               conn.st.executeUpdate("insert into postnat_ntot  (pncno,Parlor,Breast,Uterus,PPH,CSection,Lochial,Episiotomy,motherid ) "
                       + "values ('"+New_pnc+"','"+New_Parlor+"','"+New_Breast +"','"+New_Uterus+"','"+New_PPH+"','"+New_CSection+"','"+New_Lochial+"','"+New_Episiotomy+"','"+uniqkey.get(a-1) +"')");
           
                }
                
                }
            catch (SQLException ex) {
                Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
      
      //==============================updating u to z=========================
     
       
      for(int a=1; a<=Integer.parseInt(NtoT_old_rows);a++){
            try {
                 String New_Parlor=request.getParameter("New_Parlor"+a);
                String New_Breast=request.getParameter("New_Breast"+a);
                String New_Uterus=request.getParameter("New_Uterus"+a);
                String New_PPH=request.getParameter("New_PPH"+a);
                String New_CSection=request.getParameter("New_CSection"+a);
                String New_Lochial=request.getParameter("New_Lochial"+a);
                String New_Episiotomy=request.getParameter("New_Episiotomy"+a);
                String ID=request.getParameter("ID"+a);
              
              
                if(!New_Parlor.equals("")||!New_Breast.equals("") ){
                    
               conn.st.executeUpdate("update postnat_ntot  set timestamp='"+mdate+"', Parlor='"+New_Parlor+"',Breast='"+New_Breast +"',Uterus='"+New_Uterus+"',PPH='"+New_PPH+"',CSection='"+New_CSection+"',Lochial='"+New_Lochial+"',Episiotomy='"+New_Episiotomy+"'where ID='"+ ID+"' )");
           
                }
                } catch (SQLException ex) {
                Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      
      } 
     //  }//end of if     
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       // end of saving a to t......................................................................
       // begining of saving u to ai......................................................................
       String uz_newrows="",uz_oldrows="";
       
       
       
       
       if(request.getParameter("uz_oldrows")!=null){
       uz_oldrows=request.getParameter("uz_oldrows");
       
       }
       
       if(request.getParameter("uz_newrows")!=null){
           
       uz_newrows=request.getParameter("uz_newrows");
       
       
       }
       
      // if(request.getParameter("admNo")!=null&&!request.getParameter("admNo").trim().equals("")){
       
           //====================insert new values u to z ==============================
           
          
      for(int a=1; a<=Integer.parseInt(uz_newrows);a++){
            try {
                
                String New_adm_no="";
                
if (request.getParameter("New_PNCNo" + a) != null && !request.getParameter("New_PNCNo" + a).equals("")) {
New_adm_no = request.getParameter("New_PNCNo" + a);
System.out.println(a+"___New_AdmissionNum" + New_adm_no);
                                }
                
                String uz_new_pks="",uz_new_les72="",uz_new_gret72="",uz_new_nvptobaby="",new_ctxbaby="",new_ctxmother="";
                       
                if(request.getParameter("new_pks"+a)!=null){
                 uz_new_pks=request.getParameter("new_pks"+a);
                }
                
                if(request.getParameter("new_les72"+a)!=null){
                 uz_new_les72=request.getParameter("new_les72"+a);
                }
                
                if(request.getParameter("new_gret72"+a)!=null){
                 uz_new_gret72=request.getParameter("new_gret72"+a);
                }
                
                if(request.getParameter("new_nvptobaby"+a)!=null){
                 uz_new_nvptobaby=request.getParameter("new_nvptobaby"+a);
                }
                
                if(request.getParameter("new_ctxbaby"+a)!=null){
                 new_ctxbaby=request.getParameter("new_ctxbaby"+a);
                }
              
                
                if(request.getParameter("new_ctxmother"+a)!=null){
                 new_ctxmother=request.getParameter("new_ctxmother"+a);
                }
                
                if(!(uz_new_pks.equals("")&&uz_new_les72.equals("")&&uz_new_gret72.equals("")&&uz_new_nvptobaby.equals("")&&new_ctxbaby.equals("")&&new_ctxmother.equals("")) ){
                    
               conn.st.executeUpdate("insert into postnat_utoz  (pncno,Priorknownstatus,lessequals72,greaterthan72,nvptobaby,ctxbaby,ctxmother,motherid ) values ('"+New_adm_no+"','"+uz_new_pks +"','"+uz_new_les72+"','"+uz_new_gret72+"','"+uz_new_nvptobaby+"','"+new_ctxbaby+"','"+new_ctxmother+"','"+uniqkey.get(a-1) +"')");
           
                }
                
                }
            catch (SQLException ex) {
                Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
      
      //==============================updating u to z=========================
     
       
      for(int a=1; a<=Integer.parseInt(uz_oldrows);a++){
            try {
                String uz_new_pks=request.getParameter("old_pks"+a);
                String uz_new_les72=request.getParameter("old_les72"+a);
                String uz_new_gret72=request.getParameter("old_gret72"+a);
                String uz_new_nvptobaby=request.getParameter("old_nvptobaby"+a);
                String new_ctxbaby=request.getParameter("old_ctxbaby"+a);
                String tableid=request.getParameter("uztableid"+a);
                String new_ctxmother=request.getParameter("old_ctxmother"+a);
                if(!new_ctxmother.equals("")||!uz_new_pks.equals("") ){
               conn.st.executeUpdate("update postnat_utoz  set timestamp='"+mdate+"', Priorknownstatus='"+uz_new_pks +"',lessequals72='"+uz_new_les72+"',greaterthan72='"+uz_new_gret72+"',nvptobaby='"+uz_new_nvptobaby+"',ctxbaby='"+new_ctxbaby+"',ctxmother='"+new_ctxmother+"' where tableid='"+tableid+"'");
                }
                } catch (SQLException ex) {
                Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      
      } 
     //  }//end of if
       
       
       
//===================================   end of  u to z ========================================       
       
       
       
       
       

     String aaae_newrows="",aaae_oldrows="";
       
       
       
       
       if(request.getParameter("aaae_oldrows")!=null){
       aaae_oldrows=request.getParameter("aaae_oldrows");
       
       }
       
       if(request.getParameter("aaae_newrows")!=null){
           
       aaae_newrows=request.getParameter("aaae_newrows");
       
       
       }
       
      // if(request.getParameter("admNo")!=null&&!request.getParameter("admNo").trim().equals("")){
       
           //====================new values==============================
           
          
      for(int a=1; a<=Integer.parseInt(aaae_newrows);a++){
        
          
                 String New_adm_no="";
                
if (request.getParameter("New_PNCNo" + a) != null && !request.getParameter("New_PNCNo" + a).equals("")) {
New_adm_no = request.getParameter("New_PNCNo" + a);
System.out.println(a+"___New_AdmissionNum" + New_adm_no);
                                }
          
          
            try {
                String new_counselled="",new_pnc="",new_hivres="",new_meth="", new_res="",new_fp="";
                  
                
                if(request.getParameter("new_counselled"+a)!=null){
                 new_counselled=request.getParameter("new_counselled"+a);
                }
                
                if(request.getParameter("new_pnc"+a)!=null){
                 new_pnc=request.getParameter("new_pnc"+a);
                }
                
                
                if(request.getParameter("new_hivres"+a)!=null){
                 new_hivres=request.getParameter("new_hivres"+a);
                }
                
                if(request.getParameter("new_meth"+a)!=null){
                 new_meth=request.getParameter("new_meth"+a);
                }
                
                if(request.getParameter("new_res"+a)!=null){
                 new_res=request.getParameter("new_res"+a);
                }
                
                
                if(request.getParameter("new_fp"+a)!=null){
                 new_fp=request.getParameter("new_fp"+a);
                }
               
                if(!(new_counselled.equals("")&&new_pnc.equals("")&&new_res.equals("")&&new_fp.equals("")) ){
                    
               conn.st.executeUpdate("insert into postnat_aatoae (pncno,counselled,hivtested,hivresults,screenedmethod,screenedresult,modernfp,motherid ) values ('"+New_adm_no+"','"+new_counselled +"','"+new_pnc+"','"+new_hivres+"','"+new_meth+"','"+new_res+"','"+new_fp+"','"+uniqkey.get(a-1) +"')");
           
                }
                
                }
            catch (SQLException ex) {
                Logger.getLogger(savePostNatRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
      
      
     
       
      for(int a=1; a<=Integer.parseInt(aaae_oldrows);a++){
          
          
            try {
                   String new_counselled=request.getParameter("new_counselled"+a);
                String new_pnc=request.getParameter("old_pnc"+a);
                String new_hivres=request.getParameter("old_hivres"+a);
                String new_meth=request.getParameter("old_meth"+a);
                String new_res=request.getParameter("old_res"+a);
                String new_fp=request.getParameter("old_fp"+a);
                String tableid=request.getParameter("aaaetableid"+a);
                if(!new_counselled.equals("")||!new_pnc.equals("")||!new_res.equals("")||!new_fp.equals("") ){
                
               conn.st.executeUpdate("update postnat_aatoae SET timestamp='"+mdate+"', counselled='"+new_counselled+"',hivtested='"+new_pnc+"',hivresults='"+new_hivres+"',screenedmethod='"+new_meth+"',screenedresult='"+new_res+"', modernfp='"+new_fp+"'  where tableid='"+tableid+"' ");
           
            }} catch (SQLException ex) {
                Logger.getLogger(savePostNatRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      
      } 
      // }//end of if       
       
       
/////////////////////////////////////////////////////////////////////////////////////Af TO Ai /////////////////
       
        String afai_newrows="",afai_oldrows="";
       
       if(request.getParameter("afai_oldrows")!=null){
       afai_oldrows=request.getParameter("afai_oldrows");
       
       }
       
       if(request.getParameter("afai_newrows")!=null){
           
       afai_newrows=request.getParameter("afai_newrows");
       
       
       }
       
       System.out.println(""+afai_oldrows);
       
       
       //if(request.getParameter("admNo")!=null&&!request.getParameter("admNo").trim().equals("")){
       
           //====================new values==============================
           
          
      for(int c=1; c<=Integer.parseInt(afai_newrows);c++){
          
          
           String New_adm_no="";
                
if (request.getParameter("New_PNCNo" + c) != null && !request.getParameter("New_PNCNo" + c).equals("")) {
New_adm_no = request.getParameter("New_PNCNo" + c);
System.out.println(c+"___New_AdmissionNum" + New_adm_no);
                                }
          
          
          
            try {
                String new_multivitamin="";
                String new_haematinics="",afai_new_ref="",new_remarks="";
                if(request.getParameter("new_multivitamin"+c)!=null){
                new_multivitamin=request.getParameter("new_multivitamin"+c);
                }
                
                if(request.getParameter("new_haematinics"+c)!=null){
                 new_haematinics=request.getParameter("new_haematinics"+c);
                }
                
                if(request.getParameter("new_ref"+c)!=null){
                 afai_new_ref=request.getParameter("new_ref"+c);
                }
                
                if(request.getParameter("new_remarks"+c)!=null){
                 new_remarks=request.getParameter("new_remarks"+c);
                }
              
                if((!new_multivitamin.trim().equals("")&&new_multivitamin!=null)||(!new_haematinics.trim().equals("")&&new_haematinics!=null)|| (!afai_new_ref.trim().equals("")&&afai_new_ref!=null)||(!new_remarks.trim().equals("")&&new_remarks!=null)){
                    
               conn.st.executeUpdate("insert into postnat_aftoai  (pncno,multivitamin,haematinics,referred,remarks,motherid ) values ('"+New_adm_no+"','"+new_multivitamin +"','"+new_haematinics+"','"+afai_new_ref+"','"+new_remarks+"','"+uniqkey.get(c-1) +"')");
           
               
               System.out.println("================================INSERTED POSTNATAL RECORDS BY MISTAKE");
               
                }
                
                }
            catch (SQLException ex) {
                Logger.getLogger(savePostNatRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
      
      
     //==================================oldrows=================================
       
      if(!afai_oldrows.equals("0")){
       for(int c=1; c<=Integer.parseInt(afai_oldrows);c++){
            try {
                 String new_multivitamin=request.getParameter("old_multivitamin"+c);
                String new_haematinics=request.getParameter("old_haematinics"+c);
                String afai_new_ref=request.getParameter("old_ref"+c);
                String new_remarks=request.getParameter("old_remarks"+c);
               String tableid=request.getParameter("afaitableid"+c);
                if(!new_multivitamin.equals("")||!new_haematinics.equals("") ||!afai_new_ref.equals("")||!new_remarks.equals("")){
                    
               conn.st.executeUpdate("update postnat_aftoai  set timestamp='"+mdate+"',  multivitamin='"+new_multivitamin +"',haematinics='"+new_haematinics+"' ,referred='"+afai_new_ref+"',remarks='"+new_remarks+"' where tableid='"+tableid+"'");
           
                }
                
                }
            catch (SQLException ex) {
                Logger.getLogger(savePostNatRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
      } 
       }
      
      
       //}//end of if       

       
       
       
        
        session.setAttribute("mother_edited", "<font color=\"green\">mother details edited succesfully!</font>");
        
      response.sendRedirect("viewallmothers");  
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
        } catch (SQLException ex) {
            Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(saveMaternityRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    
    
    
    
    //====================random id functions================================ 

 public String uniqueid() {

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        int milsec=cal.get(Calendar.MILLISECOND);
        
        
        return year+""+month+""+date+hour+min+sec+milsec+generateRandomNumber(800, 9000);
    }

 
 
   public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }
 
//==========================================================================
    
    
    
    
    
    
    
    
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
