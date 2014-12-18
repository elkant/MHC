<%-- 
    Document   : filterMessage
    Created on : Nov 12, 2013, 9:50:28 AM
    Author     : Maureen
--%>


<%@page import="sendSMS.dbConnect"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
HttpSession session;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FILTER SMS</title>
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
    
function filtercategory(){
    
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
document.getElementById("category").innerHTML=xmlhttp.responseText;

}
}


xmlhttp.open("POST","filtercategory?status="+y,true);

xmlhttp.send();
    
 document.getElementById("category").innerHTML="loading..";
  
 } 
 </script>




             <script type="text/javascript">
// a function that filters the id in the passed county as per the county drop down.
    
function checkCat(){
    
      var j = document.getElementById("category");
  
   var k = j.options[j.selectedIndex].value;
  


 var x = document.getElementById("status");
  
   var y = x.options[x.selectedIndex].value;

    
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
  
  
}

}

xmlhttp.open("POST","retrieveSMSedit?category="+k+ "&status="+y ,true);

xmlhttp.send();
 
 } 
 </script>
 
<script language="javascript" type="text/javascript" >
function editRecord(MsgID){
    var forms=document.form;
    forms.method="post";
    forms.action="editSMS?ID="+MsgID;
    forms.submit();
    
}
</script>


    </head>
    
    <!-- Body page -->
    
       
       
    <body>
        <div id="container" style="width:1300px; height: 600px;" >
 
        <div id="header" style="width:1250px; margin-left: 25px;">

                    
                  <%@include file="menu/clerkmenu.html" %>    
                  
                  
              </div>
            <br/>
            
            
            <div id="content"   style=" padding-top:50px; width:1200px ;height:450px;">
           
              
<!--<div id="midcontent" style="margin-left:130px ; padding-top:50px; padding-left: 100px; width: 700px">-->
                

<form  name="form" id="form" style="margin-left:150px ;width:1000px; background-color: white;">
    
    
    <h3 style="text-align: center; background: greenyellow;">Edit Messages</h3>
    
    
    <table  style="" class="viewpdt">

           
              <tr><th>Select status</th>
                  <th>
                      <!--select the status of a mother-->
                      <select id="status" name="status" onchange="filtercategory();" >
                        <option  value=""></option>  
                        <option value="1">Pregnant</option>  
                        <option value="2">Post-Natal</option>  
                        
                    </select></th></tr>
              <tr><th >Select category</th>
                  <th>
                      <select id="category" name="category" onchange="checkCat()" >
                        <option value="">select status first</option> 
<!--          retrieve category from the db            -->
                                                                       
           

                        
                    </select></th>
              </tr>
        </table>

 
<table id="msgs_0" class="viewpdt" style="width:980px;"></table>

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

