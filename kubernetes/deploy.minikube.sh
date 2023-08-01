#!/usr/bin/env bash

kubectl apply -f roles.yaml --force
kubectl apply -f ingress.yaml --force
kubectl apply -f feature-flag-store-configmap.yaml --force

# Подключаем терминал к докеру minikube
minikube docker-env
eval $(minikube -p minikube docker-env)

cd ..
./gradlew clean bootJar

docker build --build-arg JAR_FILE=build/ms/feature-flag-test-app-0.0.1-SNAPSHOT.jar -t true-feature-flag/test-app:latest .

# Нужно только для minikube
minikube addons enable ingress

helm delete feature-flag-test-app

helm upgrade --install feature-flag-test-app ./feature-flag-test-app-chart/ -f ./feature-flag-test-app-chart/values.yaml \
-f ./feature-flag-test-app-chart/values-local.yaml --kube-context minikube --force