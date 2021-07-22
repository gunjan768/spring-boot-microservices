<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
    <h1>Login</h1>
    ${SPRING_SECURITY_LAST_EXCEPTION.message}
    <c:url value="/perform_login" var="loginUrl"/>

<%--The username must be present as the HTTP parameter named username
    The password must be present as the HTTP parameter named password--%>
    <form action="${loginUrl}" method="post">

        <table>
            <tr>
                <td>User: </td>
                <td><input type="text" name="username" value=""></td>
            </tr>
            <tr>
                <td>Password: </td>
                <td><input type="password" name="password" value=""></td>
            </tr>
            <tr>
                <td><input type="submit" name="submit" value="submit"></td>
            </tr>
        </table>

    </form>
</body>
</html>