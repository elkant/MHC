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
        
        
function  addrowstep4mat(tableID) {
            
            
    if(verifierstep4==1){
        allrowsoldnew4=parseInt(document.getElementById("all_rows4_count").value);
        f4=parseInt(document.getElementById("vab_oldrows").value)+(parseInt(1));
        b4=parseInt(document.getElementById("vab_newrows").value);
    //alert(""+document.getElementById("no_of_old_rows").value);        
    }
            
    b4++;
    verifierstep4++;
    verifierstep41++;  
    allrowsoldnew4++; 
            
    //add a row if only there exists data of that 
            
    //if(document.getElementById("blank_warning").innerHTML==""){
            
 var  babystatus_options = "<option value=\"NA\"></option><option title=\"Live Birth\" value=\"'Live Birth'\">LB</option><option title=\"Fresh Still Birth\" value=\"Fresh Still Birth\">FSB</option><option title=\"Macerated Still Birth\" value=\"Macerated Still Birth\">MSB</option>";
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
    
    
    col1.innerHTML=""+allrowsoldnew4+"";
    col2.innerHTML="<Select id=\"vab_new_sex"+b4+"\" style=\"width:35px;\" name=\"vab_new_sex"+b4+"\"><option value=\"\"></option><option value=\"M\">M</option><option value=\"F\">F</option> </Select>";

    col3.innerHTML="<input type=\"text\" class=\"textbox\" placeholder=\"in grams\" id=\"vab_new_weight"+b4+"\" name=\"vab_new_weight"+b4+"\" style=\"padding:1px; width:80px;\">";
    col4.innerHTML="<Select  name=\"vab_new_livebirth"+b4+"\" id=\"vab_new_livebirth"+b4+"\"  style=\"width:80px;\"/>"+babystatus_options+"</select>";
    col5.innerHTML="<input type=\"text\" name=\"new_apgarscore"+b4+"\" id=\"new_apgarscore"+b4+"\" class=\"textbox\" style=\"width:80px;\">";
    col6.innerHTML="<Select id=\"vab_new_vdrl"+b4+"\" style=\"width:35px;\" name=\"vab_new_vdrl"+b4+"\"><option value=\"\"></option><option value=\"P\">P</option><option value=\"N\">N</option> </Select>";
    col7.innerHTML="<Select id=\"vab_new_anc"+b4+"\" style=\"width:39px;\" name=\"vab_new_anc"+b4+"\"><option value=\"\"></option><option value=\"P\">P</option><option value=\"N\">N</option><option value=\"KP\">KP</option><option value=\"U\">U</option></Select>";
    col8.innerHTML="<Select id=\"vab_new_mat"+b4+"\" style=\"width:35px;\" name=\"vab_new_mat"+b4+"\"><option value=\"\"></option><option value=\"P\">P</option><option value=\"N\">N</option><option value=\"U\">U</option> </Select>";
    
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
    document.getElementById("vab_newrows").value=b4;
    document.getElementById("all_rows4_count").value=allrowsoldnew4;
     document.getElementById("page1rows").innerHTML="("+allrowsoldnew4+") Rows";      
     document.getElementById("page2rows").innerHTML="("+allrowsoldnew4+") Rows";      
     document.getElementById("page3rows").innerHTML="("+allrowsoldnew4+") Rows";      
     document.getElementById("page4rows").innerHTML="("+allrowsoldnew4+") Rows";      
    document.getElementById("page5rows").innerHTML="("+allrowsoldnew4+") Rows";      
    document.getElementById("page6rows").innerHTML="("+allrowsoldnew4+") Rows";    
      
      addrowstep5mat('actoaitable');
//alert(i);
           
}
 
function delrowstep4mat(tableID) {
    //if the addrow has not been called  
        
            
    if((verifierstep41==1)){
        allrowsoldnew4=parseInt(document.getElementById("all_rows4_count").value);
        f4 =parseInt(document.getElementById("vab_oldrows").value)+(parseInt(1));
        b4=parseInt(document.getElementById("vab_newrows").value)+(parseInt(1));
    //alert(""+document.getElementById("no_of_old_rows").value);        
    }
                     
    verifierstep41++;
    verifierstep4++;
            
            
    try {
                
            
        var table = document.getElementById(tableID);
        //delete the last row 
            
             
            
        var rowCount = table.rows.length;
       
        //delete ntil you have one row
        if(rowCount>parseInt(document.getElementById("vab_oldrows").value)+parseInt(4)){
                  
            table.deleteRow(rowCount-1);
            rowCount--;
                    
                    
            //reduce the number of rows           
            f4--;
            b4--;
            allrowsoldnew4--;
            document.getElementById("vab_newrows").value=b4;
            document.getElementById("all_rows4_count").value=allrowsoldnew4;      
            document.getElementById("page1rows").innerHTML="("+allrowsoldnew4+") Rows";      
            document.getElementById("page2rows").innerHTML="("+allrowsoldnew4+") Rows";      
            document.getElementById("page3rows").innerHTML="("+allrowsoldnew4+") Rows";      
            document.getElementById("page4rows").innerHTML="("+allrowsoldnew4+") Rows";      
            document.getElementById("page5rows").innerHTML="("+allrowsoldnew4+") Rows";      
            document.getElementById("page6rows").innerHTML="("+allrowsoldnew4+") Rows";      
                     
        }
        else{
                
            alert("Maximum deletable rows reached!!");
                
        }
                
    // }
    }catch(e) {
        alert(e);
    }
    
    
    delrowstep5mat('actoaitable');
    
}