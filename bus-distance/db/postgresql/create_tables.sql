DROP TABLE IF EXISTS tbl_servicio_srvc;
DROP TABLE IF EXISTS tbl_lectura_gps_lgps;
DROP TABLE IF EXISTS tbl_archivo_gps_agps;
DROP TABLE IF EXISTS tbl_ruta_ruta;
DROP TABLE IF EXISTS tbl_conductor_cdtr;
DROP TABLE IF EXISTS tbl_placa_plca;
DROP TABLE IF EXISTS tbl_vehiculo_vhcl;

CREATE SEQUENCE IF NOT EXISTS seq_bus;

CREATE TABLE tbl_vehiculo_vhcl (
	vhcl_pk BIGINT NOT NULL
	, vhcl_codigo VARCHAR(20) NOT NULL

	, CONSTRAINT pk_vhcl PRIMARY KEY (vhcl_pk)
	, CONSTRAINT uk_vhcl_codigo UNIQUE (vhcl_codigo)
);

INSERT INTO tbl_vehiculo_vhcl (vhcl_pk, vhcl_codigo) VALUES (2000, '0125');

CREATE TABLE tbl_placa_plca (
	plca_pk BIGINT NOT NULL
	, plca_vhcl_pk BIGINT
	, plca_codigo VARCHAR(20) NOT NULL

	, CONSTRAINT pk_plca PRIMARY KEY (plca_pk)
	, CONSTRAINT uk_plca_codigo UNIQUE (plca_codigo)
	, CONSTRAINT fk_plca_vhcl_pk FOREIGN KEY (plca_vhcl_pk) REFERENCES tbl_vehiculo_vhcl (vhcl_pk)
);

INSERT INTO tbl_placa_plca (plca_pk, plca_vhcl_pk, plca_codigo) VALUES (1000, 2000, '28022');

CREATE TABLE tbl_conductor_cdtr (
	cdtr_pk BIGINT NOT NULL
	, cdtr_codigo VARCHAR(20) NOT NULL
	, cdtr_nombre VARCHAR(50) NOT NULL

	, CONSTRAINT pk_cdtr PRIMARY KEY (cdtr_pk)
	, CONSTRAINT uk_cdtr_codigo UNIQUE (cdtr_codigo)
);

CREATE TABLE tbl_ruta_ruta (
	ruta_pk BIGINT NOT NULL
	, ruta_codigo VARCHAR(20) NOT NULL
	, ruta_nombre VARCHAR(100) NOT NULL
	, ruta_orig_lat DECIMAL(12, 8)
	, ruta_orig_lon DECIMAL(12, 8)
	, ruta_dest_lat DECIMAL(12, 8)
	, ruta_dest_lon DECIMAL(12, 8)

	, CONSTRAINT pk_ruta PRIMARY KEY (ruta_pk)
	, CONSTRAINT uk_ruta_codigo UNIQUE (ruta_codigo)
);

