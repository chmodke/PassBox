package com.passbox.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserInfoStatusPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	public UserInfoStatusPanel(String user) {
		this.setSize(200, 20);
		this.setLayout(null);
		JLabel username =new JLabel("当前用户:"+user);
		username.setBounds(0,0,200,20);
//		this.setBorder(BorderFactory.createLineBorder(Color.red, 2));
		this.add(username);
		this.setVisible(true);
	}
}
