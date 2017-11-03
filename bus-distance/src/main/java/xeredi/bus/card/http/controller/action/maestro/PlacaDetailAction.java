package xeredi.bus.card.http.controller.action.maestro;

import com.google.common.base.Preconditions;

import lombok.Data;
import xeredi.bus.card.http.controller.action.BaseAction;
import xeredi.bus.card.model.Placa;
import xeredi.bus.card.model.service.PlacaService;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadisticaDetailAction.
 */
@Data
public final class PlacaDetailAction extends BaseAction {
	/** The model. */
	private Placa model;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void doExecute() {
		Preconditions.checkNotNull(model.getId());

		final PlacaService placaService = new PlacaService();

		model = placaService.select(model.getId());
	}
}
