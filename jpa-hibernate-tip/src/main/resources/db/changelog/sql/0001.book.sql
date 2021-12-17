--liquibase formatted sql
--changeset liquibase:0001.create_book
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'BOOK' LIMIT 1;
CREATE TABLE BOOK (
	ID CHAR(36) NOT NULL,
	TITLE VARCHAR(100) NOT NULL, 
	AUTHOR_ID CHAR(36) DEFAULT NULL,
	CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LAST_MODIFIED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;