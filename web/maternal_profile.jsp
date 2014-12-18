<%-- 
    Document   : index
    Created on : Aug 5, 2013, 9:03:18 PM
    Author     : SIXTYFOURBIT
--%>

<%@page import="sender.dbConn"%>
<%@page import="sendSMS.dbConnect"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MOTHER BABY BOOK</title>
         <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         <link rel="shortcut icon" href="images/icon.png"/>
         
         
         <%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if (session.getAttribute("userid")==null) {
        response.sendRedirect("index.jsp");
    } 
%>
         
         
    
         <!--clerk menu css-->
          <link rel="stylesheet" href="menu/css/clerkmenucss.css" type="text/css" media="all"/>

<!--    WIZARD CSS-->
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script> 
<script type="text/javascript" src="js/noty/themes/default.js"></script>


<link href="style/demo_style.css" rel="stylesheet" type="text/css"/>
<link href="style/smart_wizard_vertical.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="js/jquery.smartWizard.js"></script>
<script type="text/javascript">
    
    
//      $(document).ready(function(){
//    	// Smart Wizard	
//  		
//    
//		});
    
</script>

<!--===========================GET VALIDATor==========================--->
<script type="text/javascript" src="js/validateVerticalSteps.js"></script>
<script type="text/javascript" src="js/maternalprofilevalidations-em.js"></script>







<!--+++++++++++++++++++++++++++MY CUSTOM CALENDER+++++++++++++++++++++++++++++++++++++++--->
    <link rel="stylesheet" type="text/css" href="js/codebase/dhtmlxcalendar.css"/>
    <link rel="stylesheet" type="text/css" href="js/codebase/skins/dhtmlxcalendar_dhx_skyblue.css">
    <script src="js/codebase/dhtmlxcalendar.js"></script>
<!--==================================================================-->





<script type="text/javascript">

var myCalendar;
function doOnLoad() {
    myCalendar = new dhtmlXCalendarObject(["edd","lmp","dpr11","dpr12","dpr13","dpr14","dpr15","dpr16","dpr21","dpr22","dpr23","dpr24","dpr25","dp1","dp2","dp3","dp4","dp5","del_dp","del_dp1","_dpr11","_dpr12","_dpr13","_dpr14","_dpr15","_dpr21","_dpr22","_dpr23","_dpr24","_dpr25","dw_dp0","dw_dp1","dw_dp2","dw_dp3","dw_dp4","dw_dp5","dw_dp6","dw_dp7","dw_dp8","_dw_dp0","_dw_dp1","_dw_dp2","_dw_dp3","_dw_dp4","_dw_dp5","_dw_dp6","_dw_dp7","_dw_dp8","dnadp1","dnadp2","dnadp3","dna_dp1","dna_dp2","dna_dp3","vit_dp1","vit_dp2","vit_dp3","vit_dp4","vit_dp5","vit_dp6","vit_dp7","vit_dp8","vit_dp9","vit_dp10","vit_dp11"
    ,"_vit_dp1","_vit_dp2","_vit_dp3","_vit_dp4","_vit_dp5","_vit_dp6","_vit_dp7","_vit_dp8","_vit_dp9","_vit_dp10",             "NextVisit","NextVisit1","date1","date2","date3","date4","new_NextVisit1","new_NextVisit2","new_NextVisit3","new_NextVisit4","new_date1","new_date2","new_date3","new_date4","new_date5","new_date6","new_date7","new_date8","Referral_Pap","Referral1",
        "Referral2","Referral3","DateGiven1","DateGiven2","DateGiven3","DateGiven4","New_DateGiven0","New_DateGiven1","New_DateGiven2","New_DateGiven3","New_DateGiven4",
        "VisitBaby1","New_VisitBaby2","VisitMum1","VisitMum2","VisitMum3","New_VisitMum1","New_VisitMum2","New_VisitMum3",
        "CervixDate1","CervixDate2","CervixDate3","New_CervixDate1","New_CervixDate2","New_CervixDate3"]);
   
}

</script>



<!--tooltip and calender-->
 <link href="js/css/south-street/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>
<script src="js/js/jquery-ui-1.10.3.custom.js"></script>
<link rel="stylesheet" href="js/demos.css" />

         



<script type="text/javascript" src="js/motherbabyhelp.js"></script>
 
 
 
<!------dynamic rows javascript------>
<script type="text/javascript" src="js/dynamic_rows.js"></script>

<!---=====================GET PREV PREGNACY DBASE---->

