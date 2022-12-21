package br.com.banco.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name="transferencia")
@Table(name="transferencia")
@SequenceGenerator(name = "seq_transferencia", sequenceName = "seq_transferencia", allocationSize = 1, initialValue = 7)
public class TransferenciaModal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transferencia")
    private long id;

    @Column(nullable = false)
    private LocalDateTime dataTransferencia;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = true)
    private String nome_operador_transacao;

    @Column(nullable = false)
    private Long conta_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(LocalDateTime dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome_operador_transacao() {
        return nome_operador_transacao;
    }

    public void setNome_operador_transacao(String nome_operador_transacao) {
        this.nome_operador_transacao = nome_operador_transacao;
    }

    public Long getConta_id() {
        return conta_id;
    }

    public void setConta_id(Long conta_id) {
        this.conta_id = conta_id;
    }
}
