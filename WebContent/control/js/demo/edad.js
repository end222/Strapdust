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
    url : "ObtenerStats2.do?grupo=" + grupo,
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
var ctx = document.getElementById("edad");
var myPieChart = new Chart(ctx, {
  type: 'pie',
  data: {
    labels: ["<18", "18-25", "26-39", "40-52", "53-65", ">65"],
    datasets: [{
      data: [resp[0], resp[1], resp[2], resp[3], resp[4], resp[5]],
      backgroundColor: ['#e74c3c', '#1abc9c', '#e67e22', '#3498db' , '#34495e', '#f1c40f'],
    }],
  },
});
