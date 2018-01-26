-- // 0.0.2 Fnc
CREATE OR REPLACE FUNCTION guardar_ping_placa(
	placa_serial IN VARCHAR
	, fecha IN TIMESTAMP
) RETURNS void AS $$
DECLARE
BEGIN
	INSERT INTO tbl_placa_ping_plpg (plpg_pk, plpg_plca_pk, plpg_vhcl_pk, plpg_fecha, plpg_elt)
	SELECT
		nextval('seq_app'), vhcl_plca_pk, vhcl_pk, fecha
		, EXTRACT(EPOCH FROM (fecha - COALESCE(plpg_fecha, fecha)))
	FROM
		tbl_placa_plca
		INNER JOIN tbl_vehiculo_vhcl ON
			vhcl_plca_pk = plca_pk
		LEFT JOIN LATERAL (
			SELECT * FROM tbl_placa_ping_plpg
			WHERE plpg_plca_pk = plca_pk
			ORDER BY plpg_fecha DESC
			LIMIT 1
		) lgps on true
	WHERE
		plca_codigo = placa_serial
		AND plca_fecha_fin > fecha
		AND (
			plpg_fecha IS NULL
			OR (
				plpg_fecha < fecha
			)
		)
	;
END;
$$ LANGUAGE plpgsql
\












-- //@UNDO

DROP FUNCTION guardar_ping_placa(
	VARCHAR
	, TIMESTAMP
);
\











