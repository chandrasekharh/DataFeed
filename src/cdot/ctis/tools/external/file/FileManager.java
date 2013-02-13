package cdot.ctis.tools.external.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cdot.ctis.tools.external.file.csv.ExcelCSVPrinter;
import cdot.ctis.tools.external.xml.data.payment.Payment;
import cdot.ctis.tools.external.xml.data.payment.Payments;
import cdot.ctis.tools.external.xml.data.sensor.SensorEvent;
import cdot.ctis.tools.external.xml.data.sensor.SensorEvents;

/**
 * File Manager. 
 * 
 * @author chandrasekharh
 *
 */
public class FileManager {

	private String fileNameDateFormat = "MM_dd_yyyy";
	private String outputDateFormat = "MM/dd/yyyy hh:mm ss a";
	private String inputDateFormat = "MM/dd/yyyy hh:mm:ss";
	
	private String csvStorageLocation;
	
	private String paymentFilePrefix = "Payment";
	private String sensorFilePrefix = "Sensor";


	private SimpleDateFormat outputSimpleDateFormat;
	private SimpleDateFormat inputSimpleDateFormat;
	private SimpleDateFormat fileNameSimpleDateFormat;
	private Object paymentLock;
	private Object sensorLock;
	
	private Log logger;


	/**
	 * Private constructor. 
	 */
	public FileManager() {
		logger = LogFactory.getLog(getClass());
		paymentLock = new Object();
		sensorLock = new Object();
	}
	
	/**
	 * Updates the sensor events
	 */
	public void saveSensorEvents(SensorEvents pSensorEvents) {
		if(pSensorEvents != null && pSensorEvents.getSensorEvents() != null) {
			
			List<SensorEvent> sensorEvents = pSensorEvents.getSensorEvents();
			int rowCount = 0;
			String[][] sensorEventContents = new String[sensorEvents.size()][];
			String[] header = new String[] {
					"Client Id",
					"Transmission Id",
					"Transmission Date", 
					"Event Type",
					"Meter Space Sensor Id",
					"Metered Space Session Id",
					"Meter Id", 
					"Session ID", 
					"Event Time",
					"Collection Time"};
			
			for(SensorEvent sensorEvent: sensorEvents) {
				String[] sensorEventInfo = new String[10];
				sensorEventContents[rowCount++] = sensorEventInfo;
				
				int col = 0;
				fillArray(sensorEventInfo, sensorEvent.getClientId(), col++);
				fillArray(sensorEventInfo, sensorEvent.getTransmissionId(), col++);
				fillArray(sensorEventInfo, formatAsDateTime(sensorEvent.getTransmissionDateTime()), col++);
				fillArray(sensorEventInfo, sensorEvent.getEventType().toString(), col++);				
				
				if(sensorEvent.getMeteredSpace() != null) {
					fillArray(sensorEventInfo, sensorEvent.getMeteredSpace().getSensorId(), col++);
					fillArray(sensorEventInfo, sensorEvent.getMeteredSpace().getSessionId(), col++);
				} else {
					fillArray(sensorEventInfo, "", col++);
					fillArray(sensorEventInfo, "", col++);
				}
				fillArray(sensorEventInfo, sensorEvent.getMeterId(), col++);
				fillArray(sensorEventInfo, sensorEvent.getSessionId(), col++);
				fillArray(sensorEventInfo, formatAsDateTime(sensorEvent.getEventTime()), col++);
				fillArray(sensorEventInfo, new Timestamp(System.currentTimeMillis()), col++);
			}
			
			//to be safe - use synchronized blocks for saving the file.
			synchronized (sensorLock) {
				saveAsCSVFile(generateFileName(sensorFilePrefix), sensorEventContents, header);
			}
		}
	}

