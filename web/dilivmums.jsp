<%-- 
    Document   : edit_clerk2
    Created on : Aug 15, 2013, 11:56:22 AM
    Author     : Nyabuto Geofrey
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="sender.chv"%>
<%@page import="java.util.Calendar"%>
<%@page import="sender.dbConn"%>
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

        <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>   
<!--          <script src="js/jquery-1.9.1.js" type="text/javascript"></script>-->

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
  

<!--        <script src="scripts/jquery-1.4.4.min.js" type="text/javascript"></script>-->
        <script src="scripts/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="scripts/jquery.jeditable.js" type="text/javascript"></script>
      
<!--        <script src="media/js/jquery-ui.js" type="text/javascript"></script>-->
        <!--   <script src="media/js/jquery.validate.js" type="text/javascript"></script>-->
        <script src="scripts/jquery-ui.js" type="text/javascript"></script>
        <script src="scripts/jquery.dataTables.editable.js" type="text/javascript"></script>
        <script src="scripts/jquery.validate.js" type="text/javascript"></script>
        <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
<!--        <link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />-->
  <link href="js/css/ui-lightness/jquery-ui-1.10.3.custom.css" rel="stylesheet"><!--
  -->
   <script src="media/js/jquery.jeditable.datepicker.js" type="text/javascript"></script>
  
       
