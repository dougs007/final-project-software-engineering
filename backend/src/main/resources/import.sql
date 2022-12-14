-- inserts of unitys
INSERT INTO `unity` ( `name`, `city`, `state`, `postal_code`) values ('Ceilândia Norte', 'Brasília', 'Distrito Federal', '72440812');
INSERT INTO `unity` ( `name`, `city`, `state`, `postal_code`) values ('Ceilândia Sul', 'Brasília', 'Distrito Federal', '72440812');
INSERT INTO `unity` ( `name`, `city`, `state`, `postal_code`) values ('Taguatinga', 'Brasília', 'Distrito Federal', '72440812');
INSERT INTO `unity` ( `name`, `city`, `state`, `postal_code`) values ('Águas Claras', 'Brasília', 'Distrito Federal', '72440812');
INSERT INTO `unity` ( `name`, `city`, `state`, `postal_code`) values ('Vicente Pires', 'Brasília', 'Distrito Federal', '72440812');

-- inserts of plans
-- INSERT INTO `plans` ( name, celphone,  email, password, role_id) values ('douglas', '61 991972551', 'dsfontes@gmail.com', '$2a$10$o5vhWUGZJ5rFKCVPGtD6hObB057TXLvqYny6Kss2FJTPfMg7HZy1a', 1);

-- inserts of admin's
INSERT INTO `user` ( name, celphone,  email, password, role_id) values ('admin', '61 991972551', 'admin@admin.com', '$2a$10$o5vhWUGZJ5rFKCVPGtD6hObB057TXLvqYny6Kss2FJTPfMg7HZy1a', 1);
INSERT INTO `user` ( name, celphone,  email, password, role_id) values ('douglas', '61 991972551', 'dsfontes@gmail.com', '$2a$10$o5vhWUGZJ5rFKCVPGtD6hObB057TXLvqYny6Kss2FJTPfMg7HZy1a', 1);
INSERT INTO `user` ( name, celphone,  email, password, role_id) values ('admin ceilandia', '61 991972551', 'cei@admin.com', '$2a$10$o5vhWUGZJ5rFKCVPGtD6hObB057TXLvqYny6Kss2FJTPfMg7HZy1a', 1);

-- inserts of coach
INSERT INTO `user` ( name, celphone,  email, password, user_id, role_id, unity_id, code_cref, hiring_date) values ('Coach ceilandia', '61 991972551', 'p1@admin.com', '$2a$10$o5vhWUGZJ5rFKCVPGtD6hObB057TXLvqYny6Kss2FJTPfMg7HZy1a', 3, 2, 1, 123456, now());

-- inserts of student
INSERT INTO `user` ( name, celphone,  email, password, user_id, role_id, unity_id, code_matricula) values ('Student ceilandia', '61 991972551', 'a1@admin.com', '$2a$10$o5vhWUGZJ5rFKCVPGtD6hObB057TXLvqYny6Kss2FJTPfMg7HZy1a', 4, 3, 1, 55328);


-- Inserts of alerts
INSERT INTO `alert` (id, description, name, reading_confirmation, show_date, user_id) VALUES (1, 'Atenção, seu treino está vencendo, por favor procurar o professor para renovar.', 'Renovação de treino', false, now(), 5);
INSERT INTO `alert` (id, description, name, reading_confirmation, show_date, user_id) VALUES(2, 'Atenção, seu plano está vencendo, por favor procurar a recepção.', 'Renovação de plano', 0, '2022-12-06', 5);

