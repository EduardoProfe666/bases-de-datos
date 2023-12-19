-- Trigger para prevenir borrar el ultimo admin
CREATE OR REPLACE FUNCTION prevent_bad_delete_user_app() RETURNS TRIGGER AS
$BODY$
DECLARE
	cont INTEGER;
	type_ VARCHAR;
BEGIN
	
	SELECT role_user.type_role INTO type_
	FROM role_user
	WHERE role_user.id_role = 	OLD.id_role_user;
	
	IF(type_ = 'Administrador') THEN
		SELECT COUNT(user_app.id_user) INTO cont
		FROM user_app, role_user
		WHERE user_app.id_role_user = role_user.id_role AND role_user.type_role = 'Administrador';

		IF(cont = 1) THEN
			RAISE EXCEPTION 'No se puede eliminar el último usuario administrador';
		END IF;
	END IF;
	
	RETURN OLD;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_bad_delete_user_app
BEFORE DELETE ON user_app
FOR EACH ROW EXECUTE PROCEDURE prevent_bad_delete_user_app();

-- Trigger para prevenir que se inserte un usuario estudiante con referencia null en estudiante
CREATE OR REPLACE FUNCTION prevent_bad_insert_student_user_app() RETURNS TRIGGER AS
$BODY$
DECLARE
	role_us CHARACTER VARYING:= (SELECT role_user.type_role FROM role_user WHERE role_user.id_role=NEW.id_role_user);
BEGIN
	IF(role_us='Estudiante' AND NEW.id_std_user IS NULL) THEN
		RAISE EXCEPTION 'No se puede insertar un usuario <Estudiante> sin la referencia del estudiante';
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_bad_insert_student_user_app
BEFORE INSERT ON user_app
FOR EACH ROW EXECUTE PROCEDURE prevent_bad_insert_student_user_app();

-- Trigger para prevenir que se inserte un anno academico con curso escolar y anno ya existente
CREATE OR REPLACE FUNCTION prevent_bad_insert_academic_year() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF  EXISTS(SELECT * FROM academic_year 
	WHERE academic_year.school_course_start_y=NEW.school_course_start_y AND 
				academic_year.school_course_end_y=NEW.school_course_end_y AND
				academic_year.year_y=NEW.year_y) THEN
		RAISE EXCEPTION 'Ya existe un año académico con el mismo curso escolar y el mismo año';
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_bad_insert_academic_year
BEFORE INSERT ON academic_year
FOR EACH ROW EXECUTE PROCEDURE prevent_bad_insert_academic_year();

-- Triggers para prevenir updates en las llaves primarias
CREATE OR REPLACE FUNCTION prevent_update_keys_academic_subject() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF OLD.id_sub != NEW.id_sub THEN
		RAISE EXCEPTION 'No se puede cambiar el id';
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_update_keys_academic_subject
BEFORE UPDATE ON academic_subject 
FOR EACH ROW EXECUTE PROCEDURE prevent_update_keys_academic_subject();


CREATE OR REPLACE FUNCTION prevent_update_keys_academic_year() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF OLD.id_y != NEW.id_y THEN
		RAISE EXCEPTION 'No se puede cambiar el id';
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_update_keys_academic_year
BEFORE UPDATE ON academic_year
FOR EACH ROW EXECUTE PROCEDURE prevent_update_keys_academic_year();


CREATE OR REPLACE FUNCTION prevent_update_keys_cause_of_dismissal() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF OLD.id_dm != NEW.id_dm THEN
		RAISE EXCEPTION 'No se puede cambiar el id';
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_update_keys_cause_of_dismissal
BEFORE UPDATE ON cause_of_dismissal
FOR EACH ROW EXECUTE PROCEDURE prevent_update_keys_cause_of_dismissal();


CREATE OR REPLACE FUNCTION prevent_update_keys_dismissal() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF OLD.id_std != NEW.id_std THEN
		RAISE EXCEPTION 'No se puede cambiar el id';
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_update_keys_dismissal
BEFORE UPDATE ON dismissal
FOR EACH ROW EXECUTE PROCEDURE prevent_update_keys_dismissal();


CREATE OR REPLACE FUNCTION prevent_update_keys_evaluation() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF OLD.id_sub_ev != NEW.id_sub_ev OR OLD.id_std_ev != NEW.id_std_ev THEN
		RAISE EXCEPTION 'No se puede cambiar el id';
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_update_keys_evaluation
BEFORE UPDATE ON evaluation
FOR EACH ROW EXECUTE PROCEDURE prevent_update_keys_evaluation();


