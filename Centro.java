package LABIRINTO;

import java.util.*;

public class Centro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Introdução do jogo
        System.out.println("============================================");
        System.out.println("|         nome foda do jogo           |");
        System.out.println("============================================");
        System.out.println("Um jogo de aventura e perigos infinitos...");
        System.out.println("Prepare-se para enfrentar criaturas lendárias!");
        esperar(2000);

        // Pede o nome do jogador
        System.out.print("\nAntes de começar, qual é o seu nome, aventureiro? ");
        System.out.print("\nNome: ");
        String nomeJogador = sc.nextLine();
        if (nomeJogador.trim().isEmpty()) {
            nomeJogador = "Aventureiro";
        }

        KitClasse[] classes = {
                new KitClasse("Guerreiro", 15, 15, 2, 0.3, -1),
                new KitClasse("Mago", 10, 5, 20, 0.15, 2),
                new KitClasse("Arqueiro", 13, 10, 10, 0.2, 7)
        };

        System.out.println("\nSelecione sua classe:");
        System.out.println("1 - Guerreiro");
        System.out.println("   +15 Vida, +15 Ataque, +2 Dano Verdadeiro, +30% Armadura, -1 Velocidade");
        System.out.println("2 - Mago");
        System.out.println("   +10 Vida, +5 Ataque, +20 Dano Verdadeiro, +15% Armadura, +2 Velocidade");
        System.out.println("3 - Arqueiro");
        System.out.println("   +13 Vida, +10 Ataque, +10 Dano Verdadeiro, +20% Armadura, +7 Velocidade");
        System.out.print("Escolha (1-3): ");

        int escolhaClasse;
        while (true) {
            try {
                escolhaClasse = Integer.parseInt(sc.nextLine());
                if (escolhaClasse >= 1 && escolhaClasse <= 3) break;
                System.out.print("Escolha inválida! Digite 1, 2 ou 3: ");
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida! Digite 1, 2 ou 3: ");
            }
        }

        KitClasse kitEscolhido = classes[escolhaClasse - 1];
        System.out.println("\nVocê escolheu a classe " + kitEscolhido.getNome() + "!");

        // Variáveis de controle de progresso
        boolean[] niveisCompletados = new boolean[3]; // Fácil, Médio, Difícil

        // Loop principal do jogo (permite reiniciar após morte/vitória)
        boolean jogando = true;
        while (jogando) {
            // Menu de seleção de dificuldade
            System.out.println("\n====================================");
            System.out.println("|          MENU PRINCIPAL          |");
            System.out.println("====================================");
            System.out.println("1 - Jogar no nível Fácil" + (niveisCompletados[0] ? " (COMPLETADO)" : ""));
            System.out.println("2 - Jogar no nível Médio" + (niveisCompletados[1] ? " (COMPLETADO)" : ""));
            System.out.println("3 - Jogar no nível Difícil" + (niveisCompletados[2] ? " (COMPLETADO)" : ""));
            System.out.println("4 - Sair do jogo");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número de 1 a 4.");
                continue;
            }

            if (opcao == 4) {
                System.out.println("Até a próxima, " + nomeJogador + "!");
                jogando = false;
                System.exit(0);
            }

            if (opcao < 1 || opcao > 3) {
                System.out.println("Opção inválida! Digite um número de 1 a 4.");
                continue;
            }

            int dificuldadeMapa = opcao; // 1-Fácil, 2-Médio, 3-Difícil

            // Verifica se o jogador já completou esse nível
            if (niveisCompletados[dificuldadeMapa-1]) {
                System.out.print("\nVocê já completou esse nível. Deseja jogar novamente? (S/N): ");
                char resposta = sc.nextLine().toUpperCase().charAt(0);
                if (resposta != 'S') {
                    continue;
                }
            }

            // Prepara o jogo
            boolean reiniciarNivel = true;
            while (reiniciarNivel) {
                // Criação do mapa principal
                Labirinto mapaPrincipal = new Labirinto(0, dificuldadeMapa, true);
                mapaPrincipal.gerar_Mapa(dificuldadeMapa);

                // Verifica posição inicial
                if (mapaPrincipal.getInicioI() < 0 ||
                        mapaPrincipal.getInicioJ() < 0 ||
                        mapaPrincipal.getInicioI() >= mapaPrincipal.getEstrutura().size() ||
                        mapaPrincipal.getInicioJ() >= mapaPrincipal.getEstrutura().get(0).size()) {
                    System.out.println("Posição inicial inválida no mapa principal!");
                    return;
                }

                // Cria jogador novo (reinicia todos os itens e status)
                List<Tesouros> tesourosEncontrados = new ArrayList<>();
                Aventureiro jogador = new Aventureiro(nomeJogador, tesourosEncontrados, mapaPrincipal, mapaPrincipal.getInicioI(), mapaPrincipal.getInicioJ(), kitEscolhido);

                System.out.println("\nBem-vindo, " + nomeJogador + "!");
                System.out.println("Você está prestes a enfrentar o nível " +
                        (dificuldadeMapa == 1 ? "FÁCIL" : dificuldadeMapa == 2 ? "MÉDIO" : "DIFÍCIL"));
                System.out.println("Boa sorte em sua jornada!\n");
                esperar(2000);

                // Game loop principal
                boolean emJogo = true;
                while (emJogo) {
                    // Verifica se chegou na sala do boss final
                    if (jogador.getLabirintoAtual() == mapaPrincipal &&
                            jogador.getPosI() == mapaPrincipal.getFimI() &&
                            jogador.getPosJ() == mapaPrincipal.getFimJ()) {
                        System.out.println("\n==== PARABÉNS, VOCÊ TERMINOU O LABIRINTO ====");
                        System.out.println("Você completou o nível " +
                                (dificuldadeMapa == 1 ? "FÁCIL" : dificuldadeMapa == 2 ? "MÉDIO" : "DIFÍCIL") + "!");
                        niveisCompletados[dificuldadeMapa-1] = true;
                        emJogo = false;
                        reiniciarNivel = false;
                        esperar(3000);
                        break;
                    }

                    // Verifica se o jogador morreu
                    if (!jogador.estaVivo()) {
                        System.out.println("\n====================================");
                        System.out.println("|           GAME OVER             |");
                        System.out.println("====================================");
                        System.out.println("Você foi derrotado, " + nomeJogador + "...");
                        System.out.print("Deseja tentar novamente? (S/N): ");
                        char resposta = sc.nextLine().toUpperCase().charAt(0);
                        emJogo = false;
                        reiniciarNivel = (resposta == 'S');
                        break;
                    }

                    // Exibe o mapa/labirinto atual
                    System.out.println("\n=== Mapa Atual ===");
                    jogador.getLabirintoAtual().imprimirLabirinto();

                    // Input do jogador
                    System.out.print("\nDigite a direção (W A S D), M para Menu ou Q para sair do jogo: ");
                    String input = sc.nextLine().trim();
                    if (input.isEmpty()) {
                        System.out.println("Digite um comando válido (W, A, S, D, M ou Q)");
                        continue;
                    }
                    char comando = input.toUpperCase().charAt(0);

                    switch (comando) {
                        case 'Q':
                            System.out.print("Deseja realmente sair? (S/N): ");
                            char confirmacao = sc.nextLine().toUpperCase().charAt(0);
                            if (confirmacao == 'S') {
                                emJogo = false;
                                reiniciarNivel = false;
                            }
                            break;
                        case 'M':
                            jogador.mostrarMenu();
                            break;
                        default:
                            if (!jogador.mover(comando)) {
                                System.out.println("Movimento inválido!");
                            }
                    }
                }
            }
        }
        sc.close();
    }
    private static void esperar(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}