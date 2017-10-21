DROP TABLE tbl_servicio_srvc;
DROP TABLE tbl_lectura_gps_lgps;
DROP TABLE tbl_archivo_gps_agps;
DROP TABLE tbl_ruta_ruta;
DROP TABLE tbl_conductor_cdtr;
DROP TABLE tbl_vehiculo_vhcl;
DROP TABLE tbl_placa_plca;

CREATE SEQUENCE seq_bus;

CREATE TABLE tbl_placa_plca (
	plca_pk NUMBER(19) NOT NULL
	, plca_codigo VARCHAR2(20) NOT NULL

	, CONSTRAINT pk_plca PRIMARY KEY (plca_pk)
	, CONSTRAINT uk_plca_codigo UNIQUE (plca_codigo)
);

INSERT INTO tbl_placa_plca (plca_pk, plca_codigo) VALUES (1000, '28022');

CREATE TABLE tbl_vehiculo_vhcl (
	vhcl_pk NUMBER(19) NOT NULL
	, vhcl_plca_pk NUMBER(19)
	, vhcl_codigo VARCHAR2(20) NOT NULL

	, CONSTRAINT pk_vhcl PRIMARY KEY (vhcl_pk)
	, CONSTRAINT uk_vhcl_codigo UNIQUE (vhcl_codigo)
	, CONSTRAINT fk_vhcl_plca_pk FOREIGN KEY (vhcl_plca_pk) REFERENCES tbl_placa_plca (plca_pk)
);

INSERT INTO tbl_vehiculo_vhcl (vhcl_pk, vhcl_plca_pk, vhcl_codigo) VALUES (2000, 1000, '0125');

CREATE TABLE tbl_conductor_cdtr (
	cdtr_pk NUMBER(19) NOT NULL
	, cdtr_codigo VARCHAR2(20) NOT NULL
	, cdtr_nombre VARCHAR2(50) NOT NULL

	, CONSTRAINT pk_cdtr PRIMARY KEY (cdtr_pk)
	, CONSTRAINT uk_cdtr_codigo UNIQUE (cdtr_codigo)
);

CREATE TABLE tbl_ruta_ruta (
	ruta_pk NUMBER(19) NOT NULL
	, ruta_codigo VARCHAR2(20) NOT NULL
	, ruta_nombre VARCHAR2(100) NOT NULL
	, ruta_orig_lat NUMBER(12, 8)
	, ruta_orig_lon NUMBER(12, 8)
	, ruta_dest_lat NUMBER(12, 8)
	, ruta_dest_lon NUMBER(12, 8)

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
	agps_pk NUMBER(19) NOT NULL
	, agps_vhcl_pk NUMBER(19) NOT NULL
	, agps_plca_pk NUMBER(19) NOT NULL
	, agps_nombre VARCHAR2(50) NOT NULL
	, agps_fecha TIMESTAMP NOT NULL
	, agps_fecha_proceso TIMESTAMP

	, CONSTRAINT pk_agps PRIMARY KEY (agps_pk)
	, CONSTRAINT fk_agps_vhcl_pk FOREIGN KEY (agps_vhcl_pk) REFERENCES tbl_vehiculo_vhcl (vhcl_pk)
	, CONSTRAINT fk_agps_plca_pk FOREIGN KEY (agps_plca_pk) REFERENCES tbl_placa_plca (plca_pk)
);

CREATE TABLE tbl_lectura_gps_lgps (
	lgps_pk NUMBER(19) NOT NULL
	, lgps_agps_pk NUMBER(19) NOT NULL
	, lgps_vhcl_pk NUMBER(19) NOT NULL
	, lgps_fecha TIMESTAMP NOT NULL
	, lgps_altitude NUMBER(19, 8) NOT NULL
	, lgps_climb NUMBER(19, 8) NOT NULL
	, lgps_distance NUMBER(19, 8) NOT NULL
	, lgps_eps NUMBER(19, 8) NOT NULL
	, lgps_ept NUMBER(19, 8) NOT NULL
	, lgps_epv NUMBER(19, 8) NOT NULL
	, lgps_epx NUMBER(19, 8) NOT NULL
	, lgps_latitude NUMBER(19, 8) NOT NULL
	, lgps_longitude NUMBER(19, 8) NOT NULL
	, lgps_mode NUMBER(19) NOT NULL
	, lgps_number_sats NUMBER(19) NOT NULL
	, lgps_speed NUMBER(19, 8) NOT NULL
	, lgps_track NUMBER(19, 8) NOT NULL

	, CONSTRAINT pk_lgps PRIMARY KEY (lgps_pk)
	, CONSTRAINT fk_lgps_agps_pk FOREIGN KEY (lgps_agps_pk) REFERENCES tbl_archivo_gps_agps (agps_pk)
	, CONSTRAINT fk_lgps_vhcl_pk FOREIGN KEY (lgps_vhcl_pk) REFERENCES tbl_vehiculo_vhcl (vhcl_pk)
);

CREATE INDEX ix_lgps ON tbl_lectura_gps_lgps (lgps_vhcl_pk, lgps_fecha);



CREATE TABLE tbl_servicio_srvc (
	srvc_pk NUMBER(19) NOT NULL
	, srvc_codigo_servicio VARCHAR2(20) NOT NULL
	, srvc_codigo_parte VARCHAR2(20) NOT NULL
	, srvc_ruta_pk NUMBER(19)
	, srvc_cdtr1_pk NUMBER(19)
	, srvc_cdtr2_pk NUMBER(19)
	, srvc_vhcl_pk NUMBER(19)
	, srvc_fecha_desde TIMESTAMP
	, srvc_fecha_hasta TIMESTAMP
	, srvc_util_km NUMBER(10, 3)
	, srvc_vacio_km NUMBER(10, 3)
	, srvc_util_erp_km NUMBER(10, 3)
	, srvc_vacio_erp_km NUMBER(10, 3)
	, srvc_lgps_orig_pk NUMBER(19)
	, srvc_lgps_dest_pk NUMBER(19)

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
