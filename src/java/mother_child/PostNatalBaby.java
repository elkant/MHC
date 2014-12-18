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
public class PostNatalBaby extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   String a="";
    String query2="";
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
       String anc_no, postnatalbaby="";

String query="";

String query1="";
  String timing="";
  String timing2="";
  String concatTime="";
  String times="";
  int counter=0;
dbConnect conn = new dbConnect();

String motherID = request.getParameter("motherID");
        anc_no = request.getParameter("ancno");
//        System.out.println("PostNatalBaby"+anc_no);
   String selects[];
   String selectsUmbilical[];
                   String selects2[];
                   String selects3[];
                   selects = new String[]{"Improvement","No improvement","Healed"};
                   selects2 = new String[]{"Breast Feeding","Solid Food","Both"};
                   selects3 = new String[]{"Worse","Better","Good"};
                  selectsUmbilical =new String[]{"Dry-Healed","Red-Inflammed","Drawing Pus"};
                   String selectsOption="";
                   String selectsOption1="";
                   String selectsOption2="";
                   String newselectsOption="";
                   String newselectsOption1="";
                   String newselectsOption2="";
                   String umbilicalSelects="";
                   String newumbilicalSelects="";
      
// umbilical cord
        for(int k=0;k<selectsUmbilical.length;k++){
        
        newumbilicalSelects+="<option value=\""+selectsUmbilical[k]+"\">"+selectsUmbilical[k]+"</option>";
        
        }
        //feeding methods
        for(int k=0;k<selects2.length;k++){
        
        newselectsOption1+="<option value=\""+selects2[k]+"\">"+selects2[k]+"</option>";
        
        }
        //general condition
        for(int k=0;k<selects3.length;k++){
        
        newselectsOption2+="<option value=\""+selects3[k]+"\">"+selects3[k]+"</option>";
        
        }
       
        
     
labels=new String[] {"Babys Condition","Baby's Feeding Methods","Umbilical Cord","Baby Immunization Started","Infant given ARV prophylaxis","Infant CTX Prophylaxis initiated"};
labels2=new String[] {"Condition","FeedingMethod","UmbilicalCord","Immunization","ARV","CTX"};
labels3=new String[] {"New_Condition","New_FeedingMethod","New_UmbilicalCord","New_Immunization","New_ARV","New_CTX"};
postnatalbaby+= "<td style=\"width:50px;\">Timing of Visit</td>"; 
            //============================================================IRON FOLATE TABLE DETAILS==========================   
