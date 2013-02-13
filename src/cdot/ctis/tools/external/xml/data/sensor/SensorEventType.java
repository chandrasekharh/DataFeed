package cdot.ctis.tools.external.xml.data.sensor;

import javax.xml.bind.annotation.XmlEnum;

/**
 * Sensor Event types
 * 
 * @author chandrasekharh
 *
 */
@XmlEnum(String.class)
public enum SensorEventType {
	 SS, SE, SD, SU;
	
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
    public static SensorEventType fromValue(String v) {
        return valueOf(v);
    }	
}
