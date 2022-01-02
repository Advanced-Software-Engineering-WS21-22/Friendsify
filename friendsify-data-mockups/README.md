# Friendsify - Data Mockups 

<p>This module aims to help the developers of "Friendsify" to test their services without depending on others. It simply creates data mockups based on the coresponding database-schema and returns them to the developer.</p>

**@author: Alex Wirth**

### Table of Contents
1. [ Person MockUps ](#person)
2. [ Friends MockUps ](#friends)
3. [ Registries ](#registries)
   1. [ RegistryFactory ](#registryfactory)
   2. [ RegistryInterface ](#registryinterface)
   3. [ MockUpRegistryCollection ](#registrycollection)
---
<a name="person"></a>
## Person
<p>The person mockup <b>[Person]</b> creates an object with the following attributes: <br>

* id_p [int] 
* name [String]
* birthday [Date]
* location_id [int]

</p>

---
<a name="friends"></a>
## Friends Mockups
<p>The friendship mockup <b>[Friendship]</b> creates an object with the following attributes:

* id_fs
* id_p_first
* id_p_second


</p>

---
<a name="registries"></a>
## Registries
<p>
The created MockUps can be stored in a key-value based MockUp-Registry which can be used by the Registry Interface. 
The creation of a registry can be executed by using the RegistryFactory. This way one can create its own registries 
holding self-created sample data or instead use the MockUpRegistryCollection.

<a name="registryfactory"></a>
### RegistryFactory 
<p>
The RegistryFactory provides a static method "getMockUpRegistry(String entity)". By passing the desired entity {Person,
Friends}, the corresponding registry will be created and returned for further use via the Registry Interface. Example:
</p>

```
Registry person = RegistryFactory.getMockUpRegistry("Person")
```
<a name="registryinterface"></a>
### Registry Interface
<p>
This can be used for simple operations on the registry. The following methods are provided:

* public void addMockUp(K key, V value);
* public ArrayList<V> getAll();
* public V getByKey(K key);
* public void removeByKey(K key);

Where K stands for the Key (suggested: Integer) used to access one entry in the registry and 
V stands for the corresponding entity (e.g. Person).
</p>

<a name="registrycollection"></a>
### MockUpRegistryCollection
<p>
This is presents the easiest way to use a pre-defined collection of registries containing sample data for all available
entities. The collection contains:

* PersonRegistry (getPersonRegistry())
* FriendsRegistry (getFriendsRegistry())

The registries can be accessed via simple getter-methods returning the Registry-Interface (see above). The implementation
is realized as Singleton. 
The registries contain the following sample data:
</p>

<table title="Person">
    <tr>
        <th>id_p</th>
        <th>name</th>
        <th>birthday</th>
        <th>id_geoDB</th>
    </tr>
    <tr>
        <td>0</td>
        <td>Maxi Mustermann</td>
        <td>1999-21-02</td>
        <td>12</td>
    </tr>
    <tr>
        <td>1</td>
        <td>Maxi Mustermann</td>
        <td>1999-21-02</td>
        <td>12</td>
    </tr>
</table>

<table title="Person">
    <tr>
        <th>id_f</th>
        <th>id_p_one</th>
        <th>id_p_second</th>
    </tr>
    <tr>
        <td>0</td>
        <td>1</td>
        <td>3</td>
    </tr>
    <tr>
        <td>1</td>
        <td>1</td>
        <td>2</td>
    </tr>
</table> 
