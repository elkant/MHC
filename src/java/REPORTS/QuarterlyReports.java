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
public class QuarterlyReports extends HttpServlet {

    HSSFWorkbook wb;
    int maxmerging=0;
    String maincountqry="";
    String ancmaincountqry="";
    String pncmaincountqry="";
    String matmaincountqry="";
    String groupbyqry="";
    String Year="";
    int count=0;
   
   String additiaonalparrams="EXTRACT(YEAR FROM VisitDate) ='"+Year+"' and";
   String ancadditiaonalparrams="EXTRACT(YEAR FROM DateofVisit) ='"+Year+"' and";
   String matadditiaonalparrams="EXTRACT(YEAR FROM AdmissionDate) ='"+Year+"' and";
   String pncadditiaonalparrams="EXTRACT(YEAR FROM VisitDate) ='"+Year+"' and";
   
   dbConn conn =null;
   HSSFSheet ancsheet =null;
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
            
            
            curheaderrowno=0;
           
             curcolumnheader=1;
           
            
            if(Year.equals("")){additiaonalparrams="";}
            
            wb = new HSSFWorkbook();
             String columnheaders[]={"ANC NUMBER","FIRST NAME","MIDDLE NAME","LAST NAME","PHONE NUMBER","NOK PHONE NUMBER","DELIVERY DATE","VISIT DATE","FACILITY NAME"};
          
             
             String AncvisitsHeader1[]={"","Visit Number","","","",""};
             String AncvisitsHeader2[]={"QUARTER","1st ","2nd ","3rd ","4th ","5th  and above"};
             
             String maternitycolHeader1[]={"","Delivery Type","","","Baby Status","",""};
             String maternitycolHeader2[]={"QUARTER","Live Birth","Fsb","Msb","Alive","Dead"};
                
             
           maxmerging=columnheaders.length;
           
          
           groupbyqry=" group by motherid ";
           
           
           
            


            count=0;
            //=====================================



            //==============================CREATE WORKBOOK AND SHEETS FOR EACH SITE  



             ancsheet = wb.createSheet("ANC Visits");
            HSSFSheet maternitysheet = wb.createSheet("Maternity  Register");

           


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

           


            //===============ANC VISITS ROW HEADER===============================

            HSSFRow anccolheader = ancsheet.createRow(0);
            anccolheader.setHeightInPoints(35);
            HSSFCell rw0cell0=anccolheader.createCell(1);
            rw0cell0.setCellValue("ANC VISITS QUARTERLY REPORTS  ");
            rw0cell0.setCellStyle(style_header);
            
            
            

            //===============MATERNITY REGISTER ROW HEADER=================================      


            HSSFRow matheader = maternitysheet.createRow(0);
            matheader.setHeightInPoints(35);
            HSSFCell solheadercell0 = matheader.createCell(1);
            solheadercell0.setCellValue("QUARTERLY MATERNITY REGISTER DATA ");
            solheadercell0.setCellStyle(style_header);
         
         HSSFRow anccolheader1 = ancsheet.createRow(1);   
         HSSFRow anccolheader2 = ancsheet.createRow(2);   
         HSSFCell ancheadercells=null;

                for(int d=0; d<AncvisitsHeader1.length;d++){
                    //create 
                  ancheadercells = anccolheader1.createCell(d+1);
                  ancheadercells.setCellValue(AncvisitsHeader1[d]);
                  ancheadercells.setCellStyle(th_style);
                 
                  
                  //create the second header
                   ancheadercells = anccolheader2.createCell(d+1);
                  ancheadercells.setCellValue(AncvisitsHeader2[d]);
                  ancheadercells.setCellStyle(th_style);
                }
                for (int a = 1; a <=AncvisitsHeader1.length; a++) {
                        ancsheet.setColumnWidth(a, 4500);
                    }

//create the maternity headers t
               
         
         HSSFRow matcolheader1 = maternitysheet.createRow(1);   
         HSSFRow matcolheader2 = maternitysheet.createRow(2);   
         HSSFCell matheadercells=null;

                for(int d=0; d<maternitycolHeader1.length;d++){
                    //create 
                  matheadercells = matcolheader1.createCell(d+1);
                  matheadercells.setCellValue(maternitycolHeader1[d]);
                  matheadercells.setCellStyle(th_style);
                 
                  
                  //create the second header
                   matheadercells = matcolheader2.createCell(d+1);
                  matheadercells.setCellValue(maternitycolHeader2[d]);
                  matheadercells.setCellStyle(th_style);
                }
              
