// Load the Visualization API and the controls package.
google.charts.load('current', {
  'packages': ['corechart', 'bar']
});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawDashboard);


// Callback that creates and populates a data table,
// instantiates a dashboard, a range slider and a pie chart,
// passes in the data and draws it.
function drawDashboard() {

  // Henter data fra jsp via controller
  var data = google.visualization.arrayToDataTable(array);

  // Views kontrollerer hvilken kolonne som skal vises
  var view = new google.visualization.DataView(data);

  // Instillinger for grafen
  var options = {
      colors: ['green', 'orange', 'red'],
      vAxis: {
    	  format : "",
    	  gridlines: { count: 1 }
      },
      hAxis:{
    	  format : ""
      }
  };

  // instantsierer grafen, tar instillingene som parameter
  var columnChart = new google.charts.Bar(document.getElementById('chart_div'));


  // Every time the table fires the "select" event, it should call your
  // selectHandler() function.
//  google.visualization.events.addListener(donutRangeSlider, 'statechange', selectHandler);
//  google.visualization.events.addOneTimeListener(donutRangeSlider, 'ready', myPageEventHandler);

 

// Funksjon som bytter view til alle kolonnene i grafen
showAll = function() {
  view.setColumns([0, 1, 2, 3]);
  columnChart.setOptions({

    colors: ['green', 'orange', 'red'],
    'animation': {
      "startup": true,
      'duration': '1000',
      'easing': 'out',
    }

  });
  columnChart.draw(view, google.charts.Bar.convertOptions(options));
}

// Funksjon som tar imot id til kolonne og farge via knappene i jsp-filen
// Bytter view slik at man kan se hver kolonne for seg om gangen
showColumn = function(id, farge) {
  view.setColumns([0, id]);
  columnChart.setOptions({

    colors: [farge],
    'animation': {
      "startup": true,
      'duration': '1000',
      'easing': 'out',
    },interpolateNulls: false

  });
  columnChart.draw(view, google.charts.Bar.convertOptions(options));
}


// Tegner data hvis view ikke har instillinger...
if (view === null) {
	columnChart.draw(data, google.charts.Bar.convertOptions(options));
} else {
	columnChart.draw(view, google.charts.Bar.convertOptions(options));
}
}