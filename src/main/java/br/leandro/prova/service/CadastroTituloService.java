package br.leandro.prova.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.leandro.prova.model.StatusTitulo;
import br.leandro.prova.model.Titulo;
import br.leandro.prova.repository.TitulosRepository;
import br.leandro.prova.repository.filter.TituloFilter;

@Service
public class CadastroTituloService {

	private final TitulosRepository titulos;

	public CadastroTituloService(TitulosRepository titulos) {
		this.titulos = titulos;
	}

	public void salvar(Titulo titulo) {
		try {
			titulos.save(titulo);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}

	public void excluir(Long codigo) {
		titulos.deleteById(codigo);
	}

	public Titulo buscarPorId(Long codigo){
		return titulos.getOne(codigo);
	}

	public String receber(Long codigo) {
		Titulo titulo = titulos.getOne(codigo);
		titulo.setStatus(StatusTitulo.RECEBIDO);
		titulos.save(titulo);
		
		return StatusTitulo.RECEBIDO.getDescricao();
	}
	public List<Titulo> filtrar(TituloFilter filtro) {
		if(filtro.getDescricao() == null ){
			return listarTodosTitulos();
		}else{
			return titulos.findByDescricaoContaining(filtro.getDescricao());
		}
	}

	public List<Titulo> listarTodosTitulos() {
		return titulos.findAll();
	}
}