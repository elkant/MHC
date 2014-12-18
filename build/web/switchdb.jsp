<%-- 
    Document   : index
    Created on : Aug 5, 2013, 9:03:18 PM
    Author     : SIXTYFOURBIT
--%>

<%@page import="sender.dbConn"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>switch database</title>
        <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
        <link rel="shortcut icon" href="images/icon.png"/>
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

        <script type="text/javascript" src="js/noty/jquery.noty.js"></script>

        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/center.js"></script>
        <!-- You can add more layouts if you want -->

        <script type="text/javascript" src="js/noty/themes/default.js"></script>


        <!--tooltip-->
        <link href="js/css/start/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>

        <script src="js/js/jquery-ui-1.10.3.custom.js"></script>
        <link rel="stylesheet" href="js/demos.css" />
        
         
         
         <link rel="stylesheet" href="menu/adminmenu_files/css3menu1/style.css" type="text/css" /><style type="text/css">._css3m{display:none}</style>
         <link rel="shortcut icon" href="images/icon.png"/>
         <link rel="stylesheet" type="text/css" href="css/clerkmenucss.css"/>

        <script type="text/javascript">
            $(function() {

                $( document ).tooltip();
                $( "#accordion" ).accordion();

            }); 
        </script>
    </head>

    <!-- Body page -->



    <body>
        <div id="container" style="height:620px; width:1400px;">

            
                      <%      if(session.getAttribute("level").equals("0")){ %>
   <%@include file="/menu/adminmenu.html" %>
 <%}
    
      else{%>
       <%@include file="/menu/clerkmenu.html"%>
      <%}
   %>
            
            <div id="header">
                <div class="ui-widget" style="width:1160px;" >
                    <div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
                        <p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
                            </div>


                </div>
            </div>
            <br/>
            <div id="leftBar" style="height:370px;">

            </div>

            <div id="content" style="height:500px;margin-left:50px; overflow:hidden ;">




                <!--                    <h5 align="center"><img src="images/mhc_logo.png" height="150px" alt="Health Communication System" align="centre"/></h5>-->


                <form action="switchdb" method="post" style="margin-left: 100px; height:380px; width:540px;">
                    
                    <h3 style="background-color: white; text-align: center;">Current Database : <%
   dbConn conn= new dbConn();

   String dbnames[]={"mhc","mastermhc"};
   
  out.println("<font color=\"orange\">"+ conn.dbsetup[1]+"</font>");
   
  String dbname="";
  
  if(conn.dbsetup[1].equals(dbnames[0])){
  
  dbname=dbnames[1];
  }
   else{
   
   dbname=dbnames[0];
   }
  
                     
   %>  HOST : <% out.println("<font color=\"orange\">"+ conn.dbsetup[0]+"</font>");%> </h3>
                    
                    <h4 align="center">Database Configuration</h4>
                    <table style="margin-left:120px;width:300px;"   cellpadding="8px" cellspacing="6px">
                        <tr>
                            <td style="text-align: right;">Host name:   <img src="images/blguide.png" title="enter the hostname followed by a ':' then port number e.g. localhost:3306. If there is no port number, just enter the host name alone e.g. localhost"/></td>
                            <td style="text-align: right">

                                <!--  username -->
                                <input id="hostname" type=text required name=hostname placeholder="e.g localhost:3306" value="localhost:3306" autofocus class="textbox"/></td>

                        </tr>
                        <tr> <td style="text-align: right;">Database : <img src="images/blguide.png" title="enter the database name e.g mhc"/></td>
                            <td style="text-align: right;"><input id="database"  type=text required name=database value="<%=dbname%>" placeholder="e.g mnhc"  class="textbox"/></td>
                        </tr>

                        <tr> <td style="text-align: right;">User:   <img src="images/blguide.png" title="enter a database user name name e.g root"/></td>
                            <td style="text-align: right;"><input id="user"  type=text required name=user value="root" placeholder="e.g root"  class="textbox"/></td>
                        </tr>

                        <tr><td style="text-align: right;">Password:   <img src="images/blguide.png" title="enter the database password"/></td> 
                            <!--password-->
                            <td style="text-align: right;"><input id="dbpassword"  type=password  name=password placeholder="Password" class="textbox"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right;"> </td>
                            <td style="text-align: center;"><input type="submit" class="submit" value="Switch"/>

                            </td>
                        </tr>
                    </table>
                    <h4>
                        <%
                                if (session.getAttribute("") != null) {%>
                        <script type="text/javascript"> 
                    
                            var n = noty({text: '<%=session.getAttribute("")%>',
                                layout: 'center',
                                type: 'Success',
 
                                timeout: 1800});
                    
                        </script> <%

                                session.removeAttribute("");
                            }

                        %>
                    </h4>
                </form>
                <!--                    <h3 align="center"> <img src="images/aphia_logo.png" alt="logo" height="86px" width="270px"/></h3>-->


            </div>



            <div id="footer">
                <!--  <h2 align="left"> <img src="images/Coat of arms.JPG" alt="logo" height="76px" /></h2>-->

                <%
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);



                %>
                <p align="center"> &copy <a href="#" title="">MNHC</a> Aphia Plus | USAID <%=year%></p>
            </div>
        </div>
    </body>


</html>