CREATE OR REPLACE FUNCTION prevent_update_keys_promoted() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF OLD.id_std != NEW.id_std THEN
		RAISE EXCEPTION 'No se puede cambiar el id';
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_update_keys_promoted
BEFORE UPDATE ON promoted
FOR EACH ROW EXECUTE PROCEDURE prevent_update_keys_promoted();


CREATE OR REPLACE FUNCTION prevent_update_keys_repeater() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF OLD.id_std != NEW.id_std THEN
		RAISE EXCEPTION 'No se puede cambiar el id';
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_update_keys_repeater
BEFORE UPDATE ON repeater
FOR EACH ROW EXECUTE PROCEDURE prevent_update_keys_repeater();


CREATE OR REPLACE FUNCTION prevent_update_keys_role_user() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF OLD.id_role != NEW.id_role THEN
		RAISE EXCEPTION 'No se puede cambiar el id';
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_update_keys_role_user
BEFORE UPDATE ON role_user
FOR EACH ROW EXECUTE PROCEDURE prevent_update_keys_role_user();


CREATE OR REPLACE FUNCTION prevent_update_keys_student() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF OLD.id_std != NEW.id_std THEN
		RAISE EXCEPTION 'No se puede cambiar el id';
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_update_keys_student
BEFORE UPDATE ON student
FOR EACH ROW EXECUTE PROCEDURE prevent_update_keys_student();


CREATE OR REPLACE FUNCTION prevent_update_keys_type_of_evaluation() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF OLD.id_eval != NEW.id_eval THEN
		RAISE EXCEPTION 'No se puede cambiar el id';
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_update_keys_type_of_evaluation
BEFORE UPDATE ON type_of_evaluation
FOR EACH ROW EXECUTE PROCEDURE prevent_update_keys_type_of_evaluation();


CREATE OR REPLACE FUNCTION prevent_update_keys_user_app() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF OLD.id_user != NEW.id_user THEN
		RAISE EXCEPTION 'No se puede cambiar el id';
	END IF;
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_update_keys_user_app
BEFORE UPDATE ON user_app
FOR EACH ROW EXECUTE PROCEDURE prevent_update_keys_user_app();


-- Triggers que no se pueda eliminar si existe alguna tabla que lo referencie
CREATE OR REPLACE FUNCTION prevent_delete_reference_academic_year() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF EXISTS (SELECT * FROM group_st WHERE OLD.id_y=group_st.id_y_gr) THEN
		RAISE EXCEPTION 'No se puede eliminar pues existen referencias a la tupla';
	END IF;
	
	IF EXISTS (SELECT * FROM academic_subject WHERE OLD.id_y=academic_subject.id_y_sub) THEN
		RAISE EXCEPTION 'No se puede eliminar pues existen referencias a la tupla';
	END IF;
	
	RETURN OLD;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_delete_reference_academic_year
BEFORE DELETE ON academic_year
FOR EACH ROW EXECUTE PROCEDURE prevent_delete_reference_academic_year();


CREATE OR REPLACE FUNCTION prevent_delete_reference_group_st() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF EXISTS (SELECT * FROM student WHERE OLD.id_gr=student.id_gr_std AND 
	OLD.id_y_gr=student.id_y_gr_std) THEN
		RAISE EXCEPTION 'No se puede eliminar pues existen referencias a la tupla';
	END IF;
	
	RETURN OLD;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_delete_reference_group_st
BEFORE DELETE ON group_st
FOR EACH ROW EXECUTE PROCEDURE prevent_delete_reference_group_st();


CREATE OR REPLACE FUNCTION prevent_delete_reference_role_user() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF EXISTS (SELECT * FROM user_app WHERE OLD.id_role=user_app.id_role_user) THEN
		RAISE EXCEPTION 'No se puede eliminar pues existen referencias a la tupla';
	END IF;
	
	RETURN OLD;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_delete_reference_role_user
BEFORE DELETE ON role_user
FOR EACH ROW EXECUTE PROCEDURE prevent_delete_reference_role_user();


CREATE OR REPLACE FUNCTION prevent_delete_reference_cause_of_dismissal() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF EXISTS (SELECT * FROM dismissal WHERE OLD.id_dm=dismissal.id_dm_dm) THEN
		RAISE EXCEPTION 'No se puede eliminar pues existen referencias a la tupla';
	END IF;
	
	RETURN OLD;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_delete_reference_cause_of_dismissal
