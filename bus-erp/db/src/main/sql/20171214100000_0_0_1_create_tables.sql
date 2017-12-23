-- // 0.0.1 Create Tables
CREATE SEQUENCE seq_app\
GRANT USAGE, SELECT ON seq_app TO transport\


CREATE TABLE tbl_pais_pais (
	pais_pk NUMERIC(20,0) NOT NULL
	, pais_codigo VARCHAR(2) NOT NULL
	, pais_nombre VARCHAR(80) NOT NULL

	, CONSTRAINT pk_pais PRIMARY KEY (pais_pk)

	, CONSTRAINT uk_pais UNIQUE (pais_codigo)
)\


CREATE TABLE tbl_region_rgon (
	rgon_pk NUMERIC(20,0) NOT NULL
	, rgon_pais_pk NUMERIC(20,0) NOT NULL
	, rgon_nombre VARCHAR(80) NOT NULL

	, CONSTRAINT pk_rgon PRIMARY KEY (rgon_pk)

	, CONSTRAINT uk_rgon UNIQUE (rgon_pais_pk, rgon_nombre)

	, CONSTRAINT fk_rgon_pais_pk FOREIGN KEY (rgon_pais_pk) REFERENCES tbl_pais_pais (pais_pk)
)\


CREATE TABLE tbl_usuario_usro (
	usro_pk NUMERIC(20,0) NOT NULL
	, usro_rol CHAR(1) NOT NULL
	, usro_email VARCHAR(50) NOT NULL
	, usro_contrasenia VARCHAR(50) NOT NULL
	, usro_nombre VARCHAR(50) NOT NULL

	, CONSTRAINT pk_usro PRIMARY KEY (usro_pk)

	, CONSTRAINT uk_usro UNIQUE (usro_email)
)\

GRANT SELECT, INSERT, UPDATE, DELETE ON tbl_usuario_usro TO transport\


CREATE TABLE tbl_cliente_clte (
	clte_pk NUMERIC(20,0) NOT NULL
	, clte_usro_pk NUMERIC(20,0) NOT NULL
	, clte_nombre VARCHAR(50) NOT NULL
	, clte_max_placas NUMERIC(5) NOT NULL

	, CONSTRAINT pk_clte PRIMARY KEY (clte_pk)

	, CONSTRAINT uk_clte UNIQUE (clte_usro_pk)

	, CONSTRAINT fk_clte_usro_pk FOREIGN KEY (clte_usro_pk) REFERENCES tbl_usuario_usro (usro_pk)
)\

GRANT SELECT ON tbl_cliente_clte TO transport\


CREATE TABLE tbl_placa_plca (
	plca_pk NUMERIC(20,0) NOT NULL
	, plca_clte_pk NUMERIC(20,0) NOT NULL
	, plca_codigo VARCHAR(30) NOT NULL
	, plca_fecha_fin TIMESTAMP NOT NULL
	, plca_ultimo_lgps_pk NUMERIC(20,0)

	, CONSTRAINT pk_plca PRIMARY KEY (plca_pk)

	, CONSTRAINT uk_plca UNIQUE (plca_codigo)

	, CONSTRAINT fk_plca_clte_pk FOREIGN KEY (plca_clte_pk) REFERENCES tbl_cliente_clte (clte_pk)
--	, CONSTRAINT fk_plca_ultimo_lgps_pk FOREIGN KEY (plca_ultimo_lgps_pk) REFERENCES tbl_lectura_gps_lgps (lgps_pk)
)\

GRANT SELECT, INSERT, UPDATE, DELETE ON tbl_placa_plca TO transport\


