package xeredi.bus.card.http.controller.action.maestro;

import com.google.common.base.Preconditions;

import lombok.Data;
import xeredi.bus.card.http.controller.action.ActionCode;
import xeredi.bus.card.http.controller.action.BaseAction;
import xeredi.bus.card.http.controller.action.FieldValidator;
import xeredi.bus.card.model.Placa;
import xeredi.bus.card.model.service.PlacaService;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new placa save action.
 */
@Data
public final class PlacaSaveAction extends BaseAction {

	/** The action code. */
	private ActionCode accion;

	/** The model. */
	private Placa model;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void doExecute() {
		FieldValidator.validateRequired(this, "Codigo", model.getCodigo());
		FieldValidator.validateRequired(this, "Vehiculo", model.getVehiculo());

		if (!hasErrors()) {
			FieldValidator.validateRequired(this, "Vehiculo", model.getVehiculo().getId());
		}

		if (!hasErrors()) {
			final PlacaService modelService = new PlacaService();

			switch (accion) {
			case create:
				modelService.insert(model);

				break;
			case edit:
				Preconditions.checkNotNull(model.getId());

				modelService.update(model);

				break;
			default:
				throw new Error("Invalid action: " + accion.name());
			}
		}
	}

}
