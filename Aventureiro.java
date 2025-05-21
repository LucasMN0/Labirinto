package LABIRINTO;

import java.util.*;

class   Aventureiro {
    private String nome;
    private int posJ;
    private int posI;
    private int ultimaPosicaoMapaI;
    private int ultimaPosicaoMapaJ;
    private static final Scanner sc = new Scanner(System.in);
    private List<Tesouros> tesourosEncontrados =  new ArrayList<>();
    private List<Tesouros> listaTesouros;
    private List<ItemEquipavel> equipamentos;
    private Labirinto labirintoAtual;
    private Labirinto mapaPrincipal;

    // Novos atributos de combate
    private int vida;              // Vida atual
    private int vidaMaxima;        // Vida máxima
    private int danoAtaque;        // Dano padrão
    private int danoVerdadeiro;    // Dano que ignora armadura
    private int moedas;
    private double armadura;       // Porcentagem de redução de dano (ex: 0.2 = 20%)
    private boolean podeComprarNaLoja = true;

    public Aventureiro(String nome, List<Tesouros> tesourosEncontrados, Labirinto labirinto, int i, int j) {
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
        this.moedas = tesourosEncontrados.size() * 50;
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
        System.out.println("Tesouros encontrados:");
        for (Tesouros t : tesourosEncontrados) {
            System.out.println("- " + t.getNome() + " (" + t.getTipo() + ")");
        }
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

    public int getMoedas() {
        return moedas;
    }

    public void adicionarMoedas(int quantidade) {
        if (quantidade > 0) {
            moedas += quantidade;
        }
    }

    public boolean removerMoedas(int quantidade) {
        if (quantidade > 0 && moedas >= quantidade) {
            moedas -= quantidade;
            return true;
        }
        return false;
    }

    public boolean podeComprarNaLoja() {
        return podeComprarNaLoja;
    }

    public void setPodeComprarNaLoja(boolean podeComprar) {
        this.podeComprarNaLoja = podeComprar;
    }

    public int calcularMusica(){
        int x = mapaPrincipal.getInicioI() + mapaPrincipal.getInicioJ();
        int levelMusica;
        switch(mapaPrincipal.getDificuldade()){
                case 1: 
                    if(x<=4){
                        levelMusica = 1;
                    } else if(4<x && x<6){
                        levelMusica =2;
                    } else{
                        levelMusica=3;
                    }
                    break;
                case 2:
                    if(x<=5){
                        levelMusica = 1;
                    } else if(5<x && x<8){
                        levelMusica =2;
                    } else{
                        levelMusica=3;
                    }
                    break;
                case 3:
                    if(x<=7){
                        levelMusica = 1;
                    } else if(7<x && x<10){
                        levelMusica =2;
                    } else{
                        levelMusica=3;
                    }
                    break;
                default:
                    levelMusica=1;
        }
        return levelMusica;
    }

    public void mostrarMenu() {
        Loja loja = new Loja(this); // Cria a loja

        while (true) {
            System.out.println("\n\n=== MENU ===");
            System.out.println("1 - Mostrar Status");
            System.out.println("2 - Mostrar Mapa");
            System.out.println("3 - Acessar Loja");
            System.out.println("4 - Voltar ao Jogo");
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
                    loja.mostrarMenuLoja();
                    break;
                case "4":
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
        // Garante que a posição anterior seja limpa
        if (posI >= 0 && posJ >= 0 &&
                posI < labirintoAtual.getEstrutura().size() &&
                posJ < labirintoAtual.getEstrutura().get(0).size()) {

            String celulaAtual = labirintoAtual.getEstrutura().get(posI).get(posJ);
            if (celulaAtual.equals("O")) {
                labirintoAtual.getEstrutura().get(posI).set(posJ, " ");
            }
        }

        // Atualiza as coordenadas
        this.posI = i;
        this.posJ = j;

        // Marca a nova posição se for válida
        if (i >= 0 && j >= 0 &&
                i < labirintoAtual.getEstrutura().size() &&
                j < labirintoAtual.getEstrutura().get(0).size()) {

            String celula = labirintoAtual.getEstrutura().get(i).get(j);
            if (celula.equals(" ") || celula.equals("L") || celula.equals("T")) {
                labirintoAtual.getEstrutura().get(i).set(j, "O");
            }
        }
    }

    private void limparPosicaoAnterior() {
        // Limpa todos os "O" que não deveriam existir
        for (int i = 0; i < labirintoAtual.getEstrutura().size(); i++) {
            for (int j = 0; j < labirintoAtual.getEstrutura().get(i).size(); j++) {
                String celula = labirintoAtual.getEstrutura().get(i).get(j);
                if (celula.equals("O") && (i != posI || j != posJ)) {
                    labirintoAtual.getEstrutura().get(i).set(j, " ");
                }
            }
        }
    }

    public void setUltimaPosicaoMapa(int i, int j) {
        this.ultimaPosicaoMapaI = i;
        this.ultimaPosicaoMapaJ = j;
    }

    public List<Tesouros> getTesourosEncontrados() {
        return tesourosEncontrados;
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

        limparPosicaoAnterior();

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
        if (celula.equals("F") && labirintoAtual.isMapaPrincipal()) {
            System.out.println("\nVocê encontrou uma entrada para a sala do BOSS!");

            String resposta;
            do {
                System.out.print("Deseja entrar? (s/n): ");
                resposta = sc.nextLine().trim().toLowerCase();
            } while (!resposta.equals("s") && !resposta.equals("n"));

            if (resposta.equalsIgnoreCase("s")) {
                entrarNoBOSS();
                return true;
            } else {
                // Se não quiser entrar, volta para a posição anterior
                labirintoAtual.getEstrutura().get(posI).set(posJ, "F");
                posI = posI; // Mantém na posição atual (que é a do F)
                posJ = posJ;
                labirintoAtual.getEstrutura().get(posI).set(posJ, "O");
                return true;
            }
        }

        // Verifica se está na saída do labirinto (S)
        if (celula.equals("S") && !labirintoAtual.isMapaPrincipal()) {
            sairDoLabirinto();
            labirintoAtual.limparTerminal();
            return true;
        }

        if (celula.equals("S") && labirintoAtual.isMapaPrincipal()) {
            sairDoLabirinto();
            labirintoAtual.limparTerminal();
            return true;
        }

        labirintoAtual.getEstrutura().get(posI).set(posJ, "O");
        setPosicao(novoI, novoJ);
        return true;
    }

    private void entrarNoLabirinto() {
        System.out.println("\n--- ENTRANDO NO LABIRINTO ---");

        // Guarda a posição CORRETA (i=linha, j=coluna)
        setUltimaPosicaoMapa(posI, posJ);
        // Restante do metodo permanece igual...
        int labirintoID = new Random().nextInt(12) + 1;
        Labirinto labirintoAleatorio = new Labirinto(labirintoID, 0, false);
        labirintoAleatorio.gerar_labirinto(labirintoID);

        setLabirintoAtual(labirintoAleatorio);
        setPosicao(labirintoAleatorio.getInicioI(), labirintoAleatorio.getInicioJ());
        System.out.println("Você entrou no Labirinto " + labirintoID + "!");
        int x = calcularMusica();
        mapaPrincipal.setMusica(x);
    }

    private void entrarNoBOSS() {
        System.out.println("\n--- ENTRANDO NA SALA DO BOSS ---");
        // Guarda a posição CORRETA (i=linha, j=coluna)
        setUltimaPosicaoMapa(posI, posJ);
        // Restante do metodo permanece igual...
        int labirintoID = 11;
        Labirinto labirintoAleatorio = new Labirinto(labirintoID, 0, false);
        labirintoAleatorio.gerar_labirinto(labirintoID);

        setLabirintoAtual(labirintoAleatorio);
        setPosicao(labirintoAleatorio.getInicioI(), labirintoAleatorio.getInicioJ());

        System.out.println("Você entrou na sala do BOSS !");
        mapaPrincipal.setMusica(3);
    }

    private void sairDoLabirinto() {
        System.out.println("\n--- SAINDO DO LABIRINTO ---");
        mapaPrincipal.paraMusica();

        // 1. Restaura o mapa principal
        mapaPrincipal.gerar_Mapa(mapaPrincipal.getDificuldade());

        // 2. Obtém a posição CORRETA (sem inverter)
        int novaPosI = getUltimaPosicaoMapaI(); // LINHA (i)
        int novaPosJ = getUltimaPosicaoMapaJ(); // COLUNA (j)

        // 3. Verificação de limites
        if (novaPosI < 0 || novaPosI >= mapaPrincipal.getEstrutura().size() ||
                novaPosJ < 0 || novaPosJ >= mapaPrincipal.getEstrutura().get(0).size()) {
            novaPosI = mapaPrincipal.getInicioI();
            novaPosJ = mapaPrincipal.getInicioJ();
        }

        // 4. Atualiza posição SEM inverter
        setLabirintoAtual(mapaPrincipal);
        limparTodosOsOJogador();
        setPosicao(novaPosI, novaPosJ); // CORRETO: mantém a ordem i, j
        setPodeComprarNaLoja(true);
        System.out.println("Você voltou para o mapa principal!");

    }

    private void limparTodosOsOJogador() {
        for (int i = 0; i < labirintoAtual.getEstrutura().size(); i++) {
            for (int j = 0; j < labirintoAtual.getEstrutura().get(i).size(); j++) {
                if (labirintoAtual.getEstrutura().get(i).get(j).equals("O")) {
                    labirintoAtual.getEstrutura().get(i).set(j, " ");
                }
            }
        }
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
                    System.out.println("Valor: " + item.getValor() + " moedas");
                    if(item.getTipo().equals("Consumível")){
                        System.out.print("Esse item pode recuperar sua vida!\n");
                    }
                }

                tesourosEncontrados.add(tesouro);
                labirintoAtual.getListaTesouros().remove(tesouro);
                return; // Sai do loop após encontrar o tesouro
            }
        }
    }
}