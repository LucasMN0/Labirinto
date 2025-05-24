package LABIRINTO;

import java.util.*;
import java.util.Random;

public class Perigo{
    private String nome;
    private String historia;
    private String descricao;
    private int IDP;
    private int dano;
    private int linha;
    private int coluna;

    public Perigo(String nome, String historia, String descricao, int IDP, int dano, int linha, int coluna){
        this.nome = nome;
        this.historia = historia;
        this.descricao = descricao;
        this.IDP = IDP;
        this.dano = dano;
        this.linha = linha;
        this.coluna = coluna;
    }

    public String getNome() {return nome;}
    public String getHistoria() {return  historia;}
    public String getDescricao() {return descricao;}
    public int getIDP() {return IDP;}
    public int getDano() {return dano;}
    public int getLinha() { return linha; }
    public int getColuna() { return coluna; }

    public static Perigo criarPerigoAleatorio(Random rand) {
        if (rand.nextBoolean()) {
            return Armadilha.Armadilhas.get(rand.nextInt(Armadilha.Armadilhas.size()));
        } else {
            return Inimigo.Inimigos.get(rand.nextInt(Inimigo.Inimigos.size()));
        }
    }

    public Perigo copiar() {
        if (this instanceof Armadilha) {
            Armadilha a = (Armadilha) this;
            return new Armadilha(nome, historia, descricao, IDP, dano, a.getVelocidade(), 0, 0);
        } else if (this instanceof Inimigo) {
            Inimigo i = (Inimigo) this;
            return new Inimigo(nome, historia, descricao, IDP, dano, i.getVelocidade(),
                    i.getArmadura(), i.getVida(), i.getDanoVerdadeiro(), 0, 0);
        }
        return null;
    }

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

    public static class Inimigo extends Perigo{
        private int velocidade;
        private int armadura;
        private int vida;
        private int danoVerdadeiro;

        public Inimigo(String nome, String historia, String descricao, int IDP, int dano, int velocidade, int armadura, int vida, int danoVerdadeiro, int linha, int coluna){
            super(nome, historia, descricao, IDP, dano, linha, coluna);
            this.velocidade = velocidade;
            this.armadura = armadura;
            this.vida = vida;
            this.danoVerdadeiro = danoVerdadeiro;
        }

        public int getVelocidade() { return velocidade; }
        public int getArmadura() { return armadura; }
        public int getVida() { return vida; }
        public int getDanoVerdadeiro() { return danoVerdadeiro; }
        public void setVida(int vida) { this.vida = vida; }

        public static final List<Inimigo> Inimigos = new ArrayList<>();

