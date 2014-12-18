/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package REPORTS;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.csvreader.CsvWriter;
import com.mysql.jdbc.ResultSetMetaData;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import sender.dbConn;
/**
 *
 * @author Manuel
 */
public class MaternityRawData extends HttpServlet {

    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
                                Date dat= new Date();         
         String date1=dat.toString().replace(" ","_");
         date1=date1.replace(":","_");
            
             
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%		
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%		
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
             
             
              
              HSSFWorkbook wb = new HSSFWorkbook();
              HSSFSheet ancsheet = wb.createSheet("Anc Reg (a to w ) ");
              HSSFSheet ancsheet2 = wb.createSheet("Anc Reg (x to an) ");
              HSSFSheet matsheet = wb.createSheet("Maternity Reg. ");
              HSSFSheet pncsheet = wb.createSheet("Postanatal Reg. ");
              
              
              
             
             
             
              //%%%%%%%%%%%%%%%%HEADER FONTS AND COLORATION%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                HSSFFont font_header = wb.createFont();
                font_header.setFontHeightInPoints((short) 9);
                font_header.setFontName("Adobe Garamond Pro Bold");



                //    font.setItalic(true);
                font_header.setBoldweight((short) 03);
                font_header.setColor(HSSFColor.BLACK.index);

                //font data
                HSSFFont datafont = wb.createFont();
                datafont.setBoldweight((short) 03);
              
                datafont.setFontHeightInPoints((short) 10);
                datafont.setFontName("Cambria");


                //==============HEADER STYLE==================

                CellStyle style_header = wb.createCellStyle();
                style_header.setFont(font_header);
                style_header.setWrapText(true);
                style_header.setAlignment(style_header.ALIGN_CENTER_SELECTION);
                style_header.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
                style_header.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


