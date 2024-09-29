data "aws_ecr_repository" "repository" {
  name = "${var.project_name}-repository"
}


resource "kubernetes_deployment" "deployment" {
  depends_on = [kubernetes_secret.token_secret]
  metadata {
    name = "${var.project_name}-deployment"
  }
  spec {
    replicas = 2
    selector {
      match_labels = {
        app = var.project_name
      }
    }
    template {
      metadata {
        labels = {
          app = var.project_name
        }
      }
      spec {
        container {
          image = "${data.aws_ecr_repository.repository.repository_url}:${var.appversion}"
          name  = var.project_name
          port {
            container_port = 8080
          }
          resources {
            limits = {
              cpu    = "0.5"
              memory = "512Mi"
            }
            requests = {
              cpu    = "250m"
              memory = "50Mi"
            }
          }

          env {
            name  = "SPRING_DATASOURCE_URL"
            value = "jdbc:postgresql://${data.aws_db_instance.database.endpoint}/${data.aws_db_instance.database.db_name}"
          }

          env {
            name  = "SPRING_DATASOURCE_USERNAME"
            value = data.aws_db_instance.database.master_username
          }

          env {
            name  = "SPRING_JPA_HIBERNATE_DDL_AUTO"
            value = "update"
          }

          env {
            name = "SPRING_DATASOURCE_PASSWORD"
            value_from {
              secret_key_ref {
                name = "${var.project_name}-secret-database"
                key  = "password"
              }
            }
          }

          env {
            name = "TOKEN_SECRET"
            value_from {
              secret_key_ref {
                name = "${var.project_name}-token-secret"
                key  = "secret"
              }
            }
          }
          # liveness_probe {
          #   http_get {
          #     path = "/"
          #     port = 8080
          #   }

          #   initial_delay_seconds = 3
          #   period_seconds        = 3
          # }
        }
      }
    }
  }
}