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
            // Verifica se chegou na sala do boss final
            if (jogador.getLabirintoAtual() == mapaPrincipal &&
                    jogador.getPosI() == mapaPrincipal.getFimI() && jogador.getPosJ() == mapaPrincipal.getFimJ()) {
                System.out.println("\n====PARABÉNS, VOCÊ TERMINOU O LABIRINTO====");
                //System.out.println("\n=== VOCÊ ENCONTROU O BOSS FINAL! ===");
                break;
            }
            
            
            
            // Exibe o mapa/labirinto atual
            System.out.println("\n=== Mapa Atual ===");
            jogador.getLabirintoAtual().imprimirLabirinto();


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
