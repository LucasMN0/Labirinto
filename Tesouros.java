package LABIRINTO;

public abstract class Tesouros {
    private String nome;
    private int linha;
    private int coluna;
    private String tipo;

    public Tesouros(String nome, int linha, int coluna, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
        this.linha = linha;
        this.coluna = coluna;
    }

    public String getTipo() {
        return tipo;
    }
    public String getNome() {
        return nome;
    }
    public int getLinha() {
        return linha;
    }
    public int getColuna() {
        return coluna;
    }
    public abstract int getValor();
    public abstract int getValorVenda();

}