	/**
	 * Updates the payment information
	 */
	public void savePaymentInfo(Payments pPayments) {
		if(pPayments != null && pPayments.getPayment() != null) {
			
			List<Payment> payments = pPayments.getPayment();
			int rowCount = 0;
			String[][] paymentContents = new String[payments.size()][];
			String[] header = new String[] { 
					"Transmission Id",
					"Transmission Date", "Meter Id", "Event Type",
					"New Session ID", "New Session Amount Paid",
					"Add Time Session ID", "Add Time Amount Paid",
					"Payment Type", "Start Time", "End Time", 
					"Collection Time"
			};
			
			for(Payment payment: payments) {
				String[] paymentInfo = new String[12];
				paymentContents[rowCount++] = paymentInfo;
				
				int col = 0;
				fillArray(paymentInfo, payment.getTransmissionId(), col++);
				fillArray(paymentInfo, formatAsDateTime(payment.getTransmissionDateTime()), col++);
				fillArray(paymentInfo, payment.getMeterId(), col++);
				fillArray(paymentInfo, payment.getEventType().toString(), col++);
				
				if(payment.getNewSession() != null) {
					fillArray(paymentInfo, payment.getNewSession().getSessionId(), col++);
					fillArray(paymentInfo, payment.getNewSession().getAmountPaid(), col++);
				} else {
					fillArray(paymentInfo, "", col++);
					fillArray(paymentInfo, "", col++);
				}
				if(payment.getAddTime() != null) {
					fillArray(paymentInfo, payment.getAddTime().getSessionId(), col++);
					fillArray(paymentInfo, payment.getAddTime().getAmountPaid(), col++);
				} else {
					fillArray(paymentInfo, "", col++);
					fillArray(paymentInfo, "", col++);
				}

				fillArray(paymentInfo, payment.getPaymentType(), col++);
				fillArray(paymentInfo, formatAsDateTime(payment.getStartTime()), col++);
				fillArray(paymentInfo, formatAsDateTime(payment.getEndTime()), col++);
				fillArray(paymentInfo, new Timestamp(System.currentTimeMillis()), col++);
			}
			
			//to be safe - use synchronized blocks for saving the file.
			synchronized (paymentLock) {
				saveAsCSVFile(generateFileName(paymentFilePrefix), paymentContents, header);
			}
		}
	}


	/**
	 * Formats the time in the specified output date format.
	 * 
	 * @param time
	 * @return
	 */
	private String formatAsDateTime(String time) {
		//try to format the input date time.
		if(time != null) {
			try { 
				Date date = getInputSimpleDateFormat().parse(time);
				time = convertToString(date);//now use the output date format to format .
			} catch (ParseException e) {
				logger.error("Failed to parse the date value "+time, e);
			}
		}
		return time;
	}


	/**
	 * Fills the array.
	 * 
	 * @param header
	 * @param paymentInfo
	 * @param number
	 * @param index
	 */
	private void fillArray(String[] paymentInfo, BigDecimal number, int index) {
		fillArray(paymentInfo, convertToString(number), index);
		
	}

	/**
	 * Fills the array.
	 * 
	 * @param header
	 * @param paymentInfo
	 * @param xmlCalendar
	 * @param index
	 */
	@SuppressWarnings("unused")
	private void fillArray(String[] paymentInfo, XMLGregorianCalendar xmlCalendar, int index) {
		fillArray(paymentInfo, convertToString(xmlCalendar), index);
		
	}

	/**
	 * Fills the array.
	 * 
	 * @param header
	 * @param paymentInfo
	 * @param date
	 * @param index
	 */
	private void fillArray(String[] paymentInfo, Date date, int index) {
		fillArray(paymentInfo, convertToString(date), index);
		
	}

	/**
	 * Fills the array with the given information.
	 * 
	 * @param header
	 * @param contents
	 * @param headerVal
	 * @param value
	 * @param index
	 */
	private void fillArray(String[] contents, String value, int index) {
		contents[index] = value;
	}
	
	/**
	 * Converts to string.
	 * 
	 * @param pNumber
	 * @return
	 */
	private String convertToString(Number pNumber) {
		if(pNumber != null) {
			return Double.toString(pNumber.doubleValue());
		} 
		return "N/A";
	}


	/**
	 * converts to string.
	 * 
	 * @param xmlCalendar
	 * @return
	 */
	private String convertToString(XMLGregorianCalendar xmlCalendar) {
		if(xmlCalendar != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(xmlCalendar.getYear(), xmlCalendar.getMonth(), xmlCalendar.getDay(), xmlCalendar.getHour(), xmlCalendar.getMinute(), xmlCalendar.getSecond());
			Date date = calendar.getTime();
			return convertToString(date);
		}
		return "N/A";
	}


