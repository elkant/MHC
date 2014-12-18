/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package REPORTS;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
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
public class allregistersummaries extends HttpServlet {

    HSSFWorkbook wb;
    int maxmerging=0;
    String maincountqry="";
    String ancmaincountqry="";
    String pncmaincountqry="";
    String matmaincountqry="";
    String groupbyqry="";
    String Year="";
    int count=0;
   
   String additiaonalparrams=" EXTRACT(YEAR FROM VisitDate) ='"+Year+"' and";
   String ancadditiaonalparrams=" EXTRACT(YEAR FROM DateofVisit) ='"+Year+"' and";
   String matadditiaonalparrams=" EXTRACT(YEAR FROM AdmissionDate) ='"+Year+"' and";
   String pncadditiaonalparrams=" EXTRACT(YEAR FROM VisitDate) ='"+Year+"' and";
   
   String Statisticsarr[] = {"FACILITY", "ANC MOTHERS", "MATERNITY MOTHERS", "PNC MOTHERS"};
   dbConn conn =null;
   HSSFSheet statistics =null;
    CellStyle data_style =null;
     CellStyle innerdata_style =null;
     CellStyle th_style =null;
     CellStyle style_header =null;
   
   
            int TOTALANCMOMS = 0;
            int TOTALMATMOMS = 0;
            int TOTALPNCMOMS = 0;
            int TOTALNODATEMOMS = 0;
            int TOTALTOTALS = 0;
            int curheaderrowno=0;
           
            int curcolumnheader=1;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
           
            String yrarray[]={"2012","2013","2014"};
            
            if(request.getParameterValues("year")!=null){
            yrarray=request.getParameterValues("year");
            }
            
            TOTALTOTALS = 0;
            curheaderrowno=0;
           
             curcolumnheader=1;
           
            
            if(Year.equals("")){additiaonalparrams="";}
            
            wb = new HSSFWorkbook();
             String columnheaders[]={"ANC NUMBER","FIRST NAME","MIDDLE NAME","LAST NAME","PHONE NUMBER","NOK PHONE NUMBER","DELIVERY DATE","VISIT DATE","FACILITY NAME"};
                
           maxmerging=columnheaders.length;
           
          
           groupbyqry=" group by motherid ";
           
           
           
            


            count=0;
            //=====================================



            //==============================CREATE WORKBOOK AND SHEETS FOR EACH SITE  



             statistics = wb.createSheet("STATISTICS");
            HSSFSheet allsites = wb.createSheet("ALL SITES");

            HSSFSheet emining = wb.createSheet("EMINING");
            HSSFSheet ngubereti = wb.createSheet("NGUBERETI");
            HSSFSheet solian = wb.createSheet("SOLIAN");
            HSSFSheet torongo = wb.createSheet("CONTROL SITES");


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

             style_header = wb.createCellStyle();
            style_header.setFont(font_header);
            style_header.setWrapText(true);
            style_header.setAlignment(style_header.ALIGN_CENTER_SELECTION);
            style_header.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
            style_header.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


            //========TABLE HEADER STYLING===========================
             th_style = wb.createCellStyle();
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

             data_style = wb.createCellStyle();
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

             innerdata_style = wb.createCellStyle();
            innerdata_style.setFont(datafont);
            innerdata_style.setWrapText(true);
            innerdata_style.setAlignment(data_style.ALIGN_CENTER);
            innerdata_style.setFillForegroundColor(HSSFColor.WHITE.index);
            innerdata_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            innerdata_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            innerdata_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            innerdata_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            innerdata_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

  //=======INNER DATA STYLING=========================== 

            CellStyle aggregregatedata_style = wb.createCellStyle();
            aggregregatedata_style.setFont(datafont);
            aggregregatedata_style.setWrapText(true);
            aggregregatedata_style.setAlignment(data_style.ALIGN_CENTER);
            aggregregatedata_style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
            aggregregatedata_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            aggregregatedata_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            aggregregatedata_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            aggregregatedata_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            aggregregatedata_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

           


            //===============ALL SITES HEADER===============================

            HSSFRow rw0 = allsites.createRow(0);
            rw0.setHeightInPoints(35);
            HSSFCell rw0cell0 = rw0.createCell(1);
            rw0cell0.setCellValue("KOIBATEK MNCH DATA FOR SOLIAN , NGUBERETI , EMINING AND TORONGO HEALTH FACILITIES ");
            rw0cell0.setCellStyle(style_header);
            
            
            

