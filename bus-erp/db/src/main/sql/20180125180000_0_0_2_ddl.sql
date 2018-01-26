-- // 0.0.2 DDL
CREATE TABLE tbl_placa_arranque_plaq (
	plaq_pk BIGINT NOT NULL
	, plaq_plca_pk BIGINT NOT NULL
	, plaq_vhcl_pk BIGINT NOT NULL
	, plaq_fecha TIMESTAMP NOT NULL

	, CONSTRAINT pk_plaq PRIMARY KEY (plaq_pk)
)\

CREATE INDEX ix_plaq_plca_pk ON tbl_placa_arranque_plaq(plaq_plca_pk, plaq_fecha DESC)\

GRANT SELECT, INSERT ON tbl_placa_arranque_plaq TO transport\




CREATE TABLE tbl_placa_ping_plpg (
	plpg_pk BIGINT NOT NULL
	, plpg_plca_pk BIGINT NOT NULL
	, plpg_vhcl_pk BIGINT NOT NULL
	, plpg_fecha TIMESTAMP NOT NULL
	, plpg_elt NUMERIC(12,4) NOT NULL

	, CONSTRAINT pk_plpg PRIMARY KEY (plpg_pk)
)\

CREATE INDEX ix_plpg_plca_pk ON tbl_placa_ping_plpg(plpg_plca_pk, plpg_fecha DESC)\

GRANT SELECT, INSERT ON tbl_placa_ping_plpg TO transport\















-- //@UNDO
TRUNCATE TABLE tbl_placa_ping_plpg CASCADE\
TRUNCATE TABLE tbl_placa_arranque_plaq CASCADE\

DROP TABLE tbl_placa_ping_plpg\
DROP TABLE tbl_placa_arranque_plaq\






