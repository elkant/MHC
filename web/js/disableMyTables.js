/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function disableTables(){
//if the input anc number has got no values in it, then disable the user input values.


if(document.getElementById("pregnancy_old_rows")!=null){

//step 

//step1details
if(document.getElementById("pregnancy_old_rows").value=="0"){
    //change table values to a warning
    document.getElementById("initialDets").innerHTML="<tr><td colspan=\"8\"><h3>This ANC NO. is <b>NOT</b> enrolled into this System.Reffer them to their respective CHW for enrollment</h3></td></tr>";
    //document.getElementById("ANCRegister1Table").innerHTML="<tr><td colspan=\"8\"><h3>The ANC NO. You have entered Is <b>NOT</b> enrolled into this System.</h3></td></tr>";
    //disable add rows field
   document.getElementById("addrowstep1").disabled="true";
   document.getElementById("addrowstep1").innerHTML="";
   document.getElementById("delrowstep1").disabled="true";
   document.getElementById("delrowstep1").innerHTML="";
    
}
    
    
if(document.getElementById("pregnancy_old_rows").value=="0"){
    //change table values to a warning
    document.getElementById("step5_table").innerHTML="<tr><td colspan=\"8\"><h3>The ANC NO. You have entered Is <b>NOT</b> enrolled into this System.</h3></td></tr>";
    //disable add rows field
   document.getElementById("addrowstep5").disabled="true";
   document.getElementById("delrowstep5").disabled="true";
   
    
}
    
    
    
//step6details
if(document.getElementById("pregnancy_old_rows").value=="0"){
    //change table values to a warning
    document.getElementById("step6_table").innerHTML="<tr><td colspan=\"8\"><h3>The ANC NO. You have entered is <b>NOT</b> enrolled in this System.Reffer them to their respective Community Health Worker</h3></td></tr>";
    //disable add rows field
   document.getElementById("addrowstep6").disabled="true";
   document.getElementById("delrowstep6").disabled="true";
   
    
}
    
} 
    
    
}

