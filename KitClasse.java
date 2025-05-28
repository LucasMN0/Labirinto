package LABIRINTO;

import java.io.Serializable;

public class KitClasse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private int bonusVida;
    private int bonusAtaque;
    private int bonusDanoVerdadeiro;
    private double bonusArmadura;
    private int bonusVelocidade;

    public KitClasse(String nome, int bonusVida, int bonusAtaque, int bonusDanoVerdadeiro,
                     double bonusArmadura, int bonusVelocidade) {
        this.nome = nome;
        this.bonusVida = bonusVida;
        this.bonusAtaque = bonusAtaque;
        this.bonusDanoVerdadeiro = bonusDanoVerdadeiro;
        this.bonusArmadura = bonusArmadura;
        this.bonusVelocidade = bonusVelocidade;
    }

    // Getters
    public String getNome() { return nome; }
    public int getBonusVida() { return bonusVida; }
    public int getBonusAtaque() { return bonusAtaque; }
    public int getBonusDanoVerdadeiro() { return bonusDanoVerdadeiro; }
    public double getBonusArmadura() { return bonusArmadura; }
    public int getBonusVelocidade() { return bonusVelocidade; }
}