<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewpoint=width=device-width, initial-scale=1.0">

        <title>Roll Sign-up</title>
    </head>
    <body>
        <p style = "color: red;">${error}</p>
        <div class="from-container">
            <h1>Creat an account</h1>
            <form action="signup" method="post">
                <label for="name">Name</label>
                <input type="text" id="name" name="name">

                <label for="username">Username</label>
                <input type="text" id="username" name="username">

                <label for="email">Email</label>
                <input type="text" id="email" name="email">

                <label for="password">Password</label>
                <input type="password" id="password" name="password">

                <button type="submit">Sign-up</button>
            </form>
            <p>
                Already have an account?
                <a href="login.jsp">Login</a>
            </p>

            <a href="index.jsp">Back to home</a>
        </div>    
    </body>
</html>
