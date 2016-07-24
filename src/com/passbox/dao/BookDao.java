package com.passbox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.passbox.entity.Book;
import com.passbox.exception.DefineException;
import com.passbox.util.DBUtil;
import com.passbox.util.LogUtil;

/**
 * @author KeHao 密码DAO
 */
public class BookDao {
	/**
	 * 查找当前用户下所有账号
	 * 
	 * @param userId
	 * @return
	 */
	public static List<Book> findAllByUserId(String userId) {
		List<Book> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM book WHERE user_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			list = new ArrayList<Book>();
			if(!rs.next()){
				throw new DefineException("该用户无记录");
			}
			do{
				Book book = new Book();//这里实例化才正常
				book.setBook_id(rs.getString("book_id"));
				book.setAccount_name(rs.getString("account_name"));
				book.setAccount_passwd(rs.getString("account_passwd"));
				book.setAccount_desc(rs.getString("account_desc"));
				book.setAccount_website(rs.getString("account_website"));
				book.setAccount_phone(rs.getString("account_phone"));
				list.add(book);
			}while (rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DefineException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"提示",JOptionPane.PLAIN_MESSAGE);
			LogUtil.Error(e.getMessage());
		}
		return list;
	}

	/**
	 * 保存账号信息
	 * 
	 * @param book
	 * @return
	 */
	public static boolean save(Book book) {
		boolean flag = false;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO book (book_id,user_id,account_name,account_passwd,account_desc,account_website,account_phone) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, book.getBook_id());
			ps.setString(2, book.getUser_id());
			ps.setString(3, book.getAccount_name());
			ps.setString(4, book.getAccount_passwd());
			ps.setString(5, book.getAccount_desc());
			ps.setString(6, book.getAccount_website());
			ps.setString(7, book.getAccount_phone());
			int rs=ps.executeUpdate();
			if(rs>0){
				JOptionPane.showMessageDialog(null,"添加成功","提示",JOptionPane.PLAIN_MESSAGE);
				flag=true;
			}else{
				throw new DefineException("添加记录失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DefineException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"提示",JOptionPane.PLAIN_MESSAGE);
			LogUtil.Error(e.getMessage());
		}
		return flag;
	}
	
	public static boolean deleteByBookId(String bookid){
		boolean flag=false;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM book WHERE book_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, bookid);
			int rs=ps.executeUpdate();
			if(rs>0){
				JOptionPane.showMessageDialog(null,"删除成功","提示",JOptionPane.PLAIN_MESSAGE);
				flag=true;
			}else{
				throw new DefineException("删除记录失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DefineException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"提示",JOptionPane.PLAIN_MESSAGE);
			LogUtil.Error(e.getMessage());
		}
		return flag;
	}
	public static boolean updateByBookid(Book book){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE book SET user_id=?,account_name=?,account_passwd=?,account_desc=?,account_website=?,account_phone=? WHERE book_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, book.getUser_id());
			ps.setString(2, book.getAccount_name());
			ps.setString(3, book.getAccount_passwd());
			ps.setString(4, book.getAccount_desc());
			ps.setString(5, book.getAccount_website());
			ps.setString(6, book.getAccount_phone());
			ps.setString(7, book.getBook_id());
			int rs=ps.executeUpdate();
			if(rs>0){
				JOptionPane.showMessageDialog(null,"修改成功","提示",JOptionPane.PLAIN_MESSAGE);
				flag=true;
			}else{
				throw new DefineException("修改记录失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DefineException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"提示",JOptionPane.PLAIN_MESSAGE);
			LogUtil.Error(e.getMessage());
		}
		return flag;
	}
	public static Book findByBookId(String bookid){
		Book book=new Book();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM book WHERE book_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, bookid);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()){
				throw new DefineException("该用户无记录");
			}
			do{
				book.setBook_id(rs.getString("book_id"));
				book.setUser_id(rs.getString("user_id"));
				book.setAccount_name(rs.getString("account_name"));
				book.setAccount_passwd(rs.getString("account_passwd"));
				book.setAccount_desc(rs.getString("account_desc"));
				book.setAccount_website(rs.getString("account_website"));
				book.setAccount_phone(rs.getString("account_phone"));
			}while (rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DefineException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"提示",JOptionPane.PLAIN_MESSAGE);
			LogUtil.Error(e.getMessage());
		}
		return book;
	}
}
