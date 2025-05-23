package LABIRINTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Monstruario {
    private Map<String, Perigo> inimigosRegistrados;
    private Map<String, Perigo> armadilhasRegistradas;

    public Monstruario() {
        this.inimigosRegistrados = new HashMap<>();
        this.armadilhasRegistradas = new HashMap<>();
    }

    public void registrarInimigo(Perigo.Inimigo inimigo) {
        if (!inimigosRegistrados.containsKey(inimigo.getNome())) {
            inimigosRegistrados.put(inimigo.getNome(), inimigo.copiar());
        }
    }

    public void registrarArmadilha(Perigo.Armadilha armadilha) {
        if (!armadilhasRegistradas.containsKey(armadilha.getNome())) {
            armadilhasRegistradas.put(armadilha.getNome(), armadilha.copiar());
        }
    }

    public void mostrarMonstruario() {
        System.out.println("\n=== MONSTRUÁRIO ===");

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
                System.out.println("---------------------");
            }
        }
    }

    public boolean temRegistros() {
        return !inimigosRegistrados.isEmpty() || !armadilhasRegistradas.isEmpty();
    }
}