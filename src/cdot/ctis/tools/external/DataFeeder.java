package cdot.ctis.tools.external;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cdot.ctis.tools.external.exception.ApplicationException;
import cdot.ctis.tools.external.file.FileManager;
import cdot.ctis.tools.external.xml.PaymentXMLParser;
import cdot.ctis.tools.external.xml.SensorXMLParser;
import cdot.ctis.tools.external.xml.XMLParser;
import cdot.ctis.tools.external.xml.data.payment.Payments;
import cdot.ctis.tools.external.xml.data.sensor.SensorEvents;

/**
 * Data Feeder implementation
 * 
 * @author chandrasekharh
 *
 */
@WebService(endpointInterface="cdot.ctis.tools.external.IDataFeeder")
public class DataFeeder implements IDataFeeder {

	private String paymentXMLStorageLocation;
	private String sensorXMLStorageLocation;
	private FileManager fileManager;
	private HashMap<String, String> authInfoMap;
	private Log logger;
	private XMLParser<Payments> paymentXMLParser;
	private XMLParser<SensorEvents> sensorXMLParser;

	
	/**
	 * Data Feeder.
	 */
	public DataFeeder() {
		logger = LogFactory.getLog(getClass());
		paymentXMLParser = new PaymentXMLParser();
		sensorXMLParser = new SensorXMLParser();
	}
	
	/* (non-Javadoc)
	 * @see cdot.ctis.tools.external.IDataFeeder#feedData(java.lang.String)
	 */
	@Override
	public String savePaymentInfo(String pUserName, String pPassword, String pXML) {
		if(isValidUser(pUserName, pPassword)) {
			String saveXMLResult = saveXML(pXML, paymentXMLStorageLocation+"/"+"Payment_"+System.currentTimeMillis()+".xml");
			try {
				Payments payments = paymentXMLParser.parseXML(pXML);
				fileManager.savePaymentInfo(payments);
			} catch (ApplicationException e) {
				saveXMLResult += "\n"+e.getMessage();
				e.printStackTrace();
			}
			return saveXMLResult;
		} else {
			return "Invalid username/password";
		}
	}

	/* (non-Javadoc)
	 * @see cdot.ctis.tools.external.IDataFeeder#saveSensorInfo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String saveSensorInfo(String pUserName, String pPassword, String pXML) {
		if(isValidUser(pUserName, pPassword)) {
			String saveXMLResult = saveXML(pXML, sensorXMLStorageLocation+"/"+"Sensor_"+System.currentTimeMillis()+".xml");
			try {
				SensorEvents sensorEvents = sensorXMLParser.parseXML(pXML);
				fileManager.saveSensorEvents(sensorEvents);
			} catch (ApplicationException e) {
				saveXMLResult += "\n"+e.getMessage();
				e.printStackTrace();
			}
			return saveXMLResult;
			
		} else {
			return "Invalid username/password";
		}
	}

	/**
	 * Checks if the given user is valid.
	 * @param pUserName
	 * @param pPassword
	 * @return
	 */
	private boolean isValidUser(String pUserName, String pPassword) {
		if(authInfoMap != null) {
			String password = authInfoMap.get(pUserName);
			return password != null && password.equals(pPassword);
		}
		return false;
	}

	/**
	 * @param pXML
	 * @param pFileLocation
	 * @return
	 */
	private String saveXML(String pXML, String pFileLocation) {
		String status = null; 
		try {
			File file = new File(pFileLocation);
			if(file.exists()) {
				file.delete();
			}
			file.createNewFile();
			FileWriter fileWritter = new FileWriter(file);
			fileWritter.write(pXML);
			fileWritter.close();
			status = "Success";
		} catch (Exception pException) {
			pException.printStackTrace();
			status = "Failed ("+pException.getMessage()+")";
		}
		return status;
	}

	/**
	 * @param paymentXMLStorageLocation the paymentXMLStorageLocation to set
	 */
	public void setPaymentXMLStorageLocation(String paymentXMLStorageLocation) {
		this.paymentXMLStorageLocation = paymentXMLStorageLocation;
	}

	/**
	 * @param sensorXMLStorageLocation the sensorXMLStorageLocation to set
	 */
	public void setSensorXMLStorageLocation(String sensorXMLStorageLocation) {
		this.sensorXMLStorageLocation = sensorXMLStorageLocation;
	}

	/**
	 * @param csvFileManager the csvFileManager to set
	 */
	public void setFileManager(FileManager csvFileManager) {
		this.fileManager = csvFileManager;
	}

	/**
	 * Sets the auth info.
	 * 
	 * @param authInfo the authInfo to set
	 */
	public void setAuthInfo(String authInfo) {
		this.authInfoMap = new HashMap<String, String>();
		String[] userNamePasswords = authInfo.split(";");
		if(userNamePasswords.length > 0) {
			for(String userNamePassword: userNamePasswords) {
				String[] userNameAndPassword = userNamePassword.split("\\$");
				if(userNameAndPassword.length == 2) {
					authInfoMap.put(userNameAndPassword[0], userNameAndPassword[1]);
				} else {
					logger.error("Invalid authentication info - Username passwords should be separated by '$'");
				}
			}
		} else {
			logger.error("Authentication Info not initialized in the required format");
		}
	}
}