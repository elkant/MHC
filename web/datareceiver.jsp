<%-- 
    Document   : UpdateDiary
    Created on : Oct 17, 2013, 5:17:58 PM
    Author     : Maureen
--%>

<%@page import="sender.dbConn"%>
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
         <link rel="stylesheet" href="menu/css/clerkmenucss.css" type="text/css" media="all"/>
         
         <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         
        <link rel="stylesheet" href="menu/adminmenu_files/css3menu1/style.css" type="text/css" /><style type="text/css">._css3m{display:none}</style>
         <link rel="shortcut icon" href="images/icon.png"/>
          <link rel="stylesheet" type="text/css" href="css/clerkmenucss.css"/>
    
    <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         
        <link rel="stylesheet" href="menu/adminmenu_files/css3menu1/style.css" type="text/css" /><style type="text/css">._css3m{display:none}</style>
         <link rel="shortcut icon" href="images/icon.png"/>
       
      
<!--	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
       <script type="text/javascript" src="js/jquery-ui.js"></script>  
       <script type="text/javascript" src="js/jquery-ui-1.10.3.custom.js"></script>  
       <script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js"></script>  
       <link rel="stylesheet" href="css/jquery-ui-1.10.3.custom.css">  
       <link rel="stylesheet" href="css/jquery-ui-1.10.3.custom.min.css"> 
       <link rel="stylesheet" href="css/datepicker.css"> -->

<link href="js/css/south-street/jquery-ui-1.10.3.custom.css" rel="stylesheet">
	<script src="js/jquery-1.9.1.js"></script>
	<script src="js/jquery-ui-1.10.3.custom.js"></script>
          <script>
$( document ).tooltip();
          </script>
       
        
       
       
   

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
<title>Diary</title>
<script type="text/javascript" src="js/noty/themes/default.js"></script> 
    

   
   
   
        
       <script type="text/javascript">
   
   
       
        
    </script>
   
    </head>
    <body>
        <div id="container" style="width:1250px;">

            <%      if (session.getAttribute("level").equals("0")) {%>
            <%@include file="/menu/adminmenu.html" %>
            <%} else {%>
            <%@include file="/menu/clerkmenu.html"%>
            <%}
            %>
            <br/>
            <h3 style="text-align: center; background-color: orange;"> <img src="images/blguide.png" title="This page helps users to change the email address of the backup file receiver, who will the merge data from various controll sites." /> UPDATE DATA RECEIVER EMAIL</h3>
            <h5 style="text-align: center; "></h5>



            


            


               






                    <%if (session.getAttribute("mailchanged") != null) {



                    %>
                    <script type="text/javascript"> 
                    
                                       var n = noty({
                                           text: '<%=session.getAttribute("mailchanged")%>',
                                           layout: 'center',
                                           type: 'Success',
                                           timeout:3000});
                    
                    </script>
                    <%
                            session.removeAttribute("mailchanged");
                        }

                    %>
                    <!--creating random user id-->






                    <br/><br/>

                    <form action="editmail" method="post" style="height:90px;margin-left: 200px;width: 400px;">
                        <br/>

                        <%

                            dbConn conn = new dbConn();
                            conn.rs = conn.st.executeQuery("select mail from mail where mailid='1'");

                            String mandemail = "enter mail";
                            if (conn.rs.next()) {

                                mandemail = conn.rs.getString(1);

                            } else {
                                mandemail = "no email";

                            }


                            conn.st.close();

                        %>
                        <table>                 
                            <tr>          
                                <td><input type="email" name="mandemail" required  value="<%=mandemail%>" class="textbox1" style="height:40px;width:200px;" /></td>
                                <td>

                                </td>
                                <td>
                                    <input type="submit" value="Update" style="height:40px;"/>
                                </td>
                            </tr>
                        </table>
                                <br/>
                    </form>
                <br/>
           
            </div>
    </body>
</html>