	/**
	 * Converts to string.
	 * 
	 * @param date
	 * @return
	 */
	private String convertToString(Date date) {
		if(date != null) {
			return getOutputSimpleDateFormat().format(date);
		}
		return "N/A";
	}


	/**
	 * Generates the file name for the give type.
	 * 
	 * @param pType
	 * @return
	 */
	private String generateFileName(String pType) {
		return new StringBuilder(this.csvStorageLocation)
				.append("/")
				.append(pType)
				.append("_")
				.append(getFileNameSimpleDateFormat().format(Calendar.getInstance().getTime()))
				.append(".csv").toString();
	}
	
	/**
	 * Save the contents as CSV file.
	 * 
	 * @param pContents
	 * @param pParentComponent
	 * @return
	 */
	private boolean saveAsCSVFile(
						String pFileName, 
						String[][] pContents,
						String[] pHeaders) {
		FileOutputStream fileOutputStream = null;
		boolean resultValue = false;
		try {
			
			File file = new File(pFileName);
			StringWriter stringWriter = new StringWriter();
			
			ExcelCSVPrinter csvPrinter = new ExcelCSVPrinter(stringWriter);
			csvPrinter.setAutoFlush(true);
			//add the header for new files
			if(!file.exists()) {
				csvPrinter.writeln(pHeaders);
			}
			
			csvPrinter.writeln(pContents);
			csvPrinter.close();
			
			StringBuffer csvStringBuffer = stringWriter.getBuffer();
			if(csvStringBuffer != null) {
				fileOutputStream = new FileOutputStream(file, true);
				fileOutputStream.write(csvStringBuffer.toString().getBytes());
				resultValue = true;
			}
		} catch (IOException e) {
			logger.error("Failed to create/ write CSV file "+pFileName, e);
		} finally {
			if(fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException ex) {
					//donot show message here.
				}
			}
		}
		return resultValue;
	}
	
	/**
	 * @return the outputSimpleDateFormat
	 */
	private SimpleDateFormat getOutputSimpleDateFormat() {
		if(outputSimpleDateFormat == null) {
			outputSimpleDateFormat = new SimpleDateFormat(this.outputDateFormat);
		}
		return outputSimpleDateFormat;
	}


	/**
	 * @return the inputSimpleDateFormat
	 */
	private SimpleDateFormat getInputSimpleDateFormat() {
		if(inputSimpleDateFormat == null) {
			inputSimpleDateFormat = new SimpleDateFormat(this.inputDateFormat);
		}
		return inputSimpleDateFormat;
	}


	/**
	 * @return the fileNameSimpleDateFormat
	 */
	private SimpleDateFormat getFileNameSimpleDateFormat() {
		if(fileNameSimpleDateFormat == null) {
			fileNameSimpleDateFormat = new SimpleDateFormat(this.fileNameDateFormat);
		}
		return fileNameSimpleDateFormat;
	}
	

	/**
	 * @param dateFormat the dateFormat to set
	 */
	public void setFileNameDateFormat(String dateFormat) {
		this.fileNameDateFormat = dateFormat;
	}


	/**
	 * @param paymentFilePrefix the paymentFilePrefix to set
	 */
	public void setPaymentFilePrefix(String paymentFilePrefix) {
		this.paymentFilePrefix = paymentFilePrefix;
	}


	/**
	 * @param sensorFilePrefix the sensorFilePrefix to set
	 */
	public void setSensorFilePrefix(String sensorFilePrefix) {
		this.sensorFilePrefix = sensorFilePrefix;
	}


	/**
	 * @param outputDateFormat the outputDateFormat to set
	 */
	public void setOutputDateFormat(String outputDateFormat) {
		this.outputDateFormat = outputDateFormat;
	}

	/**
	 * @param csvStorageLocation the csvStorageLocation to set
	 */
	public void setCsvStorageLocation(String csvStorageLocation) {
		this.csvStorageLocation = csvStorageLocation;
	}
}