        static{
                Inimigos.add(new Inimigo(
                        "Pato Armado",
                        "Niguem tem ideia de como isso aconteceu\n" +
                        "porém, muitos acreditavam que nestas terras devastadas\n" +
                        "os sonhos mais estranhos possam ganhar vida, porém,\n" +
                        "quem que sonha com um pato com uma doze????\n",
                        "Um pato do tamanho de um pato normal, porem com uma calibre doze em suas azas",
                        1,
                        20,
                        10,
                        3,
                        5,
                        3,
                        0,
                        0
                ));

                Inimigos.add(new Inimigo(
                        "sombra_errante",
                        "As terras devastadas e esquecida pelos deuses\n" +
                        "onde apenas o nada vagueia por este arido deserto cinza\n" +
                        "a morte dos antigos deixou sua marca em terra sangue guerra e morte\n" +
                        "as cinzas dos mortos jamais voltarão as suas sepulturas, ja suas sombras.",
                        "Uma sombra humanoide no chão porem sem niguem que esteja a projetando\n" +
                        "apenas uma sombra sozinha",
                        2,
                        15,
                        30,
                        0,
                        20,
                        3,
                        0,
                        0
                ));

                Inimigos.add(new Inimigo(
                        "anjo sem asas",
                        "A eras, quandos o proprio poder ainda dormia\n" +
                        "um ele teve um sonho um ############## foi dito que\n" +
                        "alguns dos lapsos da vontade divina personificados escolheram o melhor dos lados\n" +
                        "dessa forma a ira encarnada dos deuses veio a suas asas cortar",
                        "forçados por forças maiores a viverem em carne e osso, são humanos comuns\n" +
                        "mas muito fortes",
                        3,
                        15,
                        15,
                        20,
                        20,
                        3,
                        0,
                        0
                ));

                Inimigos.add(new Inimigo(
                        "ambição de outrora",
                        "A muito tempo existia uma ilha, niguem sobrevia para voltar\n" +
                        "há segredos nessa vida que não se deve desvendar, há brehcas que não se deve espreitar\n" +
                        "e a regras que não se deve violar, incertos do destino incerto, muitos foram para explorar\n" +
                        "porém niguem nunca voltou de la, é dito que suas almas estão presas, mas as vezes algumas voltam de la",
                        "Um homem barbudo, roupas rasgadas e molhadas pele traslucida aparecendo as veias\n" +
                        "carregando um arpão e um cheiro persitente de peixe",
                        3,
                        13,
                        12,
                        15,
                        17,
                        3,
                        0,
                        0
                ));

                Inimigos.add(new Inimigo(
                        "litch",
                        "uma vez foi dito que os sonhos de um homem não tem fim, portanto sua ambição\n" +
                        "desconhece a finitude, personificando a gula e a sede pelo conhecimento\n" +
                        "muitos se corromperam ao bisbilhotar conhecimentos antigos portanto,\n" +
                        "quanto menos se sabe mais se vive e os imortais? enlouquecem\n" +
                        "o conhecimento da imortalidade lhe custa tudo o que é mortal, seus osso, carne, sangue e mente",
                        "um esqueleto humano, ainda vivo, suas orbitas oculares são escuras\n" +
                        "tem a mior parte do corpo coberto por um pano longo e preto",
                        4,
                        15,
                        7,
                        10,
                        20,
                        3,
                        0,
                        0
                ));

                Inimigos.add(new Inimigo(
                        "anjo",
                        "em um vazio sem forma antes da propria existencia, surgiu uma vontade\n" +
                        "da vontade surgiu algo sem nome cor ou forma, a propria força, o proprio poder\n" +
                        "o poder dormia no vazio sem forma, mas sua simples presença mudava o rumo de todas as coisas\n" +
                        "lapsos de sua vontade, pedaços de sua força deu origem a seres com formas e com um fardo\n" +
                        "assima de tudo, uma sina",
                        "seres de forma mutavel e variavel quase como se fluidos, poderiam ser enchames de olhos\n" +
                        "espadas em fogo, ou mesmo um urso de pelucia, mas nunca se deixe enganar\n" +
                        "anjos não são mais humanos",
                        5,
                        24,
                        20,
                        20,
                        30,
                        3,
                        0,
                        0
                ));

                Inimigos.add(new Inimigo(
                        "diabo",
                        "Os anjos vinheram do proprio poder e os diabos vinheram ##############\n" +
                        "herdeiros do proprio ##############, nasceram com seus destino traçado\n" +
                        "o destino de serem odiados pelos herdeiros do poder\n" +
                        "o destino de serem destituidos do seu direito de herança\n" +
                        "nascidos como herdeiros de algo inominavel e com o destino de serem esquecidos\n" +
                        "buscam tomar de volta aquilo que é seu por direito, o ceus e a terra um dia farão parte de seu reino",
                        "tem uma aparencia humana com parte de animais, como pernas de bode ou chifres, porem isso é por ecolha propria\n",
                        6,
                        30,
                        10,
                        6,
                        30,
                        3,
                        0,
                        0
                ));
                //primeiro boss
                Inimigos.add(new Inimigo(
                        "Prometheus",
                        "Os anjos vieram do poder, os diabos do ##############\n"+
                                "sua união profana deu origem aos titãs, e entre os titãs houve um rei\n"+
                                "tão antigo quanto a própria história, tendo presenciado o início dos tempos.\n"+
                                "Prometheus deu origem aos humanos devido à sua grande ambição de ser como o próprio poder.\n"+
                                "Prometheus roubou a própria chama da vida do poder, mas as ambições de um rei tolo não têm fim.\n"+
                                "A gula pela força e conhecimento de Prometheus o fez querer roubar o conhecimento\n"+
                                "e a vontade de ##############, mas até mesmo a vontade do rei dos titãs encontrou-se diante de seu fim.\n"+
                                "############## deu sua vontade e conhecimento de bom grado para Prometheus, mas o conhecimento é o maior peso\n"+
                                "que se pode carregar. Logo, Prometheus desejou esquecer e enterrar a verdade.\n"+
                                "Lágrimas escorriam do rosto de Prometheus: \"A verdade é a coisa mais enlouquecedora do mundo, ##############,\n"+
                                "e conhecê-la é um tormento cruel. Portanto, eu desejo esquecê-la e enterrá-la.\"\n"+
                                "Logo, ############## disse: \"Você quer enterrar a verdade e, portanto, você não a merece.\"\n"+
                                "Prometheus arruinou seu reino e sua própria vida, enlouquecendo completamente no processo e vagando pelas cinzas da guerra.",
                        "Você se depara com uma criatura muito semelhante a um humano, mas sua pele era completamente negra,\n"+
                                "com um brilho metálico, um pouco mais alta que o comum, porém exalando uma aura de puro poder.\n"+
                                "Seus arredores estão em chamas.",
                        7,
                        45,
                        30,
                        50,
                        100,
                        10,
                        0,
                        0
                ));
                // boss dois
                Inimigos.add(new Inimigo(
                        "mão de deus",
                        "Diferente de muitos anjos que nasceram ao acaso e se atribuíram nomes\n"+
                                "até entre eles, criaturas que beiram a divindade, existem exceções. Dentre eles nasceu\n"+
                                "criado pela própria vontade, atitude e decisão do poder, com um propósito e uma sina.\n"+
                                "Sem nome, não havia necessidade de pôr nome a ferramentas de destruição. Assim, ele foi chamado de\n"+
                                "mão de deus, sendo sua vista um prelúdio de destruição e da ação da vontade divina. Após a queda do poder,\n"+
                                "a maioria dos anjos voltou aos céus, para se refugiar e correr de um perigo avassalador.\n"+
                                "Mas o poder nunca concedeu a chave dos céus a seu combatente mais forte, portanto lhe restou vagar pelas cinzas.\n"+
                                "Sendo um dos últimos resquícios da vontade do poder, os anjos foram caçados e exterminados nestas terras de morte e sangue.\n"+
                                "Os céus haviam caído e o panteão dos novos deuses pereceu há muito tempo. Apenas a mão de deus resistiu a tudo isso,\n"+
                                "um ato de força e resiliência. Devido à sua fé cega, ele se propôs uma última missão em nome do poder: eliminar ##############\n"+
                                "e assim garantir que tudo volte ao vazio eterno, imutável. E para isso seria necessário o mais puro caos e destruição.\n"+
                                "E exatamente para isso o poder o fez e o declarou sua mão, portador de sua vontade.",
                        "Um ser envolto em uma roupa branca imaculada, carregando consigo uma espada de pura luz.\n"+
                                "Suas asas parecem traços de explosões solares radiantes. Ele paira acima da terra\n"+
                                "como se ela não fosse digna de tocar os seus pés.",
                        8,
                        45,
                        20,
                        30,
                        200,
                        10,
                        0,
                        0
                ));
                // 3 e ultimo boss oldest dream esse nome eu achei muito poggers
                Inimigos.add(new Inimigo(
                        "Memórias de um Sonho Antigo",
                        "No início tudo era vazio e sem forma. Do próprio vazio surgiu a vontade; da vontade, o poder.\n"+
                                "Antes de haver o próprio tempo, o poder dormia envolto no caos que sua própria presença criava.\n"+
                                "Assim, o poder teve seu primeiro e único sonho: um sonho antigo, que, vindo do poder, carregou sua singularidade com ele.\n"+
                                "A própria vontade. O sonho antigo criou sua própria vontade; dela, seu próprio povo; dele, seu próprio império; dele, seu próprio poder.\n"+
                                "No início dos tempos, o poder despertou. Sua vontade e magnitude eram tamanhas que sua simples presença aniquilou seu povo e, como collateral,\n"+
                                "o império do sonho antigo. Desde então, ele viu o poder como seu adversário e não deixaria barata essa afronta.\n"+
                                "Éras se passaram. O sonho se fez carne e habitou entre as criações de Prometheus. Lá, criou seu novo império,\n"+
                                "aproximou-se de diversos humanos e criou afeto por vários deles. Até que a ganância de Prometheus pôs tudo à ruína.\n"+
                                "Ele quis mais do que cabia em sua boca. Assim, houve algumas retaliações do próprio poder. Entre os reinos destruídos,\n"+
                                "o do sonho foi um deles. E, nesse dia, ele entendeu que sua sina e seu destino eram solitários, frios e sem cor—apenas mais uma sombra do poder.\n"+
                                "Portanto, não haveria sombras sem luz. A única maneira de dar fim a tudo isso seria dar fim ao poder.\n"+
                                "E ele conseguiu. Uma guerra de dimensões inconcebíveis ocorreu, e o sonho saiu vitorioso. Mas, diferente do que ele achava,\n"+
                                "ele não morreu junto com o poder, e sua imortalidade não o permitia ser morto. Assim, ele separou sua mente em uma persona—você—\n"+
                                "destinada, a uma hora ou outra, a matar o próprio sonho. Agora você sabe disso. Você deveria estar morto.\n"+
                                "Que o vazio eterno seja um descanso adequado, sonho de outrora.",
                        "Uma sombra de formato humano, repleta de pontos de luz em seu interior.\n"+
                                "Ela estranhamente parece muito com você.",
                        9,
                        50,
                        50,
                        40,
                        300,
                        10,
                        0,
                        0

                ));
        }
    }
}

