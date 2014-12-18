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

/**
 *
 * @author Manuel
 */
public class CONTROLSITESFACILITYANDMATANCSUMMARIES extends HttpServlet {

    HSSFWorkbook wb;
    int maxmerging=0;
    String maincountqry="";
   String Year="";
   int visit1mom=0;
   int visit2mom=0;
   int visit3mom=0;
   int visit4mom=0;
   int visit5mom=0;
   int livebirth=0;
   int fsbbirth=0;
   int msbbirth=0;
   int alive=0;
   int dead=0;
   
   
    int sumvisit1mom=0;
   int sumvisit2mom=0;
   int sumvisit3mom=0;
   int sumvisit4mom=0;
   int sumvisit5mom=0;
   int sumlivebirth=0;
   int sumfsbbirth=0;
   int summsbbirth=0;
   int sumalive=0;
   int sumdead=0;
   
   String yearcopy="";
   String monthcopy="";
   String facilitycopy="";
   String yearmonthcopy="";
   
   String additiaonalparrams="EXTRACT(YEAR FROM VisitDate) ='"+Year+"' ";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Year="";
            additiaonalparrams=" EXTRACT(YEAR FROM VisitDate) ='"+Year+"' ";
           
            String startdate="201309";
            String enddate="201408";
            
            
            if(request.getParameter("startdate")!=null){
            
            startdate=request.getParameter("startdate");
            startdate=startdate.replace("-", "");
            startdate=startdate.substring(0,6);
            
            }
            if(request.getParameter("enddate")!=null){
                
            enddate=request.getParameter("enddate");
            enddate=enddate.replace("-", "");
            enddate=enddate.substring(0,6);
            
                      }
            
            System.out.println(startdate+"    and enddate is    "+ enddate);
            
    visit1mom=0;
    visit2mom=0;
    visit3mom=0;
    visit4mom=0;
    visit5mom=0;
    livebirth=0;
    fsbbirth=0;
    msbbirth=0;
    alive=0;
    dead=0;  
    sumvisit1mom=0;
    sumvisit2mom=0;
    sumvisit3mom=0;
    sumvisit4mom=0;
    sumvisit5mom=0;
    sumlivebirth=0;
    sumfsbbirth=0;
    summsbbirth=0;
    sumalive=0;
    sumdead=0;         
    
    
    
     int visit1mom1=0;
    int visit2mom1=0;
    int visit3mom1=0;
    int visit4mom1=0;
    int visit5mom1=0;
    int livebirth1=0;
    int fsbbirth1=0;
    int msbbirth1=0;
    int alive1=0;
    int dead1=0; 
    
    
    
    
            if(Year.equals("")){additiaonalparrams="";}
            
            wb = new HSSFWorkbook();
             String columnheaders[]={"FACILITY NAME","CHW'S NAME","CHWS PHONE NO."};
            
            
             String anccolumnheaders="ANC VISITS";
             String matcolumnheaders="MATERNITY REGISTER DETAILS";
             String babycolumnheaders="BABY STATUS";
             String deliverycolumnheaders="BIRTH TYPE";
             String compcolumnheaders="DELIVERY COMPLICATION";
             String registerdetailsheaders2[]={"MOTHER DETAILS","","",anccolumnheaders,"","","","",deliverycolumnheaders,"","",babycolumnheaders,""};
             
             String registerdetailsheaders3[]={"","","FACILITY","1st VISIT","2nd VISIT","3rd VISIT","4th VISIT","5th AND ABOVE","LIVE BIRTH","FRESH STILL BIRTH","MACERATED STILL BIRTH","ALIVE","DEAD"};
             
             
           maxmerging=columnheaders.length;
           
           maincountqry="select count(*) as curcount from mother_details where "+additiaonalparrams+"  facilityname LIKE ";
           
            String Statisticsarr[] = {"YEAR","MONTH","FACILITY", "PREGNANT MOTHERS", "DELIVERED MOTHERS", "TOTAL"};


            
            //=====================================



            //==============================CREATE WORKBOOK AND SHEETS FOR EACH SITE  



