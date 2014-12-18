/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




 
   
   function getAtoFDetails(){
     
//alert("a");
        var PNCNo=document.getElementById("PNCNo").value;    
        var motherID=document.getElementById("motherID").value;    

// window.open("districtchooser?county="+dist.value);  



var xmlhttp;    
if (PNCNo=="")
{
//filter the districts    



//document.getElementById("AtoFDets").innerHTML="";
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
document.getElementById("AtoFDets").innerHTML=xmlhttp.responseText;

doOnLoader();
doOnLoad();

}
}
xmlhttp.open("POST","getAtoG?PNCNo="+PNCNo+"&motherID="+motherID,true);
xmlhttp.send();
//call step 5 ajax
getGtoMDetails();
getNtoTDetails();
//getHtoLDetails();
//getMtoUDetails();
//getvtoabdetails();
resetAtoF();
resetGtoM();
resetNtoT();



}//end of filter districts
