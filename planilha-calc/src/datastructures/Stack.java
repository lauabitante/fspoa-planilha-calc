package datastructures;

public class Stack<T> {

	private T[] data;
	private int top;
	
	@SuppressWarnings("unchecked")
	public Stack(int size) {
		this.data = (T[])new Object[size];
		this.top = 0;
	}

	public void push(T value) {
		if (isFull()) {
			throw new RuntimeException("Pilha cheia!");
		}
		data[top] = value;
		top++;
	}

	public T pop() {
		if (isEmpty()) {
			throw new RuntimeException("Pilha vazia!");
		}
		top--;
		return data[top];
	}

	public T peek() {
		if (isEmpty()) {
			throw new RuntimeException("Pilha vazia!");
		}
		return data[top-1];
	}

	private boolean isFull() {
		return top == data.length;
	}

	public boolean isEmpty() {
		return top == 0;
	}
}
