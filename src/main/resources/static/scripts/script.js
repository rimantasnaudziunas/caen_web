function checkReaderIP() {
    return jQuery.ajax ({
        url: "http://192.168.1.195/getReaderIP",
        type: "GET",
        success: function(data){
            if (data == "NaN") {
                showErrorMessage();
            } else {
                formatTable();
            }
        },
        error: function() {
            showErrorMessage();
        },
        complete: function() {
            //
        }
    });
}

//function getEpcList() {
//    return new Promise((resolve, reject) => {
//        jQuery.ajax ({
//            async: false,
//            url: "http://192.168.1.195/getepclist",
//            type: "GET",
//            success: function(data){
//                resolve(data);
//            },
//            error: function(error) {
//                reject(error);
//            },
//            complete: function() {
//            }
//        });
//    })
//}

function formatTable() {
        $('#uploadedData').DataTable({
        ajax: {
            url: 'http://192.168.1.195/getepclist',
            dataSrc: ''
        },
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
        promise = send_data_to_write($(this).find('.epc_class').html());
        writeStarted($(this).find('.epc_class').html());
    });
}

function send_data_to_write(epcString) {
    var epcJSON = JSON.stringify({epc:epcString});
    return jQuery.ajax ({
        url: "http://192.168.1.195/writeEpc",
        type: "POST",
        data: epcJSON,
        contentType: "application/json; charset=utf-8",
        success: function(data){
            console.log(data);
            $('#uploadedData').DataTable().ajax.reload();
            writeFinished();
        },
        error: function(error) {
            console.log(error);
            writeFinished();
            alert(error.responseJSON.message);
        },
        complete: function() {
        }
    });
}

function writeStarted(epc) {
   divObject = $("<div id='error_background'></div>" )
        .css("position", "absolute")
        .css("background", "rgba(255,255,255,0.4)")
        .css("width", "100%")
        .css("height", "100%")
        .css("padding", "40px")
        .css("z-index", "1000");
    divMessageBox = $("<div id='error_message_box'></div>" )
        .css("border-radius", "25px")
        .css("background", "rgba(33,33,33,1)")
        .css("color", "#C0BAB6")
        .css("margin", "auto")
        .css("width", "50%")
        .css("text-align", "center")
        .css("top", "50%")
        .css("padding", "17px");
    Message = $("<h2>"+epc+"</h2>");
    divMessageBox.append(Message);
    divObject.append(divMessageBox);
    $("main").prepend( divObject );
}

function writeFinished() {
    $('#error_background').remove();
}

function showErrorMessage() {
   divObject = $("<div id='error_background'></div>" )
        .css("position", "absolute")
        .css("background", "rgba(255,255,255,0.4)")
        .css("width", "100%")
        .css("height", "100%")
        .css("padding", "40px")
        .css("z-index", "1000");
    divMessageBox = $("<div id='error_message_box'></div>" )
        .css("border-radius", "25px")
        .css("background", "rgba(33,33,33,1)")
        .css("color", "#C0BAB6")
        .css("margin", "auto")
        .css("width", "50%")
        .css("text-align", "center")
        .css("top", "50%")
        .css("padding", "8px 17px");
    Message = $("<h2>RFID reader IP not set, cannot continue.</h2>");
    Button = $("<a href='http://192.168.1.195/' id='error_message_button'>SET READER IP</a>" )
        .css("margin", "50px auto")
        .css("border", "solid 5px #FF8C00")
        .css("border-radius", "25px")
        .css("width", "130px")
        .css("color", "#FF8C00")
        .css("padding", "5px");
    divMessageBox.append(Message);
    divMessageBox.append(Button);
    divObject.append(divMessageBox);
    $("main").prepend( divObject );
}

$(document).ready(function () {
    checkReaderIP();
})

$(document).keypress(function(e) {
    if(e.which == 32) {
        send_data_to_write($('td.epc_class').html());
        writeStarted($('td.epc_class').html());
    }
});