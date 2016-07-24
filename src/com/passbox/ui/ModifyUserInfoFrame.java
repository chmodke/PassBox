package com.passbox.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.passbox.entity.User;
import com.passbox.service.UserService;


/**
 * 用户注册窗口
 * @author KeHao
 *
 */
public class ModifyUserInfoFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton back;//返回按钮
	private JButton create;//创建用户按钮
	JTextField accountField;//用户名
//	JTextField passwdField;//密码
	JTextField privateKeyField;//私钥
	JTextField emailField;//邮箱
	private User user;
	ModifyUserInfoFrame(User user) {
		this.user=user;
		this.setSize(400, 300);
		this.setTitle("修改用户信息");
		JPanel panel = new JPanel();//主界面面板
		panel.setLayout(null);
		JLabel account=new JLabel("用户名：");
		accountField=new JTextField();
//		JLabel passwd=new JLabel("密   码：");
//		passwdField=new JTextField();
		JLabel privateKey=new JLabel("私   钥：");
		privateKeyField=new JTextField();
		JLabel email=new JLabel("邮   箱：");
		emailField=new JTextField();
		accountField.setEditable(false);
		
		account.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.1), 60, 20);
		accountField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.1), 200, 20);
		privateKey.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.1+40), 60, 20);
		privateKeyField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.1+40), 200, 20);
		email.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.1+80), 60, 20);
		emailField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.1+80), 200, 20);
//		email.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.1+120), 60, 20);
//		emailField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.1+120), 200, 20);
		
		info(user);
		
		back = new JButton("返回");
		back.setBounds((int) (this.getWidth()*0.2), this.getHeight()-80, 100, 30);
		create = new JButton("提交");
		create.setBounds((int) (this.getWidth()*0.8-100), this.getHeight()-80, 100, 30);
		back.addActionListener(this);
		create.addActionListener(this);
		panel.add(account);
		panel.add(accountField);
//		panel.add(passwd);
//		panel.add(passwdField);
		panel.add(privateKey);
		panel.add(privateKeyField);
		panel.add(email);
		panel.add(emailField);
		panel.add(back);
		panel.add(create);
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
		if (e.getSource() == create) {
			//修改操作
			String account=accountField.getText().trim();
			String privateKey=privateKeyField.getText().trim();
			String email=emailField.getText().trim();
			if(account.equals("")||privateKey.equals("")||email.equals("")){
				JOptionPane.showMessageDialog(null,"项目不可为空，请检查","提示",JOptionPane.PLAIN_MESSAGE);
				return;
			}
			User temp=user;
			temp.setUsername(account);
			temp.setPrivite_key(privateKey);
			temp.setEmail(email);
			if(UserService.UpdateByUserName(temp)){
				this.setVisible(false);
				//TODO 关闭窗口有问题
				new LoginFrame();
			}else{
//				JOptionPane.showMessageDialog(null,"修改失败,请重试","提示",JOptionPane.PLAIN_MESSAGE);
				accountField.setText("");
			}
		}
	}
	public void info(User user){
		accountField.setText(user.getUsername());
//		passwdField.setText(user.getPassword());
		privateKeyField.setText(user.getPrivite_key());
		emailField.setText(user.getEmail());
	}
}
