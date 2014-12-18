/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mother_child;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sendSMS.dbConnect;

/**
 *
 * @author SIXTYFOURBIT
 */
public class IronFolateServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String anc_no, IronFolate;

String query="";
      ArrayList col1  = new ArrayList();
      ArrayList col2  = new ArrayList();
      ArrayList col3  = new ArrayList();
       int count1 = 1;
            int count = 1;

String queries="";
IronFolate = "";
int whileCount=0;
  dbConnect conn = new dbConnect();
        anc_no = request.getParameter("ancno");
   
           IronFolate += "<tr style=\"text-align: center;\">"+
                  
                    "<th>Iron and Folate</th>"+
                    "<th> Visit No </th>"+
                    "<th>Tablets</th>"+
                    "<th> Date Given</th>"+
                   
                    "</tr>";
        //============================================================IRON FOLATE TABLE DETAILS==========================  

//        queries = "Select * from IronFolateDetails";
//       conn.rs = conn.state.executeQuery(queries);
//            //get the values from the databse and put them in the htmlfield appropriately
//
//           
//            while (conn.rs.next()) {
////        col1.add("<td style=\"width:40px;text-align: left;\">" + count1 + "</td>");
////        col1.add("<td style=\"width:150px;\"></td>");
////        col1.add("<td style=\"width:80px;\"><input type=\"text\"  style=\"width:80px;\"value=\"" + conn.rs.getString(2) + "\" name=\"Visits" + count1 + "\" /></td>");
////        col2.add("<td style=\"width:120px;\"><input type=\"text\" style=\"width:120px;\" value=\"" + conn.rs.getString(3) + "\" name=\"Tablets" + count1 + "\"/></td>");
//            }
//        
           String motherID = request.getParameter("motherID");
        query = "Select * from ironfolate where motherid='" + motherID + "'";



            //============================================================IRON FOLATE TABLE DETAILS==========================   




            conn.rs1 = conn.state.executeQuery(query);
            //get the values from the databse and put them in the htmlfield appropriately

          
            while (conn.rs1.next()) {
                
                whileCount++;
                
int IronFolateID = conn.rs1.getInt(3);

if(IronFolateID==1){
   

               IronFolate+="<tr><td style=\"width:120px;\">Ferrous Fumarate(Combined Tablet-60mg iron and 400ug folic acid) or any other available</td>"
                     + "<td style=\"width:80px;\"><label  style=\"width:80px;\" name=\"Visits" + count1 + "\" />1st Visit</label></td>"
               +"<td style=\"width:120px;\"><label style=\"width:120px;\"  name=\"Tablets" + count1 + "\"/>30 tablets </label></td>"     
               +"<td style=\"width:80px;\"><input type=\"text\" value=\"" + conn.rs1.getString(4) + "\"  name=\"DateGiven" + count1 + "\" id=\"DateGiven" + count1 + "\" class=\"datepicker\" style=\"width: 120px;\" onkeypress=\"counter(event);\"/></td></td>"
               +"<input type=\"hidden\" value=\"" + conn.rs1.getString(1) + "\"  name=\"ID" + count1 + "\" id=\"ID" + count1 + "\"  >"
//               +"<input type=\"hidden\" value=\"" + conn.rs1.getString(2) + "\"  name=\"anc_no" + count1 + "\" id=\"anc_no" + count1 + "\"  >"
               +"<input type=\"hidden\" value=\"" + conn.rs1.getString(3) + "\"  name=\"ironfolateID" + count1 + "\" id=\"ironfolateID" + count1 + "\" >";

} 

if(IronFolateID==2){
   
        IronFolate+="<tr><td style=\"width:120px;\"></td>"
               + "<td style=\"width:80px;\"><label  style=\"width:80px;\" name=\"Visits" + count1 + "\" />2nd Visit</label></td>"
               +"<td style=\"width:120px;\"><label style=\"width:120px;\"  name=\"Tablets" + count1 + "\"/>90 tablets </label></td>"       
               +"<td style=\"width:80px;\"><input type=\"text\" value=\"" + conn.rs1.getString(4) + "\"  name=\"DateGiven" + count1 + "\" id=\"DateGiven" + count1 + "\" class=\"datepicker\" style=\"width: 120px;\" onkeypress=\"counter(event);\"/></td></tr>"
                +"<input type=\"hidden\" value=\"" + conn.rs1.getString(1) + "\"  name=\"ID" + count1 + "\" id=\"ID" + count1 + "\"  >"
//               +"<input type=\"hidden\" value=\"" + conn.rs1.getString(2) + "\"  name=\"anc_no" + count1 + "\" id=\"anc_no" + count1 + "\"  >"
               +"<input type=\"hidden\" value=\"" + conn.rs1.getString(3) + "\"  name=\"ironfolateID" + count1 + "\" id=\"ironfolateID" + count1 + "\" >";


}

if(IronFolateID==3){
   
        IronFolate+="<tr><td style=\"width:120px;\"></td>"
               + "<td style=\"width:80px;\"><label  style=\"width:80px;\" name=\"Visits" + count1 + "\" />3rd Visit</label></td>"
               +"<td style=\"width:120px;\"><label style=\"width:120px;\"  name=\"Tablets" + count1 + "\"/>30 tablets </label></td>"       
               +"<td style=\"width:80px;\"><input type=\"text\" value=\"" + conn.rs1.getString(4) + "\"  name=\"DateGiven" + count1 + "\" id=\"DateGiven" + count1 + "\" class=\"datepicker\" style=\"width: 120px;\" onkeypress=\"counter(event);\"/></td></tr>"
                 +"<input type=\"hidden\" value=\"" + conn.rs1.getString(1) + "\"  name=\"ID" + count1 + "\" id=\"ID" + count1 + "\"  >"
//               +"<input type=\"hidden\" value=\"" + conn.rs1.getString(2) + "\"  name=\"anc_no" + count1 + "\" id=\"anc_no" + count1 + "\"  >"
               +"<input type=\"hidden\" value=\"" + conn.rs1.getString(3) + "\"  name=\"ironfolateID" + count1 + "\" id=\"ironfolateID" + count1 + "\" >";


}

if(IronFolateID==4){
   
        IronFolate+="<tr><td style=\"width:120px;\"></td>"
               + "<td style=\"width:80px;\"><label  style=\"width:80px;\" name=\"Visits" + count1 + "\" />4th Visit</label></td>"
               +"<td style=\"width:120px;\"><label style=\"width:120px;\"  name=\"Tablets" + count1 + "\"/>30 tablets </label></td>"        
               +"<td style=\"width:80px;\"><input type=\"text\" value=\"" + conn.rs1.getString(4) + "\"  name=\"DateGiven" + count1 + "\" id=\"DateGiven" + count1 + "\" class=\"datepicker\" style=\"width: 120px;\" onkeypress=\"counter(event);\"/></td></tr>"
               +"<input type=\"hidden\" value=\"" + conn.rs1.getString(1) + "\"  name=\"ID" + count1 + "\" id=\"ID" + count1 + "\"  >"
//               +"<input type=\"hidden\" value=\"" + conn.rs1.getString(2) + "\"  name=\"anc_no" + count1 + "\" id=\"anc_no" + count1 + "\"  >"
               +"<input type=\"hidden\" value=\"" + conn.rs1.getString(3) + "\"  name=\"ironfolateID" + count1 + "\" id=\"ironfolateID" + count1 + "\" >";

}

 count1++;


            }//end of while
