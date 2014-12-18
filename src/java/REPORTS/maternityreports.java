/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package REPORTS;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
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
public class maternityreports extends HttpServlet {

    HSSFWorkbook wb;
    int maxmerging = 0;
    String maincountqry = "";
    int startcellpos = 2;
    int babystatusendcell = 6;
    int mainstartcell = 2;
    String Year = "";
    String additiaonalparrams = "EXTRACT(YEAR FROM VisitDate) ='" + Year + "' and";
    String villagesmainqry="";
    
    String bbystatus="";
    String mumstatus="";
    
    String lbsubqry="";
    String fsbsubqry="";
    String msbsubqry="";
    String mumalivesubqry="";
    String mumdeadsubqry="";
    String nocompsubqry="";
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");

            String yearsarray[] = {"2014", "2013", "2012"};
          
            
            
            
            
         if(request.getParameterValues("year")!=null){
            yearsarray=request.getParameterValues("year");
            }
        

            Year = "";
            
             additiaonalparrams = "EXTRACT(YEAR FROM VisitDate) ='" + Year + "' and";
             villagesmainqry="select distinct Village  from mother_details join mat_atoh on mother_details.motherID=mat_atoh.motherid where  facilityname LIKE ";
             lbsubqry=" live_birth LIKE 'Live Birth' and ";
             fsbsubqry=" live_birth LIKE 'Fresh Still Birth' and ";
             msbsubqry=" live_birth LIKE 'Macerated Still Birth' and ";
             
             mumalivesubqry=" ConditionAfterDelivery LIKE 'A' and ";
             mumdeadsubqry=" ConditionAfterDelivery LIKE 'D' and ";
             
             
             nocompsubqry=" ( DeliveryComplications LIKE 'none' || DeliveryComplications LIKE 'Nil' ) and ";
 
             
            //the startposition of the leftmost cell 
            mainstartcell = 2;

            //the start cell pos of the 
            startcellpos = 4;

            if (Year.equals("")) {
                additiaonalparrams = "";
            }

            wb = new HSSFWorkbook();

            //rem  amd not using column headers anywhere except to determine the length of the mamerging column
            String columnheaders[] = {"FACILITY", "VILLAGE", "LB", "FSB", "MSB", "ALIVE", "DEAD", "NO COMPLICATIONS"};

            maxmerging = columnheaders.length;

            maincountqry = "select count(*) as curcount from mother_details where " + additiaonalparrams + "  facilityname LIKE ";
           
            
            bbystatus="select count(*) as curcount from mother_details join mat_vtoab on mother_details.motherID=mat_vtoab.motherid join mat_atoh on mother_details.motherID=mat_atoh.motherid where  ";
            mumstatus="select count(*) as curcount from mother_details join mat_mtou on mother_details.motherID=mat_mtou.motherid join mat_atoh on mother_details.motherID=mat_atoh.motherid where  ";
           
            
            String maincolheaderarr[] = {"", "", "BABY STATUS", "", "", "MOTHERS CONDITION", "", "Delivery Complications"};
            String Visitnumber[] = {"FACILITY", "VILLAGE", "LIVE BIRTH", "FSB", "MSB", "ALIVE", "DEAD", "no complications"};


            //=====================================



            //==============================CREATE WORKBOOK AND SHEETS FOR EACH SITE  
            HSSFSheet anc2012 = null;
            HSSFSheet anc2013 = null;
            HSSFSheet anc2014 = null;

            boolean is2012 = false;
            boolean is2013 = false;
            boolean is2014 = false;


