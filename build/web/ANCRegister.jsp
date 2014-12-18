
<%@page import="java.util.ArrayList"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="sendSMS.dbConnect"%>
<!DOCTYPE HTML >
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> ANC REGISTER</title>

<%!
HttpSession session;
 String ancno="";
 
 
 
 dbConnect conn= new dbConnect();
%>
      <%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
      session = request.getSession();
   
    
    if (session.getAttribute("userid")==null) {
        response.sendRedirect("index.jsp");
    } 
%>


<link href="style/demo_style.css" rel="stylesheet" type="text/css"/>
<link href="style/smart_wizard.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="js/jquery.smartWizard.js"></script>
<script type="text/javascript" src="js/ANCRegister.js"></script>
<link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>

<!--clerk menu-->

<link rel="stylesheet" type="text/css" href="menu/css/clerkmenucss.css"/>
         <link rel="shortcut icon" href="images/icon.png"/> 
         
  
<!--tooltip and jquery calender-->



<!--======================MY CUSTOM CALENDER============================================-->

    <link rel="stylesheet" type="text/css" href="js/codebase/dhtmlxcalendar.css"/>
    <link rel="stylesheet" type="text/css" href="js/codebase/skins/dhtmlxcalendar_dhx_skyblue.css">
    <script src="js/codebase/dhtmlxcalendar.js"></script>
    
    <!--calendar based on field ids-->
         <script type="text/javascript">

var newCalendar;
function doOnLoader() {
    //alert("a");
   
    
    var s=1;
    var all="";
    for(s=1;s < 100;s++){
  all+="\"VisitDate"+s+"\",";
  all+="\"New_VisitDate"+s+"\",";
  all+="\"LMP"+s+"\",";
  all+="\"EDD"+s+"\",";
  all+="\"New_LMP"+s+"\",";
  all+="\"New_EDD"+s+"\",";
  all+="\"ARTStart"+s+"\",";
  
  all+="\"New_ARTStart"+s+"\"";
  if(s<99){all+=",";}
    }
    //alert(JSON.parse("["+all+"]"));
        newCalendar = new dhtmlXCalendarObject(JSON.parse("["+all+"]"));
   
}




function loadvillages(rowid){
    
     

// window.open("districtchooser?county="+dist.value);  



var xmlhttp;    
if (rowid=="")
{
//filter the districts    




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
if (xmlhttp.readyState==4&&xmlhttp.status==200)
{
document.getElementById("New_Village"+rowid).innerHTML=xmlhttp.responseText;


 


}
}
xmlhttp.open("POST","loadvillages",true);
xmlhttp.send();


  
    
}

    
</script>
    
    
    
<!--==================================================================-->




<link href="js/css/south-street/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>
<script src="js/js/jquery-ui-1.10.3.custom.js"></script>
<link rel="stylesheet" href="js/demos.css" />





         
         <!--======EXTRNAL JAVASCRIPTS=====-->
         
          <!--======EXTRNAL JAVASCRIPTS=====-->
          
          <!--======HELP MESSAGES JS========-->
          
          <script type="text/javascript" src="js/anchelp.js"> </script>
          
 <!--=======STEP2DETAILS=====-->
<!--<script type="text/javascript" src="js/ItoM.js"></script>-->
 <!--=======STEP4DETAILS=====-->
<script type="text/javascript" src="js/ALtoAN.js"></script>
 <!--=======STEP3DETAILS=====-->
<script type="text/javascript" src="js/QtoWRegister.js"></script>

<!--======== DISABLE TABLES IF MOTHER searched by ANC DOES NOT EXIST in the System===== -->
<script type="text/javascript" src="js/disableMyTables.js"></script>  
<script type="text/javascript" src="js/validateSteps.js"></script>

         
         <!--====STEP5DETAILS=====-->
         <script type="text/javascript" src="js/getStep5details_anc.js"></script>
         
         <!--=======STEP6DETAILS=====-->
         <script type="text/javascript" src="js/getStep6details.js"></script>
         
         <!--=======OTHERS=====-->
         <script type="text/javascript" src="js/otherotherconditions.js"></script>
   
         
           <!--=======ADD STEP 5 ROWS =====-->
           <script type="text/javascript" src="js/addstep5rows.js"></script>
           
           
           
           <!--=======ADD STEP 6 ROWS =====-->
           <script type="text/javascript" src="js/getALtoANDetails.js"></script>
           <script type="text/javascript" src="js/ALtoAN.js"></script>
           <script type="text/javascript" src="js/addStep6rows_anc.js"></script>
           <script type="text/javascript" src="js/addstep7rows_anc.js"></script>
         
         

<script type="text/javascript">
    $(document).ready(function(){
    	// Smart Wizard 	
  		$('#wizard').smartWizard();
      
      function onFinishCallback(){
        $('#wizard').smartWizard('showMessage','Finish Clicked');
        //alert('Finish Clicked');
      }     
		});
</script>
           
          
<script type="text/javascript">
    //function refresh(){
//       
//         window.location.reload();
//
//   
//         }


 var b=1;  //hplds the no of existing rows for the table to add new values
        var verifiers=1;
        var verifiers1=1;
       //verifier is a field that determines whether to fetch the value of i from the edit text.
       var jay=0;  
   var verifier2=1;
        var verifiers2=1;    


