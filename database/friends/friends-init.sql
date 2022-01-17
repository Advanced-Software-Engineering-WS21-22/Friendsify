
CREATE TABLE friends
(
    ID_FS          int primary key not null,
    ID_P_INITIATOR int             not null,
    ID_P_FRIEND    int             not null,
    FS_START_DATE  date            not null,
    IS_TIMED_OUT   bool
);

INSERT INTO friends(ID_FS, ID_P_INITIATOR, ID_P_FRIEND, FS_START_DATE, IS_TIMED_OUT)
VALUES (0, 3, 1, '2019-01-31', false),
       (1, 1, 3, '2019-02-20', false),
       (2, 0, 2, '2016-10-10', false),
       (3, 2, 1, '2009-01-05', true);