package LABIRINTO;

import java.util.*;

public static class Armadilha extends Perigo {
    private int velocidade;

    public Armadilha(String nome, String historia, String descricao, int IDP, int dano, int velocidade, int linha, int coluna) {
        super(nome, historia, descricao, IDP, dano, linha, coluna);
        this.velocidade = velocidade;
    }

    public int getVelocidade() { return velocidade; }

    // Lista de armadilhas existentes
    public static final List<Armadilha> Armadilhas = new ArrayList<>();

    static {
        Armadilhas.add(new Armadilha(
                "Armadilha de Espinhos",
                "Uma armadilha ancestral deixada pelos construtores do labirinto.\n"
                        +"não se sabe o porque deles precisarem colocar armadilha aqui\n"+
                        "mas varios aventureiros assim como voce acreditam que aqui tem um grande tesouro"+
                        "por isso varios aventureiros assim como voce morreram aqui",
                "O chão tem varios furinhos estranhos o que será que é isso?\n" +
                        "espinhos enormes surgem do chão penetrando fundo em sua carne",
                1, 15, 5,0,0
        ));

        Armadilhas.add(new Armadilha(
                "Armadilha de Flechas",
                "Mecanismo antigo que dispara flechas envenenadas...",
                "Você ouve um clique e flechas voam em sua direção",
                2, 20, 7,0 ,0
        ));

        Armadilhas.add(new Armadilha(
                "Poço Oculto",
                "Covil de criaturas subterrâneas famintas...",
                "O chão cede sob seus pés e você cai em um poço escuro",
                3, 25, 3,0 ,0
        ));
    }
}