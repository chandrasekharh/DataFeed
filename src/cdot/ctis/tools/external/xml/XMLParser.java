package cdot.ctis.tools.external.xml;

import java.io.StringReader;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import cdot.ctis.tools.external.exception.ApplicationException;

/**
 * XML Parser utility.
 * 
 * @author chandrasekharh
 *
 */
public abstract class XMLParser<K> {
	
	private static final String XML_DATA_PACKAGE = "cdot.ctis.tools.external.xml.data";

	/**
	 * Parses out the xml.
	 * 
	 * @param pXMLData
	 * @return
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public K parseXML(String pXMLData) throws ApplicationException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(getPackagesString());
			Unmarshaller xmlUnmarshaller = jaxbContext.createUnmarshaller();
			return (K)xmlUnmarshaller.unmarshal(new StringReader(pXMLData));
		} catch (JAXBException e) {
			throw new ApplicationException("Failed to parse XML", e);
		}
	}

	/**
	 * Returns the packages string.
	 * 
	 * @return
	 */
	protected String getPackagesString() {
		StringBuilder packagesStringBuilder = new StringBuilder();
		Collection<String> contextPackages = getContextPackages();
		for(String contextPackage: contextPackages) {
			if(packagesStringBuilder.length() > 0) {
				packagesStringBuilder.append(":");
			}
			if(!contextPackage.startsWith(XML_DATA_PACKAGE)) {
				contextPackage = XML_DATA_PACKAGE +"."+contextPackage;
			}
			packagesStringBuilder.append(contextPackage);
		}
		return packagesStringBuilder.toString();
	}

	/**
	 * Returns the context packages.
	 * 
	 * @return
	 */
	protected abstract Collection<String> getContextPackages();
}