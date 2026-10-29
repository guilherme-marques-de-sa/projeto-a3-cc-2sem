A forma como tudo será feito é seguindo um procedimento, e para isso usaremos:
- Variáveis e constantes para guardar informações.
- O Scanner para que o usuário possa inserir dados.
- Um ArrayList para manter os dados dos funcionários.
- Blocos de código if/else e switch para tomar decisões.
- Laços de repetição while e for para executar tarefas várias vezes.
- Métodos estáticos para deixar as funções mais organizadas.
- Faremos os cálculos de salário de acordo com o tipo de cada empregado.
É importante notar: não usaremos herança, encapsulamento, construtores ou a criação de novas classes. O programa terá apenas a classe principal que o Java exige.

1. Como o sistema vai funcionar
O menu vai apresentar as seguintes opções:
1. Adicionar um funcionário comum.
2. Adicionar um funcionário comissionado.
3. Adicionar um funcionário por produção.
4. Calcular a folha de pagamento.
5. Ver um resumo da folha.
6. Encerrar o programa.

O salário inicial será de R$ 2.000,00 para todos.

Regras para os cálculos
Tipo de funcionário | Cálculo do salário
--------------------|-------------------
Comum               | R$ 2.000,00
Comissionado        | R$ 2.000,00 + comissão
Produção            | R$ 2.000,00 + bônus de produção

- Comissão é calculada assim: valor das vendas multiplicado pelo percentual e dividido por 100.
- Bônus de produção é assim: número de peças feitas vezes o valor de cada peça.

2. O código completo em Java
Preparei tudo em um único arquivo, chamado Principal.java, para que seja fácil de rodar e entregar.

O código já inclui:
- O cadastro dos três tipos de funcionários.
- Verificação para não ter matrículas repetidas.
- Verificação para impedir valores negativos.
- O cálculo automático da comissão.
- O cálculo automático do bônus de produção.
- A geração da folha de pagamento para cada um.
- Um resumo mostrando quantos funcionários há em cada categoria.
- A soma total de todos os salários.

3. Como os dados serão organizados
Como não vamos usar orientação a objetos, os dados ficarão em listas separadas (ArrayList):

Lista | Dados guardados
-----|----------------
matrículas | Código do funcionário
nomes | Nome completo do funcionário
tipos | Categoria do funcionário
valoresVendas | Valor total das vendas
percentuais | Porcentagem de comissão
quantidadesPecas | Número de peças produzidas
valoresPorPeca | Quanto se paga por peça

Cada funcionário terá sua informação na mesma posição em todas as listas.

4. Como rodar o projeto
1. Abra o NetBeans, Eclipse ou IntelliJ IDEA.
2. Crie um novo projeto Java.
3. Faça uma classe chamada Principal.
4. Copie todo o conteúdo do arquivo que foi fornecido.
5. Rode o programa usando o método main.

Se preferir usar o terminal de comandos:
javac Principal.java
java Principal

5. Um exemplo de cálculo
Vamos supor que três funcionários foram cadastrados:

Funcionário | Tipo | Detalhes
------------|------|--------
João        | Comum | Salário base
Maria       | Comissionado | Vendeu R$ 10.000,00 e tem 5%
Pedro       | Produção | Fez 100 peças a R$ 2,00 cada

O resultado será:

Funcionário | Salário final
------------|--------------
João        | R$ 2.000,00
Maria       | R$ 2.500,00
Pedro       | R$ 2.200,00
Total       | R$ 6.700,00

6. Princípios usados no projeto
Este projeto utiliza os conceitos básicos da programação estruturada:
- Variáveis: Para guardar as informações.
- Constantes: Para definir o valor fixo do salário base.
- Métodos estáticos: Para organizar as funções.
- ArrayList: Para armazenar os dados dos funcionários.
- Switch: Para controlar as opções do menu.
- If/else: Para fazer verificações e cálculos.
- For: Para processar a folha de pagamento.
- While: Para repetir o menu e garantir que os dados inseridos estejam corretos.