INSERT INTO tbl_ruta_ruta (ruta_pk, ruta_codigo, ruta_nombre, ruta_orig_lat, ruta_orig_lon, ruta_dest_lat, ruta_dest_lon)
VALUES (4001, '00105', 'Colegio Angel de la Guarda Ruta 2 Baiona', 42.106828,	-8.874192, 42.165089, -8.796310);
INSERT INTO tbl_ruta_ruta (ruta_pk, ruta_codigo, ruta_nombre, ruta_orig_lat, ruta_orig_lon, ruta_dest_lat, ruta_dest_lon)
VALUES (4002, '00121', 'Eso Camos Con', 42.136458,-8.774341, 42.135656,-8.800163);
INSERT INTO tbl_ruta_ruta (ruta_pk, ruta_codigo, ruta_nombre, ruta_orig_lat, ruta_orig_lon, ruta_dest_lat, ruta_dest_lon)
VALUES (4003, '00123', 'Camos Piñeiros', 42.153310,-8.768611, 42.153024,-8.786511);
INSERT INTO tbl_ruta_ruta (ruta_pk, ruta_codigo, ruta_nombre, ruta_orig_lat, ruta_orig_lon, ruta_dest_lat, ruta_dest_lon)
VALUES (4004, '00125', 'Nigran Tarela Cean r-2', 42.143178,-8.783027, 42.141949,-8.807676);
INSERT INTO tbl_ruta_ruta (ruta_pk, ruta_codigo, ruta_nombre, ruta_orig_lat, ruta_orig_lon, ruta_dest_lat, ruta_dest_lon)
VALUES (4005, '00127', 'Eso Pinete', 42.114979,-8.812869, 42.113487,-8.764992);
INSERT INTO tbl_ruta_ruta (ruta_pk, ruta_codigo, ruta_nombre, ruta_orig_lat, ruta_orig_lon, ruta_dest_lat, ruta_dest_lon)
VALUES (4006, '00128', 'Eso Pinete 2', 42.113487,-8.764992, 42.114979,-8.812869);
INSERT INTO tbl_ruta_ruta (ruta_pk, ruta_codigo, ruta_nombre, ruta_orig_lat, ruta_orig_lon, ruta_dest_lat, ruta_dest_lon)
VALUES (4007, '00130', 'Carrasqueira 4', 42.198007,-8.779829, 42.166821,-8.802028);
INSERT INTO tbl_ruta_ruta (ruta_pk, ruta_codigo, ruta_nombre, ruta_orig_lat, ruta_orig_lon, ruta_dest_lat, ruta_dest_lon)
VALUES (4008, '00131', 'Vilariño Ramallosa', 42.116153,-8.812116, 42.117892,-8.792012);
INSERT INTO tbl_ruta_ruta (ruta_pk, ruta_codigo, ruta_nombre, ruta_orig_lat, ruta_orig_lon, ruta_dest_lat, ruta_dest_lon)
VALUES (4009, '00132', 'Vilariño Ramallosa 2', 42.117892,-8.792012, 42.116153,-8.812116);
INSERT INTO tbl_ruta_ruta (ruta_pk, ruta_codigo, ruta_nombre, ruta_orig_lat, ruta_orig_lon, ruta_dest_lat, ruta_dest_lon)
VALUES (4010, '00133', 'Sauces 8', 42.170316,-8.676061, 42.206236,-8.730547);
INSERT INTO tbl_ruta_ruta (ruta_pk, ruta_codigo, ruta_nombre, ruta_orig_lat, ruta_orig_lon, ruta_dest_lat, ruta_dest_lon)
VALUES (4011, '00136', 'Eso Priegue', 42.135656,-8.800163, 42.160451,-8.796269);
INSERT INTO tbl_ruta_ruta (ruta_pk, ruta_codigo, ruta_nombre, ruta_orig_lat, ruta_orig_lon, ruta_dest_lat, ruta_dest_lon)
VALUES (4012, '00137', 'Carrasqueira 8', 42.189865,-8.776250, 42.198007,-8.779829);
INSERT INTO tbl_ruta_ruta (ruta_pk, ruta_codigo, ruta_nombre, ruta_orig_lat, ruta_orig_lon, ruta_dest_lat, ruta_dest_lon)
VALUES (4013, '00139', 'Gondomar Razas r-1', 42.112446,-8.768225, 42.098639,-8.750252);
INSERT INTO tbl_ruta_ruta (ruta_pk, ruta_codigo, ruta_nombre, ruta_orig_lat, ruta_orig_lon, ruta_dest_lat, ruta_dest_lon)
VALUES (4014, '00141', 'Ies Proval', 42.136458,-8.774341, 42.113421,-8.779378);
INSERT INTO tbl_ruta_ruta (ruta_pk, ruta_codigo, ruta_nombre, ruta_orig_lat, ruta_orig_lon, ruta_dest_lat, ruta_dest_lon)
VALUES (4015, '00143', 'Gondomar Villaza r-3', 42.120201,-8.753692, 42.112446,-8.768225);
INSERT INTO tbl_ruta_ruta (ruta_pk, ruta_codigo, ruta_nombre, ruta_orig_lat, ruta_orig_lon, ruta_dest_lat, ruta_dest_lon)
VALUES (4016, '00145', 'Peitieiros Zapa', 42.111059,-8.759529, 42.112446,-8.768225);
INSERT INTO tbl_ruta_ruta (ruta_pk, ruta_codigo, ruta_nombre, ruta_orig_lat, ruta_orig_lon, ruta_dest_lat, ruta_dest_lon)
VALUES (4017, '00147', 'Eso Zapa', 42.113487,-8.764992, 42.114691,-8.729232);






CREATE TABLE tbl_archivo_gps_agps (
	agps_pk BIGINT NOT NULL
	, agps_vhcl_pk BIGINT NOT NULL
	, agps_plca_pk BIGINT NOT NULL
	, agps_nombre VARCHAR(50) NOT NULL
	, agps_fecha TIMESTAMP NOT NULL
	, agps_fecha_proceso TIMESTAMP

	, CONSTRAINT pk_agps PRIMARY KEY (agps_pk)
	, CONSTRAINT fk_agps_vhcl_pk FOREIGN KEY (agps_vhcl_pk) REFERENCES tbl_vehiculo_vhcl (vhcl_pk)
	, CONSTRAINT fk_agps_plca_pk FOREIGN KEY (agps_plca_pk) REFERENCES tbl_placa_plca (plca_pk)
);

