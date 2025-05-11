public class ArvoreBST {

	//Atributos:
	private No raiz;
	
	//Construtor
	public ArvoreBST () {
		raiz = null;
	}
	
	//Menor elemento da árvore
	//Devolve o menor elemento da árvore. Se a árvore for vazia,
	//  devolve null. Senão devolve o nó com o menor valor na 
	//  árvore.
    No menor_rec (No pai){
        if (pai != null){
            if (pai.getEsq() == null)
                return pai;
            else
                return menor_rec(pai.getEsq());
        }
        return pai;
    }

    No menor(){
        return menor_rec(raiz);    }
    
	//Sucessor de um elemento da árvore.
	//Devolve o nó com o sucessor do elemento x (x deve estar na árvore).
	//  Se x não está na árvore, devolve null. Se x não tem sucessor, devolve null.
	public No sucessor (int x) {
		return menor_rec (busca (x).getDir());
	}

	//Antecessor de um elemento da árvore.
	//Devolve o nó com o antecessor do elemento x (x deve estar na árvore).
	//  Se x não está na árvore, devolve null.  Se x não tem antecessor, devolve null.
	public No antecessor (int x) {
		return maior_rec (busca (x).getEsq());		
	}
	
	public boolean vazia() {
		return raiz == null;
	}
	
	public No insere (int v) {
		//Criando um novo nó com valor "v"
		No novo = new No (v);
		
		if (vazia()) {
			raiz = novo;
			return novo;
		}
		
		return insere_rec (raiz, novo);
	}
	
	private No insere_rec (No pai, No novo) {
		if (novo.getValor() <= pai.getValor()) {
			if (pai.getEsq() == null) {
				pai.setEsq(novo);
				return novo;
			}
			return insere_rec (pai.getEsq(), novo);
		}
		else {
			if (pai.getDir() == null) {
				pai.setDir(novo);
				return novo;
			}
			return insere_rec (pai.getDir(), novo);
		}
	}
	
	
	private int qtd_nos_rec (No pai) {
		if (pai == null) {
			return 0;
		}
		else {
			return qtd_nos_rec(pai.getEsq()) +
				qtd_nos_rec(pai.getDir()) +
				1;
		}
	}
	
	public int qtd_nos () {
		return qtd_nos_rec (raiz);
	}
	
	//Busca (x) verifica se x está na árvore ou não.
	// Devolve true se x está na árvore ou
	// false, caso contrário.
	
	public No busca (int x) {
		return busca_rec (raiz, x);
	}

	private No busca_rec (No pai, int x) {
		if (pai != null) {
			if (pai.getValor() == x) {
				return pai;
			}
			else {
				if (x < pai.getValor())
					return busca_rec(pai.getEsq(), x);
				else
					return busca_rec(pai.getDir(), x);
			}
		}
		return pai;
	}
	
	//Função para rastreio in-ordem
	public void rastreio_inordem () {
		inordem (raiz);
		System.out.println();
	}
	
	private void inordem (No x) {
		if (x != null) {
			inordem (x.getEsq());
			System.out.print(x.getValor() + " ");
			inordem (x.getDir());
		}
	}
	
	//Função para rastreio pre-ordem
	public void rastreio_preordem () {
		preordem (raiz);
		System.out.println();
	}
	
	private void preordem (No x) {
		if (x != null) {
			System.out.print(x.getValor() + " ");
			preordem (x.getEsq());
			preordem (x.getDir());
		}
	}

	//Função para rastreio pos-ordem
	public void rastreio_posordem () {
		posordem (raiz);
		System.out.println();
	}
	
	private void posordem (No x) {
		if (x != null) {
			posordem (x.getEsq());
			posordem (x.getDir());
			System.out.print(x.getValor() + " ");
		}
	}

    No maior_rec (No pai){
        if (pai != null){
            if (pai.getDir() == null)
                return pai;
            else
                return maior_rec(pai.getDir());
        }
        return pai;
    }

    No maior(){
        return maior_rec(raiz);
    }
    
    No pai (int x){
            No tmp = raiz;
            
            while (tmp != null){
                if ((tmp.getEsq() != null && x == tmp.getEsq().getValor()) || 
                    (tmp.getDir() != null && x == tmp.getDir().getValor()))
                    return tmp;
                else
                    if (x < tmp.getValor())
                        tmp = tmp.getEsq();
                    else
                        tmp = tmp.getDir();
            }
        return null;
    }

    
    //Remove um nó da árvore binária, cujo valor é "x", dado
    // como parâmetro.
    public void remove (int x) {
    	No aRemover = busca (x);
    	No pai_x = pai(x);
    	
    	//Caso 1: nó-folha
    	if (aRemover.getDir() == null && aRemover.getEsq() == null) {
    		remove_caso1(aRemover);
    	}
    	//Caso 3:
    	else if (aRemover.getDir() != null && aRemover.getEsq() != null) {
    		remove_caso3(aRemover);
    	}
    	//Caso 2:
    	else {
    		remove_caso2(aRemover);
    	}
    }
    	
    public void remove_caso1(No aRemover) {
       	No pai_x = pai(aRemover.getValor());
       	
       	//Remove nó-folha
       	if (aRemover.getDir() == null && aRemover.getEsq() == null) {
       		if (pai_x.getValor() < aRemover.getValor()) {
       			pai_x.setDir(null);
       		}
       		else {
       			pai_x.setEsq(null);
       		}
       	} 
       	return;
    }

    
    public void remove_caso2 (No aRemover) {
       	No pai_x = pai(aRemover.getValor());

    	if (pai_x.getValor() < aRemover.getValor()) {
			if (aRemover.getDir() != null)
				pai_x.setDir(aRemover.getDir());
			else
				pai_x.setDir(aRemover.getEsq());
		}
		else {
			if(aRemover.getDir() != null)
				pai_x.setEsq(aRemover.getDir());
			else
				pai_x.setDir(aRemover.getEsq());
		}
    }

    public void remove_caso3(No aRemover) {
       	No sucessor = menor_rec (aRemover.getDir());
       	
       	aRemover.setValor(sucessor.getValor());

       	if (sucessor.getEsq() == null && sucessor.getDir() == null)
       		remove_caso1(sucessor);
       	else
       		remove_caso2(sucessor);
    }    
}


class No {
	//Atributos:
	int valor; //numero armazenado no nó
	No esq;    //filho esquerdo
	No dir;    //filho direito
	
	//Construtor
	No (int v){
		valor = v;
		esq = null;
		dir = null;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public No getEsq() {
		return esq;
	}

	public void setEsq(No esq) {
		this.esq = esq;
	}

	public No getDir() {
		return dir;
	}

	public void setDir(No dir) {
		this.dir = dir;
	}
	
}