function resetAtoH(){
b=1;
verifiers=1;
verifiers1=1;
}

  function resetItoM(){
           //alert(e);
       
e=1;
verifier2=1;
verifiers2=1;

}
   
  function getANCMatDetails(){
     
//alert("a");
        var ancno1=document.getElementById("ancno").value;    
        var motherID=document.getElementById("motherID").value;    

// window.open("districtchooser?county="+dist.value);  



var xmlhttp;    
if (ancno1=="")
{
//filter the districts    



//document.getElementById("initialDets").innerHTML="";
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
if (xmlhttp.readyState==4&& xmlhttp.status==200)
{
document.getElementById("initialDets").innerHTML=xmlhttp.responseText;


 

doOnLoad(); 
doOnLoader();
}
}
xmlhttp.open("POST","ANCRegister1?ancno="+ancno1+"&motherID="+motherID,true);
xmlhttp.send();



//call step 5 ajax
getItoMDetails();
getQtoWDetails();
getStep5details();
getStep6details();
getALtoANDetails();
resetAtoH();
resetItoM();
resetQtoW();
reseta5();
//
    

//



}//end of filter districts

//********************************************
   
  //*******************ajax for i to m*************************
     
   function getItoMDetails(){
      
       var ancno1=document.getElementById("ancno").value;    
        var motherID=document.getElementById("motherID").value;    

// window.open("districtchooser?county="+dist.value);  



var xmlhttp;    
if (ancno1=="")
{
//filter the districts    




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
if (xmlhttp.readyState==4&&xmlhttp.status==200)
{
document.getElementById("ItoMDets").innerHTML=xmlhttp.responseText;
doOnLoader();

 


}
}
xmlhttp.open("POST","ItoMRegister?ancno="+ancno1+"&motherID="+motherID,true);
xmlhttp.send();


}//end of send anc no  
   
   //*******************ajax for Q to W*************************
   function getQtoWDetails(){
  var ancnos=document.getElementById("ancno").value;    
 var motherID=document.getElementById("motherID").value; 
 //alert("AAA"+motherID);
// window.open("districtchooser?county="+dist.value);     
var xmlhttp;    
if (ancnos=="")
{
//filter the districts    




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


document.getElementById("QtoWDets").innerHTML=xmlhttp.responseText;


}
}
xmlhttp.open("POST","QtoWRegister?ancno="+ancnos+"&motherID="+motherID,true);
xmlhttp.send();


}//end of send anc no  
    
   
   
   
   
   
   
   
   
   
    $(document).ready(function(){
    	// Smart Wizard	
  		$('#wizard').smartWizard({transitionEffect:'slide'});
     
		});
</script>


<script type="text/javascript">
    function change() {

   
   var a = document.getElementById("treatment");
   var v =a.options[a.selectedIndex].value;



if((v="6=Others(Specify")) {
   
       if(document.getElementById("others1").type="hidden"){
           
       document.getElementById("others1").type="text";    
       }}
else if(!(v="6=Others(Specify")) { 
   
       if(document.getElementById("others1").type="text"){
           
       document.getElementById("others1").type="hidden";    
       }
//       if(document.getElementById("others1").type="text"){
//           
//       document.getElementById("others1").type="hidden";    
//       }

}
}
</script>
<script>
 
var myCalendar;
function doOnLoad() {

  
        
         var sa=1;
    var all1="";
    for(sa=1;sa < 100;sa++){
  all1+="\"New_VisitDate"+sa+"\",";
  all1+="\"dpr1"+sa+"\",";
  
  all1+="\"dpr2"+sa+"\"";
  if(sa<99){all1+=",";}
                             }
    
    //alert(JSON.parse("["+all+"]"));
        myCalendar = new dhtmlXCalendarObject(JSON.parse("["+all1+"]"));
        
    
}



var myCalendars;
function doOnLoadItoP() {

    
     
    var sa=1;
    var all="";
    for(sa=1;sa < 100;sa++){
  all+="\"New_LMP"+sa+"\",";
  
  all+="\"New_EDD"+sa+"\"";
  if(sa<99){all+=",";}
                             }
    
    //alert(JSON.parse("["+all+"]"));
        myCalendars = new dhtmlXCalendarObject(JSON.parse("["+all+"]"));
    
    
   
}

 
 


          //var existing_no_of_rows= document.getElementById("no_of_rows").value+1;
        
       
      
