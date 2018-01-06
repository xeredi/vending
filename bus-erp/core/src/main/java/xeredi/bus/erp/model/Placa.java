package xeredi.bus.erp.model;

import java.util.Date;

import lombok.Data;

@Data
public class Placa {
	private Long id;

	private Cliente clte;

	private String codigo;

	private Date ffin;

	private LecturaGps lgps;
}
