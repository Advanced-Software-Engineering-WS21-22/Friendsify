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
* GET /persons?id={value}
* GET /persons?email={value}
* POST /persons
* PUT /persons?id={value}
* PUT /persons?email={value}
* DELETE /persons?id={value}
* DELETE /persons?email={value}

