Employee Management System (Java + MySQL)
Description

A basic Employee Management System built using Java, JDBC, and a MySQL database. The system runs in the console and allows inserting, updating, deleting, and searching employee records stored in a MySQL database.

This project was created for CSC 3350.

Features

Add new employees

Search employees by name, SSN, or Employee ID

Update employee information

Delete employees

Update salary by percentage within a salary range

View report information (total pay by job title or division)

How to Run

Install MySQL and ensure it is running

Create a database named project and add an Employee table

Open the project folder in VS Code

Make sure the Extension Pack for Java is installed

Right-click Main.java → Run Java

Database Setup
CREATE DATABASE project;
USE project;

CREATE TABLE Employee (
    empId INT PRIMARY KEY,
    name VARCHAR(100),
    ssn VARCHAR(20),
    jobTitle VARCHAR(100),
    division VARCHAR(100),
    salary DOUBLE
);


Example insert:

INSERT INTO Employee VALUES (1, 'first last', '123456789', 'Engineer', 'IT', 85000);

Project Structure
project/
   src/                     Java source files
   lib/                     JDBC Driver (mysql-connector-j-x.x.x.jar)
   .vscode/                 VS Code settings

Notes for Collaborators

Update MySQL credentials inside DatabaseOperations.java:

String url = "jdbc:mysql://localhost:3306/project";
String user = "root";
String password = "yourPassword";


The JDBC driver in /lib works for everyone in the group.