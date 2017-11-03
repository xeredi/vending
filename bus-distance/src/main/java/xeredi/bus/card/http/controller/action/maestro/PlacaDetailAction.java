package xeredi.bus.card.http.controller.action.maestro;

import com.google.common.base.Preconditions;

import lombok.Data;
import xeredi.bus.card.model.Placa;
import xeredi.bus.card.model.service.PlacaService;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadisticaDetailAction.
 */
@Data
public final class PlacaDetailAction {
	/** The model. */
	private Placa model;

	/**
	 * Execute.
	 */
	public void execute() {
		Preconditions.checkNotNull(model.getId());

		final PlacaService placaService = new PlacaService();

		model = placaService.select(model.getId());
	}
}
