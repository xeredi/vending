package xeredi.bus.card.http.controller.action.maestro;

import com.google.common.base.Preconditions;

import lombok.Data;
import xeredi.bus.card.http.controller.action.BaseAction;
import xeredi.bus.card.model.Placa;
import xeredi.bus.card.model.service.PlacaService;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new placa remove action.
 */
@Data
public final class PlacaRemoveAction extends BaseAction {

	/** The model. */
	private Placa model;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doExecute() {
		final PlacaService modelService = new PlacaService();

		Preconditions.checkNotNull(model.getId());

		modelService.delete(model);
	}

}
