package LABIRINTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Armadilha extends Perigo {
    private int velocidadeNecessaria;
    private static final List<Armadilha> Armadilhas = new ArrayList<>();
    private static final Random rand = new Random();

    static {
        Armadilhas.add(new Armadilha(
                "Armadilha de Urso",
                "Uma armadilha de caça perigosa.",
                "Você pisa em uma armadilha de urso oculta, causando dano e imobilizando-o brevemente.",
                1,
                20,
                10,
                0, // linha
                0  // coluna
        ));
        Armadilhas.add(new Armadilha(
                "Poço Escondido",
                "Um buraco coberto por folhas e galhos.",
                "Você cai em um poço, mas consegue amortecer a queda, sofrendo dano moderado.",
                2,
                15,
                8,
                0, // linha
                0  // coluna
        ));
        Armadilhas.add(new Armadilha(
                "Dardos Venenosos",
                "Dardos disparados de uma parede oculta.",
                "Uma série de dardos envenenados te atinge, causando dano contínuo por um tempo.",
                3,
                10,
                15,
                0, // linha
                0  // coluna
        ));
        Armadilhas.add(new Armadilha(
                "Pedra Rolante",
                "Uma grande pedra que desce uma rampa.",
                "Uma pedra gigante rola em sua direção! Você tenta desviar, mas é atingido de raspão.",
                4,
                25,
                12,
                0, // linha
                0  // coluna
        ));
        Armadilhas.add(new Armadilha(
                "Nuvem de Gás",
                "Um gás sufocante preenche a área.",
                "Você inala uma nuvem de gás tóxico, que drena sua vitalidade rapidamente.",
                5,
                18,
                7,
                0, // linha
                0  // coluna
        ));
        Armadilhas.add(new Armadilha(
                "Armadilha de Espinhos",
                "Uma armadilha ancestral deixada pelos construtores do labirinto.\n"
                        +"não se sabe o porque deles precisarem colocar armadilha aqui\n"+
                        "mas varios aventureiros assim como voce acreditam que aqui tem um grande tesouro"+
                        "por isso varios aventureiros assim como voce morreram aqui",
                "O chão tem varios furinhos estranhos o que será que é isso?\n" +
                        "espinhos enormes surgem do chão penetrando fundo em sua carne",
                6, 15, 5,0,0
        ));
        Armadilhas.add(new Armadilha(
                "Mímico",
                "Você não estára vivo para descobrir a história do mímico.\n",
                "Um baú!........................................\n" +
                        "...................................................\n" +
                        "...................................................\n" +
                        "...................................................\n" +
                        "ou não.. DE REPENTE UMA BOCA GIGANTE TE DEVORA E VOCÊ É ENGOLIDO E NUNCA MAIS FOI ENCONTRADO.",
                7, 100000, 100,0,0
        ));
    }

    public Armadilha(String nome, String historia, String descricao, int IDP, int dano, int velocidadeNecessaria, int linha, int coluna) {
        super(nome, historia, descricao, IDP, dano, linha, coluna);
        this.velocidadeNecessaria = velocidadeNecessaria;
    }

    public int getVelocidade() {
        return velocidadeNecessaria;
    }

    @Override
    public Perigo copiar() {
        return new Armadilha(getNome(), getHistoria(), getDescricao(), getIDP(), getDano(), getVelocidade(), getLinha(), getColuna());
    }

    public static Armadilha getArmadilhaAleatoria() {
        return Armadilhas.get(rand.nextInt(Armadilhas.size()));
    }
}