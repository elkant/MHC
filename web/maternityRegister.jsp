
<%@page import="java.util.ArrayList"%>
<%@page import="sender.dbConn"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="sendSMS.dbConnect"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Maternity Register</title>

<%!

  String ancno="";
 
 String admNo="";
 String motherID="";
 
 dbConn conn= new dbConn();
%>
      <%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
      session = request.getSession();
   
    
    if (session.getAttribute("userid")==null) {
        response.sendRedirect("index.jsp");
    } 
%>


<link href="style/demo_style.css" rel="stylesheet" type="text/css"/>
<link href="style/smart_wizard.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="js/jquery.smartWizard.js"></script>
<script type="text/javascript" src="js/MaternityValidation.js"></script>

<link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>

<!--clerk menu-->

<link rel="stylesheet" type="text/css" href="menu/css/clerkmenucss.css"/>
         <link rel="shortcut icon" href="images/icon.png"/> 
          
  
<!--tooltip and jquery calender-->



<!--======================MY CUSTOM CALENDER============================================-->

    <link rel="stylesheet" type="text/css" href="js/codebase/dhtmlxcalendar.css"/>
    <link rel="stylesheet" type="text/css" href="js/codebase/skins/dhtmlxcalendar_dhx_skyblue.css">
    <script src="js/codebase/dhtmlxcalendar.js"></script>
    
    <!--calendar based on field ids-->
     
    
    
<!--==================================================================-->




<link href="js/css/south-street/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>
<script src="js/js/jquery-ui-1.10.3.custom.js"></script>
<link rel="stylesheet" href="js/demos.css" />





         
         <!--======EXTRNAL JAVASCRIPTS=====-->
         
             
    
          
          <!--======HELP MESSAGES JS========-->
          
          <script type="text/javascript" src="js/matreg_help.js"> </script>
      

         
         
<script type="text/javascript">
    $(document).ready(function(){
    	// Smart Wizard 	
  		$('#wizard').smartWizard();
      
      function onFinishCallback(){
        $('#wizard').smartWizard('showMessage','Finish Clicked');
        //alert('Finish Clicked');
      }     
		});
</script>
           
          
<script type="text/javascript">
  



var myCalendar;
function loadcalender() {
   
    var s=1;
    var all="";
    for(s=1;s < 100;s++){
  all+="\"ajan_new_date"+s+"\",";
  
  all+="\"ajan_old_date"+s+"\"";
  if(s<99){all+=",";}
    }
    //alert(JSON.parse("["+all+"]"));
        myCalendar = new dhtmlXCalendarObject(JSON.parse("["+all+"]"));
        
}


function loadvillages(rowid){
    
     

// window.open("districtchooser?county="+dist.value);  



var xmlhttp;    
if (rowid=="")
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
if (xmlhttp.readyState==4&&xmlhttp.status==200)
{
document.getElementById("New_Village"+rowid).innerHTML=xmlhttp.responseText;


 


}
}
xmlhttp.open("POST","loadvillages",true);
xmlhttp.send();


  
    
}
   
   function getvtoabdetails(){
var ancno1;


if(document.getElementById("admNo")!=null && document.getElementById("admNo")!=""){
  ancno1=document.getElementById("admNo").value;    
}

var motherID="";
if(document.getElementById("motherID")!=null && document.getElementById("motherID")!=""){
  motherID=document.getElementById("motherID").value;    
}


  if( ancno1=="")
  {
      
      ancno1="_";
      
  }

// window.open("districtchooser?county="+dist.value);  



var xmlhttp;    
if (ancno1=="")
{
//filter the districts    



return;
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
document.getElementById("avtoabtable").innerHTML=xmlhttp.responseText;


 


}
}
xmlhttp.open("POST","vtoab?ancno="+ancno1+"&motherID="+motherID,true);
xmlhttp.send();

getactoaidetails();
getajtoandetails();
//
    

//



}

    
   
function getactoaidetails(){
     

  var ancno1;
if(document.getElementById("admNo")!=null && document.getElementById("admNo")!=""){
  ancno1=document.getElementById("admNo").value;    
}  

var motherID="";
if(document.getElementById("motherID")!=null && document.getElementById("motherID")!=""){
  motherID=document.getElementById("motherID").value;    
}


// window.open("districtchooser?county="+dist.value);  



var xmlhttp;    
if (ancno1=="")
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
document.getElementById("actoaitable").innerHTML=xmlhttp.responseText;


 


}
}
xmlhttp.open("POST","actoai?ancno="+ancno1+"&motherID="+motherID,true);
xmlhttp.send();



}//end of filter districts

   
function getajtoandetails(){
     

 var ancno1;
if(document.getElementById("admNo")!=null && document.getElementById("admNo")!=""){
  ancno1=document.getElementById("admNo").value;    
}


var motherID="";
if(document.getElementById("motherID")!=null && document.getElementById("motherID")!=""){
  motherID=document.getElementById("motherID").value;    
}


// window.open("districtchooser?county="+dist.value);  



var xmlhttp;    
if (ancno1=="")
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
document.getElementById("ajtoantable").innerHTML=xmlhttp.responseText;

loadcalender();
 


}
}
xmlhttp.open("POST","ajtoan?ancno="+ancno1+"&motherID="+motherID,true);
xmlhttp.send();


}//end of filter districts

    $(document).ready(function(){
    	// Smart Wizard	
  		$('#wizard').smartWizard({transitionEffect:'slide'});
     
		});
