/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package REPORTS;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sender.dbConn;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import sender.mobiledbConn;

/**
 *
 * @author Manuel
 */
public class MOBILEREGISTER extends HttpServlet {

    HSSFWorkbook wb;
    int maxmerging=0;
    String maincountqry="";
   String Year="";
   
   String additiaonalparrams="EXTRACT(YEAR FROM VisitDate) ='"+Year+"' ";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Year="";
            additiaonalparrams=" EXTRACT(YEAR FROM VisitDate) ='"+Year+"' ";
           
            
            if(Year.equals("")){additiaonalparrams="";}
            
            wb = new HSSFWorkbook();
             String columnheaders[]={"ANC NUMBER","FIRST NAME","MIDDLE NAME","LAST NAME","PHONE NO.","NOK PHONE NO.","EXPECTED DELIVERY DATE","LAST VISIT DATE","CURRENT FACILITY","PREFFERED FACILITY","FACILITY VISITING","CHW'S NAME","CHWS PHONE NO."};
            
            
             String anccolumnheaders="ANC VISITS";
             String matcolumnheaders="MATERNITY REGISTER DETAILS";
             String babycolumnheaders="BABY STATUS";
             String deliverycolumnheaders="BIRTH TYPE";
             String compcolumnheaders="DELIVERY COMPLICATION";
             String registerdetailsheaders2[]={"MOTHER DETAILS","","","","","","",anccolumnheaders,"","","","",deliverycolumnheaders,"","",babycolumnheaders,"",compcolumnheaders};
             
             String registerdetailsheaders3[]={"ANC NUMBER","FIRST NAME","MIDDLE NAME","LAST NAME","EDD","FACILITY","VILLAGE","1st VISIT","2nd VISIT","3rd VISIT","4th VISIT","5th AND ABOVE","LIVE BIRTH","FRESH STILL BIRTH","MACERATED STILL BIRTH","ALIVE","DEAD","COMPLICATION TYPE"};
             
             
           maxmerging=columnheaders.length;
           
           maincountqry="select count(*) as curcount from mother_details where "+additiaonalparrams+"  facilityname LIKE ";
           
            String Statisticsarr[] = {"FACILITY", "PREGNANT MOTHERS", "DELIVERED MOTHERS", "TOTAL"};


            
            //=====================================



            //==============================CREATE WORKBOOK AND SHEETS FOR EACH SITE  



            //HSSFSheet statistics = wb.createSheet("STATISTICS");
            HSSFSheet allsites = wb.createSheet("ALL ENROLLED MOTHERS");
            HSSFSheet registerdetails = wb.createSheet("MOTHERS REGISTER DETAILS");
            HSSFSheet missingmothers = wb.createSheet("MISSING IN REGISTERS");

         


            //%%%%%%%%%%%%%%%%HEADER FONTS AND COLORATION%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            HSSFFont font_header = wb.createFont();
            font_header.setFontHeightInPoints((short) 10);
            font_header.setFontName("Eras Bold ITC");



            //    font.setItalic(true);
            font_header.setBoldweight((short) 03);
            font_header.setColor(HSSFColor.BLACK.index);

            //font data
            HSSFFont datafont = wb.createFont();
            datafont.setBoldweight((short) 03);
            datafont.setColor(HSSFColor.BLACK.index);
            datafont.setFontHeightInPoints((short) 10);
            datafont.setFontName("Cambria");


            //==============HEADER STYLE==================

            CellStyle style_header = wb.createCellStyle();
            style_header.setFont(font_header);
            style_header.setWrapText(true);
            style_header.setAlignment(style_header.ALIGN_CENTER_SELECTION);
            style_header.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
            style_header.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


            //========TABLE HEADER STYLING===========================
            CellStyle th_style = wb.createCellStyle();
            th_style.setFont(datafont);
            th_style.setWrapText(true);
            th_style.setAlignment(th_style.ALIGN_CENTER);
            th_style.setFillForegroundColor(HSSFColor.GOLD.index);
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
            //data_style.setFillForegroundColor(HSSFColor.WHITE.index);
            //data_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//            data_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//            data_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//            data_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//            data_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);




            //=======INNER DATA STYLING=========================== 

