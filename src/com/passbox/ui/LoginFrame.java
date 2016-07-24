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
import com.passbox.util.MD5;


/**
 * @author KeHao
 * 登录窗口
 */
public class LoginFrame extends JFrame  implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton login;//登录按钮
	private JButton create;//创建用户按钮
	private JButton forgetpwd;//忘记密码
	JTextField accountField;//用户名
	JPasswordField passwdField;//密码
	LoginFrame() {
		this.setSize(400, 300);
		this.setTitle("登录密码本");
		JPanel panel = new JPanel();//主界面面板
		panel.setLayout(null);
		JLabel account=new JLabel("用户名：");
		accountField=new JTextField();
		forgetpwd=new JButton("忘记密码");
		forgetpwd.setMargin(new Insets(0, 0, 0, 0));
		JLabel passwd=new JLabel("密   码：");
		passwdField=new JPasswordField();
		
		account.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.3), 60, 20);
		accountField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.3), 200, 20);
		forgetpwd.setBounds((int)(this.getWidth()*0.1+280),(int)(this.getHeight()*0.3), 60, 20);
		passwd.setBounds((int)(this.getWidth()*0.1),(int)(this.getHeight()*0.3+40), 60, 20);
		passwdField.setBounds((int)(this.getWidth()*0.1+70),(int)(this.getHeight()*0.3+40), 200, 20);
		
		login = new JButton("登录");
		login.setBounds((int) (this.getWidth()*0.2), this.getHeight()-80, 100, 30);
		create = new JButton("注册");
		create.setBounds((int) (this.getWidth()*0.8-100), this.getHeight()-80, 100, 30);
		login.addActionListener(this);
		forgetpwd.addActionListener(this);
		create.addActionListener(this);
		panel.add(account);
		panel.add(accountField);
		panel.add(forgetpwd);
		panel.add(passwd);
		panel.add(passwdField);
		panel.add(login);
		panel.add(create);
		this.add(panel);
		this.setLocationRelativeTo(null);
//		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String args[]) {
		new LoginFrame();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login) {
			String accout=accountField.getText().trim();
			@SuppressWarnings("deprecation")
			String passwd=passwdField.getText().trim();
			User user=UserService.findUserByName(accout);
			if(MD5.validate(passwd, user.getPassword())){
				this.setVisible(false);
				new MainFrame(user);
			}else{
				JOptionPane.showMessageDialog(null,"密码错误,请重试","提示",JOptionPane.PLAIN_MESSAGE);
				passwdField.setText("");
			}
		}
		if (e.getSource() == create) {
			this.setVisible(false);
			new CreateFrame();
		}
		if (e.getSource() == forgetpwd) {
			this.setVisible(false);
			new RePasswdFrame();
//			Desktop desk=Desktop.getDesktop();
//			try {
//				desk.browse(new URI("http://www.baidu.com/"));
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			} catch (URISyntaxException e1) {
//				e1.printStackTrace();
//			}
		}
	}
}
