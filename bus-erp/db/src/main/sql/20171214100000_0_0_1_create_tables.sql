-- // 0.0.1 Create Tables
CREATE SEQUENCE seq_app\
GRANT USAGE, SELECT ON seq_app TO transport\


CREATE TABLE tbl_pais_pais (
	pais_pk BIGINT NOT NULL
	, pais_codigo VARCHAR(2) NOT NULL
	, pais_nombre VARCHAR(80) NOT NULL

	, CONSTRAINT pk_pais PRIMARY KEY (pais_pk)

	, CONSTRAINT uk_pais UNIQUE (pais_codigo)
)\


CREATE TABLE tbl_region_rgon (
	rgon_pk BIGINT NOT NULL
	, rgon_pais_pk BIGINT NOT NULL
	, rgon_nombre VARCHAR(80) NOT NULL

	, CONSTRAINT pk_rgon PRIMARY KEY (rgon_pk)

	, CONSTRAINT uk_rgon UNIQUE (rgon_pais_pk, rgon_nombre)

	, CONSTRAINT fk_rgon_pais_pk FOREIGN KEY (rgon_pais_pk) REFERENCES tbl_pais_pais (pais_pk)
)\


CREATE TABLE tbl_cliente_clte (
	clte_pk BIGINT NOT NULL
	, clte_email VARCHAR(50) NOT NULL
	, clte_nombre VARCHAR(50) NOT NULL

	, CONSTRAINT pk_clte PRIMARY KEY (clte_pk)

	, CONSTRAINT uk_clte UNIQUE (clte_email)
)\

GRANT SELECT ON tbl_cliente_clte TO transport\


CREATE TABLE tbl_usuario_usro (
	usro_pk BIGINT NOT NULL
	, usro_clte_pk BIGINT
	, usro_rol CHAR(1) NOT NULL
	, usro_email VARCHAR(50) NOT NULL
	, usro_contrasenia VARCHAR(50) NOT NULL
	, usro_nombre VARCHAR(50) NOT NULL

	, CONSTRAINT pk_usro PRIMARY KEY (usro_pk)

	, CONSTRAINT uk_usro UNIQUE (usro_email)

	, CONSTRAINT fk_usro_clte_pk FOREIGN KEY (usro_clte_pk) REFERENCES tbl_cliente_clte (clte_pk)
)\

GRANT SELECT, INSERT, UPDATE, DELETE ON tbl_usuario_usro TO transport\

CREATE INDEX ix_usro_clte_pk ON tbl_usuario_usro(usro_clte_pk)\



CREATE TABLE tbl_placa_plca (
	plca_pk BIGINT NOT NULL
	, plca_clte_pk BIGINT NOT NULL
	, plca_codigo VARCHAR(30) NOT NULL
	, plca_fecha_fin TIMESTAMP NOT NULL
	, plca_ultimo_lgps_pk BIGINT

	, CONSTRAINT pk_plca PRIMARY KEY (plca_pk)

	, CONSTRAINT uk_plca UNIQUE (plca_codigo)

	, CONSTRAINT fk_plca_clte_pk FOREIGN KEY (plca_clte_pk) REFERENCES tbl_cliente_clte (clte_pk)
--	, CONSTRAINT fk_plca_ultimo_lgps_pk FOREIGN KEY (plca_ultimo_lgps_pk) REFERENCES tbl_lectura_gps_lgps (lgps_pk)
)\

GRANT SELECT, INSERT, UPDATE, DELETE ON tbl_placa_plca TO transport\


CREATE TABLE tbl_vehiculo_vhcl (
	vhcl_pk BIGINT NOT NULL
	, vhcl_clte_pk BIGINT NOT NULL
	, vhcl_matricula VARCHAR(30) NOT NULL
	, vhcl_plca_pk BIGINT

	, CONSTRAINT pk_vhcl PRIMARY KEY (vhcl_pk)

	, CONSTRAINT uk_vhcl UNIQUE (vhcl_clte_pk, vhcl_matricula)

	, CONSTRAINT fk_vhcl_clte_pk FOREIGN KEY (vhcl_clte_pk) REFERENCES tbl_cliente_clte (clte_pk)
	, CONSTRAINT fk_vhcl_plca_pk FOREIGN KEY (vhcl_plca_pk) REFERENCES tbl_placa_plca (plca_pk)
)\

CREATE INDEX ix_vhcl_clte_pk ON tbl_vehiculo_vhcl(vhcl_clte_pk)\
CREATE INDEX ix_vhcl_plca_pk ON tbl_vehiculo_vhcl(vhcl_plca_pk)\

GRANT SELECT, INSERT, UPDATE, DELETE ON tbl_vehiculo_vhcl TO transport\


