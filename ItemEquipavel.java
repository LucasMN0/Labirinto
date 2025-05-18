package LABIRINTO;

public class ItemEquipavel extends Tesouros {
    private int bonusVida;
    private double bonusArmadura;
    private int bonusAtaque;
    private int bonusVerdadeiro;

    // Construtor completo
    public ItemEquipavel(String nome, int linha, int coluna, String tipo,
                         int bonusVida, double bonusArmadura, int bonusAtaque, int bonusVerdadeiro) {
        super(nome, linha, coluna, tipo);
        this.bonusVida = bonusVida;
        this.bonusArmadura = bonusArmadura;
        this.bonusAtaque = bonusAtaque;
        this.bonusVerdadeiro = bonusVerdadeiro;
    }

    // Getters
    public int getBonusVida() { return bonusVida; }
    public double getBonusArmadura() { return bonusArmadura; }
    public int getBonusAtaque() { return bonusAtaque; }
    public int getBonusVerdadeiro() { return bonusVerdadeiro; }
}