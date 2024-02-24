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




