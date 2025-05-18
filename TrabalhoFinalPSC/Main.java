package TrabalhoFinalPSC;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap; //dashboard
import java.util.Map;//dashboard

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Transacao> transacao = new ArrayList<>(); // criando lista de objetivo do tipo Transacao (ou das subclasses dela), <transacao> garante que so aceite objetos desse tipo, e new ArrayList vc esta criando uma nova instancia, ela começa vazia. 
        int opcao;

        do {
            System.out.println("\n=== MENU FINANCEIRO ===");
            System.out.println("1. Adicionar Receita");
            System.out.println("2. Adicionar Despesa");
            System.out.println("3. Listar Transações");
            System.out.println("4. Ver Saldo");
            System.out.println("5.Alterar/Excluir Transação");
            System.out.println("6. Visualizar DashBoard");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar o buffer

            switch (opcao) {
                case 1: // adicionar receita
                    System.out.print("Descrição da receita: ");
                    String descReceita = scanner.nextLine(); // recebe descriçao
                    System.out.print("Valor da receita: R$ ");
                    double valorReceita = scanner.nextDouble(); // recebe o valor
                    if (valorReceita > 0) {
                        transacao.add(new Receita(valorReceita, descReceita));
                        System.out.println("\nReceita adicionada com sucesso.");
                    } else {
                        System.out.println("\nValor inválido.");
                    }
                    break;

                case 2: // adicionar despesa
                    System.out.print("Descrição da despesa: ");
                    String descDespesa = scanner.nextLine(); // recebe descricao
                    System.out.print("Valor da despesa: R$ ");
                    double valorDespesa = scanner.nextDouble(); // recebe valor
                    scanner.nextLine(); // limpa o buffer apos a leitura do valor
                    if (valorDespesa > 0) {
                        transacao.add(new Despesa(valorDespesa, descDespesa)); //cria uma nova despesa
                        System.out.println("\nDespesa adicionada com sucesso.");
                    } else {
                        System.out.println("\nValor inválido.");
                    }
                    break;

                case 3: // listar transações
                    System.out.println("\n--- Transações ---");
                    for (Transacao t : transacao) { // percorre todas as transaçoes (despesas e receitas) armazenadas no arraylist
                        System.out.printf("%s: R$ %.2f - %s%n", // exibe o tipo (despesa/receita), valir e descricao.
                            t.getTipo(), t.getValor(), t.getDescricao());
                    }
                    break;

                case 4: // ver saldo
                    double saldo = 0.0; // inicia a variavel saldo
                    for (Transacao t : transacao) { // percorre todas as transaçoes
                        if (t instanceof Receita) { // "se a tranaçao for do tipo receita"
                            saldo += t.getValor(); // adiciona o valor
                        } else if (t instanceof Despesa) { // "se a tranaçao for do tipo despesa"
                            saldo -= t.getValor(); // adicona o valor
                        }
                    }
                    System.out.printf("\nSaldo atual: R$ %.2f%n", saldo); // exibe saldo final 
                    break;

                case 5: // Alterar/Excluir Transação (Receita ou Despesa)
                   if (transacao.isEmpty()) {
                   System.out.println("Nenhuma transação registrada.");
                 break;
                 }

                System.out.println("\n--- Transações ---");
                 for (int i = 0; i < transacao.size(); i++) {
                 Transacao t = transacao.get(i);
                 System.out.printf("[%d] %s: R$ %.2f - %s%n", i, t.getTipo(), t.getValor(), t.getDescricao());
              }

                System.out.print("Escolha o índice da transação que deseja alterar/excluir: ");
                int indice = scanner.nextInt();
                 scanner.nextLine();

                  if (indice >= 0 && indice < transacao.size()) {
                Transacao selecionada = transacao.get(indice);
                 System.out.print("Digite 'a' para alterar ou 'e' para excluir: ");
                 String acao = scanner.nextLine();
 
                  if (acao.equalsIgnoreCase("a")) {
                 System.out.print("Nova descrição: ");
                 String novaDesc = scanner.nextLine();
                 System.out.print("Novo valor: ");
                 double novoValor = scanner.nextDouble();
                 scanner.nextLine();

                 if (novoValor > 0) {
                selecionada.setDescricao(novaDesc);
                selecionada.setValor(novoValor);
                System.out.println("Transação alterada com sucesso.");
             } else {
                System.out.println("Valor inválido.");
            }
            } else if (acao.equalsIgnoreCase("e")) {
            transacao.remove(indice);
            System.out.println("Transação excluída com sucesso.");
            } else {
            System.out.println("Ação inválida.");
            }
            } else {
           System.out.println("Índice inválido.");
            }
           break;

                case 6: //ver dashboard
                    double totalReceita = 0.0;
                    Map<String, Double> resumoDespesas = new HashMap<>(); // armazena descrição e valor da despesa

                    for (Transacao t : transacao) {   // passa por todas as transações
                    if (t instanceof Receita) {
                     totalReceita += t.getValor(); // soma todas as receitas
                     } 

                     else if (t instanceof Despesa) {
                     String desc = t.getDescricao();// Agrupa despesas por descrição
                     resumoDespesas.put(desc, resumoDespesas.getOrDefault(desc, 0.0) + t.getValor());
                      }
                     }

                    System.out.println("\n=== DASHBOARD FINANCEIRO ===");

                      if (totalReceita == 0) {
                    System.out.println("Nenhuma receita registrada. Não é possível calcular porcentagens.");
                      } 
                      else {
                     for (Map.Entry<String, Double> entrada : resumoDespesas.entrySet()) {
                     String descricao = entrada.getKey();
                     valorDespesa = entrada.getValue();
                     double porcentagem = (valorDespesa / totalReceita) * 100; // calcula a porcentagem da receita que foi para essa despesa
                     System.out.printf("Neste mês, %.2f%% da sua receita foi para %s.%n", porcentagem, descricao);
                     }
                     }
                     System.out.println();
                     break;

                case 7:
                    System.out.println("Saindo..."); // exibe a mensagem de saida
                    break;
                default:
                    System.out.println("Opção inválida."); // exibe erro
            }

        } while (opcao != 0); //  Repete o menu até o usuário escolher a opção 0 

        scanner.close();
    }
}
