/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mother_child;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sendSMS.dbConnect;
import sender.getPrevPregDetails;

/**
 *
 * @author SIXTYFOURBIT
 */
public class QtoWRegister extends HttpServlet {

    
    
    String query="";
    String QtoW="";
    dbConnect conn = new dbConnect();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         String anc_no="";
         String motherID="";
         anc_no=request.getParameter("ancno");
         motherID=request.getParameter("motherID");
        query = "Select * from qtow where motherid='"+motherID+"'";



        QtoW = "";
        QtoW +="<tr>"
                +"<th></th>"
//                +"<th rowspan=\"2\" style=\"width: 40px;\"> Haemoglobin </th>"
//                +"<th rowspan=\"2\" style=\"width: 60px;\"> RPR/VDRL </th>"
                +"<th colspan=\"4\" style=\"width: 100px;\">Laboratory </th>"
                +"<th colspan=\"3\" style=\"width: 80px;\">ART Eligibilty</th>"
                + "</tr>";
               
              
        
               QtoW +="<tr>"
                       + "<th></th>"
                       + "<th style=\"width: 100px;\"></th>"
                +"<th style=\"width: 100px;\"> </th>"
                +"<th colspan=\"2\" style=\"width: 100px;\">Hiv Results</th>"
                +"<th colspan=\"2\" style=\"width: 100px;\">Assessed Through</th>"
                +"<th style=\"width: 100px;\"></th></tr>";
               
               QtoW +="<tr>"
                + "<th></th>"
                + "<th>Haemoglobin</th>"
                + "<th>RPR/DRL</th>"
                + "<th style=\"width: 100px;\">Initial</th>"
                +"<th style=\"width: 100px;\">Retest </th>"
                +"<th style=\"width: 100px;\">WHO Stage</th>"
                +"<th style=\"width: 100px;\">CD4</th>"
                +"<th style=\"width: 100px;\">Start on ART IN ANC</th></tr>";
               
