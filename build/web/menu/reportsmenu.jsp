<%-- 
    Document   : reportsmenu
    Created on : Oct 7, 2014, 11:11:26 AM
    Author     : Manuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
     <ul id="menu">
	


 
       <li style="margin-left: 400px;"><a href="#">Reports</a>
        
            <ul style="width:300px;">
            
            <li style="text-align: left;"><a href="reports_detailedsummaries.jsp">ALL Registers Summary ( # per Site)</a></li>
            <li style="text-align: left;"><a href="reports_selectyear.jsp">ANC Visits Report ( #per village)</a></li>
            <li style="text-align: left;"><a href="reports_maternityreg.jsp">Maternity Report (# per village)</a></li>
            <li style="text-align: left;"><a href="report_completeregisters.jsp">Completion Report(All registers )</a></li>
            <li style="text-align: left;"><a href="RAWDATA1">Mobile Enrollments ( # per site)</a></li>
            <li style="text-align: left;"><a title="mothers who are enrolled via mobile and went ahead to attend health facilities" href="MOBILEREGISTER">Mobile and register linking (individual report)</a></li>
            <li style="text-align: left;"><a title="" href="MOBILEANDREGISTERSUMMARY">Mobile and registers linking (summary)</a></li>
            <li style="text-align: left;"><a title="" href="facilitiesmatandancsummary.jsp">ANC Visits and Delivery report (registers Summary)</a></li>
            
        </ul>
        </li>
        <li><a href="#">Data</a>
        <ul>
           
            <li><a href="MaternityRawData">Raw Data </a></li>
            
            
            
        </ul>
        </li>
        
        
        <li><a href="#">Help</a>
        
          <ul>
            <li><a href="clerkhelp.jsp">Inbuilt Help</a></li>
            <li><a href="ppt/userguide.pptx">user Guide</a></li>
            
            
        </ul>
        </li>
	<li><a href="index.jsp">Log in</a></li>
</ul>
    </body>
</html>
