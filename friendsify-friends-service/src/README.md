# Friends Service

<p>This module contains CRUD operations over REST to handle friendship data between two persons.</p>

##Entity: Friends
* id_friend [Long]
* email_p_initiator [String]
* email_p_friend [String]
* fs_start_date [Date]
* is_timed_out [boolean]


##Paths
* GET /friends
* GET /friends?id={value}
* GET /friends?email_initiator={value}
* GET /friends?email_friends={value}
* GET /friends?email_initiator={value}&email_friend={value} 
* POST /friends
* PUT /friends?id={value}
* DELETE /friends?id={value}