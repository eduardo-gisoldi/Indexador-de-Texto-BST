import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Learning {
  public static void main(String[] args) {
    File arquivo_teste = new File("ARQUIVO_TXT_AQUI\\arquivo.txt");

    //String[] vetor = new String[30];

      try {
        Scanner leitor = new Scanner(arquivo_teste);

        String palavra1 = leitor.next();
        String palavra2 = leitor.next();

        if (palavra1.compareTo(palavra2) > 0) {
          System.out.println("A palavra " + palavra1 + " eh maior que " + palavra2);
        } else if (palavra1.compareTo(palavra2) < 0) {
          System.out.println("A palavra " + palavra1 + " eh menor que " + palavra2);
        } else {
          System.out.println("As palavras sao iguais");
        }

      } catch (FileNotFoundException ex) {
        System.err.println("Arquivo não encontrado: " + ex.getMessage());
      }

    //System.out.println(Arrays.toString(vetor));
  }
}


/*
        // Testes no App.java antigo

        // Teste de No
        No no = new No("teste");
        no.setEsq(new No("esquerda"));
        no.setDir(new No("direita"));
        no.setQtd(3);
        no.showNo();        
        System.out.println("\n\n\n");

        // Teste de ArvoreBST
        ArvoreBST arvore = new ArvoreBST();
        arvore.insere("teste");
        arvore.insere("teste2");
        arvore.insere("teste3");
        arvore.insere("teste2");
        arvore.insere("abelha");
        arvore.insere("aaaaaa");
        arvore.insere("mel"); 
        arvore.insere("melancia");
        arvore.insere("bolacha");
        arvore.insere("abelha");
        arvore.insere("abelha"); 
        arvore.insere("retorno");  
        arvore.insere("ventura");
        arvore.insere("criogenia");
        arvore.insere("abacaxi");
        arvore.insere("abacate");
        arvore.insere("feito");
        arvore.insere("teste6");
        arvore.insere("teste5");

        // Testes de rastreios
        System.out.println("\n\n");
        arvore.rastreioInordem();
        System.out.println("\n\n");
        arvore.rastreioPosordem();
        System.out.println("\n\n");
        arvore.rastreioPreordem();
        System.out.println("\n\n");
        
        // Teste de busca
        No noBusca = arvore.busca();
        if (noBusca != null) {
            System.out.println("Palavra encontrada: " + noBusca.getPalavra());
        } else {
            System.out.println("Palavra nao encontrada");
        }
                
        // Outros testes
        System.out.println("Maior: " + arvore.maior().getPalavra());
        System.out.println("Menor: " + arvore.menor().getPalavra());
        System.out.println("Raiz: " + arvore.getRaiz().getPalavra());
        System.out.println("Quantidade de nos: " + arvore.qtdNos());
    


        System.out.println("\n\n teste remove caso sem filhos");
        arvore.remove("abacate");
        arvore.rastreioInordem();
        System.out.println("Quantidade de nos: " + arvore.qtdNos());
        
        System.out.println("\n\n teste remove caso com 1 filho");
        arvore.remove("teste3");
        arvore.rastreioInordem();
        System.out.println("Quantidade de nos: " + arvore.qtdNos());
        
        System.out.println("\n\n teste remove caso nao encontrado");
        System.out.println(arvore.remove("meerio"));
        
        System.out.println("\n\n teste remove caso com 2 filhos");
        arvore.remove("abelha");
        arvore.rastreioInordem();
        System.out.println("Quantidade de nos: " + arvore.qtdNos());

        System.out.println("\n\n teste remove caso raiz");
        arvore.remove("teste");
        arvore.rastreioInordem();
        System.out.println(arvore.getRaiz().getPalavra());
        System.out.println("Quantidade de nos: " + arvore.qtdNos());

*/


/*
try (Scanner leitor = new Scanner(arquivo_teste)) {

  /**
   * Loop passando por cada palavra do arquivo
  /
  int i = 0;
  while (leitor.hasNextLine()) {
    String conteudo = leitor.next();
    System.out.print("palavra " + (i) + ": " + conteudo);

    vetor[i] = conteudo;

    if (i > 0) {
      System.out.println(" --- eh maior que o anterior? -> " + (vetor[i].compareTo(vetor[i - 1])));
    }

    if (i > 0) {
      System.out.println(" --- eh maior que o anterior? -> " + (vetor[i].compareTo(vetor[i - 1]) > 0));
    } else {
      System.out.println(" --- eh maior que o anterior? -> " + false);
    }
    i++;
  }

  leitor.close();
} catch (FileNotFoundException ex) {
  System.err.println("Arquivo não encontrado: " + ex.getMessage());
}
*/






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