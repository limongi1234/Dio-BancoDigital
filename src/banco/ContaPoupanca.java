package banco;

public class ContaPoupanca extends Conta 
{

    public ContaPoupanca() 
    {
    }


    public ContaPoupanca(String tipoConta, int numeroDaConta, String nomeCompletoCliente, 
    		String cpfTitular, double saldo, int agencia) 
    {
        super(tipoConta, numeroDaConta, nomeCompletoCliente, 
        		cpfTitular, saldo, agencia);

    }


    @Override
    public String toString() 
    {
        return "Conta Poupança: \nTipo Conta: " + getTipoConta() +
                "\nNumero da Conta =  " + getNumeroDaConta() +
                "\nAgencia= " + getAgencia() +
                "\nNome Completo do Cliente=   " + getNomeCompletoCliente() +
                "\nCPF   do  Titular= " + getCpfTitular() +
                "\nSaldo da Conta poupança= R$" + getSaldo() + "\n";
    }
}


