$(function(){
	
	$("#visitor-select").prop('disabled', true);
	
	$("#prisoner-select").on('changed.bs.select', function(e){
		
		$.ajax({
			type : 'post',
			url : 'visits/visitorsForPrisoner',
			data : { prisonerId : $(this).val() },
			success : function(data) {
				visitor_picker = $("#visitor-select");
				visitor_picker.empty();
				if(data.length > 0){
					data.forEach(function(vis){
						console.log(vis)
						var opt = "<option value='"+vis.id+"'>"+vis.name+" "+vis.lastName+"</option>";
						visitor_picker.append(opt);
						console.log(opt)					
					});
					visitor_picker.prop('title', 'Elija un visitante');
					visitor_picker.attr('disabled', false);							
				} else {
					visitor_picker.prop('title', 'El prisionero no tiene visitantes');
					visitor_picker.attr('disabled', true);
				}
				visitor_picker.selectpicker('refresh');
				visitor_picker.selectpicker('render');
				if(data.length > 0)
					visitor_picker.selectpicker('toggle');
			}
		})
		
	});
	
});