<script type="text/javascript" src="js/getpreviouspregnancies.js"></script>

<!---==============PRESENT SERVICES==============================-->
<script type="text/javascript" src="js/presentPregnancy.js"></script>



<!--==========GET PREVENTIVE SERVICES=====================-->

<script type="text/javascript" src="js/getPreventiveServices.js"></script>

<!--------------GET Delivery Details>-->

<script type="text/javascript" src="js/getDeliveryDetails.js"></script>


<!----moageDeliveryOthers---->


<script type="text/javascript" src="js/manageDeliveryOthers.js"></script>



<!--=====================GETVITAMINDETAILS==========================-->
<script type="text/javascript" src="js/getvitaminADetails.js"></script>

<!--===========================TOGLE GIVEN STATUS====================-->

<script type="text/javascript" src="js/toggleGivenStatus.js"></script>



<!--=====================GETDEWORMINGDETAILS==========================-->
<script type="text/javascript" src="js/getDewormingDetails.js"></script>




<!--===========================GET PCR DNA DETAILS====================-->

<script type="text/javascript" src="js/getPCRDNADetails.js"></script>

<!--===========================MAUREENS JS====================-->

<script type="text/javascript" src="js/Maureensjs.js"></script>





<script type="text/javascript">
   
   
   function getMatDetails(){

        var ancno=document.getElementById("ancno").value;    
var motherID=document.getElementById("motherID").value; 
//+"motherID="+motherID

// window.open("districtchooser?county="+dist.value);     
var xmlhttp;    
if (ancno=="")
{
//filter the districts    



//document.getElementById("allfields").innerHTML="";
//return;
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
document.getElementById("allfields").innerHTML=xmlhttp.responseText;
doOnLoad();
}
}
xmlhttp.open("POST","getMatDetails?ancno="+ancno+"&motherID="+motherID,true);
xmlhttp.send();

 document.getElementById("allfields").innerHTML="loading..<img src=\"images/loading.gif\" alt=\"please wait\">"
 
 

//call the previous pregnancies method which fills the alredy existing values to the form
getPreviousPregData();
getCurrPregDets();
getPreventiveServices();
getIronFolate();
getDeliveryDetails();
getPostNatalMum();
getPostNatalBaby();
getCervicalTests();
getDewormingDetails();
getvitaminADetails();
getPCRDNADetails();
reseta();
resetPresentPreg();
//doOnLoad();
}//


</script>



<!-----------------------JNOTY MESSAGE---------------------------->    
   <%
                          //  if (session.getAttribute("message")!= null)  { %>
                                <script type="text/javascript"> 
                    
//                    var n = noty({text:'saved',
//                        layout: 'center',
//                        type: 'Success',
//                        timeout: 1800});
                    
                </script> <%
                
                //session.removeAttribute("message");
                           // }

                        %>  
    
 </head>    
    
    
    
    
    
    
    
    <!-- Body page -->
    
       
       
    <body >
        <embed src="sound/error.mp3" autostart="false" width="0" height="0" id="sound1"
               enablejavascript="true"/>
        <div id="container" style="height:670px ;width:1300px;">
            <br/>
          
            
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
            <div id="content" style="width:1250px;border-top-color:greenyellow ; margin-left: 25px;border-top-style: dotted;">
                <br/>
<!------------------------------------------------CONTENT DIV------------------------------------------------------------->                

<form action="saveMotherBaby" name="myform" method="POST" style="width:1220px ;padding-left:1px;">

<table align="center" border="0" cellpadding="0" cellspacing="0">
<tr><td> 
      
<!-- Tabs -->
  		<div id="wizard" class="swMain" style="width:1180px;">
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
                   PREVIOUS PREGNANCY <br />
                   <small>PAGE <font color="blue"> <b>5</b></font> of 40</small>
                </span>
            </a></li>
<!------------------------------------------------PRESENT PREGNANCY------------------------------------------------------------->  
  				<li><a href="#step-3">
                <label class="stepNumber">3</label>
                <span class="stepDesc">
                   PRESENT PREGNANCY<br />
                   <small>PAGE <font color="blue"> <b>8</b></font> of 40</small>
                </span>                   
             </a></li>
<!------------------------------------------------PREVENTIVE SERVICES-------------------------------------------------------------> 
  				<li><a href="#step-4">
                <label class="stepNumber">4</label>
                <span class="stepDesc">
                   PREVENTIVE SERVICES<br />
                   <small>PAGE <font color="blue"> <b>9</b></font>  of 40</small>
                </span>                   
            </a></li>
