/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sendSMS;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Maureen
 */
public class importData1 extends HttpServlet {

    String replyback = "";
    int erroroccured = 0;
    String unuploadedrows = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InvalidFormatException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        dbConnect conn = new dbConnect();
        ArrayList cells = new ArrayList();

        ArrayList allcells = new ArrayList();


        String itemName = "";
        erroroccured = 0;
        unuploadedrows = "";

        itemName = request.getParameter("fname");
        //____________________GET COMPUTER NAME____________________________________
        String computername = InetAddress.getLocalHost().getHostName();
//System.out.println("Computer name "+computername);



        String splits[] = computername.split("-");
        String allpath = getServletContext().getRealPath("/dbase.txt");
        String mydrive = allpath.substring(0, 1);

         
        String pth = mydrive + ":/MHC_UPLOADS/" + itemName;

        
        //create a directory if not exists
        
       //  new File(dbconnpath).mkdir();
        
        //System.out.println("path____________________"+allpath);


        FileInputStream inputFile = new FileInputStream(pth);
        //FileInputStream inputFile = new FileInputStream("//Users//suk//Documents/tes//testexcel.xlsx");

        //now initializing the Workbook with this inputFie


        // Create workbook using WorkbookFactory util method

        Workbook wb = WorkbookFactory.create(inputFile);

        // creating helper for writing cells

        CreationHelper createHelper = wb.getCreationHelper();

        // setting the workbook to handle null

        wb.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);


        Sheet sheet = wb.getSheetAt(0);




        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
//                Iterator<Cell> cellIterator = row.cellIterator();
            if (row.getRowNum() == 0) {
                continue; //just skip the rows if row number is 0
            }
            if (cells.size() > 0 && cells != null) {
                cells.clear();
            }
            if (allcells.size() > 0 && allcells != null) {
                allcells.clear();
            }
//                 String value="";

            int lastCellNo = row.getLastCellNum();
            int firstCellNo = row.getFirstCellNum();

            int rowNo = row.getRowNum();
//            System.out.println(" row number = "+rowNo);
//            System.out.println(" last cell no = "+lastCellNo);


            for (int i = 0; i < lastCellNo; i++) {
                // System.out.println("************");

                Cell cell = row.getCell(i);
                int colIndex = cell.getColumnIndex();
                if (cell == null || getCellValue(row.getCell(i)).trim().isEmpty()) {
                    cell.setCellValue("NO VALUE");
//   System.out.println(" The Cell:"+colIndex+" for row "+row.getRowNum()+" is NULL");
                    //cells.add(cell.getStringCellValue());

                    //System.out.println("NULL CELLS    "+cell.getRichStringCellValue());
                }

                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    String stringvalues = cell.getStringCellValue();
                    cells.add(stringvalues);
//         System.out.println("STRING CELLS  "+stringvalues);

                } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

                    if (DateUtil.isCellDateFormatted(cell)) {
                        HSSFDataFormatter formatter;
                        formatter = new HSSFDataFormatter();
                        String temp = formatter.formatCellValue(cell);
//    System.out.println("DATE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+temp);
                        cells.add(temp);
                    } else {
                        HSSFDataFormatter formatter;
                        formatter = new HSSFDataFormatter();
                        String value = formatter.formatCellValue(cell);
// int value=(int)cell.getNumericCellValue();
                        cells.add(value);
// System.out.println("NUMERIC CELLS "+value);
                    }





                } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                    cells.add(cell.getBooleanCellValue());
                    //System.out.println("BOOLEAN CELLS"+cell.getBooleanCellValue());
                }


                //   System.out.println(" column  index  = "+colIndex);


                int cellType = cell.getCellType();

                // System.out.println(" cell type ="+cellType);
                allcells.add(cells);
                //System.out.println("cells___________"+cells.get(i).toString());





            }
            allcells.add(cells);

