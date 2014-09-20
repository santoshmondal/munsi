package com.license.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandUtil {

	public static String executeCommand( String command ) {
		InputStream inputStream = null;
		try {
			Process process = Runtime.getRuntime().exec( command );
			try {
				process.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			inputStream = process.getInputStream();
			process.destroy();
			String output = getStringFromInputStream(inputStream);
			
			return output;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ""; 
			
	}
	
	// convert InputStream to String
	private static String getStringFromInputStream(InputStream is) {
 		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line = "";
		try {
 			br = new BufferedReader(new InputStreamReader(is));
			while ( (line = br.readLine()) != null ) {
				if( line.trim().length() > 0 ){
					sb.append(line.trim());
				}
			}
 
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
		return sb.toString();
	}
}
