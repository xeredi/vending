-- // 0.0.2 Create Functions
CREATE OR REPLACE FUNCTION guardar_lectura_gps(
	placa_serial IN VARCHAR
	, fecha IN TIMESTAMP
	, lat IN DOUBLE PRECISION
	, lon IN DOUBLE PRECISION
	, alt IN DOUBLE PRECISION
	, spd IN DOUBLE PRECISION
	, dst IN DOUBLE PRECISION
) RETURNS void AS $$
DECLARE
BEGIN
	WITH inserted AS (
		INSERT INTO tbl_lectura_gps_lgps (lgps_pk, lgps_plca_pk, lgps_vhcl_pk, lgps_fecha, lgps_lat, lgps_lon, lgps_alt, lgps_spd, lgps_dst, lgps_elt)
			SELECT nextval('seq_app'), plca_pk, vhcl_pk
				, fecha, lat, lon, alt, spd, dst, EXTRACT(EPOCH FROM (fecha - COALESCE(lgps_fecha, fecha)))
			FROM
				tbl_placa_plca
				INNER JOIN tbl_vehiculo_vhcl ON
					vhcl_plca_pk = plca_pk
				LEFT JOIN tbl_lectura_gps_lgps ON
					lgps_pk = plca_ultimo_lgps_pk
			WHERE
				plca_codigo = placa_serial
				AND plca_fecha_fin > fecha
				AND (
					lgps_fecha IS NULL
					OR (
						lgps_fecha <= fecha
						AND (
							lgps_lat <> lat
							OR lgps_lon <> lon
						)
					)
				)
		RETURNING lgps_pk, lgps_plca_pk, lgps_vhcl_pk
	)
	UPDATE tbl_placa_plca SET
		plca_ultimo_lgps_pk = lgps_pk
	FROM inserted
	WHERE plca_pk = lgps_plca_pk
	;
/*
	IF NOT FOUND
	THEN
		RETURN NULL;
	ELSE
		RETURN currval('seq_app');
	END IF;
*/
END;
$$ LANGUAGE plpgsql
\


SELECT guardar_lectura_gps('00000000ecec8745', NOW()::timestamp, 5.5, 4.4, 3.3, 2.2, 1.1)\




CREATE OR REPLACE FUNCTION guardar_lectura_gps2(
	placa_serial IN VARCHAR
	, fecha IN TIMESTAMP
	, lat IN DOUBLE PRECISION
	, lon IN DOUBLE PRECISION
	, alt IN DOUBLE PRECISION
	, spd IN DOUBLE PRECISION
	, dst IN DOUBLE PRECISION
) RETURNS void AS $$
DECLARE
	plcaId INTEGER;
	vhclId INTEGER;
	fechaAnterior TIMESTAMP;
	distance DOUBLE PRECISION;
BEGIN
	SELECT
		INTO plcaId, vhclId, fechaAnterior
		plca_pk, vhcl_pk, lgps_fecha
	FROM
		tbl_placa_plca
		INNER JOIN tbl_vehiculo_vhcl ON vhcl_plca_pk = plca_pk
		LEFT JOIN tbl_lectura_gps_lgps ON lgps_pk = plca_ultimo_lgps_pk
	WHERE
		plca_codigo = placa_serial
		AND plca_fecha_fin > fecha
		AND (
			lgps_fecha IS NULL
			OR (
				lgps_fecha <= fecha
				AND (
					lgps_lat <> lat
					OR lgps_lon <> lon
				)
			)
		)
	;

	IF FOUND THEN
		INSERT INTO tbl_lectura_gps_lgps (
			lgps_pk, lgps_plca_pk, lgps_vhcl_pk, lgps_fecha, lgps_lat, lgps_lon, lgps_alt, lgps_spd, lgps_dst, lgps_elt)
		VALUES (
			nextval('seq_app'), plcaId, vhclId, fecha, lat, lon, alt, spd, dst
			, EXTRACT(EPOCH FROM (fecha - COALESCE(fechaAnterior, fecha)))
		);

		UPDATE tbl_placa_plca SET
			plca_ultimo_lgps_pk = currval('seq_app')
		WHERE plca_pk = plcaId;

--		RETURN currval('seq_app');
	END IF;

--	RETURN NULL;
END;
$$ LANGUAGE plpgsql
\



















-- //@UNDO

DROP FUNCTION guardar_lectura_gps2(DOUBLE VARCHAR/**/, TIMESTAMP, DOUBLE PRECISION, DOUBLE PRECISION, DOUBLE PRECISION, DOUBLE PRECISION, DOUBLE PRECISION)\
DROP FUNCTION guardar_lectura_gps(DOUBLE VARCHAR/**/, TIMESTAMP, DOUBLE PRECISION, DOUBLE PRECISION, DOUBLE PRECISION, DOUBLE PRECISION, DOUBLE PRECISION)\






