package xeredi.bus.erp.model;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new usuario.
 */
@Data
public class Usuario {

	/** The id. */
	private Long id;

	/** The clte. */
	private Cliente clte;

	/** The rol. */
	private Rol rol;

	/** The email. */
	private String email;

	/** The contrasenia. */
	private String contrasenia;

	/** The nombre. */
	private String nombre;
}
