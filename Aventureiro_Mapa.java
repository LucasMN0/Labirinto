package LABIRINTO;

import java.util.*;

class Aventureiro_Mapa {
    private int posJ;
    private int posI;
    private Mapa mapa;
/*Eu add as pos i e j alem do labirintoAtual, ainda n implementei a ideia "de matriz dentro de matriz"
 * mas imagino que seja so criar uma função de andar modificando a que ja temos e usar ela toda vez que estivermos
 * ma matriz principal tlgd? ai dai gera o random da matriz segundaria pra saber para qual seremos enviados
 * 
 * uma consideração minha, papo q eu gostei da musiquinha q tu fez mano, ficou simples mas ficou super legalzinho skskskksks
 * amanha nos vemos mais, qualquer coisa pode chamar.
 */
    public Aventureiro_Mapa(Mapa mapa) {
        this.mapa = mapa;

        encontrarPosicaoInicial();
    }
    public int getPosJ() {return posJ;}
    public int getPosi() {return posI;}

    public boolean mover(char direcao){
        char direcaoUpper = Character.toUpperCase(direcao);

        if(direcaoUpper != 'W' && direcaoUpper != 'A' && direcaoUpper != 'S' && direcaoUpper != 'D'){
            System.out.println("direção invalida! use W A S D" );
            return false;
        }
        
        int novoJ = posJ;
        int novoI = posI;

        switch(direcao){
            case 'W':
                novoI--;
                break;
            case 'S':
                novoI++;
                break;
            case 'A':
                novoJ--;
                break;
            case 'D':
                novoJ++;
                break;
            default:
                return false;
        }

        if(podeMover(novoJ, novoI)){
            mapa.getEstrutura().get(posI).set(posJ, "°");
            posJ = novoJ;
            posI = novoI;
            mapa.getEstrutura().get(posI).set(posJ, "O");
            return true;
        }
        return false;
    }

    private boolean podeMover(int j, int i){
        if(i< 0 || i >= mapa.getEstrutura().size() || j < 0 || j >= mapa.getEstrutura().get(i).size()){
            return false;
        }
        /*
            ja esta parte de cima so verifica para caso de bugs como sla o mlk ir pra uma coluna ou linha negativa ou acima no max
            issod dai n foi ideai minha n mas papai gpt mandou ;-;

            isso daq de baixo ta verificando se a celula é um X(parede) ou se esta vazia
            "" é diferente de " " por isso funfa
        */ 
        String celula = mapa.getEstrutura().get(i).get(j);
        return !celula.equals("X") && !celula.equals("") && !celula.equals("E");
    }

private void encontrarPosicaoInicial() {
    ArrayList<ArrayList<String>> estrutura = mapa.getEstrutura();
    for (int i = 0; i < estrutura.size(); i++) {
        for (int j = 0; j < estrutura.get(i).size(); j++) {
            if ("O".equals(estrutura.get(i).get(j))) {
                this.posI = i;
                this.posJ = j;
                return;
            }
        }
    }
    // Se não encontrar o "O"
    System.out.println("Erro: posição inicial 'O' não encontrada no labirinto.");
    this.posI = 0;
    this.posJ = 0;
}

}