function addANCRegister1() {
 
                              <%
String ancno="";
String motherID="";
 if(request.getParameter("ancnumber")!=null){
   
 ancno=request.getParameter("ancnumber").toString();
 
}
else{ancno="";}

 if(request.getParameter("motherID")!=null){
   
 motherID=request.getParameter("motherID").toString();
 
}
else{motherID="";}
%>
//add villages   
 
  
 var villa="";
          
         
      if(verifiers===1){
                jay =parseInt(document.getElementById("ANCRegister1_old_rows").value)+(parseInt(2));
                b=parseInt(document.getElementById("ANCRegister_newRows").value);
                 // alert("called");
                          }
            
             b++;
             
             
//             alert("j  "+j);
//             alert("b  "+b);
          verifiers++;
          verifiers1++;  
            
 
    var table = document.getElementById("initialDets");
  //get the number of rows
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
             
 //create element that is a checkbox assign type, name,style then apppend the child element to the cell
            var cell1 = row.insertCell(0);
            cell1.style.width = "40px";
            
            var element0 = document.createElement("label"); 
            element0.type = "label";
            //element1.name="chkbox[]";
            element0.innerHTML=""+(parseInt(jay));
            element0.style.width = "40px";
//            element0.style.textAlign="center";
            cell1.appendChild(element0);
   
    var cell2 = row.insertCell(1);
     cell2.style.width = "80px";
    var element1 = document.createElement("input");//create an input field of type text and assign names, ids and functions
    element1.type = "text";
    element1.value = "";
    element1.name = "New_VisitDate"+b;
    element1.id = "New_VisitDate"+b;
    element1.placeholder = "yyyy-mm-dd";
    element1.readOnly = true;
    
    element1.style.width = "80px";
//    element1.style.width = "200px";
    cell2.appendChild(element1);
    
   
    
    var cell3 = row.insertCell(2);
    cell3.style.width = "80px";
    var element2 = document.createElement("input");//create an input field of type text and assign names, ids and functions
    element2.type = "text";
    element2.value = "";
    element2.name = "New_ANCNo"+b;
    element2.id = "New_ANCNo"+b;
    element2.style.width = "80px";
    
    <%

  

 String query = "Select * from mother_details where motherID='"+motherID+"'";
 //System.out.println("query-------fff--------"+query);
 if(conn.state1.isClosed()){conn= new dbConnect();}
 conn.rs = conn.state1.executeQuery(query);
 
                                  if(conn.rs.next()==true)
                                         {
                                                          
                                                   %>
                                                          
     element2.value ="<%= conn.rs.getString(2)%>";
   
    
    <%
   // System.out.println(conn.rs.getString(1));
                                  }
 else{
    %>
         
     element2.value ="";

    <%}%>
    cell3.appendChild(element2);

            
    var cell4 = row.insertCell(3);//create an input field of type text,class name, width and assign names, ids and functions
    cell4.style.width = "80px";
    var element3 = document.createElement("select");
    element3.type = "text";
    element3.name = "New_FirstVisit"+b;
    element3.id = "New_FirstVisit"+b;
    element3.value = "";
    element3.style.width = "80px";
    var option;
    var option1;
    var option2;
      option = document.createElement("option");
                      option.type="text";
                      option.value="";
                      option.innerHTML = "";
    element3.appendChild(option);
      option1 = document.createElement("option");
                      option1.type="text";
                      option1.value="Yes";
                      option1.innerHTML = "Yes";
    element3.appendChild(option1);
      option2 = document.createElement("option");
                      option2.type="text";
                      option2.value="No";
                      option2.innerHTML = "No";
    element3.appendChild(option2);
    cell4.appendChild(element3);
    
    var cell5 = row.insertCell(4);//create an input field of type text,class name, width and assign names, ids and functions
    cell5.style.width = "80px";
    var element4 = document.createElement("input");
    element4.type = "text";
    element4.name = "New_No_Visits"+b;
    element4.id = "New_No_Visits"+b;
    element4.value ="";
    element4.style.width = "80px";
    cell5.appendChild(element4);
           
    
    var cell6= row.insertCell(5);//create an input field of type text,class name, width and assign names, ids and functions
    cell6.style.width = "80px";
    var element5 = document.createElement("input");
    element5.type = "text";
    element5.name = "New_FirstName"+b;
    element5.id ="New_FirstName"+b;
    element5.style.width = "80px";
    <% 
 String querys1= "Select * from mother_details where motherID='"+motherID+"'";
 conn.rs = conn.state1.executeQuery(querys1);
 //System.out.println("query---------------"+querys1);
                                  if(conn.rs.next()==true)
                                         {
                                                          
                                                   %>
                                                          
     element5.value ="<%= conn.rs.getString("FName")%>";
    
    <%
   // System.out.println(conn.rs.getString("FName"));
                                  }
 else{%>
  element5.value ="";
  <%}  %>
  
    cell6.appendChild(element5);
    
    var cell7= row.insertCell(6);//create an input field of type text,class name, width and assign names, ids and functions
    cell7.style.width = "80px";
    var element6 = document.createElement("input");
    element6.type = "text";
    element6.name = "New_SecondName"+b;
    element6.id ="New_SecondName"+b;
    element6.style.width = "80px";
   <% 
 String querys2= "Select * from mother_details where motherID='"+motherID+"'";
 conn.rs = conn.state1.executeQuery(querys2);
 //System.out.println("query---------------"+querys2);
                                  if(conn.rs.next()==true)
                                         {
                                                          
                                                   %>
                                                          
     element6.value ="<%= conn.rs.getString("SName")%>";
    
    <%
   // System.out.println(conn.rs.getString("SName"));
                                  }
 else{
    %>
            
             element6.value ="";
  <%}%>
    cell7.appendChild(element6);
    
    var cell8= row.insertCell(7);//create an input field of type text,class name, width and assign names, ids and functions
    cell8.style.width = "80px";
    var element7 = document.createElement("input");
    element7.type = "text";
    element7.name = "New_LastName"+b;
    element7.id ="New_LastName"+b;
    <% 
 String querys3= "Select * from mother_details where motherID='"+motherID+"'";
 conn.rs = conn.state1.executeQuery(querys3);
 //System.out.println("query---------------"+querys3);
                                  if(conn.rs.next()==true)
                                         {
                                                          
                                                   %>
                                                          
     element7.value ="<%= conn.rs.getString("LName")%>";
    
    <%
   // System.out.println(conn.rs.getString("LName"));
                                  }
 else{

    %>
          element7.value ="";    
            <%}%>
    element7.style.width = "80px";
  
    cell8.appendChild(element7);
   
   var villagesselect="<select   style=\"width:80px;\"  id=\"New_Village" + b + "\"  name=\"New_Village" + b + "\"></select>";
    var cell9= row.insertCell(8);//create an input field of type text,class name, width and assign names, ids and functions
 
 cell9.innerHTML=villagesselect;
    
    loadvillages(b);
    
    
    var cell10= row.insertCell(9);//create an input field of type text,class name, width and assign names, ids and functions
    cell10.style.width = "80px";
    var element9 = document.createElement("input");
    element9.type = "text";
    element9.name = "New_Age"+b;
    element9.id ="New_Age"+b;
      <% 
      
 String querys4= "Select * from mother_details where motherID='"+motherID+"'";
 conn.rs = conn.state1.executeQuery(querys4);
 //System.out.println("query---------------"+querys4);
                                  if(conn.rs.next()==true)
                                         {
                                                          
                                                   %>
                                                          
     element9.value ="<%= conn.rs.getString("Age")%>";
    
    <%
  //  System.out.println(conn.rs.getString("Age"));
                                  }
 else{

    %>
         element9.value ="";
     <%}%>
    element9.style.width = "60px";
    cell10.appendChild(element9);
    
    var cell11= row.insertCell(10);//create an input field of type text,class name, width and assign names, ids and functions
    cell11.style.width = "80px";
    var element10 = document.createElement("select");
    element10.type = "text";
    element10.name = "New_MaritalStatus"+b;
    element10.id ="New_MaritalStatus"+b;
    element10.style.width = "80px";
    var options;
    var options1;
    var options2;
    
     <% 
     
     String array[]={"Married","Widowed","Single","Divorced","Separated"};
     String a="";
 String querys5= "Select * from mother_details where motherID='"+motherID+"'";
 conn.rs = conn.state1.executeQuery(querys5);
 //System.out.println("query---------------"+querys5);
                                  while(conn.rs.next())
                                         {
                                                 
                           a= conn.rs.getString("maritalStatus");
                             }
for(int i=0;i<array.length;i++){
if(array[i].equalsIgnoreCase(a)){

                                           
                                                   %>
                                                          
    options = document.createElement("option");
                      options.type="text";
                      options.selected=true;
                      options.value="<%= array[i]%>";
                      options.innerHTML ="<%= array[i]%>";
    element10.appendChild(options);
    
    <%
   }
else{%>
  options1 = document.createElement("option");
                      options1.type="text";
                     
                      options1.value="";
                      options1.innerHTML ="";
  options = document.createElement("option");
                      options.type="text";
                     
                      options.value="<%=array[i].toString().trim()%>";
                      options.innerHTML="<%=array[i].toString().trim()%>";
    element10.appendChild(options);  
    element10.appendChild(options1);  
    
<%}}
                               

    %>
   cell11.appendChild(element10);
   
   
   
   
      var cell12 = row.insertCell(11);
   
    var element11 = document.createElement("input");//create an input field of type text and assign names, ids and functions
    element11.type = "hidden";
    element11.value = "<%=motherID %>";
    element11.name = "motherID"+b;
    element11.id = "motherID"+b;
   
   cell12.appendChild(element11);

       doOnLoad();      
    jay++;
   document.getElementById("ANCRegister_newRows").value=b;
 addANCRegister2();
 
 addQtoW();
 
 addstep5('step5_table');
 
 addstep6('step6_table');
 
 addstep7anc('ALtoANDets');
 
  document.getElementById("page1rows").innerHTML="("+b+") Rows";      
     document.getElementById("page2rows").innerHTML="("+b+") Rows";      
     document.getElementById("page3rows").innerHTML="("+b+") Rows";      
     document.getElementById("page4rows").innerHTML="("+b+") Rows";      
    document.getElementById("page5rows").innerHTML="("+b+") Rows";      
    document.getElementById("page6rows").innerHTML="("+b+") Rows";  
 
 
 
 
 
}
        function deleteANCRegister1() {
       
          //if the addrow has not been called  
        
            
            if((verifiers1===1)){
               jay =parseInt(document.getElementById("ANCRegister1_old_rows").value)+(parseInt(1));
               b=parseInt(document.getElementById("ANCRegister_newRows").value);
            //alert(""+document.getElementById("no_of_old_rows").value);        
                           }
                     
          verifiers1++;
          verifiers++;
            
            
            try {
                
            
            var table = document.getElementById("initialDets");
            //delete the last row 
            
             
            
            var rowCount = table.rows.length;
           //  alert(""+rowCount);
 //alert(rowCount);
           // for(var a=0; a<rowCount; a++) {
            //    var row = table.rows[a];
              //  var chkbox = row.cells[0].childNodes[0]; 
              if(rowCount>=parseInt(document.getElementById("ANCRegister1_old_rows").value)+parseInt(4)){
                  
                    table.deleteRow(rowCount-1);
                    rowCount--;
                    
                    //reduce the number of rows           
                    jay--;
                    b--;
                   document.getElementById("ANCRegister_newRows").value=b;
                   
                     
              }
              else{
                alert("Maximum deletable columns reached!");  
                  
                  
              }
  
            }catch(e) {
                alert(e);
            }
            
            deleteANCRegister2();
            deleteQtoW();
            deletestep5('step5_table');
            deletestep6('step6_table');
            deletestep7('ALtoANDets');
            
            
            
     document.getElementById("page1rows").innerHTML="("+b+") Rows";      
     document.getElementById("page2rows").innerHTML="("+b+") Rows";      
     document.getElementById("page3rows").innerHTML="("+b+") Rows";      
     document.getElementById("page4rows").innerHTML="("+b+") Rows";      
     document.getElementById("page5rows").innerHTML="("+b+") Rows";      
     document.getElementById("page6rows").innerHTML="("+b+") Rows"; 
            
            
            
        }


  var verifier2=1;
        var verifiers2=1;
