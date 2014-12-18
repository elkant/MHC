/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


       
//var existing_no_of_rows= document.getElementById("no_of_rows").value+1;
        
var b5=1;  //hplds the no of existing rows for the table to add new values
var verifierstep5=1;
var verifierstep51=1;
var allrowsoldnew=0;
//verifier is a field that determines whether to fetch the value of i from the edit text.
var f5=0;   
function ifenter(e){
    var unicode=e.keyCode? e.keyCode : e.charCode;
    if(unicode==13||unicode==9){
    
        addstep5('step5_table');
    }
}
        
        
function reseta5(){
    b5=1;
    verifierstep5=1;
    verifierstep51=1;
} 
        
        
function addstep5(tableID) {
            
            
    if(verifierstep5==1){
        allrowsoldnew=parseInt(document.getElementById("all_rows_count").value);
        f5=parseInt(document.getElementById("old_step5_no_rows").value)+(parseInt(1));
        b5=parseInt(document.getElementById("new_step5_no_rows").value);
    //alert(""+document.getElementById("no_of_old_rows").value);        
    }
            
    b5++;
    verifierstep5++;
    verifierstep51++;  
    allrowsoldnew++; 
            
    //add a row if only there exists data of that 
            
    //if(document.getElementById("blank_warning").innerHTML==""){
            
 
    var table = document.getElementById(tableID);
    //get the number of rows
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    //create element that is a checkbox assign type, name,style then apppend the child element to the cell
    var cell1 = row.insertCell(0);
    cell1.style.width = "12px";
            
    var element1 = document.createElement("label"); 
    element1.type = "label";
    //element1.name="chkbox[]";
    element1.innerHTML=""+allrowsoldnew;
    element1.style.width = "56px";
    element1.style.textAlign="center";
            
           
    cell1.appendChild(element1);
            
    /////CTX
    var cell2 = row.insertCell(1);//create an input field of type text,class name, width and assign names, ids and functions
    var ctx = document.createElement("select");
    ctx.type = "text";
    ctx.name = "CTX"+b5;
    ctx.id = "CTX"+b5;
    //ctx.value = "";
    ctx.style.width = "80px";
    var option;
    var option1;
    var option2;
    
    option = document.createElement("option");
    option.type="text";
    option.value="";
    option.innerHTML = "";
    ctx.appendChild(option);
    option1 = document.createElement("option");
    option1.type="text";
    option1.value="Y";
    option1.innerHTML = "Y";
    ctx.appendChild(option1);
    option2 = document.createElement("option");
    option2.type="text";
    option2.value="N";
    option2.innerHTML = "N";
    ctx.appendChild(option2);
    cell2.appendChild(ctx);
          
          
    //NVP
      
    var cell3 = row.insertCell(2);//create an input field of type text,class name, width and assign names, ids and functions
    var nvp = document.createElement("select");
    nvp.type = "text";
    nvp.name = "NVP"+b5;
    nvp.id = "NVP"+b5;
    //ctx.value = "";
    nvp.style.width = "80px";
    var option;
    var option1;
    var option2;
    
    option = document.createElement("option");
    option.type="text";
    option.value="";
    option.innerHTML = "";
    nvp.appendChild(option);
    option1 = document.createElement("option");
    option1.type="text";
    option1.value="Y";
    option1.innerHTML = "Y";
    nvp.appendChild(option1);
    option2 = document.createElement("option");
    option2.type="text";
    option2.value="N";
    option2.innerHTML = "N";
    nvp.appendChild(option2);
    cell3.appendChild(nvp);
          
    //AZT     

    var cell4 = row.insertCell(3);//create an input field of type text,class name, width and assign names, ids and functions
    var azt = document.createElement("select");
    azt.type = "text";
    azt.name = "AZT"+b5;
    azt.id = "AZT"+b5;
    //ctx.value = "";
    azt.style.width = "60px";
    var option;
    var option1;
    var option2;
    
    option = document.createElement("option");
    option.type="text";
    option.value="";
    option.innerHTML = "";
    azt.appendChild(option);
    option1 = document.createElement("option");
    option1.type="text";
    option1.value="Y";
    option1.innerHTML = "Y";
    azt.appendChild(option1);
    option2 = document.createElement("option");
    option2.type="text";
    option2.value="N";
    option2.innerHTML = "N";
    azt.appendChild(option2);
    cell4.appendChild(azt);


    //HAART



    var cell5 = row.insertCell(4);//create an input field of type text,class name, width and assign names, ids and functions
    var haart = document.createElement("select");
    haart.type = "text";
    haart.name = "HAART"+b5;
    haart.id = "HAART"+b5;
    //ctx.value = "";
    haart.style.width = "60px";
    var option;
    var option1;
    var option2;
    
    option = document.createElement("option");
    option.type="text";
    option.value="";
    option.innerHTML = "";
    haart.appendChild(option);
    option1 = document.createElement("option");
    option1.type="text";
    option1.value="P";
    option1.innerHTML = "P";
    haart.appendChild(option1);
    option2 = document.createElement("option");
    option2.type="text";
    option2.value="T";
    option2.innerHTML = "T";
    haart.appendChild(option2);
    cell5.appendChild(haart);




    //BABYNVP

 
    var cell6 = row.insertCell(5);//create an input field of type text,class name, width and assign names, ids and functions
    var bnvp = document.createElement("select");
    bnvp.type = "text";
    bnvp.name = "BABYNVP"+b5;
    bnvp.id = "BABYNVP"+b5;
    //ctx.value = "";
    bnvp.style.width = "60px";
    var option;
    var option1;
    var option2;
    
    option = document.createElement("option");
    option.type="text";
    option.value="";
    option.innerHTML = "";
    bnvp.appendChild(option);
    option1 = document.createElement("option");
    option1.type="text";
    option1.value="Y";
    option1.innerHTML = "Y";
    bnvp.appendChild(option1);
    option2 = document.createElement("option");
    option2.type="text";
    option2.value="N";
    option2.innerHTML = "N";
    bnvp.appendChild(option2);
    cell6.appendChild(bnvp);
          


    //TBSTATUS

    var cell7 = row.insertCell(6);//create an input field of type text,class name, width and assign names, ids and functions
    var tb = document.createElement("select");
    tb.type = "text";
    tb.name = "TB"+b5;
    tb.id = "TB"+b5;
    //ctx.value = "";
    tb.style.width = "90px";
    var option;
    var option1;
    var option2;
    var option3;
    var option4;
    var option5;
    
    
    
    option = document.createElement("option");
    option.type="text";
    option.value="";
    option.innerHTML = "";
    tb.appendChild(option);
    option1 = document.createElement("option");
    option1.type="text";
    option1.value="1";
    option1.innerHTML = "NO signs";
    tb.appendChild(option1);
    option2 = document.createElement("option");
    option2.type="text";
    option2.value="2";
    option2.innerHTML = "TB Suspected";
    tb.appendChild(option2);
    
    option3 = document.createElement("option");
    option3.type="text";
    option3.value="3";
    option3.innerHTML = "TB Rx";
    tb.appendChild(option3);
    
    option4 = document.createElement("option");
    option4.type="text";
    option4.value="4";
    option4.innerHTML = "ND";
    tb.appendChild(option4);
    
    option5 = document.createElement("option");
    option5.type="text";
    option5.value="5";
    option5.innerHTML = "REF";
    tb.appendChild(option5);
    
    
    cell7.appendChild(tb);



    //cell two//Cervical Cancer





    var cell8 = row.insertCell(7);//create an input field of type text,class name, width and assign names, ids and functions
    cell8.style.width = "130px";
    cell8.style.height="55px";
    var meth = document.createElement("select");
    meth.type = "text";
    meth.name = "Test"+b5;
    meth.id = "Test"+b5;
    meth.multiple =true;
    meth.style.height = "44px";
    meth.style.width = "125px";
    var moption;
    var moption1;
    var moption2;
    var moption3;
    var moption4;
    
    
    var roption;
    var roption1;
    var roption2;
    var roption3;
    
     
    moption1 = document.createElement("option");
    moption1.type="text";
    moption1.value="1";
    moption1.innerHTML = "VIA";
    meth.appendChild(moption1);
    
    moption2 = document.createElement("option");
    moption2.type="text";
    moption2.value="2";
    moption2.innerHTML = "VILI";
    meth.appendChild(moption2);
    
    moption3 = document.createElement("option");
    moption3.type="text";
    moption3.value="3";
    moption3.innerHTML = "PAP";
    meth.appendChild(moption3);
    
    
    
      moption4 = document.createElement("option");
    moption4.type="text";
    moption4.value="4";
    moption4.innerHTML = "NOT DONE";
    meth.appendChild(moption4);
    
    meth .onkeypress= function ifenter(e){
        var unicode=e.keyCode? e.keyCode : e.charCode;
        if(unicode==13||unicode==9){
    
            addstep5('step5_table');
        }//end of if
    }//end of function
    
    
    
    //end of method select
    
    
    //create the results select
    var res = document.createElement("select");
    res.type = "text";
    res.name = "Result"+b5;
    res.id = "Result"+b5;
   
    res.style.width = "130px";
    res.style.margin="5px";
    
    roption = document.createElement("option");
    roption.type="text";
    roption.value="";
    roption.innerHTML = "";
    res.appendChild(roption);
    roption1 = document.createElement("option");
    roption1.type="text";
    roption1.value="Normal";
    roption1.innerHTML = "Normal";
    res.appendChild(roption1);
    roption2 = document.createElement("option");
    roption2.type="text";
    roption2.value="Suspected";
    roption2.innerHTML = "Suspected";
    res.appendChild(roption2);
    
    roption3 = document.createElement("option");
    roption3.type="text";
    roption3.value="Confirmed";
    roption3.innerHTML = "Confirmed";
    res.appendChild(roption3);
    
    
    res.onkeypress= function ifenter(e){
        var unicode=e.keyCode? e.keyCode : e.charCode;
        if(unicode==13||unicode==9){
    
            addstep5('step5_table');
        }//end of if
    }//end of function
    //element5.style.width = "62px";
    
    
    
    
    
    
    cell8.appendChild(meth);
    cell8.appendChild(res);


 
 
           
    //element5.style.width = "62px";
            
           
           
 
    var frm = document.getElementById("frm");
    //var as = document.getElementById("h");    
    f5++;  
    // allrowsoldnew++;
    document.getElementById("new_step5_no_rows").value=a;
      
//alert(i);
           
}
 
