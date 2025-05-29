package LABIRINTO;

import javax.sound.sampled.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MusicaWav {

    private Clip clip;
    private final Map<Integer, String> musicas;

    public MusicaWav() {
        // Mapeia nomes lógicos aos caminhos dos arquivos de música
        musicas = new HashMap<>();
        musicas.put(1, "res/audio/batalha.wav");
        musicas.put(2, "res/audio/derrota.wav");
    }

    public void tocar(int ID) {
        parar(); // Para a música anterior

        String caminho = musicas.get(ID);
        if (caminho == null) {
            System.err.println("Música não encontrada: " + ID);
            return;
        }

        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(caminho));
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Toca em loop
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parar() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}