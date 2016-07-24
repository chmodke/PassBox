package com.passbox.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * 时间面板
 * @author KeHao
 *
 */
public class TimePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	String time=null;
	public TimePanel() {
		final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
		this.setSize(200, 20);
		this.setLayout(null);
		final JLabel currentTime =new JLabel("当前时间:"+time);
		currentTime.setBounds(0,0,200,20);
		TimerTask task=new TimerTask() {
			@Override
			public void run() {
				time=sdf.format(new Date());
				currentTime.setText("当前时间:"+time);
				repaint();
			}
		};
		Timer timer=new Timer();
		timer.schedule(task, 0, 1000);
//		this.setBorder(BorderFactory.createLineBorder(Color.red, 2));
		this.add(currentTime);
		this.setVisible(true);
	}
}
