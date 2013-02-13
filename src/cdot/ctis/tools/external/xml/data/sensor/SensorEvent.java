package cdot.ctis.tools.external.xml.data.sensor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Sensor Eventclass.
 *
 * @author chandrasekharh
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SensorEvent {

    @XmlElement(name = "ClientID")
	protected String clientId;
    
    @XmlElement(name = "TransmissionID")
	protected String transmissionId;
    
    @XmlElement(name = "TransmissionDateTime")
//    @XmlSchemaType(name = "dateTime")
//	protected XMLGregorianCalendar transmissionDateTime;
    protected String transmissionDateTime;

    @XmlElement(name = "EventType")
    protected SensorEventType eventType;

    @XmlElement(name = "MeteredSpace")
    protected MeteredSpaceType meteredSpace;

    @XmlElement(name = "MeterID")
	protected String meterId;

    @XmlElement(name = "SessionID")
    protected String sessionId;

    @XmlElement(name = "EventTime")
//  @XmlSchemaType(name = "dateTime")
//	protected XMLGregorianCalendar eventTime;
    protected String eventTime;
    
	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the transmissionId
	 */
	public String getTransmissionId() {
		return transmissionId;
	}
	/**
	 * @param transmissionId the transmissionId to set
	 */
	public void setTransmissionId(String transmissionId) {
		this.transmissionId = transmissionId;
	}
	/**
	 * @return the transmissionDateTime
	 */
	public String getTransmissionDateTime() {
		return transmissionDateTime;
	}
	/**
	 * @param transmissionDateTime the transmissionDateTime to set
	 */
	public void setTransmissionDateTime(String transmissionDateTime) {
		this.transmissionDateTime = transmissionDateTime;
	}
	/**
	 * @return the meterId
	 */
	public String getMeterId() {
		return meterId;
	}
	/**
	 * @param meterId the meterId to set
	 */
	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}
	/**
	 * @return the eventType
	 */
	public SensorEventType getEventType() {
		return eventType;
	}
	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(SensorEventType eventType) {
		this.eventType = eventType;
	}
	/**
	 * @return the meteredSpace
	 */
	public MeteredSpaceType getMeteredSpace() {
		return meteredSpace;
	}
	/**
	 * @param meteredSpace the meteredSpace to set
	 */
	public void setMeteredSpace(MeteredSpaceType meteredSpace) {
		this.meteredSpace = meteredSpace;
	}
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
	 * @return the eventTime
	 */
	public String getEventTime() {
		return eventTime;
	}
	/**
	 * @param eventTime the eventTime to set
	 */
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
}
