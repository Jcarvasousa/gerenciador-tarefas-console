import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorTarefas {
    private static final String ARQUIVO_TAREFAS = "tarefas.dat";
    private List<Tarefa> tarefas;

    public GerenciadorTarefas() {
        this.tarefas = new ArrayList<>();
        carregarDeArquivo();
    }

    public void criarTarefa(String titulo, String descricao, LocalDate prazo, Tarefa.StatusTarefa status) {
        Tarefa tarefa = new Tarefa(titulo, descricao, prazo, status);
        tarefas.add(tarefa);
        salvarEmArquivo();
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
        salvarEmArquivo();
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
                salvarEmArquivo();
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

        salvarEmArquivo();
        return true;
    }

    public void salvarEmArquivo() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO_TAREFAS))) {
            out.writeObject(tarefas);
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas no arquivo: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void carregarDeArquivo() {
        File arquivo = new File(ARQUIVO_TAREFAS);
        if (!arquivo.exists()) {
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            Object obj = in.readObject();
            if (obj instanceof List<?>) {
                tarefas = (List<Tarefa>) obj;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Classe não encontrada ao carregar tarefas: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro ao carregar tarefas do arquivo: " + e.getMessage());
        }
    }

    public List<Tarefa> getTarefas() {
        return new ArrayList<>(tarefas);
    }
}