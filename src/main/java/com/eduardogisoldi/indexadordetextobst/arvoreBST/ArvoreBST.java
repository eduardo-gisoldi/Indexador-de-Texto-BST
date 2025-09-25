package com.eduardogisoldi.indexadordetextobst.arvoreBST;

import java.text.Normalizer;
import java.util.Scanner;

/**
 * Classe que representa uma árvore binária de busca (BST) para indexação de palavras.
 * Permite inserir, buscar, remover e realizar rastreios das palavras armazenadas.
 */
public class ArvoreBST {

    // --- Atributos ---
    private No raiz; // Raiz da árvore

    // --- Construtor ---
    /**
     * Cria uma árvore BST vazia.
     */
    public ArvoreBST() {
        this.raiz = null;
    }

    // --- Métodos de Inserção ---

    /**
     * Insere um nó na subárvore usando recursividade.
     * Devolve o nó inserido. Se o nó já existe, incrementa a quantidade.
     * @param pai Nó pai que delimita a subárvore onde o nó deve ser inserido.
     * @param novo Nó a ser inserido.
     * @return O nó inserido ou o nó pai se a palavra já existe.
     */
    private No insereRec(No pai, No novo) {
        if (novo.getPalavra().compareTo(pai.getPalavra()) < 0) {
            if (pai.getEsq() == null) {
                pai.setEsq(novo);
                novo.setPai(pai);
                return novo;
            }
            return insereRec(pai.getEsq(), novo);
        } else if (novo.getPalavra().compareTo(pai.getPalavra()) > 0) {
            if (pai.getDir() == null) {
                pai.setDir(novo);
                novo.setPai(pai);
                return novo;
            }
            return insereRec(pai.getDir(), novo);
        } else {
            // Se a palavra já existe, incrementa a quantidade
            pai.incQtd();
            return pai;
        }
    }

    /**
     * Insere uma nova palavra na árvore. Trata a palavra para remover acentos e caracteres especiais.
     * Se a palavra já existe, incrementa a quantidade.
     * Se a palavra for inválida, retorna null.
     * @param v Palavra a ser inserida.
     * @return O nó inserido ou null se a palavra for inválida.
     */
    public No insere(String v, Boolean mostrarMensagem) {
        if (v == null) return null;

        // Substitui cedilha por c (caso especial)
        v = v.replace("ç", "c").replace("Ç", "C");

        // Remove acentos
        v = Normalizer.normalize(v, Normalizer.Form.NFD)
            .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        v = v.trim().toLowerCase();

        // Remove caracteres especiais de toda a palavra
        v = v.replaceAll("[^a-zA-Z0-9]+", "");

        // Aceitar apenas letras e números
        if (v.isEmpty() || !v.matches("[a-zA-Z0-9]+")) {
            System.err.println("\nValor invalido nao indexado: " + v);
            return null;
        }

        No novo = new No(v);

        if (this.vazia()) { // Se a árvore está vazia, o novo nó se torna a raiz
            raiz = novo;
            return novo;
        }

        No resultado = insereRec(raiz, novo); // Insere o novo nó na árvore

        if (mostrarMensagem){
            String mensagem = resultado.getQtd() == 1
                    ? ("Palavra '" + v + "' inserida com sucesso!")
                    : ("Palavra '" + v + "' ja existe, quantidade incrementada para: " + resultado.getQtd());
    
            System.out.println(mensagem);
        }

        return resultado;
    }

    // --- Métodos de Consulta ---

    /**
     * Retorna a quantidade de nós da subárvore.
     * @param pai Nó pai onde a contagem deve ser iniciada.
     * @return A quantidade de nós na subárvore com raiz em pai.
     */
    private int qtdNosRec(No pai) {
        return (pai == null) ? 0 : qtdNosRec(pai.getEsq()) + qtdNosRec(pai.getDir()) + 1;
    }

    /**
     * Retorna a quantidade total de nós (palavras únicas) na árvore.
     * @return Quantidade de nós.
     */
    public int qtdNos() {
        return qtdNosRec(raiz);
    }

    /**
     * Verifica se a árvore está vazia.
     * @return true se vazia, false caso contrário.
     */
    public boolean vazia() {
        return raiz == null;
    }

    // --- Métodos de Busca e Navegação ---

