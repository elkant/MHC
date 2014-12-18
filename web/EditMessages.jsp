<%-- 
    Document   : EditMessages
    Created on : Oct 22, 2013, 12:17:56 PM
    Author     : Maureen
--%>

<%@page import="sender.dbConn"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sendSMS.dbConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<%!
HttpSession session;

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      
         <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         
        <link rel="stylesheet" href="menu/adminmenu_files/css3menu1/style.css" type="text/css" /><style type="text/css">._css3m{display:none}</style>
         <link rel="shortcut icon" href="images/icon.png"/>
          <link rel="stylesheet" type="text/css" href="css/clerkmenucss.css"/>
    
    <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         
        <link rel="stylesheet" href="menu/adminmenu_files/css3menu1/style.css" type="text/css" /><style type="text/css">._css3m{display:none}</style>
         <link rel="shortcut icon" href="images/icon.png"/>
       
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
       <script type="text/javascript" src="js/jquery-ui.js"></script>  
       <script type="text/javascript" src="js/jquery-ui-1.10.3.custom.js"></script>  
       <script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js"></script>  
         <link href="js/css/south-street/jquery-ui-1.10.3.custom.css" rel="stylesheet">
	
	
       <link rel="stylesheet" href="css/jquery-ui-1.10.3.custom.min.css"> 
       <link rel="stylesheet" href="css/datepicker.css"> 
       
       
       
    
   

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
<title>Diary</title>
<script type="text/javascript" src="js/noty/themes/default.js"></script> 
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>