</script>
          
<!--          maureens scripts-->
          

  <script type="text/javascript" src="js/jquery.timepicker.js"></script>
  <link rel="stylesheet" type="text/css" href="js/jquery.timepicker.css" />
<script type="text/javascript">
  $(document).ready(function() {
       
			$('.timepicker').timepicker({ 'step': 15 });
		  });
		

</script>
<script type="text/javascript">
    function time(){
  
       
			$('.DeliveryTime1').timepicker({ 'step': 15 });
			$('#New_DeliveryTime1').timepicker({ 'step': 15 });
			$('#New_DeliveryTime2').timepicker({ 'step': 15 });
			$('#New_DeliveryTime3').timepicker({ 'step': 15 });
		 
		
}
</script>



         
         <!--======EXTRNAL JAVASCRIPTS=====-->
         
          <!--======EXTRNAL JAVASCRIPTS=====-->
          
          <!--======HELP MESSAGES JS========-->
          
          <script type="text/javascript" src="js/anchelp.js"> </script>
          

 <!--=======STEP2DETAILS=====-->
<script type="text/javascript" src="js/Maternity_AddHtoL.js"/></script>
 <!--=======STEP3DETAILS=====-->
 <script type="text/javascript" src="js/Maternity_AddMtoU.js"></script>

 <!--=======add row for step 2=====-->


<!--======== DISABLE TABLES IF MOTHER searched by ANC DOES NOT EXIST in the System===== -->
<script type="text/javascript" src="js/disableMyTables.js"></script>  
<!--<script type="text/javascript" src="js/validateSteps.js"></script>-->

   
         

<script type="text/javascript">
    $(document).ready(function(){
    	// Smart Wizard 	
  		$('#wizard').smartWizard();
      
      function onFinishCallback(){
        $('#wizard').smartWizard('showMessage','Finish Clicked');
        //alert('Finish Clicked');
      }     
		});
</script>
<!--    getting values from a to g       -->
<script src="js/maternityAtoG.js" type="text/javascript"></script>         
<!--    getting values from h to l       -->
<script src="js/maternityHtoL.js" type="text/javascript"></script>         
<!--    getting values from m to u       -->
<script src="js/maternityMtoU.js" type="text/javascript"></script>         
<script type="text/javascript">

//********************************************
    $(document).ready(function(){
    	// Smart Wizard	
  		$('#wizard').smartWizard({transitionEffect:'slide'});
     
		});
</script>


<script type="text/javascript">
    function change() {

   
   var a = document.getElementById("treatment");
   var v =a.options[a.selectedIndex].value;
//   alert("a   "+v);
if((v="6=Others(Specify")) {
   
       if(document.getElementById("others1").type="hidden"){
           
       document.getElementById("others1").type="text";    
       }}
else if(!(v="6=Others(Specify")) { 
   
       if(document.getElementById("others1").type="text"){
           
       document.getElementById("others1").type="hidden";    
       }
//       if(document.getElementById("others1").type="text"){
//           
//       document.getElementById("others1").type="hidden";    
//       }

}
}




</script>
  <script type="text/javascript">

var newCalendar;
function doOnLoader() {
    //alert("a");
    var s=1;
    var all="";
    for(s=1;s < 100;s++){
  all+="\"AdmissionDate"+s+"\",";
  all+="\"New_AdmissionDate"+s+"\",";
  all+="\"EDD"+s+"\",";
  all+="\"LMP"+s+"\",";
  all+="\"New_LMP"+s+"\",";
  all+="\"New_EDD"+s+"\",";
  
  all+="\"New_DeliveryDate"+s+"\"";
  if(s<99){all+=",";}
    }
    //alert(JSON.parse("["+all+"]"));
        newCalendar = new dhtmlXCalendarObject(JSON.parse("["+all+"]"));
}

    
</script>
<script>
 
var myCalendar;
function doOnLoad() {





   
   
   var s1=1;
    var all2="";
    for(s1=1;s1 < 100;s1++){
  all2+="\"New_VisitDate"+s1+"\",";
  all2+="\"New_DeliveryDate"+s1+"\",";
  all2+="\"DeliveryDate"+s1+"\"";
 
  if(s1<99){all2+=",";}
    }
    //alert(JSON.parse("["+all+"]"));
        myCalendar = new dhtmlXCalendarObject(JSON.parse("["+all2+"]"));
   
   
}

 function resetAtoH(){
b=1;
verifiers=1;
verifiers1=1;
}
 


          //var existing_no_of_rows= document.getElementById("no_of_rows").value+1;
        
        var b=1;  //hplds the no of existing rows for the table to add new values
        var verifiers=1;
        var verifiers1=1;
       //verifier is a field that determines whether to fetch the value of i from the edit text.
       var j=0;  
       
      
