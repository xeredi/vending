package xeredi.bus.erp.model.tachograph.util;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class CardBlockUtilTest.
 */
public final class CardBlockUtilTest {

	/**
	 * Test.
	 */
	@Test
	public void test() {
		System.out.println(CardBlockUtil.getStringIA5(new byte[] {-85, 8, 22, -1, 56, 101, 57, 95}, 0, 8));
	}

}