var e=1;

function addANCRegister2() {

      if(verifier2==1){
               //// j =parseInt(document.getElementById("ItoM_old_rows").value)+(parseInt(1));
                e=parseInt(document.getElementById("ItoM").value);
                  
                          }
            
             e++;
          verifier2++;
          verifiers2++;  
       //alert(verifier2);     
 
    var table = document.getElementById("ItoMDets");
  //get the number of rows
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    
    
    
    
           
 //create element that is a checkbox assign type, name,style then apppend the child element to the cell
            var cell1 = row.insertCell(0);
            cell1.style.width = "40px";
            
            var element0 = document.createElement("label"); 
            element0.type = "label";
            //element1.name="chkbox[]";
            element0.innerHTML=""+parseInt(e+1);
            element0.style.width = "40px";
//            element0.style.textAlign="center";
            cell1.appendChild(element0);
   
    var cell2 = row.insertCell(1);
    var element1 = document.createElement("input");//create an input field of type text and assign names, ids and functions
    element1.type = "text";
    element1.value = "";
    element1.name = "New_parity"+e;
    element1.id = "New_parity"+e;
    element1.style.width = "80px";
//    element1.style.width = "200px";
    cell2.appendChild(element1);
    
    
    var cell3 = row.insertCell(2);
    var element2 = document.createElement("input");//create an input field of type text and assign names, ids and functions
    element2.type = "text";
    element2.value = "";
    element2.name = "New_gravidae"+e;
    element2.id = "New_gravidae"+e;
    element2.style.width = "80px";
    cell3.appendChild(element2);
            
    var cell4 = row.insertCell(3);//create an input field of type text,class name, width and assign names, ids and functions
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.name = "New_LMP"+e;
    element3.id = "New_LMP"+e;
    element3.readOnly = true;
    element3.value = "";
    element3.style.width = "80px";
    cell4.appendChild(element3);
    
    var cell5 = row.insertCell(4);//create an input field of type text,class name, width and assign names, ids and functions
    var element4 = document.createElement("input");
    element4.type = "text";
    element4.name = "New_EDD"+e;
    element4.id = "New_EDD"+e;
    element4.readOnly = true;
    element4.value = "";
    element4.style.width = "80px";
    cell5.appendChild(element4);
           
    
    var cell6= row.insertCell(5);//create an input field of type text,class name, width and assign names, ids and functions
    var element5 = document.createElement("input");
    element5.type = "text";
    element5.name = "New_Gestation"+e;
    element5.id ="New_Gestation"+e;
    element5.style.width = "80px";
  
    cell6.appendChild(element5);
    
    var cell7= row.insertCell(6);//create an input field of type text,class name, width and assign names, ids and functions
    var element6 = document.createElement("input");
    element6.type = "text";
    element6.name = "New_Weight"+e;
    element6.id ="New_Weight"+e;
    element6.style.width = "80px";
  
    cell7.appendChild(element6);
    
    var cell8= row.insertCell(7);//create an input field of type text,class name, width and assign names, ids and functions
    var element7 = document.createElement("input");
    element7.type = "text";
    element7.name = "New_BP"+e;
    element7.id ="New_BP"+e;
    element7.style.width = "80px";
  
    cell8.appendChild(element7);
    
    var cell9= row.insertCell(8);//create an input field of type text,class name, width and assign names, ids and functions
   
   
   var options;
 <% 
  ArrayList counselledOn = new ArrayList();
                    ArrayList counselledOnID = new ArrayList();
           

                        String counsels = "select * from counselling";
                        conn.rs6 = conn.state6.executeQuery(counsels);
//System.out.println("query"+counsels);
                        while (conn.rs6.next()) {
//                            counselledOn.add(conn.rs6.getString("counselling"));
//                            counselledOnID.add(conn.rs6.getString("counsellingID"));
                                                        
                                                   %>
                                                options+="<option value='<%=conn.rs6.getString("counsellingID")%>'><%=conn.rs6.getString("counselling")%></option>"             
          <%
                                                      

                                  }                                                     
                                
                %>  
                         cell9.innerHTML="<select multiple id='New_counselledOn"+e+"' required='true' style='width:150px;height:50px;' name='New_counselledOn"+e+"' >\n\
  <option value=''>Choose one</option>\n\
'"+options+"'</select>";
   
//   var element8 = document.createElement("select");
//    element8.type = "text";
//    
//    element8.name = "New_CounselledOn"+e;
//    element8.id ="New_CounselledOn"+e;
//    element8.multiple =true;
//    element8.style.width = "150px";
//    element8.style.height = "50px";
  
//    cell9.appendChild(element8);
            
   
  
   
 
  
    
   // e++;

     document.getElementById("ItoM").value=e;
    doOnLoadItoP();
}
 
        function deleteANCRegister2() {
       
          //if the addrow has not been called  
        
            
            if((verifiers2==1)){
              // j =parseInt(document.getElementById("ItoM_old_rows").value)+(parseInt(1));
                e=parseInt(document.getElementById("ItoM").value)+parseInt(4);
            //alert(""+document.getElementById("no_of_old_rows").value);        
                           }
                     
          verifiers2++;
          verifier2++;
            
            
            try {
                
            
            var table = document.getElementById("ItoMDets");
            //delete the last row 
            
             
            
            var rowCount = table.rows.length;
           //  alert(""+rowCount);
 //alert(rowCount);
           // for(var a=0; a<rowCount; a++) {
            //    var row = table.rows[a];
              //  var chkbox = row.cells[0].childNodes[0]; 
            //  if(rowCount>=document.getElementById("ItoM_old_rows").value){
              if(rowCount>=parseInt(document.getElementById("ItoM_old_rows").value)+parseInt(4)){
                  
                    table.deleteRow(rowCount-1);
                    rowCount--;
                    
                    //reduce the number of rows           
                 
                    e--;
                   document.getElementById("ItoM").value=e;
                   
                     
              }
            
                
           // }
            }catch(e) {
                alert(e);
            }
        }







            function numbers(evt){
                var charCode=(evt.which) ? evt.which : event.keyCode
                if(charCode > 31 && (charCode < 48 || charCode>57)){
                    return false;
                }

                else{
 


 
                    return true;
                }
            }

