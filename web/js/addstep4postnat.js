/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


var b4=1;  //hplds the no of existing rows for the table to add new values
var verifierstep4=1;
var verifierstep41=1;
var allrowsoldnew4=0;
//verifier is a field that determines whether to fetch the value of i from the edit text.
var f4=0;   


//create a table after pressing an enter
//function ifenter(e){
//    var unicode=e.keyCode? e.keyCode : e.charCode;
//    if(unicode==13||unicode==9){
//    
//        addstep6('step6_table');
//    }
//}
        
        
function reseta4(){
    b4=1;
    verifierstep4=1;
    verifierstep41=1;
} 
        
        
function  addrowstep4postnat(tableID) {
            
            
    if(verifierstep4==1){
        allrowsoldnew4=parseInt(document.getElementById("all_rows4_count").value);
        f4=parseInt(document.getElementById("uz_oldrows").value)+(parseInt(1));
        b4=parseInt(document.getElementById("uz_newrows").value);
    //alert(""+document.getElementById("no_of_old_rows").value);        
    }
            
    b4++;
    verifierstep4++;
    verifierstep41++;  
    allrowsoldnew4++; 
            
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
   
   
   var yes_no_options = "<option value=\"\"></option><option value=\"Y\">Y</option><option value=\"N\">N</option>";
            var pnc_options = "<option value=\"\"></option><option value=\"P\">P</option><option value=\"N\">N</option><option value=\"ND\">ND</option><option value=\"NA\">NA</option>";
            var pks_options = "<option value=\"\"></option><option value=\"P\">P</option><option value=\"N\">N</option><option value=\"U\">U</option>";

    
    
    col1.innerHTML=""+allrowsoldnew4+"";
    col2.innerHTML="<select  name=\"new_pks"+b4+"\" id=\"new_pks"+b4+"\" >"+pks_options+"</select>";
        col3.innerHTML="<Select id=\"new_les72"+b4+"\" style=\"width:39px;\" name=\"new_les72"+b4+"\">" + pnc_options + " </Select>";
            col4.innerHTML="<Select id=\"new_gret72"+b4+"\" style=\"width:39px;\" name=\"new_gret72"+b4+"\">" + pnc_options + " </Select>";
                col5.innerHTML="<Select id=\"new_nvptobaby"+b4+"\" style=\"width:39px;\" name=\"new_nvptobaby"+b4+"\">" + yes_no_options + " </Select>";
                    col6.innerHTML="<Select id=\"new_ctxbaby"+b4+"\" style=\"width:39px;\" name=\"new_ctxbaby"+b4+"\">" + yes_no_options + " </Select>";
                        col7.innerHTML="<Select id=\"new_ctxmother"+b4+"\" style=\"width:39px;\" name=\"new_ctxmother"+b4+"\">" + yes_no_options + " </Select>";
    //    res.onkeypress= function ifenter(e){
    //        var unicode=e.keyCode? e.keyCode : e.charCode;
    //        if(unicode==13||unicode==9){
    //    
    //            addstep5('step6_table');
    //        }//end of if
    //    }//end of function
    //    //element5.style.width = "62px";
    
   

    //var as = document.getElementById("h");    
    f4++;  
    // allrowsoldnew++;
    document.getElementById("uz_newrows").value=b4;
    document.getElementById("all_rows4_count").value=allrowsoldnew4;
     document.getElementById("page1rows").innerHTML="("+allrowsoldnew4+") Rows";      
     document.getElementById("page2rows").innerHTML="("+allrowsoldnew4+") Rows";      
     document.getElementById("page3rows").innerHTML="("+allrowsoldnew4+") Rows";      
     document.getElementById("page4rows").innerHTML="("+allrowsoldnew4+") Rows";      
    document.getElementById("page5rows").innerHTML="("+allrowsoldnew4+") Rows";      
    document.getElementById("page6rows").innerHTML="("+allrowsoldnew4+") Rows";    
      
      addstep5postnat('aatoaetable');
//alert(i);
           
}
 
function delrowstep4postnat(tableID) {
    //if the addrow has not been called  
        
            
    if((verifierstep41==1)){
        allrowsoldnew4=parseInt(document.getElementById("all_rows4_count").value);
        f4 =parseInt(document.getElementById("uz_oldrows").value)+(parseInt(1));
        b4=parseInt(document.getElementById("uz_newrows").value)+(parseInt(1));
    //alert(""+document.getElementById("no_of_old_rows").value);        
    }
                     
    verifierstep41++;
    verifierstep4++;
            
            
    try {
                
            
        var table = document.getElementById(tableID);
        //delete the last row 
            
             
            
        var rowCount = table.rows.length;
       
        //delete ntil you have one row
        if(rowCount>parseInt(document.getElementById("uz_oldrows").value)+parseInt(5)){
                  
            table.deleteRow(rowCount-1);
            rowCount--;
                                    
            //reduce the number of rows           
            f4--;
            b4--;
            allrowsoldnew4--;
            document.getElementById("uz_newrows").value=b4;
            document.getElementById("all_rows4_count").value=allrowsoldnew4;      
            document.getElementById("page1rows").innerHTML="("+allrowsoldnew4+") Rows";      
            document.getElementById("page2rows").innerHTML="("+allrowsoldnew4+") Rows";      
            document.getElementById("page3rows").innerHTML="("+allrowsoldnew4+") Rows";      
            document.getElementById("page4rows").innerHTML="("+allrowsoldnew4+") Rows";      
            document.getElementById("page5rows").innerHTML="("+allrowsoldnew4+") Rows";      
            document.getElementById("page6rows").innerHTML="("+allrowsoldnew4+") Rows";      
                     
        }
        else{
                
            //alert("Maximum deletable rows reached!!");
                
        }
                
    // }
    }catch(e) {
        alert(e);
    }
    
    
    delstep5postnat('aatoaetable');
    
}