CREATE TABLE exame (rowid bigint auto_increment, nm_exame VARCHAR(255));
INSERT INTO exame (nm_exame) VALUES ('Acuidade Visual'), ('Urina'), ('Clinico'), ('Sangue');

CREATE TABLE funcionario (rowid bigint auto_increment, nm_func VARCHAR(255));
INSERT INTO funcionario (nm_func) VALUES ('Allan'), ('Santana'), ('Isaac'), ('Santana');

CREATE TABLE exames_realizados (rowid bigint auto_increment, id_exame bigint, id_func bigint, data date);
INSERT INTO exames_realizados (id_exame,id_func,data) VALUES (1,1,'2022-12-14');
INSERT INTO exames_realizados (id_exame,id_func,data) VALUES (1,1,'2022-12-14');
INSERT INTO exames_realizados (id_exame,id_func,data) VALUES (1,1,'2022-12-15');
INSERT INTO exames_realizados (id_exame,id_func,data) VALUES (4,4,'2022-12-15');
