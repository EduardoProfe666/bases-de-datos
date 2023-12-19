-- Inicializacion de Datos

-- Causas de Baja
SELECT public.add_cause_of_dismissal('Enfermedad');
SELECT public.add_cause_of_dismissal('Traslado');
SELECT public.add_cause_of_dismissal('Culminación de estudios');
SELECT public.add_cause_of_dismissal('Desaprobar por segunda vez un año');

-- Tipos de evaluacion
SELECT public.add_type_of_evaluation('Excelente', 5);
SELECT public.add_type_of_evaluation('Bien', 4);
SELECT public.add_type_of_evaluation('Aprobado', 3);
SELECT public.add_type_of_evaluation('Desaprobado', 2);

-- Roles de Usuario
SELECT public.add_role_user('Estudiante');
SELECT public.add_role_user('Secretario Docente');
SELECT public.add_role_user('Administrador');

-- Años academicos
SELECT public.add_academic_year(1,2020,2021);
SELECT public.add_academic_year(2,2020,2021);
SELECT public.add_academic_year(3,2020,2021);
SELECT public.add_academic_year(4,2020,2021);

SELECT public.add_academic_year(1,2021,2022);
SELECT public.add_academic_year(2,2021,2022);
SELECT public.add_academic_year(3,2021,2022);
SELECT public.add_academic_year(4,2021,2022);

SELECT public.add_academic_year(1,2022,2023);
SELECT public.add_academic_year(2,2022,2023);
SELECT public.add_academic_year(3,2022,2023);
SELECT public.add_academic_year(4,2022,2023);

SELECT public.add_academic_year(1,2023,2023);
SELECT public.add_academic_year(2,2023,2023);
SELECT public.add_academic_year(3,2023,2023);
SELECT public.add_academic_year(4,2023,2023);

-- Grupos 
SELECT public.add_group_st(13, 11, 205);
SELECT public.add_group_st(13, 12, 204);
SELECT public.add_group_st(13, 13, 203);
SELECT public.add_group_st(13, 14, 202);
SELECT public.add_group_st(13, 15, 201);

SELECT public.add_group_st(14, 21, 305);
SELECT public.add_group_st(14, 22, 304);
SELECT public.add_group_st(14, 23, 303);
SELECT public.add_group_st(14, 24, 302);
SELECT public.add_group_st(14, 25, 301);

SELECT public.add_group_st(15, 31, 305);
SELECT public.add_group_st(15, 32, 304);
SELECT public.add_group_st(15, 33, 303);
SELECT public.add_group_st(15, 34, 302);
SELECT public.add_group_st(15, 35, 301);

SELECT public.add_group_st(16, 41, 305);
SELECT public.add_group_st(16, 42, 304);
SELECT public.add_group_st(16, 43, 303);
SELECT public.add_group_st(16, 44, 302);
SELECT public.add_group_st(16, 45, 301);


-- Estudiantes Promovidos
-- Primer anno
-- Primer grupo
SELECT public.add_student('Gerianna', 'F', 'Boyeros', 13, 'De Cristofalo', 'P', 1, NULL);
SELECT public.add_student('Sallyann', 'F', 'La Lisa', 13, 'McLennan', 'P', 1, NULL);
SELECT public.add_student('Turner', 'M', 'Mayarí', 13, 'Ortler', 'P', 1, NULL);
SELECT public.add_student('Valle', 'M', '10 de Octubre', 13, 'Senchenko', 'P', 1, NULL);
SELECT public.add_student('Ag', 'F', 'Bauta', 13, 'Riddick', 'P', 1, NULL);
SELECT public.add_student('Georgine', 'F', 'Centro Habana', 13, 'Huthart', 'P', 1, NULL);
SELECT public.add_student('Elbert', 'M', 'Plaza de la Revolución', 13, 'Darree', 'P', 1, NULL);
SELECT public.add_student('Alasteir', 'M', 'Playa', 13, 'Frediani', 'P', 1, NULL);
SELECT public.add_student('Gan', 'M', 'Centro Habana', 13, 'McLleese', 'P', 1, NULL);
SELECT public.add_student('Petrina', 'F', 'Isla de la Juventud', 13, 'Lewsie', 'P', 1, NULL);
SELECT public.add_student('Loella', 'F', 'Arroyo Naranjo', 13, 'Coper', 'P', 1, NULL);
SELECT public.add_student('Frances', 'F', 'Bauta', 13, 'Bortolotti', 'P', 1, NULL);
SELECT public.add_student('Fonz', 'M', 'Isla de la Juventud', 13, 'Bridgeland', 'P', 1, NULL);
SELECT public.add_student('Carlyn', 'F', 'Arroyo Naranjo', 13, 'Jeune', 'P', 1, NULL);
SELECT public.add_student('Simone', 'F', 'Boyeros', 13, 'Bockh', 'P', 1, NULL);
SELECT public.add_student('Lyn', 'F', 'Marianao', 13, 'Chillistone', 'P', 1, NULL);
SELECT public.add_student('Weber', 'M', 'Habana del Este', 13, 'Jeannot', 'P', 1, NULL);
SELECT public.add_student('Saidee', 'F', 'Arroyo Naranjo', 13, 'Nussey', 'P', 1, NULL);
SELECT public.add_student('Cornie', 'M', 'Boyeros', 13, 'Bradfield', 'P', 1, NULL);
SELECT public.add_student('Jule', 'M', 'Bauta', 13, 'Issacov', 'P', 1, NULL);
SELECT public.add_student('Burton', 'M', '10 de Octubre', 13, 'Hendrickson', 'P', 1, NULL);
SELECT public.add_student('Sarette', 'F', 'Centro Habana', 13, 'Fairbrother', 'P', 1, NULL);
SELECT public.add_student('Nicolis', 'M', 'Arroyo Naranjo', 13, 'Dubbin', 'P', 1, NULL);
SELECT public.add_student('Kayley', 'F', 'La Lisa', 13, 'Trowbridge', 'P', 1, NULL);
SELECT public.add_student('Karrie', 'F', 'Arroyo Naranjo', 13, 'Nern', 'P', 1, NULL);
SELECT public.add_student('Leroy', 'M', 'Plaza de la Revolución', 13, 'Folshom', 'P', 1, NULL);
SELECT public.add_student('Artur', 'M', 'Plaza de la Revolución', 13, 'Scarman', 'P', 1, NULL);
SELECT public.add_student('Gonzalo', 'M', 'Plaza de la Revolución', 13, 'Poynter', 'P', 1, NULL);
SELECT public.add_student('Karilynn', 'F', 'Centro Habana', 13, 'Metheringham', 'P', 1, NULL);
SELECT public.add_student('Tod', 'M', 'Playa', 13, 'Bilbey', 'P', 1, NULL);

