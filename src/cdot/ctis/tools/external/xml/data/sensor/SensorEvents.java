package cdot.ctis.tools.external.xml.data.sensor;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SensorEvents")
public class SensorEvents {

    @XmlElement(name = "SensorEvent")
    protected List<SensorEvent> sensorEvents;

    /**
     * Gets the value of the payment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alert property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPayment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AlertType }
     * 
     * 
     */
    public List<SensorEvent> getSensorEvents() {
        if (sensorEvents == null) {
        	sensorEvents = new ArrayList<SensorEvent>();
        }
        return this.sensorEvents;
    }
}
