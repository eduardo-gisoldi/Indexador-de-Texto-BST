package com.eduardogisoldi.indexadordetextobst.arvoreBST;

/**
 * Classe que representa um nó da árvore binária de busca (BST).
 * Cada nó armazena uma palavra, referências para o pai, filho esquerdo, filho direito
 * e a quantidade de ocorrências da palavra.
 */
public class No {
    // --- Atributos ---
    private String palavra; // Palavra armazenada no nó
    private No pai;         // Referência para o nó pai
    private No esq;         // Referência para o filho esquerdo
    private No dir;         // Referência para o filho direito
    private int qtd;        // Quantidade de ocorrências da palavra

    // --- Construtor ---

    /**
     * Cria um novo nó com a palavra informada.
     * Inicializa as referências dos filhos e do pai como null e a quantidade como 1.
     * @param palavra Palavra a ser armazenada no nó.
     */
    No(String palavra) {
        this.palavra = palavra;
        this.pai = null;
        this.esq = null;
        this.dir = null;
        this.qtd = 1;
    }

    // --- Métodos de Utilidade ---

    /**
     * Incrementa a quantidade de ocorrências da palavra em 1.
     */
    public void incQtd() {
        this.qtd++;
    }

    /**
     * Exibe os dados do nó no console, incluindo palavra, quantidade, filhos e pai.
     */
    public void showNo() {
        System.out.println("---\nPalavra: " + palavra + " | Qtd: " + qtd);
        System.out.println("Filho esquerdo: " + (esq != null ? esq.getPalavra() : "null"));
        System.out.println("Filho direito: " + (dir != null ? dir.getPalavra() : "null"));
        System.out.println("Pai: " + (pai != null ? pai.getPalavra() : "null"));
        System.out.println("---");
    }

    // --- Getters e Setters ---

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
