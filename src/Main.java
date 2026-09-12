import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int opcao;

        do {
            System.out.println("\n=== MENU DE TAREFAS ===");
            System.out.println("1 - Criar tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Deletar tarefa por índice");
            System.out.println("4 - Editar tarefa");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Entrada inválida. Digite um número: ");
                scanner.next();
            }
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();

                    LocalDate prazo = null;
                    while (prazo == null) {
                        System.out.print("Prazo (dd/MM/yyyy): ");
                        String prazoTexto = scanner.nextLine();
                        try {
                            prazo = LocalDate.parse(prazoTexto, formatter);
                        } catch (DateTimeParseException e) {
                            System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
                        }
                    }

                    Tarefa.StatusTarefa status = null;
                    while (status == null) {
                        System.out.println("Status: ");
                        System.out.println("1 - PENDENTE");
                        System.out.println("2 - EM_ANDAMENTO");
                        System.out.println("3 - CONCLUIDA");
                        System.out.print("Escolha o status: ");

                        String statusTexto = scanner.nextLine();
                        switch (statusTexto) {
                            case "1":
                                status = Tarefa.StatusTarefa.PENDENTE;
                                break;
                            case "2":
                                status = Tarefa.StatusTarefa.EM_ANDAMENTO;
                                break;
                            case "3":
                                status = Tarefa.StatusTarefa.CONCLUIDA;
                                break;
                            default:
                                System.out.println("Status inválido.");
                        }
                    }

                    gerenciador.criarTarefa(titulo, descricao, prazo, status);
                    System.out.println("Tarefa criada com sucesso!");
                    break;

                case 2:
                    gerenciador.listarTarefas();
                    break;

                case 3:
                    if (gerenciador.getTarefas().isEmpty()) {
                        System.out.println("Não há tarefas para deletar.");
                        break;
                    }
                    gerenciador.listarTarefas();
                    System.out.print("Informe o índice da tarefa para deletar: ");

                    while (!scanner.hasNextInt()) {
                        System.out.print("Índice inválido. Digite um número: ");
                        scanner.next();
                    }
                    int indice = scanner.nextInt();
                    scanner.nextLine();

                    boolean removida = gerenciador.deletarTarefa(indice);
                    if (removida) {
                        System.out.println("Tarefa removida com sucesso!");
                    } else {
                        System.out.println("Índice inválido.");
                    }
                    break;

                case 4:
                    List<Tarefa> tarefasParaEditar = gerenciador.getTarefas();
                    if (tarefasParaEditar.isEmpty()) {
                        System.out.println("Não há tarefas para editar.");
                        break;
                    }

                    gerenciador.listarTarefas();
                    System.out.print("Informe o índice da tarefa para editar: ");
                    while (!scanner.hasNextInt()) {
                        System.out.print("Índice inválido. Digite um número: ");
                        scanner.next();
                    }
                    int indiceEdicao = scanner.nextInt();
                    scanner.nextLine();

                    if (indiceEdicao < 0 || indiceEdicao >= tarefasParaEditar.size()) {
                        System.out.println("Índice inválido.");
                        break;
                    }

                    Tarefa tarefaAtual = tarefasParaEditar.get(indiceEdicao);

                    String novoTitulo = null;
                    System.out.print("Título atual: " + tarefaAtual.getTitulo() + "\nNovo título (Enter para manter): ");
                    String tituloInput = scanner.nextLine();
                    if (!tituloInput.isEmpty()) {
                        novoTitulo = tituloInput;
                    }

                    String novaDescricao = null;
                    System.out.print("Descrição atual: " + tarefaAtual.getDescricao() + "\nNova descrição (Enter para manter): ");
                    String descricaoInput = scanner.nextLine();
                    if (!descricaoInput.isEmpty()) {
                        novaDescricao = descricaoInput;
                    }

                    LocalDate novoPrazo = null;
                    System.out.print("Prazo atual: " + tarefaAtual.getPrazo() + "\nNovo prazo (dd/MM/yyyy, Enter para manter): ");
                    String prazoInput = scanner.nextLine();
                    while (!prazoInput.isEmpty()) {
                        try {
                            novoPrazo = LocalDate.parse(prazoInput, formatter);
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.print("Data inválida. Digite no formato dd/MM/yyyy ou pressione Enter para manter: ");
                            prazoInput = scanner.nextLine();
                        }
                    }

                    Tarefa.StatusTarefa novoStatus = null;
                    System.out.println("Status atual: " + tarefaAtual.getStatus());
                    System.out.println("Opções de status:");
                    System.out.println("1 - PENDENTE");
                    System.out.println("2 - EM_ANDAMENTO");
                    System.out.println("3 - CONCLUIDA");
                    System.out.println("0 - Manter status atual");
                    System.out.print("Escolha o novo status (ou pressione Enter para manter): ");
                    String statusInput = scanner.nextLine();
                    if (!statusInput.isEmpty()) {
                        switch (statusInput) {
                            case "1":
                                novoStatus = Tarefa.StatusTarefa.PENDENTE;
                                break;
                            case "2":
                                novoStatus = Tarefa.StatusTarefa.EM_ANDAMENTO;
                                break;
                            case "3":
                                novoStatus = Tarefa.StatusTarefa.CONCLUIDA;
                                break;
                            case "0":
                                novoStatus = null;
                                break;
                            default:
                                System.out.println("Opção inválida. O status atual será mantido.");
                                novoStatus = null;
                        }
                    }

                    boolean editou = gerenciador.editarTarefa(
                            indiceEdicao,
                            novoTitulo,
                            novaDescricao,
                            novoPrazo,
                            novoStatus
                    );

                    if (editou) {
                        System.out.println("Tarefa editada com sucesso!");
                    } else {
                        System.out.println("Índice inválido.");
                    }
                    break;

                case 5:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 5);

        scanner.close();
    }
}