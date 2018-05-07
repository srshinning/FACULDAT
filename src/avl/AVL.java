package avl;

public class AVL<T extends Comparable<T>> {

	//RAIZ METHODS
	private NodoAVL<T> raiz;
	 
	public AVL (T dato) {
		this.raiz = new NodoAVL<T>(dato);
	}
	private AVL (NodoAVL<T> nodo) {
		this.raiz = nodo;
	}
	
	private NodoAVL<T> getRaiz() {
		return raiz;
	}

	public T getDatoRaiz() {
		if (this.getRaiz() != null) {
			return this.getRaiz().getDato();
		} else {
			return null;
		}
	}	
	//HIJOS GETS
	public AVL<T> getHijoIzquierdo() {
		return new AVL<T>(this.raiz.getHijoIzquierdo());
	}

	public AVL<T> getHijoDerecho() {
		return new AVL<T>(this.raiz.getHijoDerecho());
	}
	
	//BUSCAR
	private NodoAVL<T> buscar(Comparable<T> x, NodoAVL<T> t) {
		if (t != null) {
			if (x.compareTo(t.getDato()) < 0) {
				t = this.buscar(x, t.getHijoIzquierdo());
			} else if (x.compareTo(t.getDato()) > 0) {
				t = this.buscar(x, t.getHijoDerecho());
			} else
				; // Se encontro el nodo, asi que es t
			return t;
		} else {
			return null;
		}
	}
	
	public boolean incluye (Comparable<T> dato) {
		return buscar (dato, this.raiz) != null;
	}
	
	public T buscar (T dato)
	
	
	

	
	
	
	
		
	
	private static int altura( NodoAVL<T> t )
    {
        return t == null ? -1 : t.altura;
    }
	
	
	
	
	
	private NodoAVL<T> eliminar(T dato, NodoAVL<T> t) {
		
		if (t.getDato().compareTo(dato) > 0) {//si es menor voy a la izq
			if (t.getHijoIzquierdo() != null)
				t.setHijoIzquierdo(this.eliminar(dato, t.getHijoIzquierdo()));
		} else if (t.getDato().compareTo(dato) < 0) {//si es mayor voy a la der
			if (t.getHijoDerecho() != null)
				t.setHijoDerecho(this.eliminar(dato, t.getHijoDerecho()));
		} else if (t.getDato().compareTo(dato) == 0) {//si es el dato a eliminar
			if (t.getHijoIzquierdo() != null && t.getHijoDerecho() != null) {//si tiene 2 hijos
				NodoAVL<T> menor = this.buscarMayorDeLosMenores(t);
				t.setDato(menor.getDato());
				t.setHijoIzquierdo(this.eliminar(menor.getDato(), t.getHijoIzquierdo()));
			} 
			else {
				// tiene un solo hijo o es hoja
				if (t.getHijoIzquierdo() != null)
					t = t.getHijoIzquierdo();
				else
					t = t.getHijoDerecho();
			}
		}
		balance (t);
		return t;
	}
	
	
	
	private NodoAVL<T> buscarMayorDeLosMenores(NodoAVL<T> t) {
		if (t.getHijoIzquierdo()!=null){
			NodoAVL<T> maxDerecho = t.getHijoIzquierdo();
			while (maxDerecho.getHijoDerecho()!=null){
				maxDerecho=maxDerecho.getHijoDerecho();
			}
			return maxDerecho;
		}
		return t;
	}

	
	
	
	public void agregar(T dato) {
		if (raiz == null)
			raiz = new NodoAVL<T>(dato);
		else
			this.agregar(dato, raiz);
	}
	
	

	private void agregar(T dato, NodoAVL<T> r) {
		if (dato.compareTo(r.getDato()) < 0) {
			if (r.getHijoIzquierdo() == null)
				r.setHijoIzquierdo(new NodoAVL<T>(dato));
			else
				this.agregar(dato, r.getHijoIzquierdo());
		}
		else if (dato.compareTo(r.getDato()) > 0) {
			if (r.getHijoDerecho() == null)
				r.setHijoDerecho(new NodoAVL<T>(dato));
			else
				this.agregar(dato, r.getHijoDerecho());
		}
		balancear(r);
	}
	
	
	private NodoAVL<T> balancear (NodoAVL<T> t) {
		if (altura(t.getHijoIzquierdo()) - altura(t.getHijoDerecho()) == 2) {
			if (altura(t.getHijoIzquierdo().getHijoIzquierdo()) >= altura(t.getHijoIzquierdo().getHijoDerecho()))
				t = RSI(t);
			else
				t =RDI(t);
		}
		if (altura(t.getHijoDerecho()) - altura (t.getHijoIzquierdo()) == 2) {
			if (altura (t.getHijoDerecho().getHijoDerecho()) >= altura (t.getHijoDerecho().getHijoIzquierdo()))
				t = RSD(t);
			else 
				t=RDD(t);
		
		}
		return t;
		
		
	}
	
	
	private NodoAVL<T> RSD(NodoAVL<T> k1){
        NodoAVL<T> k2 = k1.getHijoDerecho();
        k1.setHijoDerecho(k2.getHijoIzquierdo());
        k2.setHijoIzquierdo(k1);
        updateAltura(k1);
        updateAltura(k2);
        return k1;
	}
	
