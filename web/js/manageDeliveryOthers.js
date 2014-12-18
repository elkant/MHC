/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



function conductorothersfun(id){

var others1checker=id.value;
//conductorothers
//alert(others1checker);
if(others1checker==5){
   // alert(others1checker);
       document.getElementById("conductorothers").type='text';
       
   }
       
else{
    
    document.getElementById("conductorothers").type='hidden';
    
}

}


function podothers(id2){
    
    
    

var others2checker=id2.value;
//alert(others2checker);
//deliveryothers
if(others2checker==3){
    //alert(others2checker);
       document.getElementById("deliveryothers").type='text';
       
   }
else{
    
    document.getElementById("deliveryothers").type='hidden';
    
}



}