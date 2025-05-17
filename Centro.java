package LABIRINTO;

import java.util.*;

public class Centro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Configuração inicial
        System.out.println("Escolha a dificuldade do mapa:");
        System.out.println("1 - Fácil");
        System.out.println("2 - Médio");
        System.out.println("3 - Difícil");
        System.out.print("Digite a dificuldade: ");
        int dificuldadeMapa = sc.nextInt();
        sc.nextLine(); // Limpa o buffer

        // Criação do mapa principal
        Labirinto mapaPrincipal = new Labirinto(0, dificuldadeMapa, true);
        mapaPrincipal.gerar_Mapa(dificuldadeMapa);

        if (mapaPrincipal.getInicioI() < 0 ||
                mapaPrincipal.getInicioJ() < 0 ||
                mapaPrincipal.getInicioI() >= mapaPrincipal.getEstrutura().size() ||
                mapaPrincipal.getInicioJ() >= mapaPrincipal.getEstrutura().get(0).size()) {
            System.out.println("Posição inicial inválida no mapa principal!");
            return;
        }

        ArrayList<String> tesourosEncontrados = new ArrayList<>();
        Aventureiro jogador = new Aventureiro("Lucas", tesourosEncontrados, mapaPrincipal,
                mapaPrincipal.getInicioI(), mapaPrincipal.getInicioJ());

        // Game loop principal
        while (true) {
            // Exibe o mapa/labirinto atual
            System.out.println("\n=== Mapa Atual ===");
            jogador.getLabirintoAtual().imprimirLabirinto();

            // Verifica se chegou na sala do boss final
            if (jogador.getLabirintoAtual() == mapaPrincipal &&
                    mapaPrincipal.getEstrutura().get(jogador.getPosI()).get(jogador.getPosJ()).equals("F")) {
                System.out.println("\n=== VOCÊ ENCONTROU O BOSS FINAL! ===");
                break;
            }

            // Input do jogador
            System.out.print("\nDigite a direção (W A S D), M para Menu ou Q para sair: ");
            char comando = sc.next().toUpperCase().charAt(0);
            sc.nextLine(); // Limpa o buffer

            if (comando == 'Q') break;

            if (comando == 'M') {
                jogador.mostrarMenu();
                continue;
            }

            // Movimento
            if (!jogador.mover(comando)) {
                System.out.println("Movimento inválido!");
            }
        }
    }
}

// centro antigo
// public class Centro{
//     public static void main(String[] args) {
//         int x = 12;
//         int y = 6;
//         char Direcao;
//         Scanner sc = new Scanner(System.in);
//         //Labirinto lab = new Labirinto(1);
//         Labirinto[] lab = new Labirinto[x+1];

//         for(int i=0;i<=x;i++){
//             lab[i] = new Labirinto(i);
//         }
//         ArrayList<String> tesourosEncontrados = new ArrayList<>();

//         Aventureiro P2 = new Aventureiro("Lucas",tesourosEncontrados,lab[1],lab[1].getInicioI(),lab[1].getInicioJ());
//         Aventureiro P3 = new Aventureiro("Lucas",tesourosEncontrados,lab[y],lab[y].getInicioI(),lab[y].getInicioJ());

//         lab[1].imprimirLabirinto();

//         System.out.print("Digite a direção: ");
//         Direcao = sc.next().charAt(0);
//         P2.mover(Direcao);

//         System.out.println("Aventureiro " + P2.getPosJ() + ", " + P2.getPosI() + " no mapa");
//         //Lobby.limparTerminal();

//         while(!(P3.sair())){
//             lab[y].imprimirLabirinto();
//             System.out.print("Digite a direção: ");
//             Direcao = sc.next().charAt(0);
//             P3.mover(Direcao);


//             System.out.println("Aventureiro " + P3.getPosJ() + ", " + P3.getPosI() + " no labirinto");
//         }
//         lab[y].limparTerminal();



//         System.out.println("Aventureiro " + P2.getPosJ() + ", " + P2.getPosI() + " no mapa");
//         lab[1].imprimirLabirinto();

//         // Musica music = new Musica();
//         // music.playLevel(3);


//         // for (int i = 0; i < x; i++) {
//         //     labirintos.add(new Labirinto());
//         // }

//         // for (int i = 0; i < labirintos.size(); i++) {
//         //     System.out.println("Labirinto #" + (i + 1));
//         //     labirintos.get(i).imprimirLabirinto();
//         //     System.out.println();
//         // }
//     }
// }