            //HSSFSheet statistics = wb.createSheet("STATISTICS");
            //HSSFSheet allsites = wb.createSheet("ALL ENROLLED MOTHERS");
            HSSFSheet registerdetails = wb.createSheet("MOTHERS REGISTER DETAILS");
            //HSSFSheet missingmothers = wb.createSheet("MISSING IN REGISTERS");

         


            //%%%%%%%%%%%%%%%%HEADER FONTS AND COLORATION%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            HSSFFont font_header = wb.createFont();
            font_header.setFontHeightInPoints((short) 10);
            font_header.setFontName("Adobe Garamond Pro Bold");



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
            style_header.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style_header.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style_header.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style_header.setBorderLeft(HSSFCellStyle.BORDER_THIN);

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
            data_style.setFillForegroundColor(HSSFColor.WHITE.index);
            data_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            data_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            data_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            data_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            data_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

            CellStyle lostyle = wb.createCellStyle();
            lostyle.setFont(datafont);
            lostyle.setWrapText(true);
            lostyle.setAlignment(data_style.ALIGN_CENTER);
            lostyle.setFillForegroundColor(HSSFColor.WHITE.index);
            lostyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            lostyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            lostyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            lostyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            lostyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            lostyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

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



         
   String yearcopy1="";
   String monthcopy1="";







            //===============ALL SITES HEADER===============================

          
            
               //=======STATISTICS HEADER=======================================     

            HSSFRow rw01 = registerdetails.createRow(0);
            rw01.setHeightInPoints(22);
            HSSFCell rw01cell01 = rw01.createCell(0);
            rw01cell01.setCellValue("MOTHERS ANC VISITS AND MATERNITY REGISTER DETAILS");
            rw01cell01.setCellStyle(style_header);
            
            

            //===============SOLIAN DATA HEADER=================================      





                
                //==========missing mothers header part=============
                 
               
             


               
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
            //mobiledbConn conn = new mobiledbConn();


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
             
               
            //=====this row no variable is used for register details site and will keep on incementing 
               int regsitesrowno = 3;    
               
          
             
               
           //=========a row for the register details worksheet    
               HSSFRow regdetailsworksheetrws = null;

   
     

                //determine which data goes to which worksheet.
          


//=================DEFINE THE COLUMN  HEADERS PER EACH FACILITY SHEET========================

              
            int sheetsstartcel=0;
            int statisticsstartcell=4;

              
                //sheet 2
            registerdetails.addMergedRegion(new CellRangeAddress(0, 0, sheetsstartcel,registerdetailsheaders2.length-1));
            //merging within sheet two row two
            int ancfirstcell=Arrays.asList(registerdetailsheaders2).indexOf(anccolumnheaders)-1;
            int delfirstcell=Arrays.asList(registerdetailsheaders2).indexOf(deliverycolumnheaders)-1;
            int babyfirstcell=Arrays.asList(registerdetailsheaders2).indexOf(babycolumnheaders)-1;
            //int complicationcell=Arrays.asList(registerdetailsheaders2).indexOf(compcolumnheaders)-1;
            //mother details merging
            registerdetails.addMergedRegion(new CellRangeAddress(1, 1, sheetsstartcel,ancfirstcell));
            
            //anc details register merging
            registerdetails.addMergedRegion(new CellRangeAddress(1, 1, ancfirstcell+1,delfirstcell));
            
            //delivery details merging
            registerdetails.addMergedRegion(new CellRangeAddress(1, 1, delfirstcell+1,babyfirstcell));
            
            //baby status
            registerdetails.addMergedRegion(new CellRangeAddress(1, 1, babyfirstcell+1,babyfirstcell+2));
            
