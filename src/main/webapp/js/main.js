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
 

 
 
 
 
 
 
 
 
 
 
 