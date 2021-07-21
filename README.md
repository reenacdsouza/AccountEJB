# AccountEJB

Multi Currency Accounts - Web-based Wallet System using Jakarta EE technologies

Software used: 

1.IDE: Eclipse 2021-06 (4.20.0)

2.Programming Languages: Java, SQL

3.Compiler: Java 11

4.Database: MySQL 8 Server

5.Database GUI: MySQL Workbench 8.0.22

6.Server: Wildfly 22.0.1 Final

7.Technologies: JSP, Servlets, Session Beans, Rest Webservices

8.Other: HTML, CSS, Flexbox, Grid

Folow below steps in order to use the application:
1. In Eclipse version used is 2021-06 (4.20.0), create EJB project, Remote client and dynamic web project. Add all of these while creating to EAR. You will have 4 folders when done.
2. Copy the files in the respective folders in the prototype zip provided into the folders in eclipse created in step 1.
3. Install MySQL server and workbench and execute Dump20210716.sql in mysql workbench in order to create database, table and load account and transaction data. Use ha07 as admin user with same privileges as root user and save password as ha07.
4. Install wildfly server version 22.0.1 Final along with mysql as the datasource. Wildfly standalone.xml is included in the standalone folder in wildfly folder included in the prototype zip. In order to add MySQL as the datasource, create a mysql folder containing the connector jar and the module.xml file in the folder structure provided in the wildfly folder in the protopype zip. Folder path to place connector jar and module.xml: /wildfly-22.0.1.Final/modules/system/layers/base/com/mysql/main/
5. Add JPA support to EJB project and connect to ha07 mysql schema.  
6. Run the AccountEJBEAR project on the configured wildfly server.
7. Login using username ha07 and password ha07
8. You should be able to see a view of all your account balances along with the respective currency types mentioned.
9. You can deposit money into a account by clicking the deposit button next to the respective account and entering the deposit amount in the webpage that follows.
10. You can withdraw money from an account by clicking the withdraw button next to the respective account and entering the withdrawal amount in the webpage that follows.