String timeID="";
int times2=0;

 query1 = "Select * from post_natal_hours";
                   conn.rs2 = conn.state2.executeQuery(query1);
                   int rowcount=0;
                    while (conn.rs2.next()) {
                        rowcount++;
              timing = conn.rs2.getString(2);
             
              timeID = conn.rs2.getString(1);
//              concatTime+=timing;
//              System.out.println("timing"+timing);
                    

postnatalbaby+= "<th style=\"width:50px;\">"+ timing +" <input type=\"hidden\" name=\"New_HR"+rowcount+"\" value=\""+timeID+"\"></th>";
                
                 
}
                
                   
                   
                   
                 

 try{

        query = "Select * from post_natal_baby where motherid='" + motherID + "'";
int col1Count=0;
int col2Count=0;
int col3Count=0;

      


            conn.rs1 = conn.state.executeQuery(query);
            //get the values from the databse and put them in the htmlfield appropriately
  
            int count1 = 1;
            int count = 1;
            int counts=0;
            String options="";
            String options1="";
            String options2="";
            
             if(col1!=null && col1.size()>0)
             {col1.clear();
             }
             if(col2!=null && col2.size()>0)
             {col2.clear();
             }
             if(col3!=null && col3.size()>0)
             {col3.clear();
             }
             int whileCount=0;
            while (conn.rs1.next()) {
                whileCount++;
               times2 = conn.rs1.getInt(9);
              String a = conn.rs1.getString(3);
               String b = conn.rs1.getString(4);
               String c = conn.rs1.getString(5);
                String d = conn.rs1.getString(6);
               String e = conn.rs1.getString(7);
               String f = conn.rs1.getString(8);
//              System.out.println("a"+a);
//              System.out.println("b"+b);
//              System.out.println("c"+c);
//              System.out.println("d"+d);
//              System.out.println("e"+e);
//              System.out.println("f"+f);
//             
               // =================first select option for baby umbilical cord ==================================
               
                 for(int i=0;i<selects.length;i++){
                
                if(selectsUmbilical[i].equals(c)){
                    
                 umbilicalSelects+="<option selected value=\""+selectsUmbilical[i]+"\">"+selectsUmbilical[i]+"</option>";
                
                }
                
                else{
                umbilicalSelects+="<option value=\""+selectsUmbilical[i]+"\">"+selectsUmbilical[i]+"</option>";
                }
                             }

                 
                 // ========================for feeding method =================================
                  for(int j=0;j<selects2.length;j++){
                
                if(selects2[j].equals(b)){
                    
                 selectsOption1+="<option selected value=\""+selects2[j]+"\">"+selects2[j]+"</option>";
                
                }
                
                else{
                selectsOption1+="<option value=\""+selects2[j]+"\">"+selects2[j]+"</option>";
                }
                             }
                  
                  //========================== for babys condition==============================
                  for(int k=0;k<selects3.length;k++){
                
                if(selects3[k].equals(a)){
                    
                 selectsOption2+="<option selected value=\""+selects3[k]+"\">"+selects3[k]+"</option>";
                
                }
                
                else{
                selectsOption2+="<option value=\""+selects3[k]+"\">"+selects3[k]+"</option>";
                }
                             }
                  
                  
                  
                  //---------------------- yes/no for immunization----------------------
                  String YesNo[]={"Yes","No"};
                  for(int k=0;k<YesNo.length;k++){
                
                if(YesNo[k].equals( d)){
                    
                 options1+="<option selected value=\""+YesNo[k]+"\">"+YesNo[k]+"</option>";
                
                }
                
                else{
                options1+="<option value=\""+YesNo[k]+"\">"+YesNo[k]+"</option>";
                }
                             }
    
                  //---------------------- yes/no/ n/a for arvs----------------------
                  String YesNo2[]={"Yes","No","N/A"};
                  for(int k=0;k<YesNo2.length;k++){
                
                if(YesNo2[k].equals(e)){
                    
                 options+="<option selected value=\""+YesNo2[k]+"\">"+YesNo2[k]+"</option>";
                
                }
                
                else{
                options+="<option value=\""+YesNo2[k]+"\">"+YesNo2[k]+"</option>";
                }
                             }
    
               
          //============== yes/no/na ctx=========
                  
                  
                      for(int k=0;k<YesNo2.length;k++){
                
                if(YesNo2[k].equals(f)){
                    
                 options2+="<option selected value=\""+YesNo2[k]+"\">"+YesNo2[k]+"</option>";
                
                }
                
                else{
                options2+="<option value=\""+YesNo2[k]+"\">"+YesNo2[k]+"</option>";
                }
                             }
    

                if(times2==1){
                    
//                     System.out.println("called"+1);
                for(int j=0;j<labels2.length; j++){
//                System.out.println(labels2[j]);
               
               counts=0; 
                 

//                        col1.add("<td></td><td style=\"width:80px;\"><select name=\"PostNatalBabyTime"+ count1 + "\" style=\"width: 80px;  height:30px;\" ><option vaue="+timing+">"+ timing +"</option></select></td>");
//                         counts++;
//                        col1.add("<td>Baby's Feeding Method</td><td style=\"width:80px;\"><input type=\"text\"  style=\"width:80px;\"value=\"\" name=\"VisitBaby" + count1 + "\" id=\"VisitBaby"+ count1 + "\" ></td>");
//                        counts++;
                        col1.add("<td style=\"width:200px;text-align: left;\">Babys Condition</td><td style=\"width:80px;\"><select  style=\"width:80px;\"name=\"Condition" + count1 + "\"> "+selectsOption2+"</select></td>");
                        counts++;
                        col1.add("<td style=\"width:200px;text-align: left;\">Babys Feeding Method</td><td style=\"width:120px;\"><select style=\"width:120px;\" name=\"FeedingMethod" + count1 + "\" >"
                                + ""+selectsOption1+"</select></td>");
                        counts++;
                        col1.add( "<td style=\"width:200px;text-align: left;\">Umbilical Cord</td><td style=\"width:80px;\"><select style=\"width:80px;\"  name=\"UmbilicalCord" + count1 + "\">"+umbilicalSelects+"</select></td>");
                        counts++;
                        col1.add("<td style=\"width:200px;text-align: left;\">Baby Immunization Started</td><td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"Immunization" + count1 + "\">"+ options1 +"</select></td>");
                        counts++;
                       col1.add("<td style=\"width:200px;text-align: left;\">Infant given ARVs prophylaxis</td><td style=\"width:80px;\"><select style=\"width:80px; height:30px;\"  name=\"infantARV" + count1 + "\">" + options +"</select></td>");
                        counts++;
                        col1.add("<td style=\"width:200px;text-align: left;\">Infant CTX inititated</td><td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"infantCTX" + count1 + "\">"+  options2 +" </select><input type=\"hidden\" name=\"baby_id"+ count1 + "\" value=\"" + conn.rs1.getString(1) + "\"></td>");
                } 
                   selectsOption="";
                   selectsOption1="";
                   selectsOption2="";
                    options="";
                     options1="";
                     options2="";

                }
                if(times2==2){
//                     System.out.println("called"+1);
                for(int j=0;j<labels2.length; j++){
//                System.out.println(labels2[j]);
               
               counts=0; 
                 

//                        col2.add("<td style=\"width:80px;\"><select name=\"PostNatalBabyTime"+ count1 + "\" style=\"width: 80px;  height:30px;\" ><option vaue="+timing+">"+ timing +"</option></select></td>");
//                         counts++;
//                        col2.add("<td style=\"width:80px;\"><input type=\"text\"  style=\"width:80px;\"value=\"\" name=\"VisitBaby" + count1 + "\" id=\"VisitBaby"+ count1 + "\" ></td>");
//                        counts++;
                        col2.add("<td style=\"width:80px;\"><select  style=\"width:80px;\"name=\"Condition" + count1 + "\">"+selectsOption2+"</select></td>");
                        counts++;
                        col2.add("<td style=\"width:120px;\"><select style=\"width:120px;\" name=\"FeedingMethod" + count1 + "\" >"+selectsOption1+"</select></td>");
                        counts++;
                        col2.add( "<td style=\"width:80px;\"><select style=\"width:80px;\"  name=\"UmbilicalCord" + count1 + "\">"+umbilicalSelects+"</select></td>");
                        counts++;
                        col2.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"Immunization" + count1 + "\">"+options1+"</select></td>");
                        counts++;
                        col2.add("<td style=\"width:80px;\"><select style=\"width:80px; height:30px;\"  name=\"infantARV" + count1 + "\">"+options+"</select></td>");
                        counts++;
                        col2.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"infantCTX" + count1 + "\">"+ options2 +" </select></td><input type=\"hidden\" name=\"baby_id"+ count1 + "\" value=\"" + conn.rs1.getString(1) + "\"></td>");
                } 
 selectsOption="";
                   selectsOption1="";
                    selectsOption2="";
                      options="";
            options1="";
            options2="";
            

                }
                if(times2==3){
//                     System.out.println("called"+1);
                for(int j=0;j<labels2.length; j++){
//                System.out.println(labels2[j]);
               
               counts=0; 
                 

//                        col3.add("<td style=\"width:80px;\"><select name=\"PostNatalBabyTime"+ count1 + "\" style=\"width: 80px;  height:30px;\" ><option vaue="+timing+">"+ timing +"</option></select></td>");
//                         counts++;
//                        col3.add("<td style=\"width:80px;\"><input type=\"text\"  style=\"width:80px;\"value=\"\" name=\"VisitBaby" + count1 + "\" id=\"VisitBaby"+ count1 + "\" ></td>");
//                        counts++;
                        col3.add("<td style=\"width:80px;\"><select  style=\"width:80px;\"name=\"Condition" + count1 + "\">"+selectsOption2+"</select></td>");
                        counts++;
                        col3.add("<td style=\"width:120px;\"><select style=\"width:120px;\" name=\"FeedingMethod" + count1 + "\" >"+selectsOption1+"</select></td>");
                        counts++;
                        col3.add( "<td style=\"width:80px;\"><select style=\"width:80px;\"  name=\"UmbilicalCord" + count1 + "\">"+umbilicalSelects+"</select></td>");
                        counts++;
                        col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"Immunization" + counts + "\">"+options1 + "</select></td>");
                        counts++;
                        col3.add("<td style=\"width:80px;\"><select style=\"width:80px; height:30px;\"  name=\"infantCTX" + counts + "\">" + options +"</select></td>");
                        counts++;
                        col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"infantCTX" + counts + "\">"
                        + options2 +" </select></td><input type=\"hidden\" name=\"baby_id"+ count1 + "\" value=\"" + conn.rs1.getString(1) + "\"></td>");
                } 


                }

                count1++;
                



