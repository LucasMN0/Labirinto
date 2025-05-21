package LABIRINTO;

public class ItemEquipavel extends Tesouros {
    private int bonusVida;
    private double bonusArmadura;
    private int bonusAtaque;
    private int bonusVerdadeiro;

    public ItemEquipavel(String nome, int linha, int coluna, String tipo,
                         int bonusVida, double bonusArmadura, int bonusAtaque, int bonusVerdadeiro, int valor) {
        super(nome, linha, coluna, tipo, valor);
        this.bonusVida = bonusVida;
        this.bonusArmadura = bonusArmadura;
        this.bonusAtaque = bonusAtaque;
        this.bonusVerdadeiro = bonusVerdadeiro;
        this.valor = bonusVida * 2 + bonusAtaque * 3 + bonusVerdadeiro * 4;
    }

    // Getters
    public int getBonusVida() { return bonusVida; }
    public double getBonusArmadura() { return bonusArmadura; }
    public int getBonusAtaque() { return bonusAtaque; }
    public int getBonusVerdadeiro() { return bonusVerdadeiro; }

    @Override
    public int getValor(){
        return valor;
    }

    @Override
    public int getValorVenda() {
        return (int)(valor * 0.6);
    }

}