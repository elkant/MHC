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
public class PostNatalMum extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String a="";
     String query2="";
     String timing2="";
     String labels[];
     String labels2[];
     String labels3[];
      ArrayList col1  = new ArrayList();
      ArrayList col2  = new ArrayList();
      ArrayList col3  = new ArrayList();
      
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
          String anc_no, postnatal;

String query="";

String motherID = request.getParameter("motherID");

        anc_no = request.getParameter("ancno");
//        System.out.println("PostNatalMum"+anc_no);

       try{

      
  dbConnect conn = new dbConnect();


        postnatal = "";
        
        
      String query1="";
      String timing="";
      String concatTime="";
      String timeID="";  
        String OptionsArr[]={"Yes","No","N/A"};      
            String OptionsArr2[]={"Yes","No"};     
      
labels=new String[] {"Date/Visit","Blood Pressure","General Condition","Breast","Involution of Uterus","Mother given ART or option B Plus","Mother on HAART","Mother CTX","Counselling on Family Planning"};
labels2=new String[] {"Date","BP","GeneralCondition","Breast","Involution","ART","HAART","CTX","FPCounselling"};
labels3=new String[] {"New_Date","New_BP","New_GeneralCondition","New_Breast","New_Involution","New_ART","New_HAART","New_CTX","New_FPCounselling"};
postnatal+= "<td style=\"width:50px;\">Timing of Visit</td>"; 


query1 = "Select * from post_natal_hours";
int time_count=0;
                   conn.rs2 = conn.state2.executeQuery(query1);
                    while (conn.rs2.next()) {
                       time_count++; 
              timing = conn.rs2.getString(2);
              timeID = conn.rs2.getString(1);
//              concatTime+=timing;
//              System.out.println("timing"+timing);
                    

postnatal+= "<th style=\"width:50px;\">"+ timing +" <input type=\"hidden\" name=\"New_HRID"+time_count+"\" value=\""+timeID+"\"></th>";
                
                 
}
                    
      
             //String selects[];
                  
                   //String selects3[];
                 String selects[] = {"Improvement","No improvement","Healed"};
                 String selectsIv[] = {"Improvement","No improvement","Healed"};
                  
                   String selects3[] = {"Normal","Cracked Nipple","Engorged","Mastitis"};
                  
                   String selectsOption="";
                   String selectsOption1="";
                   String selectsOption2="";
                   
                   String newselectsOption="";
                   String newselectsOption1="";
                   String newselectsOption2="";
                   
                   for(int i=0;i<OptionsArr2.length;i++){
                   newselectsOption+="<option value=\""+OptionsArr2[i]+"\">"+OptionsArr2[i]+"</option>";
                   }
                 
                   for(int j=0;j<selects3.length;j++){
                   newselectsOption2+="<option value=\""+selects3[j]+"\">"+selects3[j]+"</option>";
                   }    
        

            //============================================================IRON FOLATE TABLE DETAILS==========================   

  query = "Select * from post_natal_examination where motherid='" + motherID + "'";

int col1Count=0;
int col2Count=0;
int col3Count=0;
            conn.rs1 = conn.state.executeQuery(query);
            //get the values from the databse and put them in the htmlfield appropriately
  
 
            int count1 = 1;
            int count = 1;
             if(col1!=null && col1.size()>0)
             {col1.clear();
             }
             if(col2!=null && col2.size()>0)
             {col2.clear();
             }
             if(col3!=null && col3.size()>0)
             {col3.clear();
             }
            
                 int counts=0;  
                 int counts2=0;  
                 int counts3=0; 
              
                int time=0;
                  
          int whileCount=0;
            
            while (conn.rs1.next()) {
                
                whileCount++;
//               System.out.println("__");
                 time = conn.rs1.getInt(12);
               if(time==1){
               col1Count=4;
               
               }
               else if(time==2){
                   
               col2Count=5;
               
               }
               else if(time==2){
               col3Count=5;
               
               }
               
                 
//               String a = conn.rs1.getString(6);
             
                String uterus_involution = conn.rs1.getString("uterus_involution") ;
               System.out.println("uterus_involution---------"+uterus_involution);
//               System.out.println("b---------"+b);
                  for(int i=0;i<OptionsArr2.length;i++){
                
                if(OptionsArr2[i].equalsIgnoreCase(conn.rs1.getString("uterus_involution"))){
                    
                 selectsOption+="<option selected value=\""+OptionsArr2[i]+"\">"+OptionsArr2[i]+"</option>";
                
                }
                
                else{
                selectsOption+="<option value=\""+OptionsArr2[i]+"\">"+OptionsArr2[i]+"</option>";
                }
               
                  }
                  
                    String breast = conn.rs1.getString("breast");
                     System.out.println("breast---------"+breast);
                    
                  for(int i=0;i<selects3.length;i++){
                System.out.println(selects3[i]);
                if(selects3[i].equalsIgnoreCase(conn.rs1.getString("breast"))){
                 
                 selectsOption2+="<option selected value=\""+selects3[i]+"\">"+selects3[i]+"</option>";
                 
                }
                
                else{
                selectsOption2+="<option value=\""+selects3[i]+"\">"+selects3[i]+"</option>";
                }
               
                  }
               
            
               
                      String options1="";
                      String options="";
                      String options2="";
                      String options3="";
               
           
          
              String ART=  conn.rs1.getString(8);
              
                for(int i=0;i<OptionsArr.length;i++){
                
                if(OptionsArr[i].equalsIgnoreCase(ART)){
                    
                 options1+="<option selected value=\""+OptionsArr[i]+"\">"+OptionsArr[i]+"</option>";
                
                }
                
                else{
                options1+="<option value=\""+OptionsArr[i]+"\">"+OptionsArr[i]+"</option>";
                }
                             }
              String HAART = conn.rs1.getString(9) ;
              
               for(int i=0;i<OptionsArr.length;i++){
                
                if(OptionsArr[i].equalsIgnoreCase(HAART)){
                    
                 options+="<option selected value=\""+OptionsArr[i]+"\">"+OptionsArr[i]+"</option>";
                
                }
                
                else{
                options+="<option value=\""+OptionsArr[i]+"\">"+OptionsArr[i]+"</option>";
                }}
              String CTX = conn.rs1.getString(10) ;
              
               for(int i=0;i<OptionsArr.length;i++){
                
                if(OptionsArr[i].equalsIgnoreCase(CTX)){
                    
                 options2+="<option selected value=\""+OptionsArr[i]+"\">"+OptionsArr[i]+"</option>";
                
                }
                
                else{
                options2+="<option value=\""+OptionsArr[i]+"\">"+OptionsArr[i]+"</option>";
                }}
              String FP = conn.rs1.getString(11);
              
               for(int i=0;i<OptionsArr.length;i++){
                
                if(OptionsArr[i].equalsIgnoreCase(FP)){
                    
                 options3+="<option selected value=\""+OptionsArr[i]+"\">"+OptionsArr[i]+"</option>";
                
                }
                
                else{
                options3+="<option value=\""+OptionsArr[i]+"\">"+OptionsArr[i]+"</option>";
                }}

                

               if(time==1){
//                     System.out.println("called"+1);
                for(int j=0;j<labels2.length; j++){
//                System.out.println(labels2[j]);
               
               counts=0; 
                
                col1.add("<td style=\"width:200px;text-align: left;\">Date/Visit</td><td style=\"width:50px;\"><input type=\"text\" style=\"width:80px;\" value=\""+conn.rs1.getString(3)+"\" name=\""+ labels2[counts]+"1"+"\" id=\"VisitMum1\" ></td>");
                counts++;
                col1.add("<td style=\"width:200px;text-align: left;\">Blood Pressure</td><td style=\"width:50px;\"><input type=\"text\" style=\"width:80px;\" value=\""+conn.rs1.getString(4)+"\" name=\""+ labels2[counts]+"1"+"\"></td>");
                counts++;
                col1.add("<td style=\"width:200px;text-align: left;\">General Condition</td><td style=\"width:50px;\"><input type=\"text\"  style=\"width:80px;\"value=\""+conn.rs1.getString(5)+"\" name=\""+ labels2[counts]+"1"+"\" ></td>");
               counts++;
                col1.add("<td style=\"width:200px;text-align: left;\">Breast</td><td style=\"width:50px;\"><select  style=\"width:80px;\"name=\""+ labels2[counts]+"1"+"\" >"
//                        + "<option value=\""+conn.rs1.getString(6)+"\" >"+conn.rs1.getString(6)+"</option>"
                        + ""+selectsOption2+"</select></td>");
                counts++;
                col1.add("<td style=\"width:200px;text-align: left;\">Involution of Uterus</td><td style=\"width:50px;\"><select style=\"width:80px;\" name=\""+ labels2[counts]+"1"+"\" >"
//                        + "<option value=\""+conn.rs1.getString(7)+"\">"+conn.rs1.getString(7)+"</option>"
                        + ""+selectsOption+""
                        + "</select></td>");
               counts++;
                col1.add("<td style=\"width:200px;text-align: left;\">Mother given ART or Option B plus prophylaxis</td><td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\" name=\""+ labels2[counts]+"1"+"\" >"
                        + "" + options1 +" </select></td>");
                counts++;
                col1.add("<td style=\"width:200px;text-align: left;\">Mother on HAART</td><td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\" name=\""+ labels2[counts]+"1"+"\" >"
                        + "" + options +" </select></td>");
                counts++;
                col1.add("<td style=\"width:200px;text-align: left;\">Mother CTX</td><td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\" name=\""+ labels2[counts]+"1"+"\" >"
                        + "" + options2 +" </select></td>");
               counts++;
                col1.add("<td style=\"width:200px;text-align: left;\">Counselling on family Planning</td><td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\" name=\""+ labels2[counts]+"1"+"\" >"
                        + "" + options3 +" </select></td><input type=\"hidden\" style=\"width:80px;\" value=\""+conn.rs1.getString(1)+"\" name=\"post_natal_id1"+"\">");
   
                }
                selectsOption="";
                  selectsOption1="";
                  selectsOption2="";
               }
//           
       
                if(time==2){
//                    System.out.println("called"+2);
                for(int j=1;j<labels2.length;j++){
                    counts2=0;
                     col2Count++;
                col2.add("<td style=\"width:50px;\"><input type=\"text\" style=\"width:80px;\" value=\""+conn.rs1.getString(3)+"\" name=\""+ labels2[counts2]+"2"+"\" id=\"VisitMum2\"></td>");
                counts2++;
                col2.add("<td style=\"width:50px;\"><input type=\"text\" style=\"width:80px;\" value=\""+conn.rs1.getString(4)+"\" name=\""+ labels2[counts2]+"2"+"\" ></td>");
                counts2++;
                col2.add("<td style=\"width:50px;\"><input type=\"text\"  style=\"width:80px;\"value=\""+conn.rs1.getString(5)+"\" name=\""+ labels2[counts2]+"2"+"\" ></td>");
                counts2++;
                col2.add("<td style=\"width:50px;\"><select  style=\"width:80px;\"name=\""+ labels2[counts2]+"2"+"\" >"
//                        + "<option value=\""+conn.rs1.getString(6)+"\" >"+conn.rs1.getString(6)+"</option>"
                        + ""+selectsOption2+"</select></td>");
                counts2++;
                col2.add("<td style=\"width:50px;\"><select style=\"width:80px;\" name=\""+ labels2[counts2]+"2"+"\" >"
//                        + "<option value=\""+conn.rs1.getString(7)+"\">"+conn.rs1.getString(7)+"</option>"
                        + ""+selectsOption+""
                        + "</select></td>");
                counts2++;
               col2.add("<td style=\"width:50px;\"><select style=\"width:80px;  height:30px;\" name=\""+ labels2[counts2]+"2"+"\" >"
                        + "" + options1 +" </select></td>");
                counts2++;
                col2.add("<td style=\"width:50px;\"><select style=\"width:80px;  height:30px;\" name=\""+ labels2[counts2]+"2"+"\" >"
                        + "" + options +" </select></td>");
                counts2++;
                col2.add("<td style=\"width:50px;\"><select style=\"width:80px;  height:30px;\" name=\""+ labels2[counts2]+"2"+"\" >"
                        + "" + options2 +" </select></td>");
                counts2++;
                col2.add("<td style=\"width:50px;\"><select style=\"width:80px;  height:30px;\" name=\""+ labels2[counts2]+"2"+"\" >"
                        + "" + options3 +" </select></td><input type=\"hidden\" style=\"width:80px;\" value=\""+conn.rs1.getString(1)+"\" name=\"post_natal_id2"+"\">");
               
                }
//                System.out.println("col2Count"+col2Count);
                  selectsOption="";
                  selectsOption1="";
                  selectsOption2="";
                }
                
                
                
                 if(time==3){
//                       System.out.println("called"+3);
                 for(int j=1;j<labels2.length;j++){
                     counts3=0;
                      col3Count++;
                col3.add("<td style=\"width:50px;\"><input type=\"text\" style=\"width:80px;\" value=\""+conn.rs1.getString(3)+"\" name=\""+ labels2[counts3]+"3"+"\" id=\"VisitMum3\"></td>");
                counts3++;
                col3.add("<td style=\"width:50px;\"><input type=\"text\" style=\"width:80px;\" value=\""+conn.rs1.getString(4)+"\" name=\""+ labels2[counts3]+"3"+"\"></td>");
                counts3++;
                col3.add("<td style=\"width:50px;\"><input type=\"text\"  style=\"width:80px;\"value=\""+conn.rs1.getString(5)+"\" name=\""+ labels2[counts3]+"3"+"\" ></td>");
                counts3++;
                col3.add("<td style=\"width:50px;\"><select  style=\"width:80px;\"name=\""+ labels2[counts3]+"3"+"\" >"
//                        + "<option value=\""+conn.rs1.getString(6)+"\" >"+conn.rs1.getString(6)+"</option>"
                        +selectsOption2+"</select></td>");
                counts3++;
                col3.add("<td style=\"width:50px;\"><select style=\"width:80px;\" name=\""+ labels2[counts3]+"3"+"\" >"
//                        + "<option value=\""+conn.rs1.getString(7)+"\">"+conn.rs1.getString(7)+"</option>"
                        + selectsOption+""
                        + "</select></td>");
                counts3++;
                col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\" name=\""+ labels2[counts3]+"3"+"\" >"
                        + "" + options1 +" </select></td>");
                counts3++;
                col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\" name=\""+ labels2[counts3]+"3"+"\" >"
                        + "" + options +" </select></td>");
                counts3++;
                col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\" name=\""+ labels2[counts3]+"3"+"\" >"
                        + "" + options2 +" </select></td>");
               counts3++;
                col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\" name=\""+ labels2[counts3]+"3"+"\" >"
                        + "" + options3 +" </select></td><input type=\"hidden\" style=\"width:80px;\" value=\""+conn.rs1.getString(1)+"\" name=\"post_natal_id3"+"\">");
                 }
                 }
                  

                count1++;





            }//end of while
             if(whileCount == 1 ){
                     
                   for(int n=1;n<labels3.length;n++){
                       counts2=0;
                col2.add("<td style=\"width:50px;\"><input type=\"text\" placeholder=\"yyyy-mm-dd\" style=\"width:80px;\" value=\"\" name=\""+ labels3[counts2]+"2"+"\"  id=\"New_VisitMum2\" onclick=\"checkNextRows();\" ></td>");
                counts2++;
                col2.add("<td style=\"width:50px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\""+ labels3[counts2]+"2"+"\"   ></td>");
                counts2++;
                col2.add("<td style=\"width:50px;\"><input type=\"text\"  style=\"width:80px;\"value=\"\" name=\""+ labels3[counts2]+"2"+"\" ></td>");
                counts2++;
                col2.add("<td style=\"width:50px;\"><select  style=\"width:80px;\"name=\""+ labels3[counts2]+"2"+"\" ><option value=\"\" ></option>"+newselectsOption2+"</select></td>");
                counts2++;
                col2.add("<td style=\"width:50px;\"><select  style=\"width:80px;\"name=\""+ labels3[counts2]+"2"+"\" ><option value=\"\" ></option>"+newselectsOption+"</select></td>");
                counts2++;
                 col2.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts2]+"2"+"\">"
                               + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                               
                                + "</select></td>");
                        counts2++;
                        col2.add("<td style=\"width:80px;\"><select style=\"width:80px; height:30px;\"  name=\""+ labels3[counts2]+"2"+"\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                               
                        counts2++;
                        col2.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts2]+"2"+"\">"
                        + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                        counts2++;
                        col2.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts2]+"2"+"\">"
                        + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
               
                counts3=0;
                col3.add("<td style=\"width:50px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" placeholder=\"yyyy-mm-dd\" name=\""+ labels3[counts3]+"3"+"\" id=\"New_VisitMum3\" onclick=\"checkNextRows();\" ></td>");
                counts3++;
                col3.add("<td style=\"width:50px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\""+ labels3[counts3]+"3"+"\"></td>");
                counts3++;
                col3.add("<td style=\"width:50px;\"><input type=\"text\"  style=\"width:80px;\"value=\"\" name=\""+ labels3[counts3]+"3"+"\" ></td>");
                counts3++;
                 col3.add("<td style=\"width:50px;\"><select  style=\"width:80px;\"name=\""+ labels3[counts3]+"3"+"\" ><option value=\"\" ></option>"+newselectsOption2+"</select></td>");
                counts3++;
                col3.add("<td style=\"width:50px;\"><select  style=\"width:80px;\"name=\""+ labels3[counts3]+"3"+"\" ><option value=\"\" ></option>"+newselectsOption+"</select></td>");
                counts3++;
                
                 col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts3]+"3"+"\">"
                               + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                               
                                + "</select></td>");
                        counts3++;
                 col3.add("<td style=\"width:80px;\"><select style=\"width:80px; height:30px;\"  name=\""+ labels3[counts3]+"3"+"\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                               
                        counts3++;
                        col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts3]+"3"+"\">"
                        + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                        counts3++;
                        col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts3]+"3"+"\">"
                        + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
               
                   }}
             if(whileCount == 2 ){
                     
                   for(int n=1;n<labels3.length;n++)
                  {
                          counts3=0;
                col3.add("<td style=\"width:50px;\"><input type=\"text\" style=\"width:80px;\" placeholder=\"yyyy-mm-dd\" value=\"\" name=\""+ labels3[counts3]+"3"+"\" id=\"VisitMum3\" onblur=\"checkNextRows();\"></td>");
                counts3++;
                col3.add("<td style=\"width:50px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\""+ labels3[counts3]+"3"+"\"></td>");
                counts3++;
                col3.add("<td style=\"width:50px;\"><input type=\"text\"  style=\"width:80px;\"value=\"\" name=\""+ labels3[counts3]+"3"+"\" ></td>");
                counts3++;
                 col3.add("<td style=\"width:50px;\"><select  style=\"width:80px;\"name=\""+ labels3[counts3]+"3"+"\" ><option value=\"\" ></option>"+newselectsOption2+"</select></td>");
                counts2++;
                col3.add("<td style=\"width:50px;\"><select  style=\"width:80px;\"name=\""+ labels3[counts3]+"3"+"\" ><option value=\"\" ></option>"+newselectsOption+"</select></td>");
                counts2++;
               counts3++;
                
                 col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts3]+"3"+"\">"
                               + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                               
                                + "</select></td>");
                        counts3++;
                 col3.add("<td style=\"width:80px;\"><select style=\"width:80px; height:30px;\"  name=\""+ labels3[counts3]+"3"+"\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                               
                        counts3++;
                        col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts3]+"3"+"\">"
                        + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                        counts3++;
                        col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts3]+"3"+"\">"
                        + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
               
                  }}
            
