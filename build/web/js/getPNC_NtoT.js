/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


   function getNtoTDetails(){
     
//alert("a");
        var PNCNo=document.getElementById("PNCNo").value;    
        var motherID=document.getElementById("motherID").value;    

// window.open("districtchooser?county="+dist.value);  



var xmlhttp;    
if (PNCNo=="")
{
//filter the districts    



//document.getElementById("NtoTDets").innerHTML="";
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
document.getElementById("NtoTDets").innerHTML=xmlhttp.responseText;



}
}
xmlhttp.open("POST","getNtoT_?PNCNo="+PNCNo+"&motherID="+motherID,true);
xmlhttp.send();
//call step 5 ajax

//getHtoLDetails();
//getMtoUDetails();
//getvtoabdetails();
//resetAtoH();
//resetHtoL();
//resetMtoU();



}//end of filter districts