            //===============SOLIAN DATA HEADER=================================      


            HSSFRow solheader = solian.createRow(0);
            solheader.setHeightInPoints(35);
            HSSFCell solheadercell0 = solheader.createCell(1);
            solheadercell0.setCellValue("KOIBATEK MNCH DATA FOR SOLIAN HEALTH FACILITY ");
            solheadercell0.setCellStyle(style_header);



            //===============EMINING DATA HEADER=================================      


            HSSFRow eminiheader = emining.createRow(0);
            eminiheader.setHeightInPoints(35);
            HSSFCell emiheadercell0 = eminiheader.createCell(1);
            emiheadercell0.setCellValue("KOIBATEK MNCH DATA FOR EMINING HEALTH FACILITY ");
            emiheadercell0.setCellStyle(style_header);


            //===============NGUBERETI DATA HEADER=================================      


            HSSFRow ngubeheader = ngubereti.createRow(0);
            ngubeheader.setHeightInPoints(35);
            HSSFCell nguheadercell0 = ngubeheader.createCell(1);
            nguheadercell0.setCellValue("KOIBATEK MNCH DATA FOR NGUBERETI HEALTH FACILITY ");
            nguheadercell0.setCellStyle(style_header);




            //===============CONTROL DATA HEADER=================================      


            HSSFRow controlheader = torongo.createRow(0);
            controlheader.setHeightInPoints(35);
            HSSFCell conheadercell0 = controlheader.createCell(1);
            conheadercell0.setCellValue("KOIBATEK MNCH DATA FOR TORONGO, SOLIAN, SIMOTWET HEALTH FACILITY ");
            conheadercell0.setCellStyle(style_header);


        //===========================MAIN SITE COLUMN HEADERS========================================
           HSSFRow allsitescolumnheader = allsites.createRow(1);
                allsitescolumnheader.setHeightInPoints(30);
                HSSFCell rwcolheader=null;
                
               
                for(int d=0;d<maxmerging;d++){
                 rwcolheader = allsitescolumnheader.createCell(d+1);
                  rwcolheader.setCellValue(columnheaders[d]);
                  rwcolheader.setCellStyle(th_style);
                }
                for (int a = 1; a <=maxmerging; a++) {
                        allsites.setColumnWidth(a, 4500);
                    }


               






             conn = new dbConn();


            //===============DISTINCT FACILITY NAMES===========

            
            

           

            HSSFRow rw2 = null;

            HSSFRow rwy = null;
            HSSFRow rwy1 = null;
            HSSFRow lastatatisticsrow = null;

//[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[]][[[[[[[[[[[[[[[[[[[[[[]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]
//[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[]][[[[[[[[[[[[[[[[[[[[[[]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]
           //MAKE THIS PART TO BE LOOPING IN THE STATISTICS PAGE UNTIL IT OUTPUTS DATA FOR ALL THE SELECTED YEARS
//[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[]][[[[[[[[[[[[[[[[[[[[[[]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]
//[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[]][[[[[[[[[[[[[[[[[[[[[[]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]
            
            
            
            for(int d=0;d<yrarray.length;d++){
                
           
            //call the method for generating data reports
            createsummaryreport(yrarray[d]);
            
            
            
            count=count+2;
             curheaderrowno=count;
             curcolumnheader=count;
            
            }
            

           count=count-1; 
            
             HSSFRow   laststatisticsrow = statistics.createRow(count );
         laststatisticsrow.setHeightInPoints(30);


            //=====fACILITY NAMES COL==============

            HSSFCell facilcell = laststatisticsrow.createCell(4);
            //====ANC MUMS ROWS=====================
            HSSFCell anccol = laststatisticsrow.createCell(5);

            
            //======MAT COLS===============
            HSSFCell matcol = laststatisticsrow.createCell(6);

            
            //======PNC COLS========================
            HSSFCell pnccol = laststatisticsrow.createCell(7);

            
            //=======TOTAL COLS=====================
            HSSFCell totalcol = laststatisticsrow.createCell(8);

