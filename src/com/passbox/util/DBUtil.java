package com.passbox.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 数据库连接公用模块
 * 使用连接池
 * @author KeHao
 *
 */
public class DBUtil {
	private static String drivername;
	private static String url;
	private static BasicDataSource cp;
	static{
		/**
		 * 静态块由于是在类第一次加载时候执行，且只会执行一次，所以在这里初始化静态属性最合适。
		 */
		Properties prop=new Properties();
		try {
			prop.load(new FileInputStream("config.properties"));
			/**
			 * 根据配置文件初始化
			 */
			drivername=prop.getProperty("driverName");
			url=prop.getProperty("URL");
			cp=new BasicDataSource();
			cp.setDriverClassName(drivername);
			cp.setUrl(url);
		} catch (FileNotFoundException e) {
			LogUtil.Error("配置文件不存在 "+e.getMessage());
			JOptionPane.showMessageDialog(null,"检查数据库配置文件是否存在","提示",JOptionPane.PLAIN_MESSAGE);
		} catch (Exception e) {
			LogUtil.Error("DBUtil数据库连接异常 "+e.getMessage());
			JOptionPane.showMessageDialog(null,"检查数据库配置参数是否正确","提示",JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	/**
	 * 获取一个数据库连接
	 * @return
	 * @throws SQLException SQL异常
	 */
	public static Connection getConnection() throws SQLException{
		/**
		 * 向连接池要一条可用的空闲连接，若连接池尚有可用连接，会直接返回，若没有，则该方法进入阻塞状态，等待可用连接。
		 * 等待时间与初始化连接池时设置的maxWait的时间一致
		 * 若等待期间出现可用连接，则该方法会立即返回该连接，若等待时间超出，仍没有可用连接，该方法会抛出超时异常
		 */
		return cp.getConnection();
	}
	/**
	 * 关闭数据库链接
	 * @param conn
	 */
	public static void closeConnection(Connection conn){
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (Exception e) {
			LogUtil.Error("关闭数据库异常 "+e.getMessage());
		}
	} 
}
