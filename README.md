# Rest-api 
[![Build Status](https://travis-ci.org/vladkondratuk/rest-api.svg?branch=master)](https://travis-ci.org/github/vladkondratuk/rest-api)

Java resp-api project to study technologies such as REST, Spring Framework(MVC), Hibernate ORM, Junit5, Mockito, MockMvc, CI/CD and others.

### Project Idea 
Rest-api for "Project management tool" demo project. A simple project to do in one month and gain experience for larger projects.  
I have idea to made my own "Project management tool" to learn new technologies and track time I spent on solving "tasks".

### Project Documentation
 
Steps to make this project.

- [Creating SQL commands for generating MySQL Schema](documentation/sql_schema.md) 

### Used technologies

 - jdk: 11-amazon-corretto java version
 - build tool: Maven
 - framework: Spring Framework
 - database: MySQL
 - ORM: Hibernate ORM
 - unit test: Junit5
 - mocks: Mockito, MockMvc
 - server: Tomcat embedded
 
### Prerequisites
 
         install git
         install JDK 11(amazon correto, openJDK)
         install maven3+
         install mysql workbench    
         
### Installing
Choose a directory for project, download project from github:
 
       $ git clone https://github.com/vladkondratuk/rest-api.git

#### Build project
Run terminal command in project directory:

        $ mvn clean install

#### Start app
Run terminal command in project directory:

        $ cd /rest-app
        $ mvn clean install cargo:run 

### How it's work
Simple example data conversion from MySQL table to POJO and then to JSON.

##### MySQL Table
```sql
    CREATE TABLE `activity` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `activity_name` varchar(128) DEFAULT NULL,
      PRIMARY KEY (`id`)
    )
```

##### Java class(JPA mapping)
```java
@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "activity_name")
    private String activityName;

    //Constructors
    //Getters and Setters    
}
``` 
##### JSON object(jackson plugin mapping)
```json
{
   "id": 1,
   "activityName": "activity example"
}
```
  
>Produced by Vladiskav Kondratuk 2020