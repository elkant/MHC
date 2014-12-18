/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


var b5=1;  //hplds the no of existing rows for the table to add new values
var verifierstep5=1;
var verifierstep51=1;
var allrowsoldnew5=0;
//verifier is a field that determines whether to fetch the value of i from the edit text.
var f5=0;   


//create a table after pressing an enter
//function ifenter(e){
//    var unicode=e.keyCode? e.keyCode : e.charCode;
//    if(unicode==13||unicode==9){
//    
//        addstep6('step6_table');
//    }
//}
        
        
function reseta5(){
    b5=1;
    verifierstep5=1;
    verifierstep51=1;
} 
        
        
function  addrowstep5mat(tableID) {
  
  
            
    if(verifierstep5==1){
        allrowsoldnew5=parseInt(document.getElementById("all_rows5_count").value);
        f5=parseInt(document.getElementById("acai_oldrows").value)+(parseInt(1));
        b5=parseInt(document.getElementById("acai_newrows").value);
    //alert(""+document.getElementById("no_of_old_rows").value);        
    }
            
    b5++;
    verifierstep5++;
    verifierstep51++;  
    allrowsoldnew5++; 
            
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
       var anc_reg_options="<option value=\"\"></option><option value=\"sdNVP\">sdNVP</option><option value=\"AZT\">AZT</option><option value=\"T\">T</option><option value=\"P\">P</option>";
       var yes_no_options="<option value=\"\"></option><option value=\"Y\">Y</option><option value=\"N\">N</option>";
       var codes ="<option value=\"\"></option>s<option value=\"PM1\" title=\"AZT;NVP+AZT+3TC;AZT/3TC\">PM1</option><option value=\"PM2\" title=\"NVP+AZT+3TC;AZT/3TC\">PM2</option><option value=\"PM3\" title=\"PMTCT HAART: AZT + 3TC +NVP\">PM3</option><option value=\"PM4\" title=\"PMTCT HAART: AZT + 3TC + EFV\">PM4</option><option value=\"PM5\" title=\"PMTCT HAART: AZT + 3TC + LPV/r\">PM5</option><option value=\"PM6\" title=\"PMTCT HAART: TDF + 3TC +NVP\">PM6</option><option value=\"PM7\" title=\"PMTCT HAART: TDF + 3TC + LPV/r\">PM7</option><option value=\"PM8\" title=\"Nevirepine(NVP) Single Dose (SD)\">PM8</option>";
       var res_options="<option value=\"\"></option><option value=\"P\">P</option><option value=\"N\">N</option><option value=\"KP\">KP</option><option value=\"U\">U</option>";
       
       
       
    col1.innerHTML=""+allrowsoldnew5+"";
    col2.innerHTML="<select name=\"acai_new_anc"+b5+"\" id=\"acai_new_anc"+b5+"\" style=\"padding:1px; width:65px;\">"+anc_reg_options+"</select>";
    col3.innerHTML="<select name=\"acai_new_mat"+b5+"\" id=\"acai_new_mat"+b5+"\"  style=\"width:55px;\">"+codes+"</select>";
    col4.innerHTML="<select name=\"new_tobaby"+b5+"\" id=\"new_tobaby"+b5+"\" style=\"width:45px;\">"+yes_no_options+"</select>";
    col5.innerHTML="<Select id=\"acai_ctx"+b5+"\" style=\"width:45px;\" name=\"acai_ctx"+b5+"\">" + yes_no_options + " </Select>";
    col6.innerHTML="<Select id=\"acai_vitamina"+b5+"\" style=\"width:39px;\" name=\"acai_vitamina"+b5+"\">" + yes_no_options + " </Select>";
    col7.innerHTML="<Select id=\"acai_tested"+b5+"\" style=\"width:39px;\" name=\"acai_tetsed"+b5+"\">" + yes_no_options + " </Select>";
    col8.innerHTML="<Select id=\"acai_results"+b5+"\" style=\"width:39px;\" name=\"acai_results"+b5+"\">" + res_options + " </Select>";

//    res.onkeypress= function ifenter(e){
//        var unicode=e.keyCode? e.keyCode : e.charCode;
//        if(unicode==13||unicode==9){
//    
//            addstep5('step6_table');
//        }//end of if
//    }//end of function
//    //element5.style.width = "62px";
    
   

    //var as = document.getElementById("h");    
    f5++;  
    // allrowsoldnew++;
    document.getElementById("acai_newrows").value=b5;
    document.getElementById("all_rows5_count").value=allrowsoldnew5;
      
//alert(i);
   
   addrowstep6mat('ajtoantable');
}
 
function delrowstep5mat(tableID) {
    //if the addrow has not been called  
        
            
    if((verifierstep51==1)){
        allrowsoldnew5=parseInt(document.getElementById("all_rows5_count").value);
        f5 =parseInt(document.getElementById("acai_oldrows").value)+(parseInt(1));
        b5=parseInt(document.getElementById("acai_newrows").value)+(parseInt(1));
    //alert(""+document.getElementById("no_of_old_rows").value);        
    }
                     
    verifierstep51++;
    verifierstep5++;
            
            
    try {
                
            
        var table = document.getElementById(tableID);
        //delete the last row 
            
             
            
        var rowCount = table.rows.length;
       
        //delete ntil you have one row
        if(rowCount>parseInt(document.getElementById("acai_oldrows").value)+parseInt(5)){
                  
            table.deleteRow(rowCount-1);
            rowCount--;
                    
                    
            //reduce the number of rows           
            f5--;
            b5--;
            allrowsoldnew5--;
            document.getElementById("acai_newrows").value=b5;
             document.getElementById("all_rows5_count").value=allrowsoldnew5;      
                     
        }
        else{
                
            //alert("Maximum deletable rows reached!!");
                
        }
                
    // }
    }catch(e) {
        alert(e);
    }
    
    delrowstep6mat('ajtoantable');
    
}