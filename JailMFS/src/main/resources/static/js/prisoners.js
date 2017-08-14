
var deleteForm = $("#deleteForm");

deleteForm.submit(function(e){
	e.preventDefault();
})

function confirmDelete() {
	
	swal({
		title : "¿Está seguro?",
		text : "Se eliminará el prisionero",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "Confirmar",
		closeOnConfirm : false
		
	}, function(isConfirm) {
		if (isConfirm) {
			swal("Eliminado", "El prisionero X se ha eliminado con éxito.",
					"success");
			
			deleteForm.unbind('submit').submit();
			return true;
			
		} else {
			swal("Cancelled", "Your imaginary file is safe :)", "error");
			return false;
		}
	});
}

var url_string = location.href;
var url = new URL(url_string);
var c = url.searchParams.get("c");

if(c!=""){
	$("#prisoner-input").val(c);
}





