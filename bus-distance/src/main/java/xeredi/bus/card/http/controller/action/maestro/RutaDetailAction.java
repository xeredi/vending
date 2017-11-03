package xeredi.bus.card.http.controller.action.maestro;

import com.google.common.base.Preconditions;

import lombok.Data;
import xeredi.bus.card.http.controller.action.BaseAction;
import xeredi.bus.card.model.Ruta;
import xeredi.bus.card.model.service.RutaService;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new ruta detail action.
 */
@Data
public final class RutaDetailAction extends BaseAction {
	/** The model. */
	private Ruta model;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void doExecute() {
		Preconditions.checkNotNull(model.getId());

		final RutaService rutaService = new RutaService();

		model = rutaService.select(model.getId());
	}
}
