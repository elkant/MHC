<%-- 
    Document   : createSMS
    Created on : Oct 18, 2013, 2:29:39 PM
    Author     : Maureen
--%>

<!DOCTYPE html>

<%@page import="sendSMS.dbConnect"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CREATE SMS</title>
<!--         <link rel="stylesheet" type="text/css" href="css/divCss.css"/>-->
          <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
            <link rel="stylesheet" href="menu/css/clerkmenucss.css" type="text/css" media="all"/>
<!--        <link rel="stylesheet" href="menu/adminmenu_files/css3menu1/style.css" type="text/css" /><style type="text/css">._css3m{display:none}</style>-->
         <link rel="shortcut icon" href="images/icon.png"/>
          <link rel="stylesheet" type="text/css" href="css/clerkmenucss.css"/>
    
      
        
              
              
          
      

<!--<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>-->
<!-- You can add more layouts if you want -->
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
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
 <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.charcounter.js"></script>

 <!--tooltip-->

        <link href="js/css/south-street/jquery-ui-1.10.3.custom.css" rel="stylesheet">

        <script src="js/jquery-ui-1.10.3.custom.js"></script>


<script>
     $(document).ready(function(){
   $("#kaleMsg").charCounter(600, {
	container: "<td><em></em></td>",
	classname: "counter",
	format: "%1 characters to go!",
	pulse: false,
	delay: 100
});

 $("#kaleMsgNOK").charCounter(600, {
	container: "<td><em></em></td>",
	classname: "counter",
	format: "%1 characters to go!",
	pulse: false,
	delay: 100
});
 $("#message").charCounter(600, {
	container: "<td><em></em></td>",
	classname: "counter",
	format: "%1 characters to go!",
	pulse: false,
	delay: 100
});
 $("#nokmessage").charCounter(600, {
	container: "<td><em></em></td>",
	classname: "counter",
	format: "%1 characters to go!",
	pulse: false,
	delay: 100
});
 $("#ujumbe").charCounter(600, {
	container: "<td><em></em></td>",
	classname: "counter",
	format: "%1 characters to go!",
	pulse: false,
	delay: 100
});
 $("#ujumbenok").charCounter(600, {
	container: "<td><em></em></td>",
	classname: "counter",
	format: "%1 characters to go!",
	pulse: false,
	delay: 100
});



$( "#dialog" ).dialog({
			autoOpen: false,
			width: 400,
			buttons: [
				{
					text: "Ok",
					click: function() {
						$( this ).dialog( "close" );
					}
				}
			]
		});



$( "#dialog-link" ).click(function( event ) {
			$( "#dialog" ).dialog( "open" );
			event.preventDefault();
		});
                

                $( document ).tooltip();


});
    
</script>


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

    </head>
    
    <!-- Body page -->
    
       
       
    <body>
        <div id="container" style="width:1300px; height: 900px;" >
 
            
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
            
            <br/>
            
            <div id="content"   style=" width:1000px ;height:700px; margin-left: 200px;">
               <%
dbConnect conn = new dbConnect();               
String helpqr="select * from help where help_id='23'";

conn.rs1=conn.state1.executeQuery(helpqr);
String helptext="";

while(conn.rs1.next()){

  helptext=conn.rs1.getString(2);  
    


}
%>
                <form action="saveMessage" method="post" style="background-color: white;">
                    <h3 style="text-align: center; background-color: greenyellow;"> CREATE SMS <a href="#" id ="dialog-link"><img src="images/help_24.png"/></a></h3>
                    
                    
<!-- ui-dialog -->
<div id="dialog" title="'Create SMS' Guide ">
	<p>
         <% if(helptext!=null){%>
         <%=helptext%> 
         <%}%>
        </p>
</div>
                    
                    <table class="viewpdt" style="width:975px;" >
                        <tr><th>SELECT STATUS</th><td><select name="status" required id="status" onchange="filtercategory();">
                                <option value="">select status</option>
                                 <%

String query1="select * from status" ;
conn.rs = conn.state.executeQuery(query1);
while(conn.rs.next()){
    String statusID = conn.rs.getString(1);
    String status = conn.rs.getString(2);
%>
     <option value="<%= statusID%>"><%=status %></option>

<%}                          




%>




                            </select></td>
                    <th>SELECT A CATEGORY</th><td><select name="category" required id="category">
                                <option value="">select status first</option>
                               


                                
<!--                                <option value="back pain">English</option>
                                <option value="kiswahili">Kiswahili</option>-->
                            </select></td></tr>
<!--                    <tr><td>SELECT A LANGUAGE</td>
                        <td><select>
                                <option value=""></option>
                                <option value="english">English</option>
                                <option value="kiswahili">Kiswahili</option>
                            </select>
                        </td>
                    </tr>
                    -->
                   
                    <tr>
                        <th>MESSAGE</th><td><textarea name="message" id="message" required style="width: 300px; height: 100px;" class="counter" maxlength="600" title="Ensure the message is less than or equal to 160 characters"></textarea></td>
                        <th>NOK MESSAGE</th><td><textarea name="nokmessage" required id="nokmessage" class="counter"  style="width: 300px; height: 100px;" maxlength="600" title="Ensure the message is less than or equal to 160 characters"></textarea></td>
                        
                        
                    </tr>
                    <tr>
                        <th>UJUMBE</th><td><textarea name="ujumbe" maxlength="600" required style="width: 300px; height: 100px;" id="ujumbe" class="counter" title="Ensure the message is less than or equal to 160 characters" ></textarea></td>
                        <th>NOK UJUMBE</th><td><textarea name="ujumbenok" required maxlength="600" style="width: 300px; height: 100px;" class="counter" id="ujumbenok" title="Ensure the message is less than or equal to 160 characters"></textarea></td>
                        
                        
                    </tr>
                    <tr>
                        <th>KALENJIN MESSAGE </th><td><textarea name="kaleMsg" required id="kaleMsg" style="width: 300px; height: 100px;" class="counter" maxlength="600" ></textarea></td>
                        <th>NOK KALENJIN MESSAGE</th><td><textarea name="kaleMsgNOK" required style="width: 300px; height: 100px;" id="kaleMsgNOK" class="counter" maxlength="600"></textarea></td>
                        
                        
                    </tr>
                    <tr><td colspan="4"><input type="submit" style="height:30px;" value="submit" class="submit" name="submit"></td></tr>
                    
                    
                </table>  
              </form>
<!--                <div id="midcontent" style="margin-left:130px ; padding-top:50px; padding-left: 100px; width: 700px">-->
                

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

