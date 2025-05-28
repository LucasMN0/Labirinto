package LABIRINTO;

import java.util.*;
import java.util.Random;

public abstract class Tesouros {
    private String nome;
    private int linha;
    private int coluna;
    private String tipo;
    protected int valor;
    static Random rd = new Random();

    public Tesouros(String nome, int linha, int coluna, String tipo, int valor) {
        this.nome = nome;
        this.tipo = tipo;
        this.linha = linha;
        this.coluna = coluna;
        this.valor = valor;
    }

    private static final List<ItemEquipavel> itensEquipaveis = Arrays.asList(
            new ItemEquipavel("Espada de Onix", 0, 0, "Arma", 0, 0.00, 30, 1, 0, rd.nextInt(51) + 5),
            new ItemEquipavel("Machado Quebrado", 0, 0, "Arma", 0, 0.0, 5, 0, 0, rd.nextInt(6) + 1),
            new ItemEquipavel("Cajado de Mago", 0, 0, "Arma", 0, 0.0, 10, 30, -1, rd.nextInt(41) + 5),
            new ItemEquipavel("Adaga Sombria", 0, 0, "Arma", 0, 0.1, 15, 0, 2, rd.nextInt(31) + 10),
            new ItemEquipavel("Martelo do Thor", 0, 0, "Arma", 0, 0.0, 25, 5, -2, rd.nextInt(61) + 15),
            new ItemEquipavel("Arco Élfico", 0, 0, "Arma", 0, 0.2, 18, 10, 1, rd.nextInt(41) + 10),
            new ItemEquipavel("Moonlight GreatSword", 0, 0, "Arma", 0, 0.0, 10, 45, -2, rd.nextInt(101) + 10),

            new ItemEquipavel("Armadura de Ouro", 0, 0, "Armadura", 15, 0.05, 0, 0, 0, rd.nextInt(21) + 5),
            new ItemEquipavel("Peitoral de Ferro", 0, 0, "Armadura", 35, 0.15, -5, 0, -1, rd.nextInt(31) + 5),
            new ItemEquipavel("Asas", 0, 0, "Armadura", 3, 0.05, 0, 0, 5, rd.nextInt(41) + 3),
            new ItemEquipavel("Manto da Invisibilidade", 0, 0, "Armadura", 5, 0.3, 0, 10, 5, rd.nextInt(81) + 10),
            new ItemEquipavel("Couro de Dragão", 0, 0, "Armadura", 25, 0.1, 10, 5, -1, rd.nextInt(51) + 20),
            new ItemEquipavel("Túnica do Mago", 0, 0, "Armadura", 10, 0.0, -5, 25, 2, rd.nextInt(41) + 15),
            new ItemEquipavel("Armadura de Solar Flare", 0, 0, "Armadura", 5, 0.6, 0, 0, 2, rd.nextInt(41) + 15),

            new ItemEquipavel("Anel de Rubi", 0, 0, "Acessorio", 5, 0.0, 5, 5, 2, rd.nextInt(71) + 1),
            new ItemEquipavel("Amuleto de Proteção", 0, 0, "Acessorio", 10, 0.1, 0, 10, 0, rd.nextInt(61) + 20),
            new ItemEquipavel("Cinto de Força", 0, 0, "Acessorio", 0, 0.0, 15, 0, -1, rd.nextInt(51) + 15),
            new ItemEquipavel("Botas Velozes", 0, 0, "Acessorio", 0, 0.2, 0, 0, 3, rd.nextInt(41) + 10),
            new ItemEquipavel("Totem de Imortalidade", 0, 0, "Acessorio", 50, 0.0, 0, 0, 0, rd.nextInt(41) + 10)
    );

    private static final List<ItemComum> itensComuns = Arrays.asList(
            new ItemComum("Entulho", 0, 0, "Lixo", rd.nextInt(11) + 1),
            new ItemComum("Galeto Crocante", 0, 0, "Consumível", rd.nextInt(21) + 30),
            new ItemComum("Poção de Vida", 0, 0, "Consumível", rd.nextInt(21) + 30),
            new ItemComum("Dispositivo Desconhecido", 0, 0, "Valioso", rd.nextInt(101) + 30),
            new ItemComum("Pergaminho Antigo", 0, 0, "Miscelânea", rd.nextInt(51) + 10),
            new ItemComum("Diamante", 0, 0, "Valioso", rd.nextInt(101) + 50),
            new ItemComum("Erva Medicinal", 0, 0, "Consumível", rd.nextInt(16) + 15),
            new ItemComum("Jogo de Cartas 'Uno' ", 0, 0, "Valioso", rd.nextInt(51) + 20),
            new ItemComum("Papel Higiênico", 0, 0, "Necessário", rd.nextInt(16) + 5)
    );

    //Getters
    public static ItemEquipavel getItemEquipavelAleatorio() {
        return itensEquipaveis.get(new Random().nextInt(itensEquipaveis.size()));
    }
    public static ItemComum getItemComumAleatorio() {
        return itensComuns.get(new Random().nextInt(itensComuns.size()));
    }
    public static List<ItemEquipavel> getItensEquipaveisAleatorios(int quantidade) {
        Collections.shuffle(itensEquipaveis);
        return new ArrayList<>(itensEquipaveis.subList(0, Math.min(quantidade, itensEquipaveis.size())));
    }
    public static List<ItemComum> getItensComunsAleatorios(int quantidade) {
        Collections.shuffle(itensComuns);
        return new ArrayList<>(itensComuns.subList(0, Math.min(quantidade, itensComuns.size())));
    }
    public static int getValorRecuperacaoConsumivel(String nomeConsumivel) {
        switch (nomeConsumivel) {
            case "Galeto Crocante":
            case "Poção de Vida":
                return 30;
            case "Poção de Mana":
                return 20;
            case "Erva Medicinal":
                return 15;
            default:
                return 0;
        }
    }
    public String getTipo() {
        return tipo;
    }
    public String getNome() {
        return nome;
    }
    public int getLinha() {
        return linha;
    }
    public int getColuna() {
        return coluna;
    }
    public abstract int getValor();
    public abstract int getValorVenda();
}