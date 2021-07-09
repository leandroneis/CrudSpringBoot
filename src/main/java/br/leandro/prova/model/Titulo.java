package br.leandro.prova.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
@SequenceGenerator(name = Titulo.SEQUENCE_NAME, sequenceName = Titulo.SEQUENCE_NAME, initialValue = 1, allocationSize = 1)
public class Titulo  {

	public static final String SEQUENCE_NAME = "SEQUENCIA_TITULO";
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	private Long codigo;

	@NotBlank(message = "A Descrição é obrigatória")
	@Size(max = 60, message = "A descrição não pode conter mais de 60 caracteres")
	private String descricao;

	@NotNull(message = "A Data de vencimento é obrigatória")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;

	@NotNull(message = "O Valor é obrigatório")
	@DecimalMin(value = "0.01", message = "Valor não pode ser menor que 0,01")
	@DecimalMax(value = "9999999.99", message = "Valor não pode ser maior que 9.999.999,99")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valor;

	@Enumerated(EnumType.STRING)
	private StatusTitulo status;
	
	@Enumerated(EnumType.STRING)
	private TipoLancamento tipoLancamento;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public StatusTitulo getStatus() {
		return status;
	}

	public void setStatus(StatusTitulo status) {
		this.status = status;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public boolean isPendente() {
		return StatusTitulo.PENDENTE.equals(this.status);
	}
	
	public boolean isReceitas() {
		return TipoLancamento.RECEITAS.equals(this.tipoLancamento);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Titulo other = (Titulo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Titulo{" +
				"codigo=" + codigo +
				", descricao='" + descricao + '\'' +
				", dataVencimento=" + dataVencimento +
				", valor=" + valor +
				", status=" + status +
				", tipoLancamento=" + tipoLancamento +
				'}';
	}
}
