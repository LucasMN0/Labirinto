package LABIRINTO;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Monstruario implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String ARQUIVO_MONSTRUARIO = "monstruario.dat";
    private Map<String, Perigo> inimigosRegistrados;
    private Map<String, Perigo> armadilhasRegistradas;
    private static Monstruario instancia;

    Monstruario() {
        this.inimigosRegistrados = new HashMap<>();
        this.armadilhasRegistradas = new HashMap<>();
        carregar();
    }

    public static Monstruario getInstance() {
        if (instancia == null) {
            instancia = new Monstruario();
        }
        return instancia;
    }

    public void registrarInimigo(Inimigo inimigo) {
        if (!inimigosRegistrados.containsKey(inimigo.getNome())) {
            inimigosRegistrados.put(inimigo.getNome(), inimigo.copiar());
            salvar();
        }
    }

    public void registrarArmadilha(Armadilha armadilha) {
        if (!armadilhasRegistradas.containsKey(armadilha.getNome())) {
            armadilhasRegistradas.put(armadilha.getNome(), armadilha.copiar());
            salvar();
        }
    }

    public boolean temRegistros() {
        return !inimigosRegistrados.isEmpty() || !armadilhasRegistradas.isEmpty();
    }

    public void mostrarMonstruario() {
        System.out.println("\n=== MONSTRUÁRIO (TODAS AS PARTIDAS) ===");

        System.out.println("\n--- INIMIGOS ENCONTRADOS ---");
        if (inimigosRegistrados.isEmpty()) {
            System.out.println("Nenhum inimigo registrado ainda.");
        } else {
            for (Perigo inimigo : inimigosRegistrados.values()) {
                System.out.println("\nNome: " + inimigo.getNome());
                System.out.println("História: " + inimigo.getHistoria());
                System.out.println("Descrição: " + inimigo.getDescricao());
                System.out.println("---------------------");
            }
        }

        System.out.println("\n--- ARMADILHAS ENCONTRADAS ---");
        if (armadilhasRegistradas.isEmpty()) {
            System.out.println("Nenhuma armadilha registrada ainda.");
        } else {
            for (Perigo armadilha : armadilhasRegistradas.values()) {
                System.out.println("\nNome: " + armadilha.getNome());
                System.out.println("História: " + armadilha.getHistoria());
                System.out.println("Descrição: " + armadilha.getDescricao());
                System.out.println("---------------------\n");
            }
        }
    }

    private void salvar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_MONSTRUARIO))) {
            Monstruario paraSalvar = new Monstruario();
            paraSalvar.inimigosRegistrados = new HashMap<>(this.inimigosRegistrados);
            paraSalvar.armadilhasRegistradas = new HashMap<>(this.armadilhasRegistradas);

            oos.writeObject(paraSalvar);
        } catch (IOException e) {
            System.err.println("Erro ao salvar monstruário: " + e.getMessage());
            new File(ARQUIVO_MONSTRUARIO).delete();
        }
    }

    private void carregar() {
        File arquivo = new File(ARQUIVO_MONSTRUARIO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                Monstruario carregado = (Monstruario) ois.readObject();
                this.inimigosRegistrados = new HashMap<>(carregado.inimigosRegistrados);
                this.armadilhasRegistradas = new HashMap<>(carregado.armadilhasRegistradas);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar monstruário. Iniciando novo monstruário.");
                arquivo.delete();
                this.inimigosRegistrados = new HashMap<>();
                this.armadilhasRegistradas = new HashMap<>();
            }
        }
    }
}