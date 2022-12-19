# Deploy Spring Boot App On ECS 

This is a sample Java / Maven / Spring Boot (version 1.5.6) application .
# Steps to run this application 
 
 * Clone this repository 

1-Add AWS Secret to Github-Action Evironment
   -go To Setting Secret/Action/ -click on New Repository Secret and Add Your AWS_ACCESS_KEY_ID & AWS_SECRET_ACCESS_KEY

- set aws credential using git secrets .
   AWS_ACCESS_KEY_ID   &
   AWS_SECRET_ACCESS_KEY 
   
   ![image](https://user-images.githubusercontent.com/91631978/208396209-c9e315f8-a2bc-457e-a479-601459d6019c.png)



2- Create you s3 bucket and replace bucket name of s3 in provider.tf 
  
![image](https://user-images.githubusercontent.com/91631978/208413841-b03b2529-2d9b-44fd-8dac-4f905763524f.png)


3- Now to execute the code go to github-action and run-workflow .
  - I have set input parameter so you can use to apply and destroy accordingly.

![image](https://user-images.githubusercontent.com/91631978/208414145-219f241c-37c8-4ab5-9660-1f45179943a4.png)

### To view Swagger 
Run the server and browse to localhost:8090/swagger-ui.html    #replace localhost:8090 to loadbalancer url


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
