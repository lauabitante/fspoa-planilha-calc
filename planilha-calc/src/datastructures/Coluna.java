package datastructures;

public class Coluna {

	private ListaEncadeada<Celula> celulas = new ListaEncadeada<>();
	private String coluna;
	
	public Coluna (String coluna) {
		this.coluna = coluna;
	}
	
	public String getColuna() {
		return coluna;
	}
	public void setColuna(String coluna) {
		this.coluna = coluna;
	}
	
	// Search for a cell with a given name. E.g: "A1", "K2", and so on.
	public Celula getCelula(String cellName){
		Node<Celula> node = celulas.primeiro;
		for(int i=0; i < celulas.totalDeElementos; i++){
			if(node.valor().getCelula().equals(cellName)){
				return node.valor();
			}
			else{
				node = node.proximo();
			}
		}
		// In case of no cells with this name, create a new cell and add it to the cells list.
		Celula novaCelula = new Celula(cellName);
		celulas.adiciona(novaCelula);
		return novaCelula;
	}
}
