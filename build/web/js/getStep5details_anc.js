/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function getStep5details(){
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
document.getElementById("step5_table").innerHTML=xmlhttp.responseText;
//doOnLoad();

}
}
xmlhttp.open("POST","getStep5details_anc?ancno="+ancno+"&motherID="+motherID,true);
xmlhttp.send();

//call step 5 ajax

}