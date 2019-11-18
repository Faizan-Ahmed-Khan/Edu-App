# Edu-App
An End to End E-learning app.

Technology Used
================

Java 1.8,
Spring Boot,
Spring AOP,
GraphQL,
RESTFul web services,
Redis,
Spring Integration Test

DB setup required:
==================
Create an Admin in DB to start accessing this application to create other users

{
    "_id" : ObjectId("5d5e4b80376f581cf4a9d9be"),
    "userName" : "Faiz", //some username
    "role" : {
        "roleName" : "Admin"
    },
    "email" : "f@gmail.com",
    "pwd" : "Faiz123",
    "_class" : "com.learning.edubrains.model.User"
}

NOTE:: *Only Admin will be able to create other users, but all the other users will have access to update their details.*

//Functionality not yet implemented - TODO
==========================================

Create 3 Roles in "roles" collection   -> Admin,Teacher, Student

/* 1 */
{
    "_id" : ObjectId("5d4f2852a78a5e18200083b7"),
    "roleName" : "Admin"
}
/* 2 */
{
    "_id" : ObjectId("5d4f286ea78a5e18200083b8"),
    "roleName" : "Teacher"
}

/* 3 */
{
    "_id" : ObjectId("5d4f2878a78a5e18200083b9"),
    "roleName" : "Student"
}

