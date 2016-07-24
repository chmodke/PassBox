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

import com.passbox.entity.User;
import com.passbox.service.UserService;
import com.passbox.util.MD5;


/**
 * 用户注册窗口
 * @author KeHao
 *
 */
public class CreateFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton login;//登录按钮
	private JButton create;//创建用户按钮
	JTextField accountField;//用户名
	JPasswordField passwdField;//密码
	JPasswordField privateKeyField;//私钥
	JTextField emailField;//邮箱
	CreateFrame() {
		this.setSize(400, 300);
		this.setTitle("注册");
		JPanel panel = new JPanel();//主界面面板
		panel.setLayout(null);
		JLabel account=new JLabel("用户名：");
		accountField=new JTextField();
		JLabel passwd=new JLabel("密   码：");
		passwdField=new JPasswordField();
		JLabel privateKey=new JLabel("私   钥：");
		privateKeyField=new JPasswordField();
		JLabel email=new JLabel("邮   箱：");
		emailField=new JTextField();
		
		account.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.1), 60, 20);
		accountField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.1), 200, 20);
		passwd.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.1+40), 60, 20);
		passwdField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.1+40), 200, 20);
		privateKey.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.1+80), 60, 20);
		privateKeyField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.1+80), 200, 20);
		email.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.1+120), 60, 20);
		emailField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.1+120), 200, 20);
		
		
		login = new JButton("返回登录");
		login.setBounds((int) (this.getWidth()*0.2), this.getHeight()-80, 100, 30);
		create = new JButton("提交");
		create.setBounds((int) (this.getWidth()*0.8-100), this.getHeight()-80, 100, 30);
		login.addActionListener(this);
		create.addActionListener(this);
		panel.add(account);
		panel.add(accountField);
		panel.add(passwd);
		panel.add(passwdField);
		panel.add(privateKey);
		panel.add(privateKeyField);
		panel.add(email);
		panel.add(emailField);
		panel.add(login);
		panel.add(create);
		this.add(panel);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login) {
			this.setVisible(false);
			new LoginFrame();
		}
		if (e.getSource() == create) {
			//注册操作
			String account=accountField.getText().trim();
			String passwd=passwdField.getText().trim();
			String privateKey=privateKeyField.getText().trim();
			String email=emailField.getText().trim();
			if(account.equals("")||passwd.equals("")||privateKey.equals("")||email.equals("")){
				JOptionPane.showMessageDialog(null,"项目不可为空，请检查","提示",JOptionPane.PLAIN_MESSAGE);
				return;
			}
			User user=new User();
			user.setUser_id(MD5.createId());
			user.setUsername(account);
			user.setPassword(passwd);
			user.setPrivite_key(privateKey);
			user.setEmail(email);
			if(UserService.save(user)){
				JOptionPane.showMessageDialog(null,"注册成功","提示",JOptionPane.PLAIN_MESSAGE);
				this.setVisible(false);
				new LoginFrame();
			}else{
				JOptionPane.showMessageDialog(null,"用户名已存在,请重试","提示",JOptionPane.PLAIN_MESSAGE);
				accountField.setText("");
			}
		}
	}
}