            //====TOTAL COL======   
            facilcell.setCellValue("AGGREGATE TOTAL");
            facilcell.setCellStyle(aggregregatedata_style);

            //====ANC TOTALS======
            anccol.setCellValue(TOTALANCMOMS);
            anccol.setCellStyle(aggregregatedata_style);
            
            
             //====MAT TOTALS======
            matcol.setCellValue(TOTALMATMOMS);
            matcol.setCellStyle(aggregregatedata_style);

            //====PNC TOTALS======
            pnccol.setCellValue(TOTALPNCMOMS);
            pnccol.setCellStyle(aggregregatedata_style);

           

            //=====TOTAL TOTALS
            //totalcol.setCellValue(TOTALTOTALS);

            //totalcol.setCellStyle(aggregregatedata_style);         
                
                
            
            
            
            
            
       //[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]

            
            
            
            
            
            
            
            
            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            //THE FAMOUS WHILE LOOP STARTS HERE
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
           

             int count1 = 0;
           
            
            //=====this row no variable is used for all sites and will keep on incementing 
               int allsitesrowno = 1;
               
            //=====this variable initializes a row for the all sites worksheet   
               HSSFRow allsitesworksheetrws = null;
 String facils = "Select  Distinct facilityname from mother_details order by facilityname asc";

