// Load the Visualization API and the controls package.
google.charts.load('current', {
  'packages': ['corechart', 'controls']
});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(runIfEnabled);

//Bruker denne variablen for å tillate/stoppe skript fra å bli eksekvert
var enable = false;

function runIfEnabled(){
	if (enable === true){
		drawDashboard();
	}
}

// Callback that creates and populates a data table,
// instantiates a dashboard, a range slider and a pie chart,
// passes in the data and draws it.
function drawDashboard() {

  //Henter data fra jsp via controller
  var data = google.visualization.arrayToDataTable(array);

  //Views kontrollerer hvilken kolonne som skal vises
  var view = new google.visualization.DataView(data);

  //Dashboard er en wrapper som inneholder filter og graf
  var dashboard = new google.visualization.Dashboard(
    document.getElementById('dashboard_div'));

  //maks timer
  var prevMin;
  var prevMax;

  //Range slider
  var donutRangeSlider = new google.visualization.ControlWrapper({
    'controlType': 'DateRangeFilter',
    'containerId': 'filter_div',
    'options': {
      'filterColumnLabel': 'Tid',
      'ui': {
        'format': {
          'pattern': 'hh:mm:SS'
        },
        'step': '1 minute',
        'unitIncrement': '1 minute',
        'label': 'Tidsinterval:'
      }
    }
  });

  //Instillinger for grafen
  var options = {
    'chartType': 'ColumnChart',
    'containerId': 'chart_div',
    'options': {
      colors: ['green', 'orange', 'red'],
      'animation': {
        "startup": true,
        'duration': '500',
        'easing': 'out',
      },
      hAxis: {
        format: 'hh:MM',
        slantedText: false
      }
    }
  };

  //instantsierer grafen, tar instillingene som parameter
  var columnChart = new google.visualization.ChartWrapper(options);


  // Every time the table fires the "select" event, it should call your
  // selectHandler() function.
  google.visualization.events.addListener(donutRangeSlider, 'statechange', selectHandler);
  google.visualization.events.addOneTimeListener(donutRangeSlider, 'ready', myPageEventHandler);

  //Tar vare på max timer
  function myPageEventHandler(e) {
    maxDateTime = donutRangeSlider.getState().highValue;
  }

  function selectHandler(e) {
    var sliderState = donutRangeSlider.getState();

    if(sliderState.highValue.getHours() == maxDateTime.getHours()){
      sliderState.highValue.setMinutes(sliderState.highValue.getMinutes()-1);
      donutRangeSlider.setState({
        'lowValue': sliderState.lowValue,
        'highValue': sliderState.highValue
      });
    }

    if (sliderState.highValue - sliderState.lowValue === 0) {
      var mins = sliderState.lowValue.getMinutes();
      var a = (mins + 1) % 60;
      var b = Math.floor(mins / 60);

      if (b > 0) {

        sliderState.highValue.setHours(sliderState.highValue.getHours() + b);
        sliderState.highValue.setMinutes(sliderState.highValue.getMinutes() + a)
        donutRangeSlider.setState({
          'lowValue': sliderState.lowValue,
          'highValue': sliderState.highValue
        });

      } else {
        sliderState.highValue.setMinutes(a)
        donutRangeSlider.setState({
          'lowValue': sliderState.lowValue,
          'highValue': sliderState.highValue
        });
      }
      dashboard.draw(view);
    }
  }

  //Funksjon som bytter mellom linjegraf og kolonnegraf
  var bytt = false;
  switchView = function() {

    if (bytt === false) {
      columnChart.setChartType('LineChart');
      google.visualization.events.trigger(donutRangeSlider, 'statechange', selectHandler);
      dashboard.draw(view);
      bytt = true;

    } else {
      columnChart.setChartType('ColumnChart');
      google.visualization.events.trigger(donutRangeSlider, 'statechange', selectHandler);
      dashboard.draw(view);
      bytt = false;
    }
  };

  //Funksjon som bytter view til alle kolonnene i grafen
  showAll = function() {
    view.setColumns([0, 1, 2, 3]);
    columnChart.setOptions({

      colors: ['green', 'orange', 'red'],
      'animation': {
        "startup": true,
        'duration': '500',
        'easing': 'out',
      }

    });
    google.visualization.events.trigger(donutRangeSlider, 'statechange', selectHandler);
    dashboard.draw(view);
  }

  //Funksjon som tar imot id til kolonne og farge via knappene i jsp-filen
  //Bytter view slik at man kan se hver kolonne for seg om gangen
  showColumn = function(id, farge) {
    view.setColumns([0, id]);
    columnChart.setOptions({

      colors: [farge],
      'animation': {
        "startup": true,
        'duration': '500',
        'easing': 'out',
      }

    });
    google.visualization.events.trigger(donutRangeSlider, 'statechange', selectHandler);
    dashboard.draw(view);
  }

  // Establish dependencies, declaring that 'filter' drives 'columnChart',
  // so that the column chart will only display entries that are let through
  // given the chosen slider range.
  dashboard.bind(donutRangeSlider, columnChart);

  // Tegner data hvis view ikke har instillinger...
  if (view === null) {
    dashboard.draw(data);
  } else {
    dashboard.draw(view);
  }
}
