public class No {
    // Atributos:
    private String palavra; // palavra armazenado no nó
    private No pai;         // nó pai
    private No esq;         // filho esquerdo
    private No dir;         // filho direito
    private int qtd;        // quantidade de ocorrências da palavra

    // Construtor
    No(String palavra) {
        this.palavra = palavra;
        this.pai = null;
        this.esq = null;
        this.dir = null;
        this.qtd = 1;
    }

    
    // Incrementa a quantidade de ocorrências da palavra em 1.
    public void incQtd() {
        this.qtd++;
    }

    // Mostra os dados do nó.
    public void showNo() {
        System.out.println("---\nPalavra: " + palavra + " | Qtd: " + qtd);
        System.out.println("Filho esquerdo: " + (esq != null ? esq.getPalavra() : "null"));
        System.out.println("Filho direito: " + (dir != null ? dir.getPalavra() : "null"));
        System.out.println("Pai: " + (pai != null ? pai.getPalavra() : "null"));
        System.out.println("---");
    }


    // Getters e Setters
    public String getPalavra() {
        return palavra;
    }
    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public No getPai() {
        return pai;
    }
    public void setPai(No pai) {
        this.pai = pai;
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

    public int getQtd() {
        return qtd;
    }
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

}
