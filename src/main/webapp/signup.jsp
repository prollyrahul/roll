
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Roll | Sign Up</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <main class="form-container">
        <h1>Create Account</h1>
        <p>Join the Roll community.</p>

        <% if (request.getAttribute("error") != null) { %>
            <p class="error-message">${error}</p>
        <% } %>

        <form action="${pageContext.request.contextPath}/signup" method="post">
            <label for="name">Full Name</label>
            <input type="text" id="name" name="name"
                   autocomplete="name" required>

            <label for="username">Username</label>
            <input type="text" id="username" name="username"
                   autocomplete="username" required>

            <label for="email">Email</label>
            <input type="email" id="email" name="email"
                   autocomplete="email" required>

            <label for="password">Password</label>
            <input type="password" id="password" name="password"
                   autocomplete="new-password" required>

            <button type="submit">Create Account</button>
        </form>

        <p>Already have an account?</p>
        <a href="${pageContext.request.contextPath}/login.jsp">Log in</a>
    </main>
</body>
</html>
