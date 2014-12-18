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
        
        
function  addrowstep6mat(tableID) {
            
            
    if(verifierstep6==1){
        allrowsoldnew6=parseInt(document.getElementById("all_rows6_count").value);
        f6=parseInt(document.getElementById("ajan_oldrows").value)+(parseInt(1));
        b6=parseInt(document.getElementById("ajan_newrows").value);
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
   
    
       var anc_reg_options="<option value=\"\"></option><option value=\"sdNVP\">sdNVP</option><option value=\"AZT\">AZT</option><option value=\"T\">T</option><option value=\"P\">P</option>";
       
       var babystatus="<option></option><option value=\"Alive\">Alive</option><option value=\"Dead\">Dead</option>";
       
    col1.innerHTML=""+allrowsoldnew6+"";
    col2.innerHTML="<input type=\"text\" class=\"textbox\" placeholder=\"enter name\" name=\"ajan_new_conductor"+b6+"\" id=\"ajan_new_conductor"+b6+"\" style=\"padding:1px; \">";
        col3.innerHTML="<input type=\"text\" class=\"textbox\" placeholder=\"\" name=\"ajan_new_birthno"+b6+"\" id=\"ajan_new_birthno"+b6+"\" style=\"padding:1px; width:80px;\">";
            col4.innerHTML="<input type=\"text\" name=\"ajan_new_date"+b6+"\" id=\"ajan_new_date"+b6+"\" class=\"textbox\" style=\"width:80px;\" >";
                col5.innerHTML="<select style=\"width:50px;\" name=\"new_status_of_baby"+b6+"\"  id=\"new_status_of_baby"+b6+"\">"+babystatus+"</select>";
                    col6.innerHTML="<textarea cols=\"15\" name=\"ajan_new_comments"+b6+"\" id=\"ajan_new_comments"+b6+"\" rows=\"1\"></textarea>";
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
    document.getElementById("ajan_newrows").value=b6;
    document.getElementById("all_rows6_count").value=allrowsoldnew6;
      loadcalender();
//alert(i);
           
}
 
function delrowstep6mat(tableID) {
    //if the addrow has not been called  
        
            
    if((verifierstep61==1)){
        allrowsoldnew6=parseInt(document.getElementById("all_rows6_count").value);
        f6 =parseInt(document.getElementById("ajan_oldrows").value)+(parseInt(1));
        b6=parseInt(document.getElementById("ajan_newrows").value)+(parseInt(1));
    //alert(""+document.getElementById("no_of_old_rows").value);        
    }
                     
    verifierstep61++;
    verifierstep6++;
            
            
    try {
                
            
        var table = document.getElementById(tableID);
        //delete the last row 
            
             
            
        var rowCount = table.rows.length;
       
        //delete ntil you have one row
        if(rowCount>parseInt(document.getElementById("ajan_oldrows").value)+parseInt(4)){
                  
            table.deleteRow(rowCount-1);
            rowCount--;
                    
                    
            //reduce the number of rows           
            f6--;
            b6--;
            allrowsoldnew6--;
            document.getElementById("ajan_newrows").value=b6;
            document.getElementById("all_rows6_count").value=allrowsoldnew6;      
                     
        }
        else{
                
           // alert("Maximum deletable rows reached!!");
                
        }
                
    // }
    }catch(e) {
        alert(e);
    }
}