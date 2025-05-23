package LABIRINTO;

import java.util.*;
import java.util.Random;

public class Labirinto {
    private int ID;
    private boolean isMapaPrincipal;
    private int dificuldade; // 0 para labirintos, 1-3 para mapas
    private ArrayList<ArrayList<String>> estruturaLabirinto;
    private ArrayList<Tesouros> listaTesouros;
    private ArrayList<Perigo> listaPerigos;
    private int InicioI, InicioJ;
    private int FimI, FimJ;
    private Musica music;
    Random rand = new Random();

    public Labirinto(int ID, int dificuldade, boolean isMapaPrincipal) {
        this.ID = ID;
        this.dificuldade = dificuldade;
        this.isMapaPrincipal = isMapaPrincipal;
        this.estruturaLabirinto = new ArrayList<>();
        this.listaTesouros = new ArrayList<>();
        this.listaPerigos = new ArrayList<>();
        this.music = new Musica();

        if (isMapaPrincipal) {
            gerar_Mapa(dificuldade);  // Usando o seu metodo existente
        } else {
            gerar_labirinto(ID);  // Usando o seu metodo existente
            adicionarTesourosAleatorios(rand.nextInt(3) + 1);
            adicionarPerigosAleatorios(rand.nextInt(2) + 1);
        }
    }
    public void setMusica(int levelMusic){
        music.playLevel(levelMusic);
    }

    public void paraMusica(){
        music.stop();
    }

    public boolean isMapaPrincipal() {
        return this.isMapaPrincipal;
    }

    public boolean isEntradaLabirinto(int i, int j) {
        return isMapaPrincipal() && estruturaLabirinto.get(i).get(j).equals("L");
    }

    private void gerarMapa(int dificuldade) {
        gerar_Mapa(dificuldade); // Redireciona para o metodo existente
    }

    private void gerarLabirintoAleatorio() {
        // Implementação básica - você pode ajustar conforme necessário
        Random rand = new Random();
        int labirintoID = rand.nextInt(12) + 1; // Gera ID entre 1-12
        gerar_labirinto(labirintoID);
    }

    public void restaurarMapaPrincipal() {
        gerar_Mapa(this.dificuldade);
    }

    public int getInicioI() {
        return InicioI;
    }

    public int getInicioJ() {
        return InicioJ;
    }

    public int getFimI() {
        return FimI;
    }

    public int getFimJ() {
        return FimJ;
    }

    public int getID() {
        return this.ID;
    }

    public int getDificuldade() {
        return this.dificuldade;
    }

    public ArrayList<ArrayList<String>> getEstrutura(){
        return estruturaLabirinto;
    }

