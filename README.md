# Car Sales Garage API

This Spring Boot project provides a RESTful API for a car sales garage. The API exposes four services, allowing users to manage the garage catalog, including adding cars,
retrieving cars by specific criteria, fetching available car makes, and updating car pictures.

# technologies

* [JDK]			    (Version 17)
* [Spring Boot]	(v3.1.5)
* [Eclipse IDE] (Version: 2022-12 (4.26.0))

# Getting Started
* Clone the Repository : https://github.com/abdess-amad/Car-Sales-Garage.git
* The API will be accessible from Swagger at : http://localhost:8082/swagger-ui/index.html#/
* The API boasts the following endpoints:
 * POST /api/cars                      : Add a car to the garage catalog.
 * GET /api/carsByFuelAndPrice         : Retrieve cars based on Fuel type and Max Price criteria.
 * GET /api/makes                      : List all available car makes present in the catalog.
 * PUT /api/cars/{carId}/updatePicture : Update a car's image.
* Embedded Database accessible at : http://localhost:8082/h2-console/
  
# Architecture

Le projet suit une architecture multi-couches pour garantir la séparation des préoccupations et la maintenabilité du code. Les principales couches de l'application sont les suivantes :

* Couche de Contrôle : Cette couche contient les contrôleurs (Controllers) qui gèrent les requêtes HTTP et les réponses. Ils interagissent avec les couches de service pour traiter les demandes des clients.

* Couche de Service : Les services (Services) contiennent la logique métier de l'application. Ils sont responsables de traiter les requêtes provenant des contrôleurs et d'effectuer les opérations nécessaires, comme la validation des données et l'accès aux données.

* Couche d'Accès aux Données : Cette couche est chargée de l'accès aux données, qu'il s'agisse d'une base de données. Les classes d'accès aux données par exemple ici les Repositories.

* Modèles : Les modèles représentent les entités métier de l'application. Ils capturent les données et le comportement du domaine que l'application gère.

* DTO (Data Transfer Objects) : Les DTO sont des objets qui servent à transférer des données entre les différentes couches de l'application. Ils facilitent la sérialisation des données pour les échanges avec les clients, tout en évitant de mélanger la couche de présentation avec la couche métier.

