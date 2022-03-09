package sistema;

import Users.Usuario;
import armazenamento.File;
import banco.Conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaInterno 
{

    Scanner sc = new Scanner(System.in);


    public static void limpaTela(int numeroLinhas)
    {
        for (int i = 0; i < numeroLinhas; i++) 
            System.out.println("\n");
        
    }

    // menu principal
    public void menuPrincipal() throws InterruptedException, IOException 
    {
        //String nome, int id, String cpf, String tipo, String senha, int agencia

        System.out.println(File.logoPinguinP());
        Thread.sleep(2500);
        limpaTela(20);

        login();
    }

    //Login menu

    public void login() throws InterruptedException, IOException, InputMismatchException
    {


        System.out.print("=============================================");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("Bem vindo ao Login do Banco Terebank!\n");
        System.out.print("\n");
        String opcao;
        
        while (true)
        {
            System.out.println("\n        ===========================");
            System.out.println("      |  1 - LOGIN                  |");
            System.out.println("      |  0 - SAIR                   |");
            System.out.println("        ===========================\n");
            System.out.print("\n");
            System.out.print("OP��O --> ");

            opcao = sc.next();


            switch (opcao)
            {

                case "1":
                    System.out.print("Digite o seu CPF: ");
                    String cpf = sc.next();
                    System.out.print("Digite sua senha: ");
                    String senha = sc.next();


                    Usuario usuario = verificaLogin(cpf, senha);
                    Conta contaUsuario = pegaConta(cpf);
                    if (usuario != null) 
                    {
                        System.out.print("Login efetuado! Bem vindo!");
                        System.out.print("\n");
                        System.out.print("Quase l�, voc� ser� redirecionado...");
                        Thread.sleep(2000);
                        System.out.print("\n");
                        //for each para pegar as contas

                        menuPorTipo(usuario, contaUsuario);

                    } else 
                        System.out.print("Usu�rio n�o reconhecido, tente novamente\n");
                    break;
                case "0":
                    System.out.print("At� logo!\n");
                    System.exit(Integer.parseInt(opcao));

                default:
                    System.out.println("\n\nDigite op��o 0 ou 1");
                    break;

            }
        }
    }

    // menu opera��es de conta
    public void menuOpConta(Usuario usuario, Conta contaUsuario) throws InterruptedException, IOException, NullPointerException 
    {
        int opcao;
        do 
        {
            System.out.println("\n\n BANCO TEREBANK - MOVIMENTA��ES DE CONTA:");
            System.out.println("\n                   ======================");
            System.out.println("                  |  1 - SAQUE           |");
            System.out.println("                  |  2 - DEP�SITO        |");
            System.out.println("                  |  3 - TRANSFER�NCIA   |");
            System.out.println("                  |  4 - MENU ANTERIOR   |");
            System.out.println("                  |  0 - SAIR            |");
            System.out.println("                   ======================\n");
            System.out.print("OP��O --> ");
            opcao = sc.nextInt();
            
            switch (opcao)
            {
                case 1:
                    //SAQUE;
                    System.out.println("Quanto deseja sacar? ");
                    System.out.print("Valor: ");
                    double valorSaque = sc.nextDouble();
                    try 
                    {
                        contaUsuario.sacar(valorSaque);
                        File.comprovanteSaque("./temp/comprovanteDeSaque/" + usuario.getNome() + "_" + usuario.getCpf() + "_" + File.dataPath() + ".txt", usuario, contaUsuario, valorSaque);
                    } catch (NullPointerException e)
                    {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    //DEPOSITO;
                    System.out.println("Quanto deseja depositar? ");
                    System.out.print("Valor: ");
                    double valorDeposito = sc.nextDouble();
                    try 
                    {
                        contaUsuario.depositar(valorDeposito);
                        File.comprovanteDeposito("./temp/comprovanteDeDeposito/" + usuario.getNome() + "_" + usuario.getCpf() + "_" + File.dataPath() + ".txt", usuario, contaUsuario, valorDeposito);
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    //TRANSFERÊNCIAS
                    System.out.println("Quanto deseja transferir? ");
                    System.out.print("Valor: ");
                    double valorTransf = sc.nextDouble();
                    System.out.print("Informe o cpf do titular da conta para transferência: ");
                    String cpfDestino = sc.next();
                    Conta contaDest = pegaConta(cpfDestino);
                    try 
                    {
                        contaUsuario.transfere(contaDest, valorTransf);
                        File.comprovanteTransferencia("./temp/comprovanteDeTransferencia/" + usuario.getNome() + "_" + usuario.getCpf() + "_" + File.dataPath() + ".txt", usuario, contaUsuario, valorTransf, contaDest);
                    } catch (NullPointerException e) 
                    {
                        System.out.println(e.getMessage());
                    }


                    break;
                case 4:
                    menuPorTipo(usuario, contaUsuario);
                    break;
                case 0:
                    System.out.print("At� logo, " + usuario.getNome() + "!");
                    System.out.print("\n");
                    System.exit(opcao);
                default:
                    System.out.println("\n\nDigite uma das op��es listadas.");
            }


        } while (true);

    }

    // menu cliente
    public void menuCliente(Usuario usuario, Conta contaCliente) throws InterruptedException, IOException {
        String opcao;
        do 
        {
            System.out.println("\n\n BANCO TEREBANK - �REA DO CLIENTE:");
            System.out.println("\n                   =============================");
            System.out.println("                  |  1 - MOVIMENTA��ES DE CONTA  |");
            System.out.println("                  |  2 - RELAT�RIOS              |");
            System.out.println("                  |  0 - SAIR                    |");
            System.out.println("                   =============================\n");
            System.out.print("Op��o --> ");
            opcao = sc.next();
            
            switch (opcao) 
            {
                case "1":
                    //MENU DE CONTA;
                    menuOpConta(usuario, contaCliente);
                    break;
                case "2":
                    //MENU RELATORIOS;
                    menuRelatorioCliente(usuario, contaCliente);
                    break;
                case "0":
                    System.out.print("At� logo, " + usuario.getNome() + "!");
                    System.out.print("\n");
                    System.exit(Integer.parseInt(opcao));
                default:
                    System.out.println("\n\nDigite uma das op��es listadas.");

            }

        } while (true);

    }

    //menu relatorios cliente
    public void menuRelatorioCliente(Usuario usuario, Conta contaCliente) throws InterruptedException, IOException 
    {
        String opcao;
        do 
        {
            System.out.println("\n\n BANCO TEREBANK - RELAT�RIO :");
            System.out.println("\n                   ==========================================");
            System.out.println("                  |  1 - SALDO                                |");
            System.out.println("                  |  2 - TRIBUTA��O DE CONTA                  |");
            System.out.println("                  |  3 - SIMULA��O DE RENDIMENTO EM POUPANA   |");
            System.out.println("                  |  4 - MENU ANTERIOR                        |");
            System.out.println("                  |  5 - LOGIN COM OUTRA CONTA                |");
            System.out.println("                  |  0 - SAIR                                 |");
            System.out.println("                   ===========================================\n");
            System.out.print("Op��o --> ");
            opcao = sc.next();
            
            switch (opcao) 
            {
                case "1":
                    File.relatorioDeSaldo(usuario, contaCliente, "./temp/relatorioSaldo/" + usuario.getNome() + "_" + contaCliente.getNumeroDaConta() + "_" + File.dataPath() + ".txt");
                    break;

                case "2":
                    //				RELATORIO TRIBUTA��O CONTA CORRENTE;
                    File.relatorioTributacao("./temp/relatorioTribut/" + usuario.getNome() + "_" + contaCliente.getNumeroDaConta() + "_" + File.dataPath() + ".txt");
                    break;

                case "3":
                    //				RELATORIO RENDIMENTO POUPAN�A;
                    System.out.print("Qual valor voc� deseja simular? ");
                    double valor = sc.nextDouble();
                    System.out.print("Qual a dura��o do investimento? ");
                    int dias = sc.nextInt();
                    File.relatorioSimulacaoRendimento(valor, dias, "./temp/relatorioSimulacaoRendimento/" + usuario.getNome() + "_" + File.dataPath() + ".txt");
                    break;
                case "4":
                    menuPorTipo(usuario, contaCliente);
                    break;

                case "5":
                    //VOLTA MENU PRINC.
                    menuPrincipal();
                    break;
                case "0":
                    System.out.print("At� logo, " + usuario.getNome() + "!");
                    System.out.print("\n");
                    System.exit(Integer.parseInt(opcao));
                default:
                    System.out.println("\n\nDigite uma das opções listadas.");

            }

        } while (true);

    }

    // menu gerente
    public void menuGerente(Usuario usuario, Conta contaUsuario) throws InterruptedException, IOException {
        String opcao;
        do 
        {
            System.out.println("\n\n BANCO TEREBANK - �REA DO GERENTE:");
            System.out.println("\n       =============================");
            System.out.println("       |  1 - MOVIMENTA��ES DE CONTA |");
            System.out.println("       |  2 - RELAT�RIOS             |");
            System.out.println("       |  0 - SAIR                   |");
            System.out.println("       =============================\n");
            System.out.print("Op��o --> ");
            opcao = sc.next();
            
            switch (opcao) 
            {
                case "1":
                    //MENU DE CONTA;
                    menuOpConta(usuario, contaUsuario);
                    break;
                case "2":
                    //MENU RELATORIOS;
                    menuRelatorioGerente(usuario, contaUsuario);
                    break;
                case "0":
                    System.out.print("At� logo, " + usuario.getNome() + "!");
                    System.out.print("\n");
                    System.exit(Integer.parseInt(opcao));
                default:
                    System.out.println("\n\nDigite uma das op��es listadas.");

            }

        } while (true);

    }

    //menu relatorios gerente
    public void menuRelatorioGerente(Usuario usuario, Conta contaGerente) throws InterruptedException, IOException 
    {
        String opcao;
        do 
        {
            System.out.println("\n\nBANCO TEREBANK - RELAT�RIO GERENTE :");
            System.out.println("\n                  ==========================================");
            System.out.println("                  |  1 - SALDO                               |");
            System.out.println("                  |  2 - TRIBUTA��O DE CONTA                 |");
            System.out.println("                  |  3 - SIMULA��O DE RENDIMENTO EM POUPAN�A |");
            System.out.println("                  |  4 - CONTAS NA MESMA AG�NCIA             |");
            System.out.println("                  |  5 - MENU ANTERIOR                       |");
            System.out.println("                  |  6 - LOGIN COM OUTRA CONTA               |");
            System.out.println("                  |  0 - SAIR                                |");
            System.out.println("                   ==========================================\n");
            System.out.print("Op��o --> ");
            opcao = sc.next();
            
            switch (opcao) 
            {
                case "1":
                    File.relatorioDeSaldo(usuario, contaGerente, "./temp/relatorioSaldo/" + usuario.getNome() + "_" + contaGerente.getNumeroDaConta() + "_" + File.dataPath() + ".txt");
                    break;

                case "2":
                    //				RELATORIO TRIBUTA��O CONTA CORRENTE;
                    File.relatorioTributacao("./temp/relatorioTribut/" + usuario.getNome() + "_" + contaGerente.getNumeroDaConta() + "_" + File.dataPath() + ".txt");
                    break;

                case "3":
                    //				RELATORIO RENDIMENTO POUPAN�A;
                    System.out.print("Qual valor voc� deseja simular? ");
                    double valor = sc.nextDouble();
                    System.out.print("Qual a dura��o do investimento? ");
                    int dias = sc.nextInt();
                    File.relatorioSimulacaoRendimento(valor, dias, "./temp/relatorioSimulacaoRendimento/" + usuario.getNome() + "_" + File.dataPath() + ".txt");
                    break;

                case "4":
                    //				Relat�rio de contas na mesma agência em que este gerente trabalha

                    File.pegaContasAgencia(usuario.getAgencia(), "./temp/relatorioGerenteAg/" + usuario.getAgencia() + "_" + File.dataPath() + ".txt");

                    break;

                case "5":
                    menuPorTipo(usuario, contaGerente);
                    break;
                case "6":
                    menuPrincipal();
                    break;
                case "0":
                    System.out.print("Até logo, " + usuario.getNome() + "!");
                    System.out.print("\n");
                    System.exit(Integer.parseInt(opcao));
                default:
                    System.out.println("\n\nDigite uma das opções listadas.");

            }

        } while (true);

    }

    // menu diretor
    public void menuDiretor(Usuario usuario, Conta contaUsuario) throws InterruptedException, IOException 
    {

        String opcao;
        do 
        {
            System.out.println("\n\n BANCO TEREBANK - �REA DO DIRETOR:");
            System.out.println("\n                   =============================");
            System.out.println("                  |  1 - MOVIMENTA��ES DE CONTA |");
            System.out.println("                  |  2 - RELAT�RIOS             |");
            System.out.println("                  |  0 - SAIR                   |");
            System.out.println("                   =============================\n");
            System.out.print("Op��o --> ");
            opcao = sc.next();
            
            switch (opcao) 
            {
                case "1":
                    menuOpConta(usuario, contaUsuario);
                    break;

                case "2":
                    menuRelatorioDiretor(usuario, contaUsuario);
                    break;

                case "0":
                    System.out.print("At� logo, " + usuario.getNome() + "!");
                    System.out.print("\n");
                    System.exit(Integer.parseInt(opcao));
                default:
                    System.out.println("\n\nDigite uma das op��es listadas.");

            }

        } while (true);

    }

    // menu relatorio diretor
    public void menuRelatorioDiretor(Usuario usuario, Conta contaDiretor) throws InterruptedException, IOException 
    {
        String opcao;
        do 
        {
            System.out.println("\n\n BANCO TEREBANK - RELAT�RIO DIRETOR :");
            System.out.println("\n                  ==========================================");
            System.out.println("                  |  1 - SALDO                               |");
            System.out.println("                  |  2 - TRIBUTA��O DE CONTA                 |");
            System.out.println("                  |  3 - SIMULA��O DE RENDIMENTO EM POUPAN�A |");
            System.out.println("                  |  4 - RELAT�RIO DE CLIENTES DO BANCO      |");
            System.out.println("                  |  5 - RELAT�RIO DE CLIENTES POR AG�NCIA   |");
            System.out.println("                  |  6 - MENU ANTERIOR                       |");
            System.out.println("                  |  7 - LOGIN COM OUTRA CONTA               |");
            System.out.println("                  |  0 - SAIR                                |");
            System.out.println("                   ==========================================\n");
            System.out.print("Op��o --> ");
            opcao = sc.next();
            
            switch (opcao)
            {
                case "1":
                    File.relatorioDeSaldo(usuario, contaDiretor, "./temp/relatorioSaldo/" + usuario.getNome() + "_" + contaDiretor.getNumeroDaConta() + "_" + File.dataPath() + ".txt");
                    break;

                case "2":
                    //				RELAT�RIO TRIBUTA��O CONTA CORRENTE;
                    File.relatorioTributacao("./temp/relatorioTribut/" + usuario.getNome() + "_" + contaDiretor.getNumeroDaConta() + "_" + File.dataPath() + ".txt");
                    break;

                case "3":
                    //				RELAT�RIO RENDIMENTO POUPAN�A;
                    System.out.print("Qual valor voc� deseja simular? ");
                    double valor = sc.nextDouble();
                    System.out.print("Qual a duração do investimento? ");
                    int dias = sc.nextInt();
                    File.relatorioSimulacaoRendimento(valor, dias, "./temp/relatorioSimulacaoRendimento/" + usuario.getNome() + "_" + File.dataPath() + ".txt");
                    break;

                case "4":
                    File.nomesOrdemAlfabetica("./temp/relatorioClientesOrdem/clientesOrdemAlfabetica" + File.dataPath() + ".txt");
                    break;

                case "5":
                    System.out.print("Informe o n�mero da ag�ncia para consulta: ");
                    int agencia = sc.nextInt();
                    File.pegaContasAgencia(agencia, "./temp/relatorioDiretorAg/" + agencia + "_" + File.dataPath() + ".txt");
                    break;

                case "6":
                    menuPorTipo(usuario, contaDiretor);
                    break;

                case "7":
                    //VOLTA MENU PRINC.
                    menuPrincipal();
                    break;
                case "0":
                    System.out.print("At� logo, " + usuario.getNome() + "!");
                    System.out.print("\n");
                    System.exit(Integer.parseInt(opcao));
                default:
                    System.out.println("\n\nDigite uma das op��es listadas.");

            }

        } while (true);

    }

    // menu area presidente
    public void menuPresidente(Usuario usuario, Conta contaPresidente) throws InterruptedException, IOException {
        String opcao;
        do {
            System.out.println("\n\n BANCO TEREBANK - �REA DO PRESIDENTE:");
            System.out.println("\n                   =============================");
            System.out.println("                  |  1 - MOVIMENTA��ES DE CONTA |");
            System.out.println("                  |  2 - RELAT�RIOS             |");
            System.out.println("                  |  0 - SAIR                   |");
            System.out.println("                   =============================\n");
            System.out.print("Op��o --> ");
            opcao = sc.next();
            
            switch (opcao) 
            {
                case "1":
                    menuOpConta(usuario, contaPresidente);
                    break;
                case "2":
                    //RELAT�RIO PRESIDENTE
                    menuRelatorioPresidente(usuario, contaPresidente);
                    break;

                case "0":
                    System.out.print("At� logo, " + usuario.getNome() + "!");
                    System.out.print("\n");
                    System.exit(Integer.parseInt(opcao));
                default:
                    System.out.println("\n\nDigite uma das op��es listadas.");

            }

        } while (true);

    }

    // menu relat�rio Presidente
    public void menuRelatorioPresidente(Usuario usuario, Conta contaPresidente) throws InterruptedException, IOException 
    {
        String opcao;
        do {
            System.out.println("\n\n BANCO TEREBANK - RELAT�RIO PRESIDENTE :");
            System.out.println("\n                  ==========================================");
            System.out.println("                  |  1 - SALDO                               |");
            System.out.println("                  |  2 - TRIBUTA��O DE CONTA                 |");
            System.out.println("                  |  3 - SIMULA��O DE RENDIMENTO EM POUPAN�A |");
            System.out.println("                  |  4 - RELAT�RIO DE CLIENTES DO BANCO      |");
            System.out.println("                  |  5 - RELAT�RIO DE CLIENTES POR AG�NCIA   |");
            System.out.println("                  |  6 - RELAT�RIO DE CAPITAL TOTAL          |");
            System.out.println("                  |  7 - MENU ANTERIOR                       |");
            System.out.println("                  |  8 - LOGIN COM OUTRA CONTA               |");
            System.out.println("                  |  0 - SAIR                                |");
            System.out.println("                   ==========================================\n");
            System.out.print("Op��o --> ");
            opcao = sc.next();
            
            switch (opcao) 
            {
                case "1":
                    File.relatorioDeSaldo(usuario, contaPresidente, "./temp/relatorioSaldo/" + usuario.getNome() + "_" + contaPresidente.getNumeroDaConta() + "_" + File.dataPath() + ".txt");
                    break;

                case "2":
                    //				RELATORIO TRIBUTA��O CONTA;
                    File.relatorioTributacao("./temp/relatorioTribut/" + usuario.getNome() + "_" + contaPresidente.getNumeroDaConta() + "_" + File.dataPath() + ".txt");
                    break;

                case "3":
                    //				RELATORIO RENDIMENTO POUPANÇA;
                    System.out.print("Qual valor voc� deseja simular? ");
                    double valor = sc.nextDouble();
                    System.out.print("Qual a dura��o do investimento? ");
                    int dias = sc.nextInt();
                    File.relatorioSimulacaoRendimento(valor, dias, "./temp/relatorioSimulacaoRendimento/" + usuario.getNome() + "_" + File.dataPath() + ".txt");
                    break;

                case "4":
                    File.nomesOrdemAlfabetica("./temp/relatorioClientesOrdem/clientesOrdemAlfabetica" + File.dataPath() + ".txt");
                    break;

                case "5":
                    System.out.print("Informe o número da agência para consulta: ");
                    int agencia = sc.nextInt();
                    File.pegaContasAgencia(agencia, "./temp/relatorioPresidenteAg/" + agencia + "_" + File.dataPath() + ".txt");
                    break;

                case "6":
                    // 			RELATORIO TOTAL DE CAPITAL // Ricardo vai colocar data
                    File.capitalTotal("./temp/relatorioCapitalTotal/BancoDosPinguins" + File.dataPath() + ".txt");
                    break;

                case "7":
                    menuPorTipo(usuario, contaPresidente);
                    break;

                case "8":
                    //VOLTA MENU PRINC.
                    menuPrincipal();
                    break;
                case "0":
                    System.out.print("At� logo, " + usuario.getNome() + "!");
                    System.out.print("\n");
                    System.exit(Integer.parseInt(opcao));
                default:
                    System.out.println("\n\nDigite uma das op��es listadas.");

            }

        } while (true);

    }

    //M�todo verificar login
    public Usuario verificaLogin(String cpf, String senha) 
    {
        for (Usuario user : File.getMapUsuario().values()) 
            if ((user.getCpf().equalsIgnoreCase(cpf)) && (user.getSenha().equalsIgnoreCase(senha))) 
                return user;
            
        
        return null;
    }

    // M�todo pega conta
    public Conta pegaConta(String cpf) 
    {
        for (Conta conta : File.getMapConta().values()) 
            if (conta.getCpfTitular().equalsIgnoreCase(cpf)) 
                return conta;
            
        
        return null;
    }

    public void menuPorTipo(Usuario usuario, Conta contaUsuario) throws InterruptedException, IOException 
    {
        if (usuario.getTipo().equalsIgnoreCase("diretor")) 
            menuDiretor(usuario, contaUsuario);
        else if (usuario.getTipo().equalsIgnoreCase("cliente")) 
            menuCliente(usuario, contaUsuario);
        else if (usuario.getTipo().equalsIgnoreCase("presidente")) 
            menuPresidente(usuario, contaUsuario);
        else if (usuario.getTipo().equalsIgnoreCase("gerente")) 
            menuGerente(usuario, contaUsuario);
        
    }

}



