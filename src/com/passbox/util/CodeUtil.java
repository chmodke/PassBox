package com.passbox.util;

import java.util.Random;

public class CodeUtil {
	public static String createCode(int n){
		Random rand=new Random();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<n;i++){
			sb.append(rand.nextInt(10)+"");
		}
		return sb.toString();
	}
//	public static void main(String[] args) {
//		System.out.println(createCode(6));
//	}
}
