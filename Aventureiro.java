package LABIRINTO;

import java.util.*;

class Aventureiro {
    private String nome;
    private int posJ;
    private int posI;
    private ArrayList<String> tesourosEncontrados;
    private Labirinto labirintoAtual;
/*Eu add as pos i e j alem do labirintoAtual, ainda n implementei a ideia "de matriz dentro de matriz"
 * mas imagino que seja so criar uma função de andar modificando a que ja temos e usar ela toda vez que estivermos
 * ma matriz principal tlgd? ai dai gera o random da matriz segundaria pra saber para qual seremos enviados
 * 
 * uma consideração minha, papo q eu gostei da musiquinha q tu fez mano, ficou simples mas ficou super legalzinho skskskksks
 * amanha nos vemos mais, qualquer coisa pode chamar.
 */
    public Aventureiro(String nome, ArrayList<String> tesourosEncontrados, Labirinto labirinto, int I , int J) {
        this.nome = nome;
        this.tesourosEncontrados = tesourosEncontrados;
        this.labirintoAtual = labirinto;
        this.posI = I;
        this.posJ = J;


    }
    public int getPosJ() {return posJ;}
    public int getPosI() {return posI;}

public boolean mover(char direcao) {
    char direcaoUpper = Character.toUpperCase(direcao);

    if (direcaoUpper != 'W' && direcaoUpper != 'A' && direcaoUpper != 'S' && direcaoUpper != 'D') {
        System.out.println("direção inválida! use W A S D");
        return false;
    }

    int novoI = posI;
    int novoJ = posJ;

    switch (direcaoUpper) {
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
    }

    if (novoI < 0 || novoI >= labirintoAtual.getEstrutura().size()) {
        return false;
    }

    if (podeMover(novoJ, novoI)) {
        labirintoAtual.getEstrutura().get(posI).set(posJ, "°");
        posI = novoI;
        posJ = novoJ;
        labirintoAtual.getEstrutura().get(posI).set(posJ, "O");
        verificarTesouro();
        return true;
    }

    return false;
}



    public boolean sair(){
        return posI == labirintoAtual.getFimI() && posJ == labirintoAtual.getFimJ();
    }

    private boolean podeMover(int j, int i){
        if(i< 0 || i >= labirintoAtual.getEstrutura().size() || j < 0 || j >= labirintoAtual.getEstrutura().get(i).size()){
            return false;
        }
        /*
            ja esta parte de cima so verifica para caso de bugs como sla o mlk ir pra uma coluna ou linha negativa ou acima no max
            issod dai n foi ideai minha n mas papai gpt mandou ;-;

            isso daq de baixo ta verificando se a celula é um X(parede) ou se esta vazia
            "" é diferente de " " por isso funfa
        */ 
        String celula = labirintoAtual.getEstrutura().get(i).get(j);
        return !celula.equals("X") && !celula.equals("") && !celula.equals("E");
    }

    private void verificarTesouro(){
        String celula = labirintoAtual.getEstrutura().get(posI).get(posJ);
        if(labirintoAtual.getListaTesouros().contains(celula)){
            tesourosEncontrados.add(celula);
            System.out.println("voce encontrou um tesouro: " + celula);
        }
    }

    public void coletarRecursos() {
        // lógica futura de coleta
    }
}