-- Segundo grupo
SELECT public.add_student('Rossie', 'M', '10 de Octubre', 13, 'Wooler', 'P', 2, NULL);
SELECT public.add_student('Bobbette', 'F', 'Arroyo Naranjo', 13, 'Trelevan', 'P', 2, NULL);
SELECT public.add_student('Tania', 'F', 'Artemisa', 13, 'Edgars', 'P', 2, NULL);
SELECT public.add_student('Bondon', 'M', 'Artemisa', 13, 'Sigars', 'P', 2, NULL);
SELECT public.add_student('Helen-elizabeth', 'F', 'Habana del Este', 13, 'Foxton', 'P', 2, NULL);
SELECT public.add_student('Bat', 'M', 'Bauta', 13, 'Mieville', 'P', 2, NULL);
SELECT public.add_student('Bernardine', 'F', 'Playa', 13, 'Gumme', 'P', 2, NULL);
SELECT public.add_student('Gaye', 'F', 'Habana del Este', 13, 'Notley', 'P', 2, NULL);
SELECT public.add_student('Karlyn', 'F', 'Bauta', 13, 'O''Gorman', 'P', 2, NULL);
SELECT public.add_student('Chandler', 'M', 'Artemisa', 13, 'Bruton', 'P', 2, NULL);
SELECT public.add_student('Jamesy', 'M', 'Boyeros', 13, 'Elias', 'P', 2, NULL);
SELECT public.add_student('Tessy', 'F', 'Bauta', 13, 'Buterton', 'P', 2, NULL);
SELECT public.add_student('Rosalie', 'F', 'Isla de la Juventud', 13, 'Cudworth', 'P', 2, NULL);
SELECT public.add_student('Kristofor', 'M', 'Artemisa', 13, 'Neising', 'P', 2, NULL);
SELECT public.add_student('Forest', 'M', 'Boyeros', 13, 'Yakubovich', 'P', 2, NULL);
SELECT public.add_student('Jolyn', 'F', 'Boyeros', 13, 'Matura', 'P', 2, NULL);
SELECT public.add_student('Philip', 'M', 'Plaza de la Revolución', 13, 'Sprankling', 'P', 2, NULL);
SELECT public.add_student('Caron', 'F', 'Habana del Este', 13, 'Noel', 'P', 2, NULL);
SELECT public.add_student('Devin', 'F', 'Artemisa', 13, 'Lenton', 'P', 2, NULL);
SELECT public.add_student('Max', 'M', 'Bauta', 13, 'Sadd', 'P', 2, NULL);
SELECT public.add_student('Fitz', 'M', 'Boyeros', 13, 'Pellatt', 'P', 2, NULL);
SELECT public.add_student('Mohandas', 'M', 'Arroyo Naranjo', 13, 'Fay', 'P', 2, NULL);
SELECT public.add_student('Zola', 'F', 'Mayarí', 13, 'Brands', 'P', 2, NULL);
SELECT public.add_student('Ferdinande', 'F', 'Artemisa', 13, 'Stubbe', 'P', 2, NULL);
SELECT public.add_student('Esra', 'M', 'Plaza de la Revolución', 13, 'Fabri', 'P', 2, NULL);
SELECT public.add_student('Lowrance', 'M', 'Isla de la Juventud', 13, 'Ashfold', 'P', 2, NULL);
SELECT public.add_student('Prissie', 'F', 'Isla de la Juventud', 13, 'Drejer', 'P', 2, NULL);
SELECT public.add_student('Averil', 'M', 'Bauta', 13, 'Fold', 'P', 2, NULL);
SELECT public.add_student('Garrard', 'M', 'Boyeros', 13, 'Hackworth', 'P', 2, NULL);
SELECT public.add_student('Emelen', 'M', 'Playa', 13, 'Mathwin', 'P', 2, NULL);
SELECT public.add_student('Gabi', 'M', 'Boyeros', 13, 'Featherbie', 'P', 2, NULL);

