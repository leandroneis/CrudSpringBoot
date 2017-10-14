package br.leandro.prova.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.leandro.prova.model.Titulo;

public interface Titulos extends JpaRepository<Titulo, Long>{

	public List<Titulo> findByDescricaoContaining(String descricao);
}
