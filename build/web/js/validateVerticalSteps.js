/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    // Smart Wizard     	
    $('#wizard').smartWizard({
        transitionEffect:'slideleft',
        onLeaveStep:leaveAStepCallback,
        onFinish:onFinishCallback,
        enableFinishButton:true
    });

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
        $('#wizard').smartWizard('setError',{
            stepnum:1,
            iserror:true
        });         
    }else{
        $('#wizard').smartWizard('setError',{
            stepnum:1,
            iserror:false
        });
    }
     
   //===== 2 
     
        if(validateStep2() == false){
        isStepValid = false;
        $('#wizard').smartWizard('setError',{
            stepnum:2,
            iserror:true
        });         
    }else{
        $('#wizard').smartWizard('setError',{
            stepnum:2,
            iserror:false
        });
    }
      
     
   //======= 3 
     
    if(validateStep3() == false){
        isStepValid = false;
        $('#wizard').smartWizard('setError',{
            stepnum:3,
            iserror:true
        });         
    }else{
        $('#wizard').smartWizard('setError',{
            stepnum:3,
            iserror:false
        });
    }
       
//=========4       
          if(validateStep4() == false){
        isStepValid = false;
        $('#wizard').smartWizard('setError',{
            stepnum:4,
            iserror:true
        });         
    }else{
        $('#wizard').smartWizard('setError',{
            stepnum:4,
            iserror:false
        });
    }
      
  //====5
     if(validateStep5() == false){
        isStepValid = false;
        $('#wizard').smartWizard('setError',{
            stepnum:5,
            iserror:true
        });         
    }else{
        $('#wizard').smartWizard('setError',{
            stepnum:5,
            iserror:false
        });
    }
      
     //====6
     
    if(validateStep6() == false){
        isStepValid = false;
        $('#wizard').smartWizard('setError',{
            stepnum:6,
            iserror:true
        });         
    }else{
        $('#wizard').smartWizard('setError',{
            stepnum:6,
            iserror:false
        });
    }
          
 //======7
 
    if(validateStep7() == false){
        isStepValid = false;
        $('#wizard').smartWizard('setError',{
            stepnum:7,
            iserror:true
        });         
    }else{
        $('#wizard').smartWizard('setError',{
            stepnum:7,
            iserror:false
        });
    }
      //========8
         if(validateStep8() == false){
        isStepValid = false;
        $('#wizard').smartWizard('setError',{
            stepnum:8,
            iserror:true
        });         
    }else{
        $('#wizard').smartWizard('setError',{
            stepnum:8,
            iserror:false
        });
    }
      //=========9
      
      
         if(validateStep9() == false){
        isStepValid = false;
        $('#wizard').smartWizard('setError',{
            stepnum:9,
            iserror:true
        });         
    }else{
        $('#wizard').smartWizard('setError',{
            stepnum:9,
            iserror:false
        });
    }
      //==========10
      
         if(validateStep10() == false){
        isStepValid = false;
        $('#wizard').smartWizard('setError',{
            stepnum:10,
            iserror:true
        });         
    }else{
        $('#wizard').smartWizard('setError',{
            stepnum:10,
            iserror:false
        });
    }
      //============11
      
      
         if(validateStep11() == false){
        isStepValid = false;
        $('#wizard').smartWizard('setError',{
            stepnum:11,
            iserror:true
        });         
    }else{
        $('#wizard').smartWizard('setError',{
            stepnum:11,
            iserror:false
        });
    }
      
      //=============
      
         if(validateStep12() == false){
        isStepValid = false;
        $('#wizard').smartWizard('setError',{
            stepnum:12,
            iserror:true
        });         
    }else{
        $('#wizard').smartWizard('setError',{
            stepnum:12,
            iserror:false
        });
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
            $('#wizard').smartWizard('setError',{
                stepnum:step,
                iserror:true
            });         
        }else{
            $('#wizard').smartWizard('hideMessage');
            $('#wizard').smartWizard('setError',{
                stepnum:step,
                iserror:false
            });
        }
    }
    //validate step 2
    if(step == 2){
        if(validateStep2() == false ){
            isStepValid = false; 
            $('#wizard').smartWizard('showMessage','Please correct the errors in step'+step+ ' and click next.');
            $('#wizard').smartWizard('setError',{
                stepnum:step,
                iserror:true
            });         
        }else{
            $('#wizard').smartWizard('hideMessage');
            $('#wizard').smartWizard('setError',{
                stepnum:step,
                iserror:false
            });
        }
    }
      
      
      
    //validate step3
    if(step == 3){
        if(validateStep3() == false ){
            isStepValid = false; 
            $('#wizard').smartWizard('showMessage','Please correct the errors in step'+step+ ' and click next.');
            $('#wizard').smartWizard('setError',{
                stepnum:step,
                iserror:true
            });         
        }else{
            $('#wizard').smartWizard('hideMessage');
            $('#wizard').smartWizard('setError',{
                stepnum:step,
                iserror:false
            });
        }
    }
    
    
    
     //validate step4
    if(step == 4){
        if(validateStep4() == false ){
            isStepValid = false; 
            $('#wizard').smartWizard('showMessage','Please correct the errors in step'+step+ ' and click next.');
            $('#wizard').smartWizard('setError',{
                stepnum:step,
                iserror:true
            });         
        }else{
            $('#wizard').smartWizard('hideMessage');
            $('#wizard').smartWizard('setError',{
                stepnum:step,
                iserror:false
            });
        }
    }
    
    
    //validate step 5
    if(step == 5){
        if(validateStep5() == false ){
            isStepValid = false; 
            $('#wizard').smartWizard('showMessage','Please correct the errors in step'+step+ ' and click next.');
            $('#wizard').smartWizard('setError',{
                stepnum:step,
                iserror:true
            });         
        }else{
            $('#wizard').smartWizard('hideMessage');
            $('#wizard').smartWizard('setError',{
                stepnum:step,
                iserror:false
            });
        }
    }
    
    if(step == 6){
        if(validateStep6() == false ){
            isStepValid = false; 
            $('#wizard').smartWizard('showMessage','Please correct the errors in step'+step+ ' and click next.');
            $('#wizard').smartWizard('setError',{
                stepnum:step,
                iserror:true
            });         
        }else{
            $('#wizard').smartWizard('hideMessage');
            $('#wizard').smartWizard('setError',{
                stepnum:step,
                iserror:false
            });
        }
    }
        if(step == 7){
          if(validateStep7() == false ){
            isStepValid = false; 
            $('#wizard').smartWizard('showMessage','Please correct the errors in step'+step+ ' and click next.');
            $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
          }else{
            $('#wizard').smartWizard('hideMessage');
            $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
          }
        }
        
        if(step == 8){
          if(validateStep8() == false ){
            isStepValid = false; 
            $('#wizard').smartWizard('showMessage','Please correct the errors in step'+step+ ' and click next.');
            $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
          }else{
            $('#wizard').smartWizard('hideMessage');
            $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
          }
        }
        
        if(step == 9){
          if(validateStep9() == false ){
            isStepValid = false; 
            $('#wizard').smartWizard('showMessage','Please correct the errors in step'+step+ ' and click next.');
            $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
          }else{
            $('#wizard').smartWizard('hideMessage');
            $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
          }
        }
        
        if(step == 10){
          if(validateStep10() == false ){
            isStepValid = false; 
            $('#wizard').smartWizard('showMessage','Please correct the errors in step'+step+ ' and click next.');
            $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
          }else{
            $('#wizard').smartWizard('hideMessage');
            $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
          }
        }
        if(step == 11){
          if(validateStep11() == false ){
            isStepValid = false; 
            $('#wizard').smartWizard('showMessage','Please correct the errors in step'+step+ ' and click next.');
            $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
          }else{
            $('#wizard').smartWizard('hideMessage');
            $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
          }
        }
        if(step == 12){
          if(validateStep12() == false ){
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
    
    var un = $('#ancno').val();
     
    var today = new Date();
   
var day = today.getDate();
var month = today.getMonth()+1; //January is 0!
var year = today.getFullYear();

var todaysdate=year+"-"+month+"-"+day;

   
    var lmp=new Date($('#lmp').val());
    var edd=new Date($('#edd').val());
    todaysdate=new Date(todaysdate);
  //  alert(edd+" vs "+lmp);
    
    
    
   if($('#allfields tr').length==1){
$('#msg_ancno').html('<b><font color=\"red\">No data!</font></b>').show();

isValid=false;

}

else if(!un && un.length <= 0){
        isValid = false;
        $('#msg_ancno').html('<b><font color=\"red\">Please enter an ANCNO</font></b>').show();
    // alert("anc needed");
       
    }

else if(lmp>todaysdate && todaysdate!="" && lmp!=""){
        isValid = false;
        alert("LMP date should be less than or equal to today");
       $("#lmp").css("border-color","#ff0000");
       $("#lmp").slideToggle("slow",function() {});
       $("#lmp").slideToggle("slow",function() {}); 
       $("#lmp").focus();
    // alert("anc needed");
       
    }
else if(lmp==edd&&lmp!=""&&edd!=""){
        isValid = false;
        alert("LMP date cannot be equal to the Expected Delivery Date ");
        
       $("#lmp").css("border-color","#ff0000");
       $("#lmp").slideToggle("slow",function() {});
       $("#lmp").slideToggle("slow",function() {}); 
       $("#edd").css("border-color","#ff0000");
       $("#edd").slideToggle("slow",function() {});
       $("#edd").slideToggle("slow",function() {}); 
       $("#edd").focus();
  // alert("error");
       
    }
    
    else{
        $('#msg_ancno').html('').hide();
    }
    return isValid;
}


 //=====================validate step 2
    function validateStep2(){
    var isValid = true; 
    var old_checked=true;
    var allrows = $('#no_of_rows').val();
    var alloldrows = $('#no_of_old_rows').val();
    //alert(allrows);
   for(var p=1;p<=parseInt(alloldrows);p++){
       
   
   if(validate_prev_preg_old(p)==false){
       //alert("flase");
       old_checked=false;
    isValid=false;
    break;
   }    
       else{
        isValid=true;
        //alert("true true");
        old_checked=true;   
       }
       
   }
 //new rows  
   //check the new rows if only old rows have been checked..
   if(old_checked==true){
   for(var a=1;a<=parseInt(allrows);a++){
       
   
   if(validate_prev_preg(a)==false){
       
    isValid=false;  
    break;
   }    
       else{
        isValid=true; 
          
       }
       
   }
    } 
    return isValid;
}
    
 //==================validate step 3   
  function validateStep3(){
        
       var isValid = true;     
     var y = parseInt(document.getElementById("pregnancy_old_rows").value); 
   // alert(y);
     var a;
     var x;
       var numbers = /^\d+(?:\.\d+)?$/;  
     for(a=1;a<=y;a++){
           x= document.getElementById("weight"+a);
//      alert("a"+x.value); 
if(x.value!="" && x.value!=null){
       if(!x.value.match(numbers)){
            alert('Please input numeric characters only');  
                $("#weight"+a).css("border-color","#ff0000");
                $("#weight"+a).slideToggle("slow",function() {});
                $("#weight"+a).slideToggle("slow",function() {});   
                $("#weight"+a).focus();
           
      return false; 
      
       }
     }
      
         

         
         if(document.getElementById("date"+a).value==""){
             
             alert("Please enter a Date");
              $("#date"+a).css("border-color","#ff0000");
                $("#date"+a).slideToggle("slow",function() {});
                $("#date"+a).slideToggle("slow",function() {});   
                $("#date"+a).focus();
                return false;
         }
         if(document.getElementById("NextVisit"+a).value==""){
             
             alert("Please enter the next Visit Date");
              $("#NextVisit"+a).css("border-color","#ff0000");
                $("#NextVisit"+a).slideToggle("slow",function() {});
                $("#NextVisit"+a).slideToggle("slow",function() {});   
                $("#NextVisit"+a).focus();
                return false;
         }
 
             
             
         
         
        if(document.getElementById("date"+a).value!="" && document.getElementById("NextVisit"+a).value!=""){
         if(document.getElementById("date"+a).value > document.getElementById("NextVisit"+a).value){
         alert("The Next Visit Date should be greater than the current date");
        
         $("#NextVisit"+a).css("border-color","#ff0000");
                $("#NextVisit"+a).slideToggle("slow",function() {});
                $("#NextVisit"+a).slideToggle("slow",function() {});   
                $("#NextVisit"+a).focus();
         
         
return false;   
             
         }
         }
        
     }    
     
      var k = parseInt(document.getElementById("pregnancy").value); 
//      alert(k);
      for(var b=1;b<=k;b++){
        
         if(document.getElementById("new_NextVisit"+b).value !=""&& document.getElementById("new_date"+b).value!=""){
         if(document.getElementById("new_NextVisit"+b).value < document.getElementById("new_date"+b).value){
         alert("The Dates for the next visit should be greater than the current date");
return false;   
               $("#new_NextVisit"+b).css("border-color","#ff0000");
                $("#new_NextVisit"+b).slideToggle("slow",function() {});
                $("#new_NextVisit"+b).slideToggle("slow",function() {});   
                $("#new_NextVisit"+b).focus();
         }
         
      }
       var numbers1 = /^\d+(?:\.\d+)?$/;  
      var t;
      var z;
        t= document.getElementById("new_weight"+b);
//      alert("a"+x.value); 
if(t.value!="" && t.value!=null ){
       if(!t.value.match(numbers1)){
            alert('Please input numeric characters only');  
                $("#new_weight"+b).css("border-color","#ff0000");
                $("#new_weight"+b).slideToggle("slow",function() {});
                $("#new_weight"+b).slideToggle("slow",function() {});   
                $("#new_weight"+b).focus();
           
      return false; 
      
       }
     } 
      
      
      
      
      }
    
      return isValid;
    }


   
 //==================validate step 4   
function validateStep4(){
        
    var isValid = true;     
   var old_checked=true;
   var ps_allrows = $('#ps_no_of_rows').val();
    var ps_alloldrows = $('#ps_no_of_old_rows').val();
  
  //call function here
  //
  //old columns
  for(var b=1;b<=parseInt(ps_alloldrows);b++){    
        if(validate_dateold_4(b)==false){
       
            isValid=false; 
            old_checked=false;
            break;
        }    
        else{
            isValid=true; 
          old_checked=true;
        }
       
    }
      
  //
  //
  //
  //new rows  
if(old_checked==true){
   for(var a=1;a<=parseInt(ps_allrows);a++){    
        if(validate_date_4(a)==false){
       
            isValid=false; 
           
            break;
        }    
        else{
            isValid=true; 
          
        }
       
    }
    
    //validate select box to ensure they dont resemble each other
    for(var s=parseInt(ps_alloldrows);s<parseInt(ps_allrows);s++){
        
      for(var t=(parseInt(ps_alloldrows)+parseInt(1));t<=parseInt(ps_allrows);t++){
        
      if(($('#prevention'+t).val()!=""&&$('#prevention'+t).val()!=null)||($('#prevention'+s).val()!=""&&$('#prevention'+s).val()!=null)){
          
        if($('#prevention'+t).val()==$('#prevention'+s).val()&&s!=t&&isValid==true){    
            alert("You can't select the same prevention value in two different rows. change One");
            $('#prevention'+s).css("border-color","#ff0000");
            $('#prevention'+t).css("border-color","#ff0000");
            $('#prevention'+t).slideToggle( "slow", function() {});
            $('#prevention'+t).slideToggle( "slow", function() {});
            $('#prevention'+s).slideToggle( "slow", function() {});
            $('#prevention'+s).slideToggle( "slow", function() {});
             isValid=false;
            break;
        } 
        else{$('#prevention'+s).css("border-color","#000000");
            $('#prevention'+t).css("border-color","#000000");}
        
      }  
        
    }  
        
    }
    
    
}




    return isValid;
}


//===================validate step 5    
    function validateStep5(){
       
       var isValid = true;     
        
// var startDate = new Date($('#New_DateGiven1').val());
// var endDate = new Date($('#New_DateGiven2').val()); 
// var endDate1 = new Date($('#New_DateGiven3').val()); 
// var endDate2 = new Date($('#New_DateGiven4').val()); 
//
//if (startDate > endDate ||  endDate > endDate1 || endDate1>endDate2)
//
//{
//
// // Do something
//alert("The dates for subsequent rows shouldn't be lesser than previous rows");
//return false;
//
//}
//

var t = document.getElementById("ironfolate_old_rows").value;
for(q=1;q<=t;q++){
    
         var x;
          var b;
          if(document.getElementById("DateGiven"+q)!="" && document.getElementById("DateGiven"+(q+parseInt(1)))!=""){
          x= document.getElementById("DateGiven"+q);
          b=document.getElementById("DateGiven"+(q+parseInt(1)));
      }
         
     if(x!=null && b!=null){
          
      if(x.value!="" && b.value!=""){
      if(x.value > b.value) {
    
    alert("The dates for subsequent rows shouldn't be lesser than previous rows");
                $("#DateGiven"+(q+parseInt(1))).css("border-color","#ff0000");
                $("#DateGiven"+(q+parseInt(1))).slideToggle("slow",function() {});
                $("#DateGiven"+(q+parseInt(1))).slideToggle("slow",function() {});   
                $("#DateGiven"+(q+parseInt(1))).focus();
    return false;
      } 
      }
}
var y;
var j;
 if(document.getElementById("New_DateGiven"+q)!="" && document.getElementById("New_DateGiven"+(q+parseInt(1)))!=""){
         y= document.getElementById("New_DateGiven"+q);
          j=document.getElementById("New_DateGiven"+(q+parseInt(1)));
      }

 if(y!=null && j!=null){
          
  if(y.value!="" && j.value!=""){    
      if(y.value > j.value) {
    
    alert("The dates for subsequent rows shouldn't be lesser than previous rows");
      $("#New_DateGiven"+(q+parseInt(1))).css("border-color","#ff0000");
                $("#New_DateGiven"+(q+parseInt(1))).slideToggle("slow",function() {});
                $("#New_DateGiven"+(q+parseInt(1))).slideToggle("slow",function() {});   
                $("#New_DateGiven"+(q+parseInt(1))).focus();
    return false;
      } 
  }
}


}
// var firstDate = new Date($('#DateGiven1').val());
//// alert(firstDate);
// var secondDate = new Date($('#DateGiven2').val());
////alert(secondDate); 
// var thirdDate = new Date($('#DateGiven3').val());
// var fourthDate = new Date($('#DateGiven4').val());
//   if (firstDate > secondDate ||  secondDate > thirdDate || thirdDate >fourthDate){
//       
//    alert("The dates for subsequent rows shouldn't be lesser than previous rows");
//    $("#new_NextVisit"+a).css("border-color","#ff0000");
//                $("#new_NextVisit"+a).slideToggle("slow",function() {});
//                $("#new_NextVisit"+a).slideToggle("slow",function() {});   
//                $("#new_NextVisit"+a).focus();
//return false;  
//   }
//    
    

       return isValid;
    }
//================validate step 7=========================================================================
    function validateStep7(){
       
       var isValid = true;     
        
        
        var u= document.getElementById("PostNatal").value;
        for(var g=1;g<=u;g++){
            
         var m;
         var n;
 if(document.getElementById("New_VisitMum"+g)!="" && document.getElementById("New_VisitMum"+(g+parseInt(1)))!=""){
          m= document.getElementById("New_VisitMum"+g);
          n=document.getElementById("New_VisitMum"+(g+parseInt(1)));
      }

 if(m!=null && n!=null){
          
      if(m.value!="" && n.value!=""){

      if(m.value > n.value) {
    
    alert("The dates for subsequent rows shouldn't be lesser than previous rows");
      $("#New_VisitMum"+(g+parseInt(1))).css("border-color","#ff0000");
                $("#New_VisitMum"+(g+parseInt(1))).slideToggle("slow",function() {});
                $("#New_VisitMum"+(g+parseInt(1))).slideToggle("slow",function() {});   
                $("#New_VisitMum"+(g+parseInt(1))).focus();
    return false;
      } 
  }
}  
        }
        
        var h= document.getElementById("PostNatal_old_rows").value;
        for(var w=1;w<=h;w++){
            
          var k;
         var l;
 if(document.getElementById("VisitMum"+w)!="" && document.getElementById("VisitMum"+(w+parseInt(1)))!=""){
          k= document.getElementById("VisitMum"+w);
          l=document.getElementById("VisitMum"+(w+parseInt(1)));
      }

 if(k!=null && l!=null){
          
     if(k.value!="" && l.value!=""){ 
      if(k.value > l.value) {
    
    alert("The dates for subsequent rows shouldn't be lesser than previous rows");
      $("#VisitMum"+(w+parseInt(1))).css("border-color","#ff0000");
                $("#VisitMum"+(w+parseInt(1))).slideToggle("slow",function() {});
                $("#VisitMum"+(w+parseInt(1))).slideToggle("slow",function() {});   
                $("#VisitMum"+(w+parseInt(1))).focus();
    return false;
      } 
  }
}  
            
        }

 
// var Date1= new Date($('#VisitMum1').val());
// var Date2= new Date($('#VisitMum2').val()); 
// var Date3 = new Date($('#VisitMum3').val()); 
// 
//
//
//if (Date1 > Date2 ||  Date2 > Date3 )
//
//{
//
// // Do something
//alert("The dates for subsequent columns should be greater than previous columns");
//return false;
//
//}
       return isValid;
    }
//================validate step 7=====================================================================================
function validateStep6(){
       
    var isValid = true;     
        
    var deliverymode = $('#delivery_mode').val();
    var deliverydate = $('#del_dp').val(); 
    var apgar10 = $('#apgar_score_10').val(); 
    var rescus  =  $('#rescuscitation').val(); 
    var deliveryplace = $('#delivery_place').val(); 
    var conductor = $('#conducted_by').val(); 


 var today = new Date();
   
var day = today.getDate();
var month = today.getMonth()+1; //January is 0!
var year = today.getFullYear();

var todate=year+"-"+month+"-"+day;

   // todate=new Date(todate);

//alert(todate+" vs "+deliverydate);
    if (deliverymode !=""||  deliverydate !=""||apgar10!=""||rescus!=""||deliveryplace!=""||conductor!="")

    {
 
for(var x=0;x<1;x++){
 if(deliverymode==""){
             
             alert("select delivery mode");
            
            $('#delivery_mode').css("border-color","#ff0000");
            $('#delivery_mode').slideToggle( "slow", function() {});
            $('#delivery_mode').slideToggle( "slow", function() {});
              $('#delivery_mode').focus();
               isValid= false; 
                break;
            }
            
 else if(deliverydate==""&&deliverymode!=null){
             
             alert("enter delivery date");
            
            $('#del_dp').css("border-color","#ff0000");
            $('#del_dp').slideToggle( "slow", function() {});
            $('#del_dp').slideToggle( "slow", function() {});
            $('#del_dp').focus();
               isValid= false; 
                break;
            }
            
 else if(deliverydate!=""&&deliverydate>todate){
             
             alert("Delivery date cannot be set to future days.");
            
            $('#del_dp').css("border-color","#ff0000");
            $('#del_dp').slideToggle( "slow", function() {});
            $('#del_dp').slideToggle( "slow", function() {});
            $('#del_dp').focus();
               isValid= false; 
                break;
            }
            
      
            
//  else if(apgar10==""){
//             
//             alert("enter apgar score at 10 min");
//            
//            $('#apgar_score_10').css("border-color","#ff0000");
//            $('#apgar_score_10').slideToggle( "slow", function() {});
//            $('#apgar_score_10').slideToggle( "slow", function() {});
//            $('#apgar_score_10').focus();
//               isValid= false; 
//                break;
//            }
            
 else if(rescus==""){
             
             alert("Specify whether rescuscitation was done");
            
            $('#rescuscitation').css("border-color","#ff0000");
            $('#rescuscitation').slideToggle( "slow", function() {});
            $('#rescuscitation').slideToggle( "slow", function() {});
            $('#rescuscitation').focus();
               isValid= false; 
                break;
            }
            
  else if(deliveryplace==""){
             
             alert("select delivery place");
            
            $('#delivery_place').css("border-color","#ff0000");
            $('#delivery_place').slideToggle( "slow", function() {});
            $('#delivery_place').slideToggle( "slow", function() {});
            $('#delivery_place').focus();
               isValid= false;
                break;
            }
            
      
            
  else if(conductor==""){
             
             alert("who conducted the delivery?");
            
            $('#conducted_by').css("border-color","#ff0000");
            $('#conducted_by').slideToggle( "slow", function() {});
            $('#conducted_by').slideToggle( "slow", function() {});
            $('#conducted_by').focus();
           
               isValid= false; 
               break; 
            }
            else{isValid=true;}
         
}


     
    }
    
    //validate the old rows
    var olddeliverymode = $('#old_delivery_mode').val();
    var olddeliverydate = $('#del_dp1').val(); 
    var oldapgar10 = $('#old_apgar_score_10').val(); 
    var oldrescus  =  $('#old_rescuscitation').val(); 
    var olddeliveryplace = $('#old_delivery_place').val(); 
    var oldconductor = $('#old_conducted_by').val(); 

if($('#old_delivery_mode').val()!=null||$('#old_apgar_score_10').val()!=null){

    if (olddeliverymode !=""||  olddeliverydate !=""||oldapgar10!=""||oldrescus!=""||olddeliveryplace!=""||oldconductor!="")

    {
 
for(var g=0;g<1;g++){
 if(olddeliverymode==""){
             
             alert("select delivery mode");
            
            $('#old_delivery_mode').css("border-color","#ff0000");
            $('#old_delivery_mode').slideToggle( "slow", function() {});
            $('#old_delivery_mode').slideToggle( "slow", function() {});
            $('#old_delivery_mode').focus();
               isValid= false; 
                break;
            }
            
 else if(olddeliverydate==""){
             
             alert("enter delivery date");
            
            $('#del_dp1').css("border-color","#ff0000");
            $('#del_dp1').slideToggle( "slow", function() {});
            $('#del_dp1').slideToggle( "slow", function() {});
            $('#del_dp1').focus();
               isValid= false; 
                break;
            }
            
      
            
 else if(oldapgar10==""){
             
             alert("enter apgar score at 10 min");
            
            $('#old_apgar_score_10').css("border-color","#ff0000");
            $('#old_apgar_score_10').slideToggle( "slow", function() {});
            $('#old_apgar_score_10').slideToggle( "slow", function() {});
            $('#old_apgar_score_10').focus();
               isValid= false; 
                break;
            }
            
 else if(oldrescus==""){
             
             alert("Specify whether rescuscitation was done");
            
            $('#old_rescuscitation').css("border-color","#ff0000");
            $('#old_rescuscitation').slideToggle( "slow", function() {});
            $('#old_rescuscitation').slideToggle( "slow", function() {});
            $('#old_rescuscitation').focus();
               isValid= false; 
                break;
            }
            
 else if(olddeliveryplace==""){
             
             alert("select delivery place");
            
            $('#old_delivery_place').css("border-color","#ff0000");
            $('#old_delivery_place').slideToggle( "slow", function() {});
            $('#old_delivery_place').slideToggle( "slow", function() {});
            $('#old_delivery_place').focus();
               isValid= false;
                break;
            }
            
      
            
  else if(oldconductor==""){
             
             alert("who conducted the delivery?");
            
            $('#old_conducted_by').css("border-color","#ff0000");
            $('#old_conducted_by').slideToggle( "slow", function() {});
            $('#old_conducted_by').slideToggle( "slow", function() {});
            $('#old_conducted_by').focus();
           
               isValid= false; 
               break; 
            }
            else{isValid=true;}
}
     
    }
}
    
    
    
    
    
    
    return isValid;
}
//==============================================================================================================

//================validate step 8=========================================================================
function validateStep8(){
       
    var isValid = true;     
        
//    var startDate = new Date($('#New_VisitMum1').val());
//    var endDate = new Date($('#New_VisitMum2').val()); 
//    var endDate1 = new Date($('#New_VisitMum3').val()); 
// 
//
//    if (startDate > endDate ||  endDate > endDate1 )
//
//    {
//
//        // Do something
//        alert("The dates for subsequent columns should be greater than previous columns");
//        return false;
//
//    }
    return isValid;
}


//================validate step 9=========================================================================
function validateStep9(){
       
       var isValid = true;     
    var f= document.getElementById("CervicalCancer").value;  
//    alert(f);
    for(var p=1;p<=f;p++){
       
 
 
 var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; //January is 0!

var yyyy = today.getFullYear();
if(dd<10)
{dd='0'+dd} 
if(mm<10)
{mm='0'+mm} 
today = yyyy+'-'+mm+'-'+dd;

var curDate= new Date(today);
var startDate=new Date($('#New_CervixDate'+p).val());
if(startDate > curDate){
  alert("The Visit Date shouldn't be greater than the current date");  
                $("#New_CervixDate"+p).css("border-color","#ff0000");
                $("#New_CervixDate"+p).slideToggle("slow",function() {});
                $("#New_CervixDate"+p).slideToggle("slow",function() {});   
                $("#New_CervixDate"+p).focus();
   return false; 
}
   

} 

 return isValid;
    }

//================validate step 8=========================================================================
function validateStep10(){
       
    var isValid = true;
    var oldrowschecked=true;
     var vit_newrows = $('#vit_new_no_of_rows').val();
    var vit_oldrows = $('#vit_old_no_of_rows').val();
      
      var newid=parseInt(1);
    for(var a=1;a<=parseInt(vit_oldrows);a++){
        
      var b=parseInt(a)+parseInt(1);
     
      for(var d=b;d<=parseInt(vit_oldrows);d++){
          
         if(validate_vitamin(a,d,newid)==false){
             isValid=false;
             oldrowschecked=false;
            
             break;
         }
          else{
              oldrowschecked=true;
              isValid=true;

          }
         if(newid<parseInt(vit_oldrows)){newid++;}
      }//end of second for
      if(oldrowschecked==false){
           break;
      }
      
    }//end of outer for loop
    
    
    
    
    
    
    
    
    //====validate new values================================================
     if(oldrowschecked==true){
      var breakcalled=false;   
       var mnewid=parseInt(1);
        for(var c=1;c<=parseInt(vit_newrows);c++){
        
      var e=parseInt(c)+parseInt(1);
      
      for(var f=e;f<=parseInt(vit_newrows);f++){
          
         if(validate_new_vitamin(c,f,mnewid)==false){
             isValid=false;
           breakcalled=true; 
            
             break;
         }
          else{
              breakcalled=false; 
              isValid=true;

          }
         if(mnewid<(parseInt(vit_newrows))){mnewid++;}
      }//end of second for
     if(breakcalled==true){break;}
      
    }//end of outer for loop  
         
     }
    
    
    return isValid;
}

//================validate step 11=========================================================================
function validateStep11(){
       
   var isValid = true;
   var oldrowschecked=true;
     
    var dw_newrows = $('#dw_new_no_of_rows').val();
    var dw_oldrows = $('#dw_old_no_of_rows').val();  
      var dwnewid=parseInt(1);
    for(var a=1;a<=parseInt(dw_oldrows);a++){
        
      var b=parseInt(a)+parseInt(1);
     
      for(var d=1;d<=parseInt(dw_oldrows);d++){
          
          //alert(dwnewid);
         if(d>=b&&validate_deworming(a,d,dwnewid)==false){
             isValid=false;
             oldrowschecked=false;
            
             break;
         }
          else{
              oldrowschecked=true;
              isValid=true;

          }
        if(dwnewid<(parseInt(dw_oldrows))){dwnewid++;} 
      }//end of second for
      if(oldrowschecked==false){
           break;
      }
      
    }//end of outer for loop
    
  
    //====validate new values================================================
     if(oldrowschecked==true){
      var breakcalled=false;   
       var dwmnewid=parseInt(0);
        for(var c=1;c<=parseInt(dw_newrows);c++){
        
      var e=parseInt(c)+parseInt(1);
      
      for(var f=e;f<=parseInt(dw_newrows);f++){
           if(dwmnewid<parseInt(dw_newrows)){dwmnewid++;}
         if(validate_new_deworming(c,f,dwmnewid)==false){
             isValid=false;
           breakcalled=true; 
            
             break;
         }
          else{
              breakcalled=false; 
              isValid=true;

          }
        
      }//end of second for
     if(breakcalled==true){break;}
      
    }//end of outer for loop  
         
     }
    
    
    return isValid;
}

//================validate step 12=========================================================================
function validateStep12(){
       
    var isValid = true;     
        
 var oldrowschecked=false;
     
    var dna_newrows = $('#dna_new_no_of_rows').val();
    var dna_oldrows = $('#dna_old_no_of_rows').val();  
      var dwnewid=parseInt(1);
      //alert(dna_oldrows);
    for(var a=1;a<=parseInt(dna_oldrows);a++){
        
      var b=parseInt(a)+parseInt(1);
     
      for(var d=1;d<=parseInt(dna_oldrows);d++){
          
          //alert(dwnewid);
         if(d>=b&&validate_dna(a,d,dwnewid)==false){
             isValid=false;
             oldrowschecked=false;
            
             break;
         }
          else{
              oldrowschecked=true;
              isValid=true;

          }
        if(dwnewid<(parseInt(dna_oldrows))){dwnewid++;} 
      }//end of second for
      if(oldrowschecked==false){
           break;
      }
      
    }//end of outer for loop
    
  
    //====validate new values================================================
     if(oldrowschecked==true){
      var breakcalled=false;   
       var dwmnewid=parseInt(1);
        for(var c=1;c<=parseInt(dna_newrows);c++){
        
      var e=parseInt(c)+parseInt(1);
      
      for(var f=1;f<=parseInt(dna_newrows);f++){
           if(dwmnewid<parseInt(dna_newrows)){dwmnewid++;}
         if(f>=e&&validate_new_dna(c,f,dwmnewid)==false){
             isValid=false;
           breakcalled=true; 
            
             break;
         }
          else{
              breakcalled=false; 
              isValid=true;

          }
        
      }//end of second for
     if(breakcalled==true){break;}
      
    }//end of outer for loop  
         
     }
    
    
    return isValid;
}


//^d{4}-d{2}-d{4}$

function checkancno(){
    var redigit=/^\d{4}-d{2}-d{4}$/ //regular expression defining a 5 digit number
    if (document.myform.ancno.value.search(redigit)==-1){ //if match failed
        //alert("Please enter a valid ANC No")
        return false;
    }
    else{
        return true;
    }
}

  // functions zangu   


  function validate_prev_preg(id){
       
       var year;
       var deliv_place;
       var deliv_type
       
       
         year =document.getElementById("new_year"+id);
                 
        deliv_place=document.getElementById("new_place_of_delivery"+id);
        
         deliv_type=document.getElementById("new_Type_of_delivery"+id);
       
  
   //validate now
   
   
        if(year.value!=""||deliv_place.value!=""||deliv_type.value!=""){
            
            if(year.value==""){
             
             alert("Enter year");
            year.required=true;
         
            year.style="border-color:#ff0000;border-width:2px;";
                $('#new_year'+id).slideToggle( "slow", function() {});
            $('#new_year'+id).slideToggle( "slow", function() {});
               year.focus();
               return false; 
            }
            
            else if(year.value.length!=4){
             
             alert("Ensure your year is of format YYYY e.g. 2013");
            year.required=true;
         
            year.style="border-color:#ff0000;border-width:2px;";
                $('#new_year'+id).slideToggle( "slow", function() {});
            $('#new_year'+id).slideToggle( "slow", function() {});
               year.focus();
               return false; 
            }
            
              
           else if(deliv_place.value==""){
             
             alert("Enter delivery place");
             $('#new_place_of_delivery'+id).slideToggle( "slow", function() {});
            $('#new_place_of_delivery'+id).slideToggle( "slow", function() {});
             deliv_place.required=true;
               deliv_place.focus();
              return false; 
            }
            
//             else if(deliv_type.value==""){
//             
//             alert("choose delivery type");
//             
//            deliv_type.style="border-color:#ff0000;";
//            $('#new_Type_of_delivery'+id).slideToggle( "slow", function() {});
//            $('#new_Type_of_delivery'+id).slideToggle( "slow", function() {});
//               deliv_type.focus();
//              return false; 
//            }
            
            
          else{return true;} 
        } 
            else{return true;}
       
  }
    
  
     
     
  function validate_prev_preg_old(id){
       
       var oyear;
       var odeliv_place;
       var odeliv_type
       
       
         oyear =document.getElementById("year"+id);
                 
        odeliv_place=document.getElementById("place_of_delivery"+id);
        
         odeliv_type=document.getElementById("Type_of_delivery"+id);
       
  
   //validate now
   var ret=false;
   
        if(oyear.value!=""||odeliv_place.value!=""||odeliv_type.value!=""){
            
            
            
            if(oyear.value==""){
             
             alert("Enter year");
            oyear.required=true;
            
            oyear.style="border-color:#ff0000;border-width:2px;";
            $('#year'+id).slideToggle( "slow", function() {});
            $('#year'+id).slideToggle( "slow", function() {});
               oyear.focus();
               ret= false; 
            }
            
            
            else if(oyear.value.length!=4){
             
             alert("Ensure your year is of format YYYY e.g. 2013");
            oyear.required=true;
         
            oyear.style="border-color:#ff0000;border-width:2px;";
                $('#new_year'+id).slideToggle( "slow", function() {});
            $('#new_year'+id).slideToggle( "slow", function() {});
               oyear.focus();
               return false; 
            }
              
              
              
           else if(odeliv_place.value==""){
             
             alert("Enter delivery place");
             odeliv_place.style="border-color:#ff0000;border-width:2px;";
             $('#place_of_delivery'+id).slideToggle("slow", function() {});
            $('#place_of_delivery'+id).slideToggle("slow", function() {});
               odeliv_place.focus();
             ret= false; 
            }
            
//             else if(odeliv_type.value==""){
//             
//             alert("choose delivery type");
//            odeliv_type.style="border-color:#ff0000;";
//            $('#Type_of_delivery'+id).slideToggle("slow", function() {});
//            $('#Type_of_delivery'+id).slideToggle("slow", function() {});
//               odeliv_type.focus();
//              ret= false; 
//            }
            
            
          else{ret= true;} 
        } 
            else{ret= true;}
            
            return ret;
       
  }
    
 //=================================================================================== 
  function validate_date_4(id){
      var vld=false;
     
     var startDate = new Date($('#dpr1'+id).val());

    var endDate = new Date($('#dpr2'+id).val()); 

var prevservice=$('#prevention'+id).val();

    if (startDate >= endDate && startDate!="" && endDate!="")

    {
        // Do somethingdates for a previous visit cannot be greater than for a later visit
       alert(" 'Next visit date' value should be greater than the 'Date' value");$('#dpr2'+id).focus();
        vld= false;

    }
      
    else { 
        
        if(($('#dpr1'+id).val()!=""&&$('#dpr1'+id).val()!=null)||($('#dpr2'+id).val()!=""&&$('#dpr2'+id).val()!=null)||prevservice!=""){
      if($('#dpr1'+id).val()==""){
             
             alert("Enter date");
           $('#dpr1'+id).css("border-color","#ff0000");
            $('#dpr1'+id).slideToggle( "slow", function() {});
            $('#dpr1'+id).slideToggle( "slow", function() {});
              $('#dpr1'+id).focus();
               vld= false; 
            }   
        else if($('#dpr2'+id).val()==""){
             
             alert("Enter next visit date");
            
            $('#dpr2'+id).css("border-color","#ff0000");
            $('#dpr2'+id).slideToggle( "slow", function() {});
            $('#dpr2'+id).slideToggle( "slow", function() {});
              $('#dpr2'+id).focus();
               vld= false; 
            } 
        
        else if(prevservice==""){
            
            
             alert("Select prevention service");
            $('#prevention'+id).slideToggle( "slow", function() {});
            $('#prevention'+id).slideToggle( "slow", function() {});
            $('#prevention'+id).css("border-color","#ff0000");
              $('#prevention'+id).focus();
               vld= false; 
            
            
            
            
        }
        else{vld= true;}
        
        
    }
        else{vld= true;}
        
        
        //alert("else");
    }
    return vld;
      
  }   
 //=========================================================================================    
    
 //=================================================================================== 
  function validate_dateold_4(id ){
      var vld=false;
     
     var startDate = new Date($('#_dpr1'+id).val());

    var endDate = new Date($('#_dpr2'+id).val()); 
    
    var prevservice= $('#label'+id).val();

    if (startDate >= endDate && startDate!="" && endDate!="")

    {
        // Do somethingdates for a previous visit cannot be greater than for a later visit
        alert(" 'Next visit date' value should be greater than the 'Date' value");
        $('#_dpr2'+id).focus();
        $('#_dpr2'+id).css("border-color","#ff0000");
        $('#_dpr2'+id).slideToggle("slow",function() {});
        $('#_dpr2'+id).slideToggle("slow",function() {});
        vld= false;

    }
  
    else {
        
        
          if(startDate!=""||endDate!=""||prevservice!=""){
      if(startDate==""){
             
             alert("Enter date");
          
            $('#_dpr1'+id).css("border-color","#ff0000");
            $('#_dpr1'+id).slideToggle("slow",function() {});
            $('#_dpr1'+id).slideToggle("slow",function() {});
            
               $('#_dpr1'+id).focus();
                // alert("new end date");
               vld= false; 
            }   
        else if(endDate==""){
             
             alert("Enter next visit date");
            
             $('#_dpr2'+id).css("border-color","#ff0000");
             $('#_dpr2'+id).slideToggle("slow",function() {});
            $('#_dpr2'+id).slideToggle("slow",function() {});
               $('#_dpr2'+id).focus();
              // alert("old end date");
               vld= false; 
            } 
        else{vld= true;}
        
        
        
        
    }
        
        
        else{vld= true;}
        //alert("else");
    }
    return vld;
      
  }   
 //=========================================================================================    
//========================validate vitamina ===================================================
function validate_vitamin(id,id1,newid){

var ret=true;
 
 
 
  var today = new Date();
   
var day = today.getDate();
var month = today.getMonth()+1; //January is 0!
var year = today.getFullYear();

var todaysdat=year+"-"+month+"-"+day;

   
   
    todaysdat=new Date(todaysdat);
 
 
 
 
var startDate = new Date($('#_vit_dp'+id).val());
    var endDate = new Date($('#_vit_dp'+id1).val()); 
    var dosage = document.getElementById("dw_old_dosage"+newid); 
 //alert(startDate+"  "+todaysdat)

    if (startDate > endDate &&(startDate!=null&&endDate!=null))

    {
if($('#_vit_dp'+id)!=$('#_vit_dp'+id1)){
        // Do something
        alert("The dates for lower row should be greater than one for the upper row");
            $('#_vit_dp'+id).css("border-color","#ff0000");
            $('#_vit_dp'+id1).css("border-color","#ff0000");
            $('#_vit_dp'+id).slideToggle("slow",function() {});
            $('#_vit_dp'+id).slideToggle("slow",function() {});
            $('#_vit_dp'+id1).slideToggle("slow",function() {});
            $('#_vit_dp'+id1).slideToggle("slow",function() {});
            $('#_vit_dp'+id1).focus();
        ret=false;
    }
    }
    
    
    else{
       
        //alert(newid);
       if(dosage.checked==true&&($('#_vit_dp'+newid).val()=="")){
         
            alert("enter next visit date");
            
     
                $('#_vit_dp'+newid).css("border-color","#ff0000");
                $('#_vit_dp'+newid).slideToggle("slow",function() {});
                $('#_vit_dp'+newid).slideToggle("slow",function() {});   
                $('#_vit_dp'+newid).focus();
           
           ret=false;
       } 
        
    else  if (startDate == todaysdat && (startDate!=""&&startDate!=null) )

    {

        // Do something
        alert("The Next Visit date should be greater than today");
            $('#_vit_dp'+id).css("border-color","#ff0000");
           
            $('#_vit_dp'+id).slideToggle("slow",function() {});
            $('#_vit_dp'+id).slideToggle("slow",function() {});
           
            $('#_vit_dp'+id).focus();
        ret=false;
    
    }
       else{ret=true;}
    
}
   return ret; 

}    

//======================validate new  vitamina==================
function validate_new_vitamin(id,id1,mnewid){

var ret=true;



var today = new Date();
   
var day = today.getDate();
var month = today.getMonth()+1; //January is 0!
var year = today.getFullYear();

var todaydat=year+"-"+month+"-"+day;

   
   
    todaydat=new Date(todaydat);
 





var startDate = new Date($('#vit_dp'+id).val());
    var endDate = new Date($('#vit_dp'+id1).val()); 
   
   
    var dosage1 = document.getElementById("dw_dosage"+mnewid); 
 
//alert(todaydat+" "+startDate)

    if (startDate > endDate &&(startDate!=null&&endDate!=null))

    {
if($('#vit_dp'+id)!=$('#vit_dp'+id1)){
        // Do something
        alert("The dates for lower row should be greater than one for the upper row");
            $('#vit_dp'+id).css("border-color","#ff0000");
            $('#vit_dp'+id1).css("border-color","#ff0000");
            $('#vit_dp'+id).slideToggle("slow",function() {});
            $('#vit_dp'+id).slideToggle("slow",function() {});
            $('#vit_dp'+id1).slideToggle("slow",function() {});
            $('#vit_dp'+id1).slideToggle("slow",function() {});
            $('#vit_dp'+id1).focus();
        ret=false;
}

    }
    else{
        
       if(dosage1.checked==true&&($('#vit_dp'+mnewid).val()=="")){
         
            alert("enter next visit date");
           
                       
                $('#vit_dp'+mnewid).focus();
                $('#vit_dp'+mnewid).css("border-color","#ff0000");
                $('#vit_dp'+mnewid).slideToggle("slow",function() {});
                $('#vit_dp'+mnewid).slideToggle("slow",function() {});
               
               
            
           ret=false;
       }
       
       
       else  if (startDate <= todaydat && (startDate!=""&&startDate!=null) )

    {

        // Do something
        alert("The Next Visit date should be greater than today");
            $('#_vit_dp'+id).css("border-color","#ff0000");
           
            $('#_vit_dp'+id).slideToggle("slow",function() {});
            $('#_vit_dp'+id).slideToggle("slow",function() {});
           
            $('#_vit_dp'+id).focus();
        ret=false;
    
    }
    
        
       else{ret=true;}
    
}
   return ret; 

} 


//==========================validate deworming ====
//========================validate deworming ===================================================
function validate_deworming(id,id1,newid){

var ret=true;
 
var startDate = new Date($('#_dw_dp'+id).val());
    var endDate = new Date($('#_dw_dp'+id1).val()); 
    var dose = document.getElementById("dew_old_dosage"+newid).value; 
    var drug = document.getElementById("dw_old_drug"+newid).value; 
// alert(newid);

    if (startDate > endDate &&(startDate!=null&&endDate!=null))

    {
if($('#_dw_dp'+id)!=$('#_dw_dp'+id1)){
        // Do something
        alert("The dates for lower row should be greater than one for the upper row");
            $('#_dw_dp'+id).css("border-color","#ff0000");
            $('#_dw_dp'+id1).css("border-color","#ff0000");
            $('#_dw_dp'+id).slideToggle("slow",function() {});
            $('#_dw_dp'+id).slideToggle("slow",function() {});
            $('#_dw_dp'+id1).slideToggle("slow",function() {});
            $('#_dw_dp'+id1).slideToggle("slow",function() {});
            $('#_dw_dp'+id1).focus();
        ret=false;
    }
    }
    else{
       
        //alert(newid);
       if(dose!=""||$('#_dw_dp'+newid).val()!=""||drug!=""){
           
         if($('#_dw_dp'+newid).val()==""){
            alert("enter next visit date");
         
                $('#_dw_dp'+newid).css("border-color","#ff0000");
                $('#_dw_dp'+newid).slideToggle("slow",function() {});
                $('#_dw_dp'+newid).slideToggle("slow",function() {});   
                $('#_dw_dp'+newid).focus();
           
           ret=false;}
       else if(dose==""){
            alert("enter dose");
         
                $('#dew_old_dosage'+newid).css("border-color","#ff0000");
                $('#dew_old_dosage'+newid).slideToggle("slow",function() {});
                $('#dew_old_dosage'+newid).slideToggle("slow",function() {});   
                $('#dew_old_dosage'+newid).focus();
           
           ret=false;}
       
         else if(drug==""){
            alert("enter drugs value");
         
                $('#dw_old_drug'+newid).css("border-color","#ff0000");
                $('#dw_old_drug'+newid).slideToggle("slow",function() {});
                $('#dw_old_drug'+newid).slideToggle("slow",function() {});   
                $('#dw_old_drug'+newid).focus();
           
           ret=false;}
       else{ret=true;}
       
       } 
        
       else{ret=true;}
    
}
   return ret; 

}    

//======================validate new  deworming==================
function validate_new_deworming(id,id1,mnewid){

var ret=true;

    var startDate = new Date($('#dw_dp'+id).val());
    var endDate = new Date($('#dw_dp'+id1).val()); 
    var dose = document.getElementById("dew_dosage"+mnewid); 
    var drug = document.getElementById("dw_drug"+mnewid); 
// alert(mnewid);

    if (startDate > endDate &&(startDate!=null&&endDate!=null))

    {
if($('#dw_dp'+id)!=$('#dw_dp'+id1)){
        // Do something
        alert("The dates for lower row should be greater than one for the upper row");
            $('#dw_dp'+id).css("border-color","#ff0000");
            $('#dw_dp'+id1).css("border-color","#ff0000");
            $('#dw_dp'+id).slideToggle("slow",function() {});
            $('#dw_dp'+id).slideToggle("slow",function() {});
            $('#dw_dp'+id1).slideToggle("slow",function() {});
            $('#dw_dp'+id1).slideToggle("slow",function() {});
            $('#dw_dp'+id1).focus();
        ret=false;
}

    }
    else{
        
        if(dose.value!=""||$('#dw_dp'+mnewid).val()!=""||drug.value!=""){
           
         if($('#dw_dp'+mnewid).val()==""){
            alert("enter next visit date");
         
                $('#dw_dp'+mnewid).css("border-color","#ff0000");
                $('#dw_dp'+mnewid).slideToggle("slow",function() {});
                $('#dw_dp'+mnewid).slideToggle("slow",function() {});   
                $('#dw_dp'+mnewid).focus();
           
           ret=false;}
       else if(dose.value==""){
            alert("enter dose");
         
                $('#dew_dosage'+mnewid).css("border-color","#ff0000");
                $('#dew_dosage'+mnewid).slideToggle("slow",function() {});
                $('#dew_dosage'+mnewid).slideToggle("slow",function() {});   
                $('#dew_dosage'+mnewid).focus();
           
           ret=false;}
       
         else if(drug.value==""){
            alert("enter drugs value");
         
                $('#dw_drug'+mnewid).css("border-color","#ff0000");
                $('#dw_drug'+mnewid).slideToggle("slow",function() {});
                $('#dw_drug'+mnewid).slideToggle("slow",function() {});   
                $('#dw_drug'+mnewid).focus();
           
           ret=false;}
       else{ret=true;}
       
       } 
    else{ret=true;}
}
   return ret; 

} 


//====================================validate pcr dna=============================================================

function validate_dna(id,id1,newid){

    var ret=true;
    var startDate = new Date($('#dna_dp'+id).val());
    var endDate = new Date($('#dna_dp'+id1).val()); 
    var results = document.getElementById("dna_old_results"+newid).value; 
  
    // alert(newid);
    if (startDate > endDate &&(startDate!=null&&endDate!=null))

    {
if($('#dna_dp'+id)!=$('#dna_dp'+id1)){
        // Do something
        alert("The dates for lower row should be greater than one for the upper row");
            $('#dna_dp'+id).css("border-color","#ff0000");
            $('#dna_dp'+id1).css("border-color","#ff0000");
            $('#dna_dp'+id).slideToggle("slow",function() {});
            $('#dna_dp'+id).slideToggle("slow",function() {});
            $('#dna_dp'+id1).slideToggle("slow",function() {});
            $('#dna_dp'+id1).slideToggle("slow",function() {});
            $('#dna_dp'+id1).focus();
        ret=false;
    }
    }
    else{
       
        //alert(newid);
       if(results!=""||$('#dna_dp'+newid).val()!=""){
           
         if($('#dna_dp'+newid).val()==""){
            alert("enter next visit date");
         
                $('#dna_dp'+newid).css("border-color","#ff0000");
                $('#dna_dp'+newid).slideToggle("slow",function() {});
                $('#dna_dp'+newid).slideToggle("slow",function() {});   
                $('#dna_dp'+newid).focus();
           
           ret=false;}
       else if(results==""){
            alert("enter results");
         
                $('#dna_old_results'+newid).css("border-color","#ff0000");
                $('#dew_old_ddna_old_resultsosage'+newid).slideToggle("slow",function() {});
                $('#dew_old_ddna_old_resultsosage'+newid).slideToggle("slow",function() {});
                $('#dna_old_results'+newid).slideToggle("slow",function() {});   
                $('#dna_old_results'+newid).focus();
           
           ret=false;}
       
        
       else{ret=true;}
       
       } 
        
       else{ret=true;}
    
}
   return ret; 

}    

//======================validate new  dna==================
function validate_new_dna(id,id1,mnewid){

    var ret=true;
    var startDate = new Date($('#dnadp'+id).val());
    var endDate = new Date($('#dnadp'+id1).val()); 
    var results1 = document.getElementById("dna_results"+mnewid).value; 
   //alert(results1);

    if (startDate > endDate &&(startDate!=null&&endDate!=null))

    {
if($('#dnadp'+id)!=$('#dnadp'+id1)){
        // Do something
        alert("The dates for lower row should be greater than one for the upper row");
            $('#dnadp'+id).css("border-color","#ff0000");
            $('#dnadp'+id1).css("border-color","#ff0000");
            $('#dnadp'+id).slideToggle("slow",function() {});
            $('#dnadp'+id).slideToggle("slow",function() {});
            $('#dnadp'+id1).slideToggle("slow",function() {});
            $('#dnadp'+id1).slideToggle("slow",function() {});
            $('#dnadp'+id1).focus();
        ret=false;
}

    }
    else{
        
        if(results1!=""||$('#dnadp'+mnewid).val()!=""){
           
         if($('#dnadp'+mnewid).val()==""){
            alert("enter next_visit date");
         
                $('#dnadp'+mnewid).css("border-color","#ff0000");
                $('#dnadp'+mnewid).slideToggle("slow",function() {});
                $('#dnadp'+mnewid).slideToggle("slow",function() {});   
                $('#dnadp'+mnewid).focus();
           
           ret=false;}
       else if(results1.value==""){
            alert("enter results");
         
                $('#dna_results'+mnewid).css("border-color","#ff0000");
                $('#dna_results'+mnewid).slideToggle("slow",function() {});
                $('#dna_results'+mnewid).slideToggle("slow",function() {});   
                $('#dna_results'+mnewid).focus();
           
           ret=false;}
       
        
       else{ret=true;}
       
       } 
    else{ret=true;}
}
   return ret; 

} 

function checkNum()  
   { 
       
          var x= document.getElementById("new_weight1");
           
     
      var numbers = /^[0-9]+$/;  
//       var x= document.getElementById("new_weight1");
       if(x.value!=""){
      if(x.value.match(numbers))  
      {  
     // alert('aaa....');  
//      document.myform.new_weight1.focus();  
      return true;  
      }  
      else  
      {  
      alert('Please input numeric characters only');  
//      document.myform.new_weight1.focus();  
      return false;  
      }} 
  }
  
function checkNegative(){
    
    //no of new rows
     var w= document.getElementById("CervicalCancer").value;
    // alert(w);
     for(var p=1;p<=w;p++){
         if(document.getElementById("New_Tests"+p)!=null){
        var j = document.getElementById("New_Tests"+p);
        var k = j.options[j.selectedIndex].value;
        
        
//       alert("k___"+k);
       
       
         if(k=="VIA Negative") {
          document.getElementById("New_Referral"+p).disabled = true;
           document.getElementById("New_Referral"+p).value = "";
           document.getElementById("New_Referral"+p).style.backgroundColor='#E0E0E0'; //whatever color or hex.
             
      } 
        else if(k=="VILI Negative") {
          document.getElementById("New_Referral"+p).disabled = true;
          document.getElementById("New_Referral"+p).value = "";
         document.getElementById("New_Referral"+p).style.backgroundColor='#E0E0E0'; //whatever color or hex.
    
      } 
       
     else{
          
           document.getElementById("New_Referral"+p).disabled = false;
          
      }
     }
    
    }
    var h;
    var y;
    if(document.getElementById("New_Papsmear3")!=null )
     { h = document.getElementById("New_Papsmear3");
       y= h.options[h.selectedIndex].value;
     }
    
//          var y= h.options[h.selectedIndex].value;
      if(y=="Normal") {
          document.getElementById("New_Referral3").disabled = true;
           document.getElementById("New_Referral3").value = "";
           document.getElementById('New_Referral3').style.backgroundColor='#E0E0E0'; //whatever color or hex.
           //document.getElementById('New_Referral3').style.bordercolor='#C0C0C0'; //whatever color or hex.
             
       
      }
         else  if(y=="LSIL") {
          document.getElementById("New_Referral3").disabled = false;
       
      }
    var g;
    var l;
   if( document.getElementById("New_Papsmear2")!=null){ 
        g = document.getElementById("New_Papsmear2");
        l= g.options[g.selectedIndex].value;
      }
          
      if(l=="Normal") {
          
          document.getElementById("New_Referral2").disabled = true;
           document.getElementById("New_Referral2").value = "";
           document.getElementById('New_Referral2').style.backgroundColor='#E0E0E0'; //whatever color or hex.
           //document.getElementById('New_Referral3').style.bordercolor='#C0C0C0'; //whatever color or hex.
             
       
      }
         else  if(l=="LSIL") {
          document.getElementById("New_Referral2").disabled = false;
          
             
       
      }
      
       var b= document.getElementById("CervicalCancer_old_rows").value;
        for(var m=1;m<=q;m++){
         if(document.getElementById("tests"+m)!=null){
        var a = document.getElementById("tests"+m);
        var c = j.options[j.selectedIndex].value;
               
         if(c=="VIA Negative") {
          document.getElementById("Referral"+m).disabled = true;
           document.getElementById("Referral"+m).value = "";
           document.getElementById("Referral"+m).style.backgroundColor='#E0E0E0'; //whatever color or hex.
             
      } 
        else if(c=="VILI Negative") {
          document.getElementById("Referral"+m).disabled = true;
          document.getElementById("Referral"+m).value = "";
         document.getElementById("Referral"+m).style.backgroundColor='#E0E0E0'; //whatever color or hex.
    
      } 
       
     else{
          
           document.getElementById("Referral"+m).disabled = false;
          
      }
     }
    
    }
    var d;
    var z;
    if(document.getElementById("New_Papsmear3")!=null )
     { d = document.getElementById("New_Papsmear3");
       z= d.options[d.selectedIndex].value;
     }
    
//          var y= h.options[h.selectedIndex].value;
      if(z=="Normal") {
          document.getElementById("New_Referral3").disabled = true;
           document.getElementById("New_Referral3").value = "";
           document.getElementById('New_Referral3').style.backgroundColor='#E0E0E0'; //whatever color or hex.
           //document.getElementById('New_Referral3').style.bordercolor='#C0C0C0'; //whatever color or hex.
             
       
      }
         else  if(z=="LSIL") {
          document.getElementById("New_Referral3").disabled = false;
       
      }
    var g;
    var l;
   if( document.getElementById("New_Papsmear2")!=null){ 
        g = document.getElementById("New_Papsmear2");
        l= g.options[g.selectedIndex].value;
      }
          
      
}