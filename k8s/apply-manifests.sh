#!/bin/bash
kubectl apply -f ./app/secret.yaml
kubectl apply -f ./app/service.yaml
kubectl apply -f ./app/deployment.yaml
kubectl apply -f ./infra/metrics.yaml
kubectl apply -f ./app/hpa.yaml

