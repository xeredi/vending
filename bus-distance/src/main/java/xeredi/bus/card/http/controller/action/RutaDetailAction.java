package xeredi.bus.card.http.controller.action;

import com.google.common.base.Preconditions;

import lombok.Data;
import xeredi.bus.card.model.Ruta;
import xeredi.bus.card.model.service.RutaService;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new ruta detail action.
 */
@Data
public final class RutaDetailAction {
	/** The model. */
	private Ruta model;

	/**
	 * Execute.
	 */
	public void execute() {
		Preconditions.checkNotNull(model.getId());

		final RutaService rutaService = new RutaService();

		model = rutaService.select(model.getId());
	}
}
