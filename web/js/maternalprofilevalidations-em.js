/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


 
    
 function validatedate(id){
     var olddate=document.getElementById(id).value;
     
      var dateObject,day,month,year,current_date;
      
    dateObject =new Date();
    day=dateObject.getDate();
    month=dateObject.getMonth()+1;
    year=dateObject.getFullYear();
    //fully year separated by hyphen
   current_date=year+"-"+month+"-"+day;  
    
  
 
if (new Date(olddate) > new Date(current_date))
  {
 // alert("LMPs date cannot be greater than today");
 $("#"+id).css("border-color","#ff0000");
 $("#"+id).slideToggle("slow",function() {});
 $("#"+id).slideToggle("slow",function() {});  
  document.getElementById(id).focus();
  return false;
  }
else
  {
    //alert(current_date+"is greater than "+olddate);
  
 return true;
 }
  }
//  
//  function validate_prev_preg(id){
//       
//       var year;
//       var deliv_place;
//       var deliv_type
//       
//       
//         year =document.getElementById("new_year"+id);
//         
//        //alert(""+document.getElementById("new_year"+id).value);
//        
//        deliv_place=document.getElementById("new_place_of_delivery"+id);
//        
//        deliv_type=document.getElementById("new_Type_of_delivery"+id);
//       
//  
//   //validate now
//   
//   
//        if(year.value!=""||deliv_place.value!=""||deliv_type.value!=""){
//            
//            if(year.value==""){
//             
//            // alert("Enter year");
//            year.required=true;
//            year.style="border-color:#ff0000;border-width:2px;";
//               year.focus();
//               return false; 
//            }
//            
//              
//           else if(deliv_place.value==""){
//             
//             //alert("Enter delivery place");
//             deliv_place.style="border-color:#ff0000;"
//               deliv_place.focus();
//              return false; 
//            }
//            
//             else if(deliv_type.value==""){
//             
//            // alert("Enter delivery type");
//            deliv_type.style="border-color:#ff0000;";
//               deliv_type.focus();
//              return false; 
//            }
//            
//            
//          else{return true;} 
//        } 
//            else{return true;}
//       
//  }
 



//old columns validation
     function validate_prev_preg_old(id){
       
       var year;
       var deliv_place;
       var deliv_type
       
       
         year =document.getElementById("year"+id);
         
        //alert(""+document.getElementById("new_year"+id).value);
        
        deliv_place=document.getElementById("place_of_delivery"+id);
        
        deliv_type=document.getElementById("Type_of_delivery"+id);
       
  
   //validate now
   
   
        if(year.value!=""||deliv_place.value!=""||deliv_type.value!=""){
            
            if(year.value==""){
             
             //alert("Enter year");
             year.required=true;
               year.focus();
               year.style="border-color:#ff0000;"
               return false; 
            }
            
              
           else if(deliv_place.value==""){
             
             //alert("Enter delivery place");
             deliv_place.required=true;
               deliv_place.focus();
               deliv_place.style="border-color:#ff0000;"
              return false; 
            }
            
             else if(deliv_type.value==""){
             deliv_type.required=true;
            // alert("Enter delivery type");
               deliv_type.focus();
               deliv_type.style="border-color:#ff0000;"
              return false; 
            }
            
            
          else{
          deliv_type.style="border-color:#00ff00;"    
          year.style="border-color:none;"    
          deliv_place.style="border-color:none;"    
              return true;
          
          
      } 
        } 
            else{return true;}
       
  }
   
   function ifenter(e){
var unicode=e.keyCode? e.keyCode : e.charCode;
if(unicode==39){
    
    alert("next pressed");
}
  
   }