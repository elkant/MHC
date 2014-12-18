/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function togglevalue(name){
    
    var tickstatus=document.getElementById(name).value;
    
    
    if(tickstatus=="yes"){
      document.getElementById(name).value="no";  
        
    }
    else{
      document.getElementById(name).value="yes";  
        
    }
    //alert(tickstatus);
    
    
    
}