</script>


         <style>
            .swMain .stepContainer {
                 width:1160px;
                 padding-left: 10px;
                 height:600px;
             }
             .actionBar{
                 width:1160px;
                 padding-left: 10px;
             }
             .content{
                 width:1160px;
                 padding-left: 10px;
             }
             .swMain .stepContainer div.content{
                 margin-left: 0px;
             }
             swMain.StepTitle h2{
                 text-align: center;
                 
             }
             
             
             
             
         </style>
</head><body >
    
 <div id="container" style="width:1300px; height: 900px;">
     
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
     
     
     


    
    <form action="saveANCRegister" method="POST" name="myform" style="background:#ffffff; margin:10px;height:800px ;"> 
<table align="center" border="0" cellpadding="0" cellspacing="0">
<tr><td> 
<!-- Smart Wizard -->
      
<div id="wizard" style="width:1180px;" class="swMain" >
  			<ul>
                      <!---------------------------STEP1------------------------------------->      

                      <li style="width:175px;margin-right: 10px ;"><a href="#step-1">
                                    <label class="stepNumber">1</label>
                                    <span class="stepDesc">
                                        (a) to (h)<br />
                                        <small>a,b,c,d,e,f,g,h</small>
                                    </span>
                                </a></li>
                            
                        <!---------------------------STEP2------------------------------------->        
                            
                            <li style="width:175px;margin-right: 12px ;"><a href="#step-2">
                                    <label class="stepNumber">2</label>
                                    <span class="stepDesc">
                                        (i) to (m)<br />
                                        <small>i,j,k,l,m </small>
                                    </span>
                                </a></li>
                            
                         <!---------------------------STEP3------------------------------------->       
                            
                            <li style="width:175px;"><a href="#step-3">
                                    <label class="stepNumber">3</label>
                                    <span class="stepDesc">
                                        (q) to (w)<br />
                                        <small>q,r,s,t,u,v,w</small>
                                    </span>                   
                                </a></li>
                         
                          <!---------------------------STEP4------------------------------------->   
                       
                          
                          
                           <!---------------------------STEP5------------------------------------->  
                           
                        <li style="width:175px;"><a href="#step-4">
                                  <label class="stepNumber">4</label>
                                  <span class="stepDesc">(x) to(ad)<br/> <small>Prophylaxis <br/>& Screened For</small>
                                  </span>                   
                              </a></li>   
                           
                           
                           <!---------------------------STEP6------------------------------------->  
                           
                        <li style="width:175px;"><a href="#step-5">
                                  <label class="stepNumber">5</label>
                                  <span class="stepDesc">(ae)to(ak)<br/><small>Other Conditions<br/> & Treatment</small>
                                  </span>                   
                              </a></li>   
                           
                           
                        <li><a href="#step-6">
                                  <label class="stepNumber">6</label>
                                  <span class="stepDesc">(al)to(an)<br/><small></small>
                                  </span>                   
                              </a></li>   
                           
                           
           <%
           
