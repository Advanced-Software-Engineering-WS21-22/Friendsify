CREATE TABLE friends
(
    EMAIL_P_INITIATOR varchar  not null,
    EMAIL_P_FRIEND    varchar  not null,
    FS_START_DATE     date                not null,
    IS_TIMED_OUT      bool,
    PRIMARY KEY (EMAIL_P_FRIEND, EMAIL_P_INITIATOR)
);

INSERT INTO friends(EMAIL_P_INITIATOR, EMAIL_P_FRIEND, FS_START_DATE, IS_TIMED_OUT)
VALUES ( 'hans.m@gmail.com', 'anna@mustermann.de', '2019-01-31', false),
       ( 'anna@mustermann.de', 'hans.m@gmail.com', '2019-02-20', false),
       ( 'max@mustermann.de', 'john.doe@email.com', '2016-10-10', false),
       ( 'hans.m@gmail.com', 'anna@mustermann.de', '2009-01-05', true);