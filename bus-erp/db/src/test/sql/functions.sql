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