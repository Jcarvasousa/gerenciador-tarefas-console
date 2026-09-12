import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorTarefas {
    private List<Tarefa> tarefas;

    public GerenciadorTarefas() {
        this.tarefas = new ArrayList<>();
    }

    public void criarTarefa(String titulo, String descricao, LocalDate prazo, Tarefa.StatusTarefa status) {
        Tarefa tarefa = new Tarefa(titulo, descricao, prazo, status);
        tarefas.add(tarefa);
    }

    public void listarTarefas() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }

        for (int i = 0; i < tarefas.size(); i++) {
            Tarefa tarefa = tarefas.get(i);
            System.out.println("Índice: " + i);
            System.out.println("Título: " + tarefa.getTitulo());
            System.out.println("Descrição: " + tarefa.getDescricao());
            System.out.println("Prazo: " + tarefa.getPrazo());
            System.out.println("Status: " + tarefa.getStatus());
            System.out.println("---------------------------");
        }
    }

    public boolean deletarTarefa(int indice) {
        if (indice < 0 || indice >= tarefas.size()) {
            return false;
        }

        tarefas.remove(indice);
        return true;
    }
    public boolean deletarTarefa(String titulo) {
        int contador = 0;
        for (int i = 0; i < tarefas.size(); i++) {
            if (tarefas.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                contador++;
            }
        }
        // até aqui, NADA foi deletado — só contamos

        if (contador == 0) {
            return false;
        } else if (contador > 1) {
            System.out.println("Mais de uma tarefa com o mesmo título. Use o índice.");
            return false;
        }

        // se chegou aqui, contador é exatamente 1 — agora sim, procuramos e deletamos
        for (int i = 0; i < tarefas.size(); i++) {
            if (tarefas.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                tarefas.remove(i);
                return true;
            }
        }
        return false; // linha de segurança, tecnicamente não deveria ser alcançada
    }

    public boolean editarTarefa(int indice, String titulo, String descricao, LocalDate prazo, Tarefa.StatusTarefa status) {
        if (indice < 0 || indice >= tarefas.size()) {
            return false;
        }

        Tarefa tarefa = tarefas.get(indice);

        if (titulo != null && !titulo.trim().isEmpty()) {
            tarefa.setTitulo(titulo);
        }

        if (descricao != null) {
            tarefa.setDescricao(descricao);
        }

        if (prazo != null) {
            tarefa.setPrazo(prazo);
        }

        if (status != null) {
            tarefa.setStatus(status);
        }

        return true;
    }

    public List<Tarefa> getTarefas() {
        return new ArrayList<>(tarefas);
    }
}