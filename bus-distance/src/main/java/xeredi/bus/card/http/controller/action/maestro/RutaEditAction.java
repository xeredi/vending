package xeredi.bus.card.http.controller.action.maestro;

import com.google.common.base.Preconditions;

import xeredi.bus.card.http.controller.action.ActionCode;
import xeredi.bus.card.http.controller.action.BaseAction;
import xeredi.bus.card.model.Ruta;
import xeredi.bus.card.model.service.RutaService;

// TODO: Auto-generated Javadoc
/**
 * The Class RutaEditAction.
 */
public final class RutaEditAction extends BaseAction {

	/** The action code. */
	private ActionCode actionCode;

	/** The model. */
	private Ruta model;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void doExecute() {
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