            CellStyle innerdata_style = wb.createCellStyle();
            innerdata_style.setFont(datafont);
            innerdata_style.setWrapText(true);
            innerdata_style.setAlignment(data_style.ALIGN_CENTER);
            innerdata_style.setFillForegroundColor(HSSFColor.WHITE.index);
            innerdata_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            innerdata_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            innerdata_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            innerdata_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            innerdata_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);



         







            //===============ALL SITES HEADER===============================

            HSSFRow rw0 = allsites.createRow(0);
            rw0.setHeightInPoints(35);
            HSSFCell rw0cell0 = rw0.createCell(0);
            rw0cell0.setCellValue("ENROLLED  MOTHERS FROM SOLIAN , NGUBERETI , EMINING  HEALTH FACILITIES ");
            rw0cell0.setCellStyle(style_header);
            
            
            
               //=======STATISTICS HEADER=======================================     

            HSSFRow rw01 = registerdetails.createRow(0);
            rw01.setHeightInPoints(35);
            HSSFCell rw01cell01 = rw01.createCell(0);
            rw01cell01.setCellValue("MOTHERS ANC VISITS AND MATERNITY REGISTER DETAILS");
            rw01cell01.setCellStyle(style_header);
            
            

            //===============SOLIAN DATA HEADER=================================      


            HSSFRow solheader = missingmothers.createRow(0);
            solheader.setHeightInPoints(35);
            HSSFCell solheadercell0 = solheader.createCell(0);
            solheadercell0.setCellValue("MOTHERS NOT APPEARING IN ANC / MATERNITY REGISTERS ");
            solheadercell0.setCellStyle(style_header);




        //===========================MAIN SITE COLUMN HEADERS========================================
           HSSFRow allsitescolumnheader = allsites.createRow(1);
                allsitescolumnheader.setHeightInPoints(30);
                HSSFCell rwcolheader=null;
                
                //==========missing mothers header part=============
                   HSSFRow missingmomsheaderrow= missingmothers.createRow(1);
                missingmomsheaderrow.setHeightInPoints(30);
                 HSSFCell rwcolheader2missingreg=null;
                for(int d=0;d<maxmerging;d++){
                 rwcolheader = allsitescolumnheader.createCell(d+0);
                  rwcolheader.setCellValue(columnheaders[d]);
                  rwcolheader.setCellStyle(th_style);
                  
                  
                  rwcolheader2missingreg=missingmomsheaderrow.createCell(d);
                  rwcolheader2missingreg.setCellValue(columnheaders[d]);
                  rwcolheader2missingreg.setCellStyle(th_style);
                  
                  
                }
                for (int a = 1; a <=maxmerging; a++) {
                    if(a==9){
                    
                        allsites.setColumnWidth(a, 7500);
                        missingmothers.setColumnWidth(a, 7500);
                    }
                    else{
                        allsites.setColumnWidth(a, 4500);
                        missingmothers.setColumnWidth(a, 4500);
                    }
                        
                    }


               
//==========================DETAILED MOTHERS INFORMATION==============================  

                
//============================================THE HEADER PART========================================                
            HSSFRow registerdetheaderrow= registerdetails.createRow(1);
                registerdetheaderrow.setHeightInPoints(30);
           
                
                
                //create the cells from a loop

             HSSFCell rwcolheader2=null;      
for(int d=0;d<registerdetailsheaders2.length;d++){
    
rwcolheader2=registerdetheaderrow.createCell(d);
rwcolheader2.setCellValue(registerdetailsheaders2[d]);
rwcolheader2.setCellStyle(th_style);




}

    HSSFRow registerdetheader2row= registerdetails.createRow(2);
                registerdetheader2row.setHeightInPoints(30);
                
                //create the cells from a loop

             HSSFCell rwcolheader3=null;   
for(int d=0;d<registerdetailsheaders3.length;d++){
    
rwcolheader3=registerdetheader2row.createCell(d);
rwcolheader3.setCellValue(registerdetailsheaders3[d]);
rwcolheader3.setCellStyle(th_style);


registerdetails.setColumnWidth(d,4000);



                                                  }  

        dbConn conn1 = new dbConn();
        mobiledbConn conn = new mobiledbConn();


            //===============DISTINCT FACILITY NAMES===========


           // String facils = "Select  Distinct facilityname from mother_details order by facilityname asc";

          //  conn.rs1 = conn.st1.executeQuery(facils);

      
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            //THE FAMOUS WHILE LOOP STARTS HERE
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
          
        
            int count = 0;
            
            //=====this row no variable is used for all sites and will keep on incementing 
               int allsitesrowno = 1;
               
            //=====this row no variable is used for register details site and will keep on incementing 
               int regsitesrowno = 2;    
               
             //=====this row no variable is used for missing details site and will keep on incementing 
               int missingsitesrowno = 1;    
               
            //=====this variable initializes a row for the all sites worksheet   
               HSSFRow allsitesworksheetrws = null;
             
               
           //=========a row for the register details worksheet    
               HSSFRow regdetailsworksheetrws = null;

             //=========a row for the missing details worksheet    
               HSSFRow missingdetailsworksheetrws = null;
   
     

                //determine which data goes to which worksheet.
          