//                  System.out.println("col1Count"+col1Count);
              if(col1Count==0 ){
//                  System.out.println("col1Count"+col1Count);
                   for(int n=1;n<labels3.length;n++){
                       counts=0; 
                col1.add("<td style=\"width:200px;text-align: left;\">Date/Visit</td><td style=\"width:50px;\"><input type=\"text\" style=\"width:80px;\" placeholder=\"yyyy-mm-dd\" value=\"\" name=\""+ labels3[counts]+"1"+"\"  id=\"New_VisitMum1\" ></td>");
                counts++;
                col1.add("<td style=\"width:200px;text-align: left;\">Blood Pressure</td><td style=\"width:50px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\""+ labels3[counts]+"1"+"\"  id=\"BP" + count1 + "\" ></td>");
                counts++;
                col1.add("<td style=\"width:200px;text-align: left;\">General Condition</td><td style=\"width:50px;\"><input type=\"text\"  style=\"width:80px;\"value=\"\" name=\""+ labels3[counts]+"1"+"\" ></td>");
                counts++;
                 col1.add("<td style=\"width:200px;text-align: left;\">Breast</td><td style=\"width:50px;\"><select  style=\"width:80px;\"name=\""+ labels3[counts]+"1"+"\" ><option value=\"\" ></option>"+newselectsOption2+"</select></td>");
                counts++;
                col1.add("<td style=\"width:200px;text-align: left;\">Involution of Uterus</td><td style=\"width:50px;\"><select  style=\"width:80px;\"name=\""+ labels3[counts]+"1"+"\" ><option value=\"\" ></option>"+newselectsOption+"</select></td>");
                counts++;
               col1.add("<td style=\"width:200px;text-align: left;\">Mother given ART or option B plus prophylaxis</td><td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts]+"1"+"\">"
                               + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                               
                                + "</select></td>");
                        counts++;
             col1.add("<td style=\"width:200px;text-align: left;\">Mother on HAART</td><td style=\"width:80px;\"><select style=\"width:80px; height:30px;\"  name=\""+ labels3[counts]+"1"+"\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                               
                        counts++;
            col1.add(" <td style=\"width:200px;text-align: left;\">Mother CTX Initiated</td><td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts]+"1"+"\">"
                        + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                        counts++;
                        col1.add("<td style=\"width:200px;text-align: left;\">Counselling on Family Planning</td><td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts]+"1"+"\">"
                        + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
               
                   }
               
              }
                if(col2Count == 0 ){
                     
                   for(int n=1;n<labels3.length;n++){
                       counts2=0;
                col2.add("<td style=\"width:50px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\""+ labels3[counts2]+"2"+"\"  id=\"New_VisitMum2\" placeholder=\"yyyy-mm-dd\" onfocus=\"checkNextRows();\"></td>");
                counts2++;
                col2.add("<td style=\"width:50px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\""+ labels3[counts2]+"2"+"\"  ></td>");
                counts2++;
                col2.add("<td style=\"width:50px;\"><input type=\"text\"  style=\"width:80px;\"value=\"\" name=\""+ labels3[counts2]+"2"+"\" ></td>");
                counts2++;
                col2.add("<td style=\"width:50px;\"><select  style=\"width:80px;\"name=\""+ labels3[counts2]+"2"+"\" ><option value=\"\" ></option>"+newselectsOption2+"</select></td>");
                counts2++;
                col2.add("<td style=\"width:50px;\"><select  style=\"width:80px;\"name=\""+ labels3[counts2]+"2"+"\" ><option value=\"\" ></option>"+newselectsOption+"</select></td>");
                counts2++;
              
               col2.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts2]+"2"+"\">"
                               + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                               
                                + "</select></td>");
                        counts2++;
             col2.add("<td style=\"width:80px;\"><select style=\"width:80px; height:30px;\"  name=\""+ labels3[counts2]+"2"+"\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                               
                        counts2++;
            col2.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts2]+"2"+"\">"
                        + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                        counts2++;
           col2.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts2]+"2"+"\">"
                        + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                   }}
                if(col3Count== 0 ){
                   for(int n=1;n<labels3.length;n++){
                       counts3=0;
                col3.add("<td style=\"width:50px;\"><input type=\"text\" style=\"width:80px;\" placeholder=\"yyyy-mm-dd\" value=\"\" name=\""+ labels3[counts3]+"3"+"\" id=\"New_VisitMum3\" onfocus=\"checkNextRow();\" ></td>");
                counts3++;
                col3.add("<td style=\"width:50px;\"><input type=\"text\" style=\"width:80px;\" value=\"\" name=\""+ labels3[counts3]+"3"+"\"></td>");
                counts3++;
                col3.add("<td style=\"width:50px;\"><input type=\"text\"  style=\"width:80px;\"value=\"\" name=\""+ labels3[counts3]+"3"+"\" ></td>");
                counts3++;
                col3.add("<td style=\"width:50px;\"><select  style=\"width:80px;\"name=\""+ labels3[counts3]+"3"+"\" ><option value=\"\" ></option>"+newselectsOption2+"</select></td>");
                counts3++;
                col3.add("<td style=\"width:50px;\"><select  style=\"width:80px;\"name=\""+ labels3[counts3]+"3"+"\" ><option value=\"\" ></option>"+newselectsOption+"</select></td>");
                counts3++;
                  
               col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts3]+"3"+"\">"
                               + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                               
                                + "</select></td>");
                        counts3++;
             col3.add("<td style=\"width:80px;\"><select style=\"width:80px; height:30px;\"  name=\""+ labels3[counts3]+"3"+"\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                               
                        counts3++;
            col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts3]+"3"+"\">"
                        + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                        counts3++;
           col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\""+ labels3[counts3]+"3"+"\">"
                        + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
               
                   }}
            
            

int mc=0;
for(int a=0;a<labels2.length;a++){

//System.out.println("my count "+mc);
mc++;
postnatal+="<tr>"+col1.get(a) +""+col2.get(a)+""+col3.get(a)+"</tr>";





}
         int counter=1;   
  if(whileCount==0){
      counter=3;
  }  
  else if(whileCount==1){
      counter=2;
  }
  else if(whileCount==2){
  counter=1;
  }
  else if(whileCount==3){
  counter=0;
  }
            

            postnatal += "<input type=\"hidden\" id=\"PostNatal\" name=\"PostNatal\" value=\"" + counter + "\"/> <input type=\"hidden\" id=\"PostNatal_old_rows\" name=\"PostNatal_old_rows\" value=\"" + (count1 - 1) + "\"/>";



           
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("" + postnatal + "");
                out.println("</body>");
                out.println("</html>");

                postnatal = "";
            } finally {
                out.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(PostNatalMum.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PostNatalMum.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PostNatalMum.class.getName()).log(Level.SEVERE, null, ex);
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
