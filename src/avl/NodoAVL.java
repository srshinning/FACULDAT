package avl;

public class NodoAVL<T> {
	private T dato;
	public int altura;
	private NodoAVL<T> hijoIzquierdo;
	private NodoAVL<T> hijoDerecho;
	public NodoAVL (T dato) {
		this.dato = dato;
		this.altura = 0;
		this.hijoIzquierdo = null;
		this.hijoDerecho = null;
	}
	
	public T getDato(){		
		return this.dato; 
	}
	
	public NodoAVL<T> getHijoIzquierdo(){		
		return this.hijoIzquierdo;
	}
	
	public NodoAVL<T> getHijoDerecho(){		
		return this.hijoDerecho;
	}	
	
	public void setDato(T dato){		
		this.dato = dato;
	}
	
	public void setHijoIzquierdo(NodoAVL<T> hijoIzq){		
		this.hijoIzquierdo = hijoIzq;
	}
	
	public void setHijoDerecho(NodoAVL<T> hijoDer){		
		this.hijoDerecho = hijoDer;
	}
	
	
}
