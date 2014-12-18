<%-- 
    Document   : Diary
    Created on : Oct 17, 2013, 8:29:44 AM
    Author     : Maureen
--%>
<%@page import="sender.dbConn"%>
<%!    HttpSession session;

%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="js/css/south-street/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>
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
        <title>download data</title>
        <script type="text/javascript" src="js/noty/themes/default.js"></script> 
        <script>
            $(function() {
                    
                    
                $( "#datepicker" ).datepicker({
                    dateFormat: "yy_mm_dd",
                    changeMonth: true,
                    changeYear: true
                               
                });
                    
            });
            $(function() {
                    
                    
                $( "#datepicker1" ).datepicker({
                    dateFormat: "yy_mm_dd",
                    changeMonth: true,
                    changeYear: true
                               
                });
                    
            });

        </script>


        <script type="text/javascript">
   
   
            function download_data(){
      
                var myreturn=true;
                var formname=document.getElementById("formname").value;
                var username=document.getElementById("uname").value;

                var date=document.getElementById("datepicker").value;    
                var date1=document.getElementById("datepicker1").value;  
        
                if(date >  date1&&date!=""&&date1!=""){
            
                    alert("start date cannot be greater than end date");
                    myreturn= false;
                }
        
                var mydate="";
                var mydate1="";
                mydate=date+"_00_00_00";
                mydate1=date1+"_23_59_59";
        
        
                var dateurl="";
         
                if(date!=""&&date!=null&&date1!=""&&date1!=null){
                    mydate=mydate.substring(2);
                    mydate1=mydate1.substring(2);
                    dateurl="?start="+mydate+"&end="+mydate1;
                }
                else{
             
                    if(date!=""||date1!=""){
                        if(date==""){alert("enter start date");
        
    
                            $('#datepicker').slideToggle("slow",function() {});
                            $('#datepicker').slideToggle("slow",function() {});   
                            $('#datepicker').focus();
                            myreturn= false;    
                        }
                        else if(date1==""){alert("Enter end date"); 
        
                            $('#datepicker1').slideToggle("slow",function() {});
                            $('#datepicker1').slideToggle("slow",function() {});   
                            $('#datepicker1').focus();    
    
                            myreturn= false;    
                        }
    
    
                    }
                }
                var url="https://formhub.org/"+username+"/forms/"+formname+"/data.xls";   
  
                // window.open("districtchooser?county="+dist.value);     
   
                if (date!="" &&date1!="")
                {
                    //filter the districts    

                    url="https://formhub.org/"+username+"/forms/"+formname+"/data.xls"+dateurl;



                }

   
                //alert(""+url);
               // alert(myreturn);
                if(myreturn==true){
                    var win = window.open(url, '_blank'); 
   
                    if(win){
                        //Browser has allowed it to be opened
                        win.focus();
                    }else{
                        //Broswer has blocked it
                        alert('Please allow popups for MNHC System');
    
                    }
                }
                return myreturn;

            }//end of send search date

        
        
        </script>


        <script>
            function compare(){
                var startDate = new Date($('#datepicker').val());

                var endDate = new Date($('#datepicker1').val()); 

                if (startDate > endDate)

                {

                    // Do something
                    alert("The first date should be earlier than the second ");
                    return false;

                }
        
            }    
        </script>


    </head>
    <body >
        <div id="container" style="width:1300px;" >

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

            <div id="content" style="width:1000px;">
                <%if (session.getAttribute("warning") != null) {%>
                <script type="text/javascript"> 
                    
           var n = noty({text: '<%=session.getAttribute("warning")%>',
               layout: 'center',
               type: 'Success',
 
               timeout: 1800});
                    
                </script> <%
                        session.removeAttribute("warning");
                    }

                %>




                <div id="midcontent" style="margin-left:250px ;">

                    <form  method="post" style=" border-width: 1px; background-color:white ; margin-bottom: 20px; margin-top: 20px;">
                        <h3 style="background-color: greenyellow;">Download data of the enrolled mothers from form hub.
                            <img src="images/help_24.png" title="Do not change the formhub username and formname fields unless otherwise advised.The startdate and end date are optional. However, to get data between a certain range of time, enter the start  and the end date, then click the download data button.
                                 If you DONT fill the start and end dates, you will be downloading alltime data for all the enrollments and this is not advised unless its the first time you are downloading the data.
                                 NB: You must tranfer your .xls file into the folder C:/MNHC_UPLOADS inorder to be able to import the records into the system."/>
                        </h3>
                        <br/><br/>
                        <table cellpadding="4px" cellspacing="8px">
                            <tr><td style="text-align: left;">Formhub username:<font color="red">*</font></td><td><input type="text" value="elkant" class="textbox" id="uname"/></td></tr>
                            <tr><td style="text-align: left;">Form name:<font color="red">*</font></td><td><input type="text" id="formname" value="Koibatek" class="textbox"/></td></tr>

                            <tr><td style="text-align: left;">Start  date</td>
                                <td> <input type="text"   name="startdate" value="" class="textbox" id="datepicker"></td> 
                            </tr><tr> <td style="text-align: left;">End date</td><td> <input type="text"   name="enddate" value="" class="textbox" id="datepicker1">
                                </td></tr><tr>
                                <td></td><td>
                                    <input type="text" required  value="download data"  readonly style=" cursor:pointer;margin-left: 0px ; height: 23px; width:130px;text-align:center ; color:white ;background:coral; border-style:outset ;" onclick="return download_data(); "/>
                                </td>
                            </tr>
                        </table>



                    </form>




                </div>
            </div>



            <div id="footer">


                <%
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);

                %>
                <p align="center"> &copy Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    
    </body>
</html>