    /**
     * Retorna o menor elemento da árvore.
     * @return Nó com a menor palavra ou null se a árvore for vazia.
     */
    private No menorRec(No pai) {
        return (pai != null && pai.getEsq() != null) ? menorRec(pai.getEsq()) : pai;
    }
    public No menor() {
        return menorRec(raiz);
    }

    /**
     * Retorna o maior elemento da árvore.
     * @return Nó com a maior palavra ou null se a árvore for vazia.
     */
    private No maiorRec(No pai) {
        return (pai != null && pai.getDir() != null) ? maiorRec(pai.getDir()) : pai;
    }
    public No maior() {
        return maiorRec(raiz);
    }

    /**
     * Retorna o sucessor de um elemento da árvore.
     * @param x Palavra para a qual se deseja o sucessor.
     * @return Nó sucessor ou null se não existir.
     */
    public No sucessor(String x) {
        return menorRec(buscaRec(raiz, x).getDir());
    }

    /**
     * Retorna o antecessor de um elemento da árvore.
     * @param x Palavra para a qual se deseja o antecessor.
     * @return Nó antecessor ou null se não existir.
     */
    public No antecessor(String x) {
        return maiorRec(buscaRec(raiz, x).getEsq());
    }

    /**
     * Busca recursivamente uma palavra na árvore.
     * @param pai Nó atual.
     * @param x Palavra a ser buscada.
     * @return Nó encontrado ou null.
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

    /**
     * Busca recursivamente por substring na árvore.
     * Retorna o primeiro nó encontrado que contém a substring.
     * @param no Nó atual.
     * @param substring Substring a ser buscada.
     * @return Nó encontrado ou null.
     */
    private No buscaPorSubstringRec(No no, String substring) {
        if (no != null) {
            // Busca na subárvore esquerda
            No encontradoEsq = buscaPorSubstringRec(no.getEsq(), substring);
            if (encontradoEsq != null) {
                return encontradoEsq;
            }
            // Verifica o nó atual
            if (no.getPalavra().contains(substring)) {
                System.out.println(no.getPalavra() + " (qtd: " + no.getQtd() + ")");
                return no;
            }
            // Busca na subárvore direita
            No encontradoDir = buscaPorSubstringRec(no.getDir(), substring);
            if (encontradoDir != null) {
                return encontradoDir;
            }
        }
        return null;
    }

    /**
     * Menu de busca: permite buscar por palavra inteira ou substring.
     * Mede o tempo de busca e exibe o resultado.
     * @param scanner Scanner para entrada do usuário.
     * @return Nó encontrado ou null.
     */
    public No busca(Scanner scanner) {
        System.out.println("\nA busca eh por palavra inteira ou por substring? (I/S) ou cancelar (C)");
        String opcao = scanner.nextLine();

        while (!opcao.equalsIgnoreCase("I") && !opcao.equalsIgnoreCase("S") && !opcao.equalsIgnoreCase("C")) {
            System.out.println("Opcao invalida. Digite 'I' para palavra inteira ou 'S' para substring. Digite 'C' para cancelar.");
            opcao = scanner.nextLine();
        }

        if (opcao.equalsIgnoreCase("S")) {
            System.out.println("Digite a substring (retorna o primeiro resultado que a contem):");
            String s = scanner.nextLine();

            long inicio = System.nanoTime();
            No resultado = buscaPorSubstringRec(raiz, s);
            long fim = System.nanoTime();

            System.out.println("Tempo de busca (substring): " + (fim - inicio) + " ns");
            return resultado;
        } else if (opcao.equalsIgnoreCase("I")) {
            System.out.println("Digite a palavra:");
            String i = scanner.nextLine();

            long inicio = System.nanoTime();
            No resultado = buscaRec(raiz, i);
            long fim = System.nanoTime();
            if (resultado != null) {
                System.out.println("Palavra encontrada: " + resultado.getPalavra() + " (qtd: " + resultado.getQtd() + ")");
            }

            System.out.println("Tempo de busca (palavra inteira): " + (fim - inicio) + " ns");
            return resultado;
        }

        System.out.println("Operacao Cancelada");
        return null;
    }

    // --- Métodos de Remoção ---

