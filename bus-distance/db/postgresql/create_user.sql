CREATE USER busdistance WITH PASSWORD 'bus';

CREATE DATABASE busdistance WITH OWNER busdistance;

CREATE EXTENSION tds_fdw;
GRANT USAGE ON FOREIGN DATA WRAPPER tds_fdw TO busdistance;


CREATE SERVER erp_mssql
	FOREIGN DATA WRAPPER tds_fdw
	OPTIONS (servername '127.0.0.1', port '1433', database 'Ofibus009', tds_version '7.1');

CREATE USER MAPPING FOR busdistance
	SERVER erp_mssql
	OPTIONS (username 'sa', password 'Licor43licor43');

DROP FOREIGN TABLE PartesTrabajo;
CREATE FOREIGN TABLE PartesTrabajo (
		idParte int
		, CodParte varchar(8)
		, CodServicio varchar(8)
		, NumAutocar varchar(3)
		, IdMovimiento int
		, Kilometros float
		, KilometrosExentos float
	)
	SERVER erp_mssql
	OPTIONS (table_name 'dbo.PartesTrabajo', row_estimate_method 'showplan_all');

		SELECT p.codServicio, p.codParte, p.idParte
			, CONVERT(DATETIME, CONVERT(CHAR(20), inc.DesdeFecha, 112) + ' ' + CONVERT(CHAR(20), inc.DesdeHora,108)) AS fechaDesde
			, CONVERT(DATETIME, CONVERT(CHAR(20), inc.HastaFecha, 112) + ' ' + CONVERT(CHAR(20), inc.HastaHora,108)) AS fechaHasta
			, (SELECT codruta FROM SerAutocares aut
				WHERE aut.codservicio = p.codServicio
					AND aut.NumAutocar = p.NumAutocar) AS codRuta
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
		FROM PartesTrabajo p
			LEFT JOIN IncidenciasMovimientos inc ON
				inc.IdMovimiento = p.IdMovimiento
		WHERE
			len(inc.desdefecha) = 8
			AND len(inc.desdehora) = 5
			AND len(inc.hastafecha) = 8
			AND len(inc.hastahora) = 5
			AND CONVERT(DATETIME, CONVERT(CHAR(20), inc.DesdeFecha, 112) + ' ' + CONVERT(CHAR(20), inc.DesdeHora,108)) >= #{fechaInicio}
;


	ParteN int DEFAULT ((0)) NOT NULL,
	ProvAgregado nvarchar(1) DEFAULT ('N') NOT NULL,
	MatriculaAgr nvarchar(15) DEFAULT (''),
	CodAsistente nvarchar(4),
	HoraPresentacion nvarchar(5) DEFAULT (''),
	LugarInicio nvarchar(2147483647) DEFAULT (''),
	ItinerarioRuta nvarchar(2147483647) DEFAULT (''),
	LugarFin nvarchar(2147483647) DEFAULT (''),
	HorasParte float DEFAULT ((0)),
	NumPersonas float DEFAULT ((0)),
	IdEnlaceGastos int DEFAULT ((0)),
	ParteCerrado nvarchar(1) DEFAULT ('N') NOT NULL,
	ParteFacturado nvarchar(1) DEFAULT ('N') NOT NULL,
	NumFactura nvarchar(11) DEFAULT (''),
	BloqAnulacion nvarchar(1) DEFAULT ('N') NOT NULL,
	UsuarioCierre nvarchar(20) DEFAULT (''),
	FechaCierre nvarchar(8) DEFAULT (''),
	HoraCierre nvarchar(8) DEFAULT (''),
	NumBloque nvarchar(3) DEFAULT (''),
	ImputadaPrevGastos nvarchar(1) DEFAULT ('N') NOT NULL,
	EnviadoEmovilia nvarchar(1) DEFAULT (''),
	ClaveRefParte nvarchar(50) DEFAULT (''),
