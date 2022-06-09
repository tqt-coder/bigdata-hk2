var rownum = 0;
var yline;


function loadFile() {
    with (new XMLHttpRequest()) {
        onreadystatechange = cb;
        open('GET', '/data2', true);
        responseType = 'text';
        send();
    }
}

var loadFile = setInterval(loadFile, 5000);

function cb() {
    if (this.readyState === 4) tbl(this.responseText);
}

function tbl(csv) {
    let arrResult = JSON.parse(csv);

    // [id,name,sl, dm]
    let s = "<table>\n" +
        "        <tr>\n" +

        "            <th>product</th>\n" +
        "            <th>quantity</th>\n";

    arrResult.forEach((data, index) => {
        s = s + " <tr> ";
        data.forEach((dt) => {
            s = s + ` <td> ${dt}</td> `;
        })
        s = s + " </tr> "
    })

    s = s + "</table>";
    var ctx = document.getElementById('canvas2');
    ctx.innerHTML = s;

}

document.getElementById('stopDataset').addEventListener('click', function () {
    alert('Stop Adding Dataset...');
    clearInterval(loadFile);f
});
