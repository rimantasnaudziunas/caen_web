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
    <script type="text/javascript" src="scripts/script.js" defer></script>
    <script type="text/javascript" src="DataTables/datatables.min.js"></script>
<body>
    <header>
          <%@ include file="common/header.jsp" %>
    </header>
    <main>
        <div class="container-md">
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
                        <input type="submit" value="Upload" id="uploadBtn" >
                    </div>
                </form:form>
            </section>
            <section>
                <table id="uploadedData" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>EPC</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>EPC</th>
                            <th>Name</th>
                        </tr>
                    </tfoot>
                </table>
            </section>
        </div>
    </main>
    <footer style="position:fixed; bottom:0; width:100%">
      <%@ include file="common/footer.jsp" %>
    </footer>

</body>
</html>
