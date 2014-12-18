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
 
    
      return isStepValid;
   
	
 if(step == 3){
        if(validateStep3() == false ){
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
      
       }

		function validateStep1(){
       var isValid = true; 
     
     
     var facilityname=document.getElementById("facility").value;
     
     if(facilityname==""||facilityname==null){
         alert("Select Facility");
           $("#facility").css("border-color","#ff0000");
           $("#facility").slideToggle("slow",function() {});
           $("#facility").slideToggle("slow",function() {});   
           $("#facility").focus();
          return false;  
    
                                       }
     
     
     

var r=0;
      if(document.getElementById("MatRegister_newRows")!=null){
       r= parseInt(document.getElementById("MatRegister_newRows").value);
                                               } 
      
      
      
      
      for(var t=1;t<=r;t++){
var admno=document.getElementById("New_AdmissionNum"+t).value;
var admdate=document.getElementById("New_AdmissionDate"+t).value;
var village=document.getElementById("New_Village"+t).value;
  
if(facilityname!="" && (admdate!="" || village!="")){
    
    if(admno=="" || admno.toUpperCase()=="NO ANC NO"){
         alert("Enter Admission Number");
           $("#New_AdmissionNum"+(t)).css("border-color","#ff0000");
           $("#New_AdmissionNum"+(t)).slideToggle("slow",function() {});
           $("#New_AdmissionNum"+(t)).slideToggle("slow",function() {});   
           $("#New_AdmissionNum"+(t)).focus();
           
         // return false;  
    }
    
    
    
    
      if(admdate=="" ){
         alert("Enter Admission Date");
           $("#New_AdmissionDate"+(t)).css("border-color","#ff0000");
           $("#New_AdmissionDate"+(t)).slideToggle("slow",function() {});
           $("#New_AdmissionDate"+(t)).slideToggle("slow",function() {});   
           $("#New_AdmissionDate"+(t)).focus();
           
          //return false;  
    }
    
   
   
     if(village=="" ){
         alert("Village Name is a must enter");
           $("#New_Village"+(t)).css("border-color","#ff0000");
           $("#New_Village"+(t)).slideToggle("slow",function() {});
           $("#New_Village"+(t)).slideToggle("slow",function() {});   
           $("#New_Village"+(t)).focus();
           
          //return false;  
    }
   
    
}//end of if




                          }
      
      
      
       return isValid;
    }
    
    
    
    
   	function validateStep2(){
       var isValid = true; 


 var r=0;
      if(document.getElementById("HtoL")!=null){
       r= parseInt(document.getElementById("HtoL").value);
                                               } 
                                               
     
       var facilityname=document.getElementById("facility").value;
       
       for(var t=1;t<=r;t++){
           
           
        var parity=document.getElementById("New_Parity"+t).value;
        var gravidae=document.getElementById("New_Gravida"+t).value;   
        var edd=document.getElementById("New_EDD"+t).value;   
           
        if(facilityname!="" && (parity!="" || gravidae!="")){
    
    if(edd==""){
         alert("Enter EDD Date");
           $("#New_EDD"+(t)).css("border-color","#ff0000");
           $("#New_EDD"+(t)).slideToggle("slow",function() {});
           $("#New_EDD"+(t)).slideToggle("slow",function() {});   
           $("#New_EDD"+(t)).focus();
           
        //  return false;  
       }   
           
                                                             }
       
       
       }
       return isValid;
    }
    
    
    	function validateStep3(){
       var isValid = true; 


var r=0;
      if(document.getElementById("ItoM")!=null){
      // r= parseInt(document.getElementById("ItoM").value);
                                               } 
      
       return isValid;
    }
    
    	function validateStep4(){
       var isValid = true; 


var r=0;
      if(document.getElementById("ItoM")!=null){
      // r= parseInt(document.getElementById("ItoM").value);
                                               } 
      
       return isValid;
    }