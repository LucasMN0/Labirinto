package LABIRINTO;

import javax.sound.midi.*;
import java.util.*;

public class Musica {

    private Sequencer sequencer;

    public void playLevel(int level) { // tocar música
        try {
            if (sequencer != null && sequencer.isRunning()) {
                sequencer.stop();
                sequencer.close();
            }

            sequencer = MidiSystem.getSequencer();
            sequencer.open();

            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            int tempo;
            int[] notes;

            switch (level) {
                case 1: // Longe do boss
                    tempo = 80;
                    notes = new int[]{60, 62, 64, 67}; // C, D, E, G
                    break;
                case 2: // Meio do caminho
                    tempo = 120;
                    notes = new int[]{60, 62, 64, 65, 67}; // C, D, E, F, G
                    break;
                case 3: // Perto do boss
                    tempo = 160;
                    notes = new int[]{60, 61, 62, 63, 64, 65, 66, 67}; // Crescente rápida
                    break;
                default:
                    tempo = 100;
                    notes = new int[]{60, 62, 64};
                    break;
            }

            int tick = 0;
            for (int i = 0; i < 16; i++) {
                for (int note : notes) {
                    track.add(createNoteOnEvent(note, tick));
                    track.add(createNoteOffEvent(note, tick + 1));
                    tick += 2;
                }
            }

            sequencer.setSequence(sequence);
            sequencer.setTempoInBPM(tempo);
            sequencer.start();

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
 
    public void stop() {  // parar a música
        if (sequencer != null && sequencer.isRunning()) {
            sequencer.stop();
            sequencer.close();
        }
    }
}