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

        <link href="js/css/ui-lightness/jquery-ui-1.10.3.custom.css" rel="stylesheet">
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
<title>Diary</title>
<script type="text/javascript" src="js/noty/themes/default.js"></script> 
    <script>
           $(function() {
               
               
               
               
               $( "#dialog1" ).dialog({
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



$( "#dialog-link1" ).click(function( event ) {
			$( "#dialog1" ).dialog( "open" );
			event.preventDefault();
		});
               
                    
                    
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
   
   
   function search(){

        var date=document.getElementById("datepicker").value;    
        var date1=document.getElementById("datepicker1").value;    

// window.open("districtchooser?county="+dist.value);     
var xmlhttp;    
if (date=="" && date1=="")
{
//filter the districts    



document.getElementById("SearchResults").innerHTML="";
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
document.getElementById("SearchResults").innerHTML=xmlhttp.responseText;
counts();

}
}
xmlhttp.open("POST","searchDefaulters?startdate="+date+'&'+"enddate="+date1,true);
xmlhttp.send();


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
   else{
       return true;
       
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
    
    <script>
      function counts(){  
          var x= parseInt(document.getElementById("count").value);
//          alert(x);
       
          var i;
          for(i=1;i<=x;i++){
//              alert(document.getElementById("status"+i).value);
              if(document.getElementById("status"+i).value== "Attended"){
                  document.getElementById("defaulter"+i).disabled=true;
                  
                  
              }
              
              
          }
       
        }
        
    </script>
    </head>
    <body onload="counts();">
        <div id="container" style="width:1300px;" >
 
              <div id="header" style="width:1250px; margin-left: 25px;">

                    
                  <%@include file="menu/clerkmenu.html" %>    
                    
                    
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
                      

                       
              
                <div id="midcontent" style="margin-left:250px;">
                  
        <form action="searchDefaulters" method="post" style="background-color: orange; border-width: 0px;margin-bottom: 20px; margin-top: 20px;">
               
            <a href="#" id ="dialog-link1"><img src="images/help_24.png"/></a>
            <table>
            <tr><td>Search for dates between</td>
                <td> <input type="text"   name="startdate" value="" class="datepicker" id="datepicker"></td> 
                <td> and <input type="text"   name="enddate" value="" class="datepicker1" id="datepicker1"/>
                </td>
             <td><input type="text"  value="SEARCH" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:green; border-style:ridge ;" onclick="search(); compare();"/></td> 
            </tr>
                </table>
            </form>
                    
           <form action="defaulterMSG" method="POST"  onsubmit=" return chekBox();"  style="background-color: orange;">
                 <%
String date = (String)session.getAttribute("date");  
     
dbConn conn = new dbConn();
                       
            
String Sectionshelp3="";

conn.rs=conn.st.executeQuery("Select * from help where help_id='21'");
while(conn.rs.next()){
    
Sectionshelp3=conn.rs.getString(2);

}



%>              
        <div id="dialog1" title="'Daily expected Visitors' Guide ">
	<p>
         <% if(Sectionshelp3!=null){%>
         <%=Sectionshelp3%> 
         <%}%>
        </p>
</div>
                 <h4>ANC VISITS MOTHER DIARY <%= date %></h4>
                    <table class="viewpdt"  id="SearchResults" style="width: 630px;">
	<thead>
            
            
   
            
          
          
		<tr>
                    <th>ANC NO</th>
		    <th>FIRST NAME</th>
		    <th>MIDDLE NAME</th>
		    <th>PHONE NO.</th>
		    <th>NOK PHONE NO.</th>
		    <th>STATUS</th>
	             <th>SEND SMS</th>
		</tr>
	</thead>
        <%
        int count=0;
%>
         <c:forEach  var="detail" items="${details}"  >
           <% count++; %>
          <c:set var="id"  value="${detail.ANCNO}"></c:set>
          <c:set var="phoneNo"  value="${detail.PHONENO}"></c:set>
          <c:set var="FName"  value="${detail.FNAME}"></c:set>
          <c:set var="language"  value="${detail.LANGUAGE}"></c:set>
          <c:set var="consent"  value="${detail.CONSENT}"></c:set>
          <c:set var="nokPhone"  value="${detail.NOKPHONE}"></c:set>
          <c:set var="nok"  value="${detail.NOK}"></c:set>
         
         
       
        
        <input type="hidden" id="id" name="id<%= count %>" value="<%= pageContext.getAttribute("id")%>" />
       <input type="hidden" id="phoneNo" name="phoneNo<%= count %>" value="<%= pageContext.getAttribute("phoneNo")%>" />
       <input type="hidden" id="FName" name="FName<%= count %>" value="<%= pageContext.getAttribute("FName")%>" />
       <input type="hidden" id="language" name="language<%= count %>" value="<%= pageContext.getAttribute("language")%>" />
       <input type="hidden" id="consent" name="consent<%= count %>" value="<%= pageContext.getAttribute("consent")%>" />
       <input type="hidden" id="nokPhone" name="nokPhone<%= count %>" value="<%= pageContext.getAttribute("nokPhone")%>" />
       <input type="hidden" id="nok" name="nok<%= count %>" value="<%= pageContext.getAttribute("nok")%>" />
       <input type="hidden" id="date" name="date" value="<%= date %>" />
      
         
     <tr id="<%=pageContext.getAttribute("id")%>">
          <td>${detail.ANCNO} </td>
          <td>${detail.FNAME} </td>
          <td>${detail.SNAME} </td>
          <td>${detail.PHONENO} </td>
          <td>${detail.NOKPHONE} </td>
          
          <td> ${detail.STATUS}</td>
              <td><input type="checkbox" value="<%=pageContext.getAttribute("id")%>" name="defaulter<%= count %>" id="defaulter<%= count %>" ></td>
          <input type="hidden"  id="status<%= count %>" value="${detail.STATUS}">
         </tr>
        
       
         </c:forEach>
      
	   <input type="hidden" id="count" name="count" value="<%= count %>" /> 
        <tr >
            <td colspan="8"><input type="submit"  value="Send SMS to defaulters" name="submit" style="width: 200px;height:30px;" ></td>      

        </tr>  
                   
                    </table>
                    
        
        </form>
       
<!--                    </tbody>
          </form>-->
        
<!--        <table class="viewpdt">
                                 </table>-->
        </div>
            </div>

            

             <div id="footer">
              <!--  <h2 align="left"> <img src="images/Coat of arms.JPG" alt="logo" height="76px" /></h2>-->
              
               <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center"> &copy Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    
    </body>
</html>
