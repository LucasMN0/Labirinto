package LABIRINTO;

import java.util.*;

class   Aventureiro {
    private String nome;
    private int posJ;
    private int posI;
    private int ultimaPosicaoMapaI;
    private int ultimaPosicaoMapaJ;
    private static final Scanner sc = new Scanner(System.in);
    private ArrayList<String> tesourosEncontrados;
    private List<Tesouros> listaTesouros;
    private List<ItemEquipavel> equipamentos = new ArrayList<>();
    private Labirinto labirintoAtual;
    private Labirinto mapaPrincipal;

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
        this.listaTesouros = labirinto.getListaTesouros();
        this.mapaPrincipal = labirinto;
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
        System.out.println("Vida: " + vida + "/" + getVidaMaximaTotal());
        System.out.println("Dano de ataque: " + getDanoAtaqueTotal());
        System.out.println("Dano verdadeiro: " + getDanoVerdadeiroTotal());
        System.out.println("Armadura: " + (int)(getArmaduraTotal() * 100) + "%");
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

    public void mostrarMenu() {
        while (true) {
            System.out.println("\n\n=== MENU ===");
            System.out.println("1 - Mostrar Status");
            System.out.println("2 - Mostrar Mapa");
            System.out.println("3 - Voltar ao Jogo");
            System.out.print("Escolha uma opção: ");
            String opcao = sc.nextLine();

            switch(opcao) {
                case "1":
                    mostrarStatus();
                    break;
                case "2":
                    labirintoAtual.imprimirLabirinto();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public void setLabirintoAtual(Labirinto novoLabirinto) {
        if (novoLabirinto == null) {
            throw new IllegalArgumentException("Labirinto não pode ser null");
        }
        this.labirintoAtual = novoLabirinto;
        this.listaTesouros = novoLabirinto.getListaTesouros();
    }

    public void setPosicao(int i, int j) {
        // Verifica se as coordenadas são válidas
        if (i < 0 || i >= labirintoAtual.getEstrutura().size() ||
                j < 0 || j >= labirintoAtual.getEstrutura().get(0).size()) {
            System.out.println("Posição inválida! (" + i + "," + j + ")");
            return;
        }

        // Limpa a posição anterior se estiver dentro dos limites
        if (posI >= 0 && posI < labirintoAtual.getEstrutura().size() &&
                posJ >= 0 && posJ < labirintoAtual.getEstrutura().get(0).size()) {
            labirintoAtual.getEstrutura().get(posI).set(posJ, " ");
        }

        // Atualiza a nova posição
        this.posI = i;
        this.posJ = j;

        // Marca a nova posição no labirinto
        labirintoAtual.getEstrutura().get(i).set(j, "O");
    }

    public void setUltimaPosicaoMapa(int i, int j) {
        this.ultimaPosicaoMapaI = i;
        this.ultimaPosicaoMapaJ = j;
    }

    public int getUltimaPosicaoMapaI() {
        return ultimaPosicaoMapaI;
    }

    public int getUltimaPosicaoMapaJ() {
        return ultimaPosicaoMapaJ;
    }

    public Labirinto getLabirintoAtual() {
        return this.labirintoAtual;
    }

    public List<Tesouros> getListaTesouros() {
        return listaTesouros;
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

        // Verifica limites
        if (novoI < 0 || novoI >= labirintoAtual.getEstrutura().size() ||
                novoJ < 0 || novoJ >= labirintoAtual.getEstrutura().get(novoI).size()) {
            return false;
        }

        // Verifica se pode mover para a célula
        String celula = labirintoAtual.getEstrutura().get(novoI).get(novoJ);
        if (!celula.equals(" ") && !celula.equals("F") &&
                !celula.equals("S") && !celula.equals("T") &&
                !celula.equals("O") && !celula.equals("L")) {
            return false;
        }

        // Atualiza posição
        labirintoAtual.getEstrutura().get(posI).set(posJ, " ");
        posI = novoI;
        posJ = novoJ;

        verificarTesouro();

        // Verifica se está em uma entrada de labirinto (L)
        if (celula.equals("L") && labirintoAtual.isMapaPrincipal()) {
            System.out.println("\nVocê encontrou uma entrada para um labirinto!");

            String resposta;
            do {
                System.out.print("Deseja entrar? (s/n): ");
                resposta = sc.nextLine().trim().toLowerCase();
            } while (!resposta.equals("s") && !resposta.equals("n"));

            if (resposta.equalsIgnoreCase("s")) {
                entrarNoLabirinto();
                return true;
            } else {
                // Se não quiser entrar, volta para a posição anterior
                labirintoAtual.getEstrutura().get(posI).set(posJ, "L");
                posI = posI; // Mantém na posição atual (que é a do L)
                posJ = posJ;
                labirintoAtual.getEstrutura().get(posI).set(posJ, "O");
                return true;
            }
        }

        // Verifica se está na saída do labirinto (S)
        if (celula.equals("S") && !labirintoAtual.isMapaPrincipal()) {
            sairDoLabirinto();
            return true;
        }

        labirintoAtual.getEstrutura().get(posI).set(posJ, "O");
        return true;
    }

    private void entrarNoLabirinto() {
        System.out.println("\n--- ENTRANDO NO LABIRINTO ---");

        // Guarda a posição atual no mapa principal
        setUltimaPosicaoMapa(posI, posJ);

        // Gera um labirinto aleatório
        int labirintoID = new Random().nextInt(12) + 1;
        Labirinto labirintoAleatorio = new Labirinto(labirintoID, 0, false);
        labirintoAleatorio.gerar_labirinto(labirintoID);

        // Entra no labirinto
        setLabirintoAtual(labirintoAleatorio);
        setPosicao(labirintoAleatorio.getInicioI(), labirintoAleatorio.getInicioJ());

        System.out.println("Você entrou no Labirinto " + labirintoID + "!");
    }

    private void sairDoLabirinto() {
        System.out.println("\n--- SAINDO DO LABIRINTO ---");

        // Verifica se as últimas posições no mapa principal são válidas
        int novaPosI = getUltimaPosicaoMapaI();
        int novaPosJ = getUltimaPosicaoMapaJ();

        // Se as posições forem inválidas, usa a posição inicial do mapa principal
        if (novaPosI < 0 || novaPosI >= mapaPrincipal.getEstrutura().size() ||
                novaPosJ < 0 || novaPosJ >= mapaPrincipal.getEstrutura().get(novaPosI).size()) {
            novaPosI = mapaPrincipal.getInicioI();
            novaPosJ = mapaPrincipal.getInicioJ();
            System.out.println("Posição inválida, retornando ao início do mapa principal");
        }

        setLabirintoAtual(mapaPrincipal);
        setPosicao(novaPosI, novaPosJ);
        System.out.println("Você voltou para o mapa principal!");
    }

    private void verificarTesouro() {
        // Verifica se há um tesouro na posição atual
        for (Tesouros tesouro : new ArrayList<>(labirintoAtual.getListaTesouros())) {
            if (tesouro.getLinha() == posI && tesouro.getColuna() == posJ) {
                System.out.println("\n=== VOCÊ ENCONTROU UM TESOURO ===");
                System.out.println("Tipo: " + tesouro.getTipo());
                System.out.println("Nome: " + tesouro.getNome());

                if (tesouro instanceof ItemEquipavel) {
                    ItemEquipavel item = (ItemEquipavel) tesouro;
                    System.out.println("\n[ITEM EQUIPÁVEL]");
                    System.out.println("Vida: +" + item.getBonusVida());
                    System.out.println("Ataque: +" + item.getBonusAtaque());
                    System.out.println("Armadura: +" + (int)(item.getBonusArmadura() * 100) + "%");
                    System.out.println("Dano Verdadeiro: +" + item.getBonusVerdadeiro());

                    System.out.print("\nDeseja equipar este item? (s/n): ");
                    String resposta = sc.nextLine();
                    if (resposta.equalsIgnoreCase("s")) {
                        equipar(item);
                    }
                } else if (tesouro instanceof ItemComum) {
                    ItemComum item = (ItemComum) tesouro;
                    System.out.println("\n[ITEM COMUM]");
                    System.out.println("Valor: " + item.getValor() + " moedas");
                } else {
                    System.out.println("\n[TESOURO]");
                    System.out.println("Você encontrou um tesouro básico!");
                }

                tesourosEncontrados.add(tesouro.getNome());
                labirintoAtual.getListaTesouros().remove(tesouro);
                return; // Sai do loop após encontrar o tesouro
            }
        }
    }
}

/* metodos antigos de sair e pode mover
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
 */