            //complications
           // registerdetails.addMergedRegion(new CellRangeAddress(1, 2, complicationcell+1,registerdetailsheaders2.length-1));
            
            
            
String facils="select distinct facilityname from mother_details";            
       




//===================================================================================================
//old commented query
//===================================================================================================

//String mainquery=
//        " select mother_details.motherid as MOTHERID, atoh.ancno as ANCNO, UPPER("
//+" mother_details.facilityname) as FACILITY,"
//+" EXTRACT(YEAR_MONTH FROM MAX(DateofVisit)) as YEARMONTH, "
//+" EXTRACT(YEAR FROM MAX(DateofVisit)) as YEAR, "
//+" case "
//+" when EXTRACT(MONTH FROM MAX(DateofVisit)) =1 THEN 'JAN'"
//+" when EXTRACT(MONTH FROM MAX(DateofVisit)) =2 THEN 'FEB'"
//+" when EXTRACT(MONTH FROM MAX(DateofVisit)) =3 THEN 'MAR'"
//+" when EXTRACT(MONTH FROM MAX(DateofVisit))=4 THEN 'APR'"
//+" when EXTRACT(MONTH FROM MAX(DateofVisit))=5 THEN 'MAY'"
//+" when EXTRACT(MONTH FROM MAX(DateofVisit))=6 THEN 'JUN'"
//+" when EXTRACT(MONTH FROM MAX(DateofVisit))=7 THEN 'JUL'"
//+" when EXTRACT(MONTH FROM MAX(DateofVisit))=8 THEN 'AUG'"
//+" when EXTRACT(MONTH FROM MAX(DateofVisit))=9 THEN 'SEPT'"
//+" when EXTRACT(MONTH FROM MAX(DateofVisit))=10 THEN 'OCT'"
//+" when EXTRACT(MONTH FROM MAX(DateofVisit))=11 THEN 'NOV'"
//+" when EXTRACT(MONTH FROM MAX(DateofVisit))=12 THEn 'DEC'"
//+" END AS MONTHS,"
//+" Case"
//+" when max(visit_no) ='1' then '1st Visit'"
//+" when max(visit_no) ='2' then '2nd Visit'"
//+" when max(visit_no) ='3' then '3rd Visit'"
//+" when max(visit_no) ='4' then '4th Visit'"
//+" when max(visit_no) >=5 then '5th and above'"
// +" end as VISITNUMBER ," 
//
//+" case " 
//
//+" when ConditionAfterDelivery is null then 'NA'"
//+" else ConditionAfterDelivery "
//+" end as  BABYSTATUS,"
// +" case  "
//+" when DeliveryComplications is null then 'NA'"
//+" when DeliveryComplications like 'none' then 'NO COMPLICATIONS'"
//+" else DeliveryComplications" 
//+" end as  COMPLICATIONS,"
//+" case"  
//+" when live_birth is null then 'NA'"
//
//+" else live_birth "
//+" end as BIRTHTYPE "
//+" from mother_details "
//+" right join "
//+" (atoh left join  mat_vtoab on atoh.motherid=mat_vtoab.motherid"
//     +"  left join  mat_mtou on atoh.motherid=mat_mtou.motherid  )" 
//+" on mother_details.motherID=atoh.motherid "
//+" where EXTRACT(YEAR_MONTH FROM DateofVisit) >= '201309' and EXTRACT(YEAR_MONTH FROM DateofVisit) <= '201408'"
//        + "group by MOTHERID order by YEARMONTH, FACILITY,VISITNUMBER,BABYSTATUS,COMPLICATIONS ASC";



//===================================================================================================
//old commented query
//===================================================================================================



String mainquery=
        " select mother_details.motherid as MOTHERID, atoh.ancno as ANCNO, UPPER("
+" mother_details.facilityname) as FACILITY,"
+" EXTRACT(YEAR_MONTH FROM MAX(DateofVisit)) as YEARMONTH, "
+" EXTRACT(YEAR FROM MAX(DateofVisit)) as YEAR, "
+" case "
+" when EXTRACT(MONTH FROM MAX(DateofVisit)) =1 THEN 'JAN'"
+" when EXTRACT(MONTH FROM MAX(DateofVisit)) =2 THEN 'FEB'"
+" when EXTRACT(MONTH FROM MAX(DateofVisit)) =3 THEN 'MAR'"
+" when EXTRACT(MONTH FROM MAX(DateofVisit))=4 THEN 'APR'"
+" when EXTRACT(MONTH FROM MAX(DateofVisit))=5 THEN 'MAY'"
+" when EXTRACT(MONTH FROM MAX(DateofVisit))=6 THEN 'JUN'"
+" when EXTRACT(MONTH FROM MAX(DateofVisit))=7 THEN 'JUL'"
+" when EXTRACT(MONTH FROM MAX(DateofVisit))=8 THEN 'AUG'"
+" when EXTRACT(MONTH FROM MAX(DateofVisit))=9 THEN 'SEPT'"
+" when EXTRACT(MONTH FROM MAX(DateofVisit))=10 THEN 'OCT'"
+" when EXTRACT(MONTH FROM MAX(DateofVisit))=11 THEN 'NOV'"
+" when EXTRACT(MONTH FROM MAX(DateofVisit))=12 THEn 'DEC'"
+" END AS MONTHS,"
+" Case"
+" when max(visit_no) ='1' then '1st Visit'"
+" when max(visit_no) ='2' then '2nd Visit'"
+" when max(visit_no) ='3' then '3rd Visit'"
+" when max(visit_no) ='4' then '4th Visit'"
+" when max(visit_no) >=5 then '5th and above'"
 +" end as VISITNUMBER ," 

+" case " 

+" when ConditionAfterDelivery is null then 'NA'"
+" else ConditionAfterDelivery "
+" end as  BABYSTATUS,"
 +" case  "
+" when DeliveryComplications is null then 'NA'"
+" when DeliveryComplications like 'none' then 'NO COMPLICATIONS'"
+" else DeliveryComplications" 
+" end as  COMPLICATIONS,"
+" case"  
+" when live_birth is null then 'NA'"

+" else live_birth "
+" end as BIRTHTYPE "
+" from mother_details "
+" right join "
+" (atoh left join  mat_vtoab on atoh.motherid=mat_vtoab.motherid"
     +"  left join  mat_mtou on atoh.motherid=mat_mtou.motherid  )" 
+" on mother_details.motherID=atoh.motherid "
+" where (mother_details.facilityname LIKE 'timboroa' or mother_details.facilityname like 'torongo' or mother_details.facilityname like 'simotwet') and EXTRACT(YEAR_MONTH FROM DateofVisit) >= '"+startdate+"' and EXTRACT(YEAR_MONTH FROM DateofVisit) <= '"+enddate+"'"
        + "group by MOTHERID order by YEARMONTH, FACILITY,VISITNUMBER,BABYSTATUS,COMPLICATIONS ASC";


//conn.rs_5=conn.st_5.executeQuery(mainquery);

//while(conn.rs_5.next()){

