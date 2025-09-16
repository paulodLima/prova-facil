select * from provas_db.professor;
select * from provas_db.professor_roles;
select * from provas_db.assunto;
select * from provas_db.serie;
select * from provas_db.pergunta;
select * from provas_db.disciplina;

INSERT INTO provas_db.serie (nome) VALUES
                                       ('4º Ano - Fundamental'),
                                       ('5º Ano - Fundamental'),
                                       ('6º Ano - Fundamental'),
                                       ('7º Ano - Fundamental'),
                                       ('8º Ano - Fundamental'),
                                       ('9º Ano - Fundamental'),
                                       ('1ª série - Ensino Médio'),
                                       ('2ª série - Ensino Médio'),
                                       ('3ª série - Ensino Médio');

create table provas_db.professor_disciplina (
                                                professor_id bigint not null references provas_db.professor(id),
                                                disciplina_id bigint not null references provas_db.disciplina(id),
                                                primary key (professor_id, disciplina_id)
);

create table provas_db.professor_serie (
                                           professor_id bigint not null references provas_db.professor(id),
                                           serie_id bigint not null references provas_db.serie(id),
                                           primary key (professor_id, serie_id)
);

alter table provas_db.pergunta
alter column imagem type bytea using imagem::bytea;

