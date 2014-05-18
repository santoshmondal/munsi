package com.async.simulations;

enum TEST {
	A("a1"),
	B("b2"),
	C("c3");
	
	String value;
	
	private TEST(String string) {
		value = string;
	}
	
	@Override
	public String toString() {
		return value;
	}

	 public static String getEnumName(String value) {
		 for ( TEST d : TEST.values() ){
			 if( d.toString().equals( value ) ){
				 return d.name();
			 }
         }
		 return "";
	 }
}
public class EnumTest {

	public static void main(String[] args) {
		
		System.out.println("abc,jpg".split(",")[0]);
		
		//System.out.println(TEST.getEnumName("a1"));
	}
	
}
