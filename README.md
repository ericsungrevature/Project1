# Project1
Expense Reimbursement System (ERS) - Java Javalin JDBC

# Project Description
The ERS is a application that will manage the process of reimbursing employees for expenses incurred while on company time. As employees in the company, they can login to the application with username and password. After, they can submit requests for reimbursement and view their past reimbursement tickets and the pending requests. As finance manager in the company, they can login to the application with username and password. After, they can view all reimbursement requests and past history of reimbursement of all employees in the company. Also, finance managers are authorized to approve and/or deny requests for expense reimbursement.

# Technologies Used
* HTML
* JAVA
* JDBC
* MySQL
* Servlet
* Tomcat
* BootStrap
* Maven
* Hibernate

# Features
Allow users to login and logout the application.
Allow employees to create and submit the reimbursement request tickts.
Employees can only view their own request tickts.
Allow finance manager to view all past history of reimbursement request tickts.
Allow finance manager to approve and/or deny the reimbursement requests.

# Getting Started
You can simply download the zip package or clone it with this url: https://github.com/ericsungrevature/Project1.git.
git clone comand: git clone https://github.com/ericsungrevature/Project1.git
Create table for user and ticket in database.
commands for tables:

create table user (id integer primary key auto_increment, username char(20) unique, password char(20), status enum('employee', 'manager'));
create table ticket (id integer primary key auto_increment, user_id integer, value double, description char(200), status enum('approved', 'pending', 'rejected'));
Create users (finance manager and employee):
insert into user (username, password, status) values ('admin', '123', 'manager');
insert into user (username, password, status) values ('emp', '123', 'employee');
Usage
For Employees

Login with your username and password.
You can submit a reimbursement request by click the request button.
Once you click request button, fill out the nessesary information (such as reimbursement amount and description) then click submit button.
Once reimbursement request ticket has been created, wait for finance manager's authorization.
You can go back to main page by click the button on the web page.
You can also view your reimbursement requests history by click view button.
If you are finished, you can click logout button to logout the application.
For Finance Manager

Login with your username and passowrd.
You can view all of the reimbursement request by click the view button.
You can approve or deny new reimbursement request by click the authorize button.
If you want to approve the reimbursement request, just click approve on the ticket table.
If you want to deny the reimbursement request, just click deny on the ticket table.
You can also click back button to just go back to the main page.
If you are finished, you can click logout button to logout the application.
Contributors
My team member: Eric Sung

Team name: Team Team
