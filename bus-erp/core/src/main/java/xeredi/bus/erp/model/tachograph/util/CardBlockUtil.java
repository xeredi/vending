package xeredi.bus.erp.model.tachograph.util;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;

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
	 * Gets the byte array.
	 *
	 * @param data
	 *            the data
	 * @param offset
	 *            the offset
	 * @param size
	 *            the size
	 * @return the byte array
	 */
	public static byte[] getByteArray(final byte[] data, final int offset, final int size) {
		return Arrays.copyOfRange(data, offset, offset + size);
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
	 * Gets the string IA 5.
	 *
	 * @param data
	 *            the data
	 * @param offset
	 *            the offset
	 * @param size
	 *            the size
	 * @return the string IA 5
	 */
	public static String getStringIA5(final byte[] data, final int offset, final int size) {
		String value = "";

		for (byte byteValue : Arrays.copyOfRange(data, offset, offset + size)) {
			value += (char) (byteValue & 0xFF) ;
		}

		return value.trim();
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
		Integer numberValue = 0;

		for (byte byteValue : Arrays.copyOfRange(data, offset, offset + size)) {
			numberValue = (numberValue * 256) + byteValue;
		}

		return new Short(numberValue.toString());
	}

	/**
	 * Gets the short 16.
	 *
	 * @param data
	 *            the data
	 * @param offset
	 *            the offset
	 * @param size
	 *            the size
	 * @return the short 16
	 */
	public static Short getShort16(final byte[] data, final int offset, final int size) {
		return (short) ((data[offset] << 8) & 0xff00 | (data[offset + 1] << 0) & 0x00ff);
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
		Integer numberValue = 0;

		for (byte byteValue : Arrays.copyOfRange(data, offset, offset + size)) {
			numberValue = (numberValue * 256) + byteValue;
		}

		return numberValue;
	}

	/**
	 * Gets the integer 24.
	 *
	 * @param data
	 *            the data
	 * @param offset
	 *            the offset
	 * @param size
	 *            the size
	 * @return the integer 24
	 */
	public static Integer getInteger24(final byte[] data, final int offset, final int size) {
		return (data[offset] << 16) & 0xff0000 | (data[offset + 1] << 8) & 0x00ff00 | (data[offset + 2] << 0) & 0x0000ff;
	}

	/**
	 * Cada byte son 2 digitos.
	 *
	 * @param data
	 *            the data
	 * @param offset
	 *            the offset
	 * @param size
	 *            the size
	 * @return the integer BCD
	 */
	public static Integer getIntegerBCD(final byte[] data, final int offset, final int size) {
		// Cada byte son 2 digitos (4 bits + 4 bits)
		Integer value = 0;

		for (final byte byteValue : Arrays.copyOfRange(data, offset, offset + size)) {
			value = value * 100 + (((byteValue & 0xf0) >>> 4) & 0x0f) * 10 + (byteValue & 0x0f);
		}

		return value;
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

		// Long numberValue = 0L;
		//
		// for (byte byteValue : array) {
		// numberValue = (numberValue * 256) + byteValue;
		// }
		//
		// final Calendar calendar = Calendar.getInstance();
		//
		// calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		// calendar.setTimeInMillis(numberValue * 1000);

		// return new Date(numberValue * 1000);

		return new Date((long) ByteBuffer.wrap(array).getInt() * 1000);
	}

	/**
	 * Gets the binary string.
	 *
	 * @param data
	 *            the data
	 * @param offset
	 *            the offset
	 * @param size
	 *            the size
	 * @return the binary string
	 */
	public static String getBinaryString(final byte[] data, final int offset, final int size) {
		String value = "";

		for (byte byteValue : Arrays.copyOfRange(data, offset, offset + size)) {
			value += String.format("%8s", Integer.toBinaryString(byteValue & 0xFF)).replace(' ', '0');
		}

		return value;
	}
}