<!--        <script src="js/jquery-ui-1.10.3.custom.js"></script>-->
  
        
        
        
        <!---------JNOTY------------->
           <link rel="stylesheet" type="text/css" href="menu/css/clerkmenucss.css"/>		
        <script src="menu/prefix-free.js"></script> 

        <script type="text/javascript" src="js/noty/jquery.noty.js"></script>

        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/center.js"></script>
        <!-- You can add more layouts if you want -->
        <script type="text/javascript" src="js/noty/themes/default.js"></script>
        
        
        
 <script type="text/javascript">
    
            $(document).ready( function () {
                $('#example').dataTable().makeEditable({
                                  
									
                    sUpdateURL: "editviewedmum",
                     sDeleteURL: "deletemum",
                    //                                                                        sAddURL: "AddCounty",
                   
                    "aoColumns": [ null,   

//============
////                    									
                        {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit:'Save changes',
                            callback : function(value, settings)
                            { 
                                alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
                                document.getElementById("msg").innerHTML="(Cell Callback): is updated with value " + sValue;
                            }
                                                                                              
                        },
                       
                        
                        //================
                        
                                         {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit:'Save changes',
                            callback : function(value, settings)
                            { 
                                alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
                                document.getElementById("msg").innerHTML="(Cell Callback): is updated with value " + sValue;
                            }
                                                                                              
                        },
                        
                        
                        //================================
                        
                                         {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit:'Save changes',
                            callback : function(value, settings)
                            { 
                                alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
                                document.getElementById("msg").innerHTML="(Cell Callback): is updated with value " + sValue;
                            }
                                                                                              
                        },
                        
                        //===============================
                        
                        
                                         {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'textarea',
                            submit:'Save changes',
                            callback : function(value, settings)
                            { 
                                alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
                                document.getElementById("msg").innerHTML="(Cell Callback): is updated with value " + sValue;
                            }
                                                                                              
                        },
                        
                       
                        //===============================
                        //===============================

                                     {  type:   'datepicker',
                                        sSuccessResponse: "IGNORE"
                                     },null                   									
                    ]									

                });
				
            } );
            
           
            
        </script>
  
        
        
        
        
        
        
        
        
        <script type="text/javascript">
            $(function() {
                
                
                $( "#dialog" ).dialog({
                    autoOpen: false,
                    width: 400,
                    buttons: [
                        {
                            text: "Ok",
                            click: function() {
                                $( this ).dialog( "close" );
                            }
                        }
                    ]
                });



                $( "#dialog-link" ).click(function( event ) {
                    $( "#dialog" ).dialog( "open" );
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
                    
                    
            function searchmother(){
    

                // window.open("districtchooser?county="+dist.value);     
                var xmlhttp;  
                
                var name=document.getElementById("searchmum").value;
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
                        
                        
                        
                        document.getElementById("motherstable").innerHTML=xmlhttp.responseText;
                        //doOnLoad();

                    }
                }
                xmlhttp.open("POST","searchmother?name="+name,true);
                xmlhttp.send();

                document.getElementById("motherstable").innerHTML="<img src=\"images/loading.gif\" alt=\"searching\">";
                //               
            }//end of ajax class
               
               
               
              
            //===============transfer chw====================================
            function changestatus(statusi,ancno){
   
                  
                var xmlhttp;  
                
                var statusid=statusi.value;
                if (ancno=="")
                {
                    //filter the districts    



                    alert("choose anc");
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
                        
                        var n = noty({text: '<img src=\"images/present.png\">   update succesfull! ',
                            layout: 'center',
                            type: 'Success',
                            timeout: 1800});
                        
                        document.getElementById("loading").innerHTML="";
                        //document.getElementById("am_curchw"+ancno).innerHTML=xmlhttp.responseText;
                        //doOnLoad();

                    }
                }
                xmlhttp.open("POST","updatemotherstatus?statusid="+statusid+"&ancno="+ancno,true);
                xmlhttp.send();

                document.getElementById("loading").innerHTML="<img src=\"images/sending.gif\" alt=\"\"/>    updating..";                       
                        
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



        <title>Update mothers status</title>
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
                    
            </script> <%

                    session.removeAttribute("mother_edited");
                }
                dbConn conn = new dbConn();


                String Sectionshelp3 = "";

                conn.rs = conn.st.executeQuery("Select * from help where help_id='26'");
                while (conn.rs.next()) {

                    Sectionshelp3 = conn.rs.getString(2);

                }

            %>              





            <br/>
            <div id="content" style="height:750px;width:1200px ; margin-left: 50px;">

                <h4 style="text-align: center;"> Update Mothers Status    <a href="#" id ="dialog-link"><img src="images/help_24.png"/></a></h4>

                <!-- ui-dialog -->
                <div id="dialog" title="'View all Mothers' guide ">
                    <p>
                        <% if (Sectionshelp3 != null) {%>
                        <%=Sectionshelp3%> 
                        <%}%>
                    </p>
                </div>


                <table border="0" style="width:1100px;background: #E3E3E3;margin-left: 10px;" >

                    <tr>
                        <td>
                         </td>
<td> <button id="btnDeleteRow" value="cancel">Delete Mother</button></td>
                    </tr>

                </table>
                    
                   
                    
                <h3 id="loading" style="text-align: right;"></h3> 
                <%
                    String userid = request.getParameter("userid");


                    int counter = 1;

                    String mothers = "select *  from mother_details order by motherID DESC";


                    conn.rs1 = conn.st1.executeQuery(mothers);

                %>
                <div>
                    <table cellpadding="0px" cellspacing="0px" border="0px"  id="example" class="display" style="width:1150px;">
                        <thead>
                            <tr>
                                <th>ANC No</th>   
                                <th>First Name</th>   
                                <th>Middle name</th> 
                                <th>Last name</th>
                                <th>Mother Phone no</th> 
                                <th>Delivery Date</th> 
                                
                                <th>Change Status</th>
                               

                            </tr>
                        </thead>
                        <tbody>                  
                            <%
                                int count = 1;
                                while (conn.rs1.next()) {


                            %>
                            <%String mumid=conn.rs1.getString("motherID");%>


<tr id="<%=mumid%>">
                           

                                <td class="format_cell"><input type="hidden" name="userid" value="<%=conn.rs1.getString("motherID")%>"/>
                                    <%=conn.rs1.getString("anc_no")%></td>  
                                <td class="format_cell"><%=conn.rs1.getString("FName")%></td>  
                                <td class="format_cell"><%=conn.rs1.getString("SName")%></td> 
                                <td class="format_cell"><%=conn.rs1.getString("LName")%></td> 
                                <td class="format_cell"><%=conn.rs1.getString("PhoneNo")%></td>
                                <td class="format_cell"><%=conn.rs1.getString("delivery_date")%></td>
                                <td>
                        <%
                            String editor = "select * from status ";
                            conn.rs = conn.st.executeQuery(editor);
                         String myoption="<option value=\"\"></option>";
                          
                            while (conn.rs.next()) {
                                if(conn.rs1.getString("status_id").equals(conn.rs.getString("status_id"))){
                                    
                                    myoption+="<option selected value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";
                                
                                }
                                                               else{
                                                               
                                                              myoption+="<option  value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>"; 
                                                               }
                                                               }
                        %>
                       


                        
                            <select  name="newchw" onchange="changestatus(this,'<%=conn.rs1.getString("anc_no")%>');" >
                                <%=myoption%>

                            </select>
                        </td>     
                               
                               
                            


             

                            </tr>
                            <%
                                    count++;
                                                                       }
                               %>

                        </tbody> 

                    </table>
                    
                </div>

            </div>
<%


%>

            <div id="footer">
                <%
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);

                %>
                <p align="center"> &copy MNHC Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    

    </body>
</html>