String Sectionshelp1[]=new String [6];
int mcount=0;
 if(conn.state6.isClosed()){conn= new dbConnect();}
conn.rs6=conn.state6.executeQuery("Select * from help where help_id between 13 and 19 ");
while(conn.rs6.next()){
    
Sectionshelp1[mcount]=conn.rs6.getString(2);
if(mcount<=19){
mcount++;
}
}
%>            
                           
                           
                           
  			</ul>
<!--  ----------------------------------------------------- step 1 from a to h------------------------------------------------------------->
  
<div id="step-1" style="width: 1150px; ">	
          
            <h2 class="StepTitle"> ANC REGISTER (version jan 2011) (a) to (h) <a href="#" id="dialog-link1" >
                                    <img src="images/help_24.png"  /> </a>  <p id="page1rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p></h2>
<input type="text" value="add row" id="addrowstep1" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick=" addANCRegister1();"/>
<input type="text" value="delete last row" id="delrowstep1" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deleteANCRegister1();"/>

<!-- ui-dialog -->
<div id="dialog1" title="Columns a to h  Help ">
	<p>
         <% if(Sectionshelp1[0]!=null){%>
         <%=Sectionshelp1[0]%> 
         <%}%>
        </p>
</div>

            <table border="1px" class="viewpdt" align="center" style="margin-left: 120px;" >


          <tr>

<td> ANC NO.</td><td style="width:12px;"><input type="text" readonly  name="ancno" id="ancno" title="This field is filled only if you are entering data for one mother" required pattern="^d{4}-d{2}-d{4}$"/><input type="hidden" name="motherID" id="motherID" value=""></td>

<td><input type="text" required  value="Search.."  readonly style=" cursor:pointer;margin-left: 40px; text-transform:uppercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="checkancno();getANCMatDetails();"/> </td>
<td align="left"><font color="red"><span id="msg_ancno"></span></font>&nbsp;</td>
            
      <% String facils[]={"","Solian","","Ngubereti","","Emining","","Torongo","","Timboroa","","Simotwet"};%>
          
