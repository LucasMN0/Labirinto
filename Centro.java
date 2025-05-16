package LABIRINTO;

import java.util.*;

public class Centro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Escolha o nível do labirinto:");
        System.out.println("1 - Fácil");
        System.out.println("2 - Médio");
        System.out.println("3 - Difícil");
        System.out.print("Digite o número do nível: ");
        int nivel = sc.nextInt();


        // Cria o mapa base (nível fácil/médio/difícil)
        Labirinto lab = new Labirinto(nivel);
        lab.gerar_Mapa(nivel);

        ArrayList<String> tesourosEncontrados = new ArrayList<>();
        Aventureiro jogador = new Aventureiro("Lucas", tesourosEncontrados, lab, lab.getInicioI(), lab.getInicioJ());

        lab.imprimirLabirinto(jogador);

        char direcao;
        do {
            System.out.print("Digite a direção (W/A/S/D) ou Q para sair: ");
            direcao = sc.next().toUpperCase().charAt(0);

            if (lab.getEstrutura() == null) {
                System.out.println("Erro: Labirinto principal não foi gerado corretamente!");
                System.exit(1);
            }

            if (direcao == 'Q') {
                System.out.println("Jogo encerrado.");
                break;
            }

            if (jogador.mover(direcao)) {
                System.out.println("Posição atual: (" + jogador.getPosI() + ", " + jogador.getPosJ() + ")");

                // Verifica se entrou em sub-labirinto (apenas no mapa de nível)
                if (lab.isEmSubLabirinto()) {
                    System.out.println("Você entrou em uma sala secreta!");
                    System.out.println("Encontre a saída (S) para voltar ao mapa principal");
                }

                lab.imprimirLabirinto(jogador);
            } else {
                System.out.println("Movimento inválido!");
            }

        } while (!jogador.sair());

        sc.close();
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