function addAtoH() {
 var motherID;
                              <%
 if(request.getParameter("ancnumber")!=null){
     
 admNo=request.getParameter("ancnumber").toString();
 
}
else{
admNo="" ;
}
      if(request.getParameter("motherID")!=null){
   
 motherID=request.getParameter("motherID").toString();
 
 
}
else{motherID="";} 
%>
   
  
  
  
          
         
      if(verifiers==1){
                j =parseInt(document.getElementById("MatRegister_old_rows").value)+(parseInt(1));
                b=parseInt(document.getElementById("MatRegister_newRows").value);
                  
                          }
            
             b++;
             
             
//             alert("j  "+j);
//             alert("b  "+b);
          verifiers++;
          verifiers1++;  
            
 
    var table = document.getElementById("AtoGDets");
  //get the number of rows
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
             
 //create element that is a checkbox assign type, name,style then apppend the child element to the cell
            var cell1 = row.insertCell(0);
            cell1.style.width = "40px";
            
            var element0 = document.createElement("label"); 
            element0.type = "label";
            //element1.name="chkbox[]";
            element0.innerHTML=""+parseInt(1+j);
            element0.style.width = "40px";
//            element0.style.textAlign="center";
            cell1.appendChild(element0);
            
            
            
  var cell2 = row.insertCell(1);
    cell2.style.width = "80px";
    var element1 = document.createElement("input");//create an input field of type text and assign names, ids and functions
    element1.type = "text";
    element1.value = "";
    element1.name = "New_AdmissionNum"+b;
    element1.id = "New_AdmissionNum"+b;
      element1.className = "textbox";
    element1.style.width = "100px";
    element1.value ="";
    cell2.appendChild(element1);
            
   
    var cell3 = row.insertCell(2);
     cell3.style.width = "80px";
    var element2 = document.createElement("input");//create an input field of type text and assign names, ids and functions
    element2.type = "text";
    element2.value = "";
    element2.name = "New_AdmissionDate"+b;
    element2.id = "New_AdmissionDate"+b;
    element2.readOnly = true;
      element2.className = "textbox";
    element2.style.width = "100px";
//    element1.style.width = "200px";
    cell3.appendChild(element2);
    
    var cell4 = row.insertCell(3);//create an input field of type text,class name, width and assign names, ids and functions
    cell4.style.width = "80px";
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.name = "New_No_Visits"+b;
    element3.id = "New_No_Visits"+b;
      element3.className = "textbox";
    element3.value ="";
    element3.style.width = "100px";
    cell4.appendChild(element3);
           
    
    var cell5= row.insertCell(4);//create an input field of type text,class name, width and assign names, ids and functions
    cell5.style.width = "80px";
    var element4 = document.createElement("input");
    element4.type = "text";
    element4.name = "New_FirstName"+b;
    element4.id ="New_FirstName"+b;
    element4.className = "textbox";
    element4.style.width = "100px";
    element4.value ="";
    cell5.appendChild(element4);
    
    var cell6= row.insertCell(5);//create an input field of type text,class name, width and assign names, ids and functions
    cell6.style.width = "100px";
    var element5 = document.createElement("input");
    element5.type = "text";
    element5.name = "New_SecondName"+b;
    element5.id ="New_SecondName"+b;
    element5.className = "textbox";
    element5.style.width = "100px";
    element5.value ="";
    cell6.appendChild(element5);
    
    var cell7= row.insertCell(6);//create an input field of type text,class name, width and assign names, ids and functions
    cell7.style.width = "100px";
    var element6 = document.createElement("input");
    element6.type = "text";
    element6.name = "New_LastName"+b;
    element6.className = "textbox";
    element6.id ="New_LastName"+b;
    element6.value ="";
    element6.style.width = "100px";
    cell7.appendChild(element6);
            
    var cell8= row.insertCell(7);//create an input field of type text,class name, width and assign names, ids and functions
  
  
  
    
    var villagesselect="<select   style=\"width:100px;\"  id=\"New_Village" + b + "\"  name=\"New_Village" + b + "\"></select>";
    
    cell8.innerHTML=villagesselect;
    
    loadvillages(b);
    
    
    var cell9= row.insertCell(8);//create an input field of type text,class name, width and assign names, ids and functions
    cell9.style.width = "100px";
    var element8 = document.createElement("input");
    element8.type = "text";
    element8.name = "New_Age"+b;
    element8.id ="New_Age"+b;
    element8.className = "textbox";
    element8.value ="";
    element8.style.width = "100px";
    cell9.appendChild(element8);
    
    var cell10= row.insertCell(9);//create an input field of type text,class name, width and assign names, ids and functions
    cell10.style.width = "80px";
    var element9 = document.createElement("select");
    element9.type = "text";
    element9.name = "New_MaritalStatus"+b;
    element9.id ="New_MaritalStatus"+b;
    element9.className = "textbox";
    element9.style.width = "100px";
    var options;
    var options1;
    var options2;
    
     <% 
     
     String array[]={" ","Married", "Widowed","Single","Divorced","Separated"};
     String a="";
 
for(int i=0;i<array.length;i++){
                                                %>
   options = document.createElement("option");
                      options.type="text";
//                      options.selected=true;
                      options.value="<%= array[i]%>";
                      options.innerHTML ="<%= array[i]%>";
    element9.appendChild(options);
    
    <%
   }
%>

   cell10.appendChild(element9);

      doOnLoad();      
      doOnLoader();      
    j++;
   document.getElementById("MatRegister_newRows").value=b;
   
   
        addHtoL();
        addMtoU();
        addrowstep4mat('avtoabtable');
 
}
 
        function deleteAtoH() {
       
          //if the addrow has not been called  
        
            
            if((verifiers1==1)){
               j =parseInt(document.getElementById("MatRegister_old_rows").value)+(parseInt(1));
               b=parseInt(document.getElementById("MatRegister_newRows").value);
            //alert(""+document.getElementById("no_of_old_rows").value);        
                           }
                     
          verifiers1++;
          verifiers++;
            
            
            try {
                
            
            var table = document.getElementById("AtoGDets");
            //delete the last row 
            
             
            
            var rowCount = table.rows.length;
           //  alert(""+rowCount);
 //alert(rowCount);
           // for(var a=0; a<rowCount; a++) {
            //    var row = table.rows[a];
              //  var chkbox = row.cells[0].childNodes[0]; 
              if(rowCount>=parseInt(document.getElementById("MatRegister_old_rows").value)+parseInt(4)){
                  
                    table.deleteRow(rowCount-1);
                    rowCount--;
                    
                    //reduce the number of rows           
                    j--;
                    b--;
                   document.getElementById("MatRegister_newRows").value=b;
                   
                     
              }
              else{
               // alert("Maximum deletable columns reached!");  
                  
                  
              }
  
            }catch(e) {
                alert(e);
            }
            
            
            deleteHtoL();
            deleteMtoU();
            delrowstep4mat('avtoabtable');
            
        }

