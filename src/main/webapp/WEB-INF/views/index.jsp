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
    <script type="text/javascript" src="DataTables/datatables.min.js"></script>
<body>
    <header>
          <%@ include file="common/header.jsp" %>
    </header>
    <main>
        <div class="container-md">
            <section>
                <h1>CONNECT TO READER</h1>
            </section>
            <section>
                <form:form method="POST" modelAttribute="ip" enctype="multipart/form-data">
                    <div style="margin:5px;">
                        <label for="ip" >IP address:</label>
                        <form:input type="text" pattern="^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$" path="ip" class="form-control mb-3" required="true" />
                    </div>
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
