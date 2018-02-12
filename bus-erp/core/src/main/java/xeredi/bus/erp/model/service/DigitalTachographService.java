package xeredi.bus.erp.model.service;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.mybatis.guice.transactional.Transactional;

import xeredi.bus.erp.model.tachograph.block.CardBlock;
import xeredi.bus.erp.model.tachograph.block.CardBlockFactory;
import xeredi.bus.erp.model.tachograph.block.Fid;

@Transactional(executorType = ExecutorType.REUSE)
public class DigitalTachographService {
	private static final Log LOG = LogFactory.getLog(DigitalTachographService.class);

	public void load(final byte[] data) throws IOException {
		LOG.info("Data length: " + data.length);

		try (final DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));) {
			while (dis.available() > 0) {
				final int fid_value = dis.readUnsignedShort();
				final byte fid_type = dis.readByte();
				final int fid_length = Integer.valueOf(dis.readChar());
				final byte[] fid_data = new byte[fid_length];

				dis.read(fid_data, 0, fid_length);

				final Fid fid = Fid.valueOf(fid_value);

				LOG.info("fid_value: " + String.format("%04X", fid_value & 0xFFFF) + ", fid_type: " + fid_type
						+ ", fid_length: " + fid_length + ", fid: " + fid);

				if (fid_type == 0) {
					final CardBlock cardBlock = CardBlockFactory.getInstance(fid, fid_data);

					LOG.info(ToStringBuilder.reflectionToString(cardBlock));
				}
			}

		}

	}

	public static void main(final String[] args) {
		LOG.info("Start");

		try {
			{
				final String filename = "/home/xeredi/git/raspberry/canbus/examples/tacograph/C_E34885608J000002_E_20171205_1549.TGD";

				LOG.info(filename);

				final File file = new File(filename);
				final byte[] data = new byte[(int) file.length()];

				IOUtils.readFully(new FileInputStream(file), data);

				final DigitalTachographService service = new DigitalTachographService();

				service.load(data);
			}
			{
				final String filename = "/home/xeredi/git/raspberry/canbus/examples/tacograph/V_1927FFN_E_20171201_1025.TGD";

				LOG.info(filename);

				final File file = new File(filename);
				final byte[] data = new byte[(int) file.length()];

				IOUtils.readFully(new FileInputStream(file), data);

				final DigitalTachographService service = new DigitalTachographService();

				// service.load(data);
			}
		} catch (final IOException ex) {
			LOG.error(ex, ex);
		}

		LOG.info("End");
	}
}
