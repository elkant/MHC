/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function counter(enter){
    var unicode=enter.keyCode? enter.keyCode : enter.charCode;
    if(unicode==13||unicode==9){
    
        addALtoAN();
    }
}



var myCalendar;
function doOnLoad() {
 alert("aghfgfgf");
    myCalendar = new dhtmlXCalendarObject(["New_VisitDate1","New_VisitDate2","New_VisitDate3","dpr14","dpr15","dpr21","dpr22","dpr23",
        ]);
   
}

    


          //var existing_no_of_rows= document.getElementById("no_of_rows").value+1;
        
        var a=1;  //holds the no of existing rows for the table to add new values
        var verifier=1;
        var verifier1=1;
       //verifier is a field that determines whether to fetch the value of i from the edit text.
       var i=0;   
function addALtoAN() {
    doOnLoad();
      if(verifier==1){
                i =parseInt(document.getElementById("ALtoAN_old_rows").value)+(parseInt(1));
                a=parseInt(document.getElementById("ALtoAN").value);
                  
                          }
            
             a++;
          verifier++;
          verifier1++;  
            
 
    var table = document.getElementById("ANCRegister4Table");
  //get the number of rows
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    
    
    
    
           
 //create element that is a checkbox assign type, name,style then apppend the child element to the cell
            var cell1 = row.insertCell(0);
            cell1.style.width = "40px";
            
            var element0 = document.createElement("label"); 
            element0.type = "label";
            //element1.name="chkbox[]";
            element0.innerHTML=""+parseInt
            (1+i);
            element0.style.width = "40px";
            element0.style.textAlign="center";
            cell1.appendChild(element0);
   
    var cell2 = row.insertCell(1);
    var element1 = document.createElement("select");//create an input field of type text and assign names, ids and functions
    element1.type = "text";
    element1.value = "";
    element1.name = "New_treatment"+a;
    element1.multiple = true;
    element1.id = "New_treatment"+a;
    element1.style.width = "140px";
    element1.style.height = "60px";
//    element1.style.width = "200px";
    cell2.appendChild(element1);
    
    
    var cell3 = row.insertCell(2);
    var element2 = document.createElement("select");//create an input field of type text and assign names, ids and functions
    element2.type = "text";
    element2.value = "";
    element2.name = "New_Counselled"+a;
    element2.id = "New_Counselled"+a;
    element2.style.width = "80px";
    cell3.appendChild(element2);
            
    var cell4 = row.insertCell(3);//create an input field of type text,class name, width and assign names, ids and functions
    var element3 = document.createElement("select");
    element3.type = "text";
    element3.name = "New_PartnerResults"+a;
    element3.id = "New_PartnerResults"+a;
    element3.value = "";
    element3.style.width = "80px";
    cell4.appendChild(element3);
    
    var cell5 = row.insertCell(4);//create an input field of type text,class name, width and assign names, ids and functions
    var element4 = document.createElement("input");
    element4.type = "text";
    element4.name = "New_ReferredFor"+a;
    element4.id = "New_ReferredFor"+a;
    element4.value = "";
    element4.style.width = "80px";
    cell5.appendChild(element4);
    
    var cell6 = row.insertCell(5);//create an input field of type text,class name, width and assign names, ids and functions
    var element5 = document.createElement("input");
    element5.type = "text";
    element5.name = "New_Remarks"+a;
    element5.id = "New_Remarks"+a;
    element5.value = "";
    element5.style.width = "80px";
    cell6.appendChild(element5);
    
   
            
   
  
   
 
  
    
    i++;

     document.getElementById("ALtoAN").value=a;

}
 
        function deleteALtoAN() {
       
          //if the addrow has not been called  
        
            
            if((verifier1==1)){
               i =parseInt(document.getElementById("ALtoAN_old_rows").value)+(parseInt(1));
                a=parseInt(document.getElementById("ALtoAN").value);
            //alert(""+document.getElementById("no_of_old_rows").value);        
                           }
                     
          verifier1++;
          verifier++;
            
            
            try {
                
            
            var table = document.getElementById("ANCRegister4Table");
            //delete the last row 
            
             
            
            var rowCount = table.rows.length;
           //  alert(""+rowCount);
 //alert(rowCount);
           // for(var a=0; a<rowCount; a++) {
            //    var row = table.rows[a];
              //  var chkbox = row.cells[0].childNodes[0]; 
              if(rowCount>=document.getElementById("ALtoAN_old_rows").value){
                  
                    table.deleteRow(rowCount-1);
                    rowCount--;
                    
                    //reduce the number of rows           
                    i--;
                    a--;
                   document.getElementById("ALtoAN").value=a;
                   
                     
              }
         
            }catch(e) {
                alert(e);
            }
        }








