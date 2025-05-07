import java.util.*;
import java.util.ArrayList;

class Aventureiro{
    private String nome;
    private ArrayList<ArrayList<String>> localizacao;
    private ArrayList<String> tesourosEncontrados;

    public Aventureiro(String nome, ArrayList<ArrayList<String>> localizacao, ArrayList<String> tesourosEncontrados){
        this.nome = nome;
        this.localizacao = localizacao;
        this.tesourosEncontrados = tesourosEncontrados;
    }



    public mover(){

    }
    public coletarRecursos(){

    }




}



class Labirinto{
    private ArrayList<ArrayList<String>> estruturaLabirinto;
    private ArrayList<String> listaTesouros;
    private ArrayList<String> listaPerigos;

    public Labirinto(ArrayList<ArrayList<String>> estruturaLabirinto,ArrayList<String> listaTesouros,ArrayList<String> listaPerigos){
        this.estruturaLabirinto=estruturaLabirinto;
        this.listaTesouros=listaTesouros;
        this.listaPerigos=listaPerigos;
    }



    public gerar_labirinto(ArrayList<ArrayList<String>> estruturaLabirinto){

    }

    public adicionarTesouros(ArrayList<String> listaTesouros){

    }

    public removerTesouros(ArrayList<String> listaTesouros){

    }

    public adicionarPerigo(ArrayList<String> listaPerigos){

    }

    public removerPerigo(ArrayList<String> listaPerigos){

    }
}






public class Centro{
    public static void main(String[] args){






    }
}