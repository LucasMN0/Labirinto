package LABIRINTO;

import java.util.*;
import java.util.ArrayList;
import java.util.Random;

public class Centro{
        public static void main(String[] args) {
        int x = 12;
        char Direcao;
        Scanner sc = new Scanner(System.in);
        Labirinto lab = new Labirinto();
        Mapa Lobby = new Mapa(1);
        ArrayList<String> tesourosEncontrados = new ArrayList<>();
        Aventureiro_Mapa P1 = new Aventureiro_Mapa(Lobby);

        Aventureiro P2 = new Aventureiro("Lucas",tesourosEncontrados,lab);

        Lobby.imprimirMapa();

        System.out.printf("Digite a direção: ");
        Direcao = sc.next().charAt(0);
        P1.mover(Direcao);

        System.out.println("Aventureiro " + P1.getPosJ() + ", " + P1.getPosi() + " no mapa");
        //Lobby.limparTerminal();

        while(!(P2.sair())){
            lab.imprimirLabirinto();
            System.out.printf("Digite a direção: ");
            Direcao = sc.next().charAt(0);
            P2.mover(Direcao);

            System.out.println("Aventureiro " + P2.getPosJ() + ", " + P2.getPosi() + " no labirinto");
            lab.imprimirLabirinto();
        }
        lab.limparTerminal();



        System.out.println("Aventureiro " + P1.getPosJ() + ", " + P1.getPosi() + " no mapa");
        Lobby.imprimirMapa();
        //lab.imprimirLabirinto();
        // Musica music = new Musica();
        // music.playLevel(3);

        //Lobby.imprimirMapa();

        // for (int i = 0; i < x; i++) {
        //     labirintos.add(new Labirinto());
        // }
            
        // for (int i = 0; i < labirintos.size(); i++) {
        //     System.out.println("Labirinto #" + (i + 1));
        //     labirintos.get(i).imprimirLabirinto();
        //     System.out.println();
        // }
    }
}