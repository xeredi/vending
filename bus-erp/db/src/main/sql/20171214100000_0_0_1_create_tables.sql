-- // 0.0.1 Create Tables
CREATE SEQUENCE seq_app\
GRANT USAGE, SELECT ON seq_app TO transport\


CREATE TABLE tbl_asociado_ascd (
	ascd_pk BIGINT NOT NULL
	, ascd_email VARCHAR(50) NOT NULL
	, ascd_nombre VARCHAR(50) NOT NULL

	, CONSTRAINT pk_ascd PRIMARY KEY (ascd_pk)

	, CONSTRAINT uk_ascd UNIQUE (ascd_email)
)\

GRANT SELECT ON tbl_asociado_ascd TO transport\




CREATE TABLE tbl_cliente_clte (
	clte_pk BIGINT NOT NULL
	, clte_ascd_pk BIGINT NOT NULL
	, clte_email VARCHAR(50) NOT NULL
	, clte_nombre VARCHAR(50) NOT NULL

	, CONSTRAINT pk_clte PRIMARY KEY (clte_pk)

	, CONSTRAINT uk_clte UNIQUE (clte_ascd_pk, clte_email)

	, CONSTRAINT fk_clte_ascd_pk FOREIGN KEY (clte_ascd_pk) REFERENCES tbl_asociado_ascd (ascd_pk)
)\

GRANT SELECT, INSERT, UPDATE, DELETE ON tbl_cliente_clte TO transport\


CREATE TABLE tbl_usuario_usro (
	usro_pk BIGINT NOT NULL
	, usro_ascd_pk BIGINT
	, usro_rol CHAR(1) NOT NULL
	, usro_email VARCHAR(50) NOT NULL
	, usro_contrasenia VARCHAR(50) NOT NULL
	, usro_nombre VARCHAR(50) NOT NULL

	, CONSTRAINT pk_usro PRIMARY KEY (usro_pk)

	, CONSTRAINT uk_usro UNIQUE (usro_email)

	, CONSTRAINT fk_usro_ascd_pk FOREIGN KEY (usro_ascd_pk) REFERENCES tbl_asociado_ascd (ascd_pk)
)\

GRANT SELECT, INSERT, UPDATE, DELETE ON tbl_usuario_usro TO transport\

CREATE INDEX ix_usro_ascd_pk ON tbl_usuario_usro(usro_ascd_pk)\



CREATE TABLE tbl_placa_plca (
	plca_pk BIGINT NOT NULL
	, plca_ascd_pk BIGINT NOT NULL
	, plca_codigo VARCHAR(30) NOT NULL
	, plca_fecha_fin TIMESTAMP NOT NULL

	, CONSTRAINT pk_plca PRIMARY KEY (plca_pk)

	, CONSTRAINT uk_plca UNIQUE (plca_codigo)

	, CONSTRAINT fk_plca_ascd_pk FOREIGN KEY (plca_ascd_pk) REFERENCES tbl_asociado_ascd (ascd_pk)
)\

GRANT SELECT, INSERT, UPDATE, DELETE ON tbl_placa_plca TO transport\

CREATE INDEX ix_plca_ascd_pk ON tbl_placa_plca(plca_ascd_pk)\


CREATE TABLE tbl_vehiculo_vhcl (
	vhcl_pk BIGINT NOT NULL
	, vhcl_ascd_pk BIGINT NOT NULL
	, vhcl_matricula VARCHAR(30) NOT NULL
	, vhcl_plca_pk BIGINT

	, CONSTRAINT pk_vhcl PRIMARY KEY (vhcl_pk)

	, CONSTRAINT uk_vhcl UNIQUE (vhcl_ascd_pk, vhcl_matricula)
	, CONSTRAINT uk_vhcl_plca_pk UNIQUE (vhcl_plca_pk)

	, CONSTRAINT fk_vhcl_ascd_pk FOREIGN KEY (vhcl_ascd_pk) REFERENCES tbl_asociado_ascd (ascd_pk)
	, CONSTRAINT fk_vhcl_plca_pk FOREIGN KEY (vhcl_plca_pk) REFERENCES tbl_placa_plca (plca_pk)
)\

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

