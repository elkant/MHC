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
public class ancvisitpervillage extends HttpServlet {

    HSSFWorkbook wb;
    int maxmerging=0;
    String maincountqry="";
    String visitsmainqry="";
    String visit2subqry="";
    String visit1subqry="";
    String visit3subqry="";
    String visit4subqry="";
    String visit5andabovesubqry="";
    String villagesmainqry="";
    int startcellpos=2;
    int mainstartcell=2;
   String Year="";
   
   String additiaonalparrams="EXTRACT(YEAR FROM VisitDate) ='"+Year+"' and";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            
            
            
            
            String yearsarray[]={"2014","2013","2012"};
            
            if(request.getParameterValues("year")!=null){
            
            yearsarray=request.getParameterValues("year");
            }
            
            
            Year="";
            additiaonalparrams="EXTRACT(YEAR FROM VisitDate) ='"+Year+"' and";
           visit1subqry=" visit_no ='1' and ";
           visit2subqry=" visit_no ='2' and ";
           visit3subqry=" visit_no ='3' and ";
           visit4subqry=" visit_no ='4' and ";
           visit5andabovesubqry=" visit_no > '4' and ";
            
            //the startposition of the leftmost cell 
             mainstartcell=2;
             
             //the start cell pos of the 
             startcellpos=4;
            
            if(Year.equals("")){additiaonalparrams="";}
            
            wb = new HSSFWorkbook();
            
            //rem  amd not using column headers anywhere except to determine the length of the mamerging column
             String columnheaders[]={"FACILITY","VILLAGE","1st","2nd","3rd","4th","5th and above","TOTAL"};
                
           maxmerging=columnheaders.length;
           
           villagesmainqry="select distinct atoh.Village  from mother_details join atoh on mother_details.motherID=atoh.motherid where  facilityname LIKE ";
           visitsmainqry="select count(*) as curcount from mother_details join atoh on mother_details.motherID=atoh.motherid where  ";
           maincountqry="select count(*) as curcount from mother_details join atoh on mother_details.motherID=atoh.motherid where "+additiaonalparrams+"  facilityname LIKE ";
           
            String maincolheaderarr[] = {"", "", "VISIT NUMBER","","","","",""};
            String Visitnumber[]   = {"FACILITY","VILLAGE","1st Visit", "2nd Visit", "3rd Visit","4th Visit","5th and Above","Total "};

            
            //=====================================



            //==============================CREATE WORKBOOK AND SHEETS FOR EACH SITE  
              HSSFSheet anc2012=null;
              HSSFSheet anc2013=null;
              HSSFSheet anc2014=null;

