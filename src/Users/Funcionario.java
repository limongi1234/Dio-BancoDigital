package Users;


public abstract class Funcionario extends Usuario 
{
    private double salario;


    public Funcionario() 
    {
    }

    public Funcionario(String nome, int id, String cpf, String tipo,
    		String senha, int agencia, int numeroConta, double salario)
    {
        super(nome, id, cpf, tipo, senha, agencia, numeroConta);
        this.salario = salario;
    }

    public double getSalario() 
    {
        return salario;
    }
}
