package LABIRINTO;

import java.util.*;

public class Perigo{
    private ArrayList<ArrayList<String>> localizacao;
    private int dano;

    public Perigo(ArrayList<ArrayList<String>> localizacao , int dano){
        this.localizacao = localizacao;
        this.dano = dano;
    }


}