-- Tercer Grupo
SELECT public.add_student('Celestyna', 'F', 'Plaza de la Revolución', 13, 'Kenway', 'P', 3, NULL);
SELECT public.add_student('Goober', 'M', 'Plaza de la Revolución', 13, 'Rosenbusch', 'P', 3, NULL);
SELECT public.add_student('Roshelle', 'F', 'Mayarí', 13, 'Godrich', 'P', 3, NULL);
SELECT public.add_student('Kaylyn', 'F', 'Bauta', 13, 'Corinton', 'P', 3, NULL);
SELECT public.add_student('Kelsy', 'F', 'La Lisa', 13, 'Ducroe', 'P', 3, NULL);
SELECT public.add_student('Cam', 'F', 'Artemisa', 13, 'Fosberry', 'P', 3, NULL);
SELECT public.add_student('Reggy', 'M', 'Isla de la Juventud', 13, 'Bineham', 'P', 3, NULL);
SELECT public.add_student('Milzie', 'F', 'Centro Habana', 13, 'Mechell', 'P', 3, NULL);
SELECT public.add_student('Jany', 'F', 'Playa', 13, 'Denny', 'P', 3, NULL);
SELECT public.add_student('Valina', 'F', 'Playa', 13, 'Terrelly', 'P', 3, NULL);
SELECT public.add_student('Dore', 'M', 'Plaza de la Revolución', 13, 'Grolmann', 'P', 3, NULL);
SELECT public.add_student('Art', 'M', 'Playa', 13, 'Sliney', 'P', 3, NULL);
SELECT public.add_student('Marcy', 'F', 'Marianao', 13, 'Spaughton', 'P', 3, NULL);
SELECT public.add_student('Andrea', 'M', 'Boyeros', 13, 'Zupone', 'P', 3, NULL);
SELECT public.add_student('Melli', 'F', 'Isla de la Juventud', 13, 'Charer', 'P', 3, NULL);
SELECT public.add_student('Piotr', 'M', 'Playa', 13, 'Chinnick', 'P', 3, NULL);
SELECT public.add_student('Marsh', 'M', 'Boyeros', 13, 'Mountstephen', 'P', 3, NULL);
SELECT public.add_student('Gavin', 'M', 'Boyeros', 13, 'Jachimiak', 'P', 3, NULL);
SELECT public.add_student('Luce', 'M', 'Arroyo Naranjo', 13, 'McAllaster', 'P', 3, NULL);
SELECT public.add_student('Wilmar', 'M', 'La Lisa', 13, 'Jakoviljevic', 'P', 3, NULL);
SELECT public.add_student('Fax', 'M', 'Marianao', 13, 'Darleston', 'P', 3, NULL);
SELECT public.add_student('Tiffi', 'F', 'La Lisa', 13, 'Collinette', 'P', 3, NULL);
SELECT public.add_student('Kareem', 'M', 'Boyeros', 13, 'Windrus', 'P', 3, NULL);
SELECT public.add_student('Thaine', 'M', 'Arroyo Naranjo', 13, 'Trotter', 'P', 3, NULL);
SELECT public.add_student('Jarid', 'M', 'Boyeros', 13, 'Bowe', 'P', 3, NULL);
SELECT public.add_student('Andrus', 'M', 'Boyeros', 13, 'Barbe', 'P', 3, NULL);
SELECT public.add_student('Toddie', 'M', 'Mayarí', 13, 'McGifford', 'P', 3, NULL);

-- Cuarto Grupo
SELECT public.add_student('Poppy', 'F', 'Plaza de la Revolución', 13, 'Collcott', 'P', 4, NULL);
SELECT public.add_student('Ernesto', 'M', 'Mayarí', 13, 'Munkley', 'P', 4, NULL);
SELECT public.add_student('Nettie', 'F', 'Marianao', 13, 'McGlaughn', 'P', 4, NULL);
SELECT public.add_student('Hans', 'M', 'Plaza de la Revolución', 13, 'Skures', 'P', 4, NULL);
SELECT public.add_student('Eduard', 'M', 'La Lisa', 13, 'Brenneke', 'P', 4, NULL);
SELECT public.add_student('Simmonds', 'M', 'Playa', 13, 'Murdy', 'P', 4, NULL);
SELECT public.add_student('Karilynn', 'F', 'Artemisa', 13, 'Offord', 'P', 4, NULL);
SELECT public.add_student('Giacinta', 'F', 'La Lisa', 13, 'Bluck', 'P', 4, NULL);
SELECT public.add_student('Cindy', 'F', 'Isla de la Juventud', 13, 'Froment', 'P', 4, NULL);
SELECT public.add_student('Kaine', 'M', 'Arroyo Naranjo', 13, 'Melton', 'P', 4, NULL);
SELECT public.add_student('Gerick', 'M', 'Arroyo Naranjo', 13, 'Corington', 'P', 4, NULL);
SELECT public.add_student('Kirby', 'M', 'La Lisa', 13, 'Worsom', 'P', 4, NULL);
SELECT public.add_student('Mannie', 'M', 'Plaza de la Revolución', 13, 'Spry', 'P', 4, NULL);
SELECT public.add_student('Dominick', 'M', 'Isla de la Juventud', 13, 'Coady', 'P', 4, NULL);
SELECT public.add_student('Doria', 'F', 'Centro Habana', 13, 'Ridsdell', 'P', 4, NULL);
SELECT public.add_student('Neel', 'M', 'Mayarí', 13, 'Tousy', 'P', 4, NULL);
SELECT public.add_student('Vale', 'F', '10 de Octubre', 13, 'Gullis', 'P', 4, NULL);
SELECT public.add_student('Saraann', 'F', 'Arroyo Naranjo', 13, 'Dalston', 'P', 4, NULL);
SELECT public.add_student('Claudian', 'M', 'Mayarí', 13, 'Bemment', 'P', 4, NULL);
SELECT public.add_student('Wolfy', 'M', 'Bauta', 13, 'Ilyuchyov', 'P', 4, NULL);
SELECT public.add_student('Mellisent', 'F', '10 de Octubre', 13, 'Grinval', 'P', 4, NULL);
SELECT public.add_student('Clem', 'M', 'Playa', 13, 'Pagden', 'P', 4, NULL);
SELECT public.add_student('Berk', 'M', 'Centro Habana', 13, 'Wand', 'P', 4, NULL);
SELECT public.add_student('Kimmi', 'F', 'Marianao', 13, 'Thewys', 'P', 4, NULL);
SELECT public.add_student('Hastings', 'M', 'Bauta', 13, 'O''Fallowne', 'P', 4, NULL);
SELECT public.add_student('Benoite', 'F', 'Marianao', 13, 'Reaveley', 'P', 4, NULL);
SELECT public.add_student('Alvie', 'M', 'Marianao', 13, 'Hacker', 'P', 4, NULL);
SELECT public.add_student('Corrina', 'F', 'Boyeros', 13, 'Bebbell', 'P', 4, NULL);
SELECT public.add_student('Krissy', 'F', 'La Lisa', 13, 'Gresly', 'P', 4, NULL);
SELECT public.add_student('Nelli', 'F', 'Mayarí', 13, 'Kyston', 'P', 4, NULL);
SELECT public.add_student('Melonie', 'F', 'Centro Habana', 13, 'Milleton', 'P', 4, NULL);

