package com.passbox.ui;

import java.awt.Color;
import java.awt.Component;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 * @author KeHao
 * 自定义样式的表格
 */
public class MyJTable extends JTable {
	private static final long serialVersionUID = 1L;
	private Color selectionColor = new Color(207,228,249);//行选择颜色
    private Color evenRowColor = new Color(233,242,241);//奇数行颜色
    private Color oddRowColor = new Color(255,255,255);//偶数行颜色
    private Color gridColor = new Color(236,233,216);//网格颜色
    private int rowHeight = 24;//行高度
    
    Vector<Object> date = new Vector<Object>();
	Vector<Object> title = new Vector<Object>();
   
    @SuppressWarnings("rawtypes")
	public MyJTable(Vector date,Vector title){
       super(new DefaultTableModel(date,title){
    	private static final long serialVersionUID = 1L;
    	@Override
		public boolean isCellEditable(int row, int column)//重写isCellEditable方法，是表格可选不可编辑
           {
               return false;
           }
       });
       this.setGridColor(gridColor);
       this.setRowHeight(rowHeight);
    }
   
    public TableCellRenderer getCellRenderer(int row, int column) {
       return new MyCellRenderer();
    }
   
    class MyCellRenderer extends DefaultTableCellRenderer{
    	private static final long serialVersionUID = 1L;
		public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
        Component cell = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
        this.setColor(cell, table, isSelected, hasFocus, row, column);
            return cell;
        }
        /*
         * 设置颜色
         */
        private void setColor(Component component,JTable table,boolean isSelected,boolean hasFocus,int row,int column){
        if(isSelected){
            component.setBackground(selectionColor);
            setBorder(null);
        }else{
            if(row%2 == 0){
               component.setBackground(evenRowColor);  
            }else{
               component.setBackground(oddRowColor);
            }
        }
        }
    }
}