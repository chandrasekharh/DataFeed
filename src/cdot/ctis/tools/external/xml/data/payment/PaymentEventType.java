package cdot.ctis.tools.external.xml.data.payment;

import javax.xml.bind.annotation.XmlEnum;

/**
 * Event Type for the meter payment.
 * 
 * @author chandrasekharh
 *
 */
@XmlEnum(String.class)
public enum PaymentEventType {
	 NS, AT;
	
	/**
	 * Returns the value of this enum.
	 * 
	 * @return
	 */
	public String value() {
        return name();
    }

    /**
     * Returns the enum equivalent of the string.
     * 
     * @param v
     * @return
     */
    public static PaymentEventType fromValue(String v) {
        return valueOf(v);
    }	
}
