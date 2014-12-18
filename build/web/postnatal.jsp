<%-- 
    Document   : postnatal
    Created on : Apr 29, 2014, 9:05:02 PM
    Author     : Maureen
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="sender.dbConn"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="sendSMS.dbConnect"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Postnatal Register</title>

<%!

String facility="";
 
 String admNo="";
 
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
<script type="text/javascript" src="js/validatepostnatal.js"></script>
<script type="text/javascript" src="js/getPNC_AtoF.js"></script>
<script type="text/javascript" src="js/getPNC_GtoM.js"></script>
<script type="text/javascript" src="js/getPNC_NtoT.js"></script>

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
  myCalendar = new dhtmlXCalendarObject(["ajan_new_date1","ajan_old_date1"]);

}


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




         
         <!--======EXTRNAL JAVASCRIPTS=====-->
         
          <!--======EXTRNAL JAVASCRIPTS=====-->
          
          <!--======HELP MESSAGES JS========-->
          
          <script type="text/javascript" src="js/anchelp.js"> </script>
          

 <!--=======STEP2DETAILS=====-->
<script type="text/javascript" src="js/postnatal_AddHtoL.js"/></script>
 <!--=======STEP3DETAILS=====-->
 <script type="text/javascript" src="js/postnatal_AddMtoU.js"></script>


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
<script src="js/postnatalAtoF.js" type="text/javascript"></script>         
<!--    getting values from h to l       -->
<script src="js/postnatalGtoM.js" type="text/javascript"></script>         
<!--    getting values from m to u       -->
<script src="js/postnatalNtoT.js" type="text/javascript"></script>         
<script src="js/postnatal_NtoT.js" type="text/javascript"></script>         
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
    newCalendar = new dhtmlXCalendarObject(["AdmissionDate1","AdmissionDate2","AdmissionDate3","AdmissionDate4","AdmissionDate5","New_AdmissionDate1","New_AdmissionDate2","New_AdmissionDate3","New_AdmissionDate4","LMP1","LMP2","LMP3","LMP4","LMP5","EDD1","EDD2","EDD3","EDD4","New_LMP1","New_LMP2","New_LMP3","New_EDD1","New_EDD2","New_EDD3","New_DeliveryDate1","New_DeliveryDate2","New_DeliveryDate3"]);
   
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



    
</script>
<script>
 
