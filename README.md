# Deploy Spring Boot App On ECS 

This is a sample Java / Maven / Spring Boot (version 1.5.6) application .

## This terraform module builds an Elastic Container Service(ECS) Cluster in AWS.
## The following resources will be created:
```hcl
-- VPC , NAT Gateway , Security Group for Auto scaling group , RDS , ALB
-- RDS , SSM Parameter 
-- Application Load Balancer , Target Group
-- ECS , Task Defination , Service 

## s3 backend -- i am using s3 bucket as backend to save .tfstate file. you have to configure your s3 bucket or you can use local to save your .tfstate file


## I have used SSM parameter to store the database info i.e db_name , db_password etc .


| Name | Type |
|------|------|
| [random_string](https://registry.terraform.io/providers/hashicorp/random/latest/docs/resources/string) | resource |
| [aws_ssm_parameter](https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/ssm_parameter) | resource |
| [aws_instance](https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/instance) | resource |
| [aws_security_group](https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/security_group) | resource |
| [aws_iam_role_policy](https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/iam_role_policy) | resource 


# How to run
# Steps : 
 
 * Clone this repository 

1-Add AWS Secret to Github-Action Evironment
   -go To Setting Secret/Action/ -click on New Repository Secret and Add Your AWS_ACCESS_KEY_ID & AWS_SECRET_ACCESS_KEY

- set aws credential using git secrets .
   AWS_ACCESS_KEY_ID     = 
   AWS_SECRET_ACCESS_KEY =
  ![image](https://user-images.githubusercontent.com/91631978/208351510-1ea670e0-3a99-4c5d-ab00-2e2405958b8a.png)


2- Create you s3 bucket and replace name of s3 in provider.tf 

3- Now to execute the code go to github-action and run-workflow accordingly.