</script>
          
          <!--======HELP MESSAGES JS========-->
          
          <script type="text/javascript" src="js/matreg_help.js"> </script>
 
          
          
          
<!--       end of scripts   -->
          
          
         <style>
            .swMain .stepContainer {
                 width:1160px;
                 padding-left: 10px;
             }
             .actionBar{
                 width:1160px;
                 padding-left: 10px;
             }
             .content{
                 width:1160px;
                 padding-left: 10px;
             }
             .swMain .stepContainer div.content{
                 margin-left: 0px;
             }
             swMain.StepTitle h2{
                 text-align: center;
                 
             }
             
         </style>
         
         <script type="text/javascript" src="js/jquery.timepicker.js"></script>
  <link rel="stylesheet" type="text/css" href="js/jquery.timepicker.css" />
<script type="text/javascript">
  $(document).ready(function() {
       
			$('.timepicker').timepicker({ 'step': 15 });
		  });
		

</script>
<script type="text/javascript">
    function time(){
  
       
			$('#DeliveryTime1').timepicker({ 'step': 15 });
			$('#DeliveryTime2').timepicker({ 'step': 15 });
			$('#DeliveryTime3').timepicker({ 'step': 15 });
			$('#DeliveryTime4').timepicker({ 'step': 15 });
			$('#DeliveryTime5').timepicker({ 'step': 15 });
			$('#DeliveryTime6').timepicker({ 'step': 15 });
			$('#DeliveryTime7').timepicker({ 'step': 15 });
			$('#DeliveryTime8').timepicker({ 'step': 15 });
			$('#DeliveryTime9').timepicker({ 'step': 15 });
			$('#DeliveryTime10').timepicker({ 'step': 15 });
			$('#DeliveryTime11').timepicker({ 'step': 15 });
			$('#New_DeliveryTime1').timepicker({ 'step': 15 });
			$('#New_DeliveryTime2').timepicker({ 'step': 15 });
			$('#New_DeliveryTime3').timepicker({ 'step': 15 });
			$('#New_DeliveryTime4').timepicker({ 'step': 15 });
			$('#New_DeliveryTime5').timepicker({ 'step': 15 });
			$('#New_DeliveryTime6').timepicker({ 'step': 15 });
			$('#New_DeliveryTime7').timepicker({ 'step': 15 });
			$('#New_DeliveryTime8').timepicker({ 'step': 15 });
			$('#New_DeliveryTime9').timepicker({ 'step': 15 });
			$('#New_DeliveryTime10').timepicker({ 'step': 15 });
			$('#New_DeliveryTime11').timepicker({ 'step': 15 });
			$('#New_DeliveryTime12').timepicker({ 'step': 15 });
			$('#New_DeliveryTime13').timepicker({ 'step': 15 });
			$('#New_DeliveryTime14').timepicker({ 'step': 15 });
		 
		
}
</script>
  
  
  
  
   <script type="text/javascript" src="js/addstep4mat.js"> </script> 
   <script type="text/javascript" src="js/addstep5mat.js"> </script> 
   <script type="text/javascript" src="js/addstep6mat.js"> </script> 
  
  
  
  </head><body onload="return doOnLoader();doOnload();" >
    
 <div id="container" style="width:1300px; height: 880px;">
     
     <br/>
     
            <div id="header" style="width:1250px; margin-left: 25px;">
                <!--=========================================menu=========================================-->     
           <%
