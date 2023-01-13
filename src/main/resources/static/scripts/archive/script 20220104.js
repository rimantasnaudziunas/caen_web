var l;
l = '[{"epc":"epc1","name":"1pavadinimas1"},{"epc":"epc2","name":"2pavadinimas2"}]';
var list;
function fetchDataFromServer(){
    fetch("http://192.168.1.195:8080/caen/getepclist")
        .then(response => { return response.json();} )
        .then(responseData => { console.log(responseData); return responseData; } );
}


function formatTable(tableData) {
    $('#example').DataTable( {
        data : tableData,
        columns: [
            { data: "epc" },
            { data: "name" }
        ]
    } );
}

$('#btn2').click(function(){
    formatTable(JSON.parse(JSON.stringify(list)));
})