BEFORE DELETE ON cause_of_dismissal
FOR EACH ROW EXECUTE PROCEDURE prevent_delete_reference_cause_of_dismissal();


CREATE OR REPLACE FUNCTION prevent_delete_reference_type_of_evaluation() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF EXISTS (SELECT * FROM evaluation WHERE OLD.id_eval=evaluation.id_eval_ev) THEN
		RAISE EXCEPTION 'No se puede eliminar pues existen referencias a la tupla';
	END IF;
	
	RETURN OLD;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_prevent_delete_reference_type_of_evaluation
BEFORE DELETE ON type_of_evaluation
FOR EACH ROW EXECUTE PROCEDURE prevent_delete_reference_type_of_evaluation();


-- Triggers para inserciones automaticas
-- CREATE OR REPLACE FUNCTION "public"."automatic_insert_evaluation_student"()
--   RETURNS "pg_catalog"."trigger" AS $BODY$
-- DECLARE
-- 	rec RECORD;
-- 	year_ integer;
-- 	id_y_ integer;
-- 	subjects REFCURSOR;
-- BEGIN
-- 	
-- 	SELECT academic_year.year_y, academic_year.id_y
-- 	INTO year_, id_y_
-- 	FROM academic_year 
-- 	WHERE academic_year.id_y=NEW.id_y_gr_std;
-- 	
-- 		IF (NEW.type_std='D' AND NOT EXISTS ( SELECT * FROM evaluation 
-- 																				WHERE evaluation.id_std_ev=NEW.id_std
-- 	)) OR NEW.type_std!='D' THEN
-- 		WHILE (year_>0)
-- 		LOOP
-- 		
-- 		OPEN subjects FOR SELECT academic_subject.id_sub
-- 								FROM academic_subject
-- 								WHERE academic_subject.id_y_sub = id_y_;
-- 							
-- 		LOOP 
-- 			FETCH subjects INTO rec;
-- 			EXIT WHEN NOT FOUND;
-- 			
-- 			IF NOT EXISTS (SELECT * 
-- 											FROM evaluation 
-- 											WHERE evaluation.id_sub_ev=rec.id_sub AND
-- 														evaluation.id_std_ev=NEW.id_std) THEN
-- 				EXECUTE add_evaluation(rec.id_sub, NEW.id_std, NULL);
-- 			END IF;
-- 		END LOOP;
-- 		
-- 		CLOSE subjects;
-- 		year_ := year_ - 1;
-- 		id_y_ := id_y_ - 5;
-- 		END LOOP;
-- 	END IF;
-- 	
-- 	RETURN NEW;
-- END;
-- $BODY$
--   LANGUAGE plpgsql;
-- 
-- CREATE TRIGGER tg_automatic_insert_evaluation_student
-- AFTER INSERT OR UPDATE ON student
-- FOR EACH ROW EXECUTE PROCEDURE automatic_insert_evaluation_student();


CREATE OR REPLACE FUNCTION automatic_insert_user_app_student() RETURNS TRIGGER AS
$BODY$
DECLARE
	numb_extra integer;
	str varchar := REPLACE(LOWER(NEW.names_std), ' ', '') ||
									REPLACE(LOWER(NEW.lastnames_std), ' ', '') ||
									'@ceis.cujae.edu.cu';
BEGIN
	IF EXISTS(SELECT user_app.mail_user FROM user_app WHERE user_app.mail_user=str) THEN
		str := REPLACE(LOWER(NEW.names_std), ' ', '') ||
									REPLACE(LOWER(NEW.lastnames_std), ' ', '') ||
									nextval('mail_user_number') || '@ceis.cujae.edu.cu'; 
	END IF;
	
	EXECUTE add_user_app(1, str, 'E57c7Qf8kSo=', NEW.id_std);
	
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_automatic_insert_user_app_student
AFTER INSERT ON student
FOR EACH ROW EXECUTE PROCEDURE automatic_insert_user_app_student();


CREATE OR REPLACE FUNCTION automatic_insert_academic_subject_academic_year() RETURNS TRIGGER AS
$BODY$
DECLARE
	id_year INTEGER:= NEW.id_y;
