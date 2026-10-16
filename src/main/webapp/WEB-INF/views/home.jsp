
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Roll | Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <main class="home-container">
        <h1>Welcome, ${user.name}!</h1>
        <p class="subtitle">Welcome to your Roll account.</p>

        <section class="user-details">
            <p><strong>Username: </strong><span>${user.username}</span></p>
            <p><strong>Email: </strong><span>${user.email}</span></p>
        </section>

        <div class="home-actions">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Logout</button>
            </form>

            <a href="${pageContext.request.contextPath}/profile">Edit Profile</a>
        </div>
    </main>
</body>
</html>
