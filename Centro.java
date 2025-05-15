package LABIRINTO;

import java.util.*;
import java.util.ArrayList;
import java.util.Random;

public class Centro{
        public static void main(String[] args) {
        int x = 12;
        int y = 6;
        char Direcao;
        Scanner sc = new Scanner(System.in);
        //Labirinto lab = new Labirinto(1);
        Labirinto[] lab = new Labirinto[x+1];

        for(int i=0;i<=x;i++){
            lab[i] = new Labirinto(i);
        }
        ArrayList<String> tesourosEncontrados = new ArrayList<>();

        Aventureiro P2 = new Aventureiro("Lucas",tesourosEncontrados,lab[1],lab[1].getInicioI(),lab[1].getInicioJ());
        Aventureiro P3 = new Aventureiro("Lucas",tesourosEncontrados,lab[y],lab[y].getInicioI(),lab[y].getInicioJ());

        lab[1].imprimirLabirinto();

        System.out.printf("Digite a direção: ");
        Direcao = sc.next().charAt(0);
        P2.mover(Direcao);

        System.out.println("Aventureiro " + P2.getPosJ() + ", " + P2.getPosI() + " no mapa");
        //Lobby.limparTerminal();

        while(!(P3.sair())){
            lab[y].imprimirLabirinto();
            System.out.printf("Digite a direção: ");
            Direcao = sc.next().charAt(0);
            P3.mover(Direcao);
            

            System.out.println("Aventureiro " + P3.getPosJ() + ", " + P3.getPosI() + " no labirinto");
        }
        lab[y].limparTerminal();



        System.out.println("Aventureiro " + P2.getPosJ() + ", " + P2.getPosI() + " no mapa");
        lab[1].imprimirLabirinto();
        // Musica music = new Musica();
        // music.playLevel(3);


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