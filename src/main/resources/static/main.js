var rownum = 0;
var yline;

// mảng lồng mảng
// let arrResult = [["AVpgMuGwLJeJML43KY_c","69.0","In Stock","USD","Walmart.com","\"B018K251JE","B00VILQKQ8\"","Boytone",
//     "\"Stereos","Portable Bluetooth Speakers","TV"," Video & Home Audio","Speaker Systems","Portable Audio & Video"],
//     ["AVpgMuGwLJeJML43KY_c","69.0","In Stock","USD","Walmart.com","\"B018K251JE","B00VILQKQ8\"","Boytone","\"Stereos","Portable Bluetooth Speakers",
//         "TV"," Video & Home Audio","Speaker Systems","Portable Audio & Video"],["AVpgMuGwLJeJML43KY_c","69.99","Yes","USD","Bestbuy.com","\"B018K251JE"
//         ,"B00VILQKQ8\"","Boytone","\"Stereos","Portable Bluetooth Speakers","TV"," Video & Home Audio","Speaker Systems","Portable Audio & Video"]
//     ,["AVpgMuGwLJeJML43KY_c","66.99","Yes","USD","Bestbuy.com","\"B018K251JE","B00VILQKQ8\"","Boytone","\"Stereos","Portable Bluetooth Speakers",
//         "TV"," Video & Home Audio","Speaker Systems","Portable Audio & Video"],["AVpgMuGwLJeJML43KY_c","66.0","In Stock","USD","Walmart.com",
//         "\"B018K251JE","B00VILQKQ8\"","Boytone","\"Stereos","Portable Bluetooth Speakers","TV"," Video & Home Audio","Speaker Systems"
//         ,"Portable Audio & Video"],["AVpgMuGwLJeJML43KY_c","74.99","In Stock","USD","California Electronics","\"B018K251JE","B00VILQKQ8\"",
//         "Boytone","\"Stereos","Portable Bluetooth Speakers","TV"," Video & Home Audio","Speaker Systems","Portable Audio & Video"],
//     ["AVpgMuGwLJeJML43KY_c","65.99","Yes","USD","Bestbuy.com","\"B018K251JE","B00VILQKQ8\"","Boytone","\"Stereos","Portable Bluetooth Speakers","TV",
//         " Video & Home Audio","Speaker Systems","Portable Audio & Video"],["AVpgMuGwLJeJML43KY_c","69.99","In Stock","USD","Walmart.com","\"B018K251JE",
//         "B00VILQKQ8\"","Boytone","\"Stereos","Portable Bluetooth Speakers","TV"," Video & Home Audio","Speaker Systems","Portable Audio & Video"],
//     ["AVpgMuGwLJeJML43KY_c","69.0","In Stock","USD","Walmart.com","\"B018K251JE","B00VILQKQ8\"","Boytone","\"Stereos","Portable Bluetooth Speakers","TV",
//         " Video & Home Audio","Speaker Systems","Portable Audio & Video"]]


function loadFile(){ with (new XMLHttpRequest()) {
    onreadystatechange=cb; open('GET','/data',true); responseType='text';send();
}}

var loadFile = setInterval(loadFile, 5000);

function cb(){
    if(this.readyState===4) tbl(this.responseText);
}

function tbl(csv) {
    let arrResult = JSON.parse(csv);

    // [id,name,sl, dm]
    let s ="<table>\n" +
        "        <tr>\n" +
        "            <th>product_id</th>\n" +
        "            <th>price</th>\n" +
        "            <th>stock</th>\n" +
        "            <th>currency</th>\n" +
        "            <th>brand</th>\n" +
        "            <th>serial_number</th>\n" +
        "            <th>product</th>\n" +
        "            <th>category</th>\n" +
        "            <th>created_on</th>\n" +
        "            <th>updated_on</th>\n" +
        "            <th>model_number</th>\n" +
        "            <th>product_name</th>\n" +
        "            <th>category_type</th>\n" +
        "            <th>weight</th>\n </tr>" ;
    arrResult.forEach((data,index)=>{
        s = s + " <tr> ";
        data.forEach((dt)=>{
            s = s + ` <td> ${dt}</td> `;
        })
        s= s+ " </tr> "
    })

    s = s + "</table>";
    var ctx = document.getElementById('canvas');
    ctx.innerHTML = s;

}
//     // console.log("-->> RowNum: "+rownum+" | Data: "+csv);
//     console.log(csv)
//     var lines = csv.split('\n');
//     yline = lines[rownum].split(',');
//
//     // console.log("-->> ["+rownum+"] yline: "+yline.length + " " + yline);
//
//     if(yline.length > 5) {
//         rownum = rownum + 1;
//         addDataset();
//     } else if( lines[rownum+1].split(',').length > 5 ) {
//         rownum = rownum + 1;
//     }
// }
//
// var color = Chart.helpers.color;
// var barChartData = {
//     labels: ['Alienware', 'Apple', 'Dell', 'Lenovo', 'Samsung', 'Sony', 'Toshiba'],
// };
//
// window.onload = function() {
//     var ctx = document.getElementById('canvas').getContext('2d');
//     window.myBar = new Chart(ctx, {
//         type: 'bar',
//         data: barChartData,
//         options: {
//             responsive: true,
//             legend: {
//                 position: 'top',
//             },
//             title: {
//                 display: true,
//                 text: 'Sales Count - Bar Chart'
//             }
//         }
//     });
//
// };
//
// var colorNames = Object.keys(window.chartColors);
//
// function addDataset(){
//     var colorName = colorNames[barChartData.datasets.length % colorNames.length];
//
//     var dsColor = window.chartColors[colorName];
//     var newDataset = {
//         label: 'Dataset ' + (barChartData.datasets.length + 1),
//         backgroundColor: color(dsColor).alpha(0.5).rgbString(),
//         borderColor: dsColor,
//         borderWidth: 1,
//         data: yline
//     };
//     // console.log(newDataset)
//     // let kq = barChartData.labels.reduce((cal,da,index)=>{
//     //    let inforObj = {};
//     //    inforObj.field = da;
//     //    inforObj.value = newDataset.data[index];
//     //
//     //    return cal.push(inforObj)
//     // },[])
//     let inforObj = {};
//     inforObj.field =barChartData.labels;
//    var a =  barChartData.datasets.reduce((cal,da,index)=>{
//       return [...cal,da.data];
//     },[]);
//    console.log(a);
//     barChartData.datasets.push(newDataset);
//     window.myBar.update();
// }
//
document.getElementById('stopDataset').addEventListener('click', function() {
    alert('Stop Adding Dataset...');
    clearInterval(loadFile);
});
