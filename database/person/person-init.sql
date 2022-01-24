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

INSERT INTO person(ID_P, FIRST_NAME, LAST_NAME, BIRTHDAY, EMAIL, PASSWORD, ID_GEODB, CITY)
VALUES (0, 'Max', 'Mustermann', '2000-01-01', 'max@mustermann.de', '$2a$10$A7tRy7jyQsTKNVcEjkwlauKhSg6deU9bnpNJt2CeQQPkWJPKlhry6', 'Q483522', 'Villach'),
       (1, 'Anna', 'Mustermann', '2001-01-01', 'anna@mustermann.de', '$2a$10$v6vYLfGiwxC.Go1HyxKinuzNdhFto9ZW0K8cO6Y1LpNA5mt/YK5mK', 'Q483522', 'Villach'),
       (2, 'John', 'Doe', '1990-06-06', 'john.doe@email.com', '$2a$10$suHpLlQe44a4IzfXSx6kUeX1VN6Os1thIBaLnr2OEkkJbeb.RVeVG', 'Q41753', 'Klagenfurt'),
       (3, 'Hans', 'Müller', '1994-08-18', 'hans.m@gmail.com', '$2a$10$jyBKNyMulV6YVTgpl0M5EOl9Z2Iy/ncZvVdr.SAztZngb.qjpQJza', 'Q660687', 'Velden am Wörthersee');
       (4, 'Maria', 'Schmidt', '1994-12-01', 'm.schmidt@gmail.com', '$2a$10$cQ2liy6Ut0AsaYNoQLBxOujRERYESHTRzBO0CVPuevrrMfpmuVHSi', 'Q875805', 'Pörtschach am Wörthersee');