<!------------------------------------------------IRON FOLATE-------------------------------------------------------------> 
  				<li><a href="#step-5">
                <label class="stepNumber">5</label>
                <span class="stepDesc">
                   IRON AND FOLATE<br />
                   <small>PAGE <font color="blue"> <b>9</b></font>  of 40</small>
                </span>                   
            </a></li>
<!------------------------------------------------DELIVERY-------------------------------------------------------------> 
  				<li><a href="#step-6">
                <label class="stepNumber">6</label>
                <span class="stepDesc">
                   DELIVERY<br />
                   <small>PAGE <font color="blue"> <b>12</b></font>  of 40</small>
                </span>                   
            </a></li>
<!------------------------------------------------PREVENTIVE SERVICES-------------------------------------------------------------> 
  				<li><a href="#step-7">
                <label class="stepNumber">7</label>
                <span class="stepDesc">
                   POST NATAL EXAMINATION<br />
                   <small>PAGE <font color="blue"> <b>15</b></font>  of 40</small>
                </span>                   
            </a></li>
<!------------------------------------------------BABY-------------------------------------------------------------> 
  				<li><a href="#step-8">
                <label class="stepNumber">8</label>
                <span class="stepDesc">
                   BABY<br />
                   <small>PAGE <font color="blue"> <b>15</b></font>  of 40</small>
                </span>                   
            </a></li>
<!--______________________________________________________________________________________________________________-->
            
         
 <!------------------------------------------------Cervical Cancer Screening-------------------------------------------------------------> 
  				<li><a href="#step-9">
                <label class="stepNumber">9</label>
                <span class="stepDesc">
                   CANCER SCREENING<br />
                   <small>PAGE <font color="blue"> <b>16</b></font>  of 40</small>
                </span>                   
            </a></li>
<!--______________________________________________________________________________________________________________-->



   <!------------------------------------------------IMMUNIZATIONS-------------------------------------------------------------> 
  <!--	
   <li><a href="#step-9">
                <label class="stepNumber">9</label>
                <span class="stepDesc">
                   IMMUNIZATIONS<br />
                   <small>PAGE <font color="black"> <b>30</b></font>  of 40</small>
                </span>                   
            </a></li>-->
<!--______________________________________________________________________________________________________________-->



            <!------------------------------------------------Vitamin A Capsules-------------------------------------------------------------> 
  				<li><a href="#step-10">
                <label class="stepNumber">10</label>
                <span class="stepDesc">
                   VITAMIN A CAPSULE<br />
                   <small>PAGE <font color="blue"> <b>32</b></font>  of 40</small>
                </span>                   
            </a></li>
<!--______________________________________________________________________________________________________________-->
            
  

          <!------------------------------------------------Deworming-------------------------------------------------------------> 
  				<li><a href="#step-11">
                <label class="stepNumber">11</label>
                <span class="stepDesc">
                   DEWORMING <br />
                   <small>PAGE <font color="blue"> <b>32</b></font>  of 40</small>
                </span>                   
            </a></li>
<!--______________________________________________________________________________________________________________-->
 
            
          


<!-----------------------------------------------------PCR DNA-------------------------------------------------------------> 
  				<li><a href="#step-12">
                <label class="stepNumber">12</label>
                <span class="stepDesc">
                   PCR DNA<br />
                   <small>PAGE <font color="blue"> <b>33</b></font>  of 40</small>
                </span>                   
            </a></li>
<!--________________________________________________________________________________________________________________-->

            
            
  			</ul>
  			<div id="step-1">	
                            <h2 class="StepTitle">MATERNAL PROFILE         <a href="#" id="dialog-link1" >
                                    <img src="images/help_24.png"  /> </a>     <p style="font-size: 10px;">(Jan 2013 version) </p></h2>
            <ul type="disk">
  				   
            </ul>
            
                                          
                       
                       
       

 <% dbConn con=new dbConn();
 
 //get the helps from the database.
 
 String Sectionshelp[]=new String [12];
int mcount=0;
if(con.st.isClosed()){con=new dbConn();}

con.rs=con.st.executeQuery("Select * from help where help_id<='12'");
while(con.rs.next()){
    
Sectionshelp[mcount]=con.rs.getString(2);
if(mcount<=12){
mcount++;
}
}

%>              
                       
<!-- ui-dialog -->
<div id="dialog1" title="Maternal Profile Help ">
	<p>
         <% if(Sectionshelp[0]!=null){%>
         <%=Sectionshelp[0]%> 
         <%}%>
        </p>