CREATE TABLE tbl_lectura_gps_lgps (
	lgps_pk BIGINT NOT NULL
	, lgps_agps_pk BIGINT NOT NULL
	, lgps_vhcl_pk BIGINT NOT NULL
	, lgps_fecha TIMESTAMP NOT NULL
	, lgps_altitude DECIMAL(19, 8) NOT NULL
	, lgps_climb DECIMAL(19, 8) NOT NULL
	, lgps_distance DECIMAL(19, 8) NOT NULL
	, lgps_eps DECIMAL(19, 8) NOT NULL
	, lgps_ept DECIMAL(19, 8) NOT NULL
	, lgps_epv DECIMAL(19, 8) NOT NULL
	, lgps_epx DECIMAL(19, 8) NOT NULL
	, lgps_latitude DECIMAL(19, 8) NOT NULL
	, lgps_longitude DECIMAL(19, 8) NOT NULL
	, lgps_mode INTEGER NOT NULL
	, lgps_number_sats INTEGER NOT NULL
	, lgps_speed DECIMAL(19, 8) NOT NULL
	, lgps_track DECIMAL(19, 8) NOT NULL

	, CONSTRAINT pk_lgps PRIMARY KEY (lgps_pk)
	, CONSTRAINT fk_lgps_agps_pk FOREIGN KEY (lgps_agps_pk) REFERENCES tbl_archivo_gps_agps (agps_pk)
	, CONSTRAINT fk_lgps_vhcl_pk FOREIGN KEY (lgps_vhcl_pk) REFERENCES tbl_vehiculo_vhcl (vhcl_pk)
);

CREATE INDEX ix_lgps ON tbl_lectura_gps_lgps (lgps_vhcl_pk, lgps_fecha);



CREATE TABLE tbl_servicio_srvc (
	srvc_pk BIGINT NOT NULL
	, srvc_codigo_servicio VARCHAR(20) NOT NULL
	, srvc_codigo_parte VARCHAR(20) NOT NULL
	, srvc_ruta_pk BIGINT
	, srvc_cdtr1_pk BIGINT
	, srvc_cdtr2_pk BIGINT
	, srvc_vhcl_pk BIGINT
	, srvc_fecha_desde TIMESTAMP
	, srvc_fecha_hasta TIMESTAMP
	, srvc_util_km DECIMAL(10, 3)
	, srvc_vacio_km DECIMAL(10, 3)
	, srvc_util_erp_km DECIMAL(10, 3)
	, srvc_vacio_erp_km DECIMAL(10, 3)
	, srvc_lgps_orig_pk BIGINT
	, srvc_lgps_dest_pk BIGINT

	, CONSTRAINT pk_srvc PRIMARY KEY (srvc_pk)
	, CONSTRAINT uk_srvc UNIQUE (srvc_codigo_parte)
	, CONSTRAINT fk_srvc_ruta_pk FOREIGN KEY (srvc_ruta_pk) REFERENCES tbl_ruta_ruta (ruta_pk)
	, CONSTRAINT fk_srvc_cdtr1_pk FOREIGN KEY (srvc_cdtr1_pk) REFERENCES tbl_conductor_cdtr (cdtr_pk)
	, CONSTRAINT fk_srvc_cdtr2_pk FOREIGN KEY (srvc_cdtr2_pk) REFERENCES tbl_conductor_cdtr (cdtr_pk)
	, CONSTRAINT fk_srvc_vhcl_pk FOREIGN KEY (srvc_vhcl_pk) REFERENCES tbl_vehiculo_vhcl (vhcl_pk)
	, CONSTRAINT fk_srvc_lgps_orig_pk FOREIGN KEY (srvc_lgps_orig_pk) REFERENCES tbl_lectura_gps_lgps (lgps_pk)
	, CONSTRAINT fk_srvc_lgps_dest_pk FOREIGN KEY (srvc_lgps_dest_pk) REFERENCES tbl_lectura_gps_lgps (lgps_pk)
);

CREATE INDEX ix_srvc ON tbl_servicio_srvc (srvc_vhcl_pk, srvc_fecha_desde);
CREATE INDEX ix_srvc_ruta_pk ON tbl_servicio_srvc (srvc_ruta_pk);
