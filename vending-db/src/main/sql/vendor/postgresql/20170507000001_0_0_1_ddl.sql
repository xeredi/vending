-- // DDL

CREATE SEQUENCE sq_vending INCREMENT BY 1 START WITH 1000000\

/* tbl_currency_crcy */
/* tbl_currency_crcy */
/* tbl_currency_crcy */
CREATE TABLE tbl_currency_crcy (
	crcy_pk NUMERIC(19) NOT NULL
	, crcy_code VARCHAR(3) NOT NULL
	, crcy_decimals NUMERIC(1) NOT NULL
)\

ALTER TABLE tbl_currency_crcy ADD CONSTRAINT pk_crcy PRIMARY KEY (crcy_pk)\
ALTER TABLE tbl_currency_crcy ADD CONSTRAINT uk_crcy_code UNIQUE (crcy_code)\

/* tbl_provider_prvd */
/* tbl_provider_prvd */
/* tbl_provider_prvd */
CREATE TABLE tbl_provider_prvd (
	prvd_pk NUMERIC(19) NOT NULL
	, prvd_code VARCHAR(20) NOT NULL
)\

ALTER TABLE tbl_provider_prvd ADD CONSTRAINT pk_prvd PRIMARY KEY (prvd_pk)\
ALTER TABLE tbl_provider_prvd ADD CONSTRAINT uk_prvd_code UNIQUE (prvd_code)\

/* tbl_business_bsns */
/* tbl_business_bsns */
/* tbl_business_bsns */
CREATE TABLE tbl_business_bsns (
	bsns_pk NUMERIC(19) NOT NULL
	, bsns_code VARCHAR(20) NOT NULL
	, bsns_prvd_pk NUMERIC(19) NOT NULL
)\

ALTER TABLE tbl_business_bsns ADD CONSTRAINT pk_bsns PRIMARY KEY (bsns_pk)\
ALTER TABLE tbl_business_bsns ADD CONSTRAINT uk_bsns_code UNIQUE (bsns_prvd_pk, bsns_code)\
ALTER TABLE tbl_business_bsns ADD CONSTRAINT fk_bsns_prvd_pk
	FOREIGN KEY (bsns_prvd_pk) REFERENCES tbl_provider_prvd (prvd_pk)\

/* tbl_machine_mchn */
/* tbl_machine_mchn */
/* tbl_machine_mchn */
CREATE TABLE tbl_machine_mchn (
	mchn_pk NUMERIC(19) NOT NULL
	, mchn_prvd_pk NUMERIC(19) NOT NULL
	, mchn_bsns_pk NUMERIC(19) NOT NULL
	, mchn_crcy_pk NUMERIC(19) NOT NULL
	, mchn_code VARCHAR(20) NOT NULL
)\

ALTER TABLE tbl_machine_mchn ADD CONSTRAINT pk_mchn PRIMARY KEY (mchn_pk)\
ALTER TABLE tbl_machine_mchn ADD CONSTRAINT uk_mchn_code UNIQUE (mchn_prvd_pk, mchn_code)\
ALTER TABLE tbl_machine_mchn ADD CONSTRAINT fk_mchn_prvd_pk
	FOREIGN KEY (mchn_prvd_pk) REFERENCES tbl_provider_prvd (prvd_pk)\
ALTER TABLE tbl_machine_mchn ADD CONSTRAINT fk_mchn_bsns_pk
	FOREIGN KEY (mchn_bsns_pk) REFERENCES tbl_business_bsns (bsns_pk)\
ALTER TABLE tbl_machine_mchn ADD CONSTRAINT fk_mchn_crcy_pk
	FOREIGN KEY (mchn_crcy_pk) REFERENCES tbl_currency_crcy (crcy_pk)\

/* tbl_machine_activity_mcac */
/* tbl_machine_activity_mcac */
/* tbl_machine_activity_mcac */
CREATE TABLE tbl_machine_activity_mcac (
	mcac_pk NUMERIC(19) NOT NULL
	, mcac_mchn_pk NUMERIC(19) NOT NULL
	, mcac_date DATE NOT NULL
	, mcac_hour NUMERIC(2) NOT NULL

	, mcac_hop5000 NUMERIC(8) NOT NULL
	, mcac_hop2000 NUMERIC(8) NOT NULL
	, mcac_hop1000 NUMERIC(8) NOT NULL
	, mcac_hop0500 NUMERIC(8) NOT NULL
	, mcac_hop0200 NUMERIC(8) NOT NULL
	, mcac_hop0100 NUMERIC(8) NOT NULL
	, mcac_hop0050 NUMERIC(8) NOT NULL
	, mcac_hop0020 NUMERIC(8) NOT NULL
	, mcac_hop0010 NUMERIC(8) NOT NULL
	, mcac_ent5000 NUMERIC(8) NOT NULL
	, mcac_ent2000 NUMERIC(8) NOT NULL
	, mcac_ent1000 NUMERIC(8) NOT NULL
	, mcac_ent0500 NUMERIC(8) NOT NULL
	, mcac_ent0200 NUMERIC(8) NOT NULL
	, mcac_ent0100 NUMERIC(8) NOT NULL
	, mcac_ent0050 NUMERIC(8) NOT NULL
	, mcac_ent0020 NUMERIC(8) NOT NULL
	, mcac_ent0010 NUMERIC(8) NOT NULL
	, mcac_sal5000 NUMERIC(8) NOT NULL
	, mcac_sal2000 NUMERIC(8) NOT NULL
	, mcac_sal1000 NUMERIC(8) NOT NULL
	, mcac_sal0500 NUMERIC(8) NOT NULL
	, mcac_sal0200 NUMERIC(8) NOT NULL
	, mcac_sal0100 NUMERIC(8) NOT NULL
	, mcac_sal0050 NUMERIC(8) NOT NULL
	, mcac_sal0020 NUMERIC(8) NOT NULL
	, mcac_sal0010 NUMERIC(8) NOT NULL
)\

