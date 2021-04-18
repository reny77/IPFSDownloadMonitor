$(document).ready(function () {  
	objectInfoData();
	
	setInterval(function() {
		// update data
		updateData();
	}, 1000);	
	
});

function objectInfoData() {
	$.ajax({
        url: context + "objectinfo"
    }).then(function(data) {
       $('.cidobjectinfo').html(data.cid);
       $('.hashobjectinfo').html(data.hash);
       $('.startDateobjectinfo').html(data.start);
       $('.typeobjectinfo').html(data.type);
       $('.sizeHumanReadbleobjectinfo').html(data.sizeHumanReadble);
       
    });	  
}

function updateData() {
	$.ajax({
        url: context + "downloadstats"
    }).then(function(data) {
       $('.peersConnecteddownloadstats').html(data.peersConnected);
       $('.contributordownloadstats').html(data.contributor);
       $('.recivedTotaldownloadstats').html(data.recivedTotal);
       updatePie(data.pieData);
       redrawMap(data.mapDataList);
       redrawLineChart(data.kbProgress);
       if (!data.runDownload) {
       		objectInfoData();
       }
    });	  
    
    contributorsTable.ajax.reload();	  
    
   
}

function updatePie(donutData) {

    $.plot('#donut-chart', donutData, {
      series: {
        pie: {
          show       : true,
          radius     : 1,
          innerRadius: 0.5,
          label      : {
            show     : true,
            radius   : 2 / 3,
            formatter: labelFormatter,
            threshold: 0.1,
               formatter: function(label, series){
                        return '<div style="font-size:6pt;text-align:center;padding:2px;color:white;">'+label+'<br/>'+Math.round(series.percent)+'%</div>';
                    },
                    background: { opacity: 0.8 }
          },
          

        }
      },
      legend: {
        show: false
      }
    })
}

var donutData = [
      { label: '', data: 100, color: '#ffffff' }
    ]
     $.plot('#donut-chart', donutData, {
      series: {
        pie: {
          show       : true,
          radius     : 1,
          innerRadius: 0.5,
          label      : {
            show     : true,
            radius   : 2 / 3,
            formatter: labelFormatter,
            threshold: 0.1,
               formatter: function(label, series){
                        return '<div style="font-size:6pt;text-align:center;padding:2px;color:white;">'+label+'<br/>'+Math.round(series.percent)+'%</div>';
                    },
                    background: { opacity: 0.8 }
          },
        }
      },
      legend: {
        show: false
      }
    })
 

$('#maprefresh').on('click', function (e) {
	$.ajax({
        url: context + "downloadstats"
    }).then(function(data) {
    	redrawMap(data.mapDataList);
    });		
})
 
 function redrawMap(input) {
 	$('#world-map-markers').empty();
	var worldmapmarkers = null;
 	/* jVector Maps
   * ------------
   * Create a world map with markers
   */
  worldmapmarkers = $('#world-map-markers').vectorMap({
    map              : 'world_mill_en',
    normalizeFunction: 'polynomial',
    hoverOpacity     : 0.7,
    hoverColor       : false,
    backgroundColor  : 'transparent',
    regionStyle      : {
      initial      : {
        fill            : 'rgba(210, 214, 222, 1)',
        'fill-opacity'  : 1,
        stroke          : 'none',
        'stroke-width'  : 0,
        'stroke-opacity': 1
      },
      hover        : {
        'fill-opacity': 0.7,
        cursor        : 'pointer'
      },
      selected     : {
        fill: 'yellow'
      },
      selectedHover: {}
    },
    markerStyle      : {
      initial: {
        fill  : '#00a65a',
        stroke: '#111'
      }
    },
    markers: input 
  });
 }
 
 $('#progressrefresh').on('click', function (e) {
	$.ajax({
        url: context + "downloadstats"
    }).then(function(data) {
    	redrawLineChart(data.kbProgress);
    });		
})
 function redrawLineChart(data) {
  /*
     * LINE CHART
     * ----------
     */
    //LINE randomly generated data

    var dataline = []
    for (var i = 0; i < data.length; i += 1) {
      dataline.push([i*10, data[i]])
    }
    
    
    var line_data1 = {
      data : dataline,
      color: '#3c8dbc'
    }
    
    $('#line-chart').empty();
    var linechart = null;
    linechart = $.plot('#line-chart', [line_data1], {
      grid  : {
        hoverable  : false,
        borderColor: '#f3f3f3',
        borderWidth: 1,
        tickColor  : '#f3f3f3'
      },
      series: {
        shadowSize: 0,
        lines     : {
          show: true
        },
        points    : {
          show: false
        }
      },
      lines : {
        fill : false,
        color: ['#3c8dbc', '#f56954']
      },
      yaxis : {
        show: true,
        label: "Kb download",
      },
      xaxis : {
        show: true,
        label: "Time (10s)",
      }
    })
    //Initialize tooltip on hover
    $('<div class="tooltip-inner" id="line-chart-tooltip"></div>').css({
      position: 'absolute',
      display : 'none',
      opacity : 0.8
    }).appendTo('body')
    $('#line-chart').bind('plothover', function (event, pos, item) {

      if (item) {
        var x = item.datapoint[0].toFixed(2),
            y = item.datapoint[1].toFixed(2)

        $('#line-chart-tooltip').html(item.series.label + ' of ' + x + ' = ' + y)
          .css({ top: item.pageY + 5, left: item.pageX + 5 })
          .fadeIn(200)
      } else {
        $('#line-chart-tooltip').hide()
      }

    })
    /* END LINE CHART */
 }
 
 
 var contributorsTable ;
  $(function () {
	  contributorsTable = $('#contributorsTable').DataTable( {
	        "ajax": context + "contributors",
	        "columns": [
	        	{ "data": "exchanged" },
	            { "data": "countryCode" },
	            { "data": "countryName" },
	            { "data": "city" },
	            { "data": "ip" },
	            { "data": "peer" },
	            { "data": "recv" },
	            { "data": "sent" }
	        ],
	        "order": [[ 0, "desc" ]],
	        "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull) {
   				$('td:eq(1)', nRow).html('<img src="https://www.countryflags.io/' + aData.countryCode + '/flat/16.png">');
   			}
	    } );
  })
 
 
 
 
 