/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function getvitaminADetails(){
    
    
    var ancno=document.getElementById("ancno").value;    
 var motherID=document.getElementById("motherID").value; 
//+"motherID="+motherID
// window.open("districtchooser?county="+dist.value);     
var xmlhttp;    
if (ancno=="")
{
//filter the districts    



//document.getElementById("table_vitamin").innerHTML="";
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
document.getElementById("table_vitamin").innerHTML=xmlhttp.responseText;


//chec whether there are any form values which are checked

markthecheckboxes();


//document.getElementById("blank_warning").innerHTML="";
}
}
xmlhttp.open("POST","getvitaminADetails?ancno="+ancno+"&motherID="+motherID,true);
xmlhttp.send();
    
    
    
    
    
    
}
function markthecheckboxes(){

var allcbs=parseInt(document.getElementById("vit_old_no_of_rows").value)+parseInt(1);
//alert("called");
for(var x=1;x<allcbs;x++){
    document.getElementById("dw_old_dosage"+x).checked=true;
    
    
    
}



}