-- -- Cuarto Grupo
-- SELECT public.add_student('Poppy', 'F', 'Plaza de la Revolución', 13, 'Collcott', 'P', 4, NULL);
-- SELECT public.add_student('Ernesto', 'M', 'Mayarí', 13, 'Munkley', 'P', 4, NULL);
-- SELECT public.add_student('Nettie', 'F', 'Marianao', 13, 'McGlaughn', 'P', 4, NULL);
-- SELECT public.add_student('Hans', 'M', 'Plaza de la Revolución', 13, 'Skures', 'P', 4, NULL);
-- SELECT public.add_student('Eduard', 'M', 'La Lisa', 13, 'Brenneke', 'P', 4, NULL);
-- SELECT public.add_student('Simmonds', 'M', 'Playa', 13, 'Murdy', 'P', 4, NULL);
-- SELECT public.add_student('Karilynn', 'F', 'Artemisa', 13, 'Offord', 'P', 4, NULL);
-- SELECT public.add_student('Giacinta', 'F', 'La Lisa', 13, 'Bluck', 'P', 4, NULL);
-- SELECT public.add_student('Cindy', 'F', 'Isla de la Juventud', 13, 'Froment', 'P', 4, NULL);
-- SELECT public.add_student('Kaine', 'M', 'Arroyo Naranjo', 13, 'Melton', 'P', 4, NULL);
-- SELECT public.add_student('Gerick', 'M', 'Arroyo Naranjo', 13, 'Corington', 'P', 4, NULL);
-- SELECT public.add_student('Kirby', 'M', 'La Lisa', 13, 'Worsom', 'P', 4, NULL);
-- SELECT public.add_student('Mannie', 'M', 'Plaza de la Revolución', 13, 'Spry', 'P', 4, NULL);
-- SELECT public.add_student('Dominick', 'M', 'Isla de la Juventud', 13, 'Coady', 'P', 4, NULL);
-- SELECT public.add_student('Doria', 'F', 'Centro Habana', 13, 'Ridsdell', 'P', 4, NULL);
-- SELECT public.add_student('Neel', 'M', 'Mayarí', 13, 'Tousy', 'P', 4, NULL);
-- SELECT public.add_student('Vale', 'F', '10 de Octubre', 13, 'Gullis', 'P', 4, NULL);
-- SELECT public.add_student('Saraann', 'F', 'Arroyo Naranjo', 13, 'Dalston', 'P', 4, NULL);
-- SELECT public.add_student('Claudian', 'M', 'Mayarí', 13, 'Bemment', 'P', 4, NULL);
-- SELECT public.add_student('Wolfy', 'M', 'Bauta', 13, 'Ilyuchyov', 'P', 4, NULL);
-- SELECT public.add_student('Mellisent', 'F', '10 de Octubre', 13, 'Grinval', 'P', 4, NULL);
-- SELECT public.add_student('Clem', 'M', 'Playa', 13, 'Pagden', 'P', 4, NULL);
-- SELECT public.add_student('Berk', 'M', 'Centro Habana', 13, 'Wand', 'P', 4, NULL);
-- SELECT public.add_student('Kimmi', 'F', 'Marianao', 13, 'Thewys', 'P', 4, NULL);
-- SELECT public.add_student('Hastings', 'M', 'Bauta', 13, 'O''Fallowne', 'P', 4, NULL);
-- SELECT public.add_student('Benoite', 'F', 'Marianao', 13, 'Reaveley', 'P', 4, NULL);
-- SELECT public.add_student('Alvie', 'M', 'Marianao', 13, 'Hacker', 'P', 4, NULL);
-- SELECT public.add_student('Corrina', 'F', 'Boyeros', 13, 'Bebbell', 'P', 4, NULL);
-- SELECT public.add_student('Krissy', 'F', 'La Lisa', 13, 'Gresly', 'P', 4, NULL);
-- SELECT public.add_student('Nelli', 'F', 'Mayarí', 13, 'Kyston', 'P', 4, NULL);
-- SELECT public.add_student('Melonie', 'F', 'Centro Habana', 13, 'Milleton', 'P', 4, NULL);

-- Segundo anno
-- Primer Grupo
SELECT public.add_student('Susie', 'F', 'Plaza de la Revolución', 14, 'Edmett', 'P', 6, NULL);
SELECT public.add_student('Perice', 'M', 'Mayarí', 14, 'Rhodus', 'P', 6, NULL);
SELECT public.add_student('Fonz', 'M', 'Bauta', 14, 'Barttrum', 'P', 6, NULL);
SELECT public.add_student('Ebba', 'F', 'Bauta', 14, 'Lyven', 'P', 6, NULL);
SELECT public.add_student('Florina', 'F', 'Isla de la Juventud', 14, 'Pountney', 'P', 6, NULL);
SELECT public.add_student('Edd', 'M', 'Playa', 14, 'Crix', 'P', 6, NULL);
SELECT public.add_student('Vinson', 'M', 'Mayarí', 14, 'Slocket', 'P', 6, NULL);
SELECT public.add_student('Alejandro', 'M', 'Habana del Este', 14, 'Lister', 'P', 6, NULL);
SELECT public.add_student('Delcine', 'F', 'Arroyo Naranjo', 14, 'Petchell', 'P', 6, NULL);
SELECT public.add_student('Linnie', 'F', 'Boyeros', 14, 'Alps', 'P', 6, NULL);
SELECT public.add_student('Kelcy', 'F', 'Playa', 14, 'De Blase', 'P', 6, NULL);
SELECT public.add_student('Quent', 'M', 'Playa', 14, 'Pitrasso', 'P', 6, NULL);
SELECT public.add_student('Martelle', 'F', 'Playa', 14, 'Slad', 'P', 6, NULL);
SELECT public.add_student('Nadean', 'F', '10 de Octubre', 14, 'Wallbridge', 'P', 6, NULL);
SELECT public.add_student('Tani', 'F', 'Bauta', 14, 'Skinley', 'P', 6, NULL);
SELECT public.add_student('Tiebold', 'M', 'Artemisa', 14, 'Elgy', 'P', 6, NULL);
SELECT public.add_student('Farah', 'F', 'Centro Habana', 14, 'MacCaull', 'P', 6, NULL);
SELECT public.add_student('Arte', 'M', '10 de Octubre', 14, 'Fust', 'P', 6, NULL);
SELECT public.add_student('Sophie', 'F', 'Artemisa', 14, 'Greener', 'P', 6, NULL);
SELECT public.add_student('Zondra', 'F', 'La Lisa', 14, 'Edgerly', 'P', 6, NULL);
SELECT public.add_student('Guinevere', 'F', 'Habana del Este', 14, 'Goulder', 'P', 6, NULL);
SELECT public.add_student('Willow', 'F', 'Plaza de la Revolución', 14, 'MacPake', 'P', 6, NULL);
SELECT public.add_student('Devlin', 'M', 'Arroyo Naranjo', 14, 'O''Malley', 'P', 6, NULL);