var myCalendar;
function doOnLoad() {

    myCalendar = new dhtmlXCalendarObject(["New_VisitDate1","New_VisitDate2","New_VisitDate3","New_VisitDate4","New_VisitDate5","New_VisitDate6","New_VisitDate7","New_DeliveryDate1","New_DeliveryDate2","New_DeliveryDate3","DeliveryDate1","DeliveryDate2","DeliveryDate3","DeliveryDate4"
        ]);
    
}

 function resetAtoF(){
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
       
      
function addAtoF() {
   if(verifiers==1){
                j =parseInt(document.getElementById("PNCRegister_old_rows").value)+(parseInt(1));
                b=parseInt(document.getElementById("PNCRegister_newRows").value);
                  
                          }
            
             b++;
             
             
//             alert("j  "+j);
//             alert("b  "+b);
          verifiers++;
          verifiers1++;  
            
 
    var table = document.getElementById("AtoFDets");
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
    element1.name = "New_VisitDate"+b;
    element1.id = "New_VisitDate"+b;
    element1.readOnly = true;
    
      element1.className = "textbox";
    element1.style.width = "100px";
    element1.value ="";
    cell2.appendChild(element1);
            
   
    var cell3 = row.insertCell(2);
     cell3.style.width = "80px";
    var element2 = document.createElement("input");//create an input field of type text and assign names, ids and functions
    element2.type = "text";
    element2.value = "";
    element2.name = "New_PNCNo"+b;
    element2.id = "New_PNCNo"+b;
      element2.className = "textbox";
    element2.style.width = "100px";
//    element1.style.width = "200px";
    cell3.appendChild(element2);
    
    var cell4 = row.insertCell(3);//create an input field of type text,class name, width and assign names, ids and functions
    cell4.style.width = "80px";
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.name = "New_AdmNo"+b;
    element3.id = "New_AdmNo"+b;
      element3.className = "textbox";
    element3.value ="";
    element3.style.width = "110px";
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
    cell9.style.width = "110px";
    var element8 = document.createElement("input");
    element8.type = "text";
    element8.name = "New_Age"+b;
    element8.id ="New_Age"+b;
    element8.className = "textbox";
    element8.value ="";
    element8.style.width = "100px";
    cell9.appendChild(element8);
    
   

      doOnLoad();      
      doOnLoader();


addGtoM();
addNtoT();

addrowstep4postnat('utoztable');


    j++;
   document.getElementById("PNCRegister_newRows").value=b;
 
}
 
        function deleteAtoF() {
       
          //if the addrow has not been called  
        
            
            if((verifiers1==1)){
               j =parseInt(document.getElementById("PNCRegister_old_rows").value)+(parseInt(1));
               b=parseInt(document.getElementById("PNCRegister_newRows").value);
            //alert(""+document.getElementById("no_of_old_rows").value);        
                           }
                     
          verifiers1++;
          verifiers++;
            
            
            try {
                
            
            var table = document.getElementById("AtoFDets");
            //delete the last row 
            
             
            
            var rowCount = table.rows.length;
           //  alert(""+rowCount);
 //alert(rowCount);
           // for(var a=0; a<rowCount; a++) {
            //    var row = table.rows[a];
              //  var chkbox = row.cells[0].childNodes[0]; 
              if(rowCount>=parseInt(document.getElementById("PNCRegister_old_rows").value)+parseInt(4)){
                  
                    table.deleteRow(rowCount-1);
                    rowCount--;
                    
                    //reduce the number of rows           
                    j--;
                    b--;
                   document.getElementById("PNCRegister_newRows").value=b;
                   
                     
              }
              else{
                alert("Maximum deletable columns reached!");  
                  
                  
              }
  
            }catch(e) {
                alert(e);
            }
              
              deleteGtoM();
            deleteNtoT();
          delrowstep4postnat('utoztable')
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

//=================================================================================================================
//========================================MY JS FUNCTIONS==========================================================
//=================================================================================================================
   
   function getutozdetails(){
var motherid;
if(document.getElementById("motherID")!=null && document.getElementById("motherID")!=""){
  motherid=document.getElementById("motherID").value;    
}


  if( motherid=="")
  {
      
      motherid="_";
      
  }

// window.open("districtchooser?county="+dist.value);  



var xmlhttp;    
if (motherid=="")
{
//filter the districts    



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
document.getElementById("utoztable").innerHTML=xmlhttp.responseText;


//alert(xmlhttp.responseText);


}
}
xmlhttp.open("POST","utoz?motherid="+motherid,true);
xmlhttp.send();

getaatoaedetails();
getaftoaidetails();


}

    
   
function getaatoaedetails(){
     

  var motherid;
if(document.getElementById("motherID")!=null && document.getElementById("motherID")!=""){
  motherid=document.getElementById("motherID").value;    
}  

// window.open("districtchooser?county="+dist.value);  



var xmlhttp;    
if (motherid=="")
{
//filter the districts    

  motherid="_";
      
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
document.getElementById("aatoaetable").innerHTML=xmlhttp.responseText;


 
}
}
xmlhttp.open("POST","aatoae?motherid="+motherid,true);
xmlhttp.send();



}//end of filter districts

   
function getaftoaidetails(){
     

 var motherid;
if(document.getElementById("motherID")!=null && document.getElementById("motherID")!=""){
  motherid=document.getElementById("motherID").value;    
}

// window.open("districtchooser?county="+dist.value);  



var xmlhttp;    
if (motherid=="")
{
//filter the districts    

  motherid="_";
      


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
document.getElementById("aftoaitable").innerHTML=xmlhttp.responseText;

loadcalender();
 


}
}
xmlhttp.open("POST","aftoai?motherid="+motherid,true);
xmlhttp.send();


}//end of filter districts

//=================================================================================================================
//========================================END OF MY JS FUNCTIONS===================================================
//=================================================================================================================
   

    $(document).ready(function(){
    	// Smart Wizard	
  		$('#wizard').smartWizard({transitionEffect:'slide'});
     
		});
</script>

   <script type="text/javascript" src="js/addstep4postnat.js"> </script> 
   <script type="text/javascript" src="js/addstep5postnat.js"> </script> 
   <script type="text/javascript" src="js/addstep6postnat.js"> </script> 
  </head><body onload="return doOnLoader();doOnload();" >
    
 <div id="container" style="width:1300px; height: 860px;">
     
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
           
     
    <form action="savePostNatRegister" method="POST" name="myform" style="background:#ffffff; margin:10px ;"> 
