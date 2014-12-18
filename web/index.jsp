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
        <title>MNCH SYSTEM</title>
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

<script type="text/javascript">
      $(function() {

$( document ).tooltip();
$( "#accordion" ).accordion();

}); 
</script>
    </head>
    
    <!-- Body page -->
    
       
       
    <body>
        <div id="container" style="height:620px; border-top-color:greenyellow; border-top-width: thick ;">

            <div id="header">
                
            </div>

            <div id="leftBar" style="height:370px;">
                
            </div>

            <div id="content" style="height:590px;margin-left:50px">
                

                <div id="midcontent" style="height:160px;">
                    
                    <h5 align="center"><img src="images/mhc_logo.png" height="150px" alt="Health Communication System" align="centre"/></h5>
                    
                    <br/><br/>
                    <form action="login" method="post" style="margin-left: 205px; height:220px; width:290px;">
                       <h4 style="" align="center">Login</h4>
                        <table style="margin-left:40px;" width="100px" cellpadding="8px" cellspacing="6px">
                            <tr>
                                <td></td>
                                <td style="text-align: right">
                                    
                                    <!--  username -->
                                    <input id="username" type=text required name=uname placeholder="Username"  class="textbox"/></td>
                                
                            </tr>
                            <tr>
                                <td></td>
                                
                                <!--password-->
                                <td><input id="password"  type=password required name=pass placeholder="Password" class="textbox"></td>
                            </tr>
                            <tr>
                                <td style="text-align: right;"> </td>
                                <td style="text-align: center;"><input type="submit" class="submit" value="Log In"/>
                                   
                                    
                                </td>
                            </tr>
                        </table>
                       <h4>
                        <%
                            if (session.getAttribute("error_login") != null)  { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("error_login")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 1800});
                    
                </script> <%
                
                session.removeAttribute("error_login");
                            }

                        %>
                        </h4>
                    </form>
                         <h3 align="center"> <img src="images/aphia_logo.png" alt="logo" height="86px" width="270px"/></h3>
      
                </div>
                              </div>

            

            <div id="footer">
              <!--  <h2 align="left"> <img src="images/Coat of arms.JPG" alt="logo" height="76px" /></h2>-->
              
               <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);  
dbConn conn=new dbConn();
if(conn.st.isClosed()){conn=new dbConn();}
conn.rs=conn.st.executeQuery("select version_name from version");          
conn.rs.next();
%>
               <p align="center"> &copy <a href="#" title="<%=conn.rs.getString(1)%>">MNHC</a> Aphia Plus | USAID <%=year%></p>
            </div>
            
            
            <%
            
if(session.getAttribute("level")!=null){
    
    response.sendRedirect("viewallmothers.jsp");
    

}
System.out.println(session.getAttribute("level"));
%>
            
        </div>
    </body>
    
    
</html>
