<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload EPC file</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="scripts/script.js" defer type="text/javascript"></script>

    <!-- Include one of jTable styles. -->
    <link href="scripts/jtable.2.4.0/themes/metro/blue/jtable.min.css" rel="stylesheet" type="text/css" />
    <!-- Include jTable script file. -->
    <script src="scripts/jtable.2.4.0/jquery.jtable.min.js" type="text/javascript"></script>

    <script>
            var list;

            fetch("http://192.168.1.195:8080/caen/getepclist")
                                .then(response => response.json())
                                .then(responseJson => list = responseJson)
                                .then(constructTable('#table'));

            function constructTable(selector) {


                // Getting the all column names
                var cols = Headers(list, selector);

                // Traversing the JSON data
                for (var i = 0; i < list.length; i++) {
                    var row = $('<tr/>');
                    for (var colIndex = 0; colIndex < cols.length; colIndex++)
                    {
                        var val = list[i][cols[colIndex]];

                        // If there is any key, which is matching
                        // with the column name
                        if (val == null) val = "";
                            row.append($('<td/>').html(val));
                    }

                    // Adding each row to the table
                    $(selector).append(row);
                }
            }

            function Headers(list, selector) {
                var columns = [];
                var header = $('<tr/>');

                for (var i = 0; i < list.length; i++) {
                    var row = list[i];

                    for (var k in row) {
                        if ($.inArray(k, columns) == -1) {
                            columns.push(k);

                            // Creating the header
                            header.append($('<th/>').html(k));
                        }
                    }
                }

                // Appending the header to the table
                $(selector).append(header);
                    return columns;
            }
        </script>
</head>


<body>
	<section>
		<h1>EPC FILE UPLOAD INTERFACE</h1>
	</section>
	<section>
        <form:form method="POST" modelAttribute="epcFile" enctype="multipart/form-data">
            <div style="margin:5px;">
                <label for="file" >File</label>
                <form:input type="file" path="file" required="true" />
            </div>
            <div style="margin:5px;">
                <input type="submit" value="Save" >
            </div>
        </form:form>
    </section>
    <p id="GFG_UP"></p>
    <section>
        <table align = "center" id="table" border="1">
        </table>
    </section>
                <input type="submit" value="btn" onClick="constructTable('#table')">
</body>
</html>