ALTER TABLE tbl_machine_activity_mcac ADD CONSTRAINT pk_mcac PRIMARY KEY (mcac_pk)\
ALTER TABLE tbl_machine_activity_mcac ADD CONSTRAINT uk_mcac_hour UNIQUE (mcac_mchn_pk, mcac_date, mcac_hour)\
ALTER TABLE tbl_machine_activity_mcac ADD CONSTRAINT fk_mcac_mchn_pk
	FOREIGN KEY (mcac_mchn_pk) REFERENCES tbl_machine_mchn (mchn_pk)\


/* tbl_machine_status_mcst */
/* tbl_machine_status_mcst */
/* tbl_machine_status_mcst */
CREATE TABLE tbl_machine_status_mcst (
	mcst_pk NUMERIC(19) NOT NULL
	, mcst_mchn_pk NUMERIC(19) NOT NULL
	, mcst_last_update TIMESTAMP NOT NULL
	, mcst_next_update TIMESTAMP NOT NULL

	, mcst_hop5000 NUMERIC(8) NOT NULL
	, mcst_hop2000 NUMERIC(8) NOT NULL
	, mcst_hop1000 NUMERIC(8) NOT NULL
	, mcst_hop0500 NUMERIC(8) NOT NULL
	, mcst_hop0200 NUMERIC(8) NOT NULL
	, mcst_hop0100 NUMERIC(8) NOT NULL
	, mcst_hop0050 NUMERIC(8) NOT NULL
	, mcst_hop0020 NUMERIC(8) NOT NULL
	, mcst_hop0010 NUMERIC(8) NOT NULL
	, mcst_ent5000 NUMERIC(8) NOT NULL
	, mcst_ent2000 NUMERIC(8) NOT NULL
	, mcst_ent1000 NUMERIC(8) NOT NULL
	, mcst_ent0500 NUMERIC(8) NOT NULL
	, mcst_ent0200 NUMERIC(8) NOT NULL
	, mcst_ent0100 NUMERIC(8) NOT NULL
	, mcst_ent0050 NUMERIC(8) NOT NULL
	, mcst_ent0020 NUMERIC(8) NOT NULL
	, mcst_ent0010 NUMERIC(8) NOT NULL
	, mcst_sal5000 NUMERIC(8) NOT NULL
	, mcst_sal2000 NUMERIC(8) NOT NULL
	, mcst_sal1000 NUMERIC(8) NOT NULL
	, mcst_sal0500 NUMERIC(8) NOT NULL
	, mcst_sal0200 NUMERIC(8) NOT NULL
	, mcst_sal0100 NUMERIC(8) NOT NULL
	, mcst_sal0050 NUMERIC(8) NOT NULL
	, mcst_sal0020 NUMERIC(8) NOT NULL
	, mcst_sal0010 NUMERIC(8) NOT NULL
)\

ALTER TABLE tbl_machine_status_mcst ADD CONSTRAINT pk_mcst PRIMARY KEY (mcst_pk)\
ALTER TABLE tbl_machine_status_mcst ADD CONSTRAINT uk_mcst_mchn_pk UNIQUE (mcst_mchn_pk)\
ALTER TABLE tbl_machine_status_mcst ADD CONSTRAINT fk_mcst_mchn_pk
	FOREIGN KEY (mcst_mchn_pk) REFERENCES tbl_machine_mchn (mchn_pk)\


/* tbl_telemetry_reader_tlrd */
/* tbl_telemetry_reader_tlrd */
/* tbl_telemetry_reader_tlrd */
CREATE TABLE tbl_telemetry_reader_tlrd (
	tlrd_pk NUMERIC(19) NOT NULL
	, tlrd_code VARCHAR(20) NOT NULL
	, tlrd_mchn_pk NUMERIC(19)
)\

ALTER TABLE tbl_telemetry_reader_tlrd ADD CONSTRAINT pk_tlrd PRIMARY KEY (tlrd_pk)\
ALTER TABLE tbl_telemetry_reader_tlrd ADD CONSTRAINT uk_tlrd_code UNIQUE (tlrd_code)\
ALTER TABLE tbl_telemetry_reader_tlrd ADD CONSTRAINT fk_tlrd_mchn_pk
	FOREIGN KEY (tlrd_mchn_pk) REFERENCES tbl_machine_mchn (mchn_pk)\

CREATE INDEX ix_tlrd_mchn_pk ON tbl_telemetry_reader_tlrd(tlrd_mchn_pk)\


/* tbl_telemetry_tlmy */
/* tbl_telemetry_tlmy */
/* tbl_telemetry_tlmy */
CREATE TABLE tbl_telemetry_tlmy (
	tlmy_pk NUMERIC(19) NOT NULL
	, tlmy_date TIMESTAMP
	, tlmy_protocol_release VARCHAR(100)
	, tlmy_rawdata TEXT
)\

ALTER TABLE tbl_telemetry_tlmy ADD CONSTRAINT pk_tlmy PRIMARY KEY (tlmy_pk)\






-- //@UNDO
DROP TABLE tbl_telemetry_tlmy\
DROP TABLE tbl_telemetry_reader_tlrd\
DROP TABLE tbl_machine_status_mcst\
DROP TABLE tbl_machine_activity_mcac\
DROP TABLE tbl_machine_mchn\
DROP TABLE tbl_business_bsns\
DROP TABLE tbl_provider_prvd\
DROP TABLE tbl_currency_crcy\

DROP SEQUENCE sq_vending\

