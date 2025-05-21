package LABIRINTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Loja {
    private List<ItemEquipavel> itensAVenda;
    private List<ItemComum> itensComunsAVenda;
    private Aventureiro jogador;
    private Scanner sc;

    public Loja(Aventureiro jogador) {
        this.jogador = jogador;
        this.sc = new Scanner(System.in);
        inicializarItens();
    }

    private void inicializarItens() {
        itensAVenda = Tesouros.getItensEquipaveisAleatorios(4); // Pega 4 aleatórios
        itensComunsAVenda = Tesouros.getItensComunsAleatorios(3); // Pega 3 aleatórios
    }

    public void mostrarMenuLoja() {
        while (true) {
            System.out.println("\n=== LOJA ===");
            System.out.println("Moedas: " + jogador.getMoedas());
            System.out.println("1 - Comprar Itens Equipáveis");
            System.out.println("2 - Comprar Itens Comuns");
            System.out.println("3 - Vender Itens");
            System.out.println("4 - Sair da Loja");
            if (!jogador.podeComprarNaLoja()) {
                System.out.println("\nAVISO: Você só pode comprar um item por visita à loja!");
                System.out.println("Saia e entre novamente de um labirinto para comprar mais.");
            }

            System.out.print("Escolha uma opção: ");

            String opcao = sc.nextLine();

            switch(opcao) {
                case "1":
                    mostrarItensEquipaveis();
                    break;
                case "2":
                    mostrarItensComuns();
                    break;
                case "3":
                    venderItens();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void mostrarItensEquipaveis() {
        System.out.println("\n=== ITENS EQUIPÁVEIS ===");
        System.out.println("Moedas disponíveis: " + jogador.getMoedas());

        for (int i = 0; i < itensAVenda.size(); i++) {
            ItemEquipavel item = itensAVenda.get(i);
            System.out.println((i+1) + " - " + item.getNome() +
                    " (Preço: " + calcularPrecoItem(item) + " moedas)");
            System.out.println("   Vida: +" + item.getBonusVida() +
                    " | Ataque: +" + item.getBonusAtaque() +
                    " | Armadura: +" + (int)(item.getBonusArmadura() * 100) + "%" +
                    " | Dano Verdadeiro: +" + item.getBonusVerdadeiro());
        }

        System.out.print("\nDigite o número do item para comprar ou 0 para voltar: ");
        int escolha = sc.nextInt();
        sc.nextLine(); // Limpar buffer

        if (escolha > 0 && escolha <= itensAVenda.size()) {
            comprarItemEquipavel(escolha - 1);
        }
    }

    private void mostrarItensComuns() {
        System.out.println("\n=== ITENS COMUNS ===");
        System.out.println("Moedas disponíveis: " + jogador.getMoedas());

        for (int i = 0; i < itensComunsAVenda.size(); i++) {
            ItemComum item = itensComunsAVenda.get(i);
            System.out.println((i+1) + " - " + item.getNome() +
                    " (Preço: " + calcularPrecoItemComum(item) + " moedas)");
        }

        System.out.print("\nDigite o número do item para comprar ou 0 para voltar: ");
        int escolha = sc.nextInt();
        sc.nextLine(); // Limpar buffer

        if (escolha > 0 && escolha <= itensComunsAVenda.size()) {
            comprarItemComum(escolha - 1);
        }
    }

    private int calcularPrecoItem(ItemEquipavel item) {
        return item.getValor();
    }

    private int calcularPrecoItemComum(ItemComum item) {
        return item.getValor();
    }

    private void comprarItemEquipavel(int indice) {
        if (!jogador.podeComprarNaLoja()) {
            System.out.println("\nVocê já comprou um item nesta visita à loja!");
            System.out.println("Saia e complete um labirinto para comprar novamente.");
            return;
        }

        ItemEquipavel item = itensAVenda.get(indice);
        int preco = calcularPrecoItem(item);

        if (jogador.removerMoedas(preco)) {
            System.out.println("\nVocê comprou: " + item.getNome() + " por " + preco + " moedas!");
            jogador.equipar(item);
            System.out.println("Moedas restantes: " + jogador.getMoedas());
            jogador.setPodeComprarNaLoja(false); // Impede novas compras
        } else {
            System.out.println("\nMoedas insuficientes! Você precisa de mais " + (preco - jogador.getMoedas()) + " moedas.");
        }
    }

    private void comprarItemComum(int indice) {
        if (!jogador.podeComprarNaLoja()) {
            System.out.println("\nVocê já comprou um item nesta visita à loja!");
            System.out.println("Saia e complete um labirinto para comprar novamente.");
            return;
        }

        ItemComum item = itensComunsAVenda.get(indice);
        int preco = calcularPrecoItemComum(item);

        if (jogador.removerMoedas(preco)) {
            System.out.println("\nVocê comprou: " + item.getNome() + " por " + preco + " moedas!");
            jogador.getTesourosEncontrados().add(item);
            System.out.println("Moedas restantes: " + jogador.getMoedas());
            jogador.setPodeComprarNaLoja(false); // Impede novas compras
        } else {
            System.out.println("\nMoedas insuficientes! Você precisa de mais " + (preco - jogador.getMoedas()) + " moedas.");
        }
    }

    public void venderItens() {
        List<Tesouros> tesouros = jogador.getTesourosEncontrados();

        if (tesouros.isEmpty()) {
            System.out.println("Você não tem itens para vender!");
            return;
        }

        while (true) {
            System.out.println("\n=== VENDER ITENS ===");
            System.out.println("Seus tesouros:");
            int i = 1;
            for (Tesouros t : tesouros) {
                System.out.println(i + " - " + t.getNome() + " (Valor de venda: " + t.getValorVenda() + " moedas)");
                i++;
            }

            System.out.print("\nDigite o número do item para vender ou 0 para voltar: ");
            int escolha = sc.nextInt();
            sc.nextLine(); // Limpar buffer

            if (escolha == 0) {
                break;
            }

            if (escolha > 0 && escolha <= tesouros.size()) {
                Tesouros item = tesouros.get(escolha - 1);
                int valorVenda = item.getValorVenda();

                jogador.adicionarMoedas(valorVenda);
                tesouros.remove(escolha - 1);

                System.out.println("\nVocê vendeu " + item.getNome() + " por " + valorVenda + " moedas!");
                System.out.println("Moedas totais: " + jogador.getMoedas());
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }
}