package com.passbox.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.passbox.entity.User;


/**
 * 展示用户信息窗口
 * @author KeHao
 *
 */
public class UserInfoFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton back;//返回按钮
	JTextField accountField;//用户名
//	JPasswordField passwdField;//密码
	JTextField privateKeyField;//私钥
	JTextField emailField;//邮箱
	UserInfoFrame(User user) {
		this.setSize(400, 300);
		this.setTitle("用户信息面板");
		JPanel panel = new JPanel();//主界面面板
		panel.setLayout(null);
		JLabel account=new JLabel("用户名：");
		accountField=new JTextField();
//		JLabel passwd=new JLabel("密   码：");
//		passwdField=new JPasswordField();
		JLabel privateKey=new JLabel("私   钥：");
		privateKeyField=new JTextField();
		JLabel email=new JLabel("邮   箱：");
		emailField=new JTextField();
		
		accountField.setEditable(false);
//		passwdField.setEditable(false);
		privateKeyField.setEditable(false);
		emailField.setEditable(false);
		
		info(user);
		
		account.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.1), 60, 20);
		accountField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.1), 200, 20);
		privateKey.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.1+40), 60, 20);
		privateKeyField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.1+40), 200, 20);
		email.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.1+80), 60, 20);
		emailField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.1+80), 200, 20);
//		email.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.1+120), 60, 20);
//		emailField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.1+120), 200, 20);
		
		
		back = new JButton("返回");
		back.setBounds((int) (this.getWidth()*0.4), this.getHeight()-80, 100, 30);
		back.addActionListener(this);
		panel.add(account);
		panel.add(accountField);
//		panel.add(passwd);
//		panel.add(passwdField);
		panel.add(privateKey);
		panel.add(privateKeyField);
		panel.add(email);
		panel.add(emailField);
		panel.add(back);
		this.add(panel);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			this.setVisible(false);
		}
	}
	public void info(User user){
		accountField.setText(user.getUsername());
//		passwdField.setText(user.getPassword());
		privateKeyField.setText(user.getPrivite_key());
		emailField.setText(user.getEmail());
	}
}
