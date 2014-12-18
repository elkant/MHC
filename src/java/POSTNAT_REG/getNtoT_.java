/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package POSTNAT_REG;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sendSMS.ANCRegister1;
import sendSMS.dbConnect;

/**
 *
 * @author Maureen
 */
public class getNtoT_ extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        
          String ancREG = "";
    String query = "";

    int count1 = 1;
    int count = 1;
    String option = "";
    String querys = "";
    HttpSession session;
    String visits = "";
    String selectbreast="";
    String selectparlor="";
    String selectcsection="";
    String selectlochial="";
    String selectepisiotomy="";
    String newselectbreast="";
    String newselectparlor="";
    String newselectcsection="";
    String newselectlochial="";
    String newselectepisiotomy="";
        
        PrintWriter out = response.getWriter();
        String PNCNo = "";
        String motherID = "";
        dbConnect conn = new dbConnect();
        PNCNo = request.getParameter("PNCNo");
        motherID = request.getParameter("motherID");
        // for getting the anc no in a bean
      
         String parlor[];
    String breast[];
    String csection[];
    String lochial[];
    String episiotomy[];
       session = request.getSession();
      
     //   System.out.println(session.getAttribute("ANC"));
        
        
        
        String PostNat1 = "";
        count1 = 1;
        count = 1;
       
        parlor = new String[]{ "ND","1=Mild", "2=Moderate", "3=Severe"};
        breast = new String[]{"1=Normal","2=Cracked Nipple","3=Engorged","4=Mastitis"};
        csection = new String[]{"1=Bleeding","2=Normal","3=Infected"};
        lochial = new String[]{"1=Normal","2=Foul Smelling Excessive"};
        episiotomy = new String[]{"1=Repaired","2=Gaping","3=Infected","4=Healed"};

        
        
          for(int j=0;j<parlor.length;j++){
                   newselectparlor+="<option value=\""+parlor[j]+"\">"+parlor[j]+"</option>";
                   } 
          for(int j=0;j<breast.length;j++){
                   newselectbreast+="<option value=\""+breast[j]+"\">"+breast[j]+"</option>";
                   } 
          for(int j=0;j<csection.length;j++){
                   newselectcsection+="<option value=\""+csection[j]+"\">"+csection[j]+"</option>";
                   } 
          for(int j=0;j<lochial.length;j++){
                   newselectlochial+="<option value=\""+lochial[j]+"\">"+lochial[j]+"</option>";
                   } 
        
          for(int j=0;j<episiotomy.length;j++){
                   newselectepisiotomy+="<option value=\""+episiotomy[j]+"\">"+episiotomy[j]+"</option>";
                   } 
        
        ancREG = "";
        PostNat1 = "";

        PostNat1 += " <tr><td colspan=\"10\">Post Natal Examination</td></tr>\n" +
