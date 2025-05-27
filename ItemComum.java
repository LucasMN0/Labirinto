package LABIRINTO;

import java.util.Random;

public class ItemComum extends Tesouros {
    Random rd = new Random();
    public ItemComum(String nome, int linha, int coluna, String tipo, int valor) {
        super(nome, linha, coluna, tipo, valor);
    }

    @Override
    public int getValor(){
        return valor;
    }

    @Override
    public int getValorVenda() {
        return (int) (getValor() * 0.8);
    }
}
