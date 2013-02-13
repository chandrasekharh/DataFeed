package cdot.ctis.tools.external.xml.data.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Meter Payment class.
 *
 * @author chandrasekharh
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Payment {

    @XmlElement(name = "ClientID")
	protected String clientId;
    
    @XmlElement(name = "TransmissionID")
	protected String transmissionId;
    
    @XmlElement(name = "TransmissionDateTime")
//    @XmlSchemaType(name = "dateTime")
//	protected XMLGregorianCalendar transmissionDateTime;
    protected String transmissionDateTime;
    
    @XmlElement(name = "MeterID")
	protected String meterId;
    
    @XmlElement(name = "EventType")
    protected PaymentEventType eventType;
    
    @XmlElement(name = "NewSession")
    protected PaymentSessionType newSession;
    
    @XmlElement(name = "AddTime")
    protected PaymentSessionType addTime;
    
    @XmlElement(name = "StartTime")
    protected String startTime;
//    @XmlSchemaType(name = "dateTime")
//    protected XMLGregorianCalendar startTime;
    
    @XmlElement(name = "EndTime")
//    @XmlSchemaType(name = "dateTime")
//    protected XMLGregorianCalendar endTime;
    protected String endTime;

    @XmlElement(name = "PaymentType")
    protected String paymentType;
	
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
	public PaymentEventType getEventType() {
		return eventType;
	}
	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(PaymentEventType eventType) {
		this.eventType = eventType;
	}
	/**
	 * @return the newSession
	 */
	public PaymentSessionType getNewSession() {
		return newSession;
	}
	/**
	 * @param newSession the newSession to set
	 */
	public void setNewSession(PaymentSessionType newSession) {
		this.newSession = newSession;
	}
	/**
	 * @return the addTime
	 */
	public PaymentSessionType getAddTime() {
		return addTime;
	}
	/**
	 * @param addTime the addTime to set
	 */
	public void setAddTime(PaymentSessionType addTime) {
		this.addTime = addTime;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}
	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
}
