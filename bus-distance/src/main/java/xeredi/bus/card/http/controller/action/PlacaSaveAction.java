package xeredi.bus.card.http.controller.action;

import com.google.common.base.Preconditions;

import lombok.Data;
import xeredi.bus.card.model.Placa;
import xeredi.bus.card.model.service.PlacaService;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new placa save action.
 */
@Data
public final class PlacaSaveAction {

	/** The action code. */
	private ActionCode actionCode;

	/** The model. */
	private Placa model;

	/**
	 * Execute.
	 */
	public void execute() {
		final PlacaService modelService = new PlacaService();

		switch (actionCode) {
		case create:
			modelService.insert(model);

			break;
		case edit:
			Preconditions.checkNotNull(model.getId());

			modelService.update(model);

			break;
		default:
			throw new Error("Invalid action: " + actionCode.name());
		}
	}

}
