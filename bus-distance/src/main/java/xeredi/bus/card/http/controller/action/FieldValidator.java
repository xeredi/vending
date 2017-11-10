package xeredi.bus.card.http.controller.action;

import com.opensymphony.xwork2.ActionSupport;

import lombok.NonNull;

// TODO: Auto-generated Javadoc
/**
 * The Class FieldValidator.
 */
public final class FieldValidator {

	/**
	 * Validate required.
	 *
	 * @param action
	 *            the action
	 * @param fieldName
	 *            the field name
	 * @param fieldValue
	 *            the field value
	 */
	public static void validateRequired(final @NonNull ActionSupport action, final @NonNull String fieldName,
			final Object fieldValue) {
		if (fieldValue == null || (fieldValue instanceof String && ((String) fieldValue).isEmpty())) {
			action.addActionError(fieldName + " es obligatorio!");
		}
	}
}
