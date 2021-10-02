package TerminalDeCaixaEletronico;

public class Cliente {
  private String cpf;
  private String nome;

  public Cliente(String cpf, String nome) {
    this.cpf = cpf;
    this.nome = nome;
  }

  public String getCpf() {
    return this.cpf;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
