package LABIRINTO;

import java.util.Random;

public class ItemComum extends Tesouros {
    private int valor;
    Random rand = new Random();

    public ItemComum(String nome, int linha, int coluna, String tipo) {
        super(nome, linha, coluna, tipo);
        this.valor = new Random().nextInt(50) + 20; // Valor entre 20-70
    }

    public int getValor() {
        return valor;
    }
}
