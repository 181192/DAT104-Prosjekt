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
       array.push(['Minutter', 'Fornøyd', 'Nøytral', 'Misfornøyd']);

       for (var i = 1; i < 60; i++) {
         array.push([i, Math.floor(Math.random() * 10 + 1), Math.floor(Math.random() * 10 + 1), Math.floor(Math.random() * 10 + 1)]);
       }

       var data = google.visualization.arrayToDataTable(array);

       // Create a dashboard.
       var dashboard = new google.visualization.Dashboard(
         document.getElementById('dashboard_div'));

       // Create a range slider, passing some options
       var donutRangeSlider = new google.visualization.ControlWrapper({
         'controlType': 'ChartRangeFilter',
         'containerId': 'filter_div',
         'options': {
           'filterColumnLabel': 'Minutter',
           'ui': {
             chartType: 'LineChart',
             snapToData: 'true',
             minRangeSize: 5,
             chartOptions: {
               pointSize: 2,
               series: {
                 0: {
                   color: 'GREEN'
                 },
                 1: {
                   color: 'ORANGE'
                 },
                 2: {
                   color: 'RED'
                 }
               }
             }
           }


         }
       });

       // Create a column chart, passing some options
       var columnChart = new google.visualization.ChartWrapper({
         'chartType': 'ColumnChart',
         'containerId': 'chart_div',
         'options': {
           animation: {
             'startup': true,
             'duration': 1000,
             'easing': 'inAndOut'
           },
           annotation: {
             'column_id': {
               style: 'line'
             }
           },
           vAxis: {
             title: 'Antall'
           },
           hAxis: {
             title: 'Minutter',
             titleTextStyle: {
               color: 'BLACK'
             },
             textPosition: 'out'
           },
           explorer: {
             keepInBounds: true
           },
           'title': 'Resultat for event',
           'subtitle': 'Se nærmere på tilbakemeldingene dine',
           'width': 1240,
           'height': 300,
           'legend': 'right',
           'colors': ['GREEN', 'ORANGE', 'RED']
         }
       });

       // Establish dependencies, declaring that 'filter' drives 'columnChart',
       // so that the column chart will only display entries that are let through
       // given the chosen slider range.
       dashboard.bind(donutRangeSlider, columnChart);

       // Draw the dashboard.
       dashboard.draw(data);
     }