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
import sender.mobiledbConn;

/**
 *
 * @author Manuel
 */
public class RAWDATA1 extends HttpServlet {

    HSSFWorkbook wb;
    int maxmerging=0;
    String maincountqry="";
   String Year="";
   
   String additiaonalparrams="EXTRACT(YEAR FROM VisitDate) ='"+Year+"' and";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Year="";
            additiaonalparrams="EXTRACT(YEAR FROM VisitDate) ='"+Year+"' and";
           
            
            if(Year.equals("")){additiaonalparrams="";}
            
            wb = new HSSFWorkbook();
             String columnheaders[]={"ANC NUMBER","FIRST NAME","MIDDLE NAME","LAST NAME","PHONE NO.","NOK PHONE NO.","EXPECTED DELIVERY DATE","LAST VISIT DATE","FACILITY NAME","CHW'S NAME","CHWS PHONE NO."};
                
           maxmerging=columnheaders.length;
           
           maincountqry="select count(*) as curcount from mother_details where "+additiaonalparrams+"  facilityname LIKE ";
           
            String Statisticsarr[] = {"FACILITY", "PREGNANT MOTHERS", "DELIVERED MOTHERS", "TOTAL"};


            
            //=====================================



            //==============================CREATE WORKBOOK AND SHEETS FOR EACH SITE  



            HSSFSheet statistics = wb.createSheet("STATISTICS");
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



            //=======STATISTICS HEADER=======================================     

            HSSFRow rw01 = statistics.createRow(0);
            rw01.setHeightInPoints(35);
            HSSFCell rw01cell01 = rw01.createCell(4);
            rw01cell01.setCellValue("NUMBER OF WOMEN ENROLLED  ");
            rw01cell01.setCellStyle(style_header);







            //===============ALL SITES HEADER===============================

            HSSFRow rw0 = allsites.createRow(0);
            rw0.setHeightInPoints(35);
            HSSFCell rw0cell0 = rw0.createCell(0);
            rw0cell0.setCellValue("ENROLLED  MOTHERS FROM SOLIAN , NGUBERETI , EMINING  HEALTH FACILITIES ");
            rw0cell0.setCellStyle(style_header);
            
            
            

            //===============SOLIAN DATA HEADER=================================      


            HSSFRow solheader = solian.createRow(0);
            solheader.setHeightInPoints(35);
            HSSFCell solheadercell0 = solheader.createCell(0);
            solheadercell0.setCellValue("ENROLLED  MOTHERS FROM SOLIAN HEALTH FACILITY ");
            solheadercell0.setCellStyle(style_header);



            //===============EMINING DATA HEADER=================================      


            HSSFRow eminiheader = emining.createRow(0);
            eminiheader.setHeightInPoints(35);
            HSSFCell emiheadercell0 = eminiheader.createCell(0);
            emiheadercell0.setCellValue("ENROLLED  MOTHERS FROM EMINING HEALTH FACILITY ");
            emiheadercell0.setCellStyle(style_header);


            //===============NGUBERETI DATA HEADER=================================      


            HSSFRow ngubeheader = ngubereti.createRow(0);
            ngubeheader.setHeightInPoints(35);
            HSSFCell nguheadercell0 = ngubeheader.createCell(0);
            nguheadercell0.setCellValue("ENROLLED  MOTHERS FROM NGUBERETI HEALTH FACILITY ");
            nguheadercell0.setCellStyle(style_header);




            //===============CONTROL DATA HEADER=================================      


            HSSFRow controlheader = torongo.createRow(0);
            controlheader.setHeightInPoints(35);
            HSSFCell conheadercell0 = controlheader.createCell(0);
            conheadercell0.setCellValue("ENROLLED  MOTHERS FROM TORONGO, TIMBOROA, SIMOTWET HEALTH FACILITY ");
            conheadercell0.setCellStyle(style_header);


        //===========================MAIN SITE COLUMN HEADERS========================================
           HSSFRow allsitescolumnheader = allsites.createRow(1);
                allsitescolumnheader.setHeightInPoints(30);
                HSSFCell rwcolheader=null;
                
               
                for(int d=0;d<maxmerging;d++){
                 rwcolheader = allsitescolumnheader.createCell(d+0);
                  rwcolheader.setCellValue(columnheaders[d]);
                  rwcolheader.setCellStyle(th_style);
                }
                for (int a = 1; a <=maxmerging; a++) {
                        allsites.setColumnWidth(a, 4500);
                    }


               






            //dbConn conn = new dbConn();
            mobiledbConn conn = new mobiledbConn();


            //===============DISTINCT FACILITY NAMES===========


