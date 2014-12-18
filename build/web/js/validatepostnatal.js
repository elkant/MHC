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
      
      //validate step 3
      
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
      
      
 
    
      return isStepValid;
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
      if(document.getElementById("PNCRegister_newRows")!=null){
       r= parseInt(document.getElementById("PNCRegister_newRows").value);
                                               } 
      
      
      
      
      for(var t=1;t<=r;t++){
var admno=document.getElementById("New_PNCNo"+t).value;
var admdate=document.getElementById("New_VisitDate"+t).value;
var village=document.getElementById("New_Village"+t).value;
  
if(facilityname!="" && (admdate!="" || village!="" || admno!="")){
    
    if(admno=="" || admno.toUpperCase()=="NO PNC NO"){
         alert("Enter PNC Register Number");
           $("#New_PNCNo"+(t)).css("border-color","#ff0000");
           $("#New_PNCNo"+(t)).slideToggle("slow",function() {});
           $("#New_PNCNo"+(t)).slideToggle("slow",function() {});   
           $("#New_PNCNo"+(t)).focus();
           
          //return false;  
    }
    
    
    
    
    else  if(admdate=="" ){
         alert("Enter Date of Visit");
           $("#New_VisitDate"+(t)).css("border-color","#ff0000");
           $("#New_VisitDate"+(t)).slideToggle("slow",function() {});
           $("#New_VisitDate"+(t)).slideToggle("slow",function() {});   
           $("#New_VisitDate"+(t)).focus();
           
         // return false;  
    }
    
   
   
   else  if(village=="" ){
         alert("Enter Village Name");
           $("#New_Village"+(t)).css("border-color","#ff0000");
           $("#New_Village"+(t)).slideToggle("slow",function() {});
           $("#New_Village"+(t)).slideToggle("slow",function() {});   
           $("#New_Village"+(t)).focus();
           
        //  return false;  
    }
   
    
}//end of if




                          }//end of for loop
      
      
      

      
       return isValid;
    }//end of validate step 1
    
    
    
    
    function validateStep2(){
       var isValid = true; 


 var r=0;
      if(document.getElementById("PNCRegister_newRows")!=null){
       r= parseInt(document.getElementById("PNCRegister_newRows").value);
                                               } 
                                               
     
       var facilityname=document.getElementById("facility").value;
      
       
       for(var t=1;t<=r;t++){
           
           
          
        var edd=document.getElementById("New_DeliveryDate"+t).value;   
        var babystatus=document.getElementById("New_BabyStatus"+t).value;   
        var deliverymode=document.getElementById("New_DeliveryMode"+t).value;   
           
        if(facilityname!="" && (babystatus!="" || deliverymode!="" )){
    
    if(edd==""){
         alert("Enter EDD Date");
           $("#New_DeliveryDate"+(t)).css("border-color","#ff0000");
           $("#New_DeliveryDate"+(t)).slideToggle("slow",function() {});
           $("#New_DeliveryDate"+(t)).slideToggle("slow",function() {});   
           $("#New_DeliveryDate"+(t)).focus();
           
         // return false; 
          
       }   
           
       }
       
       
       }
       return isValid;
    }
    
    
   function validateStep3(){
   var isvalid=true;
   
   
   
   return isvalid;
   }