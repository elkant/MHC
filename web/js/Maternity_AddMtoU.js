/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 var ee=1;
 var ba
 //hplds the no of existing rows for the table to add new values
        var verifierMtoU=1;
        var verifiersMtoU=1;
       //verifier is a field that determines whether to fetch the value of i from the edit text.
       var ja=0; 
function resetMtoU(){
f=1;
verifierMtoU=1;
verifiersMtoU=1;
ee=1;
ja=0;
} 

var myCalendars;
function doOnLoadMtoU() {
 
    myCalendars = new dhtmlXCalendarObject(["New_DeliveryDate1","New_DeliveryDate2","New_DeliveryDate3","New_DeliveryDate4","New_DeliveryDate5","New_DeliveryDate6"]);
   
}

    function timer(){
                        $('#New_DeliveryTime1').timepicker({ 'step': 15 });
			$('#New_DeliveryTime2').timepicker({ 'step': 15 });
			$('#New_DeliveryTime3').timepicker({ 'step': 15 });
			$('#New_DeliveryTime4').timepicker({ 'step': 15 });
			$('#New_DeliveryTime5').timepicker({ 'step': 15 });
			$('#New_DeliveryTime6').timepicker({ 'step': 15 });
			$('#New_DeliveryTime7').timepicker({ 'step': 15 });
			$('#New_DeliveryTime8').timepicker({ 'step': 15 });
		 
		
}




          //var existing_no_of_rows= document.getElementById("no_of_rows").value+1;
        
         
