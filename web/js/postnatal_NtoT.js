/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var e=1;  //hplds the no of existing rows for the table to add new values
        var verif2=1;
        var verifiers2=1;
       var kay=1;   
function resetNtoT(){
e=1;
verif2=1;
verifiers2=1;
kay=1;
} 

//var myCalendars;
//function doOnLoadHtoL() {
// //alert("aghfgfgf");
//    myCalendars = new dhtmlXCalendarObject(["New_LMP1","New_LMP2","New_LMP3","New_LMP4","New_LMP5","New_LMP6","New_LMP7",
//        "New_EDD1","New_EDD2","New_EDD3","New_EDD4","New_EDD5","New_EDD6","New_EDD7","New_EDD8" ]);
//   
//}



          //var existing_no_of_rows= document.getElementById("no_of_rows").value+1;
        
        
       //verifier is a field that determines whether to fetch the value of i from the edit text.
        
function addNtoT() {
//    doOnLoadHtoL();
              if(verif2==1){
                kay =parseInt(document.getElementById("NtoT_old_rows").value);
                e=(parseInt(document.getElementById("NtoT").value)+parseInt(1));
                  
                              }
               // alert(e);
             e++;
          verifier2++;
          verifiers2++;  
//            alert(kay);
            //alert(e);
 
    var table1 = document.getElementById("NtoTDets");
  //get the number of rows
    var rowCount1 = table1.rows.length;
    var row = table1.insertRow(rowCount1);
    
    
    
    
           
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
//    var element1 = document.createElement("input");//create an input field of type text and assign names, ids and functions
//    element1.type = "text";
//    element1.value = "";
//    element1.name = "New_Parlor"+e;
//    element1.className = "textbox";
//    element1.id = "New_Parlor"+e;
//    element1.style.width = "125px";
////    element1.style.width = "200px";
//    cell2.appendChild(element1);
    cell2.innerHTML="<select  id='New_Parlor"+e+"'  style='width:100px;' name='New_Parlor"+e+"' >\n\
    <option value=''></option>\n\
    <option value='1=Mild'>1=Mild</option>\n\
    <option value='2=Moderate'>2=Moderate</option>\n\
    <option value='3=Severe'>3=Severe</option>\n\
</select>"
    
    var cell3 = row.insertCell(2);
//    var element2 = document.createElement("input");//create an input field of type text and assign names, ids and functions
//    element2.type = "text";
//    element2.value = "";
//    element2.name = "New_Breast"+e;
//      element2.className = "textbox";
//    element2.id = "New_Breast"+e;
//    element2.style.width = "125px";
//    cell3.appendChild(element2);
    
    
     cell3.innerHTML="<select  id='New_Breast"+e+"'  style='width:100px;' name='New_Breast"+e+"' >\n\
    <option value=''></option>\n\
    <option value='1=Normal'>1=Normal</option>\n\
    <option value='2=Cracked Nipple'>2=Cracked Nipple</option>\n\
    <option value='3=Engorged'>3=Engorged</option>\n\
    <option value='4=Mastistis'>4=Mastistis</option>\n\
</select>"
            
    var cell4 = row.insertCell(3);//create an input field of type text,class name, width and assign names, ids and functions
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.name = "New_Uterus"+e;
    element3.id = "New_Uterus"+e;
      element3.className = "textbox";
    element3.value = "";
    element3.style.width = "125px";
    cell4.appendChild(element3);
    
    var cell5 = row.insertCell(4);//create an input field of type text,class name, width and assign names, ids and functions
    var element4 = document.createElement("input");
    element4.type = "text";
    element4.name = "New_PPH"+e;
    element4.id = "New_PPH"+e;
    element4.className = "textbox";
    element4.value = "";
    element4.style.width = "100px";
    cell5.appendChild(element4);
           
    
    var cell6= row.insertCell(5);//create an input field of type text,class name, width and assign names, ids and functions
//    var element5 = document.createElement("input");
//    element5.type = "text";
//    element5.name = "New_CSection"+e;
//    element5.className = "textbox";
//    element5.id ="New_CSection"+e;
//    element5.style.width = "125px";
//    cell6.appendChild(element5);
    cell6.innerHTML="<select  id='New_CSection"+e+"'  style='width:100px;' name='New_CSection"+e+"' >\n\
    <option value=''></option>\n\
    <option value='1=Bleeding'>1=Bleeding</option>\n\
    <option value='2=Normal'>2=Normal</option>\n\
    <option value='3=Infected'>3=Infected</option></select>"
    
    var cell7= row.insertCell(6);//create an input field of type text,class name, width and assign names, ids and functions
//    var element6 = document.createElement("input");
//    element6.type = "text";
//    element6.name = "New_Lochial"+e;
//    element6.className = "textbox";
//    element6.id ="New_Lochial"+e;
//    element6.style.width = "125px";
//    cell7.appendChild(element6);
     cell7.innerHTML="<select  id='New_Lochial"+e+"'  style='width:100px;' name='New_Lochial"+e+"' >\n\
    <option value=''></option>\n\
    <option value='1=Normal'>1=Normal</option>\n\
    <option value='2=Foul Smelling Excessive'>2=Foul Smelling Excessive</option>\n\
 </select>"
    
    
    var cell8= row.insertCell(7);//create an input field of type text,class name, width and assign names, ids and functions
//    var element7 = document.createElement("input");
//    element7.type = "text";
//    element7.name = "New_Episiotomy"+e;
//    element7.className = "textbox";
//    element7.id ="New_Episiotomy"+e;
//    element7.style.width = "125px";
//    cell8.appendChild(element7);
     cell8.innerHTML="<select  id='New_Episiotomy"+e+"'  style='width:100px;' name='New_Episiotomy"+e+"' >\n\
    <option value=''></option>\n\
    <option value='1=Repaired'>1=Repaired</option>\n\
    <option value='2=Gaping'>2=Gaping</option>\n\
    <option value='3=Infected'>3=Infected</option>\n\
    <option value='4=Healed'>4=Healed</option>\n\
</select>"
   kay++;

     document.getElementById("NtoT").value=e;
     
}
 
        function deleteNtoT() {
        //if the addrow has not been called  
        
            
            if((verifiers1==1)){
               kay =parseInt(document.getElementById("NtoT_old_rows").value)+(parseInt(1));
               e=parseInt(document.getElementById("NtoT").value);
            //alert(""+document.getElementById("no_of_old_rows").value);        
                           }
                     
          verifiers1++;
          verifiers++;
            
            
            try {
                
            
            var table1 = document.getElementById("NtoTDets");
            //delete the last row 
            
             
            
            var rowCount1 = table1.rows.length;
           //  alert(""+rowCount);
 //alert(rowCount);
           // for(var a=0; a<rowCount; a++) {
            //    var row = table.rows[a];
              //  var chkbox = row.cells[0].childNodes[0]; 
              
//              alert(document.getElementById("NtoT").value);
              if(document.getElementById("NtoT").value>1){
                
                    table1.deleteRow(rowCount1-1);
                    rowCount1--;
                    
                    //reduce the number of rows           
                    kay--;
                    e--;
                   document.getElementById("NtoT").value=e;
                 
                     
              }
              else{
//                alert("Maximum deletable columns reached!");  
                  
                  
              }
  
            }catch(e) {
                alert("error"+e);
            }
        }

