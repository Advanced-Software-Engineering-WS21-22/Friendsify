# Friendsify - Frontend

The Friendsify-Frontend can be used as singe point of interaction for numerous services of the ASE-Project 2022. Although it includes
7 different services, still not all of the provided services and all of the provided functionalities are used.
It uses an authentication mechanism. For exploration, i suggest using the following credentials:

Username: max@mustermann.de </br>
Password: password

## Used Services:
Bellow a short description and guideline of the used services can be found.

### Person Services
The Person Service is used to retrieve existing person data and add new persons into the network. The usage mainly happens under
the tab "Persons" in the navigation bar, where one can add new persons, see a list of all existing persons and view person details.

### Friends Service
The Friends Service is used to add new friendships, get information about friendships and deliver information to other services such
as the E-Mail or Joke Service. The main source of interaction happens under "Friends" as well as "Find new Friends".

### E-Mail Service
This service allows sending E-Mail to persons that someone is friends with from the frontend. The functionality can be accessed
via the "Send Email" button in the list of friends under "Friends".

### Joke Service
The functionality of the Joke Service is used once on the main page (tab "Home"), showing a random joke after logging in. Additionally,
one can automatically send a joke to a friend via the "Send Joke" button in the list of friends under "Friends".

### Weather Service
Additionally, to a random joke, the home page ("Home") also loads current data regarding the Weather of a persons' location using
the Weather Service.

### Recommendation Service
When adding a new friend, a user will get basic recommendations based on the age, common friends, firstname and lastname. These
recommendations are loaded when adding a new friend under the tab "Find new Friends".

### Anniversary Service
The Anniversary Service helps the user to keep an eye on anniversaries of friendships. When showing the details of a friendship
by using the "Details" button under "Friends", one will find information on the anniversary of this friendship.

### Usage of external APIs
In order to be able to fully use the frontend, it is necessary to include tokens for the external APIs (Jokes & Weather). For details
see the READMEs of the corresponding services.