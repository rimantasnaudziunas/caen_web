var list;
function fetchDataFromServer() {
    console.log("fetchDataFromServer -> start");
    fetch("http://192.168.1.195/getepclist", { mode: 'no-cors' })
        .then(response => { return response.json(); })
        .then(responseData => { console.log(responseData); return responseData; })
        .then(responseData => formatTable(responseData));
    console.log("fetchDataFromServer -> finish");
}

function formatTable(tableData) {
    console.log("tableData -> start");
    $('#uploadedData').DataTable({
        data: tableData,
        columnDefs: [
            {
                targets: 0,
                className: 'epc_class'
            }
        ],
        columns: [
            { data: "epc" },
            { data: "name" }
        ]
    });
    $('#uploadedData tbody').on('click', 'tr', function () {
        console.log($(this).find('.epc_class').html());
        //location.href = 'http://192.168.1.195/write?epc=' + $(this).find('.epc_class').html();
        var epcJSON = JSON.stringify({epc:$(this).find('.epc_class').html()});
        console.log(epcJSON);
        jQuery.ajax ({
            url: "http://192.168.1.195/writeEpc",
            type: "POST",
            data: epcJSON,
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function(){
                console.log(epcJSON+" - nusiusta");
            }
        });
    });
    $('#uploadedData').DataTable().ajax.reload();
    console.log("tableData -> finish");
}


$(document).ready(function () {
    fetchDataFromServer();
})

$('#write').click(function(e){
    jQuery.ajax ({
        url: "http://192.168.1.195/writeEpc",
        type: "POST",
        data: "{epc:123}",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function(){
            //
        }
    });
});
