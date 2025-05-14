package Labirinto;

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
    public Aventureiro(String nome, int startJ, int startI, ArrayList<String> tesourosEncontrados, Labirinto labirinto) {
        this.nome = nome;
        this.posJ = startJ;
        this.posI = startI;
        this.tesourosEncontrados = tesourosEncontrados;
        this.labirintoAtual = labirinto;
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
            posJ = novoJ;
            posI = novoI;
            verificarTesouro();
            return true;
        }
        return false;
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
        return !celula.equals("X") && !celula.equals("");
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