function addMtoU(){
    
      if(verifierMtoU==1){
                ja =parseInt(document.getElementById("MtoU_old_rows").value)+(parseInt(document.getElementById("MtoU").value));
                ee=parseInt(document.getElementById("MtoU").value);
                  
                          }
            
             ee++;
          verifierMtoU++;
          verifiersMtoU++;  
            
 
    var table = document.getElementById("MtoUDets");
  //get the number of rows
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    
    
    
    
           
 //create element that is a checkbox assign type, name,style then apppend the child element to the cell
            var cell1 = row.insertCell(0);
            cell1.style.width = "40px";
            
            var element0 = document.createElement("label"); 
            element0.type = "label";
            //element1.name="chkbox[]";
            element0.innerHTML=""+parseInt(ja+1);
            element0.style.width = "40px";
//            element0.style.textAlign="center";
            cell1.appendChild(element0);
   
    var cell2 = row.insertCell(1);
    var element1 = document.createElement("input");//create an input field of type text and assign names, ids and functions
    element1.type = "text";
    element1.value = "";
    element1.name = "New_LabourDuration"+ee;
    element1.id = "New_LabourDuration"+ee;
    element1.className = "textbox";
    element1.style.width = "80px";
//    element1.style.width = "200px";
    cell2.appendChild(element1);
    
    
    var cell3 = row.insertCell(2);
    var element2 = document.createElement("input");//create an input field of type text and assign names, ids and functions
    element2.type = "text";
    element2.value = "";
    element2.name = "New_DeliveryDate"+ee;
    element2.id = "New_DeliveryDate"+ee;
      element2.className = "textbox";
    element2.style.width = "80px";
    cell3.appendChild(element2);
            
    var cell4 = row.insertCell(3);//create an input field of type text,class name, width and assign names, ids and functions
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.name = "New_DeliveryTime"+ee;
    element3.id = "New_DeliveryTime"+ee;
      element3.className = "textbox";
    element3.value = "";
    element3.style.width = "80px";
    cell4.appendChild(element3);
    
    var cell5 = row.insertCell(4);//create an input field of type text,class name, width and assign names, ids and functions
    var element4 = document.createElement("input");
    element4.type = "text";
    element4.name = "New_GestationAtBirth"+ee;
    element4.id = "New_GestationAtBirth"+ee;
      element4.className = "textbox";
    element4.value = "";
    element4.style.width = "80px";
    cell5.appendChild(element4);
           
    
    var cell6= row.insertCell(5);//create an input field of type text,class name, width and assign names, ids and functions
    var element5 = document.createElement("select");
    element5.type = "text";
    element5.name = "New_DeliveryMode"+ee;
    element5.id ="New_DeliveryMode"+ee;
      element5.className = "textbox";
    element5.style.width = "80px";
    var option;
    var option1;var option2;    var option3;    var option4;    var option5;
    option = document.createElement("option");
                      option.type="text";
                      option.value="";
                      option.innerHTML = "";
    element5.appendChild(option);
    option1 = document.createElement("option");
                      option1.type="text";
                      option1.value="1";
                      option1.innerHTML = "Normal Delivery";
    element5.appendChild(option1);
    option2 = document.createElement("option");
                      option2.type="text";
                      option2.value="2";
                      option2.innerHTML = "Caesarian Section";
    element5.appendChild(option2);
    option3 = document.createElement("option");
                      option3.type="text";
                      option3.value="3";
                      option3.innerHTML = "Breech";
    element5.appendChild(option3);
    option4 = document.createElement("option");
                      option4.type="text";
                      option4.value="4";
                      option4.innerHTML = "Assisted Vaginal";
    element5.appendChild(option4);
    option5 = document.createElement("option");
                      option5.type="text";
                      option5.value="4";
                      option5.innerHTML = "Separated";
    element5.appendChild(option5);
  
    cell6.appendChild(element5);
    
    var cell7= row.insertCell(6);//create an input field of type text,class name, width and assign names, ids and functions
    var element6 = document.createElement("select");
    element6.type = "text";
    element6.name = "New_PlacentaComplete"+ee;
    element6.id ="New_PlacentaComplete"+ee;
     element6.className = "textbox";
    element6.style.width = "80px";
      var option6;    var option7; var option8;
       option6 = document.createElement("option");
                      option6.type="text";
                      option6.value="";
                      option6.innerHTML = "";
    element6.appendChild(option6);
       option7 = document.createElement("option");
                      option7.type="text";
                      option7.value="Y";
                      option7.innerHTML = "Y";
    element6.appendChild(option7);
       option8 = document.createElement("option");
                      option8.type="text";
                      option8.value="N";
                      option8.innerHTML = "N";
    element6.appendChild(option8);
  
    cell7.appendChild(element6);
    
    var cell8= row.insertCell(7);//create an input field of type text,class name, width and assign names, ids and functions
    var element7 = document.createElement("input");
    element7.type = "text";
    element7.name = "New_BloodLoss"+ee;
       element7.className = "textbox";
    element7.id ="New_BloodLoss"+ee;
    element7.style.width = "80px";
  
    cell8.appendChild(element7);
    
    var cell9= row.insertCell(8);//create an input field of type text,class name, width and assign names, ids and functions
    var element8 = document.createElement("select");
    element8.type = "text";
    element8.name = "New_ConditionAfterDelivery"+ee;
    element8.id ="New_ConditionAfterDelivery"+ee;
     element8.className = "textbox";
    element8.style.width = "80px";
      var option9;    var option10; var option11;
       option9 = document.createElement("option");
                      option9.type="text";
                      option9.value="";
                      option9.innerHTML = "";
    element8.appendChild(option9);
       option10 = document.createElement("option");
                      option10.type="text";
                      option10.value="A";
                      option10.innerHTML = "A";
    element8.appendChild(option10);
       option11 = document.createElement("option");
                      option11.type="text";
                      option11.value="D";
                      option11.innerHTML = "D";
    element8.appendChild(option11);
  
    cell9.appendChild(element8);
    
    var cell10= row.insertCell(9);//create an input field of type text,class name, width and assign names, ids and functions
    var element9 = document.createElement("input");
    element9.type = "text";
    element9.name = "New_ConditionAfterDelivery"+ee;
       element9.className = "textbox";
    element9.id ="New_ConditionAfterDelivery"+ee;
    element9.multiple =true;
    element9.style.width = "80px";
    
  
    cell10.appendChild(element9);
 
    ja++;

     document.getElementById("MtoU").value=ee;
doOnLoadMtoU();
timer();
}
 
        function deleteMtoU() {
       
           //if the addrow has not been called  
        
            
            if((verifierMtoU==1)){
               ja =parseInt(document.getElementById("MtoU_old_rows").value)+(parseInt(1));
               ee=parseInt(document.getElementById("MtoU").value);
            //alert(""+document.getElementById("no_of_old_rows").value);        
                           }
                     
          verifierMtoU++;
          verifiersMtoU++;
            
            
            try {
                
            
            var table = document.getElementById("MtoUDets");
            //delete the last row 
            
             
            
            var rowCount = table.rows.length;
           //  alert(""+rowCount);
 //alert(rowCount);
           // for(var a=0; a<rowCount; a++) {
            //    var row = table.rows[a];
              //  var chkbox = row.cells[0].childNodes[0]; 
              if(rowCount>=parseInt(document.getElementById("MtoU_old_rows").value)+parseInt(5)){
                  
                    table.deleteRow(rowCount-1);
                    rowCount--;
                    
                    //reduce the number of rows           
                    ja--;
                    ee--;
                   document.getElementById("MtoU").value=ee;
                   
                     
              }
              else{
                //alert("Maximum deletable columns reached!");  
                  
                  
              }
  
            }catch(e) {
                alert(e);
            }
        }

 