BEGIN
	IF(NEW.year_y=1) THEN -- Primer Anno
		EXECUTE public.add_academic_subject('Introducción a la Informática', 48, id_year);
		EXECUTE public.add_academic_subject('Introducción a la Programación', 64, id_year);	
		EXECUTE public.add_academic_subject('Matemática Discreta', 48, id_year);
		EXECUTE public.add_academic_subject('Filosofía y Sociedad', 64, id_year);
		EXECUTE public.add_academic_subject('Matemática I', 96, id_year);
		EXECUTE public.add_academic_subject('Inglés 1', 56, id_year);
		EXECUTE public.add_academic_subject('Educación Física 1', 28, id_year);
		EXECUTE public.add_academic_subject('Historia de Cuba', 52, id_year);
		EXECUTE public.add_academic_subject('Diseño y Programación Orientada a Objetos', 80, id_year);
		EXECUTE public.add_academic_subject('Matemática Computacional', 48, id_year);
		EXECUTE public.add_academic_subject('Economía Política', 64, id_year);
		EXECUTE public.add_academic_subject('Matemática II', 96, id_year);
		EXECUTE public.add_academic_subject('Diseño de Interfaces y Pruebas', 32, id_year);
		EXECUTE public.add_academic_subject('Inglés 2', 48, id_year);
		EXECUTE public.add_academic_subject('Educación Física 2', 28, id_year);
			
	ELSIF(NEW.year_y=2) THEN -- Segundo Anno
		EXECUTE public.add_academic_subject('Estructura de Datos', 80, id_year);
		EXECUTE public.add_academic_subject('Arquitectura de Computadoras', 70, id_year);
		EXECUTE public.add_academic_subject('Razonamiento Aproximado', 70, id_year);
		EXECUTE public.add_academic_subject('Matemática III', 80, id_year);
		EXECUTE public.add_academic_subject('Problemas Sociales de la Ciencia y la Tecnología', 24, id_year);
		EXECUTE public.add_academic_subject('Inglés 3', 48, id_year);
		EXECUTE public.add_academic_subject('Educación Física 3', 28, id_year);
		EXECUTE public.add_academic_subject('Bases de Datos', 80, id_year);
		EXECUTE public.add_academic_subject('Sistemas Operativos', 56, id_year);
		EXECUTE public.add_academic_subject('Economía Empresarial', 48, id_year);
		EXECUTE public.add_academic_subject('Seguridad y Defensa Nacional', 68, id_year);
		EXECUTE public.add_academic_subject('Matemática Numérica', 48, id_year);
		EXECUTE public.add_academic_subject('Optativa I', 42, id_year);
		EXECUTE public.add_academic_subject('Inglés 4', 48, id_year);
		EXECUTE public.add_academic_subject('Educación Física 4', 28, id_year);
	
	ELSIF(NEW.year_y=3) THEN -- Tercer Anno
		EXECUTE public.add_academic_subject('Ingeniería de Requisitos', 70, id_year);
		EXECUTE public.add_academic_subject('Programación Web', 70, id_year);
		EXECUTE public.add_academic_subject('Redes de Computadora', 70, id_year);
		EXECUTE public.add_academic_subject('Inteligencia Artificial', 70, id_year);
		EXECUTE public.add_academic_subject('Metodología de la Investigación', 42, id_year);
		EXECUTE public.add_academic_subject('Sistemas Distribuidos', 28, id_year);
		EXECUTE public.add_academic_subject('Diseño de Software', 70, id_year);
		EXECUTE public.add_academic_subject('Seguridad Informática', 44, id_year);
		EXECUTE public.add_academic_subject('Investigación de Operaciones', 84, id_year);
		EXECUTE public.add_academic_subject('Bases de Datos Avanzadas', 42, id_year);
		EXECUTE public.add_academic_subject('Optativa II', 48, id_year);
		EXECUTE public.add_academic_subject('Optativa III', 56, id_year);
	
	ELSIF(NEW.year_y=4) THEN -- Cuarto Anno
		EXECUTE public.add_academic_subject('Seminario Profesional', 42, id_year);
		EXECUTE public.add_academic_subject('Optativa IV', 68, id_year);
		EXECUTE public.add_academic_subject('Optativa V', 68, id_year);
		EXECUTE public.add_academic_subject('Optativa VI', 68, id_year);
		EXECUTE public.add_academic_subject('Gestión de Proyectos', 42, id_year);
		EXECUTE public.add_academic_subject('Trabajo de Diploma', 600, id_year);
		EXECUTE public.add_academic_subject('Proyecto de Trabajo de Diploma', 68, id_year);
	
	ELSE
		RAISE EXCEPTION 'Año no válido';
	
	END IF;
	
	
	RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tg_automatic_insert_academic_subject_academic_year
AFTER INSERT ON academic_year
FOR EACH ROW EXECUTE PROCEDURE automatic_insert_academic_subject_academic_year();

-- Promocion
