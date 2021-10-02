package TerminalDeCaixaEletronico;

public class Lancamento {
  private String descricao;
  private double valor;

  public Lancamento(String descricao, double valor) {
    this.descricao = descricao;
    this.valor = valor;
  }

  public String getDescricao() {
    return this.descricao;
  }

  public double getValor() {
    return this.valor;
  }
}
