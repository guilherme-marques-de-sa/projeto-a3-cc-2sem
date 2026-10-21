import java.util.Scanner;

public class SistemaFolhaPagamento {

    public static class Funcionario {
        private String nome;
        private String cargo;
        private double salarioBruto;

        public Funcionario(String nome, String cargo, double salarioBruto) {
            this.nome = nome;
            this.cargo = cargo;
            this.salarioBruto = salarioBruto;
        }

        public String getNome() {
            return nome;
        }

        public String getCargo() {
            return cargo;
        }

        public double getSalarioBruto() {
            return salarioBruto;
        }

        public double calcularINSS() {
            if (salarioBruto <= 1412.00) {
                return salarioBruto * 0.075;
            } else if (salarioBruto <= 2666.68) {
                return salarioBruto * 0.09;
            } else if (salarioBruto <= 4000.03) {
                return salarioBruto * 0.12;
            } else {
                return salarioBruto * 0.14;
            }
        }

        public double calcularSalarioLiquido() {
            return salarioBruto - calcularINSS();
        }

        public void exibirInformacoes() {
            System.out.printf("Nome: %s | Cargo: %s | Salário Bruto: R$ %.2f | INSS: R$ %.2f | Salário Líquido: R$ %.2f%n",
                    nome, cargo, salarioBruto, calcularINSS(), calcularSalarioLiquido());
        }
    }

    private static final int MAX_FUNCIONARIOS = 100;
    private static Funcionario[] funcionarios = new Funcionario[MAX_FUNCIONARIOS];
    private static int totalFuncionarios = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarFuncionario();
                    break;
                case 2:
                    listarFuncionarios();
                    break;
                case 3:
                    exibirTotalFolha();
                    break;
                case 0:
                    System.out.println("A encerrar o sistema... Até à próxima!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("=== SISTEMA DE FOLHA DE PAGAMENTO ===");
        System.out.println("1. Cadastrar Funcionário");
        System.out.println("2. Listar Funcionários");
        System.out.println("3. Exibir Total da Folha de Pagamento");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. Digite um número correspondente às opções: ");
            scanner.next();
        }
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }

    private static void cadastrarFuncionario() {
        if (totalFuncionarios >= MAX_FUNCIONARIOS) {
            System.out.println("Erro: Limite máximo de funcionários atingido!");
            return;
        }

        System.out.println("\n--- Cadastro de Funcionário ---");

        String nome = "";
        while (nome.trim().isEmpty()) {
            System.out.print("Digite o nome do funcionário: ");
            nome = scanner.nextLine();
            if (nome.trim().isEmpty()) {
                System.out.println("Erro: O nome não pode estar vazio.");
            }
        }

        String cargo = "";
        while (cargo.trim().isEmpty()) {
            System.out.print("Digite o cargo do funcionário: ");
            cargo = scanner.nextLine();
            if (cargo.trim().isEmpty()) {
                System.out.println("Erro: O cargo não pode estar vazio.");
            }
        }

        double salario = -1;
        while (salario <= 0) {
            System.out.print("Digite o salário bruto (positivo): R$ ");
            if (scanner.hasNextDouble()) {
                salario = scanner.nextDouble();
                if (salario <= 0) {
                    System.out.println("Erro: O salário deve ser um valor maior que zero.");
                }
            } else {
                System.out.println("Erro: Digite um número válido para o salário.");
                scanner.next();
            }
        }
        scanner.nextLine();

        funcionarios[totalFuncionarios] = new Funcionario(nome, cargo, salario);
        totalFuncionarios++;

        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private static void listarFuncionarios() {
        System.out.println("\n--- Lista de Funcionários ---");
        if (totalFuncionarios == 0) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        for (int i = 0; i < totalFuncionarios; i++) {
            System.out.print((i + 1) + ". ");
            funcionarios[i].exibirInformacoes();
        }
    }

    private static void exibirTotalFolha() {
        System.out.println("\n--- Resumo da Folha de Pagamento ---");
        if (totalFuncionarios == 0) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        double totalBruto = 0;
        double totalDescontos = 0;
        double totalLiquido = 0;

        for (int i = 0; i < totalFuncionarios; i++) {
            totalBruto += funcionarios[i].getSalarioBruto();
            totalDescontos += funcionarios[i].calcularINSS();
            totalLiquido += funcionarios[i].calcularSalarioLiquido();
        }

        System.out.printf("Total de Funcionários: %d%n", totalFuncionarios);
        System.out.printf("Total Salários Brutos: R$ %.2f%n", totalBruto);
        System.out.printf("Total de Descontos (INSS): R$ %.2f%n", totalDescontos);
        System.out.printf("Total Salários Líquidos: R$ %.2f%n", totalLiquido);
    }
}