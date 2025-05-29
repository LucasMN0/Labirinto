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

    // Tocar música MIDI baseada no nível
    public void tocarMidi(int level) {
        pararTudo();

        try {
            sequencerMidi = MidiSystem.getSequencer();
            sequencerMidi.open();

            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            int tempo;
            int[] notes;

            switch (level) {
                case 1:
                    tempo = 80;
                    notes = new int[]{60, 62, 64, 67};
                    break;
                case 2:
                    tempo = 150;
                    notes = new int[]{60, 62, 64, 65, 67};
                    break;
                case 3:
                    tempo = 200;
                    notes = new int[]{60, 61, 62, 63, 64, 65, 66, 67};
                    break;
                default:
                    tempo = 200;
                    notes = new int[]{60, 62, 64};
                    break;
            }

            int tick = 0;
            for (int i = 0; i < 64; i++) {
                for (int note : notes) {
                    track.add(createNoteOnEvent(note, tick));
                    track.add(createNoteOffEvent(note, tick + 1));
                    tick += 2;
                }
            }

            sequencerMidi.setSequence(sequence);
            sequencerMidi.setTempoInBPM(tempo);
            sequencerMidi.start();
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

        if (sequencerMidi != null && sequencerMidi.isRunning()) {
            sequencerMidi.stop();
            sequencerMidi.close();
        }
    }
}