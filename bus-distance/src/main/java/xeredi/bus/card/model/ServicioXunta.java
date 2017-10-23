package xeredi.bus.card.model;

import lombok.Data;

@Data
public class ServicioXunta {
	private String concesion;

	private Servicio servicio;

	private Integer expedienteId;

	private String expedienteNombre;

}
