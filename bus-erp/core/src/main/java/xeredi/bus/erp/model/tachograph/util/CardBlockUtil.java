package xeredi.bus.erp.model.tachograph.util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class CardBlockUtil.
 */
public final class CardBlockUtil {

	/**
	 * Instantiates a new card block util.
	 */
	private CardBlockUtil() {
		super();
	}

	/**
	 * Gets the string.
	 *
	 * @param data
	 *            the data
	 * @param offset
	 *            the offset
	 * @param size
	 *            the size
	 * @return the string
	 */
	public static String getString(final byte[] data, final int offset, final int size) {
		return new String(Arrays.copyOfRange(data, offset, offset + size)).trim();
	}

	/**
	 * Gets the text.
	 *
	 * @param data
	 *            the data
	 * @param offset
	 *            the offset
	 * @param size
	 *            the size
	 * @return the text
	 */
	public static String getText(final byte[] data, final int offset, final int size) {
		return new String(Arrays.copyOfRange(data, offset + 1, offset + size)).trim();
	}

	/**
	 * Gets the short.
	 *
	 * @param data
	 *            the data
	 * @param offset
	 *            the offset
	 * @param size
	 *            the size
	 * @return the short
	 */
	public static Short getShort(final byte[] data, final int offset, final int size) {
		final byte[] array = Arrays.copyOfRange(data, offset, offset + size);

		Integer numberValue = 0;

		for (byte byteValue : array) {
			numberValue = (numberValue * 256) + byteValue;
		}

		return new Short(numberValue.toString());
	}

	/**
	 * Gets the integer.
	 *
	 * @param data
	 *            the data
	 * @param offset
	 *            the offset
	 * @param size
	 *            the size
	 * @return the integer
	 */
	public static Integer getInteger(final byte[] data, final int offset, final int size) {
		final byte[] array = Arrays.copyOfRange(data, offset, offset + size);

		Integer numberValue = 0;

		for (byte byteValue : array) {
			numberValue = (numberValue * 256) + byteValue;
		}

		return numberValue;
	}

	/**
	 * Gets the date.
	 *
	 * @param data
	 *            the data
	 * @param offset
	 *            the offset
	 * @param size
	 *            the size
	 * @return the date
	 */
	public static Date getDate(final byte[] data, final int offset, final int size) {
		final byte[] array = Arrays.copyOfRange(data, offset, offset + size);

		Long numberValue = 0L;

		for (byte byteValue : array) {
			numberValue = (numberValue * 256) + byteValue;
		}

		final Calendar calendar = Calendar.getInstance();

		calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		calendar.setTimeInMillis(numberValue * 1000);

		return new Date(numberValue * 1000);
	}

	public static String getBinaryString(final byte[] data, final int offset, final int size) {
		final byte[] array = Arrays.copyOfRange(data, offset, offset + size);

		String value = "";

		for (byte byteValue : array) {
			value += String.format("%8s", Integer.toBinaryString(byteValue & 0xFF)).replace(' ', '0');
		}

		return value;
	}
}
