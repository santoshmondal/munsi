package com.isdc.simulations;

import java.util.regex.Pattern;


public class TestObject {
	
	public static void main(String[] args) {
		
		System.out.println(Pattern.compile(".*myValue.*" , Pattern.CASE_INSENSITIVE));
		
	}
	
	static Object getIns(){
		try {
			Class<?> clazz = Class.forName(String.class.getName());
			return clazz.newInstance();
			//return null;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
