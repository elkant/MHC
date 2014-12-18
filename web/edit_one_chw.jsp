<%-- 
    Document   : edit_clerk2
    Created on : Aug 15, 2013, 11:56:22 AM
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
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />

<!--clerk menu-->

<link rel="stylesheet" type="text/css" href="menu/css/clerkmenucss.css"/>		
		<script src="menu/prefix-free.js"></script> 
<title>Edit Chw Details</title>
    </head>
<body>

<div id="container" >
<%@ include file="/menu/adminmenu.html" %>
<div id="content" style="height:750px; margin-left: 150px;">

<h4 style="text-align: center;">Edit chw Details</h4>

<h4> 
          <%
         String userid=request.getParameter("userid");

         
  int counter=1;
  String editor="select * from chw WHERE chw_id='"+userid+"'";
dbConn conn=new dbConn();
conn.rs=conn.st.executeQuery(editor);

%>
</h4>
<table cellpadding="1px" cellspacing="1px" border="0px" class="cart_style" style="width:750px;">
<tr>
 <th>First Name</th>   

  <th>Last name</th> 
   <th>Username</th>
    <th>Phone no</th> 
     <th>Edit</th> 
     
</tr>
<%
 while(conn.rs.next()){  
%>
<tr>
<form action="updatechw" method="post">
    <input type="hidden" name="userid" value="<%=conn.rs.getString("chw_id")%>"/>
    <td class="format_cell"><input type="text" name="fname" value="<%=conn.rs.getString("chv_fname")%>" required class="textbox"/></td>  
    <td class="format_cell"><input type="text" name="sname" value="<%=conn.rs.getString("chv_mname")%>"required class="textbox"/>  </td> 
    <td class="format_cell"><input type="text" name="username" value="<%=conn.rs.getString("chv_lname")%>" required class="textbox"/>  </td> 
    <td class="format_cell"><input type="number" name="phoneno" maxlength="12" pattern="(2547)[0-9]{8}" autofocus title="The field must have 12 integers" value="<%=conn.rs.getString("chv_phone")%>" required class="textbox"/>  </td> 
    <td>  
<input type="submit" name="Submit" value="Save" class="submit"/>             
    </form>
    </td> 
   
</tr>
    <%}%>
</table>


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