    /**
     * Remove um nó da árvore.
     * Devolve o nó removido. Retorna null se o nó não foi encontrado.
     * @param valor Palavra a ser removida.
     * @return Nó removido ou null.
     */
    public No remove(String valor) {
        // Caso árvore vazia
        if (this.vazia()) {
            System.err.println("Arvore vazia, remover nao eh possivel.");
            return null;
        }

        No atual = this.buscaRec(raiz, valor); // verificar se o valor está na árvore

        // Caso elemento não encontrado
        if (atual == null) {
            System.err.println("Elemento nao encontrado na arvore, nao eh possivel remover.");
            return null;
        }

        No paiAtual = atual.getPai(); // facilitando escrita

        // Caso nó folha
        if (atual.getDir() == null && atual.getEsq() == null) {
            if (paiAtual == null) { // nó raiz
                this.raiz = null;
                return atual;
            } else if ((atual.getPalavra().compareTo(paiAtual.getPalavra())) < 0) {
                paiAtual.setEsq(null); // atual está à esquerda do pai
            } else {
                paiAtual.setDir(null); // atual está à direita do pai
            }
            return atual;
        }

        // Caso atual com apenas um filho
        if ((atual.getDir() == null && atual.getEsq() != null)
                || (atual.getDir() != null && atual.getEsq() == null)) {

            // Descobrir qual é o filho existente
            No filho = (atual.getEsq() != null) ? atual.getEsq() : atual.getDir();

            if (paiAtual == null) { // nó a ser removido é a raiz
                this.raiz = filho;
                filho.setPai(null);
            } else if (atual.getPalavra().compareTo(paiAtual.getPalavra()) < 0) { // nó a ser removido está à esquerda do pai
                paiAtual.setEsq(filho); // Pai aponta para o filho no lugar do nó removido
                filho.setPai(paiAtual); // Atualiza o pai do filho
            } else { // nó a ser removido está à direita do pai
                paiAtual.setDir(filho); // Pai aponta para o filho no lugar do nó removido
                filho.setPai(paiAtual); // Atualiza o pai do filho
            }
            return atual; // Retorna o nó removido
        }

        // Encontrar o melhor candidato para substituir atual
        No candidatoEsq = maiorRec(atual.getEsq());
        No candidatoDir = menorRec(atual.getDir());

        int resultadoEsq = raiz.getPalavra().compareTo(candidatoEsq.getPalavra());
        int resultadoDir = raiz.getPalavra().compareTo(candidatoDir.getPalavra());

        No substituto = (Math.abs(resultadoEsq) < Math.abs(resultadoDir)) ? candidatoEsq : candidatoDir;

        // Remover o substituto da sua posição original
        No paiSubstituto = substituto.getPai();
        No filhoSubstituto = (substituto.getEsq() != null) ? substituto.getEsq() : substituto.getDir();

        if (paiSubstituto != null) { // o substituto não é a raiz
            if (paiSubstituto.getEsq() == substituto) { // substituto é filho à esquerda
                paiSubstituto.setEsq(filhoSubstituto);
            } else { // substituto é filho à direita
                paiSubstituto.setDir(filhoSubstituto);
            }
            if (filhoSubstituto != null) { // se o substituto tem filho
                filhoSubstituto.setPai(paiSubstituto);
            }
        }

        No tmp = atual; // armazenar nó a ser removido

        // Substituir o conteúdo do nó a ser removido pelo do substituto
        atual.setPalavra(substituto.getPalavra());
        atual.setQtd(substituto.getQtd());

        return tmp; // devolve o nó removido
    }

    // --- Métodos de Rastreamento (Percurso) ---

    /**
     * Mostra os dados do nó.
     * @param x Nó a ser exibido.
     */
    private void mostraElemento(No x) {
        // se o nó não tem pai, imprime "null"
        @SuppressWarnings("unused")
        No noPai = new No("null");
        if (x.getPai() != null) noPai = x.getPai();

        System.out.println(x.getPalavra() + " (qtd: " + x.getQtd() + ")");
    }

    /**
     * Percorre a árvore em ordem e exibe os elementos.
     */
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

    /**
     * Percorre a árvore em pré-ordem e exibe os elementos.
     */
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

    /**
     * Percorre a árvore em pós-ordem e exibe os elementos.
     */
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

    // --- Getters e Setters ---

    public No getRaiz() {
        return raiz;
    }
    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }
}