CREATE INDEX ix_lgps_vhcl_pk ON tbl_lectura_gps_lgps(lgps_vhcl_pk, lgps_fecha DESC)\

GRANT SELECT, INSERT ON tbl_lectura_gps_lgps TO transport\



CREATE TABLE tbl_lugar_lgar (
	lgar_pk BIGINT NOT NULL
	, lgar_ascd_pk BIGINT NOT NULL
	, lgar_nombre VARCHAR(50) NOT NULL
	, lgar_direcc VARCHAR(200) NOT NULL
	, lgar_lat NUMERIC(9,6) NOT NULL
	, lgar_lon NUMERIC(9,6) NOT NULL

	, CONSTRAINT pk_lgar PRIMARY KEY (lgar_pk)
	, CONSTRAINT uk_lgar UNIQUE (lgar_ascd_pk, lgar_nombre)

	, CONSTRAINT fk_lgar_ascd_pk FOREIGN KEY (lgar_ascd_pk) REFERENCES tbl_asociado_ascd (ascd_pk)
)\



CREATE TABLE tbl_ruta_ruta (
	ruta_pk BIGINT NOT NULL
	, ruta_ascd_pk BIGINT NOT NULL
	, ruta_nombre VARCHAR(50) NOT NULL
	, ruta_orig_lgar_pk BIGINT NOT NULL
	, ruta_dest_lgar_pk BIGINT NOT NULL

	, CONSTRAINT pk_ruta PRIMARY KEY (ruta_pk)
	, CONSTRAINT uk_ruta UNIQUE (ruta_ascd_pk, ruta_nombre)

	, CONSTRAINT fk_ruta_clte_pk FOREIGN KEY (ruta_ascd_pk) REFERENCES tbl_asociado_ascd (ascd_pk)
	, CONSTRAINT fk_ruta_orig_lgar_pk FOREIGN KEY (ruta_orig_lgar_pk) REFERENCES tbl_lugar_lgar (lgar_pk)
	, CONSTRAINT fk_ruta_dest_lgar_pk FOREIGN KEY (ruta_dest_lgar_pk) REFERENCES tbl_lugar_lgar (lgar_pk)
)\



CREATE TABLE tbl_servicio_srvc (
	srvc_pk BIGINT NOT NULL
	, srvc_ascd_pk BIGINT NOT NULL
	, srvc_vhcl_pk BIGINT NOT NULL
	, srvc_usro_pk BIGINT NOT NULL
	, srvc_estimado_fini TIMESTAMP NOT NULL
	, srvc_estimado_ffin TIMESTAMP NOT NULL
	, srvc_real_fini TIMESTAMP
	, srvc_real_ffin TIMESTAMP
	, srvc_inic_lgps_pk BIGINT
	, srvc_orig_lugar VARCHAR(50)
	, srvc_orig_direcc VARCHAR(200) NOT NULL
	, srvc_orig_lat NUMERIC(9,6) NOT NULL
	, srvc_orig_lon NUMERIC(9,6) NOT NULL
	, srvc_orig_lgps_pk BIGINT
	, srvc_dest_lugar VARCHAR(50)
	, srvc_dest_direcc VARCHAR(200) NOT NULL
	, srvc_dest_lat NUMERIC(9,6) NOT NULL
	, srvc_dest_lon NUMERIC(9,6) NOT NULL
	, srvc_dest_lgps_pk BIGINT
	, srvc_vacio_km NUMERIC(9,4)
	, srvc_lleno_km NUMERIC(9,4)
	, srvc_importe NUMERIC(9,2)
	, srvc_observaciones VARCHAR(500)

	, CONSTRAINT pk_srvc PRIMARY KEY (srvc_pk)

	, CONSTRAINT fk_srvc_ascd_pk FOREIGN KEY (srvc_ascd_pk) REFERENCES tbl_asociado_ascd (ascd_pk)
--	, CONSTRAINT fk_srvc_vhcl_pk FOREIGN KEY (srvc_vhcl_pk) REFERENCES tbl_vehiculo_vhcl (vhcl_pk)
--	, CONSTRAINT fk_srvc_usro_pk FOREIGN KEY (srvc_usro_pk) REFERENCES tbl_usuario_usro (usro_pk)
)\

