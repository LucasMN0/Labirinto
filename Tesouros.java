package LABIRINTO;

import java.util.*;

public abstract class Tesouros{
    private String nome;
    private ArrayList<ArrayList<String>> localizacao;
    private String tipo;

    public Tesouros(String nome,ArrayList<ArrayList<String>> localizacao,String tipo){
        this.nome=nome;
        this.localizacao=localizacao;
        this.tipo=tipo;
    } 

    public String getTipo(){
        return tipo;
    }
    public String getNome(){
        return nome;
    }
    public ArrayList<ArrayList<String>> getLocal(){
        return localizacao;
    }
}