/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


var b6=1;  //hplds the no of existing rows for the table to add new values
var verifierstep6=1;
var verifierstep61=1;
var allrowsoldnew6=0;
//verifier is a field that determines whether to fetch the value of i from the edit text.
var f6=0;   

//create a table after pressing an enter
//function ifenter(e){
//    var unicode=e.keyCode? e.keyCode : e.charCode;
//    if(unicode==13||unicode==9){
//    
//        addstep6('step6_table');
//    }
//}
        
        
function reseta6(){
    b6=1;
    verifierstep6=1;
    verifierstep61=1;
} 
        
        
function addstep6(tableID) {
            
            
    if(verifierstep6==1){
        allrowsoldnew6=parseInt(document.getElementById("all_rows6_count").value)-(parseInt(1));
        f6=parseInt(document.getElementById("old_step6_no_rows").value)+(parseInt(1));
        b6=parseInt(document.getElementById("new_step6_no_rows").value);
    //alert(""+document.getElementById("no_of_old_rows").value);        
    }
            
    b6++;
    verifierstep6++;
    verifierstep61++;  
    allrowsoldnew6++; 
            
    //add a row if only there exists data of that 
            
    //if(document.getElementById("blank_warning").innerHTML==""){
            
 
    var table = document.getElementById(tableID);
    //get the number of rows
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    //create element that is a checkbox assign type, name,style then apppend the child element to the cell
    var col1 = row.insertCell(0);
    var col2 = row.insertCell(1);
    var col3 = row.insertCell(2);
    var col4 = row.insertCell(3);
    var col5 = row.insertCell(4);
    var col6 = row.insertCell(5);
    var col7 = row.insertCell(6);
    var col8 = row.insertCell(7);
    
    
    col1.innerHTML="<label style=\"text-align:center;\">"+allrowsoldnew6+"</label>";
    
    col2.innerHTML="<select style=\"height:58px;\" multiple  name=\"other_conditions"+b6+"\" id=\"other_conditions"+b6+"\"><option value=\"1\" >1=Hypertension</option><option value=\"2\">2=Diabetes</option><option value=\"3\">3=Epilepsy</option><option value=\"4\">4=Malaria in Pregnancy</option><option value=\"5\">5=STIs/RTI</option></select>"
    + "<input type=\"checkbox\" class=\"shortinput\" title=\"click to add other conditions\" value=\"yes\" id=\"otherstriger"+b6+"\" onclick=\"otherothercondz("+b6+");\">6=Others<br/><input name=\"others"+b6+"\" required id=\"others"+b6+"\" size=\"24px\" type=\"hidden\">";
    
    
    col3.innerHTML="<select style=\"width:60px;\" id=\"deworming"+b6+"\" name=\"deworming"+b6+"\"><option value=\"\"></option><option value=\"Y\">Y</option><option value=\"N\">N</option></select>";
    
    col4.innerHTML="<select style=\"width:60px;height:58px;\" id=\"IPT"+b6+"\" name=\"IPT"+b6+"\" style=\"height:58px;\" multiple> <option value=\"1\">1</option><option value=\"2\" >2</option><option value=\"3\" >3</option>  </select>";
   
    col5.innerHTML="<select style=\"width:60px;height:58px;\" id=\"tt_dose"+b6+"\" name=\"tt_dose"+b6+"\" style=\"height:58px;\" multiple> <option value=\"1\">1</option><option value=\"2\" >2</option><option value=\"3\" >3</option> <option value=\"4\" >4</option><option value=\"5\" >5</option> </select>";
   
   
   
    col6.innerHTML="<select style=\"width:60px;\" id=\"iron"+b6+"\" name=\"iron"+b6+"\"><option value=\"\"></option><option value=\"Y\">Y</option><option value=\"N\">N</option></select>";
   
   
    col7.innerHTML="<select style=\"width:60px;\" id=\"folic_acid"+b6+"\" name=\"folic_acid"+b6+"\"><option value=\"\"></option><option value=\"Y\">Y</option><option value=\"N\">N</option></select>";
  
    col8.innerHTML="<select style=\"width:60px;\" id=\"ITN"+b6+"\" name=\"ITN"+b6+"\"><option value=\"\"></option><option value=\"Y\">Y</option><option value=\"N\">N</option></select>";
    
    
    
//    res.onkeypress= function ifenter(e){
//        var unicode=e.keyCode? e.keyCode : e.charCode;
//        if(unicode==13||unicode==9){
//    
//            addstep5('step6_table');
//        }//end of if
//    }//end of function
//    //element5.style.width = "62px";
    
   

    //var as = document.getElementById("h");    
    f6++;  
    // allrowsoldnew++;
    document.getElementById("new_step6_no_rows").value=b6;
      
//alert(i);
           
}
 
function deletestep6(tableID) {
    //if the addrow has not been called  
        
            
    if((verifierstep61==1)){
        allrowsoldnew6=parseInt(document.getElementById("all_rows6_count").value);
        f6 =parseInt(document.getElementById("old_step6_no_rows").value)+(parseInt(1));
        b6=parseInt(document.getElementById("new_step6_no_rows").value)+(parseInt(1));
    //alert(""+document.getElementById("no_of_old_rows").value);        
    }
                     
    verifierstep61++;
    verifierstep6++;
            
            
    try {
                
            
        var table = document.getElementById(tableID);
        //delete the last row 
            
             
            
        var rowCount = table.rows.length;
       
        //delete ntil you have one row
        if(rowCount>parseInt(document.getElementById("old_step6_no_rows").value)+parseInt(3)){
                  
            table.deleteRow(rowCount-1);
            rowCount--;
                    
                    
            //reduce the number of rows           
            f6--;
            b6--;
            allrowsoldnew6--;
            document.getElementById("new_step6_no_rows").value=b6;
                   
                     
        }
        else{
                
          //  alert("Maximum deletable rows reached!!");
                
        }
                
    // }
    }catch(e) {
        alert(e);
    }
}