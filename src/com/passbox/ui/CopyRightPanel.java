package com.passbox.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CopyRightPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	public CopyRightPanel() {
		this.setSize(130, 20);
		this.setLayout(null);
		JLabel version =new JLabel("版本:1.0.0");
		JLabel copy =new JLabel("版权:柯昊");
		version.setBounds(0,0,60,20);
		copy.setBounds(0+70,0,60,20);
//		this.setBorder(BorderFactory.createLineBorder(Color.red, 2));
		this.add(version);
		this.add(copy);
		this.setVisible(true);
	}
}
