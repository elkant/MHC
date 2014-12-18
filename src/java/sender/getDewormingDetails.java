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

public class getDewormingDetails extends HttpServlet {
String allinputdetails,allages,usedages,anc_no,selecedageids;
ArrayList ageids= new ArrayList();
ArrayList agenames= new ArrayList();
int allrows;//the number of total rows expected in this table for now
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    String motherID = request.getParameter("motherID");
      anc_no = request.getParameter("ancno");
      
      
    dbConn conn= new dbConn();
    
    
   
    allrows=0;
    allinputdetails="";
    allages="";
    usedages="";
    selecedageids="";
    
    
    if(ageids.size()>0){
    ageids.clear();
    }
    
     if(agenames.size()>0){
    agenames.clear();
    }
    
    
    allinputdetails+=" <tr><th style=\"width:102px;\">Age</th>" 
           +"<th style=\"width:62px;\">Drug</th>"
           +"<th style=\"width:62px;\">Dosage</th>"
          +"<th style=\"width:62px;\">Date of Next Visit</th></tr>";
    //get the ages from the database.
    //as per date of making the system, there should be only one field
      allages="<option value=\"\"></option>";
    
conn.rs=conn.st.executeQuery("Select * from vitamin_ages where age_id >'2'");

while(conn.rs.next()){
    //count all rows which determines the no of rows to have in my table.
    
    
allrows++;
allages+="<option value=\""+conn.rs.getString(1) +"\">"+conn.rs.getString(2) +"</option>";


//add the names and ids ofall the ages to arraylists

agenames.add(conn.rs.getString(2));
ageids.add(conn.rs.getString(1));


}
     
 //_______________________________if no values exist in the database, then show the fields to the user__________________________________
//create the input forms dynamically

int addedcount=0;
int notaddedcount=0;
int prevexists=0;

//if the mother is already added to the database, then gate their details from the database.
//+===============================================================================================================


String fromdbase="Select * from deworming where motherid='"+motherID+"'";
//get the selected option value


conn.rs1=conn.st1.executeQuery(fromdbase);

while(conn.rs1.next()){
  
    // get the selected option into a string fo comparison
    usedages+=""+conn.rs1.getString(2);


                      }

//____________THIS WILL BE USED IF THERE WILL BE SUGGESTIONS OF INCOPERATING THE DROP DOWN LISTS.
//FOR NOW THE FIRLDS ARE STATIC.
//now create the options to appear in the selected box


conn.rs=conn.st.executeQuery("Select * from vitamin_ages where age_id >'2'");
while(conn.rs.next()){

    if(usedages.contains(conn.rs.getString(1))){
        
    selecedageids+="<option selected value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2) +"</option>";
    
       }
    else{
    
      selecedageids+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2) +"</option>";
  
    }


}//end of creating the options 

//Now create the rows and fetch the filled values



//===================================VALUES FROM THE DATABASE==========================

conn.rs1=conn.st1.executeQuery(fromdbase);

while(conn.rs1.next()){
  
    conn.rs4=conn.st4.executeQuery("select * from vitamin_ages where age_id='"+conn.rs1.getString("age_id")+"'");
  
  conn.rs4.next();
   
   
    
  addedcount++;
  prevexists++; 
    
    
allinputdetails+="<tr><th><Label ><b>"+conn.rs4.getString(2) +"</b></label><input type=\"hidden\" name=\"dw_old_age"+addedcount+"\" value=\""+conn.rs1.getString("age_id")+"\" ></th>"
        + "<th><input type=\"text\" value=\""+conn.rs1.getString("drug")+"\"  id=\"dw_old_drug"+addedcount+"\" name=\"dw_old_drug"+addedcount+"\"/></th>"
        +"<th><input type=\"text\" value=\""+conn.rs1.getString("dosage")+"\" id=\"dew_old_dosage"+addedcount+"\" name=\"dew_old_dosage"+addedcount+"\"/></th>"
        +"<th><input type=\"text\" value=\""+conn.rs1.getString("next_visit")+"\" placeholder=\"yyyy-mm-dd\" id=\"_dw_dp"+addedcount+"\" name=\"dw_old_nextvisit"+addedcount+"\"/>"
        + "<input type=\"hidden\" value=\""+conn.rs1.getString("deworming_id")+"\" name=\"deworm_id"+addedcount+"\"></th></tr>";

for(int a=0;a<ageids.size();a++){

  if(usedages.contains(ageids.get(a).toString())){  
agenames.remove(a);  
ageids.remove(a);  

  }//end of if

}//end of for
allrows--;
    
                      }//end of while which creates all the form input fields for the previously filled parts





//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\





//===========================================================================================================

//if(addedcount==0){
for(int a=0;a<allrows;a++){
notaddedcount++;
allinputdetails+="<tr><td><Label >"+agenames.get(a)+"</label><input type=\"hidden\" name=\"dw_age"+(a+1)+"\" value=\""+ageids.get(a)+"\" ></td>"
        + "<td><input type=\"text\" id=\"dw_drug"+(a+1)+"\" name=\"dw_drug"+(a+1)+"\"/></td>"
        +"<td><input type=\"text\" id=\"dew_dosage"+(a+1)+"\" name=\"dew_dosage"+(a+1)+"\"/></td>"
        +"<td><input type=\"text\" id=\"dw_dp"+(a+1)+"\" placeholder=\"yyyy-mm-dd\" name=\"dw_nextvisit"+(a+1)+"\"/></td>";

//System.out.println("count:"+a);

}

//end the last row of the table
allinputdetails+="</tr><tr><td><input type=\"hidden\" id=\"dw_new_no_of_rows\" name=\"dw_new_no_of_rows\" value=\""+notaddedcount+"\">"
        + "<input type=\"hidden\" id=\"dw_old_no_of_rows\" name=\"dw_old_no_of_rows\" value=\""+addedcount+"\"></td></tr>";
//}
                   
    
    
    PrintWriter out = response.getWriter();
    try {
        
        out.println(""+allinputdetails);
        
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(getDewormingDetails.class.getName()).log(Level.SEVERE, null, ex);
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
