# Friendsify - Core

<p>This module contains core elements for the whole project, such as entities, common aau.at.friendsifypersonservice.exceptions and other.</p>

### Table of Contents

1. [ Entities ](#entities)
    1. [ Person ](#person)
    2. [ Friends ](#friends)
---
<a name="entities"></a>
## Entites
For the project two entities are needed. Both are stored in a postgres database and can be accessed via two independent 
REST Services. For details see READMEs of the corresponding services. 


<a name="person"></a>
### Person
The person entity stores the following information:

* id_p [Long]
* firstName [String]
* lastName [String]
* email [String]
* birthday [LocalDate]
* id_geoDB [String]
* city [String]
* password_hash [String]

<a name="friends"></a>
### Friends
Friendships are stored bidirectionally, meaning that A is friends with B but not vice versa.

* id_fs [Long]
* id_p_initiator [Long]
* id_p_friend [Long]
* fs_start_date [LocalDate]
* is_timed_out [boolean]