// Load the Visualization API and the corechart package.
google.charts.load('current', {
	'packages' : [ 'corechart', 'line' ]
});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawChart);

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart() {

	// views
	var boolFornoeyd = true;
	var boolNoeytral = true;
	var boolMisfornoeyd = true;

	var showCols = [ 0, 1, 2, 3 ];

	// Create the data table.
	var count = hentData.length;

	// Lager test data
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Event');
	data.addColumn('number', 'Fornoyd');
	data.addColumn('number', 'Noytral');
	data.addColumn('number', 'Misfornoyd');

	for (var i = 0; i < hentData.length; i++) {
		var navn = hentData[i][0]; // navn på event
		var f = parseInt(hentData[i][1]); // antall fornøyd
		var n = parseInt(hentData[i][2]); // antall nøytral
		var m = parseInt(hentData[i][3]); // antall misfornøyd

		data.addRows([ [ navn, f, n, m ] ]);
	}

	// bedre implementasjon
	var currentView = new google.visualization.DataView(data);

	// Set chart options
	var options;

	refresh = function() {
		count++;
		data.addRow([ 'SomeEvent: ' + count.toString(),
						Math.floor(Math.random() * 15),
						Math.floor(Math.random() * 15),
						Math.floor(Math.random() * 15) ]);
		currentView = new google.visualization.DataView(data);
		boolFornoeyd = true;
		boolNoeytral = true;
		boolMisfornoeyd = true;
		resetOptions();
		chart.draw(currentView, google.charts.Line.convertOptions(options));
	}

	resetOptions = function() {
		options = {
			'title' : 'Samlet tilbakemelding for aktivitet:',
			'width' : 900,
			'height' : 500,
			legend : 'none',
			series : {
				0 : {
					color : 'GREEN'
				},
				1 : {
					color : 'ORANGE'
				},
				2 : {
					color : 'RED'
				}
			}
		}
	};

	toggleColumn = function(integer) {
		var i = integer;
		switch (i) {
		case 1:
			boolFornoeyd === true ? (boolFornoeyd = false)
					: (boolFornoeyd = true);
			break;
		case 2:
			boolNoeytral === true ? (boolNoeytral = false)
					: (boolNoeytral = true);
			break;
		case 3:
			boolMisfornoeyd === true ? (boolMisfornoeyd = false)
					: (boolMisfornoeyd = true);
			break;
		}

		if (boolFornoeyd && !boolNoeytral && !boolMisfornoeyd) {
			showCols = [ 0, 1 ];
			options.series = {
				0 : {
					color : 'GREEN'
				}
			};
		} else if (!boolFornoeyd && boolNoeytral && !boolMisfornoeyd) {
			showCols = [ 0, 2 ];
			options.series = {
				0 : {
					color : 'ORANGE'
				}
			};
		} else if (!boolFornoeyd && !boolNoeytral && boolMisfornoeyd) {
			showCols = [ 0, 3 ];
			options.series = {
				0 : {
					color : 'RED'
				}
			};
		} else if (boolFornoeyd && boolNoeytral && !boolMisfornoeyd) {
			showCols = [ 0, 1, 2 ];
			options.series = {
				0 : {
					color : 'GREEN'
				},
				1 : {
					color : 'ORANGE'
				}
			};
		} else if (!boolFornoeyd && boolNoeytral && boolMisfornoeyd) {
			showCols = [ 0, 2, 3 ];
			options.series = {
				0 : {
					color : 'ORANGE'
				},
				1 : {
					color : 'RED'
				}
			};
		} else if (boolFornoeyd && !boolNoeytral && boolMisfornoeyd) {
			showCols = [ 0, 1, 3 ];
			options.series = {
				0 : {
					color : 'GREEN'
				},
				1 : {
					color : 'RED'
				}
			};
		} else if (!boolFornoeyd && !boolNoeytral && !boolMisfornoeyd) {
			alert("Du kan ikke velge bort alle linjene!");
		} else if (boolFornoeyd && boolNoeytral && boolMisfornoeyd) {
			showCols = [ 0, 1, 2, 3 ];
			options.series = {
				0 : {
					color : 'GREEN'
				},
				1 : {
					color : 'ORANGE'
				},
				2 : {
					color : 'RED'
				}
			};
		}
		currentView.setColumns(showCols);
		chart.draw(currentView, google.charts.Line.convertOptions(options));
	}

	// Instantiate and draw our chart, passing in some options.
	resetOptions();
	var chart = new google.visualization.LineChart(document
			.getElementById('chart_div'));
	chart.draw(data, google.charts.Line.convertOptions(options));
}