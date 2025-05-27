// Perigo.java
package LABIRINTO;

import java.io.Serializable;
import java.util.Random;

public class Perigo implements Serializable{
    private String nome;
    private String historia;
    private String descricao;
    private int IDP;
    private int dano;
    private int linha;
    private int coluna;
    private static final long serialVersionUID = 1L;

    public Perigo(String nome, String historia, String descricao, int IDP, int dano, int linha, int coluna){
        this.nome = nome;
        this.historia = historia;
        this.descricao = descricao;
        this.IDP = IDP;
        this.dano = dano;
        this.linha = linha;
        this.coluna = coluna;
    }

    //Getters
    public String getNome() {return nome;}
    public String getHistoria() {return  historia;}
    public String getDescricao() {return descricao;}
    public int getIDP() {return IDP;}
    public int getDano() {return dano;}
    public int getLinha() { return linha; }
    public int getColuna() { return coluna; }

    public Perigo copiar() {
        return null;
    }

    public static Perigo criarPerigoAleatorio(Random rand) {
        if (rand.nextBoolean()) {
            return Armadilha.getArmadilhaAleatoria();
        } else {
            return Inimigo.getInimigoAleatorio();
        }
    }
}