function deletestep5(tableID) {
    //if the addrow has not been called  
        
            
    if((verifierstep51==1)){
        allrowsoldnew=parseInt(document.getElementById("all_rows_count").value);
        f5 =parseInt(document.getElementById("old_step5_no_rows").value)+(parseInt(1));
        b5=parseInt(document.getElementById("new_step5_no_rows").value)+(parseInt(1));
    //alert(""+document.getElementById("no_of_old_rows").value);        
    }
                     
    verifierstep51++;
    verifierstep5++;
            
            
    try {
                
            
        var table = document.getElementById(tableID);
        //delete the last row 
            
             
            
        var rowCount = table.rows.length;
        //  alert(""+rowCount);
        //alert(rowCount);
        // for(var a=0; a<rowCount; a++) {
        //    var row = table.rows[a];
        //  var chkbox = row.cells[0].childNodes[0]; 
              
        //delete ntil you have one row
        if(rowCount>parseInt(document.getElementById("old_step5_no_rows").value)+parseInt(5)){
                  
            table.deleteRow(rowCount-1);
            rowCount--;
                    
            //reduce the number of rows           
            f5--;
            b5--;
            allrowsoldnew--;
            document.getElementById("new_step5_no_rows").value=b5;
                   
                     
        }
        else{
                
         //   alert("Maximum deletable rows reached!!");
                
        }
                
    // }
    }catch(e) {
        alert(e);
    }
}
        
 