             for (int a = 1; a <=maternitycolHeader1.length; a++) {
                        maternitysheet.setColumnWidth(a, 4500);
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




            }



//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            //THE FAMOUS WHILE LOOP ENDS HERE
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            



          


//=====================SET CELL WIDTH FOR THE MAIN WIDTH AND==================================

           


        } catch (SQLException ex) {
            Logger.getLogger(QuarterlyReports.class.getName()).log(Level.SEVERE, null, ex);
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
        response.setHeader("Content-Disposition", "attachment; filename=QUARTERLY_REPORTS_" + dat1 + ".xls");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();







    }
    
    
    public int createsummaryreport(String yr){
        try {
            String facils = " Select  Distinct facilityname from mother_details order by facilityname asc";

            
            
           HSSFRow  rw2 = ancsheet.createRow(count+1);
            rw2.setHeightInPoints(30);

         
            
         
                       
                   //=======STATISTICS HEADERS=======================================     

                  
            
            HSSFRow rw01 = ancsheet.createRow(count);
            rw01.setHeightInPoints(35);
            HSSFCell rw01cell01 = rw01.createCell(4);
            rw01cell01.setCellValue("KOIBATEK MNCH DATA SUMMARY  FOR YEAR "+yr+"");
            rw01cell01.setCellStyle(style_header);
            ancsheet.addMergedRegion(new CellRangeAddress(curheaderrowno, curheaderrowno, 4, 8));
            
               //=====if its the first row create the headers first first==========

            //==FACILITY NAME  || ANC MOTHERS ||MATERNITY MOTHERS|| PNC MOTHERS   ||  TOTAL

         
            
//               for (int a = 0; a < Statisticsarr.length; a++) {
//
//                statistics.setColumnWidth(a+4, 5700);
//                HSSFCell rwycell1 = rw2.createCell(4 + a);
//                rwycell1.setCellValue(Statisticsarr[a]);
//                rwycell1.setCellStyle(th_style);
//                
//                                }
            
            
              
                       //add the query part with the year at this point
                        
                        
                   additiaonalparrams="EXTRACT(YEAR FROM VisitDate) ='"+yr+"' and";
                   ancadditiaonalparrams="EXTRACT(YEAR FROM DateofVisit) ='"+yr+"' and";
                   matadditiaonalparrams="EXTRACT(YEAR FROM AdmissionDate) ='"+yr+"' and";
                   pncadditiaonalparrams="EXTRACT(YEAR FROM postnat_atof.VisitDate) ='"+yr+"' and";
                   
                   
                     
                   maincountqry="select count(distinct motherID) as curcount from mother_details where "+additiaonalparrams+"  facilityname LIKE ";
                   ancmaincountqry="select count(distinct atoh.motherid) as curcount from mother_details join atoh on mother_details.motherID=atoh.motherid where "+ancadditiaonalparrams+"  facilityname LIKE ";
                   pncmaincountqry="select count(distinct postnat_atof.motherid) as curcount from mother_details join postnat_atof on mother_details.motherID=postnat_atof.motherid where "+pncadditiaonalparrams+"  facilityname LIKE ";
                   matmaincountqry="select count(distinct mat_atoh.motherid) as curcount from mother_details join mat_atoh on mother_details.motherID=mat_atoh.motherid where "+matadditiaonalparrams+"  facilityname LIKE ";
                  
                   
                   
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
                       HSSFRow rwy1 = ancsheet.createRow(count + 1);
                       
                        
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
                        String ancqr = ancmaincountqry+" '" + conn.rs1.getString("facilityname") + "'  ";

                        System.out.println(ancqr);

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
                            TOTALMOMS=ANCMOMS+PNCMOMS+MATMOMS;
                            YEARLYTOTALTOTALS+=ANCMOMS+PNCMOMS+MATMOMS;
        //                if (conn.rs3.next()) {
        //                    TOTALMOMS = conn.rs3.getInt("curcount");
        //                }
                        totalcol.setCellValue(TOTALMOMS);

                        totalcol.setCellStyle(innerdata_style);

                        TOTALTOTALS += TOTALMOMS;
                        System.out.println("^^^^^^^^^^^^^^^^^^^" + TOTALMOMS + " TOTAL MOTHERS IN FACILITY  " + Facilityname);
                    
                        
                        
                        
                        

            
            
            }//end of while
                
                
         //================================================================================================================
            //statistics.setColumnWidth(2 + count, 5000);
         count=count+2;
                
         HSSFRow   lastatatisticsrow = ancsheet.createRow(count );
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
            totalcol.setCellValue(YEARLYTOTALTOTALS);

            totalcol.setCellStyle(innerdata_style);         
                
                
                
                
                
                
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
