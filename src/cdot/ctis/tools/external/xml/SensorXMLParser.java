package cdot.ctis.tools.external.xml;

import java.util.Arrays;
import java.util.Collection;

import cdot.ctis.tools.external.xml.data.sensor.SensorEvents;

/**
 * Payment XML Parser.
 * 
 * @author chandrasekharh
 *
 */
public class SensorXMLParser extends XMLParser<SensorEvents>{

	/* (non-Javadoc)
	 * @see cdot.ctis.tools.external.xml.XMLParser#getContextPackages()
	 */
	@Override
	protected Collection<String> getContextPackages() {
		return Arrays.asList("sensor");
	}
}
