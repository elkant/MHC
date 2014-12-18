<%-- 
    Document   : edit_clerk
    Created on : Aug 15, 2013, 11:18:53 AM
    Author     : Nyabuto Geofrey
--%>

<%@page import="java.util.Calendar"%>
<%@page import="sender.dbConn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if (!session.getAttribute("level").equals("0")) {
        response.sendRedirect("index.jsp");
    } 
%>
<!DOCTYPE html>
<html>
    <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
<link rel="shortcut icon" href="images/icon.png"/>

<!--  <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

--><script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
<script type="text/javascript" src="js/noty/themes/default.js"></script>
	
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>   
<script type="text/javascript" src="js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.pager.js"></script>
<link rel="stylesheet" type="text/css" href="js/jquery.tablesorter.pager.css"/>
        <link rel="stylesheet" type="text/css" href="css/style_1.css" id="" media="print, projection, screen"/>

  <script type="text/javascript"> 
	$(document).ready(function() 
    { 
        $("#allchwstable")
        .tablesorter({widthFixed: true, widgets: ['zebra']})
        .tablesorterPager({container: $("#pager")}); 
    });
	</script> 
<!--clerk menu-->

<link rel="stylesheet" type="text/css" href="menu/css/clerkmenucss.css"/>

<script type="text/javascript">
    
    
     
                    
                    
            function searchchwfulani(){
    

                // window.open("districtchooser?county="+dist.value);     
                var xmlhttp;  
                
                var name=document.getElementById("chwname").value;
                if (name=="")
                {
                   

                    //alert("anc number is blank");
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
                        
         document.getElementById("allchwstable").innerHTML=xmlhttp.responseText;
                        //doOnLoad();

                    }
                }
                xmlhttp.open("POST","searchchwfulani?chwname="+name,true);
                xmlhttp.send();
   document.getElementById("allchwstable").innerHTML="<img src=\"images/sending.gif\" alt=\"searching\">";
//               
            }
             
    
</script>
  <link href="js/css/ui-lightness/jquery-ui-1.10.3.custom.css" rel="stylesheet">
<script src="js/jquery-ui-1.10.3.custom.js"></script>
        <script type="text/javascript">
            $(function() {

                $( document ).tooltip();
                $( "#accordion" ).accordion();

            }); 
        </script>

	
<title>CHW Details</title>
  
    </head>
<body>

<div id="container" >
    
    <div id="header" style="width:1150px;">
        <br/>
        <%@ include file="/menu/adminmenu.html" %> 
        <br/>
         </div>
<div id="content" style="height:750px; margin-left: 150px;">



<h4>Edit CHW Details</h4>


  <%
                            if (session.getAttribute("chwedited") != null)  { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("chwedited")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 1800});
                    
                </script> <%
                
                session.removeAttribute("chwedited");
                            }

                        %>


<h4> 
          <%
         dbConn conn=new dbConn();
  int counter=1;
  String editor="select * from chw ";
  conn.rs=conn.st.executeQuery(editor);%>
</h4>


                    <table border="0" style="width:800px;background: #E3E3E3;">
                        <tr><td>Search :    <img src="images/blguide.png" title="search chw by their first , middle or last name"/></td><td>
                                <input type="text" onkeyup="searchchwfulani();"  style="width:205px;"  id="chwname"  name="chwname" value="" required="true" class="textbox" placeholder="type to autosearch"/>
                            </td><td>
                            </td>
                            <td></td>

                            <td><a href="allchw.jsp" class="linkstyle">refresh</a></td></tr>
                    </table>


<table cellpadding="2px" cellspacing="3px" border="0px" class="cart_style" id="allchwstable" style="width:800px;">
    <thead>
  <tr>
    <th>No.</th>
 <th>First Name</th>   
  <th>Middle name</th> 
   <th>Last name</th>
    <th>Phone no</th> 
     <th>Edit</th> 
     
</tr>
    </thead>
<%
 while(conn.rs.next()){  
%>
<tr>
    <td><%=counter++%></td>

    <%String userid=conn.rs.getString("chw_id");%>
    <td><%=conn.rs.getString("chv_fname")%></td>  

    <td><%=conn.rs.getString("chv_mname")%></td> 
    <td><%=conn.rs.getString("chv_lname")%></td> 
    <td><%=conn.rs.getString("chv_phone")%></td>  
    <td> 
        <form action="edit_one_chw.jsp" method="post" id="tableform" style="background: #ffffff;">
<input type="hidden" name="userid" value="<%=userid%>"/>
<input type="submit" name="Submit" value="Edit" class="submit"/>                   
   </form></td>
</tr>
    <%}%>
</table>
<div id="pager" class="pager" >
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

</div>



<div id="footer">
<%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center"> &copy MNHC Aphia Plus | USAID <%=year%></p>
            </div>
</div>    

</body>
</html>

