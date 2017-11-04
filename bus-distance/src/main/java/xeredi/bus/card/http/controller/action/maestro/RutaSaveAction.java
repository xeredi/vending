package xeredi.bus.card.http.controller.action.maestro;

import com.google.common.base.Preconditions;

import lombok.Data;
import xeredi.bus.card.http.controller.action.ActionCode;
import xeredi.bus.card.http.controller.action.BaseAction;
import xeredi.bus.card.model.Ruta;
import xeredi.bus.card.model.service.RutaService;

// TODO: Auto-generated Javadoc
/**
 * The Class RutaSaveAction.
 */
@Data
public final class RutaSaveAction extends BaseAction {

	/** The action code. */
	private ActionCode accion;

	/** The model. */
	private Ruta model;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void doExecute() {
		final RutaService modelService = new RutaService();

		switch (accion) {
		case edit:
			Preconditions.checkNotNull(model.getId());

			modelService.update(model);

			break;
		default:
			throw new Error("Invalid action: " + accion.name());
		}
	}

}
