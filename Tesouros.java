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
            // Armas
            new ItemEquipavel("Espada de Onix", 0, 0, "Arma", 0, 0.00, 30, 1, 0, rd.nextInt(51) + 5),
            new ItemEquipavel("Machado Quebrado", 0, 0, "Arma", 0, 0.0, 5, 0, 0, rd.nextInt(6) + 1),
            new ItemEquipavel("Cajado de Mago", 0, 0, "Arma", 0, 0.0, 10, 30, -1, rd.nextInt(41) + 5),

            // Armaduras
            new ItemEquipavel("Armadura de Ouro", 0, 0, "Armadura", 15, 0.05, 0, 0, 0, rd.nextInt(21) + 5),
            new ItemEquipavel("Peitoral de Ferro", 0, 0, "Armadura", 35, 0.15, -5, 0, -1, rd.nextInt(31) + 5),
            new ItemEquipavel("Asas", 0, 0, "Armadura", 3, 0.05, 0, 0, 5, rd.nextInt(41) + 3),

            // Acessórios
            new ItemEquipavel("Anel de Rubi", 0, 0, "Acessorio", 5, 0.0, 5, 5, 2,rd.nextInt(71) + 1)
    );

    private static final List<ItemComum> itensComuns = Arrays.asList(
            // Itens comuns (nome, linha, coluna, tipo, valor)
            new ItemComum("Entulho", 0, 0, "Lixo", rd.nextInt(11) + 1),
            new ItemComum("Galeto Crocante", 0, 0, "Consumível", rd.nextInt(21) + 30),
            new ItemComum("Poção de Vida", 0, 0, "Consumível", rd.nextInt(21) + 30)
    );

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
