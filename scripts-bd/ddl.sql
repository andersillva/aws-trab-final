create database aws-trab-final;
create user usr_aws_trab_final@localhost identified by 'usr_aws_trab_final';
grant all privileges on aws_trab_final.* to 'usr_aws_trab_final'@'localhost';

------------------------------------------------
use aws_trab_final;

------------------------------------------------
CREATE TABLE clube (
	id_clube		INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nm_clube		VARCHAR(60) NOT NULL,
	nm_cidade		VARCHAR(60) NOT NULL,
	sg_uf			VARCHAR(2) NOT NULL,
	dt_fundacao		DATE NOT NULL,
	nm_estadio		VARCHAR(30) NOT NULL
);

ALTER TABLE clube ADD CONSTRAINT clube_uk UNIQUE ( nm_clube, sg_uf );

------------------------------------------------
CREATE TABLE atleta (
	id_atleta				INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nm_atleta				VARCHAR(30) NOT NULL,
	nm_completo				VARCHAR(60) NOT NULL,
	dt_nascimento			DATE NOT NULL,
	nm_cidade_nascimento	VARCHAR(60) NOT NULL,
	sg_uf_nascimento		VARCHAR(2) NULL,
	nm_pais_nascimento		VARCHAR(30) NOT NULL,
	nr_altura				DECIMAL(3,2) NOT NULL,
	id_clube				INT NOT NULL
);

ALTER TABLE atleta
    ADD CONSTRAINT atleta_clube_fk FOREIGN KEY ( id_clube )
        REFERENCES clube ( id_clube );

------------------------------------------------
CREATE TABLE transferencia (
	id_transferencia	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id_clube_origem		INT NOT NULL,
	id_clube_destino	INT NOT NULL,
	id_atleta			INT NOT NULL,
	dt_transferencia	DATE NOT NULL,
	vl_transferencia	DECIMAL(12,2) NOT NULL,
	ds_moeda			VARCHAR(10) NOT NULL
);

ALTER TABLE transferencia
    ADD CONSTRAINT trans_clube_orig_fk FOREIGN KEY ( id_clube_origem )
        REFERENCES clube ( id_clube );

ALTER TABLE transferencia
    ADD CONSTRAINT trans_clube_dest_fk FOREIGN KEY ( id_clube_destino )
        REFERENCES clube ( id_clube );

ALTER TABLE transferencia
    ADD CONSTRAINT trans_atl_fk FOREIGN KEY ( id_atleta )
        REFERENCES atleta ( id_atleta );

------------------------------------------------
CREATE TABLE torneio (
	id_torneio	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nm_torneio	VARCHAR(30) NOT NULL,
	nr_ano		INT NOT NULL,
	dt_inicio	DATE NOT NULL,
	dt_fim		DATE NOT NULL
);

ALTER TABLE torneio ADD CONSTRAINT torneio_uk UNIQUE ( nm_torneio, nr_ano );

------------------------------------------------
CREATE TABLE participante (
	id_participante		INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id_torneio			INT NOT NULL,
	id_clube			INT NOT NULL,
	st_participante		VARCHAR(15) NOT NULL,
	nr_pontos			INT NULL
);

ALTER TABLE participante ADD CONSTRAINT participante_uk UNIQUE ( id_torneio, id_clube );

ALTER TABLE participante
    ADD CONSTRAINT participante_torneio_fk FOREIGN KEY ( id_torneio )
        REFERENCES torneio ( id_torneio );

ALTER TABLE participante
    ADD CONSTRAINT participante_clube_fk FOREIGN KEY ( id_clube )
        REFERENCES clube ( id_clube );

------------------------------------------------
CREATE TABLE partida (
	id_partida			INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id_torneio			INT NOT NULL,
	id_clube_mandante	INT NOT NULL,
	id_clube_visitante	INT NOT NULL,
	dt_partida			DATE NOT NULL,
	ds_local			VARCHAR(30) NOT NULL,
	st_partida			VARCHAR(15) NOT NULL,
	nr_placar_mandante	INT NULL,
	nr_placar_visitante	INT NULL
);

ALTER TABLE partida ADD CONSTRAINT torneio_clube_uk UNIQUE ( id_torneio, id_clube_mandante, id_clube_visitante, dt_partida );

ALTER TABLE partida
    ADD CONSTRAINT partida_torneio_fk FOREIGN KEY ( id_torneio )
        REFERENCES torneio ( id_torneio );

ALTER TABLE partida
    ADD CONSTRAINT partida_clube_mand_fk FOREIGN KEY ( id_clube_mandante )
        REFERENCES clube ( id_clube );

ALTER TABLE partida
    ADD CONSTRAINT partida_clube_vis_fk FOREIGN KEY ( id_clube_visitante )
        REFERENCES clube ( id_clube );

------------------------------------------------
CREATE TABLE evento (
	id_evento			INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id_partida			INT NOT NULL,
	tp_evento			VARCHAR(15) NOT NULL,
	ds_evento			VARCHAR(60) NOT NULL,
	dt_evento			DATETIME NOT NULL
);

ALTER TABLE evento
    ADD CONSTRAINT evento_partida_fk FOREIGN KEY ( id_partida )
        REFERENCES partida ( id_partida );