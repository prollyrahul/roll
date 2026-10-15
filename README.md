# Roll — Java Web Application

A Java web application built using Java Servlets, JSP, JDBC, and MySQL.

## Features
- User registration and login
- Session-based authentication
- Password hashing with BCrypt
- Profile management
- Password change
- Account deletion
- Input validation

## Tech Stack
- Java 21
- Jakarta Servlets and JSP
- JDBC
- MySQL
- Maven
- Apache Tomcat 10
- BCrypt

## Setup

1. Install Java 21, Maven, MySQL, and Apache Tomcat 10.
2. Create a MySQL database named `roll` and configure the required `users` table.
3. Set these environment variables before starting Tomcat:
   - `DB_URL` — `jdbc:mysql://localhost:3306/roll`
   - `DB_USER` — your MySQL username
   - `DB_PASSWORD` — your MySQL password
4. Build the project:

   `mvn clean package`

5. Deploy the generated WAR file from the `target` directory to Tomcat.

## Status
Personal learning project focused on Java web development and MVC architecture.
