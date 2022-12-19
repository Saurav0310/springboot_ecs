# Deploy Spring Boot App On ECS 

This is a sample Java / Maven / Spring Boot (version 1.5.6) application .

## The following resources will be created:
```hcl
-- VPC , NAT Gateway , Security Group for Auto scaling group , RDS , ALB
-- RDS , SSM Parameter 
-- Application Load Balancer , Target Group
-- ECS , Task Defination , Service 

## s3 backend -- i am using s3 bucket as backend to save .tfstate file. you have to configure your s3 bucket or you can use local to save your .tfstate file
## I have used SSM parameter to store the database info i.e db_name , db_password etc .

## About Spring Boot

Spring Boot is an "opinionated" application bootstrapping framework that makes it easy to create new RESTful services (among other types of applications). It provides many of the usual Spring facilities that can be configured easily usually without any XML. In addition to easy set up of Spring Controllers, Spring Data, etc. Spring Boot comes with the Actuator module that gives the application the following endpoints helpful in monitoring and operating the service

## Running the project with MySQL
This project uses an in-memory database so that you don't have to install a database in order to run it. However, converting it to run with another relational database such as MySQL or PostgreSQL is very easy. Since the project uses Spring Data and the Repository pattern, it's even fairly easy to back the same service with MongoDB. 

Here is what you would do to back the services with MySQL, for example: 

### In pom.xml add: 

```
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
```

## Steps to run this application 
 
 * Clone this repository 

1-Add AWS Secret to Github-Action Evironment
   -go To Setting Secret/Action/ -click on New Repository Secret and Add Your AWS_ACCESS_KEY_ID & AWS_SECRET_ACCESS_KEY

- set aws credential using git secrets .
   AWS_ACCESS_KEY_ID     = 
   AWS_SECRET_ACCESS_KEY =


2- Create you s3 bucket and replace name of s3 in provider.tf 
  
  terraform {
  backend "s3" {
    bucket = # Replace this with your bucket name!
    key    = "terraform.tfstate"
    region = "us-east-1"
  }
}

3- Now to execute the code go to github-action and run-workflow accordingly.

### To view Swagger 2 API docs

Run the server and browse to localhost:8090/swagger-ui.html    #replace localhost:8090 inster loadbalancer url 
