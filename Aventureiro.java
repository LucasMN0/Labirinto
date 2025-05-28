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
    private List<ItemComum> consumiveis = new ArrayList<>();
    private Labirinto labirintoAtual;
    private Labirinto mapaPrincipal;
    private ArrayList<ArrayList<String>> copiaMapaPrincipal;
    private Monstruario monstruario = Monstruario.getInstance();

    // Novos atributos de combate
    private int vida;              // Vida atual
    private int vidaMaxima;        // Vida máxima
    private int danoAtaque;        // Dano padrão
    private int danoVerdadeiro;    // Dano que ignora armadura
    private int moedas;
    private int velocidade;
    private double armadura;       // Porcentagem de redução de dano (ex: 0.2 = 20%)
    private boolean podeComprarNaLoja = true;

    public Aventureiro(String nome, List<Tesouros> tesourosEncontrados, Labirinto labirinto, int i, int j, KitClasse kitClasse) {
        this.nome = nome;
        this.tesourosEncontrados = tesourosEncontrados;
        this.labirintoAtual = labirinto;
        this.listaTesouros = labirinto.getListaTesouros();
        this.mapaPrincipal = labirinto;
        this.monstruario = Monstruario.getInstance();
        this.posI = i;
        this.posJ = j;
        this.equipamentos = new ArrayList<>();

        // Atributos iniciais
        this.vidaMaxima = 100;
        this.vida = vidaMaxima;
        this.danoAtaque = 0;
        this.danoVerdadeiro = 0;
        this.moedas = tesourosEncontrados.size() * 50;
        this.armadura = 0.0;
        this.velocidade = 0;

        if (kitClasse != null) {
            this.vidaMaxima += kitClasse.getBonusVida();
            this.vida = vidaMaxima;
            this.danoAtaque += kitClasse.getBonusAtaque();
            this.danoVerdadeiro += kitClasse.getBonusDanoVerdadeiro();
            this.armadura += kitClasse.getBonusArmadura();
            this.velocidade += kitClasse.getBonusVelocidade();

            ItemEquipavel kitItem = new ItemEquipavel(
                    "Kit de " + kitClasse.getNome(),
                    0, 0, "Classe",
                    kitClasse.getBonusVida(),
                    kitClasse.getBonusArmadura(),
                    kitClasse.getBonusAtaque(),
                    kitClasse.getBonusDanoVerdadeiro(),
                    kitClasse.getBonusVelocidade(),
                    0
            );
            this.equipamentos.add(kitItem);
        }
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
        System.out.println("\n" + nome + " recebeu " + danoComReducao + " de dano (reduzido pela armadura). Vida atual: " + vida);

        if (vida <= 0) {
            System.out.println("\nVocê foi derrotado!");
        }
    }

    public void receberDanoVerdadeiro(int dano) {
        vida -= dano;
        if (vida < 0) vida = 0;
        System.out.println(nome + " recebeu " + dano + " de dano verdadeiro. Vida atual: " + vida);

        if (vida <= 0) {
            System.out.println("\nVocê foi derrotado!");
        }
    }

    private static void esperar(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("O atraso foi interrompido.");
        }
    }

    public void mostrarStatus() {
        System.out.println("\n===== STATUS DE " + nome + " =====");
        System.out.println("Vida: " + vida + "/" + getVidaMaximaTotal());
        System.out.println("Dano de ataque: " + getDanoAtaqueTotal());
        System.out.println("Dano verdadeiro: " + getDanoVerdadeiroTotal());
        System.out.println("Armadura: " + (int)(getArmaduraTotal() * 100) + "%");
        System.out.println("Velocidade: " + getVelocidade());
        System.out.println("Tesouros encontrados: " + tesourosEncontrados.size() + " itens");
        System.out.println("===================================");
    }

    public void equipar(ItemEquipavel item) {
        equipamentos.add(item);
        System.out.println(nome + " equipou: " + item.getNome());
    }
    public void desequiparItem() {
        if (equipamentos.isEmpty()) {
            System.out.println("Você não tem itens equipados!");
            return;
        }

        System.out.println("\n=== ITENS EQUIPADOS ===");
        for (int i = 0; i < equipamentos.size(); i++) {
            ItemEquipavel item = equipamentos.get(i);
            System.out.println((i+1) + " - " + item.getNome());
        }

        System.out.print("\nEscolha o item para desequipar (0 para cancelar): ");
        try {
            int escolha = Integer.parseInt(sc.nextLine()) - 1;
            if (escolha == -1) {
                return;
            }
            if (escolha >= 0 && escolha < equipamentos.size()) {
                ItemEquipavel item = equipamentos.get(escolha);
                System.out.println("\nAVISO: Ao desequipar '" + item.getNome() + "', você perderá este item permanentemente!");
                System.out.print("Tem certeza que deseja desequipar? (s/n): ");
                String confirmacao = sc.nextLine().toLowerCase();

                if (confirmacao.equals("s")) {
                    equipamentos.remove(escolha);
                    System.out.println("Você desequipou e perdeu permanentemente: " + item.getNome());
                } else {
                    System.out.println("Operação cancelada.");
                }
            } else {
                System.out.println("Opção inválida!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida!");
        }
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    public String getNome() {
        return nome;
    }
    public int getVidaMaximaTotal() {
        int total = vidaMaxima; // Já inclui o bônus da classe
        for (ItemEquipavel item : equipamentos) {
            if (!item.getNome().startsWith("Kit de ")) {
                total += item.getBonusVida();
            }
        }
        return total;
    }
    public int getDanoAtaqueTotal() {
        int total = danoAtaque;
        for (ItemEquipavel item : equipamentos) {
            if (!item.getNome().startsWith("Kit de ")) {
                total += item.getBonusAtaque();
            }
        }
        return total;
    }
    public int getDanoVerdadeiroTotal() {
        int total = danoVerdadeiro; // Já inclui o bônus da classe
        for (ItemEquipavel item : equipamentos) {
            if (!item.getNome().startsWith("Kit de ")) {
                total += item.getBonusVerdadeiro();
            }
        }
        return total;
    }
    public int getVelocidade() {
        int total = velocidade; // Já inclui o bônus da classe
        for (ItemEquipavel item : equipamentos) {
            if (!item.getNome().startsWith("Kit de ")) {
                total += item.getBonusVelocidade();
            }
        }
        return total;
    }
    public int getMoedas() {
        return moedas;
    }
    public double getArmaduraTotal() {
        double total = armadura;
        for (ItemEquipavel item : equipamentos) {
            if (!item.getNome().startsWith("Kit de ")) {
                total += item.getBonusArmadura();
            }
        }
        return total;
    }
    public List<ItemComum> getConsumiveis() {
        return consumiveis;
    }
    public Monstruario getMonstruario() {
        return monstruario;
    }
    public int getVelocidadeTotal() {
        int total = velocidade;
        for (ItemEquipavel item : equipamentos) {
            total += item.getBonusVelocidade();
        }
        return total;
    }
    public void setMonstruario(Monstruario monstruario) {
        this.monstruario = monstruario;
    }
    public void setVida(int vida) {
        this.vida = Math.min(Math.max(0, vida), getVidaMaximaTotal());
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
    public boolean usarConsumivel(int index) {
        if (index >= 0 && index < consumiveis.size()) {
            ItemComum consumivel = consumiveis.get(index);
            if (consumivel.getTipo().equals("Consumível")) {
                if (vida >= getVidaMaximaTotal()) {
                    System.out.println("Sua vida já está cheia! Não é necessário usar este consumível.");
                    return false;
                }
                int cura = consumivel.getValor();
                vida = Math.min(vida + cura, getVidaMaximaTotal());
                System.out.println("Você usou " + consumivel.getNome() + " e recuperou " + cura + " de vida!");
                consumiveis.remove(index);
                return true;
            }
        }
        return false;
    }

    public void listarConsumiveis() {
        if (consumiveis.isEmpty()) {
            System.out.println("Você não tem consumíveis no inventário!");
            return;
        }

        System.out.println("\n=== CONSUMÍVEIS ===");
        System.out.print("\nVida: " + vida + "/" + getVidaMaximaTotal()+ "\n");
        for (int i = 0; i < consumiveis.size(); i++) {
            ItemComum item = consumiveis.get(i);
            System.out.println((i+1) + " - " + item.getNome() + " (+" + item.getValor() + " HP)");
        }
    }

    public void adicionarConsumivel(ItemComum item) {
        if (item.getTipo().equals("Consumível")) {
            consumiveis.add(item);
            System.out.println(item.getNome() + " foi adicionado ao seu inventário de consumíveis!");
        }
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
        Loja loja = new Loja(this);

        while (true) {
            System.out.println("\n\n=== MENU ===");
            System.out.println("1 - Mostrar Status");
            System.out.println("2 - Mostrar Mapa");
            System.out.println("3 - Acessar Loja");
            System.out.println("4 - Usar Consumível");
            System.out.println("5 - Desequipar Item");
            System.out.println("6 - Ver Tesouros Encontrados");
            System.out.println("7 - Ver Monstruário");
            System.out.println("8 - Voltar ao Jogo");
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
                    if (labirintoAtual.isMapaPrincipal()) {
                        loja.mostrarMenuLoja();
                    } else {
                        System.out.println("O comerciante não se aventura em lugares perigosos como este.");
                        System.out.println("Volte ao mapa principal para acessar a loja.");
                    }
                    break;
                case "4":
                    listarConsumiveis();
                    if (!consumiveis.isEmpty()) {
                        System.out.print("Escolha o consumível para usar (0 para cancelar): ");
                        try {
                            int escolha = Integer.parseInt(sc.nextLine()) - 1;
                            if (escolha >= 0 && escolha < consumiveis.size()) {
                                usarConsumivel(escolha);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida!");
                        }
                    }
                    break;
                case "5":
                    desequiparItem();
                    break;
                case "6":
                    mostrarTesourosEncontrados();
                    break;
                case "7":
                    if (monstruario.temRegistros()) {
                        monstruario.mostrarMonstruario();
                    } else {
                        System.out.println("Seu monstruário está vazio. Encontre inimigos e armadilhas para registrá-los!");
                    }
                    break;
                case "8":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public void mostrarTesourosEncontrados() {
        if (tesourosEncontrados.isEmpty()) {
            System.out.println("Você ainda não encontrou nenhum tesouro!");
            return;
        }

        // Agrupa os tesouros por nome e conta as ocorrências
        Map<String, Integer> contagemTesouros = new HashMap<>();
        Map<String, String> tiposTesouros = new HashMap<>();

        for (Tesouros tesouro : tesourosEncontrados) {
            String nome = tesouro.getNome();
            contagemTesouros.put(nome, contagemTesouros.getOrDefault(nome, 0) + 1);
            tiposTesouros.put(nome, tesouro.getTipo());
        }

        System.out.println("\n=== TESOUROS ENCONTRADOS ===");
        for (Map.Entry<String, Integer> entry : contagemTesouros.entrySet()) {
            String nome = entry.getKey();
            int quantidade = entry.getValue();
            String tipo = tiposTesouros.get(nome);

            System.out.println("- " + nome + " (" + tipo + ") " + quantidade + "x");
        }
        System.out.println("===================================");
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

    String celula = labirintoAtual.getEstrutura().get(novoI).get(novoJ);

    if (!celula.equals(" ") && !celula.equals("F") && !celula.equals("S") &&
            !celula.equals("T") && !celula.equals("O") && !celula.equals("L") &&
            !celula.equals("B")) {
        return false;
    }

    // Verifica se encontrou um labirinto (L)
    if (celula.equals("L") && labirintoAtual.isMapaPrincipal()) {
        System.out.println("\nVocê encontrou uma entrada para um labirinto!");

        String resposta;
        do {
            System.out.print("Deseja entrar? (s/n): ");
            resposta = sc.nextLine().trim().toLowerCase();
        } while (!resposta.equals("s") && !resposta.equals("n"));

        if (resposta.equals("s")) {
            // Limpa a posição anterior
            labirintoAtual.getEstrutura().get(posI).set(posJ, " ");
            posI = novoI;
            posJ = novoJ;
            labirintoAtual.getEstrutura().get(posI).set(posJ, "O");

            // Substitui L por espaço
            labirintoAtual.getEstrutura().get(novoI).set(novoJ, " ");

            entrarNoLabirinto();
        }

        // Se disser "n", não move. Apenas mantém a posição atual.
        return true;
    }

    if (celula.equals("F") && labirintoAtual.isMapaPrincipal()) {
        System.out.println("\nVocê encontrou uma entrada para a sala do BOSS!");

        String resposta;
        do {
            System.out.print("Deseja entrar? (s/n): ");
            resposta = sc.nextLine().trim().toLowerCase();
        } while (!resposta.equals("s") && !resposta.equals("n"));

        if (resposta.equals("s")) {
            labirintoAtual.getEstrutura().get(posI).set(posJ, " ");
            posI = novoI;
            posJ = novoJ;
            labirintoAtual.getEstrutura().get(posI).set(posJ, "O");

            entrarNoBOSS();
        }
        return true;
    }

    // Verifica se está na saída do labirinto (S)
    if (celula.equals("S") && !labirintoAtual.isMapaPrincipal()) {
        labirintoAtual.getEstrutura().get(posI).set(posJ, " ");
        posI = novoI;
        posJ = novoJ;
        labirintoAtual.getEstrutura().get(posI).set(posJ, "O");

        sairDoLabirinto();
        labirintoAtual.limparTerminal();
        return true;
    }

    if (celula.equals("S") && labirintoAtual.isMapaPrincipal()) {
        labirintoAtual.getEstrutura().get(posI).set(posJ, " ");
        posI = novoI;
        posJ = novoJ;
        labirintoAtual.getEstrutura().get(posI).set(posJ, "O");

        sairDoLabirinto();
        labirintoAtual.limparTerminal();
        return true;
    }
    // Movimento normal
    labirintoAtual.getEstrutura().get(posI).set(posJ, " ");
    posI = novoI;
    posJ = novoJ;
    labirintoAtual.getEstrutura().get(posI).set(posJ, "O");
    labirintoAtual.limparTerminal();
    verificarTesouro();
    verificarPerigo();

    return true;
}

    private void entrarNoLabirinto() {
        System.out.println("\n--- ENTRANDO NO LABIRINTO ---");

        // Guarda a posição CORRETA (i=linha, j=coluna)
        setUltimaPosicaoMapa(posI, posJ);
        // Restante do metodo permanece igual...
        int labirintoID = new Random().nextInt(11);
        Labirinto labirintoAleatorio = new Labirinto(labirintoID, 0, false);
        labirintoAleatorio.gerar_labirinto(labirintoID);

        // Salva o estado atual do mapa principal antes de entrar
        copiaMapaPrincipal = new ArrayList<>();
        for (ArrayList<String> linha : mapaPrincipal.getEstrutura()) {
            copiaMapaPrincipal.add(new ArrayList<>(linha));
        }

        setLabirintoAtual(labirintoAleatorio);
        setPosicao(labirintoAleatorio.getInicioI(), labirintoAleatorio.getInicioJ());
        System.out.println("Você entrou no Labirinto " + labirintoID + "!");
        int x = calcularMusica();
        mapaPrincipal.setMusica(x);
    }

    private void entrarNoBOSS() {
        System.out.println("\n--- ENTRANDO NA SALA DO BOSS ---");

        setUltimaPosicaoMapa(posI, posJ);

        int labirintoID = 11;
        Labirinto labirintoBoss = new Labirinto(labirintoID, 0, false);
        labirintoBoss.gerar_labirinto(labirintoID);

        setLabirintoAtual(labirintoBoss);
        setPosicao(labirintoBoss.getInicioI(), labirintoBoss.getInicioJ());

        mapaPrincipal.setMusica(3);

        System.out.println("Você entrou no labirinto do Boss Final!");
        labirintoAtual.imprimirLabirinto();

        while (true) {
            System.out.println("\nAvance até a saida do labirinto para enfrentar o Boss!");
            System.out.print("Use WASD para mover ou M para menu: ");

            String inputStr = sc.nextLine().trim();
            if (inputStr.isEmpty()) {
                System.out.println("Digite um comando válido (W, A, S, D, M ou Q)");
                continue;
            }
            char input = inputStr.toUpperCase().charAt(0);

            if (input == 'M') {
                mostrarMenu();
                continue;
            }

            if(input == 'Q'){
                System.out.print("Deseja realmente sair? (S/N): ");
                String confirmacaoStr = sc.nextLine().trim();
                if (!confirmacaoStr.isEmpty() && confirmacaoStr.toUpperCase().charAt(0) == 'S') {
                    System.out.println("Saindo do jogo...");
                    System.exit(0);
                }
                continue;
            }

            if (!mover(input)) {
                System.out.println("Movimento inválido!");
                continue;
            }

            labirintoAtual.imprimirLabirinto();

            if (posI == 0 && posJ == 6) {
                iniciarCombateComBoss();
                break;
            }
        }
    }

    private boolean estaNaPosicaoDoBoss() {
        return posI == 0 && posJ == 6;
    }

    private void iniciarCombateComBoss() {
        labirintoAtual.getEstrutura().get(posI).set(posJ, " ");

    Inimigo boss;
    switch (mapaPrincipal.getDificuldade()) {
        case 1:
            boss = Inimigo.getBossByName("Prometheus");
            break;
        case 2:
            boss = Inimigo.getBossByName("Mão de Deus");
            break;
        case 3:
            boss = Inimigo.getBossByName("Memórias de um Sonho Antigo");
            break;
        default:
            boss = Inimigo.getInimigoAleatorio();
            break;
    }

        Inimigo bossFinal = new Inimigo(
                boss.getNome(),
                boss.getHistoria(),
                boss.getDescricao(),
                boss.getIDP(),
                boss.getDano(),
                boss.getVelocidade(),
                boss.getArmadura(),
                boss.getVida(),
                boss.getDanoVerdadeiro(),
                posI, posJ
        );

        System.out.println("\n====================================");
        System.out.println("UM PODEROSO INIMIGO SE APROXIMA!");
        System.out.println(">> " + bossFinal.getNome().toUpperCase() + " <<");
        System.out.println("====================================");
        esperar(2000);

        SistemaCombate.encontrarInimigo(this, bossFinal);

        if (estaVivo()) {  // Só continua se o jogador venceu
            System.out.println("\nVocê derrotou " + bossFinal.getNome() + "!");
            System.out.println("O caminho para a saída se abre!");

            // Pergunta se quer trocar de classe
            System.out.print("\nDeseja trocar de classe? (s/n): ");
            String resposta = sc.nextLine().trim().toLowerCase();
            if (resposta.equals("s")) {
                trocarClasse();
            }

            // Marca a posição atual como saída
            labirintoAtual.getEstrutura().get(posI).set(posJ, "S");

            // Volta para o mapa principal
            sairDoLabirinto();
        }
    }

    private void trocarClasse() {
        KitClasse[] classes = {
                new KitClasse("Guerreiro", 15, 15, 2, 0.3, -1),
                new KitClasse("Mago", 10, 5, 20, 0.15, 2),
                new KitClasse("Arqueiro", 13, 10, 10, 0.2, 7)
        };

        System.out.println("\nSelecione sua nova classe:");
        System.out.println("1 - Guerreiro");
        System.out.println("   +15 Vida, +15 Ataque, +2 Dano Verdadeiro, +30% Armadura, -1 Velocidade");
        System.out.println("2 - Mago");
        System.out.println("   +10 Vida, +5 Ataque, +20 Dano Verdadeiro, +15% Armadura, +2 Velocidade");
        System.out.println("3 - Arqueiro");
        System.out.println("   +13 Vida, +10 Ataque, +10 Dano Verdadeiro, +20% Armadura, +7 Velocidade");
        System.out.print("Escolha (1-3): ");

        int escolhaClasse;
        while (true) {
            try {
                escolhaClasse = Integer.parseInt(sc.nextLine());
                if (escolhaClasse >= 1 && escolhaClasse <= 3) break;
                System.out.print("Escolha inválida! Digite 1, 2 ou 3: ");
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida! Digite 1, 2 ou 3: ");
            }
        }

        KitClasse novaClasse = classes[escolhaClasse - 1];

        // Remove o kit de classe atual
        equipamentos.removeIf(item -> item.getNome().startsWith("Kit de "));

        // Aplica os bônus da nova classe
        this.vidaMaxima = 100 + novaClasse.getBonusVida();
        this.vida = Math.min(vida, vidaMaxima);
        this.danoAtaque = novaClasse.getBonusAtaque();
        this.danoVerdadeiro = novaClasse.getBonusDanoVerdadeiro();
        this.armadura = novaClasse.getBonusArmadura();
        this.velocidade = novaClasse.getBonusVelocidade();

        ItemEquipavel kitItem = new ItemEquipavel(
                "Kit de " + novaClasse.getNome(),
                0, 0, "Classe",
                novaClasse.getBonusVida(),
                novaClasse.getBonusArmadura(),
                novaClasse.getBonusAtaque(),
                novaClasse.getBonusDanoVerdadeiro(),
                novaClasse.getBonusVelocidade(),
                0
        );
        this.equipamentos.add(kitItem);

        System.out.println("\nVocê agora é um " + novaClasse.getNome() + "!");
        mostrarStatus();
    }

    private void sairDoLabirinto() {
        System.out.println("\n--- SAINDO DO LABIRINTO ---");
        mapaPrincipal.paraMusica();

        // Restaura o mapa principal
        mapaPrincipal.setEstrutura(copiaMapaPrincipal);

        // Obtém a posição CORRETA (sem inverter)
        int novaPosI = getUltimaPosicaoMapaI();
        int novaPosJ = getUltimaPosicaoMapaJ();

        // Verificação de limites
        if (novaPosI < 0 || novaPosI >= mapaPrincipal.getEstrutura().size() ||
                novaPosJ < 0 || novaPosJ >= mapaPrincipal.getEstrutura().get(0).size()) {
            novaPosI = mapaPrincipal.getInicioI();
            novaPosJ = mapaPrincipal.getInicioJ();
        }

        // Atualiza posição SEM inverter
        setLabirintoAtual(mapaPrincipal);
        limparTodosOsOJogador();
        setPosicao(novaPosI, novaPosJ);
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
                    System.out.println("Velocidade: +" + item.getBonusVelocidade());
                    System.out.println("Valor de venda: " + item.getValorVenda() + " moedas");

                    System.out.print("\nDeseja equipar este item? (s/n): ");
                    String resposta = sc.nextLine();
                    if (resposta.equalsIgnoreCase("s")) {
                        equipar(item);
                    }
                    tesourosEncontrados.add(tesouro);
                }
                else if (tesouro instanceof ItemComum) {
                    ItemComum item = (ItemComum) tesouro;
                    if(item.getTipo().equals("Consumível")){
                        System.out.println("\nValor: " + item.getValor() + " moedas");
                        System.out.println("Valor de venda: " + item.getValorVenda() + " moedas");
                        System.out.println("Este item recupera " + item.getValor() + " de vida!\n");
                        System.out.print("Deseja pegar este consumível? (s/n): ");
                        String resposta = sc.nextLine();
                        if (resposta.equalsIgnoreCase("s")) {
                            adicionarConsumivel(item);
                            tesourosEncontrados.add(tesouro);
                        }
                    }else{
                        System.out.println("Valor: " + item.getValor() + " moedas");
                        System.out.println("Valor de venda: " + item.getValorVenda() + " moedas\n");
                        tesourosEncontrados.add(tesouro);
                    }
                }

                labirintoAtual.getListaTesouros().remove(tesouro);
                return;
            }
        }
    }

    private void verificarPerigo() {
        for (Perigo perigo : labirintoAtual.getListaPerigos()) {
            if (perigo.getLinha() == posI && perigo.getColuna() == posJ) {
                if (perigo instanceof Inimigo) {  // Agora usando a classe Inimigo diretamente
                    SistemaCombate.encontrarInimigo(this, (Inimigo) perigo);
                } else if (perigo instanceof Armadilha) {  // Agora usando a classe Armadilha diretamente
                    SistemaCombate.encontrarArmadilha(this, (Armadilha) perigo);
                }
                labirintoAtual.getListaPerigos().remove(perigo);
                return;
            }
        }
    }
}