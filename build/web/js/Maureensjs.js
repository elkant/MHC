/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


    function onview(){
     
      var x =parseInt(document.getElementById("CervicalCancer_old_rows").value);
     var i;
     for(i=1;i<=x;i++){
         var y =document.getElementById("Cervix"+i).value;
        
          if(document.getElementById("Cervix"+i).value=="Yes"){
            document.getElementById("Cervix"+i).checked=true ; 
          }
          else if(document.getElementById("Cervix"+i).value=="No") {
            document.getElementById("Cervix"+i).checked=false ; 
          }
      }
     for(i=1;i<=x;i++){
         var y =document.getElementById("Suspect"+i).value;
        
          if(document.getElementById("Suspect"+i).value=="Yes"){
            document.getElementById("Suspect"+i).checked=true ; 
          }
          else if(document.getElementById("Suspect"+i).value=="No") {
            document.getElementById("Suspect"+i).checked=false ; 
          }
      }
     for(i=1;i<=x;i++){
          if(document.getElementById("Cryo"+i).value=="Yes"){
            document.getElementById("Cryo"+i).checked=true ; 
          }  
          else if(document.getElementById("Cryo"+i).value=="No"){
            document.getElementById("Cryo"+i).checked=false;
          }  
      }
     for(i=1;i<=x;i++){
           if(document.getElementById("Leep"+i).value=="Yes"){
            document.getElementById("Leep"+i).checked=true ; 
          }
      }
     for(i=1;i<=x;i++){
          if(document.getElementById("Others"+i).value!=""){
            document.getElementById("OtherCheck"+i).checked=true ; 
            document.getElementById("Others"+i).type="text";
            }
            else if(document.getElementById("Others"+i).value==""){
            document.getElementById("OtherCheck"+i).checked=false ; 
            document.getElementById("Others"+i).type="hidden"; 
            document.getElementById("Others"+i).value=""; 
            } 
          
      }
      var y =parseInt(document.getElementById("CervicalCancer").value);
//      alert(y);
var e=parseInt(3);
      if(y=="1"){
      var t;
      
//          alert("yyyyyy"+document.getElementById("cervixPap3").value);
          if(document.getElementById("cervixPap3").value=="Yes"){
            document.getElementById("cervixPap3").checked=true ; }
          
   
            if(document.getElementById("HSIL3").value=="Yes"){
            document.getElementById("HSIL3").checked=true ; 
            }
  
           if(document.getElementById("Overt3").value=="Yes"){
            document.getElementById("Overt3").checked=true ; 
           } 
      
    
          if(document.getElementById("CryoPap3").value=="Yes"){
            document.getElementById("CryoPap3").checked=true ; 
          }
    
          if( document.getElementById("LeepPap3").value=="Yes"){
            document.getElementById("LeepPap3").checked=true ; }
 
        if(document.getElementById("papsmear").value != ""){
            document.getElementById("papsmear").checked=true ; 
            document.getElementById("OthersPap").type="text";
            }
        if(document.getElementById("papsmear").value == ""){
            document.getElementById("papsmear").checked=false ; 
            document.getElementById("OthersPap").type="hidden";
            document.getElementById("OthersPap").value="";
            }
          
      }
    
    }
    

   
   
  
   
   
   
   
    $(document).ready(function(){
    	// Smart Wizard	
  		$('#wizard').smartWizard({transitionEffect:'slide'});
     
		});

    
     
   function getCurrPregDets(){

        var ancno=document.getElementById("ancno").value;    
var motherID=document.getElementById("motherID").value; 
//+"motherID="+motherID
// window.open("districtchooser?county="+dist.value);     
var xmlhttp;    
if (ancno=="")
{
//filter the districts    



//document.getElementById("currPreg").innerHTML="";
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
document.getElementById("currPreg").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","getPresentPregDet?ancno="+ancno+"&motherID="+motherID,true);
xmlhttp.send();
}//end of filter districts

//*******************filter target populations*************************
   
   
   
    
    
   //IronFolate ajax  
   function getIronFolate(){

        var ancno=document.getElementById("ancno").value;    
var motherID=document.getElementById("motherID").value; 
//+"motherID="+motherID
// window.open("districtchooser?county="+dist.value);     
var xmlhttp;    
if (ancno=="")
{
//filter the districts    



//document.getElementById("IronFolate").innerHTML="";
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
document.getElementById("IronFolate").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","IronFolateServlet?ancno="+ancno+"&motherID="+motherID,true);
xmlhttp.send();
}//end of filter districts


//post natal mum ajax 
   function getPostNatalMum(){

        var ancno=document.getElementById("ancno").value;    
var motherID=document.getElementById("motherID").value; 
//+"motherID="+motherID
// window.open("districtchooser?county="+dist.value);     
var xmlhttp;    
if (ancno=="")
{
//filter the districts    



//document.getElementById("PostNatalMum").innerHTML="";
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
document.getElementById("PostNatalMum").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","PostNatalMum?ancno="+ancno+"&motherID="+motherID,true);
xmlhttp.send();
}//end of filter districts

//ajax for getting post natal baby data
   function getPostNatalBaby(){

        var ancno=document.getElementById("ancno").value;    
  var motherID=document.getElementById("motherID").value; 
//+"motherID="+motherID
// window.open("districtchooser?county="+dist.value);     
var xmlhttp;    
if (ancno=="")
{
//filter the districts    



//document.getElementById("PostNatalBaby").innerHTML="";
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
document.getElementById("PostNatalBaby").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","PostNatalBaby?ancno="+ancno+"&motherID="+motherID,true);
xmlhttp.send();
}//end of Post natal baby

//ajax for cervical cancer screening
   function getCervicalTests(){
 var motherID=document.getElementById("motherID").value; 
        var ancno=document.getElementById("ancno").value;    

// window.open("districtchooser?county="+dist.value);     
var xmlhttp;    
if (ancno=="")
{
//filter the districts    



//document.getElementById("CervicalTest").innerHTML="";
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
document.getElementById("CervicalTest").innerHTML=xmlhttp.responseText;

onview();
}
}
xmlhttp.open("POST","CervicalCancer?ancno="+ancno+"&motherID="+motherID,true);
xmlhttp.send();
}//end of cervical cancer

//*******************filter target populations*************************
   
   
   
    
    
    
    

   function check(){
       var z =document.getElementById("CervicalCancer").value;
       var u =document.getElementById("CervicalCancer_old_rows").value;
      
      if(z==3 || z==2){
     for(var t=1;t<z;t++){
//         var g=document.getElementById("OtherCheck"+t).value;
//         alert(g);
        if(document.getElementById("New_OtherCheck"+t).checked==true ){
            if( document.getElementById("New_Others"+t).type=="hidden"){
           
       document.getElementById("New_Others"+t).type="text";    
      
            }}  
        else if(document.getElementById("New_OtherCheck"+t).checked==false && document.getElementById("New_Others"+t).type=="text" ){
       

            document.getElementById("New_Others"+t).type="hidden";    
            document.getElementById("New_Others"+t).value="";    
        }
          }
        }
     var p =document.getElementById("CervicalCancer_old_rows").value;    
        if(p!=0){
        
            for(var f=1;f<=p;f++){ 
        if(document.getElementById("OtherCheck"+f).checked==true && document.getElementById("Others"+f).type=="hidden"){
           
       document.getElementById("Others"+f).type="text";    
      
   }  
        else if(document.getElementById("OtherCheck"+f).checked==false && document.getElementById("Others"+f).type=="text" ){
       

            document.getElementById("Others"+f).type="hidden";    
            document.getElementById("Others"+f).value="";    
        }
         }
         
         }
           if(document.getElementById("papsmear").checked==true){
            
          if(document.getElementById("OthersPap").type=="hidden"){
           
       document.getElementById("OthersPap").type="text";    
       }}  
        else if(document.getElementById("papsmear").checked==false){
            
          if(document.getElementById("OthersPap").type=="text"){
           
       document.getElementById("OthersPap").type="hidden";    
       document.getElementById("OthersPap").value="";    
       }}  
           
//      if(document.getElementById("New_papsmear").checked==true){
//            
//          if(document.getElementById("New_OthersPap").type=="hidden"){
//           
//       document.getElementById("New_OthersPap").type="text";    
//       }}  
//        else if(document.getElementById("New_papsmear").checked==false){
//            
//          if(document.getElementById("New_OthersPap").type=="text"){
//           
//       document.getElementById("New_OthersPap").type="hidden";    
//       document.getElementById("OthersPap").value="";    
//       }}        
//           
           
        }
 
   
   
   
    
    
    
    
    

    function oncheck(name){
        
 var k;     
if(document.getElementById(name)!=null)
{k=document.getElementById(name).value;}
// alert(k);

if(k=="No"){

document.getElementById(name).value="Yes" ;


}
if(k==""){

document.getElementById(name).value="Yes" ;


}
else if(k=="Yes"){

document.getElementById(name).value="No" ;
}




      
    }

