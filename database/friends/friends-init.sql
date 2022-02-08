CREATE TABLE friends
(

    ID_FRIEND         SERIAL PRIMARY KEY,
    EMAIL_P_INITIATOR varchar         not null,
    EMAIL_P_FRIEND    varchar         not null,
    FS_START_DATE     date            not null,
    IS_TIMED_OUT      bool
);

INSERT INTO friends( EMAIL_P_INITIATOR, EMAIL_P_FRIEND, FS_START_DATE, IS_TIMED_OUT)
VALUES ( 'john.doe@email.com', 'anna@mustermann.de', '2019-01-31', FALSE),
       ( 'anna@mustermann.de', 'hans.m@gmail.com', '2019-02-20', FALSE),
       ( 'max@mustermann.de', 'john.doe@email.com', '2016-10-10', FALSE),
       ( 'max@mustermann.de', 'anna@mustermann.de', '2016-10-10', FALSE),
       ( 'hans.m@gmail.com', 'anna@mustermann.de', '2009-01-05', TRUE),
       ( 'm.schmidt@gmail.com', 'max@mustermann.de', '2009-01-05', TRUE);

