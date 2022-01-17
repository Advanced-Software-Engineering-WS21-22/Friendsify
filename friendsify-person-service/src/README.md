# Person Service

<p>This module contains CRUD operations over REST to handle person data.</p>

##Entity: Person
* id [Long]
* firstName [String]
* lastName [String]
* email [String]
* birthday [LocalDate]
* geoID [String]
* city [String]
* passwordHash [String]

##Paths
* GET /persons
* GET /persons/{id}
* GET /persons/{email}
* POST /persons
* PUT /persons/{id}
* DELETE /persons/{id}

