package datastructures;

public class Coluna {

	private ListaEncadeada<Celula> celulas = new ListaEncadeada<>();
	private String coluna;
	
	public String getColuna() {
		return coluna;
	}
	public void setColuna(String coluna) {
		this.coluna = coluna;
	}
}
