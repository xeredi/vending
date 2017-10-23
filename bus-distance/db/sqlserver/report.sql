select * from Servicios;


		SELECT p.codServicio, p.codParte, p.idParte
			, CONVERT(DATETIME, CONVERT(CHAR(20), inc.DesdeFecha, 112) + ' ' + CONVERT(CHAR(20), inc.DesdeHora,108)) AS fechaDesde
			, CONVERT(DATETIME, CONVERT(CHAR(20), inc.HastaFecha, 112) + ' ' + CONVERT(CHAR(20), inc.HastaHora,108)) AS fechaHasta
			, aut.codruta AS codRuta
			, rut.descripcion AS descripcionRuta
			, rut.horaInicio AS horaInicio
			, rut.horaFin AS horaFin
			, rut.lugarInicio AS lugarInicio
			, rut.lugarFin AS lugarFin
			, (SELECT codEnlace FROM IncidenciasMovimientosLin lin
				WHERE lin.IdMovimiento = p.IdMovimiento
					AND AUTOCARCONDUCTOR = 'A') AS codAutocar
			, (SELECT codEnlace FROM IncidenciasMovimientosLin lin
				WHERE lin.IdMovimiento = p.IdMovimiento and AUTOCARCONDUCTOR = 'C'
					AND conductor1o2 = 1) AS codConductor1
			, (SELECT codEnlace FROM IncidenciasMovimientosLin lin
				WHERE lin.IdMovimiento = p.IdMovimiento and AUTOCARCONDUCTOR = 'C'
					AND conductor1o2 = 2) AS codConductor2
			, p.kilometros
			, p.kilometrosExentos
			, p.numPersonas AS pasajeros
			, (SELECT importeFacturado FROM PartesTrabajo_Cierre cie
				WHERE cie.codParte = p.codParte) AS importe
		FROM PartesTrabajo p
			LEFT JOIN IncidenciasMovimientos inc ON
				inc.IdMovimiento = p.IdMovimiento
			LEFT JOIN SerAutocares aut ON
				aut.codservicio = p.codServicio
				AND aut.NumAutocar = p.NumAutocar
			LEFT JOIN Rutas rut ON
				rut.codruta = aut.codruta
		WHERE
			len(inc.desdefecha) = 8
			AND len(inc.desdehora) = 5
			AND len(inc.hastafecha) = 8
			AND len(inc.hastahora) = 5
--			AND CONVERT(DATETIME, CONVERT(CHAR(20), inc.DesdeFecha, 112) + ' ' + CONVERT(CHAR(20), inc.DesdeHora,108)) >= #{fechaInicio}
--			AND CONVERT(DATETIME, CONVERT(CHAR(20), inc.DesdeFecha, 112) + ' ' + CONVERT(CHAR(20), inc.DesdeHora,108)) < #{fechaFin}

			AND EXISTS (
				SELECT 1 FROM SerAutocares aut
				WHERE aut.codservicio = p.codServicio
					AND aut.NumAutocar = p.NumAutocar
					AND aut.codruta IS NOT NULL
					AND aut.codruta <> ''
			)
		ORDER BY fechaDesde
;

SELECT * FROM PartesTrabajo;
SELECT * FROM PartesTrabajo_Cierre;