    public void gerar_labirinto(int ID){
        estruturaLabirinto.clear();
        String[][] matriz;

        switch(ID){
            case 0: 
                matriz = new String[][] {
                        {"X", "X", "X", "X", "X", "X", "X"},
                        {"X", " ", " ", " ", " ", " ", "X"},
                        {"X", " ", "X", "X", "X", " ", "X"},
                        {"X", " ", " ", " ", "X", " ", "X"},
                        {"X", "X", "X", " ", "X", " ", "X"},
                        {"X", "O", " ", " ", "X", " ", "X"},
                        {"X", "E", "X", "X", "X", "S", "X"},
                };
                this.InicioI = 5;
                this.InicioJ = 1;
                this.FimI = 5;
                this.FimJ = 6;
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
                this.InicioJ = 1;
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
                this.InicioJ = 18;
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
                        {"X", " ", " ", " ", " ", " ", "X"},
                        {"X", " ", "X", "X", "X", " ", "X"},
                        {"X", " ", " ", " ", "X", " ", "X"},
                        {"X", "X", "X", " ", "X", " ", "X"},
                        {"X", "O", " ", " ", "X", " ", "X"},
                        {"X", "E", "X", "X", "X", "S", "X"},
                };
                this.InicioI = 5;
                this.InicioJ = 1;
                this.FimI = 5;
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
        estruturaLabirinto.clear();
        String[][] matriz;
        switch(Level){
            case 1:
                matriz = new String[][] {
                        {"X", "X", "X", "X", "X"},
                        {"X", "O", "L", "L", "X"},  // L representa entrada para labirinto
                        {"X", "L", "L", "L", "X"},
                        {"X", "L", "L", "F", "X"},
                        {"X", "X", "X", "X", "X"},
                };
                this.InicioI = 1; this.InicioJ = 1;
                this.FimI = 3; this.FimJ = 3;
                break;
            case 2:
                matriz = new String[][] {
                        {"X", "X", "X", "X", "X", "X"},
                        {"X", "O", "L", "L", "L", "X"},
                        {"X", "L", "L", "L", "L", "X"},
                        {"X", "L", "L", "L", "L", "X"},
                        {"X", "L", "L", "L", "F", "X"},
                        {"X", "X", "X", "X", "X", "X"},
                };
                this.InicioI = 1; this.InicioJ = 1;
                this.FimI = 4; this.FimJ = 4;
                break;
            case 3:
                matriz = new String[][] {
                        {"X", "X", "X", "X", "X", "X", "X"},
                        {"X", "O", "L", "L", "L", "L", "X"},
                        {"X", "L", "L", "L", "L", "L", "X"},
                        {"X", "L", "L", "L", "L", "L", "X"},
                        {"X", "L", "L", "L", "L", "L", "X"},
                        {"X", "L", "L", "L", "L", "F", "X"},
                        {"X", "X", "X", "X", "X", "X", "X"},
                };
                this.InicioI = 1; this.InicioJ = 1;
                this.FimI = 5; this.FimJ = 5;
                break;
            default:
                System.out.println("Level inexistente. Será atribuido o level 1!");
                matriz = new String[][] {
                        {"X", "X", "X", "X", "X"},
                        {"X", "O", "L", "L", "X"},
                        {"X", "L", "L", "L", "X"},
                        {"X", "L", "L", "F", "X"},
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

    public void imprimirLabirinto() {
        long countO = estruturaLabirinto.stream()
                .flatMap(List::stream)
                .filter(c -> c.equals("O"))
                .count();

        if (countO > 1) {
            System.err.println("ERRO GRAVE: " + countO + " 'O' encontrados no mapa!");
            new Exception().printStackTrace(); // Mostra onde está ocorrendo o problema
        }

        for (int i = 0; i < estruturaLabirinto.size(); i++) {
            for (int j = 0; j < estruturaLabirinto.get(i).size(); j++) {
                String c = estruturaLabirinto.get(i).get(j);

                // Verifica se há um tesouro nesta posição
                boolean temTesouro = false;
                for (Tesouros tesouro : listaTesouros) {
                    if (tesouro.getLinha() == i && tesouro.getColuna() == j) {
                        temTesouro = true;
                        break;
                    }
                }

                if (temTesouro) {
                    System.out.print("T ");
                }
                // Mostra espaço vazio no lugar do L
                else if (c.equals("L")) {
                    System.out.print("  ");
                }
                // Mostra espaço duplo para células vazias
                else if (c.equals(" ")) {
                    System.out.print("  ");
                }
                else if (c.equals("X")) {
                    System.out.print("X ");
                }
                else if (c.equals(".")) {
                    System.out.print("  ");
                }
                // Mostra normalmente os outros caracteres
                else {
                    System.out.print(c + " ");
                }
            }
            System.out.println();
        }
    }

    public void adicionarTesourosAleatorios(int quantidade) {
        List<int[]> posicoesValidas = new ArrayList<>();

        // Encontra posições válidas (espaços vazios)
        for (int i = 0; i < estruturaLabirinto.size(); i++) {
            for (int j = 0; j < estruturaLabirinto.get(i).size(); j++) {
                String celula = estruturaLabirinto.get(i).get(j);
                if (celula.equals(" ")) {
                    posicoesValidas.add(new int[]{i, j});
                }
            }
        }

        // Adiciona os tesouros
        for (int n = 0; n < quantidade && !posicoesValidas.isEmpty(); n++) {
            int indice = rand.nextInt(posicoesValidas.size());
            int[] posicao = posicoesValidas.remove(indice);
            int i = posicao[0];
            int j = posicao[1];

            Tesouros tesouro = criarTesouroAleatorio(i, j);
            estruturaLabirinto.get(i).set(j, "T");
            listaTesouros.add(tesouro);
        }
    }

    private Tesouros criarTesouroAleatorio(int i, int j) {
        Random rand = new Random();

        if (rand.nextDouble() < 0.4) {
            ItemEquipavel original = Tesouros.getItemEquipavelAleatorio();
            return new ItemEquipavel(
                    original.getNome(),
                    i,
                    j,
                    original.getTipo(),
                    original.getBonusVida(),
                    original.getBonusArmadura(),
                    original.getBonusAtaque(),
                    original.getBonusVerdadeiro(),
                    original.getBonusVelocidade(),
                    original.getValor() // Mantém o valor original
            );
        } else {
            ItemComum original = Tesouros.getItemComumAleatorio();
            return new ItemComum(
                    original.getNome(),
                    i,
                    j,
                    original.getTipo(),
                    original.getValor() // Mantém o valor original
            );
        }
    }

    public List<Tesouros> getListaTesouros() {
        return listaTesouros;
    }

    public void limparTerminal(){
        for(int i=0;i<40;++i){
            System.out.println();
        }
    }

    public List<Perigo> getListaPerigos() {
        return listaPerigos;
    }

    public void adicionarPerigosAleatorios(int quantidade) {
        List<int[]> posicoesValidas = new ArrayList<>();

        // Encontra posições válidas (espaços vazios e que não têm tesouros)
        for (int i = 0; i < estruturaLabirinto.size(); i++) {
            for (int j = 0; j < estruturaLabirinto.get(i).size(); j++) {
                String celula = estruturaLabirinto.get(i).get(j);
                int finalI = i;
                int finalJ = j;
                boolean temTesouro = listaTesouros.stream()
                        .anyMatch(t -> t.getLinha() == finalI && t.getColuna() == finalJ);

                if (celula.equals(" ") && !temTesouro) {
                    posicoesValidas.add(new int[]{i, j});
                }
            }
        }

        // Adiciona os perigos
        for (int n = 0; n < quantidade && !posicoesValidas.isEmpty(); n++) {
            int indice = rand.nextInt(posicoesValidas.size());
            int[] posicao = posicoesValidas.remove(indice);
            int i = posicao[0];
            int j = posicao[1];

            Perigo perigo = criarPerigoAleatorio(i, j);
            listaPerigos.add(perigo);
        }
    }

    private Perigo criarPerigoAleatorio(int i, int j) {
        Perigo perigo = Perigo.criarPerigoAleatorio(rand);

        if (perigo instanceof Perigo.Armadilha) {
            Perigo.Armadilha armadilha = (Perigo.Armadilha) perigo;
            return new Perigo.Armadilha(
                    armadilha.getNome(),
                    armadilha.getHistoria(),
                    armadilha.getDescricao(),
                    armadilha.getIDP(),
                    armadilha.getDano(),
                    armadilha.getVelocidade(),
                    i,
                    j
            );
        } else {
            Perigo.Inimigo inimigo = (Perigo.Inimigo) perigo;
            return new Perigo.Inimigo(
                    inimigo.getNome(),
                    inimigo.getHistoria(),
                    inimigo.getDescricao(),
                    inimigo.getIDP(),
                    inimigo.getDano(),
                    inimigo.getVelocidade(),
                    inimigo.getArmadura(),
                    inimigo.getVida(),
                    inimigo.getDanoVerdadeiro(),
                    i,  // Linha
                    j   // Coluna
            );
        }
    }
}