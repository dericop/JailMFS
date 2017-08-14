$(function() {
	var deleteForm = $("#deleteForm");

	deleteForm.submit(function(e) {
		e.preventDefault();
	})

	$("#btn-deleteVisitor").click(
			function() {

				swal({
					title : "¿Está seguro?",
					text : "Se eliminará el visitante",
					type : "warning",
					showCancelButton : true,
					confirmButtonColor : "#DD6B55",
					confirmButtonText : "Confirmar",
					closeOnConfirm : false

				}, function(isConfirm) {
					if (isConfirm) {
						swal("Eliminado",
								"El prisionero X se ha eliminado con éxito.",
								"success");

						deleteForm.unbind('submit').submit();
						return true;

					} else {
						swal("Cancelled", "Your imaginary file is safe :)",
								"error");
						return false;
					}
				});
			});
	
	$(".btn-deleteAuth").click(function(){
		var that = $(this);
		
		swal({
			title : "¿Está seguro?",
			text : "El visitante ya no estará habilitado para este preso",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Confirmar",
			closeOnConfirm : false

		}, function(isConfirm) {
			if (isConfirm) {
				$(that).parent().parent().unbind('submit').submit();
				return true;

			} else {
				
				return false;
			}
		});
	});
	
	
	var url_string = location.href;
	var url = new URL(url_string);
	var c = url.searchParams.get("c");

	if(c!=""){
		$("#visitor-input").val(c);
	}
	

});