-- Segundo grupo
SELECT public.add_student('Kath', 'F', 'Playa', 14, 'Samuel', 'P', 7, NULL);
SELECT public.add_student('Clay', 'M', 'Marianao', 14, 'O'' Bee', 'P', 7, NULL);
SELECT public.add_student('Margeaux', 'F', 'La Lisa', 14, 'Habbergham', 'P', 7, NULL);
SELECT public.add_student('Brigham', 'M', '10 de Octubre', 14, 'McGuffie', 'P', 7, NULL);
SELECT public.add_student('Gabby', 'M', 'Boyeros', 14, 'Diggin', 'P', 7, NULL);
SELECT public.add_student('Jourdain', 'M', 'La Lisa', 14, 'Plitz', 'P', 7, NULL);
SELECT public.add_student('Orin', 'M', 'Mayarí', 14, 'Burfoot', 'P', 7, NULL);
SELECT public.add_student('Brockie', 'M', 'Habana del Este', 14, 'Connachan', 'P', 7, NULL);
SELECT public.add_student('Barde', 'M', 'Isla de la Juventud', 14, 'Lomond', 'P', 7, NULL);
SELECT public.add_student('Karlyn', 'F', 'Habana del Este', 14, 'Barby', 'P', 7, NULL);
SELECT public.add_student('Roxi', 'F', 'La Lisa', 14, 'Harrismith', 'P', 7, NULL);
SELECT public.add_student('Mose', 'M', 'Centro Habana', 14, 'Felkin', 'P', 7, NULL);
SELECT public.add_student('Melli', 'F', 'Playa', 14, 'Buckner', 'P', 7, NULL);
SELECT public.add_student('Gordan', 'M', 'Marianao', 14, 'Piolli', 'P', 7, NULL);
SELECT public.add_student('Dorisa', 'F', 'Boyeros', 14, 'Huggill', 'P', 7, NULL);
SELECT public.add_student('Sauncho', 'M', 'Artemisa', 14, 'Knutsen', 'P', 7, NULL);
SELECT public.add_student('Alberto', 'M', 'Isla de la Juventud', 14, 'Wybrew', 'P', 7, NULL);
SELECT public.add_student('Roderick', 'M', 'Playa', 14, 'Thundercliffe', 'P', 7, NULL);
SELECT public.add_student('Sharity', 'F', 'Plaza de la Revolución', 14, 'Hollingby', 'P', 7, NULL);
SELECT public.add_student('Marice', 'F', 'Isla de la Juventud', 14, 'Marzelli', 'P', 7, NULL);
SELECT public.add_student('Bengt', 'M', 'Mayarí', 14, 'Martinelli', 'P', 7, NULL);
SELECT public.add_student('Mahmoud', 'M', 'Centro Habana', 14, 'Glasard', 'P', 7, NULL);
SELECT public.add_student('Katrina', 'F', 'Plaza de la Revolución', 14, 'Seilmann', 'P', 7, NULL);
SELECT public.add_student('Lannie', 'M', 'Playa', 14, 'Othick', 'P', 7, NULL);
SELECT public.add_student('Josephine', 'F', 'Centro Habana', 14, 'Frayn', 'P', 7, NULL);
SELECT public.add_student('Crystie', 'F', 'Centro Habana', 14, 'Ridd', 'P', 7, NULL);
SELECT public.add_student('Diann', 'F', '10 de Octubre', 14, 'Jedrysik', 'P', 7, NULL);
SELECT public.add_student('Karoline', 'F', 'La Lisa', 14, 'Skip', 'P', 7, NULL);

-- Tercer grupo
SELECT public.add_student('Liza', 'F', 'Isla de la Juventud', 14, 'Ibbett', 'P', 8, NULL);
SELECT public.add_student('Barri', 'M', 'La Lisa', 14, 'Tolworthie', 'P', 8, NULL);
SELECT public.add_student('Glennis', 'F', 'Isla de la Juventud', 14, 'Baile', 'P', 8, NULL);
SELECT public.add_student('Leola', 'F', '10 de Octubre', 14, 'Heynen', 'P', 8, NULL);
SELECT public.add_student('Zolly', 'M', 'Plaza de la Revolución', 14, 'Imorts', 'P', 8, NULL);
SELECT public.add_student('Mame', 'F', 'Mayarí', 14, 'Graddell', 'P', 8, NULL);
SELECT public.add_student('Merlina', 'F', 'Playa', 14, 'Killgus', 'P', 8, NULL);
SELECT public.add_student('Rasia', 'F', 'Centro Habana', 14, 'Breedy', 'P', 8, NULL);
SELECT public.add_student('Will', 'M', 'La Lisa', 14, 'Gillon', 'P', 8, NULL);
SELECT public.add_student('Gwyn', 'F', 'Isla de la Juventud', 14, 'Planque', 'P', 8, NULL);
SELECT public.add_student('Brooks', 'M', '10 de Octubre', 14, 'Pithcock', 'P', 8, NULL);
SELECT public.add_student('Elijah', 'M', 'La Lisa', 14, 'Tolomio', 'P', 8, NULL);
SELECT public.add_student('Douglas', 'M', '10 de Octubre', 14, 'O''Cullinane', 'P', 8, NULL);
SELECT public.add_student('Becka', 'F', 'Isla de la Juventud', 14, 'Guesford', 'P', 8, NULL);
SELECT public.add_student('Thurstan', 'M', 'La Lisa', 14, 'Garvagh', 'P', 8, NULL);
SELECT public.add_student('Cristobal', 'M', 'Artemisa', 14, 'Poyle', 'P', 8, NULL);
SELECT public.add_student('Adham', 'M', 'Habana del Este', 14, 'Cuerda', 'P', 8, NULL);
SELECT public.add_student('Marney', 'F', 'Artemisa', 14, 'Andreaccio', 'P', 8, NULL);
SELECT public.add_student('Ezechiel', 'M', 'Isla de la Juventud', 14, 'Deners', 'P', 8, NULL);
SELECT public.add_student('Lesley', 'M', 'Arroyo Naranjo', 14, 'Kimmince', 'P', 8, NULL);
SELECT public.add_student('Gabbey', 'F', 'Bauta', 14, 'Annetts', 'P', 8, NULL);
SELECT public.add_student('Emmaline', 'F', 'Playa', 14, 'Hadwin', 'P', 8, NULL);
SELECT public.add_student('Winfield', 'M', 'Habana del Este', 14, 'O''Brien', 'P', 8, NULL);
SELECT public.add_student('Kai', 'F', 'Mayarí', 14, 'Prozillo', 'P', 8, NULL);
SELECT public.add_student('Douglas', 'M', 'Habana del Este', 14, 'Link', 'P', 8, NULL);
SELECT public.add_student('Hersh', 'M', 'Arroyo Naranjo', 14, 'Bringloe', 'P', 8, NULL);
SELECT public.add_student('Muire', 'F', 'Arroyo Naranjo', 14, 'Aizic', 'P', 8, NULL);
SELECT public.add_student('Jordain', 'F', 'Plaza de la Revolución', 14, 'Blake', 'P', 8, NULL);
SELECT public.add_student('Tucky', 'M', 'Plaza de la Revolución', 14, 'Woloschin', 'P', 8, NULL);

