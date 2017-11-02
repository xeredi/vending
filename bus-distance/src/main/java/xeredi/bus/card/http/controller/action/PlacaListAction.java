package xeredi.bus.card.http.controller.action;

import lombok.Data;
import xeredi.bus.card.model.Placa;
import xeredi.bus.card.model.PlacaCriteria;
import xeredi.bus.card.model.service.PlacaService;
import xeredi.bus.card.util.PaginatedList;

// TODO: Auto-generated Javadoc
/**
 * The Class PeriodoProcesoListAction.
 */
@Data
public final class PlacaListAction {
	/** The rows per page default. */
	public static final int ROWS_PER_PAGE_DEFAULT = 20;

	/** The page. */
	protected int page = PaginatedList.FIRST_PAGE;

	/** The limit. */
	protected int limit = ROWS_PER_PAGE_DEFAULT;

	/** The model. */
	protected PlacaCriteria model;

	/** The result list. */
	protected PaginatedList<Placa> resultList;

	/**
	 * Execute.
	 */
	public final void execute() {
		final PlacaService placaService = new PlacaService();

		resultList = placaService.selectList(model, getOffset(), limit);
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