            for (int a = 0; a < yearsarray.length; a++) {

                if (yearsarray[a].equals("2012")) {
                    anc2012 = wb.createSheet("2012");
                    is2012 = true;
                } else if (yearsarray[a].equals("2013")) {
                    anc2013 = wb.createSheet("2013");
                    is2013 = true;
                }

                if (yearsarray[a].equals("2014")) {
                    anc2014 = wb.createSheet("2014");
                    is2014 = true;
                }
            }


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
            th_style.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
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
            data_style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);



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
            innerdata_style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

            
            
              //=======AGGREGATE DATA STYLING=========================== 

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


            //=======CREATE HEADER  FOR EACH YEAR=======================================     
            HSSFRow rw012012 = null;
            HSSFRow rw012013 = null;
            HSSFRow rw012014 = null;
            String yearlyheader = "MATERNITY REGISTER MOTHER AND BABY STATUS REPORTS PER FACILITY AND VILLAGE FOR YEAR ";

            //2012 header
            if (is2012 == true) {
                rw012012 = anc2012.createRow(0);
                rw012012.setHeightInPoints(35);
                HSSFCell rw01cell01 = rw012012.createCell(mainstartcell);
                rw01cell01.setCellValue(yearlyheader + "2012");
                rw01cell01.setCellStyle(style_header);
            }

            //2013 header
            if (is2013 == true) {
                rw012013 = anc2013.createRow(0);
                rw012013.setHeightInPoints(35);
                HSSFCell rw01cell01 = rw012013.createCell(mainstartcell);
                rw01cell01.setCellValue(yearlyheader + "2013");
                rw01cell01.setCellStyle(style_header);
            }


            //2014 header
            if (is2014 == true) {
                rw012014 = anc2014.createRow(0);
                rw012014.setHeightInPoints(35);
                HSSFCell rw01cell01 = rw012014.createCell(mainstartcell);
                rw01cell01.setCellValue(yearlyheader + "2014");
                rw01cell01.setCellStyle(style_header);
            }





            //===========================MAIN SITE COLUMN HEADERS========================================
            if (is2012) {
                //row 1
                HSSFRow colheaderrow12012 = anc2012.createRow(1);
                colheaderrow12012.setHeightInPoints(30);
                //row2
                HSSFRow colheaderrow22012 = anc2012.createRow(2);
                colheaderrow22012.setHeightInPoints(30);


                HSSFCell rwcolheader = null;
                for (int d = 0; d < maincolheaderarr.length; d++) {
                    rwcolheader = colheaderrow12012.createCell(d + mainstartcell);
                    rwcolheader.setCellValue(maincolheaderarr[d]);
                    rwcolheader.setCellStyle(th_style);
                }//end of for loop

                for (int d = 0; d < Visitnumber.length; d++) {
                    rwcolheader = colheaderrow22012.createCell(d + mainstartcell);
                    rwcolheader.setCellValue(Visitnumber[d]);
                    rwcolheader.setCellStyle(th_style);
                }//end of for loop


                for (int a = mainstartcell; a < maxmerging + mainstartcell; a++) {
                      if(a==mainstartcell){
                     anc2012.setColumnWidth(a, 5000);
                     }
                     else{
                    anc2012.setColumnWidth(a, 4500);
                      }
                }
                anc2012.addMergedRegion(new CellRangeAddress(0, 0, mainstartcell, maxmerging + 1));
                anc2012.addMergedRegion(new CellRangeAddress(1, 1, startcellpos, babystatusendcell));
                anc2012.addMergedRegion(new CellRangeAddress(1, 1, babystatusendcell + 1, maxmerging));
//                  anc2012.addMergedRegion(new CellRangeAddress(1, 2,  1, 1));
//                  anc2012.addMergedRegion(new CellRangeAddress(1, 2,  2, 2));
            }//

            if (is2013) {

                HSSFRow colheaderrow12013 = anc2013.createRow(1);
                colheaderrow12013.setHeightInPoints(30);

                HSSFRow colheaderrow22013 = anc2013.createRow(2);
                colheaderrow22013.setHeightInPoints(30);
                //header1
                HSSFCell rwcolheader = null;
                //create col header1
                for (int d = 0; d < maincolheaderarr.length; d++) {
                    rwcolheader = colheaderrow12013.createCell(d + mainstartcell);
                    rwcolheader.setCellValue(maincolheaderarr[d]);
                    rwcolheader.setCellStyle(th_style);
                }//end of for loop

                //create col header 2
                for (int d = 0; d < Visitnumber.length; d++) {
                    rwcolheader = colheaderrow22013.createCell(d + mainstartcell);
                    rwcolheader.setCellValue(Visitnumber[d]);
                    rwcolheader.setCellStyle(th_style);
                }//end of for loop

                //set column width
                for (int a = mainstartcell; a < maxmerging + mainstartcell; a++) {
                      if(a==mainstartcell){
                     anc2013.setColumnWidth(a, 5000);
                     }
                     else{
                    anc2013.setColumnWidth(a, 4500);
                      }
                }
                anc2013.addMergedRegion(new CellRangeAddress(0, 0, mainstartcell, maxmerging + 1));
                anc2013.addMergedRegion(new CellRangeAddress(1, 1, startcellpos, babystatusendcell));
                anc2013.addMergedRegion(new CellRangeAddress(1, 1, babystatusendcell + 1, maxmerging));

                //FACILITY AND VILLAGE MERGE
//                  anc2013.addMergedRegion(new CellRangeAddress(1, 2,  1, 1));
//                  anc2013.addMergedRegion(new CellRangeAddress(1, 2,  2, 2));
            }//




            if (is2014) {

                HSSFRow colheaderrow2014 = anc2014.createRow(1);
                colheaderrow2014.setHeightInPoints(30);

                HSSFRow colheaderrow22014 = anc2014.createRow(2);
                colheaderrow22014.setHeightInPoints(30);

                HSSFCell rwcolheader = null;
                for (int d = 0; d < maincolheaderarr.length; d++) {
                    rwcolheader = colheaderrow2014.createCell(d + mainstartcell);
                    rwcolheader.setCellValue(maincolheaderarr[d]);
                    rwcolheader.setCellStyle(th_style);
                }//end of for loop

                //create col header 2
                for (int d = 0; d < Visitnumber.length; d++) {
                    rwcolheader = colheaderrow22014.createCell(d + mainstartcell);
                    rwcolheader.setCellValue(Visitnumber[d]);
                    rwcolheader.setCellStyle(th_style);
                }//end of for loop




                anc2014.addMergedRegion(new CellRangeAddress(0, 0, mainstartcell, maxmerging + 1));
                anc2014.addMergedRegion(new CellRangeAddress(1, 1, startcellpos, babystatusendcell));
                anc2014.addMergedRegion(new CellRangeAddress(1, 1, babystatusendcell + 1, maxmerging));

                //set column headers
                for (int a = mainstartcell; a < maxmerging + mainstartcell; a++) {
                      if(a==mainstartcell){
                     anc2014.setColumnWidth(a, 5000);
                     }
                     else{
                    anc2014.setColumnWidth(a, 4500);
                      }
                }

            }//




            //merge the headers   









            dbConn conn = new dbConn();


            //===============DISTINCT FACILITY NAMES===========


            String facils = "Select  Distinct facilityname from mother_details order by facilityname asc";

            conn.rs1 = conn.st1.executeQuery(facils);

            HSSFRow rw2 = null;

            HSSFRow rwy = null;
            HSSFRow rwy1 = null;
            HSSFRow lastatatisticsrow = null;


            //rw2 = statistics.createRow(1);
            //rw2.setHeightInPoints(30);

            //=====if its the first row create the headers first first==========

            //==FACILITY NAME  || ANC MOTHERS || PNC MOTHERS  ||  UNKNOWN DELIVERY DATE  ||  TOTAL




