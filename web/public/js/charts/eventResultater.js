     // Load the Visualization API and the controls package.
     google.charts.load('current', {
       'packages': ['corechart', 'controls']
     });

     // Set a callback to run when the Google Visualization API is loaded.
     google.charts.setOnLoadCallback(drawDashboard);

     // Callback that creates and populates a data table,
     // instantiates a dashboard, a range slider and a pie chart,
     // passes in the data and draws it.
     function drawDashboard() {

       // Create our data table.
       var array = [];
       array.push(['Minutter', 'Fornoyd', 'Noytral', 'Misfornoyd']);

       for (var i = 1; i < 200; i++) {
         array.push([i, Math.floor(Math.random() * 10 + 1), Math.floor(Math.random() * 10 + 1), Math.floor(Math.random() * 10 + 1)]);
       }

       var data = google.visualization.arrayToDataTable(array);

       var view = new google.visualization.DataView(data);
       
       // Create a dashboard.
       var dashboard = new google.visualization.Dashboard(
         document.getElementById('dashboard_div'));


       // Create a range slider, passing some options
       var donutRangeSlider = new google.visualization.ControlWrapper({
         'controlType': 'NumberRangeFilter',
         'containerId': 'filter_div',
         'options': {
           'filterColumnLabel': 'Minutter'
         }
       });

       // Create a column chart, passing some options
       var columnChart = new google.visualization.ChartWrapper({
         'chartType': 'ColumnChart',
         'containerId': 'chart_div',
         'options': {
           colors: ['green', 'orange', 'red'],
           'animation': {
             "startup": true,
             'duration': '1000',
             'easing': 'out',
           }
         }
       });

       var bytt = false;
       switchView = function() {
         if (bytt === false) {
           columnChart.setChartType('LineChart');
           dashboard.draw(view);
           bytt = true;
         } else {
           columnChart.setChartType('ColumnChart');
           dashboard.draw(view);
           bytt = false;
         }
       };

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
         dashboard.draw(view);
       }

       showColumn = function(id, farge) {
         view.setColumns([0, id]);
         columnChart.setOptions({
           colors: [farge],
           'animation': {
             "startup": true,
             'duration': '1000',
             'easing': 'out',
           }
         });
         dashboard.draw(view);
       }



       // Establish dependencies, declaring that 'filter' drives 'columnChart',
       // so that the column chart will only display entries that are let through
       // given the chosen slider range.
       dashboard.bind(donutRangeSlider, columnChart);

       // Draw the dashboard.
       if (view === null) {
         dashboard.draw(data);
       } else {
         dashboard.draw(view);
       }
     }