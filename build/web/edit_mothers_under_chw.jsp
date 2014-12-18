<%-- 
    Document   : edit_clerk2
    Created on : Aug 15, 2013, 11:56:22 AM
    Author     : Nyabuto Geofrey
--%>


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

    if (!session.getAttribute("level").equals("0")) {
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

        <!--clerk menu-->

        <link rel="stylesheet" type="text/css" href="menu/css/clerkmenucss.css"/>		
        <script src="menu/prefix-free.js"></script> 

        
        
        
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="js/noty/jquery.noty.js"></script>
        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/center.js"></script>
        <!-- You can add more layouts if you want -->
        <script type="text/javascript" src="js/noty/themes/default.js"></script>

        <script type="text/javascript" >
                    
                    
            function transferchw(chwid,ancno){
    

                // window.open("districtchooser?county="+dist.value);     
                var xmlhttp;  
                
                var chw_id=chwid.value;
                if (ancno=="")
                {
                    //filter the districts    



                    alert("anc number is blank");
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
                        
                         var n = noty({text: 'updating succesfull! ',
                                        layout: 'center',
                                        type: 'Success',
                                        timeout: 1800});
                        
                        document.getElementById("loading").innerHTML="<a style=\"text-align:center;\" href=\"mothers_under_chw.jsp\" class=\"linkstyle\">back</a>";
                        document.getElementById("curchw"+ancno).innerHTML=xmlhttp.responseText;
                        //doOnLoad();

                    }
                }
                xmlhttp.open("POST","transferchw?chwid="+chw_id+"&ancno="+ancno,true);
                xmlhttp.send();

                document.getElementById("loading").innerHTML="<img src=\"images/sending.gif\" alt=\"\"/>    updating..";                       
                        
            }//end of ajax class
                    
        </script>



        <title>Mothers under a chw</title>
    </head>
    <body>

        <div id="container" >
            <%@ include file="/menu/adminmenu.html" %>
            <br/>
            <div id="content" style="height:750px; margin-left: 150px;">

                <h4><i>select the drop down to choose the new chw of the current mother</i></h4>

                <h3 id="loading" style="text-align: right;"></h3> 
                <%
                    String userid = request.getParameter("userid");


                    int counter = 1;
                    String editor = "select * from chw WHERE chw_id='" + userid + "' OR chv_phone='"+userid+"'";
                    String mothers ="select * from mother_details where ChwID='" + userid + "'";
                    dbConn conn = new dbConn();
                   
                    conn.rs1 = conn.st1.executeQuery(mothers);

                %>

                <table cellpadding="0px" cellspacing="0px" border="0px" class="viewpdt" style="width:780px;">
                    <tr>
                        <th>ANC No</th>   
                        <th>First Name</th>   

                        <th>Middle name</th> 
                        <th>Last name</th>
                        <th>Mother Phone no</th> 
                        <th>current chw</th> 

                        <th> change chw</th> 
                         

                    </tr>
                    <%
                        int count = 1;
                        while (conn.rs1.next()) {


                    %>
                    <tr>
                    <form action="updatechw" method="post">
                        <input type="hidden" name="userid" value="<%=conn.rs1.getString("motherID")%>"/>
                        <td class="format_cell"><%=conn.rs1.getString("anc_no")%></td>  
                        <td class="format_cell"><%=conn.rs1.getString("FName")%></td>  
                        <td class="format_cell"><%=conn.rs1.getString("SName")%></td> 
                        <td class="format_cell"><%=conn.rs1.getString("LName")%></td> 
                        <td class="format_cell"><%=conn.rs1.getString("PhoneNo")%></td>
                        <%
                         conn.rs = conn.st.executeQuery(editor);
                            String currentchvphone = "",chwid;
                            int checkcurchw=0;
                            while (conn.rs.next()) {
                                checkcurchw++;
                                currentchvphone = conn.rs.getString("chv_phone");
                        %>
                        <td class="format_cell"><p id="curchw<%=conn.rs1.getString("anc_no")%>"><%=conn.rs.getString("chv_fname") + " " + conn.rs.getString("chv_lname")%></p></td> 
                        <%}
    
                        %>
                        <td> 
                            <select  name="newchw" onchange="transferchw(this,'<%=conn.rs1.getString("anc_no")%>');" ><option value="newchw">select the new chw</option>
                                <% String chwlist = "";

                                    conn.rs2 = conn.st2.executeQuery("select * from chw where chv_phone!='" + currentchvphone + "'");
                                    while (conn.rs2.next()) {
                                %>


                                <option value="<%=conn.rs2.getString("chv_phone")%>"><%=conn.rs2.getString(2) + " " + conn.rs2.getString(3) + " " + conn.rs2.getString(4)%></option>

                                <%}%>
                            </select></td>             


                        <!--<select  name="chages" onclick="makechanges('');" value="Save" ><option value="transfer">select th new chw</option>
                        <option value="remove">delete</option></select></td>             -->
                    </form>
                    </td> 

                    </tr>
                    <%
                            count++;
                        } if(count==1){%>
                        <tr  ><td colspan="7" style="background:yellow ;">No Mother is under this community health worker! hit the backspace to go back to previous page</td></tr>
                        
                        <%}%>
                </table>


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

