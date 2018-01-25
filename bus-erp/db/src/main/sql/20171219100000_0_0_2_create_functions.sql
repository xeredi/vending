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
	, spd IN DOUBLE PRECISION
) RETURNS void AS $$
DECLARE
BEGIN
	INSERT INTO tbl_lectura_gps_lgps (
		lgps_pk, lgps_plca_pk, lgps_vhcl_pk, lgps_fecha, lgps_lat, lgps_lon, lgps_spd, lgps_dst, lgps_elt
	)
		SELECT
			nextval('seq_app'), vhcl_plca_pk, vhcl_pk
			, fecha, lat, lon, spd
			, (
				CASE
					WHEN lgps_pk IS NULL THEN 0
					ELSE distanciaKm(lgps_lat, lgps_lon, lat, lon)
				END
			)
			, EXTRACT(EPOCH FROM (fecha - COALESCE(lgps_fecha, fecha)))
		FROM
			tbl_vehiculo_vhcl
			left join lateral (
				SELECT * FROM tbl_lectura_gps_lgps
				WHERE lgps_vhcl_pk = vhcl_pk
				ORDER BY lgps_fecha DESC
				LIMIT 1
			) lgps on true
		WHERE
			EXISTS (
				SELECT 1
				FROM tbl_placa_plca
				WHERE
					plca_pk = vhcl_plca_pk
					AND plca_codigo = placa_serial
					AND plca_fecha_fin > fecha
			)
			AND (
				lgps_fecha IS NULL
				OR (
					lgps_fecha < fecha
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






CREATE OR REPLACE FUNCTION guardar_lectura_gps(
	placa_serial IN VARCHAR
	, fecha IN TIMESTAMP
	, lat IN DOUBLE PRECISION
	, lon IN DOUBLE PRECISION
	, spd IN DOUBLE PRECISION
	, margen IN DOUBLE PRECISION
) RETURNS void AS $$
DECLARE
BEGIN
	WITH lgpsAnt AS (
		SELECT vhcl_plca_pk, vhcl_pk, lgps_pk, lgps_fecha, lgps_lat, lgps_lon, lgps_dst
			, (
				CASE
					WHEN lgps_pk IS NULL THEN 0
					ELSE distanciaKm(lgps_lat, lgps_lon, lat, lon)
				END
			) AS dst
			, EXTRACT(EPOCH FROM (fecha - COALESCE(lgps_fecha, fecha))) AS elt
		FROM
			tbl_vehiculo_vhcl
			left join lateral (
				SELECT * FROM tbl_lectura_gps_lgps
				WHERE lgps_vhcl_pk = vhcl_pk
				ORDER BY lgps_fecha DESC
				LIMIT 1
			) lgps on true
		WHERE
			EXISTS (
				SELECT 1
				FROM tbl_placa_plca
				WHERE
					plca_pk = vhcl_plca_pk
					AND plca_codigo = placa_serial
					AND plca_fecha_fin > fecha
			)
			AND (
				lgps_fecha IS NULL
				OR (
					lgps_fecha < fecha
					AND (
						lgps_lat <> lat
						OR lgps_lon <> lon
					)
				)
			)
	)
	INSERT INTO tbl_lectura_gps_lgps (
		lgps_pk, lgps_plca_pk, lgps_vhcl_pk, lgps_fecha, lgps_lat, lgps_lon, lgps_spd, lgps_dst, lgps_elt
	)
	SELECT
		nextval('seq_app'), vhcl_plca_pk, vhcl_pk
		, fecha
		, (CASE
			WHEN lgps_pk IS NULL OR dst > margen THEN lat
			ELSE lgps_lat
		END)
		, (CASE
			WHEN lgps_pk IS NULL OR dst > margen THEN lon
			ELSE lgps_lon
		END)
		, (CASE
			WHEN lgps_pk IS NULL OR dst > margen THEN spd
			ELSE 0
		END) AS lgps_spd
		, (CASE
			WHEN lgps_pk IS NULL OR dst > margen THEN dst
			ELSE 0
		END) AS lgps_dst
		, elt
	FROM lgpsAnt
	;
END;
$$ LANGUAGE plpgsql
\














-- //@UNDO

DROP FUNCTION guardar_lectura_gps(
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
)\
DROP FUNCTION distanciaKm(DOUBLE PRECISION, DOUBLE PRECISION, DOUBLE PRECISION, DOUBLE PRECISION)\











