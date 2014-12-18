<%-- 
    Document   : index
    Created on : Aug 5, 2013, 9:03:18 PM
    Author     : SIXTYFOURBIT
--%>

<%@page import="sendSMS.dbConnect"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SEND GENERAL SMS</title>
         <link rel="stylesheet" type="text/css" href="css/divCss.css"/>
          <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         
        <link rel="stylesheet" href="menu/adminmenu_files/css3menu1/style.css" type="text/css" /><style type="text/css">._css3m{display:none}</style>
         <link rel="shortcut icon" href="images/icon.png"/>
          <link rel="stylesheet" type="text/css" href="css/clerkmenucss.css"/>
    
    
       <link href="js/css/ui-lightness/jquery-ui-1.10.3.custom.css" rel="stylesheet">
	<script src="js/jquery-1.9.1.js"></script>
	<script src="js/jquery-ui-1.10.3.custom.js"></script>
      

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
<script type="text/javascript" src="js/noty/themes/default.js"></script> 

<script type="text/javascript">
// a function that filters the id in the passed county as per the county drop down.
    
function sendMsg(){
    
      var x = document.getElementById("status");
  
   var y = x.options[x.selectedIndex].value;
  
//   alert("status"+y);

    
 var xmlhttp;  
// alert(ids)
   
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
//    document.getElementById("msg").innerHTML=xmlhttp.responseText;
//document.getElementById("baselinew").innerHTML=xmlhttp.responseText;
//document.getElementById("baselinem").innerHTML=xmlhttp.responseText;
//document.getElementById("targetw").innerHTML=xmlhttp.responseText;
//document.getElementById("targetm").innerHTML=xmlhttp.responseText;
//document.getElementById("endtargetm").innerHTML=xmlhttp.responseText;
//document.getElementById("endtargetw").innerHTML=xmlhttp.responseText;

//    window.location='Results.jsp';
//document.getElementById("titleNo").innerHTML=xmlhttp.responseText;
}
}


xmlhttp.open("POST","/MHC/retrieveSMS?status="+y,true);

xmlhttp.send();
    
 
  
 } 
 </script>
             <script type="text/javascript">
// a function that filters the id in the passed county as per the county drop down.
    
function checkCat(){
    
      var j = document.getElementById("category");
  
   var k = j.options[j.selectedIndex].value;
  
//   alert("category"+k);

    
 var xmlhttp;  
// alert(ids)

   
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
 document.getElementById("msgs_0").innerHTML=xmlhttp.responseText;
//document.getElementById("baselinew").innerHTML=xmlhttp.responseText;
//document.getElementById("baselinem").innerHTML=xmlhttp.responseText;
//document.getElementById("targetw").innerHTML=xmlhttp.responseText;
//document.getElementById("targetm").innerHTML=xmlhttp.responseText;
//document.getElementById("endtargetm").innerHTML=xmlhttp.responseText;
//document.getElementById("endtargetw").innerHTML=xmlhttp.responseText;

//    window.location='Results.jsp';
//document.getElementById("titleNo").innerHTML=xmlhttp.responseText;
}
}


xmlhttp.open("POST","/MHC/retrieveSMS?category="+k,true);

xmlhttp.send();
 sendMsg() 
  
 } 
 </script>
 


<script type="text/javascript">
function validate(){
    var msgs = document.getElementById('msg_0');
    if (msgs.checked){
        alert("checked") ;
    
   
     alert("msg"+ g);
       

    
//  var o = document.getElementById("status");
//  var m = o.options[o.selectedIndex].value
//  alert("status to db"+m);
//  
  var g=document.getElementById("msgs_0").textContent;
  alert("msg"+g);

    
 var xmlhttp;  
// alert(ids)
   
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

}
}


xmlhttp.open("POST","/MHC/sendSMS?msg="+g,true);

xmlhttp.send();

  
 

    
  var o = document.getElementById("status");
  var m = o.options[o.selectedIndex].value
  alert("status to db"+m);
  
 
 var xmlhttp;  
// alert(ids)
   
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
//    document.getElementById("msgs_0").innerHTML=xmlhttp.responseText;
//document.getElementById("baselinew").innerHTML=xmlhttp.responseText;
//document.getElementById("baselinem").innerHTML=xmlhttp.responseText;
//document.getElementById("targetw").innerHTML=xmlhttp.responseText;
//document.getElementById("targetm").innerHTML=xmlhttp.responseText;
//document.getElementById("endtargetm").innerHTML=xmlhttp.responseText;
//document.getElementById("endtargetw").innerHTML=xmlhttp.responseText;

//    window.location='Results.jsp';
//document.getElementById("titleNo").innerHTML=xmlhttp.responseText;
}
}


xmlhttp.open("POST","/MHC/sendSMS?status="+m,true);

xmlhttp.send();
  

    }else{
        alert("You didn't check it! Let me check it for you.")
    }
}
</script>
<script type="text/javascript">
      $(function() {
$( document ).tooltip();

}); 
</script>

 <script type="text/javascript">
  function anyCheckboxesChecked() {
    
  var inputs = document.getElementsByTagName('input');
 
  for (var i = 0; i < inputs.length; ++i) {
    if (inputs[i].type === "checkbox" && inputs[i].checked)
      return true;
  }
  return false;
}
</script>
<script type="text/javascript">
function chekBox(){
   if (!anyCheckboxesChecked()) {
     alert("Please select an SMS to Send");
     return false;
   }
        }
        
    </script>
    <style>
        
        
        input.checkbox:focus{
     border-color:#cc66ff ;
    -webkit-transform: scale(1.1);
    -moz-transform: scale(1.1);
    transform: scale(1.1);
    -moz-box-shadow: 5px 3px 1px activecaption;
    -webkit-box-shadow: 5px 3px 1px activecaption;
    box-shadow: 7px 7px 2px activecaption;
    text-shadow:1px 1px 3px #777;
}

