--liquibase formatted sql
--changeset liquibase:0002.create_author
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'AUTHOR' LIMIT 1;
CREATE TABLE AUTHOR (
	ID CHAR(36) NOT NULL,
	NAME VARCHAR(100) NOT NULL,
	CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LAST_MODIFIED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;