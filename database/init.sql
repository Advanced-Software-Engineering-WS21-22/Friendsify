CREATE TABLE person
(
    ID_P       int primary key not null,
    FIRST_NAME varchar         not null,
    LAST_NAME  varchar         not null,
    BIRTHDAY   date            not null,
    EMAIL      varchar unique  not null,
    PASSWORD   varchar,
    ID_GEODB     varchar,
    CITY      varchar
);

CREATE TABLE friends
(
    ID_FS          int primary key not null,
    ID_P_INITIATOR int             not null references person,
    ID_P_FRIEND    int             not null references person,
    FS_START_DATE  date            not null,
    IS_TIMED_OUT   bool
);

INSERT INTO person(ID_P, FIRST_NAME, LAST_NAME, BIRTHDAY, EMAIL, PASSWORD, ID_GEODB, CITY)
VALUES (0, 'Max', 'Mustermann', '2000-01-01', 'max@mustermann.de', 'cGFzc3dvcmQ= ', 'Q483522', 'Villach'),
       (1, 'Anna', 'Mustermann', '2001-01-01', 'anna@mustermann.de', 'cGFzc3dvcmQ', 'Q483522', 'Villach'),
       (2, 'John', 'Doe', '1990-06-06', 'john.doe@email.com', 'cGFzc3dvcmQ', 'Q41753', 'Klagenfurt'),
       (3, 'Hans', 'Müller', '1994-08-18', 'hans.m@gmail.com', 'cGFzc3dvcmQ', 'Q660687', 'Velden am Wörthersee');

INSERT INTO friends(ID_FS, ID_P_INITIATOR, ID_P_FRIEND, FS_START_DATE, IS_TIMED_OUT)
VALUES (0, 3, 1, '2019-01-31', false),
       (1, 1, 3, '2019-02-20', false),
       (2, 0, 2, '2016-10-10', false),
       (3, 2, 1, '2009-01-05', true);