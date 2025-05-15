public class ArvoreBST {

    // Atributos:
    private No raiz;

    // Construtor
    public ArvoreBST() {
        raiz = null;
    }

    /**
     * Devolve o nó pai de um nó com valor x.
     * Se x não está na árvore, devolve null.
    */
    public No pai(String x) {
        No tmp = raiz;

        while (tmp != null) {
            if ((tmp.getEsq() != null && x.equals(tmp.getEsq().getPalavra())) ||
                    (tmp.getDir() != null && x.equals(tmp.getDir().getPalavra())))
                return tmp;
            else if (x.compareTo(tmp.getPalavra()) < 0)
                tmp = tmp.getEsq();
            else
                tmp = tmp.getDir();
        }
        return null;
    }

    /**
     * Insere um nó na árvore binária.
     * Devolve o nó inserido.
     */
    private No insereRec(No pai, No novo) {
        if (novo.getPalavra().compareTo(pai.getPalavra()) < 0) {
            // Se o nó a ser inserido é menor que o nó pai, verificar se o pai tem filho esquerdo
            // Se não tem, insere o nó a esquerda do pai
            if (pai.getEsq() == null) {
                pai.setEsq(novo);
                return novo;
            }
            return insereRec(pai.getEsq(), novo);
        } else if(novo.getPalavra().compareTo(pai.getPalavra()) > 0) {
            // Se o nó a ser inserido é maior que o nó pai, verificar se o pai tem filho direito
            // Se não tem, insere o nó a direita do pai
            if (pai.getDir() == null) {
                pai.setDir(novo);
                return novo;
            }
            return insereRec(pai.getDir(), novo);
        } else {
            // Se a palavra já existe, incrementa a quantidade
            pai.incQtd();
            return pai;
        }
    }
    public No insere(String v) {
        No novo = new No(v);

        if (this.vazia()) {
            raiz = novo;
            return novo;
        }

        return insereRec(raiz, novo);
    }

/*
    // Remove um nó da árvore binária, cujo valor é "x", dado
    // como parâmetro.
    public void remove(String x) {
        No aRemover = busca(x);
        No pai_x = pai(x);

        // Caso 1: nó-folha
        if (aRemover.getDir() == null && aRemover.getEsq() == null) {
            removeCaso1(aRemover);
        }
        // Caso 3:
        else if (aRemover.getDir() != null && aRemover.getEsq() != null) {
            removeCaso3(aRemover);
        }
        // Caso 2:
        else {
            removeCaso2(aRemover);
        }
    }

    public void removeCaso1(No aRemover) {
        No pai_x = pai(aRemover.getValor());

        // Remove nó-folha
        if (aRemover.getDir() == null && aRemover.getEsq() == null) {
            if (pai_x.getValor() < aRemover.getValor()) {
                pai_x.setDir(null);
            } else {
                pai_x.setEsq(null);
            }
        }
        return;
    }

    public void removeCaso2(No aRemover) {
        No pai_x = pai(aRemover.getValor());

        if (pai_x.getValor() < aRemover.getValor()) {
            if (aRemover.getDir() != null)
                pai_x.setDir(aRemover.getDir());
            else
                pai_x.setDir(aRemover.getEsq());
        } else {
            if (aRemover.getDir() != null)
                pai_x.setEsq(aRemover.getDir());
            else
                pai_x.setDir(aRemover.getEsq());
        }
    }

    public void removeCaso3(No aRemover) {
        No sucessor = menorRec(aRemover.getDir());

        aRemover.setValor(sucessor.getValor());

        if (sucessor.getEsq() == null && sucessor.getDir() == null)
            removeCaso1(sucessor);
        else
            removeCaso2(sucessor);
    }
*/


    /**
     * Quantidade de nós da árvore.
     * Devolve a quantidade de nós da árvore.
     */
    private int qtdNosRec(No pai) {
        if (pai == null) {
            return 0;
        } else {
            return qtdNosRec(pai.getEsq()) +
                    qtdNosRec(pai.getDir()) +
                    1;
        }
    }
    public int qtdNos() {
        return qtdNosRec(raiz);
    }


    /**
     * Verifica se a árvore está vazia.
     */
    public boolean vazia() {
        return raiz == null;
    }

    /**
     * Menor e maior elemento da árvore
     * Devolvem o menor ou o maior elemento da árvore. 
     * Se a árvore for vazia, devolvem null.
    */
    private No menorRec(No pai) {
        if (pai != null) {
            if (pai.getEsq() == null)
                return pai;
            else
                return menorRec(pai.getEsq());
        }
        return pai;
    }
    public No menor() {
        return menorRec(raiz);
    }

    private No maiorRec(No pai) {
        if (pai != null) {
            if (pai.getDir() == null)
                return pai;
            else
                return maiorRec(pai.getDir());
        }
        return pai;
    }
    public No maior() {
        return maiorRec(raiz);
    }


    /**
     * Sucessor e antecessor de um elemento da árvore.
     * Devolvem o nó com o sucessor ou o antecessor do elemento x (x deve estar na árvore).
     * Se x não está na árvore, devolvem null. Se x não tem sucessor / antecessor, devolvem null.
    */
    public No sucessor(int x) {
        return menorRec(busca(x).getDir());
    }

    public No antecessor(int x) {
        return maiorRec(busca(x).getEsq());
    }


    // Busca (x) verifica se x está na árvore ou não.
    // Devolve true se x está na árvore ou false, caso contrário.
    private No buscaRec(No pai, String x) {
        if (pai != null) {
            if (pai.getPalavra().compareTo(x) == 0) {
                return pai;
            } else {
                if (x.compareTo(pai.getPalavra()) < 0)
                    return buscaRec(pai.getEsq(), x);
                else
                    return buscaRec(pai.getDir(), x);
            }
        }
        return pai;
    }
    public No busca(String x) {
        return buscaRec(raiz, x);
    }

    // Função para rastreio in-ordem
    public void rastreio_inordem() {
        inordem(raiz);
        System.out.println();
    }
    private void inordem(No x) {
        if (x != null) {
            inordem(x.getEsq());
            System.out.print(x.getPalavra() + ", ");
            inordem(x.getDir());
        }
    }

    // Função para rastreio pre-ordem
    public void rastreio_preordem() {
        preordem(raiz);
        System.out.println();
    }
    private void preordem(No x) {
        if (x != null) {
            System.out.print(x.getPalavra() + ", ");
            preordem(x.getEsq());
            preordem(x.getDir());
        }
    }

    // Função para rastreio pos-ordem
    public void rastreio_posordem() {
        posordem(raiz);
        System.out.println();
    }
    private void posordem(No x) {
        if (x != null) {
            posordem(x.getEsq());
            posordem(x.getDir());
            System.out.print(x.getPalavra() + ", ");
        }
    }
    
}
