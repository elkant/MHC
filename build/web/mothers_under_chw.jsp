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

        <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>   
<script type="text/javascript" src="js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.pager.js"></script>
<link rel="stylesheet" type="text/css" href="js/jquery.tablesorter.pager.css"/>
        <link rel="stylesheet" type="text/css" href="css/style_1.css" id="" media="print, projection, screen"/>

  <script type="text/javascript"> 
	$(document).ready(function() 
    { 
        $("#allchws")
        .tablesorter({widthFixed: true, widgets: ['zebra']})
        .tablesorterPager({container: $("#pager")}); 
    });
	</script> 

        <script type="text/javascript" src="js/noty/jquery.noty.js"></script>

        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/center.js"></script>
        <!-- You can add more layouts if you want -->
        <script type="text/javascript" src="js/noty/themes/default.js"></script>

        
<!--        tooltip-->
<link href="js/css/south-street/jquery-ui-1.10.3.custom.css" rel="stylesheet">
<!--	<script src="js/jquery-1.9.1.js"></script>-->
	<script src="js/jquery-ui-1.10.3.custom.js"></script>
        <script type="text/javascript">
            $(function() {

                $( document ).tooltip();
                $( "#accordion" ).accordion();
                
             
        
        
        
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

        <!--clerk menu-->

        <link rel="stylesheet" type="text/css" href="menu/css/clerkmenucss.css"/>

        <script type="text/javascript">
                    
                    
            function searchchw(){
    

                // window.open("districtchooser?county="+dist.value);     
                var xmlhttp;  
                
                var name=document.getElementById("name").value;
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
                        
                        
                        
                        document.getElementById("allchws").innerHTML=xmlhttp.responseText;
                        //doOnLoad();

                    }
                }
                xmlhttp.open("POST","searchchw?name="+name,true);
                xmlhttp.send();
   document.getElementById("allchws").innerHTML="<img src=\"images/sending.gif\" alt=\"searching\">";
//               
            }//end of ajax class
                    
        </script>

        <title>CHW Details</title>
    </head>
    <body>

        <div id="container" >

            <div id="header" style="width:1150px;">
                <br/>
                <%@ include file="/menu/adminmenu.html" %> 
                <br/>
            </div>
            <div id="content" style="height:750px; width:900px; margin-left: 150px;">



                <h4>View Mothers under the selected community health worker <a href="#" id="dialog-link" ><img src="images/help_24.png"  /> </a></h4>


                <%
      if (session.getAttribute("chw") != null) {%>
                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("chw")%>',
                        layout: 'center',
                        type: 'Success',
 
                        timeout: 1800});
                    
                </script> <%

        session.removeAttribute("chw");
    }

                %>


                 



                    <table border="0" style="width:850px;background: #E3E3E3;">
                        <tr><td>Search :    <img src="images/blguide.png" title="search chw by their first , middle or last name"/></td><td>
                                <input type="text" onkeyup="searchchw();"  style="width:205px;"  id="name"  name="name" value="" required="true" class="textbox" placeholder="type to autosearch"/>
                            </td><td>
                            </td>
                            <td></td>

                            <td><a href="mothers_under_chw.jsp" class="linkstyle">refresh</a></td></tr>
                    </table>


                    <%
                        dbConn conn = new dbConn();
                        int counter = 1;
                        String editor = "select * from chw ";
              conn.rs = conn.st.executeQuery(editor);
              String helpqr="select * from help where help_id='27'";

conn.rs1=conn.st1.executeQuery(helpqr);
String helptext="";

while(conn.rs1.next()){

  helptext=conn.rs1.getString(2);  
    


}

%>
              
              
              
               <div id="dialog" title="'View Mothers under chw' Guide ">
	<p>
         <% if(helptext!=null){%>
         <%=helptext%> 
         <%}%>
        </p>
</div>
              
              
              
                
                <table cellpadding="2px" id="allchws" cellspacing="3px" border="0px" class="cart_style" style="width:850px;">
                    <thead>  <tr>
                        <th>#.</th>
                        <th>First Name</th>   
                        <th>Middle name</th> 
                        <th>Last name</th>
                        <th>Phone no</th> 
                        <th>Mothers under each chw</th> 

                    </tr>
                    </thead>
                    <%
                        while (conn.rs.next()) {
                            
                            //slect a summary of the mothers under each chw
                            
                            String totalmums="select * from mother_details where ChwID='"+conn.rs.getString("chv_phone")+"'";
                            
                            int mums=0;
                            conn.rs3 = conn.st3.executeQuery(totalmums);
                            while(conn.rs3.next()){
                            mums++;
                                
                            }
                            
                            
                    %>
                    <tr>
                        <td><%=counter++%></td>

                        <%String userid = conn.rs.getString("chv_phone");%>
                        <td><%=conn.rs.getString("chv_fname")%></td>  

                        <td><%=conn.rs.getString("chv_mname")%></td> 
                        <td><%=conn.rs.getString("chv_lname")%></td> 
                        <td><%=conn.rs.getString("chv_phone")%></td>  
                        <td> 
                           <form action="edit_mothers_under_chw.jsp" method="post" id="tableform" style="background: #ffffff;">
                                <input type="hidden" name="userid" value="<%=userid%>"/>
                                <input type="submit" name="Submit" value="view" class="submit"/> 
                                 <b>(<%=mums%>)</b>
                            </form><%mums=0;%></td>
                    </tr>
                    <%}%>
                </table>
<p id="serching" style="text-align: center;"></p>
<div id="pager" class="pager">
    <form>
            <img src="images/first.png" class="first"/>
		<img src="images/prev.png" class="prev"/>
		<input type="text" class="pagedisplay"/>
		<img src="images/next.png" class="next"/>
		<img src="images/last.png" class="last"/>
		<select class="pagesize">
			<option selected="selected"  value="10">10</option>
			<option value="20">20</option>
			<option value="30">30</option>
			<option  value="40">40</option>
		</select>
	
</form>
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