            String facils = "Select  Distinct facilityname from mother_details order by facilityname asc";

            conn.rs1 = conn.st1.executeQuery(facils);

            HSSFRow rw2 = null;

            HSSFRow rwy = null;
            HSSFRow rwy1 = null;
            HSSFRow lastatatisticsrow = null;


            rw2 = statistics.createRow(1);
            rw2.setHeightInPoints(30);

            //=====if its the first row create the headers first first==========

            //==FACILITY NAME  || ANC MOTHERS || PNC MOTHERS  ||  UNKNOWN DELIVERY DATE  ||  TOTAL



            for (int a = 0; a < Statisticsarr.length; a++) {

                statistics.setColumnWidth(4, 5700);
                HSSFCell rwycell1 = rw2.createCell(4 + a);
                rwycell1.setCellValue(Statisticsarr[a]);
                rwycell1.setCellStyle(th_style);

                System.out.println("&&&&&&&&&&&&&&&&&&Category____" + Statisticsarr[a]);

            }

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
            
            //=====this row no variable is used for all sites and will keep on incementing 
               int allsitesrowno = 1;
               
            //=====this variable initializes a row for the all sites worksheet   
               HSSFRow allsitesworksheetrws = null;

            while (conn.rs1.next()) {

                count++;
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
               //=========`
                

//=================DEFINE THE COLUMN  HEADERS PER EACH FACILITY SHEET========================

                 HSSFCell rwcolheader1=null;
                
                for(int d=0;d<maxmerging;d++){
                 rwcolheader1 = rw1.createCell(d+0);
                  rwcolheader1.setCellValue(columnheaders[d]);
                  rwcolheader1.setCellStyle(th_style);
                }

            int sheetsstartcel=0;
            int statisticsstartcell=4;

                allsites.addMergedRegion(new CellRangeAddress(0, 0, sheetsstartcel, maxmerging-1));
                solian.addMergedRegion(new CellRangeAddress(0, 0, sheetsstartcel, maxmerging-1));
                ngubereti.addMergedRegion(new CellRangeAddress(0, 0, sheetsstartcel, maxmerging-1));
                emining.addMergedRegion(new CellRangeAddress(0, 0,sheetsstartcel, maxmerging-1));
                torongo.addMergedRegion(new CellRangeAddress(0, 0, sheetsstartcel, maxmerging-1));
                statistics.addMergedRegion(new CellRangeAddress(0, 0, statisticsstartcell, Statisticsarr.length+3));
















                String Facilityname = conn.rs1.getString("facilityname");

                //============STATISTICS PAGE THE HEADER PART=================================


                statistics.setColumnWidth(4 + count, 5000);

                //=============create dynamic rows  

                //starting column two    
                rwy1 = statistics.createRow(count + 1);
                rwy1.setHeightInPoints(30);

                //=====fACILITY NAMES COL==============

                HSSFCell facilcell = rwy1.createCell(4);
                //====ANC MUMS ROWS=====================
                HSSFCell anccol = rwy1.createCell(5);

                //======PNC COLS========================
                HSSFCell pnccol = rwy1.createCell(6);

                

                //=======TOTAL COLS=====================
                HSSFCell totalcol = rwy1.createCell(7);






                facilcell.setCellValue(conn.rs1.getString("facilityname").toUpperCase());
                facilcell.setCellStyle(data_style);

                //get the summaries for this table inside
                //========================ANC NUMBER SUMMARIES=================================
                String ancqr = maincountqry+" '" + conn.rs1.getString("facilityname") + "' and (DATE(delivery_date) >= CURDATE() and delivery_date!='' and  delivery_date!='-' and delivery_date!='null')";

                System.out.println(ancqr);

                conn.rs3 = conn.st3.executeQuery(ancqr);

                int ANCMOMS = 0;

                if (conn.rs3.next()) {
                    ANCMOMS = conn.rs3.getInt("curcount");
                }

                anccol.setCellValue(ANCMOMS);
                anccol.setCellStyle(innerdata_style);

                TOTALANCMOMS += ANCMOMS;
                System.out.println("^^^^^^^^^^^^^^^^^^^" + ANCMOMS + " PREGNANT MOTHERS IN FACILITY  " + Facilityname);


                //==================================== PNC NUMBER SUMMARIES========================


                String pncqr = maincountqry+" '" + conn.rs1.getString("facilityname") + "' and (DATE(delivery_date) < CURDATE() and delivery_date!='' and  delivery_date!='-' and delivery_date!='null')";

                System.out.println(pncqr);

                conn.rs3 = conn.st3.executeQuery(pncqr);

                int PNCMOMS = 0;

                if (conn.rs3.next()) {
                    PNCMOMS = conn.rs3.getInt("curcount");
                }

                pnccol.setCellValue(PNCMOMS);
                pnccol.setCellStyle(innerdata_style);

                TOTALPNCMOMS += PNCMOMS;
                System.out.println("^^^^^^^^^^^^^^^^^^^" +TOTALPNCMOMS+ "  >>"+PNCMOMS + " PNC MOTHERS IN FACILITY  " + Facilityname);





                //=========================UNCATEGORISED MOTHERS============================

                String nodateqr = maincountqry+" '" + conn.rs1.getString("facilityname") + "' and ( delivery_date='' or delivery_date='-' or delivery_date='null')";

                System.out.println(nodateqr);

                conn.rs3 = conn.st3.executeQuery(nodateqr);

                int NODATEMOMS = 0;

                if (conn.rs3.next()) {
                    NODATEMOMS = conn.rs3.getInt("curcount");
                }
                
                TOTALNODATEMOMS += NODATEMOMS;

                System.out.println("^^^^^^^^^^^^^^^^^^^" + NODATEMOMS + " UNKNOWN DELIVERY DATE MOTHERS IN FACILITY  " + Facilityname);


                //============================TOTAL MOTHERS==================================== 

                String totalqr = maincountqry+" '" + conn.rs1.getString("facilityname") + "' ";

                System.out.println(totalqr);

                conn.rs3 = conn.st3.executeQuery(totalqr);

                int TOTALMOMS = 0;

                if (conn.rs3.next()) {
                    TOTALMOMS = conn.rs3.getInt("curcount");
                }
                totalcol.setCellValue(TOTALMOMS);

                totalcol.setCellStyle(innerdata_style);

                TOTALTOTALS += TOTALMOMS;
                System.out.println("^^^^^^^^^^^^^^^^^^^" + TOTALMOMS + " TOTAL MOTHERS IN FACILITY  " + Facilityname);



                String Currentfacility = conn.rs1.getString("facilityname");



                String mainqr = "Select anc_no,DOB ,FName ,SName,LName,PhoneNo ,NOKPhoneNo,LocationID,Gest_to_firstANC,Anc_visit,VisitDate,delivery_date,status_id,Age,Gravida,parity,maritalStatus,education,occupation,nok_consent,languagePreferred,facilityname, ChwID,chv_fname,chv_mname,chv_lname from mother_details join chw on mother_details.ChwID=chw.chv_phone where "+additiaonalparrams+" facilityname='" + Currentfacility + "' group by mother_details.motherID  order by delivery_date desc";

                conn.rs = conn.st.executeQuery(mainqr);

                System.out.println("MAIN QUERY___________"+mainqr);
                
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
                    
                    
                    int cellpos=0;
                    
                    
                    
                    //ANC NUMBER
                    
                    //specific site workbook
                    HSSFCell allsitescellx = allsitesrw.createCell(cellpos);
                    allsitescellx.setCellValue(conn.rs.getString("anc_no"));
                    allsitescellx.setCellStyle(data_style);

                    if (conn.rs.getString("anc_no") == null || conn.rs.getString("anc_no").equals("")) {
                        allsitescellx.setCellValue("NO ANC");
                    }

                    //all sites worksheet
                    HSSFCell allsitescellxx = allsitesworksheetrws.createCell(cellpos);
                    allsitescellxx.setCellValue(conn.rs.getString("anc_no"));
                    allsitescellxx.setCellStyle(data_style);

                    if (conn.rs.getString("anc_no") == null || conn.rs.getString("anc_no").equals("")) {
                        allsitescellxx.setCellValue("NO ANC");
                    }

                    cellpos++;
                    //FIRST NAME

                                        
                    HSSFCell allsitescell1 = allsitesrw.createCell(cellpos);
                    allsitescell1.setCellValue(conn.rs.getString("FName").toUpperCase());
                    allsitescell1.setCellStyle(data_style);
                    if (conn.rs.getString("FName").equals("null")) {
                        allsitescell1.setCellValue("");
                    }
                    //all sites worksheet
                     HSSFCell allsitescell11 = allsitesworksheetrws.createCell(cellpos);
                    allsitescell11.setCellValue(conn.rs.getString("FName").toUpperCase());
                    allsitescell11.setCellStyle(data_style);
                    if (conn.rs.getString("FName").equals("null")) {
                        allsitescell11.setCellValue("");
                    }
                    
                 cellpos++;
                    //MIDDLE NAME

                    HSSFCell allsitescell2 = allsitesrw.createCell(cellpos);
                    allsitescell2.setCellValue(conn.rs.getString("SName").toUpperCase());
                    allsitescell2.setCellStyle(data_style);
                    if (conn.rs.getString("SName").equals("null")) {
                        allsitescell2.setCellValue("");
                    }
                    //all sites worksheet
                      HSSFCell allsitescell22 = allsitesworksheetrws.createCell(cellpos);
                    allsitescell22.setCellValue(conn.rs.getString("SName").toUpperCase());
                    allsitescell22.setCellStyle(data_style);
                    if (conn.rs.getString("SName").equals("null")) {
                        allsitescell22.setCellValue("");
                    }
                    cellpos++;
                    
                    //LAST NAME
                    
                    HSSFCell allsitescell3 = allsitesrw.createCell(cellpos);
                    allsitescell3.setCellValue(conn.rs.getString("LName").toUpperCase());
                    allsitescell3.setCellStyle(data_style);
                    if (conn.rs.getString("LName").equals("null")) {
                        allsitescell3.setCellValue("");
                    }
                    
                    //all sites worksheet

                    HSSFCell allsitescell33 = allsitesworksheetrws.createCell(cellpos);
                    allsitescell33.setCellValue(conn.rs.getString("LName").toUpperCase());
                    allsitescell33.setCellStyle(data_style);
                    if (conn.rs.getString("LName").equals("null")) {
                        allsitescell33.setCellValue("");
                    }
                    
                    cellpos++;
                    //PHONE NUMBER

                    HSSFCell allsitescell4 = allsitesrw.createCell(cellpos);
                    allsitescell4.setCellValue(conn.rs.getString("PhoneNo"));
                    allsitescell4.setCellStyle(data_style);
                    if (conn.rs.getString("PhoneNo").equals("0")||conn.rs.getString("PhoneNo").equals("N/A")) {
                        allsitescell4.setCellValue("NO NUMBER");
                    }
                    
                    //all sites worksheet
                     HSSFCell allsitescell44 = allsitesworksheetrws.createCell(cellpos);
                    allsitescell44.setCellValue(conn.rs.getString("PhoneNo"));
                    allsitescell44.setCellStyle(data_style);
                    if (conn.rs.getString("PhoneNo").equals("0")||conn.rs.getString("PhoneNo").equals("N/A")) {
                        allsitescell44.setCellValue("NO NUMBER");
                    }
                    
                               cellpos++;
                               
                    //NOK PHONE NUMBER

                    HSSFCell allsitescell5 = allsitesrw.createCell(cellpos);
                    allsitescell5.setCellValue(conn.rs.getString("NOKPhoneNo"));
                    allsitescell5.setCellStyle(data_style);
                    
                    System.out.println("NOK PHONE IS__"+conn.rs.getString("NOKPhoneNo"));
                    
                    if (conn.rs.getString("NOKPhoneNo")==null||conn.rs.getString("PhoneNo").equals("N/A")) {
                        allsitescell5.setCellValue("NO NUMBER");
                    }

                    //all sites worksheet
                    HSSFCell allsitescell55 = allsitesworksheetrws.createCell(cellpos);
                    allsitescell55.setCellValue(conn.rs.getString("NOKPhoneNo"));
                    allsitescell55.setCellStyle(data_style);
                    if (conn.rs.getString("NOKPhoneNo")==null) {
                        allsitescell55.setCellValue("NO NUMBER");
                    }
                    
                                     cellpos++;
                    //DELIVERY DATE

                    HSSFCell allsitescell6 = allsitesrw.createCell(cellpos);
                    allsitescell6.setCellValue(conn.rs.getString("delivery_date"));
                    allsitescell6.setCellStyle(data_style);
                    if (conn.rs.getString("delivery_date")==null || conn.rs.getString("delivery_date").equals("-") || conn.rs.getString("delivery_date").equals("")) {
                        allsitescell6.setCellValue("NO DATE");
                    }


                    //all sites worksheet
                    HSSFCell allsitescell66 = allsitesworksheetrws.createCell(cellpos);
                    allsitescell66.setCellValue(conn.rs.getString("delivery_date"));
                    allsitescell66.setCellStyle(data_style);
                    if (conn.rs.getString("delivery_date")==null || conn.rs.getString("delivery_date").equals("-") || conn.rs.getString("delivery_date").equals("")) {
                        allsitescell66.setCellValue("NO DATE");
                    }

                    cellpos++;
                    //VISIT DATE

                    HSSFCell allsitescell7 = allsitesrw.createCell(cellpos);
                    allsitescell7.setCellValue(conn.rs.getString("visitDate"));
                    allsitescell7.setCellStyle(data_style);
                    if (conn.rs.getString("visitDate")==null || conn.rs.getString("visitDate").equals("") || conn.rs.getString("visitDate").equals("")) {
                        allsitescell7.setCellValue("NO DATE");
                    }

                    //all sites worksheet
                     HSSFCell allsitescell77 = allsitesworksheetrws.createCell(cellpos);
                    allsitescell77.setCellValue(conn.rs.getString("visitDate"));
                    allsitescell77.setCellStyle(data_style);
                    if (conn.rs.getString("visitDate")==null || conn.rs.getString("visitDate").equals("") || conn.rs.getString("visitDate").equals("")) {
                        allsitescell77.setCellValue("NO DATE");
                    }
                    
                    cellpos++;
                    
                    //FACILITY NAME

                    HSSFCell allsitescell8 = allsitesrw.createCell(cellpos);
                    allsitescell8.setCellValue(conn.rs.getString("facilityname").toUpperCase());
                    allsitescell8.setCellStyle(data_style);
                    
                    //all sites worksheet
                    HSSFCell allsitescell88 = allsitesworksheetrws.createCell(cellpos);
                    allsitescell88.setCellValue(conn.rs.getString("facilityname").toUpperCase());
                    allsitescell88.setCellStyle(data_style);

                    cellpos++;
                    
                    //CHW NAME
                    
                    
                      HSSFCell allsitescell9 = allsitesrw.createCell(cellpos);
                    allsitescell9.setCellValue(conn.rs.getString("chv_fname").toUpperCase()+" "+conn.rs.getString("chv_mname").toUpperCase()+" "+conn.rs.getString("chv_lname").toUpperCase());
                    allsitescell9.setCellStyle(data_style);
                    
                    //all sites worksheet
                    HSSFCell allsitescell99 = allsitesworksheetrws.createCell(cellpos);
                     allsitescell99.setCellValue(conn.rs.getString("chv_fname").toUpperCase()+" "+conn.rs.getString("chv_mname").toUpperCase()+" "+conn.rs.getString("chv_lname").toUpperCase());
                   allsitescell99.setCellStyle(data_style);
                    
                    
                    
                  cellpos++;
                    
                    //CHW PHONE
                    
                    
                      HSSFCell allsitescell10 = allsitesrw.createCell(cellpos);
                    allsitescell10.setCellValue(conn.rs.getString("ChwID").toUpperCase());
                    allsitescell10.setCellStyle(data_style);
                    
                    //all sites worksheet
                    HSSFCell allsitescell1010 = allsitesworksheetrws.createCell(cellpos);
                     allsitescell1010.setCellValue(conn.rs.getString("ChwID").toUpperCase());
                   allsitescell1010.setCellStyle(data_style);
                    
                    
                       
                    
                    

                }//end of while loop



            }



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            //THE FAMOUS WHILE LOOP ENDS HERE
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            



            //================================================================================================================
            statistics.setColumnWidth(2 + count, 5000);

            lastatatisticsrow = statistics.createRow(count + 2);
            lastatatisticsrow.setHeightInPoints(30);


            //=====fACILITY NAMES COL==============

            HSSFCell facilcell = lastatatisticsrow.createCell(4);
            //====ANC MUMS ROWS=====================
            HSSFCell anccol = lastatatisticsrow.createCell(5);

            //======PNC COLS========================
            HSSFCell pnccol = lastatatisticsrow.createCell(6);

     

            //=======TOTAL COLS=====================
            HSSFCell totalcol = lastatatisticsrow.createCell(7);

            //====TOTAL COL======   
            facilcell.setCellValue("TOTAL");
            facilcell.setCellStyle(data_style);

            //====ANC TOTALS======
            anccol.setCellValue(TOTALANCMOMS);
            anccol.setCellStyle(innerdata_style);

            //====PNC TOTALS======
            pnccol.setCellValue(TOTALPNCMOMS);
            pnccol.setCellStyle(innerdata_style);

         

            //=====TOTAL TOTALS
            totalcol.setCellValue(TOTALTOTALS);

            totalcol.setCellStyle(innerdata_style);


//=====================SET CELL WIDTH FOR THE MAIN WIDTH AND==================================

            for (int a = 1; a <= 12; a++) {

                allsites.setColumnWidth(a, 5000);

            }



        } catch (SQLException ex) {
            Logger.getLogger(RAWDATA1.class.getName()).log(Level.SEVERE, null, ex);
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
        response.setHeader("Content-Disposition", "attachment; filename=MOBILE_ENROLLMENT_" + dat1 + ".xls");
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
