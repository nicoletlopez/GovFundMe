PRAGMA FOREIGN_KEYS = ON;

CREATE TABLE card_type (
  type_id   INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  type_name TEXT    NOT NULL
);
CREATE TABLE card (

  card_id      INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  card_balance INTEGER NOT NULL,
  type_id      INTEGER NOT NULL,
  CONSTRAINT card_fk FOREIGN KEY (type_id) REFERENCES card_type (type_id)
);
SELECT *
FROM card;
CREATE TABLE user (
  user_id       INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  user_lname    TEXT    NOT NULL,
  user_fname    TEXT    NOT NULL,
  user_username TEXT    NOT NULL,
  user_password TEXT    NOT NULL,
  user_email    TEXT    NOT NULL,
  card_id       INTEGER NOT NULL,
  CONSTRAINT user_fk FOREIGN KEY (card_id) REFERENCES card (card_id)
);
CREATE TABLE project (
  project_id       INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  project_name     TEXT    NOT NULL,
  project_desc     TEXT    NOT NULL,
  project_balance  INTEGER NOT NULL,
  project_target   INTEGER NOT NULL,
  user_creator_id  INTEGER NOT NULL,
  project_status   TEXT    NOT NULL,
  project_category TEXT    NOT NULL,
  CONSTRAINT project_fk FOREIGN KEY (user_creator_id) REFERENCES user (user_id)
);
CREATE TABLE discontinued (
  project_id INTEGER NOT NULL PRIMARY KEY,
  CONSTRAINT discontinued_fk FOREIGN KEY (project_id) REFERENCES project (project_id),
  CONSTRAINT discontinued_uk UNIQUE (project_id)
);
CREATE TABLE ongoing (
  project_id INTEGER NOT NULL PRIMARY KEY,
  CONSTRAINT ongoing_fk FOREIGN KEY (project_id) REFERENCES project (project_id),
  CONSTRAINT ongoing_uk UNIQUE (project_id)
);
CREATE TABLE success (
  project_id INTEGER NOT NULL PRIMARY KEY,
  CONSTRAINT success_fk FOREIGN KEY (project_id) REFERENCES project (project_id),
  CONSTRAINT success_uk UNIQUE (project_id)
);
CREATE TABLE donation_receipt (
  user_id    INTEGER NOT NULL,
  project_id INTEGER NOT NULL,
  CONSTRAINT donation_receipt_pk PRIMARY KEY (user_id, project_id),
  CONSTRAINT donation_receipt_fk_user_id FOREIGN KEY (user_id) REFERENCES user (user_id),
  CONSTRAINT donation_receipt_fk_project_id FOREIGN KEY (project_id) REFERENCES project (project_id)
);

--SELECT *
--FROM donation_receipt;
--DROP TABLE donation_receipt;
/*
drop table card;
drop table card_type;
drop table project;
drop table user;
drop table discontinued;
drop table donation_receipt;
drop table ongoing;
drop table success;
*/
