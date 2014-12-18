<%-- 
    Document   : edit_clerk2
    Created on : Aug 15, 2013, 11:56:22 AM
    Author     : Nyabuto Geofrey
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="sender.chv"%>
<%@page import="java.util.Calendar"%>
<%@page import="sender.dbConn"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if (session.getAttribute("level") == null) {
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

        <!--        <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>   -->
        <script src="js/jquery-1.9.1.js" type="text/javascript"></script>

        <!--clerk menu-->

        <link rel="stylesheet" type="text/css" href="menu/css/clerkmenucss.css"/>		
        <script src="menu/prefix-free.js"></script> 

        <script type="text/javascript" src="js/noty/jquery.noty.js"></script>

        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/center.js"></script>
        <!-- You can add more layouts if you want -->
        <script type="text/javascript" src="js/noty/themes/default.js"></script>

        <!--tooltip-->

        <!--======================DATA TABLES================================-->
        <!----datatable--Maureens---->

        <!--
                <script src="scripts/jquery-1.4.4.min.js" type="text/javascript"></script>-->
        <script src="scripts/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="scripts/jquery.jeditable.js" type="text/javascript"></script>
        <!--<script src="media/js/jquery-ui.js" type="text/javascript"></script>-->
        <!--   <script src="media/js/jquery.validate.js" type="text/javascript"></script>-->
        <script src="scripts/jquery-ui.js" type="text/javascript"></script>
        <script src="scripts/jquery.dataTables.editable.js" type="text/javascript"></script>
        <script src="scripts/jquery.validate.js" type="text/javascript"></script>
        <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <!--        <link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />-->
        <link href="js/css/ui-lightness/jquery-ui-1.10.3.custom.css" rel="stylesheet">


        <script src="js/jquery-ui-1.10.3.custom.js"></script>

        
        
        
          <script>
    
   
            
            //end of ajax class
    
         </script>
        
        
        
        <script type="text/javascript">






            $(document).ready(function() {
                
                var chws;
                
                
                  
                        
                   
                
                 
                      
                      
                //alert(chws);    
                 
                
                $('#example').dataTable().makeEditable({
                    sUpdateURL: "editviewedmum",
                    sDeleteURL: "deletemum",
                    //                                                                        sAddURL: "AddCounty",

                    "aoColumns": [null,
       //============
       ////                    									
                        {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit: 'Save changes',
                            callback: function(value, settings)
                            {
                                alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue, value, row, column, settings) {
                                document.getElementById("msg").innerHTML = "(Cell Callback): is updated with value " + sValue;
                            }

                        },
                        //================

                        {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit: 'Save changes',
                            callback: function(value, settings)
                            {
                                alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue, value, row, column, settings) {
                                document.getElementById("msg").innerHTML = "(Cell Callback): is updated with value " + sValue;
                            }

                        },
                        //================================

                        {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit: 'Save changes',
                            callback: function(value, settings)
                            {
                                alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue, value, row, column, settings) {
                                document.getElementById("msg").innerHTML = "(Cell Callback): is updated with value " + sValue;
                            }

                        },
                        
                        
                        {
                            
                            
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'select',
                            data:"{'':'choose facility','Solian':'Solian','Ngubereti':'Ngubereti','Emmining':'Emmining','Timboroa':'Timboroa','Torongo':'Torongo','Simotwet':'Simotwet'}",
                            submit: 'Save changes',
                            callback: function(value, settings)
                            {
                                alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue, value, row, column, settings) {
                                document.getElementById("msg").innerHTML = "(Cell Callback): is updated with value " + sValue;
                            }

                        }
                        

   ,                                                                                          
     //=====edit chws page
   null, null, null, null
                    ]

                });






            });



        </script>









        <script type="text/javascript">
            $(function() {


                $("#dialog").dialog({
                    autoOpen: false,
                    width: 400,
                    buttons: [
                        {
                            text: "Ok",
                            click: function() {
                                $(this).dialog("close");
                            }
                        }
                    ]
                });



                $("#dialog-link").click(function(event) {
                    $("#dialog").dialog("open");
                    event.preventDefault();
                });





            });
        </script>

        <script type="text/javascript">

            var _gaq = _gaq || [];
            _gaq.push(['_setAccount', 'UA-17838786-2']);
            _gaq.push(['_trackPageview']);

//            (function() {
//                var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
//                ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
//                var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
//            })();

        </script>
        <script type="text/javascript">


            function searchmother() {


                // window.open("districtchooser?county="+dist.value);     
                var xmlhttp;

                var name = document.getElementById("searchmum").value;
                if (name == "")
                {
                    //filter the districts    



                    //alert("anc number is blank");
                    return;
                }
                if (window.XMLHttpRequest)
                {// code for IE7+, Firefox, Chrome, Opera, Safari
                    xmlhttp = new XMLHttpRequest();
                }
                else
                {// code for IE6, IE5
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange = function()
                {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {



                        document.getElementById("motherstable").innerHTML = xmlhttp.responseText;
                        //doOnLoad();

                    }
                }
                xmlhttp.open("POST", "searchmother?name=" + name, true);
                xmlhttp.send();

                document.getElementById("motherstable").innerHTML = "<img src=\"images/loading.gif\" alt=\"searching\">";
                //               
            }//end of ajax class




            //===============transfer chw====================================
            function transferchw(chwid, ancno) {


                // window.open("districtchooser?county="+dist.value);     
                var xmlhttp;

                var chw_id = chwid.value;
                if (ancno == "")
                {
                    //filter the districts    



                    alert("anc number is blank");
                    return;
                }
                if (window.XMLHttpRequest)
                {// code for IE7+, Firefox, Chrome, Opera, Safari
                    xmlhttp = new XMLHttpRequest();
                }
                else
                {// code for IE6, IE5
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange = function()
                {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {

                        var n = noty({text: 'updating succesfull! ',
                            layout: 'center',
                            type: 'Success',
                            timeout: 1800});

                        document.getElementById("loading").innerHTML = "";
                        document.getElementById("am_curchw" + ancno).innerHTML = xmlhttp.responseText;
                        //doOnLoad();

                    }
                }
                xmlhttp.open("POST", "transferchw?chwid=" + chw_id + "&ancno=" + ancno, true);
                xmlhttp.send();

                document.getElementById("loading").innerHTML = "<img src=\"images/sending.gif\" alt=\"\"/>    updating..";

            }//end of ajax class



            //             function setsession(anc){
            //                 
            //                 
            //                 
            //
            //        session.setAttribute("ANC_NO", request.getParameter(""));
            //
            //
            //                 
            //             }


        </script>

      
        
        
        

        <title>view all mothers</title>
    </head>
    <body>

        <div id="container" style="width:1300px;" >
            <br/>

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


            <%
                if (session.getAttribute("mother_edited") != null) {%>
            <script type="text/javascript">

                var n = noty({text: '<%=session.getAttribute("mother_edited")%>',
                    layout: 'center',
                    type: 'Success',
                    timeout: 1800});

            </script> 
            <%

                    session.removeAttribute("mother_edited");
                }
                dbConn conn = new dbConn();

                String Sectionshelp3 = "";
                if (conn.st.isClosed()) {
                    conn = new dbConn();
                }

                conn.rs = conn.st.executeQuery("Select * from help where help_id='20'");
                while (conn.rs.next()) {

                    Sectionshelp3 = conn.rs.getString(2);

                }

            %>              





            <br/>
            <div id="content" style="height:950px;width:1192px ; margin-left: 50px;">

                <h4 style="background-color: #99ff99;text-align: center;">VIEW ALL MOTHERS     <a href="#" id ="dialog-link"><img src="images/help_24.png"/></a></h4>

                <!-- ui-dialog -->
                <div id="dialog" title="'View all Mothers' guide ">
                    <p>
                        <% if (Sectionshelp3 != null) {%>
                        <%=Sectionshelp3%> 
                        <%}%>
                    </p>
                </div>


                <table border="0" style="width:1100px;background: #ffffff;margin-left: 10px;" >

                    <tr>
                        <td>
                            <a href="searchhistory.jsp" title="click to view an history of all the edits done on the mothers by anyone" class="linkstyle">history</a>
                        </td>
                        <td> <button id="btnDeleteRow" value="cancel">Delete Mother</button></td>
                        <td colspan="3"><a style="width:150px;" href="maternal_profile.jsp" class="linkstyle" >Mother Baby Book</a></td>
                        <td><a href="ANCRegister.jsp" class="linkstyle" > ANC Register</a></td>
                        <td><a style="width:150px;" href="maternityRegister.jsp" class="linkstyle" > Maternity Register</a></td>
                        <td><a style="width:150px;" href="postnatal.jsp" class="linkstyle" > Postnatal Register</a></td>
                    </tr>

                </table>



                <h3 id="loading" style="text-align: right;"></h3> 
                <%
                    String userid = request.getParameter("userid");

                    int counter = 1;

                    String mothers = "select  *  from mother_details LEFT join atoh on mother_details.motherID=atoh.motherid LEFT join mat_atoh on mat_atoh.motherid =mother_details.motherID LEFT join postnat_atof on mother_details.motherID=postnat_atof.motherid group by mother_details.motherID order by mother_details.timestamp DESC";

                    if (conn.st1.isClosed()) {
                        conn = new dbConn();
                    }
                   // conn.rs1 = conn.st1.executeQuery(mothers);

                %>
                <div>
                    <table cellpadding="0px" cellspacing="0px" border="0px"  id="example" class="display" style="width:1190px;">
                        <thead>
                            <tr style="height:40px ;">
                                <th>ANC No</th>   
                                <th>First Name</th>   
                                <th>Middle name</th> 
                                <th>Last name</th>
                                <th>Mother Phone no</th> 
                                <th>Facility</th>
                                <th>mother baby book</th>
                                <th>anc reg.</th> 
                                <th>maternity reg.</th>
                                <th>postnatal reg.</th>

                            </tr>
                        </thead>
                                        
                          <c:forEach items="${mothersfound}" var="teacher">                      
                                <tr id="${teacher.motherid}">
                                    
                                    <td>${teacher.ancNo}</td>
                                    <td>${teacher.fname}</td>                                  
                                    <td>${teacher.mname}</td>
                                    <td>${teacher.lname}</td>
                                    <td>${teacher.phone}</td>                               
                                    <td>${teacher.facility}</td>
<td> <a href='maternal_profile.jsp?motherID=${teacher.motherid}&ancnumber=${teacher.ancNo}&facil=${teacher.facility}' class='linkstyle' > mother baby</a></td>
<td> <a href='ANCRegister.jsp?motherID=${teacher.motherid}&ancnumber=${teacher.ancNo}&facil=${teacher.facility}' class='linkstyle' >anc reg</a></td>
<td> <a href='maternityRegister.jsp?motherID=${teacher.motherid}&admNo=${teacher.admNo}&facil=${teacher.facility}' class='linkstyle' > maternity reg.</a></td>
<td> <a href='postnatal.jsp?motherID=${teacher.motherid}&pncNo=${teacher.pncNo}&facil=${teacher.facility}' class='linkstyle' > Postnatal reg.</a></td>
             
                            </tr>
                        </c:forEach>
                           
                        

                    </table>

                </div>

            </div>
           

            <div id="footer">
                <%                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);

                %>
                <p align="center"> &copy MNHC Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    

    </body>
</html>