               QtoW +="<tr>"
                + "<th></th>"
                + "<th>q</th>"
                + "<th>r</th>"
                + "<th style=\"width: 100px;\">s</th>"
                +"<th style=\"width: 100px;\">t </th>"
                +"<th style=\"width: 100px;\">u</th>"
                +"<th style=\"width: 100px;\">v</th>"
                +"<th style=\"width: 100px;\">w</th></tr>";
               
                      
        

      
        try {

            //============================================================PREVIOUS PREGNANCY TABLE DETAILS==========================   


if(conn.state.isClosed()){conn = new dbConnect();}

            conn.rs1 = conn.state.executeQuery(query);
            //get the values from the databse and put them in the htmlfield appropriately

            int count1 = 1;
            int count = 1;
            String option="";
            String option1="";
            String option2="";
           String hivInit[]={"P","N","KP","U"};
           String retest[]={"P","N","NA"};
           String rpr[]={"P","N","ND"};
            while (conn.rs1.next()) {
                
                
                
                // arrray for hiv init options
                 for(int a=0;a<hivInit.length;a++){
                
                if(hivInit[a].equals(conn.rs1.getString("HIVinit"))){
                    
                option1+="<option selected value=\""+hivInit[a]+"\">"+hivInit[a]+"</option>";
                
                }
                
                else{
                option1+="<option value=\""+hivInit[a]+"\">"+hivInit[a]+"</option>";
                }
                             }
                 
                 //array for retest options
                 for(int a=0;a<retest.length;a++){
                
                if(retest[a].equals(conn.rs1.getString("HIVRetest"))){
                    
                option2+="<option selected value=\""+retest[a]+"\">"+retest[a]+"</option>";
                
                }
                
                else{
                option2+="<option value=\""+retest[a]+"\">"+retest[a]+"</option>";
                }}
                 
                 
                 for(int a=0;a<rpr.length;a++){
                
                if(rpr[a].equals(conn.rs1.getString("RPR"))){
                    
                option+="<option selected value=\""+rpr[a]+"\">"+rpr[a]+"</option>";
                
                }
                
                else{
                option+="<option value=\""+rpr[a]+"\">"+rpr[a]+"</option>";
                }
                             }
               

                QtoW += "<tr>"
                        + "<th style=\"width:40px;\">" + count1 + "</th>"
                        + "<th style=\"width:80px;\"><input type=\"text\"  style=\"width:80px;\"  value=\"" + conn.rs1.getString("Haemoglobin") + "\" name=\"haemoglobin" + count1 + "\" id=\"haemoglobin" + count1 + "\" /></th>"
                        + "<th style=\"width:100px;\">"
                        + "<select style=\"width:80px;\"name=\"RPR" + count1 + "\" id=\"RPR"+ count1 + "\">"
//                        +"<option value=\"" + conn.rs1.getString("RPR") + "\">" + conn.rs1.getString("RPR") + "</option>"
                        +""+option+""
                        + "</select></th>"
                        + "<th style=\"width:100px;\">"
                        + "<select style=\"width:80px;\"name=\"HIVInit" + count1 + "\" id=\"HIVInit"+ count1 + "\">"
//                        +"<option value=\"" + conn.rs1.getString("HIVinit") + "\">" + conn.rs1.getString("HIVinit") + "</option>"
                        +""+option1+""
                        + "</select></th>"
                        + "<th style=\"width:100px;\">"
                        + "<select style=\"width:80px;\"name=\"HIVRetest" + count1 + "\" id=\"HIVRetest"+ count1 + "\">"
//                        +"<option value=\"" + conn.rs1.getString("HIVRetest") + "\">" + conn.rs1.getString("HIVRetest") + "</option>"
                        +""+option2+""
                       
                        + "</select></th>"
                        + "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs1.getString("WHOStage") + "\" name=\"WHO" + count1 + "\"/></th>"
                        + "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs1.getString("ARTCD4") + "\" name=\"CD4" + count1 + "\"/></th>"
                        + "<th style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"" + conn.rs1.getString("ARTStart") + "\" name=\"ARTStart" + count1 + "\" id=\"ARTStart" + count1 + "\"/><input type=\"hidden\" value=\"" + conn.rs1.getString(1) + "\" name=\"QtoWID" + count1 + "\"></th>"

                        + "</tr>";




                count1++;

             option="";
             option1="";
             option2="";



            }//end of while



//(((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((())))))))))))))))))))))))))))))))))))))))))))))))            
            //System.out.println(""+allinputfields);


            if (QtoW.equals("")) {
                // allinputfields="No mother found under that ANC No.";
            }
            //_____________________after everything else, then add the default input fields.
            //This table will be used to only save the newly input table values 
            //thus the naming of the input fields is different


           QtoW += "<tr><td style=\"width:40px;\">" + count1 + "</td>"
                        + "<td style=\"width:80px;\"><input type=\"text\"  style=\"width:80px;\"value=\"\" name=\"New_haemoglobin" + count + "\"  id=\"haemoglobin" + count + "\" /></td>"
                        + "<td style=\"width:80px;\">"
                        + "<select style=\"width:80px;\"name=\"New_RPR" + count + "\" id=\"RPR"+ count + "\">"
                        +"<option value=\"\"></option>"
                        +"<option value=\"P\">P</option>"
                        +"<option value=\"N\">N</option>"
                        +"<option value=\"ND\">ND</option>"
                        + "</select></td>"
                        + "<td style=\"width:80px;\">"
                        + "<select style=\"width:80px;\"name=\"New_HIVInit" + count + "\" id=\"RPR"+ count + "\">"
                        +"<option value=\"\"></option>"
                        +"<option value=\"P\">P</option>"
                        +"<option value=\"N\">N</option>"
                        +"<option value=\"KP\">K</option>"
                        +"<option value=\"U\">U</option>"
                        + "</select></td>"
                        + "<td style=\"width:80px;\">"
                        + "<select style=\"width:80px;\"name=\"New_HIVRetest" + count + "\" id=\"RPR"+ count + "\">"
                        +"<option value=\"\"></option>"
                        +"<option value=\"P\">P</option>"
                        +"<option value=\"N\">N</option>"
                        +"<option value=\"NA\">NA</option>"
                        + "</select></td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_WHO" + count + "\"/></td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_CD4" + count + "\"/></td>"
                        + "<td style=\"width:80px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\"New_ARTStart" + count + "\" id=\"New_ARTStart" + count + "\"/></td>"
                        + "</tr>";


            //the hidden field which holds the number of hidden fields to update
            //no_of_old_rows
            QtoW += "<input type=\"hidden\" name=\"QtoWRegister\" id=\"QtoWRegister\" value=\"" + count + "\"/> <input type=\"hidden\" id=\"QtoWRegister_old_rows\" name=\"QtoWRegister_old_rows\" value=\"" + (count1 - 1) + "\"/>";


         //   System.out.println("ANC NO:" + allinputfields);


           
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("" + QtoW + "");
                out.println("</body>");
                out.println("</html>");

                QtoW = "";
            } finally {
                out.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(QtoWRegister.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
