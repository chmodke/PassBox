package com.passbox.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.passbox.entity.Book;
import com.passbox.entity.User;
import com.passbox.service.BookService;

/**
 * 添加账号窗口
 * @author KeHao
 *
 */
public class CreateAccountFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton back;//返回
	private JButton create;//创建用户按钮
	private User user;
	
	JTextField accountField;//用户名
	JPasswordField passwdField;//密码
	JTextField descField;//账户描述
	JTextField websiteField;//网址
	JTextField phoneField;//手机号
	CreateAccountFrame(User user) {
		this.user=user;
		this.setSize(400, 300);
		this.setTitle("添加账户");
		JPanel panel = new JPanel();//主界面面板
		panel.setLayout(null);
		JLabel account=new JLabel("用户名：");
		accountField=new JTextField();
		JLabel passwd=new JLabel("密   码：");
		passwdField=new JPasswordField();
		JLabel desc=new JLabel("描   述：");
		descField=new JTextField();
		JLabel website=new JLabel("网   址：");
		websiteField=new JTextField();
		JLabel phone=new JLabel("手机号：");
		phoneField=new JTextField();
		
		account.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.1), 60, 20);
		accountField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.1), 200, 20);
		passwd.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.1+40), 60, 20);
		passwdField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.1+40), 200, 20);
		
		desc.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.1+80), 60, 20);
		descField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.1+80), 200, 20);
		website.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.1+120), 60, 20);
		websiteField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.1+120), 200, 20);
		phone.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.1+160), 60, 20);
		phoneField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.1+160), 200, 20);
		
		back = new JButton("返回");
		back.setBounds((int) (this.getWidth()*0.2), this.getHeight()-80, 100, 30);
		create = new JButton("添加");
		create.setBounds((int) (this.getWidth()*0.8-100), this.getHeight()-80, 100, 30);
		back.addActionListener(this);
		create.addActionListener(this);
		panel.add(account);
		panel.add(accountField);
		panel.add(passwd);
		panel.add(passwdField);
		
		panel.add(desc);
		panel.add(descField);
		panel.add(website);
		panel.add(websiteField);
		panel.add(phone);
		panel.add(phoneField);
		
		panel.add(back);
		panel.add(create);
		this.add(panel);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==back){
			this.setVisible(false);
		}
		if(e.getSource()==create){
			String accountName=accountField.getText().trim();
			@SuppressWarnings("deprecation")
			String accounPasswd=passwdField.getText().trim();
			String accountDesc=descField.getText().trim();
			String accountWeb=websiteField.getText().trim();
			String accountPhone=phoneField.getText().trim();
			if(accountName.equals("")||accounPasswd.equals("")||accountDesc.equals("")||accountWeb.equals("")||accountPhone.equals("")){
				JOptionPane.showMessageDialog(null,"项目不可为空,请重试","提示",JOptionPane.PLAIN_MESSAGE);
			}else{
				Book book=new Book();
				book.setUser_id(user.getUser_id());
				book.setAccount_name(accountName);
				book.setAccount_passwd(accounPasswd);
				book.setAccount_desc(accountDesc);
				book.setAccount_website(accountWeb);
				book.setAccount_phone(accountPhone);
				BookService.save(book);
				//刷新界面
				MainPanel.add(book, user.getPrivite_key());
			}
		}
	}
}
