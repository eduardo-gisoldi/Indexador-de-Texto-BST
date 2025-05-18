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
        No pai_x = pai(aRemover.getPalavra());

        // Remove nó-folha
        if (aRemover.getDir() == null && aRemover.getEsq() == null) {
            if (pai_x.getPalavra() < aRemover.getPalavra()) {
                pai_x.setDir(null);
            } else {
                pai_x.setEsq(null);
            }
        }
        return;
    }

    public void removeCaso2(No aRemover) {
        No pai_x = pai(aRemover.getPalavra());

        if (pai_x.getPalavra() < aRemover.getPalavra()) {
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

        aRemover.setValor(sucessor.getPalavra());

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
    public No sucessor(String x) {
        return menorRec(busca(x).getDir());
    }

    public No antecessor(String x) {
        return maiorRec(busca(x).getEsq());
    }


    /**
     * Busca (x) verifica se x está na árvore ou não.
     * Devolve o No bucsado se x está na árvore.
     * Se x não está na árvore, devolve null.
    */
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

    /**
     * Remove um nó da árvore binária, dado o valor do nó a ser removido.
     * Devolve o nó removido.
     */
    public Boolean remove(String valor){
        No atual = this.raiz;
        No paiAtual = null;

        // percorrer a árvore até encontrar o elemento ou chegar em null
        while(atual != null){
            if (atual.getPalavra().compareTo(valor) == 0){ //elemento encontrado
                break;                
            }else if ((valor.compareTo(atual.getPalavra())) < 0){ //valor procurado é menor que o atual 
                paiAtual = atual;
                atual = atual.getEsq();
            }else{ //valor procurado é maior que o atual
                paiAtual = atual;
                atual = atual.getDir();
            }
        }
        if (atual == null) return false; // elemento não encontrado
        
        //elemento tem 2 filhos ou elemento tem somente filho à direita
        if (atual.getDir() != null){
            
            No substituto = atual.getDir();
            No paiSubstituto = atual;
            while(substituto.getEsq() != null){
                paiSubstituto = substituto;
                substituto = substituto.getEsq();
            }
            substituto.setEsq(atual.getEsq());
            if (paiAtual != null){
                if (atual.getPalavra().compareTo(paiAtual.getPalavra()) < 0){ //atual < paiAtual
                    paiAtual.setEsq(substituto);
                }else{
                    paiAtual.setDir(substituto);
                }
            }else{ //se não tem paiAtual, então é a raiz
                
                
                this.raiz = substituto;
                paiSubstituto.setEsq(null);
                this.raiz.setDir(paiSubstituto);
                this.raiz.setEsq(atual.getEsq());
            }
            
            //removeu o elemento da árvore
            if (substituto.getPalavra().compareTo(paiSubstituto.getPalavra()) < 0){ //substituto < paiSubstituto
                paiSubstituto.setEsq(null);
            }else{
                paiSubstituto.setDir(null);
            }
            
        }else if (atual.getEsq() != null){ //tem filho só à esquerda
            No substituto = atual.getEsq();
            No paiSubstituto = atual;
            while(substituto.getDir() != null){
                paiSubstituto = substituto;
                substituto = substituto.getDir();
            }
            if (paiAtual != null){
                if (atual.getPalavra().compareTo(paiAtual.getPalavra()) < 0){ //atual < paiAtual
                    paiAtual.setEsq(substituto);
                }else{
                    paiAtual.setDir(substituto);
                }
            }else{ //se for a raiz
                this.raiz = substituto;
                paiSubstituto.setDir(null);
                this.raiz.setEsq(paiSubstituto);
                this.raiz.setDir(atual.getDir());
            }
            
            //removeu o elemento da árvore
            if (substituto.getPalavra().compareTo(paiSubstituto.getPalavra()) < 0){ //substituto < paiSubstituto
                paiSubstituto.setEsq(null);
            }else{
                paiSubstituto.setDir(null);
            }
        }else{ //não tem filho
            if (paiAtual != null){
                if (atual.getPalavra().compareTo(paiAtual.getPalavra()) < 0){ //atual < paiAtual
                    paiAtual.setEsq(null);
                }else{
                    paiAtual.setDir(null);
                }
            }else{ //é a raiz
                this.raiz = null;
            }
        }

        return true;
    }




    // Função para rastreio in-ordem
    public void rastreioInordem() {
        System.out.println("Rastreio in-ordem:");
        inordem(raiz);
        System.out.println();
    }
    private void inordem(No x) {
        if (x != null) {
            inordem(x.getEsq());
            System.out.print(x.getPalavra() + " qtd: " + x.getQtd() + "\n");
            inordem(x.getDir());
        }
    }

    // Função para rastreio pre-ordem
    public void rastreioPreordem() {
        System.out.println("Rastreio pré-ordem:");
        preordem(raiz);
        System.out.println();
    }
    private void preordem(No x) {
        if (x != null) {
            System.out.print(x.getPalavra() + " qtd: " + x.getQtd() + "\n");
            preordem(x.getEsq());
            preordem(x.getDir());
        }
    }

    // Função para rastreio pos-ordem
    public void rastreioPosordem() {
        System.out.println("Rastreio pós-ordem:");
        posordem(raiz);
        System.out.println();
    }
    private void posordem(No x) {
        if (x != null) {
            posordem(x.getEsq());
            posordem(x.getDir());
            System.out.print(x.getPalavra() + " qtd: " + x.getQtd() + "\n");
        }
    }
    
    
    // Getters e Setters
    public No getRaiz() {
        return raiz;
    }
    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }
}