//=================DEFINE THE COLUMN  HEADERS PER EACH FACILITY SHEET========================

              
            int sheetsstartcel=0;
            int statisticsstartcell=4;

            
                //sheet1
            allsites.addMergedRegion(new CellRangeAddress(0, 0, sheetsstartcel, maxmerging-1));
                
                //sheet 2
            registerdetails.addMergedRegion(new CellRangeAddress(0, 0, sheetsstartcel,registerdetailsheaders2.length-1));
            //merging within sheet two row two
            int ancfirstcell=Arrays.asList(registerdetailsheaders2).indexOf(anccolumnheaders)-1;
            int delfirstcell=Arrays.asList(registerdetailsheaders2).indexOf(deliverycolumnheaders)-1;
            int babyfirstcell=Arrays.asList(registerdetailsheaders2).indexOf(babycolumnheaders)-1;
            int complicationcell=Arrays.asList(registerdetailsheaders2).indexOf(compcolumnheaders)-1;
            //mother details merging
            registerdetails.addMergedRegion(new CellRangeAddress(1, 1, sheetsstartcel,ancfirstcell));
            
            //anc details register merging
            registerdetails.addMergedRegion(new CellRangeAddress(1, 1, ancfirstcell+1,delfirstcell));
            
            //delivery details merging
            registerdetails.addMergedRegion(new CellRangeAddress(1, 1, delfirstcell+1,babyfirstcell));
            
            //baby status
            registerdetails.addMergedRegion(new CellRangeAddress(1, 1, babyfirstcell+1,complicationcell));
            
            //complications
            registerdetails.addMergedRegion(new CellRangeAddress(1, 2, complicationcell+1,registerdetailsheaders2.length-1));
            
            
            
            
                //sheet 3
            missingmothers.addMergedRegion(new CellRangeAddress(0, 0, sheetsstartcel, maxmerging-1));
            
               




                String mainqr = "Select anc_no, DOB ,FName ,SName,LName,PhoneNo ,NOKPhoneNo,ANCVisits_now,VisitDate,mother_details.delivery_date as delivery_date,status_id,Age,nok_consent,languagePreferred,facilityname, ChwID,prefered_Health from mother_details "
                        + " left join mother_profile  on (mother_details.anc_no=mother_profile.ANC_Num and mother_details.facilityname=mother_profile.health_facility) order by FName desc";

                conn.rs = conn.st.executeQuery(mainqr);

                System.out.println("MAIN QUERY___________"+mainqr);
                
                int rowno = 2;

                int test=0;
                
                while (conn.rs.next()) {
  
                    rowno++;
                    allsitesrowno ++;
                    //if the mother contains data in the anc/mat regsiter
  String checkexistance1="select * from mother_details join atoh on mother_details.motherID=atoh.motherid where ancno='"+conn.rs.getString("anc_no")+"' and facilityname LIKE '"+conn.rs.getString("facilityname")+"' and atoh.Village !=''  group by mother_details.motherID order by mother_details.FName";
    
  
  
  conn1.rs_6=conn1.st_6.executeQuery(checkexistance1);
  int cellpos1=0;
  while(conn1.rs_6.next()){
 //increment the register
  regsitesrowno++;
  
  //worksheet with either anc or maternity    
  regdetailsworksheetrws=registerdetails.createRow(regsitesrowno);
  regdetailsworksheetrws.setHeightInPoints(25);
  
 System.out.println("_________________=========="+regsitesrowno); 
 
 
 
 
 
 //==========================================================================================================
                 //display details in work sheet  2
 //==========================================================================================================
 
 
     // register details 
 
                   //================ANC NO
 
                     HSSFCell regdetailscellxx = regdetailsworksheetrws.createCell(cellpos1);
                    regdetailscellxx.setCellValue(conn.rs.getString("anc_no"));
                    regdetailscellxx.setCellStyle(data_style);

                    if (conn.rs.getString("anc_no") == null || conn.rs.getString("anc_no").equals("")) {
                        regdetailscellxx.setCellValue("NO ANC");
                    } 
                   
                    cellpos1++;
                    
                    
                   //FIRST NAME

                    //all sites worksheet
                     HSSFCell allsitescell11 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescell11.setCellValue(conn1.rs_6.getString("FName").toUpperCase());
                    allsitescell11.setCellStyle(data_style);
                    if (conn1.rs_6.getString("FName").equals("null")) {
                        allsitescell11.setCellValue("");
                    }
                    
                 cellpos1++;
                    
                   //MIDDLE NAME

                   
                    //all sites worksheet
                      HSSFCell allsitescell22 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescell22.setCellValue(conn1.rs_6.getString("SName").toUpperCase());
                    allsitescell22.setCellStyle(data_style);
                    if (conn1.rs_6.getString("SName").equals("null")) {
                        allsitescell22.setCellValue("");
                    }
                    cellpos1++; 
                    
                    
                    
                    
                    
                    //LAST NAME
                    //all sites worksheet

                    HSSFCell allsitescell33 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescell33.setCellValue(conn1.rs_6.getString("LName").toUpperCase());
                    allsitescell33.setCellStyle(data_style);
                    if (conn1.rs_6.getString("LName").equals("null")) {
                        allsitescell33.setCellValue("");
                    }
                    
                    cellpos1++;
                    
                     //EDD
                    //all sites worksheet

                    HSSFCell edd33 = regdetailsworksheetrws.createCell(cellpos1);
                    edd33.setCellValue(conn.rs.getString("delivery_date").toUpperCase());
                    edd33.setCellStyle(data_style);
                    if (conn.rs.getString("delivery_date")==null) {
                        allsitescell33.setCellValue("");
                    }
                    
                    cellpos1++;
                    
                    
                     //FACILITY NAME

                    //all sites worksheet
                    HSSFCell allsitescell88 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescell88.setCellValue(conn.rs.getString("facilityname").toUpperCase());
                    allsitescell88.setCellStyle(data_style);

                    cellpos1++;
                    
 
                    
                     //FACILITY NAME

                    //all sites worksheet
                    HSSFCell allsitescellvil = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvil.setCellValue(conn1.rs_6.getString("village").toUpperCase());
                    allsitescellvil.setCellStyle(data_style);

                    cellpos1++;
                    
                    //====NOW GET THE ANC DETAILS
                    
                    
 //===========================================================================================================
 //===========================================================================================================
                                     //ANC VISIT DETAILS
 //===========================================================================================================
 //===========================================================================================================
                  
                    //1st visit
                    
                    String anc1="select * from atoh where visit_no ='1' and motherid='"+conn1.rs_6.getString("motherID")+"'";
                                
                    conn1.rs_5=conn1.st_5.executeQuery(anc1);
                    if(conn1.rs_5.next()){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("YES");
                    allsitescellvis1.setCellStyle(data_style);

                    cellpos1++;
                      System.out.println("~~~~~~~~~~~~|||||"+conn1.rs_5.getString("DateofVisit"));                      
                    }
                    else {
                    
                         HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                    cellpos1++;
                    
                    }
                    
                    //======VISIT 2=====
                    
                     String anc2="select * from atoh where visit_no='2' and motherid='"+conn1.rs_6.getString("motherID")+"'";
                                
                    conn1.rs_5=conn1.st_5.executeQuery(anc2);
                    if(conn1.rs_5.next()){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("YES");
                    allsitescellvis1.setCellStyle(data_style);
                    cellpos1++;
                                            }
                    else {
                    
                         HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                    cellpos1++;
                    
                    }
                    
                   
                    
                      //======VISIT 3=====
                    
                     String anc3="select * from atoh where visit_no='3' and motherid='"+conn1.rs_6.getString("motherID")+"'";
                                
                    conn1.rs_5=conn1.st_5.executeQuery(anc3);
                    if(conn1.rs_5.next()){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("YES");
                    allsitescellvis1.setCellStyle(data_style);
                    cellpos1++;
                                            }
                    else {
                    
                         HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                    cellpos1++;
                    
                    }
                    
                    
                    //======VISIT 4=====
                    
                     String anc4="select * from atoh where visit_no='4' and motherid='"+conn1.rs_6.getString("motherID")+"'";
                                
                    conn1.rs_5=conn1.st_5.executeQuery(anc4);
                    if(conn1.rs_5.next()){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("YES");
                    allsitescellvis1.setCellStyle(data_style);
                    cellpos1++;
                                            }
                    else{ 
                    
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                    cellpos1++;
                    }
                  
                    //======VISIT 5 and above=====
                    
                     String anc5="select * from atoh where visit_no > 4 and motherid='"+conn1.rs_6.getString("motherID")+"'";
                                
                    conn1.rs_5=conn1.st_5.executeQuery(anc5);
                    if(conn1.rs_5.next()){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("YES");
                    allsitescellvis1.setCellStyle(data_style);
                    cellpos1++;
                                            }
                    else{
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                    cellpos1++;
                    }
                    
                    
                    
 //===========================================================================================================
                    //MATERNITY REGISTER DETAILS
 //===========================================================================================================
 
                    
                    
                    String maternitydetails="select * from mat_vtoab join mat_mtou on mat_vtoab.motherid=mat_mtou.motherid where mat_vtoab.motherid='"+conn1.rs_6.getString("motherID")+"' group by mat_vtoab.motherid";
                    conn1.rs_4=conn1.st_4.executeQuery(maternitydetails);
                    while(conn1.rs_4.next()) {
  //System.out.println("-----------------------MATERNITY DATA" +conn1.rs_4.getString("DeliveryDate"));
  System.out.println("-----------------------BIRTH TYPE " +conn1.rs_4.getString("live_birth"));
  
  
   //=============================================live birth==================================================================
  
   if(conn1.rs_4.getString("live_birth").equalsIgnoreCase("Live Birth")){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("YES");
                    allsitescellvis1.setCellStyle(data_style);
                    
                                            }
                                         else{
                            HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                         
                         }
   
   //=================================================Fresh Still Birth==============================================================
  cellpos1++;
  if(conn1.rs_4.getString("live_birth").equalsIgnoreCase("Fresh Still Birth")){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("YES");
                    allsitescellvis1.setCellStyle(data_style);
                    
                                            }
      else{
                            HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                         
                         }
                        
                       cellpos1++;  
            
   //========================================macerated still birth=======================================================================           
                         if(conn1.rs_4.getString("live_birth").equalsIgnoreCase("Macerated Still Birth")){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("YES");
                    allsitescellvis1.setCellStyle(data_style);
                    
                                            }
                         else {
                             
                            HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                         
                         }
                        cellpos1++;
                        
                        
                        
                        
//===========================================BABY STATUS============================
                        
                        //====alive==========
                  if(conn1.rs_4.getString("ConditionAfterDelivery").equalsIgnoreCase("A")){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("YES");
                    allsitescellvis1.setCellStyle(data_style);
                    
                                            }
                  else{
                  HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                  }
                        
                  cellpos1++;  
                  
                  
                    if(conn1.rs_4.getString("ConditionAfterDelivery").equalsIgnoreCase("D")){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("YES");
                    allsitescellvis1.setCellStyle(data_style);
                    
                                                                                            }
                    else {
                        
                  HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                  }
                        
                  cellpos1++; 
                  
                  
                   HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(conn1.rs_4.getString("DeliveryComplications").toUpperCase());
                    allsitescellvis1.setCellStyle(data_style);
                  
                  
                  
                        
                                            }
                    
                    
                    
  }
  
  
  //========================================================MOTHERS NOT EXISTING======================================== 
  //========================================================MOTHERS NOT EXISTING======================================== 
  
  String notexisting="select * from mother_details join atoh on mother_details.motherID=atoh.motherid where ancno='"+conn.rs.getString("anc_no")+"' and facilityname LIKE '"+conn.rs.getString("facilityname")+"' and atoh.Village !=''  ";
  
   conn1.rs_6=conn1.st_6.executeQuery(notexisting);
   if(!conn1.rs_6.next()){
   missingsitesrowno++;
   System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
     
       
     missingdetailsworksheetrws=missingmothers.createRow(missingsitesrowno);
                    missingdetailsworksheetrws.setHeightInPoints(20);
                    int cellpos2=0;
                    
                    
                    
                    
                    //ANC NUMBER
                    
              
                  
                    //all sites worksheet
                    HSSFCell allsitescellxx = missingdetailsworksheetrws.createCell(cellpos2);
                    allsitescellxx.setCellValue(conn.rs.getString("anc_no"));
                    allsitescellxx.setCellStyle(data_style);

                    if (conn.rs.getString("anc_no") == null || conn.rs.getString("anc_no").equals("")) {
                        allsitescellxx.setCellValue("NO ANC");
                    }

                
                    
                    
                     cellpos2++;
                    //FIRST NAME

                                        
                  
                    //all sites worksheet
                     HSSFCell allsitescell11 = missingdetailsworksheetrws.createCell(cellpos2);
                    allsitescell11.setCellValue(conn.rs.getString("FName").toUpperCase());
                    allsitescell11.setCellStyle(data_style);
                    if (conn.rs.getString("FName").equals("null")) {
                        allsitescell11.setCellValue("");
                    }
                    
                 cellpos2++;
                    //MIDDLE NAME

                   
                    //all sites worksheet
                      HSSFCell allsitescell22 = missingdetailsworksheetrws.createCell(cellpos2);
                    allsitescell22.setCellValue(conn.rs.getString("SName").toUpperCase());
                    allsitescell22.setCellStyle(data_style);
                    if (conn.rs.getString("SName").equals("null")) {
                        allsitescell22.setCellValue("");
                    }
                    cellpos2++;
                    
                    //LAST NAME
                    
                   
                    
                    //all sites worksheet

                    HSSFCell allsitescell33 = missingdetailsworksheetrws.createCell(cellpos2);
                    allsitescell33.setCellValue(conn.rs.getString("LName").toUpperCase());
                    allsitescell33.setCellStyle(data_style);
                    if (conn.rs.getString("LName").equals("null")) {
                        allsitescell33.setCellValue("");
                    }
                    
                    cellpos2++;
                    //PHONE NUMBER

                  
                    
                    //all sites worksheet
                     HSSFCell allsitescell44 = missingdetailsworksheetrws.createCell(cellpos2);
                    allsitescell44.setCellValue(conn.rs.getString("PhoneNo"));
                    allsitescell44.setCellStyle(data_style);
                    if (conn.rs.getString("PhoneNo").equals("0")||conn.rs.getString("PhoneNo").equals("N/A")) {
                        allsitescell44.setCellValue("NO NUMBER");
                    }
                    
                               cellpos2++;
                               
                    //NOK PHONE NUMBER

               
                    //all sites worksheet
                    HSSFCell allsitescell55 = missingdetailsworksheetrws.createCell(cellpos2);
                    allsitescell55.setCellValue(conn.rs.getString("NOKPhoneNo"));
                    allsitescell55.setCellStyle(data_style);
                    if (conn.rs.getString("NOKPhoneNo")==null) {
                        allsitescell55.setCellValue("NO NUMBER");
                    }
                    
                                     cellpos2++;
                    //DELIVERY DATE


                    //all sites worksheet
                    HSSFCell allsitescell66 = missingdetailsworksheetrws.createCell(cellpos2);
                    allsitescell66.setCellValue(conn.rs.getString("delivery_date"));
                    allsitescell66.setCellStyle(data_style);
                    if (conn.rs.getString("delivery_date")==null || conn.rs.getString("delivery_date").equals("-") || conn.rs.getString("delivery_date").equals("")) {
                        allsitescell66.setCellValue("NO DATE");
                    }

                    cellpos2++;
                    //VISIT DATE

                   
                    //all sites worksheet
                     HSSFCell allsitescell77 = missingdetailsworksheetrws.createCell(cellpos2);
                    allsitescell77.setCellValue(conn.rs.getString("visitDate"));
                    allsitescell77.setCellStyle(data_style);
                    if (conn.rs.getString("visitDate")==null || conn.rs.getString("visitDate").equals("") || conn.rs.getString("visitDate").equals("")) {
                        allsitescell77.setCellValue("NO DATE");
                    }
                    
                    cellpos2++;
                    
                    //FACILITY NAME

                    //all sites worksheet
                    HSSFCell allsitescell88 = missingdetailsworksheetrws.createCell(cellpos2);
                    allsitescell88.setCellValue(conn.rs.getString("facilityname").toUpperCase());
                    allsitescell88.setCellStyle(data_style);

                    cellpos2++;
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    //get the preffered facility name 
                    
                    
                    
                    String getpreffacility="select prefered_Health from mother_profile where ANC_Num LIKE '"+conn.rs.getString("anc_no")+"' and health_facility lIKE '"+conn.rs.getString("facilityname")+"'";
                    
                    System.out.println(getpreffacility);
                    conn.rs4=conn.st4.executeQuery(getpreffacility);
                    
                    
                       HSSFCell allsitescellprefacil = missingdetailsworksheetrws.createCell(cellpos2);
                 
                    if( conn.rs4.next()){
                        
                        System.out.println(" %%%%%%%%%%%%%%% "+conn.rs4.getString("prefered_Health").toUpperCase());
                        
                       allsitescellprefacil.setCellValue(conn.rs4.getString("prefered_Health").toUpperCase());
                    allsitescellprefacil.setCellStyle(data_style);
                                         }
                    else{
                        allsitescellprefacil.setCellValue(" ");
                    allsitescellprefacil.setCellStyle(data_style);
                    }
                          cellpos2++;
                    
                    
                    
                    
                    
                    
                          
                          
                          
                          //get the facility that this mother is visiting 
                          String findvisitingfacility="Select * from mother_details join atoh on mother_details.motherID=atoh.motherid where ancno='"+conn.rs.getString("anc_no")+"' and facilityname LIKE '"+conn.rs.getString("facilityname")+"' and atoh.Village !='' ";
                          
                    
                       conn1.rs4=conn1.st4.executeQuery(findvisitingfacility);
                    
                    
                       HSSFCell allsitescellvisingfaciil = missingdetailsworksheetrws.createCell(cellpos2);
                 
                    if( conn1.rs4.next()){
                        
                        
                       allsitescellvisingfaciil.setCellValue("Current Facility");
                    allsitescellvisingfaciil.setCellStyle(data_style);
                                         }
                    else{
                        
         
                        //check weather the mother attended the preffered facility.
                     String findvisitingfacility1="Select * from mother_details join atoh on mother_details.motherID=atoh.motherid where ancno='"+conn.rs.getString("anc_no")+"' and facilityname LIKE '"+conn.rs.getString("prefered_Health")+"'  ";
                          
                     
                    
                       conn1.rs4=conn1.st4.executeQuery(findvisitingfacility1);
                       
                       if(conn1.rs4.next()){
                       
                           allsitescellvisingfaciil.setCellValue("Preffered Facility");
                            allsitescellprefacil.setCellStyle(data_style);
                       }
                       else{
                        
                        
                        allsitescellvisingfaciil.setCellValue("Not Known");
                         allsitescellprefacil.setCellStyle(data_style);
                       }
                   
                    }
                          cellpos2++;
                    
                    
                    
                    
                    
                    
                    
                    //CHW NAME
                    
                    
                   
                       HSSFCell allsitescell99 = missingdetailsworksheetrws.createCell(cellpos2);
                      String getcurchwdetails="select chv_fname,chv_mname,chv_lname from  chw where chv_phone='"+conn.rs.getString("ChwID")+"'";
                      
                      conn.rs1=conn.st1.executeQuery(getcurchwdetails);
                    
                      if(conn.rs1.next()){
               
                    //all sites worksheet
                   
                     allsitescell99.setCellValue(conn.rs1.getString("chv_fname").toUpperCase()+" "+conn.rs1.getString("chv_mname").toUpperCase()+" "+conn.rs1.getString("chv_lname").toUpperCase());
                   allsitescell99.setCellStyle(data_style);
                    
                                          }
                      else {
                 
                     allsitescell99.setCellValue("");
                   allsitescell99.setCellStyle(data_style);
                    
                          
                      }
                    
                  cellpos2++;
                    
                    //CHW PHONE
                    
             
                    //all sites worksheet
                    HSSFCell allsitescell1010 = missingdetailsworksheetrws.createCell(cellpos2);
                     allsitescell1010.setCellValue(conn.rs.getString("ChwID").toUpperCase());
                   allsitescell1010.setCellStyle(data_style);
                    
                       
       
       
       
       
       
   
      }
   //==========================================end of mothers not existing========================================                 
   //==========================================end of mothers not existing========================================                 
                    System.out.println(conn.rs.getString("anc_no") + " " + conn.rs.getString("DOB") + " " + conn.rs.getString("FName") + " " + conn.rs.getString("SName") + " " + conn.rs.getString("PhoneNo") + " " + conn.rs.getString("facilityname"));
                    
                    //create the various cells and add data to the cells

                    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                    //DISPLAYING RAW DATA INTO DIFFERENT WORK SHEETS

                    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$      

                //all sites worksheet
                    
                    allsitesworksheetrws=allsites.createRow(allsitesrowno);
                    allsitesworksheetrws.setHeightInPoints(20);
                
                      //worksheet with either anc or maternity    
                    missingdetailsworksheetrws=missingmothers.createRow(allsitesrowno);
                    regdetailsworksheetrws.setHeightInPoints(20);
                    int cellpos=0;
                    
                    
                    
                    
                    //ANC NUMBER
                    
              
                  
                    //all sites worksheet
                    HSSFCell allsitescellxx = allsitesworksheetrws.createCell(cellpos);
                    allsitescellxx.setCellValue(conn.rs.getString("anc_no"));
                    allsitescellxx.setCellStyle(data_style);

                    if (conn.rs.getString("anc_no") == null || conn.rs.getString("anc_no").equals("")) {
                        allsitescellxx.setCellValue("NO ANC");
                    }

                
                    
                    
                     cellpos++;
                    //FIRST NAME

                                        
                  
                    //all sites worksheet
                     HSSFCell allsitescell11 = allsitesworksheetrws.createCell(cellpos);
                    allsitescell11.setCellValue(conn.rs.getString("FName").toUpperCase());
                    allsitescell11.setCellStyle(data_style);
                    if (conn.rs.getString("FName").equals("null")) {
                        allsitescell11.setCellValue("");
                    }
                    
                 cellpos++;
                    //MIDDLE NAME

                   
                    //all sites worksheet
                      HSSFCell allsitescell22 = allsitesworksheetrws.createCell(cellpos);
                    allsitescell22.setCellValue(conn.rs.getString("SName").toUpperCase());
                    allsitescell22.setCellStyle(data_style);
                    if (conn.rs.getString("SName").equals("null")) {
                        allsitescell22.setCellValue("");
                    }
                    cellpos++;
                    
                    //LAST NAME
                    
                   
                    
                    //all sites worksheet

                    HSSFCell allsitescell33 = allsitesworksheetrws.createCell(cellpos);
                    allsitescell33.setCellValue(conn.rs.getString("LName").toUpperCase());
                    allsitescell33.setCellStyle(data_style);
                    if (conn.rs.getString("LName").equals("null")) {
                        allsitescell33.setCellValue("");
                    }
                    
                    cellpos++;
                    //PHONE NUMBER

                  
                    
                    //all sites worksheet
                     HSSFCell allsitescell44 = allsitesworksheetrws.createCell(cellpos);
                    allsitescell44.setCellValue(conn.rs.getString("PhoneNo"));
                    allsitescell44.setCellStyle(data_style);
                    if (conn.rs.getString("PhoneNo").equals("0")||conn.rs.getString("PhoneNo").equals("N/A")) {
                        allsitescell44.setCellValue("NO NUMBER");
                    }
                    
                               cellpos++;
                               
                    //NOK PHONE NUMBER

               
                    //all sites worksheet
                    HSSFCell allsitescell55 = allsitesworksheetrws.createCell(cellpos);
                    allsitescell55.setCellValue(conn.rs.getString("NOKPhoneNo"));
                    allsitescell55.setCellStyle(data_style);
                    if (conn.rs.getString("NOKPhoneNo")==null) {
                        allsitescell55.setCellValue("NO NUMBER");
                    }
                    
                                     cellpos++;
                    //DELIVERY DATE


                    //all sites worksheet
                    HSSFCell allsitescell66 = allsitesworksheetrws.createCell(cellpos);
                    allsitescell66.setCellValue(conn.rs.getString("delivery_date"));
                    allsitescell66.setCellStyle(data_style);
                    if (conn.rs.getString("delivery_date")==null || conn.rs.getString("delivery_date").equals("-") || conn.rs.getString("delivery_date").equals("")) {
                        allsitescell66.setCellValue("NO DATE");
                    }

                    cellpos++;
                    //VISIT DATE

                   
                    //all sites worksheet
                     HSSFCell allsitescell77 = allsitesworksheetrws.createCell(cellpos);
                    allsitescell77.setCellValue(conn.rs.getString("visitDate"));
                    allsitescell77.setCellStyle(data_style);
                    if (conn.rs.getString("visitDate")==null || conn.rs.getString("visitDate").equals("") || conn.rs.getString("visitDate").equals("")) {
                        allsitescell77.setCellValue("NO DATE");
                    }
                    
                    cellpos++;
                    
                    //FACILITY NAME

                    //all sites worksheet
                    HSSFCell allsitescell88 = allsitesworksheetrws.createCell(cellpos);
                    allsitescell88.setCellValue(conn.rs.getString("facilityname").toUpperCase());
                    allsitescell88.setCellStyle(data_style);

                    cellpos++;
                    
                    
                    
                    
                    
                    //get the preffered facility name 
                    
                    
                    
                    String getpreffacility="select prefered_Health from mother_profile where ANC_Num LIKE '"+conn.rs.getString("anc_no")+"' and health_facility lIKE '"+conn.rs.getString("facilityname")+"'";
                    
                    System.out.println(getpreffacility);
                    conn.rs4=conn.st4.executeQuery(getpreffacility);
                    
                    
                       HSSFCell allsitescellprefacil = allsitesworksheetrws.createCell(cellpos);
                 
                    if( conn.rs4.next()){
                        
                        System.out.println(" %%%%%%%%%%%%%%% "+conn.rs4.getString("prefered_Health").toUpperCase());
                        
                       allsitescellprefacil.setCellValue(conn.rs4.getString("prefered_Health").toUpperCase());
                    allsitescellprefacil.setCellStyle(data_style);
                                         }
                    else{
                        
                        allsitescellprefacil.setCellValue(" ");
                    allsitescellprefacil.setCellStyle(data_style);
                    }
                          cellpos++;
                    
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          //get the facility that this mother is visiting 
                          String findvisitingfacility="Select * from mother_details join atoh on mother_details.motherID=atoh.motherid where ancno='"+conn.rs.getString("anc_no")+"' and facilityname LIKE '"+conn.rs.getString("facilityname")+"' and atoh.Village !='' ";
                          
                    
                       conn1.rs4=conn1.st4.executeQuery(findvisitingfacility);
                    
                    
                       HSSFCell allsitescellvisingfaciil = allsitesworksheetrws.createCell(cellpos);
                 
                    if( conn1.rs4.next()){
                        
                        
                       allsitescellvisingfaciil.setCellValue("Current Facility");
                    allsitescellvisingfaciil.setCellStyle(data_style);
                                         }
                    else{
                        
         
                        //check weather the mother attended the preffered facility.
                     String findvisitingfacility1="Select * from mother_details join atoh on mother_details.motherID=atoh.motherid where ancno='"+conn.rs.getString("anc_no")+"' and facilityname LIKE '"+conn.rs.getString("prefered_Health")+"'  ";
                          
                    
                       conn1.rs4=conn1.st4.executeQuery(findvisitingfacility1);
                       
                       if(conn1.rs4.next()){
                       
                           allsitescellvisingfaciil.setCellValue("Preffered Facility");
                            allsitescellprefacil.setCellStyle(data_style);
                       }
                       else{
                        
                        
                        allsitescellvisingfaciil.setCellValue("Not Known");
                         allsitescellprefacil.setCellStyle(data_style);
                       }
                   
                    }
                          cellpos++;
                   
                          
                          
                          
                    
                    
                    
                    
                    //CHW NAME
                    
                    
                   
                       HSSFCell allsitescell99 = allsitesworksheetrws.createCell(cellpos);
                      String getcurchwdetails="select chv_fname,chv_mname,chv_lname from  chw where chv_phone='"+conn.rs.getString("ChwID")+"'";
                      
                      conn.rs1=conn.st1.executeQuery(getcurchwdetails);
                    
                      if(conn.rs1.next()){
               
                    //all sites worksheet
                   
                     allsitescell99.setCellValue(conn.rs1.getString("chv_fname").toUpperCase()+" "+conn.rs1.getString("chv_mname").toUpperCase()+" "+conn.rs1.getString("chv_lname").toUpperCase());
                   allsitescell99.setCellStyle(data_style);
                    
                                          }
                      else {
                 
                     allsitescell99.setCellValue("");
                   allsitescell99.setCellStyle(data_style);
                    
                          
                      }
                    
                  cellpos++;
                    
                    //CHW PHONE
                    
             
                    //all sites worksheet
                    HSSFCell allsitescell1010 = allsitesworksheetrws.createCell(cellpos);
                     allsitescell1010.setCellValue(conn.rs.getString("ChwID").toUpperCase());
                   allsitescell1010.setCellStyle(data_style);
                    
                    
                       
                    
//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\                  
//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\                  
//                                        worksheet 2
//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\                  
//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\                  
                   
  
                   
     
                   

                }//end of register details while loop




//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            //THE FAMOUS WHILE LOOP ENDS HERE
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            




//=====================SET CELL WIDTH FOR THE MAIN WIDTH AND==================================

            for (int a = 1; a <= 12; a++) {

                allsites.setColumnWidth(a, 5000);

            }








        } catch (SQLException ex) {
            Logger.getLogger(MOBILEREGISTER.class.getName()).log(Level.SEVERE, null, ex);
        }

        Date dat = new Date();

        String dat1 = dat.toString().replace(" ", "_");

        // write it as an excel attachment
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=MOBILE_ENROLLMENT_FOLLOWUP" + dat1 + ".xls");
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
