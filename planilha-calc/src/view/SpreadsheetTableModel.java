package view;

import javax.swing.table.AbstractTableModel;

import model.SpreadsheetModel;

public class SpreadsheetTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -7623344886451021182L;
	private SpreadsheetModel spreadsheet;

	public SpreadsheetTableModel(SpreadsheetModel spreadsheet) {
		this.spreadsheet = spreadsheet;
	}
	
	@Override
	public int getRowCount() {
		return 100;
	}

	@Override
	public int getColumnCount() {
		return 40;
	}

	private String translateIndexToColumnName(int index) {
		index--;
		String name = "";
		int b = 26;
		while (b <= index) {
			name = (char)(65 + (index % b)) + name;
			index /= b;
			index--;
		}
		name = (char)(65 + (index % b)) + name;
		return name;
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		if (column > 0) {
			return spreadsheet.getValue(getCellName(row,column)) + " ";
		}
		return row+1;
	}

	@Override
	public void setValueAt(Object o, int row, int column) {
		if (column > 0) {
			spreadsheet.setFormula(getCellName(row,column), o.toString());
		}
	}

	@Override
	public String getColumnName(int index) {
		if (index == 0)
			return " ";
		return translateIndexToColumnName(index);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		if (column >  0)
			return true;
		return false;
	}

	public String getFormula(int row, int column) {
		return spreadsheet.getFormula(getCellName(row,column));
	}

	private String getCellName(int row, int column) {
		return translateIndexToColumnName(column) + (row + 1);			
	}
}
