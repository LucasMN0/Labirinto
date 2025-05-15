package LABIRINTO;

import java.util.*;

public class Labirinto {
    private ArrayList<ArrayList<String>> estruturaLabirinto;
    private ArrayList<String> listaTesouros;
    private ArrayList<String> listaPerigos;
    private int ID;
    private int InicioI;
    private int InicioJ;
    private int FimI;
    private int FimJ;

    public Labirinto(int ID) {
        estruturaLabirinto = new ArrayList<>();
        listaTesouros = new ArrayList<>();
        listaPerigos = new ArrayList<>();
        this.ID = ID;

        gerar_labirinto(this.ID);
    }

    public int getInicioI(){
        return this.InicioI;
    }
    public int getInicioJ(){
        return this.InicioJ;
    }
    public int getFimI(){
        return this.FimI;
    }
    public int getFimJ(){
        return this.FimJ;
    }

    public ArrayList<ArrayList<String>> getEstrutura(){
        return estruturaLabirinto;
    }
    /*
     * coloquei um treco de tesouro aq, talvez sej melhor colocar ele em outro canto.
     * 
     */
    public ArrayList<String> getListaTesouros(){
        return listaTesouros;
    }

    public void gerar_labirinto(int ID){
        String[][] matriz;
        switch(ID){
            case 1:
                matriz = new String[][] {
                {"X", "X", "S", "X", "X"},
                {"X", " ", " ", " ", "X"},
                {"X", " ", " ", " ", "X"},
                {"X", " ", "O", " ", "X"},
                {"X", "X", "E", "X", "X"},
                };
                this.InicioI = 3;
                this.InicioJ = 2;
                this.FimI = 0;
                this.FimJ = 2;
                break;
            case 2:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X", "X", "S", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"E", "O", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                this.InicioI = 6;
                this.InicioJ = 1;
                this.FimI = 0;
                this.FimJ = 6;
                break;
            case 3:
                matriz = new String[][] {
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"E", "O", " ", " ", " ", " ", " ", "S"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                this.InicioI = 2;
                this.InicioJ = 1;
                this.FimI = 2;
                this.FimJ = 7;
                break;
            case 4:
                matriz = new String[][] {
                {".", ".", "X", "X", "S", "X","X"},
                {".", "X", "X", " ", " ", " ", "X", "X"},
                {"X", "X", " ", " ", " ", " ", " ", "X","X"},
                {"X", " ", " ", " ", "X", " ", " ", " ","X"},
                {"X", " ", " ", "X", "X", "X", " ", " ","X"},
                {"X", " ", " ", " ", "X", " ", " ", " ","X"},
                {"X", "X", " ", " ", " ", " ", " ", "X","X"},
                {".", "X", "X", " ", "O", " ", "X", "X"},
                {".", ".", "X", "X", "E", "X" ,"X"},
                };
                this.InicioI = 7;
                this.InicioJ = 4;
                this.FimI = 0;
                this.FimJ = 4;
                break;
            case 5:
                matriz = new String[][] {
                {"X", "X", "E", "X", "X"},
                {"X", " ", "O", " ", "X"},
                {"X", " ", " ", " ", "X"},
                {"X", " ", " ", " ", "X"},
                {"X", " ", " ", " ", "X"},
                {"X", " ", " ", " ", "X"},
                {"X", " ", " ", " ", "X", "X", "X", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", "S"},
                {"X", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                this.InicioI = 1;
                this.InicioJ = 2;
                this.FimI = 8;
                this.FimJ = 7;
                break;
            case 6:
                matriz = new String[][] {
                {".", ".", ".", ".", "X", "X", "S", "X", "X"},
                {".", ".", ".", ".", "X", " ", " ", " ", "X"},
                {".", ".", ".", ".", "X", " ", " ", " ", "X"},
                {".", ".", ".", ".", "X", " ", " ", " ", "X"},
                {".", ".", ".", ".", "X", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", " ", " ", " ", "X", "X", "X", "X", "X"},
                {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", " ", " ", " ", "X", "X", "X", "X", "X"},
                {".", ".", ".", ".", "X", " ", " ", " ", "X"},
                {".", ".", ".", ".", "X", " ", " ", " ", "X"},
                {".", ".", ".", ".", "X", " ", " ", " ", "X"},
                {".", ".", ".", ".", "X", " ", "O", " ", "X"},
                {".", ".", ".", ".", "X", "X", "E", "X", "X"},
                };
                this.InicioI = 13;
                this.InicioJ = 6;
                this.FimI = 0;
                this.FimJ = 6;
                break;
            case 7:
                matriz = new String[][] {
        {".", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X","."},
        {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ","X"},
        {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X", " ", "X", " ","X"},
        {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ","X"},
        {"X", " ", " ", " ", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", " ", " ", " ", " ", " ","X"},
        {"X", " ", " ", " ", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", ".", "X", " ", " ", " ", " ", " ","X"},
        {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X", ".", "X", "X", " ", "O", " ", "X","X"},
        {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X", ".", ".", "X", "X", "E", "X", "X","."},
        {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X", ".", ".", ".", ".", "X"},
        {".", "X", "X", "X", "X", "X", "X", "X", "X", "X", " ", " ", " ", "X", ".", ".", ".", ".", "X"},
        {".", ".", ".", ".", "X", " ", " ", " ", " ", " ", " ", " ", " ", "X", ".", ".", ".", "X", ".","X"},
        {".", ".", ".", ".", "S", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
        {".", ".", ".", ".", "X", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
        {".", ".", ".", ".", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
                };
                this.InicioI = 6;
                this.InicioJ = 19;
                this.FimI = 11;
                this.FimJ = 4;
                break;
            case 8:
                matriz = new String[][] {
                {"X", "X", ".", ".", ".", "X", "X", "X", "X", "X"},
                {"X", " ", "X", ".", "X", " ", " "," "," "," ", "X"},
                {"X", " ", " ", "X", " ", " ", " ", " ", " ", "X"," ", "X"},
                {"E", "O", " ", " ", " ", " ", " ", " ", " ", " "," ", "S"},
                {"X", " ", " ", "X", " ", " ", " ", " ", " ", " "," ", "X"},
                {"X", " ", "X", ".", "X", " ", " "," "," "," ", "X"},
                {"X", "X", ".", ".", ".", "X", "X", "X", "X","X"},
                };
                this.InicioI = 3;
                this.InicioJ = 1;
                this.FimI = 3;
                this.FimJ = 11;
                break;
            case 9:
                matriz = new String[][] {
                {".",".","X","X","X","S","X","X","X"},
                {".","X","X"," "," "," "," "," ","X","X"},
                {"X","X"," ","X","X"," "," "," "," ","X"},
                {"X"," "," ","X","X"," "," "," ","X"},
                {"X"," "," "," ", " ", " "," ","X"},
                {"X"," "," "," ", " ", " ","X"},
                {"X"," "," "," ", " ", " "," ","X"},
                {"X"," "," "," "," "," "," "," ","X"},
                {"X","X"," "," "," "," "," "," "," ","X"},
                {".","X","X"," "," ","O"," "," ","X","X"},
                {".",".","X","X","X","E","X","X","X"},
                };
                this.InicioI = 9;
                this.InicioJ = 5;
                this.FimI = 0;
                this.FimJ = 5;
                break;
            case 10:
                matriz = new String[][] {
                {".",".",".",".","X","X","S","X","X",".",".",".","."},
                {".",".",".",".","X"," "," "," ","X",".",".",".","."},
                {".",".",".",".","X"," "," "," ","X",".",".",".","."},           
                {".",".",".",".","X"," "," "," ","X",".",".",".","."},
                {"X","X","X","X","X"," "," "," ","X","X","X","X","X"},
                {"X"," "," "," "," "," "," "," "," "," "," "," ","X"},
                {"X"," "," "," "," "," "," "," "," "," "," "," ","X"},
                {"X"," "," "," "," "," "," "," "," "," "," "," ","X"},
                {"X","X","X","X","X"," "," "," ","X","X","X","X","X"}, // 7
                {".",".",".",".","X"," "," "," ","X",".",".",".","."},  // 11
                {".",".",".",".","X"," "," "," ","X",".",".",".","."},
                {".",".",".",".","X"," "," "," ","X",".",".",".","."},          
                {".",".",".",".","X"," "," "," ","X",".",".",".","."},
                {".",".",".",".","X"," "," "," ","X",".",".",".","."},           
                {".",".",".",".","X"," "," "," ","X",".",".",".","."},
                {".",".",".",".","X"," ","O"," ","X",".",".",".","."},
                {".",".",".",".","X","X","E","X","X",".",".",".","."},
                };
                this.InicioI = 15;
                this.InicioJ = 6;
                this.FimI = 0;
                this.FimJ = 6;
                break;
            case 11:
                matriz = new String[][] {
                {".",".",".", "X", "X", "X", "S", "X","X", "X"},
                {".",".","X","X"," ", " ", " ", " ", " ", "X","X"},
                {".","X","X"," ", " ", " ", " ", " "," "," ", "X","X"},
                {"X", "X", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X","X"},
                {"X", " ", " ", "X", "X", " ", " ", " ", "X", "X", " ", " ","X"},
                {"X", " ", " ", "X", "X", " ", " ", " ", "X", "X", " ", " ","X"},
                {"X", " ", " ", "X", "X", " ", "X", " ", "X", "X", " ", " ","X"}, //Sala BOSS final
                {"X", " ", " ", " ", " ", " ", "X", " ", " ", " ", " ", " ","X"},
                {"X", "X", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X","X"},
                {".","X","X", " ", " ", "X", " ", "X"," "," ", "X","X"},
                {".",".","X", "X", " ", "X", " ", "X"," ", "X","X"},
                {".",".",".", "X", " ", "X", "O", "X"," ", "X"},
                {".",".",".", "X", "X", "X", "E", "X","X", "X"},
                };
                this.InicioI = 11;
                this.InicioJ = 6;
                this.FimI = 0;
                this.FimJ = 6;
                break;
            default:
                matriz = new String[][] {
                    {"X", "X", "X", "X", "X", "X", "X"},
                    {"X", " ", "X", " ", "X", " ", "X"},
                    {"X", "X", "X", "X", "X", "X", "X"},
                    {"X", " ", "X", " ", "X", " ", "X"},
                    {"X", "X", "X", "X", "X", "X", "X"},
                    {"X", "O", "X", " ", "X", " ", "X"},
                    {"X", "X", "X", "X", "X", "X", "X"},
                };
                this.InicioI = 6;
                this.InicioJ = 1;
                this.FimI = 0;
                this.FimJ = 6;
        

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

public void gerar_Mapa(int Level){
        String matriz[][];
        switch(Level){
            case 1:
                matriz = new String[][] {
                    {"X", "X", "X", "X", "X"},
                    {"X", "O", " ", " ", "X"},
                    {"X", " ", " ", " ", "X"},
                    {"X", " ", " ", "F", "X"},
                    {"X", "X", "X", "X", "X"},
                };
                break;
            case 2:
                matriz = new String[][] {
                    {"X", "X", "X", "X", "X", "X"},
                    {"X", "O", " ", " ", " ", "X"},
                    {"X", " ", " ", " ", " ", "X"},
                    {"X", " ", " ", " ", " ", "X"},
                    {"X", " ", " ", " ", "F", "X"},
                    {"X", "X", "X", "X", "X", "X"},
                };
                break;
            case 3: 
                matriz = new String[][] {
                    {"X", "X", "X", "X", "X", "X", "X"},
                    {"X", "O", " ", " ", " ", " ", "X"},
                    {"X", " ", " ", " ", " ", " ", "X"},
                    {"X", " ", " ", " ", " ", " ", "X"},
                    {"X", " ", " ", " ", " ", " ", "X"},
                    {"X", " ", " ", " ", " ", "F", "X"},
                    {"X", "X", "X", "X", "X", "X", "X"},
                };
                break;
            default:
                System.out.println("Level inexistente. Será atribuido o level 1!");
                matriz = new String[][] {
                    {"X", "X", "X", "X", "X"},
                    {"X", "O", " ", " ", "X"},
                    {"X", " ", " ", " ", "X"},
                    {"X", " ", " ", "F", "X"},
                    {"X", "X", "X", "X", "X"},
                };
                break;
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
    for (int i = 0; i < estruturaLabirinto.size(); i++) {
        for (int j = 0; j < estruturaLabirinto.get(i).size(); j++) {
            String c = estruturaLabirinto.get(i).get(j);
            if (c == null || c.equals(".")) // para espaços vazios ou células nulas
                System.out.print("  "); // espaço duplo pra alinhar melhor
            else
                System.out.print(c + " ");
        }
        System.out.println();
    }
}


    public void limparTerminal(){
        for(int i=0;i<15;++i){
            System.out.println();
        }
    }

    public void adicionarTesouros(ArrayList<String> listaTesouros) {
        this.listaTesouros.addAll(listaTesouros);
    }

    public void removerTesouros(ArrayList<String> listaTesouros) {
        this.listaTesouros.removeAll(listaTesouros);
    }

    public void adicionarPerigo(ArrayList<String> listaPerigos) {
        this.listaPerigos.addAll(listaPerigos);
    }

    public void removerPerigo(ArrayList<String> listaPerigos) {
        this.listaPerigos.removeAll(listaPerigos);
    }
}