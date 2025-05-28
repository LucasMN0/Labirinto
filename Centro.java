package LABIRINTO;

import java.io.*;
import java.util.*;

public class Centro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("============================================");
        System.out.println("|           Labirintos Misteriósos         |");
        System.out.println("============================================");
        System.out.println("Um jogo de aventura e perigos infinitos...");
        System.out.println("Prepare-se para enfrentar criaturas lendárias!");
        esperar(2000);

        System.out.print("\nAntes de começar, qual é o seu nome, aventureiro? ");
        System.out.print("\nNome: ");
        String nomeJogador = sc.nextLine().trim();

        while (!nomeJogador.matches("[a-zA-Z0-9]+")) {
            System.out.println("Por favor, digite um nome válido (apenas letras e números, sem espaços ou símbolos especiais).");
            System.out.print("Nome: ");
            nomeJogador = sc.nextLine().trim();
        }

        if (nomeJogador.isEmpty()) {
            nomeJogador = "Aventureiro";
        }
        Monstruario monstruario = new Monstruario();

        KitClasse[] classes = {
                new KitClasse("Guerreiro", 15, 15, 2, 0.3, -1),
                new KitClasse("Mago", 10, 5, 20, 0.15, 2),
                new KitClasse("Arqueiro", 13, 10, 10, 0.2, 7)
        };

        // Seleção de classe inicial
        KitClasse kitEscolhido = selecionarClasse(sc, classes, null); // Passamos null pois é a primeira seleção

        boolean[] niveisCompletados = new boolean[3]; // Fácil, Médio, Difícil
        carregarProgresso(niveisCompletados);

        boolean jogando = true;
        while (jogando) {
            System.out.println("\n====================================");
            System.out.println("|           MENU PRINCIPAL         |");
            System.out.println("====================================");
            System.out.println("1 - Jogar no nível Fácil" + (niveisCompletados[0] ? " (COMPLETADO)" : ""));
            System.out.println("2 - Jogar no nível Médio" + (niveisCompletados[1] ? " (COMPLETADO)" : ""));
            System.out.println("3 - Jogar no nível Difícil" + (niveisCompletados[2] ? " (COMPLETADO)" : ""));
            System.out.println("4 - Ver Monstruário");
            System.out.println("5 - Ver Tutorial");
            System.out.println("6 - Sair do jogo");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número de 1 a 6.");
                continue;
            }

            if(opcao == 4){
                if (monstruario.temRegistros()) {
                    monstruario.mostrarMonstruario();
                } else {
                    System.out.println("Seu monstruário está vazio. Encontre inimigos e armadilhas para registrá-los!");
                }
                continue;
            }

            if(opcao == 5){
                System.out.println("\n---Tutorial---");
                System.out.println("\n\uD83C\uDFAE - Nos mapas e labirintos: ");
                System.out.println("\nNo jogo você (jogador) é representado por 'O' !");
                System.out.println("Os Tesouros são representados por 'T' !");
                System.out.println("Você pode encontrar inimigos e armadilhas nos labirintos!");
                System.out.println("Use WASD para mover no mapa e labirinto!");
                System.out.println("Digite M para ir pro Menu!");
                System.out.println("Digite Q para sair para o menu do jogo!");
                System.out.println("\n⚔\uFE0F - Em combate: ");
                System.out.println("\nDigite 1 para atacar e 2 para usar consumível!");
                continue;
            }

            if (opcao == 6) {
                System.out.println("Até a próxima, " + nomeJogador + "!");
                salvarProgresso(niveisCompletados);
                jogando = false;
                System.exit(0);
                continue;
            }

            int dificuldadeMapa = opcao;

            if (opcao >= 1 && opcao <= 3) {
                dificuldadeMapa = opcao;

                if (niveisCompletados[dificuldadeMapa - 1]) {
                    System.out.print("\nVocê já completou esse nível. Deseja jogar novamente? (S/N): ");
                    char respostaRejogarCompleto = sc.nextLine().toUpperCase().charAt(0);
                    if (respostaRejogarCompleto != 'S') {
                        continue;
                    }
                }

                // Restante da lógica do jogo...
            } else {
                System.out.println("Opção inválida! Digite um número de 1 a 6.");
                continue;
            }

            boolean reiniciarNivelAtual = true;
            boolean jogoTerminouParaTrocaClasse = false; // Flag para saber se o jogo terminou (vitoria/derrota sem retry)

            while (reiniciarNivelAtual) {
                Labirinto mapaPrincipal = new Labirinto(0, dificuldadeMapa, true);
                mapaPrincipal.gerar_Mapa(dificuldadeMapa);

                if (mapaPrincipal.getInicioI() < 0 ||
                        mapaPrincipal.getInicioJ() < 0 ||
                        mapaPrincipal.getInicioI() >= mapaPrincipal.getEstrutura().size() ||
                        mapaPrincipal.getInicioJ() >= mapaPrincipal.getEstrutura().get(0).size()) {
                    System.out.println("Posição inicial inválida no mapa principal! Encerrando o jogo.");
                    salvarProgresso(niveisCompletados);
                    return; // Erro crítico, sair do main
                }

                List<Tesouros> tesourosEncontrados = new ArrayList<>(); // Reiniciar tesouros para o novo nível
                Aventureiro jogador = new Aventureiro(nomeJogador, tesourosEncontrados, mapaPrincipal, mapaPrincipal.getInicioI(), mapaPrincipal.getInicioJ(), kitEscolhido);
                System.out.println("\nBem-vindo, " + nomeJogador + " (" + kitEscolhido.getNome() + ")!");
                System.out.println("Você está prestes a enfrentar o nível " +
                        (dificuldadeMapa == 1 ? "FÁCIL" : dificuldadeMapa == 2 ? "MÉDIO" : "DIFÍCIL"));
                System.out.println("Boa sorte em sua jornada!\n");
                esperar(2000);

                boolean emJogoEsteNivel = true;
                while (emJogoEsteNivel) {
                    // Verifica se chegou na sala do boss final (Vitória)
                    if (jogador.getLabirintoAtual() == mapaPrincipal &&
                            jogador.getPosI() == mapaPrincipal.getFimI() &&
                            jogador.getPosJ() == mapaPrincipal.getFimJ()) {
                        System.out.println("\n==== PARABÉNS, VOCÊ TERMINOU O LABIRINTO ====");
                        System.out.println("Você completou o nível " +
                                (dificuldadeMapa == 1 ? "FÁCIL" : dificuldadeMapa == 2 ? "MÉDIO" : "DIFÍCIL") + "!");
                        niveisCompletados[dificuldadeMapa - 1] = true;
                        salvarProgresso(niveisCompletados); // Salvar progresso imediatamente

                        emJogoEsteNivel = false;      // Sai do loop do nível atual
                        reiniciarNivelAtual = false;  // Não reinicia este nível, volta ao menu principal
                        jogoTerminouParaTrocaClasse = true; // Indica que o jogo terminou para perguntar sobre classe
                        esperar(3000);
                        // O break implícito pela condição do loop while(emJogoEsteNivel)
                    }

                    // Verifica se o jogador morreu (Derrota)
                    if (!jogador.estaVivo()) {
                        System.out.println("\n====================================");
                        System.out.println("|           GAME OVER         |");
                        System.out.println("====================================");
                        System.out.println("Você foi derrotado, " + nomeJogador + "...");

                        emJogoEsteNivel = false;
                        reiniciarNivelAtual = false;
                        jogoTerminouParaTrocaClasse = true;
                    }

                    if (!emJogoEsteNivel) { // Se venceu ou perdeu, sai do loop de input/movimento
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
                            System.out.print("Deseja realmente sair deste nível e voltar ao Menu Principal? (S/N): ");
                            char confirmacao = sc.nextLine().toUpperCase().charAt(0);
                            if (confirmacao == 'S') {
                                emJogoEsteNivel = false;
                                reiniciarNivelAtual = false; // Não reiniciar, voltar ao menu principal
                                jogoTerminouParaTrocaClasse = false; // Não foi vitória/derrota
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

            if (jogoTerminouParaTrocaClasse) {
                System.out.print("\nDeseja trocar de classe antes de selecionar um novo desafio? (S/N): ");
                char respostaTrocarClasse = sc.nextLine().toUpperCase().charAt(0);
                if (respostaTrocarClasse == 'S') {
                    kitEscolhido = selecionarClasse(sc, classes, kitEscolhido);
                }
                jogoTerminouParaTrocaClasse = false;
            }
        }

        System.out.println("Salvando progresso final...");
        salvarProgresso(niveisCompletados);
        sc.close();
        System.out.println("Obrigado por jogar Labirintos Misteriosos!");
    }

    public static KitClasse selecionarClasse(Scanner sc, KitClasse[] classes, KitClasse kitAtual) {
        System.out.println("\n--- SELEÇÃO DE CLASSE ---");
        if (kitAtual != null) {
            System.out.println("Sua classe atual é: " + kitAtual.getNome());
        }
        System.out.println("Classes disponíveis:");
        for (int i = 0; i < classes.length; i++) {
            System.out.print((i + 1) + " - " + classes[i].getNome());
            if (classes[i].getNome().equals("Guerreiro")) System.out.println(" (+15 Vida, +15 Ataque, +2 Dano Verdadeiro, +30% Armadura, -1 Velocidade)");
            else if (classes[i].getNome().equals("Mago")) System.out.println(" (+10 Vida, +5 Ataque, +20 Dano Verdadeiro, +15% Armadura, +2 Velocidade)");
            else if (classes[i].getNome().equals("Arqueiro")) System.out.println(" (+13 Vida, +10 Ataque, +10 Dano Verdadeiro, +20% Armadura, +7 Velocidade)");
            else System.out.println();
        }
        System.out.print("Escolha sua classe (1-" + classes.length + ")" + (kitAtual != null ? ", ou 0 para manter a atual (" + kitAtual.getNome() + ")" : "") + ": ");

        int escolhaClasseNum;
        while (true) {
            try {
                String linha = sc.nextLine();
                if (linha.trim().isEmpty() && kitAtual != null) {
                    System.out.println("Você manteve a classe " + kitAtual.getNome() + ".");
                    esperar(1000);
                    return kitAtual;
                }
                escolhaClasseNum = Integer.parseInt(linha);

                if (kitAtual != null && escolhaClasseNum == 0) {
                    System.out.println("Você manteve a classe " + kitAtual.getNome() + ".");
                    esperar(1000);
                    return kitAtual;
                }
                if (escolhaClasseNum >= 1 && escolhaClasseNum <= classes.length) {
                    break;
                }
                System.out.print("Escolha inválida! Digite um número entre " + (kitAtual != null ? "0 e " : "1 e ") + classes.length + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida! Digite um número entre " + (kitAtual != null ? "0 e " : "1 e ") + classes.length + ": ");
            }
        }
        KitClasse kitSelecionado = classes[escolhaClasseNum - 1];
        System.out.println("\nVocê selecionou a classe " + kitSelecionado.getNome() + "!");
        esperar(1500);
        return kitSelecionado;
    }

    private static void carregarProgresso(boolean[] niveisCompletados) {
        File arquivo = new File("progresso.dat");
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                boolean[] progressoSalvo = (boolean[]) ois.readObject();
                int lengthToCopy = Math.min(progressoSalvo.length, niveisCompletados.length);
                System.arraycopy(progressoSalvo, 0, niveisCompletados, 0, lengthToCopy);
                System.out.println("Progresso carregado com sucesso!");
            } catch (IOException | ClassNotFoundException e) {
            }
        }
    }

    private static void salvarProgresso(boolean[] niveisCompletados) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("progresso.dat"))) {
            oos.writeObject(niveisCompletados);
        } catch (IOException e) {
            System.err.println("Erro ao salvar progresso: " + e.getMessage());
        }
    }

    private static void esperar(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}