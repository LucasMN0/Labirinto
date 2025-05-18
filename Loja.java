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
        itensAVenda = new ArrayList<>();
        itensComunsAVenda = new ArrayList<>();

        // Itens equipáveis
        itensAVenda.add(new ItemEquipavel("Espada de Ferro", 0, 0, "Arma", 0, 0, 15, 0));
        itensAVenda.add(new ItemEquipavel("Armadura de Couro", 0, 0, "Armadura", 30, 0.15, 0, 0));
        itensAVenda.add(new ItemEquipavel("Anel do Poder", 0, 0, "Acessório", 20, 0.1, 10, 5));
        itensAVenda.add(new ItemEquipavel("Botas Velozes", 0, 0, "Calçado", 10, 0.05, 0, 0));

        // Itens comuns
        itensComunsAVenda.add(new ItemComum("Poção de Vida", 0, 0, "Consumível"));
        itensComunsAVenda.add(new ItemComum("Mapa do Tesouro", 0, 0, "Utilidade"));
        itensComunsAVenda.add(new ItemComum("Chave Mestra", 0, 0, "Chave"));
    }

    public void mostrarMenuLoja() {
        while (true) {
            System.out.println("\n=== LOJA ===");
            System.out.println("Moedas: " + calcularMoedasJogador());
            System.out.println("1 - Comprar Itens Equipáveis");
            System.out.println("2 - Comprar Itens Comuns");
            System.out.println("3 - Vender Itens");
            System.out.println("4 - Sair da Loja");
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

    private int calcularMoedasJogador() {
        // Simulação - você pode implementar um sistema de moedas real no jogador
        // Por enquanto, vamos usar o número de tesouros encontrados * 50 como moedas
        return jogador.getTesourosEncontrados().size() * 50;
    }

    private void mostrarItensEquipaveis() {
        System.out.println("\n=== ITENS EQUIPÁVEIS ===");
        System.out.println("Moedas disponíveis: " + calcularMoedasJogador());

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
        System.out.println("Moedas disponíveis: " + calcularMoedasJogador());

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
        // Preço baseado nos atributos do item
        return (item.getBonusVida() * 2) +
                (item.getBonusAtaque() * 3) +
                (int)(item.getBonusArmadura() * 200) +
                (item.getBonusVerdadeiro() * 4);
    }

    private int calcularPrecoItemComum(ItemComum item) {
        return item.getValor();
    }

    private void comprarItemEquipavel(int indice) {
        ItemEquipavel item = itensAVenda.get(indice);
        int preco = calcularPrecoItem(item);
        int moedas = calcularMoedasJogador();

        if (moedas >= preco) {
            System.out.println("\nVocê comprou: " + item.getNome() + " por " + preco + " moedas!");

            // Adiciona o item ao inventário do jogador
            jogador.equipar(item);

            // Remove moedas (implementação fictícia - você precisaria adicionar um sistema de moedas real)
            System.out.println("Moedas restantes: " + (moedas - preco));
        } else {
            System.out.println("\nMoedas insuficientes! Você precisa de mais " + (preco - moedas) + " moedas.");
        }
    }

    private void comprarItemComum(int indice) {
        ItemComum item = itensComunsAVenda.get(indice);
        int preco = calcularPrecoItemComum(item);
        int moedas = calcularMoedasJogador();

        if (moedas >= preco) {
            System.out.println("\nVocê comprou: " + item.getNome() + " por " + preco + " moedas!");

            // Adiciona o item aos tesouros encontrados
            jogador.getTesourosEncontrados().add(item.getNome());

            // Remove moedas (implementação fictícia)
            System.out.println("Moedas restantes: " + (moedas - preco));
        } else {
            System.out.println("\nMoedas insuficientes! Você precisa de mais " + (preco - moedas) + " moedas.");
        }
    }

    private void venderItens() {
        System.out.println("\n=== VENDER ITENS ===");

        if (jogador.getTesourosEncontrados().isEmpty()) {
            System.out.println("Você não tem itens para vender!");
            return;
        }

        System.out.println("Seus tesouros:");
        for (int i = 0; i < jogador.getTesourosEncontrados().size(); i++) {
            System.out.println((i+1) + " - " + jogador.getTesourosEncontrados().get(i) +
                    " (Valor: " + (new Random().nextInt(30) + 10 + " moedas)"));
        }

        System.out.print("\nDigite o número do item para vender ou 0 para voltar: ");
        int escolha = sc.nextInt();
        sc.nextLine(); // Limpar buffer

        if (escolha > 0 && escolha <= jogador.getTesourosEncontrados().size()) {
            String itemVendido = jogador.getTesourosEncontrados().remove(escolha - 1);
            int valorRecebido = new Random().nextInt(30) + 10;
            System.out.println("\nVocê vendeu " + itemVendido + " por " + valorRecebido + " moedas!");
            // Aqui você adicionaria as moedas ao jogador em uma implementação real
        }
    }
}