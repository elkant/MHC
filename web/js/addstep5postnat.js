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
   allrowsoldnew5=1;
   f5=0;
} 
        
        
function  addstep5postnat(tableID) {
  
  
            
    if(verifierstep5==1){
        allrowsoldnew5=parseInt(document.getElementById("all_rows5_count").value);
        f5=parseInt(document.getElementById("aaae_oldrows").value)+(parseInt(1));
        b5=parseInt(document.getElementById("aaae_newrows").value);
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
  
  
 var yes_no_options = "<option value=\"\"></option><option value=\"Y\">Y</option><option value=\"N\">N</option>";
 var pncyesno_options = "<option value=\"\"></option><option value=\"Y\">Y</option><option value=\"N\">N</option><option value=\"NA\">NA</option>";
 var hivres_options = "<option value=\"\"></option><option value=\"P\">P</option><option value=\"N\">N</option><option value=\"U\">U</option><option value=\"KP\">KP</option>";
 var meth_options = "<option value=\"\"></option><option value=\"VIA\">VIA</option><option value=\"VILI\">VILI</option><option value=\"PAP\">PAP</option><option value=\"NOT DONE\">NOT DONE</option>";
 var res_options = "<option value=\"\"></option><option value=\"Normal\">Normal</option><option value=\"Suspect\">Suspect</option><option value=\"Confirmed\">Confirmed</option>";
 var fp_options = "<option value=\"\"></option><option value=\"C\">C</option><option value=\"ECP\">ECP</option><option value=\"OC\">OC</option><option value=\"INJ\">INJ</option><option value=\"IMP\">IMP</option><option value=\"IUD\">IUD</option><option value=\"LAM\">LAM</option><option value=\"D\">D</option><option value=\"FA\">FA</option><option value=\"TL\">TL</option><option value=\"V\">V</option>";

       
    col1.innerHTML=""+allrowsoldnew5+"";
    col2.innerHTML="<select  name=\"new_counselled"+b5+"\" id=\"new_counselled"+b5+"\" >"+yes_no_options+"</select>";
        col3.innerHTML="<Select id=\"new_pnc"+b5+"\" style=\"width:39px;\" name=\"new_pnc"+b5+"\">" + pncyesno_options + " </Select>";
            col4.innerHTML="<Select id=\"new_hivres"+b5+"\" style=\"width:39px;\" name=\"new_hivres"+b5+"\">" + hivres_options + " </Select>";
                col5.innerHTML="<Select id=\"new_meth"+b5+"\" style=\"width:39px;\" name=\"new_meth"+b5+"\">" + meth_options + " </Select><br/><select  name=\"new_res"+b5+"\" id=\"new_res"+b5+"\" >"+res_options+"</select>";
                    col6.innerHTML="<Select id=\"old_fp"+b5+"\" style=\"width:39px;\" name=\"new_fp"+b5+"\">" + fp_options + " </Select>";
    
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
    document.getElementById("aaae_newrows").value=b5;
    document.getElementById("all_rows5_count").value=allrowsoldnew5;
      
//alert(i);
   
   addstep6postnat('aftoaitable');
}
 
function delstep5postnat(tableID) {
    //if the addrow has not been called  
        
            
    if((verifierstep51==1)){
        allrowsoldnew5=parseInt(document.getElementById("all_rows5_count").value);
        f5 =parseInt(document.getElementById("aaae_oldrows").value)+(parseInt(1));
        b5=parseInt(document.getElementById("aaae_newrows").value)+(parseInt(1));
    //alert(""+document.getElementById("no_of_old_rows").value);        
                          }
                     
    verifierstep51++;
    verifierstep5++;
            
            
    try {
                
            
        var table = document.getElementById(tableID);
        //delete the last row 
            
             
            
        var rowCount = table.rows.length;
       
        //delete ntil you have one row
        if(rowCount>parseInt(document.getElementById("aaae_oldrows").value)+parseInt(7)){
                  
            table.deleteRow(rowCount-1);
            rowCount--;
                    
                    
            //reduce the number of rows           
            f5--;
            b5--;
            allrowsoldnew5--;
            document.getElementById("aaae_newrows").value=b5;
            document.getElementById("all_rows5_count").value=allrowsoldnew5;      
                     
        }
        else{
                
            //alert("Maximum deletable rows reached!!");
                
        }
                
    // }
    }catch(e) {
        alert(e);
    }
    
    delstep6postnat('aftoaitable');
    
}