                //String mainqr = "Select anc_no, DOB ,FName ,SName,LName,PhoneNo ,NOKPhoneNo,LocationID,Gest_to_firstANC,Anc_visit,VisitDate,delivery_date,status_id,Age,Gravida,parity,maritalStatus,education,occupation,nok_consent,languagePreferred,facilityname, ChwID from mother_details  where facilityname like '"+conn.rs_5.getString(1) +"'  order by FName desc";



                conn1.rs_5=conn1.st_5.executeQuery(mainquery);

                System.out.println("MAIN QUERY___________"+mainquery);
                
                int rowno = 0;

            
                
    yearcopy="";
    monthcopy="";
    facilitycopy="";
    yearmonthcopy="";            
      
     yearcopy1="";
    monthcopy1="";
    int yearcopypos=3;
    int yearcopypos1=2;
    int monthcopypos=3;
    int monthcopypos1=2;     
    
    
    
    
                while (conn1.rs_5.next()) {
  
               rowno++;   
                   
                    
                  
  if(yearmonthcopy.equals("")){yearmonthcopy=conn1.rs_5.getString("YEARMONTH");}
  if(monthcopy.equals("")){monthcopy=conn1.rs_5.getString("MONTHS");}
  if(facilitycopy.equals("")){ facilitycopy=conn1.rs_5.getString("FACILITY");}
  
  
  
  //conn1.rs_6=conn1.st_6.executeQuery(checkexistance1);
  

 //increment the register
  
  
  //worksheet with either anc or maternity    
  regdetailsworksheetrws=registerdetails.createRow(regsitesrowno);
  regdetailsworksheetrws.setHeightInPoints(22);
  
 //System.out.println("_________________=========="+regsitesrowno); 
 
    //if any change occurs then write data
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~            
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~            
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~            
     //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    //DISPLAYING RAW DATA INTO DIFFERENT WORK SHEETS

    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$      

                 if(!yearmonthcopy.equals(conn1.rs_5.getString("YEARMONTH"))||!facilitycopy.equals(conn1.rs_5.getString("FACILITY"))){
              
       int cellpos1=0;
                //facility
           yearcopypos++;
           monthcopypos++;
           regsitesrowno++;
        System.out.println("############################"+regsitesrowno);
      // if(regsitesrowno==4){visit1mom=visit1mom-1;}
   
       
       
                 HSSFCell year = regdetailsworksheetrws.createCell(cellpos1);
                    year.setCellValue(yearmonthcopy.substring(0,4));
                    year.setCellStyle(lostyle);
                                  
                    cellpos1++;
                    
                     HSSFCell month = regdetailsworksheetrws.createCell(cellpos1);
                    month.setCellValue(monthcopy.toUpperCase());
                    month.setCellStyle(lostyle);
                                  
                    cellpos1++;
                
                
                     HSSFCell facil = regdetailsworksheetrws.createCell(cellpos1);
                    facil.setCellValue(facilitycopy.toUpperCase());
                    facil.setCellStyle(data_style);
                                  
                    cellpos1++;
                
                yearmonthcopy=conn1.rs_5.getString("YEARMONTH");
  monthcopy=conn1.rs_5.getString("MONTHS");
   facilitycopy=conn1.rs_5.getString("FACILITY");
                
                if(visit1mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(visit1mom);
                    sumvisit1mom+=visit1mom;  
                    allsitescellvis1.setCellStyle(data_style);
                     //System.out.println("==========="+visit1mom+" and sum is "+sumvisit1mom );                     
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                
                       }                
                    cellpos1++;
                   
                    
                    //======VISIT 2=====
                    
                     
                if(visit2mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(visit2mom);
                    allsitescellvis1.setCellStyle(data_style);
                    sumvisit2mom+=visit2mom;                       
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                
                }                
                    cellpos1++;
                   
                    
                      //======VISIT 3=====
                     
                if(visit3mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(visit3mom);
                    allsitescellvis1.setCellStyle(data_style);
                     sumvisit3mom+=visit3mom;                      
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                
                }                
                    cellpos1++;
                    
                    //======VISIT 4=====
                     
                if(visit4mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(visit4mom);
                    allsitescellvis1.setCellStyle(data_style);
                     sumvisit4mom+=visit4mom;                      
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                
                }                
                    cellpos1++;
                  
                    //======VISIT 5 and above=====
                    
                     
                if(visit5mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(visit5mom);
                    allsitescellvis1.setCellStyle(data_style);
                    sumvisit5mom+=visit5mom;                       
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                
                }                
                    cellpos1++;
                    
    
                //=============================================live birth==================================================================
  
                             if(livebirth>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(livebirth);
                    allsitescellvis1.setCellStyle(data_style);
                   
                                            }
                                         else{
                            HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                         
                         }
   
   //=================================================Fresh Still Birth==============================================================
  cellpos1++;
  if(fsbbirth>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(fsbbirth);
                    allsitescellvis1.setCellStyle(data_style);
                   
                    
                                            }
      else{
                    HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                         
                         }
                        
                       cellpos1++;  
            
   //========================================macerated still birth=======================================================================           
                         if(msbbirth>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(msbbirth);
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
                  if(alive>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(alive);
                    allsitescellvis1.setCellStyle(data_style);
                    
                                            }
                  else {
                  HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                  }
                        
                  cellpos1++;  
                  
                  
                    if(dead>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(dead);
                    allsitescellvis1.setCellStyle(data_style);
                                                                                       }
                    else {
                        
                  HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                  }
                        
   
                
                
                
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~            
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~            
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~            
       //if not last mysql cell         
        

    
    
                    
                    
                  
    visit1mom=0;
    visit2mom=0;
    visit3mom=0;
    visit4mom=0;
    visit5mom=0;
    livebirth=0;
    fsbbirth=0;
    msbbirth=0;
    alive=0;
    dead=0; 
     
}//end of facilities loops
   
   //HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
   //HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
   //HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
   
 
 
 
 //==========================================================================================================
                 //display details in page 2
 //==========================================================================================================
 
 
     // register details 
 
                    
 //===========================================================================================================
 //===========================================================================================================
                                     //ANC VISIT DETAILS
 //===========================================================================================================
 //===========================================================================================================
                  
                    //1st visit
                    
                    
                    if(conn1.rs_5.getString("VISITNUMBER").equals("1st Visit")){

                     visit1mom++;                       
                        // System.out.println(" Visit 1 "+visit1mom+" Facil is "+conn1.rs_5.getString("FACILITY"));                 
                    }
                   
                    
                    //======VISIT 2=====
                    
                       if(conn1.rs_5.getString("VISITNUMBER").equals("2nd Visit")){
                    
                    visit2mom++;
                    //sumvisit2mom++;
                                            }
                  
                   
                    
                      //======VISIT 3=====
                    
                         if(conn1.rs_5.getString("VISITNUMBER").equals("3rd Visit")){
                    
                    visit3mom++;
                    //sumvisit3mom++;
                                            }
                   
                    
                    
                    //======VISIT 4=====
                    
                      if(conn1.rs_5.getString("VISITNUMBER").equals("4th Visit")){
                    
                    visit4mom++;
                    //sumvisit4mom++;
                                            }
                   
                  
                    //======VISIT 5 and above=====
                    
                         if(conn1.rs_5.getString("VISITNUMBER").equals("5th and above")){
                    
                    visit5mom++;
                    //sumvisit5mom++;
                                            }
                   
                    
                    
                    
 //===========================================================================================================
                    //MATERNITY REGISTER DETAILS
 //===========================================================================================================
 
                    
                    
                    
  //System.out.println("-----------------------MATERNITY DATA" +conn1.rs_4.getString("DeliveryDate"));
  System.out.println("-----------------------BIRTH TYPE " +conn1.rs_5.getString("BIRTHTYPE"));
  
  
   //=============================================live birth==================================================================
  
   if(conn1.rs_5.getString("BIRTHTYPE").equalsIgnoreCase("Live Birth")){
                    
                    livebirth++;
                    sumlivebirth++;
                                            }
                                        
   
   //=================================================Fresh Still Birth==============================================================
   if(conn1.rs_5.getString("BIRTHTYPE")!=null){ 
  if(conn1.rs_5.getString("BIRTHTYPE").equalsIgnoreCase("Fresh Still Birth")){
                     
                    fsbbirth++;
                    sumfsbbirth++;
  }
   }
     
                        
                    
            
   //========================================macerated still birth=======================================================================           
  if(conn1.rs_5.getString("BIRTHTYPE")!=null){                    
  if(conn1.rs_5.getString("BIRTHTYPE").equalsIgnoreCase("Macerated Still Birth")){
                     
                    msbbirth++;
                    summsbbirth++;
                                            }}
                        
                       
                        
                        
                        
                        
//===========================================BABY STATUS============================
                        
                        //====alive==========
 
   if(conn1.rs_5.getString("BABYSTATUS")!=null){ 
                  if(conn1.rs_5.getString("BABYSTATUS").equalsIgnoreCase("A")){
                     
                    alive++;
                    sumalive++;
                                                                               }
   }
                  
                  
                 if(conn1.rs_5.getString("BABYSTATUS")!=null){  
                  if(conn1.rs_5.getString("BABYSTATUS").equalsIgnoreCase("D")){
                    
                    
                         dead++; 
                         sumdead++; 
                    
                                                                                }}
                 
                      
                    

  
  

                 
                   

               
//begin writing to the register at this point.
             
                                      //DO THE MERGING HERE
   
   //HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
   //HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
   //HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
   //HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
   
   if(!yearcopy1.equals(conn1.rs_5.getString("YEAR"))&& yearcopypos-1>=yearcopypos1){
     
     //then merge    
    registerdetails.addMergedRegion(new CellRangeAddress(yearcopypos1, yearcopypos-1, 0, 0));                              
              
    yearcopypos1=yearcopypos;  
    yearcopy1=conn1.rs_5.getString("YEAR");
     
     } 
  
  //merge months
  
   if(!monthcopy1.equals(conn1.rs_5.getString("MONTHS"))&& monthcopypos-1>=monthcopypos1){
     
     //then merge    
    registerdetails.addMergedRegion(new CellRangeAddress(monthcopypos1, monthcopypos-1, 1, 1));                              
              
    monthcopypos1=monthcopypos;  
    monthcopy1=conn1.rs_5.getString("MONTHS");
     
     }
   
   //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
   //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
   //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    
                
                
                
                }//end of register details while loop
   
                
   //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>             
   //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>             
   //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>             
   //      Create the last row
                
            int cellpos1=0;    
                
                 HSSFCell year = regdetailsworksheetrws.createCell(cellpos1);
                    year.setCellValue(yearmonthcopy.substring(0,4));
                    year.setCellStyle(lostyle);
                                  
                    cellpos1++;
                    
                     HSSFCell month = regdetailsworksheetrws.createCell(cellpos1);
                    month.setCellValue(monthcopy.toUpperCase());
                    month.setCellStyle(lostyle);
                                  
                    cellpos1++;
                
                
                     HSSFCell facil = regdetailsworksheetrws.createCell(cellpos1);
                    facil.setCellValue(facilitycopy.toUpperCase());
                    facil.setCellStyle(data_style);
                                  
                    cellpos1++;
                
          
                
                if(visit1mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(visit1mom);
                    sumvisit1mom+=visit1mom;  
                    allsitescellvis1.setCellStyle(data_style);
                     //System.out.println("==========="+visit1mom+" and sum is "+sumvisit1mom );                     
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                
                }                
                    cellpos1++;
                   
                    
                    //======VISIT 2=====
                    
                     
                if(visit2mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(visit2mom);
                    allsitescellvis1.setCellStyle(data_style);
                    sumvisit2mom+=visit2mom;                       
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                
                }                
                    cellpos1++;
                   
                    
                      //======VISIT 3=====
                     
                if(visit3mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(visit3mom);
                    allsitescellvis1.setCellStyle(data_style);
                     sumvisit3mom+=visit3mom;                      
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                
                }                
                    cellpos1++;
                    
                    //======VISIT 4=====
                     
                if(visit4mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(visit4mom);
                    allsitescellvis1.setCellStyle(data_style);
                     sumvisit4mom+=visit4mom;                      
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                
                }                
                    cellpos1++;
                  
                    //======VISIT 5 and above=====
                    
                     
                if(visit5mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(visit5mom);
                    allsitescellvis1.setCellStyle(data_style);
                    sumvisit5mom+=visit5mom;                       
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                
                }                
                    cellpos1++;
                    
    
                //=============================================live birth==================================================================
  
                             if(livebirth>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(livebirth);
                    allsitescellvis1.setCellStyle(data_style);
                   
                                            }
                                         else{
                            HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                         
                         }
   
   //=================================================Fresh Still Birth==============================================================
  cellpos1++;
  if(fsbbirth>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(fsbbirth);
                    allsitescellvis1.setCellStyle(data_style);
                   
                    
                                            }
      else{
                    HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                         
                         }
                        
                       cellpos1++;  
            
   //========================================macerated still birth=======================================================================           
                         if(msbbirth>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(msbbirth);
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
                  if(alive>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(alive);
                    allsitescellvis1.setCellStyle(data_style);
                    
                                            }
                  else {
                  HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                  }
                        
                  cellpos1++;  
                  
                  
                    if(dead>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(dead);
                    allsitescellvis1.setCellStyle(data_style);
                                                                                       }
                    else {
                        
                  HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(data_style);
                  }
                        
   
       
                
   //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>             
   //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>             
   //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>             
                
                
    
                
                
                
                
                
                
                
                
                
                
regsitesrowno++;

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            //THE FAMOUS WHILE LOOP ENDS HERE
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            

 regdetailsworksheetrws=registerdetails.createRow(regsitesrowno);
  regdetailsworksheetrws.setHeightInPoints(22);

int cellpos=0;

                 
                    HSSFCell year1 = regdetailsworksheetrws.createCell(cellpos);
                    year1.setCellValue("");
                    year1.setCellStyle(data_style);
                                  
                    cellpos++;
                    
                     HSSFCell month1 = regdetailsworksheetrws.createCell(cellpos);
                    month1.setCellValue("");
                    month1.setCellStyle(data_style);
                                  
                    cellpos++;



                //facility
                
                
                     HSSFCell facil1 = regdetailsworksheetrws.createCell(cellpos);
                    facil1.setCellValue("TOTAL");
                    facil1.setCellStyle(style_header);
                                  
                    cellpos++;
                
                
                
                if(sumvisit1mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue(sumvisit1mom);
                   allsitescellvis1.setCellStyle(style_header);
                                          
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                
                }                
                    cellpos++;
                   
                    
                    //======VISIT 2=====
                    
                     
                if(sumvisit2mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue(sumvisit2mom);
                    allsitescellvis1.setCellStyle(style_header);
                                          
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                
                }                
                    cellpos++;
                   
                    
                      //======VISIT 3=====
                     
                if(sumvisit3mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue(sumvisit3mom);
                    allsitescellvis1.setCellStyle(style_header);
                                          
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                
                }                
                    cellpos++;
                    
                    //======VISIT 4=====
                     
                if(sumvisit4mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue(sumvisit4mom);
                    allsitescellvis1.setCellStyle(style_header);
                                          
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                
                }                
                    cellpos++;
                  
                    //======VISIT 5 and above=====
                    
                     
                if(sumvisit5mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue(sumvisit5mom);
                    allsitescellvis1.setCellStyle(style_header);
                                          
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                
                }                
                                 cellpos++;
                    
    
                //=============================================live birth==================================================================
  
                             if(sumlivebirth>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue(sumlivebirth);
                    allsitescellvis1.setCellStyle(style_header);
                   
                                            }
                                         else{
                            HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                         
                         }
   
   //=================================================Fresh Still Birth==============================================================
                                  cellpos++;
  if(sumfsbbirth>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue(sumfsbbirth);
                    allsitescellvis1.setCellStyle(style_header);
                   
                    
                                            }
      else{
                    HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                         
                         }
                        
                       cellpos++;  
            
   //========================================macerated still birth=======================================================================           
                         if(summsbbirth>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue(summsbbirth);
                    allsitescellvis1.setCellStyle(style_header);
                   
                                            }
                         else {
                             
                            HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                         
                         }
                        cellpos++;
                        
                        
                        
                        
//===========================================BABY STATUS============================
                        
                        //====alive==========
                  if(sumalive>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue(sumalive);
                    allsitescellvis1.setCellStyle(style_header);
                    
                                            }
                  else{
                  HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                  }
                        
                  cellpos++;  
                  
                  
                    if(sumdead>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue(sumdead);
                    allsitescellvis1.setCellStyle(style_header);
                                                                                       }
                    else {
                        
                  HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                  }
                        
   
               yearcopypos++;
               monthcopypos++;
 registerdetails.addMergedRegion(new CellRangeAddress(yearcopypos1, yearcopypos-1, 0, 0)); 
 registerdetails.addMergedRegion(new CellRangeAddress(monthcopypos1, monthcopypos-1, 1, 1)); 


//=====================SET CELL WIDTH FOR THE MAIN WIDTH AND==================================

           








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
        response.setHeader("Content-Disposition", "attachment; filename=FACILITY_ANC_VISITS_AND_MATERNITY_" + dat1 + ".xls");
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
