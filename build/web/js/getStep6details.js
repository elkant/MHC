/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function getStep6details(){
//alert("a");
        var ancno=document.getElementById("ancno").value;    
var motherID=document.getElementById("motherID").value; 
//+"&motherID="+motherID
// window.open("districtchooser?county="+dist.value);     
var xmlhttp;    
if (ancno=="")
{
//filter the districts    




}
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
{
document.getElementById("step6_table").innerHTML=xmlhttp.responseText;
doOnLoader();
showhiddenothers();

disableTables();
}
}
xmlhttp.open("POST","getStep6details?ancno="+ancno+"&motherID="+motherID,true);
xmlhttp.send();






}

function showhiddenothers(){

//check if the checkboxes are selected
var numrows=document.getElementById("old_step6_no_rows").value;

   // alert(numrows);

for(var x=1;x<=numrows;x++){
  
if(document.getElementById("old_others"+x).value!="null"&&document.getElementById("old_others"+x).value!=""){  
   document.getElementById("old_others"+x).type='text'; 
    document.getElementById("old_otherstriger"+x).checked=true;
}
}


}