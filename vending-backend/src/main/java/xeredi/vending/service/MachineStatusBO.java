package xeredi.vending.service;

import java.util.Calendar;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;

import lombok.NonNull;
import xeredi.util.mybatis.SqlMapperLocator;
import xeredi.vending.json.Telemetry;
import xeredi.vending.mapper.MachineActivityMapper;
import xeredi.vending.mapper.MachineStatusMapper;
import xeredi.vending.mapper.SequenceMapper;
import xeredi.vending.mapper.TelemetryReaderMapper;
import xeredi.vending.model.MachineActivity;
import xeredi.vending.model.MachineActivityCriteria;
import xeredi.vending.model.MachineCriteria;
import xeredi.vending.model.MachineStatus;
import xeredi.vending.model.MachineStatusCriteria;
import xeredi.vending.model.TelemetryReader;
import xeredi.vending.model.TelemetryReaderCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Class MachineCashBO.
 */
public final class MachineStatusBO {
	/**
	 * Merge.
	 *
	 * @param mcst
	 *            the mcst
	 */
	public void merge(@NonNull final Telemetry tlmy) {
		final MachineStatus mcst = new MachineStatus();

		mcst.setLastUpdate(tlmy.getFechaLectura());
		mcst.setEnt0010(ObjectUtils.firstNonNull(tlmy.getEnt0010(), 0L));
		mcst.setEnt0020(ObjectUtils.firstNonNull(tlmy.getEnt0020(), 0L));
		mcst.setEnt0050(ObjectUtils.firstNonNull(tlmy.getEnt0050(), 0L));
		mcst.setEnt0100(ObjectUtils.firstNonNull(tlmy.getEnt0100(), 0L));
		mcst.setEnt0200(ObjectUtils.firstNonNull(tlmy.getEnt0200(), 0L));
		mcst.setEnt0500(ObjectUtils.firstNonNull(tlmy.getEnt0500(), 0L));
		mcst.setEnt1000(ObjectUtils.firstNonNull(tlmy.getEnt1000(), 0L));
		mcst.setEnt2000(ObjectUtils.firstNonNull(tlmy.getEnt2000(), 0L));
		mcst.setEnt5000(ObjectUtils.firstNonNull(tlmy.getEnt5000(), 0L));
		mcst.setHop0010(ObjectUtils.firstNonNull(tlmy.getHop0010(), 0L));
		mcst.setHop0020(ObjectUtils.firstNonNull(tlmy.getHop0020(), 0L));
		mcst.setHop0050(ObjectUtils.firstNonNull(tlmy.getHop0050(), 0L));
		mcst.setHop0100(ObjectUtils.firstNonNull(tlmy.getHop0100(), 0L));
		mcst.setHop0200(ObjectUtils.firstNonNull(tlmy.getHop0200(), 0L));
		mcst.setHop0500(ObjectUtils.firstNonNull(tlmy.getHop0500(), 0L));
		mcst.setHop1000(ObjectUtils.firstNonNull(tlmy.getHop1000(), 0L));
		mcst.setHop2000(ObjectUtils.firstNonNull(tlmy.getHop2000(), 0L));
		mcst.setHop5000(ObjectUtils.firstNonNull(tlmy.getHop5000(), 0L));
		mcst.setSal0010(ObjectUtils.firstNonNull(tlmy.getSal0010(), 0L));
		mcst.setSal0020(ObjectUtils.firstNonNull(tlmy.getSal0020(), 0L));
		mcst.setSal0050(ObjectUtils.firstNonNull(tlmy.getSal0050(), 0L));
		mcst.setSal0100(ObjectUtils.firstNonNull(tlmy.getSal0100(), 0L));
		mcst.setSal0200(ObjectUtils.firstNonNull(tlmy.getSal0200(), 0L));
		mcst.setSal0500(ObjectUtils.firstNonNull(tlmy.getSal0500(), 0L));
		mcst.setSal1000(ObjectUtils.firstNonNull(tlmy.getSal1000(), 0L));
		mcst.setSal2000(ObjectUtils.firstNonNull(tlmy.getSal2000(), 0L));
		mcst.setSal5000(ObjectUtils.firstNonNull(tlmy.getSal5000(), 0L));

		final Calendar calendarNextUpdate = Calendar.getInstance();

		calendarNextUpdate.setTime(mcst.getLastUpdate());
		calendarNextUpdate.add(Calendar.MINUTE, 5);

		mcst.setNextUpdate(calendarNextUpdate.getTime());

		try (final SqlSession session = SqlMapperLocator.getSqlSessionFactory().openSession(ExecutorType.REUSE)) {
			final SequenceMapper sequMapper = session.getMapper(SequenceMapper.class);

			// Find TelemetryReader
			final TelemetryReaderMapper tlrdMapper = session.getMapper(TelemetryReaderMapper.class);
			final TelemetryReaderCriteria tlrdCriteria = new TelemetryReaderCriteria();

			tlrdCriteria.setCode(tlmy.getReaderCode());

			@NonNull
			final TelemetryReader tlrd = tlrdMapper.selectObject(tlrdCriteria);

			// Manage Status
			final MachineStatusMapper mcstMapper = session.getMapper(MachineStatusMapper.class);
			final MachineStatusCriteria mcstCriteria = new MachineStatusCriteria();

			mcstCriteria.setMachine(new MachineCriteria());
			mcstCriteria.getMachine().setId(tlrd.getMachine().getId());

			final MachineStatus existingMcst = mcstMapper.selectObject(mcstCriteria);

			if (existingMcst == null) {
				mcst.setId(sequMapper.selectNextval());
				mcst.setMachine(tlrd.getMachine());

				mcstMapper.insert(mcst);
			} else {
				if (existingMcst.getLastUpdate().after(mcst.getLastUpdate())) {
					throw new Error("Too Old Status!!!");
				}

				mcst.setId(existingMcst.getId());
				mcst.setMachine(existingMcst.getMachine());

				mcstMapper.update(mcst);
			}

			// Manage Activity
			final MachineActivityMapper mcacMapper = session.getMapper(MachineActivityMapper.class);
			final MachineActivityCriteria mcacCriteria = new MachineActivityCriteria();
			final Calendar calendar = Calendar.getInstance();

			calendar.setTime(mcst.getLastUpdate());

			mcacCriteria.setHour((long) calendar.get(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			mcacCriteria.setDate(calendar.getTime());
			mcacCriteria.setMachine(new MachineCriteria());
			mcacCriteria.getMachine().setId(tlrd.getMachine().getId());

			final MachineActivity existingMcac = mcacMapper.selectObject(mcacCriteria);

			if (existingMcac == null) {
				final MachineActivity mcac = new MachineActivity();

				mcac.setId(sequMapper.selectNextval());
				mcac.setMachine(tlrd.getMachine());
				mcac.setDate(mcacCriteria.getDate());
				mcac.setHour(mcacCriteria.getHour());

				if (existingMcst == null) {
					mcac.setHop0010(mcst.getHop0010());
					mcac.setHop0020(mcst.getHop0020());
					mcac.setHop0050(mcst.getHop0050());
					mcac.setHop0100(mcst.getHop0100());
					mcac.setHop0200(mcst.getHop0200());
					mcac.setHop0500(mcst.getHop0500());
					mcac.setHop1000(mcst.getHop1000());
					mcac.setHop2000(mcst.getHop2000());
					mcac.setHop5000(mcst.getHop5000());
					mcac.setEnt0010(mcst.getEnt0010());
					mcac.setEnt0020(mcst.getEnt0020());
					mcac.setEnt0050(mcst.getEnt0050());
					mcac.setEnt0100(mcst.getEnt0100());
					mcac.setEnt0200(mcst.getEnt0200());
					mcac.setEnt0500(mcst.getEnt0500());
					mcac.setEnt1000(mcst.getEnt1000());
					mcac.setEnt2000(mcst.getEnt2000());
					mcac.setEnt5000(mcst.getEnt5000());
					mcac.setSal0010(mcst.getSal0010());
					mcac.setSal0020(mcst.getSal0020());
					mcac.setSal0050(mcst.getSal0050());
					mcac.setSal0100(mcst.getSal0100());
					mcac.setSal0200(mcst.getSal0200());
					mcac.setSal0500(mcst.getSal0500());
					mcac.setSal1000(mcst.getSal1000());
					mcac.setSal2000(mcst.getSal2000());
					mcac.setSal5000(mcst.getSal5000());
				} else {
					mcac.setHop0010(mcst.getHop0010() - existingMcst.getHop0010());
					mcac.setHop0020(mcst.getHop0020() - existingMcst.getHop0020());
					mcac.setHop0050(mcst.getHop0050() - existingMcst.getHop0050());
					mcac.setHop0100(mcst.getHop0100() - existingMcst.getHop0100());
					mcac.setHop0200(mcst.getHop0200() - existingMcst.getHop0200());
					mcac.setHop0500(mcst.getHop0500() - existingMcst.getHop0500());
					mcac.setHop1000(mcst.getHop1000() - existingMcst.getHop1000());
					mcac.setHop2000(mcst.getHop2000() - existingMcst.getHop2000());
					mcac.setHop5000(mcst.getHop5000() - existingMcst.getHop5000());
					mcac.setEnt0010(mcst.getEnt0010() - existingMcst.getEnt0010());
					mcac.setEnt0020(mcst.getEnt0020() - existingMcst.getEnt0020());
					mcac.setEnt0050(mcst.getEnt0050() - existingMcst.getEnt0050());
					mcac.setEnt0100(mcst.getEnt0100() - existingMcst.getEnt0100());
					mcac.setEnt0200(mcst.getEnt0200() - existingMcst.getEnt0200());
					mcac.setEnt0500(mcst.getEnt0500() - existingMcst.getEnt0500());
					mcac.setEnt1000(mcst.getEnt1000() - existingMcst.getEnt1000());
					mcac.setEnt2000(mcst.getEnt2000() - existingMcst.getEnt2000());
					mcac.setEnt5000(mcst.getEnt5000() - existingMcst.getEnt5000());
					mcac.setSal0010(mcst.getSal0010() - existingMcst.getSal0010());
					mcac.setSal0020(mcst.getSal0020() - existingMcst.getSal0020());
					mcac.setSal0050(mcst.getSal0050() - existingMcst.getSal0050());
					mcac.setSal0100(mcst.getSal0100() - existingMcst.getSal0100());
					mcac.setSal0200(mcst.getSal0200() - existingMcst.getSal0200());
					mcac.setSal0500(mcst.getSal0500() - existingMcst.getSal0500());
					mcac.setSal1000(mcst.getSal1000() - existingMcst.getSal1000());
					mcac.setSal2000(mcst.getSal2000() - existingMcst.getSal2000());
					mcac.setSal5000(mcst.getSal5000() - existingMcst.getSal5000());
				}

				mcacMapper.insert(mcac);
			} else {
				existingMcac.setHop0010(existingMcac.getHop0010() + mcst.getHop0010() - existingMcst.getHop0010());
				existingMcac.setHop0020(existingMcac.getHop0020() + mcst.getHop0020() - existingMcst.getHop0020());
				existingMcac.setHop0050(existingMcac.getHop0050() + mcst.getHop0050() - existingMcst.getHop0050());
				existingMcac.setHop0100(existingMcac.getHop0100() + mcst.getHop0100() - existingMcst.getHop0100());
				existingMcac.setHop0200(existingMcac.getHop0200() + mcst.getHop0200() - existingMcst.getHop0200());
				existingMcac.setHop0500(existingMcac.getHop0500() + mcst.getHop0500() - existingMcst.getHop0500());
				existingMcac.setHop1000(existingMcac.getHop1000() + mcst.getHop1000() - existingMcst.getHop1000());
				existingMcac.setHop2000(existingMcac.getHop2000() + mcst.getHop2000() - existingMcst.getHop2000());
				existingMcac.setHop5000(existingMcac.getHop5000() + mcst.getHop5000() - existingMcst.getHop5000());
				existingMcac.setEnt0010(existingMcac.getEnt0010() + mcst.getEnt0010() - existingMcst.getEnt0010());
				existingMcac.setEnt0020(existingMcac.getEnt0020() + mcst.getEnt0020() - existingMcst.getEnt0020());
				existingMcac.setEnt0050(existingMcac.getEnt0050() + mcst.getEnt0050() - existingMcst.getEnt0050());
				existingMcac.setEnt0100(existingMcac.getEnt0100() + mcst.getEnt0100() - existingMcst.getEnt0100());
				existingMcac.setEnt0200(existingMcac.getEnt0200() + mcst.getEnt0200() - existingMcst.getEnt0200());
				existingMcac.setEnt0500(existingMcac.getEnt0500() + mcst.getEnt0500() - existingMcst.getEnt0500());
				existingMcac.setEnt1000(existingMcac.getEnt1000() + mcst.getEnt1000() - existingMcst.getEnt1000());
				existingMcac.setEnt2000(existingMcac.getEnt2000() + mcst.getEnt2000() - existingMcst.getEnt2000());
				existingMcac.setEnt5000(existingMcac.getEnt5000() + mcst.getEnt5000() - existingMcst.getEnt5000());
				existingMcac.setSal0010(existingMcac.getSal0010() + mcst.getSal0010() - existingMcst.getSal0010());
				existingMcac.setSal0020(existingMcac.getSal0020() + mcst.getSal0020() - existingMcst.getSal0020());
				existingMcac.setSal0050(existingMcac.getSal0050() + mcst.getSal0050() - existingMcst.getSal0050());
				existingMcac.setSal0100(existingMcac.getSal0100() + mcst.getSal0100() - existingMcst.getSal0100());
				existingMcac.setSal0200(existingMcac.getSal0200() + mcst.getSal0200() - existingMcst.getSal0200());
				existingMcac.setSal0500(existingMcac.getSal0500() + mcst.getSal0500() - existingMcst.getSal0500());
				existingMcac.setSal1000(existingMcac.getSal1000() + mcst.getSal1000() - existingMcst.getSal1000());
				existingMcac.setSal2000(existingMcac.getSal2000() + mcst.getSal2000() - existingMcst.getSal2000());
				existingMcac.setSal5000(existingMcac.getSal5000() + mcst.getSal5000() - existingMcst.getSal5000());

				mcacMapper.update(existingMcac);
			}

			session.commit();
		}
	}
}
