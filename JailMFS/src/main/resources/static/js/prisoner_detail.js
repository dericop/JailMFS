$(function(){
	$(".btn-deleteAuth").click(function(){
		var that = $(this);
		swal({
			title : "¿Está seguro?",
			text : "El visitante ya no estará habilitado para este prisionero",
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
	
	$(".btn-deleteSent").click(function(){
		var that = $(this);
		swal({
			title : "¿Está seguro?",
			text : "Se eliminará la condena de este prisionero",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Confirmar",
			closeOnConfirm : false

		}, function(isConfirm) {
			if (isConfirm) {
				$(that).parent().unbind('submit').submit();
				return true;

			} else {
				
				return false;
			}
		});
	});
})