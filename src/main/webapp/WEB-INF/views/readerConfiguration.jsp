<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Upload EPC file</title>
    <link rel="stylesheet" type="text/css" href="DataTables/datatables.min.css"/>
    <link rel="stylesheet" href="<c:url value="style/style.css" />" />
    <link rel="stylesheet" href="<c:url value="style/normalize.css" />" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script type="text/javascript" src="DataTables/jQuery3.6.1/jQuery-3.6.1.min.js"></script>
    <script type="text/javascript" src="scripts/readerconfiguration.js" defer></script>
<body>
    <header>
          <%@ include file="common/header.jsp" %>
    </header>
    <main>
        <div class="container-md">
            <section>
                <h1>READER CONFIGURATION</h1>
            </section>
            <section>
                <form:form method="POST" modelAttribute="ReaderConfDto" enctype="multipart/form-data" id="configForm">
<!--
                    <div style="margin:5px;">
                        <label for="ip" >IP address:</label>
                        <form:input type="text" pattern="^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$" path="ip" class="form-control mb-3" required="true" />
                    </div>
-->
                    <div style="margin:5px;">
                        <label for="power" >Power:</label>
                        <form:input type="number" min="5" max="1400" path="power" class="form-control mb-3" value="100" id="readerPower"/>
                    </div>
<!--
                    <div style="margin:5px;">
                        <label for="timeBetweenInventories" >Time between inventories (ms):</label>
                        <form:input type="number" path="timeBetweenInventories" class="form-control mb-3" />
                    </div>
                    <div style="margin:5px;">
                        <label for="timeBetweenAntennas" >Time between antennas (ms):</label>
                        <form:input type="number" path="timeBetweenAntennas" class="form-control mb-3" />
                    </div>
                    <div style="margin:5px;">
                        <label for="antennaReadTime" >Antenna read time (ms):</label>
                        <form:input type="number" path="antennaReadTime" class="form-control mb-3" />
                    </div>

                    <div style="margin:5px;">
                        <label for="antennasToUse" >Antennas to use:</label>
                        <form:input type="text" path="antennasToUse" class="form-control mb-3" />
                    </div>

                    <div style="margin:5px;">
                        <label for="session" >Session:</label>
                        <select name="session" path="session" class="form-control mb-3" />
                            <option value="s0">S0</option>
                            <option value="s1">S1</option>
                            <option value="s2">S2</option>
                            <option value="s3">S3</option>
                        </select>
                    </div>
                    <div style="margin:5px;">
                        <label for="q" >Q:</label>
                        <form:input type="number" path="q" class="form-control mb-3" />
                    </div>
-->
                    <div style="margin:5px;">
                        <input type="submit" value="Upload" id="uploadBtn" >
                    </div>
                </form:form>
            </section>
        </div>
    </main>
    <footer style="position:fixed; bottom:0; width:100%">
      <%@ include file="common/footer.jsp" %>
    </footer>
</body>
</html>
