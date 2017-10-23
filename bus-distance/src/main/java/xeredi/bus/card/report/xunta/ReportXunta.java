/*
 *
 */
package xeredi.bus.card.report.xunta;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import lombok.NonNull;
import xeredi.bus.card.model.ServicioXunta;
import xeredi.bus.card.model.ServicioXuntaCriteria;
import xeredi.bus.card.model.mapper.ServicioXuntaErpMapper;
import xeredi.bus.card.model.util.mybatis.SqlMapperLocator;

// TODO: Auto-generated Javadoc
/**
 * The Class ReportXunta.
 */
public final class ReportXunta {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(ReportXunta.class);

	private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	private HSSFCellStyle cellStyle;
	private HSSFDataFormat hssfDataFormat;

	/**
	 * Execute.
	 *
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void execute(final OutputStream stream) throws SQLException, IOException {
		final ServicioXuntaCriteria servicioXuntaCriteria = new ServicioXuntaCriteria();

		final Calendar calendar = Calendar.getInstance();

		servicioXuntaCriteria.setFechaFin(calendar.getTime());

		calendar.add(Calendar.MONTH, -6);

		servicioXuntaCriteria.setFechaInicio(calendar.getTime());

		try (final SqlSession erpSession = SqlMapperLocator.getErpSqlSession();
				final HSSFWorkbook workbook = new HSSFWorkbook()) {
			final ServicioXuntaErpMapper servicioXuntaErpMapper = erpSession.getMapper(ServicioXuntaErpMapper.class);

			LOG.info("Servicio");

			final HSSFSheet sheet = workbook.createSheet("reportXunta");

			cellStyle = workbook.createCellStyle();
			hssfDataFormat = workbook.createDataFormat();

			// Cabecera XLS
			int rownum = 0;

			final HSSFRow rowhead = sheet.createRow(rownum++);
			int i = 0;

			setCellValue(rowhead, i++, "AÑO");
			setCellValue(rowhead, i++, "MES");
			setCellValue(rowhead, i++, "CONCESION");
			setCellValue(rowhead, i++, "IDLINEA");
			setCellValue(rowhead, i++, "NOMLINEA");
			setCellValue(rowhead, i++, "IDEXPEDI");
			setCellValue(rowhead, i++, "NOMEXPEDI");
			setCellValue(rowhead, i++, "DIA_MES");
			setCellValue(rowhead, i++, "DIA_SEMANA");
			setCellValue(rowhead, i++, "SALIDATEORICA");
			setCellValue(rowhead, i++, "SALIDAREAL");
			setCellValue(rowhead, i++, "IDORI");
			setCellValue(rowhead, i++, "ORIGEN");
			setCellValue(rowhead, i++, "AYUORI");
			setCellValue(rowhead, i++, "IDDES");
			setCellValue(rowhead, i++, "DESTINO");
			setCellValue(rowhead, i++, "AYUDES");

			setCellValue(rowhead, i++, "KMS");
			setCellValue(rowhead, i++, "Viajeros/Km");
			setCellValue(rowhead, i++, "TOTAL_VIAJEROS");
			setCellValue(rowhead, i++, "VIAJEROS_TARJETA");
			setCellValue(rowhead, i++, "PVP_TARJETA");
			setCellValue(rowhead, i++, "VIAJEROS_EFECTIVO");
			setCellValue(rowhead, i++, "PVP_EFECTIVO");
			setCellValue(rowhead, i++, "NUM_SALTOS");
			setCellValue(rowhead, i++, "PRECIO_COMPENSACION");
			setCellValue(rowhead, i++, "RECAUDACION_TOTAL");

			int servicioCount = 0;

			for (final ServicioXunta servicioXunta : servicioXuntaErpMapper.selectList(servicioXuntaCriteria)) {
				servicioCount++;

				final HSSFRow row = sheet.createRow(rownum++);

				int j = 0;

				final Calendar calendarDesde = Calendar.getInstance();

				calendarDesde.setTime(servicioXunta.getServicio().getFechaDesde());

				setCellValue(row, j++, calendarDesde.get(Calendar.YEAR));
				setCellValue(row, j++, calendarDesde.get(Calendar.MONTH));
				setCellValue(row, j++, "ConcesiónTest");
				setCellValue(row, j++, servicioXunta.getServicio().getRuta().getCodigo());
				setCellValue(row, j++, servicioXunta.getServicio().getRuta().getNombre());
				setCellValue(row, j++, "ExpedienteId");
				setCellValue(row, j++, "ExpedienteNombre");
				setCellValue(row, j++, calendarDesde.get(Calendar.DAY_OF_MONTH));

				switch (calendarDesde.get(Calendar.DAY_OF_WEEK)) {
				case Calendar.MONDAY:
					setCellValue(row, j++, "LUNES");

					break;
				case Calendar.TUESDAY:
					setCellValue(row, j++, "MARTES");

					break;
				case Calendar.WEDNESDAY:
					setCellValue(row, j++, "MIERCOLES");

					break;
				case Calendar.THURSDAY:
					setCellValue(row, j++, "JUEVES");

					break;

				case Calendar.FRIDAY:
					setCellValue(row, j++, "VIERNES");

					break;

				case Calendar.SATURDAY:
					setCellValue(row, j++, "SABADO");

					break;

				case Calendar.SUNDAY:
					setCellValue(row, j++, "DOMINGO");

					break;

				default:
					setCellValue(row, j++, "ERROR. OTRO DIA!!!");

					break;
				}

				setCellValue(row, j++, calendarDesde);
				setCellValue(row, j++, calendarDesde);
				setCellValue(row, j++, "");
				setCellValue(row, j++, servicioXunta.getServicio().getRuta().getLugarInicio());
				setCellValue(row, j++, "");
				setCellValue(row, j++, "");
				setCellValue(row, j++, servicioXunta.getServicio().getRuta().getLugarFin());
				setCellValue(row, j++, "");
				setCellValue(row, j++, servicioXunta.getServicio().getUtilErpKm());
				setCellValue(row, j++,
						servicioXunta.getServicio().getPasajeros() == null
								|| servicioXunta.getServicio().getUtilErpKm() == null ? ""
										: servicioXunta.getServicio().getPasajeros()
												/ servicioXunta.getServicio().getUtilErpKm());
				setCellValue(row, j++, servicioXunta.getServicio().getPasajeros());
				setCellValue(row, j++, "");
				setCellValue(row, j++, "");
				setCellValue(row, j++, servicioXunta.getServicio().getPasajeros());
				setCellValue(row, j++,
						servicioXunta.getServicio().getImporte() == null
								|| servicioXunta.getServicio().getPasajeros() == null ? ""
										: servicioXunta.getServicio().getImporte()
												/ servicioXunta.getServicio().getPasajeros());

				setCellValue(row, j++, "");
				setCellValue(row, j++,
						servicioXunta.getServicio().getImporte() == null
								|| servicioXunta.getServicio().getPasajeros() == null ? ""
										: servicioXunta.getServicio().getImporte()
												/ servicioXunta.getServicio().getPasajeros());
				setCellValue(row, j++, servicioXunta.getServicio().getImporte());
			}

			LOG.info("Processed: " + servicioCount);

			erpSession.commit();

			autoSizeColumns(sheet, rowhead);

			workbook.write(stream);
		}
	}

	protected final void setCellValue(@NonNull final HSSFRow row, final int position, final Object value) {
		if (value != null) {
			if (value instanceof String) {
				row.createCell(position).setCellValue((String) value);
			} else if (value instanceof Date) {
				row.createCell(position).setCellValue(DATETIME_FORMAT.format((Date) value));
			} else if (value instanceof Calendar) {
				row.createCell(position).setCellValue(DATETIME_FORMAT.format(((Calendar) value).getTime()));
			} else if (value instanceof Integer) {
				row.createCell(position).setCellValue((Integer) value);
			} else if (value instanceof Long) {
				row.createCell(position).setCellValue((Long) value);
			} else if (value instanceof Double) {
				cellStyle.setDataFormat(hssfDataFormat.getFormat("#,##0.00"));

				final Cell cell = row.createCell(position);

				cell.setCellStyle(cellStyle);
				cell.setCellValue((Double) value);
			} else if (value instanceof Boolean) {
				row.createCell(position).setCellValue((Boolean) value);
			} else {
				throw new Error("Valor no valido:" + value);
			}
		}
	}

	protected final void autoSizeColumns(@NonNull final HSSFSheet sheet, @NonNull final HSSFRow header) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("XLS Autosize start");
		}

		for (int index = header.getFirstCellNum(); index < header.getLastCellNum(); index++) {
			sheet.autoSizeColumn(index, false);
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("XLS Autosize end");
		}
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		LOG.info("Start process");

		final ReportXunta reportXunta = new ReportXunta();
		final long start = Calendar.getInstance().getTimeInMillis();

		try {
			final OutputStream stream = new FileOutputStream("/home/xeredi/proyectos/deploy/xls/text.xls");

			reportXunta.execute(stream);
		} catch (final SQLException ex) {
			LOG.fatal(ex.getMessage(), ex);
		} catch (final IOException ex) {
			LOG.fatal(ex.getMessage(), ex);
		}

		LOG.info("End Process. Time: " + (Calendar.getInstance().getTimeInMillis() - start));
	}

}
