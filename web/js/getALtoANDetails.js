/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function getALtoANDetails(){
  var ancno=document.getElementById("ancno").value;    
var motherID=document.getElementById("motherID").value; 
//+"motherID="+motherID
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
document.getElementById("ALtoANDets").innerHTML=xmlhttp.responseText;

show7hiddenothers();

}
}
xmlhttp.open("POST","getStep7details?ancno="+ancno+"&motherID="+motherID,true);
xmlhttp.send();


}


function show7hiddenothers(){

//check if the checkboxes are selected
var numrows=document.getElementById("old_step7_no_rows").value;

   // alert(numrows);

for(var x=1;x<=numrows;x++){
  
if(document.getElementById("old_others7"+x).value!="null"&&document.getElementById("old_others7"+x).value!=""){  
   document.getElementById("old_others7"+x).type='text'; 
    document.getElementById("old_otherstriger7"+x).checked=true;
}
}


}