"                <tr>\n" +
"                   <td>No</td>    \n" +
"                   <td>Parlor</td>    \n" +
"                   <td>Breast</td>    \n" +
"                   <td>Uterus</td>    \n" +
"                   <td>PPH</td>    \n" +
"                   <td>C-Section Site</td>    \n" +
"                   <td>Lochial</td>    \n" +
"                   <td>Episiotiomy</td>    \n" +
"                     \n" +
"                 \n" +
"               </tr>  \n" +
"                \n" +
"               <tr>\n" +
"                   <td></td>    \n" +
"                 \n" +
"                   <td>n</td>    \n" +
"                   <td>o</td>    \n" +
"                   <td>p</td>    \n" +
"                   <td>q</td>    \n" +
"                   <td>r</td>    \n" +
"                   <td>s</td>    \n" +
"                   <td>t</td>    \n" +
"                  \n" +
"            \n" +
"               </tr> ";
             
       

        String options = "";
        try {

            //============================================================A TO G TABLE DETAILS==========================   
           
             String Matquery="Select * from postnat_ntot where motherid='"+motherID+"'";
          conn.rs = conn.state.executeQuery(Matquery);
//           System.out.println("B  "+B.toString());
//          
         
while(conn.rs.next()){

    
    for(int i=0;i<breast.length;i++){
                System.out.println(breast[i]);
                if(breast[i].equalsIgnoreCase(conn.rs.getString("Breast"))){
                 
                 selectbreast+="<option selected value=\""+breast[i]+"\">"+breast[i]+"</option>";
                 
                }
                
                else{
                selectbreast+="<option value=\""+breast[i]+"\">"+breast[i]+"</option>";
                }
               
                  }
    for(int i=0;i<parlor.length;i++){
                System.out.println(parlor[i]);
                if(parlor[i].equalsIgnoreCase(conn.rs.getString("Parlor"))){
                 
                 selectparlor+="<option selected value=\""+parlor[i]+"\">"+parlor[i]+"</option>";
                 
                }
                
                else{
                selectparlor+="<option value=\""+parlor[i]+"\">"+parlor[i]+"</option>";
                }
               
                  }
    for(int i=0;i<csection.length;i++){
                System.out.println(csection[i]);
                if(csection[i].equalsIgnoreCase(conn.rs.getString("CSection"))){
                 
                 selectcsection+="<option selected value=\""+csection[i]+"\">"+csection[i]+"</option>";
                 
                }
                
                else{
                selectcsection+="<option value=\""+csection[i]+"\">"+csection[i]+"</option>";
                }
               
                  }
               
    
     for(int i=0;i<lochial.length;i++){
                System.out.println(lochial[i]);
                if(lochial[i].equalsIgnoreCase(conn.rs.getString("Lochial"))){
                 
                 selectlochial+="<option selected value=\""+lochial[i]+"\">"+lochial[i]+"</option>";
                 
                }
                
                else{
                selectlochial+="<option value=\""+lochial[i]+"\">"+lochial[i]+"</option>";
                }
               
                  }
    
    
     
     
     for(int i=0;i<episiotomy.length;i++){
                System.out.println(episiotomy[i]);
                if(episiotomy[i].equalsIgnoreCase(conn.rs.getString("Episiotomy"))){
                 
                 selectepisiotomy+="<option selected value=\""+episiotomy[i]+"\">"+episiotomy[i]+"</option>";
                 
                }
                
                else{
                selectepisiotomy+="<option value=\""+episiotomy[i]+"\">"+episiotomy[i]+"</option>";
                }
               
                  }
     
    
    
                    PostNat1 += "<tr><td>" + count1 + "</td>"
                             + "<th style=\"width:100px;\"><input type=\"hidden\"   value=\"" + conn.rs.getString("motherid") + "\" name=\"motherID\" id=\"motherID\">"
                            + "<select style=\"width:100px;\"  name=\"Parlor" + count1 + "\" id=\"Parlor" + count1 + "\"/><option value=\"\"></option>"+selectparlor+"</select></th>"
                            + "<th><select style=\"width:100px;\"  name=\"Breast" + count1 + "\" id=\"Breast" + count1 + "\"/><option value=\"\"></option>"+selectbreast+"</select></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\"  style=\"width:100px;\"value=\"" + conn.rs.getString("Uterus") + "\" name=\"Uterus" + count1 + "\" id=\"Uterus" + count1 + "\" />"
                            + "<input type=\"hidden\" value=\"" + conn.rs.getString(1) + "\" name=\"ID" + count1 + "\" id=\"ID" + count1 + "\" /></th>"
                            +"<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"" + conn.rs.getString("PPH") + "\" name=\"PPH" + count1 + "\" id=\"PPH" + count1 + "\"/></td>"
                            + "<th><select style=\"width:100px;\"  name=\"CSection" + count1 + "\" id=\"CSection" + count1 + "\"/><option value=\"\"></option>"+selectcsection+"</select></th>"
                            + "<th><select style=\"width:100px;\"  name=\"Lochial" + count1 + "\" id=\"Lochial" + count1 + "\"/><option value=\"\"></option>"+selectlochial+"</select></th>"
                            + "<th><select style=\"width:100px;\"  name=\"Episiotomy" + count1 + "\" id=\"Episiotomy" + count1 + "\"/><option value=\"\"></option>"+selectepisiotomy+"</select></th></tr>";
                            
                            
                    count1++;

                   
           option="";    
 }




                PostNat1 += "<tr><td style=\"width:40px;\">" + count + "</td>"
                            + "<th style=\"width:100px;\">"
                            + "<select style=\"width:100px;\"  name=\"New_Parlor" + count + "\" id=\"New_Parlor" + count + "\"/><option value=\"\"></option>"+newselectparlor+"</select></th>"
                            + "<th><select style=\"width:100px;\"  name=\"New_Breast" + count + "\" id=\"New_Breast" + count + "\"/><option value=\"\"></option>"+newselectbreast+"</select></th>"
                            + "<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\"  style=\"width:100px;\"value=\"\" name=\"New_Uterus" + count + "\" id=\"New_Uterus" + count + "\" />"
                            + "</th>"
                            +"<th style=\"width:100px;\"><input  class=\"textbox\"  type=\"text\" style=\"width:100px;\" value=\"\" name=\"New_PPH" + count + "\" id=\"New_PPH" + count + "\"/></td>"
                            + "<th><select style=\"width:100px;\"  name=\"New_CSection" + count + "\" id=\"New_CSection" + count + "\"/><option value=\"\"></option>"+newselectcsection+"</select></th>"
                            + "<th><select style=\"width:100px;\"  name=\"New_Lochial" + count + "\" id=\"New_Lochial" + count + "\"/><option value=\"\"></option>"+newselectlochial+"</select></th>"
                            + "<th><select style=\"width:100px;\"  name=\"New_Episiotomy" + count + "\" id=\"New_Episiotomy" + count + "\"/><option value=\"\"></option>"+newselectepisiotomy+"</select></th></tr>";
                            

            //no_of_old_rows
            PostNat1 += "<input type=\"hidden\" id=\"NtoT\" name=\"NtoT\" value=\"" + count + "\" > <input type=\"hidden\" id=\"NtoT_old_rows\" value=\"" + (count1 - 1) + "\" name=\"NtoT_old_rows\" >";



//enter search details in an history..
            
            
            String user1="";
            if(session.getAttribute("userid")!=null){
            
            user1=session.getAttribute("userid").toString();
            }
            
 String history="insert  into history set ancnumber='"+PNCNo+"', userid='"+user1+"', page='PostNatal Register' ";           
 
 
 conn.state.executeUpdate(history);

            try {
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("" + PostNat1 + "");
                out.println("" + ancREG + "");
                out.println("</body>");
                out.println("</html>");

                ancREG = "";
                PostNat1 = "";
            } finally {
                out.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ANCRegister1.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(getNtoT_.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(getNtoT_.class.getName()).log(Level.SEVERE, null, ex);
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
