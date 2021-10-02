package TerminalDeCaixaEletronico;

public class Caixa {
    private Terminal meuTerminal;
    private CadastroContas bdContas;
    private double saldo;

    public Caixa(Terminal terminal, CadastroContas bd) {
        this.meuTerminal = terminal;
        this.bdContas = bd;
    }

    public double consultaSaldo(int numeroDaConta, int senha) throws ContaInvalidaException,SenhaInvalidaException{

        Conta conta;
        conta = this.bdContas.buscaConta(numeroDaConta);

        if (conta != null) {
            return conta.verificaSaldo(senha);
        }

        throw new ContaInvalidaException("Conta Inválida!");
    }


    public boolean efetuaSaque(int numeroDaConta, double valor, int senha) throws ContaInvalidaException, SenhaInvalidaException, SaldoInsuficienteException, ValorNegativoException{
        if((valor%50) != 0 || valor > 500 || valor > this.saldo) {
            throw new SaldoInsuficienteException("Saldo Insuficiente!");
        } else if(valor <0) {
            throw new ValorNegativoException("Valor solicitado para saque é negativo!");
        }
        Conta conta = bdContas.buscaConta(numeroDaConta);
        if (conta == null || !conta.debitaValor(valor, senha, "Saque Automatico")) {
            throw new ContaInvalidaException("Conta Inválida!");
        }

        this.liberaCedulas((int)(valor/50));
        this.saldo -= valor;
        if(this.saldo < 500) {
            this.meuTerminal.setModo(0);
        }
        return true;
    }

    public void recarrega() {
        this.saldo = 2000;
        this.meuTerminal.setModo(1);
    }

    private void liberaCedulas(int quantidade) {
        while(quantidade-- > 0) {
            System.out.println("===/ R$50,00 /===");
        }
    }

    public boolean efetuaTransferencia(int numContaAtual, int senha, double valor, int numContaDestino) throws ContaInvalidaException,SenhaInvalidaException, SaldoInsuficienteException,ValorNegativoException{
        Conta conta;
        conta = this.bdContas.buscaConta(numContaAtual);
        if(conta != null) {
            Conta contadestino;
            contadestino = this.bdContas.buscaConta(numContaDestino);
            if(contadestino != null) {
                if(conta.debitaValor(valor,senha,"Transferência")) {
                    contadestino.creditaValor(valor,"Transferência ");
                    return true;
                } else {
                    throw new ValorNegativoException("O valor solicitado é negativo!");
                }
            } else {
                throw new ContaInvalidaException("Conta do Destinatário Inválida!");
            }
        } else {
            throw new ContaInvalidaException("Conta do Remetente Inválida!");
        }
    }

    public String consultarExtratoBancario(int numConta) throws ContaInvalidaException{
        Conta conta;
        conta = this.bdContas.buscaConta(numConta);

        if(conta != null) {
            return conta.getHistorico().geraHistoricoDeLancamentos();

        } else {
            throw new ContaInvalidaException("Conta Inválida!");
        }
    }
    public boolean depositar(int numConta,double valor,String operacaoBancaria) throws ContaInvalidaException, ValorNegativoException{
        Conta conta;
        conta = this.bdContas.buscaConta(numConta);
        if(conta != null) {
            if(valor < 0) {
                throw new ValorNegativoException("Valor solicitado para" + operacaoBancaria + " é negativo!");
            } else {
                conta.creditaValor(valor,operacaoBancaria);
            }
        } else {
            throw new ContaInvalidaException("Conta para " + operacaoBancaria + "Inválida!");
        }
        return true;
    }

    public boolean depositar(int numConta,double valor,int numero,String operacaoBancaria) throws ContaInvalidaException, ValorNegativoException{
        Conta conta;
        conta = this.bdContas.buscaConta(numConta);
        if(conta != null) {
            if(valor < 0) {
                throw new ValorNegativoException("Valor solicitado para" + operacaoBancaria + " é negativo!");
            } else {
                conta.creditaValor(valor,operacaoBancaria);
            }
        } else {
            throw new ContaInvalidaException("Conta para " + operacaoBancaria + "Inválida!");
        }
        return true;
    }
}

