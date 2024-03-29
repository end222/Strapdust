// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

var grupo =  getUrlVars()["grupo"];
$.ajax({
    url : "ObtenerStats1.do?grupo=" + grupo,
    async : false,
    type : 'GET',
    dataType : 'json', 
    success : function(response) {
        //response = $.parseJSON(response);
        resp = response;
    },
    error : function(error) {
        //error handling....
        alert(error);
    }
})

var ctx = document.getElementById("aciertos");
var myLineChart = new Chart(ctx, {
  type: 'bar',
  data: {
    labels: ["Pregunta 1", "Pregunta 2", "Pregunta 3"],
    datasets: [{
      label: "Pregunta",
      backgroundColor: "rgba(2,117,216,1)",
      borderColor: "rgba(2,117,216,1)",
      data: [resp[0],resp[1],resp[2]],
    }],
  },
  options: {
    scales: {
      xAxes: [{
        time: {
          unit: 'month'
        },
        gridLines: {
          display: false
        },
        ticks: {
          maxTicksLimit: 6
        }
      }],
      yAxes: [{
        ticks: {
          min: 0,
          max: 100,
          maxTicksLimit: 5
        },
        gridLines: {
          display: true
        }
      }],
    },
    legend: {
      display: false
    }
  }
});