	private NodoAVL<T> RSI (NodoAVL<T> k2){
		NodoAVL<T> k1 = k2.getHijoIzquierdo();
		k2.setHijoIzquierdo(k1.getHijoDerecho());
		k2.setHijoDerecho(k2);
		updateAltura(k2);
		updateAltura(k1);
	
	}
	
	private NodoAVL<T> RDI (NodoAVL<T> k3){
		k3.setHijoIzquierdo(RSD(k3.getHijoIzquierdo()));
		return RSI(k3);	
	}
	
		

	
	
	private void updateAltura (NodoAVL<T> t) {
		if (t == null)
			return;
		t.altura = Math.max(altura(t.getHijoIzquierdo()), altura(t.getHijoDerecho())) + 1;
		
	}
	
	

    private int altura(NodoAVL<T> t) {
        if (t == null)
            return -1;
        return t.altura;
    }
	
	
	
	
	
	
	
	
	
}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
----------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------

	
	

    private static class Node {
        private int key;
        private int balance;
        private int height;
        private Node left;
        private Node right;
        private Node parent;
 
        Node(int key, Node parent) {
            this.key = key;
            this.parent = parent;
        }
    }
 
    public boolean insert(int key) {
        if (root == null) {
            root = new Node(key, null);
            return true;
        }
 
        Node n = root;
        while (true) {
            if (n.key == key)
                return false;
 
            Node parent = n;
 
            boolean goLeft = n.key > key;
            n = goLeft ? n.left : n.right;
 
            if (n == null) {
                if (goLeft) {
                    parent.left = new Node(key, parent);
                } else {
                    parent.right = new Node(key, parent);
                }
                rebalance(parent);
                break;
            }
        }
        return true;
    }
 
    private void delete(Node node) {
        if (node.left == null && node.right == null) {
            if (node.parent == null) {
                root = null;
            } else {
                Node parent = node.parent;
                if (parent.left == node) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                rebalance(parent);
            }
            return;
        }
 
        if (node.left != null) {
            Node child = node.left;
            while (child.right != null) child = child.right;
            node.key = child.key;
            delete(child);
        } else {
            Node child = node.right;
            while (child.left != null) child = child.left;
            node.key = child.key;
            delete(child);
        }
    }
 
    public void delete(int delKey) {
        if (root == null)
            return;
 
        Node child = root;
        while (child != null) {
            Node node = child;
            child = delKey >= node.key ? node.right : node.left;
            if (delKey == node.key) {
                delete(node);
                return;
            }
        }
    }
 
    private void rebalance(Node n) {
        setBalance(n);
 
        if (n.balance == -2) {
            if (height(n.left.left) >= height(n.left.right))
                n = rotateRight(n);
            else
                n = rotateLeftThenRight(n);
 
        } else if (n.balance == 2) {
            if (height(n.right.right) >= height(n.right.left))
                n = rotateLeft(n);
            else
                n = rotateRightThenLeft(n);
        }
 
        if (n.parent != null) {
            rebalance(n.parent);
        } else {
            root = n;
        }
    }
 
    private Node rotateLeft(Node a) {
 
        Node b = a.right;
        b.parent = a.parent;
 
        a.right = b.left;
 
        if (a.right != null)
            a.right.parent = a;
 
        b.left = a;
        a.parent = b;
 
        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }
 
        setBalance(a, b);
 
        return b;
    }
 
    private Node rotateRight(Node a) {
 
        Node b = a.left;
        b.parent = a.parent;
 
        a.left = b.right;
 
        if (a.left != null)
            a.left.parent = a;
 
        b.right = a;
        a.parent = b;
 
        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }
 
        setBalance(a, b);
 
        return b;
    }
 
    private Node rotateLeftThenRight(Node n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }
 
    private Node rotateRightThenLeft(Node n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }
 
    private int height(Node n) {
        if (n == null)
            return -1;
        return n.height;
    }
 
    private void setBalance(Node... nodes) {
        for (Node n : nodes) {
            reheight(n);
            n.balance = height(n.right) - height(n.left);
        }
    }
 
    public void printBalance() {
        printBalance(root);
    }
 
    private void printBalance(Node n) {
        if (n != null) {
            printBalance(n.left);
            System.out.printf("%s ", n.balance);
            printBalance(n.right);
        }
    }
 
    private void reheight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }
 
    public static void main(String[] args) {
        AVL tree = new AVL();
 
        System.out.println("Inserting values 1 to 10");
        for (int i = 1; i < 10; i++)
            tree.insert(i);
 
        System.out.print("Printing balance: ");
        tree.printBalance();
    }
	

}