System.out.println("whileCount"+whileCount);
        }
            int counters2=0;
         if(whileCount==1 ){
                  
                   for(int n=1;n<labels3.length;n++){
                       counter=0; 
                        col2.add("<td style=\"width:80px;\"><select  style=\"width:80px;\"name=\"New_Condition2\"> <option value=\"\" ></option>"+selectsOption2+"</select></td>");
                        counter++;
                        col2.add("<td style=\"width:120px;\"><select style=\"width:120px;\" name=\"New_FeedingMethod2\" ><option  value=\"\"></option>"+selectsOption1+"</select></td>");
                        counter++;
                        col2.add( "<td style=\"width:80px;\"><select style=\"width:80px;\"  name=\"New_UmbilicalCord2\"><option value=\"\" ></option>"+umbilicalSelects+"</select></td>");
                        counter++;
                        col2.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"New_Immunization2\">"
                               + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                               
                                + "</select></td>");
                        counter++;
                        col2.add("<td style=\"width:80px;\"><select style=\"width:80px; height:30px;\"  name=\"New_infantARV2\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                               
                        counter++;
                        col2.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"New_infantCTX2\">"
                        + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                                
                              
                        counters2++;
                        col3.add("<td style=\"width:80px;\"><select  style=\"width:80px;\"name=\"New_Condition3\"> <option value=\"\" ></option>"+selectsOption2+"</select></td>");
                        counters2++;
                        col3.add("<td style=\"width:120px;\"><select style=\"width:120px;\" name=\"New_FeedingMethod3\" ><option  value=\"\"></option>"+selectsOption1+"</select></td>");
                        counters2++;
                        col3.add( "<td style=\"width:80px;\"><select style=\"width:80px;\"  name=\"New_UmbilicalCord3\"><option value=\"\" ></option>"+umbilicalSelects+"</select></td>");
                        counters2++;
                        col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"New_Immunization3\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                               
                                + "</select></td>");
                          col3.add("<td style=\"width:80px;\"><select style=\"width:80px; height:30px;\"  name=\"New_infantARV3\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                        counters2++;
                        col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"New_infantCTX3\">"
                         + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                                
                
               
                   }}
                   if(whileCount==2 ){
                        for(int n=1;n<labels3.length;n++){
                         counter=0; 
                        col3.add("<td style=\"width:80px;\"><select  style=\"width:80px;\"name=\"New_Condition3\"> <option value=\"\" ></option>"+selectsOption2+"</select></td>");
                        counter++;
                        col3.add("<td style=\"width:120px;\"><select style=\"width:120px;\" name=\"New_FeedingMethod3\" ><option  value=\"\"></option>"+selectsOption1+"</select></td>");
                        counter++;
                        col3.add( "<td style=\"width:80px;\"><select style=\"width:80px;\"  name=\"New_UmbilicalCord3\"><option value=\"\" ></option>"+umbilicalSelects+"</select></td>");
                        counter++;
                        col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"New_Immunization3\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                               
                                + "</select></td>");
                          col3.add("<td style=\"width:80px;\"><select style=\"width:80px; height:30px;\"  name=\"New_infantARV3\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                        counter++;
                        col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"New_infantCTX\">"
                         + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                        }
                   }
               
              

            //end of while

