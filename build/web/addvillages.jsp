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
     if(session.getAttribute("level")!=null){
     if (!session.getAttribute("level").equals("0")) {
        response.sendRedirect("index.jsp");
        }
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


        <!--clerk menu-->

        <link rel="stylesheet" type="text/css" href="menu/css/clerkmenucss.css"/>


        
<!--tooltip and calender-->
<link href="js/css/south-street/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>
<script src="js/js/jquery-ui-1.10.3.custom.js"></script>
<link rel="stylesheet" href="js/demos.css" />
        
        
        <script type="text/javascript">
            $(document).ready(function(){
                
              
                
            });
            function validaterows(){
                var isvalid=true;
                var allrows=$("#no_of_rows").val(); 
                for(var x=1;x<parseInt(allrows);x++){
                    if(doValidation(x)==false){
                         
                        isvalid=false;
                        break; 
                    }  
                    else{
                       // alert("else");
                        isvalid=true;
                    }
                       
                }
                   
                return isvalid;
            }
                
            
            
            function doValidation(id ){
                
                var isvalid=true;
                var fname=$("#chv_fname"+id).val();    
                var mname=$("#chv_mname"+id).val();    
                var lname=$("#chv_lname"+id).val();    
                var phone=$("#chv_phone"+id).val();    
               // var sendsms=document.getElementById("chv_sms"+id).checked;    
                if(fname!=""||mname!=""||lname!=""||phone!=""){
                    
                    if(fname==""){
                        alert("enter first name");
                        $("#chv_fname"+id).css("border-color", "#ff0000"); 
                        $('#chv_fname'+id).slideToggle( "slow", function() {});
                        $('#chv_fname'+id).slideToggle( "slow", function() {});
                        $('#chv_fname'+id).focus();
                        isvalid=false;
                    }
                    
                    else if(lname==""){
                        alert("enter chws last name");
                        $("#chv_lname"+id).css("border-color", "#ff0000"); 
                        $('#chv_lname'+id).slideToggle( "slow", function() {});
                        $('#chv_lname'+id).slideToggle( "slow", function() {});
                        $('#chv_lname'+id).focus();
                        isvalid=false;
                    }
                    
                    else if(phone==""){
                        alert("enter chws phone number");
                        
                        $("#chv_phone"+id).css("border-color", "#ff0000"); 
                        $('#chv_phone'+id).slideToggle( "slow", function() {});
                        $('#chv_phone'+id).slideToggle( "slow", function() {});
                        $('#chv_phone'+id).focus();
                        isvalid=false;
                    }
                    
                    else{ //isvalid=true;
                //return checkphone('"+id+"');    
                
                    }
                    
                    
                    
                    
                }
//                else if(fname==""&&mname==""&&lname==""&&phone==""){
//               // alert("You cannot save blank fields");
//                isvalid=false;
//                
//                }
                else{isvalid=true;}
                
                return isvalid;
                
            }
            
//            
//            function togglecb(id){
//            //alert("caled");
//            
//            var cb=$("#chv_sms"+id).val();
//            if(cb=="yes"){
//               document.getElementById("chv_sms"+id).value="no";  
//            }
//            else{
//                document.getElementById("chv_sms"+id).value="yes"
//                
//            }
//            
//            }
            
            
            
            
            function checkphone(row){
            var val=true;
            
           var phonenumber=$("#chv_phone"+row).val();
           
             // window.open("districtchooser?county="+dist.value);     
                var xmlhttp;  
                
                
                if (phonenumber.length<10)
                {
                    val=false;
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
                        
                        var resp=xmlhttp.responseText;
                        resp=resp.trim();
                        if(resp.length>2){
                        $("#chv_phone"+row).css("border-color", "#ff0000"); 
                                          
                       var m = noty({text: '<font color=\"red\"> This phone number is already used</font>',
                        layout: 'center',
                        type: 'Success',
 
                        timeout: 1000});
                       val=false;
                        }
                        else{
                           $("#chv_phone"+row).css("border-color", "#000000"); 
                     
                           // val=true;
                            
                        }
                    }
                }
                if(phonenumber.length>10){
                xmlhttp.open("POST","checkchwno?phone="+phonenumber,true);
                xmlhttp.send();
                }
                  $("#chv_phone"+row).css("border-color", "#000000");
                
            return val;
            }
            
            
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

///////////////////////////////////////////////////////////////////////////////
});
        </script>



        <style type="text/css">

            input{

                height: 40px;

            }

        </style>


        <title>add villages</title>
    </head>
    <body>

        <div id="container" >

            <div id="header" style="width:1150px;">
                <br/>
                <%@ include file="/menu/adminmenu.html" %> 
                <br/>
            </div>
            <div id="content" style="height:750px; margin-left: 120px; width:900px;">



                <h4>Add new villages(s) into the system   <a href="#" id="dialog-link" ><img src="images/help_24.png"  /> </a></h4> <p><i>symbol <font color="red">*</font> denotes compulsory input fields</i></p>


                <%
                    if (session.getAttribute("villageadded") != null) {%>
                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("villageadded")%>',
                        layout: 'center',
                        type: 'Success'});
                    
                </script> <%

                        session.removeAttribute("villageadded");
                    }

                %>


                <h4> 
                    <%
                        dbConn conn = new dbConn();

                        String editor = "select * from villages ";
                        conn.rs = conn.st.executeQuery(editor);%>
                </h4>
                <form action="savevillages" method="post" id="tableform"  style="background: #ffffff;">
                    <div id="dialog" title="Adding a new Village">
                        <p>
                            To register a new Village, enter the village name in the input box provided </p>
                    </div>
                    <table cellpadding="4px" cellspacing="1px" border="0px" class="viewpdt" style="margin-left:30px ;width:800px;">
                        <tr>
                            <th>#</th>
                            <th>Village Name<b><font color="red">*</font></b></th>   
                           

<!--                            <th>Send chwid via sms </th> -->

                        </tr>
                        <%
                            int allrows = 0;
                            for (int counter = 1; counter <= 10; counter++) {
                        %>
                        <tr>
                            <td><%=counter%></td>


                            <td><input type="text" name="villagename<%=counter%>" id="villagename<%=counter%>" /></td>  

                              <p id="checkphone<%=counter%>" ></p></td>
<!--                            <td><input type="checkbox" onclick="togglecb('<%=counter%>');" name="chv_sms<%=counter%>" value="no" id="chv_sms<%=counter%>" /></td>-->

                        </tr>
                        <% allrows++;
                            }%>
                        <tr><td colspan="6" style="background:grey;"> 
                                <input type="hidden" name="no_of_rows" value="<%=allrows%>" id="no_of_rows"/>
                                <input type="submit" name="Submit"  value="SAVE" style="height:40px;" onclick="" class="submit"/>                   
                            </td></tr>
                    </table>
                </form>

            </div>



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

