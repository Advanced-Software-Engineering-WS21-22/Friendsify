# Person Service

<p>This module contains CRUD operations over REST to handle person data.</p>

##Entity: Person
* id_p [Long]
* first_name [String]
* last_name [String]
* birthday [LocalDate]
* email [String]
* password [String]
* id_geoDB [String]
* city [String]


##Paths
* GET /persons
* GET /persons/{id}
* GET /persons/{email}
* POST /persons
* PUT /persons/{id}
* PUT /persons/{email}
* DELETE /persons/{id}
* DELETE /persons/{email}

