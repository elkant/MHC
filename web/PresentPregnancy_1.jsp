<%-- 
    Document   : index
    Created on : Aug 5, 2013, 9:03:18 PM
    Author     : SIXTYFOURBIT
--%>

<%@page import="sender.dbConn"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MNHC SYSTEM</title>
   <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         <link rel="shortcut icon" href="images/icon.png"/>


<!-- WIZARD STYLING N JS-->
<link href="style/demo_style.css" rel="stylesheet" type="text/css" >
<link href="style/smart_wizard_vertical.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="js/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="js/jquery.smartWizard.js"></script>
<!--  <script src="js/jquery-1.7.2.js"></script>-->
<!--  <script src="js/jquery-1.9.1.js"></script>-->


<script >
   
    $(document).ready(function(){
    	// Smart Wizard	
  $('#wizard').smartWizard({transitionEffect:'slide'});
    });

  
</script>
<!--js n css for datepicker -->

         <link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>

	<script src="js/jquery-ui-1.10.3.custom.js"></script>

 <script type="text/javascript">
     $(document).ready(function(){
     $( ".datepicker").datepicker({
                                dateFormat: "dd/mm/yy",
                                changeMonth: true,
                                changeYear: true
                               
                           }); 
                           });
                        </script>
  <script type="text/javascript">
 $(document).tooltip();
</script>
<!--js for jnoty-->
    <script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>

<!-- You can add more layouts if you want -->
<script type="text/javascript" src="js/noty/themes/default.js"></script>
<!--fetching the js for adding different table rows that are dynamic-->
<script type="text/javascript" src="js/ferrousJS.js"></script>
<script type="text/javascript" src="js/presentPregnancy.js"></script>
       
</head>
    
    <!-- Body page -->
    
       
       
    <body>
        <div id="container">

            <div id="header">
                
            </div>

           

            <div id="content">
<!------------------------------------------------CONTENT DIV------------------------------------------------------------->                



<table align="center" border="0" cellpadding="0" cellspacing="0">
<tr><td> 
      <form name="frm" id="frm">
<!-- Tabs -->
  		<div id="wizard" class="swMain">
  			<ul>
<!------------------------------------------------MATERNAL PROFILE------------------------------------------------------------->                

  				<li><a href="#step-1">
                <label class="stepNumber">1</label>
                <span class="stepDesc">
                   MATERNAL PROFILE<br />
                   <small>PAGE 4 of 40</small>
                </span>
            </a></li>
 <!------------------------------------------------MEDICAL N SURGICAL HISTORY------------------------------------------------------------->  
  				<li><a href="#step-2">
                <label class="stepNumber">2</label>
                <span class="stepDesc">
                   MEDICAL & SURGICAL HISTORY <br />
                   <small>PAGE 5 of 40</small>
                </span>
            </a></li>
<!------------------------------------------------PRESENT PREGNANCY------------------------------------------------------------->  
  				<li><a href="#step-3">
                <label class="stepNumber">3</label>
                <span class="stepDesc">
                   PRESENT PREGNANCY<br />
                   <small>PAGE 8 of 40</small>
                </span>                   
             </a></li>
<!------------------------------------------------PREVENTIVE SERVICES-------------------------------------------------------------> 
  				<li><a href="#step-4">
                <label class="stepNumber">4</label>
                <span class="stepDesc">
                   PREVENTIVE SERVICES<br />
                   <small>PAGE 9 of 40</small>
                </span>                   
            </a></li>
<!------------------------------------------------DELIVERY-------------------------------------------------------------> 
  				<li><a href="#step-5">
                <label class="stepNumber">5</label>
                <span class="stepDesc">
                   DELIVERY<br />
                   <small>PAGE 12 of 40</small>
                </span>                   
            </a></li>
<!------------------------------------------------PREVENTIVE SERVICES-------------------------------------------------------------> 
  				<li><a href="#step-6">
                <label class="stepNumber">6</label>
                <span class="stepDesc">
                   POST NATAL EXAMINATION<br />
                   <small>PAGE 15 of 40</small>
                </span>                   
            </a></li>
