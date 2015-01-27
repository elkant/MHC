<%-- 
    Document   : UploadFie
    Created on : Nov 6, 2013, 4:35:53 PM
    Author     : Maureen
--%>


<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

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
<title>Invalid Dates DQA</title>
<script type="text/javascript" src="js/noty/themes/default.js"></script> 

<!--------------------------------------------------------------------------------------------------------------->
<!--------------------------------------------------------------------------------------------------------------->
<!--------------------------------------------------------------------------------------------------------------->

                                                          
  
        
           <!--<script src="scripts/jquery.js" type="text/javascript"></script>--> 
           <script src="scripts/jquery.dataTables.js" type="text/javascript"></script>
           <script src="scripts/jquery.dataTables.editable_1_1.js" type="text/javascript"></script>
         <script src="scripts/jquery.jeditable.js" type="text/javascript"></script>
<!--          <script src="scripts/jquery-ui.js" type="text/javascript"></script>-->
          <script src="scripts/jquery.validate.js" type="text/javascript"></script>
          <script src="scripts/dataTables.tableTools.js" type="text/javascript"></script>
          <script src="scripts/jquery.dataTables.columnFilter.js" type="text/javascript"></script>
          <link href="media2/dataTables/jquery.dataTables.css" rel="stylesheet" type="text/css" />
          <link href="scripts/dataTables.tableTools.css" rel="stylesheet" type="text/css" />
          
        <link href="media2/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media2/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media2/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media2/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="media2/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
        <link rel="stylesheet" href="themes/base/jquery.ui.all.css">
        <!---noty --->
         <script type="text/javascript" src="js/noty/jquery.noty.js"></script>

        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/center.js"></script>
        <!-- You can add more layouts if you want -->
            <script src="scripts/jquery.jeditable.datepicker.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/noty/themes/default.js"></script>            
                    <script src="js/datepicker.js" type="text/javascript"></script>
        <link rel="stylesheet" href="themes/base/jquery.ui.datepicker.css">

        
        <script src="ui/jquery.ui.core.js"></script>
	<script src="ui/jquery.ui.widget.js"></script>
	<script src="ui/jquery.ui.mouse.js"></script>
	<script src="ui/jquery.ui.draggable.js"></script>
	<script src="ui/jquery.ui.position.js"></script>
	<script src="ui/jquery.ui.resizable.js"></script>
	<script src="ui/jquery.ui.button.js"></script>
	<script src="ui/jquery.ui.dialog.js"></script>
	<script src="ui/jquery.ui.effect.js"></script>
	<script src="ui/jquery.ui.effect-blind.js"></script>
	<script src="ui/jquery.ui.effect-explode.js"></script>
	<link rel="stylesheet" href="ui-essentials/demos.css"/>


<!--------------------------------------------------------------------------------------------------------------->
<!--------------------------------------------------------------------------------------------------------------->
<!--------------------------------------------------------------------------------------------------------------->



 <script type="text/javascript">
        $(document).ready(function () {
            
            
//             $.ajax({
//    url:"uniquetargetpop",
//    type:'post',
//    dataType:'html',
//    success:function(data){
////        alert(data);
//        document.getElementById("targetpop").innerHTML="<option value=''>Select target population</option>"+data;
//        
//    }
//});  
            
           // loadduplicates1('0');
         
 var table=$("#members").dataTable({
              
             
              
              "dom": 'T<"clear">lfrtip',
        "tableTools": {
            "sSwfPath": "swf/copy_csv_xls_pdf.swf",
            "aButtons": [ {
                    "sExtends": "csv",
                    "sButtonText": "Save to csv"
                },
                {
                    "sExtends": "xls",
                    "sButtonText": "Save to xls"
                },
                {
                    "sExtends": "pdf",
                    "sButtonText": "Save to pdf"
                } ],
             "sRowSelect": "single"
            
        },
              
                "bProcessing": true,
                "sPaginationType": "full_numbers",
                "bJQueryUI": true
            }).makeEditable({
                
            sUpdateURL: "updateinvaliddate"         
                
                 
                 ,
                "aoColumns": [null,null,null,{  type:   'datepicker',
                                         datepicker: {
                                 dateFormat: 'yy-mm-dd',
                                 changeMonth:true,
                                 changeYear:true
                                          },
                                        sSuccessResponse: "IGNORE"
                                     },null
                        ]
            }
            
            ).columnFilter({"aoColumns": [{type: "text"},{},{},{},{},{},{},{},{},{},{} ]});
         
        });
        
        </script>









    
