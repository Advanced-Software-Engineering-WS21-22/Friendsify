CREATE TABLE friends
(
    ID_FRIEND         bigint primary key not null AUTO_INCREMENT,
    EMAIL_P_INITIATOR varchar         not null,
    EMAIL_P_FRIEND    varchar         not null,
    FS_START_DATE     date            not null,
    IS_TIMED_OUT      bool
)DEFAULT AUTO_INCREMENT=5;

INSERT INTO friends(ID_FRIEND, EMAIL_P_INITIATOR, EMAIL_P_FRIEND, FS_START_DATE, IS_TIMED_OUT)
VALUES (0, 'hans.m@gmail.com', 'anna@mustermann.de', '2019-01-31', false),
       (1, 'anna@mustermann.de', 'hans.m@gmail.com', '2019-02-20', false),
       (2, 'max@mustermann.de', 'john.doe@email.com', '2016-10-10', false),
       (3, 'hans.m@gmail.com', 'anna@mustermann.de', '2009-01-05', true);