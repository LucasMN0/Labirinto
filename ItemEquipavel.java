package LABIRINTO;

public class ItemEquipavel extends Tesouros {
    private int bonusVida;
    private double bonusArmadura;
    private int bonusAtaque;
    private int bonusVerdadeiro;
    private int bonusVelocidade;

    public ItemEquipavel(String nome, int linha, int coluna, String tipo,
                         int bonusVida, double bonusArmadura, int bonusAtaque, int bonusVerdadeiro, int bonusVelocidade, int valor) {
        super(nome, linha, coluna, tipo, valor);
        this.bonusVida = bonusVida;
        this.bonusArmadura = bonusArmadura;
        this.bonusAtaque = bonusAtaque;
        this.bonusVerdadeiro = bonusVerdadeiro;
        this.bonusVelocidade = bonusVelocidade;
    }

    // Getters
    public int getBonusVida() { return bonusVida; }
    public double getBonusArmadura() { return bonusArmadura; }
    public int getBonusAtaque() { return bonusAtaque; }
    public int getBonusVerdadeiro() { return bonusVerdadeiro; }
    public int getBonusVelocidade() { return bonusVelocidade; }

    @Override
    public int getValor(){
        return valor;
    }

    @Override
    public int getValorVenda() {
        return (int)(valor * 0.6);
    }

}