//                statistics.setColumnWidth(4, 5700);
//                HSSFCell rwycell1 = rw2.createCell(4 + a);
//                rwycell1.setCellValue(Statisticsarr[a]);
//                rwycell1.setCellStyle(th_style);


//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            //THE FAMOUS WHILE LOOP STARTS HERE
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
            int TOTALANCMOMS = 0;
            int TOTALPNCMOMS = 0;
            int TOTALNODATEMOMS = 0;
            int TOTALTOTALS = 0;

            int count = 0;

            
              int count2012 = 2;            
            int count2013 = 2;
            int count2014 = 2;
            
            
            //the copy variables will be used in merging data for one facility
            int count2012copy = 2;
            int count2013copy = 2;
            int count2014copy = 2;
            
            //variable for storing all totals per visit
            
            
                         int alllbtotal2012=0;
                       int allfsbtotal2012=0;
                       int allmsbtotal2012=0;
                       int allmumalivetotal2012=0;
                       int allmumdeadtotal2012=0;
                       int allnocomptotal2012=0;
            
                       int alllbtotal2013=0;
                       int allfsbtotal2013=0;
                       int allmsbtotal2013=0;
                       int allmumalivetotal2013=0;
                       int allmumdeadtotal2013=0;
                       int allnocomptotal2013=0;
            
                       int alllbtotal2014=0;
                       int allfsbtotal2014=0;
                       int allmsbtotal2014=0;
                       int allmumalivetotal2014=0;
                       int allmumdeadtotal2014=0;
                       int allnocomptotal2014=0;
                       
            
            //=====this row no variable is used for all sites and will keep on incementing 
            int allsitesrowno = 1;

            //=====this variable initializes a row for the all sites worksheet   
            HSSFRow allsitesworksheetrws = null;

            
            //distinct facility loop begins here
            while (conn.rs1.next()) {

                count++;
               
                System.out.println("__________________________FACILITY NAME " + conn.rs1.getString("facilityname") + "______________________");


                
                
                    int lbtotal2012=0;
                    int fsbtotal2012=0;
                    int msbtotal2012=0;
                    int mumalivetotal2012=0;
                    int mumdeadtotal2012=0;
                    int nocomptotal2012=0;
                
                
                    
                       int lbtotal2013=0;
                       int fsbtotal2013=0;
                       int msbtotal2013=0;
                       int mumalivetotal2013=0;
                       int mumdeadtotal2013=0;
                       int nocomptotal2013=0;
                       
                       
                       
                          int lbtotal2014=0;
                          int fsbtotal2014=0;
                          int msbtotal2014=0;
                          int mumalivetotal2014=0;
                          int mumdeadtotal2014=0;
                          int nocomptotal2014=0;
                
                String Facilityname=conn.rs1.getString("facilityname").toUpperCase();
                
                //determine which data goes to which worksheet.
                HSSFRow rw1 = null;

               if(is2012){
                    
                     
                     //this is a referencing facility which will be used to enter the totals per site
                     
                    
                    //get the village names per year and per facility
                    conn.rs2=conn.st2.executeQuery(villagesmainqry+" '"+Facilityname+"' and EXTRACT(YEAR FROM VisitDate) ='2012' order by Village ASC");
                    while(conn.rs2.next()){
                     count2012++;   
                     
                    int yr=2012;
                     
                    int lb=0;
                    int fsb=0;
                    int msb=0;
                    int mumalive=0;
                    int mumdead=0;
                    int nocomp=0;
                     
                     
                    //CREATE THE FACILITY CELL    
                    rw1 = anc2012.createRow(count2012);
                    HSSFCell facilcel= rw1.createCell(mainstartcell);
                    facilcel.setCellValue(Facilityname);
                    facilcel.setCellStyle(innerdata_style);
                    rw1.setHeightInPoints(23);
                    //create village column
                  
                    HSSFCell villcel= rw1.createCell(mainstartcell+1);
                    villcel.setCellValue(conn.rs2.getString("Village").toUpperCase());
                    villcel.setCellStyle(data_style);
                   //get the visits per each year
                    
                    
                    
                    
                     //%%%%%%%%LB 2012%%%%%%%%%%%%%%
                    
                    String lbqry=bbystatus+lbsubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+lbqry+"\n");
                    conn.rs3=conn.st4.executeQuery(lbqry);
                    if(conn.rs3.next()){
                    lb=conn.rs3.getInt(1);
                    }
                    else{
                    lb=0;
                    }
                    
                    lbtotal2012+=lb;
                    alllbtotal2012+=lb;
                     //create vist1 column
                  
                    HSSFCell visit1cel= rw1.createCell(mainstartcell+2);
                    visit1cel.setCellValue(lb);
                    visit1cel.setCellStyle(data_style);
                    
                    //%%%%%%%%FSB 2012%%%%%%%%%%%%%%
                    
                    String fsbbqry=bbystatus+fsbsubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+fsbbqry+"\n");
                    conn.rs3=conn.st4.executeQuery(fsbbqry);
                    if(conn.rs3.next()){
                    fsb=conn.rs3.getInt(1);
                    }
                    else{
                    fsb=0;
                    }
                    
                    fsbtotal2012+=fsb;
                    allfsbtotal2012+=fsb;
                     //create vist1 column
                  
                    HSSFCell fsbcel= rw1.createCell(mainstartcell+3);
                    fsbcel.setCellValue(fsb);
                    fsbcel.setCellStyle(data_style);  
                  
                    
                    
                     //%%%%%%%%MSB 2012%%%%%%%%%%%%%%
                    
                    String msbbqry=bbystatus+msbsubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+msbbqry+"\n");
                    conn.rs3=conn.st4.executeQuery(msbbqry);
                    if(conn.rs3.next()){
                    msb=conn.rs3.getInt(1);
                    }
                    else{
                    msb=0;
                    }
                    
                    msbtotal2012+=msb;
                    allfsbtotal2012+=msb;
                     //create vist1 column
                  
                    HSSFCell msbcel= rw1.createCell(mainstartcell+4);
                    msbcel.setCellValue(msb);
                    msbcel.setCellStyle(data_style);  
                    
                    
                    
                      //%%%%%%%%MUM ALIVE 2012%%%%%%%%%%%%%%
                    
                    String mumaliveqry=mumstatus+mumalivesubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+mumaliveqry+"\n");
                    conn.rs3=conn.st4.executeQuery(mumaliveqry);
                    if(conn.rs3.next()){
                    mumalive=conn.rs3.getInt(1);
                    }
                    else{
                    mumalive=0;
                    }
                    
                    mumalivetotal2012+=mumalive;
                    allmumalivetotal2012+=mumalive;
                     //create vist1 column
                  
                    HSSFCell mumalivecel= rw1.createCell(mainstartcell+5);
                    mumalivecel.setCellValue(mumalive);
                    mumalivecel.setCellStyle(data_style);  
                    
                    
                    
                     //%%%%%%%%MUM DEAD2012%%%%%%%%%%%%%%
                    
                    String mumdeadqry=mumstatus+mumdeadsubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+mumdeadqry+"\n");
                    conn.rs3=conn.st4.executeQuery(mumdeadqry);
                    if(conn.rs3.next()){
                    mumdead=conn.rs3.getInt(1);
                    }
                    else{
                    mumdead=0;
                    }
                    
                    mumdeadtotal2012+=mumdead;
                    allmumdeadtotal2012+=mumdead;
                     //create vist1 column
                  
                    HSSFCell mumdeadcel= rw1.createCell(mainstartcell+6);
                    mumdeadcel.setCellValue(mumdead);
                    mumdeadcel.setCellStyle(data_style);  
                    
                    
                    
                    //%%%%%%%%Delivery complications%%%%%%%%%%%%%%
                    
                    String nocompqry=mumstatus+nocompsubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+nocompqry+"\n");
                    conn.rs3=conn.st4.executeQuery(nocompqry);
                    if(conn.rs3.next()){
                    nocomp=conn.rs3.getInt(1);
                    }
                    else{
                    nocomp=0;
                    }
                    
                    nocomptotal2012+=nocomp;
                    allnocomptotal2012+=nocomp;
                     //create vist1 column
                  
                    HSSFCell nocompcel= rw1.createCell(mainstartcell+7);
                    nocompcel.setCellValue(nocomp);
                    nocompcel.setCellStyle(data_style);  
                    
                    
                    
                    
                    
                    
                    
                    }//end of villages while loop

                    
                    
                    
                    if(fsbtotal2012>0||msbtotal2012>0||lbtotal2012>0||mumalivetotal2012>0||mumdeadtotal2012>0){
                      //now create the total row per facility
                    count2012++;
                     rw1 = anc2012.createRow(count2012);
                    HSSFCell totalscel= rw1.createCell(mainstartcell);
                    totalscel.setCellValue(""+Facilityname+" TOTALS");
                    totalscel.setCellStyle(th_style);
                    
                     HSSFCell facilcel= rw1.createCell(mainstartcell+1);
                    facilcel.setCellValue(" ");
                    
                    facilcel.setCellStyle(th_style);
                    
                    rw1.setHeightInPoints(23);
                    if(count2012copy+1<=count2012-1){
                   anc2012.addMergedRegion(new CellRangeAddress(count2012copy+1,count2012-1,mainstartcell,mainstartcell));
                   
                    }
                    count2012copy=count2012;
                    
                      //lb total
                    HSSFCell lbs= rw1.createCell(mainstartcell+2);
                    lbs.setCellValue(lbtotal2012);
                    lbs.setCellStyle(th_style);
                    
                      //fsbs 2 total
                    HSSFCell fsbs= rw1.createCell(mainstartcell+3);
                    fsbs.setCellValue(fsbtotal2012);
                    fsbs.setCellStyle(th_style);
                    
                    
                     //msb total
                    HSSFCell msbs= rw1.createCell(mainstartcell+4);
                    msbs.setCellValue(msbtotal2012);
                    msbs.setCellStyle(th_style);
                    
                    
                    //mum alive total
                    HSSFCell malives= rw1.createCell(mainstartcell+5);
                    malives.setCellValue(mumalivetotal2012);
                    malives.setCellStyle(th_style);
                    
                    
                    //mum dead total
                    HSSFCell mdeads= rw1.createCell(mainstartcell+6);
                    mdeads.setCellValue(mumdeadtotal2012);
                    mdeads.setCellStyle(th_style);
                    
                    
                    //other condistions total
                    HSSFCell nocomps= rw1.createCell(mainstartcell+7);
                    nocomps.setCellValue(nocomptotal2012);
                    nocomps.setCellStyle(th_style);
               }//end of if data
                    
                    
 }//end of 2012 loop



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
               //2013 data 
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



 if(is2013){
                    
                     
                     //this is a referencing facility which will be used to enter the totals per site
                     
                    
                    //get the village names per year and per facility
                    conn.rs2=conn.st2.executeQuery(villagesmainqry+" '"+Facilityname+"' and EXTRACT(YEAR FROM VisitDate) ='2013' order by Village ASC");
                   
                    
                    while(conn.rs2.next()){
                     count2013++;   
                     
                     int yr=2013;
                     
                    int lb=0;
                    int fsb=0;
                    int msb=0;
                    int mumalive=0;
                    int mumdead=0;
                    int nocomp=0;
                     
                     
                    //CREATE THE FACILITY CELL    
                    rw1 = anc2013.createRow(count2013);
                    HSSFCell facilcel= rw1.createCell(mainstartcell);
                    facilcel.setCellValue(Facilityname);
                    facilcel.setCellStyle(innerdata_style);
                    rw1.setHeightInPoints(23);
                    //create village column
                  
                    HSSFCell villcel= rw1.createCell(mainstartcell+1);
                    villcel.setCellValue(conn.rs2.getString("Village").toUpperCase());
                    villcel.setCellStyle(innerdata_style);
                  
                    
                    //GET THE CONDITIONS PER EACH YEAR
                    
                     //%%%%%%%%LB 2013%%%%%%%%%%%%%%
                    
                    String lbqry=bbystatus+lbsubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+lbqry+"\n");
                    conn.rs3=conn.st4.executeQuery(lbqry);
                    if(conn.rs3.next()){
                    lb=conn.rs3.getInt(1);
                    }
                    else{
                    lb=0;
                    }
                    
                    lbtotal2013+=lb;
                    alllbtotal2013+=lb;
                     //create vist1 column
                  
                    HSSFCell visit1cel= rw1.createCell(mainstartcell+2);
                    visit1cel.setCellValue(lb);
                    visit1cel.setCellStyle(data_style);
                    
                    //%%%%%%%%FSB 2013%%%%%%%%%%%%%%
                    
                    String fsbbqry=bbystatus+fsbsubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+fsbbqry+"\n");
                    conn.rs3=conn.st4.executeQuery(fsbbqry);
                    if(conn.rs3.next()){
                    fsb=conn.rs3.getInt(1);
                    }
                    else{
                    fsb=0;
                    }
                    
                    fsbtotal2013+=fsb;
                    allfsbtotal2013+=fsb;
                     //create vist1 column
                  
                    HSSFCell fsbcel= rw1.createCell(mainstartcell+3);
                    fsbcel.setCellValue(fsb);
                    fsbcel.setCellStyle(data_style);  
                  
                    
                    
                     //%%%%%%%%MSB 2013%%%%%%%%%%%%%%
                    
                    String msbbqry=bbystatus+msbsubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+msbbqry+"\n");
                    conn.rs3=conn.st4.executeQuery(msbbqry);
                    if(conn.rs3.next()){
                    msb=conn.rs3.getInt(1);
                    }
                    else{
                    msb=0;
                    }
                    
                    msbtotal2013+=msb;
                    allfsbtotal2013+=msb;
                     //create vist1 column
                  
                    HSSFCell msbcel= rw1.createCell(mainstartcell+4);
                    msbcel.setCellValue(msb);
                    msbcel.setCellStyle(data_style);  
                    
                    
                    
                      //%%%%%%%%MUM ALIVE 2013%%%%%%%%%%%%%%
                    
                    String mumaliveqry=mumstatus+mumalivesubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+mumaliveqry+"\n");
                    conn.rs3=conn.st4.executeQuery(mumaliveqry);
                    if(conn.rs3.next()){
                    mumalive=conn.rs3.getInt(1);
                    }
                    else{
                    mumalive=0;
                    }
                    
                    mumalivetotal2013+=mumalive;
                    allmumalivetotal2013+=mumalive;
                     //create vist1 column
                  
                    HSSFCell mumalivecel= rw1.createCell(mainstartcell+5);
                    mumalivecel.setCellValue(mumalive);
                    mumalivecel.setCellStyle(data_style);  
                    
                    
                    
                     //%%%%%%%%MUM DEAD2013%%%%%%%%%%%%%%
                    
                    String mumdeadqry=mumstatus+mumdeadsubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+mumdeadqry+"\n");
                    conn.rs3=conn.st4.executeQuery(mumdeadqry);
                    if(conn.rs3.next()){
                    mumdead=conn.rs3.getInt(1);
                    }
                    else{
                    mumdead=0;
                    }
                    
                    mumdeadtotal2013+=mumdead;
                    allmumdeadtotal2013+=mumdead;
                     //create vist1 column
                  
                    HSSFCell mumdeadcel= rw1.createCell(mainstartcell+6);
                    mumdeadcel.setCellValue(mumdead);
                    mumdeadcel.setCellStyle(data_style);  
                    
                    
                    
                    //%%%%%%%%Delivery complications%%%%%%%%%%%%%%
                    
                    String nocompqry=mumstatus+nocompsubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+nocompqry+"\n");
                    conn.rs3=conn.st4.executeQuery(nocompqry);
                    if(conn.rs3.next()){
                    nocomp=conn.rs3.getInt(1);
                    }
                    else{
                    nocomp=0;
                    }
                    
                    nocomptotal2013+=nocomp;
                    allnocomptotal2013+=nocomp;
                     //create vist1 column
                  
                    HSSFCell nocompcel= rw1.createCell(mainstartcell+7);
                    nocompcel.setCellValue(nocomp);
                    nocompcel.setCellStyle(data_style);  
                    
                    
                    
                    }//end of villages while loop

                    
                    
                    //get the summeries per baby condition.   
                  
                    if(fsbtotal2013>0||msbtotal2013>0||lbtotal2013>0||mumalivetotal2013>0||mumdeadtotal2013>0){
                  
                    //now create the total row per facility
                    count2013++;
                     rw1 = anc2013.createRow(count2013);
                    HSSFCell totalscel= rw1.createCell(mainstartcell);
                    totalscel.setCellValue(""+Facilityname+" TOTALS");
                    totalscel.setCellStyle(th_style);
                    
                     HSSFCell facilcel= rw1.createCell(mainstartcell+1);
                    facilcel.setCellValue(" ");
                    
                    facilcel.setCellStyle(th_style);
                    
                    rw1.setHeightInPoints(23);
                    if(count2013copy+1<=count2013-1){
                   anc2013.addMergedRegion(new CellRangeAddress(count2013copy+1,count2013-1,mainstartcell,mainstartcell));
                    }
                    count2013copy=count2013;
                    
                      //lb total
                    HSSFCell lbs= rw1.createCell(mainstartcell+2);
                    lbs.setCellValue(lbtotal2013);
                    lbs.setCellStyle(th_style);
                    
                      //fsbs 2 total
                    HSSFCell fsbs= rw1.createCell(mainstartcell+3);
                    fsbs.setCellValue(fsbtotal2013);
                    fsbs.setCellStyle(th_style);
                    
                    
                     //msb total
                    HSSFCell msbs= rw1.createCell(mainstartcell+4);
                    msbs.setCellValue(msbtotal2013);
                    msbs.setCellStyle(th_style);
                    
                    
                    //mum alive total
                    HSSFCell malives= rw1.createCell(mainstartcell+5);
                    malives.setCellValue(mumalivetotal2013);
                    malives.setCellStyle(th_style);
                    
                    
                    //mum dead total
                    HSSFCell mdeads= rw1.createCell(mainstartcell+6);
                    mdeads.setCellValue(mumdeadtotal2013);
                    mdeads.setCellStyle(th_style);
                    
                    
                    //other condistions total
                    HSSFCell nocomps= rw1.createCell(mainstartcell+7);
                    nocomps.setCellValue(nocomptotal2013);
                    nocomps.setCellStyle(th_style);
 }//end of 2013 if not blank rows
                    
                    
 }//end of 2013 loop



  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
           //2014 DATA
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            



 
 if(is2014){
                    
                     
                     //this is a referencing facility which will be used to enter the totals per site
                     
                    
                    //get the village names per year and per facility
                    conn.rs2=conn.st2.executeQuery(villagesmainqry+" '"+Facilityname+"' and EXTRACT(YEAR FROM VisitDate) ='2014' order by Village ASC");
                    while(conn.rs2.next()){
                     count2014++;   
                     
                     
                    int yr=2014;
                     
                    int lb=0;
                    int fsb=0;
                    int msb=0;
                    int mumalive=0;
                    int mumdead=0;
                    int nocomp=0;
                     
                    //CREATE THE FACILITY CELL    
                    rw1 = anc2014.createRow(count2014);
                    HSSFCell facilcel= rw1.createCell(mainstartcell);
                    facilcel.setCellValue(Facilityname);
                    facilcel.setCellStyle(innerdata_style);
                    rw1.setHeightInPoints(23);
                    //create village column
                  
                    HSSFCell villcel= rw1.createCell(mainstartcell+1);
                    villcel.setCellValue(conn.rs2.getString("Village").toUpperCase());
                    villcel.setCellStyle(data_style);
                   //get the visits per each year
                    
                    
                    
                    
                    
                      //%%%%%%%%LB 2014%%%%%%%%%%%%%%
                    
                    String lbqry=bbystatus+lbsubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+lbqry+"\n");
                    conn.rs3=conn.st4.executeQuery(lbqry);
                    if(conn.rs3.next()){
                    lb=conn.rs3.getInt(1);
                    }
                    else{
                    lb=0;
                    }
                    
                    lbtotal2014+=lb;
                    alllbtotal2014+=lb;
                     //create vist1 column
                  
                    HSSFCell visit1cel= rw1.createCell(mainstartcell+2);
                    visit1cel.setCellValue(lb);
                    visit1cel.setCellStyle(data_style);
                    
                    //%%%%%%%%FSB 2013%%%%%%%%%%%%%%
                    
                    String fsbbqry=bbystatus+fsbsubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+fsbbqry+"\n");
                    conn.rs3=conn.st4.executeQuery(fsbbqry);
                    if(conn.rs3.next()){
                    fsb=conn.rs3.getInt(1);
                    }
                    else{
                    fsb=0;
                    }
                    
                    fsbtotal2014+=fsb;
                    allfsbtotal2014+=fsb;
                     //create vist1 column
                  
                    HSSFCell fsbcel= rw1.createCell(mainstartcell+3);
                    fsbcel.setCellValue(fsb);
                    fsbcel.setCellStyle(data_style);  
                  
                    
                    
                     //%%%%%%%%MSB 2014%%%%%%%%%%%%%%
                    
                    String msbbqry=bbystatus+msbsubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+msbbqry+"\n");
                    conn.rs3=conn.st4.executeQuery(msbbqry);
                    if(conn.rs3.next()){
                    msb=conn.rs3.getInt(1);
                    }
                    else{
                    msb=0;
                    }
                    
                    msbtotal2014+=msb;
                    allfsbtotal2014+=msb;
                     //create vist1 column
                  
                    HSSFCell msbcel= rw1.createCell(mainstartcell+4);
                    msbcel.setCellValue(msb);
                    msbcel.setCellStyle(data_style);  
                    
                    
                    
                      //%%%%%%%%MUM ALIVE 2014%%%%%%%%%%%%%%
                    
                    String mumaliveqry=mumstatus+mumalivesubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+mumaliveqry+"\n");
                    conn.rs3=conn.st4.executeQuery(mumaliveqry);
                    if(conn.rs3.next()){
                    mumalive=conn.rs3.getInt(1);
                    }
                    else{
                    mumalive=0;
                    }
                    
                    mumalivetotal2014+=mumalive;
                    allmumalivetotal2014+=mumalive;
                     //create vist1 column
                  
                    HSSFCell mumalivecel= rw1.createCell(mainstartcell+5);
                    mumalivecel.setCellValue(mumalive);
                    mumalivecel.setCellStyle(data_style);  
                    
                    
                    
                     //%%%%%%%%MUM DEAD2014%%%%%%%%%%%%%%
                    
                    String mumdeadqry=mumstatus+mumdeadsubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+mumdeadqry+"\n");
                    conn.rs3=conn.st4.executeQuery(mumdeadqry);
                    if(conn.rs3.next()){
                    mumdead=conn.rs3.getInt(1);
                    }
                    else{
                    mumdead=0;
                    }
                    
                    mumdeadtotal2014+=mumdead;
                    allmumdeadtotal2014+=mumdead;
                     //create vist1 column
                  
                    HSSFCell mumdeadcel= rw1.createCell(mainstartcell+6);
                    mumdeadcel.setCellValue(mumdead);
                    mumdeadcel.setCellStyle(data_style);  
                    
                    
                    
                    //%%%%%%%%Delivery complications%%%%%%%%%%%%%%
                    
                    String nocompqry=mumstatus+nocompsubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+nocompqry+"\n");
                    conn.rs3=conn.st4.executeQuery(nocompqry);
                    if(conn.rs3.next()){
                    nocomp=conn.rs3.getInt(1);
                    }
                    else{
                    nocomp=0;
                    }
                    
                    nocomptotal2014+=nocomp;
                    allnocomptotal2014+=nocomp;
                     //create vist1 column
                  
                    HSSFCell nocompcel= rw1.createCell(mainstartcell+7);
                    nocompcel.setCellValue(nocomp);
                    nocompcel.setCellStyle(data_style);  
                   
                    
                    
                    
                    
                    
                    
                    
                    
                    }//end of villages while loop
                    
                  //get the summeries per baby condition.   
                    
                  if(fsbtotal2014>0||msbtotal2014>0||lbtotal2014>0||mumalivetotal2014>0||mumdeadtotal2014>0){
                    //now create the total row per facility
                    count2014++;
                     rw1 = anc2014.createRow(count2014);
                    HSSFCell totalscel= rw1.createCell(mainstartcell);
                    totalscel.setCellValue(""+Facilityname+" TOTALS");
                    totalscel.setCellStyle(th_style);
                    
                     HSSFCell facilcel= rw1.createCell(mainstartcell+1);
                    facilcel.setCellValue(" ");
                    
                    facilcel.setCellStyle(th_style);
                    
                    rw1.setHeightInPoints(23);
                    //anc2014.addMergedRegion(new CellRangeAddress(count2014,count2014,mainstartcell,mainstartcell+1));
                   if(count2014copy+1<=count2014-1){
                    anc2014.addMergedRegion(new CellRangeAddress(count2014copy+1,count2014-1,mainstartcell,mainstartcell));
                   }
                    count2014copy=count2014;
                    
                      //lb total
                    HSSFCell lbs= rw1.createCell(mainstartcell+2);
                    lbs.setCellValue(lbtotal2014);
                    lbs.setCellStyle(th_style);
                    
                      //fsbs 2 total
                    HSSFCell fsbs= rw1.createCell(mainstartcell+3);
                    fsbs.setCellValue(fsbtotal2014);
                    fsbs.setCellStyle(th_style);
                    
                    
                     //msb total
                    HSSFCell msbs= rw1.createCell(mainstartcell+4);
                    msbs.setCellValue(msbtotal2014);
                    msbs.setCellStyle(th_style);
                    
                    
                    //mum alive total
                    HSSFCell malives= rw1.createCell(mainstartcell+5);
                    malives.setCellValue(mumalivetotal2014);
                    malives.setCellStyle(th_style);
                    
                    
                    //mum dead total
                    HSSFCell mdeads= rw1.createCell(mainstartcell+6);
                    mdeads.setCellValue(mumdeadtotal2014);
                    mdeads.setCellStyle(th_style);
                    
                    
                    //other condistions total
                    HSSFCell nocomps= rw1.createCell(mainstartcell+7);
                    nocomps.setCellValue(nocomptotal2014);
                    nocomps.setCellStyle(th_style);

 }//end of if data is
                    
                    
                    
                   }//end of 2014 loop
 
 
 




            }//this is the end of the main while loop
            
            
            
            
             if(is2012){
                    //now create the total row per facility
                    count2012++;
                    
                    int yr=2012;
                   HSSFRow  rw1 = anc2012.createRow(count2012);
                    HSSFCell totalscel= rw1.createCell(mainstartcell);
                    totalscel.setCellValue(""+yr+" AGGREGATE TOTALS");
                    totalscel.setCellStyle(aggregregatedata_style);
                    
                     HSSFCell facilcel= rw1.createCell(mainstartcell+1);
                    facilcel.setCellValue(" ");
                    
                    facilcel.setCellStyle(aggregregatedata_style);
                    
                    rw1.setHeightInPoints(23);
                    //if(count2012copy+1<=count2012-1){
                   anc2012.addMergedRegion(new CellRangeAddress(count2012,count2012,mainstartcell,mainstartcell+1));
                    //}
                    count2012copy=count2012;
                    
                      //lb total
                    HSSFCell lbs= rw1.createCell(mainstartcell+2);
                    lbs.setCellValue(alllbtotal2012);
                    lbs.setCellStyle(aggregregatedata_style);
                    
                      //fsbs 2 total
                    HSSFCell fsbs= rw1.createCell(mainstartcell+3);
                    fsbs.setCellValue(allfsbtotal2012);
                    fsbs.setCellStyle(aggregregatedata_style);
                    
                    
                     //msb total
                    HSSFCell msbs= rw1.createCell(mainstartcell+4);
                    msbs.setCellValue(allmsbtotal2012);
                    msbs.setCellStyle(aggregregatedata_style);
                    
                    
                    //mum alive total
                    HSSFCell malives= rw1.createCell(mainstartcell+5);
                    malives.setCellValue(allmumalivetotal2012);
                    malives.setCellStyle(aggregregatedata_style);
                    
                    
                    //mum dead total
                    HSSFCell mdeads= rw1.createCell(mainstartcell+6);
                    mdeads.setCellValue(allmumdeadtotal2012);
                    mdeads.setCellStyle(aggregregatedata_style);
                    
                    
                    //other condistions total
                    HSSFCell nocomps= rw1.createCell(mainstartcell+7);
                    nocomps.setCellValue(allnocomptotal2012);
                    nocomps.setCellStyle(aggregregatedata_style);
                    
            }//end of is2012
            
            
            
            if(is2013){
                    //now create the total row per facility
                    count2013++;
                    
                    int yr=2013;
                   HSSFRow  rw1 = anc2013.createRow(count2013);
                    HSSFCell totalscel= rw1.createCell(mainstartcell);
                    totalscel.setCellValue(""+yr+" AGGREGATE TOTALS");
                    totalscel.setCellStyle(aggregregatedata_style);
                    
                     HSSFCell facilcel= rw1.createCell(mainstartcell+1);
                    facilcel.setCellValue(" ");
                    
                    facilcel.setCellStyle(aggregregatedata_style);
                    
                    rw1.setHeightInPoints(23);
                    //if(count2013copy+1<=count2013-1){
                   anc2013.addMergedRegion(new CellRangeAddress(count2013,count2013,mainstartcell,mainstartcell+1));
                    //}
                    count2013copy=count2013;
                    
                      //lb total
                    HSSFCell lbs= rw1.createCell(mainstartcell+2);
                    lbs.setCellValue(alllbtotal2013);
                    lbs.setCellStyle(aggregregatedata_style);
                    
                      //fsbs 2 total
                    HSSFCell fsbs= rw1.createCell(mainstartcell+3);
                    fsbs.setCellValue(allfsbtotal2013);
                    fsbs.setCellStyle(aggregregatedata_style);
                    
                    
                     //msb total
                    HSSFCell msbs= rw1.createCell(mainstartcell+4);
                    msbs.setCellValue(allmsbtotal2013);
                    msbs.setCellStyle(aggregregatedata_style);
                    
                    
                    //mum alive total
                    HSSFCell malives= rw1.createCell(mainstartcell+5);
                    malives.setCellValue(allmumalivetotal2013);
                    malives.setCellStyle(aggregregatedata_style);
                    
                    
                    //mum dead total
                    HSSFCell mdeads= rw1.createCell(mainstartcell+6);
                    mdeads.setCellValue(allmumdeadtotal2013);
                    mdeads.setCellStyle(aggregregatedata_style);
                    
                    
                    //other condistions total
                    HSSFCell nocomps= rw1.createCell(mainstartcell+7);
                    nocomps.setCellValue(allnocomptotal2013);
                    nocomps.setCellStyle(aggregregatedata_style);
                    
            }//end of is2013
            
            

            
            
             if(is2014){
                    //now create the total row per facility
                    count2014++;
                    
                    int yr=2014;
                   HSSFRow  rw1 = anc2014.createRow(count2014);
                    HSSFCell totalscel= rw1.createCell(mainstartcell);
                    totalscel.setCellValue(""+yr+" AGGREGATE TOTALS");
                    totalscel.setCellStyle(aggregregatedata_style);
                    
                     HSSFCell facilcel= rw1.createCell(mainstartcell+1);
                    facilcel.setCellValue(" ");
                    
                    facilcel.setCellStyle(aggregregatedata_style);
                    
                    rw1.setHeightInPoints(23);
                    //if(count2014copy+1<=count2014-1){
                   anc2014.addMergedRegion(new CellRangeAddress(count2014,count2014,mainstartcell,mainstartcell+1));
                    //}
                    count2014copy=count2014;
                    
                      //lb total
                    HSSFCell lbs= rw1.createCell(mainstartcell+2);
                    lbs.setCellValue(alllbtotal2014);
                    lbs.setCellStyle(aggregregatedata_style);
                    
                      //fsbs 2 total
                    HSSFCell fsbs= rw1.createCell(mainstartcell+3);
                    fsbs.setCellValue(allfsbtotal2014);
                    fsbs.setCellStyle(aggregregatedata_style);
                    
                    
                     //msb total
                    HSSFCell msbs= rw1.createCell(mainstartcell+4);
                    msbs.setCellValue(allmsbtotal2014);
                    msbs.setCellStyle(aggregregatedata_style);
                    
                    
                    //mum alive total
                    HSSFCell malives= rw1.createCell(mainstartcell+5);
                    malives.setCellValue(allmumalivetotal2014);
                    malives.setCellStyle(aggregregatedata_style);
                    
                    
                    //mum dead total
                    HSSFCell mdeads= rw1.createCell(mainstartcell+6);
                    mdeads.setCellValue(allmumdeadtotal2014);
                    mdeads.setCellStyle(aggregregatedata_style);
                    
                    
                    //other condistions total
                    HSSFCell nocomps= rw1.createCell(mainstartcell+7);
                    nocomps.setCellValue(allnocomptotal2014);
                    nocomps.setCellStyle(aggregregatedata_style);
                    
            }//end of is2014


//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            //THE FAMOUS WHILE LOOP ENDS HERE
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            



            //================================================================================================================







        } catch (SQLException ex) {
            Logger.getLogger(maternityreports.class.getName()).log(Level.SEVERE, null, ex);
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
        response.setHeader("Content-Disposition", "attachment; filename=MATERNITYREPORTS_" + dat1 + ".xls");
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