<table align="center" border="0" cellpadding="0" cellspacing="0">
    <tr><td>
<!-- Smart Wizard -->
      
<div id="wizard" style="width:1180px;" class="swMain" >
  			<ul>
                      <!---------------------------STEP1------------------------------------->      

                      <li style="width:175px;margin-right: 10px ;"><a href="#step-1">
                                    <label class="stepNumber">1</label>
                                    <span class="stepDesc">
                                        (a) to (g)<br />
                                        <small>a,b,c,d,e,f,g</small>
                                    </span>
                                </a></li>
                            
                        <!---------------------------STEP2------------------------------------->        
                            
                            <li style="width:175px;margin-right: 12px ;"><a href="#step-2">
                                    <label class="stepNumber">2</label>
                                    <span class="stepDesc">
                                        (h) to (m)<br />
                                        <small>h,i,j,k,l,m </small>
                                    </span>
                                </a></li>
                            
                         <!---------------------------STEP3------------------------------------->       
                            
                            <li style="width:175px;"><a href="#step-3">
                                    <label class="stepNumber">3</label>
                                    <span class="stepDesc">
                                        (n) to (t)<br />
                                        <small>n,p,q,r,s,t</small>
                                    </span>                   
                                </a></li>
                         
                          <!---------------------------STEP4------------------------------------->   
                       
                          
                          
                           <!---------------------------STEP5------------------------------------->  
                           
                        <li style="width:175px;"><a href="#step-4">
                                  <label class="stepNumber">4</label>
                                  <span class="stepDesc">(u) to(z)<br/> <small>HIV Status<br/> Prophylaxis</small>
                                  </span>                   
                              </a></li>   
                           
                           
                           <!---------------------------STEP6------------------------------------->  
                           
                        <li style="width:175px;"><a href="#step-5">
                                  <label class="stepNumber">5</label>
                                  <span class="stepDesc">(aa)to(ae)<br/><small></small>
                                  </span>                   
                              </a></li>   
                           
                           
                        <li><a href="#step-6">
                                  <label class="stepNumber">6</label>
                                  <span class="stepDesc">(af)to(ai)<br/><small></small>
                                  </span>                   
                              </a></li>    
                           
                           
           <%
           
String Sectionshelp1[]=new String [6];
int mcount=0;
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
   
<div id="step-1" >	
          
          
            <h2 class="StepTitle"> POST NATAL REGISTER (version  ) STEP A TO F <a href="#" id="dialog-link1" >
                                    <img src="images/help_24.png"  /> </a><p id="page1rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p></h2>
    
    
    
    <table>
       <tr>

<td> PNC NO.</td><td style="width:12px;"><input type="text" readonly  name="PNCNo" id="PNCNo" title="Dont enter any value here" required pattern="^d{4}-d{2}-d{4}$"/><input type="hidden" name="motherID" id="motherID" value=""></td>

<td><input type="hidden" required  value="Search.."  readonly style=" cursor:pointer;margin-left: 40px; text-transform:uppercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="getAtoFDetails();"/> </td>

            
      <%
      
      String[] facilities={"","Solian","","Ngubereti","","Emining","","Torongo","","Timboroa","","Simotwet"};
 if(request.getParameter("pncNo")!=null){
 String mypncno=request.getParameter("pncNo").toString();
 String motherIDs=request.getParameter("motherID").toString();
 session.setAttribute("PNCNo", mypncno);
 
 String queryfacility="select * from mother_Details where motherid='"+motherIDs+"'";
 conn.rs3 = conn.st.executeQuery(queryfacility);
while(conn.rs3.next()){
 facility=conn.rs3.getString("facilityname");
}
 if(facility.equalsIgnoreCase("Ngubureti")){facility="Ngubereti";}
       if(facility.equalsIgnoreCase("Emining")){facility="Emining";}
 
 System.out.println("");
 
 %>
 <script type="text/javascript">
     
     
     
   document.getElementById("PNCNo").value="<%=mypncno%>"; 
   document.getElementById("motherID").value="<%=motherIDs%>"; 
   
   //getutozdetails();
       </script>
         <%
}else{%>
<script>

//getutozdetails();
</script>
<%
}

%>     
          
<td>Select a Facility</td>

<td><select name="facility" id="facility" required>
        <option value=""></option> 
        <%
        
        for(int p=0;p<facilities.length;p++){
if( facility.equals(facilities[p]) )  {     
%>
        
        <option selected value="<%=facilities[p]%>"><%=facilities[p]%></option> 
      <%}else{%> 
      
        <option value="<%=facilities[p]%>"><%=facilities[p]%></option> 
      
        <%}}%>
    </select></td>
          </tr>
