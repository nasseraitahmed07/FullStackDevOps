# Gestion de Stock - Formation FullStack DevOps

## Description

Ce projet est une application de gestion de stock développée dans le cadre de la formation FullStack DevOps.

## Technologies utilisées

- **Langage** : Java 17
- **Framework** : Spring Boot 3.3.0
- **Base de données** : PostgreSQL
- **Gestion de migration** : Flyway
- **Documentation API** : Swagger (Springdoc OpenAPI)

## Lien vers la documentation Swagger

Vous pouvez accéder à la documentation de l'API via Swagger en suivant ce lien :
[http://localhost:8099/stock/swagger-ui/index.html](http://localhost:8099/stock/swagger-ui/index.html)

## Dépendances principales

Voici la liste des principales dépendances utilisées dans le projet :

- **Spring Boot**
    - `spring-boot-starter-actuator`
    - `spring-boot-starter-data-jpa`
    - `spring-boot-starter-validation`
    - `spring-boot-starter-web`
    - `spring-boot-starter-test`
- **Base de données & Migration**
    - `org.postgresql:postgresql`
    - `org.flywaydb:flyway-core`
    - `org.flywaydb:flyway-database-postgresql`
- **Documentation API**
    - `org.springdoc:springdoc-openapi-starter-webmvc-ui`
- **Outils de développement**
    - `org.projectlombok:lombok`
    - `org.mapstruct:mapstruct`
    - `org.mapstruct:mapstruct-processor`
    - `org.jetbrains:annotations`
- **Tests**
    - `org.mockito:mockito-core`
    - `org.assertj:assertj-core`

## Compilation et exécution

Pour compiler et exécuter le projet, utilisez les commandes suivantes :

```sh
mvn clean install
mvn spring-boot:run
```

## Contact

Développeur : **Nasser Ait Ahmed**

