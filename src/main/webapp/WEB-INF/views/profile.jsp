
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Roll | Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <main class="profile-container">
        <h1>Your Profile</h1>

        <% if (request.getAttribute("error") != null) { %>
            <p class="error-message">${error}</p>
        <% } %>

        <% if (request.getAttribute("success") != null) { %>
            <p class="success-message">${success}</p>
        <% } %>

        <section class="profile-section">
            <h2>Edit Profile</h2>
            <form action="${pageContext.request.contextPath}/profile" method="post">
                <input type="hidden" name="action" value="updateProfile">

                <label for="name">Full Name</label>
                <input type="text" id="name" name="name"
                       value="${user.name}" required>

                <label for="username">Username</label>
                <input type="text" id="username" name="username"
                       value="${user.username}" required>

                <label for="email">Email</label>
                <input type="email" id="email" name="email"
                       value="${user.email}" required>

                <label for="currentPasswordProfile">Current Password</label>
                <input type="password" id="currentPasswordProfile"
                       name="currentPassword" autocomplete="current-password" required>

                <button type="submit">Save Changes</button>
            </form>
        </section>

        <section class="profile-section">
            <h2>Change Password</h2>
            <form action="${pageContext.request.contextPath}/profile" method="post">
                <input type="hidden" name="action" value="changePassword">

                <label for="currentPassword">Current Password</label>
                <input type="password" id="currentPassword"
                       name="currentPassword" autocomplete="current-password" required>

                <label for="newPassword">New Password</label>
                <input type="password" id="newPassword"
                       name="newPassword" autocomplete="new-password" required>

                <label for="confirmPassword">Confirm New Password</label>
                <input type="password" id="confirmPassword"
                       name="confirmPassword" autocomplete="new-password" required>

                <button type="submit">Change Password</button>
            </form>
        </section>

        <section class="profile-section danger-section">
            <h2>Delete Account</h2>
            <p>This action permanently deletes your account.</p>

            <form action="${pageContext.request.contextPath}/profile" method="post">
                <input type="hidden" name="action" value="deleteAccount">

                <label for="deletePassword">Current Password</label>
                <input type="password" id="deletePassword"
                       name="currentPassword" autocomplete="current-password" required>

                <label class="checkbox-label">
                    <input type="checkbox" name="confirmDelete" value="yes" required>
                    I understand that my account will be deleted.
                </label>

                <button type="submit" class="danger-button">Delete Account</button>
            </form>
        </section>

        <div class="home-actions">
            <a href="${pageContext.request.contextPath}/home">Back to Home</a>
        </div>
    </main>
</body>
</html>
