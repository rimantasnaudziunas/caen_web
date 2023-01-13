var list;
function fetchDataFromServer(){
    console.log("fetchDataFromServer -> start");
    fetch("http://192.168.1.195:8080/caen/getepclist")
        .then(response => { return response.json();} )
        .then(responseData => { console.log(responseData); return responseData; } )
        .then(responseData => formatTable(responseData));
    console.log("fetchDataFromServer -> finish");
}
function formatTable(tableData) {
    console.log("tableData -> start");
    $('#uploadedData').DataTable( {
        data : tableData,
        columns: [
            { data: "epc" },
            { data: "name" }
        ]
    } );
    console.log("tableData -> finish");
}

$(document).ready(function(){
    fetchDataFromServer();
})
