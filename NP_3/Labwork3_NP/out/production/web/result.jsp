<%@ page import="multiply.Multiply" %>
<%
    Multiply multiply = (Multiply) session.getAttribute("multiply");

    if (multiply == null) {
        Thread.sleep(5000);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    if (request.getParameter("download") != null) {
        request.getRequestDispatcher("/result.xml").forward(request, response);
    }

%>

<script type="javascript">
    function f() {
        alert('sdfds')
    }
</script>
<html>
<head>
    <link rel="stylesheet" href="styles.scss">
    <H1>Hey , it's the result page</H1>
</head>
<body>
<form method="post" action="result.html">
    <p>
        <label><b>First operand : </b><%=multiply.getFirstArgument()%>
        </label>
    </p>
    <p>
        <label><b>Second operand : </b><%=multiply.getSecondArgument()%>
        </label>
    </p>
    <p>
        <label><b>Result : </b><%=multiply.getResult()%>
        </label>
    </p>
    <p><%
        if (multiply == null) {

    %>
        <b>Error</b>
        <a href="index.jsp">Go to index , now!</a>
        <%
            }
        %>
    </p>

    <p>
        <a href="index.jsp">Go to index</a>
    </p>

    <p>
        <button name="download" value="true" >Open in xml</button>
    </p>

</form>


</body>


</html>