            boolean is2012=false;
            boolean is2013=false;
            boolean is2014=false;
            
            
 for(int a=0;a<yearsarray.length;a++){

     if(yearsarray[a].equals("2012")){
             anc2012 = wb.createSheet("2012");
            is2012=true;
                                     } 
     
     else if(yearsarray[a].equals("2013")){
             anc2013 = wb.createSheet("2013");
            is2013=true;
                                     } 
     
     if(yearsarray[a].equals("2014")){
             anc2014 = wb.createSheet("2014");
            is2014=true;
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
            data_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            data_style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
           



            //=======INNER DATA STYLING=========================== 

            CellStyle innerdata_style = wb.createCellStyle();
            innerdata_style.setFont(datafont);
            innerdata_style.setWrapText(true);
            innerdata_style.setAlignment(innerdata_style.ALIGN_CENTER);
            innerdata_style.setFillForegroundColor(HSSFColor.WHITE.index);
            innerdata_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            innerdata_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            innerdata_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            innerdata_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            innerdata_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            innerdata_style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

            
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
            
            

            //=======CREATE HEADER  FOR EACH YEAR=======================================     
            HSSFRow rw012012 =null;
            HSSFRow rw012013 =null;
            HSSFRow rw012014 =null;
            String yearlyheader="ANC VISITS SUMMARY PER VILLAGE FOR YEAR ";
            
           //2012 header
            if(is2012==true){
            rw012012 = anc2012.createRow(0);
            rw012012.setHeightInPoints(35);
            HSSFCell rw01cell01 = rw012012.createCell(mainstartcell);
            rw01cell01.setCellValue(yearlyheader+"2012");
            rw01cell01.setCellStyle(style_header);
            }

           //2013 header
            if(is2013==true){
            rw012013 = anc2013.createRow(0);
            rw012013.setHeightInPoints(35);
            HSSFCell rw01cell01 = rw012013.createCell(mainstartcell);
            rw01cell01.setCellValue(yearlyheader+"2013");
            rw01cell01.setCellStyle(style_header);
            }


            //2014 header
            if(is2014==true){
            rw012014 = anc2014.createRow(0);
            rw012014.setHeightInPoints(35);
            HSSFCell rw01cell01 = rw012014.createCell(mainstartcell);
            rw01cell01.setCellValue(yearlyheader+"2014");
            rw01cell01.setCellStyle(style_header);
            }

            



        //===========================MAIN SITE COLUMN HEADERS========================================
            if(is2012){
            //row 1
           HSSFRow colheaderrow12012 = anc2012.createRow(1);
           colheaderrow12012.setHeightInPoints(30);
           //row2
           HSSFRow colheaderrow22012 = anc2012.createRow(2);
                colheaderrow22012.setHeightInPoints(30);
                
                
                HSSFCell rwcolheader=null;
                 for(int d=0;d<maincolheaderarr.length;d++){
                 rwcolheader = colheaderrow12012.createCell(d+mainstartcell);
                  rwcolheader.setCellValue(maincolheaderarr[d]);
                  rwcolheader.setCellStyle(th_style);
                                              }//end of for loop
                 
                  for(int d=0;d<Visitnumber.length;d++){
                 rwcolheader = colheaderrow22012.createCell(d+mainstartcell);
                  rwcolheader.setCellValue(Visitnumber[d]);
                  rwcolheader.setCellStyle(th_style);
                                              }//end of for loop
                 
                 
                 for (int a = mainstartcell; a <maxmerging+mainstartcell; a++) {
                     
                     if(a==mainstartcell){
                     anc2012.setColumnWidth(a, 5000);
                     }
                     else{
                        anc2012.setColumnWidth(a, 4500);
                     }
                    }
                  anc2012.addMergedRegion(new CellRangeAddress(0, 0,  mainstartcell, maxmerging+1));
                  anc2012.addMergedRegion(new CellRangeAddress(1, 1,  startcellpos, maxmerging));
//                  anc2012.addMergedRegion(new CellRangeAddress(1, 2,  1, 1));
//                  anc2012.addMergedRegion(new CellRangeAddress(1, 2,  2, 2));
            }//
              
              if(is2013){
            
           HSSFRow colheaderrow12013 = anc2013.createRow(1);
                colheaderrow12013.setHeightInPoints(30);
                
           HSSFRow colheaderrow22013 = anc2013.createRow(2);
                colheaderrow22013.setHeightInPoints(30);
                //header1
                HSSFCell rwcolheader=null;
                //create col header1
                 for(int d=0;d<maincolheaderarr.length;d++){
                 rwcolheader = colheaderrow12013.createCell(d+mainstartcell);
                  rwcolheader.setCellValue(maincolheaderarr[d]);
                  rwcolheader.setCellStyle(th_style);
                }//end of for loop
                 
                 //create col header 2
                 for(int d=0;d<Visitnumber.length;d++){
                 rwcolheader = colheaderrow22013.createCell(d+mainstartcell);
                  rwcolheader.setCellValue(Visitnumber[d]);
                  rwcolheader.setCellStyle(th_style);
                }//end of for loop
                 
                 //set column width
                 for (int a = mainstartcell; a <maxmerging+mainstartcell; a++) {
                     
                       if(a==mainstartcell){
                     anc2013.setColumnWidth(a, 5000);
                      }
                     else{
                        anc2013.setColumnWidth(a, 4500);
                       }
                    }
                  anc2013.addMergedRegion(new CellRangeAddress(0, 0,  mainstartcell, maxmerging+1));
                  anc2013.addMergedRegion(new CellRangeAddress(1, 1,  startcellpos, maxmerging));
              
                  //FACILITY AND VILLAGE MERGE
//                  anc2013.addMergedRegion(new CellRangeAddress(1, 2,  1, 1));
//                  anc2013.addMergedRegion(new CellRangeAddress(1, 2,  2, 2));
            }//
          
              
              
              
               if(is2014){
            
           HSSFRow colheaderrow2014 = anc2014.createRow(1);
                colheaderrow2014.setHeightInPoints(30);
            
           HSSFRow colheaderrow22014 = anc2014.createRow(2);
                colheaderrow22014.setHeightInPoints(30);
                
                HSSFCell rwcolheader=null;
                 for(int d=0;d<maincolheaderarr.length;d++){
                 rwcolheader = colheaderrow2014.createCell(d+mainstartcell);
                  rwcolheader.setCellValue(maincolheaderarr[d]);
                  rwcolheader.setCellStyle(th_style);
                }//end of for loop
              
                  //create col header 2
                 for(int d=0;d<Visitnumber.length;d++){
                 rwcolheader = colheaderrow22014.createCell(d+mainstartcell);
                  rwcolheader.setCellValue(Visitnumber[d]);
                  rwcolheader.setCellStyle(th_style);
                }//end of for loop
                 
               
                 
                 
               anc2014.addMergedRegion(new CellRangeAddress(0, 0,  mainstartcell, maxmerging+1));
               anc2014.addMergedRegion(new CellRangeAddress(1, 1,  startcellpos, maxmerging));
               
               
                 //set column headers
                  for (int a = mainstartcell; a <maxmerging+mainstartcell; a++) {
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

            int count = 2;
            int count2012 = 2;            
            int count2013 = 2;
            int count2014 = 2;
            
            
            //the copy variables will be used in merging data for one facility
            int count2012copy = 2;
            int count2013copy = 2;
            int count2014copy = 2;
            
            //=====this row no variable is used for all sites and will keep on incementing 
               int allsitesrowno = 2;
               
        
               
               HSSFRow allsitesworksheetrws = null;
               
             //get the total no of rows per each facility so as to create a TOTALS ROW PER FACILITY  
               
               //TOTAL FOR ALL VISITS ALL FACILITIES PER YEAR
                    int allvisit1total2012=0;
                    int allvisit2total2012=0;
                    int allvisit3total2012=0;
                    int allvisit4total2012=0;
                    int allvisit5total2012=0;
                
                    
                    //2013 totals
                    int allvisit1total2013=0;
                    int allvisit2total2013=0;
                    int allvisit3total2013=0;
                    int allvisit4total2013=0;
                    int allvisit5total2013=0;
                
                
                    //2014 totals
                    
                    int allvisit1total2014=0;
                    int allvisit2total2014=0;
                    int allvisit3total2014=0;
                    int allvisit4total2014=0;
                    int allvisit5total2014=0;
             
               
               

            while (conn.rs1.next()) {

                
                System.out.println("__________________________FACILITY NAME " + conn.rs1.getString("facilityname") + "______________________");


                //determine which data goes to which worksheet.
                HSSFRow rw1 = null;
                HSSFRow villagerow = null;
                
                String Facilityname = conn.rs1.getString("facilityname").toUpperCase();
                
                    int visit1total2012=0;
                    int visit2total2012=0;
                    int visit3total2012=0;
                    int visit4total2012=0;
                    int visit5total2012=0;
                
                    
                    //2013 totals
                    int visit1total2013=0;
                    int visit2total2013=0;
                    int visit3total2013=0;
                    int visit4total2013=0;
                    int visit5total2013=0;
                
                
                    //2014 totals
                    
                    int visit1total2014=0;
                    int visit2total2014=0;
                    int visit3total2014=0;
                    int visit4total2014=0;
                    int visit5total2014=0;
                    
                  //###########################################
                  //###########################################
                                     //2012 VISIST DATA
                  //###########################################
                  //###########################################
                
                //set the facility name for 2012 worksheet 
                 if(is2012){
                    
                     
                     //this is a referencing facility which will be used to enter the totals per site
                     
                    
                    //get the village names per year and per facility
                    conn.rs2=conn.st2.executeQuery(villagesmainqry+" '"+Facilityname+"' and EXTRACT(YEAR FROM VisitDate) ='2012' order by Village ASC");
                    while(conn.rs2.next()){
                     count2012++;   
                     
                     
                     
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
                    
                    int visit1=0;
                    int visit2=0;
                    int visit3=0;
                    int visit4=0;
                    int visit5=0;
                    int totalpervillage=0;
                    
                    
                    //%%%%%%%%VISIT 1 2012%%%%%%%%%%%%%%
                    
                    String visit1qry=visitsmainqry+visit1subqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM DateofVisit) ='2012'";
                    System.out.println("\n"+visit1qry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit1qry);
                    if(conn.rs3.next()){
                    visit1=conn.rs3.getInt(1);
                    }
                    else{
                    visit1=0;
                    }
                    
                    visit1total2012+=visit1;
                    allvisit1total2012+=visit1;
                     //create vist1 column
                  
                    HSSFCell visit1cel= rw1.createCell(mainstartcell+2);
                    visit1cel.setCellValue(visit1);
                    visit1cel.setCellStyle(data_style);
                    
                    
                    
                      //%%%%%%%%VISIT 2 2012%%%%%%%%%%%%%%
                    
                    String visit2qry=visitsmainqry+visit2subqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM DateofVisit) ='2012'";
                    System.out.println("\n"+visit2qry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit2qry);
                    if(conn.rs3.next()){
                    visit2=conn.rs3.getInt(1);
                    }
                    else{
                    visit2=0;
                    }
                       visit2total2012+=visit2;
                       allvisit2total2012+=visit2;
                    
                     //create vist1 column
                  
                    HSSFCell visit2cel= rw1.createCell(mainstartcell+3);
                    visit2cel.setCellValue(visit2);
                    visit2cel.setCellStyle(data_style);
                    
                    
                       //%%%%%%%%VISIT 3 2012%%%%%%%%%%%%%%
                    
                    String visit3qry=visitsmainqry+visit3subqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM DateofVisit) ='2012'";
                    System.out.println("\n"+visit3qry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit3qry);
                    if(conn.rs3.next()){
                    visit3=conn.rs3.getInt(1);
                    }
                    else{
                    visit3=0;
                    }
                       visit3total2012+=visit3;
                       allvisit3total2012+=visit3;
                     //create vist1 column
                  
                    HSSFCell visit3cel= rw1.createCell(mainstartcell+4);
                    visit3cel.setCellValue(visit3);
                    visit3cel.setCellStyle(data_style);
                    
                    
                         //%%%%%%%%VISIT 4 2012%%%%%%%%%%%%%%
                    
                    String visit4qry=visitsmainqry+visit4subqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM DateofVisit) ='2012'";
                    System.out.println("\n"+visit4qry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit4qry);
                    if(conn.rs3.next()){
                    visit4=conn.rs3.getInt(1);
                    }
                    else{
                    visit4=0;
                    }
                       visit4total2012+=visit4;
                       allvisit4total2012+=visit4;
                     
                       //create vist1 column
                  
                    HSSFCell visit4cel= rw1.createCell(mainstartcell+5);
                    visit4cel.setCellValue(visit4);
                    visit4cel.setCellStyle(data_style);
                    
                      //%%%%%%%%VISIT 5 2012%%%%%%%%%%%%%%
                    
                    String visit5qry=visitsmainqry+visit5andabovesubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM DateofVisit) ='2012' ";
                    System.out.println("\n"+visit5qry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit5qry);
                    if(conn.rs3.next()){
                    visit5=conn.rs3.getInt(1);
                    }
                    else{
                    visit5=0;
                    }
                       visit5total2012+=visit5;
                       allvisit5total2012+=visit5;
                     //create vist5 column
                  
                    HSSFCell visit5cel= rw1.createCell(mainstartcell+6);
                    visit5cel.setCellValue(visit5);
                    visit5cel.setCellStyle(data_style);
                    
                    
                    //%%%%%%%%TOTAL PER VILLAGE 2012%%%%%%%%%%%%%%
                    
                    //String totalqry=visitsmainqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='2012' ";
                    //System.out.println("\n"+totalqry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit1qry);
                    if(conn.rs3.next()){
                    totalpervillage=conn.rs3.getInt(1);
                    }
                    else{
                    totalpervillage=0;
                    }
                    
                    //visit 1 total is taken as the distinct total
                     //create total per village column
                  
                    HSSFCell totalpervillcel= rw1.createCell(mainstartcell+7);
                    totalpervillcel.setCellValue(totalpervillage);
                    totalpervillcel.setCellStyle(data_style);
                    
                    
                    
                    }//end of facilities while loop
                    
                     if(visit1total2012>0){
                    //now create the total row per facility
                    count2012++;
                     rw1 = anc2012.createRow(count2012);
                    HSSFCell totalscel= rw1.createCell(mainstartcell);
                    totalscel.setCellValue(""+Facilityname+"  TOTALS");
                    totalscel.setCellStyle(th_style);
                    
                     HSSFCell facilcel= rw1.createCell(mainstartcell+1);
                    facilcel.setCellValue(" ");
                    
                    facilcel.setCellStyle(th_style);
                    
                    rw1.setHeightInPoints(23);
                    //anc2012.addMergedRegion(new CellRangeAddress(count2012,count2012,mainstartcell,mainstartcell+1));
                    if(count2012copy+1<=count2012-1){
                    anc2012.addMergedRegion(new CellRangeAddress(count2012copy+1,count2012-1,mainstartcell,mainstartcell));
                    }
                    count2012copy=count2012;
              //visits 1 total
                    HSSFCell visit1s= rw1.createCell(mainstartcell+2);
                    visit1s.setCellValue(visit1total2012);
                    visit1s.setCellStyle(th_style);
                    
                      //visits 2 total
                    HSSFCell visit2s= rw1.createCell(mainstartcell+3);
                    visit2s.setCellValue(visit2total2012);
                    visit2s.setCellStyle(th_style);
                    
                    
                     //visits 3 total
                    HSSFCell visit3s= rw1.createCell(mainstartcell+4);
                    visit3s.setCellValue(visit3total2012);
                    visit3s.setCellStyle(th_style);
                    
                    
                    //visits 4 total
                    HSSFCell visit4s= rw1.createCell(mainstartcell+5);
                    visit4s.setCellValue(visit4total2012);
                    visit4s.setCellStyle(th_style);
                    
                    
                    //visits 5 total
                    HSSFCell visit5s= rw1.createCell(mainstartcell+6);
                    visit5s.setCellValue(visit5total2012);
                    visit5s.setCellStyle(th_style);
                    
                    
                    //visits 5 total
                    HSSFCell visit6s= rw1.createCell(mainstartcell+7);
                    visit6s.setCellValue(visit1total2012);
                    visit6s.setCellStyle(th_style);
                    
                 }//end of if atleast one visit was made
                    
                    
                    }//end of 2012 data
                 
                 
                 //###########################################
                  //###########################################
                                     //2013 VISIST DATA
                  //###########################################
                  //###########################################
                 
                 
                 //set the facility name for 2013 worksheet
                  if(is2013){
                      
                   
                      
                      
                      
                      int yr=2013;
                      
                      //get the village names per year and per facility
                    conn.rs2=conn.st2.executeQuery(villagesmainqry+" '"+Facilityname+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"' order by Village ASC");
                    while(conn.rs2.next()){
                     count2013++;   
                        
                    rw1 = anc2013.createRow(count2013);
                    HSSFCell facilcel= rw1.createCell(mainstartcell);
                    facilcel.setCellValue(Facilityname);
                    facilcel.setCellStyle(innerdata_style);
                    rw1.setHeightInPoints(23);
                    //create village column
                  
                    HSSFCell villcel= rw1.createCell(mainstartcell+1);
                    villcel.setCellValue(conn.rs2.getString("Village").toUpperCase());
                    villcel.setCellStyle(data_style);
                    
                    
                    
                    
                    int visit1=0;
                    int visit2=0;
                    int visit3=0;
                    int visit4=0;
                    int visit5=0;
                    int totalpervillage=0;
                    
                    
                    
                    //%%%%%%%%VISIT 1 2013%%%%%%%%%%%%%%
                    
                    String visit1qry=visitsmainqry+visit1subqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+visit1qry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit1qry);
                    if(conn.rs3.next()){
                    visit1=conn.rs3.getInt(1);
                    }
                    else{
                    visit1=0;
                    }
                    
                       visit1total2013+=visit1;
                       allvisit1total2013+=visit1;
                     //create vist1 column
                  
                    HSSFCell visit1cel= rw1.createCell(mainstartcell+2);
                    visit1cel.setCellValue(visit1);
                    visit1cel.setCellStyle(data_style);
                    
                    
                    
                      //%%%%%%%%VISIT 2 2013%%%%%%%%%%%%%%
                    
                    String visit2qry=visitsmainqry+visit2subqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+visit2qry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit2qry);
                    if(conn.rs3.next()){
                    visit2=conn.rs3.getInt(1);
                    }
                    else{
                    visit2=0;
                    }
                        visit2total2013+=visit2;
                        allvisit2total2013+=visit2;
                     //create vist1 column
                  
