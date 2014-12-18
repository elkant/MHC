<%-- 
    Document   : adtelTest
    Created on : Oct 11, 2013, 2:06:56 PM
    Author     : SIXTYFOURBIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <Script type="text/javascript">
            
            function sendMsg(){
    
      var sms = document.getElementById("sms").value;
      var to=document.getElementById("to").value;
  
  
  
  if(sms==""){
      
      alert("enter sms");
      return false;
  }
  if(to==""){
      alert("enter phone number");
      
      return false;
  }
//   alert("status"+y);

    
 var xmlhttp;  
// alert(ids)
   
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

                    document.getElementById("resp").innerHTML=xmlhttp.responseText+"  <img src=\"images/present.png\" alt=\"send\">";

}
}


xmlhttp.open("POST","sms?to="+to+"&msg="+sms,true);

xmlhttp.send();
 
  document.getElementById("resp").innerHTML="sending..<img src=\"images/sending.gif\" alt=\"please wait\">"
 
 
   document.getElementById("sms").value="";
      document.getElementById("to").value="";
  
  
 } 
        </script>
        
    </head>
    <body>
        <form action="sms" method="POST">
            <table border="0">
                <tr><td>
                        <input name="to" id="to" type="text" placeholder="eg254712256703" maxlength="12" minlength="12"  /></td></tr>
            
                <tr> <td><textarea cols="25" id="sms" rows="3" placeholder="message" maxlength="160" name="msg"></textarea></td></tr>
                <tr><td><input type="text"  value="send sms" readonly style=" cursor:pointer;margin-left: 40px; text-transform:lowercase ; height: 18px; width:100px;text-align:center ; color:white ;background:coral; border-style:ridge ;" onclick="sendMsg();"/> 
         </td></tr>
                
                <tr><td><p id="resp"> </p></td></tr>
            </table>
            
            
        </form>
    </body>
</html>