</table>
<input type="text" value="add row" id="addrowstep1" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick=" addAtoF();doOnLoader();"/>
<input type="text" value="delete last row" id="delrowstep1" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deleteAtoF();"/>

<!-- ui-dialog -->
<div id="dialog1" title="Columns a to g  Help ">
	<p>
         <% if(Sectionshelp1[0]!=null)
         {%>
         <%=Sectionshelp1[0]%> 
         <%}%>
        </p>
</div>

            <table border="1px" class="viewpdt" align="center" style="margin-left: 120px;" >


  

        
            </table>
<!--           table for dispaying table from servlet-->
           <table id="AtoFDets" class="viewpdt" style=" margin-bottom: 0px; width: 1100px;">
               
               <tr><td colspan="9">Registration Information</td></tr>
               <tr>
                   <td>No</td>    
                   <td>Date of Visit</td>    
                   <td>PNC Register Number</td>    
                   <td>Admission No.</td>    
                   <td>First Name</td>    
                   <td>Second Name</td>    
                   <td>Last Name</td>    
                   <td>Village</td>    
                   <td>Age</td>    
                    
               
               </tr>  
               <tr>
                   <td></td>    
                   <td>(a)</td>    
                   <td>(b)</td>    
                   <td>(c)</td>    
                   <td colspan="3">(d)</td>    
                   <td>(e)</td>    
                   <td>(f)</td>    
                   
               
               </tr> 
         <tr><td>1</td>
                   <td><input class="textbox" style="width:110px;" readOnly type="text" name="New_VisitDate1" value="" id="New_VisitDate1"></td>
                   <td><input class="textbox" style="width:110px;" type="text" name="New_PNCNo1" value="" id="New_PNCNo1"></td>
                   <td><input class="textbox" style="width:110px;" type="text" name="New_AdmNo1" value="" id="New_AdmNo1"></td>
                   <td><input class="textbox" style="width:110px;"type="text" name="New_FirstName1" value="" id="New_FirstName1"></td>
                   <td><input class="textbox" style="width:110px;" type="text" name="New_SecondName1" value="" id="New_SecondName1"></td>
                   <td><input class="textbox" style="width:110px;" type="text" name="New_LastName1" value="" id="New_LastName1"></td>
                   <td><input class="textbox" style="width:110px;" type="text" name="New_Village1" value="" id="New_Village1"></td>
                   <td><input class="textbox" style="width:110px;" type="text" name="New_Age1" value="" id="New_Age1"></td>
                 
                    <input type="hidden" id="PNCRegister_newRows" name="PNCRegister_newRows" value="1" > 
                       <input type="hidden" id="PNCRegister_old_rows" value="0" name="PNCRegister_old_rows" >
             
                           
                   
                   
                   
               </tr>
           </table> 
         		
        </div>

<!-- -----------------------------------------------Step 2 from i to m------------------------------------------------------------>


  			<div id="step-2">
                       
                                     
       <!-----    I to P           ---->                     
                            
            <h2 class="StepTitle">h to l <a href="#" id="dialog-link2" ><img src="images/help_24.png"  /> </a>  <p id="page2rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p></h2>	
            
            <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="addAtoF();"/> 
           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deleteAtoF();"/> 
          
           <!-- ui-dialog -->
<div id="dialog2" title="Columns h to l  Help ">
	<p>
         <% if(Sectionshelp1[1]!=null){%>
         <%=Sectionshelp1[1]%> 
         <%}%>
        </p>
