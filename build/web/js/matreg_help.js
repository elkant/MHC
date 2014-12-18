/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


  $(function() {
        
        
        
    	$( "#dialog1" ).dialog({
			autoOpen: false,
			width: 400,
			buttons: [
				{
					text: "Ok",
					click: function() {
						$( this ).dialog( "close" );
					}
				}
			]
		});



$( "#dialog-link1" ).click(function( event ) {
			$( "#dialog1" ).dialog( "open" );
			event.preventDefault();
		});

///////////////////////////////////////////////////////////////////////////////




$( "#dialog2" ).dialog({
			autoOpen: false,
			width: 400,
			buttons: [
				{
					text: "Ok",
					click: function() {
						$( this ).dialog( "close" );
					}
				}
			]
		});



$( "#dialog-link2" ).click(function( event ) {
			$( "#dialog2" ).dialog( "open" );
			event.preventDefault();
		});

///////////////////////////////section 3////////////////////////////////////////////////




$( "#dialog3" ).dialog({
			autoOpen: false,
			width: 400,
			buttons: [
				{
					text: "Ok",
					click: function() {
						$( this ).dialog( "close" );
					}
				}
			]
		});



$( "#dialog-link3" ).click(function( event ) {
			$( "#dialog3" ).dialog( "open" );
			event.preventDefault();
		});




///////////////////////////////section 4////////////////////////////////////////////////




$( "#dialog4" ).dialog({
			autoOpen: false,
			width: 400,
			buttons: [
				{
					text: "Ok",
					click: function() {
						$( this ).dialog( "close" );
					}
				}
			]
		});



$( "#dialog-link4" ).click(function( event ) {
			$( "#dialog4" ).dialog( "open" );
			event.preventDefault();
		});



///////////////////////////////section 5////////////////////////////////////////////////




$( "#dialog5" ).dialog({
			autoOpen: false,
			width: 400,
			buttons: [
				{
					text: "Ok",
					click: function() {
						$( this ).dialog( "close" );
					}
				}
			]
		});



$( "#dialog-link5" ).click(function( event ) {
			$( "#dialog5" ).dialog( "open" );
			event.preventDefault();
		});





///////////////////////////////section 6////////////////////////////////////////////////




$( "#dialog6" ).dialog({
			autoOpen: false,
			width: 400,
			buttons: [
				{
					text: "Ok",
					click: function() {
						$( this ).dialog( "close" );
					}
				}
			]
		});



$( "#dialog-link6" ).click(function( event ) {
			$( "#dialog6" ).dialog( "open" );
			event.preventDefault();
		});



$(document).tooltip();
});