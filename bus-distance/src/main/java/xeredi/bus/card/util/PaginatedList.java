package xeredi.bus.card.util;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class PaginatedList.
 * 
 * @param <E>
 *            the element type
 */
public final class PaginatedList<E> {

	/** The Constant FIRST_PAGE. */
	public static final int FIRST_PAGE = 1;

	/** The Constant MIN_OFFSET. */
	public static final int MIN_OFFSET = 0;

	/** The list. */
	private final transient List<E> list;

	/** The offset. */
	private final transient int offset;

	/** The limit. */
	private final transient int limit;

	/** The total count. */
	private final transient int count;

	/** The criterio. */
	private final transient Object criterio;

	/**
	 * Instantiates a new paginated list.
	 * 
	 * @param alist
	 *            the alist
	 * @param aoffset
	 *            the aoffset
	 * @param alimit
	 *            the alimit
	 * @param acount
	 *            the acount
	 */
	public PaginatedList(final List<E> alist, final int aoffset, final int alimit, final int acount) {
		this(alist, aoffset, alimit, acount, null);
	}

	/**
	 * Instantiates a new paginated list.
	 * 
	 * @param alist
	 *            the alist
	 * @param aoffset
	 *            the aoffset
	 * @param alimit
	 *            the alimit
	 * @param acount
	 *            the acount
	 * @param acriterio
	 *            the acriterio
	 */
	public PaginatedList(final List<E> alist, final int aoffset, final int alimit, final int acount,
			final Object acriterio) {
		super();
		this.list = alist;
		this.offset = aoffset;
		this.limit = alimit;
		this.count = acount;
		this.criterio = acriterio;
	}

	/**
	 * Gets the offset.
	 * 
	 * @param pageNumber
	 *            the page number
	 * @param rowsPerPage
	 *            the rows per page
	 * @return the offset
	 */
	public static int getOffset(final int pageNumber, final int rowsPerPage) {
		int offset = (pageNumber - FIRST_PAGE) * rowsPerPage;

		if (offset < MIN_OFFSET) {
			offset = MIN_OFFSET;
		}

		return offset;
	}

	/**
	 * To string.
	 * 
	 * @return the string
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, null, true);
	}

	/**
	 * Gets the list.
	 * 
	 * @return the list
	 */
	public List<E> getList() {
		return this.list;
	}

	/**
	 * Gets the offset.
	 * 
	 * @return the offset
	 */
	public int getOffset() {
		return this.offset;
	}

	/**
	 * Gets the limit.
	 * 
	 * @return the limit
	 */
	public int getLimit() {
		return this.limit;
	}

	/**
	 * Gets the total count.
	 * 
	 * @return the total count
	 */
	public int getCount() {
		return this.count;
	}

	/**
	 * Devuelve el valor de page count.
	 * 
	 * @return El valor de page count
	 */
	public int getPageCount() {
		int totalPages = 0;

		if (this.limit > 0) {
			totalPages = this.count / this.limit;

			if (this.count % this.limit > 0) {
				totalPages++;
			}
		}

		return totalPages;
	}

	/**
	 * Devuelve el valor de page.
	 * 
	 * @return El valor de page
	 */
	public int getPage() {
		int page = FIRST_PAGE;

		if (this.limit > 0) {
			page = FIRST_PAGE + this.offset / this.limit;

			if (this.offset % this.limit > 0) {
				page++;
			}
		}

		return page;
	}

	/**
	 * Gets the min page.
	 * 
	 * @return the min page
	 */
	public int getMinPage() {
		final int actualPage = getPage();

		int minPage = actualPage - 2;

		if (minPage < FIRST_PAGE) {
			minPage = FIRST_PAGE;
		}

		return minPage;
	}

	/**
	 * Gets the max page.
	 * 
	 * @return the max page
	 */
	public int getMaxPage() {
		final int totalPages = getPageCount();
		final int minPage = getMinPage();

		int maxPage = minPage + 5;

		if (maxPage > totalPages) {
			maxPage = totalPages;
		}

		return maxPage;
	}

	/**
	 * Gets the checks for previous.
	 * 
	 * @return the checks for previous
	 */
	public boolean getHasPrevious() {
		return getPage() > FIRST_PAGE;
	}

	/**
	 * Gets the checks for next.
	 * 
	 * @return the checks for next
	 */
	public boolean getHasNext() {
		return getPage() < getPageCount();
	}

	/**
	 * Gets the criterio.
	 * 
	 * @return the criterio
	 */
	public Object getCriterio() {
		return criterio;
	}
}