</div>
           
           <table id="GtoMDets"  class="viewpdt" style="margin-left: 120px; width: 900px;">
               
               <tr><td></td>
                   <td colspan="3">Maternity History</td>
                   <td>Maternity History</td>
                   <td colspan="3">Vital Signs</td>
                   
               </tr>
               <tr>
                   <td>No</td>    
                   <td>Date of Delivery</td>    
                   <td>Place of Delivery</td>    
                   <td>Mode of Delivery</td>    
                   <td>State of baby</td>    
                   <td>Temperature</td>    
                   <td>Pulse</td>    
                   <td>Blood Pressure</td>    
                 
               </tr>  
               <tr>
                     
                   <td></td>    
                   <td>(g)</td>    
                   <td>(h)</td>    
                   <td>(i)</td>    
                   <td>(j)</td>    
                   <td>(k)</td>    
                   <td>(l)</td>    
                   <td>(m)</td>    
                     
               
               </tr> 
               
               
               
               
               
               
               <tr><td>1</td>
                   <td><input class="textbox" type="text" readonly name="New_DeliveryDate1" value="" id="New_DeliveryDate1"></td>   
                   <td><input class="textbox" type="text" name="New_DeliveryPlace1" value="" id="New_DeliveryPlace1"></td>   
                   <td><input class="textbox" type="text" name="New_DeliveryMode1" value="" id="New_DeliveryMode1"></td>   
                   <td><input class="textbox" type="text" name="New_BabyStatus1" value="" id="New_BabyStatus1"></td>   
                   <td><input class="textbox" type="text" name="New_Temp1" value="" id="New_Temp1"></td>   
                   <td><input class="textbox" type="text" name="New_Pulse1" value="" id="New_Pulse1"></td>   
                   <td><input class="textbox" type="text" name="New_BP1" value="" id="New_BP1"></td>   
                 
               </tr>
               <input type="hidden" id="GtoM" name="GtoM" value="1" > 
               <input type="hidden" id="GtoM_old_rows" value="0" name="GtoM_old_rows" >
             

           </table>         
               
        </div>    
    
    
    <!----------------------------------------------- step 3----------------------------------------------->
    
  			<div id="step-3">
        
                                    <h2 class="StepTitle">N to T<a href="#" id="dialog-link3" >
                                    <img src="images/help_24.png"  /> </a><p id="page3rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p></h2>
            <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="addAtoF();"/> 
           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deleteAtoF();"/> 
           
           <!-- ui-dialog -->
<div id="dialog3" title="Columns m to u  Help ">
	<p>
         <% if(Sectionshelp1[2]!=null){%>
         <%=Sectionshelp1[2]%> 
         <%}%>
        </p>
</div>
           <table id="NtoTDets"  class="viewpdt" margin-left: style="margin-left: 50px;">
               <tr><td colspan="10">Post Natal Examination</td></tr>
                <tr>
                   <td>No</td>    
                   <td>Parlor</td>    
                   <td>Breast</td>    
                   <td>Uterus</td>    
                   <td>PPH</td>    
                   <td>C-Section Site</td>    
                   <td>Lochial</td>    
                   <td>Episiotiomy</td>    
                     
                 
               </tr>  
                
               <tr>
                   <td></td>    
                 
                   <td>n</td>    
                   <td>o</td>    
                   <td>p</td>    
                   <td>q</td>    
                   <td>r</td>    
                   <td>s</td>    
                   <td>t</td>    
                  
            
               </tr> 
           
               <tr><td>1</td>
                   <td>
                       <select style="width:100px;" name="New_Parlor1" id="New_Parlor1">
                                <option value=""></option>
                                <option value="1=Mild">1=Mild</option>
                                <option value="2=Moderate">2=Moderate</option>
                                <option value="3=Severe">3=Severe</option>
                                
                            </select>  
<!--                       <input class="textbox" style="width:100px;"type="text" name="New_Parlor1" value="" id="New_Parlor1">-->
                   </td>   
                   <td>
                       
                        <select style="width:100px;" name="New_Breast1" id="New_Breast1">
                                <option value=""></option>
                                <option value="1=Normal">1=Normal</option>
                                <option value="2=Cracked Nipple">2=Cracked Nipple</option>
                                <option value="3=Engorged">3=Engorged</option>
                                <option value="4=Mastistis">4=Mastistis</option>
                                
                            </select>
                       
<!--                       <input class="textbox" style="width:100px;" type="text" name="New_Breast1" value="" id="New_Breast1">-->
                   </td>   
                   <td><input class="textbox" style="width:100px;" type="text" name="New_Uterus1" value="" id="New_Uterus1" class="timepicker"></td>   
                   <td><input class="textbox" style="width:100px;" type="text" name="New_PPH1" value="" id="New_PPH1"></td> 
                 
                   <td>
                          <select style="width:100px;" name="New_CSection1" id="New_CSection1">
                                <option value=""></option>
                                <option value="1=Bleeding">1=Bleeding</option>
                                <option value="2=Normal">2=Normal</option>
                                <option value="3=Infected">3=Infected</option>
                              
                            </select>
                       
<!--                       <input class="textbox" style="width:100px;" type="text" name="New_CSection1" value="" id="New_CSection1">-->
                   </td>   
                    
                   <td>
