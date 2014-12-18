/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


       
        //var existing_no_of_rows= document.getElementById("no_of_rows").value+1;
        
        var a=1;  //hplds the no of existing rows for the table to add new values
        var verifier=1;
        var verifier1=1;
       //verifier is a field that determines whether to fetch the value of i from the edit text.
        var i=0;   
        function ifenter(e){
var unicode=e.keyCode? e.keyCode : e.charCode;
if(unicode==13||unicode==9){
    
    addRow('table_prev_preg');
}
}
        
        function reseta(){
             a=1;
            verifier=1;
            verifier1=1;
        } 
        
        
        function addRow(tableID) {
            
//            alert(document.getElementById("no_of_rows").value);
//            alert("A:"+a);
            if(verifier==1){
                i =parseInt(document.getElementById("no_of_old_rows").value)+(parseInt(1));
                a=(parseInt(document.getElementById("no_of_rows").value));
            //alert(""+document.getElementById("no_of_old_rows").value);        
                          }
           
             //a++;
          verifier++;
          verifier1++;  
            
            
            //add a row if only there exists data of that 
            
            if(document.getElementById("blank_warning").innerHTML==""&&((parseInt(a)+parseInt(i))<11)){
            
            var table = document.getElementById(tableID);
            //get the number of rows
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
 //create element that is a checkbox assign type, name,style then apppend the child element to the cell
//            var cell1 = row.insertCell(0);
//            cell1.style.width = "12px";
//            
            var element1 = document.createElement("label"); 
            element1.type = "label";
            //element1.name="chkbox[]";
            element1.innerHTML=""+parseInt(1+i);
            element1.style.width = "56px";
            //element1.style.textAlign="left";
            
           
           // cell1.appendChild(element1);
            
           // cell1.appendText(""+i);
 
//            var cell2 = row.insertCell(1);
//            cell2.innerHTML = rowCount + 1;
 //cell two
            var cell2 = row.insertCell(0);
            var element2 = document.createElement("input");
            element2.type = "hidden";
            element2.name = "new_preg_0rder"+(parseInt(a)+(parseInt(1)));
            element2.id = "new_pregorder"+(parseInt(a)+(parseInt(1)));
            element2.value=""+parseInt(1+i);
            element2.onchange=function(){addRow('dataTable');} 
            //element2.style.width = "62px";
            // element.style.align = "left";
            cell2.appendChild(element1);
            cell2.appendChild(element2);
            
            
          //year  
            var cell3 = row.insertCell(1);
            var element3 = document.createElement("input");
            element3.type = "text";
            element3.name = "new_year"+(parseInt(a)+(parseInt(1)));
            element3.id = "new_year"+(parseInt(a)+(parseInt(1)));
            element3.placeholder="YYYY";
           // element3.style.width = "62px";
            
            cell3.appendChild(element3);
            
            
            
           //place of delivery 
            var cell4 = row.insertCell(2);
            var element4 = document.createElement("input");
            element4.type = "text";
            element4.name = "new_place_of_delivery"+(parseInt(a)+(parseInt(1)));
            element4.id = "new_place_of_delivery"+(parseInt(a)+(parseInt(1)));
            // element4.style.align = "left";
            cell4.appendChild(element4);
            
            //delivery type
            var cell5 = row.insertCell(3);
            var element5 = document.createElement("select");
            element5.type = "text";
            element5.name = "new_Type_of_delivery"+(parseInt(a)+(parseInt(1)));
            element5.id = "new_Type_of_delivery"+(parseInt(a)+(parseInt(1)));
            element5.onkeypress= function ifenter(e){
                       var unicode=e.keyCode? e.keyCode : e.charCode;
                                                     if(unicode==13||unicode==9){
    
                                            addRow('table_prev_preg');
                                                  }//end of if
                                                  }//end of function
            //create cells
            
            
    var option;
    var option1;
    var option2;
    var option3;
    var option4;
    var option5;
    
      option = document.createElement("option");
                      option.type="text";
                      option.value="";
                      option.innerHTML = "";
    element5.appendChild(option);
      option1 = document.createElement("option");
                      option1.type="text";
                      option1.value="1";
                      option1.innerHTML = "Normal delivery";
    element5.appendChild(option1);
      option2 = document.createElement("option");
                      option2.type="text";
                      option2.value="2";
                      option2.innerHTML = "Cesarian delivery";
    element5.appendChild(option2);
   
          option3 = document.createElement("option");
                      option3.type="text";
                      option3.value="3";
                      option3.innerHTML = "Assisted Vaginal";
    element5.appendChild(option3);
            
            
          option4 = document.createElement("option");
                      option4.type="text";
                      option4.value="4";
                      option4.innerHTML = "Breech";
    element5.appendChild(option4);
            
            
          option5 = document.createElement("option");
                      option5.type="text";
                      option5.value="5";
                      option5.innerHTML = "Separated";
    element5.appendChild(option5);
            
            
            
            
            cell5.appendChild(element5);
           
           
 
  var frm = document.getElementById("frm");
//var as = document.getElementById("h");    
         i++;  
        a++;
      document.getElementById("no_of_rows").value=a;
      
      //alert(i);
            }//end of if
        }
 
        function deleteRow(tableID) {
          //if the addrow has not been called  
        
            
            if((verifier1==1)){
               i =parseInt(document.getElementById("no_of_old_rows").value)+(parseInt(1));
                a=parseInt(document.getElementById("no_of_rows").value);
            //alert(""+document.getElementById("no_of_old_rows").value);        
                           }
                     
          verifier1++;
          verifier++;
            
            
            try {
                
            
            var table = document.getElementById(tableID);
            //delete the last row 
            
             
            
            var rowCount = table.rows.length;
           //  alert(""+rowCount);
 //alert(rowCount);
           // for(var a=0; a<rowCount; a++) {
            //    var row = table.rows[a];
              //  var chkbox = row.cells[0].childNodes[0]; 
              if(rowCount>(parseInt(document.getElementById("no_of_old_rows").value)+parseInt(1))&&document.getElementById("blank_warning").innerHTML==""){
                  
                    table.deleteRow(rowCount-1);
                    rowCount--;
                    
                    //reduce the number of rows           
                    i--;
                    a--;
                     document.getElementById("no_of_rows").value=a;
                   
                     
              }
            
                
           // }
            }catch(e) {
                alert(e);
            }
        }
        
  