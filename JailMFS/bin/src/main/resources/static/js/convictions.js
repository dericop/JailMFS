$(function() {

	$('form').on('submit', function(e) {
		var startDate = new Date($("#startDate").val());
		var endDate = new Date($("#endDate").val());
		var valid = true;

		if (startDate > endDate) {
			valid = false;
			swal({
				title : "Fechas",
				text : "La fecha de inicio debe ser mayor a la fecha de fin",
				type : "warning",
				showCancelButton : false,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "Ok",
				closeOnConfirm : false
			});
		}

		if (!valid) {
			e.preventDefault();
		}
	});

});