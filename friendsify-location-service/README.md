# Location Service
This service provides endpoints to retrieve location related information based on an id_geoDb from https://rapidapi.com/wirefreethought/api/geodb-cities/.

## Token
An API Token has to be placed in an .env file (GEODB_API_TOKEN) in the root directory of the project.
I got one, so you can also use mine, just ask me.
DON'T PUSH THE TOKEN TO GITHUB! Due to the publicity of the repo the token must not be pushed to the remote repository.

## Paths
Start this service and open localhost:9009/swagger-ui.html:

* GET /{geoDBID}/details (details about a specific city)
* GET /distance --> RequestParameters: String fromGeoDBID, String toGeoDBID (distance in km between two specific cities)
* GET /meetingsLocations --> RequestParameters: String fromGeoDBID, String toGeoDBID (find cities suitable as meeting points based on two cities as start)
* GET /{geoDBID}/nearbyCities (find cities near a specific city)
* GET /{geoDBID}/nearbyCities --> RequestParameter: int minPopulationCount (find cities with a certain population near a specific city)
* GET /{geoDBID}/nearbyBigCities (find cities with a minimal population of at least 10.000 near a specific city)
}
