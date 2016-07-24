package com.passbox.ui;

import java.awt.Insets;
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
import com.passbox.util.CodeUtil;
import com.passbox.util.SendEmail;


/**
 * 注册窗口
 * @author KeHao
 *
 */
public class RePasswdFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton login;//登录按钮
	private JButton send;//发送验证码
	private JButton create;//修改密码
	JTextField accountField;//用户名
	JTextField passCodeField;//验证码
	JPasswordField newPasswdField;//新密码
	private String checkCode=null; 
	RePasswdFrame() {
		this.setSize(400, 300);
		this.setTitle("重置密码");
		JPanel panel = new JPanel();//主界面面板
		panel.setLayout(null);
		JLabel account=new JLabel("用户名：");
		accountField=new JTextField();
		send = new JButton("验证码");
		send.setMargin(new Insets(0, 0, 0, 0));
		JLabel passCode=new JLabel("验证码：");
		passCodeField=new JTextField();
		JLabel privateKey=new JLabel("新密码：");
		newPasswdField=new JPasswordField();
		
		account.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.2), 60, 20);
		accountField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.2), 200, 20);
		send.setBounds((int)(this.getWidth()*0.1+280),(int)(this.getHeight()*0.2), 60, 20);
		passCode.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.2+40), 60, 20);
		passCodeField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.2+40), 200, 20);
		privateKey.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.2+80), 60, 20);
		newPasswdField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.2+80), 200, 20);
		
		
		login = new JButton("返回登录");
		login.setBounds((int) (this.getWidth()*0.2), this.getHeight()-80, 100, 30);
		create = new JButton("提交");
		create.setBounds((int) (this.getWidth()*0.8-100), this.getHeight()-80, 100, 30);
		login.addActionListener(this);
		send.addActionListener(this);
		create.addActionListener(this);
		panel.add(account);
		panel.add(accountField);
		panel.add(passCode);
		panel.add(passCodeField);
		panel.add(privateKey);
		panel.add(newPasswdField);
		panel.add(login);
		panel.add(send);
		panel.add(create);
		this.add(panel);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login) {
			this.setVisible(false);
			new LoginFrame();
		}
		if(e.getSource() == send){
			String username=accountField.getText().trim();
			final User user=UserService.findUserByName(username);
			final StringBuffer sb = new StringBuffer("<p>您的验证码是:"+"<span style='color: red'>");
			checkCode=CodeUtil.createCode(6);
			sb.append(checkCode+"</span></p>");
			// 发送邮件
			Thread t=new Thread (){//使用后台线程缩短响应时间
				public void run(){
					SendEmail.send(user.getEmail(), sb.toString());
					JOptionPane.showMessageDialog(null,"验证码已发送，请收取邮件","提示",JOptionPane.PLAIN_MESSAGE);
				}
			};
			t.start();
		}
		if (e.getSource() == create) {
			String username=accountField.getText().trim();
			String localCheckCode=passCodeField.getText().trim();
			@SuppressWarnings("deprecation")
			String newPasswd=newPasswdField.getText().trim();
			if(localCheckCode.equals(checkCode)){
				User user=new User();
				user.setUsername(username);
				user.setPassword(newPasswd);
//				System.out.println(user.getUsername()+" "+user.getPassword());
				if(UserService.UpdatePasswdByUserName(user)){
					JOptionPane.showMessageDialog(null,"重置密码完成，请使用新密码登录","提示",JOptionPane.PLAIN_MESSAGE);
					this.setVisible(false);
					new LoginFrame();
				}
			}
		}
	}
}