if(session.getAttribute("level")!=null){            

if (session.getAttribute("level").equals("0")) {%>
            <%@include file="menu/adminmenu.html" %>
            <%}
else{%>

 <%@include file="menu/clerkmenu.html" %>

<%}

}

else{ %>
        
             <%@include file="menu/clerkmenu.html" %>
            
           <%}%>
            
        <!--=====================================================================================--> 
            </div> 
     
     
     


    
    <form action="saveMaternityRegister" method="POST" name="myform" style="background:#ffffff; margin:10px ;"> 
<table align="center" border="0" cellpadding="0" cellspacing="0">
    <tr><td>
<!-- Smart Wizard -->
      
<div id="wizard" style="width:1180px;" class="swMain" >
  			<ul>
                      <!---------------------------STEP1------------------------------------->      

                      <li style="width:175px;margin-right: 10px ;"><a href="#step-1">
                                    <label class="stepNumber">1</label>
                                    <span class="stepDesc">
                                        (a) to (h)<br />
                                        <small>a,b,c,d,e,f,g,h</small>
                                    </span>
                                </a></li>
                            
                        <!---------------------------STEP2------------------------------------->        
                            
                            <li style="width:175px;margin-right: 12px ;"><a href="#step-2">
                                    <label class="stepNumber">2</label>
                                    <span class="stepDesc">
                                        (i) to (p)<br />
                                        <small>i,j,k,l,m </small>
                                    </span>
                                </a></li>
                            
                         <!---------------------------STEP3------------------------------------->       
                            
                            <li style="width:175px;"><a href="#step-3">
                                    <label class="stepNumber">3</label>
                                    <span class="stepDesc">
                                        (q) to (w)<br />
                                        <small>q,r,s,t,u,v,w</small>
                                    </span>                   
                                </a></li>
                         
                          <!---------------------------STEP4------------------------------------->   
                       
                          
                          
                           <!---------------------------STEP5------------------------------------->  
                           
                        <li style="width:175px;"><a href="#step-4">
                                  <label class="stepNumber">4</label>
                                  <span class="stepDesc">(v) to(ab)<br/> <small>Baby <br/> HIV Status</small>
                                  </span>                   
                              </a></li>   
                           
                           
                           <!---------------------------STEP6------------------------------------->  
                           
                        <li style="width:175px;"><a href="#step-5">
                                  <label class="stepNumber">5</label>
                                  <span class="stepDesc">(ac)to(ai)<br/><small></small>
                                  </span>                   
                              </a></li>   
                           
                           
                        <li><a href="#step-6">
                                  <label class="stepNumber">6</label>
                                  <span class="stepDesc">(aj)to(an)<br/><small></small>
                                  </span>                   
                              </a></li>   
                           
                           
           <%
           
String Sectionshelp1[]=new String [6];
int mcount=0;
if(conn.st_6.isClosed()){conn= new dbConn(); }
conn.rs_6=conn.st_6.executeQuery("Select * from help where help_id between 13 and 19 ");
while(conn.rs_6.next()){
    
Sectionshelp1[mcount]=conn.rs_6.getString(2);
if(mcount<=19){
mcount++;
}
}
%>            
                           
                           
                           
  			</ul>
<!--  -----------------------------------------------------  step 1 from a to h------------------------------------------------------------->
    <script type="text/javascript">
 
   //getvtoabdetails();
   
       </script>  
<div id="step-1" >	
          
             <h2 class="StepTitle"> MATERNITY REGISTER (Jan 2011 version) STEP A TO G <a href="#" id="dialog-link1" >
                                    <img src="images/help_24.png"  /> </a> <p id="page1rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p></h2>
<input type="text" value="add row" id="addrowstep1" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick=" addAtoH();doOnLoader();time();"/>
<input type="text" value="delete last row" id="delrowstep1" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deleteAtoH();"/>

<!-- ui-dialog -->
<div id="dialog1" title="Columns a to g  Help ">
	<p>
         <% if(Sectionshelp1[0]!=null){%>
         <%=Sectionshelp1[0]%> 
         <%}%>
        </p>
</div>

            <table border="1px" class="viewpdt" align="center" style="margin-left: 120px;" >


          <tr>
              
              