CREATE TABLE tbl_vehiculo_vhcl (
	vhcl_pk NUMERIC(20,0) NOT NULL
	, vhcl_clte_pk NUMERIC(20,0) NOT NULL
	, vhcl_matricula VARCHAR(30) NOT NULL
	, vhcl_plca_pk NUMERIC(20,0)

	, CONSTRAINT pk_vhcl PRIMARY KEY (vhcl_pk)

	, CONSTRAINT uk_vhcl UNIQUE (vhcl_clte_pk, vhcl_matricula)

	, CONSTRAINT fk_vhcl_clte_pk FOREIGN KEY (vhcl_clte_pk) REFERENCES tbl_cliente_clte (clte_pk)
	, CONSTRAINT fk_vhcl_plca_pk FOREIGN KEY (vhcl_plca_pk) REFERENCES tbl_placa_plca (plca_pk)
)\

CREATE INDEX ix_vhcl_clte_pk ON tbl_vehiculo_vhcl(vhcl_clte_pk)\
CREATE INDEX ix_vhcl_plca_pk ON tbl_vehiculo_vhcl(vhcl_plca_pk)\

GRANT SELECT, INSERT, UPDATE, DELETE ON tbl_vehiculo_vhcl TO transport\


CREATE TABLE tbl_lectura_gps_lgps (
	lgps_pk NUMERIC(20,0) NOT NULL
	, lgps_plca_pk NUMERIC(20,0) NOT NULL
	, lgps_vhcl_pk NUMERIC(20,0) NOT NULL
	, lgps_fecha TIMESTAMP NOT NULL
	, lgps_lat NUMERIC(12,8) NOT NULL
	, lgps_lon NUMERIC(12,8) NOT NULL
	, lgps_alt NUMERIC(12,8) NOT NULL
	, lgps_spd NUMERIC(12,8) NOT NULL
	, lgps_dst NUMERIC(12,4) NOT NULL
	, lgps_elt NUMERIC(12,4) NOT NULL

	, CONSTRAINT pk_lgps PRIMARY KEY (lgps_pk)

--	, CONSTRAINT fk_lgps_plca_pk FOREIGN KEY (lgps_plca_pk) REFERENCES tbl_placa_plca (plca_pk)
--	, CONSTRAINT fk_lgps_vhcl_pk FOREIGN KEY (lgps_vhcl_pk) REFERENCES tbl_vehiculo_vhcl (vhcl_pk)
)\

CREATE INDEX ix_lgps_vhcl_pk ON tbl_lectura_gps_lgps(lgps_vhcl_pk, lgps_fecha)\

GRANT SELECT, INSERT ON tbl_lectura_gps_lgps TO transport\







INSERT INTO tbl_usuario_usro (usro_pk, usro_rol, usro_email, usro_contrasenia, usro_nombre) VALUES (1000, 'M', 'xeredi@gmail.com', 'changeme', 'Xesus')\
INSERT INTO tbl_usuario_usro (usro_pk, usro_rol, usro_email, usro_contrasenia, usro_nombre) VALUES (1001, 'C', 'cliente@gmail.com', 'changeme', 'Cliente')\

INSERT INTO tbl_cliente_clte (clte_pk, clte_usro_pk, clte_nombre, clte_max_placas) VALUES (2000, 1001, 'Cliente', 10)\

INSERT INTO tbl_placa_plca (plca_pk, plca_clte_pk, plca_codigo, plca_fecha_fin, plca_ultimo_lgps_pk) VALUES (3000, 2000, '00000000ecec8745', '2050-01-01 00:00:00', NULL)\

INSERT INTO tbl_vehiculo_vhcl (vhcl_pk, vhcl_clte_pk, vhcl_matricula, vhcl_plca_pk) VALUES (4000, 2000, 'PO-5378-Y', 3000)\


-- //@UNDO

DROP TABLE tbl_lectura_gps_lgps\
DROP TABLE tbl_vehiculo_vhcl\
DROP TABLE tbl_placa_plca\
DROP TABLE tbl_cliente_clte\
DROP TABLE tbl_usuario_usro\
DROP TABLE tbl_region_rgon\
DROP TABLE tbl_pais_pais\

DROP SEQUENCE seq_app\






