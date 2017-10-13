SELECT s.*
    , (
        SELECT lgps_pk
        FROM tbl_lectura_gps_lgps
        WHERE 
            1 = 1
            AND lgps_vhcl_pk = srvc_vhcl_pk
            AND srvc_fecha_desde BETWEEN (lgps_fecha - 0.04) AND (lgps_fecha + 0.04)
            AND distanciaKm(lgps_latitude, lgps_longitude, ruta_orig_lat, ruta_orig_lon) < 0.05
        ORDER BY distanciaKm(lgps_latitude, lgps_longitude, ruta_orig_lat, ruta_orig_lon)
        FETCH NEXT 1 ROWS ONLY
    ) AS srvc_lgps_orig_pk
    , (
        SELECT lgps_pk
        FROM tbl_lectura_gps_lgps
        WHERE 
            1 = 1
            AND lgps_vhcl_pk = srvc_vhcl_pk
            AND srvc_fecha_hasta BETWEEN (lgps_fecha - 0.04) AND (lgps_fecha + 0.04)
            AND distanciaKm(lgps_latitude, lgps_longitude, ruta_dest_lat, ruta_dest_lon) < 0.05
        ORDER BY distanciaKm(lgps_latitude, lgps_longitude, ruta_dest_lat, ruta_dest_lon)
        FETCH NEXT 1 ROWS ONLY
    ) AS srvc_lgps_dest_pk
FROM tbl_servicio_srvc s
    INNER JOIN tbl_ruta_ruta r ON
        ruta_pk = srvc_ruta_pk
WHERE 
    1 = 1
--    AND srvc_util_km IS NULL
    -- AND srvc_vacio_km IS NULL
    AND srvc_ruta_pk IS NOT NULL
    AND srvc_vhcl_pk IS NOT NULL
    AND srvc_fecha_desde >= (
        SELECT MIN(lgps_fecha) - 1
        FROM tbl_lectura_gps_lgps
    )
ORDER BY srvc_vhcl_pk, srvc_fecha_desde
;








SELECT * FROM tbl_servicio_srvc WHERE srvc_codigo_parte = '17081256'
;






SELECT s.*, r.*
FROM tbl_servicio_srvc s
    left join tbl_ruta_ruta r on ruta_pk = srvc_ruta_pk
WHERE
    -- srvc_fecha_desde >= TO_DATE('20170514', 'YYYYMMDD') AND srvc_fecha_desde <= TO_DATE('20170606', 'YYYYMMDD') 
    srvc_fecha_desde BETWEEN TO_DATE('20170514', 'YYYYMMDD') AND TO_DATE('20170606', 'YYYYMMDD') 
    AND srvc_fecha_hasta BETWEEN TO_DATE('20170514', 'YYYYMMDD') AND TO_DATE('20170606', 'YYYYMMDD')
    AND EXISTS (
        SELECT 1 FROM tbl_vehiculo_vhcl
        WHERE
            vhcl_pk = srvc_vhcl_pk
            AND vhcl_codigo = '0125'
    )
;


SELECT MIN(lgps_fecha), MAX(lgps_fecha) FROM tbl_lectura_gps_lgps;
SELECT * FROM tbl_lectura_gps_lgps;


UPDATE tbl_servicio_srvc SET
    srvc_lgps_orig_pk = 1
    , srvc_lgps_dest_pk = 1
    , srvc_util_km = (
        SELECT SUM(lgps_distance) / 1000
        FROM tbl_lectura_gps_lgps
        WHERE lgps_vhcl_pk = srvc_vhcl_pk
            AND lgps_pk BETWEEN 1 AND 1
    )
WHERE srvc_pk = 1;



SELECT * 
FROM
    tbl_servicio_srvc s
WHERE 
    srvc_util_km IS NOT NULL
    OR srvc_lgps_orig_pk IS NOT NULL
;


select *
from tbl_servicio_srvc
WHERE srvc_fecha_desde >= TO_DATE('20170519', 'YYYYMMDD')
    and srvc_vhcl_pk = 2000
    -- and srvc_ruta_pk = 4001
order by srvc_fecha_desde;

select * from tbl_lectura_gps_lgps 
WHERE 
    lgps_fecha >= TO_DATE('20170530 07', 'YYYYMMDD HH24')
--    AND lgps_latitude = 42.114438333
/*
*/
ORDER BY lgps_fecha
;