<!--<td>Search Mother using their Admission number.</td><td style="width:12px;"><input type="text"   name="admNo" id="admNo" title="" required pattern="^d{4}-d{2}-d{4}$"/></td>

<td><input type="text" required  value="Search.."   style=" cursor:pointer;margin-left: 40px; text-transform:uppercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="getvtoabdetails();"/> </td>-->

<td>Admission Number.</td><td style="width:12px;"><input type="text" name="admNo" id="admNo" readonly required pattern="^d{4}-d{2}-d{4}$"/><input type="hidden" name="motherID" id="motherID" value=""></td>
   <% String facils[]={"","Solian","","Ngubereti","","Emmining","","Torongo","","Timboroa","","Simotwet"};%>
          
<td>Select a Facility</td>
<td><select name="facility" id="facility">
        <option value=""></option> 
       
       <%
       
       
 String fac="";
       if(request.getParameter("facil")!=null) {
               fac=request.getParameter("facil");
                                               }
       
       if(fac.equalsIgnoreCase("Ngubureti")){fac="Ngubereti";}
       if(fac.equalsIgnoreCase("Emmining")){fac="Emmining";}
       
       for(int b=0;b<facils.length;b++){
           if(facils[b].equalsIgnoreCase(fac))
           {            out.println("<option selected value=\""+facils[b]+"\">"+facils[b]+"</option>" );}
                     else{
                     out.println("<option value=\""+facils[b]+"\">"+facils[b]+"</option>" );}
                     }
            

%>
        
    </select></td>

<td><input type="text" required  value="Search.."  readonly style=" cursor:pointer;margin-left: 40px; text-transform:uppercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;"/> </td>
<td align="left"><font color="red"><span id="msg_ancno"></span></font>&nbsp;</td>
             </tr>
        
            </table>
        
    <%
 if(request.getParameter("admNo")!=null){
 String myadmno=request.getParameter("admNo").toString();
 String motherIDs=request.getParameter("motherID").toString();
 session.setAttribute("AdmNO", myadmno);
 %>
 <script type="text/javascript">
   document.getElementById("admNo").value="<%=myadmno%>"; 
   document.getElementById("motherID").value="<%=motherIDs%>"; 
   
       </script>
         <%
}

%>
<!--           table for dispaying table from servlet-->
           <table id="AtoGDets"  class="viewpdt" style=" margin-bottom: 0px; width: 800px;">
               <tr>
                   <td>No</td>    
                   <td>Admission Number</td>    
                   <td>Date of Admission</td>    
                   <td>No. of ANC Visits</td>    
                   <td>First Name</td>    
                   <td>Second Name</td>    
                   <td>Last Name</td>    
                   <td>Village</td>    
                   <td>Age</td>    
                   <td>Marital Status</td>    
               
               </tr>  
               <tr>
                   <td></td>    
                   <td>(a)</td>    
                   <td>(b)</td>    
                   <td>(c)</td>    
                   <td colspan="3">(d)</td>    
                   <td>(e)</td>    
                   <td>(f)</td>    
                   <td>(g)</td>    
               
               </tr> 
         <tr><td>1</td>
                   <td><input class="textbox" style="width:100px;" type="text" name="New_AdmissionNum1" value="" id="New_AdmissionNum1"></td>
                   <td><input class="textbox" style="width:100px;" type="text" name="New_AdmissionDate1" value="" id="New_AdmissionDate1"></td>
                   <td><input class="textbox" style="width:100px;" type="text" name="New_No_Visits1" value="" id="New_No_Visits1"></td>
                   <td><input class="textbox" style="width:100px;"type="text" name="New_FirstName1" value="" id="New_FirstName1"></td>
                   <td><input class="textbox" style="width:100px;" type="text" name="New_SecondName1" value="" id="New_SecondName1"></td>
                   <td><input class="textbox" style="width:100px;" type="text" name="New_LastName1" value="" id="New_LastName1"></td>
                   <td><input class="textbox" style="width:100px;" type="text" name="New_Village1" value="" id="New_Village1"></td>
                   <td><input class="textbox" style="width:100px;" type="text" name="New_Age1" value="" id="New_Age1"></td>
                    <td><select style="width:100px;" name="New_MaritalStatus1" id="New_MaritalStatus1">
                            
                         <%
               
 String arrays[]={" ","Married", "Widowed","Single","Divorced","Separated"};
 for(int i=0;i<arrays.length;i++){


%>
<option value="<%=arrays[i]%>"><%= arrays[i] %></option>
<%
 }
%>
                        </select>
                        
                        
                   <input type="hidden" id="MatRegister_newRows" name="MatRegister_newRows" value="1" > 
                       <input type="hidden" id="MatRegister_old_rows" value="0" name="MatRegister_old_rows" >
             
                           </td>
                   
                   
                   
               </tr>
           </table> 
         		
         		
        </div>

