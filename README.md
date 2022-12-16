# Deploy Spring Boot App On ECS 

This is a sample Java / Maven / Spring Boot (version 1.5.6) application .

## For executing the Workflow this steps riquired 
- set aws credential using git secrets .

## This terraform module builds an Elastic Container Service(ECS) Cluster in AWS.
## The following resources will be created:
```hcl
-- VPC , NAT Gateway , Security Group for Auto scaling group , RDS , ALB
-- RDS , SSM Parameter 
-- Application Load Balancer , Target Group
-- ECS , Task Defination , Service 
``` 

## s3 backend -- i am using s3 bucket as backend to save .tfstate file. you have to configure your s3 bucket or you can use local to save your .tfstate file

## Resources![template1-designer](https://user-images.githubusercontent.com/91631978/208026098-c70a02c5-4d7c-49a5-9fc9-96347a78cd44.png)


| Name | Type |
|------|------|
| [random_string](https://registry.terraform.io/providers/hashicorp/random/latest/docs/resources/string) | resource |
| [aws_ssm_parameter](https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/ssm_parameter) | resource |
| [aws_instance](https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/instance) | resource |
| [aws_security_group](https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/security_group) | resource |
| [aws_iam_role](https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/iam_role) | resource |
| [aws_iam_instance_profile](https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/iam_instance_profile) | resource |
| [aws_iam_role_policy](https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/iam_role_policy) | resource 


# How to run

# Steps to Be Followed 
 
 * Clone this repository 

1-Add AWS Secret to Github-Action Evironment
   -go To Setting Secret/Action/ -click on New Repository Secret and Add Your AWS_ACCESS_KEY_ID & AWS_SECRET_ACCESS_KEY

2- Create Your Own s3 bucket and replace details in main.tf

3- Now you can manage your architecture using github action 


