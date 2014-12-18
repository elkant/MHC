<%-- 
    Document   : Messages
    Created on : Oct 22, 2013, 11:24:39 AM
    Author     : Maureen
--%>

<%!
HttpSession session;

%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      
         <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
       
          <link rel="stylesheet" type="text/css" href="css/clerkmenucss.css"/>
 
   
        <link rel="stylesheet" href="menu/adminmenu_files/css3menu1/style.css" type="text/css" /><style type="text/css">._css3m{display:none}</style>
         <link rel="shortcut icon" href="images/icon.png"/>
       
     
	
  
      
<!--       <link rel="stylesheet" href="css/datepicker.css"> -->
       

    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>       
<script type="text/javascript" src="js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.pager.js"></script>
<link rel="stylesheet" type="text/css" href="js/jquery.tablesorter.pager.css"/>
<link rel="stylesheet" type="text/css" href="css/style_1.css" id="" media="print, projection, screen"/>

  <script type="text/javascript"> 
	$(document).ready(function() 
    { 
        $("#SearchResults")
        .tablesorter({widthFixed: true, widgets: ['zebra']})
        .tablesorterPager({container: $("#pager")}); 
        
        
   
         
    });
	</script> 
       
       

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>



<!--        tooltip-->
<link href="js/css/south-street/jquery-ui-1.10.3.custom.css" rel="stylesheet">
<!--	<script src="js/jquery-1.9.1.js"></script>-->
	<script src="js/jquery-ui-1.10.3.custom.js"></script>
        <script type="text/javascript">
            $(function() {

                $( document ).tooltip();
                $( "#accordion" ).accordion();
                
             
        
        
        
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

                

            }); 
        </script>

        <!--clerk menu-->





<!-- You can add more layouts if you want -->
<title>View messages</title>
<script type="text/javascript" src="js/noty/themes/default.js"></script> 

    <script language="javascript" type="text/javascript" >
function editRecord(MsgID){
    var f=document.form;
    f.method="post";
    f.action="editSMS?ID="+MsgID;
    f.submit();
    
}
</script>

       
    </head>
    <body>
        <div id="container" style="width:1300px; height: 700px;"  >
 
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
                <div id="content"   style=" padding-top:50px; padding-right:50px; padding-left:0px; width:900px ;height:500px; margin-left: 200px;">
                    
      
                    <form  name="form" id="form" style="background-color: white;" >
                 <h4 style="background-color: greenyellow;">VIEW MESSAGES  <img src="images/help_24.png"  title="This section shows all the messages that are added to the system. You can edit each of the message by clicking the edit button at the end of every row."/> </h4>
               
                    <table class="viewpdt"  id="SearchResults" style="width: 840px;">
	<thead>
            
            
            
          
               <tr>
                    <th>ID</th>
		    <th>MESSAGE</th>
		    <th>NOK MESSAGE</th>
		    <th>EDIT</th>
		</tr>
	</thead>
      
       
         <c:forEach  var="msg" items="${msgs}"  >
          <c:set var="id"  value="${msg.ID}"></c:set>
          <c:set var="message"  value="${msg.MESSAGE}"></c:set>
          <c:set var="nokmessage"  value="${msg.NOKMESSAGE}"></c:set>
        
           <input type="hidden" id="id" name="id<%= pageContext.getAttribute("id")%>" value="<%= pageContext.getAttribute("id")%>" />
       
<!--       <input type="hidden" id="phoneNo" name="phoneNo" value="<%= pageContext.getAttribute("message")%>" />
       <input type="hidden" id="FName" name="FName<%= pageContext.getAttribute("id")%>" value="<%= pageContext.getAttribute("FName")%>" />-->
       
         
     <tr>
        
        
        
          <td>${msg.ID}</td>
          <td>${msg.MESSAGE} </td>
          <td>${msg.NOKMESSAGE} </td>
         
             <td colspan="8"><input type="button" name="edit" value="Edit" style="background-color:#49743D;font-weight:bold;color:#ffffff;" onclick="editRecord(<%= pageContext.getAttribute("id")%>);" ></td>      

       
     </tr>
        
       
         </c:forEach>
      
	
        
                   
                    </table>
                   <div id="pager" class="pager" style="background-color:#99ff99; padding-top: 10px; padding-bottom: 10px;">
    <form>
            <img src="images/first.png" class="first"/>
		<img src="images/prev.png" class="prev"/>
		<input type="text" class="pagedisplay"/>
		<img src="images/next.png" class="next"/>
		<img src="images/last.png" class="last"/>
		<select class="pagesize">
			<option selected="selected"  value="10">10</option>
			<option value="20">20</option>
			<option value="30">30</option>
			<option  value="40">40</option>
		</select>
	
</form>
</div>        
                    
        
        </form>
       
        </div>
            </div>

            

             <div id="footer">
              <!--  <h2 align="left"> <img src="images/Coat of arms.JPG" alt="logo" height="76px" /></h2>-->
              
               <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center"> &copy Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    
    </body>
</html>

