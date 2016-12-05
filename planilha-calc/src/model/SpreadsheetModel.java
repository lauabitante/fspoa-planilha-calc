package model;

public interface SpreadsheetModel {
	String getValue(String cell);
	String getFormula(String cell);
	void setFormula(String cell, String formula);
}
