import java.io.Serializable;
import java.time.LocalDate;

public class Tarefa implements Serializable {
    private static final long serialVersionUID = 1L;
    public enum StatusTarefa {
        PENDENTE,
        EM_ANDAMENTO,
        CONCLUIDA
    }

    private String titulo;
    private String descricao;
    private LocalDate prazo;
    private StatusTarefa status;

    public Tarefa() {
    }

    public Tarefa(String titulo, String descricao, LocalDate prazo, StatusTarefa status) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Tarefa{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", prazo=" + prazo +
                ", status=" + status +
                '}'; }
    }
