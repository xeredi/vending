package xeredi.bus.card.http.controller.action.maestro;

import com.google.common.base.Preconditions;

import lombok.Data;
import xeredi.bus.card.http.controller.action.BaseAction;
import xeredi.bus.card.model.Placa;
import xeredi.bus.card.model.service.PlacaService;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new placa delete action.
 */
@Data
public final class PlacaDeleteAction extends BaseAction {

	/** The model. */
	private Placa model;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void doExecute() {
		Preconditions.checkNotNull(model.getId());

		final PlacaService modelService = new PlacaService();

		modelService.delete(model);
	}
}
