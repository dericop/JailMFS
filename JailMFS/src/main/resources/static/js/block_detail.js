$(function(){
	
	$("#btn-new-cell").click(function(){
		$("#modal-cell").modal('show');
	});
	/*
	$("#btn-confirm-cell").click(function(){		
		$.ajax({
			  type: "POST",
			  url: "blocks/addCell",
			  data: {
				  blockId : $("#block-id").val(),
				  cellCapacity : $("#cellCapacity").val()
			  },
			  success: function(data){
				  console.log("prueba");
				  swal({
					  title: "Celda creada", 
					  text: "La celda se ha creado con exito.", 
					  type: "success"			  
				  });
			  },
			  dataType: "json"
			});
	});*/
	
	$(".deleteCellForm").submit(function(event){
		event.preventDefault();
		$('<input/>').attr('type','hidden').attr('name', "blockId").attr('value', $("#block-id").val()).appendTo(this);
		var form = this;
		swal({
			title : "¿Está seguro?",
			text : "Se eliminará la celda y se reubicaran los prisioneros",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Confirmar",
			closeOnConfirm : false			
		}, function(isConfirm) {
			if (isConfirm) {
				swal({
					title : "Eliminado", 
					text : "Se ha eliminado la celda.",	
					type : "success"
				}, function(){
					form.submit();
				});
				return true;
			} else {
				swal("Cancelled", "Se ha cancelado la operación", "error");
				return false;
			}
		});
	});
	
	$("#deleteBlockForm").submit(function(event){
		event.preventDefault();
		var form = this;
		swal({
			title : "¿Está seguro?",
			text : "Se eliminará el bloque y todas sus celdas y se reubicaran los prisioneros",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Confirmar",
			closeOnConfirm : false			
		}, function(isConfirm) {
			if (isConfirm) {
				swal({
					title : "Eliminado", 
					text : "Se ha eliminado el bloque.",	
					type : "success"
				}, function(){
					form.submit();
				});
				return true;
			} else {
				swal("Cancelled", "Se ha cancelado la operación", "error");
				return false;
			}
		});
	});
	
});
