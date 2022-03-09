package armazenamento;


import Users.*;
import banco.Conta;
import banco.ContaCorrente;
import banco.ContaPoupanca;
import enumm.ContaTipoEnum;
import enumm.UsuarioTipoEnum;

import java.io.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class File 
{

    static final String PATH_BASICO = "./temp/";
    static final String EXTENSAO = ".txt";

    static Map <String, Usuario> mapUsuario = new HashMap <>();
    static Map <String, Conta> mapConta = new HashMap <>();

    public static Map <String, Usuario> getMapUsuario()
    {
        return mapUsuario;
    }

    public static Map<String, Conta> getMapConta()
    {
        return mapConta;
    }

    public static void hashMapFromFileTextUsuario(String path) 
    {

        try {
            BufferedReader buffRead = new BufferedReader(new FileReader(PATH_BASICO + "lista" + EXTENSAO));
            String linha = "";

            while (true)
            {
                linha = buffRead.readLine();
                if (linha != null)
                {
                    String [] pp = linha.split(";");

                    if (pp[3].equalsIgnoreCase(UsuarioTipoEnum.DIRETOR.getTipo())) 
                    {
                        Diretor diretor = new Diretor(pp[0], Integer.parseInt(pp[1]), pp[2], 
                        		pp[3], pp[4],Integer.parseInt(pp[5]),
                        		Integer.parseInt(pp[6]), Double.parseDouble(pp[7]));
                        
                        String cpf = pp[2];
                        
                        mapUsuario.put(cpf, diretor);
                        //String nome, int id, String cpf, String tipo, String senha, int agencia, int numeroConta, double salario
                    } else if (pp[3].equalsIgnoreCase(UsuarioTipoEnum.CLIENTE.getTipo()))
                    {
                        Cliente cliente = new Cliente(pp[0], 
                        		Integer.parseInt(pp[1]), pp[2], 
                        		pp[3], pp[4], Integer.parseInt(pp[5]), 
                        		Integer.parseInt(pp[6]));
                        
                        String cpf = pp[2];
                        
                        mapUsuario.put(cpf, cliente);
                    } else if (pp[3].equalsIgnoreCase(UsuarioTipoEnum.GERENTE.getTipo())) 
                    {
                        Gerente gerente = new Gerente(pp[0],
                        		Integer.parseInt(pp[1]), pp[2],
                        		pp[3], pp[4], Integer.parseInt(pp[5]), 
                        		Integer.parseInt(pp[6]), 
                        		Double.parseDouble(pp[7]), 
                        		Integer.parseInt(pp[8]));
                        //String nome, int id, String cpf, String tipo, String senha, int agencia, int numeroConta, double salario, int idAgencia
                        String cpf = pp[2];
                        
                        mapUsuario.put(cpf, gerente);
                    } else if (pp[3].equalsIgnoreCase(UsuarioTipoEnum.PRESIDENTE.getTipo())) 
                    {
                        Presidente presidente = new Presidente(pp[0], 
                        		Integer.parseInt(pp[1]), pp[2], 
                        		pp[3], pp[4], Integer.parseInt(pp[5]), 
                        		Integer.parseInt(pp[6]), Double.parseDouble(pp[7]));
                        //String nome, int id, String cpf, String tipo, String senha, int agencia, int numeroConta, double salario

                        String cpf = pp[2];
                        mapUsuario.put(cpf, presidente);
                    }

                } else
                    break;
            }
            buffRead.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void hashMapFromFileTextContas(String path) 
    {
        try 
        {
            BufferedReader buffRead = new BufferedReader(new FileReader(PATH_BASICO + "contas" + EXTENSAO));
            String linha = "";

            while (true) 
            {
                linha = buffRead.readLine();
                if (linha != null) 
                {
                    String[] pp = linha.split(";");
                    if (pp[0].equalsIgnoreCase(ContaTipoEnum.CORRENTE.getTipo())) 
                    {
                        ContaCorrente contaCorrente = new ContaCorrente(pp[0], 
                        		Integer.parseInt(pp[1]), 
                        		pp[2], pp[3],
                        		Double.parseDouble(pp[4]), 
                        		Integer.parseInt(pp[5]));
                        
                        String cpf = pp[3];
                        
                        mapConta.put(cpf, contaCorrente);
//                            (int numeroDaConta, String nomeCompletoCliente, String cpfTitular, double saldo, int agencia,String tipoConta)
                    } else if (pp[0].equalsIgnoreCase(ContaTipoEnum.POUPANCA.getTipo()))
                    {
                        ContaPoupanca contaPoupanca = new ContaPoupanca(pp[0],
                        		Integer.parseInt(pp[1]),
                        		pp[2], pp[3],
                        		Double.parseDouble(pp[4]),
                        		Integer.parseInt(pp[5]));
                        
                        String cpf = pp[3];
                        
                        mapConta.put(cpf, contaPoupanca);
//                          (String tipoConta, int numeroDaConta,String nomeCompletoCliente,String cpfTitular,double saldo,int agencia)
                    }

                } else
                    break;
            }
            buffRead.close();

        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public static void comprovanteDeposito(String path, Usuario usuario, Conta numeroConta, double valorDeposito) throws IOException 
    {

        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        String linha = "******************DepÃ³sito*************************";
        buffWrite.append(linha).append("\n\n\n");

        linha = dataTela();
        buffWrite.append("\t").append(linha).append("\n\n");

        linha = "Nome do Titular: " + numeroConta.getNomeCompletoCliente();
        buffWrite.append("\t").append(linha).append("\n");

        linha = "CPF do Titular: " + usuario.getCpf();
        buffWrite.append("\t").append(linha).append("\n");

        linha = "Agência: " + usuario.getAgencia();
        buffWrite.append("\t").append(linha).append("\n");

        linha = "Conta: " + numeroConta.getNumeroDaConta();
        buffWrite.append("\t").append(linha).append("\n");

        linha = "Valor do Depósito: R$ " + valorDeposito;
        buffWrite.append("\t").append(linha).append("\n");

        linha = "******************Fim do Depósito******************";
        buffWrite.append("\n\n").append(linha);

        linha = logoPinguinP();
        buffWrite.append(linha).append("\n");


        buffWrite.close();
    }


    public static void comprovanteSaque(String path, Usuario usuario, Conta numeroConta, double valorSaque) throws IOException 
    {

        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        String linha = "******************Saque******************";
        buffWrite.append(linha).append("\n\n\n");

        linha = dataTela();
        buffWrite.append("\t").append(linha).append("\n\n");

        linha = "Nome do Titular: " + numeroConta.getNomeCompletoCliente();
        buffWrite.append("\t").append(linha).append("\n");

        linha = "CPF do Titular: " + usuario.getCpf();
        buffWrite.append("\t").append(linha).append("\n");

        linha = "Agência: " + usuario.getAgencia();
        buffWrite.append("\t").append(linha).append("\n");

        linha = "Conta: " + numeroConta.getNumeroDaConta();
        buffWrite.append("\t").append(linha).append("\n");

        linha = "Saque: R$" + valorSaque;
        buffWrite.append("\t").append(linha).append("\n");

        linha = "******************Fim do Saque******************";
        buffWrite.append("\n\n").append(linha);

        linha = logoPinguinP();
        buffWrite.append(linha).append("\n");


        buffWrite.close();
    }

    public static void comprovanteTransferencia(String path, Usuario usuario, Conta numeroConta, double valorTransferencia, Conta numContaDestino) throws IOException 
    {

        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        String linha = "************************Transferência************************";
        buffWrite.append(linha).append("\n\n\n");

        linha = dataTela();
        buffWrite.append("\t").append(linha).append("\n\n");

        linha = "Nome do Titular (Conta Origem): " + numeroConta.getNomeCompletoCliente();
        buffWrite.append("\t").append(linha).append("\n");

        linha = "CPF do Titular (Conta Origem): " + usuario.getCpf();
        buffWrite.append("\t").append(linha).append("\n");

        linha = "Conta (Origem): " + numeroConta.getNumeroDaConta();
        buffWrite.append("\t").append(linha).append("\n");

        linha = "Agência (Origem): " + usuario.getAgencia();
        buffWrite.append("\t").append(linha).append("\n");

        linha = "Agência (Origem): " + numeroConta.getAgencia();
        buffWrite.append("\t").append(linha).append("\n\n");

        linha = "Nome do Titular (Conta Destino): " + numContaDestino.getNomeCompletoCliente();
        buffWrite.append("\t").append(linha).append("\n");

        linha = "CPF do Titular (Conta Destino): " + numContaDestino.getCpfTitular();
        buffWrite.append("\t").append(linha).append("\n");

        linha = "Conta (Destino): " + numContaDestino.getNumeroDaConta();
        buffWrite.append("\t").append(linha).append("\n");

        linha = "Agência (Destino): " + numContaDestino.getAgencia();
        buffWrite.append("\t").append(linha).append("\n\n");

        linha = "Valor Transferido: R$" + valorTransferencia;
        buffWrite.append("\t").append(linha).append("\n");

        linha = "********************Fim da TransferÃªncia*********************";
        buffWrite.append("\n\n").append(linha);

        linha = logoPinguinP();
        buffWrite.append(linha).append("\n");


        buffWrite.close();
    }

    public static void pegaContasAgencia(int numeroAgencia, String path) throws IOException 
    {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        int totalContas = 0;

        System.out.println("*****************Relatório de Contas da Agência " + numeroAgencia + "******************\n\n");
        String linha = "*****************Relatório de Contas da Agência " + numeroAgencia + "******************\n\n";
        buffWrite.append(linha).append("\n");

        linha = dataTela();
        buffWrite.append("\t\t").append(linha).append("\n\n");

        for (Conta conta : File.getMapConta().values()) 
        {
            if (conta.getAgencia() == numeroAgencia)
            {
                totalContas++;

                System.out.println("\t\t\t\t\t\tTitular: " + conta.getNomeCompletoCliente());
                linha = "\t\t\tTitular " + conta.getNomeCompletoCliente();
                buffWrite.append(linha).append("\n");

                System.out.println("\t\t\t\t\t\tNúmero da Conta: " + conta.getNumeroDaConta());
                linha = "\t\t\tNúmero da Conta: " + conta.getNumeroDaConta();
                buffWrite.append(linha).append("\n");

                System.out.println("\t\t\t\t\t\tTipo da Conta: " + conta.getTipoConta() + "\n\n");
                linha = "\t\t\tTipo da Conta: " + conta.getTipoConta();
                buffWrite.append(linha).append("\n\n");
            }
        }
        
        System.out.println("\t\t\t\tTotal de contas registradas na agência: " + totalContas);
        linha = "\n\t\tTotal de contas registradas na agência: " + totalContas;
        buffWrite.append(linha).append("\n\n");

        System.out.println("\n**************************Fim do Relatório***************************");
        linha = "\n**************************Fim do Relatório***************************";
        buffWrite.append("\n\n").append(linha);

        linha = logoPinguinP();
        buffWrite.append(linha).append("\n");

        buffWrite.close();

    }

    public static void capitalTotal(String path) throws IOException
    {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        double totalSoma = 0;
        
        System.out.println("*****************Relatório de Capital Total do Banco dos Pinguins*****************\n\n");
        String linha = "*****************Relatório de Capital Total do Banco dos Pinguins********************\n\n";
        buffWrite.append(linha).append("\n");

        linha = dataTela();
        buffWrite.append("\t\t\t\t").append(linha).append("\n\n");

        double totalMovimentacoes = (Conta.getTotalMovimentacoes() * Conta.getTaxaMovimentacao());
        double totalSaldo = (Conta.getTotalTransferencia() * Conta.getTaxaTransferencia());
        double totalTarifa = totalSoma + totalSaldo + Conta.getTarifa();

        for (Conta conta : getMapConta().values()) 
            totalSoma = totalSoma + conta.getSaldo() + totalTarifa;
        
        System.out.println("\t\tValor do Capital Total do Banco: R$ " + new DecimalFormat("#,##0.00").format(totalSoma));
        linha = "\t\t\tValor do Capital Total do Banco " + new DecimalFormat("#,##0.00").format(totalSoma);
        buffWrite.append(linha).append("\n");

        System.out.println("\n********************************Fim do Relatório***********************************");
        linha = "\n********************************Fim do Relatório************************************";
        buffWrite.append("\n\n").append(linha);

        linha = logoPinguinP();
        buffWrite.append(linha).append("\n");

        buffWrite.close();
    }


    public static void relatorioTributacao(String path) throws IOException 
    {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        System.out.println("***********************Relatório de Tributação de Conta***********************\n\n");
        String linha = "***********************Relatório de Tributação de Conta***********************\n\n";
        buffWrite.append(linha).append("\n");

        linha = dataTela();
        buffWrite.append("\t").append(linha).append("\n\n");

        double totalMovimentacoes = (Conta.getTotalMovimentacoes() * Conta.getTaxaMovimentacao());
        double totalSaldo = (Conta.getTotalTransferencia() * Conta.getTaxaTransferencia());
        double totalTarifa = totalMovimentacoes + totalSaldo + Conta.getTarifa();

        System.out.println("\t\tO valor da tarifa cobrada é de R$ " + new DecimalFormat("#,##0.00").format(Conta.getTarifa()));
        linha = "\t\t\tO valor da tarifa cobrada é de R$ " + new DecimalFormat("#,##0.00").format(Conta.getTarifa());
        buffWrite.append(linha).append("\n");

        System.out.println("\t\tO valor total das taxas de movimentação de saldo e depósito é de R$ " + new DecimalFormat("#,##0.00").format(totalSaldo));
        linha = "\t\t\tO valor total das taxas de movimentação de saque e depósito de R$ " + new DecimalFormat("#,##0.00").format(totalSaldo);
        buffWrite.append(linha).append("\n");

        System.out.println("\t\tO valor total das taxas de transferência é de R$ " + new DecimalFormat("#,##0.00").format(totalSaldo));
        linha = "\t\t\tO valor total das taxas de movimentação é de R$ " + new DecimalFormat("#,##0.00").format(totalSaldo);
        buffWrite.append(linha).append("\n");

        System.out.println("\t\tO total das tributações até agora é de = R$ " + new DecimalFormat("#,##0.00").format(totalTarifa));
        linha = "\t\t\tO total das tributações até agora é de = R$ " + new DecimalFormat("#,##0.00").format(totalTarifa);
        buffWrite.append(linha).append("\n");

        System.out.println("\n***************************Fim do RelatÃ³rio****************************");
        linha = "\n***************************Fim do RelatÃ³rio****************************";
        buffWrite.append("\n\n").append(linha);

        linha = logoPinguinP();
        buffWrite.append(linha).append("\n");

        buffWrite.close();
    }

    public static void nomesOrdemAlfabetica(String path) throws IOException 
    {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        System.out.println("****************Relatório de Clientes em Ordem Alfabética****************\n\n");
        String linha = "****************Relatório de Clientes em Ordem Alfabética****************\n\n";
        buffWrite.append(linha).append("\n");

        linha = dataTela();
        buffWrite.append("\t\t\t").append(linha).append("\n\n");

        List <Usuario> mapValues = new ArrayList<>(getMapUsuario().values());

        mapValues.sort(Comparator.comparing(Usuario::getNome));

        for (Usuario pessoa : mapValues)
        {
            System.out.println("Nome: " + pessoa.getNome() + " | CPF: " + pessoa.getCpf() + " | Agência: " + pessoa.getAgencia());
            linha = "\tNome: " + pessoa.getNome() + " | CPF: " + pessoa.getCpf() + " | Agência: " + pessoa.getAgencia();
            buffWrite.append(linha).append("\n");
        }

        System.out.println("\n***************************Fim do Relatório****************************");
        linha = "\n***************************Fim do Relatório****************************";
        buffWrite.append("\n\n").append(linha);

        linha = logoPinguinP();
        buffWrite.append(linha).append("\n");

        buffWrite.close();
    }

    public static void relatorioDeSaldo(Usuario usuario, Conta conta, String path) throws IOException 
    {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        System.out.println("********************Relatório de Saldo da Conta********************\n\n");
        String linha = "********************Relatório de Saldo da Conta********************\n\n";
        buffWrite.append(linha).append("\n");

        linha = dataTela();
        buffWrite.append("\t").append(linha).append("\n\n");

        System.out.println("Saldo em conta");
        linha = "Saldo em conta";
        buffWrite.append("\t").append(linha).append("\n");

        System.out.println("O saldo atual de " + usuario.getNome() + " na conta " + conta.getTipoConta() + " Ã© de R$ " + new DecimalFormat("#,##0.00").format(conta.getSaldo()));
        linha = "O saldo atual de " + usuario.getNome() + " na conta " + conta.getTipoConta() + " Ã© de R$ " + new DecimalFormat("#,##0.00").format(conta.getSaldo());
        buffWrite.append("\t").append(linha).append("\n");

        linha = logoPinguinP();
        buffWrite.append(linha).append("\n");

        System.out.println("\n***************************Fim do Relatório****************************");
        linha = "\n***************************Fim do Relatório****************************";
        buffWrite.append("\n\n").append(linha);

        buffWrite.close();
    }

    public static void relatorioSimulacaoRendimento(Double valor, int dias, String path) throws IOException
    {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        double rend = 0.00533333;
        double rendimento = rend / 30 * valor * dias;

        System.out.println("***************************Simulação de Rendimento (POUPANçA)****************************");
        String linha = "***************************Simulação de Rendimento (POUPANçA)****************************";
        buffWrite.append(linha).append("\n\n");

        linha = dataTela();
        buffWrite.append("\t").append(linha).append("\n\n");

        System.out.println("O rendimento estimado de R$ " + valor + " em " + dias + " dias Ã© de R$ " + new DecimalFormat("#,##0.00").format(rendimento));
        linha = "O rendimento estimado de R$ " + valor + " em " + dias + " dias Ã© de R$ " + new DecimalFormat("#,##0.00").format(rendimento);
        buffWrite.append(linha).append("\n\n");

        System.out.println("\n***************************Fim do Relatório****************************");
        linha = "\n***************************Fim do Relatório****************************";
        buffWrite.append("\n\n").append(linha);

        buffWrite.close();

    }

    public static String dataPath() 
    {
        final DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_hh-mm-ss");
        Calendar calendario = Calendar.getInstance();
        return (dateFormat.format(calendario.getTime()));
    }

    public static String dataTela() 
    {
        final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Calendar calendario = Calendar.getInstance();
        return (dateFormat.format(calendario.getTime()));
    }


    public static String getPathBasico()
    {
        return PATH_BASICO;
    }

    public static String getEXTENSAO() 
    {
        return EXTENSAO;
    }

    public static String logoPinguinP() 
    {
        return ("" +
                "\n\n\n\n\n\n        â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „\n" +
                "        â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „\n" +
                "        â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „\n" +
                "        â „â „â „â „â „â „â „â „â „â „â „â „â „â¢°â£¶â£¶â£¶â£¶â£¶â£¦â¡€â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â£€â£¤â£¶â£¶â£¦â£„â¡€â „â „â „â „â „â „â „â „â „â „â „\n" +
                "        â „â „â „â „â „â „â „â „â „â „â£€â£¤â¡†â¢¸â£¿â¡‡â „â „â¢¸â£¿â¡‡â „â£¶â£¶â£¶â£¶â£¶â£„â¢°â£¶â£¦â£´â£¶â£¶â£¦â „â¢ â£¶â£¶â£¶â£¶â£¶â „â£´â£¶â£¶â£¶â£¶â£¦â¡€â „â „â „â „â „â „â „â¢¸â£¿â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â¢€â£´â£¿â£¿â£¿â£¿â£¿â£¿â£¿â£¿â£¦â „â „â „â „â „â „â „â „â „â „\n" +
                "        â „â „â „â „â „â „â „â£´â£¾â¡¿â Ÿâ ‰â �â¢¸â£¿â¡¿â ¿â ¿â¢¿â£·â¡„â¢€â£ â£¤â£¤â£¼â£¿â£¿â¢¸â£¿â£¿â „â „â£¿â£¿â¡‡â¢¸â£¿â „â „â „â „â¢¸â£¿â£¿â „â „â¢¸â£¿â¡‡â „â „â „â „â£¿â¡Ÿâ ›â¢»â£¿â „â£¿â¡Ÿâ ›â¢»â£¿â¢¸â£¿â£Ÿâ£›â£›â „â „â „â „â „â „â „â „â£ â¡¾â ‹â �â¢€â£¿â£¿â£·â£Œâ£¹â£¿â£¿â£¿â£¤â£€â „â „â „â „â „â „â „\n" +
                "        â „â „â „â „â „â „â „â ™â »â ¿â¢¿â£¦â¡„â¢¸â£¿â£‡â£€â£€â£¸â£¿â¡‡â¢¸â£¿â£�â£‰â£¹â£¿â£¿â¢¸â£¿â£¿â „â „â£¿â£¿â¡‡â¢¸â£¿â£€â£€â£€â£€â ¸â£¿â£¿â£€â£€â£¼â£¿â ‡â „â „â „â „â£¿â£‡â£€â£¸â£¿â „â£¿â£‡â£€â£¸â£¿â¢ˆâ£›â£›â£›â£¿â¡§â „â „â „â „â „â „â£¸â Ÿâ „â „â „â ¾â£¿â£¿â£¿â£¿â£¿â£¿â£¿â¡¿â ¿â ¿â£¿â£¶â¡€â „â „â „â „\n" +
                "        â „â „â „â „â „â „â „â „â „â „â „â ‰â ƒâ ˜â ›â ›â ›â ›â ›â ‹â �â „â ™â ›â ‹â ‰â ›â ›â ˜â ›â ›â „â „â ›â ›â ƒâ ˆâ ›â ›â ›â ›â ›â „â ‰â ›â ›â ›â ›â ‹â „â „â „â „â „â ›â ›â ‹â ™â ›â „â ™â ›â ›â ›â ‰â ˜â ›â ›â ›â ›â �â „â „â „â „â „â£°â¡Ÿâ „â£€â£¤â „â „â „â ‰â ™â ›â¢¿â£¿â �â „â „â „â „â ‰â “â „â „â „â „\n" +
                "        â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â¢¸â£¿â¢£â£¾â¡Ÿâ ƒâ¡„â „â „â „â „â „â ˆâ »â£¦â¡€â „â „â „â „â „â „â „â „â „\n" +
                "        â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â¢ â£¿â¡Ÿâ£¼â¡¿â¢£â¡žâ �â „â „â „â „â „â „â „â ›â£¿â¡€â „â „â „â „â „â „â „â „\n" +
                "        â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â¢°â£¶â¡†â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â¢°â£¶â£¶â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â£¶â¡–â „â „â „â „â „â „â „â „â „â¢ â£¿â£¿â£¿â£¿â �â¡žâ �â „â „â „â „â „â „â „â „â „â ˆâ£·â¡„â „â „â „â „â „â „â „\n" +
                "        â „â „â „â „â „â „â „â£¶â£¶â£¤â£¶â£¶â£¶â¡„â¢°â£¾â£¿â¡‡â¢°â£¶â£¦â£¶â£¶â£¶â£†â „â¢°â£¶â£¶â£¶â£¶â£¶â¡†â¢°â£¶â¡†â „â „â£¶â£¶â¢°â£¶â£¿â£·â „â£¶â£¦â£´â£¶â£¶â£¦â¡€â£´â£¶â£¶â£¶â£¶â£¶â „â „â „â£¼â£¿â ƒâ¢°â£¦â£€â¡€â „â „â „â „â „â£¸â£¿â£¿â£¿â¡‡â£¼â¡‡â „â „â „â „â „â „â „â „â „â „â „â ™â£·â „â „â „â „â „â „â „\n" +
                "        â „â „â „â „â „â „â „â£¿â£¿â¡�â ‰â¢¹â£¿â£¿â ˆâ¢¹â£¿â¡‡â¢¸â£¿â¡�â ‰â ‰â£¿â£¿â „â£¿â£¿â ‰â ‰â¢¹â£¿â¡‡â¢¸â£¿â¡‡â „â „â£¿â£¿â ˆâ¢¹â£¿â£¿â „â£¿â£¿â ‰â ‰â£¿â£¿â¡‡â£¿â£¿â£­â£­â£­â£­â „â „â£¾â£¿â ƒâ „â ˆâ ‰â ›â ¿â£¶â£¦â „â „â¢°â£¿â£¿â£¿â£¿â£§â£¿â „â „â „â „â „â „â „â „â „â „â „â „â „â¢¹â¡„â „â „â „â „â „â „\n" +
                "        â „â „â „â „â „â „â „â£¿â£¿â£§â£¤â£¼â£¿â¡¿â „â¢¸â£¿â¡‡â¢¸â£¿â¡‡â „â „â£¿â£¿â „â¢¿â£¿â£¦â£¤â£¼â£¿â¡‡â¢¸â£¿â£§â£¤â£¤â£¿â£¿â „â¢¸â£¿â£¿â „â£¿â£¿â „â „â£¿â£¿â¡‡â£¤â£�â£‰â£‰â£¿â£¿â ‡â£°â£¿â¡Ÿâ „â „â¢ â£´â£¿â¡¿â ›â ‹â „â „â£¿â£¿â£¿â£¿â£¿â¢¸â¡‡â „â „â „â „â „â „â „â „â „â „â „â „â „â „â£§â „â „â „â „â „â „\n" +
                "        â „â „â „â „â „â „â „â£¿â£¿â¡Ÿâ ›â ›â ‹â „â „â ˜â ›â ƒâ ˜â ›â ƒâ „â „â ›â ›â „â¢ â£‰â£‰â£‰â£¼â£¿â¡‡â „â ™â ›â ‹â �â ›â ›â „â ˆâ ›â ‹â „â ›â ›â „â „â ™â ›â �â ‰â ™â ›â ›â ›â ‹â �â ›â ‹â „â „â „â ˆâ �â „â „â „â „â „â ˜â ›â ›â ›â ›â ›â ˜â ƒâ „â „â „â „â „â „â „â „â „â „â „â „â „â „â ›â „â „â „â „â „â „\n" +
                "        â „â „â „â „â „â „â „â ™â ›â ƒâ „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â ˆâ ›â ›â ›â ›â ‰â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „\n" +
                "        â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „\n" +
                "        â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „â „\n\n\n");

    }
}