int whileCounter=0;
if(whileCount==1){



IronFolate+="<tr><td style=\"width:120px;\"></td>"
       + "<td style=\"width:80px;\"><label  style=\"width:80px;\" />2nd Visit</label></td>"
               +"<td style=\"width:120px;\"><label style=\"width:120px;\" >90 tablets </label></td>"     
               +"<td style=\"width:80px;\"><input type=\"text\" value=\"\"  name=\"DateGiven2\" id=\"New_DateGiven2\" class=\"datepicker\" style=\"width: 120px;\" onkeypress=\"counter(event);\"/></td></tr>"
               +"<input type=\"hidden\" value=\"2\"  name=\"New_ironfolateID2\" id=\"New_ironfolateID2\">"


        +"<tr><td style=\"width:120px;\"></td>"
        + "<td style=\"width:80px;\"><label  style=\"width:80px;\" />3rd Visit</label></td>"
               +"<td style=\"width:120px;\"><label style=\"width:120px;\"  />30 tablets </label></td>"
               +"<td style=\"width:80px;\"><input type=\"text\" value=\"\"  name=\"New_DateGiven3\" id=\"New_DateGiven3\" class=\"datepicker\" style=\"width: 120px;\" onkeypress=\"counter(event);\"/></td></tr>"
               +"<input type=\"hidden\" value=\"3\"  name=\"New_ironfolateID3\" id=\"New_ironfolateID3\">"

        +"<tr><td style=\"width:120px;\"></td>"
         + "<td style=\"width:80px;\"><label  style=\"width:80px;\" />4th Visit</label></td>"
               +"<td style=\"width:120px;\"><label style=\"width:120px;\" />30 tablets </label></td>"      
               +"<td style=\"width:80px;\"><input type=\"text\" value=\"\"  name=\"New_DateGiven4\" id=\"New_DateGiven4\" class=\"datepicker\" style=\"width: 120px;\" onkeypress=\"counter(event);\"/></td></tr>"
               +"<input type=\"hidden\" value=\"4\"  name=\"New_ironfolateID4\" id=\"New_ironfolateID4\">";

 whileCounter++;
}
int whileCounter2=0;
if(whileCount==2){



IronFolate+="<tr><td style=\"width:120px;\"></td>"
        + "<td style=\"width:80px;\"><label  style=\"width:80px;\">3rd Visit</label></td>"
               +"<td style=\"width:120px;\"><label style=\"width:120px;\" >30 tablets </label></td>"
               +"<td style=\"width:80px;\"><input type=\"text\" value=\"\"  name=\"New_DateGiven3\" id=\"New_DateGiven3\" class=\"datepicker\" style=\"width: 120px;\" onkeypress=\"counter(event);\"/></td></tr>"
               +"<input type=\"hidden\" value=\"3\"  name=\"New_ironfolateID3\" id=\"New_ironfolateID3\">"

        +"<tr><td style=\"width:120px;\"></td>"
        + "<td style=\"width:80px;\"><label  style=\"width:80px;\" >4th Visit</label></td>"
               +"<td style=\"width:120px;\"><label style=\"width:120px;\" >30 tablets </label></td>"
               +"<td style=\"width:80px;\"><input type=\"text\" value=\"\"  name=\"New_DateGiven4\" id=\"New_DateGiven4\" class=\"datepicker\" style=\"width: 120px;\" onkeypress=\"counter(event);\"/></td></tr>"
         +"<input type=\"hidden\" value=\"4\"  name=\"New_ironfolateID4\" id=\"New_ironfolateID4\">";
 whileCounter2++;
 
}
int whileCounter3=0;
int finalCounter=0;
if(whileCount==3){



IronFolate+="<tr><td style=\"width:120px;\"></td>"
       + "<td style=\"width:80px;\"><label  style=\"width:80px;\" >4th Visit</label></td>"
               +"<td style=\"width:120px;\"><label style=\"width:120px;\"  >30 tablets </label></td>"
               +"<td style=\"width:80px;\"><input type=\"text\" value=\"\"  name=\"New_DateGiven4\" id=\"New_DateGiven4\" class=\"datepicker\" style=\"width: 120px;\" onkeypress=\"counter(event);\"/></td></tr>"
               +"<input type=\"hidden\" value=\"4\"  name=\"New_ironfolateID4\" id=\"New_ironfolateID4\">";

 whileCounter++;
}

