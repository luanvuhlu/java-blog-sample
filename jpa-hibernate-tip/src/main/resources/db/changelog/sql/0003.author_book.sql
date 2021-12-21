--liquibase formatted sql
--changeset liquibase:0002.create_author_book
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'AUTHOR_BOOK' LIMIT 1;
CREATE TABLE AUTHOR_BOOK (
	ID CHAR(36) NOT NULL,
    BOOK_ID CHAR(36) NOT NULL,
    AUTHOR_ID CHAR(36) NOT NULL,
	CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT AUTHOR_BOOK_UQ UNIQUE (BOOK_ID, AUTHOR_ID),
	PRIMARY KEY(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;