package TerminalDeCaixaEletronico;

public class SaldoInsuficienteException extends Exception {
  public SaldoInsuficienteException(String descricao) {
    super(descricao);
  }
}
