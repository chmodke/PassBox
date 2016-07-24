package com.passbox.service;

import java.util.List;
import java.util.Vector;

import com.aes.util.AesUtil;
import com.passbox.dao.BookDao;
import com.passbox.entity.Book;
import com.passbox.entity.User;
import com.passbox.util.MD5;

public class BookService {
	/**
	 * 获取该用户下所有账号,并进行解码操作
	 * 
	 * @param userId
	 * @return
	 */
	public static Vector<Vector<String>> findAllByUser(User user) {
		String userId = user.getUser_id();
		String privateKey = user.getPrivite_key();
		List<Book> list = BookDao.findAllByUserId(userId);
		Vector<Vector<String>> data=new Vector<Vector<String>>();
		for (int i = 0; i < list.size(); i++) {
			Vector<String> book=new Vector<String>();
			book.add(i + 1+"");
			book.add(list.get(i).getAccount_name());
			book.add(AesUtil.decrypt(list.get(i).getAccount_passwd(), privateKey));
			book.add(list.get(i).getAccount_desc());
			book.add(list.get(i).getAccount_website());
			book.add(list.get(i).getAccount_phone());
			book.add(list.get(i).getBook_id());
			data.add(book);
		}
		return data;
	}

	public static boolean save(Book book) {
		String priKey = UserService.findByUserId(book.getUser_id()).getPrivite_key();
		Book b = book;
		b.setBook_id(MD5.createId());
		b.setAccount_passwd(AesUtil.encrypt(book.getAccount_passwd(), MD5.base64Decode(priKey)));
		return BookDao.save(b);
	}
	public static boolean deleteByBookid(String bookid){
		return BookDao.deleteByBookId(bookid);
	}
	public static boolean updateByBookid(Book book,String priKey){
		//加密处理
		Book temp=new Book();
		temp=book;
		temp.setAccount_passwd(AesUtil.encrypt(book.getAccount_passwd(), priKey));
		return BookDao.updateByBookid(temp);
	}
	public static Book findByBookid(String bookid,String priKey){
		Book book=new Book();
		Book temp=BookDao.findByBookId(bookid);
		book=temp;
		//解密处理
		book.setAccount_passwd(AesUtil.decrypt(temp.getAccount_passwd(),priKey));
		return book;
	}
//	public static void main(String[] args) {
//		Book t=new Book();
//		t.setUser_id("d5e08d44fe9d4f92851177ecd7a46dff");
//		t.setAccount_name("computer4");
//		t.setAccount_passwd("123456");
//		t.setAccount_desc("电脑4");
//		t.setAccount_website("www.mypc.com");
//		t.setAccount_phone("12312312312");
//		save(t);
//	}
}
