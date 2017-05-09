-- // DDL

CREATE SEQUENCE sq_vending INCREMENT BY 1 START WITH 1000000\

CREATE TABLE tbl_telemetry_tlmy (
	tlmy_pk NUMERIC(19) NOT NULL
	, tlmy_date TIMESTAMP
	, tlmy_protocol_release VARCHAR(100)
	, tlmy_rawdata TEXT
)
\

ALTER TABLE tbl_telemetry_tlmy ADD CONSTRAINT pk_tlmy PRIMARY KEY (tlmy_pk)\

-- //@UNDO
DROP TABLE tbl_telemetry_tlmy\

DROP SEQUENCE sq_vending\

