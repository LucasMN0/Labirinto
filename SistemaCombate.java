package LABIRINTO;

import java.util.Random;
import java.util.Scanner;
import java.util.Map;

public class SistemaCombate {
    private static final Scanner sc = new Scanner(System.in);
    private static final Random rand = new Random();

    public static void encontrarPerigo(Aventureiro jogador) {
        if (rand.nextInt(100) < 30) { // 30% de chance de encontrar perigo
            Perigo perigo = Perigo.criarPerigoAleatorio(rand);

            if (perigo instanceof Perigo.Armadilha) {
                encontrarArmadilha(jogador, (Perigo.Armadilha) perigo);
            } else {
                encontrarInimigo(jogador, (Perigo.Inimigo) perigo);
            }
        }
    }

    public static void encontrarArmadilha(Aventureiro jogador, Perigo.Armadilha armadilha) {
        System.out.println("\n=== PERIGO ===");
        System.out.println("Você caiu em uma " + armadilha.getNome());
        esperar(3500);
        System.out.println("\nDescrição: " + armadilha.getDescricao());
        esperar(3500);

        if (jogador.getVelocidade() > armadilha.getVelocidade()) {
            System.out.println("\nVocê foi rápido o suficiente para evitar a armadilha!");
        } else {
            int dano = armadilha.getDano();
            jogador.receberDano(dano);
            System.out.println("\nVocê não conseguiu evitar a armadilha e sofreu " + dano + " de dano!");
        }

        jogador.getMonstruario().registrarArmadilha(armadilha);
        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
    }

    public static void encontrarInimigo(Aventureiro jogador, Perigo.Inimigo inimigo) {
        System.out.println("\n=== COMBATE ===");
        System.out.println("Você encontrou um " + inimigo.getNome() + "!");
        esperar(3500);
        System.out.println("\nDescrição: " + inimigo.getDescricao());
        esperar(3500);

        boolean jogadorPodeFugir = jogador.getVelocidade() > inimigo.getVelocidade();

        if (jogadorPodeFugir) {
            System.out.println("\nVocê é mais rápido e pode tentar fugir!");
            System.out.print("Deseja fugir? (s/n): ");
            String escolha = sc.nextLine().toLowerCase();

            if (escolha.equals("s")) {
                System.out.println("Você fugiu do combate!");
                return;
            }
        }

        combatePorTurnos(jogador, inimigo);
        if (inimigo.getVida() <= 0) {
            jogador.getMonstruario().registrarInimigo(inimigo);
        }
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

            System.out.println("\n=== STATUS ===");
            System.out.println(jogador.getNome() + ": " + jogador.getVida() + "/" + jogador.getVidaMaximaTotal());
            System.out.println(inimigo.getNome() + ": " + Math.max(0, inimigo.getVida()) + "/" + getVidaMaximaInimigo(inimigo));

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
        }
    }

    private static void turnoJogador(Aventureiro jogador, Perigo.Inimigo inimigo) {
        boolean turnoConcluido = false;
        boolean consumivelUsado = false;

        while (!turnoConcluido) {
            System.out.println("\n=== SEU TURNO ===");
            System.out.println("1 - Atacar");

            if (!consumivelUsado && !jogador.getConsumiveis().isEmpty()) {
                System.out.println("2 - Usar consumível");
            }

            System.out.println("3 - Mostrar Status");
            System.out.println("4 - Sair do jogo");
            System.out.print("Escolha uma ação: ");

            String escolha = sc.nextLine();

            switch (escolha) {
                case "1":
                    atacar(jogador, inimigo);
                    turnoConcluido = true;
                    break;

                case "2":
                    if (!consumivelUsado && !jogador.getConsumiveis().isEmpty()) {
                        if (usarConsumivelCombate(jogador)) {
                            consumivelUsado = true;
                            System.out.println("Você ainda pode atacar neste turno!");
                        }
                    }
                    break;

                case "3":
                    jogador.mostrarStatus();
                    break;

                case "4":
                    System.out.println("Saindo do jogo...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Ação inválida! Tente novamente.");
            }

            // Força o ataque se usou consumível e não fez outra ação
            if (consumivelUsado && !turnoConcluido) {
                System.out.println("Pressione ENTER para atacar...");
                sc.nextLine();
                atacar(jogador, inimigo);
                turnoConcluido = true;
            }
        }
    }

    private static void atacar(Aventureiro jogador, Perigo.Inimigo inimigo) {
        int danoBase = (int)(jogador.getDanoAtaqueTotal() * (1 - (inimigo.getArmadura() / 100.0)));
        int danoVerdadeiro = jogador.getDanoVerdadeiroTotal();
        int danoTotal = danoBase + danoVerdadeiro;

        inimigo.setVida(inimigo.getVida() - danoTotal);

        System.out.println("\nVocê atacou o " + inimigo.getNome() + "!");
        System.out.println("Dano base: " + danoBase);
        System.out.println("Dano verdadeiro: " + danoVerdadeiro);
        System.out.println("Dano total: " + danoTotal);
    }

    private static void turnoInimigo(Aventureiro jogador, Perigo.Inimigo inimigo) {
        System.out.println("\n=== TURNO DO " + inimigo.getNome().toUpperCase() + " ===");

        // Inimigo sempre ataca
        int danoBase = (int)(inimigo.getDano() * (1 - jogador.getArmaduraTotal()));
        int danoVerdadeiro = inimigo.getDanoVerdadeiro();
        int danoTotal = danoBase + danoVerdadeiro;

        jogador.receberDano(danoTotal);

        System.out.println(inimigo.getNome() + " atacou você!");
        System.out.println("Dano base: " + danoBase);
        System.out.println("Dano verdadeiro: " + danoVerdadeiro);
        System.out.println("Dano total: " + danoTotal);
    }

    private static int getVidaMaximaInimigo(Perigo.Inimigo inimigo) {
        Map<String, Integer> vidaMaximaInimigos = Map.of(
                "Pato Armado", 5,
                "sombra_errante", 20,
                "anjo sem asas", 20,
                "ambição de outrora", 17,
                "litch", 20,
                "anjo", 30,
                "diabo", 30
        );
        return vidaMaximaInimigos.getOrDefault(inimigo.getNome(), 30);
    }

    private static boolean usarConsumivelCombate(Aventureiro jogador) {
        if (jogador.getVida() >= jogador.getVidaMaximaTotal()) {
            System.out.println("Sua vida já está cheia! Não é necessário usar consumíveis.");
            return false;
        }

        jogador.listarConsumiveis();
        System.out.print("Escolha o consumível para usar (0 para cancelar): ");
        try {
            int escolha = Integer.parseInt(sc.nextLine()) - 1;
            if (escolha >= 0 && escolha < jogador.getConsumiveis().size()) {
                return jogador.usarConsumivel(escolha);
            } else if (escolha == -1) {
                System.out.println("Uso de consumível cancelado.");
                return false;
            } else {
                System.out.println("Opção inválida!");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido!");
            return false;
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