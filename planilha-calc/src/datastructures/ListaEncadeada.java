package datastructures;


public class ListaEncadeada <T> {

	public Node<T> primeiro = null;
	public Node<T> ultimo = null;
	public int totalDeElementos = 0;

	public void adiciona(T elemento) {

		Node<T> novo = new Node<T>(elemento);

		if(this.totalDeElementos == 0){
			this.primeiro = novo;
			this.ultimo = novo;
		} else {
			this.ultimo.setProximo(novo);
			this.ultimo = novo;
		}
		this.totalDeElementos++;
	}

	public void remove(T elemento) {

		Node<T> atual = primeiro;

		// Verifica se o primeiro nó é o elemento
		if (atual.valor().equals(elemento)) {
			// O primeiro elemento passa a ser o próximo.
			primeiro = atual.proximo();
			this.totalDeElementos--;
		} else {
			for (int i = 0; i < this.totalDeElementos; i++) {
				
				// Se o próximo elemento for o desejado
				if(atual.proximo().valor().equals(elemento)) {
					
					// Pega o próximo elemento após o desejado
					Node<T> proximo = atual.proximo();
					
					// Seta como próximo do elemento atual
					atual.setProximo(proximo.proximo());
					
					if(atual.proximo() == null){
						this.ultimo = atual;
					}
					
					this.totalDeElementos--;
					break;
				} else {
					// Passa para o próximo nó.
					atual = atual.proximo();
				}
			}
		}
	}
	
	public Node<T> getElemento(T dado) {
		Node<T> node = primeiro;
		for(int i=1; i<totalDeElementos + 1; i++){
			if(node == dado){
				return node;
			}
			else{
				node = node.proximo();
			}
		}
		return null;
	}
	
	// Retorna o tamanho da lista
	public int tamanho() {
		return this.totalDeElementos;
	}

	public String toString() {
		// Verificando se a Lista está vazia
		if(this.totalDeElementos == 0){
			return "[]";
		}

		StringBuilder builder = new StringBuilder("[");
		Node<T> atual = primeiro;

		// Percorrendo até o penúltimo elemento.
		for (int i = 0; i < this.totalDeElementos - 1; i++) {
			builder.append(atual.valor());
			builder.append("\n");
			atual = atual.proximo();
		}

		// último elemento
		builder.append(atual.valor());
		builder.append("]");

		return builder.toString();
	}
}