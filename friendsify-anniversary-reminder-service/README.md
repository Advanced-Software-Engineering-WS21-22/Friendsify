# Anniversary Reminder Service

### This module offers an Endpoint to get a notification about the friendship anniversaries.

## Endpoint info:

* port: 9008
* path: /anniversary/?email_initiator={value}&email_friend{value}
* response:
  * If today is the anniversary date, then a special message is returned
  * If today is not the anniversary date, then the remaining days until the anniversary are returned in a message

