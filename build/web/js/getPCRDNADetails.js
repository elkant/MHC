/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function getPCRDNADetails(){
    
    
    
      
    var ancno=document.getElementById("ancno").value;    
    var motherID=document.getElementById("motherID").value; 
//+"motherID="+motherID
// window.open("districtchooser?county="+dist.value);     
var xmlhttp;    
if (ancno=="")
{
//filter the districts    



//document.getElementById("table_dna_pcr").innerHTML="";
//return;
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
document.getElementById("table_dna_pcr").innerHTML=xmlhttp.responseText;
//document.getElementById("blank_warning").innerHTML="";
doOnLoad();
}
}
xmlhttp.open("POST","getPCRDNADetails?ancno="+ancno+"&motherID="+motherID,true);
xmlhttp.send();
    
}
