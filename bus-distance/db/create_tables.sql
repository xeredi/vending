DROP TABLE tbl_lectura_gps_lgps;
DROP TABLE tbl_archivo_gps_agps;
DROP TABLE tbl_servicio_srvc;
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
	, CONSTRAINT uk_vhcl_plca_pk UNIQUE (vhcl_plca_pk)
	, CONSTRAINT fk_vhcl_plca_pk FOREIGN KEY (vhcl_plca_pk) REFERENCES tbl_placa_plca (plca_pk)
);

INSERT INTO tbl_vehiculo_vhcl (vhcl_pk, vhcl_plca_pk, vhcl_codigo) VALUES (2000, 1000, '28022v');

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
	, ruta_orig_lat NUMBER(12, 8) NOT NULL
	, ruta_orig_lon NUMBER(12, 8) NOT NULL
	, ruta_dest_lat NUMBER(12, 8) NOT NULL
	, ruta_dest_lon NUMBER(12, 8) NOT NULL

	, CONSTRAINT pk_ruta PRIMARY KEY (ruta_pk)
	, CONSTRAINT uk_ruta_codigo UNIQUE (ruta_codigo)
);

CREATE TABLE tbl_servicio_srvc (
	srvc_pk NUMBER(19) NOT NULL
	, srvc_ruta_pk NUMBER(19) NOT NULL
	, srvc_cdtr_pk NUMBER(19) NOT NULL
	, srvc_vhcl_pk NUMBER(19) NOT NULL
	, srvc_fecha TIMESTAMP NOT NULL
	, srvc_util_km NUMBER(10, 3)
	, srvc_vacio_km NUMBER(10, 3)

	, CONSTRAINT pk_srvc PRIMARY KEY (srvc_pk)
	, CONSTRAINT uk_srvc UNIQUE (srvc_ruta_pk, srvc_cdtr_pk, srvc_vhcl_pk, srvc_fecha)
	, CONSTRAINT fk_srvc_ruta_pk FOREIGN KEY (srvc_ruta_pk) REFERENCES tbl_ruta_ruta (ruta_pk)
	, CONSTRAINT fk_srvc_cdtr_pk FOREIGN KEY (srvc_cdtr_pk) REFERENCES tbl_conductor_cdtr (cdtr_pk)
	, CONSTRAINT fk_srvc_vhcl_pk FOREIGN KEY (srvc_vhcl_pk) REFERENCES tbl_vehiculo_vhcl (vhcl_pk)
);

CREATE TABLE tbl_archivo_gps_agps (
	agps_pk NUMBER(19) NOT NULL
	, agps_vhcl_pk NUMBER(19) NOT NULL
	, agps_plca_pk NUMBER(19) NOT NULL
	, agps_nombre VARCHAR2(50) NOT NULL
	, agps_fecha TIMESTAMP NOT NULL

	, CONSTRAINT pk_agps PRIMARY KEY (agps_pk)
	, CONSTRAINT fk_agps_vhcl_pk FOREIGN KEY (agps_vhcl_pk) REFERENCES tbl_vehiculo_vhcl (vhcl_pk)
	, CONSTRAINT fk_agps_plca_pk FOREIGN KEY (agps_plca_pk) REFERENCES tbl_placa_plca (plca_pk)
);

CREATE TABLE tbl_lectura_gps_lgps (
	lgps_pk NUMBER(19) NOT NULL
	, lgps_agps_pk NUMBER(19) NOT NULL
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
);