-- Tercer anno
-- Primer Grupo
SELECT public.add_student('Robinson', 'M', 'Bauta', 15, 'Anderson', 'P', 11, NULL);
SELECT public.add_student('Amalie', 'F', 'Habana del Este', 15, 'Graveston', 'P', 11, NULL);
SELECT public.add_student('Mordy', 'M', 'Mayarí', 15, 'Baudino', 'P', 11, NULL);
SELECT public.add_student('Ema', 'F', 'Boyeros', 15, 'McCard', 'P', 11, NULL);
SELECT public.add_student('Cynthie', 'F', 'La Lisa', 15, 'Cranch', 'P', 11, NULL);
SELECT public.add_student('Lauritz', 'M', 'Arroyo Naranjo', 15, 'Neeve', 'P', 11, NULL);
SELECT public.add_student('Hal', 'M', 'Playa', 15, 'Suddaby', 'P', 11, NULL);
SELECT public.add_student('Marthena', 'F', '10 de Octubre', 15, 'Frood', 'P', 11, NULL);
SELECT public.add_student('Berne', 'M', 'Centro Habana', 15, 'Humphrys', 'P', 11, NULL);
SELECT public.add_student('Westleigh', 'M', 'Mayarí', 15, 'Ody', 'P', 11, NULL);
SELECT public.add_student('Britta', 'F', 'Bauta', 15, 'Cafferky', 'P', 11, NULL);
SELECT public.add_student('Sergeant', 'M', 'Artemisa', 15, 'Ralph', 'P', 11, NULL);
SELECT public.add_student('Arlinda', 'F', 'Playa', 15, 'Raine', 'P', 11, NULL);
SELECT public.add_student('Pippy', 'F', 'La Lisa', 15, 'Paolone', 'P', 11, NULL);
SELECT public.add_student('Thoma', 'M', 'Arroyo Naranjo', 15, 'Males', 'P', 11, NULL);
SELECT public.add_student('Shel', 'F', 'La Lisa', 15, 'Paget', 'P', 11, NULL);
SELECT public.add_student('Norina', 'F', 'Marianao', 15, 'Nerheny', 'P', 11, NULL);
SELECT public.add_student('Ediva', 'F', '10 de Octubre', 15, 'Iapico', 'P', 11, NULL);
SELECT public.add_student('Luciano', 'M', 'Habana del Este', 15, 'Divers', 'P', 11, NULL);
SELECT public.add_student('Leilah', 'F', '10 de Octubre', 15, 'Duquesnay', 'P', 11, NULL);
SELECT public.add_student('Shalom', 'M', 'Marianao', 15, 'Bonsul', 'P', 11, NULL);
SELECT public.add_student('Matilde', 'F', 'Habana del Este', 15, 'Whitchurch', 'P', 11, NULL);
SELECT public.add_student('Merrily', 'F', 'Plaza de la Revolución', 15, 'Ballingal', 'P', 11, NULL);
SELECT public.add_student('Kathryne', 'F', 'Mayarí', 15, 'Liebermann', 'P', 11, NULL);
SELECT public.add_student('Eleanor', 'F', 'Bauta', 15, 'McLukie', 'P', 11, NULL);
SELECT public.add_student('Hedy', 'F', 'Plaza de la Revolución', 15, 'Fanton', 'P', 11, NULL);
SELECT public.add_student('Michaeline', 'F', 'Habana del Este', 15, 'Zimmermanns', 'P', 11, NULL);
SELECT public.add_student('Stirling', 'M', 'Centro Habana', 15, 'Buckberry', 'P', 11, NULL);