//                      allcells2.add(allcells);
            System.out.println("cells___________" + allcells.get(0).toString());
            //System.out.println("cells___________"+allcells.get(1).toString());



            String query = "insert into mother_profile(note_socio,Unit_choice,mnumber,household_no,Survey_Date,ID_Code,note_begin,ANC_Num,Date_of_Birth,marital_stat,mobile_number,mobile_number_other,relationship,messages,language,marital_type,respondentEdu,husbandEdu,Location,sublocation,health_facility,health_facility_distance,transportmode,note_obstetric,parity,surviving_children,miscarriage,delivery_locale,delivered_byc,ANCAnya,ANCVisitsb,Gest_current_pregnancy,Gest_to_firstANC,ANCVisits_now,IBPa,ANC_date,Delivery_Date,ITN_use,HIVstatus,Basic_key,note_delivery,deliveryA,complications,complications_Premature_Delivery,complications_Maternal_Infections,complications_Prolapsed_Cord,complications_Perineal_Damage,complications_Shoulder_Dystocia,complications_Bleeding_Excessively,complications_Eclampsia_Pre_Eclampsia,complications_Obstructed_Labour,complications_Malpresentation_Breech,complications_Other,baby_complications,b_comp_details,b_comp_details_Foetal_Distress,b_comp_details_Trouble_Breathing,b_comp_details_Yellow_Eyes,b_comp_details_Congenital_Infections,b_comp_details_Infections,b_comp_details_Other,note_postnatal,post_natal,hh_photo,hh_location,_hh_location_latitude,_hh_location_longitude,_hh_location_altitude,_hh_location_precision,note_end,meta_instanceID,_id,_uuid,_submission_time,_index,_parent_table_name,_parent_index) values"
                    + "('" + cells.get(0) + "',"
                    + "'" + cells.get(1) + "',"
                    + "'" + cells.get(2) + "',"
                    + "'" + cells.get(3) + "',"
                    + "'" + cells.get(4) + "',"
                    + "'" + cells.get(5) + "',"
                    + "'" + cells.get(6) + "',"
                    + "'" + cells.get(7) + "',"
                    + "'" + cells.get(8) + "',"
                    + "'" + cells.get(9) + "',"
                    + "'" + cells.get(10) + "',"
                    + "'" + cells.get(11) + "',"
                    + "'" + cells.get(12) + "',"
                    + "'" + cells.get(13) + "',"
                    + "'" + cells.get(14) + "',"
                    + "'" + cells.get(15) + "',"
                    + "'" + cells.get(16) + "',"
                    + "'" + cells.get(17) + "',"
                    + "'" + cells.get(18) + "',"
                    + "'" + cells.get(19) + "',"
                    + "'" + cells.get(20) + "',"
                    + "'" + cells.get(21) + "',"
                    + "'" + cells.get(22) + "',"
                    + "'" + cells.get(23) + "',"
                    + "'" + cells.get(24) + "',"
                    + "'" + cells.get(25) + "',"
                    + "'" + cells.get(26) + "',"
                    + "'" + cells.get(27) + "',"
                    + "'" + cells.get(28) + "',"
                    + "'" + cells.get(29) + "',"
                    + "'" + cells.get(30) + "',"
                    + "'" + cells.get(31) + "',"
                    + "'" + cells.get(32) + "',"
                    + "'" + cells.get(33) + "',"
                    + "'" + cells.get(34) + "',"
                    + "'" + cells.get(35) + "',"
                    + "'" + cells.get(36) + "',"
                    + "'" + cells.get(37) + "',"
                    + "'" + cells.get(38) + "',"
                    + "'" + cells.get(39) + "',"
                    + "'" + cells.get(40) + "',"
                    + "'" + cells.get(41) + "',"
                    + "'" + cells.get(42) + "',"
                    + "'" + cells.get(43) + "',"
                    + "'" + cells.get(44) + "',"
                    + "'" + cells.get(45) + "',"
                    + "'" + cells.get(46) + "',"
                    + "'" + cells.get(47) + "',"
                    + "'" + cells.get(48) + "',"
                    + "'" + cells.get(49) + "',"
                    + "'" + cells.get(50) + "',"
                    + "'" + cells.get(51) + "',"
                    + "'" + cells.get(52) + "',"
                    + "'" + cells.get(53) + "',"
                    + "'" + cells.get(54) + "',"
                    + "'" + cells.get(55) + "',"
                    + "'" + cells.get(56) + "',"
                    + "'" + cells.get(57) + "',"
                    + "'" + cells.get(58) + "',"
                    + "'" + cells.get(59) + "',"
                    + "'" + cells.get(60) + "',"
                    + "'" + cells.get(61) + "',"
                    + "'" + cells.get(62) + "',"
                    + "'" + cells.get(63) + "',"
                    + "'" + cells.get(64) + "',"
                    + "'" + cells.get(65) + "',"
                    + "'" + cells.get(66) + "',"
                    + "'" + cells.get(67) + "',"
                    + "'" + cells.get(68) + "',"
                    + "'" + cells.get(69) + "',"
                    + "'" + cells.get(70) + "',"
                    + "'" + cells.get(71) + "',"
                    + "'" + cells.get(72) + "',"
                    + "'" + cells.get(73) + "',"
                    + "'" + cells.get(74) + "',"
                    + "'" + cells.get(75) + "',"
                    + "'" + cells.get(76) + "')";

//                   
            // System.out.println("query +++++++++++++++"+query);

            try {
                conn.state.executeUpdate(query);
                replyback = "<font color=\"orange\"><b>Importing completed ";
            } catch (SQLException se) {
                erroroccured = 1;
                unuploadedrows += row.getRowNum() + " , ";

            }






            System.out.print("REPLY BACK  ====" + replyback);

        }// row and cell iterator

        if (erroroccured == 1) {
            replyback = "<font color=\"red\">Importing completed with an error.<br> Row " + unuploadedrows + " of the excel file contains errors.Check if the ANC numbers are already added.<br/>.</font>";


        } else {

            replyback += "succesfully!!</b></font>";
        }
        try {
            out.println(replyback);
        } finally {
            out.close();
        }

    }

    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();

        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return cell.getNumericCellValue() + "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return cell.getBooleanCellValue() + "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
            return cell.getErrorCellValue() + "";
        } else {
            return null;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            try {
                processRequest(request, response);
            } catch (InvalidFormatException ex) {
                Logger.getLogger(importData1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            replyback = "error while uploading!";
            Logger.getLogger(importData1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            try {
                processRequest(request, response);
            } catch (InvalidFormatException ex) {
                Logger.getLogger(importData1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            replyback = "error while uploading!";
            Logger.getLogger(importData1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
