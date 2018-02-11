package xeredi.bus.erp.model.tachograph.block;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

// TODO: Auto-generated Javadoc
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Data
@ToString
public abstract class CardBlock {

	/** The fid. */
	private final Fid fid;

	/** The data. */
	private final transient byte[] data;

	/**
	 * Instantiates a new card block.
	 *
	 * @param afid
	 *            the afid
	 * @param adata
	 *            the adata
	 */
	public CardBlock(final @NonNull Fid afid, final @NonNull byte[] adata) {
		super();
		this.fid = afid;
		this.data = adata;

		// System.out.println(new String(this.data));

//		for (int i = 0; i < this.data.length; i++) {
//			System.out.println("data[i] pre : " + data[i]);
//
//			final String string = Integer.toString((this.data[i] & 0xff) + 0x100, 16);
//
//			System.out.println("string      : " + string);
//
//			this.data[i] = (byte) string.charAt(1);
//
//			System.out.println("data[i] post: " + data[i]);
//		}
//
//		System.out.println(new String(this.data));
	}
}
