package com.async.simulations;

public class SplitTest {
	public static void main(String[] args) {
		String str = "raj|is||here!!";
		
		String[] s = str.split("\\|");
		System.out.println(s);
	}
}