<td>Select a Facility</td>
<td><select name="facility" id="facility">
        <option value=""></option> 
       
       <% 
       String fac="";
       if(request.getParameter("facil")!=null) {
               fac=request.getParameter("facil");
                                               }
       if(fac.equalsIgnoreCase("Ngubureti")){fac="Ngubereti";}
       if(fac.equalsIgnoreCase("Emmining")){fac="Emining";}
       
       for(int b=0;b<facils.length;b++){
           if(facils[b].equalsIgnoreCase(fac))
           {            out.println("<option selected value=\""+facils[b]+"\">"+facils[b]+"</option>" );}
                     else{
                     out.println("<option value=\""+facils[b]+"\">"+facils[b]+"</option>" );}
                     }
            

%>
        
    </select></td>
          </tr>
        
            </table>
<!--           table for dispaying table from servlet-->
           <table id="initialDets"  class="viewpdt" style=" margin-bottom: 0px; ">
               
                <tr><th></th>
                <th style="width: 40px;"> Date of Visit </th>
                <th style="width: 60px;"> ANC No </th>
                <th style="width: 100px;">1st Visit </th>
                <th style="width: 80px;">Number of Visits</th>
                <th style="width: 100px;">First Name</th>
                <th style="width: 100px;">Middle Name </th>
                <th style="width: 100px;">Surname</th>
                <th style="width: 100px;">Village/Estate</th>
                <th style="width: 60px;">Age</th>
                <th style="width: 100px;">Marital Status</th></tr>
        <tr><th></th>
              <th style="width: 40px;"> a </th>
               <th style="width: 60px;"> b </th>
                <th style="width: 100px;">c </th>
                <th style="width: 80px;">d</th>
                <th style="width: 100px;">e</th>
                <th style="width: 100px;">e </th>
                <th style="width: 100px;">e</th>
                <th style="width: 100px;">f</th>
                <th style="width: 60px;">g</th>
                <th style="width: 100px;">h</th></tr>

                  <input type="hidden" id="ANCRegister_newRows" name="ANCRegister_newRows" value="0" >
                      <input type="hidden" id="ANCRegister1_old_rows" name="ANCRegister1_old_rows" value="0" >
        
           </table> 
   <input type="hidden" id="ANCRegister_newRows" name="ANCRegister_newRows" value="0" >
         <input type="hidden" id="ANCRegister1_old_rows" name="ANCRegister1_old_rows" value="0" >
           <!--           table for dispaying table from external js wen u press add row and delete row-->
           <table id="ANCRegister1Table"  class="viewpdt" style="margin-top: 0px;">
              </table> 
           
                                       
                               <%
 if(request.getParameter("ancnumber")!=null){
 String myancno=request.getParameter("ancnumber").toString();
 String motherIDs=request.getParameter("motherID").toString();
 session.setAttribute("ANCNO", myancno);
 %>
 <script type="text/javascript">
   document.getElementById("ancno").value="<%=myancno%>"; 
   document.getElementById("motherID").value="<%=motherIDs%>"; 
   
       </script>
         <%
}
%>  

        </div>

<!-- -----------------------------------------------Step 2 from i to m------------------------------------------------------------>


  			<div id="step-2">
                 

        
        
                             <!-----    I to P           ---->                     
                            
            <h2 class="StepTitle">ANC REGISTER  (i) to (p)     <a href="#" id="dialog-link2" >
                                    <img src="images/help_24.png"  /> </a>  <p id="page2rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p></h2>	
            
            <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick=" addANCRegister1();doOnLoad();"/> 
           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deleteANCRegister1();"/> 
          
           <!-- ui-dialog -->
<div id="dialog2" title="Columns i to p  Help ">
	<p>
         <% if(Sectionshelp1[1]!=null){%>
         <%=Sectionshelp1[1]%> 
         <%}%>
        </p>
</div>
           
           <table id="ItoMDets"  class="viewpdt" style="margin-left: 120px;">
                <tr><th></th>
                <th style="width: 40px;"> Parity</th>
                <th style="width: 60px;"> Gravidae </th>
                <th style="width: 100px;">Date of Last Menstrual Period </th>
                <th style="width: 80px;">Expected date of delivery</th>
                <th style="width: 100px;">Gestation in weeks</th>
                <th style="width: 100px;">Weight </th>
                <th style="width: 100px;">Blood Pressure</th>
                <th style="width: 100px;">Counselled on</th></tr>

        <tr><th></th>
             <th style="width: 40px;"> (i)</th>
              <th style="width: 60px;"> (j) </th>
              <th style="width: 100px;">(k) </th>
               <th style="width: 80px;">(l)</th>
               <th style="width: 100px;">(m)</th>
               <th style="width: 100px;">(n)</th>
                <th style="width: 100px;">(o)</th>
                <th style="width: 100px;">(p)</th></tr>
               <input type="hidden" id="ItoM" name="ItoM" value="0" >
              <input type="hidden" id="ItoM_old_rows" name="ItoM_old_rows" value="0" >
           </table> 
           
              
           <!--           table for dispaying table from external js wen u press add row and delete row-->
           <table id="ANCRegister2Table"  class="viewpdt" style="width: 930px; margin-left: 120px; "></table>     
            
                  
        </div>    
    
    
    <!---------------- Q to W-------------->
    
  			<div id="step-3">
            <h2 class="StepTitle">ANC REGISTER  (q) to (w)      <a href="#" id="dialog-link3" >
                                    <img src="images/help_24.png"  /> </a>
              <p id="page3rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p>
            </h2>
            <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick=" addANCRegister1();doOnLoad();"/> 
           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deleteANCRegister1();"/> 
           
           <!-- ui-dialog -->
