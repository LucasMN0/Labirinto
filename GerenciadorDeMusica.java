package LABIRINTO;

import javax.sound.sampled.*;
import javax.sound.midi.*;
import java.io.File;
import java.util.*;

public class GerenciadorDeMusica {

    private Clip clipWav;
    private Sequencer sequencerMidi;
    private final Map<Integer, String> musicasWav;

    public GerenciadorDeMusica() {
        musicasWav = new HashMap<>();
        musicasWav.put(1, "res/audio/batalha.wav");
        musicasWav.put(2, "res/audio/derrota.wav");
        musicasWav.put(3, "res/audio/ambiente.wav");
    }

    // Tocar música WAV
    public void tocarWav(int ID) {
        pararTudo();

        String caminho = musicasWav.get(ID);
        if (caminho == null) {
            System.err.println("Música WAV não encontrada: " + ID);
            return;
        }

        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(caminho));
            clipWav = AudioSystem.getClip();
            clipWav.open(audioInput);
            clipWav.loop(Clip.LOOP_CONTINUOUSLY);
            clipWav.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private MidiEvent createNoteOnEvent(int note, int tick) throws Exception {
        return new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, note, 100), tick);
    }

    private MidiEvent createNoteOffEvent(int note, int tick) throws Exception {
        return new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, 0, note, 100), tick);
    }

    // Parar qualquer tipo de música
    public void pararTudo() {
        if (clipWav != null && clipWav.isRunning()) {
            clipWav.stop();
            clipWav.close();
        }
    }
}