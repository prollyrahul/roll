
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Home</title>
    </head>
    <body>
        <H1>Welcome, ${user.name}!</H1>

        <p>Username: ${user.username}</p>
        <P>Email: ${user.email}</P>

        <a href="${pageContext.request.contextPath}/logout">Logout</a>

        <a href="${pageContext.request.contextPath}/profile">Edit Profile</a>
    </body>
</html>
