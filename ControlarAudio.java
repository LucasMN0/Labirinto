package LABIRINTO;

//testando uma classe para controlar as músicas

public class ControlarAudio {
    // Instância global e reutilizável do controlador de som
    private static final GerenciadorDeMusica musica = new GerenciadorDeMusica();

    public static GerenciadorDeMusica getMusica() {
        return musica;
    }
}