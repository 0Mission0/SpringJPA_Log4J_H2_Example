--DROP TABLE users IF EXISTS;

CREATE TABLE customer (
	id	INTEGER PRIMARY KEY,
	firstName	VARCHAR(30),
	lastName	VARCHAR(30)
);

-- For Log4j 
CREATE TABLE LOGS (
    APPLICATION VARCHAR2(20) NULL,
    LOG_DATE   DATE      NOT NULL,
    LOGGER  VARCHAR2(250)    NOT NULL,
    LOG_LEVEL   VARCHAR2(10)    NOT NULL,
    MESSAGE VARCHAR2(4000)  NOT NULL
);