</div>
                            
                <table align="center" cellpadding="4px" cellspacing="6px">
                <tr>
                    <!--
                    <td style="width:80px;">Name of Institution:<font color="red">*</font></td>
                    <td><input type="text"  name="institution" required="true"/>
                        
                        </td>-->
   

                       
                       
                      <td style="width:130px;"><b>ANC NO</b>:<font color="red">*</font></td><td><input type="text"  placeholder="enter anc no" onkeyup="" name="ancNo" id="ancno" required="true"/></td>
                <td>
                    
                    
                    
                     <% String facils[]={"","Solian","Ngubereti","Emining","Torongo","Timboroa","Simotwet"};%>
          
<td> Facility</td>
<td><select name="facility" id="facility">
        <option value=""></option> 
       
       <%
       
       
 String fac="";
       if(request.getParameter("facil")!=null) {
               fac=request.getParameter("facil");
                                               }
       
       if(fac.equalsIgnoreCase("Ngubureti")){fac="Ngubereti";}
       if(fac.equalsIgnoreCase("Emmining")){fac="Emmining";}
       
       for(int b=0;b<facils.length;b++){
           if(facils[b].equalsIgnoreCase(fac))
           {            out.println("<option selected value=\""+facils[b]+"\">"+facils[b]+"</option>" );}
                     else{
                     out.println("<option value=\""+facils[b]+"\">"+facils[b]+"</option>" );}
                     }
            

%>
        
    </select></td>
                    
                    
                    
                    
                    <p id="msg_ancno"> </p><input type="hidden" name="motherID" id="motherID" value=""></td>
<!--           <td> <input type="submit" value="update" class="submit"/></td>-->
                

<%


       
 String momid="";
       if(request.getParameter("motherID")!=null) {
               momid=request.getParameter("motherID");
               %>
            <script>
    
    
    document.getElementById("motherID").value="<%=momid%>";
    
</script>   
               
                 <%                              }

%>
       


                </tr>      
            </table>
            
            
            <table id="allfields" cellpadding="5px" cellspacing="10px" align="center"></table> 
                
            </div>
            
            
            
            
          
                                  <%
 if(request.getParameter("ancnumber")!=null){
 String ancno=request.getParameter("ancnumber").toString();
 %>
 <script type="text/javascript">
   document.getElementById("ancno").value="<%=ancno%>"; 
   getMatDetails();
       </script>
         <%
}
%>
        
   <script>
       
       getMatDetails();
       
   </script>            
                    
             
                
        <!-------------------------------2 PREVIOUS PREGNANCY DIV----------------------------------------->        
               			
        
  	<div id="step-2">
            <h2 class="StepTitle">Previous Pregnancy  <a href="#" id="dialog-link2" ><img src="images/help_24.png"  /> </a> </h2>
            
            
            <!-- ui-dialog -->
<div id="dialog2" title="Previous Pregnancy help">
	<p>
         <% if(Sectionshelp[1]!=null){%>
         <%=Sectionshelp[1]%> 
         <%}%>
        </p>
</div>
            
            
            <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick=" addRow('table_prev_preg');"/> 
           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deleteRow('table_prev_preg');"/> 
          
<!--            <input type="text"  value="check validator" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="return validate_prev_preg('1');"/> 
          
           -->
           <!---------The already filled table values---------------------->
           
            <table border="1px" class="viewpdt"     style="width: 600px; padding-bottom: 0px;">
             <!--      Number        -->   
               </table>
          <!--====================THE EXISTING PREVIOUS PREG TABLE WHICH WILL BE FILLED WITH VALUES FROM DBASE ON ENTERING MOTHERS ANC NO--> 
           <table border="0px" class="viewpdt" align="center" id="existing_prev_preg" style="padding-top: 0px;width: 600px;">
              
              </table>
          
                <table border="0px"  class="viewpdt" align="center"   id="table_prev_preg" style="margin-left:120px;width: 700px;">
             <h3 id="blank_warning"> <font color="red">Search a Mother By their ANC NO. first.</font></h3>
                   
              </table>
              
                   
              
        
           
            <table  class="viewpdt" style="width: 600px;"></table>
        </div>
        
        
        <!---------------------------------------END OF PREVIOUS PREGNANCY------------------------------------------------->
  	
       
        <div id="step-3">
       <h2 class="StepTitle">Present Pregnancy  <a href="#" id="dialog-link3" ><img src="images/help_24.png"  /> </a>  </h2>
         
               <!-- ui-dialog -->
