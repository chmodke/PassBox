package com.passbox.ui;

import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.aes.util.AesUtil;
import com.passbox.entity.Book;
import com.passbox.entity.User;
import com.passbox.service.BookService;

/**
 * 主面板
 * @author KeHao
 *
 */
public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static MyJTable table;
	public static Vector<Vector<String>> tableData;

	public MainPanel(User user) {
		this.setBounds(0, 0, 900, 530);
		this.setLayout(null);
		// 定义二维数组作为表格数据
		tableData = BookService.findAllByUser(user);
		// 定义一维数据作为列标题
		Vector<String> columnTitle=new Vector<String>();
		columnTitle.add("序号");
		columnTitle.add("用户名");
		columnTitle.add("密码");
		columnTitle.add("描述");
		columnTitle.add("网站");
		columnTitle.add("手机号");
		columnTitle.add("ID");
		
		table = new MyJTable(tableData, columnTitle);
		JTableHeader header = table.getTableHeader();
		for(int i=0;i<tableData.size();i++){
			for(int j=0;j<6;j++){
				table.isCellEditable(i, j);
			}
		}
		table.setBounds(0, 20, this.getWidth(), this.getHeight() - 50);
		header.setBounds(0, 0, table.getWidth(), 20);
		TableColumnModel colModel = table.getColumnModel();
		TableColumn col2 = colModel.getColumn(6);
		col2.setMinWidth(0);
		col2.setMaxWidth(0);
		col2.setWidth(0);
		col2.setPreferredWidth(0);

//		table.setBorder(BorderFactory.createLineBorder(Color.red, 2));
//		this.setBorder(BorderFactory.createLineBorder(Color.green, 2));
		this.add(header);
		this.add(table);
		this.setVisible(true);
	}

	public void paint(User user) {
		// 定义二维数组作为表格数据
		tableData = BookService.findAllByUser(user);
		tableData.remove(table.getSelectedRow());
		table.updateUI();
		this.repaint();
	}
	public static void add(Book book,String priKey){
		Vector<String> tmp = new Vector<String>();
		tmp.add(new Integer(tableData.size())+"");
		tmp.add(book.getAccount_name());
		tmp.add(AesUtil.decrypt(book.getAccount_passwd(), priKey));
		tmp.add(book.getAccount_desc());
		tmp.add(book.getAccount_website());
		tmp.add(book.getAccount_phone());
		tmp.add(book.getBook_id());
		tableData.add(tmp);
		table.updateUI();
	}
	public void delete(int rowNum) {
		// 定义二维数组作为表格数据
		tableData.remove(rowNum);
		table.updateUI();
		this.repaint();
	}
}