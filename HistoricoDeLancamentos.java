package TerminalDeCaixaEletronico;

import java.util.ArrayList;

public class HistoricoDeLancamentos {
  private ArrayList<Lancamento> lancamentos;
  private int ultimoLancamento;

  public HistoricoDeLancamentos() {
    this.lancamentos = new ArrayList<Lancamento>();
  }

  public void insereLancamento(Lancamento lancamento) {
    this.lancamentos.add(lancamento);
  }

  public String geraHistoricoDeLancamentos() {
    StringBuilder historico = new StringBuilder();
    for (Lancamento lancamento : this.lancamentos) {
      if (lancamento != null) {
        historico
            .append(lancamento.getDescricao())
            .append(": R$")
            .append(lancamento.getValor())
            .append("\n");
      }
    }
    return historico.toString();
  }
}
