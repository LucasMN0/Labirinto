package LABIRINTO;

import java.util.*;

public class Armas extends Tesouros{
    private int dano;

    public Armas(String nome,ArrayList<ArrayList<String>> localizacao,String tipo, int dano){
        super(nome,localizacao,tipo);
        this.dano=dano;
    }

    public int getDano(){
        return dano;
    }
}