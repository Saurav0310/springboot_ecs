variable "region" {
  description = "The region in which to create the ECR repository."
  type        = string
  default = "us-east-1"
}

variable "repository_name" {
  description = "The repository name to use for the ECR repository."
  type = string
  default = "newimage"
}
