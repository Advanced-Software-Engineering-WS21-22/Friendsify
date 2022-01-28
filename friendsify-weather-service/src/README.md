# Weather Service
This service provides endpoints to retrieve weather information based on a city name.
To do this an endpoint of https://openweathermap.org/ serves the information.

## Token
API Token has to be placed in the .env file (WEATHER_API_TOKEN) in the root directory of the project.
I got one so you can also use mine, just ask ;-)
DON'T PUSH THE TOKEN TO GITHUB! Due to publicity of the repo the token must not be pushed to the remote repository.

## Paths
start service and open localhost:9007/swagger-ui.html 

## Hint
The iconUrl within the response can be used, to show an image without downloading it. <br>
Example: <br>
```html
<img src="http://openweathermap.org/img/wn/01d@2x.png" />
```
<img src="http://openweathermap.org/img/wn/01d@2x.png" />