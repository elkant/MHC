/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 $(document).ready(function(){
    	// Smart Wizard     	
  		$('#wizard').smartWizard({transitionEffect:'slideleft',onLeaveStep:leaveAStepCallback,onFinish:onFinishCallback,enableFinishButton:true});

      function leaveAStepCallback(obj){
        var step_num= obj.attr('rel');
        return validateSteps(step_num);
      }
      
      function onFinishCallback(){
       if(validateAllSteps()){
        $('form').submit();
       }
      }
            
		});
	   
    function validateAllSteps(){
       var isStepValid = true;
       
       if(validateStep1() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:1,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:1,iserror:false});
       }
       
       if(validateStep2() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:2,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:2,iserror:false});
       }
//       if(validateStep3() == false){
//         isStepValid = false;
//         $('#wizard').smartWizard('setError',{stepnum:3,iserror:true});         
//       }else{
//         $('#wizard').smartWizard('setError',{stepnum:3,iserror:false});
//       }
        if(validateStep4() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:4,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:4,iserror:false});
       }
         if(validateStep5() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:5,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:5,iserror:false});
       }
       
         if(validateStep6() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:6,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:6,iserror:false});
       }
       
       
       
       if(!isStepValid){
          $('#wizard').smartWizard('showMessage','Please correct the errors in the steps and continue');
       }
              
       return isStepValid;
    } 	
		
		
		function validateSteps(step){
		  var isStepValid = true;
      // validate step 1
      if(step == 1){
        if(validateStep1() == false ){
          isStepValid = false; 
          $('#wizard').smartWizard('showMessage','Please correct the errors in step'+step+ ' and click next.');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      
     //  validate step2
      if(step == 2){
        if(validateStep2() == false ){
          isStepValid = false; 
          $('#wizard').smartWizard('showMessage','Please correct the errors in step'+step+ ' and click next.');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
 if(step == 4){
        if(validateStep4() == false ){
          isStepValid = false; 
          $('#wizard').smartWizard('showMessage','Please correct the errors in step'+step+ ' and click next.');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      if(step == 5){
        if(validateStep5() == false ){
          isStepValid = false; 
          $('#wizard').smartWizard('showMessage','Please correct the errors in step'+step+ ' and click next.');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
     }
      if(step == 6){
        if(validateStep6() == false ){
          isStepValid = false; 
          $('#wizard').smartWizard('showMessage','Please correct the errors in step'+step+ ' and click next.');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      return isStepValid;
    }
		
	
        
        function validateStep1(){
        
       var isValid = true; 
       // Validate Username
//       var un = $('#ancno').val();
//       
//if((document.getElementById("New_VisitDate1"))==null){
//$('#msg_ancno').html('<b><font color=\"red\">No data!</font></b>').show();
//
//isValid=false;
//
//}
//else if(!un && un.length <= 0){
//         isValid = false;
//        $('#msg_ancno').html('Please enter an ANCNO').show();
//          $("#ancno").css("border-color","#ff0000");
//           $("#ancno").slideToggle("slow",function() {});
//           $("#ancno").slideToggle("slow",function() {});   
//           $("#ancno").focus();
//       
//       }else{
//         $('#msg_ancno').html('').hide();
//       }
//      if(document.getElementById("ANCRegister1_old_rows")!=null){
//      var y = parseInt(document.getElementById("ANCRegister1_old_rows").value);}
    //  alert(y);
      
//      for(var q=1;q<=y;q++){
//          var x;
//          var b;
//          if(document.getElementById("VisitDate"+q)!="" && document.getElementById("VisitDate"+(q+parseInt(1)))!=""){
//          x= document.getElementById("VisitDate"+q);
//          b=document.getElementById("VisitDate"+(q+parseInt(1)));
//      }
////      else{
////          x="";
////          b="";
////      }
//      if(x!=null && b!=null){
//          
//      
//      if(x.value > b.value) {
//          
//          alert("Initial Visit Date shouldnt be Greater than subsequent Visit Dates");
//          $("#VisitDate"+(q+parseInt(1))).css("border-color","#ff0000");
//           $("#VisitDate"+(q+parseInt(1))).slideToggle("slow",function() {});
//           $("#VisitDate"+(q+parseInt(1))).slideToggle("slow",function() {});   
//           $("#VisitDate"+(q+parseInt(1))).focus();
//          return false;
//      }
//      }
//      }
      if(document.getElementById("ANCRegister_newRows")!=null){
      var r= parseInt(document.getElementById("ANCRegister_newRows").value);
                                                              }
//alert(r);
var facilityname=document.getElementById("facility").value;
if(facilityname==""||facilityname==null){
         alert("Select Facility");
           $("#facility").css("border-color","#ff0000");
           $("#facility").slideToggle("slow",function() {});
           $("#facility").slideToggle("slow",function() {});   
           $("#facility").focus();
          return false;  
    
    
                                       }

var ancno="";

for(var t=1;t<=r;t++){
          var a;
          var p;
          
          if(document.getElementById("New_VisitDate"+t)!="" && document.getElementById("New_VisitDate"+(t+parseInt(1)))!=""){
          a= document.getElementById("New_VisitDate"+t);
          p=document.getElementById("New_VisitDate"+(t+parseInt(1)));
     
ancno=document.getElementById("New_ANCNo"+t).value;
var visitdate=document.getElementById("New_VisitDate"+t).value;
var village=document.getElementById("New_Village"+t).value;
if(facilityname!="" && (visitdate!="" || village!="")){
    
    if(ancno=="" || ancno.toUpperCase()=="NO ANC NO"){
         alert("Enter ANC Number");
           $("#New_ANCNo"+(t)).css("border-color","#ff0000");
           $("#New_ANCNo"+(t)).slideToggle("slow",function() {});
           $("#New_ANCNo"+(t)).slideToggle("slow",function() {});   
           $("#New_ANCNo"+(t)).focus();
           
          return false;  
    }
    
    //get the DATE OF VISIT.
    
    if(visitdate==""){
         alert("Enter date of Visit");
           $("#New_VisitDate"+(t)).css("border-color","#ff0000");
           $("#New_VisitDate"+(t)).slideToggle("slow",function() {});
           $("#New_VisitDate"+(t)).slideToggle("slow",function() {});   
           $("#New_VisitDate"+(t)).focus();
           
         // return false;  
    } 
    
    //get the village name
      if(village==""){
         alert("Enter Village Name");
           $("#New_Village"+(t)).css("border-color","#ff0000");
           $("#New_Village"+(t)).slideToggle("slow",function() {});
           $("#New_Village"+(t)).slideToggle("slow",function() {});   
           $("#New_Village"+(t)).focus();
           
          //return false;  
    } 
    
    
}


 }
//      else{
//          x="";
//          b="";
//      }
      if(a!=null && p!=null){
          
      
      if(a.value > p.value) {
          
//          alert("Initial Visit Date shouldn't be Greater than subsequent Visit Dates");
//           $("#New_VisitDate"+(t+parseInt(1))).css("border-color","#ff0000");
//           $("#New_VisitDate"+(t+parseInt(1))).slideToggle("slow",function() {});
//           $("#New_VisitDate"+(t+parseInt(1))).slideToggle("slow",function() {});   
//           $("#New_VisitDate"+(t+parseInt(1))).focus();
//          return false;
      }
      }
    
    
}
//var w = document.getElementById("FirstVisit1").value;
//
//if(w=="Yes"){
//   for(var u=2;u<=y;u++){
//       if( document.getElementById("FirstVisit"+u).value=="Yes"){
//            alert("Cant Select Yes since first visit was already done ");
//
//      return false;
//       }
//      
//   } 
//}
//if(w==""){
//     for(var l=2;l<=y;l++){
//       document.getElementById("New_FirstVisit"+l).value=="No";
//
//      return false;
//       }
//      
//   
//}
       return isValid;
    }//end of validate step 1
    
    
    
    
    function validateStep2(){
      var isValid = true; 
       var r=0;
      if(document.getElementById("ItoM")!=null){
       r= parseInt(document.getElementById("ItoM").value);
                                               } 
                                               
       if(document.getElementById("ItoM_old_rows")!=null){
     var y = parseInt(document.getElementById("ItoM_old_rows").value); 
       }
       var facilityname=document.getElementById("facility").value;
       
       for(var t=1;t<=r;t++){
           
          if(document.getElementById("New_parity"+t)!=null){ 
        var parity=document.getElementById("New_parity"+t).value;
        var gravidae=document.getElementById("New_gravidae"+t).value;   
        var edd=document.getElementById("New_EDD"+t).value;   
           
        if(facilityname!="" && (parity!="" || gravidae!="")){
    
    if(edd==""){
         alert("Enter EDD Date");
           $("#New_EDD"+(t)).css("border-color","#ff0000");
           $("#New_EDD"+(t)).slideToggle("slow",function() {});
           $("#New_EDD"+(t)).slideToggle("slow",function() {});   
           $("#New_EDD"+(t)).focus();
           
          //return false;  
       }   
           
       }
       
       }
       }
       
       
//     alert(y);
//     
//     for(var a=1;a<=y;a++){
//         if(document.getElementById("LMP"+a).value > document.getElementById("EDD"+a).value){
//         alert("LMP cannot be greater than EDD");
//           $("#LMP"+a).css("border-color","#ff0000");
//           $("#LMP"+a).slideToggle("slow",function() {});
//           $("#LMP"+a).slideToggle("slow",function() {});   
//           $("#LMP"+a).focus();
//return false;   
//             
//         }
//         
//      }
      var k = parseInt(document.getElementById("ItoM").value); 
//      alert(k);
//  for(var b=1;b<=k;b++){        
//         if(document.getElementById("New_LMP"+b).value > document.getElementById("New_EDD"+b).value){
//          alert("LMP cannot be greater than EDD");
//           $("#New_LMP"+b).css("border-color","#ff0000");
//           $("#New_LMP"+b).slideToggle("slow",function() {});
//           $("#New_LMP"+b).slideToggle("slow",function() {});   
//           $("#New_LMP"+b).focus();
//return false;  
//         }
//}
      return isValid;
    
    }
    
  

function validateStep4(){
 var isValid=true;   

    var allrows = $('#new_step5_no_rows').val();
    var alloldrows = $('#old_step5_no_rows').val();
    //alert(allrows);
   for(var p=1;p<=parseInt(allrows);p++){
       
   
   if(validate_x_to_ad(p)==false){
       //alert("flase");
       //old_checked=false;
   // isValid=false;
   // break;
   }    
       else{
        isValid=true;
        //alert("true true");
//        old_checked=true;   
       }
       
   } 
    
    
    return isValid;
}

function validateStep5(){
 var isValid=true;   
    
    
     //validate the new files   
    
    
      var allrows6 = $('#new_step6_no_rows').val();
    var alloldrows = $('#old_step6_no_rows').val();
    //alert(allrows);
   for(var p=1;p<=parseInt(allrows6);p++){
       
   
   if(validate_ae_to_ak(p)==false){
       //alert("flase");
       //old_checked=false;
    isValid=false;
    break;
   }    
       else{
        isValid=true;
        //alert("true true");
//        old_checked=true;   
       }
       
   } 
    
    
    
    return isValid;
}

function validateStep6(){
 var isValid=true;   

      var allrows7 = $('#new_step7_no_rows').val();
    var alloldrows7 = $('#old_step7_no_rows').val();
    //alert(allrows);
   for(var p=1;p<=parseInt(allrows7);p++){
       
   
   if(validate_al_to_an(p)==false){
       //alert("flase");
       //old_checked=false;
    isValid=false;
    break;
   }    
       else{
        isValid=true;
        //alert("true true");
//        old_checked=true;   
       }
       
   } 
     
      return isValid;
    }


function checkancno(){
var redigit=/^\d{4}-d{2}-d{4}$/ //regular expression defining a 5 digit number
if (document.myform.ancno.value.search(redigit)==-1) //if match failed
          
//alert("Please enter a valid ancNo")
return false;
}
function validate_x_to_ad(id){
    var validit=true;
    var ctx,nvp,azt,haart,babynvp,tb,test,result;
    
    ctx=$("#CTX"+id).val();
    nvp=$("#NVP"+id).val();
    azt=$("#AZT"+id).val();
    haart=$("#HAART"+id).val();
    
    babynvp=$("#BABYNVP"+id).val();
    tb=$("#TB"+id).val();
    test= $('#Test'+id+' > option:selected');
    //!$("#Test"+id+" option  :selected").length
    result=$("#Result"+id).val();
    
    if((ctx!=""&&ctx!=null)||(nvp!=""&&nvp!=null)||(azt!=""&&azt!=null)||(haart!=""&&haart!=null)||(babynvp!=""&&babynvp!=null)||(tb!=""&&tb!=null)||(test.length!=0&&$('#Test'+id)!=null)||(result!=""&&result!=null)){
        if(ctx==""){
            
            // alert("Select ctx");
            
            $("#CTX"+id).css("border-color","#ff0000");
            $("#CTX"+id).slideToggle( "slow", function() {});
            $("#CTX"+id).slideToggle( "slow", function() {});
           // $("#CTX"+id).focus();
           
               validit= false; 
               
        }
        
        
       else if(nvp==""){
            
            
             //alert("select nvp");
            
            $("#NVP"+id).css("border-color","#ff0000");
            $("#NVP"+id).slideToggle( "slow", function() {});
            $("#NVP"+id).slideToggle( "slow", function() {});
           // $("#NVP"+id).focus();
           
               validit= false; 
               
        }
        else if(azt==""){
            
            
             //alert("select azt");
            
            $("#AZT"+id).css("border-color","#ff0000");
            $("#AZT"+id).slideToggle( "slow", function() {});
            $("#AZT"+id).slideToggle( "slow", function() {});
            //$("#AZT"+id).focus();
           
               validit= false; 
            
            
        }
        
        
        
         else if(haart==""){
            
            
            // alert("select HAART");
            
            $("#HAART"+id).css("border-color","#ff0000");
            $("#HAART"+id).slideToggle( "slow", function() {});
            $("#HAART"+id).slideToggle( "slow", function() {});
            //$("#HAART"+id).focus();
           
               validit= false; 
            
            
        }
        
         else if(tb==""){
            
            
            // alert("");
            
            $("#TB"+id).css("border-color","#ff0000");
            $("#TB"+id).slideToggle( "slow", function() {});
            $("#TB"+id).slideToggle( "slow", function() {});
            //$("#TB"+id).focus();
           
               validit= false; 
            
            
        }
        
         else if(test.length==0){
            
            
             //alert("select test(s)");
            
            $("#Test"+id).css("border-color","#ff0000");
            $("#Test"+id).slideToggle( "slow", function() {});
            $("#Test"+id).slideToggle( "slow", function() {});
           // $("#Test"+id).focus();
           
               validit= false; 
            
            
        }
        
         else if(babynvp==""){
            
            
             //alert("select NVP for BABY");
            
            $("#BABYNVP"+id).css("border-color","#ff0000");
            $("#BABYNVP"+id).slideToggle( "slow", function() {});
            $("#BABYNVP"+id).slideToggle( "slow", function() {});
           // $("#BABYNVP"+id).focus();
           
               validit= false; 
            
            
        }
        
         else if(result==""){
            
            
            // alert("enter results");
            
            $("#Result"+id).css("border-color","#ff0000");
            $("#Result"+id).slideToggle( "slow", function() {});
            $("#Result"+id).slideToggle( "slow", function() {});
            //$("#Result"+id).focus();
           
               validit= false; 
            
            
        }
        else{validit=true;}
        
    }
    
    return validit;
}

//=========================================VALIDATE STEP 6=====================================


function validate_ae_to_ak(id){
var isvalid6=true;
var conditions=document.getElementById("otherstriger"+id).checked;
var others=document.getElementById("others"+id).value;
var treatment= $('#other_conditions'+id+' > option:selected');

if(conditions!=false){
        if(others==""){
                  alert("enter other conditions");
            $("#others"+id).css("border-color","#ff0000");
            $("#others"+id).slideToggle( "slow", function() {});
            $("#others"+id).slideToggle( "slow", function() {});
            $("#others"+id).focus();
            isvalid6=false; 
}
else{isvalid6=true;}


}
//else if(treatment.length==0&&others==""){
// alert("enter other conditions");
//            $("#other_conditions"+id).css("border-color","#ff0000");
//            $("#other_conditions"+id).slideToggle( "slow", function() {});
//            $("#other_conditions"+id).slideToggle( "slow", function() {});
//            $("#other_conditions"+id).focus();
//            isvalid6=false;    
//    
//}

 
    return isvalid6;
}



//validate step 7
function validate_al_to_an(id){
    var isvalid7=true;
    
    
var conditions=document.getElementById("otherstriger7"+id).checked;
var others=document.getElementById("others7"+id).value;
var test_results=$("#test_results"+id).val();
var remarks=$("#remarks"+id).val();
var reffered=$("#reffered"+id).val();
var treatment= $('#adt_treatment'+id+' > option:selected');

if(conditions!=false){
        if(others==""){
            alert("enter other conditions");
            $("#others7"+id).css("border-color","#ff0000");
            $("#others7"+id).slideToggle( "slow", function() {});
            $("#others7"+id).slideToggle( "slow", function() {});
            $("#others7"+id).focus();
            $("#others7"+id).css("border-color","#ff0000");
            $("#others7"+id).slideToggle( "slow", function() {});
            $("#others7"+id).slideToggle( "slow", function() {});
            $("#others7"+id).focus();
            isvalid7=false; 
}
else{isvalid7=true;}


}

else if(remarks!=""||test_results!=""||reffered!=""||treatment.length!=0){
    
 if(remarks==""){
     
            alert("enter remarks");
            $("#remarks"+id).css("border-color","#ff0000");
            $("#remarks"+id).slideToggle( "slow", function() {});
            $("#remarks"+id).slideToggle( "slow", function() {});
            $("#remarks"+id).focus();
            //isvalid7=false;    
     
 }  
else if(treatment.length==0&&others==""){
    
  //alert("select a treatment");
            $("#others7"+id).css("border-color","#ff0000");
            $("#others7"+id).slideToggle( "slow", function() {});
            $("#others7"+id).slideToggle( "slow", function() {});
            $("#others7"+id).focus();
           // isvalid7=false;     
    
} 
else if(test_results==""){
    
  //alert("enter results");
            $("#test_results"+id).css("border-color","#ff0000");
            $("#test_results"+id).slideToggle( "slow", function() {});
            $("#test_results"+id).slideToggle( "slow", function() {});
            $("#test_results"+id).focus();
            //isvalid7=false;     
    
} 
    
else if(reffered==""){
    
 // alert("enter reffered value");
            $("#reffered"+id).css("border-color","#ff0000");
            $("#reffered"+id).slideToggle( "slow", function() {});
            $("#reffered"+id).slideToggle( "slow", function() {});
            $("#reffered"+id).focus();
            //isvalid7=false;     
    
} 
  else{
      
      isvalid7=true;
      
  }  
    
}
 
    
    
    
    
  return isvalid7;  
}