                    HSSFCell visit2cel= rw1.createCell(mainstartcell+3);
                    visit2cel.setCellValue(visit2);
                    visit2cel.setCellStyle(data_style);
                    
                    
                       //%%%%%%%%VISIT 3 2013%%%%%%%%%%%%%%
                    
                    String visit3qry=visitsmainqry+visit3subqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+visit3qry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit3qry);
                    if(conn.rs3.next()){
                    visit3=conn.rs3.getInt(1);
                    }
                    else{
                    visit3=0;
                    }
                        visit3total2013+=visit3;
                        allvisit3total2013+=visit3;
                     //create vist1 column
                  
                    HSSFCell visit3cel= rw1.createCell(mainstartcell+4);
                    visit3cel.setCellValue(visit3);
                    visit3cel.setCellStyle(data_style);
                    
                    
                         //%%%%%%%%VISIT 4 2013%%%%%%%%%%%%%%
                    
                    String visit4qry=visitsmainqry+visit4subqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+visit4qry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit4qry);
                    if(conn.rs3.next()){
                    visit4=conn.rs3.getInt(1);
                    }
                    else{
                    visit4=0;
                    }
                        visit4total2013+=visit4;
                        allvisit4total2013+=visit4;
                     //create vist1 column
                  
                    HSSFCell visit4cel= rw1.createCell(mainstartcell+5);
                    visit4cel.setCellValue(visit4);
                    visit4cel.setCellStyle(data_style);
                    
                      //%%%%%%%%VISIT 5 2013%%%%%%%%%%%%%%
                    
                    String visit5qry=visitsmainqry+visit5andabovesubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"' ";
                    System.out.println("\n"+visit5qry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit5qry);
                    if(conn.rs3.next()){
                    visit5=conn.rs3.getInt(1);
                    }
                    else{
                    visit5=0;
                    }
                        visit5total2013+=visit5;
                        allvisit5total2013+=visit5;
                     //create vist5 column
                  
                    HSSFCell visit5cel= rw1.createCell(mainstartcell+6);
                    visit5cel.setCellValue(visit5);
                    visit5cel.setCellStyle(data_style);
                    
                    
                    //%%%%%%%%TOTAL PER VILLAGE 2013%%%%%%%%%%%%%%
                    
                    //String totalqry=visitsmainqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"' ";
                    //System.out.println("\n"+totalqry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit1qry);
                    if(conn.rs3.next()){
                    totalpervillage=conn.rs3.getInt(1);
                    }
                    else{
                    totalpervillage=0;
                    }
                     //create total per village column
                  
                    HSSFCell totalpervillcel= rw1.createCell(mainstartcell+7);
                    totalpervillcel.setCellValue(totalpervillage);
                    totalpervillcel.setCellStyle(data_style);
                    
                    
                    
                    
                    
                    
                    
                    
                    }//end of facilities while loop
                    
                    
                     if(visit1total2013>0){
                    
                    //now create the total row per facility
                    count2013++;
                     rw1 = anc2013.createRow(count2013);
                    HSSFCell totalscel= rw1.createCell(mainstartcell);
                    totalscel.setCellValue(" "+Facilityname+" TOTALS");
                    totalscel.setCellStyle(th_style);
                    
                     HSSFCell facilcel= rw1.createCell(mainstartcell+1);
                    facilcel.setCellValue(" ");
                    
                    facilcel.setCellStyle(th_style);
                    
                    rw1.setHeightInPoints(23);
                    //anc2013.addMergedRegion(new CellRangeAddress(count2013,count2013,mainstartcell,mainstartcell+1));
                    if(count2013copy+1<=count2013-1){
                    anc2013.addMergedRegion(new CellRangeAddress(count2013copy+1,count2013-1,mainstartcell,mainstartcell));
                    }
                    count2013copy=count2013;
              //visits 1 total
                    HSSFCell visit1s= rw1.createCell(mainstartcell+2);
                    visit1s.setCellValue(visit1total2013);
                    visit1s.setCellStyle(th_style);
                    
                      //visits 2 total
                    HSSFCell visit2s= rw1.createCell(mainstartcell+3);
                    visit2s.setCellValue(visit2total2013);
                    visit2s.setCellStyle(th_style);
                    
                    
                     //visits 3 total
                    HSSFCell visit3s= rw1.createCell(mainstartcell+4);
                    visit3s.setCellValue(visit3total2013);
                    visit3s.setCellStyle(th_style);
                    
                    
                    //visits 4 total
                    HSSFCell visit4s= rw1.createCell(mainstartcell+5);
                    visit4s.setCellValue(visit4total2013);
                    visit4s.setCellStyle(th_style);
                    
                    
                    //visits 5 total
                    HSSFCell visit5s= rw1.createCell(mainstartcell+6);
                    visit5s.setCellValue(visit5total2013);
                    visit5s.setCellStyle(th_style);
                    
                    
                    //visits 5 total
                    HSSFCell visit6s= rw1.createCell(mainstartcell+7);
                    visit6s.setCellValue(visit1total2013);
                    visit6s.setCellStyle(th_style);
                  }
                    
                    }//end of 2013 data
                 
                  //###########################################
                  //###########################################
                                     //2014 VISIST DATA
                  //###########################################
                  //###########################################
                  
                  
                //set the facility name for 2014 worksheet
                   if(is2014){
                       
                       int yr=2014;
                       
                    conn.rs2=conn.st2.executeQuery(villagesmainqry+" '"+Facilityname+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"' order by Village ASC");
                    while(conn.rs2.next()){
                     count2014++;   
                        
                    rw1 = anc2014.createRow(count2014);
                    HSSFCell facilcel= rw1.createCell(mainstartcell);
                    facilcel.setCellValue(Facilityname);
                    facilcel.setCellStyle(innerdata_style);
                     rw1.setHeightInPoints(23);
                    //create village column
                  
                    HSSFCell villcel= rw1.createCell(mainstartcell+1);
                    villcel.setCellValue(conn.rs2.getString("Village").toUpperCase());
                    villcel.setCellStyle(data_style);
                    
                    
                    
                    
                    int visit1=0;
                    int visit2=0;
                    int visit3=0;
                    int visit4=0;
                    int visit5=0;
                    int totalpervillage=0;
                    
                    
                    //%%%%%%%%VISIT 1 2014%%%%%%%%%%%%%%
                    
                    String visit1qry=visitsmainqry+visit1subqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+visit1qry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit1qry);
                    if(conn.rs3.next()){
                    visit1=conn.rs3.getInt(1);
                    }
                    else{
                    visit1=0;
                    }
                        visit1total2014+=visit1;
                        allvisit1total2014+=visit1;
                     //create vist1 column
                  
                    HSSFCell visit1cel= rw1.createCell(mainstartcell+2);
                    visit1cel.setCellValue(visit1);
                    visit1cel.setCellStyle(data_style);
                    
                    
                    
                      //%%%%%%%%VISIT 2 2014%%%%%%%%%%%%%%
                    
                    String visit2qry=visitsmainqry+visit2subqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+visit2qry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit2qry);
                    if(conn.rs3.next()){
                    visit2=conn.rs3.getInt(1);
                    }
                    else{
                    visit2=0;
                    }
                    visit2total2014+=visit2;
                    allvisit2total2014+=visit2;
                     //create vist1 column
                  
                    HSSFCell visit2cel= rw1.createCell(mainstartcell+3);
                    visit2cel.setCellValue(visit2);
                    visit2cel.setCellStyle(data_style);
                    
                    
                       //%%%%%%%%VISIT 3 2014%%%%%%%%%%%%%%
                    
                    String visit3qry=visitsmainqry+visit3subqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+visit3qry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit3qry);
                    if(conn.rs3.next()){
                    visit3=conn.rs3.getInt(1);
                    }
                    else{
                    visit3=0;
                    }
                    visit3total2014+=visit3;
                    allvisit3total2014+=visit3;
                     //create vist1 column
                  
                    HSSFCell visit3cel= rw1.createCell(mainstartcell+4);
                    visit3cel.setCellValue(visit3);
                    visit3cel.setCellStyle(data_style);
                    
                    
                         //%%%%%%%%VISIT 4 2014%%%%%%%%%%%%%%
                    
                    String visit4qry=visitsmainqry+visit4subqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"'";
                    System.out.println("\n"+visit4qry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit4qry);
                    if(conn.rs3.next()){
                    visit4=conn.rs3.getInt(1);
                    }
                    else{
                    visit4=0;
                    }
                    visit4total2014+=visit4;
                    allvisit4total2014+=visit4;
                     //create vist1 column
                  
                    HSSFCell visit4cel= rw1.createCell(mainstartcell+5);
                    visit4cel.setCellValue(visit4);
                    visit4cel.setCellStyle(data_style);
                    
                      //%%%%%%%%VISIT 5 2014%%%%%%%%%%%%%%
                    
                    String visit5qry=visitsmainqry+visit5andabovesubqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"' ";
                    System.out.println("\n"+visit5qry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit5qry);
                    if(conn.rs3.next()){
                    visit5=conn.rs3.getInt(1);
                    }
                    else{
                    visit5=0;
                    }
                    visit5total2014+=visit5;
                    allvisit5total2014+=visit5;
                     //create vist5 column
                  
                    HSSFCell visit5cel= rw1.createCell(mainstartcell+6);
                    visit5cel.setCellValue(visit5);
                    visit5cel.setCellStyle(data_style);
                    
                    
                    //%%%%%%%%TOTAL PER VILLAGE 2014%%%%%%%%%%%%%%
                    
                    //String totalqry=visitsmainqry+" facilityname LIKE '"+Facilityname+"' and village LIKE '"+conn.rs2.getString("Village")+"' and EXTRACT(YEAR FROM VisitDate) ='"+yr+"' ";
                    //System.out.println("\n"+totalqry+"\n");
                    conn.rs3=conn.st4.executeQuery(visit1qry);
                    if(conn.rs3.next()){
                    totalpervillage=conn.rs3.getInt(1);
                    }
                    else{
                    totalpervillage=0;
                    }
                     //create total per village column
                  
                    HSSFCell totalpervillcel= rw1.createCell(mainstartcell+7);
                    totalpervillcel.setCellValue(totalpervillage);
                    totalpervillcel.setCellStyle(data_style);
                    
                    
                    
                    
                    
                    
                    }//end of facilities while loop
                    
                    
                    
                    
                    //now create the total row per facility
                    //only create the totals column IFF atleast one village had a representative here
                    if(visit1total2014>0){
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
                    
                      //visits 1 total
                    HSSFCell visit1s= rw1.createCell(mainstartcell+2);
                    visit1s.setCellValue(visit1total2014);
                    visit1s.setCellStyle(th_style);
                    
                      //visits 2 total
                    HSSFCell visit2s= rw1.createCell(mainstartcell+3);
                    visit2s.setCellValue(visit2total2014);
                    visit2s.setCellStyle(th_style);
                    
                    
                     //visits 3 total
                    HSSFCell visit3s= rw1.createCell(mainstartcell+4);
                    visit3s.setCellValue(visit3total2014);
                    visit3s.setCellStyle(th_style);
                    
                    
                    //visits 4 total
                    HSSFCell visit4s= rw1.createCell(mainstartcell+5);
                    visit4s.setCellValue(visit4total2014);
                    visit4s.setCellStyle(th_style);
                    
                    
                    //visits 5 total
                    HSSFCell visit5s= rw1.createCell(mainstartcell+6);
                    visit5s.setCellValue(visit5total2014);
                    visit5s.setCellStyle(th_style);
                    
                    
                    //visits 5 total
                    HSSFCell visit6s= rw1.createCell(mainstartcell+7);
                    visit6s.setCellValue(visit1total2014);
                    visit6s.setCellStyle(th_style);
                    
                    
                    
                    }//end of if atleast 1 vist was made
                    
                    
                   }//end of 2014 data
                 

            
            
            }//end of main while loop
               
               
