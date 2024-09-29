terraform {
  backend "s3" {
    bucket = "ms-lanchonete"
    key    = "deployment-service/terraform.tfstate"
    region = "us-east-1"
  }
}

module "mslanchonete" {
  source = "./infra"

  project_name = var.projectname
  region       = var.aws_region
  appversion   = var.app_version
  token_secret = var.tokensecret
}

variable "aws_region" {
  type        = string
  default     = "us-east-1"
  description = "AWS region"
}

variable "app_version" {
  type        = string
  description = "version to deploy"
}

variable "projectname" {
  type        = string
  default     = "mslanchonete"
  description = "Application Name"
}

variable "tokensecret" {
  type      = string
  sensitive = true
}