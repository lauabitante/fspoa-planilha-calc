package datastructures;


public class Node <T> {

	private T valor;
	private Node<T> proximo;

	// Cria um novo nó 
	public Node (T value) {
		this.valor = value;
	}

	// Retorna o dado que o nó guarda
	public T valor () {
		return valor;
	}

	// Retorna o próximo nó
	public Node<T> proximo () {
		return proximo;
	}

	// Seta o próximo nó
	public void setProximo(Node<T> next) {
		this.proximo = next;
	}
}