<!-- -----------------------------------------------Step 2 from i to m------------------------------------------------------------>


  			<div id="step-2">
                       
                                     
                     
            <h2 class="StepTitle">MATERNITY REGISTER   H to L <a href="#" id="dialog-link2" ><img src="images/help_24.png"  /> </a><p id="page2rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p></h2>	
            
            <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="addAtoH();"/> 
           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deleteAtoH();"/> 
          
           <!-- ui-dialog -->
<div id="dialog2" title="Columns h to l  Help ">
	<p>
         <% if(Sectionshelp1[1]!=null){%>
         <%=Sectionshelp1[1]%> 
         <%}%>
        </p>
</div>
           
           <table id="HtoLDets"  class="viewpdt" style="margin-left: 120px; width: 900px;">
               <tr>
                   <td>No</td>    
                   <td>Parity</td>    
                   <td>Gravidae</td>    
                   <td>Date of Last Menstrual Period(LMP)</td>    
                   <td>Expected Date of Delivery (EDD)</td>    
                   <td>Diagnosis</td>    
                 
               </tr>  
               <tr>
                   <td></td>    
                   <td>(h)</td>    
                   <td>(i)</td>    
                   <td>(j)</td>    
                   <td>(k)</td>    
                   <td>(l)</td>    
                     
               
               </tr> 
               
               
               
               
               
               
               <tr><td>1</td>
                   <td><input class="textbox" type="text" name="New_Parity1" value="" id="New_Parity1"></td>   
                   <td><input class="textbox" type="text" name="New_Gravida1" value="" id="New_Gravida1"></td>   
                   <td><input class="textbox" type="text" readonly name="New_LMP1" value="" id="New_LMP1"></td>   
                   <td><input class="textbox" type="text" readonly name="New_EDD1" value="" id="New_EDD1"></td>   
                   <td><input type="text" datalist name="New_Diagnosis1" list="conditions"  value="" id="New_Diagnosis1"></td>  
                   
                   <datalist id="conditions">
                       <option value="APH">
                           <option value="PPH">
                               <option value="ECLAMPSIA">
                                   <option value="RUPTURED UTERUS">
                                       <option value="OBSTRUCTED LABOUR">
                                           <option value="SEPSIS">
                                               <option value="LABOUR PAINS">
                                                   </datalist>
                   
                   
               </tr>
               <input type="hidden" id="HtoL" name="HtoL" value="1" > 
               <input type="hidden" id="HtoL_old_rows" value="0" name="HtoL_old_rows" >
             

           </table>             
               
        </div>    
    
    
    <!----------------------------------------------- step 3----------------------------------------------->
    
  			<div id="step-3">
        
                                     <h2 class="StepTitle">MATERNITY REGISTER   M to U<a href="#" id="dialog-link3" >
                                    <img src="images/help_24.png"  /> </a><p id="page3rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p></h2>
            <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick=" addAtoH();"/> 
           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deleteAtoH();"/> 
           
           <!-- ui-dialog -->
<div id="dialog3" title="Columns m to u  Help ">
	<p>
         <% if(Sectionshelp1[2]!=null){%>
         <%=Sectionshelp1[2]%> 
         <%}%>
        </p>
</div>
           <table id="MtoUDets"  class="viewpdt" margin-left: style="margin-left: 50px;">
               <tr><td colspan="10">Delivery</td></tr>
                <tr>
                    <td rowspan="3">No</td>    
                   <td rowspan="2">Duration of Labour</td>    
                   <td rowspan="2">Date of Delivery</td>    
                   <td rowspan="2">Time of Delivery</td>    
                   <td>Gestation at Birth</td>    
                   <td rowspan="2">Mode of Delivery</td>    
                   <td>Placenta Complete</td>    
                   <td>Blood Loss</td>    
                   <td>Condition After Delivery</td>    
                   <td>Other Delivery Complications</td>    
                 
               </tr>  
               <tr>
                    
                      
                      
                   <td>(wks)</td>    
                   <td>(Y/N)</td>    
                   <td>(in mls)</td>    
                   <td>(A/D)</td>    
                   <td>(Codes)</td>    
                     
               
               </tr> 
               <tr>
                   <td>m</td>    
                   <td>n</td>    
                   <td>o</td>    
                   <td>p</td>    
                   <td>q</td>    
                   <td>r</td>    
                   <td>s</td>    
                   <td>t</td>    
                   <td>u</td>    
            
               </tr> 
           
               <tr><td>1</td>
                   <td><input class="textbox" style="width:100px;"type="text" name="New_LabourDuration1" value="" id="New_LabourDuration1"></td>   
                   <td><input class="textbox" style="width:100px;" type="text" name="New_DeliveryDate1" value="" id="New_DeliveryDate1"></td>   
                   <td><input class="textbox" style="width:100px;" type="text" name="New_DeliveryTime1" value="" id="New_DeliveryTime1" class="timepicker"></td>   
                   <td><input class="textbox" style="width:100px;" type="text" name="New_GestationAtBirth1" value="" id="New_GestationAtBirth1"></td> 
                   <td><select style="width:100px;" name="New_DeliveryMode1" id="New_DeliveryMode1">
                           <option value=""></option>
                   <%
                     ArrayList deliveryMode= new ArrayList();
                     ArrayList deliveryModeID= new ArrayList();
              String deliveryQuery ="select * from delivery_mode";
              conn.rs = conn.st.executeQuery(deliveryQuery);
              while(conn.rs.next()){
                  
              deliveryModeID.add(conn.rs.getString(1));
              deliveryMode.add(conn.rs.getString(2));    
              }

 for(int i=0;i<deliveryMode.size();i++){


%>
<option value="<%=deliveryModeID.get(i)%>"><%= deliveryMode.get(i) %></option>
<%
 }
