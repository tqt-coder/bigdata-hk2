function loadFile() {
    with (new XMLHttpRequest()) {
        onreadystatechange = cb;
        open('GET', '/data2', true);
        responseType = 'text';
        send();
    }
}

var loadFile = setInterval(loadFile, 1000);

function cb() {
    if (this.readyState === 4) tbl(this.responseText);
}


function tbl(csv) {
    let labelColors = [];
    let arrData = [];
    let arrBackGroundColor = [];
    let arrResult = JSON.parse(csv);

    arrResult.forEach((data, index) => {
        labelColors.push(data[0]);
        arrData.push(data[1]);
        let r = Math.floor(Math.random() * 255);
        let g = Math.floor(Math.random() * 255);
        let b = Math.floor(Math.random() * 255);

        let strColorRGB = `rgb(${r}, ${g}, ${b})`;
        arrBackGroundColor.push(strColorRGB);

    })
    const data = {
        labels: labelColors,
        datasets: [{
            label: 'Đồ thị doanh số bán hàng',
            data: arrData,
            backgroundColor: arrBackGroundColor,
            hoverOffset: 4
        }]
    };

    const config = {
        type: 'line',
        data: data,
        options: {}
    };

    const myChart3 = new Chart(
        document.getElementById('myChart'),
        config
    );
    // myChart.destroy();
}



