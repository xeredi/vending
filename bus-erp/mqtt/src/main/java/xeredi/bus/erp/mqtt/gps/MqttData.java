package xeredi.bus.erp.mqtt.gps;

import java.util.List;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new mqtt data.
 */
@Data
public final class MqttData {
	/** The sender id. */
	private String senderId;

	/** The message list. */
	private List<String> messageList;
}
