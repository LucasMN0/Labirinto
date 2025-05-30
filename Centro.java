package LABIRINTO;

import java.io.*;
import java.util.*;

public class Centro {
    private static final String SAVE_DIR = "saves/";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final long serialVersionUID = 1L;
        new File(SAVE_DIR).mkdirs();

        System.out.println("============================================");
        System.out.println("|           Labirintos Misteriósos         |");
        System.out.println("============================================");
        System.out.println("Um jogo de aventura e perigos infinitos...");
        System.out.println("Prepare-se para enfrentar criaturas lendárias!");
        esperar(2000);

        File[] saveFiles = new File(SAVE_DIR).listFiles((dir, name) -> name.endsWith(".dat"));

        if (saveFiles != null && saveFiles.length > 0) {
            System.out.println("\nSaves disponíveis:");
            for (int i = 0; i < saveFiles.length; i++) {
                String saveName = saveFiles[i].getName().replace(".dat", "");
                System.out.println((i+1) + " - " + saveName);
            }
            System.out.println((saveFiles.length+1) + " - Criar novo save");

            System.out.print("\nEscolha uma opção: ");
            int escolha = sc.nextInt();
            sc.nextLine(); // Limpar buffer

            if (escolha > 0 && escolha <= saveFiles.length) {
                // Carregar save existente
                carregarJogo(saveFiles[escolha-1].getName().replace(".dat", ""), sc);
                return;
            }
            // Se escolheu criar novo save, continuar normalmente
        }

