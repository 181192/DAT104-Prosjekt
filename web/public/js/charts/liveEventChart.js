google.charts.load('current', {
	'packages' : [ 'corechart', 'bar' ]
});

google.charts.setOnLoadCallback(drawChart);

function drawChart() {
	var data = new google.visualization.DataTable();
	data.addColumn('number', 'Tilbakemelding');
	data.addColumn('number', 'Fornøyd');
	data.addColumn({
		type : 'string',
		role : 'tooltip'
	});
	data.addColumn('number', 'Nøytral');
	data.addColumn({
		type : 'string',
		role : 'tooltip'
	});
	data.addColumn('number', 'Misfornøyd');
	data.addColumn({
		type : 'string',
		role : 'tooltip'
	});
	data.addRows([ [0, 1, "hah", 2, "hah", 1, "hah"],]);

	var view = new google.visualization.DataView(data);
	view.setColumns([ 0, 1, 6 ]);
	view.setColumns([ 0, 3, 6 ]);
	view.setColumns([ 0, 5, 6 ]);

	view.setColumns([ 0, 1, 2, 3, 4, 5, 6 ]);

	var options = {
		height : 400,

		series : {
			0 : {
				type : 'bars',
				color : 'green'
			},
			1 : {
				type : 'bars',
				color : 'yellow'
			},
			2 : {
				type : 'bars',
				color : 'red'
			}
		},
		vAxis : {
			title : "Antall"
		},
		hAxis : {
			title : "Tilbakemeldinger",
			format : ""
		}
	};
	var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
	chart.draw(view, options);
}
