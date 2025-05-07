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



    public void mover(){

    }
    public void coletarRecursos(){

    }




}



class Labirinto{
    private ArrayList<ArrayList<String>> estruturaLabirinto;
    private ArrayList<String> listaTesouros;
    private ArrayList<String> listaPerigos;
    private static int contador = 0;
    private int ID;

    public Labirinto(){
        estruturaLabirinto = new ArrayList<>();
        listaTesouros = new ArrayList<>();
        listaPerigos = new ArrayList<>();
        this.ID=++contador;

        gerar_labirinto(this.ID);
    }



    public void gerar_labirinto(int ID){
        String[][] matriz;
        switch(ID){
            case 1:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X"},
                {"X", " ", "T", " ", "S", "X"},
                {"X", "P", " ", " ", "X"},
                {"E", "O", " ", "T", "X"},
                {"X", "X", "X", "X", "X"},
                };
                break;
            case 2:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X", "X", "S", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"E", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                break;
            case 3:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X", "X", "S", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"E", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                break;
            case 4:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X", "X", "S", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"E", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                break;
            case 5:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X", "X", "S", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"E", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                break;
            case 6:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X", "X", "S", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"E", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                break;
            case 7:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X", "X", "S", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"E", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                break;
            case 8:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X", "X", "S", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"E", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                break;
            case 9:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X", "X", "S", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"E", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                break;
            case 10:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X", "X", "S", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"E", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                break;
            case 11:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X", "X", "S", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"E", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                break;
            case 12:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X", "X", "S", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"E", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                break;
            case 13:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X", "X", "S", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"E", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                break;
            case 14:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X", "X", "S", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"E", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                break;
            case 15:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X", "X", "S", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"E", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                break;
            case 16:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X", "X", "S", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"E", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                break;
            default:
                matriz = new String[][] {
                    {"X", "X", "X"},
                    {"X", " ", "X"},
                    {"X", "X", "X"}
                };
        

        }   

        for (int i = 0; i < matriz.length; i++) {
            ArrayList<String> linha = new ArrayList<>();
            for (int j = 0; j < matriz[i].length; j++) {
                String celula = matriz[i][j];
                linha.add(celula);

            }
            estruturaLabirinto.add(linha);
    

        }
    }

        public void imprimirLabirinto() {
        for (ArrayList<String> linha : estruturaLabirinto) {
            for (String celula : linha) {
                System.out.print(celula + " ");
            }
            System.out.println();
        }
    }

    public void adicionarTesouros(ArrayList<String> listaTesouros){

    }

    public void removerTesouros(ArrayList<String> listaTesouros){

    }

    public void adicionarPerigo(ArrayList<String> listaPerigos){

    }

    public void removerPerigo(ArrayList<String> listaPerigos){

    }
}






public class Centro{
        public static void main(String[] args) {
        
        ArrayList<Labirinto> labirintos = new ArrayList<>();

        Labirinto lab1 = new Labirinto(); // usa layout padrão
        Labirinto lab2 = new Labirinto(); // outro labirinto (poderia receber layout diferente)
        Labirinto lab3 = new Labirinto();
        Labirinto lab4 = new Labirinto();

        labirintos.add(lab1);
        labirintos.add(lab2);
        labirintos.add(lab3);
        labirintos.add(lab4);

        for (int i = 0; i < labirintos.size(); i++) {
            System.out.println("Labirinto #" + (i + 1));
            labirintos.get(i).imprimirLabirinto();
            System.out.println();
        }
    }
}