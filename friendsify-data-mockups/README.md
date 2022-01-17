# Friendsify - Data Mockups 

<p>This module aims to help the developers of "Friendsify" to test their services without depending on others. It simply creates data mockups based on the coresponding database-schema and returns them to the developer.</p>

**@author: Alex Wirth**

### Table of Contents
1. [ MockUpRegistry ](#registries)
   1. [ MockUpRegistry-Interface ](#registryinterface)
   2. [ MockUpRegistryCollection ](#registrycollection)
2. [ MockUp-Samples](#mockupsamples)
---
<a name="registries"></a>
## MockUpRegistries
<p>
The created MockUps can be stored in a MockUpRegistry which can be used by the MockUpRegistry-Interface. 
One can simply create an individual MockUpRegistry to hold self-created sample data or instead use the 
MockUpRegistryCollection. For the initialization the entity-type has to be specified, as the following example
suggests:

```
MockUpRegistry<Person> personRegistry = new MockUpRegistryImpl()
```
<a name="registryinterface"></a>
### MockUpRegistry Interface
<p>
This can be used for simple operations on the registry. The following methods are provided:

* public void addMockUp(E value);
* public ArrayList<E> getAll();
* public E getByKey(int key);
* public void removeByKey(int key);
* public void changeMockup(int key, E value);

Where E stands for the corresponding entity.
</p>

<a name="registrycollection"></a>
### MockUpRegistryCollection
<p>
This is the easiest way to use a pre-defined collection of registries containing sample data for all available
entities. The collection contains:

* PersonRegistry (getPersonRegistry())
* FriendsRegistry (getFriendsRegistry())

The registries holding the samples of a certain entity can be accessed via simple getter-methods returning the 
registry-Interface (see above). 
The implementation is realized as Singleton, where the instance of the collection can be accesed via the static
method getMockUpRegistryCollection().

```
MockUpRegsitryCollection mockUpRegistryCollection = MockUpRegsitryCollection.getMockUpRegistryCollection();
```
---
<a name="mockupsamples"></a>
## MockUp-Samples
The registries contain the following sample data:
</p>

#### Persons
<table title="Person">
    <tr>
        <th>id_p</th>
        <th>firstName</th>
        <th>lastName</th>
        <th>email</th>
        <th>birthday</th>
        <th>id_geoDB</th>
        <th>city</th>
        <th>password_hash</th>
    </tr>
    <tr>
        <td>0</td>
        <td>Max</td>
        <td>Mustermann</td>
        <td>max@mustermann.de</td>
        <td>01.01.2000</td>
        <td>Q483522</td>
        <td>Villach</td>
        <td>cGFzc3dvcmQ= (password)</td>
    </tr>
    <tr>
        <td>1</td>
        <td>Anna</td>
        <td>Mustermann</td>
        <td>anna@mustermann.de</td>
        <td>01.01.2001</td>
        <td>Q483522</td>
        <td>Villach</td>
        <td>aG91c2U= (house)</td>
    </tr>
    <tr>
        <td>2</td>
        <td>John</td>
        <td>Doe</td>
        <td>john.doe@email.com</td>
        <td>06.06.1990</td>
        <td>Q41753</td>
        <td>Klagenfurt</td>
        <td>YW5pbWFsCg== (animal)</td>
    </tr>
    <tr>
        <td>3</td>
        <td>Hans</td>
        <td>Müller</td>
        <td>hans.m@gmail.com</td>
        <td>18.08.1994</td>
        <td>Q660687</td>
        <td>Velden am Wörthersee</td>
        <td>Y2Fy (car)</td>
    </tr>
    <tr>
        <td>4</td>
        <td>Maria</td>
        <td>Schmidt</td>
        <td>m.schmidt@gmail.com</td>
        <td>01.12.1994</td>
        <td>Q875805</td>
        <td>Pörtschach am Wörthersee</td>
        <td>dHJlZQ== (tree)</td>
    </tr>
    <tr>
        <td>5</td>
        <td>Lukas</td>
        <td>Maier</td>
        <td>lumai@gmx.com</td>
        <td>15.10.1999</td>
        <td>Q494604</td>
        <td>Sankt Veit an der Glan</td>
        <td>Y29tcHV0ZXI= (computer)</td>
    </tr>
</table>

#### Friends
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
        <td>4</td>
        <td>31.01.2019</td>
        <td>false</td>
    </tr>
    <tr>
        <td>1</td>
        <td>1</td>
        <td>0</td>
        <td>01.06.2020</td>
        <td>false</td>
    </tr>
    <tr>
        <td>2</td>
        <td>6</td>
        <td>5</td>
        <td>18.02.2021</td>
        <td>false</td>
    </tr>
    <tr>
        <td>3</td>
        <td>4</td>
        <td>5</td>
        <td>21.08.2020</td>
        <td>true</td>
    </tr>
</table> 