        // Se não há saves ou escolheu criar novo
        iniciarNovoJogo(sc);
    }

    private static void iniciarNovoJogo(Scanner sc) {
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

        try {
            jogar(nomeJogador, sc);
        } catch (Exception e) {
            System.out.println("Erro ao iniciar novo jogo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void carregarJogo(String saveName, Scanner sc) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_DIR + saveName + ".dat"))) {
            SaveData saveData = (SaveData) ois.readObject();

            System.out.println("\nBem-vindo de volta, " + saveData.getNomeJogador() + "!");

            // Se já tinha classe salva, usa ela
            if (saveData.getClasseAtual() != null) {
                jogar(saveData.getNomeJogador(), sc, saveData, saveData.getClasseAtual());
            } else {
                jogar(saveData.getNomeJogador(), sc, saveData);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar o save. Iniciando novo jogo...");
            e.printStackTrace();
            iniciarNovoJogo(sc);
        }
    }

    private static void jogar(String nomeJogador, Scanner sc) {
        jogar(nomeJogador, sc, null, null);
    }

    private static void jogar(String nomeJogador, Scanner sc, SaveData saveData) {
        jogar(nomeJogador, sc, saveData, null);
    }

    private static void jogar(String nomeJogador, Scanner sc, SaveData saveData, KitClasse classeSalva) {
        Monstruario monstruario = saveData != null ? saveData.getMonstruario() : Monstruario.getInstance();
        boolean[] niveisCompletados = saveData != null ? saveData.getNiveisCompletados() : new boolean[3];

        KitClasse[] classes = {
                new KitClasse("Guerreiro", 15, 15, 2, 0.3, -1),
                new KitClasse("Mago", 10, 5, 20, 0.15, 2),
                new KitClasse("Arqueiro", 13, 10, 10, 0.2, 7)
        };

        // Seleção de classe inicial
        KitClasse kitEscolhido = classeSalva != null ? classeSalva : selecionarClasse(sc, classes, null);

        carregarProgresso(niveisCompletados);

        boolean jogando = true;
        while (jogando) {
            System.out.println("\n====================================");
            System.out.println("|           MENU PRINCIPAL         |");
            System.out.println("====================================");
            System.out.println("1 - Jogar no nível Fácil" + (niveisCompletados[0] ? " (COMPLETADO)" : ""));
            System.out.println("2 - Jogar no nível Médio" + (niveisCompletados[1] ? " (COMPLETADO)" : ""));
            System.out.println("3 - Jogar no nível Difícil" + (niveisCompletados[2] ? " (COMPLETADO)" : ""));
            System.out.println("4 - Ver Tutorial");
            System.out.println("5 - Gerenciar Saves");
            System.out.println("6 - Sair do jogo");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número de 1 a 7.");
                continue;
            }

            if(opcao == 4){
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

            if(opcao == 5){
                gerenciarSaves(sc, nomeJogador, niveisCompletados, monstruario, kitEscolhido);
                continue;
            }

            if (opcao == 6) {
                System.out.println("Até a próxima, " + nomeJogador + "!");
                salvarJogo(nomeJogador, niveisCompletados, monstruario, kitEscolhido);
                jogando = false;
                System.exit(0);
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
            } else {
                System.out.println("Opção inválida! Digite um número de 1 a 7.");
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
                        ControlarAudio.getMusica().tocarWav(2);
                        System.out.println("\n====================================");
                        System.out.println("|           GAME OVER         |");
                        System.out.println("====================================");
                        System.out.println("Você foi derrotado, " + nomeJogador + "...");
                        esperar(6000);
                        ControlarAudio.getMusica().pararTudo();
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
                System.out.println("Erro ao carregar progresso, iniciando novo jogo...");
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

    private static void salvarJogo(String nomeJogador, boolean[] niveisCompletados,
                                   Monstruario monstruario, KitClasse classeAtual) {
        try {
            SaveData saveData = new SaveData(nomeJogador, niveisCompletados, monstruario, classeAtual);

            // Garante que o diretório existe
            new File(SAVE_DIR).mkdirs();

            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(SAVE_DIR + nomeJogador + ".dat"))) {
                oos.writeObject(saveData);
                System.out.println("Jogo salvo com sucesso!");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void salvarJogo(String nomeJogador, boolean[] niveisCompletados, Monstruario monstruario) {
        salvarJogo(nomeJogador, niveisCompletados, monstruario, null);
    }

    private static void gerenciarSaves(Scanner sc, String nomeJogadorAtual, boolean[] niveisCompletadosAtual,
                                       Monstruario monstruarioAtual, KitClasse classeAtual) {
        System.out.println("\n=== GERENCIAR SAVES ===");

        File[] saveFiles = new File(SAVE_DIR).listFiles((dir, name) -> name.endsWith(".dat"));

        if (saveFiles == null || saveFiles.length == 0) {
            System.out.println("Nenhum save encontrado.");
            return;
        }

        for (int i = 0; i < saveFiles.length; i++) {
            String saveName = saveFiles[i].getName().replace(".dat", "");
            System.out.println((i+1) + " - " + saveName);
        }
        System.out.println((saveFiles.length+1) + " - Criar novo save");

        System.out.print("\nEscolha uma opção: ");
        int escolha;
        try {
            escolha = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida!");
            return;
        }

        if (escolha == saveFiles.length + 1) {
            // Criar novo save
            return;
        }

        if (escolha > 0 && escolha <= saveFiles.length) {
            try {
                // Salvar o jogo atual antes de carregar outro
                if (nomeJogadorAtual != null && !nomeJogadorAtual.isEmpty()) {
                    salvarJogo(nomeJogadorAtual, niveisCompletadosAtual, monstruarioAtual, classeAtual);
                }

                String saveName = saveFiles[escolha-1].getName().replace(".dat", "");
                carregarJogo(saveName, sc);
            } catch (Exception e) {
                System.out.println("Erro ao trocar de save: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void esperar(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("O atraso foi interrompido.");
        }
    }
}

class SaveData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nomeJogador;
    private boolean[] niveisCompletados;
    private Monstruario monstruario;
    private KitClasse classeAtual;

    public SaveData(String nomeJogador, boolean[] niveisCompletados, Monstruario monstruario, KitClasse classeAtual) {
        this.nomeJogador = nomeJogador;
        this.niveisCompletados = niveisCompletados;
        this.monstruario = monstruario;
        this.classeAtual = classeAtual;
    }

    // Getters
    public String getNomeJogador() { return nomeJogador; }
    public boolean[] getNiveisCompletados() { return niveisCompletados; }
    public Monstruario getMonstruario() { return monstruario; }
    public KitClasse getClasseAtual() { return classeAtual; }
}