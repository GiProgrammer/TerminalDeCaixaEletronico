package TerminalDeCaixaEletronico;


public class Conta {
    private int numero;
    private int senha;
    private Cliente titular;
    private double saldo;
    private HistoricoDeLancamentos historico;

    public Conta(int numero, Cliente titular, int senha, double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.senha = senha;
        this.saldo = saldo;
        this.historico = new HistoricoDeLancamentos();
    }

    public int getNumero() {
        return this.numero;
    }

    public Cliente getTitular() {
        return this.titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public double verificaSaldo(int senha) throws SenhaInvalidaException {
        if (senhaEhValida(senha)) {
            return this.saldo;
        }
        throw new SenhaInvalidaException("Senha Incorreta!");
    }

    public boolean debitaValor(double valor, int senha, String operacaoBancaria) throws SenhaInvalidaException, SaldoInsuficienteException,ValorNegativoException{
        if (!senhaEhValida(senha) | valor > this.saldo) {
            throw new SaldoInsuficienteException("Saldo Insuficiente!");

        } else if(valor < 0) {
            throw new ValorNegativoException("Valor solicitado para " + operacaoBancaria + " é negativo!");
        }

        this.saldo -= valor;
        this.historico.insereLancamento(new Lancamento(operacaoBancaria, -valor));
        return true;
    }

    public boolean creditaValor(double valor, String operacaoBancaria) throws ValorNegativoException{
        if (valor < 0) {
            throw new ValorNegativoException("Valor solicitado para " + operacaoBancaria + " é negativo!");
        }
        this.saldo += valor;
        this.historico.insereLancamento(new Lancamento(operacaoBancaria, valor));
        return true;
    }

    private boolean senhaEhValida(int senha) throws SenhaInvalidaException {
        if(senha == this.senha) {
            return this.senha == senha;
        }
        else {
            throw new SenhaInvalidaException("Senha Inválida!");
        }
    }
    public HistoricoDeLancamentos getHistorico() {
        return this.historico;
    }
}
