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
public class MOBILEANDREGSITERSUMMARY extends HttpServlet {

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
   
   String additiaonalparrams="EXTRACT(YEAR FROM VisitDate) ='"+Year+"' ";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Year="";
            additiaonalparrams=" EXTRACT(YEAR FROM VisitDate) ='"+Year+"' ";
           
            
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
    
    
    
            if(Year.equals("")){additiaonalparrams="";}
            
            wb = new HSSFWorkbook();
             String columnheaders[]={"FACILITY NAME","CHW'S NAME","CHWS PHONE NO."};
            
            
             String anccolumnheaders="ANC VISITS";
             String matcolumnheaders="MATERNITY REGISTER DETAILS";
             String babycolumnheaders="BABY STATUS";
             String deliverycolumnheaders="BIRTH TYPE";
             String compcolumnheaders="DELIVERY COMPLICATION";
             String registerdetailsheaders2[]={"MOTHER DETAILS",anccolumnheaders,"","","","",deliverycolumnheaders,"","",babycolumnheaders,""};
             
             String registerdetailsheaders3[]={"FACILITY","1st VISIT","2nd VISIT","3rd VISIT","4th VISIT","5th AND ABOVE","LIVE BIRTH","FRESH STILL BIRTH","MACERATED STILL BIRTH","ALIVE","DEAD"};
             
             
           maxmerging=columnheaders.length;
           
           maincountqry="select count(*) as curcount from mother_details where "+additiaonalparrams+"  facilityname LIKE ";
           
            String Statisticsarr[] = {"FACILITY", "PREGNANT MOTHERS", "DELIVERED MOTHERS", "TOTAL"};


            
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

          
            
               //=======STATISTICS HEADER=======================================     

            HSSFRow rw01 = registerdetails.createRow(0);
            rw01.setHeightInPoints(35);
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
       

conn.rs_5=conn.st_5.executeQuery(facils);

while(conn.rs_5.next()){
regsitesrowno++;
                String mainqr = "Select anc_no, DOB ,FName ,SName,LName,PhoneNo ,NOKPhoneNo,LocationID,Gest_to_firstANC,Anc_visit,VisitDate,delivery_date,status_id,Age,Gravida,parity,maritalStatus,education,occupation,nok_consent,languagePreferred,facilityname, ChwID from mother_details  where facilityname like '"+conn.rs_5.getString(1) +"'  order by FName desc";

                conn.rs = conn.st.executeQuery(mainqr);

                System.out.println("MAIN QUERY___________"+mainqr);
                
                int rowno = 2;

            
                
                while (conn.rs.next()) {
  
                    rowno++;
                    allsitesrowno ++;
                    //if the mother contains data in the anc/mat regsiter
  String checkexistance1="select * from mother_details join atoh on mother_details.motherID=atoh.motherid where ancno='"+conn.rs.getString("anc_no")+"' and facilityname LIKE '"+conn.rs.getString("facilityname")+"' and atoh.Village !=''  group by mother_details.motherID order by mother_details.FName";
    
  
  
  conn1.rs_6=conn1.st_6.executeQuery(checkexistance1);
  int cellpos1=0;
  while(conn1.rs_6.next()){
 //increment the register
  
  
  //worksheet with either anc or maternity    
  regdetailsworksheetrws=registerdetails.createRow(regsitesrowno);
  regdetailsworksheetrws.setHeightInPoints(25);
  
 System.out.println("_________________=========="+regsitesrowno); 
 
 
 
 
 
 //==========================================================================================================
                 //display details in page 2
 //==========================================================================================================
 
 
     // register details 
 
                    
                    
                    
                    //====NOW GET THE ANC DETAILS
                    
                    
 //===========================================================================================================
 //===========================================================================================================
                                     //ANC VISIT DETAILS
 //===========================================================================================================
 //===========================================================================================================
                  
                    //1st visit
                    
                    String anc1="select * from atoh where (select max(visit_no) from atoh where motherid='"+conn1.rs_6.getString("motherID")+"' ) ='1' and motherid='"+conn1.rs_6.getString("motherID")+"'";
                    System.out.println(anc1);            
                    conn1.rs_5=conn1.st_5.executeQuery(anc1);
                    if(conn1.rs_5.next()){

                    
                     visit1mom++;                       
                     sumvisit1mom++;                       
                    }
                   
                    
                    //======VISIT 2=====
                    
                     String anc2="select * from atoh where (select max(visit_no)from atoh where motherid='"+conn1.rs_6.getString("motherID")+"'  )='2' and motherid='"+conn1.rs_6.getString("motherID")+"'";
                         System.out.println(anc2);            
                    conn1.rs_5=conn1.st_5.executeQuery(anc2);
                    if(conn1.rs_5.next()){
                    
                    visit2mom++;
                    sumvisit2mom++;
                                            }
                  
                   
                    
                      //======VISIT 3=====
                    
                     String anc3="select * from atoh where (select max(visit_no)from atoh where motherid='"+conn1.rs_6.getString("motherID")+"'  )='3' and motherid='"+conn1.rs_6.getString("motherID")+"'";
                            System.out.println(anc3);         
                    conn1.rs_5=conn1.st_5.executeQuery(anc3);
                    if(conn1.rs_5.next()){
                    
                    visit3mom++;
                    sumvisit3mom++;
                                            }
                   
                    
                    
                    //======VISIT 4=====
                    
                     String anc4="select * from atoh where (select max(visit_no) from atoh where motherid='"+conn1.rs_6.getString("motherID")+"')='4' and motherid='"+conn1.rs_6.getString("motherID")+"'";
                                
                    conn1.rs_5=conn1.st_5.executeQuery(anc4);
                    if(conn1.rs_5.next()){
                    
                    visit4mom++;
                    sumvisit4mom++;
                                            }
                   
                  
                    //======VISIT 5 and above=====
                    
                     String anc5="select * from atoh where (select max(visit_no) from atoh where motherid='"+conn1.rs_6.getString("motherID")+"') > 4 and motherid='"+conn1.rs_6.getString("motherID")+"'";
                     
                     
                     
                    conn1.rs_5=conn1.st_5.executeQuery(anc5);
                    if(conn1.rs_5.next()){
                    
                    visit5mom++;
                    sumvisit5mom++;
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
                    
                    livebirth++;
                    sumlivebirth++;
                                            }
                                        
   
   //=================================================Fresh Still Birth==============================================================
  
  if(conn1.rs_4.getString("live_birth").equalsIgnoreCase("Fresh Still Birth")){
                     
                    fsbbirth++;
                    sumfsbbirth++;
                    
                                            }
     
                        
                    
            
   //========================================macerated still birth=======================================================================           
                         if(conn1.rs_4.getString("live_birth").equalsIgnoreCase("Macerated Still Birth")){
                     
                    msbbirth++;
                    summsbbirth++;
                                            }
                        
                       
                        
                        
                        
                        
//===========================================BABY STATUS============================
                        
                        //====alive==========
                  if(conn1.rs_4.getString("ConditionAfterDelivery").equalsIgnoreCase("A")){
                     
                    alive++;
                    sumalive++;
                                            }
                  
                  
                  
                    if(conn1.rs_4.getString("ConditionAfterDelivery").equalsIgnoreCase("D")){
                    
                    
                         dead++; 
                         sumdead++; 
                    
                    }
                 
                        
                  
                                            }
                    
                    
                    
  }
  
  

                    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                    //DISPLAYING RAW DATA INTO DIFFERENT WORK SHEETS

                    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$      

                   

                }//end of register details while loop
//begin writing to the register at this point.
                
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~            
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~            
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~            
   
