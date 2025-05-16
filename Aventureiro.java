package LABIRINTO;

import java.util.*;

class   Aventureiro {
    private String nome;
    private int posJ;
    private int posI;
    private ArrayList<String> tesourosEncontrados;
    private List<Tesouros> listaTesouros;
    private List<ItemEquipavel> equipamentos = new ArrayList<>();
    private Labirinto labirintoAtual;

    // Novos atributos de combate
    private int vida;              // Vida atual
    private int vidaMaxima;        // Vida máxima
    private int danoAtaque;        // Dano padrão
    private int danoVerdadeiro;    // Dano que ignora armadura
    private double armadura;       // Porcentagem de redução de dano (ex: 0.2 = 20%)

    public Aventureiro(String nome, ArrayList<String> tesourosEncontrados, Labirinto labirinto, int i, int j) {
        this.nome = nome;
        this.tesourosEncontrados = tesourosEncontrados;
        this.labirintoAtual = labirinto;
        this.posI = i;
        this.posJ = j;
        this.equipamentos = new ArrayList<>();

        // Atributos iniciais
        this.vidaMaxima = 100;
        this.vida = vidaMaxima;
        this.danoAtaque = 20;
        this.danoVerdadeiro = 5;
        this.armadura = 0.2; // 20% de redução de dano
    }

    public int getPosJ() { return posJ; }
    public int getPosI() { return posI; }

    public int getVida() { return vida; }
    public int getDanoAtaque() { return danoAtaque; }
    public int getDanoVerdadeiro() { return danoVerdadeiro; }
    public double getArmadura() { return armadura; }

    public void receberDano(int danoRecebido) {
        int danoComReducao = (int)(danoRecebido * (1 - armadura));
        vida -= danoComReducao;
        if (vida < 0) vida = 0;
        System.out.println(nome + " recebeu " + danoComReducao + " de dano (reduzido por armadura). Vida atual: " + vida);
    }

