package TerminalDeCaixaEletronico;

public class Poupanca extends Conta {
  private double rendimentoMensal = 0.086;

  public Poupanca(int numero, Cliente titular, int senha, double saldo) {
    super(numero, titular, senha, saldo);
  }

  @Override
  public double verificaSaldo(int senha) throws SenhaInvalidaException {
    return super.verificaSaldo(senha) + super.verificaSaldo(senha) * this.rendimentoMensal;
  }

  public void alteraRendimentoMensal(double novoRendimentoMensal) {
    this.rendimentoMensal = novoRendimentoMensal;
  }

  public double retornaRendimento() {
    return this.rendimentoMensal;
  }
}
