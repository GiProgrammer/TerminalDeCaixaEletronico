package TerminalDeCaixaEletronico;

import java.util.ArrayList;

public class CadastroContas {
  private ArrayList<Conta> contas;

  public CadastroContas() {
    this.contas = new ArrayList<Conta>();
  }

  public boolean adicionaConta(Conta conta) throws ContaInvalidaException {
    if (this.buscaConta(conta.getNumero()) != null) {
      throw new ContaInvalidaException("Conta Inválida!");
    }
    this.contas.add(conta);
    return true;
  }

  public Conta buscaConta(int numero) throws ContaInvalidaException {
    for (Conta item : contas) {
      if (numero == item.getNumero()) {
        return item;
      }
    }
    return null;
  }
}
