resource "kubernetes_secret" "token_secret" {
  metadata {
    name = "${var.project_name}-token-secret"
  }

  data = {
    secret = var.token_secret
  }

  type = "Opaque"
}