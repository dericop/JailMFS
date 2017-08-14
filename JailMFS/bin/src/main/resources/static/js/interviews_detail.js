$(function(){
	
	$("#visitor-data").hide();
	
	$("#btn-authorize").click(function(){
		
		swal({
		  title: "Aprobación de visitante",
		  text: "¿Está seguro que desea aprobar este visitante?",
		  type: "info",
		  showCancelButton: true,
		  confirmButtonColor: "#5cb85c",
		  confirmButtonText: "Confirmar",
		  closeOnConfirm: false
		}, function(isConfirm){
			if (isConfirm) {
				
				var idv = $("#intervieweeId").val();
				console.log("Id card: "+idv);
				$.ajax({
			        type: "GET",
			        contentType: "application/json",
			        url: "/jmfs/visitors/detail",
			        data: {id:idv},
			        dataType: 'json',
			        cache: false,
			        timeout: 600000,
			        success: function (data) {
			        	console.log(data);
			        	if(data["idCard"] == 0 && data["name"] == ""){
			        		swal({
								  title:"Aprobado!",
								  text: "Ahora ingrese la información del visitante.",
								  type: "success",
								  showCancelButton: false,
								  confirmButtonColor: "#5cb85c",
								  confirmButtonText: "Ok",
								  closeOnConfirm: true
								}, function(){
									$("#visitor-data").show();
									
									$('html, body').animate({
								        scrollTop: $("#visitor-data").offset().top
								    }, 1000);
									
									//$("#relationship").prop("disabled", "disabled");
									$("#relationship").addClass("disabled");
									$("#btn-authorize").prop("disabled", "disabled");
									$("#btn-authorize").addClass("disabled");
									$("#btn-dennied").prop("disabled", "disabled");
									$("#btn-dennied").addClass("disabled");
									
								});
			        		
			        		
			        	}else{
			        		
			        		swal({
								  title:"Aprobado!",
								  text: "El visitante ya existe. A partir de ahora queda autorizado para visitar a este preso",
								  type: "success",
								  showCancelButton: false,
								  confirmButtonColor: "#5cb85c",
								  confirmButtonText: "Ok",
								  closeOnConfirm: true
								}, function(){
									
									$("#form_interview").unbind("submit").submit();
									
								});
			        	}
			        		
			        	
			        },
			        error: function (e) {
			            console.log(e);
			        }
			    });
				
				return true;
				
			} else {
				return false;
			}
		});
	});
	
	
	$("#btn-dennied").click(function(e){
		$("#form_interview").attr("action", "/jmfs/interviews/denied");
		swal({
			  title: "Denegación de visitante",
			  text: "¿Está seguro que desea denegar este visitante?",
			  type: "info",
			  showCancelButton: true,
			  confirmButtonColor: "#d9534f",
			  confirmButtonText: "Confirmar",
			  closeOnConfirm: false
			}, function(isConfirm){
				if(isConfirm){
					swal({
						  title:"Denegado!",
						  text: "La autorización para este visitante ha sido denegada",
						  type: "success",
						  showCancelButton: false,
						  confirmButtonColor: "#5cb85c",
						  confirmButtonText: "Ok",
						  closeOnConfirm: true
						}, function(){
							
							$("#form_interview").unbind("submit").submit();
							
						});
					
					return true;
				}
				
				return false;
			});
		
		
		
	});
});