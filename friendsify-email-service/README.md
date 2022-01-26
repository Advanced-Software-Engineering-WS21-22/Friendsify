# Email Service 

### Endpoint: 
* <i>localhost:9002/email</i>

### Requests:

* GET  --> returns body { emails }
* POST --> send email via Friendsify gmail account 
  * Sample request body:
    ``` 
     {
        from:     'sender@mail'
        to:       'receiver@mail'
        subject:  'subject'
        text:     'sample text'
     } 
    ```
  
