package xeredi.bus.card.http.controller.action;

import lombok.Data;
import xeredi.bus.card.model.Ruta;
import xeredi.bus.card.model.RutaCriteria;
import xeredi.bus.card.model.service.RutaService;
import xeredi.bus.card.util.PaginatedList;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new ruta list action.
 */
@Data
public final class RutaListAction {
	/** The rows per page default. */
	public static final int ROWS_PER_PAGE_DEFAULT = 20;

	/** The page. */
	protected int page = PaginatedList.FIRST_PAGE;

	/** The limit. */
	protected int limit = ROWS_PER_PAGE_DEFAULT;

	/** The model. */
	protected RutaCriteria model;

	/** The result list. */
	protected PaginatedList<Ruta> resultList;

	/**
	 * Execute.
	 */
	public final void execute() {
		final RutaService rutaService = new RutaService();

		resultList = rutaService.selectList(model, getOffset(), limit);
	}

	/**
	 * Gets the offset.
	 *
	 * @return the offset
	 */
	protected final int getOffset() {
		return (page - PaginatedList.FIRST_PAGE) * limit;
	}
}