// System.out.println("col1Count"+col1Count);
              if(col1Count==0 ){
                  
                   for(int n=1;n<labels3.length;n++){
                       counter=0; 
                        col1.add("<td style=\"width:200px; text-align: left;\">Babys Condition</td><td style=\"width:80px;\"><select  style=\"width:80px;\"name=\"New_Condition1\"> <option value=\"\" ></option>"+newselectsOption2+"</select></td>");
                        counter++;
                        col1.add("<td style=\"width:200px; text-align: left;\">Babys Feeding Method</td><td style=\"width:120px;\"><select  style=\"width:80px;\"name=\"New_FeedingMethod1\"> <option value=\"\" ></option>"+newselectsOption1+"</select></td>");
                        counter++;
                        col1.add( "<td style=\"width:200px; text-align: left;\">Umbilical Cord</td><td style=\"width:80px;\"><select  style=\"width:80px;\"name=\"New_UmbilicalCord1\"> <option value=\"\" ></option>"+newumbilicalSelects+"</select></td>");
                        counter++;
                        col1.add("<td style=\"width:200px; text-align: left;\"> Baby Immunization Started</td><td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"New_Immunization1\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "</select></td>");
                      col1.add("<td style=\"width:200px; text-align: left;\">Infant given ARVs prophylaxis</td><td style=\"width:80px;\"><select style=\"width:80px; height:30px;\"  name=\"New_infantARV1\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                        counter++;
                        col1.add("<td style=\"width:200px; text-align: left;\">Infant given CTX prophylaxis initiated</td><td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"New_infantCTX1\">"
                        + "<option value=\"\"></option>"
                        + "<option value=\"Yes\">Yes</option>"
                        + "<option value=\"No\">No</option>"
                        + "<option value=\"N/A\">N/A</option>"
                                + " </select></td>");
               
                   }
               
              }
                if(col2Count == 0 ){
                     
                   for(int n=1;n<labels3.length;n++){
                       counter=0;
                      col2.add("<td style=\"width:80px;\"><select  style=\"width:80px;\"name=\"New_Condition2\"> <option value=\"\" ></option>"+newselectsOption2+"</select></td>");
                        counter++;
                        col2.add("<td style=\"width:120px;\"><select style=\"width:120px;\" name=\"New_FeedingMethod2\" ><option  value=\"\"></option>"+newselectsOption1+"</select></td>");
                        counter++;
                        col2.add( "<td style=\"width:80px;\"><select style=\"width:80px;\"  name=\"New_UmbilicalCord2\"><option value=\"\" ></option>"+newumbilicalSelects+"</select></td>");
                        counter++;
                        col2.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"New_Immunization2\">"
                               + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                               
                                + "</select></td>");
                        counter++;
                        col2.add("<td style=\"width:80px;\"><select style=\"width:80px; height:30px;\"  name=\"New_infantARV2\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                               
                        counter++;
                        col2.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"New_infantCTX2\">"
                        + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                                
                              
                   }}
                if(col3Count == 0 ){
                   for(int n=1;n<labels3.length;n++){
                       counter=0;
                         col3.add("<td style=\"width:80px;\"><select  style=\"width:80px;\"name=\"New_Condition3\"> <option value=\"\" ></option>"+newselectsOption2+"</select></td>");
                        counter++;
                        col3.add("<td style=\"width:120px;\"><select style=\"width:120px;\" name=\"New_FeedingMethod3\" ><option  value=\"\"></option>"+newselectsOption1+"</select></td>");
                        counter++;
                        col3.add( "<td style=\"width:80px;\"><select style=\"width:80px;\"  name=\"New_UmbilicalCord3\"><option value=\"\" ></option>"+newumbilicalSelects+"</select></td>");
                        counter++;
                        col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"New_Immunization3\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                               
                                + "</select></td>");
                          col3.add("<td style=\"width:80px;\"><select style=\"width:80px; height:30px;\"  name=\"New_infantARV3\">"
                                + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                        counter++;
                        col3.add("<td style=\"width:80px;\"><select style=\"width:80px;  height:30px;\"  name=\"New_infantCTX3\">"
                         + "<option value=\"\"></option>"
                                + "<option value=\"Yes\">Yes</option>"
                                + "<option value=\"No\">No</option>"
                                + "<option value=\"N/A\">N/A</option>"
                                + "</select></td>");
                                
                
               
                   }}
             int counters=1;   
  if(whileCount==0){
      counters=3;
  }  
  else if(whileCount==1){
      counters=2;
  }
  else if(whileCount==2){
  counters=1;
  }
  else if(whileCount==3){
  counters=0;
  }
            
System.out.println("counters"+counters);
int mc=0;
for(int a=0;a<labels2.length;a++){

//System.out.println("my count "+mc);
mc++;
postnatalbaby+="<tr>"+col1.get(a) +""+col2.get(a)+""+col3.get(a)+"</tr>";





}
            postnatalbaby += "<input type=\"hidden\" id=\"PostNatalBaby\"  name=\"PostNatalBaby\" value=\"" + (counters+1)+ "\"/> <input type=\"hidden\" id=\"PostNatalBaby_old_rows\" name=\"PostNatalBaby_old_rows\" value=\"" + (count1 - 1) + "\"/>";


         //   System.out.println("ANC NO:" + allinputfields);

         //   System.out.println("ANC NO:" + allinputfields);


           
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("" + postnatalbaby + "");
                out.println("</body>");
                out.println("</html>");

                postnatalbaby = "";
            } finally {
                out.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(PostNatalBaby.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PostNatalBaby.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PostNatalBaby.class.getName()).log(Level.SEVERE, null, ex);
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