<div id="dialog3" title="Previous Pregnancy help">
	<p>
         <% if(Sectionshelp[2]!=null){%>
         <%=Sectionshelp[2]%> 
         <%}%>
        </p>
</div>
       
       <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick=" addPregnancyRow();"/> 
           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deletePregnancyRow()"/> 
<!--          <input type="submit" name="submitPresentPreg" value="submit">-->
    
           <table id="currPreg" class="viewpdt" style="margin-left: 120px;margin-bottom: 0px;width:700px;"></table>
           <table id="dataTable" class="viewpdt" style="margin-left: 120px; margin-top: 0px;"> </table>
<!--         </form> -->
      
        </div>
       <!--------------------------------------------end of previous pregnancy--------------------------------------------------------> 
        
       
       <!--+++++++++++++++++++++++++++++++++++++++PREVENT  SERVICES--------------------------------------------------->       
         <div id="step-4">
         <h2 class="StepTitle">Preventive services   <a href="#" id="dialog-link4" ><img src="images/help_24.png"  /> </a><h3></h3></h2>	
                     <!-- ui-dialog -->
<div id="dialog4" title="Preventive Services help">
	<p>
         <% if(Sectionshelp[3]!=null){%>
         <%=Sectionshelp[3]%> 
         <%}%>
        </p>
</div> 
         <table border="0px" align="center" class="viewpdt" id="table_prev_pregnancies" style="margin-left: 120px;width: 700px;">
             <!--      Number        -->  
             
               <tr><th style="width:12px;">Preventive Services</th> 
               <th style="width:62px;">DATE</th>
               <th style="width:62px;">Next Visit</th></tr>
              
               
               
                   </table>
            
            </div> 
         <!-----------------------------------------end of prevent services------------------->
      <!---========================================IRON FOLATE===============================================---->         
               <div id="step-5">
           <h2 class="StepTitle">Iron and Folate  <a href="#" id="dialog-link5" ><img src="images/help_24.png"  /> </a></h2>
<!--          <input type="submit" name="submitIron" value="submit">-->
  
        <!-- ui-dialog -->
<div id="dialog5" title="Iron and Folate help">
	<p>
         <% if(Sectionshelp[4]!=null){%>
         <%=Sectionshelp[4]%> 
         <%}%>
        </p>
</div>

                    <table id="IronFolate" class="viewpdt" style="width:700px; margin-left: 120px;"></table>
                    <table id="ferrousTable" class="viewpdt" style="width:700px ;margin-left: 120px;"></table>

<!-- </form>           -->
             
             
             </div>
      
      
      
      <!---========================================DELIVERY PLACE===============================================---->         
               <div id="step-6">
            <h2 class="StepTitle">Delivery   <a href="#" id="dialog-link6" ><img src="images/help_24.png"  /> </a><h3></h3></h2>	
                    <!-- ui-dialog -->
<div id="dialog6" title="Delivery help">
	<p>
         <% if(Sectionshelp[5]!=null){%>
         <%=Sectionshelp[5]%> 
         <%}%>
        </p>
</div>
            
             <table id="table_alldeliveryfields" cellpadding="4px" cellspacing="3px" style="margin-left: 120px; width:700px;"></table> 
             
             
             
             
             </div>
            
            
            
              
               
    <!---=====================================================================================---->                  
               <div id="step-7">
            <h2 class="StepTitle"> POST NATAL EXAMINATION - MOTHER  <a href="#" id="dialog-link7" ><img src="images/help_24.png"  /> </a></h2>	
        <!-- ui-dialog -->
<div id="dialog7" title="Post natal help">
	<p>
         <% if(Sectionshelp[6]!=null){%>
         <%=Sectionshelp[6]%> 
         <%}%>
        </p>
</div>

            <table id="PostNatalMum" class="viewpdt" style="margin-left: 120px;width:700px;"></table>
             <table id="PostNatalMumTable" class="viewpdt"></table>
        
                       			
        </div>
               
           
               <div id="step-8">
            <h2 class="StepTitle">POST NATAL EXAMINATION - BABY  <a href="#" id="dialog-link8" ><img src="images/help_24.png"  /> </a></h2>	
      	
                    <!-- ui-dialog -->
<div id="dialog8" title="Post natal  help">
	<p>
         <% if(Sectionshelp[7]!=null){%>
         <%=Sectionshelp[7]%> 
         <%}%>
        </p>
</div>
            
            <table class="viewpdt">
