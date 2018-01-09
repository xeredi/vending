DO
$$
BEGIN
 FOR i IN 1..200000 LOOP
       perform guardar_lectura_gps2(
	'00000000ecec8745'
	, now()::timestamp
	, random()*6
	, random()*5
	, random()*4
	, random()*3
	, random()*2
);
 END LOOP;
END
$$

SELECT COUNT(1) FROM tbl_lectura_gps_lgps ;

select guardar_lectura_gps ('00000000ecec8745', now()::timestamp - interval '1 day', random()*6, random()*5, random()*4, random()*3);
select guardar_lectura_gps ('00000000ecec8745', now()::timestamp, random()*6, random()*5, random()*4, random()*3);

select guardar_lectura_gps2('00000000ecec8745', now()::timestamp - interval '1 day', random()*6, random()*5, random()*4, random()*3);
select guardar_lectura_gps2('00000000ecec8745', now()::timestamp, random()*6, random()*5, random()*4, random()*3);

select distanciaKm(1, 1, 2, 2);


SELECT nextval('seq_app'), plca_pk, vhcl_pk
				, now()::timestamp, 4.4, 3.3, 2.2, 1.1
				, (
					CASE
						WHEN plca_ultimo_lgps_pk IS NULL THEN 0
						ELSE distanciaKm(lgps_lat, lgps_lon, 4.4, 3.3)
					END
				)
				, EXTRACT(EPOCH FROM (now()::timestamp - COALESCE(lgps_fecha, now()::timestamp)))
			FROM
				tbl_placa_plca
				INNER JOIN tbl_vehiculo_vhcl ON
					vhcl_plca_pk = plca_pk
				LEFT JOIN tbl_lectura_gps_lgps ON
					lgps_pk = plca_ultimo_lgps_pk
			WHERE
				plca_codigo = '00000000ecec8745'
				AND plca_fecha_fin > now()::timestamp
				AND (
					lgps_fecha IS NULL
					OR (
						lgps_fecha <= (now()::timestamp /* - interval '1 day'*/)
						AND (
							lgps_lat <> 4.4
							OR lgps_lon <> 3.3
						)
					)
				)
;
