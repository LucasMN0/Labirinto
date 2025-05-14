package Labirinto;

import java.util.*;

class Dinheiro extends Tesouros{
    private int quantidade;

    public Dinheiro(String nome,ArrayList<ArrayList<String>> localizacao,String tipo, int quantidade){
        super(nome,localizacao,tipo);
        this.quantidade=quantidade;
    }

    public int getQuantidade(){
        return quantidade;
    }
}