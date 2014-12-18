/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function resetGtoM(){
g=1;
verifier2=1;
verifiers2=1;
} 

//var myCalendars;
//function doOnLoadHtoL() {
// //alert("aghfgfgf");
//    myCalendars = new dhtmlXCalendarObject(["New_LMP1","New_LMP2","New_LMP3","New_LMP4","New_LMP5","New_LMP6","New_LMP7",
//        "New_EDD1","New_EDD2","New_EDD3","New_EDD4","New_EDD5","New_EDD6","New_EDD7","New_EDD8" ]);
//   
//}



          //var existing_no_of_rows= document.getElementById("no_of_rows").value+1;
     
        var g=1;  //hplds the no of existing rows for the table to add new values
        var verifier2=1;
        var verifiers2=1;
       //verifier is a field that determines whether to fetch the value of i from the edit text.
       var w=0;   
function addGtoM() {
//    doOnLoadHtoL();
      if(verifier2==1){
                w =parseInt(document.getElementById("GtoM_old_rows").value)+(parseInt(1));
                g=parseInt(document.getElementById("GtoM").value);
                  
                          }
            
             g++;
          verifier2++;
          verifiers2++;  
            

    var tables = document.getElementById("GtoMDets");
  //get the number of rows
    var rowCounts = tables.rows.length;
    var row = tables.insertRow(rowCounts);
    
//   alert("g"+g);
    
    
           
 //create element that is a checkbox assign type, name,style then apppend the child element to the cell
            var cell1 = row.insertCell(0);
            cell1.style.width = "40px";
            
            var element0 = document.createElement("label"); 
            element0.type = "label";
            //element1.name="chkbox[]";
            element0.innerHTML=""+parseInt(1+w);
            element0.style.width = "40px";
//            element0.style.textAlign="center";
            cell1.appendChild(element0);
   
    var cell2 = row.insertCell(1);
    var element1 = document.createElement("input");//create an input field of type text and assign names, ids and functions
    element1.type = "text";
    element1.value = "";
    element1.name = "New_DeliveryDate"+g;
    element1.className = "textbox";
    element1.id = "New_DeliveryDate"+g;
    element1.readOnly = true;
    element1.style.width = "100px";
//    element1.style.width = "200px";
    cell2.appendChild(element1);
    
    
    var cell3 = row.insertCell(2);
    var element2 = document.createElement("input");//create an input field of type text and assign names, ids and functions
    element2.type = "text";
    element2.value = "";
    element2.name = "New_DeliveryPlace"+g;
      element2.className = "textbox";
    element2.id = "New_DeliveryPlace"+g;
    element2.style.width = "100px";
    cell3.appendChild(element2);
            
    var cell4 = row.insertCell(3);//create an input field of type text,class name, width and assign names, ids and functions
//    var element3 = document.createElement("input");
//    element3.type = "text";
//    element3.name = "New_DeliveryMode"+g;
//    element3.id = "New_DeliveryMode"+g;
//      element3.className = "textbox";
//    element3.value = "";
//    element3.style.width = "100px";
//    cell4.appendChild(element3);
   
   var selectDelivery="<option value=\"\"></option><option value=\"Normal delivery\">Normal delivery</option><option value=\"Caesarian Section\">Caesarian Section</option><option value=\"Assisted vaginal\">Assisted vaginal</option><option value=\"Breech\">Breech</option><option value=\"Separated\">Separated</option><option value=\"SVD\">SVD</option>";
    
    cell4.innerHTML="<select style=\"width:80px;\"  name=\"New_DeliveryMode" + g + "\"  id=\"New_DeliveryMode" + g + "\"/>"+selectDelivery+"</select>";
    
    
    var cell5 = row.insertCell(4);//create an input field of type text,class name, width and assign names, ids and functions
    var element4 = document.createElement("input");
    element4.type = "text";
    element4.name = "New_BabyStatus"+g;
    element4.id = "New_BabyStatus"+g;
    element4.className = "textbox";
    element4.value = "";
    element4.style.width = "100px";
    cell5.appendChild(element4);
           
    
    var cell6= row.insertCell(5);//create an input field of type text,class name, width and assign names, ids and functions
    var element5 = document.createElement("input");
    element5.type = "text";
    element5.name = "New_Temp"+g;
    element5.className = "textbox";
    element5.id ="New_Temp"+g;
    element5.style.width = "100px";
    cell6.appendChild(element5);
    
    var cell7= row.insertCell(6);//create an input field of type text,class name, width and assign names, ids and functions
    var element6 = document.createElement("input");
    element6.type = "text";
    element6.name = "New_Pulse"+g;
    element6.className = "textbox";
    element6.id ="New_Pulse"+g;
    element6.style.width = "100px";
    cell7.appendChild(element6);
    
    var cell8= row.insertCell(7);//create an input field of type text,class name, width and assign names, ids and functions
    var element7 = document.createElement("input");
    element7.type = "text";
    element7.name = "New_BP"+g;
    element7.className = "textbox";
    element7.id ="New_BP"+g;
    element7.style.width = "100px";
    cell8.appendChild(element7);
    
   w++;

doOnLoad();


     document.getElementById("GtoM").value=g;

}
 
        function deleteGtoM() {
        //if the addrow has not been called  
        
            
            if((verifiers1==1)){
               w =parseInt(document.getElementById("GtoM_old_rows").value)+(parseInt(1));
               g=parseInt(document.getElementById("GtoM").value);
            //alert(""+document.getElementById("no_of_old_rows").value);        
                           }
                     
          verifiers1++;
          verifiers++;
            
            
            try {
                
            
            var tables = document.getElementById("GtoMDets");
            //delete the last row 
            
             
            
            var rowCounts = tables.rows.length;
//           alert("rowcount b4 "+rowCounts);
 //alert(rowCount);
           // for(var a=0; a<rowCount; a++) {
            //    var row = table.rows[a];
              //  var chkbox = row.cells[0].childNodes[0]; 
            
                    if(document.getElementById("GtoM").value>1){
                    tables.deleteRow(rowCounts-1);
                    rowCounts--;
//                    alert("rowcount"+rowCounts);
                    //reduce the number of rows           
                    w--;
                    g--;
                   document.getElementById("GtoM").value=g;
                   
                     
              }
              else{
//                alert("Maximum deletable columns reached!");  
                  
                  
              }
  
            }catch(e) {
                alert(e);
            }
        }

