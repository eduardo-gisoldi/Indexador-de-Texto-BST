import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe Usada no desenvolvimento para aprender conceitos básicos de manipulação de arquivos e comparação de strings.
 */
public class Learning {
  public static void main(String[] args) {
    File arquivo_teste = new File("arquivo teste aqui\\arquivoTeste.txt");

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


/*  Testes realizados no App.java antigo

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