                //========TABLE HEADER STYLING===========================
                CellStyle th_style = wb.createCellStyle();
                th_style.setFont(datafont);
                th_style.setWrapText(true);
                th_style.setAlignment(th_style.ALIGN_CENTER);
               th_style.setFillForegroundColor(HSSFColor.LAVENDER.index);
                th_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                th_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                th_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
                th_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                th_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);


                //=======DATA STYLING=========================== 

                CellStyle data_style = wb.createCellStyle();
                data_style.setFont(datafont);
                data_style.setWrapText(true);
                data_style.setAlignment(data_style.ALIGN_CENTER);
                data_style.setFillForegroundColor(HSSFColor.WHITE.index);
                data_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                data_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                data_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
                data_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                data_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);




                //=======INNER DATA STYLING=========================== 

                CellStyle innerdata_style = wb.createCellStyle();
                innerdata_style.setFont(datafont);
                innerdata_style.setWrapText(true);
                innerdata_style.setAlignment(data_style.ALIGN_CENTER);
            
                innerdata_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                innerdata_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                innerdata_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
                innerdata_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                innerdata_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                
                
                HSSFRow matcolumnheader = matsheet.createRow(0);
                matcolumnheader.setHeightInPoints(22);
                
                
                 HSSFRow pnccolumnheader = pncsheet.createRow(0);
                 pnccolumnheader.setHeightInPoints(22);
                
                 
                 
                           
                HSSFRow anccolumnheader =ancsheet.createRow(0);
                anccolumnheader.setHeightInPoints(22);
               
                
                
                 HSSFRow anc1columnheader =ancsheet2.createRow(0);
                anc1columnheader.setHeightInPoints(22);
                
                 HSSFCell rwcolheader=null;
                
              
                
              
            
            
                 HSSFRow rw2 = null;

           

            int rowno=0;
            
             
             
            
              
              
              
              
              
              
		// before we open the file check to see if it already exists
		
                
                try {
                    
               
			
                String maternityqry="SELECT mat_atoh.motherid as UniqKey, mat_atoh.AdmissionNo as AdmissionNo,AdmissionDate,AncVisits,FirstName,"
+" SecondName ,LastName,Village,Age,MaritalStatus"
+"  ,Parity ,Gravidae, LMP, EDD,Diagnosis ,LabourDuration,DeliveryDate,DeliveryTime,"
+" GestationAtBirth,DeliveryMode,PlacentaComplete,BloodLoss,ConditionAfterDelivery,"
+" DeliveryComplications, Sex as BabySex,birth_weight,live_Birth as BirthType,"
+" apgar_score,vdrl_rpr_results,hiv_anc,hiv_maternity,arv_anc,arv_maternity,arv_tobaby,"
+" ctx_tomother,vitamin_a,partner_tested,partner_results,delivery_by,birth_no,"
+" discharge_date,baby_status,comments"
+" FROM mastermhc.mat_atoh "
+" join mat_htol on mat_atoh.motherid=mat_htol.motherid"
+" join mat_mtou on mat_atoh.motherid=mat_mtou.motherid" 
+" join mat_vtoab on mat_atoh.motherid=mat_vtoab.motherid"
+" join mat_actoai on mat_atoh.motherid=mat_actoai.motherid"
+" join mat_ajtoan on mat_atoh.motherid=mat_ajtoan.motherid group by mat_atoh.MatRegisterId ;";
          
                //get headers query
                
              String headerqry="SELECT mat_atoh.motherid as UniqKey, mat_atoh.AdmissionNo as AdmissionNo,AdmissionDate,AncVisits,FirstName,"
+" SecondName ,LastName,Village,Age,MaritalStatus"
+"  ,Parity ,Gravidae, LMP, EDD,Diagnosis ,LabourDuration,DeliveryDate,DeliveryTime,"
+" GestationAtBirth,DeliveryMode,PlacentaComplete,BloodLoss,ConditionAfterDelivery,"
+" DeliveryComplications, Sex as BabySex,birth_weight,live_Birth as BirthType,"
+" apgar_score,vdrl_rpr_results,hiv_anc,hiv_maternity,arv_anc,arv_maternity,arv_tobaby,"
+" ctx_tomother,vitamin_a,partner_tested,partner_results,delivery_by,birth_no,"
+" discharge_date,baby_status,comments"
+" FROM mastermhc.mat_atoh "
+" join mat_htol on mat_atoh.motherid=mat_htol.motherid"
+" join mat_mtou on mat_atoh.motherid=mat_mtou.motherid" 
+" join mat_vtoab on mat_atoh.motherid=mat_vtoab.motherid"
+" join mat_actoai on mat_atoh.motherid=mat_actoai.motherid"
+" join mat_ajtoan on mat_atoh.motherid=mat_ajtoan.motherid  limit 1 ;";   
      
              dbConn conn= new dbConn();
              conn.rs=conn.st.executeQuery(headerqry);
              int numberOfColumns=0;
                
                
               
                
              if(conn.rs.next()){
			// if the file didn't already exist then we need to write out the header line
    java.sql.ResultSetMetaData rsMetaData = conn.rs.getMetaData();
  numberOfColumns= rsMetaData.getColumnCount();

    // get the column names; column indexes start from 1
    for (int i = 1; i < numberOfColumns+1 ; i++) {
        matsheet.setColumnWidth(i-1, 4000);
       String columnName = rsMetaData.getColumnName(i);
      if (1==1)
			{
rwcolheader = matcolumnheader.createCell(i-1);
                 
                  rwcolheader.setCellValue(columnName);
                  rwcolheader.setCellStyle(th_style);
				
			}
      
    
                  
			
              }
      }
    
               HSSFRow matrow = null;
              
        // else assume that the file already has the correct header line
			
               conn.rs1=conn.st1.executeQuery(maternityqry);
               while(conn.rs1.next()){
                   rowno++;
                       
                  matrow = matsheet.createRow(rowno);
                matrow.setHeightInPoints(22);
              for(int b=1;b<=numberOfColumns;b++){
              
              
                  
              HSSFCell cl = matrow.createCell(b-1);
                 
               cl.setCellValue(conn.rs1.getString(b));
               cl.setCellStyle(data_style);   
                  
               //csvOutput.write(conn.rs1.getString(b));
               
               
               
                   }
                   
             
              // csvOutput.endRecord();
               }
               
               
			// write out a few records
			
			
               //=========================================================
               //=========================================================
               //=========================================================
               
               
               
               
               
               
String pncqr="SELECT postnat_atof.ID as Tableid,VisitDate,PNCRegNo,AdmNo,FirstName,SecondName,LastName,Village,"
+" Age,postnat_atof.motherid as MotherID,DeliveryDate, UPPER(DeliveryPlace) as DELIVERYPLACE," 
+" DeliveryMode,BabyState,Temp,Temp,Pulse,BP, Parlor,Breast,Uterus,PPH,Csection,"
+" Lochial,Episiotomy,priorknownstatus,lessequals72,greaterthan72,"
+" nvptobaby,ctxbaby,ctxmother,counselled,hivtested,hivresults,screenedmethod,"
+" screenedresult,modernfp,multivitamin,haematinics,referred,remarks "
+" FROM  postnat_atof "
+" join postnat_gtom on postnat_atof.motherid=postnat_gtom.motherid"
+" join postnat_ntot on postnat_atof.motherid=postnat_ntot.motherid"
+" join postnat_utoz on postnat_atof.motherid=postnat_utoz.motherid"
+" join postnat_aatoae on postnat_atof.motherid=postnat_aatoae.motherid"
+" join postnat_aftoai on postnat_atof.motherid=postnat_aftoai.motherid group by Tableid limit 1"
;
               
                 
String pncmainqr="SELECT postnat_atof.ID as Tableid,VisitDate,PNCRegNo,AdmNo,FirstName,SecondName,LastName,Village,"
+" Age,postnat_atof.motherid as MotherID,DeliveryDate, UPPER(DeliveryPlace) as DELIVERYPLACE," 
+" DeliveryMode,BabyState,Temp,Temp,Pulse,BP, Parlor,Breast,Uterus,PPH,Csection,"
+" Lochial,Episiotomy,priorknownstatus,lessequals72,greaterthan72,"
+" nvptobaby,ctxbaby,ctxmother,counselled,hivtested,hivresults,screenedmethod,"
+" screenedresult,modernfp,multivitamin,haematinics,referred,remarks "
+" FROM  postnat_atof "
+" join postnat_gtom on postnat_atof.motherid=postnat_gtom.motherid"
+" join postnat_ntot on postnat_atof.motherid=postnat_ntot.motherid"
+" join postnat_utoz on postnat_atof.motherid=postnat_utoz.motherid"
+" join postnat_aatoae on postnat_atof.motherid=postnat_aatoae.motherid"
+" join postnat_aftoai on postnat_atof.motherid=postnat_aftoai.motherid group by Tableid "
;             
               
    

               
//=========================================================
//=========================================================
//=========================================================

//create the postnat header


     conn.rs=conn.st.executeQuery(pncqr);
              int numberOfpncColumns=0;
                
                
               
                
              if(conn.rs.next()){
			// if the file didn't already exist then we need to write out the header line
    java.sql.ResultSetMetaData rsMetaData = conn.rs.getMetaData();
  numberOfpncColumns= rsMetaData.getColumnCount();

    // get the column names; column indexes start from 1
    for (int i = 1; i < numberOfpncColumns+1 ; i++) {
        pncsheet.setColumnWidth(i-1, 4000);
       String columnName = rsMetaData.getColumnName(i);
      if (1==1)
			{
               rwcolheader = pnccolumnheader.createCell(i-1);                 
               rwcolheader.setCellValue(columnName);
               rwcolheader.setCellStyle(th_style);
				
			}
      
    
                  
			
              }
      }
///================================end of postnatal header========================================
int rowcount=0;

               HSSFRow pncrow = null;
              
        // else assume that the file already has the correct header line
			
               conn.rs1=conn.st1.executeQuery(pncmainqr);
               while(conn.rs1.next()){
                   rowcount++;
                       
                  pncrow = pncsheet.createRow(rowcount);
                pncrow.setHeightInPoints(22);
              for(int b=1;b<=numberOfpncColumns;b++){
              
              
                  
              HSSFCell cl = pncrow.createCell(b-1);
                 
               cl.setCellValue(conn.rs1.getString(b));
               cl.setCellStyle(data_style);   
                  
               //csvOutput.write(conn.rs1.getString(b));
               
               
               
                   }
                   
             
              // csvOutput.endRecord();
               }
              
              
              
              
              
   //=====================================================Maternity register====================           
			
	
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
               
 //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
 //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
 //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
 String ancheader="  SELECT atoh.motherid as MOTHERID, DOB,FNAME,SNAME,LNAME,PhoneNo,NOKPhoneNo,VisitDate,"
+" delivery_date as EDD,Age,Gravida,parity,UPPER(facilityname) as Facility,"
+" atoh.ancno as Anc_no,DateofVisit,FirstVisit,visit_no,Village,Gestation,Weight,"
+" BloodPressure,CounselledOn,qtow.Haemoglobin as Haemoglobin,RPR,HIVinit,HIVRetest,"
+" WHOStage,ARTCD4,ARTStart,CTX,MotherNVP,MotherAZT, MotherHAART,BabyNVP,TBScreen,"
+" Cc_methods,cc_results,ConditionID,others_section_6,deworming,ipt,ttdose,iron,"
+" folic,itn,PartnerResults,AdditionalTreatment,others_section_7,reffered"
+" FROM mother_details "
+" join (atoh  "
+" join itop on atoh.motherid=itop.motherid"
+" join qtow on atoh.motherid=qtow.motherid"
+" join xtoad on atoh.motherid=xtoad.motherid"
+" join aetoak on atoh.motherid=aetoak.motherid"
+" join altoan on atoh.motherid=altoan.motherid) "
+" on mother_details.motherprofileid=atoh.motherid"
+" limit 1";
               
               
         String ancheader1=" SELECT atoh.ancRegisterID,atoh.motherid as MOTHERID, DOB,FNAME,SNAME,LNAME,PhoneNo,NOKPhoneNo,VisitDate,"
+" delivery_date as EDD,Age,Gravida,parity,UPPER(facilityname) as Facility,"
+" atoh.ancno as Anc_no,DateofVisit,FirstVisit,visit_no,Village,Gestation,Weight,"
+" BloodPressure,CounselledOn,qtow.Haemoglobin as Haemoglobin,RPR,HIVinit,HIVRetest,"
+" WHOStage,ARTCD4,ARTStart"
+" FROM mother_details "
+" join (atoh  "
+" left join itop on atoh.motherid=itop.motherid"
+" left join qtow on atoh.motherid=qtow.motherid"
+" ) "
+" on mother_details.motherprofileid=atoh.motherid"

+" limit 1";        
               
               
               
               
               
               
               
      String anc1qr=" SELECT atoh.ancRegisterID,atoh.motherid as MOTHERID, DOB,FNAME,SNAME,LNAME,PhoneNo,NOKPhoneNo,VisitDate,"
+" delivery_date as EDD,Age,Gravida,parity,UPPER(facilityname) as Facility,"
+" atoh.ancno as Anc_no,DateofVisit,FirstVisit,visit_no,Village,Gestation,Weight,"
+" BloodPressure,CounselledOn,qtow.Haemoglobin as Haemoglobin,RPR,HIVinit,HIVRetest,"
+" WHOStage,ARTCD4,ARTStart"
+" FROM mother_details "
+" join (atoh  "
+" left join itop on atoh.motherid=itop.motherid"
+" left join qtow on atoh.motherid=qtow.motherid"
+" ) "
+" on mother_details.motherprofileid=atoh.motherid"

+" group by atoh.ancRegisterID;";         
               
   
      
      
      
      
         String ancheader2=" SELECT atoh.ancRegisterID, atoh.motherid as MOTHERID,CTX,MotherNVP,MotherAZT, MotherHAART,BabyNVP,TBScreen,"
+" Cc_methods,cc_results,ConditionID,others_section_6,deworming,ipt,ttdose,iron,"
+" folic,itn,PartnerResults,AdditionalTreatment,others_section_7,reffered"
+" FROM mother_details "
+" join (atoh  "

+" left join xtoad on atoh.motherid=xtoad.motherid"
+" left join aetoak on atoh.motherid=aetoak.motherid"
+" left join altoan on atoh.motherid=altoan.motherid) "
+" on mother_details.motherprofileid=atoh.motherid"

+" limit 1"; 
     
   String anc2qr=" SELECT atoh.ancRegisterID, atoh.motherid as MOTHERID,CTX,MotherNVP,MotherAZT, MotherHAART,BabyNVP,TBScreen,"
+" Cc_methods,cc_results,ConditionID,others_section_6,deworming,ipt,ttdose,iron,"
+" folic,itn,PartnerResults,AdditionalTreatment,others_section_7,reffered"
+" FROM mother_details "
+" join (atoh  "

+" left join xtoad on atoh.motherid=xtoad.motherid"
+" left join aetoak on atoh.motherid=aetoak.motherid"
+" left join altoan on atoh.motherid=altoan.motherid) "
+" on mother_details.motherprofileid=atoh.motherid"

+" group by atoh.ancRegisterID";   
      
      
      
      
      
      
      
      conn.rs=conn.st.executeQuery(ancheader1);
              int numberOfColumnsanc=0;
                
                
               
                
              if(conn.rs.next()){
			// if the file didn't already exist then we need to write out the header line
    java.sql.ResultSetMetaData rsMetaData = conn.rs.getMetaData();
  numberOfColumnsanc= rsMetaData.getColumnCount();

    // get the column names; column indexes start from 1
    for (int i = 1; i < numberOfColumnsanc+1 ; i++) {
        ancsheet.setColumnWidth(i-1, 4000);
       String columnName = rsMetaData.getColumnName(i);
      if (1==1)
			{
rwcolheader = anccolumnheader.createCell(i-1);                 
rwcolheader.setCellValue(columnName);
rwcolheader.setCellStyle(th_style); 
				
			}
      
    
                  
			
              }
      }
    
 
              
 //========================================             
      
      
      
      
      
      int rowcount1=0;

               HSSFRow ancrow = null;
              
        // else assume that the file already has the correct header line
			
               conn.rs1=conn.st1.executeQuery(anc1qr);
               while(conn.rs1.next()){
                   rowcount1++;
                       
                  ancrow = ancsheet.createRow(rowcount1);
                ancrow.setHeightInPoints(22);
              for(int b=1;b<=numberOfColumnsanc;b++){
              
              
                  
              HSSFCell cl = ancrow.createCell(b-1);
                 
               cl.setCellValue(conn.rs1.getString(b));
               cl.setCellStyle(data_style);   
                  
               //csvOutput.write(conn.rs1.getString(b));
               
               
               
                   }
                   
             
              // csvOutput.endRecord();
               }
      
      
      
      
      
      
               
 //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
 //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

               
               
               

               
               
               
                conn.rs=conn.st.executeQuery(ancheader2);
              int numberOfColumnsanc2=0;
                
                
               
                
              if(conn.rs.next()){
			// if the file didn't already exist then we need to write out the header line
    java.sql.ResultSetMetaData rsMetaData = conn.rs.getMetaData();
  numberOfColumnsanc2= rsMetaData.getColumnCount();

    // get the column names; column indexes start from 1
    for (int i = 1; i < numberOfColumnsanc2+1 ; i++) {
        ancsheet2.setColumnWidth(i-1, 4000);
       String columnName = rsMetaData.getColumnName(i);
      if (1==1)
			{
rwcolheader = anc1columnheader.createCell(i-1);                 
rwcolheader.setCellValue(columnName);
rwcolheader.setCellStyle(th_style); 
				
			}
      
    
                  
			
              }
      }
    
 
              
 //========================================             
      
      
      
      
      
      int rowcount2=0;

               HSSFRow ancrow2 = null;
              
        // else assume that the file already has the correct header line
			
               conn.rs1=conn.st1.executeQuery(anc2qr);
               while(conn.rs1.next()){
                   rowcount2++;
                       
                  ancrow2 = ancsheet2.createRow(rowcount2);
                ancrow2.setHeightInPoints(22);
              for(int b=1;b<=numberOfColumnsanc2;b++){
              
              
                  
              HSSFCell cl = ancrow2.createCell(b-1);
                 
               cl.setCellValue(conn.rs1.getString(b));
               cl.setCellStyle(data_style);   
                  
               //csvOutput.write(conn.rs1.getString(b));
               
               
               
                   }
                   
             
              // csvOutput.endRecord();
               }
      
      
     
               
               
               
               
               
               
               
               
               
               
//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
               
               
               
               
               
			} catch (SQLException ex) {
            Logger.getLogger(MaternityRawData.class.getName()).log(Level.SEVERE, null, ex);
        } 
                        
                        Date dat1= new Date();         
         String date2=dat1.toString().replace(" ","_"); 
         ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
//        
    byte [] outArray = outByteStream.toByteArray();
    response.setContentType("application/ms-excel");
    response.setContentLength(outArray.length);
    response.setHeader("Expires:", "0"); // eliminates browser caching
    response.setHeader("Content-Disposition", "attachment; filename=Maternity_raw_Data_"+date2+".xls");
    OutputStream outStream = response.getOutputStream();
    outStream.write(outArray);
    outStream.flush();
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