<script type="text/javascript" src="js/jquery.charcounter.js"></script>
<script>
     $(document).ready(function(){
   $("#kaleMsg").charCounter(600, {
	container: "<td><em></em></td>",
	classname: "counter",
	format: "%1 characters to go!",
	pulse: false,
	delay: 100
});

 $("#kaleMsgNOK").charCounter(600, {
	container: "<td><em></em></td>",
	classname: "counter",
	format: "%1 characters to go!",
	pulse: false,
	delay: 100
});
 $("#message").charCounter(600, {
	container: "<td><em></em></td>",
	classname: "counter",
	format: "%1 characters to go!",
	pulse: false,
	delay: 100
});
 $("#nokmessage").charCounter(600, {
	container: "<td><em></em></td>",
	classname: "counter",
	format: "%1 characters to go!",
	pulse: false,
	delay: 100
});
 $("#ujumbe").charCounter(600, {
	container: "<td><em></em></td>",
	classname: "counter",
	format: "%1 characters to go!",
	pulse: false,
	delay: 100
});
 $("#ujumbenok").charCounter(600, {
	container: "<td><em></em></td>",
	classname: "counter",
	format: "%1 characters to go!",
	pulse: false,
	delay: 100
});

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
  
       
    </head>
    <body>
        <div id="container" style="width:1300px; height: 900px;"  >
 
               <div id="header" style="width:1250px; margin-left: 25px;">

                    
                  <%@include file="menu/clerkmenu.html" %>    
                    
                    
              </div>
                  <br/>
                 <div id="content"   style=" width:1000px ;height:700px; margin-left: 200px;">
                    
          <%
dbConnect conn = new dbConnect(); 
                       
            
String Sectionshelp3="";

conn.rs3=conn.state3.executeQuery("Select * from help where help_id='25'");
while(conn.rs3.next()){
    
Sectionshelp3=conn.rs3.getString(2);

}


    

%>
                    <form action="UpdateSMS" method="POST" style="width:990px; ">
<h3 align="center">EDIT MESSAGES  <a href="#" id ="dialog-link"><img src="images/help_24.png"/></a> </h3>
                   
                         
                             <!-- ui-dialog -->
<div id="dialog" title="'Edit Messages' Guide ">
	<p>
         <% if(Sectionshelp3!=null){%>
         <%=Sectionshelp3%> 
         <%}%>
        </p>
</div>
                         
                         <table class="viewpdt"  id="SearchResults" style="width: 975px;">
	<thead>
            
            
            
           
              
	</thead>
      
       
         <c:forEach  var="msgs" items="${message}"  >
          
          <c:set var="id"  value="${msgs.ID}"></c:set>
          <c:set var="message"  value="${msgs.MESSAGE}"></c:set>
          <c:set var="nokmessage"  value="${msgs.NOKMESSAGE}"></c:set>
          <c:set var="status"  value="${msgs.STATUS}"></c:set>
          <c:set var="category"  value="${msgs.CATEGORY}"></c:set>
          <c:set var="ujumbe"  value="${msgs.UJUMBE}"></c:set>
          <c:set var="nokujumbe"  value="${msgs.UJUMBENOK}"></c:set>
        
           <input type="hidden" id="id" name="ID" value="<%= pageContext.getAttribute("id")%>" />
       
       <input type="hidden" id="status" name="status" value="<%= pageContext.getAttribute("status")%>" />
       <input type="hidden" id="category" name="category" value="<%= pageContext.getAttribute("category")%>" />
       
         
    
        
     <tr> <th>Status</th><td>
             <select name="status">
                 <% 
                 ArrayList s = new ArrayList() ;
ArrayList b = new ArrayList() ;
                 if(s!=null && s.size()>0 )
                            {
                                s.clear();
                            }   
                 if(b!=null && b.size()>0 )
                            {
                                b.clear();
                            }   
                 
                String query1 = "select * from status";

                       conn.rs1 = conn.state1.executeQuery(query1);
while (conn.rs1.next()){
b.add(conn.rs1.getString(1));
s.add(conn.rs1.getString(2));

}                                      
        String query = "select * from status where status_id='"+ pageContext.getAttribute("status")+"'";
        
       conn.rs = conn.state.executeQuery(query);
       while(conn.rs.next()){
       String statusID = conn.rs.getString(1);       
       String status = conn.rs.getString(2);  
for(int i=0;i<s.size();i++){

if(s.get(i).equals(status)){
                 
     %>
             <option selected value="<%=b.get(i)%>"><%=s.get(i)%></option>     
                 <%  }
else{
%>
     <option  value="<%=b.get(i)%>"><%=s.get(i)%></option>                
             <%
        }}
              }
        %>
                
                 
                 
                 
             </select>
           </td>
          <th>Category</th><td>
              <select name="category">
               <% 
               ArrayList q = new ArrayList() ;
ArrayList a = new ArrayList() ;
                if(q!=null && q.size()>0 )
                            {
                                q.clear();
                            }   
                 if(a!=null && a.size()>0 )
                            {
                               a.clear();
                            }  
                String querys1 = "select * from message_category";

                       conn.rs1 = conn.state1.executeQuery(querys1);
while (conn.rs1.next()){
q.add(conn.rs1.getString(1));
a.add(conn.rs1.getString(2));

}                                      
        String querys = "select * from message_category where cat_id='"+ pageContext.getAttribute("category")+"'";
        
       conn.rs = conn.state.executeQuery(querys);
       while(conn.rs.next()){
       String catID = conn.rs.getString(1);       
       String cat = conn.rs.getString(2);  
for(int i=0;i<a.size();i++){

if(a.get(i).equals(cat)){
                 
     %>
             <option selected value="<%=q.get(i)%>"><%=a.get(i)%></option>     
                 <%  }
else{
%>
     <option  value="<%=q.get(i)%>"><%=a.get(i)%></option>                
             <%
        }}
              }
        %>
              
              
              
              
              
                    </select>    
              </td></tr>
     <tr> <th>Message</th><td><textarea  name="message" id="message" style="width: 300px; height: 100px;"> ${msgs.MESSAGE}</textarea></td>
         <th>NOK Message</th><td><textarea  name="nokmessage" id="nokmessage" style="width: 300px; height: 100px;"> ${msgs.NOKMESSAGE}</textarea></td></tr>
     <tr> <th>Ujumbe</th><td><textarea  name="ujumbe" id="ujumbe" style="width: 300px; height: 100px;"> ${msgs.UJUMBE}</textarea></td>
         <th>Ujumbe NOK</th><td><textarea  name="ujumbenok" id="ujumbenok" style="width: 300px; height: 100px;" > ${msgs.UJUMBENOK}</textarea></td></tr>
     <tr> <th>Kalenjin</th><td><textarea  name="kaleMsg" id="kaleMsg"   style="width: 300px; height: 100px;"> ${msgs.KALENJIN}</textarea></td>
         <th>Kalenjin NOK</th><td><textarea  name="kaleMsgNOK" id="kaleMsgNOK" style="width: 300px; height: 100px;"> ${msgs.KALENJINNOK}</textarea></td></tr>
         
       
        
         
            <td colspan="8"><input type="submit" value="Update" class="submit" style="height:30px;" name="submit" ></td>      

       
     </tr>
        
       
         </c:forEach>
      
	
        
                   
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


