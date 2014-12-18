/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 var eee=1; 
function resetHtoL(){
eee=1;
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
        
        //hplds the no of existing rows for the table to add new values
        var verifier2=1;
        var verifiers2=1;
       //verifier is a field that determines whether to fetch the value of i from the edit text.
       var jee=0;   
function addHtoL() {
//    doOnLoadHtoL();
      if(verifier2==1){
                jee =parseInt(document.getElementById("HtoL_old_rows").value)+(parseInt(1));
                eee=parseInt(document.getElementById("HtoL").value);
                  
                          }
            
             eee++;
          verifier2++;
          verifiers2++;  
            
 
    var table = document.getElementById("HtoLDets");
  //get the number of rows
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    
    
    
    
           
 //create element that is a checkbox assign type, name,style then apppend the child element to the cell
            var cell1 = row.insertCell(0);
            cell1.style.width = "40px";
            
            var element0 = document.createElement("label"); 
            element0.type = "label";
            //element1.name="chkbox[]";
            element0.innerHTML=""+parseInt(1+jee);
            element0.style.width = "40px";
//            element0.style.textAlign="center";
            cell1.appendChild(element0);
   
    var cell2 = row.insertCell(1);
    var element1 = document.createElement("input");//create an input field of type text and assign names, ids and functions
    element1.type = "text";
    element1.value = "";
    element1.name = "New_Parity"+eee;
    element1.className = "textbox";
    element1.id = "New_Parity"+eee;
    element1.style.width = "80px";
//    element1.style.width = "200px";
    cell2.appendChild(element1);
    
    
    var cell3 = row.insertCell(2);
    var element2 = document.createElement("input");//create an input field of type text and assign names, ids and functions
    element2.type = "text";
    element2.value = "";
    element2.name = "New_Gravida"+eee;
      element2.className = "textbox";
    element2.id = "New_Gravida"+eee;
    element2.style.width = "80px";
    cell3.appendChild(element2);
            
    var cell4 = row.insertCell(3);//create an input field of type text,class name, width and assign names, ids and functions
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.name = "New_LMP"+eee;
    element3.id = "New_LMP"+eee;
    element3.readOnly = true;
      element3.className = "textbox";
    element3.value = "";
    element3.style.width = "80px";
    cell4.appendChild(element3);
    
    var cell5 = row.insertCell(4);//create an input field of type text,class name, width and assign names, ids and functions
    var element4 = document.createElement("input");
    element4.type = "text";
    element4.name = "New_EDD"+eee;
    element4.id = "New_EDD"+eee;
    element4.readOnly= true;
    
    element4.className = "textbox";
    element4.value = "";
    element4.style.width = "80px";
    cell5.appendChild(element4);
           
    
    var cell6= row.insertCell(5);//create an input field of type text,class name, width and assign names, ids and functions
   
    cell6.innerHTML="<input type=\"text\" list=\"conditions\" style=\"width:120px;\" class=\"textbox\" name=\"New_Diagnosis" + eee + "\"  id=\"New_Diagnosis" + eee + "\"/><datalist id=\"conditions\"><option value=\"APH\"><option value=\"PPH\"><option value=\"ECLAMPSIA\"><option value=\"RUPTURED UTERUS\"><option value=\"OBSTRUCTED LABOUR\"><option value=\"SEPSIS\"><option value=\"LABOUR PAINS\"></datalist>";
   jee++;

     document.getElementById("HtoL").value=eee;

}
 
        function deleteHtoL() {
        //if the addrow has not been called  
        
            
            if((verifiers1==1)){
               jee =parseInt(document.getElementById("HtoL_old_rows").value)+(parseInt(1));
               eee=parseInt(document.getElementById("HtoL").value);
            //alert(""+document.getElementById("no_of_old_rows").value);        
                           }
                     
          verifiers1++;
          verifiers++;
            
            
            try {
                
            
            var table = document.getElementById("HtoLDets");
            //delete the last row 
            
             
            
            var rowCount = table.rows.length;
           //  alert(""+rowCount);
 //alert(rowCount);
           // for(var a=0; a<rowCount; a++) {
            //    var row = table.rows[a];
              //  var chkbox = row.cells[0].childNodes[0]; 
              if(rowCount>=parseInt(document.getElementById("HtoL_old_rows").value)+parseInt(4)){
                  
                    table.deleteRow(rowCount-1);
                    rowCount--;
                    
                    //reduce the number of rows           
                    jee--;
                    eee--;
                   document.getElementById("HtoL").value=eee;
                   
                     
              }
              else{
                //alert("Maximum deletable columns reached!");  
                  
                  
              }
  
            }catch(e) {
                alert(e);
            }
        }
