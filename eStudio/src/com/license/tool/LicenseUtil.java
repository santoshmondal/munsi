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
	public static String ENCODING_UTF8 = "UTF-8";
	
	public static String licenseToXmlString(License license){
        try {
            
            JAXBContext jaxbContext = JAXBContext.newInstance(License.class);
            
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
     
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter stringWriter = new StringWriter();
            jaxbMarshaller.marshal(license, stringWriter);
            System.out.println( stringWriter.toString() );
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

	public static String getKeyEncKey() {
		return LicenseUtil.class.getName().toString();
	}

	public static void generateLicenseFile(String licenseFile, License license) {

		FileOutputStream out = null;
		try {
			out =  new FileOutputStream( licenseFile );
			String message = LicenseUtil.licenseToXmlString(license);
			String licenseEncKey = Encryption.md5Encrypt( LicenseUtil.getKeyEncKey() );
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
			String licenseEncKey = Encryption.md5Encrypt( LicenseUtil.getKeyEncKey() );
			String xmlString = Encryption.decryptMessage( fileByte, licenseEncKey );
			License license = LicenseUtil.xmlStringToLicense( xmlString );
			return  license;
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
		finally {
			if(fios != null){
				try {
					fios.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/*
	private static String getEncriptionKey(){
		String KEY_FILE = "com"+File.separator+"license"+File.separator+"tool"+File.separator+"util"+File.separator+"KeyUtil.class";
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream( KEY_FILE );
		try {
			byte [] fileByte = new byte[inputStream.available()];
			inputStream.read(fileByte);
			inputStream.close();
			String keyEncKey = Encryption.md5Encrypt( LicenseUtil.getKeyEncKey() );
			String licenseEncKey = Encryption.decryptMessage(fileByte, keyEncKey);
			return licenseEncKey;
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
		finally {
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	*/
}
