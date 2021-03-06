package xeredi.bus.card.http.controller.action.maestro;

import java.util.List;

import com.google.common.base.Preconditions;

import lombok.Data;
import xeredi.bus.card.http.controller.action.ActionCode;
import xeredi.bus.card.http.controller.action.BaseAction;
import xeredi.bus.card.model.Placa;
import xeredi.bus.card.model.Vehiculo;
import xeredi.bus.card.model.VehiculoCriteria;
import xeredi.bus.card.model.service.PlacaService;
import xeredi.bus.card.model.service.VehiculoService;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new placa edit action.
 */
@Data
public final class PlacaEditAction extends BaseAction {

	/** The action code. */
	private ActionCode accion;

	/** The model. */
	private Placa model;

	/** The vhcl list. */
	private List<Vehiculo> vhclList;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void doExecute() {
		final PlacaService modelService = new PlacaService();

		switch (accion) {
		case create:
			model = new Placa();

			break;
		case edit:
			Preconditions.checkNotNull(model.getId());

			model = modelService.select(model.getId());

			break;
		default:
			throw new Error("Invalid action: " + accion.name());
		}

		// Dependencies
		final VehiculoService vhclService = new VehiculoService();

		this.vhclList = vhclService.selectList(new VehiculoCriteria());
	}
}
