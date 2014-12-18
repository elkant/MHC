<%-- 
    Document   : index
    Created on : Aug 5, 2013, 9:03:18 PM
    Author     : SIXTYFOURBIT
--%>

<%@page import="java.util.Calendar"%>
<%@page import="sender.dbConn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Audit</title>
         <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         <link rel="shortcut icon" href="images/icon.png"/>
          <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>


<!--menu-->
<!--clerk menu-->

<link rel="stylesheet" type="text/css" href="menu/css/clerkmenucss.css"/>


<script type="text/javascript">
    
   
</script>
        


    </head>
    
    <!-- Body page -->
    
       
       
    <body>
        <div id="container" style="height:650px;">
            <%@include file="/menu/adminmenu.html" %>
            <div id="header">
                
            </div>
<br/>
           

            <div id="content" style="width:1150px;height:560px;">
                

                <div id="midcontent">
                    
                    <iframe width="1100px" style="margin-left:25px;" height="550px"  src="audit_table.jsp"></iframe>
                    
       
                    
                    
                 
                </div>
            </div>

           

            <div id="footer">
                           </div>
        </div>
    </body>
    
    
</html>
