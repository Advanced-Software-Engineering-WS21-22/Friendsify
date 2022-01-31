CREATE TABLE person
(
    ID_P       bigserial primary key not null,
    FIRST_NAME varchar         not null,
    LAST_NAME  varchar         not null,
    BIRTHDAY   date            not null,
    EMAIL      varchar unique  not null,
    PASSWORD   varchar,
    ID_GEODB     varchar,
    CITY      varchar
);

INSERT INTO person( FIRST_NAME, LAST_NAME, BIRTHDAY, EMAIL, PASSWORD, ID_GEODB, CITY)
VALUES ( 'Max', 'Mustermann', '2000-01-01', 'max@mustermann.de', 'cGFzc3dvcmQ= ', 'Q483522', 'Villach'),
       ( 'Anna', 'Mustermann', '2001-01-01', 'anna@mustermann.de', 'cGFzc3dvcmQ', 'Q483522', 'Villach'),
       ( 'John', 'Doe', '1990-06-06', 'john.doe@email.com', 'cGFzc3dvcmQ', 'Q41753', 'Klagenfurt'),
       ( 'Hans', 'Müller', '1994-08-18', 'hans.m@gmail.com', 'cGFzc3dvcmQ', 'Q660687', 'Velden am Wörthersee');
