import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    // Constante: salario-base igual para todos os colaboradores
    static final double SALARIO_BASE = 2000.00;

    // Listas paralelas: cada posicao representa um colaborador
    static ArrayList<String> matriculas = new ArrayList<>();
    static ArrayList<String> nomes = new ArrayList<>();
    static ArrayList<Integer> tipos = new ArrayList<>();
    static ArrayList<Double> valoresVendas = new ArrayList<>();
    static ArrayList<Double> percentuais = new ArrayList<>();
    static ArrayList<Integer> quantidadesPecas = new ArrayList<>();
    static ArrayList<Double> valoresPorPeca = new ArrayList<>();

    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    cadastrarPadrao();
                    break;
                case 2:
                    cadastrarComissionado();
                    break;
                case 3:
                    cadastrarProducao();
                    break;
                case 4:
                    gerarFolha();
                    break;
                case 5:
                    exibirResumo();
                    break;
                case 0:
                    System.out.println("\\nPrograma encerrado. Ate mais!");
                    break;
                default:
                    System.out.println("\\nOpcao invalida. Tente novamente.");
            }
        } while (opcao != 0);

        entrada.close();
    }

    static void exibirMenu() {
        System.out.println("\\n==============================================");
        System.out.println("       SISTEMA DE FOLHA DE PAGAMENTO");
        System.out.println("==============================================");
        System.out.println("1 - Cadastrar Colaborador Padrao");
        System.out.println("2 - Cadastrar Colaborador Comissionado");
        System.out.println("3 - Cadastrar Colaborador de Producao");
        System.out.println("4 - Gerar Folha de Pagamento");
        System.out.println("5 - Exibir Resumo da Folha");
        System.out.println("0 - Sair do Programa");
        System.out.println("==============================================");
    }

    static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            if (entrada.hasNextInt()) {
                int valor = entrada.nextInt();
                entrada.nextLine();
                return valor;
            }
            System.out.println("Digite um numero inteiro valido.");
            entrada.nextLine();
        }
    }

    static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            if (entrada.hasNextDouble()) {
                double valor = entrada.nextDouble();
                entrada.nextLine();
                if (Double.isFinite(valor)) {
                    return valor;
                }
            } else {
                entrada.nextLine();
            }
            System.out.println("Digite um valor numerico valido.");
        }
    }

    static String lerTexto(String mensagem) {
        String texto;
        do {
            System.out.print(mensagem);
            texto = entrada.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("O campo nao pode ficar vazio.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    static boolean matriculaExiste(String matricula) {
        for (int i = 0; i < matriculas.size(); i++) {
            if (matriculas.get(i).equalsIgnoreCase(matricula)) {
                return true;
            }
        }
        return false;
    }

    static void cadastrarDadosBasicos(int tipo) {
        System.out.println("\\n--- Cadastro de colaborador ---");

        String matricula;
        do {
            matricula = lerTexto("Numero de registro/matricula: ");
            if (matriculaExiste(matricula)) {
                System.out.println("Essa matricula ja esta cadastrada. Informe outra.");
            }
        } while (matriculaExiste(matricula));

        String nome = lerTexto("Nome completo: ");

        matriculas.add(matricula);
        nomes.add(nome);
        tipos.add(tipo);

        // Campos que nao se aplicam ao tipo recebem zero
        valoresVendas.add(0.0);
        percentuais.add(0.0);
        quantidadesPecas.add(0);
        valoresPorPeca.add(0.0);

        System.out.println("Colaborador cadastrado com sucesso!");
    }

    static void cadastrarPadrao() {
        cadastrarDadosBasicos(1);
        System.out.println("Tipo: Colaborador Padrao");
        System.out.printf("Salario-base: R$ %.2f%n", SALARIO_BASE);
    }

    static void cadastrarComissionado() {
        cadastrarDadosBasicos(2);
        int i = matriculas.size() - 1;

        double vendas;
        do {
            vendas = lerDouble("Valor total das vendas (R$): ");
            if (vendas < 0) {
                System.out.println("O valor das vendas nao pode ser negativo.");
            }
        } while (vendas < 0);

        double percentual;
        do {
            percentual = lerDouble("Percentual de comissao (0 a 100): ");
            if (percentual < 0 || percentual > 100) {
                System.out.println("Informe um percentual entre 0 e 100.");
            }
        } while (percentual < 0 || percentual > 100);

        valoresVendas.set(i, vendas);
        percentuais.set(i, percentual);

        System.out.println("Colaborador comissionado cadastrado com sucesso!");
    }

    static void cadastrarProducao() {
        cadastrarDadosBasicos(3);
        int i = matriculas.size() - 1;

        int pecas;
        do {
            pecas = lerInteiro("Quantidade de pecas produzidas: ");
            if (pecas < 0) {
                System.out.println("A quantidade nao pode ser negativa.");
            }
        } while (pecas < 0);

        double valorPeca;
        do {
            valorPeca = lerDouble("Valor pago por peca (R$): ");
            if (valorPeca < 0) {
                System.out.println("O valor por peca nao pode ser negativo.");
            }
        } while (valorPeca < 0);

        quantidadesPecas.set(i, pecas);
        valoresPorPeca.set(i, valorPeca);

        System.out.println("Colaborador de producao cadastrado com sucesso!");
    }

    static double calcularSalario(int i) {
        double salario = SALARIO_BASE;

        if (tipos.get(i) == 1) {
            // Colaborador padrao: recebe somente o salario-base
            salario = SALARIO_BASE;
        } else if (tipos.get(i) == 2) {
            double comissao = valoresVendas.get(i) * percentuais.get(i) / 100.0;
            salario = SALARIO_BASE + comissao;
        } else if (tipos.get(i) == 3) {
            double produtividade = quantidadesPecas.get(i) * valoresPorPeca.get(i);
            salario = SALARIO_BASE + produtividade;
        }

        return salario;
    }

    static String nomeTipo(int tipo) {
        if (tipo == 1) {
            return "Padrao";
        } else if (tipo == 2) {
            return "Comissionado";
        } else {
            return "Producao";
        }
    }

    static void gerarFolha() {
        if (matriculas.isEmpty()) {
            System.out.println("\\nNao existem colaboradores cadastrados.");
            return;
        }

        double totalFolha = 0.0;

        System.out.println("\\n================ FOLHA DE PAGAMENTO ================");
        for (int i = 0; i < matriculas.size(); i++) {
            double salario = calcularSalario(i);
            totalFolha += salario;

            System.out.println("\\nMatricula: " + matriculas.get(i));
            System.out.println("Nome: " + nomes.get(i));
            System.out.println("Tipo: " + nomeTipo(tipos.get(i)));

            if (tipos.get(i) == 2) {
                double comissao = valoresVendas.get(i) * percentuais.get(i) / 100.0;
                System.out.printf("Comissao: R$ %.2f%n", comissao);
            } else if (tipos.get(i) == 3) {
                double produtividade = quantidadesPecas.get(i) * valoresPorPeca.get(i);
                System.out.printf("Produtividade: R$ %.2f%n", produtividade);
            }

            System.out.printf("Salario-base: R$ %.2f%n", SALARIO_BASE);
            System.out.printf("Salario final: R$ %.2f%n", salario);
            System.out.println("-----------------------------------------------------");
        }

        System.out.printf("TOTAL DA FOLHA: R$ %.2f%n", totalFolha);
        System.out.println("=====================================================");
    }

    static void exibirResumo() {
        int padrao = 0;
        int comissionados = 0;
        int producao = 0;
        double total = 0.0;

        for (int i = 0; i < matriculas.size(); i++) {
            total += calcularSalario(i);

            if (tipos.get(i) == 1) {
                padrao++;
            } else if (tipos.get(i) == 2) {
                comissionados++;
            } else if (tipos.get(i) == 3) {
                producao++;
            }
        }

        System.out.println("\\n============= RESUMO DA FOLHA =============");
        System.out.println("Total de colaboradores: " + matriculas.size());
        System.out.println("Colaboradores padrao: " + padrao);
        System.out.println("Colaboradores comissionados: " + comissionados);
        System.out.println("Colaboradores de producao: " + producao);
        System.out.printf("Valor total da folha: R$ %.2f%n", total);
        System.out.println("===========================================");
    }
}
