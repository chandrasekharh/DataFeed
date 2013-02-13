package cdot.ctis.tools.external.xml.data.payment;

import java.math.BigDecimal;

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
public class PaymentSessionType {

	@XmlElement(name = "SessionID")
	protected String sessionId;
	
	@XmlElement(name = "AmountPaid")
	protected BigDecimal amountPaid;
	
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
	 * @return the amountPaid
	 */
	public BigDecimal getAmountPaid() {
		return amountPaid;
	}
	/**
	 * @param amountPaid the amountPaid to set
	 */
	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}
}