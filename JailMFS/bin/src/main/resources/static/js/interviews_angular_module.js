/**
 * 
 */
// declare a module
var jmfsModule = angular.module('jmfs', [ 'mwl.calendar', 'ui.bootstrap' ]);

jmfsModule.config([ 'calendarConfig', function(calendarConfig) {

	// View all available config
	console.log(calendarConfig);

	// Change the month view template globally to a custom
	// template
	// calendarConfig.templates.calendarMonthView =
	// 'path/to/custom/template.html';

	// Use either moment or angular to format dates on the
	// calendar. Default angular. Setting this will override any
	// date formats you have already set.
	calendarConfig.dateFormatter = 'moment';

	// This will configure times on the day view to display in
	// 24 hour format rather than the default of 12 hour
	calendarConfig.allDateFormats.moment.date.hour = 'HH:mm';

	// This will configure the day view title to be shorter
	calendarConfig.allDateFormats.moment.title.day = 'ddd D MMM';

	// This will set the week number hover label on the month
	// view
	calendarConfig.i18nStrings.weekNumber = 'Week {week}';

	// This will display all events on a month view even if
	// they're not in the current month. Default false.
	calendarConfig.displayAllMonthEvents = true;

	// Make the week view more like the day view, ***with the
	// caveat that event end times are ignored***.
	calendarConfig.showTimesOnWeekView = true;

} ]);

jmfsModule.controller('InterviewsController', [ '$scope', '$http', function($scope, $http) {
	var vm = this;

	$scope.calendarView = 'month';
	$scope.viewDate = moment().startOf('month').toDate();
	$scope.viewChangeEnabled = true;

	moment.locale("es");
	$('#datetimepicker1').datetimepicker();

	$scope.events = [ ];

	$scope.viewChangeClicked = function(date, nextView) {
		console.log(date, nextView);
		return vm.viewChangeEnabled;
	};

	vm.rangeSelected = function(startDate, endDate) {
		vm.firstDateClicked = startDate;
		vm.lastDateClicked = endDate;
		
		$("#startDate").val(moment(vm.firstDateClicked).format("YYYY-MM-DD HH:mm"));
		$("#endDate").val(moment(vm.lastDateClicked).format("YYYY-MM-DD HH:mm"));
		
		$("#newInterviewModal").modal("show");
	};
	
	vm.init = function(){
		
		/*$("#form_interview").submit(function(e){
			e.preventDefault();
			
			if(vm.firstDateClicked < (new Date())){
				document.getElementById("startDate").setCustomValidity("La fecha de inicio debe ser mayor a la fecha actual");
			}
			
		});*/
		
		$.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/jmfs/interviews/list",
	        data: [],
	        dataType: 'json',
	        cache: false,
	        timeout: 600000,
	        success: function (data) {
	        	vm.loadEventsWithBaseData(data);
	            console.log(data);
	        },
	        error: function (e) {
	            console.log("ERROR : ", e);
	        }
	    });
		
	}
	
	vm.loadEventsWithBaseData = function(data){
		for(i=0;i<data.length;i++){
			var dict = data[i];
			
			var newEvent = {};
			newEvent["title"] = dict["intervieweeName"];
			newEvent["id"] = dict["id"];
			newEvent["startsAt"] = new Date(dict["startDate"]);
			newEvent["endsAt"] = new Date(dict["endDate"]);
			newEvent["color"] = {
				primary : '#e3bc08',
				secondary : '#fdf1ba'
			};
			newEvent["actions"] = [ {
				label : '<i class=\'glyphicon glyphicon-pencil\'></i>',
				cssClass : 'edit-action',
				onClick : function(args) {
					console.log(args.calendarEvent);
					location.href= "/jmfs/interviews/"+args.calendarEvent["id"];
				}
			} ];
			newEvent["draggable"] = false;
			newEvent["resizable"] = false;
			newEvent["incrementsBadgeTotal"] = true;
			newEvent["recursOn"] = 'year';
			newEvent["cssClass"] = 'a-css-class-name';
			newEvent["allDay"] = false;
			
			$scope.events.push(newEvent);
			
			
		}
		
		$scope.$apply();
	}
	
	vm.init();
	window.vm = vm;
	window.scope = $scope;
	
	/* Event Template
	 * {
		title : 'Entrevista con Alam Brito',
		startsAt : new Date(2017, 6, 26, 15),
		endsAt : new Date(2017, 8, 26, 15),
		color : {
			primary : '#e3bc08',
			secondary : '#fdf1ba'
		},
		actions : [ {
			label : '<i class=\'glyphicon glyphicon-pencil\'></i>',
			cssClass : 'edit-action',
			onClick : function(args) {
				console.log('Edit event', args.calendarEvent);
			}
		} ],
		draggable : true,
		resizable : true,
		incrementsBadgeTotal : true,
		recursOn : 'year',
		cssClass : 'a-css-class-name',
		allDay : false
	}*/

} ]);