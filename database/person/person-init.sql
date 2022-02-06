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
VALUES    ( 'Max', 'Mustermann', '2000-01-01', 'max@mustermann.de', '$2a$10$A7tRy7jyQsTKNVcEjkwlauKhSg6deU9bnpNJt2CeQQPkWJPKlhry6', 'Q483522', 'Villach'),
          ( 'Anna', 'Mustermann', '1990-06-06', 'anna@mustermann.de', '$2a$10$v6vYLfGiwxC.Go1HyxKinuzNdhFto9ZW0K8cO6Y1LpNA5mt/YK5mK', 'Q483522', 'Villach'),
          ( 'John', 'Doe', '1994-01-01', 'john.doe@email.com', '$2a$10$suHpLlQe44a4IzfXSx6kUeX1VN6Os1thIBaLnr2OEkkJbeb.RVeVG', 'Q41753', 'Klagenfurt'),
          ( 'Hans', 'Müller', '2001-01-01', 'hans.m@gmail.com', '$2a$10$jyBKNyMulV6YVTgpl0M5EOl9Z2Iy/ncZvVdr.SAztZngb.qjpQJza', 'Q660687', 'Velden am Wörthersee'),
          ( 'Maria', 'Schmidt', '1994-12-01', 'm.schmidt@gmail.com', '$2a$10$cQ2liy6Ut0AsaYNoQLBxOujRERYESHTRzBO0CVPuevrrMfpmuVHSi', 'Q875805', 'Pörtschach am Wörthersee'),
          ( 'Alex', 'Wirth', '1999-01-01', 'alex@wirth.email', '$2a$10$A7tRy7jyQsTKNVcEjkwlauKhSg6deU9bnpNJt2CeQQPkWJPKlhry6', 'Q41753', 'Klagenfurt'),
          ( 'Max', 'Doe', '1999-01-01', 'max@doe.email', '$2a$10$A7tRy7jyQsTKNVcEjkwlauKhSg6deU9bnpNJt2CeQQPkWJPKlhry6', 'Q41753', 'Klagenfurt');