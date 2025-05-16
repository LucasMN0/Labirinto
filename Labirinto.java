package LABIRINTO;

import java.util.*;

public class Labirinto {
    private ArrayList<ArrayList<String>> estruturaLabirinto;
    private ArrayList<Tesouros> listaTesouros;
    private ArrayList<String> listaPerigos;
    private Labirinto subLabirintoAtual = null;
    private int[] posicaoEntradaSubLabirinto = null;
    private boolean emSubLabirinto = false;
    private int ID;
    private int InicioI, InicioJ;
    private int FimI, FimJ;

    public Labirinto(int ID) {
        this.ID = ID;
        estruturaLabirinto = new ArrayList<>();
        listaTesouros = new ArrayList<>();
        listaPerigos = new ArrayList<>();

        gerar_labirinto(this.ID);
        adicionarTesouroAleatorio();
    }

    public void entrarSubLabirinto(int i, int j) {
        this.posicaoEntradaSubLabirinto = new int[]{i, j};
        Random rand = new Random();
        int idSubLabirinto = rand.nextInt(11) + 4; // Começa do 4 para não conflitar com os níveis 1-3

        // Garante que o sub-labirinto seja diferente do atual
        while (idSubLabirinto == this.getID()) {
            idSubLabirinto = rand.nextInt(11) + 4;
        }

        // Inicialização completa do sub-labirinto
        this.subLabirintoAtual = new Labirinto(idSubLabirinto);
        this.subLabirintoAtual.setLabirintoPai(this); // aqui liga o pai
        this.subLabirintoAtual.emSubLabirinto = true;
        this.emSubLabirinto = true;


        System.out.println("Entrando no labirinto secreto ID: " + idSubLabirinto);
    }
    private Labirinto encontrarLabirintoPrincipal(Labirinto atual) {
        if (!atual.isEmSubLabirinto()) {
            return atual;
        }
        if (atual.getLabirintoPai() != null) {
            return encontrarLabirintoPrincipal(atual.getLabirintoPai());
        }
        return atual;
    }

    public void sairDoSubLabirinto() {
        if (this.subLabirintoAtual != null) {
            this.subLabirintoAtual = null;
        }
        this.emSubLabirinto = false;
        this.posicaoEntradaSubLabirinto = null;
    }

    public boolean deveGerarSubLabirinto() {
        // Só gera sub-labirinto se for um mapa de nível (1-3) e não estiver já em um sub-labirinto
        return (this.ID >= 1 && this.ID <= 3) && !this.emSubLabirinto;
    }

    public void setSubLabirintoAtual(Labirinto subLabirinto) {
        this.subLabirintoAtual = subLabirinto;
    }

    public boolean isEmSubLabirinto() {
        return emSubLabirinto;
    }

    public Labirinto getSubLabirintoAtual() {
        return this.subLabirintoAtual;
    }

    public int[] getPosicaoEntradaSubLabirinto() {
        return posicaoEntradaSubLabirinto;
    }

    public int getInicioI() {
        if (emSubLabirinto && subLabirintoAtual != null) {
            return subLabirintoAtual.getInicioI();
        }
        return InicioI;
    }

    public int getInicioJ() {
        if (emSubLabirinto && subLabirintoAtual != null) {
            return subLabirintoAtual.getInicioJ();
        }
        return InicioJ;
    }

    public int getFimI() {
        return (emSubLabirinto && subLabirintoAtual != null) ?
                subLabirintoAtual.getFimI() :
        FimI;
    }

    public int getFimJ() {
        return (emSubLabirinto && subLabirintoAtual != null) ?
                subLabirintoAtual.getFimJ() :
        FimJ;
    }

    public int getID() {
        return this.ID;
    }

    public ArrayList<ArrayList<String>> getEstrutura() {
        if (emSubLabirinto && subLabirintoAtual != null) {
            return subLabirintoAtual.getEstrutura();
        }
        return estruturaLabirinto;
    }

    private Labirinto labirintoPai = null;

    public Labirinto getLabirintoPai() {
        return labirintoPai;
    }

