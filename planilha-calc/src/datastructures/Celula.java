package datastructures;

public class Celula {

	public Formula formula = new Formula();
	private String celula;

	public Celula(String celula) {
		this.celula = celula;
		this.formula.setFormula("");
	}
	
	public String getCelula() {
		return celula;
	}
	public void setCelula(String celula) {
		this.celula = celula;
	}

}
