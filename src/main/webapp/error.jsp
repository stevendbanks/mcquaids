<%@ page isErrorPage="true" import="java.io.StringWriter, java.io.PrintWriter" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
    <h1>An error occurred</h1>
    <p>Sorry, an unexpected error occurred. Please try again later.</p>
    <h2>Error Details:</h2>
    <%
        if (exception != null) {
    %>
        <p><strong>Message:</strong> <%= exception.getMessage() %></p>
        <h3>Stack Trace:</h3>
        <pre>
            <%
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                exception.printStackTrace(pw);
                out.print(sw.toString());
            %>
        </pre>
    <%
        } else {
    %>
        <p>No exception details available.</p>
    <%
        }
    %>
</body>
</html>