textarea:focus{
     border-color:#cc66ff ;
    -webkit-transform: scale(1.1);
    -moz-transform: scale(1.1);
    transform: scale(1.1);
    -moz-box-shadow: 5px 3px 1px activecaption;
    -webkit-box-shadow: 5px 3px 1px activecaption;
    box-shadow: 7px 7px 2px activecaption;
    text-shadow:1px 1px 3px #777;
}
select:focus{
     border-color:#cc66ff ;
    -webkit-transform: scale(1.1);
    -moz-transform: scale(1.1);
    transform: scale(1.1);
    -moz-box-shadow: 5px 3px 1px activecaption;
    -webkit-box-shadow: 5px 3px 1px activecaption;
    box-shadow: 7px 7px 2px activecaption;
    text-shadow:1px 1px 3px #777;
}
textarea{
    padding:3px 3px;
    -webkit-border-radius: 7px;
    -moz-border-radius: 7px;
    border-radius: 7px;
    border:1px solid #fff;
   width: 125px;
    height: 60px;
    text-shadow:1px 1px 1px #777;
    -moz-box-shadow: 0px 2px 0px #999;
    -webkit-box-shadow: 0px 2px 0px #999;
    box-shadow: 0px 2px 0px #999;
    -webkit-transition: all 0.5s ease-in-out;
    -moz-transition: all 0.5s ease-in-out;
    transition: all 0.5s ease-in-out;
}
select{
    padding:3px 3px;
    -webkit-border-radius: 7px;
    -moz-border-radius: 7px;
    border-radius: 7px;
    border:1px solid #fff;
   width: 150px;
    height: 30px;
    text-shadow:1px 1px 1px #777;
    -moz-box-shadow: 0px 2px 0px #999;
    -webkit-box-shadow: 0px 2px 0px #999;
    box-shadow: 0px 2px 0px #999;
    -webkit-transition: all 0.5s ease-in-out;
    -moz-transition: all 0.5s ease-in-out;
    transition: all 0.5s ease-in-out;
}
    </style>
    </head>
    
    <!-- Body page -->
    
       
       
    <body>
        <div id="container" style="width:1300px; height: 400px;" >
 
              <div id="header" align="center" style="width:1000px; ">
                 
                                
     <ul id="menu">
	<li><a href="maternal_profile_1.jsp">Mother-Baby Book</a></li>
        <li><a href="ANCRegister.jsp">ANC Register</a></li>
        <li><a href="#">Maternity Register</a> </li>
	<li>
		<a href="#">ANC Daily Visits</a>
		<ul>
			<li><a href="../MHC/MothersDiary">Expected visitors</a></li>
			
                        
			<li><a href="UpdateDiary.jsp">Update Visits</a></li>
			
		</ul>
	</li>
	<li><a href="#">SMS</a>
        <ul>
			<li><a href="sendGeneralSms.jsp">Send General Sms</a></li>
			<li><a href="#">Sms schedule settings</a></li>
                        <li><a href="createSMS.jsp">Create SMS</a></li>
			
		</ul>
        </li>
	<li><a href="#">Help</a></li>
	<li><a href="logout.jsp">Log out</a></li>
</ul>
              
           
                  
           
                    
                    
                    
                    
                    
              </div>
            
            
            <div id="content"   style=" padding-top:50px; width:1300px ;height:300px;">
            <%if (session.getAttribute("warnings") != null) { %>
       <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("warnings")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 1800});
                    
         </script> <%
                session.removeAttribute("warnings");
                            }
                       
                       %>
                     
              
<!--                <div id="midcontent" style="margin-left:130px ; padding-top:50px; padding-left: 100px; width: 700px">-->
                

<form action="sendMsg" method="post" style="background-color: #ffffff;" onsubmit="chekBox();">
        <table class="viewpdt">

           
              <tr><th>STATUS</th>
                  <th>
                      <!--select the status of a mother-->
                    <select id="status" name="status" onchange="sendMsg()" >
                        <option  value=""></option>  
                        <option value="1">Pregnant</option>  
                        <option value="2">Post-Natal</option>  
                        
                    </select></th></tr>
              <tr><th >CATEGORY</th>
                  <th>
                      <select id="category" name="category" onchange="checkCat()" >
                        <option value=""></option> 
<!--          retrieve category from the db            -->
  <% 
        dbConnect conn = new dbConnect();     
                                                      String Query= "SELECT * FROM message_category";
                                                      
                                                    
                                                     
				conn.rs = conn.state.executeQuery(Query);
                                                      while(conn.rs.next())
                                                           {
                                                   %>                                                                       
            <option value='<%=conn.rs.getInt("cat_id")%>'><%=conn.rs.getString("category")%></option>
                                                   <%
                                                      }
                               
                                                   %>
<!--                        <option value="1">Pregnant</option>  -->
                        <!--<option value="2">Not Pregnant</option>-->  
                        
                    </select></th>
              </tr>
        </table>
<!--            <tr><td>Specify Number</td><td><input type="text" id="phoneNo" name="phoneNo"></td></tr>-->

<table id="msgs_0" class="viewpdt"></table>
<table>
<tr><td><input type="submit" value="submit" name="submit" class="submit" id="submit"  ></td></tr>
            
        </table>
        </form>
             </div>
            </div>

            
<!--
             <div id="footer">
                <h2 align="left"> <img src="images/Coat of arms.JPG" alt="logo" height="76px" /></h2>
              
               <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center"> &copy Aphia Plus | USAID <%=year%></p>
            </div>-->
<!--        </div>    -->
        
    </body>
</html>
