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






          //var existing_no_of_rows= document.getElementById("no_of_rows").value+1;
        
        var e=1;  //hplds the no of existing rows for the table to add new values
        var verifier2=1;
        var verifiers2=1;
       //verifier is a field that determines whether to fetch the value of i from the edit text.
       var j=0;   
       
       
       function resetItoM(){
           //alert(e);
       
e=1;
verifier2=1;
verifiers2=1;

} 

function addANCRegister2() {
doOnLoadItoP();

alert(e);
      if(verifier2==1){
                j =parseInt(document.getElementById("ItoM_old_rows").value)+(parseInt(1));
                e=(parseInt(document.getElementById("ItoM").value)+parseInt(1));
                  
                          }
            alert(e);
             e++;
             alert(e);
          verifier2++;
          verifiers2++;  
            
 
    var table = document.getElementById("ItoMDets");
  //get the number of rows
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    
    
    
    
           
 //create element that is a checkbox assign type, name,style then apppend the child element to the cell
            var cell1 = row.insertCell(0);
            cell1.style.width = "40px";
            
            var element0 = document.createElement("label"); 
            element0.type = "label";
            //element1.name="chkbox[]";
            element0.innerHTML=""+parseInt(e);
            element0.style.width = "40px";
//            element0.style.textAlign="center";
            cell1.appendChild(element0);
   
    var cell2 = row.insertCell(1);
    var element1 = document.createElement("input");//create an input field of type text and assign names, ids and functions
    element1.type = "text";
    element1.value = "";
    element1.name = "New_parity"+e;
    element1.id = "New_parity"+e;
    element1.style.width = "80px";
//    element1.style.width = "200px";
    cell2.appendChild(element1);
    
    
    var cell3 = row.insertCell(2);
    var element2 = document.createElement("input");//create an input field of type text and assign names, ids and functions
    element2.type = "text";
    element2.value = "";
    element2.name = "New_gravidae"+e;
    element2.id = "New_gravidae"+e;
    element2.style.width = "80px";
    cell3.appendChild(element2);
            
    var cell4 = row.insertCell(3);//create an input field of type text,class name, width and assign names, ids and functions
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.name = "New_LMP"+e;
    element3.id = "New_LMP"+e;
    element3.value = "";
    element3.style.width = "80px";
    cell4.appendChild(element3);
    
    var cell5 = row.insertCell(4);//create an input field of type text,class name, width and assign names, ids and functions
    var element4 = document.createElement("input");
    element4.type = "text";
    element4.name = "New_EDD"+e;
    element4.id = "New_EDD"+e;
    element4.value = "";
    element4.style.width = "80px";
    cell5.appendChild(element4);
           
    
    var cell6= row.insertCell(5);//create an input field of type text,class name, width and assign names, ids and functions
    var element5 = document.createElement("input");
    element5.type = "text";
    element5.name = "New_Gestation"+e;
    element5.id ="New_Gestation"+e;
    element5.style.width = "80px";
  
    cell6.appendChild(element5);
    
    var cell7= row.insertCell(6);//create an input field of type text,class name, width and assign names, ids and functions
    var element6 = document.createElement("input");
    element6.type = "text";
    element6.name = "New_Weight"+e;
    element6.id ="New_Weight"+e;
    element6.style.width = "80px";
  
    cell7.appendChild(element6);
    
    var cell8= row.insertCell(7);//create an input field of type text,class name, width and assign names, ids and functions
    var element7 = document.createElement("input");
    element7.type = "text";
    element7.name = "New_BP"+e;
    element7.id ="New_BP"+e;
    element7.style.width = "80px";
  
    cell8.appendChild(element7);
    
    var cell9= row.insertCell(8);//create an input field of type text,class name, width and assign names, ids and functions
    var element8 = document.createElement("select");
    element8.type = "text";
    
    element8.name = "New_CounselledOn"+e;
    element8.id ="New_CounselledOn"+e;
    element8.multiple =true;
    element8.style.width = "150px";
    element8.style.height = "50px";
  
    cell9.appendChild(element8);
            
   
  
   
 doOnLoadItoP();
  
    
    j++;

     document.getElementById("ItoM").value=e;
    doOnLoadItoP();
}
 
        function deleteANCRegister2() {
       
          //if the addrow has not been called  
        
            
            if((verifiers2==1)){
             j =parseInt(document.getElementById("ItoM_old_rows").value)+(parseInt(1));
                e=parseInt(document.getElementById("ItoM").value);
            //alert(""+document.getElementById("no_of_old_rows").value);        
                           }
                     
          verifiers2++;
          verifier2++;
            
            
            try {
                
            
            var table = document.getElementById("ItoMDets");
            //delete the last row 
            
             
            
            var rowCount = table.rows.length;
           //  alert(""+rowCount);
 //alert(rowCount);
           // for(var a=0; a<rowCount; a++) {
            //    var row = table.rows[a];
              //  var chkbox = row.cells[0].childNodes[0]; 
              if(rowCount>=document.getElementById("ItoM_old_rows").value){
                  
                    table.deleteRow(rowCount-1);
                    rowCount--;
                    
                    //reduce the number of rows           
                    j--;
                    e--;
                   document.getElementById("ItoM").value=e;
                   
                     
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
    
 