<div id="dialog3" title="Columns q to w  Help ">
	<p>
         <% if(Sectionshelp1[2]!=null){%>
         <%=Sectionshelp1[2]%> 
         <%}%>
        </p>
</div>
        
        
                 <table id="QtoWDets" class="viewpdt" margin-left: style="margin-left: 120px;">
                      
                  <tr>
                <th></th>
               
                <th colspan="4" style="width: 100px;">Laboratory </th>
                <th colspan="3" style="width: 80px;">ART Eligibilty</th>
                </tr>
               
              
        
               <tr>
                <th></th>
                 <th style="width: 100px;"></th>
                <th style="width: 100px;"> </th>
                <th colspan="2" style="width: 100px;">Hiv Results</th>
                <th colspan="2" style="width: 100px;">Assessed Through</th>
                <th style="width: 100px;"></th></tr>
               
                <tr>
                <th></th>
                <th>Haemoglobin</th>
                <th>RPR/DRL</th>
                <th style="width: 100px;">Initial</th>
                <th style="width: 100px;">Retest </th>
                <th style="width: 100px;">WHO Stage</th>
                <th style="width: 100px;">CD4</th>
                <th style="width: 100px;">Start on ART IN ANC</th></tr>
               
               <tr>
                <th></th>
                <th>q</th>
                <th>r</th>
                <th style="width: 100px;">s</th>
                <th style="width: 100px;">t </th>
                <th style="width: 100px;">u</th>
                <th style="width: 100px;">v</th>
                <th style="width: 100px;">w</th></tr>
                  <input type="hidden" name="QtoWRegister" id="QtoWRegister" value="0"/>
           <input type="hidden" name="QtoWRegister_old_rows" id="QtoWRegister_old_rows" value="0"/>
         
           </table> 
                <!--      table for dispaying table from external js wen u press add row and delete row-->
           <table id="ANCRegister3Table"  class="viewpdt" style="width: 855px; margin-left: 120px;"></table>  
                             
        </div>
  		
        
        
        
        
        
        
        
        
        
        
    <!-------------------x to ad       ----------->
    
    <div id="step-4" >
            <h2 class="StepTitle"> ANC REGISTER (x) to (ad)  Prophylaxis / Screened for?      <a href="#" id="dialog-link4" >
                                    <img src="images/help_24.png"  /> </a>
              <p id="page4rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p>
            
            </h2>	
         <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="addANCRegister1();" id="addrowstep5"/> 
           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deleteANCRegister1();" id="delrowstep5"/> 
    
           <!-- ui-dialog -->
           
           
<div id="dialog4" title="Columns x to ad  Help ">
	<p>
         <% if(Sectionshelp1[3]!=null){%>
         <%=Sectionshelp1[3]%> 
         <%}%>
        </p>
</div>
           
                  
            <table border="1px" class="viewpdt"  id="step5_table" style=" margin-left: 120px;padding-top: 0px;width: 900px;">
              
              </table>
            
            
            
                       			
        </div>
    
    <!-----------------ae to ak  --->
    <div id="step-5">
        <h2 class="StepTitle">ANC REGISTER (ae) to (ak) Other Conditions And Treatment  (ae) to (ak)   <a href="#" id="dialog-link5" >
                                    <img src="images/help_24.png"  /> </a>
        
         <p id="page5rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p>
        </h2>
            <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick=" addANCRegister1();" id="addrowstep6"/> 
           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deleteANCRegister1();" id="delrowstep6"/> 
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label title="" class="linkstyle">point me</label>
       
           <!-- ui-dialog -->
<div id="dialog5" title="Columns ae to ak  Help ">
	<p>
         <% if(Sectionshelp1[4]!=null){%>
         <%=Sectionshelp1[4]%> 
         <%}%>
        </p>
</div>
           
              <table border="1px" class="viewpdt"  id="step6_table" style=" margin-left: 120px;padding-top: 0px;width: 900px;">
              
              </table>
            
                       			
        </div>
    
    
    <div id="step-6">
            <h2 class="StepTitle">Additional Treatment | Partner HIV C & T | Reffered For | Remarks<font color="white"> (al)-(ap)</font>     <a href="#" id="dialog-link6" >
                                    <img src="images/help_24.png"  /> </a>
             <p id="page6rows" style="margin-right:10px;font-size: 11px;">(1) Rows</p>
            </h2>	
            
            <input type="text"  value="add row" readonly style=" cursor:pointer;margin-left: 120px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="addANCRegister1();"/> 
           <input type="text"  value="delete last row" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="deleteANCRegister1();"/> 
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       
            <!-- ui-dialog -->
<div id="dialog6" title="Columns al to an  Help ">
	<p>
         <% if(Sectionshelp1[5]!=null){%>
         <%=Sectionshelp1[5]%> 
         <%}%>
        </p>
</div>
            
           <table id="ALtoANDets"  class="viewpdt" style="width: 900px; margin-left: 120px;"></table> 
           <!--           table for dispaying table from external js wen u press add row and delete row-->
           <table id="ANCRegister4Table"  class="viewpdt" style="width: 900px; "></table>                 			
        </div>
                       			
        </div>
    
        <script>
    getANCMatDetails();
    
</script> 
    
    <%
    conn.state1.close();
    conn.state6.close();
    
%>
    
  		</div>
<!-- End SmartWizard Content -->  		
 		
</td></tr>
</table>
     </form>
 </div>
    
</body>
</html>
