
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewpoint" content="WIdth = device-width, initial-scale = 1.0">

        <title>Login Roll</title>
    </head>
    <body>
        <p style = "color : red;">${error}</p>
        <div class="from-container">
            <h1>login</h1>

            <form action="login" method="post">

                <label for="username">username</label>
                <input type = "text" id="username" name="username">

                <label for="password">password</label>
                <input type = "password" id="password" name="password">

                <button type="submit">Login</button>
                
            </form>
            <p>Dont have an account?
                <a href="signup.jsp">Sign-up</a>
            </p>
            <a href="index.jsp">Back to home</a>
        </div>
    </body>
</html>
