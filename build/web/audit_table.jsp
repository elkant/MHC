<%-- 
    Document   : audit_table
    Created on : Sep 4, 2013, 3:44:00 PM
    Author     : SIXTYFOURBIT
--%>
<%@page import="sender.dbConn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         <% dbConn conn= new dbConn();

String adt="select * from audit order by action_id desc";

conn.rs=conn.st.executeQuery(adt);




%>
   
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="js/jquery.tablesorter.js"></script>
	<script type="text/javascript" src="js/jquery.tablesorter.pager.js"></script>
        <link rel="stylesheet" type="text/css" href="js/jquery.tablesorter.pager.css"/>
        <link rel="stylesheet" type="text/css" href="css/style_1.css" id="" media="print, projection, screen"/>
  <script type="text/javascript">
	$(function() {
		$(".viewpdt")
			.tablesorter({widthFixed: true, widgets: ['zebra']})
			.tablesorterPager({container: $("#pager")});
	});
	</script>      


    
    </head>
    <body>
        <div id="pager" class="pager">
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
        <div>
        <table  cellpadding="0px" class="viewpdt" id="tablesorter" cellspacing="0px" border="1px" style="width:840px;" >
            <thead>
<tr><th>Action</th>
    <th>Time</th>
    <th>User</th>
    <th>Host Machine</th>
</tr>
            </thead>
            <tbody>
<%
while(conn.rs.next()){
    
    String users="Select * from users where userid='"+conn.rs.getString(4) +"'";
    
    conn.rs2=conn.st2.executeQuery(users);
    conn.rs2.next();
%>
            <tr><td> <%=conn.rs.getString(2)%> </td><td> <%=conn.rs.getString(3)%></td><td> <%=conn.rs2.getString(2)%></td><td> <%=conn.rs.getString(5)%></td></tr>

            

<%
}
%>

        </tbody>
        
                    </table>

        </div>

    </body>
</html>
