/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


 
   
   function getAtoGDetails(){
     
//alert("a");
        var admNo=document.getElementById("admNo").value;    
        var momid=document.getElementById("motherID").value;    
//+"&motherID="+momid
// window.open("districtchooser?county="+dist.value);  



var xmlhttp;    
if (admNo=="")
{
//filter the districts    



//document.getElementById("AtoGDets").innerHTML="";
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
document.getElementById("AtoGDets").innerHTML=xmlhttp.responseText;

doOnLoad();
doOnLoader();
time();

}
}
xmlhttp.open("POST","AtoG?admNo="+admNo+"&motherID="+momid,true);
xmlhttp.send();
//call step 5 ajax

getHtoLDetails();
getMtoUDetails();
getvtoabdetails();
resetAtoH();
resetHtoL();
resetMtoU();



}//end of filter districts
