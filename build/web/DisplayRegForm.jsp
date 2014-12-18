<%-- 
    Document   : index
    Created on : Aug 5, 2013, 9:03:18 PM
    Author     : SIXTYFOURBIT
--%>

<%@page import="sender.dbConn"%>
<%@page import="sendSMS.dbConnect"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MOTHER BABY BOOK</title>
        <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
        <link rel="shortcut icon" href="images/icon.png"/>


        <%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
            response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server

            if (session.getAttribute("userid") == null) {
                response.sendRedirect("index.jsp");
            }
        %>



        <!--clerk menu css-->
        <link rel="stylesheet" href="menu/css/clerkmenucss.css" type="text/css" media="all"/>

        <!--    WIZARD CSS-->
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="js/noty/jquery.noty.js"></script>
        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/center.js"></script> 
        <script type="text/javascript" src="js/noty/themes/default.js"></script>


        <link href="styles/demo_style.css" rel="stylesheet" type="text/css"/>
        <link href="style/smart_wizard_vertical.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="js/jquery-2.0.0.min.js"></script>
        <script type="text/javascript" src="js/jquery.smartWizard.js"></script>
        <script type="text/javascript">
    
    
            //      $(document).ready(function(){
            //    	// Smart Wizard	
            //  		
            //    
            //		});
    
        </script>

        <!--===========================GET VALIDATor==========================--->
        <script type="text/javascript" src="js/validateVerticalSteps.js"></script>
        <script type="text/javascript" src="js/maternalprofilevalidations-em.js"></script>







        <!--+++++++++++++++++++++++++++MY CUSTOM CALENDER+++++++++++++++++++++++++++++++++++++++--->
        <link rel="stylesheet" type="text/css" href="js/codebase/dhtmlxcalendar.css"/>
        <link rel="stylesheet" type="text/css" href="js/codebase/skins/dhtmlxcalendar_dhx_skyblue.css">
        <script src="js/codebase/dhtmlxcalendar.js"></script>
        <!--==================================================================-->





        <script type="text/javascript">

            var myCalendar;
            function doOnLoad() {
                myCalendar = new dhtmlXCalendarObject(["edd","lmp","dpr11","dpr12","dpr13","dpr14","dpr15","dpr16","dpr21","dpr22","dpr23","dpr24","dpr25","dp1","dp2","dp3","dp4","dp5","del_dp","del_dp1","_dpr11","_dpr12","_dpr13","_dpr14","_dpr15","_dpr21","_dpr22","_dpr23","_dpr24","_dpr25","dw_dp0","dw_dp1","dw_dp2","dw_dp3","dw_dp4","dw_dp5","dw_dp6","dw_dp7","dw_dp8","_dw_dp0","_dw_dp1","_dw_dp2","_dw_dp3","_dw_dp4","_dw_dp5","_dw_dp6","_dw_dp7","_dw_dp8","dnadp1","dnadp2","dnadp3","dna_dp1","dna_dp2","dna_dp3","vit_dp1","vit_dp2","vit_dp3","vit_dp4","vit_dp5","vit_dp6","vit_dp7","vit_dp8","vit_dp9","vit_dp10","vit_dp11"
                    ,"_vit_dp1","_vit_dp2","_vit_dp3","_vit_dp4","_vit_dp5","_vit_dp6","_vit_dp7","_vit_dp8","_vit_dp9","_vit_dp10",             "NextVisit","NextVisit1","date1","date2","date3","date4","new_NextVisit1","new_NextVisit2","new_NextVisit3","new_NextVisit4","new_date1","new_date2","new_date3","new_date4","new_date5","new_date6","new_date7","new_date8","Referral_Pap","Referral1",
                    "Referral2","Referral3","DateGiven1","DateGiven2","DateGiven3","DateGiven4","New_DateGiven0","New_DateGiven1","New_DateGiven2","New_DateGiven3","New_DateGiven4",
                    "VisitBaby1","New_VisitBaby2","VisitMum1","VisitMum2","VisitMum3","New_VisitMum1","New_VisitMum2","New_VisitMum3","New_Referral1",
                    "New_Referral2","New_Referral3","CervixDate1","CervixDate2","CervixDate3","New_CervixDate1","New_CervixDate2","New_CervixDate3"]);
   
            }

        </script>



        <!--tooltip and calender-->
        <link href="js/css/start/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>
        <script src="js/js/jquery-ui-1.10.3.custom.js"></script>
        <link rel="stylesheet" href="js/demos.css" />





        <script type="text/javascript">
            $(function() {


                $(document).tooltip();
            }); 
   
        </script>
















        <%
       //  if (session.getAttribute("message")!= null)  { %>
        <script type="text/javascript"> 
                    
            //                    var n = noty({text:'saved',
            //                        layout: 'center',
            //                        type: 'Success',
            //                        timeout: 1800});
                    
        </script> <%

//session.removeAttribute("message");
// }

        %>  

    </head>    




    <!-- Body page -->



    <body >
        <embed src="sound/error.mp3" autostart="false" width="0" height="0" id="sound1"
               enablejavascript="true"/>
        <div id="container" style="height:670px ;width:1300px;">
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


            <br/>
            <div id="content" style="width:1250px;border-top-color:greenyellow ; margin-left: 25px;border-top-style: dotted;">
                <br/>
                <!------------------------------------------------CONTENT DIV------------------------------------------------------------->                

                
                
                <iframe name="myform" style="width:1220px ;height:670px; padding-left:1px;" src="https://om5be.enketo.formhub.org/webform">

                </iframe>  <!--last form-->
            </div>



            <div id="footer">

            </div>
        </div>
    </body>


</html>
