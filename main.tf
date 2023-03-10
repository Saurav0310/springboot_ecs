module "network" {
  source = "./modules/network"
}

  module "asg" {
  source            = "./modules/asg"
  name              = var.name
  asg_max           = 1
  asg_min           = 1
  health_check_type = "ELB"
  desired_capacity  = 1
  force_delete      = "true"
  instance_types    = "t3.small"
  asg_sg            = [module.sgASG.sgid]
  vpc_zone_id       = [module.network.private_subnet_ids1, module.network.private_subnet_ids2]

}
    
module "ecs" {
  source     = "./modules/ecs"
  asg_arn    = module.asg.asg_arn
  name       = var.name
  vpc_id     = module.network.vpcid   
  public_sg  = [module.sgALB.sgid]    
  public_sub = [module.network.public_subnet_ids1, module.network.public_subnet_ids2]
  path = "/swagger-ui.html" 
  imageURI         = "${var.imageURI}"
  ssm_variables    = { "DB_ENDPOINT" : module.rds.ssm_parameter_rds_endpoint, 
                       "DB_NAME" : module.rds.ssm_parameter_rds_dbname, 
                       "DB_USER" : module.rds.ssm_parameter_rds_user, 
                       "DB_PASS" : module.rds.ssm_parameter_rds_password }
  container_cpu    = 1024
  container_memory = 1024
  containerPort    = 8090
  hostPort         = 0
  autoscaling_cpu  = true
  autoscaling_target_cpu = 70
}

                         
module "sgASG" {
  source    = "./modules/sg"
  name      = "ecs"
  sg_cidr   = [module.network.cidr_block]
  sg_vpc_id = module.network.vpcid
  from_port = 49153 
  to_port   = 65535  
}
    

module "sgALB" {
  source    = "./modules/sg"
  name      = "ecs2"
  sg_cidr   = ["0.0.0.0/0"]
  sg_vpc_id = module.network.vpcid
  from_port = 80
  to_port   = 80

}

module "sgRDS" {
  source    = "./modules/sg"
  name      = "ecs3"
  sg_cidr   = [module.network.cidr_block]
  sg_vpc_id = module.network.vpcid
  from_port = 3306
  to_port   = 3306
}

module "rds" {
  source            = "./modules/rds"
  allocated_storage = 10
  db_name           = "springdb21"
  engine            = "mysql"
  engine_version    = "5.7"
  instance_class    = "db.t3.micro"
  username          = "springdb21"

  parameter_group_name   = "default.mysql5.7"
  skip_final_snapshot    = true
  db_subnet_group_name   = module.network.aws_db_subnet_group-default
  vpc_security_group_ids = [module.sgRDS.sgid]
}

# module "s3" {
#   source = "./modules/s3"
# }