            conn.rs1 = conn.st1.executeQuery(facils);
            while (conn.rs1.next()) {

                count1++;
                
                System.out.println("__________________________FACILITY NAME " + conn.rs1.getString("facilityname") + "______________________");


                //determine which data goes to which worksheet.
                HSSFRow rw1 = null;

                if (conn.rs1.getString("facilityname").equalsIgnoreCase("EMINING") || conn.rs1.getString("facilityname").equalsIgnoreCase("EMMINING")) {
                    rw1 = emining.createRow(1);
                    for (int a = 1; a <=maxmerging; a++) {
                        emining.setColumnWidth(a, 4500);
                    }
                } //===Ngubereti
                else if (conn.rs1.getString("facilityname").equalsIgnoreCase("Ngubereti") || conn.rs1.getString("facilityname").equalsIgnoreCase("Ngubureti")) {
                    rw1 = ngubereti.createRow(1);
                    for (int a = 1; a <= maxmerging; a++) {
                        ngubereti.setColumnWidth(a, 4500);
                    }
                } //====solian
                else if (conn.rs1.getString("facilityname").equalsIgnoreCase("Solian")) {
                    rw1 = solian.createRow(1);
                    for (int a = 1; a <= maxmerging; a++) {
                        solian.setColumnWidth(a, 4500);
                    }
                } //====control sites
                else if (conn.rs1.getString("facilityname").equalsIgnoreCase("Torongo") || conn.rs1.getString("facilityname").equalsIgnoreCase("Simotwet") || conn.rs1.getString("facilityname").equalsIgnoreCase("Timboroa")) {
                    rw1 = torongo.createRow(1);
                    for (int a = 1; a <= maxmerging; a++) {
                        torongo.setColumnWidth(a, 4500);
                    }
                }
                else{
                 rw1 = torongo.createRow(1);
                    for (int a = 1; a <= maxmerging; a++) {
                        torongo.setColumnWidth(a, 4500);
                    }
                
                }
               //=========
                

//=================DEFINE THE COLUMN  HEADERS PER EACH FACILITY SHEET========================

                 HSSFCell rwcolheader1=null;
                
                for(int d=0;d<maxmerging;d++){
                 rwcolheader1 = rw1.createCell(d+1);
                  rwcolheader1.setCellValue(columnheaders[d]);
                  rwcolheader1.setCellStyle(th_style);
                }


                allsites.addMergedRegion(new CellRangeAddress(0, 0, 1, maxmerging));
                solian.addMergedRegion(new CellRangeAddress(0, 0, 1, maxmerging));
                ngubereti.addMergedRegion(new CellRangeAddress(0, 0, 1, maxmerging));
                emining.addMergedRegion(new CellRangeAddress(0, 0, 1, maxmerging));
                torongo.addMergedRegion(new CellRangeAddress(0, 0, 1, maxmerging));
              //  statistics.addMergedRegion(new CellRangeAddress(0, 0, 4, 8));
















                String Facilityname = conn.rs1.getString("facilityname");
                
                
                

                //============STATISTICS PAGE THE DATA PART=================================

                
//==============================LOOP THROUGH THE THREE YEARS ===================
                

               // statistics.setColumnWidth(4 + count, 5000);

                //=============create dynamic rows  

                
            
           
            
          //[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]  
          //[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]
          //end of yearly loop  
                
                
                
                String Currentfacility = conn.rs1.getString("facilityname");



                String mainqr = "Select anc_no,DOB ,FName ,SName,LName,PhoneNo ,NOKPhoneNo,LocationID,Gest_to_firstANC,Anc_visit,VisitDate,delivery_date,status_id,Age,Gravida,parity,maritalStatus,education,occupation,nok_consent,languagePreferred,facilityname from mother_details where  facilityname='" + Currentfacility + "' order by delivery_date desc";

                conn.rs = conn.st.executeQuery(mainqr);

                int rowno = 1;

                while (conn.rs.next()) {

                    rowno++;
                    allsitesrowno ++;

                    System.out.println(conn.rs.getString("anc_no") + " " + conn.rs.getString("DOB") + " " + conn.rs.getString("FName") + " " + conn.rs.getString("SName") + " " + conn.rs.getString("PhoneNo") + " " + conn.rs.getString("facilityname"));
                    //create the various cells and add data to the cells

                    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                    //DISPLAYING RAW DATA INTO DIFFERENT WORK SHEETS

                    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$      

                    //determine which data goes to which worksheet.
                    HSSFRow allsitesrw = null;

                    if (conn.rs.getString("facilityname").equalsIgnoreCase("EMINING") || conn.rs.getString("facilityname").equalsIgnoreCase("EMMINING")) {
                        allsitesrw = emining.createRow(rowno);
                        
                      } //===Ngubereti
                    else if (conn.rs.getString("facilityname").equalsIgnoreCase("Ngubereti") || conn.rs.getString("facilityname").equalsIgnoreCase("Ngubureti")) {
                        allsitesrw = ngubereti.createRow(rowno);
                      } //====solian
                    else if (conn.rs.getString("facilityname").equalsIgnoreCase("Solian")) {
                        allsitesrw = solian.createRow(rowno);
                    } //====control sites
                    else if (conn.rs.getString("facilityname").equalsIgnoreCase("Torongo") || conn.rs.getString("facilityname").equalsIgnoreCase("Simotwet") || conn.rs.getString("facilityname").equalsIgnoreCase("Timboroa")) {
                        allsitesrw = torongo.createRow(rowno);
                    }
                    else{
                      allsitesrw = torongo.createRow(rowno);
                    
                    }


                    allsitesrw.setHeightInPoints(25);
                    allsitesworksheetrws=allsites.createRow(allsitesrowno);
                    allsitesworksheetrws.setHeightInPoints(25);
                    //ANC NUMBER
                    
                    //specific site workbook
                    HSSFCell allsitescellx = allsitesrw.createCell(1);
                    allsitescellx.setCellValue(conn.rs.getString("anc_no"));
                    allsitescellx.setCellStyle(data_style);

                    if (conn.rs.getString("anc_no") == null || conn.rs.getString("anc_no").equals("")) {
                        allsitescellx.setCellValue("NO ANC");
                    }

                    //all sites worksheet
                    HSSFCell allsitescellxx = allsitesworksheetrws.createCell(1);
                    allsitescellxx.setCellValue(conn.rs.getString("anc_no"));
                    allsitescellxx.setCellStyle(data_style);

                    if (conn.rs.getString("anc_no") == null || conn.rs.getString("anc_no").equals("")) {
                        allsitescellxx.setCellValue("NO ANC");
                    }

                    
                    //FIRST NAME

                                        
                    HSSFCell allsitescell1 = allsitesrw.createCell(2);
                    allsitescell1.setCellValue(conn.rs.getString("FName").toUpperCase());
                    allsitescell1.setCellStyle(data_style);
                    if (conn.rs.getString("FName").equals("null")) {
                        allsitescell1.setCellValue("");
                                                                    }
                    //all sites worksheet
                     HSSFCell allsitescell11 = allsitesworksheetrws.createCell(2);
                    allsitescell11.setCellValue(conn.rs.getString("FName").toUpperCase());
                    allsitescell11.setCellStyle(data_style);
                    if (conn.rs.getString("FName").equals("null")) {
                        allsitescell11.setCellValue("");
                    }
                    

                    //MIDDLE NAME

                    HSSFCell allsitescell2 = allsitesrw.createCell(3);
                    allsitescell2.setCellValue(conn.rs.getString("SName").toUpperCase());
                    allsitescell2.setCellStyle(data_style);
                    if (conn.rs.getString("SName").equals("null")) {
                        allsitescell2.setCellValue("");
                    }
                    //all sites worksheet
                      HSSFCell allsitescell22 = allsitesworksheetrws.createCell(3);
                    allsitescell22.setCellValue(conn.rs.getString("SName").toUpperCase());
                    allsitescell22.setCellStyle(data_style);
                    if (conn.rs.getString("SName").equals("null")) {
                        allsitescell22.setCellValue("");
                    }
                    
                    
                    //LAST NAME

                    HSSFCell allsitescell3 = allsitesrw.createCell(4);
                    allsitescell3.setCellValue(conn.rs.getString("LName").toUpperCase());
                    allsitescell3.setCellStyle(data_style);
                    if (conn.rs.getString("LName").equals("null")) {
                        allsitescell3.setCellValue("");
                    }
                    
                    //all sites worksheet

                    HSSFCell allsitescell33 = allsitesworksheetrws.createCell(4);
                    allsitescell33.setCellValue(conn.rs.getString("LName").toUpperCase());
                    allsitescell33.setCellStyle(data_style);
                    if (conn.rs.getString("LName").equals("null")) {
                        allsitescell33.setCellValue("");
                    }
                    
                    
                    //PHONE NUMBER

                    HSSFCell allsitescell4 = allsitesrw.createCell(5);
                    allsitescell4.setCellValue(conn.rs.getString("PhoneNo"));
                    allsitescell4.setCellStyle(data_style);
                    if (conn.rs.getString("PhoneNo").equals("0")) {
                        allsitescell4.setCellValue("NO NUMBER");
                    }
                    
                    //all sites worksheet
                     HSSFCell allsitescell44 = allsitesworksheetrws.createCell(5);
                    allsitescell44.setCellValue(conn.rs.getString("PhoneNo"));
                    allsitescell44.setCellStyle(data_style);
                    if (conn.rs.getString("PhoneNo").equals("0")) {
                        allsitescell44.setCellValue("NO NUMBER");
                    }
                    

                    //NOK PHONE NUMBER

                    HSSFCell allsitescell5 = allsitesrw.createCell(6);
                    allsitescell5.setCellValue(conn.rs.getString("NOKPhoneNo"));
                    allsitescell5.setCellStyle(data_style);
                    if (conn.rs.getString("NOKPhoneNo").equals("null")) {
                        allsitescell5.setCellValue("NO NUMBER");
                    }

                    //all sites worksheet
                    HSSFCell allsitescell55 = allsitesworksheetrws.createCell(6);
                    allsitescell55.setCellValue(conn.rs.getString("NOKPhoneNo"));
                    allsitescell55.setCellStyle(data_style);
                    if (conn.rs.getString("NOKPhoneNo").equals("null")) {
                        allsitescell55.setCellValue("NO NUMBER");
                    }
                    

                    //DELIVERY DATE

                    HSSFCell allsitescell6 = allsitesrw.createCell(7);
                    allsitescell6.setCellValue(conn.rs.getString("delivery_date"));
                    allsitescell6.setCellStyle(data_style);
                    if (conn.rs.getString("delivery_date").equals("null") || conn.rs.getString("delivery_date").equals("-") || conn.rs.getString("delivery_date").equals("")) {
                        allsitescell6.setCellValue("NO DATE");
                    }


                    //all sites worksheet
                    HSSFCell allsitescell66 = allsitesworksheetrws.createCell(7);
                    allsitescell66.setCellValue(conn.rs.getString("delivery_date"));
                    allsitescell66.setCellStyle(data_style);
                    if (conn.rs.getString("delivery_date").equals("null") || conn.rs.getString("delivery_date").equals("-") || conn.rs.getString("delivery_date").equals("")) {
                        allsitescell66.setCellValue("NO DATE");
                    }

                    
                    //VISIT DATE

                    HSSFCell allsitescell7 = allsitesrw.createCell(8);
                    allsitescell7.setCellValue(conn.rs.getString("visitDate"));
                    allsitescell7.setCellStyle(data_style);
                    if (conn.rs.getString("visitDate").equals("null") || conn.rs.getString("visitDate").equals("") || conn.rs.getString("visitDate").equals("")) {
                        allsitescell7.setCellValue("NO DATE");
                    }

                    //all sites worksheet
                     HSSFCell allsitescell77 = allsitesworksheetrws.createCell(8);
                    allsitescell77.setCellValue(conn.rs.getString("visitDate"));
                    allsitescell77.setCellStyle(data_style);
                    if (conn.rs.getString("visitDate").equals("null") || conn.rs.getString("visitDate").equals("") || conn.rs.getString("visitDate").equals("")) {
                        allsitescell77.setCellValue("NO DATE");
                    }
                    
                    
                    
                    //FACILITY NAME

                    HSSFCell allsitescell8 = allsitesrw.createCell(9);
                    allsitescell8.setCellValue(conn.rs.getString("facilityname").toUpperCase());
                    allsitescell8.setCellStyle(data_style);
                    
                    //all sites worksheet
                    HSSFCell allsitescell88 = allsitesworksheetrws.createCell(9);
                    allsitescell88.setCellValue(conn.rs.getString("facilityname").toUpperCase());
                    allsitescell88.setCellStyle(data_style);


                }//end of while loop



            }



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
            Logger.getLogger(allregistersummaries.class.getName()).log(Level.SEVERE, null, ex);
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
        response.setHeader("Content-Disposition", "attachment; filename=ALL_REGISTER_SUMMARIES_" + dat1 + ".xls");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();







    }
    
    
    public int createsummaryreport(String yr){
        try {
            String facils = " Select  Distinct facilityname from mother_details order by facilityname asc";
//get the previous year
            int prevyear=0;
            
            
            prevyear=Integer.parseInt(yr)-1;
            
           System.out.println("previous year is "+prevyear); 
           
           
           HSSFRow  rw2 = statistics.createRow(count+1);
            rw2.setHeightInPoints(30);

         
            
         
                       
                   //=======STATISTICS HEADERS=======================================     

                  
            
            HSSFRow rw01 = statistics.createRow(count);
            rw01.setHeightInPoints(35);
            HSSFCell rw01cell01 = rw01.createCell(4);
            rw01cell01.setCellValue("KOIBATEK MNCH DATA SUMMARY  FOR YEAR _ "+yr+"");
            rw01cell01.setCellStyle(style_header);
            statistics.addMergedRegion(new CellRangeAddress(curheaderrowno, curheaderrowno, 4, ((Statisticsarr.length-1)+4)));
            
               //=====if its the first row create the headers first first==========

            //==FACILITY NAME  || ANC MOTHERS ||MATERNITY MOTHERS|| PNC MOTHERS   ||  TOTAL

         
            
               for (int a = 0; a < Statisticsarr.length; a++) {

                statistics.setColumnWidth(a+4, 5700);
                HSSFCell rwycell1 = rw2.createCell(4 + a);
                rwycell1.setCellValue(Statisticsarr[a]);
                rwycell1.setCellStyle(th_style);
                
                                }
            
            
              
                       //add the query part with the year at this point
                        
                        
                   additiaonalparrams=" EXTRACT(YEAR FROM VisitDate) ='"+yr+"' and EXTRACT(YEAR FROM VisitDate) > "+prevyear+" and";
                   ancadditiaonalparrams=" EXTRACT(YEAR FROM DateofVisit) ='"+yr+"' and EXTRACT(YEAR FROM DateofVisit) > "+prevyear+" and ";
                   matadditiaonalparrams=" EXTRACT(YEAR FROM AdmissionDate) ='"+yr+"' and EXTRACT(YEAR FROM AdmissionDate) > "+prevyear+" and";
                   pncadditiaonalparrams=" EXTRACT(YEAR FROM postnat_atof.VisitDate) ='"+yr+"' and EXTRACT(YEAR FROM postnat_atof.VisitDate) > "+prevyear+" and";
                   
                   
                     
                   maincountqry=" select count(distinct motherID) as curcount from mother_details where "+additiaonalparrams+"  facilityname LIKE ";
                   ancmaincountqry=" select count(distinct atoh.motherid) as curcount from mother_details join atoh on mother_details.motherID=atoh.motherid where "+ancadditiaonalparrams+"  facilityname LIKE ";
                   pncmaincountqry=" select count(distinct postnat_atof.motherid) as curcount from mother_details join postnat_atof on mother_details.motherID=postnat_atof.motherid where "+pncadditiaonalparrams+"  facilityname LIKE ";
                   matmaincountqry=" select count(distinct mat_atoh.motherid) as curcount from mother_details join mat_atoh on mother_details.motherID=mat_atoh.motherid where "+matadditiaonalparrams+"  facilityname LIKE ";
                  
                   
                   
            int YEARLYTOTALANCMOMS = 0;
            int YEARLYTOTALMATMOMS = 0;
            int YEARLYTOTALPNCMOMS = 0;
        
            int YEARLYTOTALTOTALS = 0;
                   
                   
                   
                   conn.rs1=conn.st1.executeQuery(facils);
            
                   System.out.println("count is "+count);
                   
                while(conn.rs1.next()){    
             
                    
                   
                    count++;
                    
                    String Facilityname=conn.rs1.getString(1);
                    
                        //starting column two    
                       HSSFRow rwy1 = statistics.createRow(count + 1);
                       
                        
                        rwy1.setHeightInPoints(30);

                        //=====fACILITY NAMES COL==============

                        HSSFCell facilcell = rwy1.createCell(4);
                        //====ANC MUMS ROWS=====================
                        HSSFCell anccol = rwy1.createCell(5);

                        //====ANC MUMS ROWS=====================
                        HSSFCell matcol = rwy1.createCell(6);
                        
                        
                        //======PNC COLS========================
                        HSSFCell pnccol = rwy1.createCell(7);

                       

                        //=======TOTAL COLS=====================
                        HSSFCell totalcol = rwy1.createCell(8);






                        facilcell.setCellValue(conn.rs1.getString("facilityname").toUpperCase());
                        facilcell.setCellStyle(data_style);

                        //get the summaries for this table inside
                        //========================ANC NUMBER SUMMARIES=================================
                        String ancqr = ancmaincountqry+" '" + conn.rs1.getString("facilityname") + "'";

                        System.out.println("================="+ancqr);

                        conn.rs3 = conn.st3.executeQuery(ancqr);

                        int ANCMOMS = 0;

                        if (conn.rs3.next()) {
                            ANCMOMS = conn.rs3.getInt("curcount");
                        }

                        anccol.setCellValue(ANCMOMS);
                        anccol.setCellStyle(innerdata_style);

                        TOTALANCMOMS += ANCMOMS;
                        YEARLYTOTALANCMOMS += ANCMOMS;
                        System.out.println("^^^^^^^^^^^^^^^^^^^" + ANCMOMS + " ANC MOTHERS IN FACILITY  " + Facilityname);

                        
                        
                         //==================================== MAT NUMBER SUMMARIES========================


                        String matqr = matmaincountqry+" '" + conn.rs1.getString("facilityname") + "'  ";

                        System.out.println(matqr);

                        conn.rs3 = conn.st3.executeQuery(matqr);

                        int MATMOMS = 0;

                        if (conn.rs3.next()) {
                            MATMOMS = conn.rs3.getInt("curcount");
                        }

                        matcol.setCellValue(MATMOMS);
                        matcol.setCellStyle(innerdata_style);

                        YEARLYTOTALMATMOMS += MATMOMS;
                        TOTALMATMOMS += MATMOMS;
                        System.out.println("^^^^^^^^^^^^^^^^^^^" +TOTALMATMOMS+ "  >>"+MATMOMS + " MAT MOTHERS IN FACILITY  " + Facilityname);

                        
                        
                        
                        

                        //==================================== PNC NUMBER SUMMARIES========================


                        String pncqr = pncmaincountqry+" '" + conn.rs1.getString("facilityname") + "'";

                        System.out.println(pncqr);

                        conn.rs3 = conn.st3.executeQuery(pncqr);

                        int PNCMOMS = 0;

                        if (conn.rs3.next()) {
                            PNCMOMS = conn.rs3.getInt("curcount");
                        }

                        pnccol.setCellValue(PNCMOMS);
                        pnccol.setCellStyle(innerdata_style);

                        YEARLYTOTALPNCMOMS += PNCMOMS;
                        TOTALPNCMOMS += PNCMOMS;
                        System.out.println("^^^^^^^^^^^^^^^^^^^" +TOTALPNCMOMS+ "  >>"+PNCMOMS + " PNC MOTHERS IN FACILITY  " + Facilityname);





        //                //=========================UNCATEGORISED MOTHERS============================
        //
        //                String nodateqr = maincountqry+" '" + conn.rs1.getString("facilityname") + "' and ( delivery_date='' or delivery_date='-' or delivery_date='null')";
        //
        //                System.out.println(nodateqr);
        //
        //                conn.rs3 = conn.st3.executeQuery(nodateqr);
        //
        //                int NODATEMOMS = 0;
        //
        //                if (conn.rs3.next()) {
        //                    NODATEMOMS = conn.rs3.getInt("curcount");
        //                }
        //                nodatecol.setCellValue(NODATEMOMS);
        //                nodatecol.setCellStyle(innerdata_style);
        //
        //                TOTALNODATEMOMS += NODATEMOMS;
        //
        //                System.out.println("^^^^^^^^^^^^^^^^^^^" + NODATEMOMS + " UNKNOWN DELIVERY DATE MOTHERS IN FACILITY  " + Facilityname);
        //

                        //============================TOTAL MOTHERS==================================== 

                        String totalqr = maincountqry+" '" + conn.rs1.getString("facilityname") + "' ";

                        System.out.println(totalqr);

                        conn.rs3 = conn.st3.executeQuery(totalqr);

                        int TOTALMOMS = 0;
                           
                        if (conn.rs3.next()) {
                            TOTALMOMS = conn.rs3.getInt("curcount");
                        }
                        
                        // TOTALMOMS=ANCMOMS+PNCMOMS+MATMOMS;
                           // YEARLYTOTALTOTALS+=ANCMOMS+PNCMOMS+MATMOMS;
                            YEARLYTOTALTOTALS+=TOTALMOMS;
                       // totalcol.setCellValue(TOTALMOMS);

                       // totalcol.setCellStyle(innerdata_style);

                        TOTALTOTALS += TOTALMOMS;
                        System.out.println("^^^^^^^^^^^^^^^^^^^" + TOTALMOMS + " TOTAL MOTHERS IN FACILITY  " + Facilityname);
                    
                        
                        
                        
                        

            
            
            }//end of while
                
                
         //================================================================================================================
            //statistics.setColumnWidth(2 + count, 5000);
         count=count+2;
                
         HSSFRow   lastatatisticsrow = statistics.createRow(count );
         lastatatisticsrow.setHeightInPoints(30);


            //=====fACILITY NAMES COL==============

            HSSFCell facilcell = lastatatisticsrow.createCell(4);
            //====ANC MUMS ROWS=====================
            HSSFCell anccol = lastatatisticsrow.createCell(5);

            
            //======MAT COLS===============
            HSSFCell matcol = lastatatisticsrow.createCell(6);

            
            //======PNC COLS========================
            HSSFCell pnccol = lastatatisticsrow.createCell(7);

            
            //=======TOTAL COLS=====================
            HSSFCell totalcol = lastatatisticsrow.createCell(8);

            //====TOTAL COL======   
            facilcell.setCellValue("ANNUAL TOTAL");
            facilcell.setCellStyle(data_style);

            //====ANC TOTALS======
            anccol.setCellValue(YEARLYTOTALANCMOMS);
            anccol.setCellStyle(innerdata_style);
            
            
             //====MAT TOTALS======
            matcol.setCellValue(YEARLYTOTALMATMOMS);
            matcol.setCellStyle(innerdata_style);

            //====PNC TOTALS======
            pnccol.setCellValue(YEARLYTOTALPNCMOMS);
            pnccol.setCellStyle(innerdata_style);

           

            //=====TOTAL TOTALS
            //totalcol.setCellValue(YEARLYTOTALTOTALS);

            //totalcol.setCellStyle(innerdata_style);         
                
                
                
                
                
                
        } catch (SQLException ex) {
            Logger.getLogger(allregistersummaries.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Count is "+count);
        
        return count;
        
    }//end of while
    
    

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