<!--                       <input class="textbox" style="width:100px;" type="text" name="New_Lochial1" value="" id="New_Lochial1">-->
                               <select style="width:100px;" name="New_Lochial1" id="New_Lochial1">
                                <option value=""></option>
                                <option value="1=Normal">1=Normal</option>
                                <option value="2=Foul Smelling Excessive">2=Foul Smelling Excessive</option>
                                
                            </select>
                   </td>   
                   <td>
                       
                       
                        <select style="width:100px;" name="New_Episitiomy1" id="New_Episitiomy1">
                                <option value=""></option>
                                <option value="1=Repaired">1=Repaired</option>
                                <option value="2=Gaping">2=Gaping</option>
                                <option value="3=Infected">3=Infected</option>
                                <option value="4=Healed">4=Healed</option>
                                
                            </select>
<!--                       <input class="textbox" style="width:100px;" type="text" name="New_Episitiomy1" value="" id="New_Episitiomy1">-->
                   </td>   
               </tr>
              
               <input type="hidden" id="NtoT" name="NtoT" value="1" > 
               <input type="hidden" id="NtoT_old_rows" value="0" name="NtoT_old_rows" >
             
               
           </table>
            
                       			
        </div>
    <!----------------------------------v to ab       ---------------------------------------------------------->
        <!----------------------------------utoz       ---------------------------------------------------------->
  
     <div id="step-4">
            <h2 class="StepTitle">HIV Status |Prophylaxis  (u) to (z)    <a href="#" id="dialog-link4" >
                     <img src="images/help_24.png"  /> </a><p id="page4rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p></h2>	
         <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="addrowstep4postnat('utoztable');" id="addrowstep4_pt"/> 
           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="delrowstep4postnat('utoztable');" id="delrowstep4_pt"/> 
    
           <!-- ui-dialog -->
        <div id="dialog4" title="Columns u to z  Help ">
	<p>
         <% if(Sectionshelp1[3]!=null){%>
         <%=Sectionshelp1[3]%> 
         <%}%>
        </p>
</div>
           
           
            <table border="1px" class="viewpdt"  id="utoztable" style=" margin-left: 120px;padding-top: 0px;width: 900px;">
              
              </table>
            
        </div>
    
    <!------------------------------------------ae to ak  --------------------------->
    <div id="step-5">
        <h2 class="StepTitle"> Partner HIV C& T | Screened for Cervical Cancer | Modern Fp (aa) to (ae)   <a href="#" id="dialog-link5" >
                                    <img src="images/help_24.png"  /> </a><p id="page5rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p></h2>
            <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="addrowstep4postnat('utoztable');" id="addrowstep6"/> 
           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="delrowstep4postnat('utoztable');" id="delrowstep6"/> 
           
       
           <!-- ui-dialog -->
<div id="dialog5" title="Columns ac to ai  Help ">
	<p>
         <% if(Sectionshelp1[4]!=null){%>
         <%=Sectionshelp1[4]%> 
         <%}%>
        </p>
</div>
           
              <table border="1px" class="viewpdt"  id="aatoaetable" style=" margin-left: 120px;padding-top: 0px;width: 900px;">
              
              </table>
            
                       			
        </div>
    
    
    <div id="step-6">
            <h2 class="StepTitle"> Treatment | Referred | Remarks<font color="white"> (af)-(ai)</font>     <a href="#" id="dialog-link6" >
                                    <img src="images/help_24.png"  /> </a>        <p id="page6rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p></h2>	
            
            <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick=" addrowstep4postnat('utoztable');"/> <!--
-->           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="delrowstep4postnat('utoztable');"/> 
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       
            <!-- ui-dialog -->
<div id="dialog6" title="Columns af to ai  Help ">
	<p>
         <% if(Sectionshelp1[5]!=null){%>
         <%=Sectionshelp1[5]%> 
         <%}%>
        </p>
</div>
            
           <table id="aftoaitable"  class="viewpdt" style="width: 900px; margin-left: 120px;"></table> 
           <!--           table for dispaying table from external js wen u press add row and delete row-->
                        			
        </div>
                       			
        </div>
    
    
    
    
    
  		</div>		
        
<!-- End SmartWizard Content -->  		
</div>		
</td></tr></table> 
    <script>
            getutozdetails();
          getAtoFDetails();
                           </script>        
     </form>
 </div>
        
 
    
</body>
</html>
