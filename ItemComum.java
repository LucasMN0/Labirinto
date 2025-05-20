package LABIRINTO;

import java.util.Random;

public class ItemComum extends Tesouros {
    private int valor;

    public ItemComum(String nome, int linha, int coluna, String tipo) {
        super(nome, linha, coluna, tipo);

        // Defina valores diferentes baseados no tipo de item
        switch(tipo.toLowerCase()) {
            case "consumível":
                this.valor = 30;
                break;
            case "utilidade":
                this.valor = 50;
                break;
            case "chave":
                this.valor = 70;
                break;
            default:
                this.valor = 20;
        }
    }

    public int getValor() {
        return valor;
    }
}
