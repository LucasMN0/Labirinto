package LABIRINTO;

import java.util.*;

public class Perigo{
    private String nome;
    private String historia;
    private String descricao;
    private int IDP;
    private int dano;

    public Perigo(String nome, String historia, String descricao, int IDP, int dano){
        this.nome = nome;
        this.historia = historia;
        this.descricao = descricao;
        this.IDP = IDP;
        this.dano = dano;
    }

    public String getNome() {return nome;}
    public String getHistoria() {return  historia;}
    public String getDescricao() {return descricao;}
    public int getIDP() {return IDP;}
    public int getDano() {return dano;}

    public static final Perigo ARMADILHA_DE_ESPINHOS = new Perigo(
            "Armadilha de Espinhos",
            "Uma armadilha ancestral deixada pelos construtores do labirinto.\n" +
            "não se sabe o porque deles precisarem colocar armadilha aqui\n" +
            "mas varios aventureiros assim como voce acreditam que aqui tem um grande tesouro"+
            "por isso varios aventureiros assim como voce morreram aqui",
            "O chão tem varios furinhos estranhos o que será que é isso?\n" +
                    "espinhos enormes surgem do chão penetrando fundo em sua carne",
            1,
            15
    );


    // aq pra baixo so tem monstros, aq pra cima tem armadilhas
    class Inimigo extends Perigo{
        private int velocidade;
        private int armadura;

        public Inimigo(String nome, String historia, String descricao, int IDP, int dano, int velocidade, int armadura){
            super(nome, historia, descricao, IDP, dano);
            this.velocidade = velocidade;
            this.armadura = armadura;
        }

        public int getVelocidade() {return velocidade;}
        public int getArmadura() {return armadura;}

        public final Inimigo Pato_Armado = new Inimigo(
                "Pato Armado",
                "Niguem tem ideia de como isso aconteceu" +
                        "porém, muitos acreditam que dentro desta grande tumba"+
                        "os sonhos mais estranhos possam ganhar vida, porém,"+
                        "quem que sonha com um pato com uma doze????",
                "Um pato do tamanho de um pato normal, porem com uma calibre doze em suas azas",
                1,
                20,
                10,
                3
        );
    }
}

