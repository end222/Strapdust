// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Pie Chart Example
var ctx = document.getElementById("edad");
var myPieChart = new Chart(ctx, {
  type: 'pie',
  data: {
    labels: ["<18", "18-25", "26-39", "40-52", "53-65", ">65"],
    datasets: [{
      data: [12.21, 15.58, 11.25, 8.32, 17, 12],
      backgroundColor: ['#e74c3c', '#1abc9c', '#e67e22', '#3498db' , '#34495e', '#f1c40f'],
    }],
  },
});
