public class No {
    // Atributos:
    int valor; // numero armazenado no nó
    No esq;    // filho esquerdo
    No dir;    // filho direito

    // Construtor
    No(int v) {
        valor = v;
        esq = null;
        dir = null;
    }

    //Getters e Setters
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
