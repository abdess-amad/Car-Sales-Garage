# Car Sales Garage API

This Spring Boot project provides a RESTful API for a car sales garage. The API exposes four services, allowing users to manage the garage catalog, including adding cars,
retrieving cars by specific criteria, fetching available car makes, and updating car pictures.

# technologies

* [JDK]			    (Version 17)
* [Spring Boot]	(v3.1.5)

# Getting Started
* Clone the Repository : https://github.com/abdess-amad/Car-Sales-Garage.git
* The API will be accessible from Swagger at : http://localhost:8082/swagger-ui/index.html#/
* The API boasts the following endpoints:
 * POST /api/cars                      : Add a car to the garage catalog.
 * GET /api/carsByFuelAndPrice         : Retrieve cars based on Fuel type and Max Price criteria.
 * GET /api/makes                      : List all available car makes present in the catalog.
 * PUT /api/cars/{carId}/updatePicture : Update a car's image.
* Embedded Database accessible at : http://localhost:8082/h2-console/

