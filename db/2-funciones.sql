-- Funciones

-- Academic subject
CREATE OR REPLACE FUNCTION "public"."add_academic_subject"("name_sub" varchar, "hours_plan_sub" int4, "id_y_sub" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	INSERT INTO academic_subject(name_sub, hours_plan_sub, id_y_sub) VALUES (TRIM($1),$2,$3);
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."delete_academic_subject"("id_sub" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	DELETE FROM academic_subject WHERE academic_subject.id_sub = $1;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_academic_subject"("id_sub" int4)
  RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
open r for SELECT academic_subject.*, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y
	FROM academic_subject, academic_year
	WHERE academic_subject.id_sub = $1 AND academic_subject.id_y_sub = academic_year.id_y;
RETURN r; 
END;
$BODY$
  LANGUAGE plpgsql;
		
CREATE OR REPLACE FUNCTION "public"."get_all_academic_subject"()
  RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
open r for SELECT academic_subject.*, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y
	FROM academic_subject, academic_year
	WHERE academic_subject.id_y_sub = academic_year.id_y;
return r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."update_academic_subject"("id_sub" int4, "name_sub" varchar, "hours_plan_sub" int4, "id_y_sub" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	UPDATE academic_subject
	SET name_sub = TRIM($2), hours_plan_sub = $3, id_y_sub = $4
	WHERE academic_subject.id_sub = $1;
END;
$BODY$
  LANGUAGE plpgsql;

-- Academic year
CREATE OR REPLACE FUNCTION "public"."add_academic_year"("year_y" int4, "school_course_start_y" int4, "school_course_end_y" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	INSERT INTO academic_year(year_y, school_course_start_y, school_course_end_y) VALUES ($1,$2,$3);
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."delete_academic_year"("id_y" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	DELETE FROM academic_year 
	WHERE academic_year.id_y = $1;
END;
$BODY$
  LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "public"."get_academic_year"("id_y" int4)
  RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for SELECT academic_year.*
	FROM academic_year
	WHERE academic_year.id_y = $1;
	RETURN r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_all_academic_year"()
  RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
open r for SELECT academic_year.*
	FROM academic_year
	ORDER BY academic_year.id_y ASC;
return r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."update_academic_year"("id_y" int4, "year_y" int4, "school_course_start_y" int4, "school_course_end_y" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	UPDATE academic_year
	SET year_y = $2, school_course_start_y = $3, school_course_start_y = $4
	WHERE academic_year.id_y = $1;
END;
$BODY$
  LANGUAGE plpgsql;

-- Cause of dismissal
CREATE OR REPLACE FUNCTION "public"."add_cause_of_dismissal"("dismissal" varchar)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	INSERT INTO cause_of_dismissal(dm) VALUES (TRIM($1));
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."delete_cause_of_dismissal"("id_dm" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	DELETE FROM cause_of_dismissal WHERE cause_of_dismissal.id_dm = $1;
END;
$BODY$
  LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "public"."get_all_cause_of_dismissal"()
  RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
	OPEN r for SELECT *
		FROM cause_of_dismissal;
	return r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_cause_of_dismissal"("id_dm" int4)
  RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
open r for SELECT cause_of_dismissal.*
	FROM cause_of_dismissal
	WHERE cause_of_dismissal.id_dm = $1;
RETURN r; 
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."update_cause_of_dismissal"("id_dm" int4, "dm" varchar)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	UPDATE cause_of_dismissal SET dm = TRIM($2)
	WHERE cause_of_dismissal.id_dm = $1;
END;
$BODY$
  LANGUAGE plpgsql;

-- Dismissal (No tiene add, delete, update)
CREATE OR REPLACE FUNCTION "public"."get_all_dismissal"()
  RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for SELECT student.*, group_st.classroom_gr, group_st.gr_number, group_st.id_y_gr, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y, cause_of_dismissal.*
	FROM dismissal, student, cause_of_dismissal, group_st, academic_year
	WHERE dismissal.id_std = student.id_std AND dismissal.id_dm_dm = cause_of_dismissal.id_dm AND
	group_st.id_gr = student.id_gr_std AND group_st.id_y_gr = academic_year.id_y;
	return r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_dismissal"("id_std" int4)
  RETURNS REFCURSOR AS $BODY$
	DECLARE 
	r REFCURSOR := 'm';
BEGIN
	OPEN r for SELECT student.*, group_st.classroom_gr, group_st.gr_number, group_st.id_y_gr, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y, cause_of_dismissal.*
	FROM dismissal, student, cause_of_dismissal, group_st, academic_year
	WHERE dismissal.id_std = student.id_std AND dismissal.id_std = $1  AND 
	dismissal.id_dm_dm= cause_of_dismissal.id_dm AND 
	group_st.id_gr = student.id_gr_std AND group_st.id_y_gr = academic_year.id_y;
	RETURN r;
END;
$BODY$
  LANGUAGE plpgsql;

-- Evaluation
CREATE OR REPLACE FUNCTION "public"."add_evaluation"("id_sub_ev" int4, "id_std_ev" int4, "id_eval_ev" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	INSERT INTO evaluation VALUES ($1,$2,$3);
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."delete_evaluation"("id_sub_ev" int4, "id_std_ev" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	DELETE FROM evaluation
	WHERE evaluation.id_sub_ev = $1 AND evaluation.id_std_ev=$2;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_all_evaluation"()
  RETURNS REFCURSOR AS $BODY$
	DECLARE 
	r REFCURSOR := 'm';
BEGIN
open r for SELECT academic_subject.*, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y, student.*, group_st.classroom_gr, group_st.gr_number, type_of_evaluation.*
	FROM evaluation, academic_subject, student, academic_year, type_of_evaluation, group_st
	WHERE academic_subject.id_sub = evaluation.id_sub_ev AND student.id_std=evaluation.id_std_ev
	AND academic_subject.id_y_sub = academic_year.id_y
	AND evaluation.id_eval_ev = type_of_evaluation.id_eval
	AND group_st.id_gr = student.id_gr_std AND group_st.id_y_gr = student.id_y_gr_std;
return r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_evaluation"("id_sub_ev" int4, "id_std_ev" int4)
  RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for SELECT academic_subject.*, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y, student.*, group_st.classroom_gr, group_st.gr_number, type_of_evaluation.*
	FROM evaluation, academic_subject, student, academic_year, type_of_evaluation, group_st
	WHERE evaluation.id_sub_ev = $1 AND evaluation.id_std_ev=$2 AND academic_subject.id_sub = $1 
	AND student.id_std=$2 AND academic_subject.id_y_sub = academic_year.id_y
	AND evaluation.id_eval_ev = type_of_evaluation.id_eval
	AND group_st.id_gr = student.id_gr_std AND group_st.id_y_gr = student.id_y_gr_std;
	RETURN r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_all_evaluation_null"()
  RETURNS REFCURSOR AS $BODY$
	DECLARE 
	r REFCURSOR := 'm';
BEGIN
open r for SELECT DISTINCT academic_subject.*, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y, student.*, group_st.classroom_gr, group_st.gr_number
	FROM evaluation, academic_subject, student, academic_year, type_of_evaluation, group_st
	WHERE academic_subject.id_sub = evaluation.id_sub_ev AND student.id_std=evaluation.id_std_ev
	AND academic_subject.id_y_sub = academic_year.id_y
	AND evaluation.id_eval_ev IS NULL
	AND group_st.id_gr = student.id_gr_std AND group_st.id_y_gr = student.id_y_gr_std;
return r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."update_evaluation"("id_sub_ev" int4, "id_std_ev" int4, "id_eval_ev" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	UPDATE evaluation
	SET id_eval_ev = $3
	WHERE evaluation.id_sub_ev = $1 AND evaluation.id_std_ev=$2;
END;
$BODY$
  LANGUAGE plpgsql;

-- Group st
CREATE OR REPLACE FUNCTION "public"."add_group_st"("id_y_gr" int4, "gr_number" int4, "classroom_gr" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	INSERT INTO group_st(id_y_gr, gr_number, classroom_gr) VALUES ($1,$2,$3);
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."delete_group_st"("id_gr" int4, "id_y_gr" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	DELETE FROM group_st
	WHERE group_st.id_gr = $1 AND group_st.id_y_gr=$2;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_all_group_st"()
  RETURNS REFCURSOR AS $BODY$
	DECLARE 
	r REFCURSOR := 'm';
BEGIN
open r for SELECT group_st.*, academic_year.year_y, 
	academic_year.school_course_start_y, academic_year.school_course_end_y
	FROM group_st, academic_year
	WHERE academic_year.id_y = group_st.id_y_gr;
return r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_group_st"("id_gr" int4, "id_y_gr" int4)
  RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for SELECT group_st.*, academic_year.year_y, 
	academic_year.school_course_start_y, academic_year.school_course_end_y
	FROM group_st, academic_year
	WHERE group_st.id_gr = $1 AND group_st.id_y_gr=$2 AND 
	academic_year.id_y = group_st.id_y_gr;
	RETURN r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."update_group_st"("id_gr" int4, "id_y_gr" int4, "gr_number" int4, "classroom_gr" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	UPDATE group_st
	SET gr_number=$3, classroom_gr = $4
	WHERE group_st.id_gr = $1 AND group_st.id_y_gr=$2;
END;
$BODY$
  LANGUAGE plpgsql;

-- Promoted (No tiene add, delete, update)
CREATE OR REPLACE FUNCTION "public"."get_all_promoted"()
  RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
open r for SELECT student.*, group_st.classroom_gr, group_st.gr_number, group_st.id_y_gr, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y
	FROM promoted, student, group_st, academic_year
	WHERE promoted.id_std = student.id_std AND 
	group_st.id_gr = student.id_gr_std AND group_st.id_y_gr = student.id_y_gr_std AND
	group_st.id_y_gr = academic_year.id_y;
return r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_promoted"("id_std" int4)
  RETURNS REFCURSOR AS $BODY$
	DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for SELECT student.*, group_st.classroom_gr, group_st.gr_number, group_st.id_y_gr, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y
	FROM promoted, student, group_st, academic_year
	WHERE promoted.id_std = student.id_std AND promoted.id_std = $1 AND
	group_st.id_gr = student.id_gr_std AND group_st.id_y_gr = student.id_y_gr_std AND
	group_st.id_y_gr = academic_year.id_y;
	RETURN r;
END;
$BODY$
  LANGUAGE plpgsql;

-- Repeater (No tiene add, delete, update)
CREATE OR REPLACE FUNCTION "public"."get_all_repeater"()
  RETURNS REFCURSOR AS $BODY$
	DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for SELECT student.*, group_st.classroom_gr, group_st.gr_number, group_st.id_y_gr, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y
	FROM repeater, student, group_st, academic_year
	WHERE repeater.id_std = student.id_std AND 
	group_st.id_gr = student.id_gr_std AND group_st.id_y_gr = student.id_y_gr_std AND
	group_st.id_y_gr = academic_year.id_y;
	return r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_repeater"("id_std" int4)
  RETURNS REFCURSOR AS $BODY$
	DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for SELECT student.*, group_st.classroom_gr, group_st.gr_number, group_st.id_y_gr, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y
	FROM repeater, student, group_st, academic_year
	WHERE repeater.id_std = student.id_std AND repeater.id_std = $1 AND
	group_st.id_gr = student.id_gr_std AND group_st.id_y_gr = student.id_y_gr_std AND
	group_st.id_y_gr = academic_year.id_y;
	RETURN r;
END;
$BODY$
  LANGUAGE plpgsql;

-- Role User
CREATE OR REPLACE FUNCTION "public"."add_role_user"("type_role" varchar)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	INSERT INTO role_user(type_role) VALUES(TRIM($1));
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."delete_role_user"("id_role" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	DELETE FROM role_user WHERE role_user.id_role = $1;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_all_role_user"()
  RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for SELECT * 
		FROM role_user;
	return r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_role_user"("id_role" int4)
  RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for SELECT role_user.*
	FROM role_user
	WHERE role_user.id_role = $1;
RETURN r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."update_role_user"("id_role" int4, "type_role" varchar)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	UPDATE role_user SET type_role=TRIM($2) WHERE role_user.id_role=$1;	
END;
$BODY$
  LANGUAGE plpgsql;

-- Student
CREATE OR REPLACE FUNCTION "public"."add_student"("names_std" varchar, "sex_std" bpchar, "mun_std" varchar, "id_y_gr_std" int4, "lastnames_std" varchar, "type_std" bpchar, "id_gr_std" int4, "id_dm_dm" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
DECLARE
	id_st INTEGER;
BEGIN
	INSERT INTO student(names_std, sex_std, mun_std, id_y_gr_std, lastnames_std, type_std, id_gr_std) VALUES (TRIM($1),UPPER($2),TRIM($3),$4,TRIM($5),UPPER($6),$7);
	
	Select student.id_std INTO id_st
	FROM student
	ORDER BY student.id_std DESC;
	
	IF UPPER($6)='P' THEN
		INSERT INTO promoted VALUES(id_st);
	ELSIF UPPER($6)='R' THEN 
		INSERT INTO repeater VALUES(id_st);
	ELSIF UPPER($6)='D' THEN
		INSERT INTO dismissal VALUES(id_st, $8);
	END IF;
	
	EXECUTE public.automatic_insert_evaluation_student(id_st, $4, UPPER($6));
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."delete_student"("id_std" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	DELETE FROM student
	WHERE student.id_std = $1;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_all_student"()
  RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for SELECT student.*, group_st.classroom_gr, group_st.gr_number, group_st.id_y_gr, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y
	FROM student, group_st, academic_year
	WHERE student.id_gr_std = group_st.id_gr AND student.id_y_gr_std = group_st.id_y_gr AND 
	group_st.id_y_gr = academic_year.id_y;
	return r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_student"("id_std" int4)
  RETURNS REFCURSOR AS $BODY$
	DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for SELECT student.*, group_st.classroom_gr, group_st.gr_number, group_st.id_y_gr, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y
	FROM student, group_st, academic_year
	WHERE student.id_std = $1 AND student.id_gr_std = group_st.id_gr AND 
	student.id_y_gr_std = group_st.id_y_gr AND 
	group_st.id_y_gr = academic_year.id_y;
	RETURN r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."update_student"("id_std" int4, "names_std" varchar, "sex_std" bpchar, "mun_std" varchar, "id_y_gr_std" int4, "lastnames_std" varchar, "type_std" bpchar, "id_gr_std" int4, "id_dm_dm" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
DECLARE 
	-- Find where is the students table according to his type
	type_std_before char := (SELECT aux_find_type_of_student($1));
BEGIN
	UPDATE student SET names_std=TRIM($2), sex_std=UPPER($3), mun_std=TRIM($4), id_y_gr_std=$5, lastnames_std=TRIM($6), type_std=UPPER($7), id_gr_std=$8 WHERE student.id_std=$1;
	
	-- Case in which the type isnt modified
	IF UPPER($7)=type_std_before THEN
		IF UPPER($7)='D' THEN
			UPDATE dismissal SET id_dm_dm=$9 WHERE dismissal.id_std=$1;
		END IF;
	-- Case in which the type is modified
	ELSE
		EXECUTE aux_delete_type_of_student($1, type_std_before);
		EXECUTE aux_add_type_of_student($1, UPPER($7), $9);
	END IF;

END;
$BODY$
  LANGUAGE plpgsql;

-- Type of evaluation
CREATE OR REPLACE FUNCTION "public"."add_type_of_evaluation"("eval" varchar, "eval_num" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	INSERT INTO type_of_evaluation(eval, eval_num) VALUES (TRIM($1), $2);
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."delete_type_of_evaluation"("id_eval" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	DELETE FROM type_of_evaluation WHERE type_of_evaluation.id_eval = $1;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_all_type_of_evaluation"()
  RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for SELECT *
		FROM type_of_evaluation;
	return r; 
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_type_of_evaluation"("id_eval" int4)
  RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
    open r for SELECT type_of_evaluation.*
    FROM type_of_evaluation
    WHERE type_of_evaluation.id_eval=$1;
RETURN r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."update_type_of_evaluation"("id_eval" int4, "eval" varchar, "eval_num" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	UPDATE type_of_evaluation SET eval = $2, eval_num = $3
	WHERE type_of_evaluation.id_eval = $1;
END;
$BODY$
  LANGUAGE plpgsql;

-- User app
CREATE OR REPLACE FUNCTION "public"."add_user_app"("id_role_user" int4, "mail_user" varchar, "password_user" varchar, "id_std_user" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	INSERT INTO user_app (id_role_user, mail_user, password_user, id_std_user) VALUES ($1,TRIM($2),$3,$4);
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."delete_user_app"("id_user" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	DELETE FROM user_app
	WHERE user_app.id_user = $1;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_all_user_app"()
  RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for SELECT user_app.*, role_user.type_role
	FROM user_app, role_user
	WHERE role_user.id_role = user_app.id_role_user;
	return r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."get_user_app"("id_user" int4)
  RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for SELECT user_app.*, role_user.type_role
	FROM user_app, role_user
	WHERE user_app.id_user = $1 AND role_user.id_role = user_app.id_role_user;
	RETURN r;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."update_user_app"("id_user" int4, "id_role_user" int4, "mail_user" varchar, "password_user" varchar, "id_std_user" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	UPDATE user_app
	SET id_role_user = $2, mail_user = TRIM($3), password_user = $4, id_std_user = $5
	WHERE user_app.id_user = $1;
END;
$BODY$
  LANGUAGE plpgsql;
	
-- Metodos Auxiliares
CREATE OR REPLACE FUNCTION "public"."aux_add_type_of_student"("id_std" int4, "type_std" bpchar, "id_dm_dm" int4)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	IF UPPER($2)='P' THEN
		INSERT INTO promoted VALUES($1);
	ELSIF UPPER($2)='R' THEN
		INSERT INTO repeater VALUES($1);
	ELSIF UPPER($2)='D' THEN
		INSERT INTO dismissal VALUES($1, $3);
	END IF;
END;
$BODY$
  LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "public"."aux_delete_type_of_student"("id_std" int4, "type_std" bpchar)
  RETURNS "pg_catalog"."void" AS $BODY$
BEGIN
	IF UPPER($2)='P' THEN
		DELETE FROM promoted WHERE promoted.id_std=$1;
	ELSIF UPPER($2)='R' THEN
		DELETE FROM repeater WHERE repeater.id_std=$1;
	ELSIF UPPER($2)='D' THEN
		DELETE FROM dismissal WHERE dismissal.id_std=$1;
	END IF;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."aux_find_type_of_student"("id_std" int4)
  RETURNS "pg_catalog"."bpchar" AS $BODY$
DECLARE
	type_st character;
BEGIN
	SELECT student.type_std INTO type_st
	FROM promoted, student
	WHERE promoted.id_std=student.id_std AND promoted.id_std=$1;
	
	IF type_st IS NULL THEN
		SELECT student.type_std INTO type_st
		FROM repeater, student
		WHERE repeater.id_std=student.id_std AND repeater.id_std=$1;
	END IF;
	
	IF type_st IS NULL THEN
		SELECT student.type_std INTO type_st
		FROM dismissal, student
		WHERE dismissal.id_std=student.id_std AND dismissal.id_std=$1;
	END IF;
	
	RETURN type_st;
	
END;
$BODY$
  LANGUAGE plpgsql;
	
-- Reportes
CREATE OR REPLACE FUNCTION get_subject_filter(c_start integer, c_end integer, acad_year integer) RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN

	IF $1 IS NULL AND $2 IS NULL AND $3 IS NULL THEN
	open r for 
	SELECT academic_subject.name_sub, academic_subject.hours_plan_sub, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y
	FROM academic_subject, academic_year
	WHERE academic_subject.id_y_sub = academic_year.id_y;
	
	ELSIF $1 IS NOT NULL AND $2 IS NOT NULL AND $3 IS NULL THEN
	open r for 
	SELECT academic_subject.name_sub, academic_subject.hours_plan_sub, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y
	FROM academic_subject, academic_year
	WHERE academic_subject.id_y_sub = academic_year.id_y AND academic_year.school_course_start_y = $1 AND academic_year.school_course_end_y = $2;
	
	ELSIF $1 IS NULL AND $2 IS NULL AND $3 IS NOT NULL THEN
	open r for 
	SELECT academic_subject.name_sub, academic_subject.hours_plan_sub, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y
	FROM academic_subject, academic_year
	WHERE academic_subject.id_y_sub = academic_year.id_y AND academic_year.year_y = $3;
	
	ELSIF $1 IS NOT NULL AND $2 IS NOT NULL AND $3 IS NOT NULL THEN
	open r for 
	SELECT academic_subject.name_sub, academic_subject.hours_plan_sub, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y
	FROM academic_subject, academic_year
	WHERE academic_subject.id_y_sub = academic_year.id_y AND academic_year.school_course_start_y = $1 AND academic_year.school_course_end_y = $2 AND academic_year.year_y = $3;
	
	END IF;
	
return r;
END;
$BODY$
  LANGUAGE plpgsql;
	
	
CREATE OR REPLACE FUNCTION get_students_filter() RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for 
	SELECT student.id_std, student.names_std, student.lastnames_std, group_st.gr_number, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y
	FROM group_st, academic_year, student
	WHERE student.id_gr_std = group_st.id_gr AND student.id_y_gr_std = academic_year.id_y
	ORDER BY group_st.gr_number, student.lastnames_std; 
	
return r;
END;
$BODY$
  LANGUAGE plpgsql;
	
	
CREATE OR REPLACE FUNCTION get_students_evaluations_filter() RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for 
	SELECT student.names_std, student.lastnames_std, academic_subject.name_sub, evaluation.id_eval_ev, group_st.gr_number, academic_year.year_y, academic_year.school_course_start_y, academic_year.school_course_end_y
	FROM group_st, academic_year, student, academic_subject, evaluation
	WHERE student.id_gr_std = group_st.id_gr AND student.id_y_gr_std = academic_year.id_y AND evaluation.id_std_ev = student.id_std AND evaluation.id_sub_ev = academic_subject.id_sub
	ORDER BY group_st.gr_number, student.lastnames_std; 
	
return r;
END;
$BODY$
  LANGUAGE plpgsql;
	
	
CREATE OR REPLACE FUNCTION get_student_average(id_stud integer) RETURNS DOUBLE PRECISION AS $BODY$
DECLARE 
	av DOUBLE PRECISION := 5;
BEGIN
	IF EXISTS (SELECT * FROM student, evaluation, type_of_evaluation
					WHERE student.id_std = $1 AND student.id_std = evaluation.id_std_ev 
					AND evaluation.id_eval_ev = type_of_evaluation.id_eval) THEN
	SELECT INTO av AVG(type_of_evaluation.eval_num)
	FROM student, evaluation, type_of_evaluation
	WHERE student.id_std = $1 AND student.id_std = evaluation.id_std_ev AND evaluation.id_eval_ev = type_of_evaluation.id_eval;
	END IF;
return av;
END;
$BODY$
  LANGUAGE plpgsql;
	

CREATE OR REPLACE FUNCTION get_escalafon_group(id_year integer, id_group integer) RETURNS REFCURSOR AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for 
	SELECT student.*, group_st.classroom_gr, group_st.gr_number, group_st.id_y_gr, 
	academic_year.year_y, academic_year.school_course_start_y, 
	academic_year.school_course_end_y, get_student_average(student.id_std) AS prom
	FROM group_st, academic_year, student
	WHERE student.id_gr_std = group_st.id_gr AND student.id_y_gr_std = group_st.id_y_gr AND
	group_st.id_y_gr = academic_year.id_y AND academic_year.id_y = $1 AND group_st.id_gr = $2 AND
	student.type_std != 'D'
	ORDER BY prom DESC; 
	
return r;
END;
$BODY$
  LANGUAGE plpgsql;
	
	
CREATE OR REPLACE FUNCTION "public"."get_escalafon_year"("id_year" int4)
  RETURNS "pg_catalog"."refcursor" AS $BODY$
DECLARE 
	r REFCURSOR := 'm';
BEGIN
	open r for 
	SELECT student.*, group_st.classroom_gr, group_st.gr_number, group_st.id_y_gr, 
	academic_year.year_y, academic_year.school_course_start_y, 
	academic_year.school_course_end_y, get_student_average(student.id_std) AS prom
	FROM group_st, academic_year, student
	WHERE student.id_gr_std = group_st.id_gr AND student.id_y_gr_std = group_st.id_y_gr AND
	group_st.id_y_gr = academic_year.id_y AND  academic_year.id_y = $1 AND
	student.type_std != 'D'
	ORDER BY prom DESC; 
return r;
END;
$BODY$
  LANGUAGE plpgsql;
	
	
CREATE OR REPLACE FUNCTION public.automatic_evaluation_assigment() RETURNS void AS 
$$
DECLARE
	evals CURSOR FOR SELECT evaluation.id_std_ev
									FROM evaluation, student
									WHERE student.id_std = evaluation.id_std_ev AND student.type_std='R';
	rec RECORD;
BEGIN
	UPDATE evaluation SET id_eval_ev= floor(random()*3)+1 WHERE evaluation.id_eval_ev IS NULL;
	
	FOR rec in evals LOOP
		UPDATE evaluation SET id_eval_ev= floor(random()*4)+1 WHERE evaluation.id_std_ev = rec.id_std_ev;
	END LOOP;
END;
$$
LANGUAGE plpgsql;


-- Promocion
CREATE OR REPLACE FUNCTION public.validate_promotion() RETURNS BOOLEAN AS
$$
DECLARE
	validation boolean;
BEGIN
	IF NOT EXISTS (SELECT *
						FROM evaluation
						WHERE evaluation.id_eval_ev IS NULL) THEN
		validation:=TRUE;
	ELSE
		validation:=FALSE;
	END IF;
	RETURN validation;
END;
$$
LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION public.validate_course(course_start integer, course_end integer) RETURNS BOOLEAN AS
$$
DECLARE
	validation boolean;
BEGIN
	IF NOT EXISTS (
	SELECT *
	FROM academic_year
	WHERE academic_year.school_course_start_y=$1 AND academic_year.school_course_end_y=$2
	) AND NOT EXISTS(
		SELECT *
		FROM academic_year
		WHERE academic_year.school_course_start_y>$1
	) AND NOT EXISTS(
		SELECT *
		FROM academic_year
		WHERE academic_year.school_course_end_y>$2
	) AND $1<=$2 THEN
		validation:=true;
	ELSE
		validation:=false;
	END IF;
	RETURN validation;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION public.insert_new_academic_years(course_start_y integer, course_end_y integer) RETURNS VOID AS 
$$
DECLARE
	year_ integer:=1;
BEGIN
	WHILE(year_<=4)
	LOOP
	
	EXECUTE add_academic_year(year_, course_start_y, course_end_y);
	
	year_:=year_+1;
	END LOOP;

END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION public.update_new_groups() RETURNS VOID AS
$$
DECLARE
	rec RECORD;
	groups REFCURSOR;
	id_year_1 integer;
	id_year_2 integer;
	id_year_3 integer;
	id_year_4 integer;
	year_ integer;
BEGIN
	SELECT id_y INTO id_year_1
	FROM academic_year
	WHERE academic_year.year_y=1
	ORDER BY id_y DESC;
	
	SELECT id_y INTO id_year_2
	FROM academic_year
	WHERE academic_year.year_y=2
	ORDER BY id_y DESC;
	
	SELECT id_y INTO id_year_3
	FROM academic_year
	WHERE academic_year.year_y=3
	ORDER BY id_y DESC;
	
	SELECT id_y INTO id_year_4
	FROM academic_year
	WHERE academic_year.year_y=4
	ORDER BY id_y DESC;
	
	OPEN groups FOR SELECT *
							FROM group_st;
							
	LOOP
	FETCH groups INTO rec;
	EXIT WHEN NOT FOUND; 
	
	SELECT year_y INTO year_
	FROM academic_year
	WHERE academic_year.id_y=rec.id_y_gr;
	
	CASE year_
	WHEN 1 THEN
		UPDATE group_st SET id_y_gr=id_year_1 WHERE group_st.id_gr=rec.id_gr;
	WHEN 2 THEN
		UPDATE group_st SET id_y_gr=id_year_2 WHERE group_st.id_gr=rec.id_gr;
	WHEN 3 THEN
		UPDATE group_st SET id_y_gr=id_year_3 WHERE group_st.id_gr=rec.id_gr;
	ELSE
		UPDATE group_st SET id_y_gr=id_year_4 WHERE group_st.id_gr=rec.id_gr;
	END CASE;
	
	END LOOP;

	
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION public.the_promotion() RETURNS VOID AS 
$$
DECLARE
	rec RECORD;
	students CURSOR FOR SELECT *
						FROM student
						WHERE student.type_std != 'D';
	counter integer;
	id_year_1 integer;
	id_year_2 integer;
	id_year_3 integer;
	id_year_4 integer;
	year_ integer;
	finished_dm integer;
	susp_dm integer;
	group_n integer;
	last_group integer;
BEGIN
	SELECT id_y INTO id_year_1
	FROM academic_year
	WHERE academic_year.year_y=1
	ORDER BY id_y DESC;
	
	SELECT id_y INTO id_year_2
	FROM academic_year
	WHERE academic_year.year_y=2
	ORDER BY id_y DESC;
	
	SELECT id_y INTO id_year_3
	FROM academic_year
	WHERE academic_year.year_y=3
	ORDER BY id_y DESC;
	
	SELECT id_y INTO id_year_4
	FROM academic_year
	WHERE academic_year.year_y=4
	ORDER BY id_y DESC;
	
	SELECT cause_of_dismissal.id_dm INTO finished_dm
	FROM cause_of_dismissal
	WHERE cause_of_dismissal.dm = 'Culminación de estudios';
	
	SELECT cause_of_dismissal.id_dm INTO susp_dm
	FROM cause_of_dismissal
	WHERE cause_of_dismissal.dm = 'Desaprobar por segunda vez un año';
	
	
	FOR rec IN students LOOP
	
	SELECT COUNT(evaluation.id_eval_ev) INTO counter
	FROM evaluation, type_of_evaluation, academic_subject
	WHERE evaluation.id_eval_ev = type_of_evaluation.id_eval AND 
				evaluation.id_std_ev = rec.id_std AND type_of_evaluation.eval_num = 2 AND
				evaluation.id_sub_ev = academic_subject.id_sub AND
				rec.id_y_gr_std-4 = academic_subject.id_y_sub;
	
	SELECT group_st.gr_number INTO last_group
	FROM group_st
	WHERE group_st.id_gr = rec.id_gr_std;
	
	SELECT group_st.id_gr INTO group_n
	FROM group_st
	WHERE group_st.gr_number = last_group + 10;
	
	SELECT academic_year.year_y INTO year_
	FROM academic_year
	WHERE academic_year.id_y = rec.id_y_gr_std;
	
	CASE year_
	WHEN 1 THEN
		year_:= id_year_2;
	WHEN 2 THEN
		year_:= id_year_3;
	WHEN 3 THEN
		year_:= id_year_4;
	ELSE
		year_:= -1;
	END CASE;
	
	IF rec.type_std = 'P' AND counter=0 THEN
		IF year_=-1 THEN
			EXECUTE update_student(rec.id_std, rec.names_std, rec.sex_std, rec.mun_std, rec.id_y_gr_std, rec.lastnames_std, 'D', rec.id_gr_std, finished_dm);
			EXECUTE public.automatic_insert_evaluation_student(rec.id_std, rec.id_y_gr_std, 'D');
		ELSE
			SELECT group_st.id_gr INTO group_n
			FROM group_st
			WHERE group_st.gr_number = last_group + 10;
			
			EXECUTE update_student(rec.id_std, rec.names_std, rec.sex_std, rec.mun_std, year_, rec.lastnames_std, 'P', group_n, 1);
			EXECUTE public.automatic_insert_evaluation_student(rec.id_std, year_, 'P');
		END IF;
		
	ELSIF rec.type_std = 'P' and counter>0 THEN
		EXECUTE update_student(rec.id_std, rec.names_std, rec.sex_std, rec.mun_std, rec.id_y_gr_std, rec.lastnames_std, 'R', rec.id_gr_std, 1);
		EXECUTE public.automatic_insert_evaluation_student(rec.id_std, rec.id_y_gr_std, 'R');
		
	ELSIF rec.type_std = 'R' and counter=0 THEN
		IF year_=-1 THEN
			EXECUTE update_student(rec.id_std, rec.names_std, rec.sex_std, rec.mun_std, rec.id_y_gr_std, rec.lastnames_std, 'D', rec.id_gr_std, finished_dm);
			EXECUTE public.automatic_insert_evaluation_student(rec.id_std, rec.id_y_gr_std, 'D');
		ELSE
			SELECT group_st.id_gr INTO group_n
			FROM group_st
			WHERE group_st.gr_number = last_group + 10;
		
			EXECUTE update_student(rec.id_std, rec.names_std, rec.sex_std, rec.mun_std, year_, rec.lastnames_std, 'P', group_n, 1);
			EXECUTE public.automatic_insert_evaluation_student(rec.id_std, year_, 'P');
		END IF;
		
	ELSE
		EXECUTE update_student(rec.id_std, rec.names_std, rec.sex_std, rec.mun_std, rec.id_y_gr_std, rec.lastnames_std, 'D', rec.id_gr_std, susp_dm);
		EXECUTE public.automatic_insert_evaluation_student(rec.id_std, rec.id_y_gr_std, 'D');
	END IF;
	
	END LOOP;

END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION public.make_promotion(course_start_y integer, course_end_y integer) RETURNS VOID AS
$$
BEGIN
	IF NOT validate_promotion() THEN
		RAISE EXCEPTION 'No es posible realizar la promoción';
	ELSIF NOT validate_course($1, $2) THEN
		RAISE EXCEPTION 'Curso no válido';
	END IF;
	
	EXECUTE public.insert_new_academic_years(course_start_y, course_end_y);
	EXECUTE public.update_new_groups();
	EXECUTE public.the_promotion();
	
END;
$$
LANGUAGE plpgsql; 

-- Otras funciones
CREATE OR REPLACE FUNCTION "public"."get_student_average_year"("id_stud" int4, "id_year" int4)
  RETURNS "pg_catalog"."float8" AS $BODY$
DECLARE 
	av DOUBLE PRECISION := 5;
BEGIN
	IF EXISTS (SELECT * FROM student, evaluation, type_of_evaluation, academic_subject, academic_year
					WHERE student.id_std = $1 AND student.id_std = evaluation.id_std_ev 
					AND evaluation.id_eval_ev = type_of_evaluation.id_eval 
					AND academic_subject.id_y_sub = academic_year.id_y 
					AND academic_subject.id_sub = evaluation.id_sub_ev 
					AND academic_year.id_y = $2) THEN
	SELECT INTO av AVG(type_of_evaluation.eval_num)
	FROM student, evaluation, type_of_evaluation, academic_subject, academic_year
	WHERE student.id_std = $1 AND student.id_std = evaluation.id_std_ev AND evaluation.id_eval_ev = type_of_evaluation.id_eval AND academic_subject.id_y_sub = academic_year.id_y AND academic_subject.id_sub = evaluation.id_sub_ev AND academic_year.id_y = $2;
	END IF;
return av;
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION "public"."automatic_insert_evaluation_student"(id_std integer, id_y_gr_std integer, type_std bpchar)
  RETURNS VOID AS $BODY$
DECLARE
	rec RECORD;
	year_ integer;
	id_y_ integer;
	subjects REFCURSOR;
BEGIN
	
	SELECT academic_year.year_y, academic_year.id_y
	INTO year_, id_y_
	FROM academic_year 
	WHERE academic_year.id_y=$2;
	
		IF ($3='D' AND NOT EXISTS ( SELECT * FROM evaluation 
																				WHERE evaluation.id_std_ev=$1
	)) OR $3!='D' THEN
		WHILE (year_>0)
		LOOP
		
		OPEN subjects FOR SELECT academic_subject.id_sub
								FROM academic_subject
								WHERE academic_subject.id_y_sub = id_y_;
							
		LOOP 
			FETCH subjects INTO rec;
			EXIT WHEN NOT FOUND;
			
			IF NOT EXISTS (SELECT * 
											FROM evaluation 
											WHERE evaluation.id_sub_ev=rec.id_sub AND
														evaluation.id_std_ev=$1) THEN
				EXECUTE add_evaluation(rec.id_sub, $1, NULL);
			END IF;
		END LOOP;
		
		CLOSE subjects;
		year_ := year_ - 1;
		id_y_ := id_y_ - 5;
		END LOOP;
	END IF;
	
END;
$BODY$
  LANGUAGE plpgsql;
	
CREATE OR REPLACE FUNCTION num_order(id_std integer) RETURNS INTEGER AS $$
DECLARE
	numOrder integer;
	group_id integer;
	year_id integer;
	nums REFCURSOR;
	rec RECORD;
BEGIN
	SELECT student.id_gr_std, student.id_y_gr_std INTO group_id, year_id
	FROM student
	WHERE student.id_std = $1;

	OPEN nums FOR SELECT ROW_NUMBER() OVER (ORDER BY student.lastnames_std, student.names_std) AS indice, student.id_std
	FROM student, group_st
	WHERE student.id_gr_std = group_st.id_gr AND student.id_y_gr_std = group_st.id_y_gr AND
	student.id_gr_std = group_id AND student.id_y_gr_std = year_id AND student.type_std != 'D'
	ORDER BY student.lastnames_std, student.names_std;
	
	LOOP
	FETCH nums INTO rec;
	EXIT WHEN NOT FOUND;
	IF rec.id_std = $1 THEN
		numOrder := rec.indice;
		EXIT;
	END IF;
	
	END LOOP;
	
	RETURN numOrder;
END;
$$
LANGUAGE plpgsql;