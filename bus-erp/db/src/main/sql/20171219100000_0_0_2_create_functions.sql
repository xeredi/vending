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
	INSERT INTO tbl_lectura_gps_lgps (
		lgps_pk, lgps_plca_pk, lgps_vhcl_pk, lgps_fecha, lgps_lat, lgps_lon, lgps_alt, lgps_spd, lgps_dst, lgps_elt
	)
		SELECT
			nextval('seq_app'), vhcl_plca_pk, vhcl_pk
			, fecha, lat, lon, alt, spd
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


















-- //@UNDO

DROP FUNCTION guardar_lectura_gps(
	DOUBLE VARCHAR
	, TIMESTAMP
	, DOUBLE PRECISION
	, DOUBLE PRECISION
	, DOUBLE PRECISION
	, DOUBLE PRECISION
)\
DROP FUNCTION distanciaKm(DOUBLE PRECISION, DOUBLE PRECISION, DOUBLE PRECISION, DOUBLE PRECISION)\






INSERT INTO tbl_cliente_clte (clte_pk, clte_email, clte_nombre)
SELECT nextval('seq_app'), CONCAT('plca_', serie.id, '@gmail.com'), CONCAT('Nombre ', serie.id)
FROM generate_series(1, 100)  AS serie(id)\

INSERT INTO tbl_usuario_usro (usro_pk, usro_clte_pk, usro_rol, usro_email, usro_contrasenia, usro_nombre) VALUES (1000, NULL, 'M', 'xeredi@gmail.com', 'changeme', 'Xesus')\


INSERT INTO tbl_usuario_usro (usro_pk, usro_clte_pk, usro_rol, usro_email, usro_contrasenia, usro_nombre)
SELECT nextval('seq_app'), clte_pk, 'C', clte_email, 'changeMe', CONCAT('Nombre ', currval('seq_app'))
FROM tbl_cliente_clte\
INSERT INTO tbl_usuario_usro (usro_pk, usro_clte_pk, usro_rol, usro_email, usro_contrasenia, usro_nombre)
SELECT nextval('seq_app'), clte_pk, 'D', CONCAT(currval('seq_app'), '@gmail.com'), 'changeMe', CONCAT('Nombre ', currval('seq_app'))
FROM tbl_cliente_clte, generate_series(1, 5)  AS serie(id)\

INSERT INTO tbl_placa_plca (plca_pk, plca_clte_pk, plca_codigo, plca_fecha_fin)
SELECT nextval('seq_app'), clte_pk, CONCAT('plca_', currval('seq_app')), '2050-01-01 00:00:00'
FROM tbl_cliente_clte, generate_series(1, 5)  AS serie(id)
\
UPDATE tbl_placa_plca SET plca_codigo = '00000000ecec8745'
WHERE plca_pk = currval('seq_app')
\

INSERT INTO tbl_vehiculo_vhcl (vhcl_pk, vhcl_clte_pk, vhcl_matricula, vhcl_plca_pk)
SELECT nextval('seq_app'), plca_clte_pk, CONCAT('mat_', currval('seq_app')), plca_pk
FROM tbl_placa_plca
\
