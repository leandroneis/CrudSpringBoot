package br.leandro.prova.model;

public enum TipoLancamento {


	DESPESAS("Despesas"),
	RECEITAS("Receitas");

private String descricao;

TipoLancamento(String descricao) {
	this.descricao = descricao;
}

public String getDescricao() {
	return descricao;
}

}
