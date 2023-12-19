-- Inicializacion de Tablas

DROP TABLE IF EXISTS STUDENT;
DROP TABLE IF EXISTS REPEATER;
DROP TABLE IF EXISTS PROMOTED;
DROP TABLE IF EXISTS CAUSE_OF_DISMISSAL;
DROP TABLE IF EXISTS DISMISSAL;
DROP TABLE IF EXISTS ACADEMIC_YEAR;
DROP TABLE IF EXISTS GROUP_ST;
DROP TABLE IF EXISTS ACADEMIC_SUBJECT;
DROP TABLE IF EXISTS TYPE_OF_EVALUATION;
DROP TABLE IF EXISTS EVALUATION;
DROP TABLE IF EXISTS USER_APP;
DROP TABLE IF EXISTS ROLE_USER;


CREATE TABLE STUDENT (
id_std SERIAL PRIMARY KEY NOT NULL,
names_std VARCHAR(256) NOT NULL CHECK(CHARACTER_LENGTH(TRIM(names_std)) > 0),
sex_std CHAR NOT NULL CHECK(sex_std='F' OR sex_std='M'),
mun_std VARCHAR(64) NOT NULL CHECK(CHARACTER_LENGTH(TRIM(mun_std)) > 0),
id_y_gr_std INTEGER NOT NULL,
lastnames_std VARCHAR(256) NOT NULL CHECK(CHARACTER_LENGTH(TRIM(lastnames_std)) > 0),
type_std CHAR NOT NULL CHECK(type_std='P' OR type_std='R' OR type_std='D'),
id_gr_std INTEGER NOT NULL);

CREATE TABLE REPEATER (
id_std INTEGER PRIMARY KEY NOT NULL);

CREATE TABLE PROMOTED (
id_std INTEGER PRIMARY KEY NOT NULL);

CREATE TABLE CAUSE_OF_DISMISSAL (
id_dm SERIAL PRIMARY KEY NOT NULL,
dm VARCHAR(128) NOT NULL UNIQUE CHECK(CHARACTER_LENGTH(TRIM(dm)) > 0));

CREATE TABLE DISMISSAL (
id_std INTEGER PRIMARY KEY NOT NULL,
id_dm_dm INTEGER NOT NULL);

CREATE TABLE ACADEMIC_YEAR (
id_y SERIAL PRIMARY KEY NOT NULL,
year_y INTEGER NOT NULL CHECK(year_y BETWEEN 1 AND 7),
school_course_start_y INTEGER NOT NULL CHECK(school_course_start_y BETWEEN 2000 AND 2100),
school_course_end_y INTEGER NOT NULL CHECK((school_course_end_y BETWEEN 2000 AND 2100) AND school_course_start_y<=school_course_end_y));

CREATE TABLE GROUP_ST (
id_gr SERIAL NOT NULL,
id_y_gr INTEGER NOT NULL,
gr_number INTEGER NOT NULL UNIQUE CHECK(gr_number>0),
classroom_gr INTEGER NOT NULL CHECK(classroom_gr >= 0),
PRIMARY KEY (id_gr,id_y_gr));

CREATE TABLE ACADEMIC_SUBJECT (
id_sub SERIAL PRIMARY KEY NOT NULL,
name_sub VARCHAR(128) NOT NULL CHECK(CHARACTER_LENGTH(TRIM(name_sub)) > 0),
hours_plan_sub INTEGER NOT NULL CHECK(hours_plan_sub > 0),
id_y_sub INTEGER NOT NULL);

CREATE TABLE TYPE_OF_EVALUATION (
id_eval SERIAL PRIMARY KEY NOT NULL,
eval VARCHAR(60) NOT NULL UNIQUE CHECK(CHARACTER_LENGTH(TRIM(eval)) > 0),
eval_num INTEGER NOT NULL UNIQUE CHECK(eval_num >= 0));

CREATE TABLE EVALUATION (
id_sub_ev INTEGER NOT NULL,
id_std_ev INTEGER NOT NULL,
id_eval_ev INTEGER,
PRIMARY KEY (id_sub_ev,id_std_ev));

