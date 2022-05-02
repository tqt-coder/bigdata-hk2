var rownum = 0;
var yline;

function loadFile(){ with (new XMLHttpRequest()) {
    onreadystatechange=cb; open('GET','/data',true); responseType='text';send();
}}

var loadFile = setInterval(loadFile, 5000);

function cb(){
    if(this.readyState===4)tbl(this.responseText);
}

function tbl(csv){
    // console.log("-->> RowNum: "+rownum+" | Data: "+csv);
    var lines = csv.split('\n');
    yline = lines[rownum].split(',');

    // console.log("-->> ["+rownum+"] yline: "+yline.length + " " + yline);

    if(yline.length > 5) {
        rownum = rownum + 1;
        addDataset();
    } else if(lines[rownum+1].split(',').length > 5) {
        rownum = rownum + 1;
    }
}

var color = Chart.helpers.color;
var barChartData = {
    labels: ['Alienware', 'Apple', 'Dell', 'Lenovo', 'Samsung', 'Sony', 'Toshiba'],
};

window.onload = function() {
    var ctx = document.getElementById('canvas').getContext('2d');
    window.myBar = new Chart(ctx, {
        type: 'bar',
        data: barChartData,
        options: {
            responsive: true,
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: 'Sales Count - Bar Chart'
            }
        }
    });

};

var colorNames = Object.keys(window.chartColors);

function addDataset(){
    var colorName = colorNames[barChartData.datasets.length % colorNames.length];
    var dsColor = window.chartColors[colorName];
    var newDataset = {
        label: 'Dataset ' + (barChartData.datasets.length + 1),
        backgroundColor: color(dsColor).alpha(0.5).rgbString(),
        borderColor: dsColor,
        borderWidth: 1,
        data: yline
    };
    barChartData.datasets.push(newDataset);
    window.myBar.update();
}

document.getElementById('stopDataset').addEventListener('click', function() {
    alert('Stop Adding Dataset...');
    clearInterval(loadFile);
});
