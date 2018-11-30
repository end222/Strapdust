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
    url : "ObtenerStats3.do?grupo=" + grupo,
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

// Pie Chart Example
var ctx = document.getElementById("unizar");
var myPieChart = new Chart(ctx, {
  type: 'pie',
  data: {
    labels: ["No pertenece a la Universidad de Zaragoza", "Trabaja en la Universidad de Zaragoza", "Estudia en la EINA", "Estudia en Unizar pero no en la EINA"],
    datasets: [{
      data: [resp[0], resp[1], resp[2], resp[3]],
      backgroundColor: ['#007bff', '#dc3545', '#ffc107', '#28a745'],
    }],
  },
});
