package cdot.ctis.tools.external;

import javax.jws.WebService;

/**
 * Interface for feeding 
 * @author chandrasekharh
 *
 */
@WebService
public interface IDataFeeder {

	/**
	 * An interface to save the payment information.
	 * 
	 * @param pUserName
	 * @param pPassword
	 * @param pXML
	 * @return
	 */
	public String savePaymentInfo(String pUserName, String pPassword, String pXML);

	/**
	 * An interface to save the sensor information.
	 * 
	 * @param pUserName
	 * @param pPassword
	 * @param pXML
	 * @return
	 */
	public String saveSensorInfo(String pUserName, String pPassword, String pXML);
}