CREATE TABLE tbl_lectura_gps_lgps (
	lgps_pk BIGINT NOT NULL
	, lgps_plca_pk BIGINT NOT NULL
	, lgps_vhcl_pk BIGINT NOT NULL
	, lgps_fecha TIMESTAMP NOT NULL
	, lgps_lat NUMERIC(9,6) NOT NULL
	, lgps_lon NUMERIC(9,6) NOT NULL
	, lgps_alt NUMERIC(12,6) NOT NULL
	, lgps_spd NUMERIC(9,5) NOT NULL
	, lgps_dst NUMERIC(12,4) NOT NULL
	, lgps_elt NUMERIC(12,4) NOT NULL

	, CONSTRAINT pk_lgps PRIMARY KEY (lgps_pk)

--	, CONSTRAINT fk_lgps_plca_pk FOREIGN KEY (lgps_plca_pk) REFERENCES tbl_placa_plca (plca_pk)
--	, CONSTRAINT fk_lgps_vhcl_pk FOREIGN KEY (lgps_vhcl_pk) REFERENCES tbl_vehiculo_vhcl (vhcl_pk)
)\

CREATE INDEX ix_lgps_vhcl_pk ON tbl_lectura_gps_lgps(lgps_vhcl_pk, lgps_fecha)\

GRANT SELECT, INSERT ON tbl_lectura_gps_lgps TO transport\





INSERT INTO tbl_cliente_clte (clte_pk, clte_email, clte_nombre) VALUES (2000, 'cliente@gmail.com', 'Cliente')\

INSERT INTO tbl_usuario_usro (usro_pk, usro_clte_pk, usro_rol, usro_email, usro_contrasenia, usro_nombre) VALUES (1000, NULL, 'M', 'xeredi@gmail.com', 'changeme', 'Xesus')\
INSERT INTO tbl_usuario_usro (usro_pk, usro_clte_pk, usro_rol, usro_email, usro_contrasenia, usro_nombre) VALUES (1001, 2000, 'C', 'cliente@gmail.com', 'changeme', 'Cliente')\

INSERT INTO tbl_placa_plca (plca_pk, plca_clte_pk, plca_codigo, plca_fecha_fin, plca_ultimo_lgps_pk) VALUES (3000, 2000, '00000000ecec8745', '2050-01-01 00:00:00', NULL)\
INSERT INTO tbl_vehiculo_vhcl (vhcl_pk, vhcl_clte_pk, vhcl_matricula, vhcl_plca_pk) VALUES (4000, 2000, 'PO-5378-Y', 3000)\
INSERT INTO tbl_placa_plca (plca_pk, plca_clte_pk, plca_codigo, plca_fecha_fin, plca_ultimo_lgps_pk) VALUES (3001, 2000, '00000000ecec8746', '2050-01-01 00:00:00', NULL)\
INSERT INTO tbl_vehiculo_vhcl (vhcl_pk, vhcl_clte_pk, vhcl_matricula, vhcl_plca_pk) VALUES (4001, 2000, 'PO-5378-Z', 3001)\
INSERT INTO tbl_placa_plca (plca_pk, plca_clte_pk, plca_codigo, plca_fecha_fin, plca_ultimo_lgps_pk) VALUES (3002, 2000, '00000000ecec8747', '2050-01-01 00:00:00', NULL)\
INSERT INTO tbl_vehiculo_vhcl (vhcl_pk, vhcl_clte_pk, vhcl_matricula, vhcl_plca_pk) VALUES (4002, 2000, 'PO-5378-A', 3002)\
INSERT INTO tbl_placa_plca (plca_pk, plca_clte_pk, plca_codigo, plca_fecha_fin, plca_ultimo_lgps_pk) VALUES (3003, 2000, '00000000ecec8748', '2050-01-01 00:00:00', NULL)\
INSERT INTO tbl_vehiculo_vhcl (vhcl_pk, vhcl_clte_pk, vhcl_matricula, vhcl_plca_pk) VALUES (4003, 2000, 'PO-5378-B', 3003)\
INSERT INTO tbl_placa_plca (plca_pk, plca_clte_pk, plca_codigo, plca_fecha_fin, plca_ultimo_lgps_pk) VALUES (3004, 2000, '00000000ecec8749', '2050-01-01 00:00:00', NULL)\
INSERT INTO tbl_vehiculo_vhcl (vhcl_pk, vhcl_clte_pk, vhcl_matricula, vhcl_plca_pk) VALUES (4004, 2000, 'PO-5378-C', 3004)\


-- //@UNDO


TRUNCATE TABLE tbl_lectura_gps_lgps\

DROP TABLE tbl_lectura_gps_lgps\
DROP TABLE tbl_vehiculo_vhcl\
DROP TABLE tbl_placa_plca\
DROP TABLE tbl_usuario_usro\
DROP TABLE tbl_cliente_clte\
DROP TABLE tbl_region_rgon\
DROP TABLE tbl_pais_pais\

DROP SEQUENCE seq_app\






