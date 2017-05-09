package xeredi.vending.service;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;

import lombok.NonNull;
import xeredi.util.mybatis.SqlMapperLocator;
import xeredi.vending.json.Telemetry;
import xeredi.vending.mapper.SequenceMapper;
import xeredi.vending.mapper.TelemetryMapper;

// TODO: Auto-generated Javadoc
/**
 * The Class TelemetryBO.
 */
public final class TelemetryBO {

	/**
	 * Insert.
	 *
	 * @param telemetry
	 *            the telemetry
	 */
	public void insert(@NonNull final Telemetry telemetry) {
		try (final SqlSession session = SqlMapperLocator.getSqlSessionFactory().openSession(ExecutorType.REUSE)) {
			final SequenceMapper sequMapper = session.getMapper(SequenceMapper.class);
			final TelemetryMapper tlmyMapper = session.getMapper(TelemetryMapper.class);

			telemetry.setId(sequMapper.selectNextval());
			tlmyMapper.insert(telemetry);

			session.commit();
		}
	}

	/**
	 * Update.
	 *
	 * @param telemetry
	 *            the telemetry
	 */
	public void update(@NonNull final Telemetry telemetry) {
		try (final SqlSession session = SqlMapperLocator.getSqlSessionFactory().openSession(ExecutorType.REUSE)) {
			final TelemetryMapper tlmyMapper = session.getMapper(TelemetryMapper.class);

			tlmyMapper.update(telemetry);

			session.commit();
		}
	}
}
