package LABIRINTO;

import java.util.*;

public class Dinheiro extends Tesouros{
    private int quantidade;

    public Dinheiro(String nome, int linha, int coluna, String tipo, int quantidade){
        super(nome, linha, coluna, tipo);
        this.quantidade=quantidade;
    }

    public int getQuantidade(){
        return quantidade;
    }
}