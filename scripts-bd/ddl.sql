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

------------------------------------------------
