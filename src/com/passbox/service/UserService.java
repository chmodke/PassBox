package com.passbox.service;

import com.passbox.dao.UserDao;
import com.passbox.entity.User;
import com.passbox.util.MD5;

public class UserService {
	/**
	 * 根据用户ID查找用户
	 * @param UserName
	 * @return
	 */
	public static User findByUserId(String UserId){
		return UserDao.findByUserId(UserId);
	}
	/**
	 * 根据用户名查找用户
	 * @param UserName
	 * @return
	 */
	public static User find(String UserName){
		User user=new User();
		User temp=UserDao.findUserByName(UserName);
		user.setUser_id(temp.getUser_id());
		user.setUsername(temp.getUsername());
		user.setPassword(temp.getPassword());//不可逆
		user.setPrivite_key(MD5.base64Decode(temp.getPrivite_key()));//明文
		user.setEmail(temp.getEmail());
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
		User temp=new User();
		
		temp.setUser_id(user.getUser_id());
		temp.setUsername(user.getUsername());
		temp.setPassword(MD5.md5(user.getPassword()));
		temp.setPrivite_key(MD5.base64Encode(user.getPrivite_key()));
		temp.setEmail(user.getEmail());
		
		return UserDao.save(temp);
	}
	
	/**
	 * 修改用户信息
	 * 注：用户名不可以修改
	 * @param user
	 * @return
	 */
	public static boolean UpdateByUserName(User user){
		User temp=new User();
		temp.setUser_id(user.getUser_id());
		temp.setUsername(user.getUsername());
		temp.setPrivite_key(MD5.base64Encode(user.getPrivite_key()));
		temp.setEmail(user.getEmail());
		
		return UserDao.UpdateByUserName(temp);
	}
	/**
	 * 修改用户密码
	 * 注：用户名不可以修改
	 * @param user
	 * @return
	 */
	public static boolean UpdatePasswdByUserName(User user){
		User temp=new User();
		temp.setUsername(user.getUsername());
		temp.setPassword(MD5.md5(user.getPassword()));
		return UserDao.UpdatePasswdByUserName(temp);
	}
}
