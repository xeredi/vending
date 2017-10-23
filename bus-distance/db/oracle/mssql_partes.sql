select *
from SerAutocares s
where exists (
	select 1 from  PartesTrabajo p
	where p.codservicio = s.codservicio
		and p.codparte in (
			'17080973'
			, '17081068'
			, '17081111'
			, '17081035'
			, '17081008'
			, '17081036'
			, '17081063'
			, '17080947'
			, '17081022'
			, '17081256'
			, '17081346'
			, '17081902'
			, '17082252'
			, '17082403'
			, '17082447'
			, '17082436'
			, '17082490'
			, '17082395'
		)
)
;

select s.codservicio
/*
	, (
		select codparte from  PartesTrabajo p
		where p.codservicio = s.codservicio
	) as codparte
*/
	, s.codruta, s.numkilometros, s.numkilometrosexentos, s.kilometrosreales
from SerAutocares s
where exists (
	select 1 from  PartesTrabajo p
	where p.codservicio = s.codservicio
		and p.codparte in (
			'17080973'
			, '17081068'
			, '17081111'
			, '17081035'
			, '17081008'
			, '17081036'
			, '17081063'
			, '17080947'
			, '17081022'
			, '17081256'
			, '17081346'
			, '17081902'
			, '17082252'
			, '17082403'
			, '17082447'
			, '17082436'
			, '17082490'
			, '17082395'
		)
)
;



UPDATE aut SET codRuta = '00133'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17080947';
UPDATE aut SET codRuta = '00121'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17080973';
UPDATE aut SET codRuta = '00130'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17081008';
UPDATE aut SET codRuta = '00136'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17081022';
UPDATE aut SET codRuta = '00127'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17081035';
UPDATE aut SET codRuta = '00128'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17081036';
UPDATE aut SET codRuta = '00132'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17081063';
UPDATE aut SET codRuta = '00123'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17081068';
UPDATE aut SET codRuta = '00125'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17081111';
UPDATE aut SET codRuta = '00105'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17081256';
UPDATE aut SET codRuta = '00131'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17081346';
UPDATE aut SET codRuta = '00137'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17081902';
UPDATE aut SET codRuta = '00139'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17082252';
UPDATE aut SET codRuta = '00147'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17082395';
UPDATE aut SET codRuta = '00141'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17082403';
UPDATE aut SET codRuta = '00145'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17082436';
UPDATE aut SET codRuta = '00143'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17082447';
UPDATE aut SET codRuta = '00139'
FROM SerAutocares aut INNER JOIN PartesTrabajo p ON aut.codservicio = p.codServicio AND aut.NumAutocar = p.NumAutocar
WHERE p.codparte = '17082490';






