package TerminalDeCaixaEletronico;

import TerminalDeCaixaEletronico.SaldoInsuficienteException;
import TerminalDeCaixaEletronico.ValorNegativoException;
import java.util.Scanner;

public class Terminal {
  private Caixa meuCaixa;
  private int modoAtual;

  public Terminal(CadastroContas bd) {
    this.meuCaixa = new Caixa(this, bd);
  }

  public void iniciaOperacao() {
    int opcao;
    opcao = this.getOpcao();
    while (opcao != 8) {

      switch (opcao) {
        case 1:
          try {
            double saldo = this.meuCaixa.consultaSaldo(getInt("Numero da Conta"), getInt("Senha"));
            if (saldo != -1) {
              System.out.printf("Saldo Atual: %.2f\n",saldo);
            } else {

            }
          } catch (ContaInvalidaException contaIn) {
            System.err.println(contaIn + "\n");
          } catch (SenhaInvalidaException senhaIn) {
            System.err.println(senhaIn + "\n");
          }
          break;

        case 2:
          try {
            boolean b =
                this.meuCaixa.efetuaSaque(
                    getInt("Numero da Conta"), (double) getInt("Valor"), getInt("Senha"));
            if (b) {
              System.out.println("Retire o dinheiro");
            }
          } catch (ContaInvalidaException contaIn) {
            System.err.println(contaIn + "\n");

          } catch (SenhaInvalidaException senhaIn) {
            System.err.println(senhaIn + "\n");

          } catch (SaldoInsuficienteException saldoIn) {
            System.err.println(saldoIn + "\n");
          } catch (ValorNegativoException valorNeg) {
            System.err.println(valorNeg + "\n");
          }
          break;

        case 3:
          try {
            boolean c =
                this.meuCaixa.efetuaTransferencia(
                    getInt("Numero da Conta"),
                    getInt("Senha"),
                    (double) getInt("Valor"),
                    getInt("Conta Destino"));
            if (c) {
              System.out.println("Transferência ocorrida com sucesso!");
            } else {

            }
          } catch (ContaInvalidaException contaIn) {
            System.err.println(contaIn + "\n");

          } catch (SenhaInvalidaException senhaIn) {
            System.err.println(senhaIn + "\n");
          } catch (SaldoInsuficienteException saldoIn) {
            System.err.println(saldoIn + "\n");
          } catch (ValorNegativoException valorNeg) {
            System.err.println(valorNeg + "\n");
          }

          break;

        case 4:
          this.meuCaixa.recarrega();
          break;

        case 5:
          try {
            boolean d =
                this.meuCaixa.depositar(
                    getInt("Numero da Conta"), getInt("Valor:"), "Depósito em Dinheiro ");
            if (d) {
              System.out.println("Depósito Concluído!");
            }
          } catch (ContaInvalidaException contaIn) {
            System.err.println(contaIn + "\n");
          } catch (ValorNegativoException valorNeg) {
            System.err.println(valorNeg + "\n");
          }

          break;
        case 6:
          try {
            boolean e =
                this.meuCaixa.depositar(
                    getInt("Numero da Conta"),
                    getInt("Valor"),
                    getInt("Numero do Cheque"),
                    "Depósito em Cheque");
            if (e) {
              System.out.println("Depósito Concluído!");
            }
          } catch (ContaInvalidaException contaIn) {
            System.err.println(contaIn + "\n");
          } catch (ValorNegativoException valorNeg) {
            System.err.println(valorNeg + "\n");
          }

          break;
        case 7:
          String[] f = new String[10];
          try {
            f[0] = this.meuCaixa.consultarExtratoBancario(getInt("Numero da Conta"));

            for (String historico : f) {
              if (historico != null) {
                System.out.println(historico);
              }
            }

          } catch (ContaInvalidaException contaIn) {
            System.err.println(contaIn + "\n");
          }
          break;
      }
      opcao = getOpcao();
    }
  }

  public void setModo(int modo) {
    if (modo == 0 || modo == 1) {
      this.modoAtual = modo;
    }
  }

  private int getOpcao() {
    int opcao;
    do {
      if (this.modoAtual == 1) {
        opcao =
            getInt(
                "Opcao: 1 - Consulta Saldo"
                    + "\n2 - Saque"
                    + "\n3 - Transferências"
                    + "\n5 - Depósitos em Dinheiro"
                    + "\n6 - Depósito em Cheque"
                    + "\n7 - Consultar Extrato"
                    + "\n8 - Sair");
        if (opcao != 1 & opcao != 2 & opcao != 3 & opcao != 5 & opcao != 6 & opcao != 7) {
          opcao = 0;
        }
      } else {
        opcao = getInt("Opcao: 4 - Recarrega, 8 - Sair");
        if (opcao != 4 & opcao != 8) {
          opcao = 0;
        }
      }
    } while (opcao == 0);
    return opcao;
  }

  private int getInt(String string) {
    Scanner r = new Scanner(System.in);
    System.out.println("Entre com " + string);
    if (r.hasNextInt()) {
      return r.nextInt();
    }
    String st = r.next();
    System.out.println("Erro na Leitura de Dados");
    return 0;
  }
}
