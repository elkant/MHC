<%-- 
    Document   : UploadFie
    Created on : Nov 6, 2013, 4:35:53 PM
    Author     : Maureen
--%>


<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="js/css/ui-lightness/jquery-ui-1.10.3.custom.css" rel="stylesheet">
	<script src="js/jquery-1.9.1.js"></script>
	<script src="js/jquery-ui-1.10.3.custom.js"></script>
          <script>
$(document).tooltip();
         </script>
         <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         
         <link rel="stylesheet" href="menu/adminmenu_files/css3menu1/style.css" type="text/css" /><style type="text/css">._css3m{display:none}</style>
         <link rel="shortcut icon" href="images/icon.png"/>
         <link rel="stylesheet" type="text/css" href="css/clerkmenucss.css"/>
         <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         <link rel="stylesheet" href="menu/adminmenu_files/css3menu1/style.css" type="text/css" /><style type="text/css">._css3m{display:none}</style>
         <link rel="shortcut icon" href="images/icon.png"/>
       
      


<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
<title>Backup data</title>
<script type="text/javascript" src="js/noty/themes/default.js"></script> 
    
<script type="text/javascript">
    
    
               
            function uploadfile(){
    

                // window.open("districtchooser?county="+dist.value);     
                var xmlhttp;  
                
                var name=document.getElementById("fname").value;
                if (name=="")
                {
                    //filter the districts    



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
                        
                        
                        
                        document.getElementById("loading").innerHTML="<img src=\"images/present.png\" alt=\"done\"><font color=\"orange\"> "+xmlhttp.responseText+"</font>";
                        //doOnLoad();

                    }
                }
                xmlhttp.open("POST","importData1?fname="+name,true);
                xmlhttp.send();

  document.getElementById("loading").innerHTML="<img src=\"images/sending.gif\" alt=\"uploading\"><b><font color=\"grey\"> importing data..</font></b>";
//               
            }//end of ajax class
             
    
</script>     


    </head>
    <body>
        <div id="container" style="width:1300px;" >
 <br/>
              <div id="header" style="width:1250px; margin-left: 25px;">

                    
                   <!--=========================================menu=========================================-->     
            <%
                if (session.getAttribute("level") != null) {

                    if (session.getAttribute("level").equals("0")) {%>
            <%@include file="menu/adminmenu.html" %>
            <%} else {%>

            <%@include file="menu/clerkmenu.html" %>

            <%}

            } else {%>

            <%@include file="menu/clerkmenu.html" %>

            <%}%>

            <!--=====================================================================================--> 
                    
                    
              </div>
            
             
                         <%if (session.getAttribute("datasend") != null) {
                         
    
    
    %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("datasend")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("datasend");
                            }

                        %>
            
            
            <br/>
            <div id="content" style="width:1020px;">
       
                 
                    <form action="back_up_tables" method="post" style="height:90px;margin-left: 400px;margin-top: 200px;width:400px;">
                        <br/>
                       <input type="submit" value="Send Backup" style="height:60px;" >
                    </form>
              
              
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

