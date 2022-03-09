package banco;

import java.text.DecimalFormat;

public abstract class Conta 
{

    protected static double taxaMovimentacao = 0.10, tarifa = 15;
    private static int totalMovimentacoes, totalTransferencia;
    public String tipoConta, nomeCompletoCliente, cpfTitular;
    private int numeroDaConta, agencia;
    private double saldo;
    private static final double taxaTransferencia = 0.20;

    public Conta() 
    {
    }

    public Conta(String tipoConta, int numeroDaConta, String nomeCompletoCliente, String cpfTitular, double saldo, int agencia)
    {
        this.tipoConta = tipoConta;
        this.numeroDaConta = numeroDaConta;
        this.nomeCompletoCliente = nomeCompletoCliente;
        this.cpfTitular = cpfTitular;
        this.saldo = saldo;
        this.agencia = agencia;

    }


    //*****************************************************************************************************************
    //            saque

    public static int getTotalMovimentacoes()
    {
        return totalMovimentacoes;
    }

//********************************************************************************************************************
    //   TRANSFERENCIA
    //   os 0,20 centavos que cobra da transferencia sera retidado via taxa deposito e saque

    public static double getTarifa() 
    {
        return tarifa;
    }

    public static double getTaxaMovimentacao() 
    {
        return taxaMovimentacao;
    }

    public boolean sacar(double valor) 
    {
        if (valor <= 0) 
        {
            System.out.println("Valor inválido para saque.");
            return false;
        } else if (this.saldo < valor) 
          {

            System.out.println("Seu saldo é insuficiente!!!");
            return false;
          } else 
          {
            totalTransferencia++;
            this.saldo = this.saldo - valor;
            this.saldo = this.saldo - taxaMovimentacao;    // taxa cobrada por saque
            System.out.println("Saque realizado com sucesso!!!");
            return true;
          }
    }

    public boolean transfere(Conta destino, double valor) 
    {

        if (this.numeroDaConta == destino.numeroDaConta) 
        {
            System.out.println("Transferência não realizada!");
            System.out.println("A conta destino é igual à conta origem.");
            return false;
        } else 
        {
            if (valor <= 0) 
            {
                System.out.println("Valor inv�lido para transfer�ncia.");
                System.out.println("Por favor, revise o valor desejado e tente novamente.");
                return false;
            } else if (this.saldo < valor) 
            {
                System.out.println("Seu saldo � insuficiente!!!");
                System.out.println("Transfer�ncia n�o realizada");
                return false;
            } else 
            {
                this.saldo = this.saldo - valor;
                this.saldo = this.saldo - taxaTransferencia;
                destino.saldo = destino.saldo + valor;
                totalMovimentacoes++;
                System.out.println("Transfer�ncia realizada com sucesso!!!");
                return true;
            }
        }
    }

    public boolean depositar(double valor)
    {
        totalMovimentacoes++;
        if (valor <= 0) 
        {
            System.out.println("Depósito com envelope vazio é proibido!!! Depósito com R$ " + valor + " é inválido.");
            System.out.println("Depósitos vazios geram despesas e, convencionalmente, será cobrada tarifa de depósito.");
            this.saldo -= taxaMovimentacao;
            return true;
        } else 
        {

            this.saldo = this.saldo + valor;
            this.saldo = this.saldo - taxaMovimentacao;  // taxa cobrada por deposito
            System.out.println("Depósito realizado com sucesso!!!");
            return false;

        }
    }

    public String getTipoConta()
    {
        return tipoConta;
    }

    public int getNumeroDaConta()
    {
        return numeroDaConta;
    }

    public String getNomeCompletoCliente()
    {
        return nomeCompletoCliente;
    }

    public String getCpfTitular() 
    {
        return cpfTitular;
    }

    public double getSaldo() 
    {
        new DecimalFormat("#,##0.00").format(saldo);
        return saldo;
    }

    public int getAgencia() 
    {
        return agencia;
    }

    public static int getTotalTransferencia() 
    {
        return totalTransferencia;
    }

    public static double getTaxaTransferencia() 
    {
        return taxaTransferencia;
    }
}