CREATE TABLE USER_APP (
id_user SERIAL PRIMARY KEY NOT NULL,
id_role_user INTEGER NOT NULL,
mail_user VARCHAR(128) NOT NULL UNIQUE CHECK(CHARACTER_LENGTH(TRIM(mail_user)) > 0),
password_user VARCHAR(64) NOT NULL CHECK(CHARACTER_LENGTH(password_user) > 0),
id_std_user INTEGER);

CREATE TABLE ROLE_USER(
id_role SERIAL PRIMARY KEY NOT NULL,
type_role VARCHAR(64) NOT NULL UNIQUE CHECK(CHARACTER_LENGTH(TRIM(type_role)) > 0));


ALTER TABLE STUDENT ADD CONSTRAINT STUDENT_foreign_key_GROUP_ST 
FOREIGN KEY (id_y_gr_std, id_gr_std) REFERENCES GROUP_ST(id_y_gr, id_gr) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE REPEATER ADD CONSTRAINT REPEATER_id_std_STUDENT_id_std 
FOREIGN KEY (id_std) REFERENCES STUDENT(id_std) ON DELETE CASCADE;

ALTER TABLE PROMOTED ADD CONSTRAINT PROMOTED_id_std_STUDENT_id_std 
FOREIGN KEY (id_std) REFERENCES STUDENT(id_std) ON DELETE CASCADE;

ALTER TABLE DISMISSAL ADD CONSTRAINT DISMISSAL_id_std_STUDENT_id_std 
FOREIGN KEY (id_std) REFERENCES STUDENT(id_std) ON DELETE CASCADE;

ALTER TABLE DISMISSAL ADD CONSTRAINT DISMISSAL_id_dm_dm_CAUSE_OF_DISMISSAL_id_dm 
FOREIGN KEY (id_dm_dm) REFERENCES CAUSE_OF_DISMISSAL(id_dm) ON DELETE CASCADE;

ALTER TABLE GROUP_ST ADD CONSTRAINT GROUP_ST_id_y_gr_ACADEMIC_YEAR_id_y 
FOREIGN KEY (id_y_gr) REFERENCES ACADEMIC_YEAR(id_y) ON DELETE CASCADE;

ALTER TABLE ACADEMIC_SUBJECT ADD CONSTRAINT ACADEMIC_SUBJECT_id_y_sub_ACADEMIC_YEAR_id_y 
FOREIGN KEY (id_y_sub) REFERENCES ACADEMIC_YEAR(id_y) ON DELETE CASCADE;

ALTER TABLE EVALUATION ADD CONSTRAINT EVALUATION_id_sub_ev_ACADEMIC_SUBJECT_id_sub 
FOREIGN KEY (id_sub_ev) REFERENCES ACADEMIC_SUBJECT(id_sub) ON DELETE CASCADE;

ALTER TABLE EVALUATION ADD CONSTRAINT EVALUATION_id_std_ev_STUDENT_id_std 
FOREIGN KEY (id_std_ev) REFERENCES STUDENT(id_std) ON DELETE CASCADE;

ALTER TABLE EVALUATION ADD CONSTRAINT EVALUATION_id_eval_ev_TYPE_OF_EVALUATION_id_eval 
FOREIGN KEY (id_eval_ev) REFERENCES TYPE_OF_EVALUATION(id_eval) ON DELETE CASCADE;

ALTER TABLE USER_APP ADD CONSTRAINT  USER_APP_id_role_user_ROLE_USER_id_role 
FOREIGN KEY(id_role_user) REFERENCES ROLE_USER(id_role) ON DELETE CASCADE;

ALTER TABLE USER_APP ADD CONSTRAINT  USER_APP_id_std_user_ROLE_USER_id_std 
FOREIGN KEY(id_std_user) REFERENCES STUDENT(id_std) ON DELETE CASCADE;


-- --------------

CREATE SEQUENCE mail_user_number
START WITH 1
INCREMENT BY 1
minvalue 1;