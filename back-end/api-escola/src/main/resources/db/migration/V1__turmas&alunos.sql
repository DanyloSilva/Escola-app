CREATE TABLE turmas (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        nome VARCHAR(100) NOT NULL,
                        horario_inicio TIME NOT NULL,
                        ativo BOOLEAN NOT NULL,
                        PRIMARY KEY (id)
);

INSERT INTO turmas (nome, horario_inicio, ativo)
VALUES ('SEM TURMA', '00:00:00', 1);

CREATE TABLE alunos (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        turmas_id BIGINT,
                        nome VARCHAR(255) NOT NULL,
                        matricula VARCHAR(20) NOT NULL,
                        data_nascimento DATE NOT NULL,
                        turma_id BIGINT NULL,
                        ativo BOOLEAN,
                        FOREIGN KEY (turma_id) REFERENCES turmas(id),
                        PRIMARY KEY (id)
);
INSERT INTO turmas (nome, horario_inicio, ativo)
VALUES ('JAVA', '08:00:00', 1),
       ('História', '10:00:00', 1),
       ('Ciências', '13:30:00', 1);

INSERT INTO alunos (turma_id, nome, matricula, data_nascimento, ativo)
VALUES (1, 'João Silva', '123456', '2005-03-15', 1),
       (2, 'Maria Souza', '789012', '2004-07-22', 1),
       (3, 'Pedro Santos', '345678', '2006-01-10', 1);




