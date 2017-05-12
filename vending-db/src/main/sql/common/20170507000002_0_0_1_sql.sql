-- // DDL

INSERT INTO tbl_currency_crcy (crcy_pk, crcy_code, crcy_decimals) VALUES (1000, 'EUR', 2)\
INSERT INTO tbl_currency_crcy (crcy_pk, crcy_code, crcy_decimals) VALUES (1001, 'USD', 2)\
INSERT INTO tbl_currency_crcy (crcy_pk, crcy_code, crcy_decimals) VALUES (1002, 'GBP', 2)\

INSERT INTO tbl_provider_prvd (prvd_pk, prvd_code) VALUES (2000, 'pr1')\
INSERT INTO tbl_provider_prvd (prvd_pk, prvd_code) VALUES (2001, 'pr2')\

INSERT INTO tbl_business_bsns (bsns_pk, bsns_prvd_pk, bsns_code) VALUES (4000, 2000, 'b1')\
INSERT INTO tbl_business_bsns (bsns_pk, bsns_prvd_pk, bsns_code) VALUES (4001, 2000, 'b2')\
INSERT INTO tbl_business_bsns (bsns_pk, bsns_prvd_pk, bsns_code) VALUES (4002, 2001, 'b1')\

INSERT INTO tbl_machine_mchn (mchn_pk, mchn_prvd_pk, mchn_bsns_pk, mchn_crcy_pk, mchn_code)
	VALUES (3000, 2000, 4000, 1000, 'm1')\
INSERT INTO tbl_machine_mchn (mchn_pk, mchn_prvd_pk, mchn_bsns_pk, mchn_crcy_pk, mchn_code)
	VALUES (3001, 2000, 4000, 1000, 'm2')\
INSERT INTO tbl_machine_mchn (mchn_pk, mchn_prvd_pk, mchn_bsns_pk, mchn_crcy_pk, mchn_code)
	VALUES (3002, 2001, 4002, 1000, 'm1')\
INSERT INTO tbl_machine_mchn (mchn_pk, mchn_prvd_pk, mchn_bsns_pk, mchn_crcy_pk, mchn_code)
	VALUES (3003, 2001, 4002, 1000, 'm2')\

INSERT INTO tbl_telemetry_reader_tlrd(tlrd_pk, tlrd_code, tlrd_mchn_pk)
	VALUES (5000, 'tlmy1', 3000)\
INSERT INTO tbl_telemetry_reader_tlrd(tlrd_pk, tlrd_code, tlrd_mchn_pk)
	VALUES (5001, 'tlmy2', 3001)\
INSERT INTO tbl_telemetry_reader_tlrd(tlrd_pk, tlrd_code, tlrd_mchn_pk)
	VALUES (5002, 'tlmy3', 3002)\
INSERT INTO tbl_telemetry_reader_tlrd(tlrd_pk, tlrd_code, tlrd_mchn_pk)
	VALUES (5003, 'tlmy4', 3003)\






-- //@UNDO
DELETE FROM tbl_telemetry_tlmy\
DELETE FROM tbl_machine_status_mcst\
DELETE FROM tbl_machine_activity_mcac\

DELETE FROM tbl_telemetry_reader_tlrd\
DELETE FROM tbl_machine_mchn\
DELETE FROM tbl_business_bsns\
DELETE FROM tbl_provider_prvd\
DELETE FROM tbl_currency_crcy\


