
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Roll | Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <main class="form-container">
        <h1>Welcome Back</h1>
        <p>Log in to your Roll account.</p>

        <% if (request.getAttribute("error") != null) { %>
            <p class="error-message">${error}</p>
        <% } %>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <label for="username">Username</label>
            <input type="text" id="username" name="username"
                   autocomplete="username" required>

            <label for="password">Password</label>
            <input type="password" id="password" name="password"
                   autocomplete="current-password" required>

            <button type="submit">Log in</button>
        </form>

        <p>Don't have an account?</p>
        <a href="${pageContext.request.contextPath}/signup.jsp">Create an account</a>
    </main>
</body>
</html>
