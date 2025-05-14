package LABIRINTO;

import java.util.*;

public class Mapa{
    private int level;
    private ArrayList<ArrayList<String>> estruturaMapa;

    public Mapa(int level) {
        estruturaMapa = new ArrayList<>();
        this.level=level;

        gerar_Mapa(this.level);
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
            estruturaMapa.add(linha);

        }
    }
    public void imprimirMapa() {
        for (ArrayList<String> linha : estruturaMapa) {
            for (String celula : linha) {
                System.out.print(celula + " ");
            }
            System.out.println();
        }
    }
    
    public ArrayList<ArrayList<String>> getEstrutura(){
        return estruturaMapa;
    }

    public void limparTerminal(){
        for(int i=0;i<15;++i){
            System.out.println();
        }
    }

}