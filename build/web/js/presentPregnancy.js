/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 function counts(enter){
var unicode=enter.keyCode? enter.keyCode : enter.charCode;
if(unicode==13||unicode==9){
    
   addPregnancyRow();
}
}
 
var myCalendar;
function doOnLoad1() {

    myCalendar = new dhtmlXCalendarObject(["new_date2","new_date3","new_date4","new_date5","new_date6",
        "new_date6","new_date7","new_date8","new_NextVisit1","new_NextVisit2","new_NextVisit3","new_NextVisit4","new_NextVisit5","new_NextVisit6","new_NextVisit7","new_NextVisit8","new_NextVisit9"
        ]);
   
}
 
 function resetPresentPreg(){
     
        var w=1;  //hplds the no of existing rows for the table to add new values
        var verifier3=1;
        var verifiers3=1;
     
 }
 //var existing_no_of_rows= document.getElementById("no_of_rows").value+1;
        
        var w=1;  //hplds the no of existing rows for the table to add new values
        var verifier3=1;
        var verifiers3=1;
       //verifier is a field that determines whether to fetch the value of i from the edit text.
       var m=0;  
        function addPregnancyRow() {
        
   if(verifier3==1){
                m =parseInt(document.getElementById("pregnancy_old_rows").value)+(parseInt(1));
                w=parseInt(document.getElementById("pregnancy").value);
            //alert(""+document.getElementById("no_of_old_rows").value);        
                          }
            
             w++;
          verifier3++;
          verifiers3++;  
            
            
            //add a row if only there exists data of that 
            
            //if(document.getElementById("blank_warning").innerHTML==""){
            var table = document.getElementById("currPreg");
 
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
 //create element that is a checkbox assign type, name,style then apppend the child element to the cell
//          var cell1 = row.insertCell(0);
//            cell1.style.width = "40px";
            
            var element0 = document.createElement("label"); 
            element0.type = "label";
            //element1.name="chkbox[]";
            element0.innerHTML=""+parseInt(1+m);
            element0.style.width = "40px";
//            element1.style.textAlign="left";
            
           
//            cell1.appendChild(element1);
 
            var cell2 = row.insertCell(0);
            var element1 = document.createElement("input");//create an input field of type text and assign names, ids and functions
            element1.type = "hidden";
            element1.name = "new_VisitNo"+w;
            element1.id = "new_VisitNo"+w;
           element1.value=""+parseInt(1+m);
            element1.style.width = "80px";
            cell2.appendChild(element0);
            cell2.appendChild(element1);
            
            var cell3 = row.insertCell(1);//create an input field of type text,class name, width and assign names, ids and functions
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name = "new_date"+w;
            element2.id = "new_date"+w;
//            element2.className = "datepicker";
            element2.style.width = "120px";
//            element2.onclick= function dates(){
//               
//                      $(document).ready(function(){
//     $( ".datepicker").datepicker({
//                                dateFormat: "dd/mm/yy",
//                                changeMonth: true,
//                                changeYear: true
//                               
//                           }); 
//                           });}
                      
            cell3.appendChild(element2);
            
            var cell4 = row.insertCell(2);//create an input field of type text,class name, width and assign names, ids and functions
            var element3 = document.createElement("input");
            element3.type = "text";
            element3.name = "new_weight"+w;
            element3.id = "new_weight"+w;
            element3.style.width = "80px";
            element3.onblur =  function checkNums()  
   { 
     
      var x= document.getElementById("new_weight"+w);
        
 var numbers = /^\d+(?:\.\d+)?$/;  
      if(x.value!="" && x.value!=null){ 
      if(x.value.match(numbers))  
      {   
      return true; 
     
      }  
      else  
      {  
      alert('Please input numeric characters only');  
       return false; 
    
      }  
   
 }

   }   ;
            cell4.appendChild(element3);
            
            var cell5 = row.insertCell(3);//create an input field of type text,class name, width and assign names, ids and functions
            var element4 = document.createElement("input");
            element4.type = "text";
            element4.name = "new_NextVisit"+w;
            element4.id = "new_NextVisit"+w;
           
            element4.onkeypress=function counter(e){  
                
var unicode=e.keyCode? e.keyCode : e.charCode;
if(unicode==13||unicode==9){
    
   addPregnancyRow();
}
}
            
             
//        if($("#NextVisits_"+j).val().length > 1){
//          addPregnancyRow(); 
////          alert(j);
//        }
//         
//         j++;
//        
//        }   
    
            element4.style.width = "120px";
//            element4.className = "datepickers";
//            element4.onclick= function date(){
//               
//                      $(document).ready(function(){
//     $( ".datepickers").datepicker({
//                                dateFormat: "dd/mm/yy",
//                                changeMonth: true,
//                                changeYear: true
//                               
//                           }); 
//                           });}
            cell5.appendChild(element4);
           
           
// 
//  var frm = document.getElementById("frm");
//            var as = document.getElementById("h");    
         m++;
         
//      frm.pregnancy.value=i;
        document.getElementById("pregnancy").value=w;
//      alert(i);
 doOnLoad1();
        }
        //}
        function deletePregnancyRow() {
       
          //if the addrow has not been called  
        
            
            if((verifiers3==1)){
               m =parseInt(document.getElementById("pregnancy_old_rows").value)+(parseInt(1));
                w=parseInt(document.getElementById("pregnancy").value);
            //alert(""+document.getElementById("no_of_old_rows").value);        
                           }
                     
          verifiers3++;
          verifier3++;
            
            
            try {
                
            
            var table = document.getElementById("currPreg");
            //delete the last row 
            
             
            
            var rowCount = table.rows.length;
           //  alert(""+rowCount);
 //alert(rowCount);
           // for(var a=0; a<rowCount; a++) {
            //    var row = table.rows[a];
              //  var chkbox = row.cells[0].childNodes[0]; 
              if(rowCount>=document.getElementById("pregnancy_old_rows").value){
                  
                    table.deleteRow(rowCount-1);
                    rowCount--;
                    
                    //reduce the number of rows           
                    m--;
                    w--;
                   document.getElementById("pregnancy").value=w;
                   
                     
              }
            
                
           // }
            }catch(e) {
                alert(e);
            }
        }
        
    
//      var j=0; 
//     function counts(){
//        
////         alert(j);
//        if($("#NextVisits_"+j).val().length > 1){
//          addPregnancyRow();  
//        }
//         j++;
//        }   
     
       