else if (whileCount==0){
     
     IronFolate+="<tr><td style=\"width:120px;\">Ferrous Fumarate(Combined Tablet-60mg iron and 400ug folic acid) or any other available</td>"
               + "<td style=\"width:80px;\"><label  style=\"width:80px;\" >1st Visit</label></td>"
               +"<td style=\"width:120px;\"><label style=\"width:120px;\"  >30 tablets </label></td>"        
               +"<td style=\"width:80px;\"><input type=\"text\" value=\"\"  name=\"New_DateGiven1\" id=\"New_DateGiven1\" class=\"datepicker\" style=\"width: 120px;\" onkeypress=\"counter(event);\"/></td></td>"
               +"<input type=\"hidden\" value=\"1\"  name=\"New_ironfolateID1\" id=\"New_ironfolateID1\">"

            +"<tr><td style=\"width:120px;\"></td>"
               + "<td style=\"width:80px;\"><label  style=\"width:80px;\">2nd Visit</label></td>"
               +"<td style=\"width:120px;\"><label style=\"width:120px;\"  >90 tablets </label></td>"
               +"<td style=\"width:80px;\"><input type=\"text\" value=\"\"  name=\"New_DateGiven2\" id=\"New_DateGiven2\" class=\"datepicker\" style=\"width: 120px;\" onkeypress=\"counter(event);\"/></td></tr>"
               +"<input type=\"hidden\" value=\"2\"  name=\"New_ironfolateID2\" id=\"New_ironfolateID2\">"

            +"<tr><td style=\"width:120px;\"></td>"
               + "<td style=\"width:80px;\"><label  style=\"width:80px;\" >3rd Visit</label></td>"
               +"<td style=\"width:120px;\"><label style=\"width:120px;\"  >30 tablets </label></td>"
               +"<td style=\"width:80px;\"><input type=\"text\" value=\"\"  name=\"New_DateGiven3\" id=\"New_DateGiven3\" class=\"datepicker\" style=\"width: 120px;\" onkeypress=\"counter(event);\"/></td></tr>"
               +"<input type=\"hidden\" value=\"3\"  name=\"New_ironfolateID3\" id=\"New_ironfolateID3\">"
                       
            +"<tr><td style=\"width:120px;\"></td>"
               + "<td style=\"width:80px;\"><label  style=\"width:80px;\" >4th Visit</label></td>"
               +"<td style=\"width:120px;\"><label style=\"width:120px;\" >30 tablets </label></td>"
               +"<td style=\"width:80px;\"><input type=\"text\" value=\"\"  name=\"New_DateGiven4\" id=\"New_DateGiven4\" style=\"width: 120px;\" onkeypress=\"counter(event);\"/></td></tr>"
               +"<input type=\"hidden\" value=\"4\"  name=\"New_ironfolateID4\" id=\"New_ironfolateID4\">";

 
}
    count++;           
                       //_____________________after everything else, then add the default input fields.
            //This table will be used to only save the newly input table values 
            //thus the naming of the input fields is different
//int mc=0;
//for(int a=0;a<4;a++){
//
////System.out.println("my count "+mc);
//mc++;
//IronFolate+="<tr>"+col1.get(a) +""+col2.get(a)+""+col3.get(a)+"</tr>";
//
//
//
//
//
//}

    System.out.println("whileCount========="+whileCount);
            IronFolate += "<input type=\"hidden\" id=\"ironfolate\" name=\"ironfolate\" value=\"" + whileCount + "\"/> <input type=\"hidden\" id=\"ironfolate_old_rows\" value=\"4\" name=\"ironfolate_old_rows\" />";


        


           
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("" + IronFolate + "");
                out.println("</body>");
                out.println("</html>");

                IronFolate = "";
            } finally {
                out.close();
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
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IronFolateServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IronFolateServlet.class.getName()).log(Level.SEVERE, null, ex);
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
