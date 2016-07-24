package com.passbox.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 用来写入读取日志（序列）
 * @author kehao
 *
 */
public class LogUtil {
	/**
	 * 写出日志
	 * @param context
	 * @return
	 */
	public static boolean WriteLog(String context){
		boolean flag=false;
		Date time=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String temp=sdf.format(time)+" "+context+'\n';
		PrintWriter pw=null;
		try {
			File file=new File(".log.x");
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream out=new FileOutputStream(file,true);
			OutputStreamWriter osw =new OutputStreamWriter(out,"utf-8");
			pw=new PrintWriter(osw,true);
			pw.print(temp);
			temp=null;
			flag=true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			pw.close();
		}
		return flag;
	}
	public static void Error(String context){
		Date time=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String temp=sdf.format(time)+" "+context+'\n';
		PrintWriter pw=null;
		try {
			File file=new File(".error.x");
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream out=new FileOutputStream(file,true);
			OutputStreamWriter osw =new OutputStreamWriter(out,"utf-8");
			pw=new PrintWriter(osw,true);
			pw.print(temp);
			temp=null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			pw.close();
		}
	}
}
