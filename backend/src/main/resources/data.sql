INSERT INTO tb_user (user_name, password) VALUES ('Alex', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (user_name, password) VALUES ('Maria', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (name) VALUES ('ROLE_USER');
INSERT INTO tb_role (name) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_roles (user_id, role_id) VALUES (2, 2);

INSERT INTO tb_vocabulary (word, meaning) VALUES ('goers', 'asistentes');
INSERT INTO tb_vocabulary (word, meaning) VALUES ('putting up their tents', 'levantando sus carpas');
INSERT INTO tb_vocabulary (word, meaning) VALUES ('floods', 'inundaciones');
INSERT INTO tb_vocabulary (word, meaning) VALUES ('mud', 'barro');
INSERT INTO tb_vocabulary (word, meaning) VALUES ('gathering', 'reunión');
INSERT INTO tb_vocabulary (word, meaning) VALUES ('dairy farm', 'granja lechera');
INSERT INTO tb_vocabulary (word, meaning) VALUES ('play the fool', 'hacer el tonto');
INSERT INTO tb_vocabulary (word, meaning) VALUES ('raging', 'furioso');
INSERT INTO tb_vocabulary (word, meaning) VALUES ('overtly', 'abiertamente, publicamente');
INSERT INTO tb_vocabulary (word, meaning) VALUES ('catchy', 'pegadizo');
INSERT INTO tb_vocabulary (word, meaning) VALUES ('venue', ' lugar de eventos, encuentro o proceso');
INSERT INTO tb_vocabulary (word, meaning) VALUES ('die-hard fans', 'fanáticos acérrimos');
INSERT INTO tb_vocabulary (word, meaning) VALUES ('mankind', 'humanidad');
INSERT INTO tb_vocabulary (word, meaning) VALUES ('obliterate', 'anular, borrar o tachar algo');
INSERT INTO tb_vocabulary (word, meaning) VALUES ('wipe out', 'borrar, aniquilar');
INSERT INTO tb_vocabulary (word, meaning) VALUES ('endeavour', 'esfuerzo, empeño');
INSERT INTO tb_vocabulary (word, meaning) VALUES ('longings', 'anhelos, nostalgia');
INSERT INTO tb_vocabulary (word, meaning) VALUES ('grasp', 'agarrar, comprender, entender');


