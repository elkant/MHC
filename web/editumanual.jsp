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
    if (session.getAttribute("level") != null) {
        if (!session.getAttribute("level").equals("0")) {
            response.sendRedirect("index.jsp");
        }
    } else {
        response.sendRedirect("index.jsp");

    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
        <link rel="shortcut icon" href="images/icon.png"/>

        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

        <script type="text/javascript" src="js/noty/jquery.noty.js"></script>

        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/center.js"></script>
        <!-- You can add more layouts if you want -->
        <script type="text/javascript" src="js/noty/themes/default.js"></script>


        <!--tooltip-->
        <link href="js/css/south-street/jquery-ui-1.10.3.custom.css" rel="stylesheet">

        <script src="js/jquery-ui-1.10.3.custom.js"></script>


        <!--clerk menu-->

        <link rel="stylesheet" type="text/css" href="menu/css/clerkmenucss.css"/>

        <script type="text/javascript">
                    
                    
            function edithelp(tableid,textid){
    
                //alert(tableid);

                //alert(textid);
                // window.open("districtchooser?county="+dist.value);     
                var xmlhttp;  
                
                var textar=document.getElementById("textarea"+textid).value;
               
                document.getElementById("saved"+textid).innerHTML="";
                
                //alert(textar);
                if (textar=="")
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
                        
                        //alert(xmlhttp.responseText);  
                        
                        var n = noty({text: '<img src=\"images/present.png\"><font color=\"orange\">       Update succesfull!</font>',
                            layout: 'center',
                            type: 'Success',
 
                            timeout: 1800});
                        //doOnLoad();

                    }
                }
                xmlhttp.open("POST","updateumanual?id="+tableid+"&update="+textar,true);
                xmlhttp.send();
                //  document.getElementById("allchws").innerHTML="<img src=\"images/sending.gif\" alt=\"searching\">";
                //               
            }//end of ajax class
                  
                  
                  
                  
                            
            function filterhelp(id){
    
                //alert(menuid);
                var menuid=id.value;
                //alert(textid);
                // window.open("districtchooser?county="+dist.value);     
                var xmlhttp;  
                
                // var menuid=document.getElementById("textarea"+textid).value;
               
                
                
                //alert(textar);
                if (menuid=="")
                {
                    //filter the districts    



                    alert("No option selected .Please select a valid option");
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
                        
                        //alert(xmlhttp.responseText);  
                       
                        document.getElementById("accordion").innerHTML=xmlhttp.responseText;
                
                        var n = noty({text: '<img src=\"images/present.png\"><font color=\"orange\">data loaded!</font>',
                            layout: 'center',
                            type: 'Success',
 
                            timeout: 1800});
                        
                        newaccord();
                    }
                }
                xmlhttp.open("POST","filterumanual?menuid="+menuid,true);
                xmlhttp.send();
                //  document.getElementById("allchws").innerHTML="<img src=\"images/sending.gif\" alt=\"searching\">";
                //               
            }
                  
                  
               function savedstatus(suf){
                   
        document.getElementById("saved"+suf).innerHTML="<font color=\"red\">*</font>";   
                   
                   
                   
               }   
                  
                  
        </script>

        <script type="text/javascript">
            $(function() {
                
                
                
                
                

                $( document ).tooltip();
                
                
              $("#accordion").accordion();
           
            }); 
            
            function newaccord(){
                
                $("#accordion").accordion();
                
                
            }
            
        </script>

        <title>Help Modules</title>
    </head>
    <body>

        <div id="container" >

            <div id="header" style="width:1150px;">
                <br/>
                <%@ include file="/menu/adminmenu.html" %> 
                <br/>
            </div>
            <div id="content" style="height:750px; margin-left: 90px;width:950px;">



                <h3 align="center" style="background-color: greenyellow;">EDIT USER MANUAL</h3>





                <%

                    dbConn conn = new dbConn();
                    String helpmsg = "";
                    String menuqry = "select distinct menu_id, menu_name from usermanual";
                    String allmenus = "<option value=\"\"></option>";
                    conn.rs1 = conn.st1.executeQuery(menuqry);
                    while (conn.rs1.next()) {


                        allmenus += "<option value=\"" + conn.rs1.getString(1) + "\">" + conn.rs1.getString(2) + "</option>";


                    }

                %>



                <table border="0" style="width:950px;background: #E3E3E3;">
                    <tr><td>Filter modules :    <img src="images/blguide.png" title="choose a specific menu to edit sections for that menu only."/></td><td>
                            <select onchange="filterhelp(this);"  style="width:205px;"  class="textbox" >
                                <%=allmenus%> 

                            </select>
                        </td><td>
                        </td>
                        <td></td>

                        <td><a href="editumanual.jsp" class="linkstyle">refresh</a></td></tr>
                </table>


                <%

                    int counter = 1;
                    helpmsg = "select * from usermanual ";
                    conn.rs = conn.st.executeQuery(helpmsg);
                %>

              
                    <div id="accordion" style="font-weight: normal;">
                        <%
                            while (conn.rs.next()) {

                        %>


                        <h3><%=counter%>  <%=conn.rs.getString(3)%> <b>></b> <font color="orange"><%=conn.rs.getString(5)%></font> <span id="saved<%=counter%>"></span></h3>
                        <div><textarea cols="80" rows="5" id="textarea<%=counter%>" onkeypress="savedstatus('<%=counter%>');"> <%=conn.rs.getString(2)%></textarea>
                            <input type="button" name="edit"  value="save" style="background-color:coral;height:30px ;font-weight:normal;color:#ffffff;" onclick="edithelp('<%=conn.rs.getString(1)%>','<%=counter%>');" >
                        </div>




                        <%
                            counter++;

                        }%>
                    </div>
                    

               
            </div>



            <div id="footer">
                <%
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);

                %>

            </div>
        </div>    

    </body>
</html>
