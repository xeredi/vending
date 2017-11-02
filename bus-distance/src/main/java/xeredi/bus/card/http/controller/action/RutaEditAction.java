package xeredi.bus.card.http.controller.action;

import com.google.common.base.Preconditions;

import xeredi.bus.card.model.Ruta;
import xeredi.bus.card.model.service.RutaService;

// TODO: Auto-generated Javadoc
/**
 * The Class RutaEditAction.
 */
public final class RutaEditAction {

	/** The action code. */
	private ActionCode actionCode;

	/** The model. */
	private Ruta model;

	/**
	 * Execute.
	 */
	public void execute() {
		final RutaService modelService = new RutaService();

		switch (actionCode) {
		case edit:
			Preconditions.checkNotNull(model.getId());

			model = modelService.select(model.getId());

			break;
		default:
			throw new Error("Invalid action: " + actionCode.name());
		}
	}

}
