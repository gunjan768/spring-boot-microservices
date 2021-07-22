<!DOCTYPE html>
<html>
<head>
    <title>Web Application</title>
</head>
<body>
<%--    We can use JSTL (JSTL format of Expression Language (EL) : ${key_name}) to fetch the whatever is in the HttpSession class --%>
<%--    <h2>I love ${name}</h2>--%>

<%-- Accepting whole as an object --%>
<h2>Welcome ${obj.id} ${obj.name} ${obj.lang}</h2>
</body>
</html>