%>
                        </select>
                  </td>   
                        <td>
                            <select style="width:100px;" name="New_PlacentaComplete1" id="New_PlacentaComplete1">
                                <option value=""></option>
                                <option value="Y">Y</option>
                                <option value="N">N</option>
                                
                            </select>
                        </td>   
                   <td><input class="textbox" style="width:100px;" type="text" name="New_BloodLoss1" value="" id="New_BloodLoss1"></td>   
                   <td><select style="width:100px;" name="New_ConditionAfterDelivery1" id="New_ConditionAfterDelivery1">
                           <option value=""></option>
                           <option value="A">A</option>
                           <option value="D">D</option>
                        </select>
                   </td>   
                   <td><input class="textbox" style="width:100px;" type="text" name="New_DeliveryComplications1" value="" id="New_DeliveryComplications1"></td>   
               </tr>
              
               <input type="hidden" id="MtoU" name="MtoU" value="1" > 
               <input type="hidden" id="MtoU_old_rows" value="0" name="MtoU_old_rows" >
             
               
           </table>
            
                       			
        </div>
    <!----------------------------------v to ab       ---------------------------------------------------------->
    
     <div id="step-4">
            <h2 class="StepTitle">MATERNITY REGISTER V to AB   Baby | VDRL/ RPR Results | HIV Status     <a href="#" id="dialog-link4" >
                     <img src="images/help_24.png"  /> </a><p id="page4rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p></h2>	
         <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="addAtoH();" id="addrowstep4_mat"/> 
           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deleteAtoH();" id="delrowstep4_mat"/> 
    
           <!-- ui-dialog -->
        <div id="dialog4" title="Columns x to ad  Help ">
	<p>
         <% if(Sectionshelp1[3]!=null){%>
         <%=Sectionshelp1[3]%> 
         <%}%>
        </p>
</div>
           
           
            <table border="1px" class="viewpdt"  id="avtoabtable" style=" margin-left: 120px;padding-top: 0px;width: 900px;">
              
              </table>
            
            
            
                       			
        </div>
    
    <!------------------------------------------ae to ak  --------------------------->
    <div id="step-5">
        <h2 class="StepTitle">MATERNITY REGISTER AC to AI  ARV Prophylaxis | CTX to Mother | Vitamin A | Partner HIV C& T (ac) to (ai)   <a href="#" id="dialog-link5" >
                                    <img src="images/help_24.png"  /> </a><p id="page5rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p></h2>
            <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="addAtoH();" id="addrowstep6"/> 
           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deleteAtoH();" id="delrowstep6"/> 
           
       
           <!-- ui-dialog -->
<div id="dialog5" title="Columns ac to ai  Help ">
	<p>
         <% if(Sectionshelp1[4]!=null){%>
         <%=Sectionshelp1[4]%> 
         <%}%>
        </p>
</div>
           
              <table border="1px" class="viewpdt"  id="actoaitable" style=" margin-left: 120px;padding-top: 0px;width: 900px;">
              
              </table>
            
                       			
        </div>
    
    
    <div id="step-6">
            <h2 class="StepTitle">MATERNITY REGISTER  AJ to AN Delivery Conducted By | Birth notification number | Discharge | Comments<font color="white"> </font>     <a href="#" id="dialog-link6" >
                                    <img src="images/help_24.png"  /> </a>        <p id="page6rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p></h2>	
            
            <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="addAtoH();"/> <!--
-->           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deleteAtoH();"/> 
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       
            <!-- ui-dialog -->
<div id="dialog6" title="Columns aj to an  Help ">
	<p>
         <% if(Sectionshelp1[5]!=null){%>
         <%=Sectionshelp1[5]%> 
         <%}%>
        </p>
</div>
            
           <table id="ajtoantable"  class="viewpdt" style="width: 900px; margin-left: 120px;"></table> 
           <!--           table for dispaying table from external js wen u press add row and delete row-->
                        			
        </div>
                       			
        </div>
    
    
    <script>
        getAtoGDetails();
        
    </script>
    
    
  		</div>		
        
<!-- End SmartWizard Content -->  		
</div>		
</td></tr></table> 
          
     </form>
 </div>
        
 
    
</body>
</html>
