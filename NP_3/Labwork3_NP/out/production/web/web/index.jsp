<%@ page import="multiply.Multiply" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="static jdk.nashorn.internal.runtime.JSType.isNumber" %>
<%@page contentType="charset=UTF-8" pageEncoding="UTF-8" %>


<html>
    <head>
        <link rel="stylesheet" href="styles.scss"/>
        <script type="text/javascript" src="scripts.js"></script>
        <H1>Hey , it's the index page</H1>
        <H3>
            Here you have to multiply pair of numbers
        </H3>
    </head>
    <body>
        <div class="circles">
            <div class="circle"></div>
            <div class="circle"></div>
            <div class="circle"></div>
            <div class="circle"></div>
        </div>

        <form method="post" action="index.jsp">
            <p>
                <input type="text" name="first" placeholder="First operand"
                       pattern="^[-+]?[0-9]*[.,]?[0-9]+(?:[eE][-+]?[0-9]+)?$"
                       value="<%=(request.getParameter("first")!=null) ? request.getParameter("first") : ""%>"/>
            </p>
            <p>
                <input type="text" name="second" placeholder="Second operand"
                       pattern="^[-+]?[0-9]*[.,]?[0-9]+(?:[eE][-+]?[0-9]+)?$"
                       value="<%=(request.getParameter("second")!=null) ? request.getParameter("second") : ""%>"/>
            </p>
            <button name="redirect" value="true">
                OK
            </button>
        </form>

    </body>
</html>

<%
    if (request.getParameter("redirect") != null && request.getParameter("first") != null && request.getParameter("second") != null) {
        try {
            String a = request.getParameter("first");
            String b = request.getParameter("second");
            double first = Double.valueOf(a);
            double second = Double.valueOf(b);
            Multiply multiply = new Multiply(first, second);
            session.setAttribute("multiply", multiply);
            request.getRequestDispatcher("/result.html").forward(request, response);
        } catch (NumberFormatException e) {

        }
    }
%>


