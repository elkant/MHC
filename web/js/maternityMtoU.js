/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
   //*******************ajax for M to U*************************
   function getMtoUDetails(){
  var admNo=document.getElementById("admNo").value; 
  
   var momid=document.getElementById("motherID").value;    
//+"&motherID="+momid
//alert("getMtoUDetails"+admNo);
// window.open("districtchooser?county="+dist.value);     
var xmlhttp;    
if (admNo=="")
{
//filter the districts    



//document.getElementById("MtoUDets").innerHTML="";
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


document.getElementById("MtoUDets").innerHTML=xmlhttp.responseText;
doOnLoad();
doOnLoader();
time();

}
}
xmlhttp.open("POST","maternityMtoU?admNo="+admNo+"&motherID="+momid,true);
xmlhttp.send();


}//end of send anc no  