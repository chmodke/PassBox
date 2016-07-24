package com.passbox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.JOptionPane;

import com.aes.util.AesUtil;
import com.passbox.entity.Book;
import com.passbox.entity.User;
import com.passbox.exception.DefineException;
import com.passbox.service.BookService;
import com.passbox.util.DBUtil;
import com.passbox.util.LogUtil;
import com.passbox.util.MD5;

/**
 * @author KeHao
 * 用户DAO
 */
public class UserDao {
	/**
	 * 根据用户ID查找用户
	 * @param UserName
	 * @return
	 */
	public static User findByUserId(String UserId){
		User user=null;
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * from user where user_id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, UserId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				user=new User();
				user.setUser_id(rs.getString("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setPrivite_key(rs.getString("privite_key"));
				user.setEmail(rs.getString("email"));
			}else{
				throw new DefineException("用户不存在！");
			}
		} catch (DefineException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"提示",JOptionPane.PLAIN_MESSAGE);
			LogUtil.Error(e.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally{
			DBUtil.closeConnection(conn);
		}
		return user;
	}
	/**
	 * 根据用户名查找用户
	 * @param UserName
	 * @return
	 */
	private static User find(String UserName){
		User user=null;
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * from user where username=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, UserName);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				user=new User();
				user.setUser_id(rs.getString("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setPrivite_key(rs.getString("privite_key"));
				user.setEmail(rs.getString("email"));
			}else{
				throw new DefineException("用户名不存在！");
			}
		} catch (DefineException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"提示",JOptionPane.PLAIN_MESSAGE);
			LogUtil.Error(e.getMessage());
		}catch (Exception e1) {
			e1.printStackTrace();
		} finally{
			DBUtil.closeConnection(conn);
		}
		return user;
	}
	
	
	/**
	 * 根据用户名查找用户
	 * @param UserName
	 * @return
	 */
	public static User findUserByName(String UserName){
		return find(UserName);
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public static boolean save(User user){
		boolean flag=false;
		Connection conn=null;
		try {
			if(find(user.getUsername())!=null){
				throw new DefineException("用户名已存在");
			}
			conn=DBUtil.getConnection();
			String save="INSERT INTO user (user_id,username,password,privite_key,email) VALUES (?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(save);
			ps.setString(1, user.getUser_id());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());//md5不可逆加密
			ps.setString(4, user.getPrivite_key());//base64可逆加密
			ps.setString(5, user.getEmail());
			int rs=ps.executeUpdate();
			if(rs<0){
				throw new DefineException("添加用户失败");
			}
			flag=true;
		} catch (DefineException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"提示",JOptionPane.PLAIN_MESSAGE);
			LogUtil.Error(e.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally{
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * 修改用户信息
	 * 注：用户名不可以修改
	 * @param user
	 * @return
	 */
	public static boolean UpdateByUserName(User user){
		boolean flag=false;
		Connection conn=null;
		User temp=UserDao.findUserByName(user.getUsername());
		String user_id=temp.getUser_id();
		String perDecodePriKey=temp.getPrivite_key();//修改之前的私钥，密文
		String user_priKey=MD5.base64Decode(perDecodePriKey);//修改之前的私钥，明文
		try {
			if(find(user.getUsername())==null){
				throw new DefineException("用户名不存在");
			}
			conn=DBUtil.getConnection();
			String update="UPDATE user SET privite_key=?,email=? WHERE username=?";
			PreparedStatement ps=conn.prepareStatement(update);
			ps.setString(1, user.getPrivite_key());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getUsername());
			int rs=ps.executeUpdate();
			if(rs<0){
				throw new DefineException("修改用户失败");
			}
			flag=true;
			if(!user_priKey.equals(user.getPrivite_key())){
				List<Book> data=BookDao.findAllByUserId(user_id);
				for(int i=0;i<data.size();i++){
					Book t=data.get(i);
					t.setUser_id(user_id);
					t.setAccount_passwd(AesUtil.decrypt(t.getAccount_passwd(), user_priKey));
					BookService.updateByBookid(t, MD5.base64Decode(user.getPrivite_key()));
				}
			}
		} catch (DefineException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"提示",JOptionPane.PLAIN_MESSAGE);
			LogUtil.Error(e.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally{
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	/**
	 * 修改用户密码
	 * 注：用户名不可以修改
	 * @param user
	 * @return
	 */
	public static boolean UpdatePasswdByUserName(User user){
		boolean flag=false;
		Connection conn=null;
		try {
			if(find(user.getUsername())==null){
				throw new DefineException("用户名不存在");
			}
			conn=DBUtil.getConnection();
			String update="UPDATE user SET password=?WHERE username=?";
			PreparedStatement ps=conn.prepareStatement(update);
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getUsername());
			int rs=ps.executeUpdate();
			if(rs<0){
				throw new DefineException("修改密码失败");
			}
			flag=true;
		} catch (DefineException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"提示",JOptionPane.PLAIN_MESSAGE);
			LogUtil.Error(e.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally{
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
}
