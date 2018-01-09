-- // 0.0.2 Create Functions
CREATE OR REPLACE FUNCTION distanciaKm(
	latOrig IN DOUBLE PRECISION, lonOrig IN DOUBLE PRECISION, latDest IN DOUBLE PRECISION, lonDest IN DOUBLE PRECISION
) RETURNS DOUBLE PRECISION AS $$
BEGIN
   RETURN (
   		acos(
   			sin(radians(latOrig)) * sin(radians(latDest))
   			+ cos(radians(latOrig)) * cos(radians(latDest)) * cos(radians(lonOrig) - radians(lonDest))
   		) * 6378
   );
END;
$$ LANGUAGE plpgsql IMMUTABLE PARALLEL SAFE
\


CREATE OR REPLACE FUNCTION guardar_lectura_gps(
	placa_serial IN VARCHAR
	, fecha IN TIMESTAMP
	, lat IN DOUBLE PRECISION
	, lon IN DOUBLE PRECISION
	, alt IN DOUBLE PRECISION
	, spd IN DOUBLE PRECISION
) RETURNS void AS $$
DECLARE
BEGIN
	WITH inserted AS (
		INSERT INTO tbl_lectura_gps_lgps (
			lgps_pk, lgps_plca_pk, lgps_vhcl_pk, lgps_fecha, lgps_lat, lgps_lon, lgps_alt, lgps_spd, lgps_dst, lgps_elt
		)
			SELECT nextval('seq_app'), plca_pk, vhcl_pk
				, fecha, lat, lon, alt, spd
				, (
					CASE
						WHEN plca_ultimo_lgps_pk IS NULL THEN 0
						ELSE distanciaKm(lgps_lat, lgps_lon, lat, lon)
					END
				)
				, EXTRACT(EPOCH FROM (fecha - COALESCE(lgps_fecha, fecha)))
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
END;
$$ LANGUAGE plpgsql
\


SELECT guardar_lectura_gps('00000000ecec8745', NOW()::timestamp, 5.5, 4.4, 3.3, 2.2)\




CREATE OR REPLACE FUNCTION guardar_lectura_gps2(
	placa_serial IN VARCHAR
	, fecha IN TIMESTAMP
	, lat IN DOUBLE PRECISION
	, lon IN DOUBLE PRECISION
	, alt IN DOUBLE PRECISION
	, spd IN DOUBLE PRECISION
) RETURNS void AS $$
DECLARE
	plcaId INTEGER;
	vhclId INTEGER;
	fechaAnt TIMESTAMP;
	latAnt DOUBLE PRECISION;
	lonAnt DOUBLE PRECISION;
BEGIN
	SELECT
		INTO plcaId, vhclId, fechaAnt, latAnt, lonAnt
		plca_pk, vhcl_pk, lgps_fecha, lgps_lat, lgps_lon
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
			nextval('seq_app'), plcaId, vhclId, fecha, lat, lon, alt, spd
			, (
				CASE
					WHEN latAnt IS NULL OR lonAnt IS NULL THEN 0
					ELSE distanciaKm(latAnt, lonAnt, lat, lon)
				END
			)
			, EXTRACT(EPOCH FROM (fecha - COALESCE(fechaAnt, fecha)))
		);

		UPDATE tbl_placa_plca SET
			plca_ultimo_lgps_pk = currval('seq_app')
		WHERE plca_pk = plcaId;
	END IF;
END;
$$ LANGUAGE plpgsql
\



CREATE OR REPLACE FUNCTION guardar_lectura_gps3(
	placa_serial IN VARCHAR
	, fecha IN TIMESTAMP
	, lat IN DOUBLE PRECISION
	, lon IN DOUBLE PRECISION
	, alt IN DOUBLE PRECISION
	, spd IN DOUBLE PRECISION
) RETURNS void AS $$
DECLARE
BEGIN
	INSERT INTO tbl_lectura_gps_lgps (
		lgps_pk, lgps_plca_pk, lgps_vhcl_pk, lgps_fecha, lgps_lat, lgps_lon, lgps_alt, lgps_spd, lgps_dst, lgps_elt
	)
		SELECT
			nextval('seq_app'), plca_pk, vhcl_pk
			, fecha, lat, lon, alt, spd
			, (
				CASE
					WHEN lgps_pk IS NULL THEN 0
					ELSE distanciaKm(lgps_lat, lgps_lon, lat, lon)
				END
			)
			, EXTRACT(EPOCH FROM (fecha - COALESCE(lgps_fecha, fecha)))
		FROM
			tbl_placa_plca
			inner join tbl_vehiculo_vhcl ON vhcl_plca_pk = plca_pk
			left join lateral (
				SELECT * FROM tbl_lectura_gps_lgps
				WHERE lgps_vhcl_pk = vhcl_pk
				ORDER BY lgps_fecha DESC
				LIMIT 1
			) lgps on true
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
END;
$$ LANGUAGE plpgsql
\


















-- //@UNDO

DROP FUNCTION guardar_lectura_gps3(
	DOUBLE VARCHAR
	, TIMESTAMP
	, DOUBLE PRECISION
	, DOUBLE PRECISION
	, DOUBLE PRECISION
	, DOUBLE PRECISION
)\
DROP FUNCTION guardar_lectura_gps2(
	DOUBLE VARCHAR
	, TIMESTAMP
	, DOUBLE PRECISION
	, DOUBLE PRECISION
	, DOUBLE PRECISION
	, DOUBLE PRECISION
)\
DROP FUNCTION guardar_lectura_gps(
	DOUBLE VARCHAR
	, TIMESTAMP
	, DOUBLE PRECISION
	, DOUBLE PRECISION
	, DOUBLE PRECISION
	, DOUBLE PRECISION
)\
DROP FUNCTION distanciaKm(DOUBLE PRECISION, DOUBLE PRECISION, DOUBLE PRECISION, DOUBLE PRECISION)\