-- Segundo Grupo
SELECT public.add_student('Vladimir', 'M', 'Artemisa', 15, 'Pringle', 'P', 12, NULL);
SELECT public.add_student('Janeta', 'F', 'Bauta', 15, 'Kubach', 'P', 12, NULL);
SELECT public.add_student('Klemens', 'M', '10 de Octubre', 15, 'O''Brian', 'P', 12, NULL);
SELECT public.add_student('Ashley', 'M', 'La Lisa', 15, 'Luetkemeyer', 'P', 12, NULL);
SELECT public.add_student('Caryl', 'F', 'Boyeros', 15, 'Norwich', 'P', 12, NULL);
SELECT public.add_student('Sibel', 'F', 'Marianao', 15, 'Burnie', 'P', 12, NULL);
SELECT public.add_student('Teri', 'F', 'La Lisa', 15, 'Loxdale', 'P', 12, NULL);
SELECT public.add_student('Diane', 'F', 'Artemisa', 15, 'Wooller', 'P', 12, NULL);
SELECT public.add_student('Darrel', 'M', 'Playa', 15, 'Hackley', 'P', 12, NULL);
SELECT public.add_student('Ginger', 'F', 'La Lisa', 15, 'Elder', 'P', 12, NULL);
SELECT public.add_student('Domenico', 'M', '10 de Octubre', 15, 'Smewin', 'P', 12, NULL);
SELECT public.add_student('Justinian', 'M', 'Boyeros', 15, 'Amyes', 'P', 12, NULL);
SELECT public.add_student('Conway', 'M', 'Habana del Este', 15, 'Tallowin', 'P', 12, NULL);
SELECT public.add_student('Patrizia', 'F', 'Centro Habana', 15, 'Linnemann', 'P', 12, NULL);
SELECT public.add_student('Glennis', 'F', 'Plaza de la Revolución', 15, 'Ledbury', 'P', 12, NULL);
SELECT public.add_student('Broderic', 'M', 'Bauta', 15, 'Crayton', 'P', 12, NULL);
SELECT public.add_student('Robbert', 'M', 'Isla de la Juventud', 15, 'Loins', 'P', 12, NULL);
SELECT public.add_student('Audrie', 'F', 'La Lisa', 15, 'Niesel', 'P', 12, NULL);
SELECT public.add_student('Kali', 'F', 'La Lisa', 15, 'Beidebeke', 'P', 12, NULL);
SELECT public.add_student('Leilah', 'F', 'Bauta', 15, 'Forst', 'P', 12, NULL);
SELECT public.add_student('Jill', 'F', 'Boyeros', 15, 'Overstreet', 'P', 12, NULL);
SELECT public.add_student('Jemima', 'F', 'Mayarí', 15, 'Mathivon', 'P', 12, NULL);
SELECT public.add_student('Ellsworth', 'M', 'Bauta', 15, 'O''Hartnett', 'P', 12, NULL);
SELECT public.add_student('Rhodie', 'F', 'Marianao', 15, 'Swanborough', 'P', 12, NULL);
SELECT public.add_student('Herbie', 'M', 'La Lisa', 15, 'Dorkens', 'P', 12, NULL);
SELECT public.add_student('Arlen', 'M', 'Boyeros', 15, 'Armiger', 'P', 12, NULL);
SELECT public.add_student('Carny', 'M', 'Artemisa', 15, 'Guitton', 'P', 12, NULL);
SELECT public.add_student('Dion', 'F', 'La Lisa', 15, 'Kelberer', 'P', 12, NULL);
SELECT public.add_student('Fina', 'F', 'Marianao', 15, 'Canadine', 'P', 12, NULL);

-- Cuarto anno
-- Primer grupo
SELECT public.add_student('Nert', 'F', 'Artemisa', 16, 'Marchello', 'P', 16, NULL);
SELECT public.add_student('Yank', 'M', 'Centro Habana', 16, 'Lavalle', 'P', 16, NULL);
SELECT public.add_student('Dud', 'M', 'Boyeros', 16, 'Crane', 'P', 16, NULL);
SELECT public.add_student('Aguste', 'M', 'Isla de la Juventud', 16, 'Dennehy', 'P', 16, NULL);
SELECT public.add_student('Kamillah', 'F', 'Plaza de la Revolución', 16, 'Tolputt', 'P', 16, NULL);
SELECT public.add_student('Spence', 'M', 'Artemisa', 16, 'Sporner', 'P', 16, NULL);
SELECT public.add_student('Rollo', 'M', '10 de Octubre', 16, 'Riddler', 'P', 16, NULL);
SELECT public.add_student('Broddy', 'M', 'Arroyo Naranjo', 16, 'Baskeyfied', 'P', 16, NULL);
SELECT public.add_student('Karlens', 'M', 'Boyeros', 16, 'Goggey', 'P', 16, NULL);
SELECT public.add_student('Joelynn', 'F', 'Boyeros', 16, 'Ravelus', 'P', 16, NULL);
SELECT public.add_student('Madelle', 'F', 'Habana del Este', 16, 'Voller', 'P', 16, NULL);
SELECT public.add_student('Nyssa', 'F', 'Centro Habana', 16, 'Cordova', 'P', 16, NULL);
SELECT public.add_student('Charlton', 'M', 'Playa', 16, 'Stook', 'P', 16, NULL);
SELECT public.add_student('Shelia', 'F', 'Arroyo Naranjo', 16, 'Pablo', 'P', 16, NULL);
SELECT public.add_student('Patti', 'F', 'Habana del Este', 16, 'Assandri', 'P', 16, NULL);
SELECT public.add_student('Ermin', 'M', 'Boyeros', 16, 'Lauritzen', 'P', 16, NULL);
SELECT public.add_student('Ashley', 'M', 'Isla de la Juventud', 16, 'Benasik', 'P', 16, NULL);
SELECT public.add_student('Garth', 'M', 'Arroyo Naranjo', 16, 'Eldrett', 'P', 16, NULL);
SELECT public.add_student('Gaylor', 'M', 'Playa', 16, 'Andreia', 'P', 16, NULL);
SELECT public.add_student('Micky', 'M', 'Mayarí', 16, 'Duns', 'P', 16, NULL);
SELECT public.add_student('Wade', 'M', 'Habana del Este', 16, 'Hover', 'P', 16, NULL);
SELECT public.add_student('Aloise', 'F', 'Boyeros', 16, 'Snashall', 'P', 16, NULL);
SELECT public.add_student('Daphna', 'F', 'Playa', 16, 'Regina', 'P', 16, NULL);
SELECT public.add_student('Davine', 'F', 'Boyeros', 16, 'Lackner', 'P', 16, NULL);
SELECT public.add_student('Kynthia', 'F', '10 de Octubre', 16, 'Filipov', 'P', 16, NULL);
SELECT public.add_student('Justine', 'F', 'Boyeros', 16, 'McInteer', 'P', 16, NULL);
SELECT public.add_student('Allin', 'M', '10 de Octubre', 16, 'Rayment', 'P', 16, NULL);
SELECT public.add_student('Jemmy', 'F', 'Marianao', 16, 'Emblow', 'P', 16, NULL);
SELECT public.add_student('Vernon', 'M', 'Habana del Este', 16, 'Crunden', 'P', 16, NULL);
SELECT public.add_student('Shurlocke', 'M', 'Boyeros', 16, 'Daens', 'P', 16, NULL);
SELECT public.add_student('Todd', 'M', 'Boyeros', 16, 'Lory', 'P', 16, NULL);
SELECT public.add_student('Rodi', 'F', 'Playa', 16, 'Pescott', 'P', 16, NULL);