    public void setLabirintoPai(Labirinto pai) {
        this.labirintoPai = pai;
    }


    public void gerar_labirinto(int ID){
        estruturaLabirinto.clear();
        String[][] matriz;

        switch(ID){
            case 1:
                matriz = new String[][] {
                        {"X", "X", "S", "X", "X"},
                        {"X", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", "X"},
                        {"X", " ", "O", " ", "X"},
                        {"X", "X", "E", "X", "X"},
                };
                this.InicioI = 3;
                this.InicioJ = 2;
                this.FimI = 0;
                this.FimJ = 2;
                break;
            case 2:
                matriz = new String[][] {
                        {"X", "X", "X", "X", "X", "X", "S", "X"},
                        {"X", " ", " ", " ", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", " ", " ", " ", "X"},
                        {"E", "O", " ", " ", " ", " ", " ", "X"},
                        {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                this.InicioI = 6;
                this.InicioJ = 0;
                this.FimI = 0;
                this.FimJ = 6;
                break;
            case 3:
                matriz = new String[][] {
                        {"X", "X", "X", "X", "X", "X", "X", "X"},
                        {"X", " ", " ", " ", " ", " ", " ", "X"},
                        {"E", "O", " ", " ", " ", " ", " ", "S"},
                        {"X", " ", " ", " ", " ", " ", " ", "X"},
                        {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                this.InicioI = 2;
                this.InicioJ = 1;
                this.FimI = 2;
                this.FimJ = 7;
                break;
            case 4:
                matriz = new String[][] {
                        {".", ".", "X", "X", "S", "X","X"},
                        {".", "X", "X", " ", " ", " ", "X", "X"},
                        {"X", "X", " ", " ", " ", " ", " ", "X","X"},
                        {"X", " ", " ", " ", "X", " ", " ", " ","X"},
                        {"X", " ", " ", "X", "X", "X", " ", " ","X"},
                        {"X", " ", " ", " ", "X", " ", " ", " ","X"},
                        {"X", "X", " ", " ", " ", " ", " ", "X","X"},
                        {".", "X", "X", " ", "O", " ", "X", "X"},
                        {".", ".", "X", "X", "E", "X" ,"X"},
                };
                this.InicioI = 7;
                this.InicioJ = 4;
                this.FimI = 0;
                this.FimJ = 4;
                break;
            case 5:
                matriz = new String[][] {
                        {"X", "X", "E", "X", "X"},
                        {"X", " ", "O", " ", "X"},
                        {"X", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", "X", "X", "X", "X"},
                        {"X", " ", " ", " ", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", " ", " ", " ", "S"},
                        {"X", " ", " ", " ", " ", " ", " ", "X"},
                        {"X", "X", "X", "X", "X", "X", "X", "X"},
                };
                this.InicioI = 1;
                this.InicioJ = 2;
                this.FimI = 8;
                this.FimJ = 7;
                break;
            case 6:
                matriz = new String[][] {
                        {".", ".", ".", ".", "X", "X", "S", "X", "X"},
                        {".", ".", ".", ".", "X", " ", " ", " ", "X"},
                        {".", ".", ".", ".", "X", " ", " ", " ", "X"},
                        {".", ".", ".", ".", "X", " ", " ", " ", "X"},
                        {".", ".", ".", ".", "X", " ", " ", " ", "X"},
                        {"X", "X", "X", "X", "X", " ", " ", " ", "X", "X", "X", "X", "X"},
                        {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
                        {"X", "X", "X", "X", "X", " ", " ", " ", "X", "X", "X", "X", "X"},
                        {".", ".", ".", ".", "X", " ", " ", " ", "X"},
                        {".", ".", ".", ".", "X", " ", " ", " ", "X"},
                        {".", ".", ".", ".", "X", " ", " ", " ", "X"},
                        {".", ".", ".", ".", "X", " ", "O", " ", "X"},
                        {".", ".", ".", ".", "X", "X", "E", "X", "X"},
                };
                this.InicioI = 13;
                this.InicioJ = 6;
                this.FimI = 0;
                this.FimJ = 6;
                break;
            case 7:
                matriz = new String[][] {
                        {".", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X","."},
                        {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ","X"},
                        {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X", " ", "X", " ","X"},
                        {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ","X"},
                        {"X", " ", " ", " ", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", " ", " ", " ", " ", " ","X"},
                        {"X", " ", " ", " ", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", ".", "X", " ", " ", " ", " ", " ","X"},
                        {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X", ".", "X", "X", " ", "O", " ", "X","X"},
                        {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X", ".", ".", "X", "X", "E", "X", "X","."},
                        {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X", ".", ".", ".", ".", "X"},
                        {".", "X", "X", "X", "X", "X", "X", "X", "X", "X", " ", " ", " ", "X", ".", ".", ".", ".", "X"},
                        {".", ".", ".", ".", "X", " ", " ", " ", " ", " ", " ", " ", " ", "X", ".", ".", ".", "X", ".","X"},
                        {".", ".", ".", ".", "S", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
                        {".", ".", ".", ".", "X", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
                        {".", ".", ".", ".", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
                };
                this.InicioI = 6;
                this.InicioJ = 19;
                this.FimI = 11;
                this.FimJ = 4;
                break;
            case 8:
                matriz = new String[][] {
                        {"X", "X", ".", ".", ".", "X", "X", "X", "X", "X"},
                        {"X", " ", "X", ".", "X", " ", " "," "," "," ", "X"},
                        {"X", " ", " ", "X", " ", " ", " ", " ", " ", "X"," ", "X"},
                        {"E", "O", " ", " ", " ", " ", " ", " ", " ", " "," ", "S"},
                        {"X", " ", " ", "X", " ", " ", " ", " ", " ", " "," ", "X"},
                        {"X", " ", "X", ".", "X", " ", " "," "," "," ", "X"},
                        {"X", "X", ".", ".", ".", "X", "X", "X", "X","X"},
                };
                this.InicioI = 3;
                this.InicioJ = 1;
                this.FimI = 3;
                this.FimJ = 11;
                break;
            case 9:
                matriz = new String[][] {
                        {".",".","X","X","X","S","X","X","X"},
                        {".","X","X"," "," "," "," "," ","X","X"},
                        {"X","X"," ","X","X"," "," "," "," ","X"},
                        {"X"," "," ","X","X"," "," "," ","X"},
                        {"X"," "," "," ", " ", " "," ","X"},
                        {"X"," "," "," ", " ", " ","X"},
                        {"X"," "," "," ", " ", " "," ","X"},
                        {"X"," "," "," "," "," "," "," ","X"},
                        {"X","X"," "," "," "," "," "," "," ","X"},
                        {".","X","X"," "," ","O"," "," ","X","X"},
                        {".",".","X","X","X","E","X","X","X"},
                };
                this.InicioI = 9;
                this.InicioJ = 5;
                this.FimI = 0;
                this.FimJ = 5;
                break;
            case 10:
                matriz = new String[][] {
                        {".",".",".",".","X","X","S","X","X",".",".",".","."},
                        {".",".",".",".","X"," "," "," ","X",".",".",".","."},
                        {".",".",".",".","X"," "," "," ","X",".",".",".","."},
                        {".",".",".",".","X"," "," "," ","X",".",".",".","."},
                        {"X","X","X","X","X"," "," "," ","X","X","X","X","X"},
                        {"X"," "," "," "," "," "," "," "," "," "," "," ","X"},
                        {"X"," "," "," "," "," "," "," "," "," "," "," ","X"},
                        {"X"," "," "," "," "," "," "," "," "," "," "," ","X"},
                        {"X","X","X","X","X"," "," "," ","X","X","X","X","X"}, // 7
                        {".",".",".",".","X"," "," "," ","X",".",".",".","."},  // 11
                        {".",".",".",".","X"," "," "," ","X",".",".",".","."},
                        {".",".",".",".","X"," "," "," ","X",".",".",".","."},
                        {".",".",".",".","X"," "," "," ","X",".",".",".","."},
                        {".",".",".",".","X"," "," "," ","X",".",".",".","."},
                        {".",".",".",".","X"," "," "," ","X",".",".",".","."},
                        {".",".",".",".","X"," ","O"," ","X",".",".",".","."},
                        {".",".",".",".","X","X","E","X","X",".",".",".","."},
                };
                this.InicioI = 15;
                this.InicioJ = 6;
                this.FimI = 0;
                this.FimJ = 6;
                break;
            case 11:
                matriz = new String[][] {
                        {".",".",".", "X", "X", "X", "S", "X","X", "X"},
                        {".",".","X","X"," ", " ", " ", " ", " ", "X","X"},
                        {".","X","X"," ", " ", " ", " ", " "," "," ", "X","X"},
                        {"X", "X", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X","X"},
                        {"X", " ", " ", "X", "X", " ", " ", " ", "X", "X", " ", " ","X"},
                        {"X", " ", " ", "X", "X", " ", " ", " ", "X", "X", " ", " ","X"},
                        {"X", " ", " ", "X", "X", " ", "X", " ", "X", "X", " ", " ","X"}, //Sala BOSS final
                        {"X", " ", " ", " ", " ", " ", "X", " ", " ", " ", " ", " ","X"},
                        {"X", "X", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X","X"},
                        {".","X","X", " ", " ", "X", " ", "X"," "," ", "X","X"},
                        {".",".","X", "X", " ", "X", " ", "X"," ", "X","X"},
                        {".",".",".", "X", " ", "X", "O", "X"," ", "X"},
                        {".",".",".", "X", "X", "X", "E", "X","X", "X"},
                };
                this.InicioI = 11;
                this.InicioJ = 6;
                this.FimI = 0;
                this.FimJ = 6;
                break;
            default:
                matriz = new String[][] {
                        {"X", "X", "X", "X", "X", "X", "X"},
                        {"X", " ", "X", " ", "X", " ", "X"},
                        {"X", "X", "X", "X", "X", "X", "X"},
                        {"X", " ", "X", " ", "X", " ", "X"},
                        {"X", "X", "X", "X", "X", "X", "X"},
                        {"X", " ", "X", " ", "X", " ", "X"},
                        {"X", "X", "X", "X", "X", "X", "X"},
                };
                this.InicioI = 6;
                this.InicioJ = 1;
                this.FimI = 0;
                this.FimJ = 6;
        }

        for(int i = 0; i < matriz.length; i++) {
            ArrayList<String> linha = new ArrayList<>();
            for(int j = 0; j < matriz[i].length; j++) {
                String celula = matriz[i][j];
                linha.add(celula);
            }
            estruturaLabirinto.add(linha);
        }
    }

    public void gerar_Mapa(int Level) {
        //estruturaLabirinto.clear(); // limpa mapa anterior, se houver
        String[][] matriz;
        switch(Level){
            case 1:
                matriz = new String[][] {
                        {"X", "X", "X", "X", "X"},
                        {"X", "O", " ", " ", "X"},
                        {"X", " ", " ", " ", "X"},
                        {"X", " ", " ", "F", "X"},
                        {"X", "X", "X", "X", "X"},
                };
                this.InicioI = 1; this.InicioJ = 1;
                this.FimI = 3; this.FimJ = 3;
                break;
            case 2:
                matriz = new String[][] {
                        {"X", "X", "X", "X", "X", "X"},
                        {"X", "O", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", "F", "X"},
                        {"X", "X", "X", "X", "X", "X"},
                };
                this.InicioI = 1; this.InicioJ = 1;
                this.FimI = 4; this.FimJ = 4;
                break;
            case 3:
                matriz = new String[][] {
                        {"X", "X", "X", "X", "X", "X", "X"},
                        {"X", "O", " ", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", " ", " ", "X"},
                        {"X", " ", " ", " ", " ", "F", "X"},
                        {"X", "X", "X", "X", "X", "X", "X"},
                };
                this.InicioI = 1; this.InicioJ = 1;
                this.FimI = 5; this.FimJ = 5;
                break;
            default:
                System.out.println("Level inexistente. Será atribuido o level 1!");
                matriz = new String[][] {
                        {"X", "X", "X", "X", "X"},
                        {"X", "O", " ", " ", "X"},
                        {"X", " ", " ", " ", "X"},
                        {"X", " ", " ", "F", "X"},
                        {"X", "X", "X", "X", "X"},
                };
                this.InicioI = 1; this.InicioJ = 1;
                this.FimI = 3; this.FimJ = 3;
                break;
        }

        for (int i = 0; i < matriz.length; i++) {
            ArrayList<String> linha = new ArrayList<>();
            for (int j = 0; j < matriz[i].length; j++) {
                linha.add(matriz[i][j]);
            }
            estruturaLabirinto.add(linha);
        }
    }


    public void imprimirLabirinto(Aventureiro jogador) {
        //Verifica qual labirinto está ativo
        Labirinto labAtual = this.emSubLabirinto ? this.subLabirintoAtual : this;

        if (labAtual == null) {
            System.out.println("Erro: Labirinto atual não existe!");
            return;
        }

        System.out.println("=== Labirinto " + (emSubLabirinto ? "Secreto" : "Principal") + " ID: " + labAtual.getID() + " ===");

        for (int i = 0; i < labAtual.estruturaLabirinto.size(); i++) {
            for (int j = 0; j < labAtual.estruturaLabirinto.get(i).size(); j++) {
                if (jogador != null && i == jogador.getPosI() && j == jogador.getPosJ()) {
                    System.out.print("O ");
                } else {
                    String c = labAtual.estruturaLabirinto.get(i).get(j);
                    System.out.print((c == null || c.equals(".")) ? "  " : c + " ");
                }
            }
            System.out.println();
        }
    }

    private void adicionarTesouroAleatorio() {
        Random rand = new Random();
        List<int[]> posicoesValidas = new ArrayList<>();

        for (int i = 0; i < estruturaLabirinto.size(); i++) {
            for (int j = 0; j < estruturaLabirinto.get(i).size(); j++) {
                String celula = estruturaLabirinto.get(i).get(j);
                if (celula.equals(" ")) {
                    posicoesValidas.add(new int[]{i, j});
                }
            }
        }

        if (!posicoesValidas.isEmpty()) {
            int[] posicao = posicoesValidas.get(rand.nextInt(posicoesValidas.size()));
            estruturaLabirinto.get(posicao[0]).set(posicao[1], "T");
            listaTesouros.add(new Tesouros("TesouroAleatorio", posicao[0], posicao[1], "ouro"));
        }
    }

    public Tesouros getTesouroPorSimbolo(String simbolo) {
        for (Tesouros t : listaTesouros) {
            if (t.getNome().equals(simbolo)) {
                return t;
            }
        }
        return null;
    }

    public List<Tesouros> getListaTesouros() {
        return listaTesouros;
    }

    public void limparTerminal(){
        for(int i=0;i<15;++i){
            System.out.println();
        }
    }

    public void adicionarPerigo(ArrayList<String> listaPerigos) {
        this.listaPerigos.addAll(listaPerigos);
    }

    public void removerPerigo(ArrayList<String> listaPerigos) {
        this.listaPerigos.removeAll(listaPerigos);
    }
}

/* imprimirlabirinto antigo
public void imprimirLabirinto() {
    for (int i = 0; i < estruturaLabirinto.size(); i++) {
        for (int j = 0; j < estruturaLabirinto.get(i).size(); j++) {
            String c = estruturaLabirinto.get(i).get(j);
            if (c == null || c.equals(".")) // para espaços vazios ou células nulas
                System.out.print("  "); // espaço duplo pra alinhar melhor
            else
                System.out.print(c + " ");
        }
        System.out.println();
    }
}*/

/* get estrutura antigo
    public ArrayList<ArrayList<String>> getEstrutura(){
        return estruturaLabirinto;
    }*/