<!------------------------------------------------BABY-------------------------------------------------------------> 
  				<li><a href="#step-7">
                <label class="stepNumber">7</label>
                <span class="stepDesc">
                   BABY<br />
                   <small>PAGE 15 of 40</small>
                </span>                   
            </a></li>
  			</ul>
                    
 <div id="step-1">	
            <h2 class="StepTitle">Present Pregnancy</h2>
            <table>
                <tr>
                    <td></td>
                    <td>No of Visit</td>
                    <td>Date</td>
                    <td>Weight</td>
                    <td>Next Visit</td>
                    
                    
                </tr>
                
                
                
            </table>
                			
        </div>
  			<div id="step-2">
            <h2 class="StepTitle">Step 2 Content</h2>	
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, 
            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, 
            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
            Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>
            <p>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, 
            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, 
            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
            Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>
            <p>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, 
            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, 
            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
            Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p> 
            <p>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, 
            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, 
            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
            Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>
            <p>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, 
            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, 
            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
            Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>            
        </div>                      
  			<div id="step-3">
 <!------------------------------------------------Present Pregnancy-------------------------------------------------------------> 
           <h2 class="StepTitle">Present Pregnancy</h2>
           <table id="dataTable">
               
                <tr style="text-align: center;">
                       
                    <th style="width: 20px;"></th>
                    <th style="width: 80px;"> No of Visit </th>
                    <th style="width: 120px;"> Date </th>
                    <th style="width: 80px;">Weight</th>
                    <th style="width: 120px;">Next Visit</th>
                    
                    
                </tr>     
                <tr>
                     
                    <td><input type="checkbox" name="chk" title="select to delete a row"style="width: 20px;"/></td>
                    <td><input type="text" name="" value="" id="NoVisits_0" style="width: 80px;" title="No of Visits" id="NoVisits" ></td>
                    <td><input type="text" name="" value="" id="Date_0" id="dates" title="Pick a Date" class="datepicker"  style="width:120px;"></td>
                    <td><input type="text" name="" value="" id="Weight_0" title="weight" style="width: 80px;"></td>
                    <td><input type="text" name="" value="" id="NextVisits_0" title="next visit"  class="datepicker" style="width: 120px;" onchange="counts()"></td>
                    
                    
                </tr> 
                
<!--                  <INPUT type="button" value="Add Row" onClick="addRow('dataTable')" />-->
 
                  <INPUT type="button" value="Delete Row" onClick="deleteRow()" />
                  
                   <label>
                       <input name="h" type="hidden" id="h" value="0" />
                  </label>
            
            </table>
                        </div>
  			<div id="step-4">
            <h2 class="StepTitle">Step 4 Content</h2>	
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, 
            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, 
            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
            Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>
            <p>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, 
            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, 
            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
            Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>
            <p>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, 
            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, 
            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
            Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>                			
        </div>
  			<div id="step-5">
 <!------------------------------------------------Present Pregnancy------------------------------------------------------------->
   <h2 class="StepTitle">Iron Folate</h2>
            <table id="ferrousTable">
               
                <tr style="text-align: center;">
                       
                    <th style="width: 20px;"></th>
                    <th style="width: 200px;">Iron and Folate </th>
                    <th style="width: 120px;"> Visit No </th>
                    <th style="width: 80px;">Tablets</th>
                    <th style="width: 120px;">Date Given</th>
                    
                    
                </tr>     
                <tr>
                     
                    <td><input type="checkbox" name="chk" style="width: 20px;"/></td>
                    <td rowspan="20"><label >Combined Tablet-60mg iron and 400 ug folic acid)<br/> or any other available</label></td>
                    <td><label >1st Visit</label></td>
                    <td><label >30 Tablets</label></td>
                    <td><input type="text" name="dateGiven_0" value="" id="dateGiven_0" title="Date Given"  class="datepicker" style="width: 120px;" onchange="counter();"></td>
                    
                    
                </tr> 
                
<!--                  <INPUT type="button" value="Add Row" onClick="addRow('dataTable')" />-->
 
                  <INPUT type="button" value="Delete Row" onClick="deleteRow()" />
                  
                   <label>
                       <input name="f" type="hidden" id="f" value="0" />
                  </label>
            
            </table>           			
        </div>
  			<div id="step-6">
            <h2 class="StepTitle">Step 4 Content</h2>	
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, 
            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, 
            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
            Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>
            <p>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, 
            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, 
            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
            Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>
            <p>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, 
            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, 
            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
            Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>                			
        </div>
  			<div id="step-7">
            <h2 class="StepTitle">Step 4 Content</h2>	
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, 
            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, 
            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
            Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>
            <p>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, 
            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, 
            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
            Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>
            <p>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit, 
            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, 
            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
            Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>                			
        </div>
                        
  		</div>

  		
<!-- End SmartWizard Content -->  		
  	</form>	
</td></tr>
</table> 

<!------------------------------------------------------------------------------------------------------------------------>               
            </div>

           

            <div id="footer">
             
            </div>
        </div>
    </body>
    
    
</html>
