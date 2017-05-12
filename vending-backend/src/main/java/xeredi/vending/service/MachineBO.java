package xeredi.vending.service;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;

import lombok.NonNull;
import xeredi.util.mybatis.SqlMapperLocator;
import xeredi.vending.mapper.MachineMapper;
import xeredi.vending.model.Machine;
import xeredi.vending.model.MachineCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Class MachineBO.
 */
public final class MachineBO {

	/**
	 * Select.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the machine
	 */
	public final Machine select(final @NonNull MachineCriteria criteria) {
		try (final SqlSession session = SqlMapperLocator.getSqlSessionFactory().openSession(ExecutorType.REUSE)) {
			final MachineMapper mchnMapper = session.getMapper(MachineMapper.class);

			return mchnMapper.selectObject(criteria);
		}
	}
}
