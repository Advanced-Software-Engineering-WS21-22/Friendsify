# Database setup

### This directory contains the initialization scripts for the postgres databse.

#### Note that the ```docker-compose.yml``` creates and binds a local data directory to the internal docker volume. That volume persists even after shutting down the services. If you want to newly create the database with the ```init.sql``` script, you have two options:

* Either run ```docker-compose down -v``` <i> the "-v" specifies that all associated volumes must also be removed.</i>
* Or remove the volume manually by: 
  * ```docker volume ls``` <i> list all volumes on your local machine with their ids</i>
  *  ```docker volume rm id``` <i> remove the volume with the specified id</i>

#### After that, running the```docker-compose.yml``` file should create a new and empty data directory and therefore run the ```init.sql``` script.

## Data 

#### The ```init.sql``` creates two tables and fills them with some sample data, the schema is listed below. For simplicity reasons all Persons use the password: <i>"password"</i>
 
### Persons
<table title="Person">
    <tr>
        <th>id_p</th>
        <th>firstName</th>
        <th>lastName</th>
        <th>birthday</th>
        <th>email</th>
        <th>password</th>
        <th>id_geoDB</th>
        <th>city</th>
    </tr>
    <tr>
        <td>0</td>
        <td>Max</td>
        <td>Mustermann</td>
        <td>2000-01-01</td>
        <td>max@mustermann.de</td>
        <td>cGFzc3dvcmQ=</td>
        <td>Q483522</td>
        <td>Villach</td>
    </tr>
    <tr>
        <td>1</td>
        <td>Anna</td>
        <td>Mustermann</td>
        <td>2001-01-01</td>
        <td>anna@mustermann.de</td>
        <td>cGFzc3dvcmQ=</td>
        <td>Q483522</td>
        <td>Villach</td>
    </tr>
    <tr>
        <td>2</td>
        <td>John</td>
        <td>Doe</td>
        <td>1990-06-06</td>
        <td>john.doe@email.com</td>
        <td>cGFzc3dvcmQ=</td>
        <td>Q41753</td>
        <td>Klagenfurt</td>
    </tr>
    <tr>
        <td>3</td>
        <td>Hans</td>
        <td>Müller</td>
        <td>1994-08-18</td>
        <td>hans.m@gmail.com</td>
        <td>cGFzc3dvcmQ=</td>
        <td>Q660687</td>
        <td>Velden am Wörthersee</td>
    </tr>
</table>

### Friends
<table title="Friends">
    <tr>
        <th>id_fs</th>
        <th>id_p_initiator</th>
        <th>id_p_friend</th>
        <th>fs_start_date</th>
        <th>is_timed_out</th>
    </tr>
    <tr>
        <td>0</td>
        <td>3</td>
        <td>1</td>
        <td>2019-01-31</td>
        <td>false</td>
    </tr>
    <tr>
        <td>1</td>
        <td>1</td>
        <td>3</td>
        <td>2019-02-20</td>
        <td>false</td>
    </tr>
    <tr>
        <td>2</td>
        <td>0</td>
        <td>2</td>
        <td>2016-10-10</td>
        <td>false</td>
    </tr>
    <tr>
        <td>3</td>
        <td>2</td>
        <td>1</td>
        <td>2009-01-05</td>
        <td>true</td>
    </tr>
</table> 