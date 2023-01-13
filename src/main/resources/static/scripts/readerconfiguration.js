function checkReaderIP() {
    return jQuery.ajax ({
        url: "http://192.168.1.195/getReaderIP",
        type: "GET",
        success: function(data){
            if (data == "NaN") {
                showErrorMessage();
            } else {
                fetchDataFromServer();
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

function fetchDataFromServer() {
    $.getJSON( "http://192.168.1.195/getReaderSettings", function( data ) {
        $('#readerPower').val(data.power);
    });
}

function prepareDataToSend () {
    return JSON.stringify({ip:null,power:$('#readerPower').val(),timeBetweenInventories:null,timeBetweenAntennas:null,antennaReadTime:null,antennasToUse:null,session:null,q:null,status:null})
}

$(document).ready(function () {
    checkReaderIP();
});

$('#configForm').on('submit', function(e){
    e.preventDefault();
    jQuery.ajax ({
        url: "http://192.168.1.195/setReaderSettings",
        type: "POST",
        data: prepareDataToSend(),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function(){
            //
        }
    });
});

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





