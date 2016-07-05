var UIJQueryUI = function () {

    
    var handleDatePickers = function () {
        
        


	    $( "#ui_date_picker_range_from" ).datepicker({
	      defaultDate: "+1w",
	      changeMonth: true,
	      numberOfMonths: 2,
	      onClose: function( selectedDate ) {
	        $( "#ui_date_picker_range_to" ).datepicker( "option", "minDate", selectedDate );
	      }
	    });
	    $( "#ui_date_picker_range_to" ).datepicker({
	      defaultDate: "+1w",
	      changeMonth: true,
	      numberOfMonths: 2,
	      onClose: function( selectedDate ) {
	        $( "#ui_date_picker_range_from" ).datepicker( "option", "maxDate", selectedDate );
	      }
	    });

    }


    return {
        //main function to initiate the module
        init: function () {
            handleDatePickers();
        }

    };

}();