    public void receberDanoVerdadeiro(int dano) {
        vida -= dano;
        if (vida < 0) vida = 0;
        System.out.println(nome + " recebeu " + dano + " de dano verdadeiro. Vida atual: " + vida);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void mostrarStatus() {
        System.out.println("===== STATUS DE " + nome + " =====");
        System.out.println("Vida: " + vida + "/" + vidaMaxima);
        System.out.println("Dano de ataque: " + danoAtaque);
        System.out.println("Dano verdadeiro: " + danoVerdadeiro);
        System.out.println("Armadura: " + (int)(armadura * 100) + "%");
        System.out.println("Tesouros encontrados: " + tesourosEncontrados);
        System.out.println("===================================");
    }

    public void equipar(ItemEquipavel item) {
        equipamentos.add(item);
        System.out.println(nome + " equipou: " + item.getNome());
    }

    public void desequipar(ItemEquipavel item) {
        equipamentos.remove(item);
        System.out.println(nome + " removeu: " + item.getNome());
    }

    public int getVidaMaximaTotal() {
        int total = vidaMaxima;
        for (ItemEquipavel item : equipamentos) {
            total += item.getBonusVida();
        }
        return total;
    }

    public double getArmaduraTotal() {
        double total = armadura;
        for (ItemEquipavel item : equipamentos) {
            total += item.getBonusArmadura();
        }
        return total;
    }

    public int getDanoAtaqueTotal() {
        int total = danoAtaque;
        for (ItemEquipavel item : equipamentos) {
            total += item.getBonusAtaque();
        }
        return total;
    }

    public int getDanoVerdadeiroTotal() {
        int total = danoVerdadeiro;
        for (ItemEquipavel item : equipamentos) {
            total += item.getBonusVerdadeiro();
        }
        return total;
    }

    public List<Tesouros> getListaTesouros() {
        return listaTesouros;
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

public boolean mover(char direcao) {
    char direcaoUpper = Character.toUpperCase(direcao);

    if (direcaoUpper != 'W' && direcaoUpper != 'A' && direcaoUpper != 'S' && direcaoUpper != 'D') {
        System.out.println("Direção inválida! Use W A S D");
        return false;
    }

    int novoI = posI;
    int novoJ = posJ;

    switch (direcaoUpper) {
        case 'W': novoI--; break;
        case 'S': novoI++; break;
        case 'A': novoJ--; break;
        case 'D': novoJ++; break;
    }

    if (labirintoAtual.getEstrutura() == null) {
        System.out.println("Erro: Estrutura do labirinto não existe!");
        return false;
    }

    // Verificação de limites com null safety
    if (novoI < 0 || novoI >= labirintoAtual.getEstrutura().size() ||
        novoJ < 0 || novoJ >= labirintoAtual.getEstrutura().get(novoI).size()) {
        return false;
    }

    String celulaDestino = labirintoAtual.getEstrutura().get(novoI).get(novoJ);

    // Saída do sub-labirinto
    if (labirintoAtual.isEmSubLabirinto() && celulaDestino.equals("S")) {
        System.out.println("Você encontrou a saída do labirinto secreto!");

        Labirinto labPrincipal = encontrarLabirintoPrincipal(labirintoAtual);

        if (labPrincipal != null && labPrincipal.getPosicaoEntradaSubLabirinto() != null) {
            int[] posEntrada = labPrincipal.getPosicaoEntradaSubLabirinto();
            this.labirintoAtual = labPrincipal;
            this.posI = posEntrada[0];
            this.posJ = posEntrada[1];
        } else {
            this.posI = 0;
            this.posJ = 0;
            System.out.println("Aviso: Posição de retorno padrão utilizada");
        }

        labPrincipal.sairDoSubLabirinto();
        return true;
    }

    // Verificação para entrada em sub-labirinto (APENAS no lobby principal)
    if (!labirintoAtual.isEmSubLabirinto() && labirintoAtual.deveGerarSubLabirinto() && celulaDestino.equals(" ")) {
        System.out.print("Você encontrou uma entrada secreta! Deseja entrar? (S/N): ");
        Scanner sc = new Scanner(System.in);
        String resposta = sc.nextLine().toUpperCase();

        if (resposta.equals("S")) {
            labirintoAtual.entrarSubLabirinto(posI, posJ);
            this.labirintoAtual = labirintoAtual.getSubLabirintoAtual();
            this.posI = this.labirintoAtual.getInicioI();
            this.posJ = this.labirintoAtual.getInicioJ();
            return true;
        }
        return false;
    }

    // Movimento normal
    if (celulaDestino.equals(" ") || celulaDestino.equals("T") ||
        celulaDestino.equals("°") || celulaDestino.equals("S")) {

        // Atualiza posição anterior
        String marcadorAnterior = labirintoAtual.isEmSubLabirinto() ? " " : "°";
        labirintoAtual.getEstrutura().get(posI).set(posJ, marcadorAnterior);

        // Atualiza nova posição
        posI = novoI;
        posJ = novoJ;
        labirintoAtual.getEstrutura().get(posI).set(posJ, "O");

        verificarTesouro();
        return true;
    }
    return false;
}


    public boolean sair() {
        // Verificação completa de null safety
        if (labirintoAtual == null) {
            return false;
        }

        int currentFimI = labirintoAtual.getFimI();
        int currentFimJ = labirintoAtual.getFimJ();

        return posI == currentFimI && posJ == currentFimJ;
    }

    private boolean podeMover(int j, int i) {
        // Verifica limites
        if (i < 0 || i >= labirintoAtual.getEstrutura().size() ||
                j < 0 || j >= labirintoAtual.getEstrutura().get(i).size()) {
            return false;
        }

        String celula = labirintoAtual.getEstrutura().get(i).get(j);
        return celula.equals(" ") || celula.equals("S") || celula.equals("T");
    }


    private void verificarTesouro() {
        for (Tesouros tesouro : labirintoAtual.getListaTesouros()) {
            if (tesouro.getLinha() == posI && tesouro.getColuna() == posJ) {
                System.out.println("Você encontrou: " + tesouro.getNome());

                if (tesouro instanceof ItemEquipavel) {
                    ItemEquipavel item = (ItemEquipavel) tesouro;
                    System.out.println("Este item é equipável! Deseja equipar? (s/n)");
                    Scanner sc = new Scanner(System.in);
                    String resposta = sc.nextLine();
                    if (resposta.equalsIgnoreCase("s")) {
                        equipar(item);
                    }
                }
                } else if (tesouro instanceof ItemComum) {
                    System.out.println("Hmm... parece só um " + tesouro.getNome() + ". Nada útil.");
                }

                tesourosEncontrados.add(tesouro.getNome());

                // Limpa o tesouro do mapa
                labirintoAtual.getEstrutura().get(posI).set(posJ, "O");

                return;
            }
        }
}

// //metodo antigo de mover
//     public boolean mover(char direcao) {
//         char direcaoUpper = Character.toUpperCase(direcao);

//         if (direcaoUpper != 'W' && direcaoUpper != 'A' && direcaoUpper != 'S' && direcaoUpper != 'D') {
//             System.out.println("Direção inválida! Use W A S D");
//             return false;
//         }

//         int novoI = posI;
//         int novoJ = posJ;

//         switch (direcaoUpper) {
//             case 'W': novoI--; break;
//             case 'S': novoI++; break;
//             case 'A': novoJ--; break;
//             case 'D': novoJ++; break;
//         }

//         if (novoI < 0 || novoI >= labirintoAtual.getEstrutura().size()) {
//             return false;
//         }

//         if (podeMover(novoJ, novoI)) {
//             labirintoAtual.getEstrutura().get(posI).set(posJ, " ");
//             posI = novoI;
//             posJ = novoJ;
//             labirintoAtual.getEstrutura().get(posI).set(posJ, "O");
//             verificarTesouro();
//             return true;
//         }

//         return false;
//     }
// }