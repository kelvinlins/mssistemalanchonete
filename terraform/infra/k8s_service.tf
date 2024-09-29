resource "kubernetes_service" "service" {
  depends_on = [kubernetes_deployment.deployment]
  metadata {
    name = "${var.project_name}-service"
  }
  spec {
    selector = {
      app = kubernetes_deployment.deployment.spec.0.template.0.metadata.0.labels.app
    }
    type = "NodePort"
    port {
      # node_port   = 80
      port        = 80
      target_port = 8080
    }
  }
}

resource "kubernetes_ingress_v1" "ingress" {
  depends_on = [kubernetes_service.service]
  wait_for_load_balancer = true
  metadata {
    name = "${var.project_name}-ingress"
    annotations = {
      "kubernetes.io/ingress.class"                  = "alb"
      "alb.ingress.kubernetes.io/target-type"        = "instance"
      "alb.ingress.kubernetes.io/scheme"             = "internal"
      "alb.ingress.kubernetes.io/load-balancer-name" = "${var.project_name}-ingress2"
      "alb.ingress.kubernetes.io/healthcheck-path"   = "/sistema-lanchonete/api/v1/pedidos"
      "alb.ingress.kubernetes.io/success-codes"      = "200-404"
    }
  }

  spec {
    ingress_class_name = "alb"
    default_backend {
      service {
        name = kubernetes_service.service.metadata.0.name
        port {
          number = kubernetes_service.service.spec[0].port[0].port
        }
      }
    }

    rule {
      http {
        path {
          path = "/sistema-lanchonete"

          backend {
            service {
              name = kubernetes_service.service.metadata.0.name
              port {
                number = kubernetes_service.service.spec[0].port[0].port
              }
            }
          }
        }
      }
    }
  }
}

