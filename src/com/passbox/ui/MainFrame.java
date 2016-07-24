package com.passbox.ui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;

import com.passbox.entity.User;
import com.passbox.service.BookService;

/**
 * 程序主窗口
 * @author KeHao
 *
 */
public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	MainPanel panel;
	MainFrame(final User user) {
		this.setTitle("密码本");
		this.setBounds(200, 50, 900, 600);
		this.setLayout(null);
		
		panel=new MainPanel(user);//主面板
		UserInfoStatusPanel userPanel=new UserInfoStatusPanel(user.getUsername());
		TimePanel timePanel=new TimePanel();
		CopyRightPanel copyPanel=new CopyRightPanel();
		panel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
		userPanel.setBounds(0,this.getHeight()-70 , userPanel.getWidth(), userPanel.getHeight());
		timePanel.setBounds(0+userPanel.getWidth()+10,this.getHeight()-70 , timePanel.getWidth(), timePanel.getHeight());
		copyPanel.setBounds(this.getWidth()-copyPanel.getWidth(),this.getHeight()-70 , copyPanel.getWidth(), copyPanel.getHeight());
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("选项");
		JMenuItem menuRefresh = new JMenuItem("刷新");
		JMenuItem menuLogout = new JMenuItem("注销");
		JMenuItem menuExit = new JMenuItem("退出");
		JMenu menuOper = new JMenu("操作");
		JMenuItem add = new JMenuItem("添加");
		JMenuItem delete = new JMenuItem("删除");
		JMenuItem update = new JMenuItem("修改");
		JMenu menuInfo = new JMenu("个人信息");
		JMenuItem look = new JMenuItem("查看信息");
		JMenuItem person = new JMenuItem("修改信息");
		menuBar.setBounds(0, 0, 900, 20);
		menuRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO 调用查询,刷新
				System.out.println("Hi refresh");
				panel=new MainPanel(user);
				panel.repaint();
			}
		});
		menuLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new LoginFrame();
			}
		});
		menuExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreateAccountFrame(user);
			}
		});
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JTable table=MainPanel.table;
				String col_id=table.getValueAt(table.getSelectedRow(), 6).toString();
				BookService.deleteByBookid(col_id);
				panel.delete(table.getSelectedRow());
			}
		});
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JTable table=MainPanel.table;
				String col_id=table.getValueAt(table.getSelectedRow(), 6).toString();
				new ModifyAccountFrame(col_id,user);
			}
		});
		look.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserInfoFrame(user);
			}
		});
		person.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ModifyUserInfoFrame(user);
			}
		});
		//添加面板
		this.add(panel);
		this.add(userPanel);
		this.add(timePanel);
		this.add(copyPanel);
		//添加菜单栏
		menuBar.add(menu);
		menuBar.add(menuOper);
		menuBar.add(menuInfo);
		menu.add(menuRefresh);
		menu.add(menuLogout);
		menu.add(menuExit);
		menuOper.add(add);
		menuOper.add(delete);
		menuOper.add(update);
		menuInfo.add(look);
		menuInfo.add(person);
		this.setJMenuBar(menuBar);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
//		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}
}
