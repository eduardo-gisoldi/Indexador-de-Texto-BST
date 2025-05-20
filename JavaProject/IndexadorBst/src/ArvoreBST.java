import java.util.Scanner;

public class ArvoreBST {

    // Atributos:
    private No raiz;

    // Construtor
    public ArvoreBST() {
        this.raiz = null;
    }

    /**
     * Insere um no na arvore binaria.
     * Devolve o no inserido.
     */
    private No insereRec(No pai, No novo) {
        if (novo.getPalavra().compareTo(pai.getPalavra()) < 0) {
            // Se o no a ser inserido e menor que o no pai, verificar se o pai tem filho esquerdo
            // Se nao tem, insere o no a esquerda do pai
            if (pai.getEsq() == null) {
                pai.setEsq(novo);
                novo.setPai(pai);
                return novo;
            }
            return insereRec(pai.getEsq(), novo);
        } else if(novo.getPalavra().compareTo(pai.getPalavra()) > 0) {
            // Se o no a ser inserido e maior que o no pai, verificar se o pai tem filho direito
            // Se nao tem, insere o no a direita do pai
            if (pai.getDir() == null) {
                pai.setDir(novo);
                novo.setPai(pai);
                return novo;
            }
            return insereRec(pai.getDir(), novo);
        } else {
            // Se a palavra ja existe, incrementa a quantidade
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

    /**
     * Quantidade de nos da arvore.
     * Devolve a quantidade de nos da arvore.
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
     * Verifica se a arvore esta vazia.
     */
    public boolean vazia() {
        return raiz == null;
    }

    /**
     * Menor e maior elemento da arvore
     * Devolvem o menor ou o maior elemento da arvore. 
     * Se a arvore for vazia, devolvem null.
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
     * Sucessor e antecessor de um elemento da arvore.
     * Devolvem o no com o sucessor ou o antecessor do elemento x (x deve estar na arvore).
     * Se x nao esta na arvore, devolvem null. Se x nao tem sucessor / antecessor, devolvem null.
    */
    public No sucessor(String x) {
        return menorRec(buscaRec(raiz, x).getDir());
    }

    public No antecessor(String x) {
        return maiorRec(buscaRec(raiz, x).getEsq());
    }


    /**
     * Busca (x) verifica se x esta na arvore ou nao.
     * Devolve o No bucsado se x esta na arvore.
     * Se x nao esta na arvore, devolve null.
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
    private No buscaPorSubstringRec(No no, String substring) {
        if (no != null) {
            // Busca na subarvore esquerda
            No encontradoEsq = buscaPorSubstringRec(no.getEsq(), substring);
            if (encontradoEsq != null) {
                return encontradoEsq;
            }
            // Verifica o no atual
            if (no.getPalavra().contains(substring)) {
                System.out.println(no.getPalavra() + " (qtd: " + no.getQtd() + ")");
                return no;
            }
            // Busca na subarvore direita
            No encontradoDir = buscaPorSubstringRec(no.getDir(), substring);
            if (encontradoDir != null) {
                return encontradoDir;
            }
        }
        return null;
    }
    public No busca() {
    System.out.println("A busca eh por palavra inteira ou por substring? (I/S)");
    Scanner scanner = new Scanner(System.in);

    String opcao = scanner.nextLine().toUpperCase();

    while (!opcao.equals("I") && !opcao.equals("S") && !opcao.equals("C")) {
        System.out.println("Opcao invalida. Digite 'I' para palavra inteira ou 'S' para substring. Digite 'C' para cancelar.");
        opcao = scanner.nextLine().toUpperCase();
    }
    
    if (opcao.equals("S")) {
        System.out.println("Digite a substring (retornara o primeiro resultado):");
        String s = scanner.nextLine();

        long inicio = System.nanoTime();
        No resultado = buscaPorSubstringRec(raiz, s);
        long fim = System.nanoTime();

        System.out.println("Tempo de busca (substring): " + (fim - inicio) + " ns");
        return resultado;
    } else if (opcao.equals("I")) {
        System.out.println("Digite a palavra:");
        String i = scanner.nextLine();

        long inicio = System.nanoTime();
        No resultado = buscaRec(raiz, i);
        long fim = System.nanoTime();

        System.out.println("Tempo de busca (palavra inteira): " + (fim - inicio) + " ns");
        return resultado;
    }

    System.out.println("Operacao Cancelada");
    return null;
}




    /**
     * Remove um no da arvore.
     * Devolve o no removido. Retorna null se o no nao foi encontrado.
     */
    public No remove(String valor){
            // Caso Arvore Vazia
        if (this.vazia()) {
            System.err.println("Arvore vazia, remover nao eh possível.");
            return null; 
        }

        No atual = this.buscaRec(raiz, valor);// verificar se o valor esta na arvore

        // Caso Elemento Nao Encontrado
        if (atual == null) {
            System.err.println("Elemento nao encontrado na arvore, nao eh possível remover.");
            return null; 
        }

        No paiAtual = atual.getPai(); // facilitando escrita
        
        // Caso No Folha
        if (atual.getDir() == null && atual.getEsq() == null) { 
            if (paiAtual == null) { // no raiz
            this.raiz = null;
            return atual;
        } else if ( (atual.getPalavra().compareTo(paiAtual.getPalavra())) < 0) {
            paiAtual.setEsq(null); // atual esta à esquerda do pai
        } else {
            paiAtual.setDir(null); // atual esta à direita do pai
        }
        return atual;
        }
        
        
        // Caso Atual com apenas Um Filho
        if ((atual.getDir() == null && atual.getEsq() != null)
        || (atual.getDir() != null && atual.getEsq() == null)) {
            
            // Descobrir qual e o filho existente
            No filho = (atual.getEsq() != null) ? atual.getEsq() : atual.getDir();
            
            if (paiAtual == null) { // no a ser removido e a raiz
                this.raiz = filho;
                filho.setPai(null);
            } else if (atual.getPalavra().compareTo(paiAtual.getPalavra()) < 0) { // no a ser removido esta à esquerda do pai
                paiAtual.setEsq(filho); // Pai aponta para o filho no lugar do no removido
                filho.setPai(paiAtual); // Atualiza o pai do filho
            } else { // no a ser removido esta à direita do pai
                paiAtual.setDir(filho); // Pai aponta para o filho no lugar do no removido
                filho.setPai(paiAtual); // Atualiza o pai do filho
            }
            return atual; // Retorna o no removido
        }
            
        // Encontrar o melhor candidato para substituir Atual
        No candidatoEsq = maiorRec(atual.getEsq());
        No candidatoDir = menorRec(atual.getDir());
        
        int resultadoEsq = raiz.getPalavra().compareTo(candidatoEsq.getPalavra());
        int resultadoDir = raiz.getPalavra().compareTo(candidatoDir.getPalavra());
        
        No substituto;
        if (Math.abs(resultadoEsq) < Math.abs(resultadoDir)) { // candidatoEsq e mais proximo à raiz
            substituto = candidatoEsq;
        } else { // candidatoDir e mais proximo à raiz ou estao à mesma distância
            substituto = candidatoDir;
        }
        
        
        // Remover o substituto da sua posiçao original
        No paiSubstituto = substituto.getPai();
        No filhoSubstituto = (substituto.getEsq() != null) ? substituto.getEsq() : substituto.getDir();

        if (paiSubstituto != null) { // o substituto nao e a raiz
            if (paiSubstituto.getEsq() == substituto) { // substituto e filho à esquerda
                paiSubstituto.setEsq(filhoSubstituto);
            } else { // substituto e filho à direita
                paiSubstituto.setDir(filhoSubstituto);
            }
            if (filhoSubstituto != null) { // se o substituto tem filho
                filhoSubstituto.setPai(paiSubstituto);
            }
        }
        
        No tmp = atual; //armazenar no a ser removido
        
        // Substituir o conteúdo do no a ser removido pelo do substituto
        atual.setPalavra(substituto.getPalavra());
        atual.setQtd(substituto.getQtd());

        return tmp; // devolve o no removido  
    }


    /**
     * Mostra os dados do no.
     */
    private void mostraElemento(No x) {
        // se o no nao tem pai, imprime "null"
        No noPai = new No("null");
        if (x.getPai() != null) noPai = x.getPai();
    
        System.out.print(x.getPalavra() + ", qtd: " + x.getQtd() + ", pai: " + noPai.getPalavra() + "\n");
    }

    // Funçao para rastreio in-ordem
    public void rastreioInordem() {
        System.out.println("Rastreio in-ordem:");
        inordem(raiz);
        System.out.println();
    }
    private void inordem(No x) {
        if (x != null) {
            inordem(x.getEsq());
            mostraElemento(x);
            inordem(x.getDir());
        }
    }

    // Funçao para rastreio pre-ordem
    public void rastreioPreordem() {
        System.out.println("Rastreio preh-ordem:");
        preordem(raiz);
        System.out.println();
    }
    private void preordem(No x) {
        if (x != null) {
            mostraElemento(x);
            preordem(x.getEsq());
            preordem(x.getDir());
        }
    }

    // Funçao para rastreio pos-ordem
    public void rastreioPosordem() {
        System.out.println("Rastreio pos-ordem:");
        posordem(raiz);
        System.out.println();
    }
    private void posordem(No x) {
        if (x != null) {
            posordem(x.getEsq());
            posordem(x.getDir());
            mostraElemento(x);
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





/* Devolve o no pai de um no com valor x.
    * Se x nao esta na arvore, devolve null.
    * Se x e a raiz, devolve null.
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
    */

/*



// Garantir a integridade estrutura da arvore        
        No paiSubstituto = substituto.getPai();

        if (substituto.getEsq() != null){ // substituto tem filho à esquerda (maior que seu pai)
            paiSubstituto.setDir(substituto.getEsq()); // ligar filho à direita do pai do substituto ao seu novo filho
            substituto.getEsq().setPai(paiSubstituto); // settar filho à esquerda do substituto ao seu novo pai
        } else { // substituto nao tem filho à esquerda
            paiSubstituto.setDir(null); 
        }
        if (substituto.getDir() != null){ // substituto tem filho à direita (menor que seu pai)
            paiSubstituto.setEsq(substituto.getDir()); // ligar filho à esquerda do pai do substituto ao seu novo filho
            substituto.getDir().setPai(paiSubstituto); // settar filho à direita do substituto ao seu novo pai
        } else { // substituto nao tem filho à direita
            paiSubstituto.setEsq(null); 
        }
        

        // Realizar Substituiçao
        substituto.setPai(paiAtual);
        substituto.setEsq(atual.getEsq());
        substituto.setDir(atual.getDir()); 
        
        if (paiAtual == null) this.raiz = substituto; // substituindo raiz  
        
        return atual;






    // Remove um no da arvore binaria, cujo valor e "x", dado
    // como parâmetro.
    public void remove(String x) {
        No aRemover = busca(x);
        No pai_x = pai(x);

        // Caso 1: no-folha
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

        // Remove no-folha
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
     * Remove um no da arvore binaria, dado o valor do no a ser removido.
     * Devolve o no removido.
     * while(atual != null){
            if (atual.getPalavra().compareTo(valor) == 0){ //elemento encontrado
                break;                
            }else if ((valor.compareTo(atual.getPalavra())) < 0){ //valor procurado e menor que o atual 
                paiAtual = atual;
                atual = atual.getEsq();
            }else{ //valor procurado e maior que o atual
                paiAtual = atual;
                atual = atual.getDir();
            }
        }
     */



        /*
        if (atual.getDir() != null && atual.getEsq() != null){ // tem 2 filhos
            

                No substituto = maiorRec(atual.getEsq());
                No paiSubstituto = substituto.getPai();
            



            
            if (paiAtual != null){
                if (atual.getPalavra().compareTo(paiAtual.getPalavra()) < 0){ //atual < paiAtual
                    paiAtual.setEsq(substituto);
                }else{
                    paiAtual.setDir(substituto);
                }
            }else{ //se nao tem paiAtual, entao e a raiz
                
                
                this.raiz = substituto;
                paiSubstituto.setEsq(null);
                this.raiz.setDir(paiSubstituto);
                this.raiz.setEsq(atual.getEsq());
            }
            
            //removeu o elemento da arvore
            if (substituto.getPalavra().compareTo(paiSubstituto.getPalavra()) < 0){ //substituto < paiSubstituto
                paiSubstituto.setEsq(null);
            }else{
                paiSubstituto.setDir(null);
            }
            
        }else if (atual.getEsq() != null){ //tem filho so à esquerda
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
            
            //removeu o elemento da arvore
            if (substituto.getPalavra().compareTo(paiSubstituto.getPalavra()) < 0){ //substituto < paiSubstituto
                paiSubstituto.setEsq(null);
            }else{
                paiSubstituto.setDir(null);
            }
        }else{ //nao tem filho
            if (paiAtual != null){
                if (atual.getPalavra().compareTo(paiAtual.getPalavra()) < 0){ //atual < paiAtual
                    paiAtual.setEsq(null);
                }else{
                    paiAtual.setDir(null);
                }
            }else{ //e a raiz
                this.raiz = null;
            }
        }

    }
    
    return true; 
    */