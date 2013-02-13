package cdot.ctis.tools.external.xml.data.sensor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Session Info for meter payment.
 * 
 * @author chandrasekharh
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MeteredSpaceType {

	@XmlElement(name = "SensorID")
	private String sensorId;

	@XmlElement(name = "SessionID")
	protected String sessionId;

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}
	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	/**
	 * @return the sensorId
	 */
	public String getSensorId() {
		return sensorId;
	}
	/**
	 * @param sensorId the sensorId to set
	 */
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}
}