-- Estudiantes Repitentes
-- Repitentes
SELECT public.add_student('Andras', 'M', 'Artemisa', 14, 'Quantick', 'R', 6, NULL);
SELECT public.add_student('Ennis', 'M', 'Mayarí', 13, 'Marczyk', 'R', 1, NULL);
SELECT public.add_student('Kaela', 'F', 'Bauta', 14, 'McKerrow', 'R', 7, NULL);
SELECT public.add_student('Lorrayne', 'F', 'Bauta', 15, 'Rieme', 'R', 11, NULL);
SELECT public.add_student('Arlie', 'F', 'Centro Habana', 16, 'McElvogue', 'R', 16, NULL);
SELECT public.add_student('Mark', 'M', 'Plaza de la Revolución', 15, 'Swadling', 'R', 11, NULL);
SELECT public.add_student('Kean', 'M', 'La Lisa', 15, 'Swetman', 'R', 12, NULL);
SELECT public.add_student('Gwenore', 'F', 'Boyeros', 15, 'Rudland', 'R', 11, NULL);
SELECT public.add_student('Candice', 'F', '10 de Octubre', 14, 'Leach', 'R', 7, NULL);
SELECT public.add_student('Sarette', 'F', 'Bauta', 14, 'Redrup', 'R', 7, NULL);
SELECT public.add_student('Karim', 'M', 'La Lisa', 14, 'Turl', 'R', 7, NULL);
SELECT public.add_student('Carling', 'M', 'Marianao', 14, 'Foss', 'R', 6, NULL);
SELECT public.add_student('Ambrosius', 'M', 'Isla de la Juventud', 13, 'MacKilroe', 'R', 1, NULL);
SELECT public.add_student('Verine', 'F', 'La Lisa', 13, 'Hanley', 'R', 2, NULL);
SELECT public.add_student('Angele', 'F', '10 de Octubre', 13, 'Armand', 'R', 3, NULL);
SELECT public.add_student('Gregoire', 'M', 'Marianao', 14, 'Ivanchin', 'R', 6, NULL);
SELECT public.add_student('Hube', 'M', 'Playa', 14, 'Mainwaring', 'R', 6, NULL);
SELECT public.add_student('Walden', 'M', 'Arroyo Naranjo', 14, 'Wehner', 'R', 7, NULL);
SELECT public.add_student('Cordi', 'F', 'Habana del Este', 14, 'Thomassen', 'R', 7, NULL);
SELECT public.add_student('Bunny', 'F', 'Plaza de la Revolución', 15, 'McIndoe', 'R', 12, NULL);
SELECT public.add_student('Holden', 'M', 'Marianao', 13, 'Kirtland', 'R', 2, NULL);
SELECT public.add_student('Morie', 'M', 'Mayarí', 13, 'Deners', 'R', 2, NULL);
SELECT public.add_student('Wiley', 'M', 'Plaza de la Revolución', 16, 'Ellard', 'R', 16, NULL);
SELECT public.add_student('Dodi', 'F', 'Mayarí', 15, 'Duran', 'R', 12, NULL);
SELECT public.add_student('Shay', 'M', 'Playa', 14, 'Samper', 'R', 6, NULL);
SELECT public.add_student('Stanfield', 'M', 'Marianao', 14, 'Ponde', 'R', 7, NULL);


-- Estudiantes Bajas
-- Bajas
SELECT public.add_student('Lowell', 'M', 'Habana del Este', 13, 'Candie', 'D', 1, 1);
SELECT public.add_student('Kaycee', 'F', 'Playa', 13, 'Skews', 'D', 1, 1);
SELECT public.add_student('Cyndi', 'F', 'La Lisa', 13, 'Shannahan', 'D', 2, 2);
SELECT public.add_student('Maryrose', 'F', 'Mayarí', 13, 'Beasley', 'D', 2, 2);
SELECT public.add_student('Curr', 'M', 'Plaza de la Revolución', 14, 'Bartczak', 'D', 6, 3);
SELECT public.add_student('Niels', 'M', 'Arroyo Naranjo', 14, 'Dudenie', 'D', 6, 3);
SELECT public.add_student('Lelia', 'F', '10 de Octubre', 14, 'Sagrott', 'D', 7, 1);
SELECT public.add_student('Raffaello', 'M', 'La Lisa', 15, 'Puttnam', 'D', 11, 1);
SELECT public.add_student('Linoel', 'M', 'Plaza de la Revolución', 15, 'Edwick', 'D', 11, 2);
SELECT public.add_student('Karry', 'F', '10 de Octubre', 16, 'Le Leu', 'D', 16, 2);
SELECT public.add_student('Odelinda', 'F', 'La Lisa', 16, 'Curteis', 'D', 16, 3);
SELECT public.add_student('Dillie', 'M', 'Centro Habana', 16, 'Feldbau', 'D', 16, 3);
SELECT public.add_student('Catlee', 'F', 'Bauta', 15, 'Headingham', 'D', 12, 2);
SELECT public.add_student('Cathi', 'F', 'Playa', 15, 'Tuplin', 'D', 12, 2);
SELECT public.add_student('Dwayne', 'M', 'Mayarí', 15, 'Jodrellec', 'D', 11, 2);
SELECT public.add_student('Tanhya', 'F', 'Isla de la Juventud', 14, 'Jerome', 'D', 7, 1);
SELECT public.add_student('Sampson', 'M', 'Mayarí', 14, 'Camocke', 'D', 8, 1);
SELECT public.add_student('Amaleta', 'F', '10 de Octubre', 14, 'Martinets', 'D', 6, 3);


-- Cuentas de Usuario
SELECT public.add_user_app(3,'admin','E57c7Qf8kSo=',NULL);
SELECT public.add_user_app(2,'secretario','E57c7Qf8kSo=',NULL);
SELECT public.add_user_app(1,'estudiante','E57c7Qf8kSo=',1);


-- Rellenar notas
Select public.automatic_evaluation_assigment();

