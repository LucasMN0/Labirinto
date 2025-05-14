import java.util.*;
import java.util.ArrayList;
import java.util.Random;

public class Centro{
        public static void main(String[] args) {
        int x = 12;
        ArrayList<Labirinto> labirintos = new ArrayList<>();
        Labirinto_Principal Lobby = new Labirinto_Principal(1);
        DungeonMusic music = new DungeonMusic();
        music.playLevel(3);

        Lobby.imprimirLabirinto();

        for (int i = 0; i < x; i++) {
            labirintos.add(new Labirinto());
        }
            
        for (int i = 0; i < labirintos.size(); i++) {
            System.out.println("Labirinto #" + (i + 1));
            labirintos.get(i).imprimirLabirinto();
            System.out.println();
        }
    }
}