<script type="text/javascript">
    
   function loaddates(id){
            document.getElementById("members").innerHTML="<tr><td colspan='11'><h3 color='green'>Analysing Dates...Please wait.. </h3><img src='images/loading.gif'></td></tr>";
      var groupid=null;
      if(id!==""){   
          groupid=id.value; 
      
           
             $.ajax({
                    url:"invalid_dates?register="+groupid+"",
                    type:'post',
                    dataType:'html',
                    success:function (data){                              
                      window.location.reload();                                
                    }
                });}
            
            }
 
               
            
    
</script>     


    </head>
    <body>
        <div id="container" style="width:1300px;" >
 <br/>
              <div id="header" style="width:1250px; margin-left: 25px;">

                    
                   <!--=========================================menu=========================================-->     
            <%
                if (session.getAttribute("level") != null) {

                    if (session.getAttribute("level").equals("0")) {%>
            <%@include file="menu/adminmenu.html" %>
            <%} else {%>

            <%@include file="menu/clerkmenu.html" %>

            <%}

            } else {%>

            <%@include file="menu/clerkmenu.html" %>

            <%}%>

            <!--=====================================================================================--> 
                    
                    
              </div>
            
             
                         <%if (session.getAttribute("datasend") != null) {
                         
    
    
    %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("datasend")%>',
                        layout: 'center',
                         type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("datasend");
                            }

                        %>
            
            
            <br/>
           
       
                 
                   <form action="loadduplicates"   method="post"  style="width:1100px;margin-left: 100px;"  onsubmit="return nullness();">
                   <p id="loading"></p>
                    <table cellpadding="2px" cellspacing="3px" border="0px" class="table_form1" style="width:980px;" >


                        <tr><td style="text-align: right;">Show data where the mother's </td>
                            <td style="text-align: center;"> 
                                <select style="width:240px;height:40px;font-size:18px;" onchange="loaddates(this);" title="select the register to show the dates in that register "  id="duplicatecategory" name="duplicatecategory" class="textbox2">
                                   
                                    
                                   <option value="">Select an option</option>
                                   <option value="anc">Anc Register</option>
                                   <option value="maternity">Maternity Register</option>
                                   <option value="postnatal">Postnal Register</option>
                                 
                                </select></td> 
                             
                                <td style="text-align: left;"> dates are invalid  </td>
                                

             



<!--                            <td colspan="2">  <input type="submit"  name="sub"  value="Search..." class="submit"/>   </td>  -->


                        </tr>     
                    </table>
                </form>
                
                
                 <br/>
                <br/>
                
                
                <!--------------------Select Group to load its data--------------------------->
                
                
                
                
                
               <div id="demo_jui">
                  <% if(session.getAttribute("level").equals("0") ) {%>  
                 
                
                  <h2 id="notice" style="text-align: center;color:green;"> <% if(session.getAttribute("inv_header")!=null){ out.println(session.getAttribute("inv_header")); } else {%> Select The Duplicate Category in the Drop down list above <%}%></h2>
                  
                  
                  <%}else {%><%}%>
		        <table id="members" class="display">
		          
                            <%if(session.getAttribute("loaded_inv_table")!=null){
                                
                                out.println(session.getAttribute("loaded_inv_table"));
                                
                                }
            
            %>
                            
                            
		        </table>
                                
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

