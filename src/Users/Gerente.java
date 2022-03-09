package Users;


public class Gerente extends Funcionario 
{
    private int idAgencia;

    public Gerente() 
    {
    }

    public Gerente(String nome, int id, String cpf, String tipo,
    		String senha, int agencia, int numeroConta, 
    		double salario, int idAgencia)
    {
        super(nome, id, cpf, tipo, senha, agencia, numeroConta, salario);
        this.idAgencia = idAgencia;

    }

    @Override
    public String toString()
    {
        return "Gerente: \nNome: " + getNome() +
                "\nSalario: " + getSalario() +
                "\nIdAgencia: " + idAgencia +
                "\nNome: " + getNome() +
                "\nId: " + getId() +
                "\nCPF: " + getCpf() +
                "\nTipo: " + getTipo() +
                "\nSenha: " + getSenha() +
                "\nAgencia: " + getAgencia() +
                "\nNumeroConta: " + getNumeroConta() + "\n";
    }
}