/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


var b7=1;  //hplds the no of existing rows for the table to add new values
var verifierstep7=1;
var verifierstep71=1;
var allrowsoldnew7=0;
//verifier is a field that determines whether to fetch the value of i from the edit text.
var f7=0;   

//create a table after pressing an enter
//function ifenter(e){
//    var unicode=e.keyCode? e.keyCode : e.charCode;
//    if(unicode==13||unicode==9){
//    
//        addstep6('step6_table');
//    }
//}
        
        
function reseta7(){
    b7=1;
    verifierstep7=1;
    verifierstep71=1;
} 
        
        
function addstep7anc(tableID) {
            
     // alert("w");      
    if(verifierstep7==1){
        allrowsoldnew7=parseInt(document.getElementById("all_rows7_count").value)-(parseInt(1));;
        f7=parseInt(document.getElementById("old_step7_no_rows").value)+(parseInt(1));
        b7=parseInt(document.getElementById("new_step7_no_rows").value);
    //alert(""+document.getElementById("no_of_old_rows").value);        
    }
            
    b7++;
    verifierstep7++;
    verifierstep71++;  
    allrowsoldnew7++; 
            
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

    
    
    col1.innerHTML="<label style=\"text-align:center;\">"+allrowsoldnew7+"</label>";
    
    col2.innerHTML="<select style=\"height:58px;\" multiple  name=\"adt_treatment"+b7+"\" id=\"adt_treatment"+b7+"\"><option value=\"1\" >1=Hypertension</option><option value=\"2\">2=Diabetes</option><option value=\"3\">3=Epilepsy</option><option value=\"4\">4=Malaria in Pregnancy</option><option value=\"5\">5=STIs/RTI</option></select>"
    + "<input type=\"checkbox\" class=\"shortinput\" title=\"click to add other conditions\" value=\"yes\" id=\"otherstriger7"+b7+"\" onclick=\"otherothercondz7("+b7+");\">6=Others<br/><input name=\"others7"+b7+"\" required id=\"others7"+b7+"\" size=\"24px\" type=\"hidden\">";
    
    
    col3.innerHTML="<select  id=\"coupleconselled"+b7+"\" name=\"coupleconselled"+b7+"\"><option value=\"\"></option><option value=\"Y\">Y</option><option value=\"N\">N</option></select>";
    
    col4.innerHTML="<select  id=\"test_results"+b7+"\" name=\"test_results"+b7+"\"  > <option value=\"\"></option><option value=\"P\">P</option><option value=\"N\">N</option><option value=\"U\">U</option><option value=\"KP\">KP</option></select>";
   
   
   
    col5.innerHTML="<input id=\"reffered"+b7+"\" type=\"text\"  name=\"reffered"+b7+"\" >";
   
   
   
    col6.innerHTML="<textarea id=\"remarks"+b7+"\" rows=\"3\" cols=\"15\" name=\"remarks"+b7+"\"></textarea>";
   
   
   
//    
//    
//    res.onkeypress= function ifenter(e){
//        var unicode=e.keyCode? e.keyCode : e.charCode;
//        if(unicode==13||unicode==9){
//    
//            addstep5('step7_table');
//        }//end of if
//    }//end of function
//    //element5.style.width = "62px";
//    
    
    
 
   // var frm = document.getElementById("frm");
    //var as = document.getElementById("h");    
    f7++;  
    // allrowsoldnew++;
    document.getElementById("new_step7_no_rows").value=b7;
      
//alert(b7);
           
}
 
function deletestep7(tableID) {
    //if the addrow has not been called  
        
            
    if((verifierstep71==1)){
        allrowsoldnew7=parseInt(document.getElementById("all_rows7_count").value);
        f7 =parseInt(document.getElementById("old_step7_no_rows").value)+(parseInt(1));
        b7=parseInt(document.getElementById("new_step7_no_rows").value)+(parseInt(1));
    //alert(""+document.getElementById("no_of_old_rows").value);        
    }
                     
    verifierstep71++;
    verifierstep7++;
            
            
    try {
                
            
        var table = document.getElementById(tableID);
        //delete the last row 
            
             
            
        var rowCount = table.rows.length;
       
        //delete ntil you have one row
        if(rowCount>parseInt(document.getElementById("old_step7_no_rows").value)+parseInt(3)){
                  
            table.deleteRow(rowCount-1);
            rowCount--;
                    
                    
            //reduce the number of rows           
            f7--;
            b7--;
            allrowsoldnew7--;
            document.getElementById("new_step7_no_rows").value=b7;
                   
                     
        }
        else{
                
           // alert("Maximum deletable rows reached!!");
                
        }
                
    // }
    }catch(e) {
        alert(e);
    }
}