module "alb_controller" {
  source  = "campaand/alb-ingress-controller/aws"
  version = "2.1.0"

  cluster_name = "${var.project_name}-cluster"
}