<!--               <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick=" addPostNatalBabyRow();"/> 
           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deletePostNatalBabyRow()"/> 
-->

            </table>
            <table id="PostNatalBaby" class="viewpdt" style="margin-left: 120px; width:700px;"></table>  
                    <table id="PostNatalBabyTable"class="viewpdt" ></table>
                        			
        </div>
               
        
    
      
               <div id="step-9">
            <h2 class="StepTitle">CERVICAL CANCER SCREENING <a href="#" id="dialog-link9" ><img src="images/help_24.png"  /> </a></h2>	
      
            
            
        <!-- ui-dialog -->
<div id="dialog9" title="Cervical Cancer help">
	<p>
         <% if(Sectionshelp[8]!=null){%>
         <%=Sectionshelp[8]%> 
         <%}%>
        </p>
</div>
           <table id="CervicalTest" class="viewpdt" style="width:600px;" ></table>
         
               
                 
               <table id="CervicalCancerTable" class="viewpdt" style="width: 600px;"></table>
                    
                                			
        </div>
    <!---
     <div id="step-9">
            <h2 class="StepTitle">IMMUNIZATIONS</h2>	
           
                
            
            
            
            <!---
            
             <table border="0px" class="viewpdt" id="table_missles" style="width: 650px;">
             <!--      Number        -->  
             <!--
               <tr><th style="width:12px;">Measles Vacine at 6 Months</th> 
               <th style="width:62px;">Date given</th>
               </tr>
               
               
                   </table> 
            
        </div> -->

    
    
      <div id="step-10">
            <h2 class="StepTitle">VITAMIN A CAPSULES FROM 6 MONTHS <a href="#" id="dialog-link10" ><img src="images/help_24.png"  /> </a>   </h2>	
                   <!-- ui-dialog -->
<div id="dialog10" title="Vitamin A help">
	<p>
         <% if(Sectionshelp[9]!=null){%>
         <%=Sectionshelp[9]%> 
         <%}%>
        </p>
</div>
             
           <table border="0px" class="viewpdt" id="table_vitamin" style="margin-left: 120px;width: 700px; ">
             <!--      Number        -->  
             
               <tr><th style="width:12px;">Dose</th> 
               <th style="width:62px;">Age</th>
               <th style="width:62px;">Tick Age Given</th>
              <th style="width:62px;">Date of Next Visit</th></tr>
               
               
                   </table> 
           
        </div>
              
    
    
      <div id="step-11">
            <h2 class="StepTitle">DEWORMING FROM 1 YEAR <a href="#" id="dialog-link11" ><img src="images/help_24.png"  /> </a> </h2>	
                   <!-- ui-dialog -->
<div id="dialog11" title="Deworming Help">
	<p>
         <% if(Sectionshelp[10]!=null){%>
         <%=Sectionshelp[10]%> 
         <%}%>
        </p>
</div>
            
            
            <table border="0px" class="viewpdt" id="table_deworming" style="margin-left: 120px;width: 700px; ">
             <!--      Number        -->  
             
               <tr><th style="width:12px;">Age</th> 
               <th style="width:62px;">Drug</th>
               <th style="width:62px;">Dosage</th>
              <th style="width:62px;">Date of Next Visit</th></tr>
               
               
                   </table> 
                       			
        </div>
     
    
   <!----------------------------------DNA PCR DIV---------------------------------------> 
      <div id="step-12">
            <h2 class="StepTitle">HIV EXPOSURE TEST <a href="#" id="dialog-link12" ><img src="images/help_24.png"  /> </a></h2>	
           
                    <!-- ui-dialog -->
<div id="dialog12" title="HEI help">
	<p>
         <% if(Sectionshelp[11]!=null){%>
         <%=Sectionshelp[11]%> 
         <%}%>
        </p>
</div>
            
            <table border="0px" class="viewpdt" id="table_dna_pcr" style="margin-left: 120px;width: 700px; ">
             <!--      Number        -->  
             
               <tr><th style="width:12px;">Type of Test</th> 
               <th style="width:62px;">Date of Sample</th>
               <th style="width:62px;">Results</th>
           </tr>
               
               
                   </table> 

            
        </div>
    
    
  		</div>
  		
<!-- End SmartWizard Content -->  		
  		
</td></tr>
</table> 

<!------------------------------------------------------------------------------------------------------------------------>               
          
                </form>  <!--last form-->
            </div>

           

            <div id="footer">
             
            </div>
        </div>
    </body>
    
    
</html>
