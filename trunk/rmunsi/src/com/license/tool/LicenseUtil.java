package com.license.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class LicenseUtil {
	
	public static final String ENCODING_UTF8 = "UTF-8";
	public static final String LIC_DATE_FORMAT = "dd-MM-yyyy";
	
	public static String licenseToXmlString(License license){
        try {
            
            JAXBContext jaxbContext = JAXBContext.newInstance(License.class);
            
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
     
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter stringWriter = new StringWriter();
            jaxbMarshaller.marshal(license, stringWriter);
            //System.out.println( stringWriter.toString() );
            return stringWriter.toString();
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        
        
        return "";
    }
    
    
    public static License xmlStringToLicense(String xmlString){
        try {
            
            JAXBContext jaxbContext = JAXBContext.newInstance(License.class);
            
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
     
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            
            StringReader stringReader = new StringReader(xmlString);
            
            License license = (License) jaxbUnmarshaller.unmarshal( stringReader );
            return license;
     
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        
        return null;
    }

	public static String getEncryptionKey() {
		String key = LicenseUtil.class.getName().toString();
		String licenseEncKey = Encryption.md5Encrypt( key );
		licenseEncKey = licenseEncKey.substring(0, 16);
		return licenseEncKey;
	}

	public static void generateLicenseFile(String licenseFile, License license) {

		FileOutputStream out = null;
		try {
			out =  new FileOutputStream( licenseFile );
			String message = licenseToXmlString(license);
			String licenseEncKey = LicenseUtil.getEncryptionKey();
			byte[] encMessage = Encryption.encryptMessage( message, licenseEncKey );
			out.write(encMessage);
			out.flush();
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
		finally {
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public static License readLicenseFile( File file ) {
		FileInputStream fios = null;
		try {
			fios = new FileInputStream( file );
			byte [] fileByte = new byte[fios.available()];
			fios.read(fileByte);
			fios.close();
			String licenseEncKey = LicenseUtil.getEncryptionKey();
			String xmlString = Encryption.decryptMessage( fileByte, licenseEncKey );
			if( xmlString != null &&xmlString.trim().length() > 0 ){
				License license = LicenseUtil.xmlStringToLicense( xmlString );
				return  license;
			}
		}
		catch (Exception exception) {
			Global.LOG.error("Problem to read license file",exception);
		}
		finally {
			if(fios != null){
				try {
					fios.close();
				} 
				catch (IOException e) {
					Global.LOG.info("Problem to close license file");
				}
			}
		}
		return null;
	}
	
}
