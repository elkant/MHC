/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

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


/**
 *
 * @author SIXTYFOURBIT
 */

public class getvitaminADetails extends HttpServlet {
String allinputdetails,allages,usedages,anc_no,selecedageids;

ArrayList doseids= new ArrayList();
//
ArrayList agenames= new ArrayList();


ArrayList dosenames= new ArrayList();

int allrows=0;//the number of total rows expected in this table for now
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    
      anc_no = request.getParameter("ancno");
     String motherID = request.getParameter("motherID"); 
      
    dbConn conn= new dbConn();
    
    
   
    
    allinputdetails="";
    allages="";
    usedages="";
    selecedageids="";
    allrows=0;
    
    if(doseids.size()>0){
    doseids.clear();
    }
    
    if(dosenames.size()>0){
    dosenames.clear();
    }
    
    
     if(agenames.size()>0){
    agenames.clear();
    }
    
    
    allinputdetails+=" <tr><th style=\"width:102px;\">Dose</th>" 
           +"<th style=\"width:62px;\">Age</th>"
           +"<th style=\"width:62px;\">Tick Age Given</th>"
          +"<th style=\"width:62px;\">Date of Next Visit</th></tr>";
    //get the ages from the database.
    //as per date of making the system, there should be only one field
      //allages="<option value=\"\"></option>";
    
conn.rs=conn.st.executeQuery("Select * from dose ");

while(conn.rs.next()){
    //count all rows which determines the no of rows to have in my table.
    
    
allrows++;
//allages+="<option value=\""+conn.rs.getString(1) +"\">"+conn.rs.getString(2) +"</option>";


//add the names and ids ofall the ages and doses to arraylists

agenames.add(conn.rs.getString(3));
doseids.add(conn.rs.getString(1));
dosenames.add(conn.rs.getString(2));


}
     
 //_______________________________if no values exist in the database, then show the fields to the user__________________________________
//create the input forms dynamically

int addedcount=0;
int notaddedcount=0;

//if the mother is already added to the database, then gate their details from the database.
//+===============================================================================================================


String fromdbase="Select * from vitamin_a_capsule where motherid='"+motherID+"' order by dose_id ";
//get the selected option value


conn.rs1=conn.st1.executeQuery(fromdbase);

while(conn.rs1.next()){
  
    // get the selected option into a string for comparison
    //this is not used for now, its just there awaiting change
    usedages+=""+conn.rs1.getString(2)+",";


                      }

//____________THIS WILL BE USED IF THERE WILL BE SUGGESTIONS OF INCOPERATING  DROP DOWN LISTS.
//FOR NOW THE FIELDS ARE STATIC.
//now create the options to appear in the selected box


conn.rs=conn.st.executeQuery("Select * from vitamin_a_capsule");
while(conn.rs.next()){

    if(usedages.contains(conn.rs.getString(1))){
        
    selecedageids+="<option selected value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2) +"</option>";
    
       }
    else{
    
      selecedageids+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2) +"</option>";
  
    }


}//end of creating the options 

//Now create the rows and fetch the filled values



//=========================================THE ALREADY ADDED FIELDS==========================================

conn.rs1=conn.st1.executeQuery(fromdbase);

while(conn.rs1.next()){
  
   
   
  //this variable is used for comparison purpose  
  addedcount++;
//get the dose details from the database
  
  conn.rs4=conn.st4.executeQuery("select * from  dose where dose_id='"+conn.rs1.getString("dose_id")+"'");
  
  conn.rs4.next();
  
  
  
    
    
allinputdetails+="<tr style=\"\"><th><Label ><b>"+conn.rs4.getString(2) +"</b></label></th><th><Label ><b>"+conn.rs4.getString(3) +"</b></label><input type=\"hidden\" name=\"vit_old_dose_id"+addedcount+"\" value=\""+conn.rs1.getString("dose_id")+"\" ></th>"
          +"<th><input type=\"checkbox\" onclick=\"togglevalue(\'dw_old_dosage"+addedcount+"\');\" value=\""+conn.rs1.getString("dose_given")+"\" id=\"dw_old_dosage"+addedcount+"\" name=\"dw_old_dosage"+addedcount+"\"/></th>"
        +"<th><input type=\"text\" value=\""+conn.rs1.getString("nextvisit_date")+"\" placeholder=\"yyyy-mm-dd\" id=\"_vit_dp"+addedcount+"\" name=\"vit_old_nextvisit"+addedcount+"\"/>"
        + "<input type=\"hidden\" value=\""+conn.rs1.getString("vitamin_id")+"\" name=\"vit_id"+addedcount+"\"></th></tr>";


//remove that specific dose from my arraylist using a loop

for(int a=0;a<doseids.size();a++){

  if(usedages.contains(doseids.get(a).toString())){  
agenames.remove(a);  
doseids.remove(a);  
dosenames.remove(a);  
  }
}
allrows--;
    
                      }//end of while which creates all the form input fields for the previously filled parts





//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\





//===========================================================================================================

//if(addedcount==0){
for(int a=0;a<allrows;a++){
notaddedcount++;

allinputdetails+="<tr><td><Label >"+dosenames.get(a) +"</label></td><td><Label >"+agenames.get(a) +"</label><input type=\"hidden\" name=\"vit_dose_id"+(a+1)+"\" value=\""+doseids.get(a) +"\" ></td>"
          +"<td><input type=\"checkbox\" onclick=\"togglevalue(\'dw_dosage"+(a+1)+"\');\" value=\"no\" name=\"dw_dosage"+(a+1)+"\"  id=\"dw_dosage"+(a+1)+"\" /></td>"
        +"<td><input type=\"text\"  id=\"vit_dp"+(a+1)+"\" placeholder=\"yyyy-mm-dd\" name=\"vit_nextvisit"+(a+1)+"\"/></td></tr>";

//System.out.println("count:"+a);

}

//end the last row of the table
allinputdetails+="</tr><tr><td colspan=\"4\"><input type=\"hidden\" id=\"vit_new_no_of_rows\" name=\"vit_new_no_of_rows\" value=\""+notaddedcount+"\">"
        + "<input type=\"hidden\" id=\"vit_old_no_of_rows\" name=\"vit_old_no_of_rows\" value=\""+addedcount+"\"></td></tr>";
//}
                   
    
    
    PrintWriter out = response.getWriter();
    try {
        
        out.println(""+allinputdetails);
        
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(getvitaminADetails.class.getName()).log(Level.SEVERE, null, ex);
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
