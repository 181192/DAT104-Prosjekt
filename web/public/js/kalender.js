var test = "dette er en test";
$('#calendar').fullCalendar({
			editable: false,
			aspectRatio: 1.8,
			scrollTime: '07:00',
			dayClick: function(date, jsEvent, view, resourceObj) {
				// ny event modul.
				datoen = date.format();
				console.log(datoen);
				lagEvent();
    		},
			header: {
				left: 'today prev,next',
				center: 'title',
				right: 'timelineDay,month'
			},
			defaultView: 'month',
			resources: [
				{ id: 'dat103', Hvor: 'f117', title: 'dat103' },
				{ id: 'dat104', Hvor: 'f119', title: 'dat104', eventColor: 'green' },
				{ id: 'mat102', Hvor: 'e403', title: 'mat102', eventColor: 'orange' }],

			events: [
				{ id: '1', resourceId: 'dat103', start: '2017-10-07T02:00:00', end: '2017-10-07T07:00:00', title: 'Forelesning' },
				{ id: '2', resourceId: 'dat104', start: '2017-10-07T05:00:00', end: '2017-10-07T22:00:00', title: 'Forelesning' },
				{ id: '3', resourceId: 'mat102', start: '2017-10-06', end: '2017-10-06', title: 'Lab' },
			]
		});
function lagEvent() {
	window.location.href = "lagevent";
};