                int cellpos1=0;
                //facility
                
                
                     HSSFCell facil = regdetailsworksheetrws.createCell(cellpos1);
                    facil.setCellValue(conn.rs_5.getString(1).toString().toUpperCase());
                    facil.setCellStyle(data_style);
                                  
                    cellpos1++;
                
                
                
                if(visit1mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(visit1mom);
                    allsitescellvis1.setCellStyle(data_style);
                                          
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
                  else{
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
regsitesrowno++;

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            //THE FAMOUS WHILE LOOP ENDS HERE
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            

 regdetailsworksheetrws=registerdetails.createRow(regsitesrowno);
  regdetailsworksheetrws.setHeightInPoints(25);

int cellpos1=0;
                //facility
                
                
                     HSSFCell facil = regdetailsworksheetrws.createCell(cellpos1);
                    facil.setCellValue("TOTAL");
                    facil.setCellStyle(style_header);
                                  
                    cellpos1++;
                
                
                
                if(sumvisit1mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(sumvisit1mom);
                    allsitescellvis1.setCellStyle(style_header);
                                          
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                
                }                
                    cellpos1++;
                   
                    
                    //======VISIT 2=====
                    
                     
                if(sumvisit2mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(sumvisit2mom);
                    allsitescellvis1.setCellStyle(style_header);
                                          
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                
                }                
                    cellpos1++;
                   
                    
                      //======VISIT 3=====
                     
                if(sumvisit3mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(sumvisit3mom);
                    allsitescellvis1.setCellStyle(style_header);
                                          
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                
                }                
                    cellpos1++;
                    
                    //======VISIT 4=====
                     
                if(sumvisit4mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(sumvisit4mom);
                    allsitescellvis1.setCellStyle(style_header);
                                          
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                
                }                
                    cellpos1++;
                  
                    //======VISIT 5 and above=====
                    
                     
                if(sumvisit5mom>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(sumvisit5mom);
                    allsitescellvis1.setCellStyle(style_header);
                                          
                    }
                else {
                
                 HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                
                }                
                    cellpos1++;
                    
    
                //=============================================live birth==================================================================
  
                             if(sumlivebirth>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(sumlivebirth);
                    allsitescellvis1.setCellStyle(style_header);
                   
                                            }
                                         else{
                            HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                         
                         }
   
   //=================================================Fresh Still Birth==============================================================
  cellpos1++;
  if(sumfsbbirth>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(sumfsbbirth);
                    allsitescellvis1.setCellStyle(style_header);
                   
                    
                                            }
      else{
                    HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                         
                         }
                        
                       cellpos1++;  
            
   //========================================macerated still birth=======================================================================           
                         if(summsbbirth>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(summsbbirth);
                    allsitescellvis1.setCellStyle(style_header);
                   
                                            }
                         else {
                             
                            HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                         
                         }
                        cellpos1++;
                        
                        
                        
                        
//===========================================BABY STATUS============================
                        
                        //====alive==========
                  if(sumalive>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(sumalive);
                    allsitescellvis1.setCellStyle(style_header);
                    
                                            }
                  else{
                  HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                  }
                        
                  cellpos1++;  
                  
                  
                    if(sumdead>0){
                     HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue(sumdead);
                    allsitescellvis1.setCellStyle(style_header);
                                                                                       }
                    else {
                        
                  HSSFCell allsitescellvis1 = regdetailsworksheetrws.createCell(cellpos1);
                    allsitescellvis1.setCellValue("");
                    allsitescellvis1.setCellStyle(style_header);
                  }
                        
   
               



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
        response.setHeader("Content-Disposition", "attachment; filename=MOBILE_ANC_MATERNITY_VISITS" + dat1 + ".xls");
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
