/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function counter(enter){
    var unicode=enter.keyCode? enter.keyCode : enter.charCode;
    if(unicode==13||unicode==9){
    
        addRows();
    }
}


var myCalendar1;
function doOnLoadQtoW() {
// alert("a");
    myCalendar1 = new dhtmlXCalendarObject(["New_ARTStart2","New_ARTStart3","New_ARTStart4","New_ARTStart5","New_ARTStart6"]);
   
}




          //var existing_no_of_rows= document.getElementById("no_of_rows").value+1;
        
        var r=1;  //hplds the no of existing rows for the table to add new values
        var verifierQ=1;
        var verifier1Q=1;
       //verifier is a field that determines whether to fetch the value of i from the edit text.
       var k=0;   
       
       
       
         function resetQtoW(){
             
             
         
      r=1;  //hplds the no of existing rows for the table to add new values
         verifierQ=1;
         verifier1Q=1;
    } 
       
       
function addQtoW() {
   
    //doOnLoadQtoW();
      if(verifierQ==1){
                k =parseInt(document.getElementById("QtoWRegister_old_rows").value)+(parseInt(1));
                r=parseInt(document.getElementById("QtoWRegister").value);
                  
                          }
            
             r++;
          verifierQ++;
          verifier1Q++;  
            
 
    var table = document.getElementById("QtoWDets");
  //get the number of rows
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    
    
    
    
           
 //create element that is a checkbox assign type, name,style then apppend the child element to the cell
            var cell1 = row.insertCell(0);
            cell1.style.width = "33px";
            
            var element0 = document.createElement("label"); 
            element0.type = "label";
            //element1.name="chkbox[]";
            element0.innerHTML=""+parseInt(1+k);
            element0.style.width = "33px";
//            element0.style.textAlign="left";
            cell1.appendChild(element0);
   
    var cell2 = row.insertCell(1);
     cell2.style.width = "80px";
    var element1 = document.createElement("input");//create an input field of type text and assign names, ids and functions
    element1.type = "text";
    element1.value = "";
    element1.name = "New_haemoglobin"+r;
    element1.id = "New_haemoglobin"+r;
    element1.style.width = "80px";
//    element1.style.width = "200px";
    cell2.appendChild(element1);
    
    
    var cell3 = row.insertCell(2);
    cell3.style.width = "80px";
    var element2 = document.createElement("select");//create an input field of type text and assign names, ids and functions
    element2.type = "text";
    element2.value = "";
    element2.name = "New_RPR"+r;
    element2.id = "New_RPR"+r;
    element2.style.width = "80px";
    
    var option;
    var option1;
    var option2;
    var option3;
      option = document.createElement("option");
                      option.type="text";
                      option.value="";
                      option.innerHTML = "";
    element2.appendChild(option);
      option1 = document.createElement("option");
                      option1.type="text";
                      option1.value="P";
                      option1.innerHTML = "P";
    element2.appendChild(option1);
      option2 = document.createElement("option");
                      option2.type="text";
                      option2.value="N";
                      option2.innerHTML = "N";
    element2.appendChild(option2);
      option3 = document.createElement("option");
                      option3.type="text";
                      option3.value="ND";
                      option3.innerHTML = "ND";
    element2.appendChild(option3);
    cell3.appendChild(element2);
            
    var cell4 = row.insertCell(3);//create an input field of type text,class name, width and assign names, ids and functions
    cell4.style.width = "80px";
    var element3 = document.createElement("select");
    element3.type = "text";
    element3.name = "New_HIVInit"+r;
    element3.id = "New_HIVInit"+r;
    element3.value = "";
    element3.style.width = "80px";
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
    element3.appendChild(option);
      option1 = document.createElement("option");
                      option1.type="text";
                      option1.value="P";
                      option1.innerHTML = "P";
    element3.appendChild(option1);
      option2 = document.createElement("option");
                      option2.type="text";
                      option2.value="N";
                      option2.innerHTML = "N";
    element3.appendChild(option2);
      option4 = document.createElement("option");
                      option4.type="text";
                      option4.value="K";
                      option4.innerHTML = "K";
    element3.appendChild(option4);
      option5 = document.createElement("option");
                      option5.type="text";
                      option5.value="U";
                      option5.innerHTML = "U";
    element3.appendChild(option5);
    cell4.appendChild(element3);
    
    var cell5 = row.insertCell(4);//create an input field of type text,class name, width and assign names, ids and functions
    var element4 = document.createElement("select");
    cell5.style.width = "80px";
    element4.type = "text";
    element4.name = "New_HIVRetest"+r;
    element4.id = "New_HIVRetest"+r;
    element4.value = "";
    element4.style.width = "80px";
    option = document.createElement("option");
                      option.type="text";
                      option.value="";
                      option.innerHTML = "";
    element4.appendChild(option);
      option1 = document.createElement("option");
                      option1.type="text";
                      option1.value="P";
                      option1.innerHTML = "P";
    element4.appendChild(option1);
      option2 = document.createElement("option");
                      option2.type="text";
                      option2.value="N";
                      option2.innerHTML = "N";
    element4.appendChild(option2);
      option3 = document.createElement("option");
                      option3.type="text";
                      option3.value="NK";
                      option3.innerHTML = "NK";
    element4.appendChild(option3);
    cell5.appendChild(element4);
           
    
    var cell6= row.insertCell(5);//create an input field of type text,class name, width and assign names, ids and functions
    cell6.style.width = "80px";
    var element5 = document.createElement("input");
    element5.type = "text";
    element5.name = "New_WHO"+r;
    element5.id ="New_WHO"+r;
    element5.style.width = "80px";
  
    cell6.appendChild(element5);
    
    var cell7= row.insertCell(6);//create an input field of type text,class name, width and assign names, ids and functions
    cell7.style.width = "80px";
    var element6 = document.createElement("input");
    element6.type = "text";
    element6.name = "New_CD4"+r;
    element6.id ="New_CD4"+r;
    element6.style.width = "80px";
  
    cell7.appendChild(element6);
    
    var cell8= row.insertCell(7);//create an input field of type text,class name, width and assign names, ids and functions
    cell8.style.width = "80px";
    var element7 = document.createElement("input");
    element7.type = "text";
    element7.name = "New_ARTStart"+r;
    element7.id ="New_ARTStart"+r;
    element7.style.width = "80px";
  
    cell8.appendChild(element7);
            
   

          
    k++;

     document.getElementById("QtoWRegister").value=r;
doOnLoader();
}
 
        function deleteQtoW() {
       
          //if the addrow has not been called  
        
            
            if((verifier1Q==1)){
               k =parseInt(document.getElementById("QtoWRegister_old_rows").value)+(parseInt(1));
                c=parseInt(document.getElementById("QtoWRegister").value);
            //alert(""+document.getElementById("no_of_old_rows").value);        
                           }
                     
          verifier1Q++;
          verifierQ++;
            
            
            try {
                
            
            var table = document.getElementById("QtoWDets");
            //delete the last row 
            
             
            
            var rowCount = table.rows.length;
             //
 //alert(rowCount);
           // for(var a=0; a<rowCount; a++) {
            //    var row = table.rows[a];
              //  var chkbox = row.cells[0].childNodes[0]; 
              if(rowCount>=parseInt(document.getElementById("QtoWRegister_old_rows").value)+parseInt(6)){
                  
                    table.deleteRow(rowCount-1);
                    rowCount--;
                    
                    //reduce the number of rows           
                    k--;
                    r--;
                   document.getElementById("QtoWRegister").value=r;
                   
                     
              }else{
               // alert("Maximum deletable columns reached!");  
                  
                  
              }
            
                
           // }
            }catch(e) {
                alert(e);
            }
        }
        
//var j=0; 
//function counter(){
//        
//    alert("counter"+j);
//    if($("#dateGiven_"+j).val().length > 1){
//        addRows();  
//    }
//    j++;
//}   
    
  






