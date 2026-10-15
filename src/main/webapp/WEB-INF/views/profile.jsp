<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Edit Profile</title>
    </head>
    <body>
        <h1>Manage Your Account</h1>
        <p style="color: red;">${error}</p>
        <h2>Edit Profile</h2>

        <form action="${pageContext.request.contextPath}/profile" method="post">
            <input type="hidden" name="action" value="updateProfile">

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${user.name}" required>
            <br><br>

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="${user.username}" required>
            <br><br>

            <label for="email">Email:</label>
            <input type="text" id="email" name="email" value="${user.email}" required>
            <br><br>

            <label for="profileCurrentPassword">Current Password:</label>
            <input type="password" id="profileCurrentPassword" name="currentPassword" required>
            <br><br>

            <button type="submit">Save Changes</button>
        </form>
        <hr>

        <h2>Change Password</h2>

        <form action="${pageContext.request.contextPath}/profile" method="post">
            <input type="hidden" name="action" value="changePassword">
            <br><br>

            <label for="currentPassword">Current Password:</label>
            <input type="password" id="currentPassword" name="currentPassword" required>
            <br><br>

            <label for="newPassword">New Password:</label>
            <input type="Password" id="newPassword" name="newPassword" required>
            <br><br>

            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
            <br><br>
            <button type="submit">Save Changes</button>
        </form>

        <h2>Delete Account</h2>
        <p>This Action is permanent and cannot be undone</p>

        <form action="${pageContext.request.contextPath}/profile" method="post">
            <input type="hidden" name="action" value="deleteAccount">

            <label for="deletePassword">Current Password:</label>
            <input type="password" id="deletePassword" name="deletePassword" required>
            <br><br>

            <label for="confirmDelete">
                <input type="checkbox" id="confirmDelete" name="confirmDelete" value="Yes" required>
                I understand that my account will be permanently deleted.
            </label>

            <button type="submit">Delete My Account</button>
        </form>

        <hr>

        <a href="${pageContext.request.contextPath}/home">Back to Home</a>

    </body>
</html>
