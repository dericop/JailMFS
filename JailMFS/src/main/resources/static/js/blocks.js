$(function(){
	$("#btn-new-block").click(function(){
		swal({
		  title: "Crear nuevo bloque",
		  text: "Confirmar la creación de un nuevo bloque",
		  type: "info",
		  showCancelButton: true,
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "Confirmar",
		  closeOnConfirm: false
		}, function(){
			$.ajax({
			  type: "POST",
			  url: "blocks",
			  data: {},
			  success: function(data){
				  swal({
					  title: "Bloque creado", 
					  text: "El bloque se ha creado con éxito.", 
					  type: "success"					  
				  }, function(){
					  location.reload();
				  });
				  console.log(data);
			  },
			  dataType: "json"
			});				
		});
	});
});