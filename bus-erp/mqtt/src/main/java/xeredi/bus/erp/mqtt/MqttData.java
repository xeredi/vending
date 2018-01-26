package xeredi.bus.erp.mqtt;

import java.util.List;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new mqtt data.
 */
public class MqttData {
	/** The sender id. */
	private String senderId;

	/** The message list. */
	private List<String> messageList;

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public List<String> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<String> messageList) {
		this.messageList = messageList;
	}
}