CREATE INDEX ix_srvc_ascd_pk ON tbl_servicio_srvc(srvc_ascd_pk, srvc_estimado_fini DESC)\

GRANT SELECT, INSERT ON tbl_servicio_srvc TO transport\






-- Asociados
INSERT INTO tbl_asociado_ascd (ascd_pk, ascd_email, ascd_nombre)
SELECT nextval('seq_app'), CONCAT('plca_', serie.id, '@gmail.com'), CONCAT('Nombre ', serie.id)
FROM generate_series(1, 100)  AS serie(id)\

-- Usuarios Administradores
INSERT INTO tbl_usuario_usro (usro_pk, usro_ascd_pk, usro_rol, usro_email, usro_contrasenia, usro_nombre)
VALUES (1000, NULL, 'M', 'xeredi@gmail.com', 'changeme', 'Xesus')\
-- Usuarios Asociados
INSERT INTO tbl_usuario_usro (usro_pk, usro_ascd_pk, usro_rol, usro_email, usro_contrasenia, usro_nombre)
SELECT nextval('seq_app'), ascd_pk, 'A', ascd_email, 'changeMe', CONCAT('Nombre ', currval('seq_app'))
FROM tbl_asociado_ascd\
-- Usuarios Conductores
INSERT INTO tbl_usuario_usro (usro_pk, usro_ascd_pk, usro_rol, usro_email, usro_contrasenia, usro_nombre)
SELECT nextval('seq_app'), ascd_pk, 'D', CONCAT(currval('seq_app'), '@gmail.com'), 'changeMe', CONCAT('Nombre ', currval('seq_app'))
FROM tbl_asociado_ascd, generate_series(1, 5)  AS serie(id)\

-- Placas
INSERT INTO tbl_placa_plca (plca_pk, plca_ascd_pk, plca_codigo, plca_fecha_fin)
SELECT nextval('seq_app'), ascd_pk, CONCAT('plca_', currval('seq_app')), '2050-01-01 00:00:00'
FROM tbl_asociado_ascd, generate_series(1, 5)  AS serie(id)
\
UPDATE tbl_placa_plca SET plca_codigo = '00000000ecec8745'
WHERE plca_pk = currval('seq_app')
\

-- Vehiculos
INSERT INTO tbl_vehiculo_vhcl (vhcl_pk, vhcl_ascd_pk, vhcl_matricula, vhcl_plca_pk)
SELECT nextval('seq_app'), plca_ascd_pk, CONCAT('mat_', currval('seq_app')), plca_pk
FROM tbl_placa_plca
\

-- Clientes
INSERT INTO tbl_cliente_clte (clte_pk, clte_ascd_pk, clte_email, clte_nombre)
SELECT nextval('seq_app'), ascd_pk, CONCAT('clte_', serie.id, '@gmail.com'), CONCAT('Nombre ', serie.id)
FROM tbl_asociado_ascd, generate_series(1, 8)  AS serie(id)
\



-- //@UNDO

DROP TABLE tbl_servicio_srvc\
DROP TABLE tbl_ruta_ruta\
DROP TABLE tbl_lugar_lgar\

TRUNCATE TABLE tbl_lectura_gps_lgps CASCADE\

DROP TABLE tbl_lectura_gps_lgps\
DROP TABLE tbl_vehiculo_vhcl\
DROP TABLE tbl_placa_plca\
DROP TABLE tbl_usuario_usro\
DROP TABLE tbl_cliente_clte\
DROP TABLE tbl_asociado_ascd\

DROP SEQUENCE seq_app\






