package LABIRINTO;

import java.util.Random;
import java.util.Scanner;

public class SistemaCombate {
    private static final Scanner sc = new Scanner(System.in);
    private static final Random rand = new Random();

    public static void encontrarPerigo(Aventureiro jogador) {
        if (rand.nextInt(100) < 30) {
            if (rand.nextBoolean()) {
                Perigo.Armadilha armadilha = Perigo.Armadilha.Armadilhas.get(
                        rand.nextInt(Perigo.Armadilha.Armadilhas.size())
                );
                encontrarArmadilha(jogador, armadilha);
            } else {
                Perigo.Inimigo inimigo = Perigo.Inimigo.Inimigos.get(
                        rand.nextInt(Perigo.Inimigo.Inimigos.size())
                );
                encontrarInimigo(jogador, inimigo);
            }
        }
    }

    public static void encontrarArmadilha(Aventureiro jogador, Perigo.Armadilha armadilha) {
        armadilha = Perigo.Armadilha.Armadilhas.get(
                rand.nextInt(Perigo.Armadilha.Armadilhas.size())
        );

        System.out.println("\n=== PERIGO ===");
        System.out.println("Você caiu em uma " + armadilha.getNome());
        System.out.println("\nHistória: " + armadilha.getHistoria());
        System.out.println("\nDescrição: " + armadilha.getDescricao());

        // Verifica se o jogador consegue evitar a armadilha
        if (jogador.getVelocidade() > armadilha.getVelocidade()) {
            System.out.println("\nVocê foi rápido o suficiente para evitar a armadilha!");
        } else {
            int dano = armadilha.getDano();
            jogador.receberDano(dano);
            System.out.println("\nVocê não conseguiu evitar a armadilha e sofreu " + dano + " de dano!");
        }

        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
    }

    public static void encontrarInimigo(Aventureiro jogador, Perigo.Inimigo inimigo) {
        inimigo = Perigo.Inimigo.Inimigos.get(
                new Random().nextInt(Perigo.Inimigo.Inimigos.size())
        );

        System.out.println("\n=== COMBATE ===");
        System.out.println("Você encontrou um " + inimigo.getNome() + "!");
        System.out.println("\nHistória: " + inimigo.getHistoria());
        System.out.println("\nDescrição: " + inimigo.getDescricao());

        // Verifica se o jogador pode fugir
        boolean jogadorComeca = jogador.getVelocidade() > inimigo.getVelocidade();

        if (jogadorComeca) {
            System.out.println("\nVocê é mais rápido e pode tentar fugir!");
            System.out.print("Deseja fugir? (s/n): ");
            String escolha = sc.nextLine().toLowerCase();

            if (escolha.equals("s")) {
                System.out.println("Você fugiu do combate!");
                return;
            }
        }

        // Inicia o combate por turnos
        combatePorTurnos(jogador, inimigo);
    }

    private static void combatePorTurnos(Aventureiro jogador, Perigo.Inimigo inimigo) {
        boolean jogadorComeca = jogador.getVelocidade() > inimigo.getVelocidade();
        boolean turnoJogador = jogadorComeca;

        while (jogador.estaVivo() && inimigo.getVida() > 0) {
            if (turnoJogador) {
                turnoJogador(jogador, inimigo);
            } else {
                turnoInimigo(jogador, inimigo);
            }

            turnoJogador = !turnoJogador;

            // Mostra status após cada turno
            System.out.println("\n=== STATUS ===");
            System.out.println(jogador.getNome() + ": " + jogador.getVida() + "/" + jogador.getVidaMaximaTotal());
            System.out.println(inimigo.getNome() + ": " + inimigo.getVida() + "/" + inimigo.getVida());

            if (jogador.estaVivo() && inimigo.getVida() > 0) {
                System.out.println("\nPressione ENTER para continuar o combate...");
                sc.nextLine();
            }
        }

        if (!jogador.estaVivo()) {
            System.out.println("\nVocê foi derrotado!");
            System.exit(0);
        } else {
            System.out.println("\nVocê derrotou o " + inimigo.getNome() + "!");
            // Podemos adicionar recompensas aqui
        }
    }

    private static void turnoJogador(Aventureiro jogador, Perigo.Inimigo inimigo) {
        System.out.println("\n=== SEU TURNO ===");
        System.out.println("1 - Atacar");
        System.out.println("2 - Usar consumível");
        System.out.println("3 - Mostrar menu");
        System.out.println("4 - Sair do jogo");
        System.out.print("Escolha uma ação: ");

        String escolha = sc.nextLine();

        switch (escolha) {
            case "1":
                atacar(jogador, inimigo);
                break;
            case "2":
                usarConsumivel(jogador);
                break;
            case "3":
                jogador.mostrarMenu();
                break;
            case "4":
                System.out.println("Saindo do jogo...");
                System.exit(0);
                break;
            default:
                System.out.println("Ação inválida! Você perdeu o turno.");
        }
    }

    private static void atacar(Aventureiro jogador, Perigo.Inimigo inimigo) {
        // Calcula dano base (reduzido pela armadura do inimigo)
        int danoBase = (int)(jogador.getDanoAtaqueTotal() * (1 - (inimigo.getArmadura() / 100.0)));
        int danoVerdadeiro = jogador.getDanoVerdadeiroTotal();
        int danoTotal = danoBase + danoVerdadeiro;

        inimigo.setVida(inimigo.getVida() - danoTotal);

        System.out.println("\nVocê atacou o " + inimigo.getNome() + "!");
        System.out.println("Dano base: " + danoBase + " (reduzido por armadura)");
        System.out.println("Dano verdadeiro: " + danoVerdadeiro);
        System.out.println("Dano total: " + danoTotal);
    }

    private static void usarConsumivel(Aventureiro jogador) {
        // Implementar lógica para usar consumíveis
        System.out.println("\nVocê não tem consumíveis no momento!");
    }

    private static void turnoInimigo(Aventureiro jogador, Perigo.Inimigo inimigo) {
        System.out.println("\n=== TURNO DO " + inimigo.getNome().toUpperCase() + " ===");

        // Inimigo sempre ataca
        int danoBase = (int)(inimigo.getDano() * (1 - jogador.getArmaduraTotal()));
        int danoVerdadeiro = inimigo.getDanoVerdadeiro();
        int danoTotal = danoBase + danoVerdadeiro;

        jogador.receberDano(danoTotal);

        System.out.println(inimigo.getNome() + " atacou você!");
        System.out.println("Dano base: " + danoBase + " (reduzido por armadura)");
        System.out.println("Dano verdadeiro: " + danoVerdadeiro);
        System.out.println("Dano total: " + danoTotal);
    }
}