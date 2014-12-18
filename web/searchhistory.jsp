<%-- 
    Document   : Diary
    Created on : Oct 17, 2013, 8:29:44 AM
    Author     : Maureen
--%>
<%@page import="sender.dbConn"%>
<%!
HttpSession session;

%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!--         <script type="text/javascript" src="js/jquery-1.9.1.js" ></script>-->
        
          <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
        
        <link href="js/css/ui-lightness/jquery-ui-1.10.3.custom.css" rel="stylesheet">
<!--	<script src="js/jquery-1.9.1.js"></script>-->
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
<title>Editing History</title>
<script type="text/javascript" src="js/noty/themes/default.js"></script> 
   <script type="text/javascript" src="js/noty/themes/default.js"></script> 
    <script>
           $(function() {
                    
                    
                        $( "#datepicker" ).datepicker({
                                dateFormat: "yy/mm/dd",
                                changeMonth: true,
                                changeYear: true
                               
                        });
                    
                });
           $(function() {
                    
                    
                        $( "#datepicker1" ).datepicker({
                                dateFormat: "yy/mm/dd",
                                changeMonth: true,
                                changeYear: true
                               
                        });
                    
                });

    </script>
        
        <script type="text/javascript">
   
   
   function filter_history(){

        var date=document.getElementById("datepicker").value;    
        var date1=document.getElementById("datepicker1").value;   
        
        
        
        

// window.open("districtchooser?county="+dist.value);     
var xmlhttp;    
if (date=="" && date1=="")
{
//filter the districts    




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
    

document.getElementById("historytable").innerHTML=xmlhttp.responseText;


}
}
xmlhttp.open("POST","filter_history?startdate="+date+'&'+"enddate="+date1,true);
xmlhttp.send();

document.getElementById("historytable").innerHTML=" <img src=\"images/sending.gif\">  Loading.. ";

}//end of send search date

        
        
    </script>
    <script type="text/javascript">
  function anyCheckboxesChecked() {
    
  var inputs = document.getElementsByTagName('input');
 
  for (var i = 0; i < inputs.length; ++i) {
    if (inputs[i].type === "checkbox" && inputs[i].checked)
      return true;
  }
  return false;
}
</script>
<script type="text/javascript">
function chekBox(){
   if (!anyCheckboxesChecked()) {
     alert("Please select a mother to send an sms");
     return false;
   }
        }
        
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
    
    
  
 
    
           

<script type="text/javascript" src="js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.pager.js"></script>
<link rel="stylesheet" type="text/css" href="js/jquery.tablesorter.pager.css"/>
        <link rel="stylesheet" type="text/css" href="css/style_1.css" id="" media="print, projection, screen"/>

  <script type="text/javascript"> 
	$(document).ready(function() 
    { 
        $("#historytable")
        .tablesorter({widthFixed: true, widgets: ['zebra']})
        .tablesorterPager({container: $("#pager")}); 
    });
	</script> 

<!--	
//     function tableSorter()
//{  
//  $(".viewpdt")
//    .tablesorter()
//    .tablesorterPager({container: $("#pager")});
//
////  var rows = $('table.historytable')[0].config.totalRows;
////  $('select.pagesize').find('option:contains("all")').val(rows);
//} -->
  
  
  </script> 
         
    </head>
    <body>
        <div id="container" style="width:1300px;" >
 
              <div id="header" style="width:1250px; margin-left: 25px;">

                    
                  <!--=========================================menu=========================================-->     
           <%
if(session.getAttribute("level")!=null){            

if (session.getAttribute("level").equals("0")) {%>
            <%@include file="menu/adminmenu.html" %>
            <%}
else{%>

 <%@include file="menu/clerkmenu.html" %>

<%}

}

else{ %>
        
             <%@include file="menu/clerkmenu.html" %>
            
           <%}%>
            
        <!--=====================================================================================-->  
                    
                    
              </div>
        <br/>
            
            <div id="content" style="width:1000px;">
       <%if (session.getAttribute("warning") != null) { %>
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
                    
        <form  method="post" style=" border-width: 0px;margin-bottom: 20px; margin-top: 20px;">
                <table>
            <tr><td>Search for history between date</td>
                <td> <input type="text"   name="startdate" value="" class="datepicker" id="datepicker"></td> 
                <td> and <input type="text"   name="enddate" value="" class="datepicker1" id="datepicker1">
                </td>
             <td>
            <input type="text" required  value="Search.."  readonly style=" cursor:pointer;margin-left: 40px; text-transform:uppercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="filter_history();"/>
             </td>
            </tr>
               </table>
            </form>
                      
   
           <form action="#" method="POST"  onsubmit="chekBox();"  >
                 <%
String date = (String)session.getAttribute("date");  
     

%>
                 <h5>A history of  inserts and updates of Mothers  </h5>
                 
                   <%
                   dbConn conn= new dbConn();

String hist="select * from history order by date desc";           
%>
                    <table class="viewpdt"  id="historytable" style="width: 630px;">
	<thead>
         <tr>
                    <th>ANC NO</th>
		    <th>DATE</th>
		    <th>PAGE</th>
		    <th>DONE BY USER</th>
		    
	
		</tr>
	</thead>
      
               
       
    
<%  conn.rs=conn.st.executeQuery(hist); 
        
while(conn.rs.next()){
%>
<tr>
    <td> <%=conn.rs.getString(2)%></td>
          <td> <%=conn.rs.getString(4)%></td>
          <td><%=conn.rs.getString(5)%> </td>
          
 <% conn.rs1=conn.st1.executeQuery("select * from clerks where clerk_id='"+conn.rs.getString(3)+"'");
          
if (conn.rs1.next()){%>

<td> <%=conn.rs1.getString(2)+" "+conn.rs1.getString(3)%>  </td>  
<%}          
                    else{
%>
          <td><%=conn.rs.getString(3)%> (admin) </td><%
                    }%>
         
          
        
              
      
         </tr>
        
       <%}%>
         
          
                    </table>
       <div id="pager" class="pager" style="background-color:#99ff99; padding-top: 10px; padding-bottom: 10px; position: inherit;">
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
        </form>
      
      
        </div>
            </div>

            

             <div id="footer">
            
              
               <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center"> &copy Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    
    </body>
</html>