//TOTAS PER VISIT PER YEAR
            
            
            if(is2012){
            
                int yr=2012;
             //now create the total row per facility
                    count2012++;
                   HSSFRow  rw1 = anc2012.createRow(count2012);
                    HSSFCell totalscel= rw1.createCell(mainstartcell);
                    totalscel.setCellValue(yr+" AGGREGATE TOTALS ");
                    totalscel.setCellStyle(aggregregatedata_style);
                    
                     HSSFCell facilcel= rw1.createCell(mainstartcell+1);
                    facilcel.setCellValue(" ");
                    
                    facilcel.setCellStyle(aggregregatedata_style);
                    
                    rw1.setHeightInPoints(23);
                    anc2012.addMergedRegion(new CellRangeAddress(count2012,count2012,mainstartcell,mainstartcell+1));
                    
                      //visits 1 total
                    HSSFCell visit1s= rw1.createCell(mainstartcell+2);
                    visit1s.setCellValue(allvisit1total2012);
                    visit1s.setCellStyle(aggregregatedata_style);
                    
                      //visits 2 total
                    HSSFCell visit2s= rw1.createCell(mainstartcell+3);
                    visit2s.setCellValue(allvisit2total2012);
                    visit2s.setCellStyle(aggregregatedata_style);
                    
                    
                     //visits 3 total
                    HSSFCell visit3s= rw1.createCell(mainstartcell+4);
                    visit3s.setCellValue(allvisit3total2012);
                    visit3s.setCellStyle(aggregregatedata_style);
                    
                    
                    //visits 4 total
                    HSSFCell visit4s= rw1.createCell(mainstartcell+5);
                    visit4s.setCellValue(allvisit4total2012);
                    visit4s.setCellStyle(aggregregatedata_style);
                    
                    
                    //visits 5 total
                    HSSFCell visit5s= rw1.createCell(mainstartcell+6);
                    visit5s.setCellValue(allvisit5total2012);
                    visit5s.setCellStyle(aggregregatedata_style);
                    
                    
                    //visits 5 total
                    HSSFCell visit6s= rw1.createCell(mainstartcell+7);
                    visit6s.setCellValue(allvisit1total2012);
                    visit6s.setCellStyle(aggregregatedata_style);
                   
            
            
            }//end of ANNUAL VISIT TOTALS
            
            
           
            
            
             if(is2013){
            
                int yr=2013;
             //now create the total row per facility
                    count2013++;
                   HSSFRow  rw1 = anc2013.createRow(count2013);
                    HSSFCell totalscel= rw1.createCell(mainstartcell);
                    totalscel.setCellValue(yr+" AGGREGATE TOTALS");
                    totalscel.setCellStyle(aggregregatedata_style);
                    
                     HSSFCell facilcel= rw1.createCell(mainstartcell+1);
                    facilcel.setCellValue(" ");
                    
                    facilcel.setCellStyle(aggregregatedata_style);
                    
                    rw1.setHeightInPoints(23);
                    anc2013.addMergedRegion(new CellRangeAddress(count2013,count2013,mainstartcell,mainstartcell+1));
                    
                      //visits 1 total
                    HSSFCell visit1s= rw1.createCell(mainstartcell+2);
                    visit1s.setCellValue(allvisit1total2013);
                    visit1s.setCellStyle(aggregregatedata_style);
                    
                      //visits 2 total
                    HSSFCell visit2s= rw1.createCell(mainstartcell+3);
                    visit2s.setCellValue(allvisit2total2013);
                    visit2s.setCellStyle(aggregregatedata_style);
                    
                    
                     //visits 3 total
                    HSSFCell visit3s= rw1.createCell(mainstartcell+4);
                    visit3s.setCellValue(allvisit3total2013);
                    visit3s.setCellStyle(aggregregatedata_style);
                    
                    
                    //visits 4 total
                    HSSFCell visit4s= rw1.createCell(mainstartcell+5);
                    visit4s.setCellValue(allvisit4total2013);
                    visit4s.setCellStyle(aggregregatedata_style);
                    
                    
                    //visits 5 total
                    HSSFCell visit5s= rw1.createCell(mainstartcell+6);
                    visit5s.setCellValue(allvisit5total2013);
                    visit5s.setCellStyle(aggregregatedata_style);
                    
                    
                    //visits 5 total
                    HSSFCell visit6s= rw1.createCell(mainstartcell+7);
                    visit6s.setCellValue(allvisit1total2013);
                    visit6s.setCellStyle(aggregregatedata_style);
                   
            
            
            }//end of ANNUAL VISIT TOTALS
            
           
            
            
            
            
            
            if(is2014){
            
                int yr=2014;
             //now create the total row per facility
                    count2014++;
                   HSSFRow  rw1 = anc2014.createRow(count2014);
                    HSSFCell totalscel= rw1.createCell(mainstartcell);
                    totalscel.setCellValue(yr+"  AGGREGATE TOTALS");
                    totalscel.setCellStyle(aggregregatedata_style);
                    
                     HSSFCell facilcel= rw1.createCell(mainstartcell+1);
                    facilcel.setCellValue(" ");
                    
                    facilcel.setCellStyle(aggregregatedata_style);
                    
                    rw1.setHeightInPoints(23);
                    anc2014.addMergedRegion(new CellRangeAddress(count2014,count2014,mainstartcell,mainstartcell+1));
                    
                      //visits 1 total
                    HSSFCell visit1s= rw1.createCell(mainstartcell+2);
                    visit1s.setCellValue(allvisit1total2014);
                    visit1s.setCellStyle(aggregregatedata_style);
                    
                      //visits 2 total
                    HSSFCell visit2s= rw1.createCell(mainstartcell+3);
                    visit2s.setCellValue(allvisit2total2014);
                    visit2s.setCellStyle(aggregregatedata_style);
                    
                    
                     //visits 3 total
                    HSSFCell visit3s= rw1.createCell(mainstartcell+4);
                    visit3s.setCellValue(allvisit3total2014);
                    visit3s.setCellStyle(aggregregatedata_style);
                    
                    
                    //visits 4 total
                    HSSFCell visit4s= rw1.createCell(mainstartcell+5);
                    visit4s.setCellValue(allvisit4total2014);
                    visit4s.setCellStyle(aggregregatedata_style);
                    
                    
                    //visits 5 total
                    HSSFCell visit5s= rw1.createCell(mainstartcell+6);
                    visit5s.setCellValue(allvisit5total2014);
                    visit5s.setCellStyle(aggregregatedata_style);
                    
                    
                    //visits 5 total
                    HSSFCell visit6s= rw1.createCell(mainstartcell+7);
                    visit6s.setCellValue(allvisit1total2014);
                    visit6s.setCellStyle(aggregregatedata_style);
                   
            
            
            }//end of ANNUAL VISIT TOTALS
            
            
            


      






                //String Facilityname = conn.rs1.getString("facilityname");

                //============STATISTICS PAGE THE HEADER PART=================================


               

                //=============create dynamic rows  


               



            



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            //THE FAMOUS WHILE LOOP ENDS HERE
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            



            //================================================================================================================
           






      

        Date dat = new Date();

        String dat1 = dat.toString().replace(" ", "_");

        // write it as an excel attachment
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=ANCVISITS_" + dat1 + ".xls");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
        } catch (SQLException ex) {
            Logger.getLogger(ancvisitpervillage.class.getName()).log(Level.SEVERE, null, ex);
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
