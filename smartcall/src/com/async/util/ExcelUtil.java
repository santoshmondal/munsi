package com.async.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.smartcall.dao.impl.MongoCustomerDetailsDao;
import com.smartcall.pojo.CustomerDetails;
import com.smartcall.pojo.PersonalInfo;
import com.smartcall.pojo.VehicleInfo;

public class ExcelUtil {

	private List<String> errorList;
	private List<CustomerDetails> customerDetailsList;
	private InputStream inputStream;
	private Logger log = Logger.getLogger(ExcelUtil.class);
	public ExcelUtil(InputStream inputStream){
		this.inputStream = inputStream;
		this.errorList = new ArrayList<>();
	}
	
	public List<CustomerDetails> parse( Boolean ignoreDateError ) {
		System.out.println("11");
		if( inputStream == null ){
			return null;
		}
		
		long sTime = System.currentTimeMillis();
		Workbook lWorkbook = null;
		try {
			lWorkbook = WorkbookFactory.create(inputStream);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long eTime = System.currentTimeMillis();
		System.out.println("TT::" + (eTime-sTime));

		Sheet lSheet = lWorkbook.getSheetAt(0);
		
		Iterator<Row> lRowIterator = lSheet.iterator();
		customerDetailsList = new ArrayList<>();
		CustomerDetails customerDetails = null;
		MongoCustomerDetailsDao mcd = new MongoCustomerDetailsDao();
	        	 
	        
		while (lRowIterator.hasNext()) {
			Row row = lRowIterator.next();
			if (row == null || row.getRowNum() == 0){
				continue;	// Ignore header
			}
			
			try {
				customerDetails = parseRow( row, ignoreDateError) ;
				
				if( customerDetails != null ){
					mcd.create( customerDetails );
					//customerDetailsList.add( customerDetails );
				}
				
			} catch (Exception e) {
				//e.printStackTrace();
				log.error("",e);
			}
			
		}
		
		return customerDetailsList;
	}

	private CustomerDetails parseRow( Row row, Boolean ignoreDateError) throws Exception{
		CustomerDetails customerDetails = new CustomerDetails();
		
		PersonalInfo personalInfo = new PersonalInfo();
		personalInfo.setName( getStringValue( row.getCell(1) ) );
		
		String addressLine1 = getStringValue( row.getCell(2) );
		String addressLine2 = getStringValue( row.getCell(3) );
		String addressLine3 = getStringValue( row.getCell(4) );
		personalInfo.setResidentialAddress( addressLine1+" "+addressLine2+" "+addressLine3 );
		
		personalInfo.setCompanyName( getStringValue( row.getCell(5) ) );
		
		addressLine1 = getStringValue( row.getCell(6) );
		addressLine2 = getStringValue( row.getCell(7) );
		addressLine3 = getStringValue( row.getCell(8) );
		personalInfo.setCompanyAddress( addressLine1+" "+addressLine2+" "+addressLine3 );
		
		personalInfo.setState( getStringValue( row.getCell(9) ));
		personalInfo.setCity( getStringValue( row.getCell(10) ));
		personalInfo.setPin( getStringValue( row.getCell(11) ));
		
		personalInfo.setMobile( getStringValue( row.getCell(12) ) );
		personalInfo.setResidencePhone( getStringValue( row.getCell(13) ) );
		personalInfo.setOfficePhone( getStringValue( row.getCell(14) ) );
		personalInfo.setEmailId( getStringValue( row.getCell(15) ) );
		
		customerDetails.setPersonalInfo(personalInfo);
		
		VehicleInfo vehicleInfo =  new VehicleInfo();
		vehicleInfo.setSalesConsultant( getStringValue( row.getCell(16) ) );
		vehicleInfo.setBookingOrderNo( getStringValue( row.getCell(17) ) );
		
/*D*/	vehicleInfo.setBookingOrderDate( getDateValue( row.getCell(18), ignoreDateError ) );
		
		vehicleInfo.setModel( getStringValue( row.getCell(19) ) );
		vehicleInfo.setVariant( getStringValue( row.getCell(20) ) );
		vehicleInfo.setColor( getStringValue( row.getCell(21) ) );
		vehicleInfo.setVin( getStringValue( row.getCell(22) ) );
		vehicleInfo.setEngineNo( getStringValue( row.getCell(23) ) );
		vehicleInfo.setVehicleNo( getStringValue( row.getCell(24) ) );
		
/*D*/	vehicleInfo.setInvoiceDate(getDateValue( row.getCell(25),ignoreDateError ));
/*D*/	vehicleInfo.setDeliveryDate( getDateValue( row.getCell(27),ignoreDateError ) );

		vehicleInfo.setDealerName( getStringValue( row.getCell(29) ) );
		vehicleInfo.setDealerState( getStringValue( row.getCell(30) ) );
		vehicleInfo.setDealerCity( getStringValue( row.getCell(31) ) );
		vehicleInfo.setDealerRegion( getStringValue( row.getCell(32) ) );
		
		customerDetails.setVehicleInfo(vehicleInfo);
		customerDetails.set_id( vehicleInfo.getVin() );
		customerDetails.setLastServiceDate( vehicleInfo.getDeliveryDate() );
		return customerDetails;
	}
	
	private String getStringValue(Cell cell) throws Exception {
		if ( cell == null){
			return "";
		}
		try{
			FormulaEvaluator evaluator = cell.getSheet().getWorkbook()
					.getCreationHelper().createFormulaEvaluator();
			DataFormatter dataFormatter = new DataFormatter();
			return dataFormatter.formatCellValue(cell, evaluator);
		}
		catch(Exception e){
			String columnName =  cell.getSheet().getRow(0).getCell(cell.getColumnIndex()).getStringCellValue();
			String message = "Srl No. : "+cell.getRowIndex()+", Row : "+(cell.getRowIndex()+1)+", Column: "+(cell.getColumnIndex()+1) +"("+columnName+")";
			errorList.add( "Prolem in " + message );
			throw e;
		}
	}
	
	private Date getDateValue(Cell cell, Boolean ignoreDateError) throws Exception {
		if ( cell == null){
			return null;
		}
		
		Date date = null;
		
		try{
			date = cell.getDateCellValue();
		} catch( Exception exception ){}
		
		if( date != null){
			return date;	
		}
		
		DataFormatter df = new DataFormatter();
		String str = null;
		try{
			str = df.formatCellValue(cell);
			if( str ==  null || str.trim().length() ==0){
				return null;
			}
			str = str.trim();
			str.replace("'", "");
			String formate = getDataFormat(str);
			
			SimpleDateFormat sdf = new SimpleDateFormat(formate);
			date = sdf.parse(str);
			return date;
		}
		catch(Exception e){
			String columnName =  cell.getSheet().getRow(0).getCell(cell.getColumnIndex()).getStringCellValue();
			String message = "[Srl No. : "+cell.getRowIndex()+", Row : "+(cell.getRowIndex()+1)+", Column: "+(cell.getColumnIndex()+1) +"("+columnName+"), Value:"+str+"]";
			errorList.add( "Prolem in " + message );
			
			if( !ignoreDateError ){
				throw e;
			}
			else{
				//exception.printStackTrace();
				log.error("",e);
				return null;
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
    
	public static void main(String[] args) throws ParseException, IOException {
    	
		String lstrExelFilePath = "/home/isdc/Desktop/Total Data.xlsx";
        InputStream inputStream = null;
        try {
        	
			inputStream = new BufferedInputStream(
					new FileInputStream(new File( lstrExelFilePath )));
			ExcelUtil excelUtil = new ExcelUtil(inputStream);
	        List<CustomerDetails> l = excelUtil.parse( false );
	        System.out.println( l.size() );
	        
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        finally{
        	inputStream.close();
        }
        
        
        //MongoCustomerDetailsDao mcd = new MongoCustomerDetailsDao();
       // for(CustomerDetails cs : l ){
        	//mcd.create( cs ); 
        //}
    	
        //for(String str : excelUtil.errorList){
        	//System.out.println(str);
        //}
    }
}
