
public class Player {
  public String nome;
  private int HP;
  private int atackForce;
  private int resistencia;
  private int defesa;
  private int destreza;
  private int velocidade;

  public Player(String nome, int HP, int atackForce, int resistência, int defesa, int destreza, int velocidade) {
    this.nome = nome;
    this.HP = HP;
    this.atackForce = atackForce;
    this.resistencia = resistencia;
    this.defesa = defesa;
    this.destreza = destreza;
    this.velocidade = velocidade;
  }

  public String getNome() {
    return nome;
  }
  public int getAtackForce() {
    return atackForce;
  }
  public void setAtackForce(int atackForce) {
    this.atackForce = atackForce;
  }

  public int getResistencia() {
    return resistencia;
  }
  public void setResistencia(int resistencia) {
    this.resistencia = resistencia;
  }

  public int getDefesa() {
    return defesa;
  }
  public void setDefesa(int defesa) {
    this.defesa = defesa;
  }

  public int getDestreza() {
    return destreza;
  }
  public void setDestreza(int destreza) {
    this.destreza = destreza;
  }

  public int getVelocidade() {
    return velocidade;
  }
  public void setVelocidade(int velocidade) {
    this.velocidade = velocidade;
  }

  public int getHP() {
    return HP;
  }
  public void setHP(int HP) {
    this.HP = HP;
  }
  public void receberDano(double dano) {
    this.HP -= dano;
    if (this.HP < 0) {
      this.HP = 0;
    }
  }
//  public int[] getInformacoes(){
//    return informacoes = new int[] {getAtackForce(),getResistencia(),getDefesa(),getDestreza(),getVelocidade()};
//

//  }

}
