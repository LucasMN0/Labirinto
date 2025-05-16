package LABIRINTO;

import java.util.*;

public class Armas extends Tesouros{
    private int dano;

    public Armas(String nome,int linha, int coluna, String tipo, int dano){
        super(nome, linha, coluna, tipo);
        this.dano=dano;
    }

    public int getDano(){
        return dano;
    }
}