package com.async.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.smartcall.dao.impl.MongoCustomerDetailsDao;
import com.smartcall.pojo.CustomerDetails;
import com.smartcall.pojo.PersonalInfo;
import com.smartcall.pojo.VehicleInfo;

public class CSVUtil {

	private List<String> errorList;
	private List<CustomerDetails> customerDetailsList;
	private BufferedReader bufferedReader;
	private String[] arrHeader = null;
	
	private Logger log = Logger.getLogger(CSVUtil.class);
	
	public CSVUtil(BufferedReader bufferedReader){
		this.bufferedReader = bufferedReader;
		this.errorList = new ArrayList<>();
	}
	
	public List<CustomerDetails> parse( Boolean ignoreDateError ) throws IOException {
		if( bufferedReader == null ){
			return null;
		}
		
		customerDetailsList = new ArrayList<>();
		CustomerDetails customerDetails = null;
		
		String line = "";
		String header = bufferedReader.readLine();
		
		header = header.replaceAll("\\|\\|", "\\| \\|");
		header = header.replaceAll("\\|\\|", "\\| \\|");
		header = header.replaceAll("\\|\\|", "\\| \\|");
		arrHeader = header.split("\\|");
		
		Integer count = 1;
		while ( (line = bufferedReader.readLine()) != null ) {
			try {
				customerDetails = parseLine( line, ignoreDateError, count) ;
				
				if( customerDetails != null ){
					customerDetailsList.add( customerDetails );
				}
				
			} catch (Exception e) {
				log.error("",e);
			}
			
			count++;
 			
		}
 		
		return customerDetailsList;
	}

	private CustomerDetails parseLine( String line, Boolean ignoreDateError, Integer count) throws Exception{
		line = line.replaceAll("\\|\\|", "\\| \\|");
		line = line.replaceAll("\\|\\|", "\\| \\|");
		line = line.replaceAll("\\|\\|", "\\| \\|");
		String[] arrLine = line.split("\\|");
		
		CustomerDetails customerDetails = new CustomerDetails();
		PersonalInfo personalInfo = new PersonalInfo();
		personalInfo.setName( arrLine[1] );
		personalInfo.setResidentialAddress( arrLine[2]+" "+arrLine[3]+" "+arrLine[4]);
		personalInfo.setCompanyName( arrLine[5] );
		personalInfo.setCompanyAddress( arrLine[6]+" "+arrLine[6]+" "+arrLine[8] );
		
		personalInfo.setState( arrLine[9]);
		personalInfo.setCity( arrLine[10]);
		personalInfo.setPin( arrLine[11]);
		
		personalInfo.setMobile( arrLine[12]);
		personalInfo.setResidencePhone( arrLine[13] );
		personalInfo.setOfficePhone( arrLine[14] );
		personalInfo.setEmailId( arrLine[15] );
		
		customerDetails.setPersonalInfo(personalInfo);
		
		VehicleInfo vehicleInfo =  new VehicleInfo();
		vehicleInfo.setSalesConsultant( arrLine[16] );
		vehicleInfo.setBookingOrderNo( arrLine[17] );
		
/*Date*/	vehicleInfo.setBookingOrderDate( getDateValue( arrLine[18], ignoreDateError, arrHeader[18], count ) );
		
		vehicleInfo.setModel( arrLine[19] );
		vehicleInfo.setVariant( arrLine[20] );
		vehicleInfo.setColor( arrLine[21] );
		vehicleInfo.setVin( arrLine[22] );
		vehicleInfo.setEngineNo( arrLine[23] );
		vehicleInfo.setVehicleNo( arrLine[24] );
		
/*Date*/	vehicleInfo.setInvoiceDate(getDateValue( arrLine[25],ignoreDateError, arrHeader[25], count ));
/*Date*/	vehicleInfo.setDeliveryDate( getDateValue( arrLine[27],ignoreDateError, arrHeader[27], count ) );

		vehicleInfo.setDealerName( arrLine[29] );
		vehicleInfo.setDealerState( arrLine[30] );
		vehicleInfo.setDealerCity( arrLine[31] );
		vehicleInfo.setDealerRegion( arrLine[32] );
		
		customerDetails.setVehicleInfo(vehicleInfo);
		customerDetails.set_id( vehicleInfo.getVin() );
		customerDetails.setLastServiceDate( vehicleInfo.getDeliveryDate() );
		return customerDetails;
		
	}
	
	private Date getDateValue(String  string, Boolean ignoreDateError, String columnName, Integer count) throws Exception {
		Date date = null;
		try{
			if( string ==  null || string.trim().length() ==0){
				return null;
			}
			string = string.trim();
			string.replace("'", "");
			String formate = getDataFormat(string);
			
			SimpleDateFormat sdf = new SimpleDateFormat(formate);
			date = sdf.parse(string);
			return date;
		}
		catch(Exception e){
			String message = "[Srl No. : "+count+", Row : "+(count+1)+", Column: "+columnName+", Value:"+string+"]";
			errorList.add( "Prolem in " + message );
			if( ignoreDateError ){
				//exception.printStackTrace();
				log.error("",e);
				return null;
			}
			else{
				System.out.println("hi");
				throw e;
			}
		}
	}
	
    private String getDataFormat(String str) {
    	if( str.indexOf("/") != -1 ){
			try{
				Integer.parseInt( str.replace("/","") );
				return "dd/M/yyyy";
			}
			catch(NumberFormatException nfe){
				return "dd/MMM/yyyy";
			}
		}
		else if ( str.indexOf("-") != -1 ){
			try{
				Integer.parseInt( str.replace("-","") );
				return "dd-M-yyyy";
			}
			catch(NumberFormatException nfe){
				return "dd-MMM-yyyy";
			}
		}
		return "";
	}

    public List<String> getErrorList(){
    	return errorList;
    }
	public List<CustomerDetails> getCustomerDetailsList() {
		return customerDetailsList;
	}

	public static void main(String[] args) throws ParseException, IOException {
    	
		String csvFile = "doc/total _data1.csv";
		BufferedReader br = null;
		List<CustomerDetails> customerDetailsList = null;
		try {
	 		br = new BufferedReader(new FileReader(csvFile));
			CSVUtil csvUtil =  new CSVUtil( br );
			customerDetailsList = csvUtil.parse(true);
	 		
		 }
	 	catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
        MongoCustomerDetailsDao mcd = new MongoCustomerDetailsDao();
        for(CustomerDetails cs : customerDetailsList ){
        	mcd.create( cs ); 
        }
    	
        //for(String str : excelUtil.errorList){
        	//System.out.println(str);
        //}
		
		System.out.println("Done");
    }
}
