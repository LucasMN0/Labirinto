package LABIRINTO;

import java.util.Random;
import java.util.Scanner;
import java.util.Map;
import LABIRINTO.Inimigo;
import LABIRINTO.Armadilha;

public class SistemaCombate {
    private static final Scanner sc = new Scanner(System.in);
    private static final Random rand = new Random();

    public static void encontrarPerigo(Aventureiro jogador) {
        if (rand.nextInt(100) < 30) {
            Perigo perigo = Perigo.criarPerigoAleatorio(rand);

            if (perigo instanceof Armadilha) {
                encontrarArmadilha(jogador, (Armadilha) perigo);
            } else {
                encontrarInimigo(jogador, (Inimigo) perigo);
            }
        }
    }

    public static void encontrarArmadilha(Aventureiro jogador, Armadilha armadilha) {
        System.out.println("\n=== PERIGO ===\n");
        System.out.println("Você caiu em uma " + armadilha.getNome());
        esperar(2000);
        System.out.println("\nDescrição: " + armadilha.getDescricao());
        esperar(2000);

        jogador.getMonstruario().registrarArmadilha(armadilha); // Registra a armadilha no monstrurio do aventureiro

        if (jogador.getVelocidadeTotal() > armadilha.getVelocidade()) {
            System.out.println("\nVocê foi rápido o suficiente para evitar a armadilha!");
            esperar(2000);
        } else {
            int danoSofrido = armadilha.getDano();
            jogador.setVida(jogador.getVida() - danoSofrido);
            System.out.println("\nVocê sofreu " + danoSofrido + " de dano da armadilha!");
            esperar(2000);
            System.out.println("Sua vida atual: " + jogador.getVida() + "/" + jogador.getVidaMaximaTotal());
            if (jogador.getVida() <= 0) {
                System.out.println("Você foi derrotado pela armadilha...");
                System.out.println("Fim de jogo!");
                System.exit(0);
            }
        }
    }

    public static void encontrarInimigo(Aventureiro jogador, Inimigo inimigo) {
        System.out.println("\n=== COMBATE ===\n");
        System.out.println("Você encontrou um " + inimigo.getNome() + "!");
        esperar(2000);
        System.out.println("\nDescrição: " + inimigo.getDescricao());
        esperar(2000);

        jogador.getMonstruario().registrarInimigo(inimigo);

        int vidaInimigo = inimigo.getVida();
        int vidaMaximaInimigo = getVidaMaximaInimigo(inimigo); // Vida máxima original
        String nomeInimigo = inimigo.getNome();
        int danoInimigo = inimigo.getDano();
        double armaduraInimigo = inimigo.getArmadura();
        int velocidadeInimigo = inimigo.getVelocidade();
        int danoVerdadeiroInimigo = inimigo.getDanoVerdadeiro();

        System.out.println("\n--- Detalhes do Inimigo ---");
        System.out.println("Nome: " + nomeInimigo);
        System.out.println("Vida: " + vidaInimigo + "/" + vidaMaximaInimigo); // Exibe vida atual/máxima
        System.out.println("Dano: " + danoInimigo);
        System.out.printf("Armadura: %.2f%%\n", armaduraInimigo * 100);
        System.out.println("Velocidade: " + velocidadeInimigo);
        System.out.println("Dano Verdadeiro: " + danoVerdadeiroInimigo);
        esperar(3000);

        while (jogador.getVida() > 0 && vidaInimigo > 0) {
            System.out.println("\n--- Sua Vez (" + jogador.getNome() + ") ---");
            System.out.println("Sua Vida: " + jogador.getVida() + "/" + jogador.getVidaMaximaTotal());
            System.out.println("Vida do " + nomeInimigo + ": " + Math.max(0, vidaInimigo) + "/" + vidaMaximaInimigo);
            System.out.println("1 - Atacar");
            System.out.println("2 - Usar Consumível");
            System.out.print("Escolha sua ação: ");

            int escolha = 0;
            boolean entradaValida = false;

            while (!entradaValida) {
                try {
                    String input = sc.nextLine();
                    escolha = Integer.parseInt(input);

                    if (escolha == 1 || escolha == 2) {
                        entradaValida = true;
                    } else {
                        System.out.print("Opção inválida! Digite 1 ou 2: ");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Entrada inválida! Digite 1 ou 2: ");
                }
            }

            switch (escolha) {
                case 1:
                    // Calcula o dano do jogador
                    int danoBaseJogador = jogador.getDanoAtaqueTotal();
                    int danoVerdadeiroJogador = jogador.getDanoVerdadeiroTotal();
                    double reducaoDanoInimigo = armaduraInimigo; // Armadura do inimigo

                    int danoCausado = (int) (danoBaseJogador * (1 - reducaoDanoInimigo));
                    danoCausado += danoVerdadeiroJogador; // Dano verdadeiro ignora armadura

                    vidaInimigo -= danoCausado;
                    System.out.println("\nVocê atacou o " + nomeInimigo + " e causou " + danoCausado + " de dano!");
                    esperar(2000);
                    break;
                case 2:
                    if (!usarConsumivelCombate(jogador)) {
                        esperar(1000);
                        continue; // Permite que o jogador tente novamente se não usou o consumível
                    }
                    break;
                default:
                    System.out.println("Ação inválida! Você perdeu sua vez.");
                    esperar(1000);
            }

            if (vidaInimigo <= 0) {
                System.out.println("\nVocê derrotou o " + nomeInimigo + "!");
                esperar(2000);
                jogador.adicionarMoedas(10 + rand.nextInt(20)); // Recompensa em moedas
                System.out.println("Você ganhou moedas! Moedas atuais: " + jogador.getMoedas());
                esperar(2000);
                return;
            }

            // Turno do inimigo
            System.out.println("\n--- Turno do Inimigo (" + nomeInimigo + ") ---");
            esperar(2000);

            // Calcula o dano do inimigo ao jogador
            int danoBaseInimigo = danoInimigo;
            int danoVerdadeiroInimigoAtual = danoVerdadeiroInimigo;
            double reducaoDanoJogador = jogador.getArmaduraTotal(); // Armadura do jogador

            int danoRecebido = (int) (danoBaseInimigo * (1 - reducaoDanoJogador));
            danoRecebido += danoVerdadeiroInimigoAtual; // Dano verdadeiro ignora armadura

            jogador.setVida(jogador.getVida() - danoRecebido);
            System.out.println("O " + nomeInimigo + " atacou você e causou " + danoRecebido + " de dano!");
            esperar(2000);
            System.out.println("Sua vida atual: " + jogador.getVida() + "/" + jogador.getVidaMaximaTotal());

            if (jogador.getVida() <= 0) {
                System.out.println("\nVocê foi derrotado pelo " + nomeInimigo + "...");
                System.out.println("Fim de jogo!");
                System.exit(0);
            }
        }
    }


    // Metodo auxiliar para obter a vida máxima de inimigos específicos (para exibir ao jogador)
    private static int getVidaMaximaInimigo(Inimigo inimigo) {
        // Retorna a vida máxima do inimigo (usando o valor original do construtor)
        return inimigo.getVida();
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
            System.out.println("O atraso foi interrompido.");
        }
    }
}