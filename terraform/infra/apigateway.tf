module "api_gateway" {
  source = "terraform-aws-modules/apigateway-v2/aws"
  depends_on = [ kubernetes_ingress_v1.ingress ]

  # API
  cors_configuration = {
    allow_headers = ["*"]
    allow_methods = ["*"]
    allow_origins = ["*"]
  }

  description = "HTTP API Gateway with VPC links"
  name        = "${var.project_name}-apigateway"

  # Custom Domain
  create_domain_name = false

  # Routes & Integration(s)
  routes = {
    
    "ANY /{proxy+}" = {
      integration = {
        connection_type = "VPC_LINK"
        uri             = data.aws_lb_listener.ingress.arn
        type            = "HTTP_PROXY"
        method          = "ANY"
        vpc_link_key    = "my-vpc"
      }
    }

    "$default" = {
      integration = {
        connection_type = "VPC_LINK"
        uri             = data.aws_lb_listener.ingress.arn
        type            = "HTTP_PROXY"
        method          = "ANY"
        vpc_link_key    = "my-vpc"
      }
    }
  }

  # VPC Link
  vpc_links = {
    my-vpc = {
      name               = "${var.project_name}-vpc-link"
      security_group_ids = [module.api_gateway_security_group.security_group_id]
      subnet_ids         = data.aws_subnets.mslanchonete_public_subnets.ids
    }
  }

  tags = {
    Name = "${var.project_name}-apigateway"
  }
}

################################################################################
# Supporting Resources
################################################################################
data "aws_lb" "ingress" {
  depends_on = [ kubernetes_ingress_v1.ingress ]
  tags = {
    Name = "${var.project_name}-ingress"
  }
}

data "aws_lb_listener" "ingress" {
  load_balancer_arn = data.aws_lb.ingress.arn
  port              = 80
}

module "api_gateway_security_group" {
  source  = "terraform-aws-modules/security-group/aws"
  version = "~> 5.0"

  name        = "${var.project_name}-apigateway"
  description = "API Gateway group"
  vpc_id      = data.aws_vpc.mslanchonete_vpc.id

  ingress_cidr_blocks = ["0.0.0.0/0"]
  ingress_rules       = ["http-80-tcp"]

  egress_rules = ["all-all"]

   tags = {
    Name = "${var.project_name}-apigateway-sg"
  }
}

